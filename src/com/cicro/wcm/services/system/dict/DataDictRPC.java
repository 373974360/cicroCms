/*     */ package com.cicro.wcm.services.system.dict;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictCategoryBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class DataDictRPC
/*     */ {
/*     */   public static List<DataDictCategoryBean> getDictCategoryForSyS(int is_sys)
/*     */   {
/*  25 */     return DataDictionaryCategoryManager.getDictCategoryForSyS(is_sys);
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictList(String dcid)
/*     */   {
/*  34 */     return DataDictionaryManager.getDataDictList(dcid);
/*     */   }
/*     */ 
/*     */   public static List<DataDictCategoryBean> getDCList(Map<String, String> map)
/*     */   {
/*  39 */     return DataDictionaryCategoryManager.getDataDictionaryCategoryList(map);
/*     */   }
/*     */ 
/*     */   public static String getDcTreeJson(Map<String, String> map) {
/*  43 */     return DataDictionaryCategoryManager.getDcTreeJson(map);
/*     */   }
/*     */   public static boolean addDC(DataDictCategoryBean dd, HttpServletRequest request) {
/*  46 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  47 */     if (stl != null) {
/*  48 */       return DataDictionaryCategoryManager.addDataDictionaryCategory(dd, stl);
/*     */     }
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delDC(String ids, HttpServletRequest request) {
/*  54 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  55 */     if (stl != null) {
/*  56 */       return DataDictionaryCategoryManager.delDataDictionaryCategory(ids, stl);
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDC(DataDictCategoryBean dd, HttpServletRequest request) {
/*  62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  63 */     if (stl != null) {
/*  64 */       return DataDictionaryCategoryManager.updateDataDictionaryCategory(dd, stl);
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   public static DataDictCategoryBean getOneDCBean(String id) {
/*  70 */     return DataDictionaryCategoryManager.selectOneDataDictionaryCategoryBean(id);
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDictList(Map<String, String> map)
/*     */   {
/*  75 */     return DataDictionaryManager.getDataDictionaryList(map);
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDictList(String dcid, String sortType) {
/*  79 */     return DataDictionaryManager.getDataDictionaryListOfCategory(dcid, sortType);
/*     */   }
/*     */ 
/*     */   public static boolean addDict(DataDictBean dd, HttpServletRequest request) {
/*  83 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  84 */     if (stl != null) {
/*  85 */       return DataDictionaryManager.addDataDictionary(dd, stl);
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delDict(String ids, HttpServletRequest request) {
/*  91 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  92 */     if (stl != null) {
/*  93 */       return DataDictionaryManager.delDataDictionary(ids, stl);
/*     */     }
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delDicts(String id, HttpServletRequest request) {
/*  99 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 100 */     if (stl != null) {
/* 101 */       return DataDictionaryManager.delDataDictionaryOfCategory(id, stl);
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDict(DataDictBean dd, HttpServletRequest request) {
/* 107 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 108 */     if (stl != null) {
/* 109 */       return DataDictionaryManager.updateDataDictionary(dd, stl);
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public static DataDictBean getOneDictBean(String id) {
/* 115 */     return DataDictionaryManager.selectOneDataDictionaryBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSort(String id, String sortNum, HttpServletRequest request) {
/* 119 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 120 */     if (stl != null) {
/* 121 */       return DataDictionaryManager.updateDictSort(id, sortNum, stl);
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 128 */     System.out.println("getDataDictList----" + DataDictionaryCategoryManager.getDictCategoryForSyS(0));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.dict.DataDictRPC
 * JD-Core Version:    0.6.2
 */