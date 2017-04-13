package com.cicro.project.dz_driver;


import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DriverCompanyManager {
	
	public static String getDriverCompanyCount(Map<String, String> m) {
        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return "0";
        }
        return DriverCompanyDAO.getDriverCompanyCount(m);
    }

    public static List<DriverCompanyBean> getDriverCompanyList(Map<String, String> m) {

        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return new ArrayList();
        }
        return DriverCompanyDAO.getDriverCompanyList(m);
    }

    public static List<DriverCompanyBean> getAllDriverCompanyList(Map<String, String> m) {
        return DriverCompanyDAO.getAllDriverCompanyList(m);
    }

    public static DriverCompanyBean getDriverCompanyBean(String id) {
        return DriverCompanyDAO.getDriverCompanyBean(id);
    }

    public static boolean insertDriverCompany(DriverCompanyBean hb, SettingLogsBean stl) {
        hb.setAddTime(DateUtil.getCurrentDateTime());
        hb.setId(PublicTableDAO.getIDByTableName("dz_DriverCompany"));
        return DriverCompanyDAO.insertDriverCompany(hb, stl);
    }

    public static boolean updateDriverCompany(DriverCompanyBean hb, SettingLogsBean stl) {
        return DriverCompanyDAO.updateDriverCompany(hb, stl);
    }

    public static boolean deleteDriverCompany(Map<String, String> m, SettingLogsBean stl) {
        return DriverCompanyDAO.deleteDriverCompany(m, stl);
    }
}