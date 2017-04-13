package com.cicro.project.dz_driver;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.services.Log.LogManager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class DriverViolationRPC {
    public static String getDriverViolationCount(Map<String, String> m) {
        return DriverViolationManager.getDriverViolationCount(m);
    }

    public static List<DriverViolationBean> getDriverViolationList(Map<String, String> m) {
        return DriverViolationManager.getDriverViolationList(m);
    }

    public static List<DriverViolationBean> getAllDriverViolationList() {
        return DriverViolationManager.getAllDriverViolationList();
    }

    public static List<DriverViolationBean> getAllDriverViolationBySiteID(Map<String, String> m) {
        return DriverViolationManager.getAllDriverViolationBySiteID(m);
    }

    public static DriverViolationBean getDriverViolationBean(String gq_id) {
        return DriverViolationManager.getDriverViolationBean(gq_id);
    }

    public static boolean updateDriverViolation(DriverViolationBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if (stl != null) {
            return DriverViolationManager.updateDriverViolation(hb, stl);
        }
        return false;
    }

    public static boolean publishDriverViolation(Map<String, String> m)
    {
        return DriverViolationManager.publishDriverViolation(m);
    }

    public static boolean insertDriverViolation(DriverViolationBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if (stl != null) {
            return DriverViolationManager.insertDriverViolation(hb, stl);
        }
        return false;
    }

    public static boolean deleteDriverViolation(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if (stl != null) {
            return DriverViolationManager.deleteDriverViolation(m, stl);
        }
        return false;
    }
    
    public static List<DriverCompanyBean> getAllDriverCompanyList(Map<String, String> m) {
        return DriverCompanyManager.getAllDriverCompanyList(m);
    }

    public static DriverCompanyBean getDriverCompanyBean(String id) {

        return DriverCompanyManager.getDriverCompanyBean(id);
    }

    public static boolean insertDriverCompany(DriverCompanyBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if (stl != null) {
            return DriverCompanyManager.insertDriverCompany(hb, stl);
        }
        return false;
    }

    public static boolean updateDriverCompany(DriverCompanyBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if (stl != null) {
            return DriverCompanyManager.updateDriverCompany(hb, stl);
        }
        return false;
    }

    public static boolean deleteDriverCompany(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if (stl != null) {
            return DriverCompanyManager.deleteDriverCompany(m, stl);
        }
        return false;
    }
}