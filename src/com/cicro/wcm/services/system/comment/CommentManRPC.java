/*     */ package com.cicro.wcm.services.system.comment;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.comment.CommentBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CommentManRPC
/*     */ {
/*     */   public static String getCommontCountByMemberID(String me_id)
/*     */   {
/*  21 */     return CommentManager.getCommontCountByMemberID(me_id);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommontListByMemberID(String me_id, String start_num, String page_size)
/*     */   {
/*  31 */     return CommentManager.getCommontListByMemberID(me_id, start_num, page_size);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentList(Map<String, String> mp)
/*     */   {
/*  41 */     return CommentManager.getCommentList(mp);
/*     */   }
/*     */ 
/*     */   public static String getCommentListCnt(Map<String, String> mp)
/*     */   {
/*  51 */     return CommentManager.getCommentListCnt(mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertComment(CommentBean cb, HttpServletRequest request)
/*     */   {
/*  60 */     cb.setCmt_ip(FormatUtil.getIpAddr(request));
/*  61 */     return CommentManager.insertComment(cb);
/*     */   }
/*     */ 
/*     */   public static boolean updateComment(CommentBean cb, HttpServletRequest request)
/*     */   {
/*  72 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  73 */     if (stl != null)
/*     */     {
/*  75 */       return CommentManager.updateComment(cb, stl);
/*     */     }
/*     */ 
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteComment(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/*  91 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  92 */     if (stl != null)
/*     */     {
/*  94 */       return CommentManager.deleteComment(mp, stl);
/*     */     }
/*     */ 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getIPAdress(List<CommentBean> lt)
/*     */   {
/* 109 */     return CommentManager.getIPAdress(lt);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.comment.CommentManRPC
 * JD-Core Version:    0.6.2
 */