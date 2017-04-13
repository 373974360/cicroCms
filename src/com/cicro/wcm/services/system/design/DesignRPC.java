/*     */ package com.cicro.wcm.services.system.design;
/*     */ 
/*     */ import com.cicro.wcm.bean.system.design.DesignCSSBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignCaseBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignCategoryBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignFrameBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignLayoutBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignModuleBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignStyleBean;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DesignRPC
/*     */ {
/*     */   public static String getDesignCssCount()
/*     */   {
/*  18 */     return DesignManager.getDesignCssCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignCSSBean> getDesignCssList(Map<String, String> m)
/*     */   {
/*  23 */     return DesignManager.getDesignCssList(m);
/*     */   }
/*     */ 
/*     */   public static DesignCSSBean getDesignCssBean(int css_id)
/*     */   {
/*  28 */     return DesignManager.getDesignCssBean(css_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCss(DesignCSSBean cssbean)
/*     */   {
/*  33 */     return DesignManager.insertDesignCss(cssbean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCss(DesignCSSBean cssbean)
/*     */   {
/*  38 */     return DesignManager.updateDesignCss(cssbean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCss(String css_ids)
/*     */   {
/*  43 */     return DesignManager.deleteDesignCss(css_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignLayoutCount()
/*     */   {
/*  51 */     return DesignManager.getDesignLayoutCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignLayoutBean> getDesignLayoutList(Map<String, String> m)
/*     */   {
/*  57 */     return DesignManager.getDesignLayoutList(m);
/*     */   }
/*     */ 
/*     */   public static DesignLayoutBean getDesignLayoutBean(int layout_id)
/*     */   {
/*  62 */     return DesignManager.getDesignLayoutBean(layout_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignLayout(DesignLayoutBean layoutbean)
/*     */   {
/*  67 */     return DesignManager.insertDesignLayout(layoutbean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignLayout(DesignLayoutBean layoutbean)
/*     */   {
/*  72 */     return DesignManager.updateDesignLayout(layoutbean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignLayout(String layout_ids)
/*     */   {
/*  77 */     return DesignManager.deleteDesignLayout(layout_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignModuleCount()
/*     */   {
/*  84 */     return DesignManager.getDesignModuleCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignModuleBean> getDesignModuleList(Map<String, String> m)
/*     */   {
/*  90 */     return DesignManager.getDesignModuleList(m);
/*     */   }
/*     */ 
/*     */   public static DesignModuleBean getDesignModuleBean(int module_id)
/*     */   {
/*  95 */     return DesignManager.getDesignModuleBean(module_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignModule(DesignModuleBean modulebean)
/*     */   {
/* 100 */     return DesignManager.insertDesignModule(modulebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignModule(DesignModuleBean modulebean)
/*     */   {
/* 105 */     return DesignManager.updateDesignModule(modulebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignModule(String module_ids)
/*     */   {
/* 110 */     return DesignManager.deleteDesignModule(module_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignStyleCount()
/*     */   {
/* 117 */     return DesignManager.getDesignStyleCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignStyleBean> getDesignStyleList(Map<String, String> m)
/*     */   {
/* 123 */     return DesignManager.getDesignStyleList(m);
/*     */   }
/*     */ 
/*     */   public static DesignStyleBean getDesignStyleBean(int style_id)
/*     */   {
/* 128 */     return DesignManager.getDesignStyleBean(style_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignStyle(DesignStyleBean stylebean)
/*     */   {
/* 133 */     return DesignManager.insertDesignStyle(stylebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignStyle(DesignStyleBean stylebean)
/*     */   {
/* 138 */     return DesignManager.updateDesignStyle(stylebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignStyle(String style_ids)
/*     */   {
/* 143 */     return DesignManager.deleteDesignStyle(style_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignFrameCount()
/*     */   {
/* 150 */     return DesignManager.getDesignFrameCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignFrameBean> getDesignFrameList(Map<String, String> m)
/*     */   {
/* 156 */     return DesignManager.getDesignFrameList(m);
/*     */   }
/*     */ 
/*     */   public static DesignFrameBean getDesignFrameBean(int frame_id)
/*     */   {
/* 161 */     return DesignManager.getDesignFrameBean(frame_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignFrame(DesignFrameBean framebean)
/*     */   {
/* 166 */     return DesignManager.insertDesignFrame(framebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignFrame(DesignFrameBean framebean)
/*     */   {
/* 171 */     return DesignManager.updateDesignFrame(framebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignFrame(String frame_ids)
/*     */   {
/* 176 */     return DesignManager.deleteDesignFrame(frame_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignCaseCount()
/*     */   {
/* 183 */     return DesignManager.getDesignCaseCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignCaseBean> getDesignCaseList(Map<String, String> m)
/*     */   {
/* 189 */     return DesignManager.getDesignCaseList(m);
/*     */   }
/*     */ 
/*     */   public static DesignCaseBean getDesignCaseBean(int case_id)
/*     */   {
/* 194 */     return DesignManager.getDesignCaseBean(case_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCase(DesignCaseBean casebean)
/*     */   {
/* 199 */     return DesignManager.insertDesignCase(casebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCase(DesignCaseBean casebean)
/*     */   {
/* 204 */     return DesignManager.updateDesignCase(casebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCase(String case_ids)
/*     */   {
/* 209 */     return DesignManager.deleteDesignCase(case_ids);
/*     */   }
/*     */ 
/*     */   public static String getDesignCategoryCount()
/*     */   {
/* 216 */     return DesignManager.getDesignCategoryCount();
/*     */   }
/*     */ 
/*     */   public static List<DesignCategoryBean> getDesignCategoryList(Map<String, String> m)
/*     */   {
/* 222 */     return DesignManager.getDesignCategoryList(m);
/*     */   }
/*     */ 
/*     */   public static DesignCategoryBean getDesignCategoryBean(int category_id, String site_id, String page_type)
/*     */   {
/* 227 */     return DesignManager.getDesignCategoryBean(category_id, site_id, page_type);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCategory(DesignCategoryBean categorybean)
/*     */   {
/* 232 */     return DesignManager.insertDesignCategory(categorybean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCategory(DesignCategoryBean categorybean)
/*     */   {
/* 237 */     return DesignManager.updateDesignCategory(categorybean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCategory(String category_ids, String template_ids, String site_id)
/*     */   {
/* 242 */     return DesignManager.deleteDesignCategory(category_ids, template_ids, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean nameIsExist(String table_name, String item_name, String item_value)
/*     */   {
/* 253 */     return DesignManager.nameIsExist(table_name, item_name, item_value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.design.DesignRPC
 * JD-Core Version:    0.6.2
 */