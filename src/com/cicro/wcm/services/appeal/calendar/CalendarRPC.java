/*     */ package com.cicro.wcm.services.appeal.calendar;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.calendar.CalendarBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CalendarRPC
/*     */ {
/*     */   public static List<CalendarBean> getCalendarList()
/*     */   {
/*  36 */     return CalendarManager.getAllCalendarList();
/*     */   }
/*     */ 
/*     */   public static String getCalendarCount()
/*     */   {
/*  46 */     return CalendarManager.getCalendarCount();
/*     */   }
/*     */ 
/*     */   public static CalendarBean getCalendarBean(int ca_id)
/*     */   {
/*  55 */     return CalendarManager.getCalendarBean(ca_id);
/*     */   }
/*     */ 
/*     */   public static int getInsertID()
/*     */   {
/*  64 */     return CalendarManager.getInsertID();
/*     */   }
/*     */ 
/*     */   public static boolean insertCalendar(CalendarBean bean, HttpServletRequest request)
/*     */   {
/*  75 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  76 */     if (stl != null)
/*     */     {
/*  78 */       return CalendarManager.insertCalendar(bean, stl);
/*     */     }
/*     */ 
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCalendar(CalendarBean bean, HttpServletRequest request)
/*     */   {
/*  93 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  94 */     if (stl != null)
/*     */     {
/*  96 */       return CalendarManager.updateCalendar(bean, stl);
/*     */     }
/*     */ 
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCalendar(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 111 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 112 */     if (stl != null)
/*     */     {
/* 114 */       return CalendarManager.deleteCalendar(mp, stl);
/*     */     }
/*     */ 
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.calendar.CalendarRPC
 * JD-Core Version:    0.6.2
 */