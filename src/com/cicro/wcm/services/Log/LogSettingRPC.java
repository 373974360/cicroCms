/*    */ package com.cicro.wcm.services.Log;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.LoginLogsBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class LogSettingRPC
/*    */ {
/*    */   public static List<SettingLogsBean> getLogSetting(Map<String, String> m)
/*    */   {
/* 24 */     return LogManager.searchSettingLog(m);
/*    */   }
/*    */ 
/*    */   public static String getLogSettingCount(Map<String, String> m)
/*    */   {
/* 34 */     return LogManager.searchSettingLogCnt(m);
/*    */   }
/*    */ 
/*    */   public static List<LoginLogsBean> searchLoginLogs(Map<String, String> mp)
/*    */   {
/* 44 */     return LogManager.searchLoginLogs(mp);
/*    */   }
/*    */ 
/*    */   public static String searchLoginLogsCnt(Map<String, String> mp)
/*    */   {
/* 53 */     return LogManager.searchLoginLogsCnt(mp);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.Log.LogSettingRPC
 * JD-Core Version:    0.6.2
 */