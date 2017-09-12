package com.cicro.wcm.services.org.user;

import com.cicro.wcm.bean.org.operate.MenuBean;
import com.cicro.wcm.bean.org.user.LoginUserBean;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLoginRPC {
    public static String getSessionID(HttpServletRequest request) {
        return request.getSession().getId();
    }

    public static String checkUserLogin(String user_name, String pass_word, String auth_code, HttpServletRequest request) {
        String codeSession = (String) request.getSession().getAttribute("valiCode");
        System.out.println("**************codeSession************" + codeSession);
        if (codeSession == null || "".equals(codeSession)) {
            System.out.println("**************系统session错误！************");
        } else if (!codeSession.equals(auth_code)) {
            return "auth_code_error";
        }
        return UserLogin.checkUserLogin(user_name, pass_word, request);
    }

    public static boolean loginOut(HttpServletRequest request) {
        return UserLogin.loginOut(request);
    }

    public static List<MenuBean> getMenuListByUserID(String user_id) {
        return UserLogin.getMenuListByUserID(user_id);
    }

    public static String menuListToJsonStrByUserID(String user_id, String menu_id) {
        return UserLogin.menuListToJsonStrByUserID(user_id, menu_id);
    }

    public static LoginUserBean getUserBySession(HttpServletRequest request) {
        return UserLogin.getUserBySession(request);
    }

    public static String getOptIDSByUserID(String user_id) {
        return UserLogin.getOptIDSByUserID(user_id);
    }

    public static String getOptIDSByUserAPPSite(String user_id, String app_id, String site_id) {
        return UserLogin.getOptIDSByUserAPPSite(user_id, app_id, site_id);
    }

    public static boolean isSiteManager(String user_id, String app_id, String site_id) {
        return UserLogin.isSiteManager(user_id, app_id, site_id);
    }

    public static String getMyPlatformTreeStr() {
        return UserLogin.getMyPlatformTreeStr();
    }
}