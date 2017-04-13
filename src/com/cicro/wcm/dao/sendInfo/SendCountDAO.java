/*    */ package com.cicro.wcm.dao.sendInfo;
/*    */ 
/*    */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*    */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*    */ import com.cicro.wcm.bean.sendInfo.SendRecordCount;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SendCountDAO
/*    */ {
/*    */   public static List<SendRecordCount> getSendUserListForRecord(Map<String, String> m)
/*    */   {
/* 20 */     return DBManager.queryFList("getSendUserListForRecord", m);
/*    */   }
/*    */ 
/*    */   public static List<SendRecordCount> getSendCateListForRecord(Map<String, String> m)
/*    */   {
/* 31 */     return DBManager.queryFList("getSendCateListForRecord", m);
/*    */   }
/*    */ 
/*    */   public static List<SendRecordBean> getReceiveSiteListForRecord(String site_id)
/*    */   {
/* 42 */     return DBManager.queryFList("getReceiveSiteListForRecord", site_id);
/*    */   }
/*    */ 
/*    */   public static List<SendRecordCount> getReceiveCateListForRecord(Map<String, String> m)
/*    */   {
/* 53 */     return DBManager.queryFList("getReceiveCateListForRecord", m);
/*    */   }
/*    */ 
/*    */   public static List<SendRecordCount> getSendSiteCountForReceive(Map<String, String> m)
/*    */   {
/* 64 */     return DBManager.queryFList("getSendSiteCountForReceive", m);
/*    */   }
/*    */ 
/*    */   public static List<ReceiveInfoBean> getSendSiteList(String site_id)
/*    */   {
/* 75 */     return DBManager.queryFList("getSendSiteListForCount", site_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.sendInfo.SendCountDAO
 * JD-Core Version:    0.6.2
 */