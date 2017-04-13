/*     */ package com.cicro.wcm.dao.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CategoryDAO
/*     */ {
/*     */   public static List<CategoryBean> getCategoryListBySiteID(String site_id)
/*     */   {
/*  33 */     return DBManager.queryFList("getCategoryListBySiteID", site_id);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getAllCategoryList()
/*     */   {
/*  44 */     return DBManager.queryFList("getAllCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static CategoryBean getCategoryBean(String cat_id)
/*     */   {
/*  54 */     return (CategoryBean)DBManager.queryFObj("getCategoryBean", cat_id);
/*     */   }
/*     */ 
/*     */   public static String getCategoryCountBySiteAndType(Map<String, String> m)
/*     */   {
/*  64 */     return DBManager.getString("getCategoryCountBySiteAndType", m);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getCategoryListBySiteAndType(Map<String, String> m)
/*     */   {
/*  75 */     return DBManager.queryFList("getCategoryListBySiteAndType", m);
/*     */   }
/*     */ 
/*     */   public static boolean cloneCategory(CategoryBean cgb)
/*     */   {
/*  86 */     cgb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_TABLE_NAME));
/*  87 */     return DBManager.insert("insert_info_category", cgb);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategory(CategoryBean cgb, SettingLogsBean stl)
/*     */   {
/*  98 */     if (DBManager.insert("insert_info_category", cgb))
/*     */     {
/* 100 */       PublicTableDAO.insertSettingLogs("添加", "目录", cgb.getCat_id(), stl);
/* 101 */       return true;
/*     */     }
/*     */ 
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategory(CategoryBean cgb, SettingLogsBean stl)
/*     */   {
/* 115 */     if (DBManager.update("update_info_category", cgb))
/*     */     {
/* 117 */       PublicTableDAO.insertSettingLogs("修改", "目录", cgb.getCat_id(), stl);
/* 118 */       return true;
/*     */     }
/*     */ 
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean batchUpdateCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 132 */     if (DBManager.update("batch_update_category", m))
/*     */     {
/* 134 */       PublicTableDAO.insertSettingLogs("修改", "目录", (String)m.get("cat_ids"), stl);
/* 135 */       return true;
/*     */     }
/*     */ 
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryTemplate(Map<String, String> m)
/*     */   {
/* 148 */     return DBManager.update("update_info_category_template", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryArchiveStatus(String ids, String is_archive, SettingLogsBean stl)
/*     */   {
/* 160 */     Map m = new HashMap();
/* 161 */     m.put("ids", ids);
/* 162 */     m.put("is_archive", is_archive);
/* 163 */     if (DBManager.update("update_info_category_archive", m))
/*     */     {
/* 165 */       PublicTableDAO.insertSettingLogs("修改", "目录归档状态", ids, stl);
/* 166 */       return true;
/*     */     }
/*     */ 
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryByClass(String cat_ename, String cat_cname, String cat_id)
/*     */   {
/* 182 */     Map m = new HashMap();
/* 183 */     m.put("cat_ename", cat_ename);
/* 184 */     m.put("cat_cname", cat_cname);
/* 185 */     m.put("cat_id", cat_id);
/* 186 */     return DBManager.update("update_info_categoryByClass", m);
/*     */   }
/*     */ 
/*     */   public static boolean sortCategory(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 198 */       Map m = new HashMap();
/* 199 */       String[] tempA = ids.split(",");
/* 200 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 202 */         m.put("cat_sort", Integer.valueOf(i + 1));
/* 203 */         m.put("id", tempA[i]);
/* 204 */         DBManager.update("sort_info_category", m);
/*     */       }
/* 206 */       PublicTableDAO.insertSettingLogs("保存排序", "目录", ids, stl);
/* 207 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 211 */       e.printStackTrace();
/* 212 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean clearCategoryTemplate(String template_ids, String site_id)
/*     */   {
/* 224 */     Map m = new HashMap();
/* 225 */     m.put("template_ids", template_ids);
/* 226 */     m.put("site_id", site_id);
/* 227 */     return DBManager.update("clear_info_category_template", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategory(String cat_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 238 */     Map m = new HashMap();
/* 239 */     m.put("cat_ids", cat_ids);
/* 240 */     if ((site_id != null) && (!"".equals(site_id)))
/* 241 */       m.put("site_id", site_id);
/* 242 */     if (DBManager.delete("delete_info_category", m))
/*     */     {
/* 244 */       PublicTableDAO.insertSettingLogs("删除", "目录", cat_ids, stl);
/* 245 */       return true;
/*     */     }
/*     */ 
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategoryBySiteiD(String site_id)
/*     */   {
/* 258 */     Map m = new HashMap();
/* 259 */     m.put("site_id", site_id);
/*     */     try
/*     */     {
/* 262 */       DBManager.delete("deleteSync_for_site_id", m);
/*     */ 
/* 264 */       DBManager.delete("delete_category_sharedBySiteID", m);
/*     */ 
/* 266 */       DBManager.delete("delete_category_model_bySiteID", m);
/*     */ 
/* 268 */       CategoryReleDAO.deleteCategoryReleUserBySiteID(site_id);
/* 269 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 272 */       e.printStackTrace();
/* 273 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 286 */     if (DBManager.update("move_category", m))
/*     */     {
/* 288 */       PublicTableDAO.insertSettingLogs("移动", "目录", (String)m.get("cat_id"), stl);
/* 289 */       return true;
/*     */     }
/*     */ 
/* 292 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean releCategoryClass(String class_id, String cat_id, SettingLogsBean stl)
/*     */   {
/* 304 */     Map m = new HashMap();
/* 305 */     m.put("cat_class_id", class_id);
/* 306 */     m.put("cat_id", cat_id);
/* 307 */     if (DBManager.update("rele_category_class", m))
/*     */     {
/* 309 */       PublicTableDAO.insertSettingLogs("修改", "目录与分类方式关联", cat_id, stl);
/* 310 */       return true;
/*     */     }
/*     */ 
/* 313 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategoryByClassID(String class_ids)
/*     */   {
/* 323 */     Map m = new HashMap();
/* 324 */     m.put("class_id", class_ids);
/* 325 */     return DBManager.delete("delete_info_categoryByClassID", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertGKDefaultCategory(CategoryBean cgb)
/*     */   {
/* 335 */     return DBManager.insert("insert_gk_default_cate", cgb);
/*     */   }
/*     */ 
/*     */   public static boolean updateBaseCategoryTemplate(String template_id)
/*     */   {
/* 345 */     Map m = new HashMap();
/* 346 */     m.put("template_id", template_id);
/* 347 */     return DBManager.update("update_baseCategory_template", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateCateTemplateListByID(String template_id, String id)
/*     */   {
/* 358 */     Map m = new HashMap();
/* 359 */     m.put("template_id", template_id);
/* 360 */     m.put("id", id);
/* 361 */     return DBManager.update("update_baseCategory_template", m);
/*     */   }
/*     */ 
/*     */   public static String getBaseCategoryTemplateListID()
/*     */   {
/* 371 */     return DBManager.getString("getBaseCategoryTemplateListID", "");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.CategoryDAO
 * JD-Core Version:    0.6.2
 */