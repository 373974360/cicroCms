/*     */ package com.cicro.wcm.services.comment;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.comment.CommentBean;
/*     */ import com.cicro.wcm.bean.comment.CommentSet;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CommentSetRPC
/*     */ {
/*     */   public static String getCommentCount(String id, String type)
/*     */   {
/*  26 */     Map m = new HashMap();
/*  27 */     m.put("id", id);
/*  28 */     if ("info".equals(type))
/*  29 */       m.put("info_type", "1");
/*  30 */     if ("survey".equals(type))
/*     */     {
/*  32 */       m.put("survey", "2");
/*  33 */       m.put("info_type", "2");
/*     */     }
/*  35 */     if ("appeal".equals(type))
/*  36 */       m.put("info_type", "3");
/*  37 */     return CommentService.getCommentMainCountForBrowser(m);
/*     */   }
/*     */ 
/*     */   public static String getCommentMainCount(Map<String, String> m, String info_type)
/*     */   {
/*  47 */     return CommentService.getCommentMainCount(m, info_type);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentMainList(Map<String, String> m, String info_type)
/*     */   {
/*  57 */     return CommentService.getCommentMainList(m, info_type);
/*     */   }
/*     */ 
/*     */   public static String insertCommentMain(CommentBean comB, HttpServletRequest request)
/*     */   {
/*  62 */     String site_id = SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());
/*  63 */     String ip = FormatUtil.getIpAddr(request);
/*  64 */     CommentSet cs = getCommentSetBySiteIdAndAppIDD(comB.getSite_id(), "cms");
/*  65 */     if (!"0".equals(cs.getIp_time()))
/*     */     {
/*  67 */       Map m = new HashMap();
/*  68 */       m.put("site_id", site_id);
/*  69 */       m.put("ip", ip);
/*  70 */       m.put("page_size", "1");
/*  71 */       m.put("start_num", "0");
/*  72 */       m.put("sort_name", "com.id");
/*  73 */       m.put("sort_type", "desc");
/*  74 */       List cl = CommentService.getCommentMainList(m, "info");
/*  75 */       if ((cl != null) && (cl.size() > 0)) {
/*     */         try
/*     */         {
/*  78 */           long pre_time = DateUtil.dateToTimestamp(((CommentBean)cl.get(0)).getAdd_time());
/*  79 */           long confine_time = pre_time + Integer.parseInt(cs.getIp_time()) * 60 * 1000;
/*  80 */           if (DateUtil.dateToTimestamp() < confine_time)
/*     */           {
/*  82 */             return "timeout";
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/*  86 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*  90 */     comB.setSite_id(site_id);
/*  91 */     comB.setIp(ip);
/*  92 */     return CommentService.insertCommentMain(comB);
/*     */   }
/*     */ 
/*     */   public static boolean supportComment(Map<String, String> m)
/*     */   {
/*  98 */     return CommentService.updateCommentMain(m, null);
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentMain(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 109 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 110 */     if (stl != null)
/*     */     {
/* 112 */       return CommentService.updateCommentMain(m, stl);
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentStatus(String ids, List<CommentBean> l, String is_status, HttpServletRequest request)
/*     */   {
/* 126 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 127 */     if (stl != null)
/*     */     {
/* 129 */       return CommentService.updateCommentStatus(ids, l, is_status, stl);
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCommentMain(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 142 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 143 */     if (stl != null)
/*     */     {
/* 145 */       return CommentService.deleteCommentMain(m, stl);
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public static CommentSet getCommentSetBySiteIdAndAppID(String site_id, String app_id)
/*     */   {
/* 155 */     return CommentSetManager.getCommentSetBySiteIdAndAppID(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static CommentSet getCommentSetBySiteIdAndAppIDD(String site_id, String app_id)
/*     */   {
/* 160 */     return CommentSetManager.getCommentSetBySiteIdAndAppIDD(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentSet(CommentSet commentSet)
/*     */   {
/* 169 */     return CommentSetManager.updateCommentSet(commentSet);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.comment.CommentSetRPC
 * JD-Core Version:    0.6.2
 */