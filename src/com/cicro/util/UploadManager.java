//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.util;

import com.cicro.util.CryptoTools;
import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.RandomStrg;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import com.cicro.wcm.services.material.MateInfoRPC;
import java.io.File;

public class UploadManager {
    private static String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
    private static String hostRoot_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "hostRoot_path");
    private static String public_save_path = JconfigUtilContainer.bashConfig().getProperty("save_path", "", "resource_server");
    private static String public_path = JconfigUtilContainer.bashConfig().getProperty("public_path", "", "resource_server");
    private static String img_domain = JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server");
    private static String site_port = JconfigUtilContainer.bashConfig().getProperty("port", "", "site_port");

    public UploadManager() {
    }

    public static String getUploadSecretKey() {
        String sid = "";

        try {
            String e = RandomStrg.getRandomStr("", RandomStrg.getRandomStr("0-9", "1")) + "," + DateUtil.dateToTimestamp();
            CryptoTools ct = new CryptoTools();
            sid = ct.encode(e).replace("+", "WcMrEPlAcE").substring(3);
            return sid;
        } catch (Exception var3) {
            var3.printStackTrace();
            return sid;
        }
    }

    public static boolean checkUploadSecretKey(String key) {
        try {
            if(key == null || "".equals(key.trim())) {
                key = MateInfoRPC.getUploadSecretKey();
            }

            key = "=#=" + key;
            CryptoTools e = new CryptoTools();
            key = e.decode(key.replace("WcMrEPlAcE", "+"));
            key = key.substring(key.indexOf(",") + 1);
            int times = 60 * Integer.parseInt(JconfigUtilContainer.bashConfig().getProperty("value", "10", "upload_check_times"));
            long timel = Long.parseLong(key);
            return DateUtil.dateToTimestamp() - timel < (long)(1000 * times + 100);
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static String getUploadFilePath2() {
        String savePath = "";
        if(img_domain != null && !"".equals(img_domain.trim())) {
            savePath = FormatUtil.formatPath(public_save_path);
            return savePath;
        } else {
            return FormatUtil.formatPath(root_path);
        }
    }

    public static String getUploadFileUrl(String site_id) {
        String savePath = "";
        if(img_domain != null && !"".equals(img_domain.trim())) {
            if(site_port != null && !"".equals(site_port.trim())) {
                savePath = "http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server") + ":" + site_port;
            } else {
                savePath = "http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server");
            }
        } else if(public_path != null && !"".equals(public_path)) {
            savePath = "/cms.files";
        }

        savePath = savePath + getUploadFileSitePath(site_id);
        return savePath;
    }

    public static String getUploadFilePath(String site_id) {
        String savePath = "";
        if(img_domain != null && !"".equals(img_domain.trim())) {
            savePath = FormatUtil.formatPath(public_save_path);
        } else if(public_path != null && !"".equals(public_path)) {
            savePath = FormatUtil.formatPath(public_path);
        } else {
            savePath = hostRoot_path + "/" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id) + "/ROOT";
        }

        savePath = FormatUtil.formatPath(savePath + getUploadFileSitePath(site_id));
        File f1 = new File(savePath);
        if(!f1.exists()) {
            f1.mkdirs();
        }

        return savePath;
    }

    public static String getUploadFileSitePath(String site_id) {
        return "/upload/" + site_id + "/" + DateUtil.getCurrentDateTime("yyyyMM") + "/";
    }

    public static String getImgBrowserUrl(String site_id) {
        return img_domain != null && !"".equals(img_domain.trim())?(site_port != null && !"".equals(site_port.trim())?"http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server") + ":" + site_port:"http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server")):"";
    }

    public static String getUploadFilePathForUeditor(String site_id) {
        String savePath = "";
        if(img_domain != null && !"".equals(img_domain.trim())) {
            savePath = FormatUtil.formatPath(public_save_path);
        } else if(public_path != null && !"".equals(public_path)) {
            savePath = FormatUtil.formatPath(public_path);
        } else {
            savePath = hostRoot_path + "/" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id) + "/ROOT";
        }

        String dirString = getUploadFileSitePath(site_id);
        savePath = FormatUtil.formatPath(savePath + dirString.substring(0, dirString.length() - 8));
        File f1 = new File(savePath);
        if(!f1.exists()) {
            f1.mkdirs();
        }

        return savePath;
    }

    public static String getFileUrlForUeditor(String site_id) {
        String savePath = "";
        if(img_domain != null && !"".equals(img_domain.trim())) {
            if(site_port != null && !"".equals(site_port.trim())) {
                savePath = "http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server") + ":" + site_port;
                return savePath;
            } else {
                savePath = "http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server");
                return savePath;
            }
        } else if(public_path != null && !"".equals(public_path)) {
            savePath = root_path;
            return savePath;
        } else {
            savePath = hostRoot_path + "/" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id) + "/ROOT";
            return savePath;
        }
    }

    public static void main(String[] args) {
        System.out.println("/deya/cms/vhosts/www.demo.com/ROOT/upload/CMSdemo/201508".replace("/upload/CMSdemo/201508/", ""));
    }
}
