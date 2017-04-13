/*     */ package com.cicro.wcm.services.system.comment;
/*     */ 
/*     */ import com.cicro.util.ip.Utils;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.comment.CommentBean;
/*     */ import com.cicro.wcm.dao.system.comment.CommentDAO;
/*     */ import com.cicro.wcm.services.appeal.sq.SQManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommentManager
/*     */ {
/*     */   public static String getCommontCountByMemberID(String me_id)
/*     */   {
/*  24 */     return CommentDAO.getCommontCountByMemberID(me_id);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommontListByMemberID(String me_id, String start_num, String page_size)
/*     */   {
/*  34 */     Map m = new HashMap();
/*  35 */     m.put("me_id", me_id);
/*  36 */     m.put("start_num", start_num);
/*  37 */     m.put("page_size", page_size);
/*  38 */     return CommentDAO.getCommontListByMemberID(m);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentList(Map<String, String> mp)
/*     */   {
/*  48 */     List l = new ArrayList();
/*  49 */     if (mp.get("search") != null)
/*     */     {
/*  51 */       l = CommentDAO.searchCommentList(mp);
/*     */     }
/*     */     else {
/*  54 */       l = CommentDAO.getCommentList(mp);
/*     */     }
/*  56 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  58 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  60 */         SQBean sb = SQManager.getSQSimpleBean(Integer.parseInt(((CommentBean)l.get(i)).getItem_id()));
/*  61 */         if (sb != null)
/*     */         {
/*  63 */           ((CommentBean)l.get(i)).setRele_title(sb.getSq_title2());
/*  64 */           ((CommentBean)l.get(i)).setModel_id(sb.getModel_id());
/*  65 */           ((CommentBean)l.get(i)).setDept_id(sb.getDo_dept());
/*  66 */           ((CommentBean)l.get(i)).setIp_addr(Utils.getCountry(((CommentBean)l.get(i)).getCmt_ip()));
/*     */         }
/*     */       }
/*     */     }
/*  70 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getCommentReleTitle(String app_id, String id)
/*     */   {
/*  80 */     String title = "";
/*  81 */     if ("appeal".endsWith(app_id))
/*     */     {
/*  83 */       SQBean sb = SQManager.getSqBean(Integer.parseInt(id));
/*  84 */       if (sb != null)
/*  85 */         title = sb.getSq_title2();
/*     */     }
/*  87 */     return title;
/*     */   }
/*     */ 
/*     */   public static String getCommentListCnt(Map<String, String> mp)
/*     */   {
/*  97 */     mp.put("is_deleted", "0");
/*  98 */     if (mp.get("search") == "getCnt")
/*     */     {
/* 101 */       return CommentDAO.searchCommentCnt(mp);
/*     */     }
/*     */ 
/* 104 */     return CommentDAO.getCommentCnt(mp);
/*     */   }
/*     */ 
/*     */   public static String getAllCommentCnt(Map<String, String> mp)
/*     */   {
/* 116 */     mp.remove("cmt_status");
/* 117 */     mp.remove("is_deleted");
/* 118 */     return CommentDAO.getCommentCnt(mp);
/*     */   }
/*     */ 
/*     */   public static String getCheckedCommentCnt(Map<String, String> mp)
/*     */   {
/* 127 */     mp.put("cmt_status", "1");
/* 128 */     mp.put("is_deleted", "0");
/* 129 */     return CommentDAO.getCommentCnt(mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertComment(CommentBean cb)
/*     */   {
/* 138 */     return CommentDAO.insertComment(cb);
/*     */   }
/*     */ 
/*     */   public static boolean updateComment(CommentBean cb, SettingLogsBean stl)
/*     */   {
/* 149 */     return CommentDAO.updateComment(cb, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteComment(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 160 */     return CommentDAO.deleteComment(mp, stl);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getIPAdress(List<CommentBean> lt)
/*     */   {
/* 170 */     Map mp = new HashMap();
/* 171 */     for (int i = 0; i < lt.size(); i++)
/*     */     {
/* 173 */       String ip = ((CommentBean)lt.get(i)).getCmt_ip();
/* 174 */       String addr = Utils.getCountry(ip);
/* 175 */       mp.put(ip, addr);
/*     */     }
/* 177 */     return mp;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 182 */     System.out.println(getCommontListByMemberID("13", "0", "10"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.comment.CommentManager
 * JD-Core Version:    0.6.2
 */