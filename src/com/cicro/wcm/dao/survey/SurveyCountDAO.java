/*    */ package com.cicro.wcm.dao.survey;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SurveyCountDAO
/*    */ {
/*    */   public static List getAllSurveyCategory(String site_id)
/*    */   {
/* 16 */     return DBManager.queryFList("getAllSurveyCategory", site_id);
/*    */   }
/*    */ 
/*    */   public static List getSurveyCategoryCount(Map m)
/*    */   {
/* 26 */     return DBManager.queryFList("getSurveyCategoryCount2", m);
/*    */   }
/*    */ 
/*    */   public static List getSurveySubjectCount(Map m)
/*    */   {
/* 36 */     return DBManager.queryFList("getSurveySubjectCount", m);
/*    */   }
/*    */ 
/*    */   public static List getSurveyAnswerCount(Map m)
/*    */   {
/* 46 */     return DBManager.queryFList("getSurveyAnswerCount", m);
/*    */   }
/*    */ 
/*    */   public static List getHotCount_answer(Map m)
/*    */   {
/* 56 */     return DBManager.queryFList("getHotCount_answer", m);
/*    */   }
/*    */ 
/*    */   public static List getSurveySubjectCountBySub(Map m)
/*    */   {
/* 66 */     return DBManager.queryFList("getSurveySubjectCountBySub", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.survey.SurveyCountDAO
 * JD-Core Version:    0.6.2
 */