/*     */ package com.cicro.wcm.services.system.metadata;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.metadata.MetaDataBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.metadata.MetaDataDAO;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class MetaDataManager
/*     */   implements ISyncCatch
/*     */ {
/*  26 */   private static TreeMap<Integer, MetaDataBean> md_map = new TreeMap();
/*     */ 
/*     */   static {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  39 */     List md_list = MetaDataDAO.getAllMetaDataList();
/*  40 */     md_map.clear();
/*  41 */     if ((md_list != null) && (md_list.size() > 0))
/*     */     {
/*  43 */       for (int i = 0; i < md_list.size(); i++)
/*     */       {
/*  45 */         md_map.put(Integer.valueOf(((MetaDataBean)md_list.get(i)).getMeta_id()), (MetaDataBean)md_list.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMetaData()
/*     */   {
/*  57 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.metadata.MetaDataManager");
/*     */   }
/*     */ 
/*     */   public static Map<Integer, MetaDataBean> getMetaDataMap()
/*     */   {
/*  68 */     return md_map;
/*     */   }
/*     */ 
/*     */   public static String getMetaDataCount()
/*     */   {
/*  79 */     return md_map.size();
/*     */   }
/*     */ 
/*     */   public static String getMetaDataCountForDB(Map<String, String> m)
/*     */   {
/*  90 */     return MetaDataDAO.getMetaDataCountForDB(m);
/*     */   }
/*     */ 
/*     */   public static List<MetaDataBean> getMetaDataListForDB(Map<String, String> m)
/*     */   {
/* 100 */     return MetaDataDAO.getMetaDataListForDB(m);
/*     */   }
/*     */ 
/*     */   public static MetaDataBean getMetaDataBean(int metaData_id)
/*     */   {
/* 111 */     if (md_map.containsKey(Integer.valueOf(metaData_id))) {
/* 112 */       return (MetaDataBean)md_map.get(Integer.valueOf(metaData_id));
/*     */     }
/*     */ 
/* 115 */     MetaDataBean mdb = MetaDataDAO.getMetaDataBean(metaData_id);
/* 116 */     if (mdb != null)
/* 117 */       md_map.put(Integer.valueOf(metaData_id), mdb);
/* 118 */     return mdb;
/*     */   }
/*     */ 
/*     */   public static boolean insertMetaData(MetaDataBean mdb, SettingLogsBean stl)
/*     */   {
/* 129 */     if (MetaDataDAO.insertMetaData(mdb, stl))
/*     */     {
/* 131 */       reloadMetaData();
/* 132 */       return true;
/*     */     }
/*     */ 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMetaData(MetaDataBean mdb, SettingLogsBean stl)
/*     */   {
/* 145 */     if (MetaDataDAO.updateMetaData(mdb, stl))
/*     */     {
/* 147 */       reloadMetaData();
/* 148 */       return true;
/*     */     }
/*     */ 
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMetaData(String meta_id, SettingLogsBean stl)
/*     */   {
/* 161 */     if (MetaDataDAO.deleteMetaData(meta_id, stl))
/*     */     {
/* 163 */       reloadMetaData();
/* 164 */       return true;
/*     */     }
/*     */ 
/* 167 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.metadata.MetaDataManager
 * JD-Core Version:    0.6.2
 */