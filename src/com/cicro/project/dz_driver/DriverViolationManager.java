package com.cicro.project.dz_driver;


import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DriverViolationManager {
    public static String getDriverViolationCount(Map<String, String> m) {
        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return "0";
        }
        return DriverViolationDAO.getDriverViolationCount(m);
    }

    public static List<DriverViolationBean> getDriverViolationList(Map<String, String> m) {

        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return new ArrayList();
        }
        return setNames(DriverViolationDAO.getDriverViolationList(m));
    }
    
    public static List<DriverViolationBean> getTotleDriverViolationList(Map<String, String> m) {

        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return new ArrayList();
        }
        return setNames(DriverViolationDAO.getTotleDriverViolationList(m));
    }
    
    public static String getTotleDriverViolationCount(Map<String, String> m) {
        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return "0";
        }
        return DriverViolationDAO.getTotleDriverViolationCount(m);
    }

    public static List<DriverViolationBean> getAllDriverViolationList() {
        return setNames(DriverViolationDAO.getAllDriverViolationList());
    }

    public static List<DriverViolationBean> getAllDriverViolationBySiteID(Map<String, String> m) {
        return setNames(DriverViolationDAO.getAllDriverViolationBySiteID(m));
    }

    public static DriverViolationBean getDriverViolationBean(String id) {
        return setNames(DriverViolationDAO.getDriverViolationBean(id));
    }

    public static boolean insertDriverViolation(DriverViolationBean hb, SettingLogsBean stl) {
        hb.setAddTime(DateUtil.getCurrentDateTime());
        hb.setId(PublicTableDAO.getIDByTableName("dz_DriverViolation"));
        return DriverViolationDAO.insertDriverViolation(hb, stl);
    }

    public static boolean updateDriverViolation(DriverViolationBean hb, SettingLogsBean stl) {
        return DriverViolationDAO.updateDriverViolation(hb, stl);
    }

    public static boolean publishDriverViolation(Map<String, String> m)
    {
        String status = m.get("status");
        if("2".equals(status))
        {
            m.put("handleTime",DateUtil.getCurrentDateTime());
        }
        return DriverViolationDAO.publishDriverViolation(m);
    }

    public static boolean deleteDriverViolation(Map<String, String> m, SettingLogsBean stl) {
        return DriverViolationDAO.deleteDriverViolation(m, stl);
    }

    private static List<DriverViolationBean> setNames(List<DriverViolationBean> old)
    {
        List<DriverViolationBean> returnList = new ArrayList<DriverViolationBean>();
        if(old != null && old.size() > 0)
        {
            for(DriverViolationBean seb : old)
            {
            	seb.setSimpleName((DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "") != null ? DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "").getSimpleName() : ""));
                seb.setAllName((DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "") != null ? DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "").getAllName() : ""));
                returnList.add(seb);
            }
        }
        return returnList;
    }
    
    private static DriverViolationBean setNames(DriverViolationBean seb)
    {
       seb.setSimpleName((DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "") != null ? DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "").getSimpleName() : ""));
       seb.setAllName((DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "") != null ? DriverCompanyManager.getDriverCompanyBean(seb.getCompanyId() + "").getAllName() : ""));
        return seb;
    }
}