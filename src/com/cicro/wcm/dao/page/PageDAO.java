/*     */ package com.cicro.wcm.dao.page;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.page.PageBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PageDAO
/*     */ {
/*     */   public static List<PageBean> getPageListBySiteID(String site_id)
/*     */   {
/*  29 */     return DBManager.queryFList("getPageListBySiteID", site_id);
/*     */   }
/*     */ 
/*     */   public static List<PageBean> getAllPageList()
/*     */   {
/*  35 */     return DBManager.queryFList("getAllPageList", "");
/*     */   }
/*     */ 
/*     */   public static List<PageBean> getTimerPageList(String current_time)
/*     */   {
/*  41 */     Map m = new HashMap();
/*  42 */     m.put("current_time", current_time);
/*  43 */     String ip = ServerManager.LOCAL_IP;
/*  44 */     if ((ip != null) && (!"".equals(ip)) && (!"127.0.0.1".equals(ip)))
/*  45 */       m.put("server_ip", ServerManager.LOCAL_IP);
/*  46 */     return DBManager.queryFList("getTimerPageList", m);
/*     */   }
/*     */ 
/*     */   public static List<PageBean> getAllPageListBySiteID(String app_id, String site_id)
/*     */   {
/*  52 */     Map m = new HashMap();
/*  53 */     m.put("app_id", app_id);
/*  54 */     m.put("site_id", site_id);
/*  55 */     return DBManager.queryFList("getAllPageListBySiteID", m);
/*     */   }
/*     */ 
/*     */   public static PageBean getPageBean(int id)
/*     */   {
/*  60 */     return (PageBean)DBManager.queryFObj("getAllPageList", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static boolean clonePage(PageBean pb)
/*     */   {
/*  65 */     return DBManager.insert("insert_page", pb);
/*     */   }
/*     */ 
/*     */   public static boolean insertPage(PageBean pb, SettingLogsBean stl)
/*     */   {
/*  70 */     if (DBManager.insert("insert_page", pb))
/*     */     {
/*  72 */       PublicTableDAO.insertSettingLogs("添加", "页面", pb.getId(), stl);
/*  73 */       return true;
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updatePage(PageBean pb, SettingLogsBean stl)
/*     */   {
/*  80 */     if (DBManager.update("update_page", pb))
/*     */     {
/*  82 */       PublicTableDAO.insertSettingLogs("修改", "页面", pb.getId(), stl);
/*  83 */       return true;
/*     */     }
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updatePageTime(Map<String, String> m)
/*     */   {
/*  90 */     return DBManager.update("update_page_time", m);
/*     */   }
/*     */ 
/*     */   public static boolean deletePage(int id, SettingLogsBean stl)
/*     */   {
/*  95 */     if (DBManager.delete("delete_page", Integer.valueOf(id)))
/*     */     {
/*  97 */       PublicTableDAO.insertSettingLogs("删除", "页面", id, stl);
/*  98 */       return true;
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.page.PageDAO
 * JD-Core Version:    0.6.2
 */