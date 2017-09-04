//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.cms.info;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.cms.category.SyncBean;
import com.cicro.wcm.bean.cms.count.InfoAccessBean;
import com.cicro.wcm.bean.cms.count.InfoCountBean;
import com.cicro.wcm.bean.cms.info.GKInfoBean;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.cms.info.RelatedInfoBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.org.user.UserRegisterBean;
import com.cicro.wcm.bean.system.formodel.ModelBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.dao.cms.info.InfoDAO;
import com.cicro.wcm.dao.zwgk.info.GKInfoDAO;
import com.cicro.wcm.services.cms.category.CategoryManager;
import com.cicro.wcm.services.cms.category.CategoryModelManager;
import com.cicro.wcm.services.cms.category.CategoryUtil;
import com.cicro.wcm.services.cms.category.SyncManager;
import com.cicro.wcm.services.cms.count.AccessCountManager;
import com.cicro.wcm.services.cms.info.InfoPublishManager;
import com.cicro.wcm.services.cms.info.ModelUtil;
import com.cicro.wcm.services.cms.workflow.WorkFlowManager;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import com.cicro.wcm.services.control.site.SiteAppRele;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.org.user.UserManager;
import com.cicro.wcm.services.system.formodel.ModelManager;
import com.cicro.wcm.services.zwgk.index.IndexManager;
import com.cicro.wcm.services.zwgk.info.GKInfoManager;
import com.cicro.wcm.services.zwgk.node.GKNodeManager;
import com.cicro.wcm.template.velocity.impl.VelocityInfoContextImp;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

public class InfoBaseManager {
    protected static String LINK_MODEL_ENAME = "link";
    public static String ARTICLE_MODEL_ENAME = "article";
    public static String GKTYGS_MODEL_ENAME = "gkftygs";

    public InfoBaseManager() {
    }

    public static InfoBean getInfoById(String info_id, String site_id) {
        return InfoDAO.getInfoById(info_id, site_id);
    }

    public static InfoBean getInfoById(String info_id) {
        return InfoDAO.getInfoById(info_id);
    }

    public static List<InfoBean> getBroInfoList(Map<String, String> map) {
        int start_page = Integer.parseInt((String)map.get("current_page"));
        int page_size = Integer.parseInt((String)map.get("page_size"));
        map.put("start_num", String.valueOf((start_page - 1) * page_size));
        map.put("page_size", String.valueOf(page_size));
        return InfoDAO.getBroInfoList(map);
    }

    public static String getBroInfoCount(Map<String, String> map) {
        return InfoDAO.getBroInfoCount(map);
    }

    public static List<InfoBean> getGKZNInfoList(Map<String, String> map) {
        return InfoDAO.getGKZNInfoList(map);
    }

    public static String getGKZNInfoCount(Map<String, String> map) {
        return InfoDAO.getGKZNInfoCount(map);
    }

    public static boolean batchPublishContentHtml(Map<String, String> map) {
        List l = InfoDAO.batchPublishContentHtml(map);
        if(l != null && l.size() > 0) {
            try {
                return InfoPublishManager.createContentHTML(l);
            } catch (IOException var3) {
                var3.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public static List<InfoBean> getInfoList(Map<String, String> map) {
        getSearchSQL(map);
        return InfoDAO.getInfoBeanList(map);
    }

    public static String getTimeStr(String searchTime) {
        String time = "";
        if(searchTime.trim().equals("searchType_1b")) {
            time = time(0L, "yyyy-MM-dd") + " 00:00:00";
        } else if(searchTime.trim().equals("searchType_2b")) {
            time = time(1L, "yyyy-MM-dd") + " 00:00:00";
        } else if(searchTime.trim().equals("searchType_3b")) {
            time = time(7L, (String)null);
        } else if(searchTime.trim().equals("searchType_4b")) {
            time = time(30L, (String)null);
        }

        return time;
    }

    public static List<UserRegisterBean> getAllInuptUserID(Map<String, String> m) {
        return InfoDAO.getAllInuptUserID(m);
    }

    public static String time(long n, String pattern) {
        if(pattern == null) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date date2 = new Date(date.getTime() - 86400000L * n);
        return DateUtil.getString(date2, pattern);
    }

    public static void getSearchSQL(Map<String, String> map) {
        String user_id = (String)map.get("user_id");
        String category_ids = (String)map.get("search_steps");
        String app_id = (String)map.get("app_id");
        String site_id = (String)map.get("site_id");
        String searchTime = (String)map.get("searchString");
        String time = "";
        String modelStr = map.get("modelString") == null?"":(String)map.get("modelString") + " and ";
        if(searchTime != null && searchTime.trim().startsWith("searchType_")) {
            map.remove("searchString");
            time = getTimeStr(searchTime);
            map.put("searchString", modelStr + " ci.opt_dtime > \'" + time + "\' ");
        } else if(modelStr != null && !modelStr.trim().equals("")) {
            map.put("searchString", modelStr.replaceAll(" and ", ""));
        }

        String cids = "";
        String sql;
        if(map.containsKey("cat_ids")) {
            cids = (String)map.get("cat_ids");
            sql = CategoryManager.getAllChildCategoryIDS(cids, (String)map.get("site_id"));
            if(sql != null && sql.length() > 0) {
                map.put("cat_ids", sql);
                map.remove("cat_id");
            } else {
                map.remove("cat_ids");
                map.put("cat_id", cids);
            }
        }

        if(!map.containsKey("input_user")) {
            sql = "";
            if(category_ids != null && !category_ids.trim().equals("")) {
                if(category_ids.trim().startsWith(",")) {
                    category_ids = category_ids.trim().substring(1);
                }

                if(category_ids.trim().endsWith(",")) {
                    category_ids = category_ids.trim().substring(0, category_ids.length() - 1);
                }

                sql = CategoryManager.getSearchSQLByCategoryIDS(user_id, category_ids, app_id, site_id);
                map.remove("cat_id");
            } else if(map.get("step_id") != null && !((String)map.get("step_id")).trim().equals("")) {
                sql = " and ci.step_id=" + (String)map.get("step_id");
            }

            map.put("searchString2", sql);
        }

    }

    public static int getInfoCount(Map<String, String> map) {
        getSearchSQL(map);
        return InfoDAO.getInfoCount(map);
    }

    public static boolean goBackInfo(List<InfoBean> l, SettingLogsBean stl) {
        String info_ids = "";
        Iterator var4 = l.iterator();

        while(var4.hasNext()) {
            InfoBean ib = (InfoBean)var4.next();
            info_ids = info_ids + "," + ib.getInfo_id();
            if(ib.getInfo_status() == 8) {
                InfoPublishManager.publishAfterEvent(ib);
            }
        }

        info_ids = info_ids.substring(1);
        return InfoDAO.updateInfoStatus(info_ids, "0", stl);
    }

    public static boolean backInfo(String infoIds, SettingLogsBean stl) {
        return InfoDAO.updateInfoStatus(infoIds, "1", stl);
    }

    public static boolean updateInfoStatus(List<InfoBean> l, String status, SettingLogsBean stl) {
        String info_ids = "";
        String site_id = ((InfoBean)l.get(0)).getSite_id();
        HashSet cat_ids = new HashSet();
        ArrayList publish_info_list = new ArrayList();

        try {
            InfoBean e;
            for(Iterator var8 = l.iterator(); var8.hasNext(); InfoDAO.updateInfoStatus2(e, status, stl)) {
                e = (InfoBean)var8.next();
                info_ids = info_ids + "," + e.getInfo_id();
                cat_ids.add(Integer.valueOf(e.getCat_id()));
                if("8".equals(status)) {
                    e.setInfo_status(8);
                    if(e.getReleased_dtime() == null || "".equals(e.getReleased_dtime())) {
                        e.setReleased_dtime(DateUtil.getCurrentDateTime());
                    }

                    publish_info_list.add(e);
                }

                if("3".equals(status)) {
                    e.setInfo_status(3);
                    publish_info_list.add(e);
                }
            }

            if("8".equals(status)) {
                InfoPublishManager.publishAfterEvent(publish_info_list, cat_ids, site_id);
            }

            if("3".equals(status)) {
                InfoPublishManager.cancelAfterEvent(publish_info_list, cat_ids, site_id);
            }

            info_ids = info_ids.substring(1);
            if(stl != null) {
                PublicTableDAO.insertSettingLogs("修改", "主信息信息状态更改为" + status, info_ids, stl);
            }

            return true;
        } catch (Exception var9) {
            var9.printStackTrace();
            return false;
        }
    }

    public static boolean MoveInfo(List<InfoBean> l, int to_cat_id, String app_id, String site_id, SettingLogsBean stl) {
        String nochange = JconfigUtilContainer.bashConfig().getProperty("nochange", "", "index_number");
        String bytime = JconfigUtilContainer.bashConfig().getProperty("bytime", "", "index_number");
        if(bytime == "" && bytime == null) {
            bytime = "";
        } else {
            try {
                bytime = DateUtil.formatToDateString(bytime) + " 23:59:59";
            } catch (ParseException var21) {
                var21.printStackTrace();
            }
        }

        try {
            String e = "";
            int wf_id = CategoryManager.getWFIDByCatID(to_cat_id, site_id);
            int info_status = ((InfoBean)l.get(0)).getInfo_status();
            String cat_save_path = CategoryUtil.getFoldePathByCategoryID(to_cat_id, app_id, site_id);
            HashSet cat_ids = new HashSet();
            Iterator var13 = l.iterator();

            while(true) {
                while(true) {
                    InfoBean ib;
                    do {
                        if(!var13.hasNext()) {
                            e = e.substring(1);
                            PublicTableDAO.insertSettingLogs("转移", "信息", e, stl);
                            if(info_status == 8) {
                                cat_ids.add(Integer.valueOf(to_cat_id));
                                InfoPublishManager.publishAfterEvent(l, cat_ids, site_id);
                            }

                            return true;
                        }

                        ib = (InfoBean)var13.next();
                        if(info_status == 8) {
                            InfoPublishManager.cancelAfterEvent(ib);
                            cat_ids.add(Integer.valueOf(ib.getCat_id()));
                        }

                        if(info_status == 2) {
                            ib.setStep_id(0);
                        }

                        if(ib.getModel_id() != ModelManager.getModelBeanByEName(LINK_MODEL_ENAME).getModel_id()) {
                            ib.setContent_url(cat_save_path + ib.getInfo_id() + ".htm");
                        }

                        ib.setCat_id(to_cat_id);
                        ib.setWf_id(wf_id);
                        InfoDAO.MoveInfo(ib);
                        e = e + "," + ib.getInfo_id();
                    } while(!"zwgk".equals(app_id));

                    if("yes".equals(nochange)) {
                        InfoBean gk_year2 = getInfoById(String.valueOf(ib.getInfo_id()));
                        String m2 = gk_year2.getInput_dtime();
                        Date byDate = null;
                        Date inputDate = null;
                        if(m2 != "" || m2 != null) {
                            inputDate = DateUtil.getDate(m2);
                        }

                        byDate = DateUtil.getDate(bytime);
                        int s = byDate.compareTo(inputDate);
                        if(s < 0) {
                            String gk_year1 = GKInfoManager.getGKYearStr(String.valueOf(ib.getInfo_id()));
                            Map m1 = IndexManager.getIndex(site_id, String.valueOf(to_cat_id), gk_year1 + "-01", "");
                            if(m1 != null) {
                                GKInfoDAO.updateGKIndex(String.valueOf(ib.getInfo_id()), (String)m1.get("gk_index"), (String)m1.get("gk_num"));
                            }
                        }
                    } else {
                        String gk_year = GKInfoManager.getGKYearStr(String.valueOf(ib.getInfo_id()));
                        Map m = IndexManager.getIndex(site_id, String.valueOf(to_cat_id), gk_year + "-01", "");
                        if(m != null) {
                            GKInfoDAO.updateGKIndex(String.valueOf(ib.getInfo_id()), (String)m.get("gk_index"), (String)m.get("gk_num"));
                        }
                    }
                }
            }
        } catch (Exception var22) {
            var22.printStackTrace();
            return false;
        }
    }

    public static boolean passInfoStatus(List<InfoBean> info_list, String user_id, SettingLogsBean stl) {
        try {
            String e = ((InfoBean)info_list.get(0)).getSite_id();
            String ids = "";
            HashSet cat_ids = new HashSet();
            ArrayList publish_info_list = new ArrayList();

            InfoBean info;
            int stepId;
            String info_status;
            for(Iterator var8 = info_list.iterator(); var8.hasNext(); InfoDAO.passInfoStatus(String.valueOf(info.getInfo_id()), info_status, String.valueOf(stepId), info.getReleased_dtime())) {
                info = (InfoBean)var8.next();
                ids = ids + "," + info.getInfo_id();
                CategoryBean cb = CategoryManager.getCategoryBeanCatID(info.getCat_id(), info.getSite_id());
                int wf_id = CategoryManager.getCategoryBean(info.getCat_id()).getWf_id();
                stepId = WorkFlowManager.getMaxStepIDByUserID(wf_id, user_id, info.getApp_id(), info.getSite_id());
                info_status = "2";
                if(wf_id == 0 || stepId == 100) {
                    info_status = "4";
                    if(cb.getIs_wf_publish() == 1) {
                        info_status = "8";
                        info.setInfo_status(8);
                        if(info.getReleased_dtime() == null || "".equals(info.getReleased_dtime())) {
                            info.setReleased_dtime(DateUtil.getCurrentDateTime());
                        }

                        publish_info_list.add(info);
                        cat_ids.add(Integer.valueOf(info.getCat_id()));
                    }
                }
            }

            InfoPublishManager.publishAfterEvent(publish_info_list, cat_ids, e);
            PublicTableDAO.insertSettingLogs("审核", "信息状态为通过", ids, stl);
            return true;
        } catch (Exception var13) {
            var13.printStackTrace();
            return false;
        }
    }

    public static boolean noPassInfoStatus(String info_ids, String auto_desc, SettingLogsBean stl) {
        return InfoDAO.noPassInfoStatus(info_ids, auto_desc, stl);
    }

    public static boolean addInfo(Object ob, SettingLogsBean stl) {
        InfoBean info = (InfoBean)ob;
        if(info.getId() == 0) {
            int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_TABLE_NAME);
            info.setId(id);
            info.setInfo_id(id);
        }

        info.setInput_dtime(DateUtil.getCurrentDateTime());
        changeInfoStatus(info);
        return InfoDAO.addInfo(info, stl);
    }

    public static boolean updateInfo(Object ob, SettingLogsBean stl) {
        InfoBean info = (InfoBean)ob;
        info.setModify_dtime(DateUtil.getCurrentDateTime());
        info.setOpt_dtime(DateUtil.getCurrentDateTime());
        changeInfoStatus(info);
        return InfoDAO.updateInfo(info, stl);
    }

    public static boolean updateInfoEvent(InfoBean info, SettingLogsBean stl) {
        if(updateInfo(info, stl)) {
            if(info.getInfo_status() == 8) {
                InfoPublishManager.publishAfterEvent(info);
            }

            return true;
        } else {
            return false;
        }
    }

    public static void changeInfoStatus(InfoBean info) {
        if(info.getInfo_status() == 4) {
            info.setStep_id(100);
            /*
            CategoryBean cb = CategoryManager.getCategoryBeanCatID(info.getCat_id(), info.getSite_id());
            if(cb.getIs_wf_publish() == 1) {
                info.setInfo_status(8);
                if(info.getReleased_dtime() == null || "".equals(info.getReleased_dtime())) {
                    info.setReleased_dtime(DateUtil.getCurrentDateTime());
                }
            }
            */
        }

        if(info.getInfo_status() == 8) {
            if(info.getReleased_dtime() == null || "".equals(info.getReleased_dtime())) {
                info.setReleased_dtime(DateUtil.getCurrentDateTime());
            }

            info.setStep_id(100);
        }

        if(info.getContent_url() == null || "".equals(info.getContent_url())) {
            info.setContent_url(CategoryUtil.getFoldePathByCategoryID(info.getCat_id(), info.getApp_id(), info.getSite_id()) + info.getInfo_id() + ".htm");
        }

    }

    public static List<InfoBean> getQuoteInfoList(String info_id) {
        return InfoDAO.getQuoteInfoList(info_id);
    }

    public static boolean insertInfoToOtherCat(Object o, String to_cat_ids, SettingLogsBean stl) {
        try {
            InfoBean e = (InfoBean)o;
            InfoBean new_bean = e;
            String[] tempA = to_cat_ids.split(",");

            for(int i = 0; i < tempA.length; ++i) {
                new_bean.setId(getNextInfoId());
                new_bean.setInfo_id(new_bean.getId());
                new_bean.setCat_id(Integer.parseInt(tempA[i]));
                new_bean.setFrom_id(e.getFrom_id());
                new_bean.setIs_host(2);
                new_bean.setWf_id(CategoryManager.getWFIDByCatID(new_bean.getCat_id(), new_bean.getSite_id()));
                if(new_bean.getWf_id() == 0) {
                    new_bean.setInfo_status(4);
                    new_bean.setStep_id(100);
                } else {
                    new_bean.setInfo_status(2);
                    new_bean.setStep_id(0);
                }

                new_bean.setModel_id(ModelManager.getModelBeanByEName(LINK_MODEL_ENAME).getModel_id());
                InfoDAO.addInfo(new_bean, stl);
            }

            return true;
        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
    }

    public static boolean deleteInfo(List<InfoBean> l, SettingLogsBean stl) {
        String info_ids = "";
        String site_id = ((InfoBean)l.get(0)).getSite_id();
        HashSet cat_id = new HashSet();
        Iterator ib = l.iterator();

        while(ib.hasNext()) {
            InfoBean m = (InfoBean)ib.next();
            info_ids = info_ids + "," + m.getInfo_id();
            if(m.getInfo_status() == 8 && !"LINK_MODEL_ENAME".equals(ModelManager.getModelEName(m.getModel_id()))) {
                InfoPublishManager.cancelAfterEvent(m);
                cat_id.add(Integer.valueOf(m.getCat_id()));
            }
        }

        info_ids = info_ids.substring(1);
        if(InfoDAO.updateInfoStatus(info_ids, "-1", stl)) {
            List m1 = InfoDAO.getFromInfoListByIDS(info_ids);
            if(m1 != null && m1.size() > 0) {
                Iterator var7 = m1.iterator();

                while(var7.hasNext()) {
                    InfoBean ib1 = (InfoBean)var7.next();
                    cat_id.add(Integer.valueOf(ib1.getCat_id()));
                    if(ib1.getInfo_status() == 8 && !"LINK_MODEL_ENAME".equals(ModelManager.getModelEName(ib1.getModel_id()))) {
                        InfoPublishManager.cancelAfterEvent(ib1);
                        cat_id.add(Integer.valueOf(ib1.getCat_id()));
                    }
                }

                realDeleteInfo(m1, stl);
            }
        }

        InfoPublishManager.resetCategoryPage(cat_id, site_id);
        HashMap m2 = new HashMap();
        m2.put("info_id", info_ids);
        AccessCountManager.deleteAccessCountInfos(m2);
        return true;
    }

    public static boolean realDeleteInfo(List<InfoBean> l, SettingLogsBean stl) {
        String info_ids = "";

        try {
            if(l != null && l.size() != 0) {
                HashMap e = new HashMap();
                Iterator model_map = l.iterator();

                while(model_map.hasNext()) {
                    InfoBean info_map = (InfoBean)model_map.next();
                    info_ids = info_ids + "," + info_map.getInfo_id();
                    if(e.containsKey(Integer.valueOf(info_map.getModel_id()))) {
                        e.put(Integer.valueOf(info_map.getModel_id()), (String)e.get(String.valueOf(info_map.getModel_id())) + "," + info_map.getInfo_id());
                    } else {
                        e.put(Integer.valueOf(info_map.getModel_id()), String.valueOf(info_map.getInfo_id()));
                    }
                }

                info_ids = info_ids.substring(1);
                HashMap info_map1 = new HashMap();
                info_map1.put("info_ids", info_ids);
                InfoDAO.deleteInfo(info_map1, stl);
                HashMap model_map1 = new HashMap();
                Set s = e.keySet();
                Iterator var8 = s.iterator();

                while(var8.hasNext()) {
                    int i = ((Integer)var8.next()).intValue();
                    ModelBean mb = ModelManager.getModelBean(i);
                    if(mb.getTable_name() != null && !"".equals(mb.getTable_name()) && !"infoLink".equals(mb.getTable_name())) {
                        model_map1.put("table_name", mb.getTable_name());
                        model_map1.put("info_ids", (String)e.get(Integer.valueOf(i)));
                        InfoDAO.deleteInfoModel(model_map1);
                    }

                    if(mb.getTable_name().contains("gk")) {
                        model_map1.put("table_name", "cs_gk_info");
                        model_map1.put("info_ids", info_ids);
                        GKInfoDAO.deleteGKInfo(info_ids);
                    }
                }

                return true;
            } else {
                return true;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            return false;
        }
    }

    public static boolean clearTrach(String cat_ids, String app_id, String site_id, SettingLogsBean stl) {
        return InfoDAO.clearTrach(cat_ids, app_id, site_id, stl);
    }

    public static String getInfoTemplateContent(String info_id, String site_id) {
        String content = "";
        InfoBean ib = getInfoById(info_id, site_id);
        if(ib != null) {
            String temp_site_id = "";
            if(!ib.getApp_id().equals("cms")) {
                temp_site_id = SiteAppRele.getSiteIDByAppID(ib.getApp_id());
            } else {
                temp_site_id = ib.getSite_id();
            }

            String model_ename = ModelManager.getModelEName(ib.getModel_id());
            int cat_id = ib.getCat_id();
            int model_id = ib.getModel_id();
            String template_id = CategoryModelManager.getTemplateID(String.valueOf(cat_id), ib.getSite_id(), model_id);
            VelocityInfoContextImp vici = new VelocityInfoContextImp(String.valueOf(ib.getInfo_id()), temp_site_id, template_id, model_ename);
            content = vici.parseTemplate();
        }

        return content;
    }

    public static boolean infoTo(List<InfoBean> l, String s_site_id, String s_app_id, String cat_ids, int get_type, boolean is_publish) {
        if(l != null && l.size() > 0) {
            String[] tempA = cat_ids.split(",");
            Iterator var8 = l.iterator();

            while(var8.hasNext()) {
                InfoBean info = (InfoBean)var8.next();

                try {
                    String e = ModelManager.getModelEName(info.getModel_id());
                    Object o = ModelUtil.select(String.valueOf(info.getInfo_id()), info.getSite_id(), e);
                    BeanUtils.setProperty(o, "site_id", s_site_id);
                    BeanUtils.setProperty(o, "app_id", s_app_id);
                    if(get_type == 2 && info.getIs_host() == 0) {
                        BeanUtils.setProperty(o, "model_id", Integer.valueOf(ModelManager.getModelBeanByEName(LINK_MODEL_ENAME).getModel_id()));
                        BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
                        BeanUtils.setProperty(o, "is_host", Integer.valueOf(get_type));
                        String i = BeanUtils.getProperty(o, "content_url");
                        if(!s_site_id.equals(info.getSite_id())) {
                            i = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(info.getSite_id()) + i;
                        }

                        BeanUtils.setProperty(o, "content_url", i);
                    }

                    for(int var20 = 0; var20 < tempA.length; ++var20) {
                        int id = getNextInfoId();
                        BeanUtils.setProperty(o, "id", Integer.valueOf(id));
                        BeanUtils.setProperty(o, "info_id", Integer.valueOf(id));
                        int cat_id = Integer.parseInt(tempA[var20]);
                        CategoryBean cb = CategoryManager.getCategoryBeanCatID(cat_id, s_site_id);
                        BeanUtils.setProperty(o, "cat_id", Integer.valueOf(cat_id));
                        if(cb.getWf_id() != 0) {
                            BeanUtils.setProperty(o, "info_status", Integer.valueOf(2));
                            BeanUtils.setProperty(o, "step_id", Integer.valueOf(0));
                            BeanUtils.setProperty(o, "wf_id", Integer.valueOf(cb.getWf_id()));
                        } else {
                            BeanUtils.setProperty(o, "info_status", Integer.valueOf(4));
                            BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
                        }

                        if(get_type == 0 && info.getIs_host() == 0) {
                            BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + id + ".htm");
                        }

                        if(get_type == 1 && info.getIs_host() == 0) {
                            BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
                            BeanUtils.setProperty(o, "is_host", Integer.valueOf(get_type));
                            BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + id + ".htm");
                        }

                        if(info.getIs_host() == 1) {
                            BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + id + ".htm");
                        }

                        if(info.getIs_host() == 2 && !s_site_id.equals(info.getSite_id())) {
                            String sourch_content_url = BeanUtils.getProperty(o, "content_url");
                            if(!sourch_content_url.startsWith("http://")) {
                                String temp_site_id = info.getSite_id();
                                if(temp_site_id.startsWith("GK")) {
                                    temp_site_id = SiteAppRele.getSiteIDByAppID(info.getApp_id());
                                }

                                sourch_content_url = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(temp_site_id) + sourch_content_url;
                                BeanUtils.setProperty(o, "content_url", sourch_content_url);
                            }
                        }

                        ModelUtil.insert(o, e, (SettingLogsBean)null);
                    }
                } catch (IllegalAccessException var17) {
                    var17.printStackTrace();
                } catch (InvocationTargetException var18) {
                    var18.printStackTrace();
                } catch (NoSuchMethodException var19) {
                    var19.printStackTrace();
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String getInfoReleSiteID(String site_id, String app_id) {
        String n_site_id = "";
        if(!app_id.equals("cms")) {
            n_site_id = SiteAppRele.getSiteIDByAppID(app_id);
        } else {
            n_site_id = site_id;
        }

        return n_site_id;
    }

    public static boolean infoGet(List<InfoBean> l, String s_site_id, String s_app_id, int cat_id, int get_type, boolean is_publish, int user_id) {
        if(l != null && l.size() > 0) {
            Iterator var8 = l.iterator();

            while(var8.hasNext()) {
                InfoBean info = (InfoBean)var8.next();

                try {
                    int e = getNextInfoId();
                    String model_ename = ModelManager.getModelEName(info.getModel_id());
                    Object o = ModelUtil.select(String.valueOf(info.getInfo_id()), info.getSite_id(), model_ename);
                    if("zwgk".equals(BeanUtils.getProperty(o, "app_id"))) {
                        GKInfoBean sourch_content_url = (GKInfoBean)o;
                        List temp_site_id = sourch_content_url.getFile_list();
                        if(temp_site_id != null && temp_site_id.size() > 0) {
                            for(int i = 0; i < temp_site_id.size(); ++i) {
                                BeanUtils.setProperty(o, "file_list[" + i + "].info_id", Integer.valueOf(e));
                            }
                        }
                    }

                    if(user_id != 0) {
                        BeanUtils.setProperty(o, "input_user", Integer.valueOf(user_id));
                    }

                    BeanUtils.setProperty(o, "site_id", s_site_id);
                    BeanUtils.setProperty(o, "cat_id", Integer.valueOf(cat_id));
                    BeanUtils.setProperty(o, "app_id", s_app_id);
                    BeanUtils.setProperty(o, "id", Integer.valueOf(e));
                    BeanUtils.setProperty(o, "info_id", Integer.valueOf(e));
                    if(is_publish) {
                        BeanUtils.setProperty(o, "info_status", Integer.valueOf(8));
                        BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
                    } else {
                        CategoryBean var19 = CategoryManager.getCategoryBeanCatID(cat_id, s_site_id);
                        BeanUtils.setProperty(o, "cat_id", Integer.valueOf(cat_id));
                        if(var19.getWf_id() != 0) {
                            BeanUtils.setProperty(o, "info_status", Integer.valueOf(2));
                            BeanUtils.setProperty(o, "step_id", Integer.valueOf(0));
                            BeanUtils.setProperty(o, "wf_id", Integer.valueOf(var19.getWf_id()));
                        } else {
                            BeanUtils.setProperty(o, "info_status", Integer.valueOf(4));
                            BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
                        }
                    }

                    if(get_type == 0 && info.getIs_host() == 0) {
                        BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + e + ".htm");
                    }

                    if(get_type == 1 && info.getIs_host() == 0) {
                        BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
                        BeanUtils.setProperty(o, "is_host", Integer.valueOf(get_type));
                        BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + e + ".htm");
                    }

                    String var20;
                    String var21;
                    if(get_type == 2 && info.getIs_host() == 0) {
                        BeanUtils.setProperty(o, "model_id", Integer.valueOf(ModelManager.getModelBeanByEName(LINK_MODEL_ENAME).getModel_id()));
                        BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
                        BeanUtils.setProperty(o, "is_host", Integer.valueOf(get_type));
                        var20 = BeanUtils.getProperty(o, "content_url");
                        if(!s_site_id.equals(info.getSite_id())) {
                            var21 = info.getSite_id();
                            if(var21.startsWith("GK")) {
                                var21 = SiteAppRele.getSiteIDByAppID(info.getApp_id());
                            }

                            var20 = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(var21) + var20;
                        }

                        BeanUtils.setProperty(o, "content_url", var20);
                    }

                    if(info.getIs_host() == 1) {
                        BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + e + ".htm");
                    }

                    if(info.getIs_host() == 2 && !s_site_id.equals(info.getSite_id())) {
                        var20 = BeanUtils.getProperty(o, "content_url");
                        if(!var20.startsWith("http://")) {
                            var21 = info.getSite_id();
                            if(var21.startsWith("GK")) {
                                var21 = SiteAppRele.getSiteIDByAppID(info.getApp_id());
                            }

                            var20 = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(var21) + var20;
                            BeanUtils.setProperty(o, "content_url", var20);
                        }
                    }

                    ModelUtil.insert(o, model_ename, (SettingLogsBean)null);
                    Thread.sleep(1000L);
                } catch (IllegalAccessException var15) {
                    var15.printStackTrace();
                } catch (InvocationTargetException var16) {
                    var16.printStackTrace();
                } catch (NoSuchMethodException var17) {
                    var17.printStackTrace();
                } catch (InterruptedException var18) {
                    var18.printStackTrace();
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean syncInfoToSite(List<InfoBean> l) {
        try {
            if(l != null && l.size() > 0) {
                Iterator var2 = l.iterator();

                while(var2.hasNext()) {
                    InfoBean e = (InfoBean)var2.next();
                    syncInfoToSite(e);
                }
            }

            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static boolean syncInfoToSite(InfoBean info) {
        InfoBean new_infoBean = info.clone();
        List li = SyncManager.getSyncCatListBySiteCatID(new_infoBean.getSite_id(), new_infoBean.getCat_id());
        if(li != null && li.size() > 0) {
            syncInfoToSiteHandl(li, info, 0);
        }

        List to_li = SyncManager.getToInfoCategoryList(new_infoBean.getSite_id(), new_infoBean.getCat_id());
        if(to_li != null && to_li.size() > 0) {
            syncInfoToSiteHandl(to_li, info, 1);
        }

        return true;
    }

    public static boolean syncInfoToSiteHandl(List<SyncBean> li, InfoBean info, int orientation) {
        try {
            String e = ModelManager.getModelEName(info.getModel_id());
            Object o = ModelUtil.select(String.valueOf(info.getInfo_id()), info.getSite_id(), e);
            Iterator var6 = li.iterator();

            while(var6.hasNext()) {
                SyncBean sb = (SyncBean)var6.next();
                String s_site_id = "";
                int s_cat_id = 0;
                if(orientation == 0) {
                    s_site_id = sb.getS_site_id();
                    s_cat_id = sb.getS_cat_id();
                }

                if(orientation == 1) {
                    s_site_id = sb.getT_site_id();
                    s_cat_id = sb.getT_cat_id();
                }

                int temp_old_info_id = info.getInfo_id();
                if(info.getFrom_id() != 0) {
                    temp_old_info_id = info.getFrom_id();
                }

                if("0".equals(InfoDAO.getQuoteInfoCount(temp_old_info_id, s_cat_id, s_site_id))) {
                    int is_publish = sb.getIs_auto_publish();
                    int type = sb.getCite_type();
                    int id = getNextInfoId();
                    String s_app_id = "cms";
                    if(s_site_id.startsWith("GK")) {
                        s_app_id = "zwgk";
                    }

                    BeanUtils.setProperty(o, "site_id", s_site_id);
                    BeanUtils.setProperty(o, "cat_id", Integer.valueOf(s_cat_id));
                    BeanUtils.setProperty(o, "app_id", s_app_id);
                    BeanUtils.setProperty(o, "id", Integer.valueOf(id));
                    BeanUtils.setProperty(o, "info_id", Integer.valueOf(id));
                    BeanUtils.setProperty(o, "wf_id", Integer.valueOf(CategoryManager.getWFIDByCatID(s_cat_id, s_site_id)));
                    if(info.getIs_host() == 0) {
                        BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
                        BeanUtils.setProperty(o, "is_host", Integer.valueOf(1));
                        BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(s_cat_id, s_app_id, s_site_id) + id + ".htm");
                    }

                    if(info.getIs_host() == 1) {
                        BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(s_cat_id, s_app_id, s_site_id) + id + ".htm");
                    }

                    if(info.getIs_host() == 2 && !s_site_id.equals(info.getSite_id())) {
                        String sourch_content_url = info.getContent_url();
                        if(!sourch_content_url.startsWith("http://")) {
                            String temp_site_id = info.getSite_id();
                            if(temp_site_id.startsWith("GK")) {
                                temp_site_id = SiteAppRele.getSiteIDByAppID(info.getApp_id());
                            }

                            sourch_content_url = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(temp_site_id) + sourch_content_url;
                            BeanUtils.setProperty(o, "content_url", sourch_content_url);
                        }
                    }

                    if(is_publish == 1) {
                        BeanUtils.setProperty(o, "info_status", Integer.valueOf(8));
                        BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
                    } else {
                        BeanUtils.setProperty(o, "info_status", Integer.valueOf(4));
                        BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
                    }

                    ModelUtil.insert(o, e, (SettingLogsBean)null);
                }
            }

            return true;
        } catch (IllegalAccessException var16) {
            var16.printStackTrace();
            return false;
        } catch (InvocationTargetException var17) {
            var17.printStackTrace();
            return false;
        }
    }

    public static int getNextInfoId() {
        return PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_TABLE_NAME);
    }

    public static int getReleInfoID() {
        return PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_INFO_TABLE_NAME);
    }

    public static boolean addRelatedInfo(RelatedInfoBean rinfo, SettingLogsBean stl) {
        return InfoDAO.addRelatedInfo(rinfo, stl);
    }

    public static boolean updateRelatedInfo(RelatedInfoBean rinfo, SettingLogsBean stl) {
        return InfoDAO.updateRelatedInfo(rinfo, stl);
    }

    public static boolean deleteRelatedInfo(Map<String, String> map, SettingLogsBean stl) {
        return InfoDAO.deleteRelatedInfo(map, stl);
    }

    public static List<RelatedInfoBean> getRelatedInfoList(Map<String, String> map) {
        return InfoDAO.getRelatedInfoList(map);
    }

    public static List<RelatedInfoBean> getBroRelatedInfoList(Map<String, String> map) {
        List l = InfoDAO.getRelatedInfoList(map);
        if(l != null && l.size() > 0) {
            return l;
        } else {
            InfoBean ib = getInfoById((String)map.get("info_id"));
            if(ib != null) {
                String info_count = JconfigUtilContainer.bashConfig().getProperty("count", "", "autoReleInfoCount");
                if(info_count == null || "".equals(info_count)) {
                    info_count = "10";
                }

                map.put("site_id", ib.getSite_id());
                map.put("page_size", info_count);
                map.put("start_num", "0");
                if("cms".equals(ib.getApp_id())) {
                    if(!"".equals(ib.getTags().trim())) {
                        map.put("keyword_con", getKeyWordConSQL(ib.getTags(), "tags"));
                        return getReleInfoListForAuto(map, Integer.parseInt(info_count));
                    }
                } else {
                    GKInfoBean gk = GKInfoManager.getGKInfoBean((String)map.get("info_id"));
                    if(gk != null && !"".equals(gk.getTopic_key())) {
                        map.put("keyword_con", getKeyWordConSQL(gk.getTopic_key(), "gk.topic_key"));
                        return GKInfoDAO.getReleGKInfoListForAuto(map);
                    }
                }
            }

            return null;
        }
    }

    public static List<RelatedInfoBean> getReleInfoListForAuto(Map<String, String> map, int info_count) {
        Object r_l = new ArrayList();
        List l = InfoDAO.getReleInfoListForAuto(map);
        HashMap riMap = new HashMap();
        if(l != null && l.size() > 0) {
            Iterator ids = l.iterator();

            while(ids.hasNext()) {
                RelatedInfoBean set = (RelatedInfoBean)ids.next();
                riMap.put(set.getTitle(), set);
                if(riMap.size() == info_count) {
                    break;
                }
            }

            Set set1 = riMap.keySet();
            String ids1 = "";

            String t;
            for(Iterator var8 = set1.iterator(); var8.hasNext(); ids1 = ids1 + "," + ((RelatedInfoBean)riMap.get(t)).getInfo_id()) {
                t = (String)var8.next();
            }

            if(!"".equals(ids1)) {
                r_l = InfoDAO.orderByReleInfoList(ids1.substring(1));
            }
        }

        return (List)r_l;
    }

    public static String getKeyWordConSQL(String keyword, String item_name) {
        String str = "";
        String[] tempA = keyword.split(" ");
        if(tempA != null && tempA.length > 0) {
            for(int i = 0; i < tempA.length; ++i) {
                str = str + "or " + item_name + " like \'%" + tempA[i] + "%\'";
            }

            if(str.length() > 0) {
                str = str.substring(2);
            }
        }

        return "(" + str + ")";
    }

    public static RelatedInfoBean getRelatedInfoBean(String id, String related_id) {
        return InfoDAO.getRelatedInfoBean(id, related_id);
    }

    public static void addInfoHits(String info_id, String num, String lastHit_date) {
        Calendar cal = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentCal = GregorianCalendar.getInstance();

        try {
            Date m = sdf.parse(lastHit_date);
            cal.setTime(m);
        } catch (ParseException var10) {
            cal.setTime(currentCal.getTime());
        }

        HashMap m1 = new HashMap();
        m1.put("info_id", info_id);
        m1.put("num", num);
        String month_num = num;
        if(currentCal.get(2) == cal.get(2)) {
            month_num = "month_hits + " + num;
        }

        m1.put("m_num", month_num);
        String week_num = num;
        if(currentCal.get(3) == cal.get(3)) {
            week_num = "week_hits + " + num;
        }

        m1.put("w_num", week_num);
        String day_num = num;
        if(currentCal.get(6) == cal.get(6)) {
            day_num = "day_hits + " + num;
        }

        m1.put("d_num", day_num);
        m1.put("lasthit_dtime", DateUtil.getDateTimeString(currentCal.getTime()));
        InfoDAO.addInfoHits(m1);
    }

    public static boolean deleteInfoBySite(String site_id, String delete_cat_id) {
        try {
            HashMap e = new HashMap();
            e.put("site_id", site_id);
            if(delete_cat_id != null && !"".equals(delete_cat_id)) {
                e.put("delete_cat_id", delete_cat_id);
            }

            List ml = ModelManager.getModelList();
            Iterator var5 = ml.iterator();

            while(var5.hasNext()) {
                ModelBean mb = (ModelBean)var5.next();

                try {
                    if(!mb.getModel_ename().equals(LINK_MODEL_ENAME)) {
                        e.put("table_name", mb.getTable_name());
                        InfoDAO.deleteInfoModelBySite(e);
                    }
                } catch (Exception var7) {
                    var7.printStackTrace();
                }
            }

            GKInfoDAO.clearGKResFile(e);
            GKInfoDAO.clearGKInfo(e);
            InfoDAO.deleteInfo(e);
            return true;
        } catch (Exception var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public static void clearInfoSearchByCatID(String site_id, String delete_cat_id) {
        HashMap m = new HashMap();
        m.put("site_id", site_id);
        m.put("delete_cat_id", delete_cat_id);
        List l = InfoDAO.getPublishInfoByCateID(m);
        if(l != null && l.size() > 0) {
            if(!site_id.startsWith("GK") && !"ggfw".equals(site_id)) {
                InfoPublishManager.cancelInfoSearch(l);
            } else {
                InfoPublishManager.cancelInfoSearchAndPage(l);
            }
        }

    }

    public static String getInfoClickCount(String info_id) {
        return InfoDAO.getInfoClickCount(info_id);
    }

    public static List<InfoCountBean> getInfoTotalForApp(int row_count, String app_id, Map<String, String> m) {
        m.put("app_id", app_id);
        ArrayList c_l = new ArrayList();
        List l = InfoDAO.getInfoTotalForApp(m);
        processTotalList(l, c_l, app_id, row_count);
        return c_l;
    }

    public static List<InfoCountBean> getSiteAccessStatistics(String item_name, int row_count, String app_id) {
        HashMap m = new HashMap();
        m.put("app_id", app_id);
        m.put("item_name", item_name);
        ArrayList c_l = new ArrayList();
        List l = InfoDAO.getSiteAccessStatistics(m);
        processTotalList(l, c_l, app_id, row_count);
        return c_l;
    }

    public static void processTotalList(List<InfoCountBean> l, List<InfoCountBean> c_l, String app_id, int row_count) {
        if(row_count == 0) {
            row_count = 10;
        }

        if(l != null && l.size() > 0) {
            if(l.size() < row_count) {
                row_count = l.size();
            }

            int i = 1;

            for(Iterator var6 = l.iterator(); var6.hasNext(); ++i) {
                InfoCountBean icb = (InfoCountBean)var6.next();
                if(i > row_count) {
                    break;
                }

                try {
                    if("zwgk".equals(app_id)) {
                        icb.setSite_name(GKNodeManager.getNodeNameByNodeID(icb.getSite_id()));
                    }

                    if("cms".equals(app_id)) {
                        icb.setSite_name(SiteManager.getSiteBeanBySiteID(icb.getSite_id()).getSite_name());
                    }

                    c_l.add(icb);
                } catch (Exception var8) {
                    var8.printStackTrace();
                }
            }
        }

    }

    public static List<InfoCountBean> getInfoTotalForSiteUser(String site_id, int row_count) {
        HashMap m = new HashMap();
        m.put("site_id", site_id);
        ArrayList c_l = new ArrayList();
        List l = InfoDAO.getInfoTotalForSiteUser(m);
        if(row_count == 0) {
            row_count = 10;
        }

        if(l != null && l.size() > 0) {
            if(l.size() < row_count) {
                row_count = l.size();
            }

            byte i = 1;
            Iterator var7 = l.iterator();

            while(var7.hasNext()) {
                InfoCountBean icb = (InfoCountBean)var7.next();
                if(i > row_count) {
                    break;
                }

                icb.setUser_realname(UserManager.getUserRealName(String.valueOf(icb.getUser_id())));
                c_l.add(icb);
            }
        }

        return c_l;
    }

    public static void insertAccessInfo(String info_id, String info_tile, int cat_id, String app_id, String site_id, String access_url, HttpServletRequest request) {
        String access_time = DateUtil.getCurrentDateTime();
        String access_ip = FormatUtil.getIpAddr(request);
        String site_domain = request.getServerName();
        String temp_time = "";
        if(access_time != "") {
            try {
                temp_time = DateUtil.formatToString(access_time, "yyyy-MM-dd");
            } catch (ParseException var13) {
                var13.printStackTrace();
            }
        }

        int id = PublicTableDAO.getIDByTableName(PublicTableDAO.AccessInfoCount_TABLE_NAME);
        InfoAccessBean accbean = new InfoAccessBean();
        accbean.setId(id);
        accbean.setInfo_id(Integer.parseInt(info_id));
        accbean.setCat_id(cat_id);
        accbean.setInfo_title(info_tile);
        accbean.setAccess_day(temp_time.substring(8, 10));
        accbean.setAccess_month(temp_time.substring(5, 7));
        accbean.setAccess_year(temp_time.substring(0, 4));
        accbean.setApp_id(app_id);
        if(access_ip != "" && access_ip != null) {
            accbean.setAccess_ip(access_ip);
        } else {
            accbean.setAccess_ip("");
        }

        accbean.setAccess_time(access_time);
        accbean.setAccess_url(access_url);
        accbean.setSite_domain(site_domain);
        accbean.setSite_id(site_id);
        InfoDAO.insertAccessInfo(accbean);
    }

    public static void main(String[] args) {
        List c_l = getInfoTotalForSiteUser("HIWCMdemo", 3);
        System.out.println(((InfoCountBean)c_l.get(0)).getUser_realname() + "  " + ((InfoCountBean)c_l.get(0)).getPublish_count());
    }
}
