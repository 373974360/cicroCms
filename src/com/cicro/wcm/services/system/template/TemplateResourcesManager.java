/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.RandomStrg;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateResourcesBean;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TemplateResourcesManager
/*     */ {
/*     */   public static List<TemplateResourcesBean> getResourcesListBySiteID(String path)
/*     */   {
/*  27 */     List l = FileOperation.getFileSinglList(path);
/*  28 */     List file_list = new ArrayList();
/*  29 */     FileStringToList(l, file_list);
/*  30 */     return file_list;
/*     */   }
/*     */ 
/*     */   public static List<TemplateResourcesBean> getResImageListBySiteID(String site_id)
/*     */   {
/*  35 */     String path = "";
/*  36 */     if ("shared_res".equals(site_id))
/*  37 */       path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + "/wcm.files";
/*     */     else
/*  39 */       path = SiteManager.getSiteBeanBySiteID(site_id).getSite_path();
/*  40 */     String img_path = FormatUtil.formatPath(path + "/images");
/*     */ 
/*  42 */     List l = FileOperation.getFileSinglList(img_path);
/*  43 */     List file_list = new ArrayList();
/*  44 */     FileStringToList(l, file_list);
/*  45 */     return file_list;
/*     */   }
/*     */ 
/*     */   public static void FileStringToList(List<String> l, List<TemplateResourcesBean> file_list)
/*     */   {
/*  50 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  52 */       for (int i = 0; i < l.size(); i++)
/*     */         try
/*     */         {
/*  55 */           String name = "";
/*  56 */           if (ServerManager.isWindows())
/*     */           {
/*  58 */             name = ((String)l.get(i)).substring(((String)l.get(i)).lastIndexOf("\\") + 1);
/*     */           }
/*  60 */           else name = ((String)l.get(i)).substring(((String)l.get(i)).lastIndexOf("/") + 1);
/*  61 */           TemplateResourcesBean trb = new TemplateResourcesBean();
/*  62 */           trb.setFile_name(name);
/*  63 */           trb.setFile_type(name.substring(name.lastIndexOf(".") + 1).toLowerCase());
/*  64 */           trb.setFile_size(FileOperation.getFileSize((String)l.get(i)) + "K");
/*  65 */           trb.setFile_path((String)l.get(i));
/*  66 */           file_list.add(trb);
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/*  70 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean deleteTemplateResources(String file_path)
/*     */   {
/*  78 */     System.out.println("deleteTemplateResources----------------" + file_path);
/*  79 */     File f = new File(file_path);
/*  80 */     return f.delete();
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateResourcesFolder(String file_path)
/*     */   {
/*  85 */     File f = new File(file_path);
/*  86 */     return f.mkdir();
/*     */   }
/*     */ 
/*     */   public static boolean updateResourcesFile(String file_path, String file_content) throws IOException
/*     */   {
/*  91 */     return FileOperation.writeStringToFile(file_path, file_content, false, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String getResourcesFileContent(String file_path) throws IOException
/*     */   {
/*  96 */     return FileOperation.readFileToString(file_path, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStr(String site_id)
/*     */   {
/* 102 */     String path = "";
/* 103 */     if ("shared_res".equals(site_id))
/* 104 */       path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + "/wcm.files";
/*     */     else
/* 106 */       path = SiteManager.getSiteBeanBySiteID(site_id).getSite_path();
/* 107 */     String img_path = FormatUtil.formatPath(path + "/images");
/* 108 */     String style_path = FormatUtil.formatPath(path + "/styles");
/* 109 */     String js_path = FormatUtil.formatPath(path + "/js");
/*     */ 
/* 111 */     String json = "";
/* 112 */     json = json + "{\"id\":0,\"text\":\"images\",\"attributes\":{\"url\":\"" + img_path + "\"},\"children\":[" + getFolderJSONStrForPath(img_path) + "]}";
/* 113 */     json = json + ",{\"id\":2,\"text\":\"styles\",\"attributes\":{\"url\":\"" + style_path + "\"},\"children\":[" + getFolderJSONStrForPath(style_path) + "]}";
/* 114 */     json = json + ",{\"id\":1,\"text\":\"js\",\"attributes\":{\"url\":\"" + js_path + "\"},\"children\":[" + getFolderJSONStrForPath(js_path) + "]}";
/*     */ 
/* 116 */     if (ServerManager.isWindows())
/*     */     {
/* 118 */       json = json.replaceAll("\\\\", "\\\\\\\\");
/*     */     }
/* 120 */     return "[" + json + "]";
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStrForPath(String file_path)
/*     */   {
/* 125 */     String json_str = "";
/* 126 */     List list = FileOperation.getFolderList(file_path);
/* 127 */     if ((list != null) && (list.size() > 0))
/*     */     {
/* 129 */       json_str = getFolderJSONStrHandl(list);
/*     */     }
/* 131 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStrHandl(List<String> l)
/*     */   {
/* 136 */     String json = "";
/* 137 */     for (int i = 0; i < l.size(); i++)
/*     */     {
/* 139 */       String text = (String)l.get(i);
/* 140 */       text = text.substring(text.lastIndexOf(File.separator) + 1);
/* 141 */       json = json + ",{\"id\":" + Integer.parseInt(RandomStrg.getRandomStr("0-9", "4")) + ",\"text\":\"" + text + "\",\"attributes\":{\"url\":\"" + (String)l.get(i) + "\"}";
/* 142 */       List list = FileOperation.getFolderList((String)l.get(i));
/* 143 */       if ((list != null) && (list.size() > 0))
/*     */       {
/* 145 */         json = json + ",\"children\":[" + getFolderJSONStrHandl(list) + "]";
/*     */       }
/* 147 */       json = json + "}";
/*     */     }
/* 149 */     if ((json != "") && (json != null)) {
/* 150 */       json = json.substring(1);
/*     */     }
/* 152 */     return json;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 157 */     System.out.println(deleteTemplateResources("d:\\cicro\\cms\\vhosts\\www.cicrocms.com\\ROOT\\js\\photo\\img\\white\\Chrysanthemum.jpg"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateResourcesManager
 * JD-Core Version:    0.6.2
 */