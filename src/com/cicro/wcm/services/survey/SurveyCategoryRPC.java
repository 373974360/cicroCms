/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyCategory;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SurveyCategoryRPC
/*     */ {
/*     */   public static String getSurveyCategoryCount(String con, String site_id)
/*     */   {
/*  19 */     return SurveyCategoryService.getSurveyCategoryCount(con, site_id);
/*     */   }
/*     */ 
/*     */   public static List getSurveyCategoryList(String con, int start_num, int page_size, String site_id)
/*     */   {
/*  31 */     return SurveyCategoryService.getSurveyCategoryList(con, start_num, page_size, site_id);
/*     */   }
/*     */ 
/*     */   public static SurveyCategory getSurveyCategoryBean(String category_id)
/*     */   {
/*  41 */     return SurveyCategoryService.getSurveyCategoryBean(category_id);
/*     */   }
/*     */ 
/*     */   public static List getAllSurveyCategoryName(String site_id)
/*     */   {
/*  51 */     return SurveyCategoryService.getAllSurveyCategoryName(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveyCategory(SurveyCategory sc, HttpServletRequest request)
/*     */   {
/*  61 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  62 */     if (stl != null)
/*     */     {
/*  64 */       return SurveyCategoryService.insertSurveyCategory(sc, stl);
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyCategory(SurveyCategory sc, HttpServletRequest request)
/*     */   {
/*  76 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  77 */     if (stl != null)
/*     */     {
/*  79 */       return SurveyCategoryService.updateSurveyCategory(sc, stl);
/*     */     }
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSurveyCategory(String ids, String user_name, HttpServletRequest request)
/*     */   {
/*  92 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  93 */     if (stl != null)
/*     */     {
/*  95 */       return SurveyCategoryService.deleteSurvey(ids, user_name, stl);
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyCategoryPublishStatus(String ids, String publish_status, String user_name, HttpServletRequest request)
/*     */   {
/* 109 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 110 */     if (stl != null)
/*     */     {
/* 112 */       return SurveyCategoryService.updateSurveyCategoryPublishStatus(ids, publish_status, user_name, stl);
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getSurveyCountByCategoryID(int id)
/*     */   {
/* 124 */     return SurveyCategoryService.getSurveyCountByCategoryID(id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.SurveyCategoryRPC
 * JD-Core Version:    0.6.2
 */