/*    */ package com.cicro.wcm.dao.cms.category;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.category.CategoryModel;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CategoryModelDAO
/*    */ {
/*    */   public static List<CategoryModel> getCategoryReleModelList()
/*    */   {
/* 31 */     return DBManager.queryFList("getCategoryReleModelList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertCategoryModel(List<CategoryModel> l)
/*    */   {
/*    */     try
/*    */     {
/* 44 */       for (int i = 0; i < l.size(); i++)
/*    */       {
/* 46 */         ((CategoryModel)l.get(i)).setCat_model_id(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_MODEL_TABLE_NAME));
/* 47 */         DBManager.insert("insert_category_model", l.get(i));
/*    */       }
/* 49 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 52 */       e.printStackTrace();
/* 53 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateCategoryModel(List<CategoryModel> l, String cat_id, String site_id)
/*    */   {
/* 67 */     if (deleteCategoryModel(cat_id, site_id))
/*    */     {
/* 69 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 71 */         for (int i = 0; i < l.size(); i++)
/*    */         {
/* 73 */           ((CategoryModel)l.get(i)).setCat_model_id(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_MODEL_TABLE_NAME));
/* 74 */           DBManager.insert("insert_category_model", l.get(i));
/*    */         }
/*    */       }
/* 77 */       return true;
/*    */     }
/*    */ 
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteCategoryModel(String cat_id, String site_id)
/*    */   {
/* 91 */     Map m = new HashMap();
/* 92 */     m.put("cat_id", cat_id);
/* 93 */     if ((site_id != null) && (!"".equals(site_id)))
/* 94 */       m.put("site_id", site_id);
/* 95 */     return DBManager.delete("delete_category_model", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.CategoryModelDAO
 * JD-Core Version:    0.6.2
 */