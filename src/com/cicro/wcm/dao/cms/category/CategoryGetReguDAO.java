/*    */ package com.cicro.wcm.dao.cms.category;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.category.CategoryGetRegu;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CategoryGetReguDAO
/*    */ {
/*    */   public static List<CategoryGetRegu> getCategoryReguList(Map<String, String> m)
/*    */   {
/* 29 */     return DBManager.queryFList("getCategoryReguList", m);
/*    */   }
/*    */ 
/*    */   public static boolean insertCategoryRegu(CategoryGetRegu cgr)
/*    */   {
/* 39 */     return DBManager.insert("insert_info_category_regu", cgr);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategoryRegu(String cat_id, String site_id)
/*    */   {
/* 49 */     Map m = new HashMap();
/* 50 */     m.put("cat_ids", cat_id);
/* 51 */     m.put("site_id", site_id);
/* 52 */     return DBManager.insert("delete_info_category_regu", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.CategoryGetReguDAO
 * JD-Core Version:    0.6.2
 */