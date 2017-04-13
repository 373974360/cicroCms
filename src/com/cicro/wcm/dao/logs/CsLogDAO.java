/*     */ package com.cicro.wcm.dao.logs;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CsLogDAO
/*     */ {
/*     */   public static List getAllLogSetting(Map<String, String> m)
/*     */   {
/*  42 */     return DBManager.queryFList("getAllLogSetting", m);
/*     */   }
/*     */ 
/*     */   public static String getLogSettingCount(Map<String, String> m)
/*     */   {
/*  52 */     return DBManager.getString("getLogSettingCount", m);
/*     */   }
/*     */ 
/*     */   public static SettingLogsBean getLogSettingBean(int audit_id)
/*     */   {
/*  61 */     return (SettingLogsBean)DBManager.queryFObj("getLogSettingBean", audit_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertLogSetting(SettingLogsBean slb)
/*     */   {
/*  70 */     return DBManager.insert("insert_LogSetting", slb);
/*     */   }
/*     */ 
/*     */   public static boolean updateLogSetting(SettingLogsBean slb)
/*     */   {
/*  79 */     return DBManager.update("update_LogSetting", slb);
/*     */   }
/*     */ 
/*     */   public static boolean deleteLogSetting(String audit_ids)
/*     */   {
/*  88 */     Map m = new HashMap();
/*  89 */     m.put("audit_ids", audit_ids);
/*  90 */     return DBManager.delete("delete_LogSetting", m);
/*     */   }
/*     */ 
/*     */   public static String searchLogSettingCount(Map<String, String> mp)
/*     */   {
/*  99 */     return DBManager.getString("searchLogSettingCount", mp);
/*     */   }
/*     */ 
/*     */   public static List<SettingLogsBean> searchLogSetting(Map<String, String> mp)
/*     */   {
/* 109 */     return DBManager.queryFList("searchLogSetting", mp);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 113 */     Map mp = new HashMap();
/* 114 */     mp.put("page_size", "20");
/* 115 */     mp.put("start_num", "20");
/* 116 */     List lt = searchLogSetting(mp);
/* 117 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.logs.CsLogDAO
 * JD-Core Version:    0.6.2
 */