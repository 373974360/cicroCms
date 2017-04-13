/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CateCurPositionBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CategoryUtil
/*     */ {
/*     */   private static final String ZWGK_FOLDER = "/gk";
/*     */ 
/*     */   public static String[] getFoldeArrByCatIDS(String old_cat_ids, String site_id, String app_id)
/*     */   {
/*  25 */     String[] tempA = old_cat_ids.split(",");
/*  26 */     String[] arr = new String[tempA.length];
/*  27 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  29 */       arr[i] = getFoldePathByCategoryID(Integer.parseInt(tempA[i]), app_id, site_id);
/*     */     }
/*     */ 
/*  32 */     return arr;
/*     */   }
/*     */ 
/*     */   public static String getFoldePathByCategoryID(int cat_id, String app_id, String site_id)
/*     */   {
/*  43 */     String path = "/";
/*  44 */     if ("zwgk".equals(app_id))
/*  45 */       path = "/gk" + path;
/*  46 */     CategoryBean cb = CategoryManager.getCategoryBeanCatID(cat_id, site_id);
/*     */ 
/*  48 */     if (cb != null)
/*     */     {
/*  50 */       String position = cb.getCat_position();
/*     */ 
/*  52 */       String[] tempA = position.split("\\$");
/*  53 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  55 */         if ((tempA[i] != null) && (!"".equals(tempA[i])) && (!"0".equals(tempA[i])))
/*     */         {
/*  57 */           CategoryBean temp_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
/*  58 */           if (temp_cb != null)
/*     */           {
/*  60 */             path = path + temp_cb.getCat_ename() + "/";
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  65 */     return path;
/*     */   }
/*     */ 
/*     */   public static List<CateCurPositionBean> getCategoryPosition(String cat_id, String site_id, String page_type)
/*     */   {
/*  76 */     List ccpb_list = new ArrayList();
/*     */ 
/*  78 */     CateCurPositionBean first_b = new CateCurPositionBean();
/*  79 */     first_b.setCat_cname("首页");
/*  80 */     first_b.setCat_id(0);
/*  81 */     first_b.setUrl("/");
/*  82 */     ccpb_list.add(first_b);
/*  83 */     if ((cat_id != null) && (!"0".equals(cat_id)))
/*     */     {
/*  85 */       CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), site_id);
/*  86 */       if (cb != null)
/*     */       {
/*  88 */         String[] tempA = cb.getCat_position().split("\\$");
/*     */ 
/*  90 */         if ((tempA != null) && (tempA.length > 0))
/*     */         {
/*  92 */           for (int i = 2; i < tempA.length; i++)
/*     */           {
/*  94 */             if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */             {
/*  96 */               CategoryBean cb2 = CategoryManager.getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
/*  97 */               if (cb2 != null)
/*     */               {
/*  99 */                 CateCurPositionBean ccpb = new CateCurPositionBean();
/* 100 */                 ccpb.setJump_url(cb2.getJump_url());
/* 101 */                 ccpb.setCat_cname(cb2.getCat_cname());
/* 102 */                 ccpb.setCat_id(cb2.getCat_id());
/* 103 */                 if ((cb2.getJump_url() != null) && (!"".equals(cb2.getJump_url())))
/*     */                 {
/* 105 */                   ccpb.setUrl(cb2.getJump_url());
/*     */                 }
/* 109 */                 else if (("index".equals(page_type)) || (CategoryManager.isHasChildNode(Integer.parseInt(tempA[i]), site_id)))
/*     */                 {
/* 112 */                   ccpb.setUrl("/info/iIndex.jsp?cat_id=" + cb2.getCat_id());
/*     */                 }
/*     */                 else {
/* 115 */                   ccpb.setUrl("/info/iList.jsp?cat_id=" + cb2.getCat_id());
/*     */                 }
/* 117 */                 ccpb_list.add(ccpb);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 124 */     return ccpb_list;
/*     */   }
/*     */ 
/*     */   public static String getCategoryCName(int cat_id, String site_id)
/*     */   {
/* 135 */     CategoryBean cgb = CategoryManager.getCategoryBeanCatID(cat_id, site_id);
/* 136 */     if (cgb != null)
/*     */     {
/* 138 */       return cgb.getCat_cname();
/*     */     }
/* 140 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getCategoryIDSByUser(int cat_id, String site_id, int user_id)
/*     */   {
/* 153 */     if (CategoryManager.haveCategoryManagementAuthority(cat_id, site_id, user_id)) {
/* 154 */       return CategoryManager.getAllChildCategoryIDS(cat_id, site_id);
/*     */     }
/*     */ 
/* 157 */     List l = CategoryManager.getChildCategoryList(cat_id, site_id);
/*     */ 
/* 159 */     return getCategoryIDSByUserHandl(l, site_id, user_id);
/*     */   }
/*     */ 
/*     */   public static String getCategoryIDSByUserHandl(List<CategoryBean> l, String site_id, int user_id)
/*     */   {
/* 165 */     String cat_ids = "";
/* 166 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 168 */       for (CategoryBean cb : l)
/*     */       {
/* 170 */         if ((cb.getIs_archive() == 0) && (CategoryReleManager.isCategoryManagerByUser(user_id, site_id, cb.getCat_id())))
/*     */         {
/* 172 */           String all_child_ids = CategoryManager.getAllChildCategoryIDS(cb.getCat_id(), site_id);
/*     */ 
/* 174 */           if ((all_child_ids != null) && (!"".equals(all_child_ids)))
/* 175 */             cat_ids = cat_ids + "," + all_child_ids;
/*     */           else
/* 177 */             cat_ids = cat_ids + "," + cb.getCat_id();
/*     */         }
/*     */         else
/*     */         {
/* 181 */           List child_list = CategoryManager.getChildCategoryList(cb.getCat_id(), site_id);
/* 182 */           String child_ids = getCategoryIDSByUserHandl(child_list, site_id, user_id);
/* 183 */           if ((child_ids != null) && (!"".equals(child_ids)))
/* 184 */             cat_ids = cat_ids + "," + child_ids;
/*     */         }
/*     */       }
/* 187 */       if ((cat_ids != null) && (!"".equals(cat_ids)))
/* 188 */         cat_ids = cat_ids.substring(1);
/*     */     }
/* 190 */     return cat_ids;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 195 */     System.out.println(getCategoryIDSByUser(0, "HIWCMdemo", 1));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryUtil
 * JD-Core Version:    0.6.2
 */