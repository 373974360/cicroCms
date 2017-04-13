/*    */ package com.cicro.wcm.services.system.config;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ConfigRPC
/*    */ {
/*    */   public static boolean add(Map<String, String> map, HttpServletRequest request)
/*    */   {
/* 18 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 19 */     if (stl != null) {
/* 20 */       return ConfigManager.adddConfigs(map, stl);
/*    */     }
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean update(Map<String, String> map, HttpServletRequest request) {
/* 26 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 27 */     if (stl != null) {
/* 28 */       return ConfigManager.updateConfigs(map, stl);
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   public static Map<String, String> getValues(Map<String, String> map) {
/* 34 */     return ConfigManager.getConfigMap(map);
/*    */   }
/*    */ 
/*    */   public static boolean setGroupWebPageGrey()
/*    */   {
/* 43 */     return WebPageColorService.setGroupWebPageGrey();
/*    */   }
/*    */ 
/*    */   public static boolean setGroupWebPageNoGrey()
/*    */   {
/* 51 */     return WebPageColorService.setGroupWebPageNoGrey();
/*    */   }
/*    */ 
/*    */   public static boolean setSiteWebPageGrey(String site_id)
/*    */   {
/* 60 */     return WebPageColorService.setSiteWebPageGrey(site_id);
/*    */   }
/*    */ 
/*    */   public static boolean setSiteWebPageNoGrey(String site_id)
/*    */   {
/* 68 */     return WebPageColorService.setSiteWebPageNoGrey(site_id);
/*    */   }
/*    */ 
/*    */   public static boolean setGroupWebPageGreyNoGrey()
/*    */   {
/* 75 */     return WebPageColorService.setGroupWebPageGreyNoGrey();
/*    */   }
/*    */ 
/*    */   public static boolean setSiteWebPageGreyORNoGrey(String site_id)
/*    */   {
/* 82 */     return WebPageColorService.setSiteWebPageGreyORNoGrey(site_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.config.ConfigRPC
 * JD-Core Version:    0.6.2
 */