package com.cicro.project.salarySearch;

import com.cicro.util.CryptoTools;
import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.services.Log.LogManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class SalaryUserRPC {

    public static String getSalaryUserCount(Map<String, String> m) {
        return SalaryUserManager.getSalaryUserCount(m);
    }

    public static List<SalaryUserBean> getSalaryUserList(Map<String, String> m) {
        return SalaryUserManager.getSalaryUserList(m);
    }

    public static List<SalaryUserBean> getAllSalaryUserList() {
        return SalaryUserManager.getAllSalaryUserList();
    }

    public static List<SalaryUserBean> getAllSalaryUserByProductID(Map<String, String> m) {
        return SalaryUserManager.getAllSalaryUserByProductID(m);
    }

    public static SalaryUserBean getSalaryUserBean(String id) {
        SalaryUserBean sub = SalaryUserDAO.getSalaryUserBean(id);
        sub.setPassword(new CryptoTools().decode(sub.getPassword()));
        return sub;
    }

    public static boolean insertSalaryUser(SalaryUserBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            return SalaryUserManager.insertSalaryUser(hb,stl);
        }else
            return false;
    }

    public static boolean updateSalaryUser(SalaryUserBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            return SalaryUserManager.updateSalaryUser(hb, stl);
        }else
            return false;
    }

    public static boolean deleteSalaryUser(Map<String, String> m,HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            return SalaryUserManager.deleteSalaryUser(m, stl);
        }else
            return false;
    }
}