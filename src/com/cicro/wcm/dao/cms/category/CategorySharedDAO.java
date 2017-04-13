/*    */ package com.cicro.wcm.dao.cms.category;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.category.CategorySharedBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CategorySharedDAO
/*    */ {
/*    */   public static List<CategorySharedBean> getAllCategorySharedList()
/*    */   {
/* 30 */     return DBManager.queryFList("getAllCategorySharedList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertCategoryShared(CategorySharedBean csb)
/*    */   {
/* 40 */     return DBManager.insert("insert_category_shared", csb);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategoryShared(String site_id, int cat_id, int shared_type)
/*    */   {
/* 53 */     Map m = new HashMap();
/* 54 */     m.put("s_site_id", site_id);
/* 55 */     m.put("cat_id", Integer.valueOf(cat_id));
/* 56 */     m.put("shared_type", Integer.valueOf(shared_type));
/* 57 */     return DBManager.delete("delete_category_shared", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategorySharedByCatID(String cat_ids, String site_id)
/*    */   {
/* 67 */     Map m = new HashMap();
/* 68 */     m.put("cat_ids", cat_ids);
/* 69 */     m.put("s_site_id", site_id);
/* 70 */     return DBManager.delete("delete_category_sharedByCatID", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.CategorySharedDAO
 * JD-Core Version:    0.6.2
 */