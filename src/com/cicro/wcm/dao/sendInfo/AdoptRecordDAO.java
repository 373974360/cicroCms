/*    */ package com.cicro.wcm.dao.sendInfo;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.sendInfo.AdoptRecordBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AdoptRecordDAO
/*    */ {
/*    */   public static String getAdoptRecordCount(Map<String, String> m)
/*    */   {
/* 20 */     return DBManager.getString("getAdoptRecordCount", m);
/*    */   }
/*    */ 
/*    */   public static List<AdoptRecordBean> getAdoptRecordList(Map<String, String> m)
/*    */   {
/* 31 */     return DBManager.queryFList("getAdoptRecordList", m);
/*    */   }
/*    */ 
/*    */   public static boolean insertAdoptRecord(AdoptRecordBean arb, SettingLogsBean stl)
/*    */   {
/* 42 */     if (DBManager.insert("insert_adopt_record", arb))
/*    */     {
/* 44 */       PublicTableDAO.insertSettingLogs("添加", "接收信息采用记录", arb.getId(), stl);
/* 45 */       return true;
/*    */     }
/*    */ 
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteAdoptRecord(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 59 */     if (DBManager.delete("delete_adopt_record", stl))
/*    */     {
/* 61 */       PublicTableDAO.insertSettingLogs("删除", "接收信息采用记录", (String)m.get("ids"), stl);
/* 62 */       return true;
/*    */     }
/* 64 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.sendInfo.AdoptRecordDAO
 * JD-Core Version:    0.6.2
 */