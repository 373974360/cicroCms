/*     */ package com.cicro.wcm.dao.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryConfBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class queryConfDao
/*     */ {
/*     */   public static List<QueryConfBean> getAllQueryConfList()
/*     */   {
/*  22 */     return DBManager.queryFList("getAllQueryConfList", "");
/*     */   }
/*     */ 
/*     */   public static String getAllQueryConfCounts()
/*     */   {
/*  32 */     return DBManager.getString("getAllQueryConfCounts", "");
/*     */   }
/*     */ 
/*     */   public static String getQueryConfCount(Map<String, String> m)
/*     */   {
/*  43 */     return DBManager.getString("getQueryConfCount", m);
/*     */   }
/*     */ 
/*     */   public static List<QueryConfBean> getQueryConfList(Map<String, String> m)
/*     */   {
/*  54 */     return DBManager.queryFList("getQueryConfList", m);
/*     */   }
/*     */ 
/*     */   public static QueryConfBean getQueryConfBeanById(int conf_id)
/*     */   {
/*  64 */     return (QueryConfBean)DBManager.queryFObj("getQueryConfBean", Integer.valueOf(conf_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryConf(QueryConfBean ob, SettingLogsBean stl)
/*     */   {
/*  76 */     System.out.println("insertQueryConf dao =========" + ob);
/*  77 */     if (DBManager.insert("insert_QueryConf", ob)) {
/*  78 */       PublicTableDAO.insertSettingLogs("添加", "查询业务", ob.getConf_id(), stl);
/*  79 */       return true;
/*     */     }
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryConf(QueryConfBean ob, SettingLogsBean stl)
/*     */   {
/*  92 */     if (DBManager.update("update_QueryConf", ob))
/*     */     {
/*  94 */       PublicTableDAO.insertSettingLogs("修改", "查询业务", ob.getConf_id(), stl);
/*  95 */       return true;
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryConf(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 109 */     if (DBManager.delete("delete_QueryConf", m))
/*     */     {
/* 111 */       PublicTableDAO.insertSettingLogs("删除", "查询业务", (String)m.get("conf_id"), stl);
/* 112 */       return true;
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.query.queryConfDao
 * JD-Core Version:    0.6.2
 */