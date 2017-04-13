/*     */ package com.cicro.wcm.dao.sendInfo;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveCatConf;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveConfigBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ReceiveConfigDAO
/*     */ {
/*     */   public static List<ReceiveConfigBean> getReceiveConfigList()
/*     */   {
/*  22 */     return DBManager.queryFList("getReceiveConfigList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertReceiveConfig(ReceiveConfigBean rcf, SettingLogsBean stl)
/*     */   {
/*  33 */     if (DBManager.insert("insert_receive_config", rcf))
/*     */     {
/*  35 */       PublicTableDAO.insertSettingLogs("添加", "接收站点设置", rcf.getId(), stl);
/*  36 */       return true;
/*     */     }
/*     */ 
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveConfig(ReceiveConfigBean rcf, SettingLogsBean stl)
/*     */   {
/*  50 */     if (DBManager.update("update_receive_config", rcf))
/*     */     {
/*  52 */       PublicTableDAO.insertSettingLogs("修改", "接收站点设置", rcf.getId(), stl);
/*  53 */       return true;
/*     */     }
/*     */ 
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveConfigStatus(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  67 */     if (DBManager.update("update_receive_config_status", m))
/*     */     {
/*  69 */       PublicTableDAO.insertSettingLogs("修改", "接收站点设置状态", (String)m.get("ids"), stl);
/*  70 */       return true;
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReceiveConfig(String site_id, SettingLogsBean stl)
/*     */   {
/*  83 */     String[] ids = site_id.split(",");
/*  84 */     if ((ids != null) && (ids.length > 0))
/*     */     {
/*  86 */       for (int i = 0; i < ids.length; i++)
/*     */       {
/*  88 */         DBManager.delete("delete_receive_config", ids[i]);
/*  89 */         DBManager.delete("delete_receive_cat_conf", ids[i]);
/*     */       }
/*  91 */       PublicTableDAO.insertSettingLogs("删除", "接收站点设置", site_id, stl);
/*  92 */       return true;
/*     */     }
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   public static List<ReceiveCatConf> getReceiveCatConfList()
/*     */   {
/* 105 */     return DBManager.queryFList("getReceiveCatConfList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertReceiveCatConf(List<ReceiveCatConf> l, SettingLogsBean stl)
/*     */   {
/* 115 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 117 */       if (insertRCFHandl(l))
/*     */       {
/* 119 */         PublicTableDAO.insertSettingLogs("添加", "接收站点栏目设置", ((ReceiveCatConf)l.get(0)).getSite_id(), stl);
/* 120 */         return true;
/*     */       }
/* 122 */       return false;
/*     */     }
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveCatConf(String site_id, List<ReceiveCatConf> l, SettingLogsBean stl)
/*     */   {
/* 136 */     if (DBManager.delete("delete_receive_cat_conf", site_id))
/*     */     {
/* 138 */       if (insertRCFHandl(l))
/*     */       {
/* 140 */         PublicTableDAO.insertSettingLogs("修改", "接收站点栏目设置", site_id, stl);
/* 141 */         return true;
/*     */       }
/* 143 */       return false;
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRCFHandl(List<ReceiveCatConf> l)
/*     */   {
/*     */     try {
/* 151 */       for (ReceiveCatConf rcc : l)
/*     */       {
/* 153 */         rcc.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.RECEIVE_CAT_TABLE_NAME));
/* 154 */         DBManager.insert("insert_receive_cat_conf", rcc);
/*     */       }
/* 156 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */     }return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.sendInfo.ReceiveConfigDAO
 * JD-Core Version:    0.6.2
 */