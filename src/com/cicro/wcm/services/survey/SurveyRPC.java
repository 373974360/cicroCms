/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SurveyRPC
/*     */ {
/*     */   public static String getCurrentDate()
/*     */   {
/*  27 */     return DateUtil.getCurrentDate();
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyRecommend(String ids, String recommend_flag)
/*     */   {
/*  38 */     return SurveyService.updateSurveyRecommend(ids, recommend_flag);
/*     */   }
/*     */ 
/*     */   public static String getSurveyCount(String con, String site_id)
/*     */   {
/*  48 */     return SurveyService.getSurveyCount(con, site_id);
/*     */   }
/*     */ 
/*     */   public static List getSurveyList(String con, int start_num, int page_size, String site_id)
/*     */   {
/*  58 */     return SurveyService.getSurveyList(con, start_num, page_size, site_id);
/*     */   }
/*     */ 
/*     */   public static SurveyBean getSurveyBean(String s_id)
/*     */   {
/*  68 */     return SurveyService.getSurveyBean(s_id);
/*     */   }
/*     */ 
/*     */   public static List getSurveySubjectBean(String s_id)
/*     */   {
/*  78 */     return SurveyService.getSurveySubjectBean(s_id);
/*     */   }
/*     */ 
/*     */   public static List getSurveySubjectSingle(String s_id)
/*     */   {
/*  88 */     return SurveyService.getSurveySubjectSingle(s_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSurvey(SurveyBean sb, List l, HttpServletRequest request)
/*     */   {
/*  98 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  99 */     if (stl != null)
/*     */     {
/* 101 */       return SurveyService.updateSurvey(sb, l, stl);
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean setSurveyAttr(SurveyBean sb, HttpServletRequest request)
/*     */   {
/* 113 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 114 */     if (stl != null)
/*     */     {
/* 116 */       return SurveyService.setSurveyAttr(sb, stl);
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSurvey(SurveyBean sb, List l, HttpServletRequest request)
/*     */   {
/* 128 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 129 */     if (stl != null)
/*     */     {
/* 131 */       return SurveyService.insertSurvey(sb, l, stl);
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveySub(List l)
/*     */   {
/* 142 */     return SurveyService.insertSurveySub(l);
/*     */   }
/*     */ 
/*     */   public static boolean publishSurvey(String ids, String publish_status, String user_name, HttpServletRequest request)
/*     */   {
/* 154 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 155 */     if (stl != null)
/*     */     {
/* 157 */       return SurveyService.publishSurvey(ids, publish_status, user_name, stl);
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSurvey(String ids, String user_name, HttpServletRequest request)
/*     */   {
/* 169 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 170 */     if (stl != null)
/*     */     {
/* 172 */       return SurveyService.deleteSurvey(ids, user_name, stl);
/*     */     }
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getStatisticsCount(String con)
/*     */   {
/* 185 */     return StatisticsService.getStatisticsCount(con);
/*     */   }
/*     */ 
/*     */   public static List getStatisticsList(String con, int start_num, int page_size)
/*     */   {
/* 197 */     return StatisticsService.getStatisticsList(con, start_num, page_size);
/*     */   }
/*     */ 
/*     */   public static Map getStatisticsDataBySurvey(String s_id)
/*     */   {
/* 207 */     return StatisticsService.getStatisticsDataBySurvey(s_id, "");
/*     */   }
/*     */ 
/*     */   public static Map getStatisticsDataBySurvey(String s_id, String con)
/*     */   {
/* 218 */     return StatisticsService.getStatisticsDataBySurvey(s_id, con);
/*     */   }
/*     */ 
/*     */   public static String getItemTextCount(String s_id, String item_id, String item_value, String is_text, String search_con)
/*     */   {
/* 228 */     return StatisticsService.getItemTextCount(s_id, item_id, item_value, is_text, search_con);
/*     */   }
/*     */ 
/*     */   public static List getItemTextList(String s_id, String item_id, String item_value, String is_text, int start_num, int page_size, String search_con)
/*     */   {
/* 238 */     return StatisticsService.getItemTextList(s_id, item_id, item_value, is_text, start_num, page_size, search_con);
/*     */   }
/*     */ 
/*     */   public static List getAnswerItemDetail(String answer_id)
/*     */   {
/* 248 */     return StatisticsService.getAnswerItemDetail(answer_id);
/*     */   }
/*     */ 
/*     */   public static String getAnswerListCount(String s_id, String con)
/*     */   {
/* 259 */     return StatisticsService.getAnswerListCount(s_id, con);
/*     */   }
/*     */ 
/*     */   public static List getAnswerList(String s_id, String con, int start_num, int page_size)
/*     */   {
/* 272 */     return StatisticsService.getAnswerList(s_id, con, start_num, page_size);
/*     */   }
/*     */ 
/*     */   public static Map getSurveyCategoryCount(String start_time, String end_time, String time_type, String site_id)
/*     */   {
/* 285 */     return SurveyCountServices.getSurveyCategoryCount(start_time, end_time, time_type, site_id);
/*     */   }
/*     */ 
/*     */   public static Map getHotCount(String start_time, String end_time, String category_ids, int count_num, String time_type, String site_id)
/*     */   {
/* 298 */     return SurveyCountServices.getHotCount(start_time, end_time, category_ids, count_num, time_type, site_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.SurveyRPC
 * JD-Core Version:    0.6.2
 */