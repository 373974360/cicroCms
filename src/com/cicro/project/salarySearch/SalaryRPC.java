package com.cicro.project.salarySearch;

import com.cicro.util.CryptoTools;
import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.services.org.user.SessionManager;
import com.cicro.wcm.services.org.user.UserLogin;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalaryRPC {

    public static boolean importSalaryData(String excel_path,String site_id,String date)
    {
        return ExcelHandleUtil.importSalaryData(excel_path,site_id,date);
    }

    public static boolean deleteSalaryData(Map<String,String> m,HttpServletRequest request)
    {
        return SalaryManager.deleteSalary(m,null);
    }

    public static SalaryUserBean salaryUserLogin(Map<String,String> m,HttpServletRequest request)
    {
        return SalaryUserManager.salaryUserLogin(m,request);
    }

    public static List<SalaryBean> searchSalary(Map<String,String> m)
    {
        return SalaryManager.getSalaryList(m);
    }

    /**
     * 从session得到用户对象
     *
     * @param HttpServletRequest request
     * @return boolean
     */
    public static SalaryUserBean getUserBySession(HttpServletRequest request)
    {
        return SalaryUserManager.getUserBySession(request);
    }

    /**
     * 注销登陆
     *
     * @param HttpServletRequest request
     * @return boolean
     */
    public static void logout(HttpServletRequest request)
    {
        SalaryUserManager.logout(request);
    }

    public static SalaryUserBean changePassword(String id,String password,HttpServletRequest request)
    {
        return SalaryUserManager.changePassword(id,password,request);
    }

    public static void main(String[] args) {
        System.out.println(new CryptoTools().decode("=#=WS8IX5sqB4k="));
    }
}