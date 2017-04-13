/*     */ package com.cicro.wcm.dao.control;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteServerBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteServerDAO
/*     */ {
/*     */   public static List getSiteServerList()
/*     */   {
/*  32 */     return DBManager.queryFList("getSiteServerList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteServer(SiteServerBean ssb, SettingLogsBean stl)
/*     */   {
/*  43 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SITESERVER_TABLE_NAME);
/*  44 */     ssb.setServer_id(id);
/*  45 */     if (DBManager.insert("insert_site_server", ssb))
/*     */     {
/*  47 */       PublicTableDAO.insertSettingLogs("添加", "网站服务器", id, stl);
/*  48 */       return true;
/*     */     }
/*     */ 
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteServer(SiteServerBean ssb, SettingLogsBean stl)
/*     */   {
/*  64 */     if (DBManager.update("update_site_server", ssb))
/*     */     {
/*  66 */       PublicTableDAO.insertSettingLogs("修改", "网站服务器", ssb.getServer_id(), stl);
/*  67 */       return true;
/*     */     }
/*     */ 
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteServer(String server_ids, SettingLogsBean stl)
/*     */   {
/*  82 */     Map m = new HashMap();
/*  83 */     m.put("server_id", server_ids);
/*  84 */     if (DBManager.delete("delete_site_server", m))
/*     */     {
/*  86 */       PublicTableDAO.insertSettingLogs("删除", "网站服务器", server_ids, stl);
/*  87 */       return true;
/*     */     }
/*     */ 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SiteServerBean> getSiteServerListByPage(Map<String, String> m)
/*     */   {
/* 101 */     return DBManager.queryFList("getServerList", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.control.SiteServerDAO
 * JD-Core Version:    0.6.2
 */