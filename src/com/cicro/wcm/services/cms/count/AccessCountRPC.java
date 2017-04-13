//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.cms.count;

import com.cicro.wcm.bean.cms.count.InfoAccessBean;
import com.cicro.wcm.bean.cms.info.HitsCountBean;
import com.cicro.wcm.services.cms.count.AccessCountManager;
import com.cicro.wcm.services.cms.count.CmsCountExcelOut;
import java.util.List;
import java.util.Map;

public class AccessCountRPC {
    public AccessCountRPC() {
    }

    public static List<InfoAccessBean> getAccessCountsBySite(Map mp) {
        return AccessCountManager.getAccessCountsBySite(mp);
    }

    public static String AccessCountsBySiteOutExcel(List listBean, List headerList) {
        return CmsCountExcelOut.AccessCountsBySiteOutExcel(listBean, headerList);
    }

    public static List<InfoAccessBean> getSiteCountListByCate(Map mp) {
        return AccessCountManager.getSiteCountListByCate(mp);
    }

    public static List<InfoAccessBean> getSiteCateAccessList(Map mp) {
        return AccessCountManager.getSiteCateAccessList(mp);
    }

    public static String CateAccessCountsOutExcel(List listBean, List headerList) {
        return CmsCountExcelOut.CateAccessCountsOutExcel(listBean, headerList);
    }

    public static String CateAccessOrderCountsOutExcel(List listBean, List headerList) {
        return CmsCountExcelOut.CateAccessOrderCountsOutExcel(listBean, headerList);
    }

    public static String InfoAccessOrderCountsOutExcel(List listBean, List headerList) {
        return CmsCountExcelOut.InfoAccessOrderCountsOutExcel(listBean, headerList);
    }

    public static List<InfoAccessBean> getAccessInfoLists(Map mp) {
        return AccessCountManager.getAccessInfoListsByCats(mp);
    }

    public static InfoAccessBean getBeanByID(String info_id) {
        return AccessCountManager.getBeanByID(info_id);
    }

    public static List<InfoAccessBean> getCatOrderListByCat_id(Map mp) {
        return AccessCountManager.getCatOrderListByCat_id(mp);
    }

    public static List<InfoAccessBean> getInfoOrderListByInfo_id(Map mp) {
        return AccessCountManager.getInfoOrderListByInfo_id(mp);
    }

    public static boolean deleteAccessCountInfos(Map m) {
        return AccessCountManager.deleteAccessCountInfos(m);
    }

    public static Map<String, String> getDayAccessCountList(String site_id, String constant) {
        return AccessCountManager.getDayAccessCountList(site_id, constant);
    }
    //统计每个栏目的信息访问量
    public static List<HitsCountBean>  getHitsByCat(Map mp){
        return AccessCountManager.getHitsByCat(mp);
    }

    //统计每条信息的访问量
    public static List<HitsCountBean>  getHitsByInfo(Map mp){
        return AccessCountManager.getHitsByInfo(mp);
    }
}
