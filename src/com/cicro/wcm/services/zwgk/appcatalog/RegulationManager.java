/*     */ package com.cicro.wcm.services.zwgk.appcatalog;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.RegulationBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.appcatalog.AppCatalogDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RegulationManager
/*     */ {
/*     */   public static int getNewRegulationID()
/*     */   {
/*  25 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.GK_APPREGU_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static List<RegulationBean> getAppCataReguList(int cata_id)
/*     */   {
/*  35 */     List l = AppCatalogDAO.getAppCataReguList(cata_id);
/*  36 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  38 */       for (RegulationBean rgb : l)
/*     */       {
/*  40 */         if (rgb.getRegu_type() == 0)
/*     */         {
/*  42 */           rgb.setCat_id_names(CategoryManager.getMutilCategoryNames(rgb.getCat_ids(), ""));
/*  43 */           if ((rgb.getSite_ids() != null) && (!"".equals(rgb.getSite_ids())))
/*  44 */             rgb.setSite_id_names(GKNodeManager.getMutilNodeNames(rgb.getSite_ids()));
/*     */         }
/*     */         else
/*     */         {
/*  48 */           rgb.setSite_id_names(GKNodeManager.getNodeNameByNodeID(rgb.getSite_ids()));
/*  49 */           rgb.setCat_id_names(CategoryManager.getMutilCategoryNames(rgb.getCat_ids(), rgb.getSite_ids()));
/*     */         }
/*     */       }
/*     */     }
/*  53 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean insertAppCateRegu(List<RegulationBean> l, String cata_id, SettingLogsBean stl)
/*     */   {
/*  65 */     String sql_node = "";
/*  66 */     String sql_cat = "";
/*  67 */     String sql = "";
/*     */ 
/*  69 */     if (AppCatalogDAO.deleteAppCateRegu(cata_id)) {
/*     */       try
/*     */       {
/*  72 */         if ((l != null) && (l.size() > 0))
/*     */         {
/*  74 */           for (RegulationBean rgb : l)
/*     */           {
/*  76 */             rgb.setId(getNewRegulationID());
/*     */ 
/*  78 */             if (AppCatalogDAO.insertAppCateRegu(rgb))
/*     */             {
/*  80 */               if (rgb.getRegu_type() == 1)
/*     */               {
/*  82 */                 sql_node = sql_node + "or " + spellConSql(rgb.getCat_ids(), rgb.getSite_ids(), rgb.getRegu_type());
/*     */               }
/*     */               else {
/*  85 */                 sql_cat = sql_cat + "or " + spellConSql(rgb.getCat_ids(), rgb.getSite_ids(), rgb.getRegu_type());
/*     */               }
/*     */             }
/*     */           }
/*  89 */           if ((sql_node != null) && (!"".equals(sql_node)))
/*  90 */             sql_node = sql_node.substring(2);
/*  91 */           if ((sql_cat != null) && (!"".equals(sql_cat)))
/*     */           {
/*  93 */             sql_cat = " ca.cat_id in (select cat_id from cs_info_category where " + sql_cat.substring(2) + ")";
/*     */           }
/*  95 */           sql = sql_node + " or " + sql_cat;
/*  96 */           if (sql.startsWith(" or"))
/*     */           {
/*  98 */             sql = sql.substring(3);
/*     */           }
/* 100 */           if (sql.endsWith("or "))
/*     */           {
/* 102 */             sql = sql.substring(0, sql.length() - 3);
/*     */           }
/* 104 */           sql = "(" + sql + ")";
/*     */         }
/*     */ 
/* 107 */         AppCatalogManager.updateGKAppCatelogSQL(sql, cata_id);
/* 108 */         PublicTableDAO.insertSettingLogs("移动", "公开应用目录同步规则", cata_id, stl);
/* 109 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 112 */         e.printStackTrace();
/* 113 */         return false;
/*     */       }
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static String spellConSql(String cat_ids, String site_ids, int regu_type)
/*     */   {
/* 127 */     String sql = "";
/*     */ 
/* 129 */     if (regu_type == 1)
/*     */     {
/* 131 */       site_ids = "'" + site_ids.replaceAll(",", "','") + "'";
/*     */ 
/* 133 */       sql = "( ci.site_id in (" + site_ids + ") and ci.cat_id in (" + cat_ids + ") )";
/*     */     }
/*     */     else
/*     */     {
/* 138 */       sql = "( cat_class_id in (" + cat_ids + ")";
/* 139 */       if ((site_ids != null) && (!"".equals(site_ids)))
/*     */       {
/* 141 */         site_ids = "'" + site_ids.replaceAll(",", "','") + "'";
/* 142 */         sql = sql + " and site_id in (" + site_ids + ")";
/*     */       }
/* 144 */       sql = sql + " )";
/*     */     }
/* 146 */     return sql;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.appcatalog.RegulationManager
 * JD-Core Version:    0.6.2
 */