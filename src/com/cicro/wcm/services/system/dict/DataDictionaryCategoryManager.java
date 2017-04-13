/*     */ package com.cicro.wcm.services.system.dict;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictCategoryBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.dict.DataDictDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DataDictionaryCategoryManager
/*     */ {
/*     */   public static List<DataDictCategoryBean> getDataDictionaryCategoryList(Map<String, String> map)
/*     */   {
/*  21 */     return DataDictDAO.getDataDictionaryCategoryList(map);
/*     */   }
/*     */ 
/*     */   public static List<DataDictCategoryBean> getDictCategoryForSyS(int is_sys)
/*     */   {
/*  30 */     List c_list = new ArrayList();
/*  31 */     Map map = new HashMap();
/*  32 */     List l = getDataDictionaryCategoryList(map);
/*  33 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  35 */       for (DataDictCategoryBean dc : l) {
/*  36 */         if ((dc != null) && 
/*  37 */           (dc.getIs_sys() == is_sys)) {
/*  38 */           c_list.add(dc);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  43 */     return c_list;
/*     */   }
/*     */ 
/*     */   public static String getDcTreeJson(Map<String, String> map) {
/*  47 */     String defaultStr = "{\"id\":\"-2\",\"text\":\"系统数据字典\",\"attributes\":{\"nt\":\"df\"}, \"children\":[";
/*  48 */     String jsonStr = "{\"id\":\"-1\",\"text\":\"扩展数据字典\",\"attributes\":{\"nt\":\"kz\"},\"children\":[";
/*  49 */     boolean dflag = false; boolean cflag = false;
/*  50 */     List list = getDataDictionaryCategoryList(map);
/*  51 */     for (DataDictCategoryBean dc : list) {
/*  52 */       if (dc != null) {
/*  53 */         if (dc.getIs_sys() == 1) {
/*  54 */           defaultStr = defaultStr + "{\"id\":\"" + dc.getDict_cat_id() + "\",\"text\":\"" + dc.getDict_cat_name() + "\",\"attributes\":{\"site_id\":\"" + dc.getSite_id() + "\",\"nt\":\"df\"}},";
/*  55 */           dflag = true;
/*     */         } else {
/*  57 */           jsonStr = jsonStr + "{\"id\":\"" + dc.getDict_cat_id() + "\",\"text\":\"" + dc.getDict_cat_name() + "\",\"attributes\":{\"site_id\":\"" + dc.getSite_id() + "\",\"nt\":\"kz\"}},";
/*  58 */           cflag = true;
/*     */         }
/*     */       }
/*     */     }
/*  62 */     if (dflag) {
/*  63 */       defaultStr = defaultStr.substring(0, defaultStr.length() - 1);
/*     */     }
/*  65 */     if (cflag) {
/*  66 */       jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
/*     */     }
/*  68 */     defaultStr = defaultStr + "]}";
/*  69 */     jsonStr = jsonStr + "]}";
/*  70 */     return "[" + defaultStr + "," + jsonStr + "]";
/*     */   }
/*     */ 
/*     */   public static boolean addDataDictionaryCategory(DataDictCategoryBean dd, SettingLogsBean stl) {
/*  74 */     if (dd == null) {
/*  75 */       return false;
/*     */     }
/*  77 */     if ((dd.getDict_cat_id() != null) && (!dd.getDict_cat_id().trim().equals("")) && (selectOneDataDictionaryCategoryBean(dd.getDict_cat_id()) != null)) {
/*  78 */       return updateDataDictionaryCategory(dd, stl);
/*     */     }
/*     */ 
/*  81 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SYS_DICT_CATEGORY_TABLE_NAME);
/*  82 */     dd.setId(id);
/*  83 */     if ((dd.getDict_cat_id() == null) || (dd.getDict_cat_id().trim().equals(""))) {
/*  84 */       dd.setDict_cat_id(id);
/*     */     }
/*  86 */     return DataDictDAO.addDataDictionaryCategory(dd, stl);
/*     */   }
/*     */ 
/*     */   public static boolean delDataDictionaryCategory(String ids, SettingLogsBean stl) {
/*  90 */     return DataDictDAO.delDataDictionaryCategory(ids, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateDataDictionaryCategory(DataDictCategoryBean dd, SettingLogsBean stl) {
/*  94 */     return DataDictDAO.updateDataDictionaryCategory(dd, stl);
/*     */   }
/*     */ 
/*     */   public static DataDictCategoryBean selectOneDataDictionaryCategoryBean(String id) {
/*  98 */     return DataDictDAO.selectOneDataDictionaryCategoryBean(id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 102 */     Map map = new HashMap();
/* 103 */     System.out.println(getDcTreeJson(map));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.dict.DataDictionaryCategoryManager
 * JD-Core Version:    0.6.2
 */