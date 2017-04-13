/*     */ package com.cicro.wcm.services.system.design;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.system.design.DesignCSSBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignCaseBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignCategoryBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignFrameBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignLayoutBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignModuleBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignStyleBean;
/*     */ import com.cicro.wcm.dao.system.design.DesignDAO;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DesignManager
/*     */ {
/*     */   public static String getDesignCssCount()
/*     */   {
/*  23 */     return DesignDAO.getDesignCssCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignCSSBean> getDesignCssList(Map<String, String> m)
/*     */   {
/*  28 */     return DesignDAO.getDesignCssList(m);
/*     */   }
/*     */ 
/*     */   public static DesignCSSBean getDesignCssBean(int css_id)
/*     */   {
/*  33 */     return DesignDAO.getDesignCssBean(css_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCss(DesignCSSBean cssbean)
/*     */   {
/*  38 */     if (DesignDAO.insertDesignCss(cssbean))
/*     */     {
/*  40 */       String filepath = FormatUtil.formatPath(getCssSavePath() + cssbean.getCss_ename());
/*  41 */       File f = new File(filepath);
/*  42 */       f.mkdirs();
/*  43 */       return true;
/*     */     }
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCss(DesignCSSBean cssbean)
/*     */   {
/*  50 */     return DesignDAO.updateDesignCss(cssbean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCss(String css_ids)
/*     */   {
/*  55 */     return DesignDAO.deleteDesignCss(css_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignLayoutCount()
/*     */   {
/*  63 */     return DesignDAO.getDesignLayoutCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignLayoutBean> getDesignLayoutList(Map<String, String> m)
/*     */   {
/*  69 */     return DesignDAO.getDesignLayoutList(m);
/*     */   }
/*     */ 
/*     */   public static DesignLayoutBean getDesignLayoutBean(int layout_id)
/*     */   {
/*  74 */     return DesignDAO.getDesignLayoutBean(layout_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignLayout(DesignLayoutBean layoutbean)
/*     */   {
/*  79 */     return DesignDAO.insertDesignLayout(layoutbean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignLayout(DesignLayoutBean layoutbean)
/*     */   {
/*  84 */     return DesignDAO.updateDesignLayout(layoutbean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignLayout(String layout_ids)
/*     */   {
/*  89 */     return DesignDAO.deleteDesignLayout(layout_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignModuleCount()
/*     */   {
/*  96 */     return DesignDAO.getDesignModuleCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignModuleBean> getDesignModuleList(Map<String, String> m)
/*     */   {
/* 102 */     return DesignDAO.getDesignModuleList(m);
/*     */   }
/*     */ 
/*     */   public static DesignModuleBean getDesignModuleBean(int module_id)
/*     */   {
/* 107 */     return DesignDAO.getDesignModuleBean(module_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignModule(DesignModuleBean modulebean)
/*     */   {
/* 112 */     return DesignDAO.insertDesignModule(modulebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignModule(DesignModuleBean modulebean)
/*     */   {
/* 117 */     return DesignDAO.updateDesignModule(modulebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignModule(String module_ids)
/*     */   {
/* 122 */     return DesignDAO.deleteDesignModule(module_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignStyleCount()
/*     */   {
/* 129 */     return DesignDAO.getDesignStyleCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignStyleBean> getDesignStyleList(Map<String, String> m)
/*     */   {
/* 135 */     return DesignDAO.getDesignStyleList(m);
/*     */   }
/*     */ 
/*     */   public static DesignStyleBean getDesignStyleBean(int style_id)
/*     */   {
/* 140 */     return DesignDAO.getDesignStyleBean(style_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignStyle(DesignStyleBean stylebean)
/*     */   {
/* 145 */     return DesignDAO.insertDesignStyle(stylebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignStyle(DesignStyleBean stylebean)
/*     */   {
/* 150 */     return DesignDAO.updateDesignStyle(stylebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignStyle(String style_ids)
/*     */   {
/* 155 */     return DesignDAO.deleteDesignStyle(style_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignFrameCount()
/*     */   {
/* 162 */     return DesignDAO.getDesignFrameCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignFrameBean> getDesignFrameList(Map<String, String> m)
/*     */   {
/* 168 */     return DesignDAO.getDesignFrameList(m);
/*     */   }
/*     */ 
/*     */   public static DesignFrameBean getDesignFrameBean(int frame_id)
/*     */   {
/* 173 */     return DesignDAO.getDesignFrameBean(frame_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignFrame(DesignFrameBean framebean)
/*     */   {
/* 178 */     return DesignDAO.insertDesignFrame(framebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignFrame(DesignFrameBean framebean)
/*     */   {
/* 183 */     return DesignDAO.updateDesignFrame(framebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignFrame(String frame_ids)
/*     */   {
/* 188 */     return DesignDAO.deleteDesignFrame(frame_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignCaseCount()
/*     */   {
/* 195 */     return DesignDAO.getDesignCaseCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignCaseBean> getDesignCaseList(Map<String, String> m)
/*     */   {
/* 201 */     return DesignDAO.getDesignCaseList(m);
/*     */   }
/*     */ 
/*     */   public static DesignCaseBean getDesignCaseBean(int case_id)
/*     */   {
/* 206 */     return DesignDAO.getDesignCaseBean(case_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCase(DesignCaseBean casebean)
/*     */   {
/* 211 */     return DesignDAO.insertDesignCase(casebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCase(DesignCaseBean casebean)
/*     */   {
/* 216 */     return DesignDAO.updateDesignCase(casebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCase(String case_ids)
/*     */   {
/* 221 */     return DesignDAO.deleteDesignCase(case_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignCategoryCount()
/*     */   {
/* 228 */     return DesignDAO.getDesignCategoryCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignCategoryBean> getDesignCategoryList(Map<String, String> m)
/*     */   {
/* 234 */     return DesignDAO.getDesignCategoryList(m);
/*     */   }
/*     */ 
/*     */   public static DesignCategoryBean getDesignCategoryBean(int category_id, String site_id, String page_type)
/*     */   {
/* 239 */     return DesignDAO.getDesignCategoryBean(category_id, site_id, page_type);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCategory(DesignCategoryBean categorybean)
/*     */   {
/* 244 */     return DesignDAO.insertDesignCategory(categorybean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCategory(DesignCategoryBean categorybean)
/*     */   {
/* 249 */     return DesignDAO.updateDesignCategory(categorybean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCategory(String category_ids, String template_ids, String site_id)
/*     */   {
/* 254 */     return DesignDAO.deleteDesignCategory(category_ids, template_ids, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean nameIsExist(String table_name, String item_name, String item_value)
/*     */   {
/* 265 */     Map m = new HashMap();
/* 266 */     m.put("table_name", table_name);
/* 267 */     m.put("item_name", item_name);
/* 268 */     m.put("item_value", item_value);
/* 269 */     return DesignDAO.nameIsExist(m);
/*     */   }
/*     */ 
/*     */   public static String getCssSavePath()
/*     */   {
/* 279 */     return JconfigUtilContainer.bashConfig().getProperty("path", "", "wcm_files") + "/design/theme/";
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 284 */     System.out.println(nameIsExist("cs_design_css", "css_ename", "bbbb"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.design.DesignManager
 * JD-Core Version:    0.6.2
 */