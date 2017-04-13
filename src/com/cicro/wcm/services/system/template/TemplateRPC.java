/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateClassBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateResourcesBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateVerBean;
/*     */ import com.cicro.wcm.rmi.file.FileRmiFactory;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class TemplateRPC
/*     */ {
/*     */   public static int getNewTemplate()
/*     */   {
/*  29 */     return TemplateEditManager.getNewTemplate();
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStr(String site_id)
/*     */   {
/*  35 */     return FileRmiFactory.getFolderJSONStr(site_id);
/*     */   }
/*     */ 
/*     */   public static List<TemplateResourcesBean> getResourcesListBySiteID(String site_id)
/*     */   {
/*  41 */     return FileRmiFactory.getResourcesListBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static List<TemplateResourcesBean> getResImageListBySiteID(String site_id)
/*     */   {
/*  46 */     return FileRmiFactory.getResImageListBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateResourcesFolder(String file_path)
/*     */   {
/*  52 */     return FileRmiFactory.addTemplateResourcesFolder(file_path);
/*     */   }
/*     */ 
/*     */   public static boolean deleteTemplateResources(String file_path, String site_id)
/*     */   {
/*  57 */     return FileRmiFactory.deleteTemplateResources(file_path, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateResourcesFile(String file_path, String file_content, String site_id) throws IOException
/*     */   {
/*  62 */     return FileRmiFactory.updateResourcesFile(file_path, file_content, site_id);
/*     */   }
/*     */ 
/*     */   public static String getResourcesFileContent(String file_path, String site_id) throws IOException
/*     */   {
/*  67 */     return FileRmiFactory.getResourcesFileContent(file_path, site_id);
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getAllTemplateEditList()
/*     */   {
/*  72 */     return TemplateEditManager.getAllTemplateEditList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getTemplateEditList(Map<String, String> con_map) {
/*  76 */     return TemplateEditManager.getTemplateEditListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateEditCount(Map<String, String> con_map) {
/*  80 */     return TemplateEditManager.getTemplateEditCount(con_map);
/*     */   }
/*     */ 
/*     */   public static TemplateEditBean getTemplateEditById(int template_id, String site_id, String app_id) {
/*  84 */     return TemplateEditManager.getTemplateEditBean(template_id, site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static String getTemplateNameById(int template_id, String site_id, String app_id) {
/*  88 */     return TemplateEditManager.getTemplateNameById(template_id, site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateEditById(TemplateEditBean template, HttpServletRequest request) {
/*  92 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  93 */     if (stl != null)
/*     */     {
/*  95 */       return TemplateEditManager.updateTemplateEdit(template, stl);
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateEdit(TemplateEditBean template, HttpServletRequest request) {
/* 101 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 102 */     if (stl != null)
/*     */     {
/* 104 */       return TemplateEditManager.addTemplateEdit(template, stl);
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateEditForDesign(TemplateEditBean template, boolean is_update, HttpServletRequest request)
/*     */   {
/* 116 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 117 */     if (stl != null)
/*     */     {
/* 119 */       return TemplateEditManager.addTemplateEditForDesign(template, is_update, stl);
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateEditById(String template_id, String site_id, HttpServletRequest request) {
/* 125 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 126 */     if (stl != null)
/*     */     {
/* 128 */       return TemplateEditManager.delTemplateEditById(template_id, site_id, stl);
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publish(String t_ids, String site_id, String app_id, HttpServletRequest request) {
/* 134 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 135 */     if (stl != null)
/*     */     {
/* 137 */       return TemplateEditManager.publishTemplate(t_ids, site_id, app_id, stl);
/*     */     }
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean cancel(String t_ids, String site_id, String app_id, HttpServletRequest request) {
/* 143 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 144 */     if (stl != null)
/*     */     {
/* 146 */       return TemplateEditManager.cancelTemplate(t_ids, site_id, app_id, stl);
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TemplateVerBean> getAllTemplateVerList()
/*     */   {
/* 153 */     return TemplateVerManager.getAllTemplateVerList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateVerBean> getTemplateVerList(Map<String, String> con_map) {
/* 157 */     return TemplateVerManager.getTemplateVerListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateVerCount(Map<String, String> con_map) {
/* 161 */     return TemplateVerManager.getTemplateVerCount(con_map);
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getTemplateVerById(int template_id, String version, String site_id) {
/* 165 */     return TemplateVerManager.getTemplateVerBean(template_id, version, site_id);
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getSimpleTemplateVerBean(int template_id, String version, String site_id)
/*     */   {
/* 174 */     return TemplateVerManager.getSimpleTemplateVerBean(template_id, version, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean recoveryTemplateVer(int template_id, String site_id, String version, HttpServletRequest request) {
/* 178 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 179 */     if (stl != null)
/*     */     {
/* 181 */       return TemplateVerManager.recoveryTemplateVer(template_id, site_id, version, stl);
/*     */     }
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TemplateBean> getAllTemplateList()
/*     */   {
/* 189 */     return TemplateManager.getAllTemplateList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateBean> getTemplateList(Map<String, String> con_map) {
/* 193 */     return TemplateManager.getTemplateListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateCount(Map<String, String> con_map) {
/* 197 */     return TemplateManager.getTemplateCount(con_map);
/*     */   }
/*     */ 
/*     */   public static TemplateBean getTemplateById(int template_id) {
/* 201 */     return TemplateManager.getTemplateBean(template_id);
/*     */   }
/*     */ 
/*     */   public static List<TemplateClassBean> getAllTemplateClassList()
/*     */   {
/* 206 */     return TemplateClassManager.getAllTemplateClassList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateClassBean> getTemplateClassList(Map<String, String> con_map) {
/* 210 */     return TemplateClassManager.getTemplateClassListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateClassCount(Map<String, String> con_map) {
/* 214 */     return TemplateClassManager.getTemplateClassCount(con_map);
/*     */   }
/*     */ 
/*     */   public static TemplateClassBean getTemplateClassById(int template_id) {
/* 218 */     return TemplateClassManager.getTemplateClassBean(template_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateClassById(TemplateClassBean template, HttpServletRequest request) {
/* 222 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 223 */     if (stl != null)
/*     */     {
/* 225 */       return TemplateClassManager.updateTemplateClass(template, stl);
/*     */     }
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateClass(TemplateClassBean template, HttpServletRequest request) {
/* 231 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 232 */     if (stl != null)
/*     */     {
/* 234 */       return TemplateClassManager.addTemplateClass(template, stl);
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateClassById(String template_id, HttpServletRequest request) {
/* 240 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 241 */     if (stl != null)
/*     */     {
/* 243 */       return TemplateClassManager.delTemplateClassById(template_id, stl);
/*     */     }
/* 245 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getAllTemplateCategoryList()
/*     */   {
/* 250 */     return TemplateCategoryManager.getAllTemplateCategoryList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getTemplateCategoryList(Map<String, String> con_map) {
/* 254 */     return TemplateCategoryManager.getTemplateCategoryListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateCategoryCount(Map<String, String> con_map) {
/* 258 */     return TemplateCategoryManager.getTemplateCategoryCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean sortTemplateCategory(String ids, HttpServletRequest request)
/*     */   {
/* 269 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 270 */     if (stl != null)
/*     */     {
/* 272 */       return TemplateCategoryManager.sortTemplateCategory(ids, stl);
/*     */     }
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   public static TemplateCategoryBean getTemplateCategoryById(int template_id, String site_id, String app_id) {
/* 278 */     return TemplateCategoryManager.getTemplateCategoryBean(template_id, site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateCategoryById(TemplateCategoryBean template, HttpServletRequest request) {
/* 282 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 283 */     if (stl != null)
/*     */     {
/* 285 */       return TemplateCategoryManager.updateTemplateCategory(template, stl);
/*     */     }
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getNewID()
/*     */   {
/* 292 */     return TemplateCategoryManager.getNewID();
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateCategory(TemplateCategoryBean template, HttpServletRequest request) {
/* 296 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 297 */     if (stl != null)
/*     */     {
/* 299 */       return TemplateCategoryManager.addTemplateCategory(template, stl);
/*     */     }
/* 301 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateCategoryById(String template_id, String site_id, HttpServletRequest request) {
/* 305 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 306 */     if (stl != null)
/*     */     {
/* 308 */       return TemplateCategoryManager.delTemplateCategoryById(template_id, site_id, stl);
/*     */     }
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */   public static String templateCategoryListToJsonStr(String id, String site_id, String appid) {
/* 314 */     return TemplateCategoryManager.templateCategoryListToJsonStr(id, site_id, appid);
/*     */   }
/*     */ 
/*     */   public static boolean updateUploadTemplateFile(String file_path, String t_id, String site_id, String t_cname, String modify_dtime, String t_ename, String creat_dtime, String id, String modify_user, String tcat_id, String t_path, String app_id, String t_ver, String creat_user, HttpServletRequest request)
/*     */   {
/* 336 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*     */ 
/* 338 */     String tContent = TemplateManager.readFileToString(site_id, file_path);
/*     */ 
/* 340 */     TemplateEditBean template = null;
/*     */ 
/* 342 */     if (stl != null)
/*     */     {
/* 344 */       template = new TemplateEditBean();
/* 345 */       template.setT_content(tContent);
/* 346 */       template.setT_id(Integer.parseInt(t_id));
/* 347 */       template.setSite_id(site_id);
/* 348 */       template.setT_cname(t_cname);
/* 349 */       template.setModify_dtime(modify_dtime);
/* 350 */       template.setT_ename(t_ename);
/* 351 */       template.setCreat_dtime(creat_dtime);
/* 352 */       template.setId(Integer.parseInt(id));
/* 353 */       template.setModify_user(Integer.parseInt(modify_user));
/* 354 */       template.setTcat_id(Integer.parseInt(tcat_id));
/* 355 */       template.setT_path(t_path);
/* 356 */       template.setApp_id(app_id);
/* 357 */       template.setT_ver(Integer.parseInt(t_ver));
/* 358 */       template.setCreat_user(Integer.parseInt(creat_user));
/* 359 */       TemplateManager.delFile(site_id, file_path);
/* 360 */       return TemplateEditManager.updateTemplateEdit(template, stl);
/*     */     }
/* 362 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 382 */     System.out.println(getTemplateEditById(997, "HIWCMdemo", "zwgk"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateRPC
 * JD-Core Version:    0.6.2
 */