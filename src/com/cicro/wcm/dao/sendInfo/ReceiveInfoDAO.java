/*     */ package com.cicro.wcm.dao.sendInfo;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ReceiveInfoDAO
/*     */ {
/*     */   public static String getReceiveInfoCount(Map<String, String> m)
/*     */   {
/*  20 */     return DBManager.getString("getReceiveInfoCount", m);
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getReceiveInfoList(Map<String, String> m)
/*     */   {
/*  31 */     return DBManager.queryFList("getReceiveInfoList", m);
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getReceiveInfoListForIDS(Map<String, String> m)
/*     */   {
/*  42 */     return DBManager.queryFList("getReceiveInfoListForIDS", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertReceiveInfo(ReceiveInfoBean rib)
/*     */   {
/*  52 */     return DBManager.insert("insert_receive_info", rib);
/*     */   }
/*     */ 
/*     */   public static boolean adoptReceiveInfo(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  62 */     if (DBManager.update("adopt_receive_info", m))
/*     */     {
/*  64 */       if ("1".equals(m.get("adopt_status")))
/*     */       {
/*  66 */         PublicTableDAO.insertSettingLogs("处理", "采用报送信息", (String)m.get("ids"), stl);
/*     */       }
/*  68 */       else PublicTableDAO.insertSettingLogs("处理", "不采用报送信息", (String)m.get("ids"), stl);
/*     */ 
/*  70 */       return true;
/*     */     }
/*     */ 
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReceiveInfo(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  83 */     if (DBManager.update("delete_receive_info", m))
/*     */     {
/*  85 */       PublicTableDAO.insertSettingLogs("删除", "报送信息", (String)m.get("ids"), stl);
/*  86 */       return true;
/*     */     }
/*     */ 
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getReceiveCateInfoList(String site_id)
/*     */   {
/* 100 */     return DBManager.queryFList("getReceiveCateInfoList", site_id);
/*     */   }
/*     */ 
/*     */   public static ReceiveInfoBean getReceiveInfoBean(String id)
/*     */   {
/* 106 */     return (ReceiveInfoBean)DBManager.queryFObj("getReceiveInfoBean", id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.sendInfo.ReceiveInfoDAO
 * JD-Core Version:    0.6.2
 */