//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.zwgk.ysqgk;

import com.cicro.project.dz_siteError.ErrorHandleUserBean;
import com.cicro.project.dz_siteError.ErrorHandleUserManager;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkBean;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkConfigBean;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkListBean;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkPhrasalBean;
import com.cicro.wcm.services.Log.LogManager;
import com.cicro.wcm.services.zwgk.ysqgk.YsqgkConfigManager;
import com.cicro.wcm.services.zwgk.ysqgk.YsqgkInfoManager;
import com.cicro.wcm.services.zwgk.ysqgk.YsqgkPhrasaManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class YsqgkRPC {
    public YsqgkRPC() {
    }

    public static YsqgkConfigBean getYsqgkConfigBean() {
        return YsqgkConfigManager.getYsqgkConfigBean();
    }

    public static boolean insertYsqgkConfig(YsqgkConfigBean ysqgk, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?YsqgkConfigManager.insertYsqgkConfig(ysqgk, stl):false;
    }

    public static Map<String, String> insertYsqgkInfo(YsqgkBean ysqgk, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?YsqgkInfoManager.insertYsqgkInfo(ysqgk, stl):null;
    }

    public static Map<String, String> insertYsqgkInfoForBro(YsqgkBean ysqgk) {
    	
    	Map<String,String> m = new HashMap<String, String>();
		List<ErrorHandleUserBean> allErrorHandleUserList = ErrorHandleUserManager.getAllErrorHandleUserList(m);
		if(allErrorHandleUserList != null && allErrorHandleUserList.size() > 0)
		{
			for (ErrorHandleUserBean errorHandleUserBean : allErrorHandleUserList) {
				if("1".equals(errorHandleUserBean.getIsSendMsg()))
				{
					HttpClient httpclient = new HttpClient();
					PostMethod post = new PostMethod("http://sx.ums86.com:8899/sms/Api/Send.do");
					post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
					post.addParameter("SpCode", "218968");
					post.addParameter("LoginName", "wn_xxh");
					post.addParameter("Password", "wn1234");
					post.addParameter("MessageContent", "市政府网站有依申请公开需要处理，请及时回复。");
					post.addParameter("UserNumber", errorHandleUserBean.getPhone());
					post.addParameter("SerialNumber", "");
					post.addParameter("ScheduleTime", "");
					post.addParameter("whitevalid", "1");
					post.addParameter("f", "1");
					try {
						httpclient.executeMethod(post);
						String info = new String(post.getResponseBody(),"gbk");
						System.out.println(info);
					} catch (HttpException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
    	
        return YsqgkInfoManager.insertYsqgkInfo(ysqgk, (SettingLogsBean)null);
    }

    public static YsqgkBean getYsqgkBean(String ysq_id) {
        return YsqgkInfoManager.getYsqgkBean(ysq_id);
    }

    public static List<YsqgkListBean> getYsqgkLists(Map<String, String> m) {
        return YsqgkInfoManager.getYsqgkLists(m);
    }

    public static int getYsqgkListsCount(Map<String, String> m) {
        return YsqgkInfoManager.getYsqgkListsCount(m);
    }

    public static boolean updateStatus(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?YsqgkInfoManager.updateStatus(m, stl):false;
    }

    public static boolean updateYsqgkInfo(YsqgkBean ysqgk, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null) {
            YsqgkInfoManager.updateYsqgkInfo(ysqgk, stl);
            return true;
        } else {
            return false;
        }
    }

    public static boolean setDeleteState(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null) {
            YsqgkInfoManager.setDeleteState(m, stl);
            return true;
        } else {
            return false;
        }
    }

    public static boolean reBackInfos(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null) {
            YsqgkInfoManager.reBackInfos(m, stl);
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteYsqgkInfo(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null) {
            YsqgkInfoManager.deleteYsqgkInfo(m, stl);
            return true;
        } else {
            return false;
        }
    }

    public static boolean clearDeleteYsqgkInfos(HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null) {
            YsqgkInfoManager.clearDeleteYsqgkInfos(stl);
            return true;
        } else {
            return false;
        }
    }

    public static YsqgkPhrasalBean getYsqgkPhrasalBean(String gph_id) {
        return YsqgkPhrasaManager.getYsqgkPhrasalById(gph_id);
    }

    public static List<YsqgkPhrasalBean> getYsqgkPhrasalLists() {
        return YsqgkPhrasaManager.getYsqgkPhrasaList();
    }

    public static List<YsqgkPhrasalBean> getYsqgkPhrasaListByType(int gph_type) {
        return YsqgkPhrasaManager.getYsqgkPhrasaListByType(gph_type);
    }

    public static String getGph_id() {
        return YsqgkPhrasaManager.getGph_id();
    }

    public static boolean insertYsqgkPhrasalBean(YsqgkPhrasalBean ypb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?YsqgkPhrasaManager.insertYsqgkPhrasal(ypb, stl):false;
    }

    public static boolean updateYsqgkPhrasalBean(YsqgkPhrasalBean ypb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?YsqgkPhrasaManager.updateYsqgkPhrasal(ypb, stl):false;
    }

    public static boolean deleteYsqgkPhrasal(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null) {
            YsqgkPhrasaManager.deletePhrasalBeans(m, stl);
            return true;
        } else {
            return false;
        }
    }

    public static boolean saveYsqgkPhrasalSort(String gph_id, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?YsqgkPhrasaManager.savePhrasalSort(gph_id, stl):false;
    }
}
