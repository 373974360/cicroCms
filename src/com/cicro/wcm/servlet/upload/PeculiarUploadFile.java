//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.servlet.upload;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.UploadManager;
import com.cicro.util.jconfig.JconfigUtilContainer;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PeculiarUploadFile extends HttpServlet {

    public PeculiarUploadFile() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("OK");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    String savePath = JconfigUtilContainer.bashConfig().getProperty("path", "", "hostRoot_path");
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List fileList = null;

        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException var17) {
            return;
        }

        String app_id = request.getParameter("app_id");
        String site_id = request.getParameter("site_id");
        String savePath = UploadManager.getUploadFilePath(site_id) + "/";
        String sid = request.getParameter("sid");
        if (sid == null || "".equals(sid) || UploadManager.checkUploadSecretKey(sid)) {
            savePath = FormatUtil.formatPath(savePath + "/upload/" + DateUtil.getCurrentDateTime("yyyyMM"));
            File ps = new File(savePath);
            if (!ps.exists()) {
                ps.mkdirs();
            }

            Iterator<FileItem> it = fileList.iterator();
            String name = "";
            String extName = "";

            while (true) {
                FileItem item;
                do {
                    do {
                        do {
                            if (!it.hasNext()) {
                                response.getWriter().print(name);
                                return;
                            }

                            item = (FileItem) it.next();
                        } while (item.isFormField());

                        name = item.getName();
                    } while (name == null);
                } while (name.trim().equals(""));

                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf(".")).toLowerCase();
                    if (UploadFileIfy.NOTUPLOAT_FILE_EXT.contains("," + extName.substring(1) + ",")) {
                        return;
                    }
                }

                File file = null;

                do {
                    name = DateUtil.getCurrentDateTime("yyyyMMddhhmmsss");
                    name = savePath + "/" + name + extName;
                    file = new File(name);
                } while (file.exists());
                try {
                    File saveFile = new File(name);
                    item.write(saveFile);
                } catch (Exception var16) {
                    var16.printStackTrace();
                }
            }
        }
    }
}
