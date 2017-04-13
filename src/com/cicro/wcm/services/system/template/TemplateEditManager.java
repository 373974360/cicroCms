/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateVerBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.design.DesignDAO;
/*     */ import com.cicro.wcm.dao.system.template.TemplateEditDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.template.TemplateBase;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateEditManager
/*     */   implements ISyncCatch
/*     */ {
/*  27 */   private static Map<String, String> temp_name_map = new HashMap();
/*     */ 
/*     */   static {
/*  30 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  35 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  40 */     temp_name_map.clear();
/*  41 */     List<TemplateEditBean> l = TemplateEditDAO.getAllTemplateEditList();
/*     */     try {
/*  43 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  45 */         for (TemplateEditBean tm : l)
/*     */         {
/*  47 */           temp_name_map.put(tm.getT_id() + "_" + tm.getSite_id(), tm.getT_cname());
/*     */         }
/*     */       }
/*     */     } catch (Exception e) { e.printStackTrace(); }
/*     */   }
/*     */ 
/*     */   public static void reloadTemplateEdit()
/*     */   {
/*  55 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.template.TemplateEditManager");
/*     */   }
/*     */ 
/*     */   public static int getNewTemplate()
/*     */   {
/*  60 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_EDIT_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static TemplateEditBean getTemplateEditBean(int t_id, String site_id, String app_id)
/*     */   {
/*  70 */     return TemplateEditDAO.getTemplateEditBean(t_id, site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static String getTemplateNameById(int template_id, String site_id, String app_id) {
/*  74 */     String k = template_id + "_" + site_id;
/*  75 */     if (temp_name_map.containsKey(k))
/*     */     {
/*  77 */       return (String)temp_name_map.get(k);
/*     */     }
/*     */ 
/*  80 */     TemplateEditBean t = getTemplateEditBean(template_id, site_id, app_id);
/*  81 */     if (t != null)
/*     */     {
/*  83 */       return t.getT_cname();
/*     */     }
/*  85 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getTemplateEditCount(Map<String, String> con_map)
/*     */   {
/*  96 */     return TemplateEditDAO.getTemplateEditCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateEdit(TemplateEditBean template, SettingLogsBean stl)
/*     */   {
/* 105 */     if (template == null) {
/* 106 */       return false;
/*     */     }
/* 108 */     template.setT_ver(1 + TemplateVerManager.getTemplateVerNum(template.getT_id(), template.getSite_id()));
/* 109 */     template.setModify_dtime(DateUtil.getCurrentDateTime());
/*     */ 
/* 111 */     if (TemplateEditDAO.updateTemplateEdit(template, stl)) {
/* 112 */       TemplateVerManager.addTemplateVer(TEBtoTVB(template), stl);
/*     */ 
/* 114 */       TemplateUtils.setTemplatePath(template.getT_id() + "_" + template.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(SiteManager.getSiteTempletPath(template.getSite_id()))).append("/").append(TemplateCategoryManager.getTemplateCategoryBean(template.getTcat_id(), template.getSite_id(), template.getApp_id()).getTcat_position()).toString(), true) + template.getT_id() + "_" + template.getT_ename() + ".vm");
/*     */     }
/* 116 */     reloadTemplateEdit();
/* 117 */     return true;
/*     */   }

			public static boolean updateTemplateEditStatus(TemplateEditBean template, SettingLogsBean stl)
/*     */   {
/* 105 */     if (template == null) {
/* 106 */       return false;
/*     */     }
/* 108 */     template.setT_status(1);
/*     */ 
/* 111 */     TemplateEditDAO.updateTemplateEdit(template, stl);
/* 116 */     reloadTemplateEdit();
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateEdit(TemplateEditBean template, SettingLogsBean stl)
/*     */   {
/* 126 */     if (template == null) {
/* 127 */       return false;
/*     */     }
/* 129 */     template.setT_ver(1 + TemplateVerManager.getTemplateVerNum(template.getT_id(), template.getSite_id()));
/* 130 */     template.setCreat_dtime(DateUtil.getCurrentDateTime());
/*     */ 
/* 132 */     int tbId = PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_EDIT_TABLE_NAME);
/* 133 */     template.setT_id(tbId);
/* 134 */     template.setId(tbId);
/* 135 */     if (TemplateEditDAO.addTemplateEdit(template, stl)) {
/* 136 */       TemplateVerManager.addTemplateVer(TEBtoTVB(template), stl);
/*     */ 
/* 138 */       TemplateUtils.setTemplatePath(template.getT_id() + "_" + template.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(SiteManager.getSiteTempletPath(template.getSite_id()))).append("/").append(TemplateCategoryManager.getTemplateCategoryBean(template.getTcat_id(), template.getSite_id(), template.getApp_id()).getTcat_position()).toString(), true) + template.getT_id() + "_" + template.getT_ename() + ".vm");
/*     */     }
/* 140 */     reloadTemplateEdit();
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateEditForDesign(TemplateEditBean template, boolean is_update, SettingLogsBean stl)
/*     */   {
/* 151 */     template.setT_ver(1 + TemplateVerManager.getTemplateVerNum(template.getT_id(), template.getSite_id()));
/* 152 */     template.setModify_dtime(DateUtil.getCurrentDateTime());
/* 153 */     if (is_update)
/*     */     {
/* 155 */       if (TemplateEditDAO.updateTemplateEdit(template, stl)) {
/* 156 */         TemplateVerManager.addTemplateVer(TEBtoTVB(template), stl);
/*     */ 
/* 158 */         TemplateUtils.setTemplatePath(template.getT_id() + "_" + template.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(SiteManager.getSiteTempletPath(template.getSite_id()))).append("/").append(TemplateCategoryManager.getTemplateCategoryBean(template.getTcat_id(), template.getSite_id(), template.getApp_id()).getTcat_position()).toString(), true) + template.getT_id() + "_" + template.getT_ename() + ".vm");
/* 159 */         publishTemplate(template.getT_id()+"", template.getSite_id(), template.getApp_id(), stl);
/* 160 */         return true;
/*     */       }
/* 162 */       return false;
/*     */     }
/*     */ 
/* 165 */     if (TemplateEditDAO.addTemplateEdit(template, stl)) {
/* 166 */       TemplateVerManager.addTemplateVer(TEBtoTVB(template), stl);
/*     */ 
/* 168 */       TemplateUtils.setTemplatePath(template.getT_id() + "_" + template.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(SiteManager.getSiteTempletPath(template.getSite_id()))).append("/").append(TemplateCategoryManager.getTemplateCategoryBean(template.getTcat_id(), template.getSite_id(), template.getApp_id()).getTcat_position()).toString(), true) + template.getT_id() + "_" + template.getT_ename() + ".vm");
/* 169 */       publishTemplate(template.getT_id()+"", template.getSite_id(), template.getApp_id(), stl);
/* 170 */       return true;
/*     */     }
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getAllTemplateEditList()
/*     */   {
/* 182 */     return TemplateEditDAO.getAllTemplateEditList(null, null, null);
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getAllTemplateEditList(String tcat_id, String site_id, String app_id)
/*     */   {
/* 191 */     return TemplateEditDAO.getAllTemplateEditList(tcat_id, site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getTemplateEditListForDB(Map<String, String> con_map)
/*     */   {
/* 201 */     return TemplateEditDAO.getTemplateEditListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateEditById(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 210 */     if ((t_ids == null) || (t_ids.equals(""))) {
/* 211 */       return false;
/*     */     }
/* 213 */     if (TemplateEditDAO.delTemplateEdit(t_ids, site_id, stl)) {
/* 214 */       TemplateVerManager.delTemplateVerByIds(t_ids, site_id, stl);
/*     */ 
/* 216 */       if (t_ids.indexOf(",") != -1) {
/* 217 */         for (String id : t_ids.split(",")) {
/* 218 */           TemplateBase.delTemplateFile(id, site_id, "");
/* 219 */           TemplateUtils.delTemplatePath(id + "_" + site_id);
/*     */         }
/*     */       } else {
/* 222 */         TemplateBase.delTemplateFile(t_ids, site_id, "");
/* 223 */         TemplateUtils.delTemplatePath(t_ids + "_" + site_id);
/*     */       }
/*     */ 
/* 226 */       DesignDAO.deleteDesignCategory("", t_ids, site_id);
/*     */ 
/* 228 */       CategoryManager.clearCategoryTemplate(t_ids, site_id);
/*     */     } else {
/* 230 */       return false;
/*     */     }
/* 232 */     reloadTemplateEdit();
/* 233 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean publishTemplate(String t_ids, String site_id, String app_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 243 */       if (t_ids == null) {
/* 244 */         return false;
/*     */       }
/* 246 */       t_ids = TemplateUtils.formatSymbolString(t_ids, ",");
/*     */ 
/* 248 */       for (String id : t_ids.split(",")) {
/* 249 */         int t_id = Integer.parseInt(id);
/*     */ 
/* 251 */         TemplateEditBean teb = getTemplateEditBean(t_id, site_id, app_id);
				  updateTemplateEditStatus(teb, stl);
/* 252 */         TemplateVerBean tvb = TEBtoTVB(teb);
/* 253 */         tvb.setT_status(1);
/* 254 */         TemplateVerManager.updateTemplateVer(tvb, stl);
/*     */ 
/* 256 */         TemplateManager.delTemplateById(t_id+"", site_id, stl);
/*     */ 
/* 258 */         TemplateBean tb = new TemplateBean();
/* 259 */         tb.setT_id(t_id);
/* 260 */         tb.setT_ver(tvb.getT_ver());
/* 261 */         tb.setSite_id(site_id);
/* 262 */         tb.setApp_id(app_id);
/* 263 */         TemplateManager.addTemplate(tb, stl);
/*     */ 
/* 265 */         TemplateUtils.initTemplatePathMap();
/* 266 */         TemplateBase.saveTemplateFile(teb);
/*     */       }
/*     */     } catch (Exception e) {
/* 269 */       e.printStackTrace();
/* 270 */       return false;
/*     */     }
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean cancelTemplate(String t_ids, String site_id, String app_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 282 */       if (TemplateManager.delTemplateById(t_ids, site_id, stl))
/* 283 */         for (String tid : t_ids.split(",")) {
/* 284 */           if ((tid != null) && (tid.length() > 0)) {
/* 285 */             TemplateEditBean teb = getTemplateEditBean(Integer.parseInt(tid), site_id, app_id);
/* 286 */             TemplateVerBean tvb = TEBtoTVB(teb);
/* 287 */             tvb.setT_status(0);
/* 288 */             TemplateVerManager.updateTemplateVer(tvb, stl);
/*     */           }
/* 290 */           TemplateBase.delTemplateFile(tid, site_id, app_id);
/*     */         }
/*     */     }
/*     */     catch (Exception e) {
/* 294 */       e.printStackTrace();
/* 295 */       return false;
/*     */     }
/* 297 */     return true;
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean TEBtoTVB(TemplateEditBean teb)
/*     */   {
/* 306 */     if (teb == null) return null;
/* 307 */     TemplateVerBean tvb = new TemplateVerBean();
/* 308 */     tvb.setT_id(teb.getT_id());
/* 309 */     tvb.setTcat_id(teb.getTcat_id());
/* 310 */     tvb.setT_ename(teb.getT_ename());
/* 311 */     tvb.setT_cname(teb.getT_cname());
/* 312 */     tvb.setT_path(teb.getT_path());
/* 313 */     tvb.setT_content(teb.getT_content());
/* 314 */     tvb.setT_ver(teb.getT_ver());
/* 315 */     tvb.setCreat_user(teb.getCreat_user());
/* 316 */     tvb.setCreat_dtime(teb.getCreat_dtime());
/* 317 */     tvb.setModify_user(teb.getModify_user());
/* 318 */     tvb.setModify_dtime(teb.getModify_dtime());
/* 319 */     tvb.setApp_id(teb.getApp_id());
/* 320 */     tvb.setSite_id(teb.getSite_id());
/* 321 */     tvb.setT_status(0);
/* 322 */     return tvb;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 326 */     publishTemplate("182", "11111ddd", "0", new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateEditManager
 * JD-Core Version:    0.6.2
 */