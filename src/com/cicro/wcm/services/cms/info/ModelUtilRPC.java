//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.cms.info;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.rmi.file.FileRmiFactory;
import com.cicro.wcm.services.Log.LogManager;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.cms.info.ModelUtil;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

public class ModelUtilRPC {
    public ModelUtilRPC() {
    }

    public static Object select(String infoId, String siteId, String model_name) {
        return ModelUtil.select(infoId, siteId, model_name);
    }

    public static boolean insert(Object ob, String model_name, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);

        try {
            if(stl != null) {
                String e = "0";
                String site_id = "";
                String app_id = "";

                try {
                    e = BeanUtils.getProperty(ob, "info_status");
                    site_id = BeanUtils.getProperty(ob, "site_id");
                    app_id = BeanUtils.getProperty(ob, "app_id");
                } catch (IllegalAccessException var8) {
                    var8.printStackTrace();
                } catch (InvocationTargetException var9) {
                    var9.printStackTrace();
                } catch (NoSuchMethodException var10) {
                    var10.printStackTrace();
                }

                return "8".equals(e)?FileRmiFactory.insertInfo(InfoBaseManager.getInfoReleSiteID(site_id, app_id), ob, model_name, stl):ModelUtil.insert(ob, model_name, stl);
            } else {
                return false;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            return false;
        }
    }

    public static boolean update(Object ob, String model_name, HttpServletRequest request) {
        try {
            System.out.println("ModelUtilRPC :: update :: start");
            SettingLogsBean e = LogManager.getSettingLogsByRequest(request);
            if(e != null) {
                String info_status = "0";
                String site_id = "";
                String app_id = "";

                try {
                    info_status = BeanUtils.getProperty(ob, "info_status");
                    site_id = BeanUtils.getProperty(ob, "site_id");
                    app_id = BeanUtils.getProperty(ob, "app_id");
                } catch (IllegalAccessException var8) {
                    var8.printStackTrace();
                } catch (InvocationTargetException var9) {
                    var9.printStackTrace();
                } catch (NoSuchMethodException var10) {
                    var10.printStackTrace();
                }

                return "8".equals(info_status)?FileRmiFactory.updateInfo(InfoBaseManager.getInfoReleSiteID(site_id, app_id), ob, model_name, e):ModelUtil.update(ob, model_name, e);
            } else {
                return false;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            return false;
        }
    }
}
