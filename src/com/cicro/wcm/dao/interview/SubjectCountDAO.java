/*    */ package com.cicro.wcm.dao.interview;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SubjectCountDAO
/*    */ {
/*    */   public static List getAllSubjectCategory(String site_id)
/*    */   {
/* 24 */     return DBManager.queryFList("getAllSubjectCategory", site_id);
/*    */   }
/*    */ 
/*    */   public static List getSubjectCategoryCount(Map m)
/*    */   {
/* 35 */     return DBManager.queryFList("getSubjectCategoryCount", m);
/*    */   }
/*    */ 
/*    */   public static List getSubjectCategoryCount_user(Map m)
/*    */   {
/* 46 */     return DBManager.queryFList("getSubjectCategoryCount_user", m);
/*    */   }
/*    */ 
/*    */   public static List getSubjectCategoryCount_chat(Map m)
/*    */   {
/* 57 */     return DBManager.queryFList("getSubjectCategoryCount_chat", m);
/*    */   }
/*    */ 
/*    */   public static List getHotCount_user(Map m)
/*    */   {
/* 69 */     return DBManager.queryFList("getHotCount_user", m);
/*    */   }
/*    */ 
/*    */   public static List getHotCount_chat(Map m)
/*    */   {
/* 80 */     return DBManager.queryFList("getHotCount_chat", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.interview.SubjectCountDAO
 * JD-Core Version:    0.6.2
 */