/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class CalendarUtil
/*     */ {
/*     */   public static GregorianCalendar[] getBetweenDate(String d1, String d2)
/*     */     throws ParseException
/*     */   {
/*  18 */     Vector v = new Vector();
/*  19 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  20 */     GregorianCalendar gc1 = new GregorianCalendar(); GregorianCalendar gc2 = new GregorianCalendar();
/*  21 */     gc1.setTime(sdf.parse(d1));
/*  22 */     gc2.setTime(sdf.parse(d2));
/*     */     do {
/*  24 */       GregorianCalendar gc3 = (GregorianCalendar)gc1.clone();
/*  25 */       v.add(gc3);
/*  26 */       gc1.add(5, 1);
/*     */     }
/*  23 */     while (!
/*  27 */       gc1.after(gc2));
/*  28 */     return (GregorianCalendar[])v.toArray(new GregorianCalendar[v.size()]);
/*     */   }
/*     */ 
/*     */   public static int getWorkDaysInWeekend(Date date_start, Date date_end, List<String> list)
/*     */   {
/*     */     try
/*     */     {
/*  36 */       int days = 0;
/*  37 */       for (String time : list) {
/*  38 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  39 */         Date dateTime = sdf.parse(time);
/*     */ 
/*  41 */         if ((isWeekDay(dateTime)) && 
/*  42 */           (dateCompare(date_start, date_end, dateTime))) {
/*  43 */           days++;
/*     */         }
/*     */       }
/*     */ 
/*  47 */       return days;
/*     */     } catch (Exception e) {
/*     */     }
/*  50 */     return 0;
/*     */   }
/*     */ 
/*     */   public static int getVacationDays(Date date_start, Date date_end, List<String> list)
/*     */   {
/*     */     try
/*     */     {
/*  59 */       int days = 0;
/*  60 */       for (String time : list) {
/*  61 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  62 */         Date dateTime = sdf.parse(time);
/*     */ 
/*  64 */         if ((!isWeekDay(dateTime)) && 
/*  65 */           (dateCompare(date_start, date_end, dateTime))) {
/*  66 */           days++;
/*     */         }
/*     */       }
/*     */ 
/*  70 */       return days;
/*     */     } catch (Exception e) {
/*     */     }
/*  73 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean isWeekDay(Date dateTime)
/*     */   {
/*     */     try
/*     */     {
/*  81 */       GregorianCalendar c = new GregorianCalendar();
/*  82 */       c.setTime(dateTime);
/*  83 */       int ee = c.get(7) - 1;
/*     */ 
/*  85 */       if ((ee == 0) || (ee == 6)) {
/*  86 */         return true;
/*     */       }
/*  88 */       return false;
/*     */     } catch (Exception e) {
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean dateCompare(Date date_start, Date date_end, Date dateTime)
/*     */   {
/*  97 */     boolean dateComPareFlag = false;
/*  98 */     if ((date_start.compareTo(dateTime) == -1) && 
/*  99 */       (dateTime.compareTo(date_end) == -1)) {
/* 100 */       dateComPareFlag = true;
/*     */     }
/* 102 */     return dateComPareFlag;
/*     */   }
/*     */ 
/*     */   public int getDaysBetween(Calendar d1, Calendar d2)
/*     */   {
/* 107 */     if (d1.after(d2))
/*     */     {
/* 109 */       Calendar swap = d1;
/* 110 */       d1 = d2;
/* 111 */       d2 = swap;
/*     */     }
/* 113 */     int days = d2.get(6) - 
/* 114 */       d1.get(6);
/* 115 */     int y2 = d2.get(1);
/* 116 */     if (d1.get(1) != y2) {
/* 117 */       d1 = (Calendar)d1.clone();
/*     */       do {
/* 119 */         days += d1.getActualMaximum(6);
/* 120 */         d1.add(1, 1);
/* 121 */       }while (d1.get(1) != y2);
/*     */     }
/* 123 */     return days;
/*     */   }
/*     */ 
/*     */   public int getWorkingDay(Calendar d1, Calendar d2)
/*     */   {
/* 134 */     int result = -1;
/* 135 */     if (d1.after(d2))
/*     */     {
/* 137 */       Calendar swap = d1;
/* 138 */       d1 = d2;
/* 139 */       d2 = swap;
/*     */     }
/* 141 */     int betweendays = getDaysBetween(d1, d2);
/* 142 */     int charge_date = 0;
/* 143 */     int charge_start_date = 0;
/*     */ 
/* 145 */     int charge_end_date = 0;
/*     */ 
/* 150 */     int stmp = 7 - d1.get(7);
/* 151 */     int etmp = 7 - d2.get(7);
/* 152 */     if ((stmp != 0) && (stmp != 6))
/*     */     {
/* 154 */       charge_start_date = stmp - 1;
/*     */     }
/* 156 */     if ((etmp != 0) && (etmp != 6))
/*     */     {
/* 158 */       charge_end_date = etmp - 1;
/*     */     }
/*     */ 
/* 161 */     result = getDaysBetween(getNextMonday(d1), getNextMonday(d2)) / 7 * 
/* 162 */       5 + charge_start_date - charge_end_date;
/*     */ 
/* 166 */     return result;
/*     */   }
/*     */ 
/*     */   public String getChineseWeek(Calendar date) {
/* 170 */     String[] dayNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", 
/* 171 */       "星期六" };
/* 172 */     int dayOfWeek = date.get(7);
/*     */ 
/* 174 */     return dayNames[(dayOfWeek - 1)];
/*     */   }
/*     */ 
/*     */   public Calendar getNextMonday(Calendar date)
/*     */   {
/* 184 */     Calendar result = null;
/* 185 */     result = date;
/*     */     do {
/* 187 */       result = (Calendar)result.clone();
/* 188 */       result.add(5, 1);
/* 189 */     }while (result.get(7) != 2);
/* 190 */     return result;
/*     */   }
/*     */ 
/*     */   public int getHolidays(Calendar d1, Calendar d2)
/*     */   {
/* 201 */     return getDaysBetween(d1, d2) - getWorkingDay(d1, d2);
/*     */   }
/*     */ 
/*     */   public static int getHolidayDays(String start_time, String now_time, String holidays_start_time, String holidays_end_time)
/*     */   {
/*     */     try
/*     */     {
/* 209 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 210 */       Date date_start = null;
/* 211 */       Date date_end = null;
/* 212 */       date_start = sdf.parse(start_time);
/* 213 */       date_end = sdf.parse(now_time);
/*     */ 
/* 215 */       GregorianCalendar[] ga = getBetweenDate(holidays_start_time, holidays_end_time);
/* 216 */       List list = new ArrayList();
/* 217 */       for (GregorianCalendar e : ga)
/*     */       {
/* 219 */         list.add(e.get(1) + "-" + (e.get(2) + 1) + "-" + e.get(5));
/*     */       }
/*     */ 
/* 222 */       return getVacationDays(date_start, date_end, list);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 226 */       e.printStackTrace();
/* 227 */     }return 0;
/*     */   }
/*     */ 
/*     */   public static int getWorkDaysInHolidayDays(String start_time, String now_time, String work_start_time, String work_end_time)
/*     */   {
/*     */     try
/*     */     {
/* 236 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 237 */       Date date_start = null;
/* 238 */       Date date_end = null;
/* 239 */       date_start = sdf.parse(start_time);
/* 240 */       date_end = sdf.parse(now_time);
/*     */ 
/* 242 */       GregorianCalendar[] ga = getBetweenDate(work_start_time, work_end_time);
/* 243 */       List list = new ArrayList();
/* 244 */       for (GregorianCalendar e : ga)
/*     */       {
/* 246 */         list.add(e.get(1) + "-" + (e.get(2) + 1) + "-" + e.get(5));
/*     */       }
/*     */ 
/* 249 */       return getWorkDaysInWeekend(date_start, date_end, list);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 253 */       e.printStackTrace();
/* 254 */     }return 0;
/*     */   }
/*     */ 
/*     */   public static int getWorkDays(String start_time, String now_time, List<Map<String, String>> holidays_list, List<Map<String, String>> work_list)
/*     */   {
/*     */     try
/*     */     {
/* 261 */       int holidays_days = 0;
/* 262 */       for (Map map : holidays_list) {
/* 263 */         holidays_days += getHolidayDays(start_time, now_time, (String)map.get("start_time"), (String)map.get("end_time"));
/*     */       }
/*     */ 
/* 266 */       int work_days = 0;
/* 267 */       for (Map map : work_list) {
/* 268 */         work_days += getWorkDaysInHolidayDays(start_time, now_time, (String)map.get("start_time"), (String)map.get("end_time"));
/*     */       }
/*     */ 
/* 272 */       int workDaysAll = 0;
/* 273 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 274 */       Date date_start = null;
/* 275 */       Date date_end = null;
/* 276 */       date_start = sdf.parse(start_time);
/* 277 */       date_end = sdf.parse(now_time);
/* 278 */       Calendar cal_start = Calendar.getInstance();
/* 279 */       Calendar cal_end = Calendar.getInstance();
/* 280 */       cal_start.setTime(date_start);
/* 281 */       cal_end.setTime(date_end);
/* 282 */       CalendarUtil calendarUtil = new CalendarUtil();
/* 283 */       workDaysAll = calendarUtil.getWorkingDay(cal_start, cal_end);
/*     */ 
/* 289 */       if ((cal_end.get(7) != 1) && (cal_end.get(7) != 7)) {
/* 290 */         return workDaysAll - 1 - holidays_days + work_days;
/*     */       }
/* 292 */       return workDaysAll - holidays_days + work_days;
/*     */     } catch (Exception e) {
/* 294 */       e.printStackTrace();
/* 295 */     }return 0;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 303 */     GregorianCalendar[] ga = getBetweenDate("2010-03-25", "2010-03-26");
/* 304 */     for (GregorianCalendar e : ga)
/*     */     {
/* 306 */       System.out.println(e.get(1) + "-" + (
/* 307 */         e.get(2) + 1) + "-" + 
/* 308 */         e.get(5));
/*     */     }
/*     */ 
/* 313 */     String strDateStart = "2010-03-31";
/* 314 */     String strDateEnd = "2010-05-03";
/* 315 */     int workDaysAll = 0;
/* 316 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 317 */     Date date_start = null;
/* 318 */     Date date_end = null;
/*     */ 
/* 320 */     date_start = sdf.parse(strDateStart);
/* 321 */     date_end = sdf.parse(strDateEnd);
/*     */ 
/* 324 */     Calendar cal_start = Calendar.getInstance();
/* 325 */     Calendar cal_end = Calendar.getInstance();
/* 326 */     cal_start.setTime(date_start);
/* 327 */     cal_end.setTime(date_end);
/* 328 */     CalendarUtil calendarUtil = new CalendarUtil();
/* 329 */     workDaysAll = calendarUtil.getWorkingDay(cal_start, cal_end);
/*     */ 
/* 332 */     String[] time = { "2010-04-03", "2010-05-02" };
/* 333 */     List list = Arrays.asList(time);
/* 334 */     int days = getVacationDays(date_start, date_end, list);
/* 335 */     System.out.println("法定假日的日期(在周一到周五之间休息的时间)-->" + days);
/*     */ 
/* 337 */     String[] time_2 = { "2010-04-10", "2010-05-03" };
/* 338 */     List list_2 = Arrays.asList(time_2);
/* 339 */     int days_2 = getWorkDaysInWeekend(date_start, date_end, list_2);
/* 340 */     System.out.println("法定假日的日期(在周六和周天上班的时间)-->" + days_2);
/*     */ 
/* 342 */     int workDays = workDaysAll - 1 - days + days_2;
/* 343 */     System.out.println(strDateStart + "到" + strDateEnd + "之间不包含（" + 
/* 344 */       strDateStart + "和" + strDateEnd + 
/* 345 */       "） 减去 法定假日的日期(在周一到周五之间休息的时间) 加上 法定假日的日期(在周六和周天上班的时间)-->" + workDays);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.CalendarUtil
 * JD-Core Version:    0.6.2
 */