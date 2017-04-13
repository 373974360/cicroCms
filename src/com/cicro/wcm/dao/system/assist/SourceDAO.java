/*     */ package com.cicro.wcm.dao.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.SourceBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SourceDAO
/*     */ {
/*     */   public static List<SourceBean> getAllSourceList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllSourceList", "");
/*     */   }
/*     */ 
/*     */   public static SourceBean getSourceBean(int source_id)
/*     */   {
/*  42 */     return (SourceBean)DBManager.queryFObj("getSourceBean", Integer.valueOf(source_id));
/*     */   }
/*     */ 
/*     */   public static String getSourceCount(Map<String, String> con_map)
/*     */   {
/*  52 */     return DBManager.getString("getSourceCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<SourceBean> getSourceListForDB(Map<String, String> con_map)
/*     */   {
/*  63 */     return DBManager.queryFList("getSourceListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateSource(SourceBean source, SettingLogsBean stl)
/*     */   {
/*  72 */     if (DBManager.update("updateSource", source))
/*     */     {
/*  74 */       PublicTableDAO.insertSettingLogs("修改", "来源", source.getSource_id(), stl);
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSource(SourceBean source, SettingLogsBean stl)
/*     */   {
/*  86 */     int sourceId = PublicTableDAO.getIDByTableName(PublicTableDAO.SOURCE_TABLE_NAME);
/*  87 */     source.setSource_id(sourceId);
/*  88 */     if (DBManager.insert("insertSource", source))
/*     */     {
/*  90 */       PublicTableDAO.insertSettingLogs("添加", "来源", sourceId, stl);
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delSource(String source_ids, SettingLogsBean stl)
/*     */   {
/* 103 */     Map map = new HashMap();
/* 104 */     map.put("source_ids", source_ids);
/* 105 */     if (DBManager.delete("deleteSource", map))
/*     */     {
/* 107 */       PublicTableDAO.insertSettingLogs("删除", "来源", source_ids, stl);
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.assist.SourceDAO
 * JD-Core Version:    0.6.2
 */