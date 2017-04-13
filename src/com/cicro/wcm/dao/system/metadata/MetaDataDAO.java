/*     */ package com.cicro.wcm.dao.system.metadata;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.metadata.MetaDataBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MetaDataDAO
/*     */ {
/*     */   public static List<MetaDataBean> getAllMetaDataList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllMetaDataList", "");
/*     */   }
/*     */ 
/*     */   public static String getMetaDataCountForDB(Map<String, String> m)
/*     */   {
/*  43 */     return DBManager.getString("getMetaDataCountForDB", m);
/*     */   }
/*     */ 
/*     */   public static List<MetaDataBean> getMetaDataListForDB(Map<String, String> m)
/*     */   {
/*  55 */     return DBManager.queryFList("getMetaDataListForDB", m);
/*     */   }
/*     */ 
/*     */   public static MetaDataBean getMetaDataBean(int metaData_id)
/*     */   {
/*  66 */     return (MetaDataBean)DBManager.queryFObj("getMetaDataBean", metaData_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMetaData(MetaDataBean mdb, SettingLogsBean stl)
/*     */   {
/*  76 */     int meta_id = PublicTableDAO.getIDByTableName(PublicTableDAO.METADATA_TABLE_NAME);
/*  77 */     mdb.setMeta_id(meta_id);
/*  78 */     if (DBManager.insert("insert_metadata", mdb))
/*     */     {
/*  80 */       PublicTableDAO.insertSettingLogs("添加", "元数据", meta_id, stl);
/*  81 */       return true;
/*     */     }
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMetaData(MetaDataBean mdb, SettingLogsBean stl)
/*     */   {
/*  93 */     if (DBManager.update("update_metadata", mdb))
/*     */     {
/*  95 */       PublicTableDAO.insertSettingLogs("修改", "元数据", mdb.getMeta_id(), stl);
/*  96 */       return true;
/*     */     }
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMetaData(String meta_id, SettingLogsBean stl)
/*     */   {
/* 108 */     Map m = new HashMap();
/* 109 */     m.put("meta_id", meta_id);
/* 110 */     if (DBManager.delete("delete_metadata", m))
/*     */     {
/* 112 */       PublicTableDAO.insertSettingLogs("修改", "元数据", meta_id, stl);
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.metadata.MetaDataDAO
 * JD-Core Version:    0.6.2
 */