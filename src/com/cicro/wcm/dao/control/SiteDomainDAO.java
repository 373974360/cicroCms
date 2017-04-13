/*     */ package com.cicro.wcm.dao.control;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteDomainDAO
/*     */ {
/*     */   public static List getSiteDomainList()
/*     */   {
/*  31 */     return DBManager.queryFList("getSiteDomainList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteDomain(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/*  42 */     int domain_id = PublicTableDAO.getIDByTableName(PublicTableDAO.SITEDOMAIN_TABLE_NAME);
/*  43 */     sdb.setDomain_id(domain_id);
/*  44 */     if (DBManager.insert("insert_site_domain", sdb))
/*     */     {
/*  46 */       PublicTableDAO.insertSettingLogs("添加", "域名", sdb.getDomain_id(), stl);
/*  47 */       return true;
/*     */     }
/*     */ 
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteDomain(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/*  62 */     if (DBManager.update("update_site_domain", sdb))
/*     */     {
/*  64 */       PublicTableDAO.insertSettingLogs("修改", "域名", sdb.getDomain_id(), stl);
/*  65 */       return true;
/*     */     }
/*     */ 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteDomainByName(Map<String, String> m)
/*     */   {
/*  80 */     return DBManager.update("update_site_domain_byName", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSDomainStatus(String domain_id, String site_id, SettingLogsBean stl)
/*     */   {
/*  92 */     if (DBManager.update("cancel_sitedomain_default", site_id))
/*     */     {
/*  94 */       if (DBManager.update("update_site_domain_status", domain_id))
/*     */       {
/*  96 */         PublicTableDAO.insertSettingLogs("修改", "域名状态", domain_id, stl);
/*  97 */         return true;
/*     */       }
/*     */ 
/* 100 */       return false;
/*     */     }
/*     */ 
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteDomainByID(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 115 */     if (DBManager.delete("delete_site_domain", m))
/*     */     {
/* 117 */       PublicTableDAO.insertSettingLogs("删除", "域名", (String)m.get("domain_id"), stl);
/* 118 */       return true;
/*     */     }
/*     */ 
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteDomainBySiteID(String site_id, SettingLogsBean stl)
/*     */   {
/* 133 */     if (DBManager.delete("delete_site_domainBySiteID", site_id))
/*     */     {
/* 135 */       PublicTableDAO.insertSettingLogs("删除", "域名 站点ID", site_id, stl);
/* 136 */       return true;
/*     */     }
/*     */ 
/* 139 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.control.SiteDomainDAO
 * JD-Core Version:    0.6.2
 */