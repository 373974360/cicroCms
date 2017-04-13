/*     */ package com.cicro.wcm.dao.survey;
/*     */ 
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class StatisticsDAO
/*     */ {
/*     */   public static String getStatisticsCount(String con)
/*     */   {
/*  24 */     return DBManager.getString("getStatisticsCount", con);
/*     */   }
/*     */ 
/*     */   public static List getStatisticsList(Map m)
/*     */   {
/*  34 */     return DBManager.queryFList("getStatisticsList", m);
/*     */   }
/*     */ 
/*     */   public static String getStatisticsCountBySurvey(String s_id)
/*     */   {
/*  44 */     return DBManager.getString("getStatisticsCountBySurvey", s_id);
/*     */   }
/*     */ 
/*     */   public static List getStatisticsDataBySurvey(Map m)
/*     */   {
/*  54 */     return DBManager.queryFList("getStatisticsDataBySurvey", m);
/*     */   }
/*     */ 
/*     */   public static String getItemTextCount(Map m)
/*     */   {
/*  64 */     return DBManager.getString("getItemTextCount", m);
/*     */   }
/*     */ 
/*     */   public static String getVoteCountBySurveyItem(Map m)
/*     */   {
/*  74 */     return DBManager.getString("getVoteCountBySurveyItem", m);
/*     */   }
/*     */ 
/*     */   public static List getVoteTotalBySurveyItem(Map m)
/*     */   {
/*  84 */     return DBManager.queryFList("getVoteTotalBySurveyItem", m);
/*     */   }
/*     */ 
/*     */   public static List getItemTextList(Map m)
/*     */   {
/*  94 */     return DBManager.queryFList("getItemTextList", m);
/*     */   }
/*     */ 
/*     */   public static List getItemTextList2(Map m)
/*     */   {
/*  99 */     return DBManager.queryFList("getItemTextList2", m);
/*     */   }
/*     */ 
/*     */   public static List getAnswerItemDetail(String answer_id)
/*     */   {
/* 109 */     return DBManager.queryFList("getAnswerItemDetail", answer_id);
/*     */   }
/*     */ 
/*     */   public static String getAnswerListCount(Map m)
/*     */   {
/* 119 */     return DBManager.getString("getAnswerListCount", m);
/*     */   }
/*     */ 
/*     */   public static List getAnswerList(Map m)
/*     */   {
/* 130 */     return DBManager.queryFList("getAnswerList", m);
/*     */   }
/*     */ 
/*     */   public static List getAnswerList2(Map m)
/*     */   {
/* 135 */     return DBManager.queryFList("getAnswerList2", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.survey.StatisticsDAO
 * JD-Core Version:    0.6.2
 */