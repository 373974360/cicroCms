/*    */ package com.cicro.wcm.dao.system.config;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.system.config.ConfigBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ConfigDAO
/*    */ {
/*    */   public static List<ConfigBean> getSystemConfigListBySiteID(String site_id)
/*    */   {
/* 25 */     return DBManager.queryFList("getSystemConfigListBySiteID", site_id);
/*    */   }
/*    */ 
/*    */   public static List<ConfigBean> getConfigList(Map<String, String> map)
/*    */   {
/* 30 */     return DBManager.queryFList("getSystemConfigList", map);
/*    */   }
/*    */ 
/*    */   public static boolean addConfig(String insertSql, SettingLogsBean stl)
/*    */   {
/* 35 */     Map map = new HashMap();
/* 36 */     map.put("insertSql", insertSql);
/* 37 */     if (DBManager.insert("insertSystemConfig", map))
/*    */     {
/* 39 */       return true;
/*    */     }
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateConfig(Map<String, String> map, SettingLogsBean stl)
/*    */   {
/* 46 */     if (DBManager.update("updateSystemConfig", map))
/*    */     {
/* 48 */       return true;
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteConfig(Map<String, String> map, SettingLogsBean stl) {
/* 54 */     if (DBManager.delete("deleteSystenConfig", map))
/*    */     {
/* 56 */       return true;
/*    */     }
/* 58 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.config.ConfigDAO
 * JD-Core Version:    0.6.2
 */