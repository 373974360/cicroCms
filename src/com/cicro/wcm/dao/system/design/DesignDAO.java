/*     */ package com.cicro.wcm.dao.system.design;
/*     */ 
/*     */ import com.cicro.wcm.bean.system.design.DesignCSSBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignCaseBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignCategoryBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignFrameBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignLayoutBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignModuleBean;
/*     */ import com.cicro.wcm.bean.system.design.DesignStyleBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DesignDAO
/*     */ {
/*     */   public static String getDesignCssCount()
/*     */   {
/*  32 */     return DBManager.getString("getDesignCssCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignCSSBean> getDesignCssList(Map<String, String> m)
/*     */   {
/*  38 */     return DBManager.queryFList("getDesignCssList", m);
/*     */   }
/*     */ 
/*     */   public static DesignCSSBean getDesignCssBean(int css_id)
/*     */   {
/*  43 */     return (DesignCSSBean)DBManager.queryFObj("getDesignCssBean", css_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCss(DesignCSSBean cssbean)
/*     */   {
/*  48 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_CSS_TABLE_NAME);
/*  49 */     cssbean.setCss_id(id);
/*  50 */     cssbean.setId(id);
/*  51 */     return DBManager.insert("insert_desing_css", cssbean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCss(DesignCSSBean cssbean)
/*     */   {
/*  56 */     return DBManager.update("update_desing_css", cssbean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCss(String css_ids)
/*     */   {
/*  61 */     Map m = new HashMap();
/*  62 */     m.put("css_ids", css_ids);
/*  63 */     return DBManager.delete("delete_desing_css", m);
/*     */   }
/*     */ 
/*     */   public static String getDesignLayoutCount()
/*     */   {
/*  71 */     return DBManager.getString("getDesignLayoutCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignLayoutBean> getDesignLayoutList(Map<String, String> m)
/*     */   {
/*  77 */     return DBManager.queryFList("getDesignLayoutList", m);
/*     */   }
/*     */ 
/*     */   public static DesignLayoutBean getDesignLayoutBean(int layout_id)
/*     */   {
/*  82 */     return (DesignLayoutBean)DBManager.queryFObj("getDesignLayoutBean", layout_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignLayout(DesignLayoutBean layoutbean)
/*     */   {
/*  87 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_LAYOUT_TABLE_NAME);
/*  88 */     layoutbean.setLayout_id(id);
/*  89 */     layoutbean.setId(id);
/*  90 */     return DBManager.insert("insert_desing_layout", layoutbean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignLayout(DesignLayoutBean layoutbean)
/*     */   {
/*  95 */     return DBManager.update("update_desing_layout", layoutbean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignLayout(String layout_ids)
/*     */   {
/* 100 */     Map m = new HashMap();
/* 101 */     m.put("layout_ids", layout_ids);
/* 102 */     return DBManager.delete("delete_desing_layout", m);
/*     */   }
/*     */ 
/*     */   public static String getDesignModuleCount()
/*     */   {
/* 109 */     return DBManager.getString("getDesignModuleCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignModuleBean> getDesignModuleList(Map<String, String> m)
/*     */   {
/* 115 */     return DBManager.queryFList("getDesignModuleList", m);
/*     */   }
/*     */ 
/*     */   public static DesignModuleBean getDesignModuleBean(int module_id)
/*     */   {
/* 120 */     return (DesignModuleBean)DBManager.queryFObj("getDesignModuleBean", module_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignModule(DesignModuleBean modulebean)
/*     */   {
/* 125 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_MODULE_TABLE_NAME);
/* 126 */     modulebean.setModule_id(id);
/* 127 */     modulebean.setId(id);
/* 128 */     return DBManager.insert("insert_desing_module", modulebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignModule(DesignModuleBean modulebean)
/*     */   {
/* 133 */     return DBManager.update("update_desing_module", modulebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignModule(String module_ids)
/*     */   {
/* 138 */     Map m = new HashMap();
/* 139 */     m.put("module_ids", module_ids);
/* 140 */     return DBManager.delete("delete_desing_module", m);
/*     */   }
/*     */ 
/*     */   public static String getDesignStyleCount()
/*     */   {
/* 147 */     return DBManager.getString("getDesignStyleCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignStyleBean> getDesignStyleList(Map<String, String> m)
/*     */   {
/* 153 */     return DBManager.queryFList("getDesignStyleList", m);
/*     */   }
/*     */ 
/*     */   public static DesignStyleBean getDesignStyleBean(int style_id)
/*     */   {
/* 158 */     return (DesignStyleBean)DBManager.queryFObj("getDesignStyleBean", style_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignStyle(DesignStyleBean stylebean)
/*     */   {
/* 163 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_STYLE_TABLE_NAME);
/* 164 */     stylebean.setStyle_id(id);
/* 165 */     stylebean.setId(id);
/* 166 */     return DBManager.insert("insert_desing_style", stylebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignStyle(DesignStyleBean stylebean)
/*     */   {
/* 171 */     return DBManager.update("update_desing_style", stylebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignStyle(String style_ids)
/*     */   {
/* 176 */     Map m = new HashMap();
/* 177 */     m.put("style_ids", style_ids);
/* 178 */     return DBManager.delete("delete_desing_style", m);
/*     */   }
/*     */ 
/*     */   public static String getDesignFrameCount()
/*     */   {
/* 185 */     return DBManager.getString("getDesignFrameCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignFrameBean> getDesignFrameList(Map<String, String> m)
/*     */   {
/* 191 */     return DBManager.queryFList("getDesignFrameList", m);
/*     */   }
/*     */ 
/*     */   public static DesignFrameBean getDesignFrameBean(int frame_id)
/*     */   {
/* 196 */     return (DesignFrameBean)DBManager.queryFObj("getDesignFrameBean", frame_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignFrame(DesignFrameBean framebean)
/*     */   {
/* 201 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_FRAME_TABLE_NAME);
/* 202 */     framebean.setFrame_id(id);
/* 203 */     framebean.setId(id);
/* 204 */     return DBManager.insert("insert_desing_frame", framebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignFrame(DesignFrameBean framebean)
/*     */   {
/* 209 */     return DBManager.update("update_desing_frame", framebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignFrame(String frame_ids)
/*     */   {
/* 214 */     Map m = new HashMap();
/* 215 */     m.put("frame_ids", frame_ids);
/* 216 */     return DBManager.delete("delete_desing_frame", m);
/*     */   }
/*     */ 
/*     */   public static String getDesignCaseCount()
/*     */   {
/* 223 */     return DBManager.getString("getDesignCaseCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignCaseBean> getDesignCaseList(Map<String, String> m)
/*     */   {
/* 229 */     return DBManager.queryFList("getDesignCaseList", m);
/*     */   }
/*     */ 
/*     */   public static DesignCaseBean getDesignCaseBean(int case_id)
/*     */   {
/* 234 */     return (DesignCaseBean)DBManager.queryFObj("getDesignCaseBean", case_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCase(DesignCaseBean casebean)
/*     */   {
/* 239 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_CASE_TABLE_NAME);
/* 240 */     casebean.setCase_id(id);
/* 241 */     casebean.setId(id);
/* 242 */     return DBManager.insert("insert_desing_case", casebean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCase(DesignCaseBean casebean)
/*     */   {
/* 247 */     return DBManager.update("update_desing_case", casebean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCase(String case_ids)
/*     */   {
/* 252 */     Map m = new HashMap();
/* 253 */     m.put("case_ids", case_ids);
/* 254 */     return DBManager.delete("delete_desing_case", m);
/*     */   }
/*     */ 
/*     */   public static String getDesignCategoryCount()
/*     */   {
/* 261 */     return DBManager.getString("getDesignCategoryCount", "");
/*     */   }
/*     */ 
/*     */   public static List<DesignCategoryBean> getDesignCategoryList(Map<String, String> m)
/*     */   {
/* 267 */     return DBManager.queryFList("getDesignCategoryList", m);
/*     */   }
/*     */ 
/*     */   public static DesignCategoryBean getDesignCategoryBean(int category_id, String site_id, String page_type)
/*     */   {
/* 272 */     Map m = new HashMap();
/* 273 */     m.put("category_id", category_id);
/* 274 */     m.put("site_id", site_id);
/* 275 */     m.put("page_type", page_type);
/* 276 */     return (DesignCategoryBean)DBManager.queryFObj("getDesignCategoryBean", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertDesignCategory(DesignCategoryBean categorybean)
/*     */   {
/* 281 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DESIGN_CATEGORY_TABLE_NAME);
/* 282 */     categorybean.setId(id);
/* 283 */     return DBManager.insert("insert_desing_category", categorybean);
/*     */   }
/*     */ 
/*     */   public static boolean updateDesignCategory(DesignCategoryBean categorybean)
/*     */   {
/* 288 */     return DBManager.update("update_desing_category", categorybean);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDesignCategory(String category_ids, String template_ids, String site_id)
/*     */   {
/* 293 */     Map m = new HashMap();
/* 294 */     m.put("site_id", site_id);
/* 295 */     if ((category_ids != null) && (!"".equals(category_ids)))
/* 296 */       m.put("category_ids", category_ids);
/* 297 */     if ((template_ids != null) && (!"".equals(template_ids)))
/* 298 */       m.put("template_ids", template_ids);
/* 299 */     return DBManager.delete("delete_desing_category", m);
/*     */   }
/*     */ 
/*     */   public static boolean nameIsExist(Map<String, String> m)
/*     */   {
/* 310 */     String count = DBManager.getString("getDesignItemValueCount", m);
/* 311 */     if ("0".equals(count))
/*     */     {
/* 313 */       return false;
/*     */     }
/* 315 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 320 */     System.out.println(getDesignCssCount());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.design.DesignDAO
 * JD-Core Version:    0.6.2
 */