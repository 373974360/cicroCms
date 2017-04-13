/*    */ package com.cicro.wcm.services.cms.category;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*    */ import com.cicro.wcm.bean.cms.category.CategoryModel;
/*    */ import com.cicro.wcm.dao.cms.category.CategoryDAO;
/*    */ import com.cicro.wcm.dao.cms.category.CategoryModelDAO;
/*    */ import com.cicro.wcm.services.control.site.ICloneSite;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CategoryCloneImpl
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 15 */       List l = CategoryDAO.getCategoryListBySiteID(s_site_id);
/* 16 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 18 */         for (CategoryBean cgb : l)
/*    */         {
/* 21 */           cgb.setSite_id(site_id);
/* 22 */           cgb.setHj_sql("");
/* 23 */           CategoryDAO.cloneCategory(cgb);
/* 24 */           cloneCategoryModel(CategoryModelManager.getCategoryReleModelList(cgb.getCat_id(), s_site_id), site_id);
/*    */         }
/* 26 */         CategoryManager.reloadCategory();
/* 27 */         CategoryModelManager.reloadCategoryModel();
/*    */       }
/* 29 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 32 */       e.printStackTrace();
/* 33 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean cloneCategoryModel(List<CategoryModel> cmb_list, String site_id)
/*    */   {
/*    */     try
/*    */     {
/* 40 */       if ((cmb_list != null) && (cmb_list.size() > 0))
/*    */       {
/* 42 */         for (CategoryModel cmb : cmb_list)
/*    */         {
/* 44 */           cmb.setSite_id(site_id);
/*    */         }
/* 46 */         CategoryModelDAO.insertCategoryModel(cmb_list);
/*    */       }
/* 48 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 51 */       e.printStackTrace();
/* 52 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryCloneImpl
 * JD-Core Version:    0.6.2
 */