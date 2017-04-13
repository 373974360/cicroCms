/*     */ package com.cicro.wcm.services.appeal.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CategoryRPC
/*     */ {
/*     */   public static String getCategoryTreeJsonStr()
/*     */   {
/*  19 */     return CategoryManager.getAppCateTreeJsonStr();
/*     */   }
/*     */ 
/*     */   public static int getCategoryID()
/*     */   {
/*  29 */     return CategoryManager.getAppealCategoryID();
/*     */   }
/*     */ 
/*     */   public static CategoryBean getCategoryBean(int Category_id)
/*     */   {
/*  39 */     return CategoryManager.getApp_categoryBean(Category_id);
/*     */   }
/*     */ 
/*     */   public static String getChildCategoryCount(String Category_id)
/*     */   {
/*  50 */     return CategoryManager.getChildAppCateCount(Category_id);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getChildCategoryList(String Category_id)
/*     */   {
/*  61 */     return CategoryManager.getChildAppCateList(Category_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategory(CategoryBean mb, HttpServletRequest request)
/*     */   {
/*  72 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  73 */     if (stl != null)
/*     */     {
/*  75 */       return CategoryManager.insertAppCate(mb, stl);
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategory(CategoryBean mb, HttpServletRequest request)
/*     */   {
/*  88 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  89 */     if (stl != null)
/*     */     {
/*  91 */       return CategoryManager.updateAppCate(mb, stl);
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveCategorySort(String Category_id, HttpServletRequest request)
/*     */   {
/* 104 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 105 */     if (stl != null)
/*     */     {
/* 107 */       return CategoryManager.saveAppCateSort(Category_id, stl);
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategory(String Category_id, HttpServletRequest request)
/*     */   {
/* 120 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 121 */     if (stl != null)
/*     */     {
/* 123 */       return CategoryManager.deleteAppCate(Category_id, stl);
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveCategory(String parent_id, String Category_ids, HttpServletRequest request)
/*     */   {
/* 135 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 136 */     if (stl != null)
/*     */     {
/* 138 */       return CategoryManager.moveAppCate(parent_id, Category_ids, stl);
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 145 */     System.out.println(getCategoryTreeJsonStr());
/* 146 */     System.out.println(getCategoryBean(1));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.category.CategoryRPC
 * JD-Core Version:    0.6.2
 */