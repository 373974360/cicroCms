package com.cicro.project.dz_driver;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DriverViolationDAO {
    public static String getDriverViolationCount(Map<String, String> m) {
        return DBManager.getString("getDriverViolationCount", m);
    }

    public static List<DriverViolationBean> getDriverViolationList(Map<String, String> m) {
        return DBManager.queryFList("getDriverViolationList", m);
    }
    
    public static List<DriverViolationBean> getTotleDriverViolationList(Map<String, String> m) {
        return DBManager.queryFList("getTotleDriverViolationList", m);
    }
    
    public static String getTotleDriverViolationCount(Map<String, String> m) {
        return DBManager.getString("getTotleDriverViolationCount", m);
    }

    public static DriverViolationBean getDriverViolationBean(String id) {
        Map m = new HashMap();
        m.put("id", id);
        return (DriverViolationBean) DBManager.queryFObj("getDriverViolationBean", m);
    }

    public static List<DriverViolationBean> getAllDriverViolationList() {
        return DBManager.queryFList("getAllDriverViolationList", "");
    }

    public static List<DriverViolationBean> getAllDriverViolationBySiteID(Map<String, String> m) {
        return DBManager.queryFList("getAllDriverViolationBySiteID", m);
    }

    public static boolean insertDriverViolation(DriverViolationBean seb, SettingLogsBean stl) {
        if (DBManager.insert("insertDriverViolation", seb)) {
            PublicTableDAO.insertSettingLogs("添加", "车辆违规信息", seb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean insertDriverViolation(DriverViolationBean seb) {
        return DBManager.insert("insertDriverViolation", seb);
    }

    public static boolean updateDriverViolation(DriverViolationBean seb, SettingLogsBean stl) {
        if (DBManager.update("updateDriverViolation", seb)) {
            PublicTableDAO.insertSettingLogs("修改", "车辆违规信息", seb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean publishDriverViolation(Map<String, String> m)
    {
        return DBManager.update("publishDriverViolation", m);
    }

    public static boolean publishDriverViolation(Map<String, String> m, SettingLogsBean stl) {
        if (DBManager.update("publishDriverViolation", m)) {
            PublicTableDAO.insertSettingLogs("发布设置", "车辆违规信息", (String) m.get("id"), stl);
            return true;
        }
        return false;
    }

    public static boolean deleteDriverViolation(Map<String, String> m, SettingLogsBean stl) {
        if (DBManager.delete("deleteDriverViolation", m)) {
            PublicTableDAO.insertSettingLogs("删除", "车辆违规信息", (String) m.get("id"), stl);
            return true;
        }
        return false;
    }
}