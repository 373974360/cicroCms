//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.dao.cms.count;

import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.cms.count.InfoAccessBean;
import com.cicro.wcm.bean.cms.info.HitsCountBean;
import com.cicro.wcm.db.DBManager;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessCountDao {
    public AccessCountDao() {
    }

    public static List<InfoAccessBean> getAccessCountsBySite(Map m) {
        return DBManager.queryFList("getAccessCountsBySite_id", m);
    }

    public static List<InfoAccessBean> getSiteCateAccessList(Map m) {
        return DBManager.queryFList("getSiteCateAccessList", m);
    }

    public static List<InfoAccessBean> getAccessInfoLists(Map m) {
        return DBManager.queryFList("getAccessInfoLists", m);
    }

    public static InfoAccessBean getBeanByID(String info_id) {
        return (InfoAccessBean)DBManager.queryFObj("getBeanByID", info_id);
    }

    public static List<InfoAccessBean> getAccessInfoListsByCat_SiteId(Map m) {
        return DBManager.queryFList("getcounts_ByCatSiteId", m);
    }

    public static boolean deleteAccessCountInfo(Map mp) {
        return DBManager.delete("delete_AccessCountInfos", mp);
    }

    public static List<InfoAccessBean> getCatOrderListByCat_id(Map mp) {
        return DBManager.queryFList("getCatOrderListByCatid", mp);
    }

    public static List<InfoAccessBean> getInfoOrderListByInfo_id(Map mp) {
        return DBManager.queryFList("getInfoOrderListByInfoid", mp);
    }

    public static Map<String, String> getDayAccessCountList(String site_id, String constant) throws ParseException {
        if("".equals(constant)) {
            constant = "0";
        }

        HashMap rs_mp = new HashMap();
        String current_day = DateUtil.getCurrentDate();
        String yesterday = DateUtil.getDateBefore(current_day, 1);
        String current_day_d = DateUtil.formatToString(current_day, "dd");
        String current_day_m = DateUtil.formatToString(current_day, "MM");
        String current_day_y = DateUtil.formatToString(current_day, "yyyy");
        String yesterday_d = DateUtil.formatToString(yesterday, "dd");
        String yesterday_m = DateUtil.formatToString(yesterday, "MM");
        String yesterday_y = DateUtil.formatToString(yesterday, "yyyy");
        HashMap t_m = new HashMap();
        t_m.put("site_id", site_id);
        t_m.put("access_day", current_day_d);
        t_m.put("access_month", current_day_m);
        t_m.put("access_year", current_day_y);
        String today_num = DBManager.getString("getDayAccessCount", t_m);
        if(today_num == "" || today_num == null) {
            today_num = "0";
        }

        HashMap y_map = new HashMap();
        y_map.put("site_id", site_id);
        y_map.put("access_day", yesterday_d);
        y_map.put("access_month", yesterday_m);
        y_map.put("access_year", yesterday_y);
        String yesterday_num = DBManager.getString("getDayAccessCount", y_map);
        if(yesterday_num == "" || yesterday_num == null) {
            yesterday_num = "0";
        }

        String year_month = DateUtil.formatToString(current_day, "yyyy-MM");
        String start_time = year_month + "-01 00:00:00";
        String end_time = year_month + "-31 23:59:59";
        HashMap m_map = new HashMap();
        m_map.put("site_id", site_id);
        m_map.put("start_day", start_time);
        m_map.put("end_day", end_time);
        String monthnum = DBManager.getString("getMonthAccessCount", m_map);
        if(monthnum == "" || monthnum == null) {
            monthnum = "0";
        }

        int day_of_month = DateUtil.getDayOfMonth(DateUtil.getDate(current_day, "yyyy-MM-dd"));
        double average = (double)(Integer.parseInt(monthnum) / day_of_month);
        int averageNum = Integer.parseInt((new DecimalFormat("0")).format(average));
        rs_mp.put("todayNum", Integer.valueOf(Integer.parseInt(today_num) + Integer.parseInt(constant)));
        rs_mp.put("yesterDayNum", Integer.valueOf(Integer.parseInt(yesterday_num) + Integer.parseInt(constant)));
        rs_mp.put("averageDayNum", Integer.valueOf(averageNum + Integer.parseInt(constant)));
        return rs_mp;
    }
    
  //统计每个栏目的信息访问量
    public static List<HitsCountBean>  getHitsByCat(Map mp){
        return DBManager.queryFList("getHitsByCat", mp);
    }

    //统计每条信息的访问量
    public static List<HitsCountBean>  getHitsByInfo(Map mp){
        return DBManager.queryFList("getHitsByInfo", mp);
    }
}
