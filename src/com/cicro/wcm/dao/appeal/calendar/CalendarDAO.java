/*    */ package com.cicro.wcm.dao.appeal.calendar;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.calendar.CalendarBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CalendarDAO
/*    */ {
/*    */   public static List<CalendarBean> getAllCalendarList()
/*    */   {
/* 31 */     return DBManager.queryFList("getAllCalendar", "");
/*    */   }
/*    */ 
/*    */   public static CalendarBean getCalendarBean(int ca_id)
/*    */   {
/* 41 */     return (CalendarBean)DBManager.queryFObj("getCalendarBean", Integer.valueOf(ca_id));
/*    */   }
/*    */ 
/*    */   public static String getCalendarCount()
/*    */   {
/* 46 */     return DBManager.getString("getCalendarCount", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertCalendar(CalendarBean calebean, SettingLogsBean stl)
/*    */   {
/* 56 */     if (DBManager.insert("insert_Calendar", calebean)) {
/* 57 */       PublicTableDAO.insertSettingLogs("添加", "节假日", calebean.getCa_id(), stl);
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateCalendar(CalendarBean calebean, SettingLogsBean stl)
/*    */   {
/* 71 */     if (DBManager.update("update_Calendar", calebean)) {
/* 72 */       PublicTableDAO.insertSettingLogs("修改", "节假日", calebean.getCa_id(), stl);
/* 73 */       return true;
/*    */     }
/* 75 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteCalendar(Map<String, String> mp, SettingLogsBean stl)
/*    */   {
/* 87 */     if (DBManager.delete("delete_Calendar_ids", mp)) {
/* 88 */       PublicTableDAO.insertSettingLogs("删除", "节假日", (String)mp.get("ca_id"), stl);
/* 89 */       return true;
/*    */     }
/* 91 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.calendar.CalendarDAO
 * JD-Core Version:    0.6.2
 */