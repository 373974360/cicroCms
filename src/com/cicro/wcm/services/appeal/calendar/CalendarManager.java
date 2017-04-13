/*     */ package com.cicro.wcm.services.appeal.calendar;
/*     */ 
/*     */ import com.cicro.util.CalendarUtil;
/*     */ import com.cicro.wcm.bean.appeal.calendar.CalendarBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.calendar.CalendarDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class CalendarManager
/*     */   implements ISyncCatch
/*     */ {
/*  31 */   private static TreeMap<Integer, CalendarBean> cal_map = new TreeMap();
/*     */ 
/*     */   static {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  39 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  44 */     cal_map.clear();
/*  45 */     List lt = CalendarDAO.getAllCalendarList();
/*  46 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  48 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  50 */         cal_map.put(Integer.valueOf(((CalendarBean)lt.get(i)).getCa_id()), (CalendarBean)lt.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadCalendar()
/*     */   {
/*  61 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.calendar.CalendarManager");
/*     */   }
/*     */ 
/*     */   public static List<CalendarBean> getAllCalendarList()
/*     */   {
/*  69 */     List lt = new ArrayList();
/*  70 */     Iterator it = cal_map.values().iterator();
/*  71 */     while (it.hasNext())
/*     */     {
/*  73 */       lt.add((CalendarBean)it.next());
/*     */     }
/*  75 */     return lt;
/*     */   }
/*     */ 
/*     */   public static String getCalendarCount()
/*     */   {
/*  84 */     return CalendarDAO.getCalendarCount();
/*     */   }
/*     */ 
/*     */   public static CalendarBean getCalendarBean(int ca_id)
/*     */   {
/*  93 */     if (cal_map.containsKey(Integer.valueOf(ca_id)))
/*     */     {
/*  95 */       return (CalendarBean)cal_map.get(Integer.valueOf(ca_id));
/*     */     }
/*     */ 
/*  98 */     CalendarBean ob = CalendarDAO.getCalendarBean(ca_id);
/*  99 */     cal_map.put(Integer.valueOf(ca_id), ob);
/* 100 */     return ob;
/*     */   }
/*     */ 
/*     */   public static int getInsertID()
/*     */   {
/* 110 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_CALENDAR_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertCalendar(CalendarBean bean, SettingLogsBean stl)
/*     */   {
/* 118 */     if (CalendarDAO.insertCalendar(bean, stl))
/*     */     {
/* 120 */       reloadCalendar();
/* 121 */       return true;
/*     */     }
/*     */ 
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCalendar(CalendarBean bean, SettingLogsBean stl)
/*     */   {
/* 136 */     if (CalendarDAO.updateCalendar(bean, stl))
/*     */     {
/* 138 */       reloadCalendar();
/* 139 */       return true;
/*     */     }
/*     */ 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCalendar(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 154 */     if (CalendarDAO.deleteCalendar(mp, stl))
/*     */     {
/* 156 */       reloadCalendar();
/* 157 */       return true;
/*     */     }
/*     */ 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getWorkDays(String start_date, String current_date)
/*     */   {
/*     */     try
/*     */     {
/* 173 */       int work_days = 0;
/*     */       List holidays_list;
/*     */       List work_list;
/* 174 */       if (!start_date.equals(current_date))
/*     */       {
/* 176 */         List CalendarBeanList = getAllCalendarList();
/*     */ 
/* 178 */         holidays_list = new ArrayList();
/* 179 */         work_list = new ArrayList();
/* 180 */         for (CalendarBean calendarBean : CalendarBeanList) {
/* 181 */           Map map = new HashMap();
/* 182 */           map.put("start_time", calendarBean.getStart_dtime());
/* 183 */           map.put("end_time", calendarBean.getEnd_dtime());
/* 184 */           if (calendarBean.getCa_flag() == 0)
/* 185 */             holidays_list.add(map);
/* 186 */           else if (calendarBean.getCa_flag() == 1) {
/* 187 */             work_list.add(map);
/*     */           }
/*     */         }
/*     */       }
/* 191 */       return CalendarUtil.getWorkDays(start_date, current_date, holidays_list, work_list);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 196 */       e.printStackTrace();
/* 197 */     }return 0;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 203 */     System.out.println(getWorkDays("2011-04-01", "2011-04-25"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.calendar.CalendarManager
 * JD-Core Version:    0.6.2
 */