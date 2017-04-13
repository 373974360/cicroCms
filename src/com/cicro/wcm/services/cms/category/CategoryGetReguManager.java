/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryGetRegu;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.cms.category.CategoryGetReguDAO;
/*     */ import com.cicro.wcm.services.zwgk.appcatalog.RegulationManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CategoryGetReguManager
/*     */ {
/*     */   public static int getNewRegulationID()
/*     */   {
/*  26 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CATEGORY_REGU_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static List<CategoryGetRegu> getCatagoryReguList(int cat_id, String site_id)
/*     */   {
/*  36 */     Map m = new HashMap();
/*  37 */     m.put("cat_id", cat_id);
/*  38 */     m.put("site_id", site_id);
/*  39 */     List l = CategoryGetReguDAO.getCategoryReguList(m);
/*  40 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  42 */       for (CategoryGetRegu cgr : l)
/*     */       {
/*  44 */         if (cgr.getRegu_type() == 0)
/*     */         {
/*  46 */           cgr.setCat_id_names(CategoryManager.getMutilCategoryNames(cgr.getCat_ids(), ""));
/*  47 */           if ((cgr.getSite_ids() != null) && (!"".equals(cgr.getSite_ids())))
/*  48 */             cgr.setSite_id_names(GKNodeManager.getMutilNodeNames(cgr.getSite_ids()));
/*     */         }
/*     */         else
/*     */         {
/*  52 */           cgr.setSite_id_names(GKNodeManager.getNodeNameByNodeID(cgr.getSite_ids()));
/*  53 */           cgr.setCat_id_names(CategoryManager.getMutilCategoryNames(cgr.getCat_ids(), cgr.getSite_ids()));
/*     */         }
/*     */       }
/*     */     }
/*  57 */     return l;
/*     */   }
/*     */ 
/*     */   public static String insertCategoryRegu(List<CategoryGetRegu> l, int cat_id, String site_id, String app_id)
/*     */   {
/*  69 */     String sql = "";
/*     */ 
/*  71 */     if (CategoryGetReguDAO.deleteCategoryRegu(cat_id, site_id)) {
/*     */       try
/*     */       {
/*  74 */         sql = insertReguHandl(l, cat_id, site_id, app_id);
/*     */       }
/*     */       catch (Exception e) {
/*  77 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  80 */     return sql;
/*     */   }
/*     */ 
/*     */   public static String insertReguHandl(List<CategoryGetRegu> l, int cat_id, String site_id, String app_id)
/*     */   {
/*  85 */     String sql_node = "";
/*  86 */     String sql_cat = "";
/*  87 */     String sql = "";
/*     */     try {
/*  89 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  91 */         for (CategoryGetRegu rgb : l)
/*     */         {
/*  93 */           rgb.setId(getNewRegulationID());
/*  94 */           rgb.setCat_id(cat_id);
/*  95 */           rgb.setSite_id(site_id);
/*  96 */           rgb.setApp_id(app_id);
/*     */ 
/*  98 */           if (CategoryGetReguDAO.insertCategoryRegu(rgb))
/*     */           {
/* 100 */             if (rgb.getRegu_type() == 1)
/*     */             {
/* 102 */               sql_node = sql_node + "or " + RegulationManager.spellConSql(rgb.getCat_ids(), rgb.getSite_ids(), rgb.getRegu_type());
/*     */             }
/*     */             else {
/* 105 */               sql_cat = sql_cat + "or " + RegulationManager.spellConSql(rgb.getCat_ids(), rgb.getSite_ids(), rgb.getRegu_type());
/*     */             }
/*     */           }
/*     */         }
/* 109 */         if ((sql_node != null) && (!"".equals(sql_node)))
/* 110 */           sql_node = sql_node.substring(2);
/* 111 */         if ((sql_cat != null) && (!"".equals(sql_cat)))
/*     */         {
/* 113 */           sql_cat = " ca.cat_id in (select cat_id from cs_info_category where " + sql_cat.substring(2) + ")";
/*     */         }
/* 115 */         sql = sql_node + " or " + sql_cat;
/*     */ 
/* 117 */         if (sql.startsWith(" or"))
/*     */         {
/* 119 */           sql = sql.substring(3);
/*     */         }
/* 121 */         if (sql.endsWith("or "))
/*     */         {
/* 123 */           sql = sql.substring(0, sql.length() - 3);
/*     */         }
/*     */       }
/* 125 */       return "(" + sql + ")";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 130 */       e.printStackTrace();
/* 131 */     }return "";
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 137 */     System.out.println(" or cat_d".startsWith(" or"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryGetReguManager
 * JD-Core Version:    0.6.2
 */