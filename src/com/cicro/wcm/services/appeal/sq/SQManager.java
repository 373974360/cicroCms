package com.cicro.wcm.services.appeal.sq;

import com.cicro.util.CalculateNumber;
import com.cicro.util.DateUtil;
import com.cicro.util.RandomStrg;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.bean.appeal.area.AreaBean;
import com.cicro.wcm.bean.appeal.count.CountBean;
import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
import com.cicro.wcm.bean.appeal.model.ModelBean;
import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
import com.cicro.wcm.bean.appeal.sq.ProcessBean;
import com.cicro.wcm.bean.appeal.sq.SQAttachment;
import com.cicro.wcm.bean.appeal.sq.SQBean;
import com.cicro.wcm.bean.appeal.sq.SQCustom;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.system.assist.TagsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.dao.appeal.sq.SQDAO;
import com.cicro.wcm.services.appeal.area.AreaManager;
import com.cicro.wcm.services.appeal.cpDept.CpDeptManager;
import com.cicro.wcm.services.appeal.cpUser.CpUserManager;
import com.cicro.wcm.services.appeal.model.ModelManager;
import com.cicro.wcm.services.appeal.purpose.PurposeManager;
import com.cicro.wcm.services.appeal.satisfaction.SatisfactionManager;
import com.cicro.wcm.services.appeal.sq.SQSMSFactory;
import com.cicro.wcm.services.org.role.RoleUserManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SQManager {
	private static String defalut_randon_str = "A-Z0-9";

	public SQManager() {
	}

	public static List<SQBean> getBroSQListByMemberID(String me_id) {
		List sl = SQDAO.getBroSQListByMemberID(me_id);
		if (sl != null && sl.size() > 0) {
			for (int i = 0; i < sl.size(); ++i) {
				SQBean sb = (SQBean) sl.get(i);
				ModelBean mb = ModelManager.getModelBean(sb.getModel_id());
				if (mb != null) {
					sb.setModel_cname(mb.getModel_cname());
				}

				if (sb.getSq_status() == 3) {
					sb.setSq_status_name("已办结");
				} else {
					sb.setSq_status_name("未办结");
				}
			}
		}

		return sl;
	}

	public static String getBrowserSQCount(String con) {
		HashMap m = new HashMap();
		m.put("con", con);
		return SQDAO.getBrowserSQCount(m);
	}

	public static List<SQBean> getBrowserSQList(String con, String current_page, String page_sizes, String order_by) {
		HashMap m = new HashMap();
		int start_page = 1;
		int page_size = 15;
		if (current_page != null && !"".equals(current_page) && !"0".equals(current_page)) {
			try {
				start_page = Integer.parseInt(current_page);
			} catch (Exception var11) {
				var11.printStackTrace();
			}
		}

		if (page_sizes != null && !"".equals(page_sizes)) {
			try {
				page_size = Integer.parseInt(page_sizes);
			} catch (Exception var10) {
				var10.printStackTrace();
			}
		}

		m.put("con", con);
		m.put("start_num", String.valueOf((start_page - 1) * page_size));
		m.put("page_size", String.valueOf(page_size));
		m.put("order_by", order_by);
		List sl = SQDAO.getBrowserSQList(m);
		if (sl != null && sl.size() > 0) {
			for (int i = 0; i < sl.size(); ++i) {
				SQBean sb = (SQBean) sl.get(i);
				if (sb.getSq_status() == 3) {
					sb.setSq_status_name("已办结");
				} else {
					sb.setSq_status_name("未办结");
				}

				SetOtherCNameInSQBean(sb);
			}
		}

		return sl;
	}

	public static SQBean searchBrowserSQBean(String sq_code, String query_code) {
		HashMap m = new HashMap();
		m.put("sq_code", sq_code);
		m.put("query_code", query_code);
		return SQDAO.searchBrowserSQBean(m);
	}

	public static long getAppealFileSize() {
		try {
			return Long.parseLong(JconfigUtilContainer.bashConfig().getProperty("appeal", "", "file_size"));
		} catch (Exception var1) {
			var1.printStackTrace();
			return 2097152L;
		}
	}

	public static boolean isAppealManager(int user_id) {
		String user_ids = "," + RoleUserManager.getRoleIDSByUserAPP(String.valueOf(user_id), "appeal", "") + ",";
		return user_ids
				.contains("," + JconfigUtilContainer.systemRole().getProperty("appeal_manager", "", "role_id") + ",");
	}

	public static String getSqCount(Map<String, String> m, int user_id) {
		setTimeCon(m);
		getSqSearchCon(m, user_id);
		return SQDAO.getSqCount(m);
	}

	public static List<SQBean> getSqList(Map<String, String> m, int user_id) {
		setTimeCon(m);
		getSqSearchCon(m, user_id);
		List l = SQDAO.getSqList(m);
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); ++i) {
				CpDeptBean cdb2;
				if (((SQBean) l.get(i)).getDo_dept() != 0) {
					cdb2 = CpDeptManager.getCpDeptBean(((SQBean) l.get(i)).getDo_dept());
					if (cdb2 != null) {
						((SQBean) l.get(i)).setDo_dept_name(cdb2.getDept_name());
					}
				}

				if (((SQBean) l.get(i)).getSubmit_dept_id() != 0) {
					cdb2 = CpDeptManager.getCpDeptBean(((SQBean) l.get(i)).getSubmit_dept_id());
					if (cdb2 != null) {
						((SQBean) l.get(i)).setSubmit_name(cdb2.getDept_name());
					}
				}
			}
		}

		return l;
	}

	public static void getSqSearchCon(Map<String, String> m, int user_id) {
		String model_ids = ModelManager.getModelIDSByUserID(user_id);
		if (!m.containsKey("model_id")) {
			m.put("model_id", model_ids);
		}

		if (isAppealManager(user_id)) {
			m.put("is_admin", String.valueOf(user_id));
		}

		if ("dsj".equals(m.get("page_type"))) {
			m.put("dsj_sql", getAutoConSq(model_ids, user_id));
		} else {
			m.remove("page_type");
		}

		m.put("do_dept", String.valueOf(CpUserManager.getSQDeptIDbyUserID(user_id)));
	}

	public static String getTransactSQCount(Map<String, String> m, int user_id) {
		setTimeCon(m);
		String model_ids = ModelManager.getModelIDSByUserID(user_id);
		if (!m.containsKey("model_id")) {
			m.put("model_id", model_ids);
		}

		m.put("do_dept", String.valueOf(CpUserManager.getSQDeptIDbyUserID(user_id)));
		return SQDAO.getTransactSQCount(m);
	}

	public static String getAutoConSq(String model_ids, int user_id) {
		String sql = "";
		String[] tempA = model_ids.split(",");

		for (int i = 0; i < tempA.length; ++i) {
			if (i > 0) {
				sql = sql + " or ";
			}

			sql = sql + "(model_id = " + tempA[i] + " and step_id <= "
					+ ModelManager.getWFIDByMIDUserID(tempA[i], String.valueOf(user_id)) + ")";
		}

		if (!"".equals(sql)) {
			sql = " and (" + sql + ")";
		}

		return sql;
	}

	public static List<SQBean> getTransactSQList(Map<String, String> m, int user_id) {
		setTimeCon(m);
		String model_ids = ModelManager.getModelIDSByUserID(user_id);
		if (!m.containsKey("model_id")) {
			m.put("model_id", model_ids);
		}

		m.put("do_dept", String.valueOf(CpUserManager.getSQDeptIDbyUserID(user_id)));
		List l = SQDAO.getTransactSQList(m);
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); ++i) {
				CpDeptBean cdb2;
				if (((SQBean) l.get(i)).getDo_dept() != 0) {
					cdb2 = CpDeptManager.getCpDeptBean(((SQBean) l.get(i)).getDo_dept());
					if (cdb2 != null) {
						((SQBean) l.get(i)).setDo_dept_name(cdb2.getDept_name());
					}
				}

				if (((SQBean) l.get(i)).getSubmit_dept_id() != 0) {
					cdb2 = CpDeptManager.getCpDeptBean(((SQBean) l.get(i)).getSubmit_dept_id());
					if (cdb2 != null) {
						((SQBean) l.get(i)).setSubmit_name(cdb2.getDept_name());
					}
				}
			}
		}

		return l;
	}

	public static void setTimeCon(Map<String, String> m) {
		if (m.containsKey("search_date")) {
			if ("day".equals(m.get("search_date"))) {
				m.put("sq_dtime", DateUtil.getCurrentDate() + " 00:00:00");
			}

			if ("week".equals(m.get("search_date"))) {
				m.put("sq_dtime", DateUtil.getDateBefore(DateUtil.getCurrentDate(), 7) + " 00:00:00");
			}

			if ("month".equals(m.get("search_date"))) {
				m.put("sq_dtime", DateUtil.getDateBefore(DateUtil.getCurrentDate(), 30) + " 00:00:00");
			}

			if ("year".equals(m.get("search_date"))) {
				m.put("sq_dtime", DateUtil.getDateBefore(DateUtil.getCurrentDate(), 365) + " 00:00:00");
			}
		}

	}

	public static SQBean getSqBean(int sq_id) {
		SQBean sb = SQDAO.getSqBean(sq_id);
		SetOtherCNameInSQBean(sb);
		return sb;
	}

	public static SQBean getBrowserSQBean(int sq_id, String me_id) {
		SQBean sb = SQDAO.getBrowserSQBean(sq_id, me_id);
		SetOtherCNameInSQBean(sb);
		return sb;
	}

	public static void SetOtherCNameInSQBean(SQBean sb) {
		if (sb != null) {
			if (sb.getModel_id() != 0) {
				ModelBean ab = ModelManager.getModelBean(sb.getModel_id());
				if (ab != null) {
					sb.setModel_cname(ab.getModel_cname());
				}
			}

			if (sb.getPur_id() != 0) {
				PurposeBean ab1 = PurposeManager.getPurposeByID(String.valueOf(sb.getPur_id()));
				if (ab1 != null) {
					sb.setPur_name(ab1.getPur_name());
				}
			}

			if (sb.getDo_dept() != 0) {
				CpDeptBean ab2 = CpDeptManager.getCpDeptBean(sb.getDo_dept());
				if (ab2 != null) {
					sb.setDo_dept_name(ab2.getDept_name());
				}
			}

			if (sb.getArea_id() != 0) {
				AreaBean ab3 = AreaManager.getAreaBean(sb.getArea_id());
				if (ab3 != null) {
					sb.setArea_name(ab3.getArea_cname());
				}
			}
		}

	}

	public static List<SQCustom> getSQCustomList(int sq_id) {
		return SQDAO.getSQCustomList(sq_id);
	}

	public static String getSQCustomValue(int sq_id, String cu_key) {
		return SQDAO.getSQCustomValue(sq_id, cu_key);
	}

	public static String getQueryCode(int model) {
		ModelBean mb = ModelManager.getModelBean(model);
		return RandomStrg.getRandomStr(defalut_randon_str, String.valueOf(mb.getQuery_num()));
	}

	public static String getSQCode(int model) {
		ModelBean mb = ModelManager.getModelBean(model);
		return mb.getCode_pre() + DateUtil.getCurrentDateTime(mb.getCode_rule())
				+ RandomStrg.getRandomStr(defalut_randon_str, String.valueOf(mb.getCode_num()));
	}

	public static synchronized SQBean insertSQ(SQBean sb, SQAttachment sqa) {
		sb.setSubmit_dept_id(sb.getDo_dept());
		int sq_id = PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_SQ_TABLE_NAME);
		sb.setSq_id(sq_id);
		sb.setQuery_code(getQueryCode(sb.getModel_id()));
		ModelBean mb = ModelManager.getModelBean(sb.getModel_id());
		sb.setPublish_status(mb.getIs_auto_publish());
		sb.setSq_code(getSQCode(sb.getModel_id()));
		if ("".equals(sb.getSq_dtime())) {
			sb.setSq_dtime(DateUtil.getCurrentDateTime());
		}

		sb.setTime_limit(mb.getTime_limit());
		if (mb.getIs_sort() == 1) {
			sb.setDo_dept(0);
		}

		sb.setSq_all_number(SQDAO.getSQYearNum() + 1);
		sb.setSq_number(SQDAO.getSQNumber(sb.getModel_id(), sb.getSq_dtime().substring(0, 10)) + 1);
		if (SQDAO.insertSQ(sb)) {
			if (sqa != null) {
				sqa.setSq_id(sq_id);
				sqa.setRelevance_type(0);
				SQDAO.insertSQAtta(sqa);
			}

			if ("sms".equals(mb.getRemind_type()) || "email".equals(mb.getRemind_type())) {
				SQSMSFactory.sendSMSForAdd(sb, mb);
			}

			return sb;
		} else {
			return null;
		}
	}

	public static boolean insertSQCursom(List<SQCustom> l) {
		return SQDAO.insertSQCursom(l);
	}

	public static boolean setSQClickCount(int sq_id) {
		HashMap m = new HashMap();
		m.put("sq_id", Integer.valueOf(sq_id));
		m.put("lasthit_dtime", DateUtil.getCurrentDateTime());
		return SQDAO.setSQClickCount(m);
	}

	public static boolean updateSQ(SQBean sb, String tag_ids, SettingLogsBean stl) {
		if (SQDAO.updateSQ(sb, stl)) {
			SQDAO.deleteSQTag(String.valueOf(sb.getSq_id()));
			SQDAO.insertSQTag(String.valueOf(sb.getSq_id()), tag_ids);
			return true;
		} else {
			return false;
		}
	}

	public static boolean updateSQCustom(List<SQCustom> l) {
		return SQDAO.updateSQCustom(l);
	}

	public static boolean updateStatus(Map<String, String> m) {
		if (m.containsKey("accept_dtime")) {
			m.put("accept_dtime", DateUtil.getCurrentDateTime());
		}

		if (m.containsKey("over_dtime")) {
			String over_dtime = m.get("over_dtime");
			if (over_dtime != null && !"".equals(over_dtime)) {
				m.put("over_dtime", over_dtime);
			} else {
				m.put("over_dtime", DateUtil.getCurrentDateTime());
			}
		}

		if (m.containsKey("publish_dtime")) {
			if ("1".equals(m.get("publish_status"))) {
				m.put("publish_dtime", DateUtil.getCurrentDateTime());
			} else {
				m.put("publish_dtime", "");
			}
		}

		if (!SQDAO.updateStatus(m)) {
			return false;
		} else {
			String model_id = (String) m.get("model_id");
			ModelBean mb = ModelManager.getModelBean(Integer.parseInt(model_id));
			if ("sms".equals(mb.getRemind_type()) || "email".equals(mb.getRemind_type())) {
				String pro_type = (String) m.get("pro_type");
				String publish_status;
				if ("1".equals(pro_type) || "100".equals(pro_type)) {
					publish_status = (String) m.get("sq_status");
					if ("3".equals(publish_status)) {
						SQSMSFactory.sendSMSForResult(getSqBean(Integer.parseInt((String) m.get("sq_id"))), mb);
					}
				}

				if ("13".equals(pro_type)) {
					publish_status = (String) m.get("supervise_flag");
					if ("1".equals(publish_status)) {
						SQSMSFactory.sendSMSForSupervise(getSqBean(Integer.parseInt((String) m.get("sq_id"))), mb);
					}
				}

				if ("2".equals(pro_type) || "3".equals(pro_type) || "4".equals(pro_type)) {
					publish_status = (String) m.get("prove_type");
					if ("2".equals(publish_status) || "3".equals(publish_status) || "4".equals(publish_status)) {
						SQSMSFactory.sendSMSForTrans(getSqBean(Integer.parseInt((String) m.get("sq_id"))), mb);
					}
				}

				if ("102".equals(pro_type)) {
					publish_status = (String) m.get("publish_status");
					if ("1".equals(publish_status)) {
						SQSMSFactory.sendSMSForPublish(getSqBean(Integer.parseInt((String) m.get("sq_id"))), mb);
					}
				}
			}

			return true;
		}
	}

	public static boolean deleteSQ(String sq_ids, SettingLogsBean stl) {
		return SQDAO.deleteSQ(sq_ids, stl);
	}

	public static boolean saveScore(String sq_id, String sat_score_str, String raty_score_str) {
		String[] temp_sat = sat_score_str.split(",");
		String[] temp_raty = raty_score_str.split(",");
		int sat_score = 0;

		for (int m = 0; m < temp_sat.length; ++m) {
			sat_score += Integer.parseInt(temp_sat[m]) * Integer.parseInt(temp_raty[m]) / 10;
		}

		HashMap var7 = new HashMap();
		var7.put("sq_id", sq_id);
		var7.put("sat_score", String.valueOf(sat_score));
		if (SQDAO.updateStatus(var7)) {
			SatisfactionManager.insertSatRecord(sq_id, temp_raty);
			return true;
		} else {
			return false;
		}
	}

	public static List<SQBean> getReduplicateSQList(Map<String, String> m) {
		return SQDAO.getReduplicateSQList(m);
	}

	public static boolean updateProcessNote(Map<String, String> m, SettingLogsBean stl) {
		return SQDAO.updateProcessNote(m, stl);
	}

	public static List<ProcessBean> getProcessListBySqID(int sq_id) {
		return SQDAO.getProcessListBySqID(sq_id);
	}

	public static boolean insertProcess(ProcessBean pb, SQAttachment sqa) {
		try {
			pb.setDept_name(CpDeptManager.getCpDeptBean(pb.getDo_dept()).getDept_name());
		} catch (Exception var3) {
			var3.printStackTrace();
			System.out.println("cp_dept id " + pb.getDo_dept() + " is null");
		}

		int pro_id = PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_PROCESS_TABLE_NAME);
		pb.setPro_id(pro_id);
		pb.setPro_dtime(DateUtil.getCurrentDateTime());
		if (SQDAO.insertProcess(pb)) {
			if (sqa != null) {
				sqa.setSq_id(pro_id);
				sqa.setRelevance_type(1);
				SQDAO.insertSQAtta(sqa);
			}

			return true;
		} else {
			return false;
		}
	}

	public static String withdrawSQForProcess(String sq_id, int user_id) {
		int detp_id = CpUserManager.getSQDeptIDbyUserID(user_id);
		ProcessBean pb = SQDAO.getLastProcessBySqID(sq_id);
		if (pb.getUser_id() != user_id && pb.getDo_dept() != detp_id) {
			return "process_is_worked";
		} else {
			HashMap m = new HashMap();
			m.put("sq_id", sq_id);
			m.put("do_dept", String.valueOf(pb.getOld_dept_id()));
			m.put("sq_status", String.valueOf(pb.getOld_sq_status()));
			m.put("prove_type", String.valueOf(pb.getOld_prove_type()));
			return SQDAO.updateStatus(m) ? (SQDAO.deleteProcessByProID(pb.getPro_id()) ? "true" : "false") : "false";
		}
	}

	public static List<TagsBean> getSQTagList(int sq_id) {
		return SQDAO.getSQTagList(sq_id);
	}

	public static boolean insertSQTag(String sq_id, String tag_ids) {
		return SQDAO.deleteSQTag(sq_id) ? SQDAO.insertSQTag(sq_id, tag_ids) : false;
	}

	public static List<SQAttachment> getSQAttachmentList(String sq_ids, String relevance_type) {
		return SQDAO.getSQAttachmentList(sq_ids, relevance_type);
	}

	public static boolean insertSQAtta(SQAttachment sqa) {
		return SQDAO.insertSQAtta(sqa);
	}

	public static boolean getAllSQListByYear() {
		for (int i = 2000; i < 2020; ++i) {
			List l = SQDAO.getAllSQListByYear(String.valueOf(i));
			if (l != null && l.size() > 0) {
				int num = 1;
				HashMap m = new HashMap();
				System.out.println(l.size());

				for (int j = 0; j < l.size(); ++j) {
					m.put("sq_id", String.valueOf(((SQBean) l.get(j)).getSq_id()));
					m.put("sq_all_number", String.valueOf(num));
					m.put("sq_number", String
							.valueOf(SQDAO.getSQNumber(((SQBean) l.get(j)).getModel_id(), String.valueOf(i)) + 1));
					SQDAO.updateSQNumber(m);
					++num;
				}
			}
		}

		return true;
	}

	public static List<CountBean> getSqFinishCountForDept(String model_ids, int row_count) {
		if (row_count == 0) {
			row_count = 10;
		}

		HashMap m = new HashMap();
		m.put("sq_status", "3");
		if (model_ids != null && !"".equals(model_ids)) {
			m.put("model_ids", model_ids);
		}

		List l = SQDAO.getSqFinishCountForDept(m);
		ArrayList count_l = new ArrayList();
		if (l != null && l.size() > 0) {
			if (l.size() < row_count) {
				row_count = l.size();
			}

			int i = 1;
			Iterator var7 = l.iterator();

			while (var7.hasNext()) {
				CountBean cb = (CountBean) var7.next();

				try {
					if (i > row_count) {
						break;
					}

					cb.setDept_name(CpDeptManager.getCpDeptName(cb.getDept_id()));
					count_l.add(cb);
					++i;
				} catch (Exception var9) {
					var9.printStackTrace();
				}
			}
		}

		return count_l;
	}

	public static List<CountBean> getAppealFinishRate(Map<String, String> m, int row_count) {
		List l = SQDAO.getSqFinishCountForDept(m);
		m.put("sq_status", "3");
		List bj_list = SQDAO.getSqFinishCountForDept(m);
		HashMap bj_map = new HashMap();
		CountBean cb;
		Iterator var6;
		if (bj_list != null && bj_list.size() > 0) {
			var6 = bj_list.iterator();

			while (var6.hasNext()) {
				cb = (CountBean) var6.next();
				bj_map.put(Integer.valueOf(cb.getDept_id()), cb);
			}
		}

		if (l != null && l.size() > 0) {
			var6 = l.iterator();

			while (var6.hasNext()) {
				cb = (CountBean) var6.next();
				cb.setDept_name(CpDeptManager.getCpDeptName(cb.getDept_id()));
				if (bj_map.containsKey(Integer.valueOf(cb.getDept_id()))) {
					CountBean bj_cb = (CountBean) bj_map.get(Integer.valueOf(cb.getDept_id()));
					cb.setF_temp_count(getRate(bj_cb.getCount(), cb.getCount()));
					cb.setCountend(bj_cb.getCount());
					cb.setCountendp(cb.getF_temp_count() + "%");
					cb.setCountwei(
							String.valueOf(Integer.parseInt(cb.getCount()) - Integer.parseInt(bj_cb.getCount())));
				}
			}

			Collections.sort(l, new AppealCountComparatorForFloat());
			if (l.size() < row_count) {
				row_count = l.size();
			}

			return l.subList(0, row_count);
		} else {
			return null;
		}
	}

	public static List<CountBean> getAppealFinishRateForAlarm(Map<String, String> m, int row_count) {
		List alarm_list = SQDAO.getSqFinishCountForDept(m);
		m.remove("alarm_flag");
		List l = SQDAO.getSqFinishCountForDept(m);
		HashMap alarm_map = new HashMap();
		CountBean cb;
		Iterator var6;
		if (alarm_list != null && alarm_list.size() > 0) {
			var6 = alarm_list.iterator();

			while (var6.hasNext()) {
				cb = (CountBean) var6.next();
				alarm_map.put(Integer.valueOf(cb.getDept_id()), cb);
			}
		}

		if (l != null && l.size() > 0) {
			var6 = l.iterator();

			while (var6.hasNext()) {
				cb = (CountBean) var6.next();
				cb.setDept_name(CpDeptManager.getCpDeptName(cb.getDept_id()));
				cb.setCountwei("0");
				cb.setCount_red("0");
				if (alarm_map.containsKey(Integer.valueOf(cb.getDept_id()))) {
					CountBean alarm_cb = (CountBean) alarm_map.get(Integer.valueOf(cb.getDept_id()));
					cb.setCount_red(alarm_cb.getCount());
					cb.setCountwei(alarm_cb.getCount());
				}
			}

			Collections.sort(l, new AppealCountComparatorForDESC());
			if (l.size() < row_count) {
				row_count = l.size();
			}

			return l.subList(0, row_count);
		} else {
			return null;
		}
	}

	public static float getRate(String strUp, String strDown) {
		try {
			float e = Float.parseFloat(strUp);
			float floatDown = Float.parseFloat(strDown);
			int intResult = (int) ((double) (e / floatDown) * 10000.0D);
			return (float) intResult / 100.0F;
		} catch (Exception var5) {
			return 0.0F;
		}
	}

	public static List<CountBean> getAppealFinishRateForSort(List<CountBean> l, String sort_type, int row_count) {
		if ("desc".equals(sort_type)) {
			Collections.sort(l, new AppealCountComparatorForDESC());
		} else {
			Collections.sort(l, new AppealCountComparatorForASC());
		}

		ArrayList count_l = new ArrayList();
		if (l != null && l.size() > 0) {
			if (l.size() < row_count) {
				row_count = l.size();
			}

			int i = 1;

			for (Iterator var6 = l.iterator(); var6.hasNext(); ++i) {
				CountBean cb = (CountBean) var6.next();
				if (i > row_count) {
					break;
				}

				count_l.add(cb);
			}
		}

		return count_l;
	}

	public static List<CountBean> getAppealFinishRate2(String model_id) {
		HashMap m = new HashMap();
		m.put("model_ids", model_id);
		List l = SQDAO.getSqFinishCountForDept(m);
		ArrayList count_l = new ArrayList();
		if (l != null && l.size() > 0) {
			Iterator var5 = l.iterator();

			while (var5.hasNext()) {
				CountBean cb = (CountBean) var5.next();

				try {
					cb.setDept_name(CpDeptManager.getCpDeptName(cb.getDept_id()));
					m.put("bj_con", "bj_con");
					m.put("do_dept", String.valueOf(cb.getDept_id()));
					String e = SQDAO.getSQStatistics(m);
					cb.setCountendp(CalculateNumber.getRate(e, cb.getCount()));
					cb.setCountwei(String.valueOf(Integer.parseInt(cb.getCount()) - Integer.parseInt(e)));
					count_l.add(cb);
				} catch (Exception var7) {
					var7.printStackTrace();
				}
			}
		}

		return count_l;
	}

	public static SQBean getSQSimpleBean(int sq_id) {
		return SQDAO.getSQSimpleBean(sq_id);
	}

	public static List<CountBean> getSQSatisfaction(String model_id, int row_count) {
		HashMap m = new HashMap();
		m.put("model_id", model_id);
		m.put("sat_score", "6");
		List l = SQDAO.getSQSatisfaction(m);
		ArrayList count_l = new ArrayList();
		if (l != null && l.size() > 0) {
			if (l.size() < row_count) {
				row_count = l.size();
			}

			int i = 1;
			Iterator var7 = l.iterator();

			while (var7.hasNext()) {
				CountBean cb = (CountBean) var7.next();

				try {
					if (i > row_count) {
						break;
					}

					cb.setDept_name(CpDeptManager.getCpDeptName(cb.getDept_id()));
					m.put("do_dept", String.valueOf(cb.getDept_id()));
					cb.setCount_normal(SQDAO.getSQSatisfactionCount(m));
					count_l.add(cb);
					++i;
				} catch (Exception var9) {
					var9.printStackTrace();
				}
			}
		}

		return count_l;
	}

	public static List<CountBean> getSQStatisticsForModel(Map<String, String> m, int row_count) {
		List l = SQDAO.getSQStatisticsForModel(m);
		if (l != null && l.size() > 0 && l.size() > row_count) {
			l = l.subList(0, row_count);
		}

		return l;
	}

	public static String getModelIdByDept_id(String dept_id) {
		return SQDAO.getModelIdByDept_id(dept_id);
	}

	public static void main(String[] args) {
		List l = getSqFinishCountForDept("30", 5);
		System.out.println(((CountBean) l.get(0)).getCount() + "  " + ((CountBean) l.get(0)).getDept_id() + "  "
				+ ((CountBean) l.get(0)).getDept_name());
	}

	public static class AppealCountComparatorForASC implements Comparator<Object> {
		public int compare(Object o1, Object o2) {

			CountBean cgb1 = (CountBean) o1;
			CountBean cgb2 = (CountBean) o2;
			if (Integer.parseInt(cgb1.getCountwei()) > Integer.parseInt(cgb2.getCountwei())) {
				return 1;
			} else {
				if (cgb1.getCountwei() == cgb2.getCountwei()) {
					return 0;
				} else {
					return -1;
				}
			}
		}
	}

	public static class AppealCountComparatorForFloat implements Comparator<Object> {
		public int compare(Object o1, Object o2) {

			CountBean cgb1 = (CountBean) o1;
			CountBean cgb2 = (CountBean) o2;
			if (cgb2.getF_temp_count() > cgb1.getF_temp_count()) {
				return 1;
			} else {
				if (cgb1.getF_temp_count() == cgb2.getF_temp_count()) {
					return 0;
				} else {
					return -1;
				}
			}
		}
	}

	public static class AppealCountComparatorForDESC implements Comparator<Object> {
		public int compare(Object o1, Object o2) {

			CountBean cgb1 = (CountBean) o1;
			CountBean cgb2 = (CountBean) o2;
			if (Integer.parseInt(cgb2.getCountwei()) > Integer.parseInt(cgb1.getCountwei())) {
				return 1;
			} else {
				if (cgb1.getCountwei() == cgb2.getCountwei()) {
					return 0;
				} else {
					return -1;
				}
			}
		}
	}
}
