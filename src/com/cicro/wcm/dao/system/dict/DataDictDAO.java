/*     */ package com.cicro.wcm.dao.system.dict;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictCategoryBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DataDictDAO
/*     */ {
/*     */   public static List<DataDictCategoryBean> getDataDictionaryCategoryList(Map<String, String> map)
/*     */   {
/*  23 */     return DBManager.queryFList("getDataDictionaryCategoryList", map);
/*     */   }
/*     */ 
/*     */   public static boolean addDataDictionaryCategory(DataDictCategoryBean dc, SettingLogsBean stl) {
/*  27 */     if (DBManager.insert("addDataDictionaryCategory", dc))
/*     */     {
/*  29 */       PublicTableDAO.insertSettingLogs("添加", "字典分类", dc.getDict_cat_id(), stl);
/*  30 */       return true;
/*     */     }
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delDataDictionaryCategory(String ids, SettingLogsBean stl) {
/*  36 */     Map map = new HashMap();
/*  37 */     map.put("ids", ids);
/*  38 */     if (DBManager.delete("delDataDictionaryCategory", map))
/*     */     {
/*  40 */       PublicTableDAO.insertSettingLogs("删除", "字典分类", ids, stl);
/*  41 */       return true;
/*     */     }
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDataDictionaryCategory(DataDictCategoryBean dc, SettingLogsBean stl) {
/*  47 */     if (DBManager.update("updateDataDictionaryCategory", dc))
/*     */     {
/*  49 */       PublicTableDAO.insertSettingLogs("修改", "字典分类", dc.getDict_cat_id(), stl);
/*  50 */       return true;
/*     */     }
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   public static DataDictCategoryBean selectOneDataDictionaryCategoryBean(String id) {
/*  56 */     Map map = new HashMap();
/*  57 */     map.put("dict_cat_id", id);
/*  58 */     return (DataDictCategoryBean)DBManager.queryFObj("selectOneDataDictionaryCategoryBean", map);
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictionaryList(Map<String, String> map)
/*     */   {
/*  64 */     return DBManager.queryFList("getDataDictionaryList", map);
/*     */   }
/*     */ 
/*     */   public static boolean addDataDictionary(DataDictBean dd, SettingLogsBean stl) {
/*  68 */     if (DBManager.insert("addDataDictionary", dd))
/*     */     {
/*  70 */       PublicTableDAO.insertSettingLogs("添加", "字典项", dd.getDict_id(), stl);
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delDataDictionary(String ids, SettingLogsBean stl) {
/*  77 */     Map map = new HashMap();
/*  78 */     map.put("ids", ids);
/*  79 */     if (DBManager.delete("delDataDictionary", map))
/*     */     {
/*  81 */       PublicTableDAO.insertSettingLogs("删除", "字典项", ids, stl);
/*  82 */       return true;
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delDataDictionaryOfCategory(String id, SettingLogsBean stl) {
/*  88 */     Map map = new HashMap();
/*  89 */     map.put("id", id);
/*  90 */     if (DBManager.delete("delDataDictionaryOfCategory", map))
/*     */     {
/*  92 */       PublicTableDAO.insertSettingLogs("删除", "字典分类" + id + "下所有数据项", id, stl);
/*  93 */       return true;
/*     */     }
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDataDictionary(DataDictBean dd, SettingLogsBean stl) {
/*  99 */     if (DBManager.update("updateDataDictionary", dd))
/*     */     {
/* 101 */       PublicTableDAO.insertSettingLogs("修改", "字典项", dd.getDict_id(), stl);
/* 102 */       return true;
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public static DataDictBean selectOneDataDictionaryBean(String id) {
/* 108 */     Map map = new HashMap();
/* 109 */     map.put("dict_id", id);
/* 110 */     return (DataDictBean)DBManager.queryFObj("selectOneDataDictionaryBean", map);
/*     */   }
/*     */ 
/*     */   public static boolean updateDictSort(String id, String sortNum, SettingLogsBean stl) {
/* 114 */     Map map = new HashMap();
/* 115 */     map.put("dict_sort", sortNum);
/* 116 */     map.put("dict_id", id);
/* 117 */     if (DBManager.update("updateDictSort", map))
/*     */     {
/* 119 */       PublicTableDAO.insertSettingLogs("修改", "字典项排序", id, stl);
/* 120 */       return true;
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.dict.DataDictDAO
 * JD-Core Version:    0.6.2
 */