package com.cicro.project.dz_driver;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverCompanyDAO {
	
	public static String getDriverCompanyCount(Map<String, String> m) {
        return DBManager.getString("getDriverCompanyCount", m);
    }

    public static List<DriverCompanyBean> getDriverCompanyList(Map<String, String> m) {
        return DBManager.queryFList("getDriverCompanyList", m);
    }

    public static DriverCompanyBean getDriverCompanyBean(String id) {
        Map m = new HashMap();
        m.put("id", id);
        return (DriverCompanyBean) DBManager.queryFObj("getDriverCompanyBean", m);
    }

    public static List<DriverCompanyBean> getAllDriverCompanyList(Map<String, String> m) {
        return DBManager.queryFList("getAllDriverCompanyList", m);
    }

    public static boolean insertDriverCompany(DriverCompanyBean esb, SettingLogsBean stl) {
        if (DBManager.insert("insertDriverCompany", esb)) {
            PublicTableDAO.insertSettingLogs("添加", "企业信息", esb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean insertDriverCompany(DriverCompanyBean esb) {
        return DBManager.insert("insertDriverCompany", esb);
    }

    public static boolean updateDriverCompany(DriverCompanyBean esb, SettingLogsBean stl) {
        if (DBManager.update("updateDriverCompany", esb)) {
            PublicTableDAO.insertSettingLogs("修改", "企业信息", esb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean deleteDriverCompany(Map<String, String> m, SettingLogsBean stl) {
        if (DBManager.delete("deleteDriverCompany", m)) {
            PublicTableDAO.insertSettingLogs("删除", "企业信息", (String) m.get("id"), stl);
            return true;
        }
        return false;
    }
}
