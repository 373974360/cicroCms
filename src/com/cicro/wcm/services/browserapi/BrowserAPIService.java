/*     */ package com.cicro.wcm.services.browserapi;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.count.CmsCountBean;
/*     */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*     */ import com.cicro.wcm.services.cms.count.SiteCountManager;
/*     */ import com.cicro.wcm.services.cms.count.SiteCountRPC;
/*     */ import com.cicro.wcm.services.cms.countsource.CmsCountSourceRPC;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BrowserAPIService
/*     */ {
/*     */   public static List<SiteCountBean> getSiteCountListByMap(Map map)
/*     */   {
/*  37 */     List listResult = new ArrayList();
/*     */     try
/*     */     {
/*  40 */       Map mapt = map;
/*  41 */       String start_day = (String)map.get("start_day");
/*  42 */       if ((start_day != null) && (!"".equals(start_day))) {
/*  43 */         if (!start_day.contains(":")) {
/*  44 */           start_day = start_day + " 00:00:01";
/*     */         }
/*  46 */         mapt.put("start_day", start_day);
/*     */       } else {
/*  48 */         mapt.put("start_day", "2000-01-01 00:00:01");
/*     */       }
/*     */ 
/*  51 */       String end_day = (String)map.get("end_day");
/*  52 */       if ((end_day != null) && (!"".equals(end_day))) {
/*  53 */         if (!end_day.contains(":")) {
/*  54 */           end_day = start_day + " 23:59:59";
/*     */         }
/*  56 */         mapt.put("end_day", end_day);
/*     */       } else {
/*  58 */         mapt.put("end_day", DateUtil.getCurrentDateTime());
/*     */       }
/*  60 */       String atype = (String)map.get("atype");
/*  61 */       if (atype == null) {
/*  62 */         atype = "";
/*     */       }
/*  64 */       if ("lastmonth".equals(atype))
/*     */       {
/*  66 */         mapt.put("start_day", getFirstDayByLastMonth());
/*  67 */         mapt.put("end_day", getLastDayByLastMonth());
/*  68 */       } else if ("currmonth".equals(atype)) {
/*  69 */         mapt.put("start_day", getFirstDayByCurrMonth());
/*  70 */         mapt.put("end_day", getLastDayByCurrMonth());
/*     */       }
/*     */ 
/*  74 */       List list = SiteCountManager.getSiteCountListBySite(map);
/*  75 */       String numString = (String)map.get("num");
/*  76 */       if (numString == null) {
/*  77 */         numString = "0";
/*     */       }
/*  79 */       int num = Integer.parseInt(numString);
/*     */ 
/*  82 */       SiteCountComparator countComparator = new SiteCountComparator();
/*  83 */       Collections.sort(list, countComparator);
/*     */ 
/*  85 */       for (int i = 0; i < list.size(); i++)
/*  86 */         if ((num != 0) && (i < num))
/*  87 */           listResult.add((SiteCountBean)list.get(i));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  91 */       e.printStackTrace();
/*     */     } finally {
/*  93 */       return listResult;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getDeptNameListByMap(Map map)
/*     */   {
/* 114 */     List listResult = new ArrayList();
/*     */     try {
/* 116 */       Map mapt = new HashMap();
/* 117 */       String site_ids = (String)map.get("site_ids");
/* 118 */       if ((site_ids != null) && (!"".equals(site_ids))) {
/* 119 */         mapt.put("site_ids", site_ids);
/*     */       }
/* 121 */       String cate_ids = (String)map.get("cate_ids");
/* 122 */       if ((cate_ids != null) && (!"".equals(cate_ids))) {
/* 123 */         mapt.put("cate_ids", cate_ids);
/*     */       }
/* 125 */       String start_day = (String)map.get("start_day");
/* 126 */       if ((start_day != null) && (!"".equals(start_day))) {
/* 127 */         if (!start_day.contains(":")) {
/* 128 */           start_day = start_day + " 00:00:01";
/*     */         }
/* 130 */         mapt.put("start_day", start_day);
/*     */       } else {
/* 132 */         mapt.put("start_day", "2000-01-01 00:00:01");
/*     */       }
/*     */ 
/* 135 */       String end_day = (String)map.get("end_day");
/* 136 */       if ((end_day != null) && (!"".equals(end_day))) {
/* 137 */         if (!end_day.contains(":")) {
/* 138 */           end_day = start_day + " 23:59:59";
/*     */         }
/* 140 */         mapt.put("end_day", end_day);
/*     */       } else {
/* 142 */         mapt.put("end_day", DateUtil.getCurrentDateTime());
/*     */       }
/* 144 */       String atype = (String)map.get("atype");
/* 145 */       if (atype == null) {
/* 146 */         atype = "";
/*     */       }
/* 148 */       if ("lastmonth".equals(atype))
/*     */       {
/* 150 */         mapt.put("start_day", getFirstDayByLastMonth());
/* 151 */         mapt.put("end_day", getLastDayByLastMonth());
/* 152 */       } else if ("currmonth".equals(atype)) {
/* 153 */         mapt.put("start_day", getFirstDayByCurrMonth());
/* 154 */         mapt.put("end_day", getLastDayByCurrMonth());
/*     */       }
/*     */ 
/* 158 */       List list = SiteCountRPC.getSiteCountListByInput(mapt);
/* 159 */       System.out.println(" list --- > " + list.size());
/*     */ 
/* 161 */       List listTemp = new ArrayList();
/* 162 */       if (list.size() != 0) {
/* 163 */         for (int i = 0; i < list.size(); i++) {
/* 164 */           setTreeNode((SiteCountBean)list.get(i), "", listTemp);
/*     */         }
/*     */       }
/* 167 */       System.out.println(" listTemp --- > " + listTemp.size());
/*     */ 
/* 169 */       String numString = (String)map.get("num");
/* 170 */       if (numString == null) {
/* 171 */         numString = "0";
/*     */       }
/* 173 */       int num = Integer.parseInt(numString);
/*     */ 
/* 176 */       com.cicro.wcm.services.cms.count.SiteCountComparator countComparator = new com.cicro.wcm.services.cms.count.SiteCountComparator();
/* 177 */       Collections.sort(listTemp, countComparator);
/*     */ 
/* 179 */       for (int i = 0; i < listTemp.size(); i++)
/* 180 */         if ((num != 0) && (i < num))
/* 181 */           listResult.add((SiteCountBean)listTemp.get(i));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 185 */       e.printStackTrace();
/*     */     } finally {
/* 187 */       return listResult;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setTreeNode(SiteCountBean bean, String parent_id, List<SiteCountBean> list)
/*     */   {
/*     */     try {
/* 194 */       if (bean.isIs_leaf())
/*     */       {
/* 196 */         list.add(bean);
/*     */       }
/*     */       else
/*     */       {
/* 200 */         List child_list = bean.getChild_list();
/* 201 */         if (child_list.size() > 0)
/*     */         {
/* 203 */           for (int i = 0; i < child_list.size(); i++)
/*     */           {
/* 205 */             setTreeNode((SiteCountBean)child_list.get(i), bean.getDept_id(), list);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 210 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<CmsCountBean> getSourceNameListByMap(Map map)
/*     */   {
/* 231 */     List listResult = new ArrayList();
/*     */     try {
/* 233 */       Map mapt = new HashMap();
/* 234 */       String site_ids = (String)map.get("site_ids");
/* 235 */       if ((site_ids != null) && (!"".equals(site_ids))) {
/* 236 */         mapt.put("site_ids", site_ids);
/*     */       }
/* 238 */       String cate_ids = (String)map.get("cate_ids");
/* 239 */       if ((cate_ids != null) && (!"".equals(cate_ids))) {
/* 240 */         mapt.put("cate_ids", cate_ids);
/*     */       }
/* 242 */       String start_day = (String)map.get("start_day");
/* 243 */       if ((start_day != null) && (!"".equals(start_day))) {
/* 244 */         if (!start_day.contains(":")) {
/* 245 */           start_day = start_day + " 00:00:01";
/*     */         }
/* 247 */         mapt.put("start_day", start_day);
/*     */       } else {
/* 249 */         mapt.put("start_day", "2000-01-01 00:00:01");
/*     */       }
/*     */ 
/* 252 */       String end_day = (String)map.get("end_day");
/* 253 */       if ((end_day != null) && (!"".equals(end_day))) {
/* 254 */         if (!end_day.contains(":")) {
/* 255 */           end_day = start_day + " 23:59:59";
/*     */         }
/* 257 */         mapt.put("end_day", end_day);
/*     */       } else {
/* 259 */         mapt.put("end_day", DateUtil.getCurrentDateTime());
/*     */       }
/* 261 */       String atype = (String)map.get("atype");
/* 262 */       if (atype == null) {
/* 263 */         atype = "";
/*     */       }
/* 265 */       if ("lastmonth".equals(atype))
/*     */       {
/* 267 */         mapt.put("start_day", getFirstDayByLastMonth());
/* 268 */         mapt.put("end_day", getLastDayByLastMonth());
/* 269 */       } else if ("currmonth".equals(atype)) {
/* 270 */         mapt.put("start_day", getFirstDayByCurrMonth());
/* 271 */         mapt.put("end_day", getLastDayByCurrMonth());
/*     */       }
/*     */ 
/* 275 */       List listTemp = CmsCountSourceRPC.getInfoCountListBySource(mapt);
/* 276 */       System.out.println(" list --- > " + listTemp.size());
/*     */ 
/* 278 */       String numString = (String)map.get("num");
/* 279 */       if (numString == null) {
/* 280 */         numString = "0";
/*     */       }
/* 282 */       int num = Integer.parseInt(numString);
/*     */ 
/* 288 */       for (int i = 0; i < listTemp.size(); i++)
/* 289 */         if ((num != 0) && (i < num))
/* 290 */           listResult.add((CmsCountBean)listTemp.get(i));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 294 */       e.printStackTrace();
/*     */     } finally {
/* 296 */       return listResult;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getFirstDayByCurrMonth()
/*     */   {
/* 303 */     return (String)findLastMonth(0).get("prevMonthFD");
/*     */   }
/*     */ 
/*     */   public static String getLastDayByCurrMonth()
/*     */   {
/* 308 */     return (String)findLastMonth(0).get("prevMonthPD");
/*     */   }
/*     */ 
/*     */   public static String getFirstDayByLastMonth()
/*     */   {
/* 314 */     return (String)findLastMonth(-1).get("prevMonthFD");
/*     */   }
/*     */ 
/*     */   public static String getLastDayByLastMonth()
/*     */   {
/* 319 */     return (String)findLastMonth(-1).get("prevMonthPD");
/*     */   }
/*     */ 
/*     */   private static Map<String, String> findLastMonth(int num)
/*     */   {
/* 328 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/* 330 */     Calendar cal = Calendar.getInstance();
/* 331 */     GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
/* 332 */     Calendar calendar = Calendar.getInstance();
/* 333 */     calendar.setTime(new Date());
/*     */ 
/* 335 */     calendar.add(2, num);
/* 336 */     Date theDate = calendar.getTime();
/* 337 */     gcLast.setTime(theDate);
/* 338 */     gcLast.set(5, 1);
/* 339 */     String day_first_prevM = df.format(gcLast.getTime());
/* 340 */     StringBuffer str = new StringBuffer().append(day_first_prevM).append(
/* 341 */       " 00:00:00");
/* 342 */     day_first_prevM = str.toString();
/*     */ 
/* 344 */     calendar.add(2, 1);
/* 345 */     calendar.set(5, 1);
/* 346 */     calendar.add(5, -1);
/* 347 */     String day_end_prevM = df.format(calendar.getTime());
/* 348 */     StringBuffer endStr = new StringBuffer().append(day_end_prevM).append(
/* 349 */       " 23:59:59");
/* 350 */     day_end_prevM = endStr.toString();
/*     */ 
/* 352 */     Map map = new HashMap();
/* 353 */     map.put("prevMonthFD", day_first_prevM);
/* 354 */     map.put("prevMonthPD", day_end_prevM);
/* 355 */     return map;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.browserapi.BrowserAPIService
 * JD-Core Version:    0.6.2
 */