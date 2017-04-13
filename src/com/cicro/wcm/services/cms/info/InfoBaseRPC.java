//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.cms.info;

import com.cicro.analyzer.AnalyzerManager;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.cms.info.RelatedInfoBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.org.user.UserRegisterBean;
import com.cicro.wcm.dao.cms.info.InfoDAO;
import com.cicro.wcm.rmi.file.FileRmiFactory;
import com.cicro.wcm.services.Log.LogManager;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.cms.info.InfoDesktop;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class InfoBaseRPC {
    public InfoBaseRPC() {
    }

    public static String getTagsForTitle(String title) {
        return AnalyzerManager.getWordsByTitle(title);
    }

    public static boolean infoTo(String infoId, String s_site_id, String s_app_id, String cat_ids, int get_type, boolean is_publish) {
        return InfoBaseManager.infoTo(infoId, s_site_id, s_app_id, cat_ids, get_type, is_publish);
    }

    public static boolean infoGet(List<InfoBean> l, String s_site_id, String s_app_id, int cat_id, int get_type, boolean is_publish, int user_id) {
        if(is_publish) {
            try {
                return FileRmiFactory.infoGet(InfoBaseManager.getInfoReleSiteID(s_site_id, s_app_id), l, s_site_id, s_app_id, cat_id, get_type, is_publish, user_id);
            } catch (RemoteException var8) {
                var8.printStackTrace();
                return false;
            }
        } else {
            return InfoBaseManager.infoGet(l, s_site_id, s_app_id, cat_id, get_type, is_publish, user_id);
        }
    }

    public static String getInfoClickCount(String info_id) {
        return InfoBaseManager.getInfoClickCount(info_id);
    }

    public static boolean batchPublishContentHtml(Map<String, String> map) {
        String site_id = "";
        String app_id = "";
        if(map.containsKey("site_id")) {
            site_id = (String)map.get("site_id");
        }

        if(map.containsKey("app_id")) {
            app_id = (String)map.get("app_id");
        }

        return FileRmiFactory.batchPublishContentHtml(InfoBaseManager.getInfoReleSiteID(site_id, app_id), map);
    }

    public static InfoBean getInfoById(String info_id, String site_id) {
        return InfoBaseManager.getInfoById(info_id, site_id);
    }

    public static InfoBean getInfoById(String info_id) {
        return InfoBaseManager.getInfoById(info_id);
    }

    public static boolean insertInfoToOtherCat(Object o, String to_cat_ids, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.insertInfoToOtherCat(o, to_cat_ids, stl):false;
    }

    public static List<UserRegisterBean> getAllInuptUserID(Map<String, String> m) {
        return InfoBaseManager.getAllInuptUserID(m);
    }

    public static List<InfoBean> getGKZNInfoList(Map<String, String> map) {
        return InfoBaseManager.getGKZNInfoList(map);
    }

    public static List<InfoBean> getQuoteInfoList(String info_id) {
        return InfoBaseManager.getQuoteInfoList(info_id);
    }

    public static String getGKZNInfoCount(Map<String, String> map) {
        return InfoBaseManager.getGKZNInfoCount(map);
    }

    public static List<InfoBean> getInfoList(Map<String, String> map) {
        return InfoBaseManager.getInfoList(map);
    }

    public static int getInfoCount(Map<String, String> map) {
        return InfoBaseManager.getInfoCount(map);
    }

    public static boolean deleteInfo(List<InfoBean> l, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?FileRmiFactory.deleteInfo(InfoBaseManager.getInfoReleSiteID(((InfoBean)l.get(0)).getSite_id(), ((InfoBean)l.get(0)).getApp_id()), l, stl):false;
    }

    public static boolean realDeleteInfo(List<InfoBean> l, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.realDeleteInfo(l, stl):false;
    }

    public static boolean MoveInfo(List<InfoBean> l, int to_cat_id, String app_id, String site_id, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?FileRmiFactory.MoveInfo(InfoBaseManager.getInfoReleSiteID(site_id, app_id), l, to_cat_id, app_id, site_id, stl):false;
    }

    public static boolean clearTrach(String cat_ids, String app_id, String site_id, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.clearTrach(cat_ids, app_id, site_id, stl):false;
    }

    public static boolean goBackInfo(List<InfoBean> l, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.goBackInfo(l, stl):false;
    }

    public static boolean backInfo(String infoIds, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.backInfo(infoIds, stl):false;
    }

    public static boolean updateInfoStatus(List<InfoBean> l, String status, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?FileRmiFactory.updateInfoStatus(InfoBaseManager.getInfoReleSiteID(((InfoBean)l.get(0)).getSite_id(), ((InfoBean)l.get(0)).getApp_id()), l, status, stl):false;
    }

    public static boolean updateInfo(InfoBean info, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?(info.getInfo_status() == 8?FileRmiFactory.updateInfoEvent(InfoBaseManager.getInfoReleSiteID(info.getSite_id(), info.getApp_id()), info, stl):InfoBaseManager.updateInfoEvent(info, stl)):false;
    }

    public static boolean passInfoStatus(List<InfoBean> info_list, String user_id, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?FileRmiFactory.passInfoStatus(InfoBaseManager.getInfoReleSiteID(((InfoBean)info_list.get(0)).getSite_id(), ((InfoBean)info_list.get(0)).getApp_id()), info_list, user_id, stl):false;
    }

    public static boolean noPassInfoStatus(String info_ids, String auto_desc, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.noPassInfoStatus(info_ids, auto_desc, stl):false;
    }

    public static String getTempJsonDataTree() {
        String s = "[{\"id\":0,\"iconCls\":\"icon-org\", \"text\":\"站点\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=0\"},\"children\":[{\"id\":10,\"iconCls\":\"icon-templateFolder\",\"text\":\"会员\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=10\"}},{\"id\":8,\"iconCls\":\"icon-templateFolder\",\"text\":\"调查\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=8\"}},{\"id\":7,\"iconCls\":\"icon-templateFolder\",\"text\":\"访谈\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=7\"}},{\"id\":6,\"iconCls\":\"icon-templateFolder\",\"text\":\"诉求\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=6\"}},{\"id\":3,\"iconCls\":\"icon-templateFolder\",\"state\":\'closed\',\"text\":\"文章\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=3\"},\"children\":[{\"id\":158,\"iconCls\":\"icon-templateFolder\",\"text\":\"13\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=158\"}},{\"id\":148,\"iconCls\":\"icon-templateFolder\",\"state\":\'closed\',\"text\":\"12\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=148\"},\"children\":[{\"id\":157,\"iconCls\":\"icon-templateFolder\",\"text\":\"vvvvvvv\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=157\"}},{\"id\":156,\"iconCls\":\"icon-templateFolder\",\"text\":\"dddcvcvcvc\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=156\"}},{\"id\":155,\"iconCls\":\"icon-templateFolder\",\"text\":\"bbbbbb\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=155\"}}]}]},{\"id\":2,\"iconCls\":\"icon-templateFolder\",\"text\":\"系统\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=2\"}},{\"id\":1,\"iconCls\":\"icon-templateFolder\",\"text\":\"首页\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?cid=1\"}}]}]";
        return s;
    }

    public static int getInfoId() {
        return InfoBaseManager.getNextInfoId();
    }

    public static int getReleInfoID() {
        return InfoBaseManager.getReleInfoID();
    }

    public static boolean addRelatedInfo(RelatedInfoBean rinfo, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.addRelatedInfo(rinfo, stl):false;
    }

    public static boolean updateRelatedInfo(RelatedInfoBean rinfo, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.updateRelatedInfo(rinfo, stl):false;
    }

    public static boolean deleteRelatedInfo(Map<String, String> map, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?InfoBaseManager.deleteRelatedInfo(map, stl):false;
    }

    public static List<RelatedInfoBean> getRelatedInfoList(Map<String, String> map) {
        return InfoBaseManager.getRelatedInfoList(map);
    }

    public static RelatedInfoBean getRelatedInfoBean(String id, String related_id) {
        return InfoBaseManager.getRelatedInfoBean(id, related_id);
    }

    public static boolean createContentHTML(List<InfoBean> l) throws IOException {
        return FileRmiFactory.createContentHTML(InfoBaseManager.getInfoReleSiteID(((InfoBean)l.get(0)).getSite_id(), ((InfoBean)l.get(0)).getApp_id()), l);
    }

    public static void addInfoHits(String info_id, String num) {
        InfoBaseManager.addInfoHits(info_id, num, "");
    }

    public static Map<String, Object> getWaitVerifyInfoList(Map<String, String> m) {
        return InfoDesktop.getWaitVerifyInfoList(m);
    }

    public static void insertAccessInfo(String info_id, String info_tile, int cat_id, String app_id, String site_id, String access_url, HttpServletRequest request) {
        InfoBaseManager.insertAccessInfo(info_id, info_tile, cat_id, app_id, site_id, access_url, request);
    }

    public static boolean updateInfoWeight(int info_id, int weight) {
        InfoBean bean = new InfoBean();
        bean.setInfo_id(info_id);
        bean.setWeight(weight);
        return InfoDAO.updateInfoWeight(bean);
    }

    public static void main(String[] args) {
        HashMap m = new HashMap();
        m.put("app_id", "0");
        m.put("site_id", "0");
        m.put("start_num", "0");
        m.put("page_size", "10");
        m.put("sort_name", "info_id");
        m.put("sort_type", "desc");
        m.put("is_auto_up", "0");
        m.put("is_host", "0");
        m.put("info_status", "0");
        m.put("final_status", "0");
        m.put("cat_id", "0");
        RelatedInfoBean ri = new RelatedInfoBean();
        ri.setContent_url("utl-------------------=");
        ri.setModel_id(1);
        ri.setRelated_info_id(1);
        ri.setSort_id(1);
        ri.setThumb_url("src==================");
        ri.setTitle("a");
        addRelatedInfo(ri, (HttpServletRequest)null);
    }
}
