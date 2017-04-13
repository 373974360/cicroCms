/*     */ package com.cicro.wcm.services.system.dict;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.dict.DataDictDAO;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DataDictionaryManager
/*     */   implements ISyncCatch
/*     */ {
/*  20 */   private static Map<String, List<DataDictBean>> ddMap = new HashMap();
/*     */ 
/*     */   static {
/*  23 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  28 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  33 */     ddMap.clear();
/*     */   }
/*     */ 
/*     */   public static void clearMap()
/*     */   {
/*  38 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.dict.DataDictionaryManager");
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictList(String dcid)
/*     */   {
/*  47 */     List list = (List)ddMap.get(dcid);
/*  48 */     if (list == null) {
/*  49 */       Map map = new HashMap();
/*  50 */       map.put("dict_cat_id", dcid);
/*  51 */       ddMap.put(dcid, getDataDictionaryList(map));
/*     */     }
/*  53 */     return (List)ddMap.get(dcid);
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictionaryList(Map<String, String> map) {
/*  57 */     return DataDictDAO.getDataDictionaryList(map);
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictionaryListOfCategory(String dcid, String sortType)
/*     */   {
/*  65 */     Map map = new HashMap();
/*  66 */     map.put("dict_cat_id", dcid);
/*  67 */     map.put("dict_sort", sortType);
/*  68 */     return DataDictDAO.getDataDictionaryList(map);
/*     */   }
/*     */   public static boolean addDataDictionary(DataDictBean dd, SettingLogsBean stl) {
/*  71 */     if (dd == null) {
/*  72 */       return false;
/*     */     }
/*  74 */     ddMap.remove(dd.getDict_cat_id());
/*  75 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SYS_DICT_TABLE_NAME);
/*  76 */     dd.setId(id);
/*  77 */     if ((dd.getDict_id() == null) || (dd.getDict_id().trim().equals(""))) {
/*  78 */       dd.setDict_id(id);
/*     */     }
/*  80 */     return DataDictDAO.addDataDictionary(dd, stl);
/*     */   }
/*     */ 
/*     */   public static boolean delDataDictionary(String ids, SettingLogsBean stl) {
/*  84 */     ddMap.clear();
/*  85 */     return DataDictDAO.delDataDictionary(ids, stl);
/*     */   }
/*     */ 
/*     */   public static boolean delDataDictionaryOfCategory(String id, SettingLogsBean stl) {
/*  89 */     ddMap.clear();
/*  90 */     return DataDictDAO.delDataDictionaryOfCategory(id, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateDataDictionary(DataDictBean dd, SettingLogsBean stl) {
/*  94 */     ddMap.remove(dd.getDict_cat_id());
/*  95 */     return DataDictDAO.updateDataDictionary(dd, stl);
/*     */   }
/*     */ 
/*     */   public static DataDictBean selectOneDataDictionaryBean(String id) {
/*  99 */     return DataDictDAO.selectOneDataDictionaryBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean updateDictSort(String id, String sortNum, SettingLogsBean stl) {
/* 103 */     return DataDictDAO.updateDictSort(id, sortNum, stl);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.dict.DataDictionaryManager
 * JD-Core Version:    0.6.2
 */