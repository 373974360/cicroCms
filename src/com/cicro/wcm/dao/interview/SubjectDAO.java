/*     */ package com.cicro.wcm.dao.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.SubjectBean;
/*     */ import com.cicro.wcm.bean.interview.SubjectResouse;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubjectDAO
/*     */ {
/*     */   public static String getSubjectRecommendListCount(Map<String, String> m)
/*     */   {
/*  28 */     return DBManager.getString("getSubjectRecommendListCount", m);
/*     */   }
/*     */ 
/*     */   public static String getSubjectBrowserListHandlCount(Map<String, String> m)
/*     */   {
/*  38 */     return DBManager.getString("getSubjectBrowserListHandlCount", m);
/*     */   }
/*     */ 
/*     */   public static List getSubjectRecommendList(Map m)
/*     */   {
/*  49 */     return DBManager.queryFList("getSubjectRecommendList", m);
/*     */   }
/*     */ 
/*     */   public static List getSubjectBrowserList(Map m)
/*     */   {
/*  60 */     return DBManager.queryFList("getSubjectBrowserList", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubjectRecommend(Map m)
/*     */   {
/*  71 */     return DBManager.update("updateSubjectRecommend", m);
/*     */   }
/*     */ 
/*     */   public static String getSubjectCount(Map m)
/*     */   {
/*  82 */     return DBManager.getString("getSubjectCount", m);
/*     */   }
/*     */ 
/*     */   public static String getSubjectManagerCount(Map m)
/*     */   {
/*  93 */     return DBManager.getString("getSubjectManagerCount", m);
/*     */   }
/*     */ 
/*     */   public static List getSubjectList(Map m)
/*     */   {
/* 104 */     List l = DBManager.queryFList("getSubjectList", m);
/* 105 */     return l;
/*     */   }
/*     */ 
/*     */   public static List getSubjectManagerList(Map m)
/*     */   {
/* 116 */     List l = DBManager.queryFList("getSubjectManagerList", m);
/* 117 */     return l;
/*     */   }
/*     */ 
/*     */   public static SubjectBean getHistoryRecord(String sub_id)
/*     */   {
/* 127 */     return (SubjectBean)DBManager.queryFObj("getHistoryRecord", sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateHistoryRecord(SubjectBean sb, SettingLogsBean stl)
/*     */   {
/* 137 */     if (DBManager.update("updateHistoryRecord", sb))
/*     */     {
/* 139 */       PublicTableDAO.insertSettingLogs("修改", "访谈历史记录", sb.getSub_id(), stl);
/* 140 */       return true;
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   public static SubjectBean getSubjectObj(int id)
/*     */   {
/* 152 */     return (SubjectBean)DBManager.queryFObj("getSubjectObj", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static SubjectBean getSubjectObjBySubID(String sub_id)
/*     */   {
/* 162 */     return (SubjectBean)DBManager.queryFObj("getSubjectObjBySubID", sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubject(SubjectBean sub, SettingLogsBean stl)
/*     */   {
/* 172 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_SUBJECT_TABLE_NAME);
/* 173 */     sub.setId(id);
/* 174 */     if (DBManager.insert("insertSubject", sub))
/*     */     {
/* 176 */       PublicTableDAO.insertSettingLogs("添加", "访谈主题", id, stl);
/* 177 */       return true;
/*     */     }
/*     */ 
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubject(SubjectBean sub, SettingLogsBean stl)
/*     */   {
/* 190 */     if (DBManager.update("updateSubject", sub))
/*     */     {
/* 192 */       PublicTableDAO.insertSettingLogs("修改", "访谈主题", sub.getId(), stl);
/* 193 */       return true;
/*     */     }
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubject(Map m, SettingLogsBean stl)
/*     */   {
/* 206 */     if (DBManager.update("delete_interview_subject", m))
/*     */     {
/* 208 */       PublicTableDAO.insertSettingLogs("删除", "访谈主题", m.get("ids"), stl);
/* 209 */       return true;
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubjectStatus(Map m, SettingLogsBean stl)
/*     */   {
/* 222 */     if (DBManager.update("updateSubjectStatus", m))
/*     */     {
/* 224 */       PublicTableDAO.insertSettingLogs("修改", "主题" + m.get("oper_name") + "状态", m.get("ids"), stl);
/* 225 */       return true;
/*     */     }
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean subjectSubmit(Map m, SettingLogsBean stl)
/*     */   {
/* 238 */     if (DBManager.update("subjectSubmit", m))
/*     */     {
/* 240 */       PublicTableDAO.insertSettingLogs("提交", "访谈主题", m.get("ids"), stl);
/* 241 */       return true;
/*     */     }
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertResouse(SubjectResouse sr, SettingLogsBean stl)
/*     */   {
/* 255 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_RESOUSE_TABLE_NAME);
/* 256 */     sr.setId(id);
/* 257 */     if (DBManager.insert("insertResouse", sr))
/*     */     {
/* 259 */       PublicTableDAO.insertSettingLogs("添加", "访谈主题附件", id, stl);
/* 260 */       return true;
/*     */     }
/*     */ 
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteResouseBySub(String sub_id)
/*     */   {
/* 273 */     return DBManager.delete("deleteResouseBySub", sub_id);
/*     */   }
/*     */ 
/*     */   public static List getResouseList(Map m)
/*     */   {
/* 284 */     return DBManager.queryFList("getResouseList", m);
/*     */   }
/*     */ 
/*     */   public static List getResouseListByManager(String sub_id)
/*     */   {
/* 295 */     return DBManager.queryFList("getResouseListByManager", sub_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.interview.SubjectDAO
 * JD-Core Version:    0.6.2
 */