/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class DateUtil
/*     */ {
/*     */   private static final String DATE_PATTERN = "yyyy-MM-dd";
/*     */   private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
/*     */ 
/*     */   public static String getCurrentDateTime()
/*     */   {
/*  40 */     return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate()
/*     */   {
/*  49 */     return getCurrentDateTime("yyyy-MM-dd");
/*     */   }
/*     */ 
/*     */   public static String getCurrentDateTime(String pattern)
/*     */   {
/*  58 */     Calendar cal = Calendar.getInstance();
/*  59 */     SimpleDateFormat sdf = new SimpleDateFormat(pattern);
/*  60 */     return sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static Date getDate(String dateStr)
/*     */     throws ParseException
/*     */   {
/*  71 */     return getDate(dateStr, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static Date getDate(String dateStr, String pattern)
/*     */     throws ParseException
/*     */   {
/*  83 */     Date date = null;
/*  84 */     SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
/*  85 */     date = dateFormat.parse(dateStr);
/*     */ 
/*  87 */     return date;
/*     */   }
/*     */ 
/*     */   public static String getDateString(Date date)
/*     */   {
/*  96 */     return getString(date, "yyyy-MM-dd");
/*     */   }
/*     */ 
/*     */   public static String getDateTimeString(Date date)
/*     */   {
/* 105 */     return getString(date, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static String getString(Date date, String pattern)
/*     */   {
/* 115 */     SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
/*     */ 
/* 117 */     return dateFormat.format(date);
/*     */   }
/*     */ 
/*     */   public static String formatToDateString(String dateStr)
/*     */     throws ParseException
/*     */   {
/* 127 */     return formatToString(dateStr, "yyyy-MM-dd");
/*     */   }
/*     */ 
/*     */   public static String formatToDateTimeString(String dateTimeStr)
/*     */     throws ParseException
/*     */   {
/* 137 */     return formatToString(dateTimeStr, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static String formatToString(String dateStr, String pattern)
/*     */     throws ParseException
/*     */   {
/* 148 */     dateStr = format(dateStr);
/* 149 */     Date date = null;
/* 150 */     if (checkDateString(dateStr)) {
/* 151 */       date = getDate(dateStr, "yyyy-MM-dd");
/* 152 */       return getString(date, pattern);
/*     */     }
/* 154 */     if (checkDateTimeString(dateStr)) {
/* 155 */       date = getDate(dateStr);
/* 156 */       return getString(date, pattern);
/*     */     }
/*     */ 
/* 159 */     throw new ParseException("日期格式不正确", 1);
/*     */   }
/*     */ 
/*     */   public static boolean checkDateString(String dateStr)
/*     */   {
/* 169 */     Pattern pattern = Pattern.compile("\\d{2,4}-\\d{1,2}-\\d{1,2}");
/* 170 */     Matcher matcher = pattern.matcher(dateStr);
/*     */ 
/* 172 */     return matcher.matches();
/*     */   }
/*     */ 
/*     */   public static boolean checkDateTimeString(String dateTimeStr)
/*     */   {
/* 181 */     Pattern pattern = Pattern.compile(
/* 182 */       "\\d{2,4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");
/* 183 */     Matcher matcher = pattern.matcher(dateTimeStr);
/*     */ 
/* 185 */     return matcher.matches();
/*     */   }
/*     */ 
/*     */   public static String format(String dateStr)
/*     */   {
/* 194 */     Pattern pattern = Pattern.compile(
/* 195 */       "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.*");
/* 196 */     Matcher matcher = pattern.matcher(dateStr);
/* 197 */     if (matcher.matches()) {
/* 198 */       dateStr = dateStr.substring(0, 19);
/*     */     }
/*     */     else {
/* 201 */       pattern = Pattern.compile(
/* 202 */         "\\d{4}-\\d{2}-\\d{2}.*");
/* 203 */       matcher = pattern.matcher(dateStr);
/* 204 */       if (matcher.matches()) {
/* 205 */         dateStr = dateStr.substring(0, 10);
/*     */       }
/*     */     }
/*     */ 
/* 209 */     return dateStr;
/*     */   }
/*     */ 
/*     */   public static int getYear(Date date)
/*     */   {
/* 220 */     Calendar calendar = Calendar.getInstance();
/* 221 */     calendar.setTime(date);
/* 222 */     int year = calendar.get(1);
/* 223 */     return year;
/*     */   }
/*     */ 
/*     */   public static int getMonth(Date date)
/*     */   {
/* 234 */     Calendar calendar = Calendar.getInstance();
/* 235 */     calendar.setTime(date);
/* 236 */     int month = calendar.get(2);
/* 237 */     return month;
/*     */   }
/*     */ 
/*     */   public static int getDayOfMonth(Date date)
/*     */   {
/* 248 */     Calendar calendar = Calendar.getInstance();
/* 249 */     calendar.setTime(date);
/* 250 */     int day = calendar.get(5);
/* 251 */     return day;
/*     */   }
/*     */ 
/*     */   public static int getDayOfWek(Date date)
/*     */   {
/* 262 */     Calendar calendar = Calendar.getInstance();
/* 263 */     calendar.setTime(date);
/* 264 */     int day = calendar.get(7);
/* 265 */     return day;
/*     */   }
/*     */ 
/*     */   public static int getHour(Date date)
/*     */   {
/* 276 */     Calendar calendar = Calendar.getInstance();
/* 277 */     calendar.setTime(date);
/* 278 */     int hour = calendar.get(11);
/* 279 */     return hour;
/*     */   }
/*     */ 
/*     */   public static int getMinute(Date date)
/*     */   {
/* 290 */     Calendar calendar = Calendar.getInstance();
/* 291 */     calendar.setTime(date);
/* 292 */     int minute = calendar.get(12);
/* 293 */     return minute;
/*     */   }
/*     */ 
/*     */   public static int getSecond(Date date)
/*     */   {
/* 304 */     Calendar calendar = Calendar.getInstance();
/* 305 */     calendar.setTime(date);
/* 306 */     int second = calendar.get(13);
/* 307 */     return second;
/*     */   }
/*     */ 
/*     */   public static Date getStartOfDay(Date date)
/*     */   {
/* 316 */     Date startDate = null;
/*     */     try {
/* 318 */       startDate = getDate(getString(date, "yyyy-MM-dd"), 
/* 319 */         "yyyy-MM-dd");
/*     */     } catch (Exception e) {
/* 321 */       return null;
/*     */     }
/* 323 */     return startDate;
/*     */   }
/*     */ 
/*     */   public static Date getEndOfDay(Date date)
/*     */   {
/* 332 */     Calendar calendar = Calendar.getInstance();
/* 333 */     Date endDate = null;
/*     */     try {
/* 335 */       calendar.set(getYear(date), getMonth(date), 
/* 336 */         getDayOfMonth(date), 23, 59, 59);
/* 337 */       endDate = calendar.getTime();
/*     */     } catch (Exception e) {
/* 339 */       return null;
/*     */     }
/* 341 */     return endDate;
/*     */   }
/*     */ 
/*     */   public static Date getStartOfWeek(Date date)
/*     */   {
/* 350 */     Calendar calendar = Calendar.getInstance();
/* 351 */     calendar.setTime(date);
/* 352 */     int n = 0;
/* 353 */     int day = calendar.get(7);
/* 354 */     switch (day) {
/*     */     case 2:
/* 356 */       n = 0;
/* 357 */       break;
/*     */     case 3:
/* 360 */       n = 1;
/* 361 */       break;
/*     */     case 4:
/* 364 */       n = 2;
/* 365 */       break;
/*     */     case 5:
/* 368 */       n = 3;
/* 369 */       break;
/*     */     case 6:
/* 372 */       n = 4;
/* 373 */       break;
/*     */     case 7:
/* 376 */       n = 5;
/* 377 */       break;
/*     */     case 1:
/* 380 */       n = 6;
/*     */     }
/*     */ 
/* 384 */     Date monday = new Date(date.getTime() - 86400000 * n);
/* 385 */     Date startDate = getStartOfDay(monday);
/* 386 */     return startDate;
/*     */   }
/*     */ 
/*     */   public static Date getEndOfWeek(Date date)
/*     */   {
/* 395 */     Calendar calendar = Calendar.getInstance();
/* 396 */     calendar.setTime(date);
/* 397 */     int n = 0;
/* 398 */     int day = calendar.get(7);
/* 399 */     switch (day) {
/*     */     case 2:
/* 401 */       n = 6;
/* 402 */       break;
/*     */     case 3:
/* 405 */       n = 5;
/* 406 */       break;
/*     */     case 4:
/* 409 */       n = 4;
/* 410 */       break;
/*     */     case 5:
/* 413 */       n = 3;
/* 414 */       break;
/*     */     case 6:
/* 417 */       n = 2;
/* 418 */       break;
/*     */     case 7:
/* 421 */       n = 1;
/* 422 */       break;
/*     */     case 1:
/* 425 */       n = 0;
/*     */     }
/*     */ 
/* 429 */     Date sunday = new Date(date.getTime() + 86400000 * n);
/* 430 */     Date startDate = getEndOfDay(sunday);
/* 431 */     return startDate;
/*     */   }
/*     */ 
/*     */   public static long daysOf2Day(String day1, String day2)
/*     */   {
/*     */     try
/*     */     {
/* 442 */       day1 = day1 + " 00:00:00";
/* 443 */       day2 = day2 + " 00:00:00";
/* 444 */       long secs = secsOf2Day(day1, day2);
/* 445 */       return secs / 86400L; } catch (Exception e) {
/*     */     }
/* 447 */     return -1L;
/*     */   }
/*     */ 
/*     */   public static long secsOf2Day(String day1, String day2)
/*     */   {
/*     */     try
/*     */     {
/* 459 */       Date date1 = getDate(day1);
/* 460 */       Date date2 = getDate(day2);
/* 461 */       return Math.abs(date1.getTime() - date2.getTime()) / 1000L;
/*     */     } catch (Exception e) {
/*     */     }
/* 464 */     return -1L;
/*     */   }
/*     */ 
/*     */   public static String getDaysToNow(String strDateTime)
/*     */   {
/*     */     try
/*     */     {
/* 475 */       StringTokenizer strToken = new StringTokenizer(strDateTime, " ");
/* 476 */       StringTokenizer strTokenDate = new StringTokenizer(strToken.nextToken(), "-");
/* 477 */       StringTokenizer strTokenTime = new StringTokenizer(strToken.nextToken(), ":");
/* 478 */       int intYear = Integer.parseInt(strTokenDate.nextToken());
/* 479 */       int intMonth = Integer.parseInt(strTokenDate.nextToken()) - 1;
/* 480 */       int intDay = Integer.parseInt(strTokenDate.nextToken());
/* 481 */       int intHour = Integer.parseInt(strTokenTime.nextToken());
/* 482 */       int intMin = Integer.parseInt(strTokenTime.nextToken());
/* 483 */       int intSec = Integer.parseInt(strTokenTime.nextToken());
/*     */ 
/* 485 */       Calendar cal = Calendar.getInstance();
/* 486 */       cal.set(intYear, intMonth, intDay, intHour, intMin, intSec);
/* 487 */       long longDays = (new Date().getTime() - cal.getTimeInMillis()) / 24L / 60L / 60L / 1000L;
/* 488 */       return longDays == 0L ? 1L : longDays;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 492 */     return "0";
/*     */   }
/*     */ 
/*     */   public static long compareDatetime(String date1, String date2)
/*     */   {
/* 506 */     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 507 */     long timestamp1 = -1L;
/* 508 */     long timestamp2 = -1L;
/*     */     try
/*     */     {
/* 511 */       timestamp1 = df.parse(date1).getTime() / 1000L;
/* 512 */       timestamp2 = df.parse(date2).getTime() / 1000L;
/*     */     } catch (ParseException e) {
/* 514 */       System.out.println("时间格式 [ " + date1 + " ] 或 [ " + date2 + 
/* 515 */         " ] 无法被解析");
/* 516 */       return -1L;
/*     */     }
/* 518 */     if (timestamp1 > timestamp2) {
/* 519 */       return (timestamp1 - timestamp2) / 3600L;
/*     */     }
/* 521 */     return (timestamp2 - timestamp1) / 3600L;
/*     */   }
/*     */ 
/*     */   public static Date getDateTimesAfter(String datetimes, int day)
/*     */   {
/* 534 */     Calendar now = Calendar.getInstance();
/*     */     try {
/* 536 */       now.setTime(getDate(datetimes, "yyyy-MM-dd HH:mm:ss"));
/*     */     } catch (ParseException e) {
/* 538 */       System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
/* 539 */       return null;
/*     */     }
/* 541 */     now.set(5, now.get(5) + day);
/* 542 */     return now.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getDateAfter(String datetimes, int day)
/*     */   {
/* 555 */     Calendar now = Calendar.getInstance();
/*     */     try {
/* 557 */       now.setTime(getDate(datetimes, "yyyy-MM-dd"));
/*     */     } catch (ParseException e) {
/* 559 */       System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
/* 560 */       return null;
/*     */     }
/* 562 */     now.set(5, now.get(5) + day);
/* 563 */     return now.getTime();
/*     */   }
/*     */ 
/*     */   public static String getDateBefore(String datetimes, int day)
/*     */   {
/* 576 */     Calendar now = Calendar.getInstance();
/*     */     try {
/* 578 */       now.setTime(getDate(datetimes, "yyyy-MM-dd"));
/*     */     } catch (ParseException e) {
/* 580 */       System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
/* 581 */       return null;
/*     */     }
/* 583 */     now.set(5, now.get(5) - day);
/* 584 */     return getString(now.getTime(), "yyyy-MM-dd");
/*     */   }
/*     */ 
/*     */   public static String timestampToDate(long timestamp, String format)
/*     */   {
/* 597 */     Date date = new Timestamp(timestamp);
/* 598 */     DateFormat df = new SimpleDateFormat(format);
/* 599 */     return df.format(date);
/*     */   }
/*     */ 
/*     */   public static long dateToTimestamp()
/*     */   {
/* 607 */     long ts = System.currentTimeMillis();
/* 608 */     return ts;
/*     */   }
/*     */ 
/*     */   public static String getDateTimeAfter(String times, int num)
/*     */   {
/* 619 */     if ((times == null) || ("".equals(times))) {
/* 620 */       times = getCurrentDateTime();
/*     */     }
/* 622 */     long tl = dateToTimestamp(times) + num * 1000;
/* 623 */     return timestampToDate(tl, "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static long dateToTimestamp(String dateFormat)
/*     */   {
/* 634 */     long timestamp = -1L;
/*     */     try {
/* 636 */       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 637 */       Date date = df.parse(dateFormat);
/* 638 */       timestamp = date.getTime();
/*     */     } catch (Exception e) {
/* 640 */       System.out.println("时间格式 [ " + dateFormat + " ] 无法被解析");
/*     */     }
/* 642 */     return timestamp;
/*     */   }
/*     */ 
/*     */   public static long compareDatetime2(String date1, String date2) {
/* 646 */     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
/* 647 */     long timestamp1 = -1L;
/* 648 */     long timestamp2 = -1L;
/*     */     try
/*     */     {
/* 651 */       timestamp1 = df.parse(date1).getTime();
/* 652 */       timestamp2 = df.parse(date2).getTime();
/*     */     } catch (ParseException e) {
/* 654 */       System.out.println("时间格式 [ " + date1 + " ] 或 [ " + date2 + " ] 无法被解析");
/* 655 */       return -1L;
/*     */     }
/* 657 */     if (timestamp1 > timestamp2) {
/* 658 */       return timestamp1 - timestamp2;
/*     */     }
/* 660 */     return timestamp2 - timestamp1;
/*     */   }
/*     */ 
/*     */   public static boolean compare_date(String DATE1, String DATE2)
/*     */   {
/* 666 */     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/* 668 */       Date dt1 = df.parse(DATE1);
/* 669 */       Date dt2 = df.parse(DATE2);
/* 670 */       if (dt1.getTime() > dt2.getTime())
/*     */       {
/* 672 */         return false;
/* 673 */       }if (dt1.getTime() < dt2.getTime())
/*     */       {
/* 675 */         return true;
/*     */       }
/* 677 */       return true;
/*     */     }
/*     */     catch (Exception exception) {
/* 680 */       exception.printStackTrace();
/*     */     }
/* 682 */     return true;
/*     */   }
/*     */ 
/*     */   public static String time_difference(String small, String big)
/*     */   {
/* 687 */     String time = "";
/* 688 */     SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 689 */     long between = 0L;
/*     */     try {
/* 691 */       Date begin = dfs.parse(small);
/* 692 */       Date end = dfs.parse(big);
/* 693 */       between = end.getTime() - begin.getTime();
/*     */     } catch (Exception ex) {
/* 695 */       ex.printStackTrace();
/*     */     }
/* 697 */     long day = between / 86400000L;
/* 698 */     long hour = between / 3600000L - day * 24L;
/* 699 */     long min = between / 60000L - day * 24L * 60L - hour * 60L;
/*     */ 
/* 703 */     time = day * 24L * 60L + hour * 60L + min;
/* 704 */     return time;
/*     */   }
/*     */ 
/*     */   public static String getFormatTimeString(String minits)
/*     */   {
/* 709 */     String time = "";
/* 710 */     int minit = Integer.parseInt(minits);
/* 711 */     long old = minit * 60;
/* 712 */     long hour = old / 3600L;
/* 713 */     long min = old % 3600L / 60L;
/* 714 */     long second = old % 60L;
/* 715 */     StringBuffer s = new StringBuffer();
/*     */ 
/* 717 */     if (hour != 0L) {
/* 718 */       s.append(hour + "小时");
/*     */     }
/* 720 */     if (min != 0L) {
/* 721 */       s.append(min + "分钟");
/*     */     }
/* 723 */     if (second != 0L) {
/* 724 */       s.append(second + "秒");
/*     */     }
/* 726 */     time = s.toString();
/* 727 */     return time;
/*     */   }
/*     */ 
/*     */   public static String getNextDayByTime(String datetimes, int day)
/*     */   {
/* 732 */     Calendar now = Calendar.getInstance();
/*     */     try {
/* 734 */       now.setTime(getDate(datetimes, "yyyy-MM-dd"));
/*     */     } catch (ParseException e) {
/* 736 */       System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
/* 737 */       return null;
/*     */     }
/* 739 */     now.set(5, now.get(5) + day);
/* 740 */     return getString(now.getTime(), "yyyy-MM-dd");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 827 */     String i_time_s = "2011-01-01";
/* 828 */     String i_time_e = "2011-01-01";
/*     */ 
/* 842 */     String ss = "0.9";
/*     */ 
/* 845 */     double tt2 = Double.parseDouble(ss);
/*     */ 
/* 848 */     double s = 49.0D;
/* 849 */     double q = 100.0D;
/*     */ 
/* 851 */     double t = s / q * 100.0D;
/* 852 */     DecimalFormat df = new DecimalFormat("##0.0");
/* 853 */     String ff = df.format(t);
/* 854 */     System.out.println(ff);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.DateUtil
 * JD-Core Version:    0.6.2
 */