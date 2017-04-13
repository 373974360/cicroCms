/*     */ package com.cicro.wcm.dao.survey;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyChildItem;
/*     */ import com.cicro.wcm.bean.survey.SurveySub;
/*     */ import com.cicro.wcm.bean.survey.SurveySuvItem;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SurveyDAO
/*     */ {
/*     */   public static List getSurveyRecommendList(Map m)
/*     */   {
/*  26 */     return DBManager.queryFList("getSurveyRecommendList", m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyRecommendListCount(Map m)
/*     */   {
/*  36 */     return DBManager.getString("getSurveyRecommendListCount", m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyListCountBrowser(Map m)
/*     */   {
/*  46 */     return DBManager.getString("getSurveyListCountBrowser", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyRecommend(Map m)
/*     */   {
/*  56 */     return DBManager.update("updateSurveyRecommend", m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyCount(Map m)
/*     */   {
/*  66 */     return DBManager.getString("getSurveyCount", m);
/*     */   }
/*     */ 
/*     */   public static List getSurveyList(Map m)
/*     */   {
/*  76 */     List l = DBManager.queryFList("getSurveyList", m);
/*  77 */     return l;
/*     */   }
/*     */ 
/*     */   public static SurveyBean getSurveyBean(String s_id)
/*     */   {
/*  87 */     return (SurveyBean)DBManager.queryFObj("getSurveyBean", s_id);
/*     */   }
/*     */ 
/*     */   public static List getAllSurveyObjBYPublish()
/*     */   {
/*  97 */     return DBManager.queryFList("getAllSurveyObjBYPublish", "");
/*     */   }
/*     */ 
/*     */   public static List getSurveySubjectBean(String s_id)
/*     */   {
/* 107 */     return DBManager.queryFList("getSurveySubjectBean", s_id);
/*     */   }
/*     */ 
/*     */   public static List getSurveySubjectSingle(String s_id)
/*     */   {
/* 117 */     return DBManager.queryFList("getSurveySubjectSingle", s_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSurvey(SurveyBean sb, SettingLogsBean stl)
/*     */   {
/* 127 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SURVEY_TABLE_NAME);
/* 128 */     sb.setId(id);
/* 129 */     if (DBManager.insert("insertSurvey", sb))
/*     */     {
/* 131 */       PublicTableDAO.insertSettingLogs("添加", "问卷调查", id, stl);
/* 132 */       return true;
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurvey(SurveyBean sb, SettingLogsBean stl)
/*     */   {
/* 144 */     if (DBManager.update("updateSurvey", sb))
/*     */     {
/* 146 */       PublicTableDAO.insertSettingLogs("修改", "问卷调查", sb.getS_id(), stl);
/* 147 */       return true;
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean setSurveyAttr(SurveyBean sb, SettingLogsBean stl)
/*     */   {
/* 159 */     if (DBManager.update("setSurveyAttr", sb))
/*     */     {
/* 161 */       PublicTableDAO.insertSettingLogs("修改", "问卷调查属性", sb.getS_id(), stl);
/* 162 */       return true;
/*     */     }
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubjectItem(String survey_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 175 */       DBManager.delete("deleteSubject", survey_id);
/* 176 */       DBManager.delete("deleteSubjectChild", survey_id);
/* 177 */       DBManager.delete("deleteSubjectItem", survey_id);
/*     */ 
/* 179 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 182 */       e.printStackTrace();
/* 183 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveySub(SurveySub sub, SettingLogsBean stl)
/*     */   {
/* 194 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SURVEY_SUB_TABLE_NAME);
/* 195 */     sub.setId(id);
/* 196 */     return DBManager.insert("insertSurveySub", sub);
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveySubItem(SurveySuvItem item)
/*     */   {
/* 206 */     return DBManager.insert("insertSurveySubItem", item);
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveyChildItem(SurveyChildItem ci)
/*     */   {
/* 217 */     return DBManager.insert("insertSurveyChildItem", ci);
/*     */   }
/*     */ 
/*     */   public static boolean publishSurvey(Map m, SettingLogsBean stl)
/*     */   {
/* 227 */     if (DBManager.update("publishSurvey", m))
/*     */     {
/* 229 */       PublicTableDAO.insertSettingLogs("修改", "问卷调查发布状态", (String)m.get("ids"), stl);
/* 230 */       return true;
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSurvey(Map m, SettingLogsBean stl)
/*     */   {
/* 242 */     if (DBManager.update("deleteSurvey", m))
/*     */     {
/* 244 */       PublicTableDAO.insertSettingLogs("删除", "问卷调查", (String)m.get("ids"), stl);
/* 245 */       return true;
/*     */     }
/* 247 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.survey.SurveyDAO
 * JD-Core Version:    0.6.2
 */