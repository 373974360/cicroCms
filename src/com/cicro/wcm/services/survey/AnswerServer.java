/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.survey.SurveyAnswer;
/*     */ import com.cicro.wcm.bean.survey.SurveyAnswerItem;
/*     */ import com.cicro.wcm.dao.survey.AnswerDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class AnswerServer
/*     */ {
/*     */   public static boolean isSubmitTimeout(String s_id, String s_time, String ip)
/*     */   {
/*  30 */     if ((s_time != null) && (!"".equals(s_time)))
/*     */     {
/*  32 */       Map m = new HashMap();
/*  33 */       m.put("ip", ip);
/*  34 */       m.put("s_id", s_id);
/*  35 */       String last_time = AnswerDAO.getLastAnswerTimeByIP(m);
/*     */ 
/*  40 */       if ((last_time != null) && (!"".equals(last_time))) {
/*     */         try
/*     */         {
/*  43 */           return DateUtil.dateToTimestamp() > DateUtil.dateToTimestamp(last_time) + getSpacingIntervalTimes(s_time).longValue();
/*     */         }
/*     */         catch (Exception e) {
/*  46 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   public static Long getSpacingIntervalTimes(String s_time)
/*     */   {
/*  55 */     Long tL = Long.valueOf(0L);
/*  56 */     String type = s_time.substring(0, 1);
/*  57 */     int ti = Integer.parseInt(s_time.substring(1));
/*  58 */     if ("d".equals(type))
/*  59 */       tL = Long.valueOf(ti * 24 * 60 * 60 * 1000L);
/*  60 */     if ("h".equals(type))
/*  61 */       tL = Long.valueOf(ti * 60 * 60 * 1000L);
/*  62 */     if ("m".equals(type)) {
/*  63 */       tL = Long.valueOf(ti * 60 * 1000L);
/*     */     }
/*  65 */     return tL;
/*     */   }
/*     */ 
/*     */   public static String getAnswerCountByIP(String s_id, String ip)
/*     */   {
/*  75 */     Map m = new HashMap();
/*  76 */     m.put("ip", ip);
/*  77 */     m.put("s_id", s_id);
/*  78 */     return AnswerDAO.getAnswerCountByIP(m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyCount_browser(String con)
/*     */   {
/*  88 */     return AnswerDAO.getSurveyCount_browser();
/*     */   }
/*     */ 
/*     */   public static List getSurveyList_browser(String con, int start_num, int page_size)
/*     */   {
/*  98 */     Map m = new HashMap();
/*  99 */     m.put("start_num", Integer.valueOf(start_num));
/* 100 */     m.put("page_size", Integer.valueOf(page_size));
/* 101 */     m.put("con", con);
/* 102 */     return AnswerDAO.getSurveyList_browser(m);
/*     */   }
/*     */ 
/*     */   public static synchronized boolean insertSurveyAnswer(SurveyAnswer sa)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       String answer_id = UUID.randomUUID().toString();
/* 114 */       sa.setAnswer_id(answer_id);
/* 115 */       sa.setAnswer_time(DateUtil.getCurrentDateTime());
/*     */ 
/* 117 */       AnswerDAO.insertSurveyAnswer(sa);
/* 118 */       List item_list = sa.getItem_list();
/*     */ 
/* 120 */       if ((item_list != null) && (item_list.size() > 0))
/*     */       {
/* 122 */         for (int i = 0; i < item_list.size(); i++)
/*     */         {
/* 124 */           SurveyAnswerItem item = (SurveyAnswerItem)item_list.get(i);
/* 125 */           item.setS_id(sa.getS_id());
/* 126 */           item.setAnswer_id(answer_id);
/* 127 */           AnswerDAO.insertSurveyAnswerItem(item);
/*     */         }
/*     */       }
/* 130 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 133 */       e.printStackTrace();
/* 134 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 140 */     System.out.println(isSubmitTimeout("87095242-86f3-4fcb-8d1c-254d955d0f05", "m2", "192.168.12.80"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.AnswerServer
 * JD-Core Version:    0.6.2
 */