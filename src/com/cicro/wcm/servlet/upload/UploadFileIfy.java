package com.cicro.wcm.servlet.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.UploadManager;
import com.cicro.util.img.ImageUtils;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.material.MateInfoBean;
import com.cicro.wcm.bean.org.user.LoginUserBean;
import com.cicro.wcm.server.ServerManager;
import com.cicro.wcm.services.Log.LogManager;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import com.cicro.wcm.services.material.MateInfoManager;
import com.cicro.wcm.services.material.MateInfoRPC;
import com.cicro.wcm.services.org.user.SessionManager;
import com.cicro.wcm.services.system.config.ConfigManager;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class UploadFileIfy extends HttpServlet {
    public static String hd_url = "";
    public static String thum_url = "";
    public static String pic_name = "";
    public static String NOTUPLOAT_FILE_EXT = ",php,php3,php5,phtml,asp,aspx,ascx,jsp,cfm,cfc,pl,bat,exe,dll,reg,cgi,";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete_file = request.getParameter("delete_file");
        String sid = FormatUtil.formatNullString(request.getParameter("sid"));

        if ((sid == null) || ("".equals(sid)) || (!UploadManager.checkUploadSecretKey(sid))) {
            System.out.println("Upload validation errors");
            return;
        }

        if ((delete_file != null) && (!"".equals(delete_file))) {
            String savePath = UploadManager.getUploadFilePath2();
            String[] tempA = delete_file.split(",");
            for (int i = 0; i < tempA.length; i++) {
                String file_path = FormatUtil.formatPath(savePath + tempA[i]);
                File f = new File(file_path);
                if (f.exists())
                    f.delete();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String site_id = request.getParameter("site_id");
        String sid = FormatUtil.formatNullString(request.getParameter("sid"));

        if ((sid == null) || ("".equals(sid)) || (!UploadManager.checkUploadSecretKey(sid))) {
            System.out.println("Upload validation errors");
            return;
        }

        thum_url = "";

        String savePath = UploadManager.getUploadFilePath(site_id) + "/";

        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }

        String is_press = request.getParameter("is_press");
        String press_osition = request.getParameter("press_osition");

        Iterator it = fileList.iterator();
        String name = "";
        String extName = "";
        while(true) {
            FileItem tmpUploadFilePath;
            do {
                label66:
                do {
                    while(it.hasNext()) {
                        tmpUploadFilePath = (FileItem)it.next();
                        if(!tmpUploadFilePath.isFormField()) {
                            name = tmpUploadFilePath.getName();
                            continue label66;
                        }

                        System.out.println("-------->" + tmpUploadFilePath.getFieldName());
                    }

                    String domain1 = UploadManager.getUploadFilePath2();
                    savePath = savePath.substring(domain1.length());
                    if(thum_url == null || "".equals(thum_url)) {
                        thum_url = name + extName;
                    }

                    if(ServerManager.isWindows()) {
                        savePath = savePath.replaceAll("\\\\", "\\/");
                        if(savePath.startsWith("//")) {
                            savePath = savePath.substring(1);
                        }
                    }

                    System.out.println(savePath + "***************savePath***********************");
                    String var202 = SiteDomainManager.getDefaultSiteDomainBySiteID(site_id);
                    System.out.println(var202 + "***************domain***********************");
                    String replace1 = "/vhosts/";
                    String replace2 = "/ROOT";
                    savePath = savePath.replace(replace1, "http://");
                    savePath = savePath.replace(replace2, "");
                    String outStr = "{\"att_path\":\"" + savePath + "\",\"att_ename\":\"" + name + extName + "\",\"hd_url\":\"" + hd_url + "\",\"thum_url\":\"" + thum_url + "\"}";
                    System.out.println(outStr + "***************outStr***********************");
                    response.getWriter().print(outStr);
                    return;
                } while(name == null);
            } while(name.trim().equals(""));

            if(name.lastIndexOf(".") >= 0) {
                extName = name.substring(name.lastIndexOf(".")).toLowerCase();
                if(NOTUPLOAT_FILE_EXT.contains("," + extName.substring(1) + ",")) {
                    return;
                }
            }

            File domain = null;

            do {
                name = DateUtil.getCurrentDateTime("yyyyMMddhhmmsss");
                domain = new File(savePath + name + extName);
            } while(domain.exists());

            try {
                if(!extName.equals(".gif") && !extName.equals(".jpg") && !extName.equals(".jpeg") && !extName.equals(".png")) {
                    File var20 = new File(savePath + name + extName);
                    tmpUploadFilePath.write(var20);
                    do {
                        System.out.println("正在写入非图片文件！");
                    } while (!var20.exists());
                } else {
                    processImage(domain, tmpUploadFilePath, name, extName, savePath, is_press, press_osition, site_id);
                }
            } catch (Exception var21) {
                var21.printStackTrace();
            }
        }
    }

    public static void processImage(File file, FileItem item, String name, String extName, String savePath, String is_press, String press_osition, String site_id) throws IOException {
        try {
            String pressImg = "";
            int press_img_width = 300;
            int press_img_height = 150;
            int alpha = 80;
            int content_img_width = ImageUtils.getImgWidth();
            int prev_img_width = ImageUtils.getImgPreWidth();

            Map<String, String> m = new HashMap<String, String>();
            m.put("group", "attachment");
            m.put("site_id", site_id);
            m.put("app_id", "cms");
            Map<String, String> con_map = ConfigManager.getConfigMap(m);

            String watermark = (String) con_map.get("watermark");
            String press_osition_config = (String) con_map.get("water_location");
            if ("0".equals(press_osition))
                press_osition = press_osition_config;
            pressImg = (String) con_map.get("water_pic");
            if ((pressImg != null) && (!"".equals(pressImg))) {
                String img_domain = JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server");
                if (img_domain != null && !"".equals(img_domain)) {
                    pressImg = JconfigUtilContainer.bashConfig().getProperty("save_path", "", "resource_server") + pressImg;
                } else {
                    String public_path = JconfigUtilContainer.bashConfig().getProperty("public_path", "", "resource_server");
                    String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
                    if (public_path != null && !"".equals(public_path)) {
                        pressImg = root_path + pressImg;
                    } else {
                        String allString = UploadManager.getUploadFilePath(site_id) + "/";
                        String replaString = UploadManager.getUploadFileSitePath(site_id);
                        pressImg = allString.replace(replaString, "") + pressImg;
                    }
                }
            }
            String water_width = (String) con_map.get("water_width");
            if ((water_width != null) && (!"".equals(water_width)))
                press_img_width = Integer.parseInt(water_width);
            String water_height = (String) con_map.get("water_height");
            if ((water_height != null) && (!"".equals(water_height)))
                press_img_height = Integer.parseInt(water_height);
            String water_transparent = (String) con_map.get("water_transparent");
            alpha = (int) (Float.parseFloat(water_transparent) * 100);
            String normal_width = (String) con_map.get("normal_width");
            if ((normal_width != null) && (!"".equals(normal_width))) {
                content_img_width = Integer.parseInt(normal_width);
            }
            String thumb_width = (String) con_map.get("thumb_width");
            if ((thumb_width != null) && (!"".equals(thumb_width))) {
                prev_img_width = Integer.parseInt(thumb_width);
            }
            String is_compress = "1";
            if (con_map.containsKey("is_compress")) {
                is_compress = (String) con_map.get("is_compress");
            }
            BufferedImage bis = null;
            try {
                bis = ImageIO.read(item.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
                JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(item.getInputStream());
                bis = decoder.decodeAsBufferedImage();
            }
            //BufferedImage bis = ImageIO.read(item.getInputStream());
            int w = bis.getWidth();
            int h = bis.getHeight();
            if (w > content_img_width) {
                File saveFile = new File(savePath + name + "_b" + extName);
                hd_url = name + "_b" + extName;
                item.write(saveFile);
                do {
                    System.out.println("正在写入文件！");
                } while (!saveFile.exists());
                ImageUtils.cutImage(content_img_width, savePath + hd_url, savePath + name + extName);
                if ("1".equals(is_compress)) {
                    if (("1".equals(is_press)) && ("1".equals(watermark))) {
                        if ((pressImg != null) && (!"".equals(pressImg))) {
                            ImageUtils.addImgText(pressImg, savePath + name + extName, Integer.parseInt(press_osition), extName, alpha);
                        }
                    }
                }
                if (w > prev_img_width) {
                    thum_url = name + "_s" + extName;
                    ImageUtils.cutImage(prev_img_width, savePath + hd_url, savePath + thum_url);
                }
            } else {
                File saveFile = file;
                item.write(saveFile);
                do {
                    System.out.println("正在写入文件！");
                } while (!saveFile.exists());
                if (("1".equals(is_press)) && ("1".equals(watermark)) && (w > press_img_width) && (h > press_img_height)) {
                    ImageUtils.addImgText(pressImg, savePath + name + extName, Integer.parseInt(press_osition), extName, alpha);
                }
                if (w > prev_img_width) {
                    thum_url = name + "_s" + extName;
                    ImageUtils.cutImage(prev_img_width, savePath + name + extName, savePath + thum_url);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //String outStr = "{\"att_path\":\"" + savePath + "\",\"att_ename\":\"" + name + extName + "\",\"hd_url\":\"" + hd_url + "\",\"thum_url\":\"" + thum_url + "\"}";

    //response.getWriter().print(outStr);
    //return outStr;
}