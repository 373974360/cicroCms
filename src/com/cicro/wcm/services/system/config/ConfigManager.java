/*     */ package com.cicro.wcm.services.system.config;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.config.ConfigBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.config.ConfigDAO;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ConfigManager
/*     */   implements ISyncCatch
/*     */ {
/*  22 */   private static Map<String, Map<String, String>> configMap = new HashMap();
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  26 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  31 */     configMap.clear();
/*     */   }
/*     */ 
/*     */   public static void clearMap()
/*     */   {
/*  36 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.config.ConfigManager");
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getConfigMap(Map<String, String> map) {
/*  40 */     Map key_value = (Map)configMap.get((String)map.get("group") + "_" + (String)map.get("site_id") + "_" + (String)map.get("app_id"));
/*  41 */     if (key_value != null) {
/*  42 */       return key_value;
/*     */     }
/*  44 */     key_value = new HashMap();
/*  45 */     List cbList = ConfigDAO.getConfigList(map);
/*  46 */     if ((cbList != null) && (cbList.size() > 0))
/*     */     {
/*  48 */       for (ConfigBean cb : cbList)
/*  49 */         key_value.put(cb.getKey(), cb.getValue());
/*     */     }
/*     */     else
/*     */     {
/*  53 */       key_value.put("upload_allow", "gif,jpg,jpeg,bmp,png,txt,zip,rar,doc,docx,xls,ppt,pdf,swf,flv");
/*  54 */       key_value.put("water_pic", "");
/*  55 */       key_value.put("water_width", "300");
/*  56 */       key_value.put("water_height", "150");
/*  57 */       key_value.put("water_transparent", "0.8");
/*  58 */       key_value.put("water_height", "150");
/*  59 */       key_value.put("normal_width", "600");
/*  60 */       key_value.put("thumb_width", "100");
/*  61 */       key_value.put("is_compress", "1");
/*     */     }
/*  63 */     configMap.put((String)map.get("group") + "_" + (String)map.get("site_id") + "_" + (String)map.get("app_id"), key_value);
/*     */ 
/*  66 */     return key_value;
/*     */   }
/*     */ 
/*     */   public static boolean adddConfigs(Map<String, String> map, SettingLogsBean stl) {
/*  70 */     configMap.remove((String)map.get("group") + "_" + (String)map.get("site_id") + "_" + (String)map.get("app_id"));
/*  71 */     Map mapTemp = new HashMap();
/*  72 */     mapTemp.put("group", (String)map.get("group"));
/*  73 */     mapTemp.put("site_id", (String)map.get("site_id"));
/*  74 */     mapTemp.put("app_id", (String)map.get("app_id"));
/*  75 */     if (ConfigDAO.deleteConfig(map, stl)) {
/*  76 */       Set set = map.keySet();
/*  77 */       for (String key : set)
/*  78 */         if (key.startsWith("key_"))
/*     */         {
/*     */           try
/*     */           {
/*  82 */             String insertSQL = "@ID,'@GROUP','@KEY','@VALUE','@SITEID','@APPID'";
/*  83 */             insertSQL = insertSQL.replace("@ID", PublicTableDAO.getIDByTableName(PublicTableDAO.SYS_CONFIG_TABLE_NAME));
/*  84 */             insertSQL = insertSQL.replace("@GROUP", map.get("group") == null ? "" : (String)map.get("group"));
/*  85 */             insertSQL = insertSQL.replace("@KEY", key.replaceAll("key_", ""));
/*  86 */             String value = (String)map.get(key);
/*  87 */             if ((value != null) && (!value.equals("")))
/*     */             {
/*  90 */               insertSQL = insertSQL.replace("@VALUE", value);
/*  91 */               insertSQL = insertSQL.replace("@SITEID", map.get("site_id") == null ? "" : (String)map.get("site_id"));
/*  92 */               insertSQL = insertSQL.replace("@APPID", map.get("app_id") == null ? "" : (String)map.get("app_id"));
/*  93 */               ConfigDAO.addConfig(insertSQL, stl);
/*     */             }
/*     */           } catch (Exception e) { e.printStackTrace(); }
/*     */ 
/*     */         }
/*     */     }
/*  99 */     clearMap();
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean updateConfigs(Map<String, String> map, SettingLogsBean stl) {
/* 104 */     configMap.remove((String)map.get("group") + "_" + (String)map.get("site_id") + "_" + (String)map.get("app_id"));
/* 105 */     Set set = map.keySet();
/* 106 */     for (String key : set)
/* 107 */       if (key.startsWith("key_"))
/*     */       {
/* 110 */         Map updateMap = new HashMap();
/* 111 */         updateMap.put("group", (String)map.get("group"));
/* 112 */         updateMap.put("key", key.replaceAll("key_", ""));
/* 113 */         String value = (String)map.get(key);
/* 114 */         if ((value == null) || (value.equals(""))) {
/* 115 */           ConfigDAO.deleteConfig(updateMap, stl);
/*     */         }
/*     */         else {
/* 118 */           updateMap.put("value", value);
/* 119 */           updateMap.put("site_id", (String)map.get("site_id"));
/* 120 */           updateMap.put("app_id", (String)map.get("app_id"));
/* 121 */           ConfigDAO.updateConfig(updateMap, stl);
/*     */         }
/*     */       }
/* 123 */     clearMap();
/* 124 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.config.ConfigManager
 * JD-Core Version:    0.6.2
 */