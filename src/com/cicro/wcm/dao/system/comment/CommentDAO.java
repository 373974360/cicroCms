/*     */ package com.cicro.wcm.dao.system.comment;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.comment.CommentBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommentDAO
/*     */ {
/*     */   public static String getCommontCountByMemberID(String me_id)
/*     */   {
/*  25 */     return DBManager.getString("getCommontCountByMemberID", me_id);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommontListByMemberID(Map<String, String> mp)
/*     */   {
/*  36 */     return DBManager.queryFList("getCommontListByMemberID", mp);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentList(Map<String, String> mp)
/*     */   {
/*  47 */     return DBManager.queryFList("getCommontListByDB", mp);
/*     */   }
/*     */ 
/*     */   public static String getCommentCnt(Map<String, String> mp)
/*     */   {
/*  57 */     return (String)DBManager.queryFObj("getCommentCnt", mp);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> searchCommentList(Map<String, String> mp)
/*     */   {
/*  67 */     return DBManager.queryFList("searchCommentList", mp);
/*     */   }
/*     */ 
/*     */   public static String searchCommentCnt(Map<String, String> mp)
/*     */   {
/*  77 */     return (String)DBManager.queryFObj("", mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertComment(CommentBean cb)
/*     */   {
/*  88 */     String id = PublicTableDAO.getIDByTableName(PublicTableDAO.COMMENT_TABLE_NAME);
/*  89 */     cb.setAdd_dtime(DateUtil.getCurrentDateTime());
/*  90 */     cb.setCmt_id(id);
/*  91 */     return DBManager.insert("insertComment", cb);
/*     */   }
/*     */ 
/*     */   public static boolean updateComment(CommentBean cb, SettingLogsBean stl)
/*     */   {
/* 102 */     if (DBManager.update("updateComment", cb))
/*     */     {
/* 104 */       PublicTableDAO.insertSettingLogs("修改", "评论", cb.getCmt_id(), stl);
/* 105 */       return true;
/*     */     }
/*     */ 
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteComment(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 121 */     if (DBManager.delete("deleteComment", mp))
/*     */     {
/* 123 */       PublicTableDAO.insertSettingLogs("删除", "评论", (String)mp.get("con_value"), stl);
/* 124 */       return true;
/*     */     }
/*     */ 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 135 */     System.out.println(getCommontCountByMemberID("13"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.comment.CommentDAO
 * JD-Core Version:    0.6.2
 */