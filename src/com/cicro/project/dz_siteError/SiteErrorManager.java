package com.cicro.project.dz_siteError;


import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SiteErrorManager {
    public static String getSiteErrorCount(Map<String, String> m) {
        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return "0";
        }
        return SiteErrorDAO.getSiteErrorCount(m);
    }

    public static List<SiteErrorBean> getSiteErrorList(Map<String, String> m) {

        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return new ArrayList();
        }
        return setNames(SiteErrorDAO.getSiteErrorList(m));
    }

    public static List<SiteErrorBean> getAllSiteErrorList() {
        return setNames(SiteErrorDAO.getAllSiteErrorList());
    }

    public static List<SiteErrorBean> getAllSiteErrorBySiteID(Map<String, String> m) {
        return setNames(SiteErrorDAO.getAllSiteErrorBySiteID(m));
    }

    public static SiteErrorBean getSiteErrorBean(String id) {
        return setNames(SiteErrorDAO.getSiteErrorBean(id));
    }

    public static boolean insertSiteError(SiteErrorBean hb, SettingLogsBean stl) {
        hb.setAddTime(DateUtil.getCurrentDateTime());
        hb.setId(PublicTableDAO.getIDByTableName("dz_siteerror"));
        return SiteErrorDAO.insertSiteError(hb, stl);
    }

    public static boolean updateSiteError(SiteErrorBean hb, SettingLogsBean stl) {
        if("2".equals(hb.getStatus()))
        {
            hb.setHandleTime(DateUtil.getCurrentDateTime());
        }
        return SiteErrorDAO.updateSiteError(hb, stl);
    }

    public static boolean publishSiteError(Map<String, String> m)
    {
        String status = m.get("status");
        if("2".equals(status))
        {
            m.put("handleTime",DateUtil.getCurrentDateTime());
        }
        return SiteErrorDAO.publishSiteError(m);
    }

    public static boolean deleteSiteError(Map<String, String> m, SettingLogsBean stl) {
        return SiteErrorDAO.deleteSiteError(m, stl);
    }

    private static List<SiteErrorBean> setNames(List<SiteErrorBean> old)
    {
        List<SiteErrorBean> returnList = new ArrayList<SiteErrorBean>();
        if(old != null && old.size() > 0)
        {
            for(SiteErrorBean seb : old)
            {
                seb.setTypeName(ErrorTypeManager.getErrorTypeBean(seb.getTypeId()+"") != null ? ErrorTypeManager.getErrorTypeBean(seb.getTypeId()+"").getTypeName() : "");
                seb.setSiteName(ErrorSiteManager.getErrorSiteBean(seb.getSiteId() + "") != null ? ErrorSiteManager.getErrorSiteBean(seb.getSiteId() + "").getSiteName() : "");
                returnList.add(seb);
            }
        }
        return returnList;
    }

    private static SiteErrorBean setNames(SiteErrorBean seb)
    {
        seb.setTypeName(ErrorTypeManager.getErrorTypeBean(seb.getTypeId() + "") != null ? ErrorTypeManager.getErrorTypeBean(seb.getTypeId() + "").getTypeName() : "");
        seb.setSiteName(ErrorSiteManager.getErrorSiteBean(seb.getSiteId() + "") != null ?ErrorSiteManager.getErrorSiteBean(seb.getSiteId() + "").getSiteName() : "");
        return seb;
    }
    
    public static List<EchartsBean> getQxEchartsData(Map<String, String> m)
    {
    	List<ErrorSiteBean> allErrorSiteList = ErrorSiteManager.getAllErrorSiteList(m);
    	List<EchartsBean> result = new ArrayList<EchartsBean>();
    	if(allErrorSiteList != null && allErrorSiteList.size() > 0)
    	{
    		for(ErrorSiteBean esb : allErrorSiteList)
    		{
    			EchartsBean eb = new EchartsBean();
    			eb.setSiteName(esb.getSiteName());
    			Map<String, String> mm = new HashMap<String, String>();
    			mm.put("siteId", esb.getId()+"");
    			mm.put("status", "1");
    			String wzg = SiteErrorManager.getSiteErrorCount(mm);
    			eb.setWzg(wzg);
    			mm.remove("status");
    			mm.put("status", "2");
    			String yzg = SiteErrorManager.getSiteErrorCount(mm);
    			eb.setYzg(yzg);
    			result.add(eb);
    		}
    	}
    	return result;
    }
    
    public static List<EchartsBean> getBmEchartsData()
    {
    	List<SiteErrorTopBean> allErrorSiteList = SiteErrorDAO.getSiteErrorTop();
    	List<EchartsBean> result = new ArrayList<EchartsBean>();
    	int index = 0;
    	if(allErrorSiteList != null && allErrorSiteList.size() > 0)
    	{
    		for(int i = 0; i < allErrorSiteList.size(); i++)
    		{
    			SiteErrorTopBean setb = allErrorSiteList.get(i);
    			ErrorSiteBean esb = ErrorSiteManager.getErrorSiteBean(setb.getSiteId());
    			if(esb.getSiteType() == "市级部门")
    			{
	    			EchartsBean eb = new EchartsBean();
	    			eb.setSiteName(esb.getSiteName());
	    			Map<String, String> mm = new HashMap<String, String>();
	    			mm.put("siteId", esb.getId()+"");
	    			mm.put("status", "1");
	    			String wzg = SiteErrorManager.getSiteErrorCount(mm);
	    			eb.setWzg(wzg);
	    			mm.remove("status");
	    			mm.put("status", "2");
	    			String yzg = SiteErrorManager.getSiteErrorCount(mm);
	    			eb.setYzg(yzg);
	    			result.add(eb);
	    			index = index + 1;
	    			if(index >= 15)
	    			{
	    				break;
	    			}
    			}
    		}
    	}
    	return result;
    }
}