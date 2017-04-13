/*     */ package com.cicro.wcm.dao.survey;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyCategory;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SurveyCategoryDAO
/*     */ {
/*     */   public static String getSurveyCategoryCount(Map m)
/*     */   {
/*  17 */     return DBManager.getString("getSurveyCategoryCount", m);
/*     */   }
/*     */ 
/*     */   public static List<SurveyCategory> getAllSurveyCategoryList()
/*     */   {
/*  28 */     return DBManager.queryFList("getAllSurveyCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static List getSurveyCategoryList(Map m)
/*     */   {
/*  38 */     return DBManager.queryFList("getSurveyCategoryList", m);
/*     */   }
/*     */ 
/*     */   public static SurveyCategory getSurveyCategoryBean(String category_id)
/*     */   {
/*  48 */     return (SurveyCategory)DBManager.queryFObj("getSurveyCategoryBean", category_id);
/*     */   }
/*     */ 
/*     */   public static List getAllSurveyCategoryName()
/*     */   {
/*  58 */     return DBManager.queryFList("getAllSurveyCategoryName", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveyCategory(SurveyCategory sc, SettingLogsBean stl)
/*     */   {
/*  68 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SURVEY_CATEGORY_TABLE_NAME);
/*  69 */     sc.setId(id);
/*  70 */     if (DBManager.insert("insertSurveyCategory", sc))
/*     */     {
/*  72 */       PublicTableDAO.insertSettingLogs("添加", "调查问卷分类", id, stl);
/*  73 */       return true;
/*     */     }
/*     */ 
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyCategory(SurveyCategory sc, SettingLogsBean stl)
/*     */   {
/*  86 */     if (DBManager.update("updateSurveyCategory", sc))
/*     */     {
/*  88 */       PublicTableDAO.insertSettingLogs("修改", "调查问卷分类", sc.getId(), stl);
/*  89 */       return true;
/*     */     }
/*     */ 
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getSurveyCountByCategoryID(int id)
/*     */   {
/* 102 */     return DBManager.getString("getSurveyCountByCategoryID", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static boolean deleteSurveyCategory(Map m, SettingLogsBean stl)
/*     */   {
/* 112 */     if (DBManager.update("deleteSurveyCategory", m))
/*     */     {
/* 114 */       PublicTableDAO.insertSettingLogs("删除", "调查问卷分类", m.get("ids"), stl);
/* 115 */       return true;
/*     */     }
/*     */ 
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyCategoryPublishStatus(Map m, SettingLogsBean stl)
/*     */   {
/* 128 */     if (DBManager.update("updateSurveyCategoryPublishStatus", m))
/*     */     {
/* 130 */       PublicTableDAO.insertSettingLogs("修改", "调查问卷分类发布状态", m.get("ids"), stl);
/* 131 */       return true;
/*     */     }
/*     */ 
/* 134 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.survey.SurveyCategoryDAO
 * JD-Core Version:    0.6.2
 */