package com.cicro.project.dz_memberAction;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.template.TurnPageBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberInfoData {
	private static int cur_page = 1;
	private static int page_size = 15;

	public static void getMemberInfoSearchCon(String params, Map<String, String> con_map) {
		String orderby = "addTime desc";
		String[] tempA = params.split(";");
		for (int i = 0; i < tempA.length; i++) {
			if (tempA[i].toLowerCase().startsWith("memberid=")) {
				String memberId = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

				if ((!"".equals(memberId)) && (!memberId.startsWith("$memberId")) && (FormatUtil.isValiditySQL(memberId))) {
					con_map.put("memberId", memberId);
				}
			}
			if (tempA[i].toLowerCase().startsWith("type=")) {
				String type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

				if ((!"".equals(type)) && (!type.startsWith("$type")) && (FormatUtil.isValiditySQL(type))) {
					con_map.put("type", type);
				}
			}
			if (tempA[i].toLowerCase().startsWith("infoid=")) {
				String infoId = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

				if ((!"".equals(infoId)) && (!infoId.startsWith("$infoId")) && (FormatUtil.isValiditySQL(infoId))) {
					con_map.put("infoId", infoId);
				}
			}
			if (tempA[i].toLowerCase().startsWith("ipaddr=")) {
				String IpAddr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

				if ((!"".equals(IpAddr)) && (!IpAddr.startsWith("$IpAddr")) && (FormatUtil.isValiditySQL(IpAddr))) {
					con_map.put("IpAddr", IpAddr);
				}
			}
			if (tempA[i].toLowerCase().startsWith("byzd=")) {
				String byzd = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

				if ((!"".equals(byzd)) && (!byzd.startsWith("$byzd")) && (FormatUtil.isValiditySQL(byzd))) {
					con_map.put("byzd", byzd);
				}
			}
			if (tempA[i].toLowerCase().startsWith("byzd1=")) {
				String byzd1 = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

				if ((!"".equals(byzd1)) && (!byzd1.startsWith("$byzd1")) && (FormatUtil.isValiditySQL(byzd1))) {
					con_map.put("byzd1", byzd1);
				}
			}
			if (tempA[i].toLowerCase().startsWith("orderby=")) {
				String o_by = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
				if ((!"".equals(o_by)) && (!o_by.startsWith("$orderby")) && (FormatUtil.isValiditySQL(o_by))) {
					orderby = o_by;
				}
			}
			if (tempA[i].toLowerCase().startsWith("size=")) {
				String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
				if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
					page_size = Integer.parseInt(ps);
			}
			if (tempA[i].toLowerCase().startsWith("cur_page=")) {
				String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
				if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
					cur_page = Integer.parseInt(cp);
			}
		}
		con_map.put("current_data", DateUtil.getCurrentDate());
		con_map.put("page_size", page_size + "");
		con_map.put("current_page", cur_page + "");
		con_map.put("orderby", orderby);
		System.out.println(con_map);
	}

	public static TurnPageBean getMemberInfoCount(String params) {
		Map con_map = new HashMap();
		getMemberInfoSearchCon(params, con_map);

		TurnPageBean tpb = new TurnPageBean();
		tpb.setCount(Integer.parseInt(MemberInfoManager.getMemberInfoCount(con_map)));
		tpb.setCur_page(cur_page);
		tpb.setPage_size(page_size);
		tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);

		if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
			tpb.setPage_count(tpb.getPage_count() - 1);
		}
		if (cur_page > 1) {
			tpb.setPrev_num(cur_page - 1);
		}
		tpb.setNext_num(tpb.getPage_count());
		if (cur_page < tpb.getPage_count()) {
			tpb.setNext_num(cur_page + 1);
		}

		if (tpb.getPage_count() > 10) {
			if (cur_page > 5) {
				if (cur_page > tpb.getPage_count() - 4)
					tpb.setCurr_start_num(tpb.getPage_count() - 6);
				else
					tpb.setCurr_start_num(cur_page - 2);
			}
		}
		return tpb;
	}

	public static List<MemberInfoBean> getMemberInfoList(String params) {
		Map con_map = new HashMap();
		getMemberInfoSearchCon(params, con_map);
		int start_page = Integer.parseInt((String) con_map.get("current_page"));
		int page_size = Integer.parseInt((String) con_map.get("page_size"));
		con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
		con_map.put("page_size", Integer.valueOf(page_size));
		return MemberInfoManager.getMemberInfoList(con_map);
	}

	public static List<MemberInfoBean> getMemberInfoHotList(String params) {
		Map con_map = new HashMap();
		getMemberInfoSearchCon(params, con_map);
		con_map.put("current_page", "1");
		int start_page = Integer.parseInt((String) con_map.get("current_page"));
		int page_size = Integer.parseInt((String) con_map.get("page_size"));
		con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
		con_map.put("page_size", Integer.valueOf(page_size));
		return MemberInfoManager.getMemberInfoList(con_map);
	}

	public static MemberInfoBean getMemberInfoObject(String id) {
		return MemberInfoManager.getMemberInfoBean(id);
	}
}