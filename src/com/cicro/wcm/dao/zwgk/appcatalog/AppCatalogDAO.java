/*     */ package com.cicro.wcm.dao.zwgk.appcatalog;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.RegulationBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AppCatalogDAO
/*     */ {
/*     */   public static List<AppCatalogBean> getGKAppCatalogList()
/*     */   {
/*  31 */     return DBManager.queryFList("getGKAppCatalogList", "");
/*     */   }
/*     */ 
/*     */   public static AppCatalogBean getAppCatalogBean(int cata_id)
/*     */   {
/*  41 */     return (AppCatalogBean)DBManager.queryFObj("getAppCatalogBean", Integer.valueOf(cata_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertGKAppCatelog(AppCatalogBean acb)
/*     */   {
/*  51 */     return DBManager.insert("insert_gk_app_catalog", acb);
/*     */   }
/*     */ 
/*     */   public static boolean updateGKAppCatelog(AppCatalogBean acb, SettingLogsBean stl)
/*     */   {
/*  61 */     if (DBManager.update("update_gk_app_catalog", acb))
/*     */     {
/*  63 */       PublicTableDAO.insertSettingLogs("修改", "公开应用目录", acb.getCata_id(), stl);
/*  64 */       return true;
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKAppCatelog(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  77 */       Map m = new HashMap();
/*  78 */       String[] tempA = ids.split(",");
/*  79 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  81 */         m.put("cat_sort", Integer.valueOf(i + 1));
/*  82 */         m.put("cata_id", tempA[i]);
/*  83 */         DBManager.update("sort_gk_app_catalog", m);
/*     */       }
/*  85 */       PublicTableDAO.insertSettingLogs("保存排序", "公开应用目录", ids, stl);
/*  86 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  90 */       e.printStackTrace();
/*  91 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKAppCatelog(Map<String, String> m)
/*     */   {
/* 102 */     return DBManager.update("move_gk_app_catalog", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateGKAppCatelogSQL(Map<String, String> m)
/*     */   {
/* 112 */     return DBManager.update("update_gk_app_cata_sql", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKAppCatelog(String cata_ids, SettingLogsBean stl)
/*     */   {
/* 122 */     Map m = new HashMap();
/* 123 */     m.put("cata_ids", cata_ids);
/* 124 */     if (DBManager.delete("delete_gk_app_catalog", m))
/*     */     {
/* 126 */       PublicTableDAO.insertSettingLogs("删除", "公开应用目录", cata_ids, stl);
/* 127 */       return true;
/*     */     }
/* 129 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<RegulationBean> getAppCataReguList(int cata_id)
/*     */   {
/* 141 */     return DBManager.queryFList("getAppCataReguList", cata_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertAppCateRegu(RegulationBean rub)
/*     */   {
/* 151 */     return DBManager.insert("insert_gk_app_regu", rub);
/*     */   }
/*     */ 
/*     */   public static boolean deleteAppCateRegu(String cata_ids)
/*     */   {
/* 161 */     Map m = new HashMap();
/* 162 */     m.put("cata_ids", cata_ids);
/* 163 */     return DBManager.insert("delete_gk_app_regu", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.appcatalog.AppCatalogDAO
 * JD-Core Version:    0.6.2
 */