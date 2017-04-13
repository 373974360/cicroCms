/*    */ package com.cicro.wcm.dao.cms.category;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.category.CategoryReleBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CategoryReleDAO
/*    */ {
/*    */   public static List<CategoryReleBean> getCategoryReleUserList()
/*    */   {
/* 30 */     return DBManager.queryFList("getCategoryReleUserList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertCategoryReleUser(CategoryReleBean crb)
/*    */   {
/* 40 */     return DBManager.insert("insert_category_releUser", crb);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategoryReleUserByCatID(String cat_id, String site_id)
/*    */   {
/* 50 */     Map m = new HashMap();
/* 51 */     m.put("cat_id", cat_id);
/* 52 */     m.put("site_id", site_id);
/* 53 */     return DBManager.delete("delete_category_releByCatID", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategoryReleUserByCatID(int priv_type, String prv_id, String site_id)
/*    */   {
/* 65 */     Map m = new HashMap();
/* 66 */     m.put("priv_type", priv_type);
/* 67 */     m.put("prv_id", prv_id);
/* 68 */     m.put("site_id", site_id);
/* 69 */     return DBManager.delete("delete_category_releByCatID", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategoryReleUserBySiteID(String site_id)
/*    */   {
/* 79 */     return DBManager.delete("delete_category_releBySiteID", site_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.CategoryReleDAO
 * JD-Core Version:    0.6.2
 */