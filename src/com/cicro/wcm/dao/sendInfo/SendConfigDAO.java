/*     */ package com.cicro.wcm.dao.sendInfo;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendConfigBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SendConfigDAO
/*     */ {
/*     */   public static List<SendConfigBean> getSendConfigList()
/*     */   {
/*  22 */     return DBManager.queryFList("getSendConfigList", "");
/*     */   }
/*     */ 
/*     */   public static SendConfigBean getSendConfigBean(String site_id)
/*     */   {
/*  32 */     return (SendConfigBean)DBManager.queryFObj("getSendConfigBean", site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSendConfig(List<SendConfigBean> l, SettingLogsBean stl)
/*     */   {
/*  43 */     String site_ids = "";
/*  44 */     if ((l != null) && (l.size() > 0)) {
/*     */       try
/*     */       {
/*  47 */         for (SendConfigBean scf : l)
/*     */         {
/*  49 */           scf.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.SEND_CONFIG_TABLE_NAME));
/*  50 */           site_ids = site_ids + "," + scf.getSite_id();
/*  51 */           DBManager.update("insert_send_conf", scf);
/*     */         }
/*  53 */         PublicTableDAO.insertSettingLogs("添加", "报送站点设置", site_ids.substring(1), stl);
/*  54 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/*  57 */         e.printStackTrace();
/*  58 */         return false;
/*     */       }
/*     */     }
/*     */ 
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean updateSendConfig(SendConfigBean scb, SettingLogsBean stl)
/*     */   {
/*  73 */     if (DBManager.update("update_send_conf", scb))
/*     */     {
/*  75 */       PublicTableDAO.insertSettingLogs("修改", "报送站点设置", scb.getId(), stl);
/*  76 */       return true;
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSendConfig(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  89 */     if (DBManager.delete("delete_send_conf", m))
/*     */     {
/*  91 */       PublicTableDAO.insertSettingLogs("删除", "报送站点设置", (String)m.get("ids"), stl);
/*  92 */       return true;
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SendRecordBean> getSendSiteList()
/*     */   {
/* 105 */     return DBManager.queryFList("getSendSiteList", "");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.sendInfo.SendConfigDAO
 * JD-Core Version:    0.6.2
 */