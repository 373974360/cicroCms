/*     */ package com.cicro.wcm.services.comment;
/*     */ 
/*     */ import com.cicro.wcm.bean.comment.CommentSet;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.comment.CommentDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommentSetManager
/*     */   implements ISyncCatch
/*     */ {
/*  14 */   private static Map<String, CommentSet> map = new HashMap();
/*     */ 
/*  16 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  21 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  26 */     map.clear();
/*     */     try {
/*  28 */       List list = CommentDAO.getCommentSetList();
/*  29 */       for (CommentSet set : list) {
/*  30 */         List tactList = new ArrayList();
/*  31 */         String app_id = set.getApp_id() == null ? "" : set.getApp_id();
/*  32 */         String site_id = set.getSite_id() == null ? "" : set.getSite_id();
/*  33 */         String[] s = set.getTact_word().split("[,，]");
/*  34 */         for (int i = 0; i < s.length; i++) {
/*  35 */           if ((s[i] != null) && (!"".equals(s[i]))) {
/*  36 */             tactList.add(s[i]);
/*     */           }
/*     */         }
/*  39 */         set.setTactList(tactList);
/*  40 */         map.put(app_id + "-" + site_id, set);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  44 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadList()
/*     */   {
/*  51 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.comment.CommentSetManager");
/*     */   }
/*     */ 
/*     */   public static CommentSet getCommentSetBySiteIdAndAppID(String site_id, String app_id)
/*     */   {
/*  57 */     CommentSet commentSet = new CommentSet();
/*     */     try {
/*  59 */       app_id = site_id == null ? "" : app_id;
/*  60 */       site_id = site_id == null ? "" : site_id;
/*  61 */       if (map.containsKey(app_id + "-" + site_id));
/*  62 */       return (CommentSet)map.get(app_id + "-" + site_id);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  66 */       e.printStackTrace();
/*  67 */     }return commentSet;
/*     */   }
/*     */ 
/*     */   public static CommentSet getCommentSetBySiteIdAndAppIDD(String site_id, String app_id)
/*     */   {
/*  74 */     CommentSet commentSet = new CommentSet();
/*     */     try {
/*  76 */       Map map = new HashMap();
/*  77 */       map.put("app_id", app_id);
/*  78 */       if ((site_id != null) && (!"".equals(site_id))) {
/*  79 */         map.put("site_id", site_id);
/*     */       }
/*  81 */       int count = CommentDAO.getCommentSetCount(map);
/*  82 */       if (count == 0) {
/*  83 */         commentSet.setApp_id(app_id);
/*  84 */         commentSet.setCom_prefix("");
/*  85 */         commentSet.setIp_time("0");
/*  86 */         commentSet.setIs_code("1");
/*  87 */         commentSet.setIs_need("1");
/*  88 */         commentSet.setIs_public("1");
/*  89 */         commentSet.setPass_size(5);
/*  90 */         commentSet.setSite_id(site_id);
/*  91 */         commentSet.setTact_word("");
/*  92 */         commentSet.setTime_spacer("60");
/*  93 */         CommentDAO.addCommentSet(commentSet);
/*     */       }
/*  95 */       return CommentDAO.getCommentSetByAppIdAndSiteId(site_id, app_id);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  99 */       e.printStackTrace();
/* 100 */     }return commentSet;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentSet(CommentSet commentSet)
/*     */   {
/* 111 */     CommentDAO.updateCommentSet(commentSet);
/* 112 */     reloadList();
/* 113 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.comment.CommentSetManager
 * JD-Core Version:    0.6.2
 */