/*     */ package com.cicro.wcm.dao.cms.digg;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.digg.InfoDiggBean;
/*     */ import com.cicro.wcm.bean.cms.digg.InfoDiggLogBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DiggDAO
/*     */ {
/*     */   public static InfoDiggBean getInfoDigg(String info_id)
/*     */   {
/*  34 */     return (InfoDiggBean)DBManager.queryFObj("getInfoDigg", info_id);
/*     */   }
/*     */ 
/*     */   public static String getInfoDiggCnt(Map<String, String> mp)
/*     */   {
/*  44 */     return (String)DBManager.queryFObj("getInfoDiggListCnt", mp);
/*     */   }
/*     */ 
/*     */   public static List getInfoDigg(Map<String, String> mp)
/*     */   {
/*  55 */     return DBManager.queryFList("getInfoDiggList", mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertInfoDigg(InfoDiggBean digg)
/*     */   {
/*  66 */     return DBManager.insert("insert_InfoDigg", digg);
/*     */   }
/*     */ 
/*     */   public static boolean updateInfoDigg(InfoDiggBean digg)
/*     */   {
/*  78 */     return DBManager.update("update_InfoDigg", digg);
/*     */   }
/*     */ 
/*     */   public static InfoDiggLogBean getDiggLog(String info_id, String user)
/*     */   {
/*  89 */     return (InfoDiggLogBean)DBManager.queryFObj("get_InfoDiggLog", info_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertInfoDiggLog(InfoDiggLogBean log)
/*     */   {
/*  99 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_DIGG_LOG_TABLE_NAME);
/* 100 */     log.setId(id);
/* 101 */     return DBManager.insert("insert_InfoDiggLog", log);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.digg.DiggDAO
 * JD-Core Version:    0.6.2
 */