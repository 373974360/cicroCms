package com.cicro.wcm.servlet.upload;

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

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.UploadManager;

public class PeculiarUploadFile extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	  doPost(request,response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	  String site_id = request.getParameter("site_id");
    String savePath = UploadManager.getUploadFileFrontPath(site_id) + "/";
    DiskFileItemFactory fac = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(fac);
    upload.setHeaderEncoding("utf-8");
    List fileList = null;
    try {
      fileList = upload.parseRequest(request);
    } catch (FileUploadException ex) {
      return;
    }

    String app_id = request.getParameter("app_id");
    String sid = request.getParameter("sid");
    System.out.println("PeculiarUploadFile sid----- " + sid);
    if (!UploadManager.checkUploadSecretKey(sid))
    {
      System.out.println("Upload validation errors");
      return;
    }
    System.out.println(savePath+"*******************************");
    savePath = FormatUtil.formatPath(savePath + site_id + "/" + app_id + "/" + DateUtil.getCurrentDateTime("yyyyMM"));
    System.out.println(savePath+"*******************************");
    File ps = new File(savePath);
    if (!ps.exists()) {
      ps.mkdirs();
    }
    Iterator it = fileList.iterator();
    String name = "";
    String extName = "";
    String outName = "";
    while (it.hasNext()) {
      FileItem item = (FileItem)it.next();
      if (!item.isFormField()) {
        name = item.getName();

        if ((name != null) && (!name.trim().equals("")))
        {
          if (name.lastIndexOf(".") >= 0) {
            extName = name.substring(name.lastIndexOf("."))
              .toLowerCase();
          }
          File file = null;
          do
          {
            name = DateUtil.getCurrentDateTime("yyyyMMddhhmmsss");
            outName = name + extName;
            name = savePath + "/" + name + extName;
            file = new File(name);
          }
          while (
            file.exists());
          try
          {
        	  if(extName.equals(".wmv") || extName.equals(".mp4") || extName.equals(".rmvb") ||
        	     extName.equals(".mkv") || extName.equals(".avi") || extName.equals(".3gp") ||
        	     extName.equals(".flv") || extName.equals(".rm") || extName.equals(".mpg") ||
        	     extName.equals(".mov") || extName.equals(".vob") || extName.equals(".jpg") ||
        	     extName.equals(".png") || extName.equals(".jpeg") || extName.equals(".bmp") || 
        	     extName.equals(".gif"))
        	  {
        		  System.out.println("文件写入文件成功！***********************");
        		  File saveFile = new File(name);
                  item.write(saveFile);
                  savePath = "/" + site_id + "/" + app_id + "/" + DateUtil.getCurrentDateTime("yyyyMM") + "/";
                  String outStr = "{\"att_path\":\"" + savePath + "\",\"att_ename\":\"" + outName + "\"}";

                  response.getWriter().print(outStr);
                  System.out.println(outStr);
        	  }
        	  else
        	  {
        		  return;
        	  }
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
    //response.getWriter().print(name);
  }
}