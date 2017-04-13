/*     */ package com.cicro.wcm.services.model.services;
/*     */ 
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.model.WcmZykFile;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WcmZykInfoDao
/*     */ {
/*  23 */   private static String FIELD_TABLE_NAME = "cs_wcminfo_zykinfo";
/*     */ 
/*     */   public static boolean addWcminfo_zykinfo(String id, String info_id, String site_id)
/*     */   {
/*  31 */     Map map = new HashMap();
/*  32 */     map.put("id", id);
/*  33 */     map.put("info_id", info_id);
/*  34 */     map.put("site_id", site_id);
/*  35 */     return DBManager.insert("model.wcm_zykinfo.addWcminfo_zykinfo", map);
/*     */   }
/*     */ 
/*     */   public static String getWcminfo_zykinfoById(String id, String site_id)
/*     */   {
/*  45 */     Map map = new HashMap();
/*  46 */     map.put("id", id);
/*  47 */     map.put("site_id", site_id);
/*  48 */     return DBManager.getString("model.wcm_zykinfo.getWcminfo_zykinfoById", map);
/*     */   }
/*     */ 
/*     */   public static List<Map> getWcminfo_zykinfosById(String id)
/*     */   {
/*  58 */     Map map = new HashMap();
/*  59 */     map.put("id", id);
/*  60 */     return DBManager.queryFList("model.wcm_zykinfo.getWcminfo_zykinfosById", map);
/*     */   }
/*     */ 
/*     */   public static boolean deleteWcminfo_zykinfoById(String id, String site_id)
/*     */   {
/*  70 */     Map map = new HashMap();
/*  71 */     map.put("id", id);
/*  72 */     map.put("site_id", site_id);
/*  73 */     return DBManager.insert("model.wcm_zykinfo.deleteWcminfo_zykinfoById", map);
/*     */   }
/*     */ 
/*     */   public static boolean addZykinfoFile(Map map)
/*     */   {
/*  83 */     map.put("id", Integer.valueOf(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME)));
/*  84 */     return DBManager.insert("model.wcm_zykinfo.addZykinfoFile", map);
/*     */   }
/*     */ 
/*     */   public static boolean deleteZykinfoFileById(String id)
/*     */   {
/*  93 */     Map map = new HashMap();
/*  94 */     map.put("info_id", id);
/*  95 */     return DBManager.insert("model.wcm_zykinfo.deleteZykinfoFileById", map);
/*     */   }
/*     */ 
/*     */   public static List<WcmZykFile> getZykinfoFileListByInfoId(String info_id, String fieldName)
/*     */   {
/* 101 */     Map map = new HashMap();
/* 102 */     map.put("info_id", info_id);
/* 103 */     map.put("fieldName", fieldName);
/* 104 */     return DBManager.queryFList("model.wcm_zykinfo.getZykinfoFileListByInfoId", map);
/*     */   }
/*     */ 
/*     */   public static WcmZykFile getZykinfoFileByInfoId(String id)
/*     */   {
/* 109 */     Map map = new HashMap();
/* 110 */     map.put("id", id);
/* 111 */     return (WcmZykFile)DBManager.queryFObj("model.wcm_zykinfo.getZykinfoFileByInfoId", map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.WcmZykInfoDao
 * JD-Core Version:    0.6.2
 */