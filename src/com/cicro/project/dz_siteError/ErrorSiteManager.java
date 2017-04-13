package com.cicro.project.dz_siteError;


import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.List;
import java.util.Map;

public class ErrorSiteManager {

    public static List<ErrorSiteBean> getAllErrorSiteList(Map<String, String> m) {
        return ErrorSiteDAO.getAllErrorSiteList(m);
    }

    public static ErrorSiteBean getErrorSiteBean(String id) {
        return ErrorSiteDAO.getErrorSiteBean(id);
    }

    public static boolean insertErrorSite(ErrorSiteBean hb, SettingLogsBean stl) {
        hb.setAddTime(DateUtil.getCurrentDateTime());
        hb.setId(PublicTableDAO.getIDByTableName("dz_errorsite"));
        return ErrorSiteDAO.insertErrorSite(hb, stl);
    }

    public static boolean updateErrorSite(ErrorSiteBean hb, SettingLogsBean stl) {
        return ErrorSiteDAO.updateErrorSite(hb, stl);
    }

    public static boolean deleteErrorSite(Map<String, String> m, SettingLogsBean stl) {
        return ErrorSiteDAO.deleteErrorSite(m, stl);
    }
}