/*    */ package com.cicro.wcm.dao.sendInfo;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SendRecordDAO
/*    */ {
/*    */   public static String getSendRecordCount(Map<String, String> m)
/*    */   {
/* 20 */     return DBManager.getString("getSendRecordCount", m);
/*    */   }
/*    */ 
/*    */   public static List<SendRecordBean> getSendRecordList(Map<String, String> m)
/*    */   {
/* 31 */     return DBManager.queryFList("getSendRecordList", m);
/*    */   }
/*    */ 
/*    */   public static boolean insertSendRecord(List<SendRecordBean> l)
/*    */   {
/*    */     try
/*    */     {
/* 42 */       for (SendRecordBean srb : l)
/*    */       {
/* 44 */         DBManager.insert("insert_send_record", srb);
/*    */       }
/* 46 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateSendRecord(Map<String, String> m)
/*    */   {
/* 61 */     return DBManager.update("update_send_record", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteSendRecord(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 72 */     if (DBManager.delete("delete_send_record", m))
/*    */     {
/* 74 */       PublicTableDAO.insertSettingLogs("删除", "报送记录信息", (String)m.get("ids"), stl);
/* 75 */       return true;
/*    */     }
/*    */ 
/* 78 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.sendInfo.SendRecordDAO
 * JD-Core Version:    0.6.2
 */