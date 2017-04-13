/*    */ package com.cicro.wcm.dao.survey;
/*    */ 
/*    */ import com.cicro.wcm.bean.survey.SurveyAnswer;
/*    */ import com.cicro.wcm.bean.survey.SurveyAnswerItem;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AnswerDAO
/*    */ {
/*    */   public static String getLastAnswerTimeByIP(Map m)
/*    */   {
/*    */     try
/*    */     {
/* 27 */       return DBManager.getString("getLastAnswerTimeByIP", m);
/*    */     }
/*    */     catch (Exception e) {
/*    */     }
/* 31 */     return "";
/*    */   }
/*    */ 
/*    */   public static String getAnswerCountByIP(Map m)
/*    */   {
/* 42 */     return DBManager.getString("getAnswerCountByIP", m);
/*    */   }
/*    */ 
/*    */   public static String getSurveyCount_browser()
/*    */   {
/* 52 */     return DBManager.getString("getSurveyCount_browser", "");
/*    */   }
/*    */ 
/*    */   public static List getSurveyList_browser(Map m)
/*    */   {
/* 62 */     List l = DBManager.queryFList("getSurveyList_browser", m);
/* 63 */     return l;
/*    */   }
/*    */ 
/*    */   public static boolean insertSurveyAnswer(SurveyAnswer sa)
/*    */   {
/* 73 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SURVEY_ANSWER_TABLE_NAME);
/* 74 */     sa.setId(id);
/* 75 */     return DBManager.insert("insertAnswer", sa);
/*    */   }
/*    */ 
/*    */   public static boolean insertSurveyAnswerItem(SurveyAnswerItem item)
/*    */   {
/* 85 */     return DBManager.insert("insertAnswerItem", item);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.survey.AnswerDAO
 * JD-Core Version:    0.6.2
 */