/*    */ package com.cicro.project.comment;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CommentDao
/*    */ {
/*    */   public static List getAllcomment(Map<String, String> map)
/*    */   {
/* 11 */     return DBManager.queryFList("getCommentcontent", map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.project.comment.CommentDao
 * JD-Core Version:    0.6.2
 */