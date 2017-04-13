//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.server;

import com.cicro.license.GetLicense;
import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.startup.ServerListener;
import java.io.File;
import java.util.Date;

public class LicenseCheck {
    public static String HAVE_APP_IDS = "system,org,cms,control,zwgk,appeal,ggfw";
    public static int LICENSE_SITE_NUM = 0;
    private static String defaultLicensePath;
    private static String defaultLicensePathRoot;

    static {
        defaultLicensePath = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + File.separator + "cicroAuthorization" + File.separator + "cicroAuthorization.data");
        defaultLicensePathRoot = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + File.separator + "cicroAuthorization");
    }

    public LicenseCheck() {
    }

    public static boolean isHaveApp(String app_id) {
        return "all".equals(HAVE_APP_IDS)?true:HAVE_APP_IDS.contains(app_id);
    }

    public static boolean createLicense(String key) {
        try {
            File e = new File(defaultLicensePathRoot);
            if(!e.exists()) {
                e.mkdir();
            }

            FileOperation.writeStringToFile(new File(defaultLicensePath), key, false);
            if(!check()) {
                ServerListener.isLicenseRight = false;
                FileOperation.deleteDir(defaultLicensePathRoot);
                return false;
            } else {
                ServerListener.isLicenseExist = true;
                ServerListener.isLicenseRight = true;
                return true;
            }
        } catch (Exception var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public static String getAppForLicense() {
        return HAVE_APP_IDS;
    }

    public static boolean isLicenseExist() {
        try {
            if((new File(defaultLicensePath)).exists()) {
                return true;
            } else {
                System.out.println("License----------not found-----");
                return false;
            }
        } catch (Exception var1) {
            var1.printStackTrace();
            return false;
        }
    }

    public static boolean check() {
        try {
        	/*
            GetLicense e = new GetLicense("");
            if(!e.ifServerIdentical()) {
                System.out.println("LicenseCheck : server CPU or MAC inconsistent  ");
                return false;
            } else if(!checkTimeOut(e.getLicenseItemValue("wcm", "time_limit"), e.getLicenseItemValue("wcm", "start_time"))) {
                System.out.println("LicenseCheck : Licence timeout");
                return false;
            } else {
                LICENSE_SITE_NUM = Integer.parseInt(e.getLicenseItemValue("wcm", "site_num"));
                if(checkSiteCount(LICENSE_SITE_NUM)) {
                    System.out.println("LicenseCheck : More than the number of site licenses");
                    return false;  
                } else {
                    String app_ids = e.getLicenseItemValue("wcm", "app_ids");
                    if(app_ids != null) {
                        HAVE_APP_IDS = app_ids;
                    }

                    System.out.println("LicenseCheck : it\'s OK");
                    return true;
                }
            }
            */
        	GetLicense e = new GetLicense("");
        	String app_ids = e.getLicenseItemValue("wcm", "app_ids");
            if(app_ids != null) {
                HAVE_APP_IDS = app_ids;
            }
            return true;
        } catch (Exception var2) {
            var2.printStackTrace(System.out);
            return false;
        }
    }

    public static boolean checkSiteCount(int lic_site_num) {
        try {
            int e = SiteManager.getSiteList().size();
            return e == 0?false:e > lic_site_num;
        } catch (Exception var2) {
            System.out.print(var2);
            return true;
        }
    }

    public static boolean checkTimeOut(String time_limit, String start_time) {
        if(time_limit != null && !"".equals(time_limit) && !"0".equals(time_limit)) {
            try {
                System.out.println("startupDate===============" + start_time);
                if(start_time != null && !"".equals(start_time)) {
                    Date e = DateUtil.getDateTimesAfter(start_time + " 00:00:00", Integer.parseInt(time_limit) * 30);
                    Date current_date = new Date();
                    return e.after(current_date);
                } else {
                    System.out.println("LicenseCheck：startup time is null!");
                    return false;
                }
            } catch (Exception var4) {
                System.out.println(var4);
                return false;
            }
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
    }
}
