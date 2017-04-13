/*     */ package com.cicro.wcm.dao.comment;
/*     */ 
/*     */ import com.cicro.wcm.bean.comment.CommentBean;
/*     */ import com.cicro.wcm.bean.comment.CommentSet;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommentDAO
/*     */ {
/*     */   public static boolean addCommentSet(CommentSet commentSet)
/*     */   {
/*  23 */     commentSet.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.COMSET_TABLE_NAME));
/*  24 */     return DBManager.insert("comment_set.addCommentSet", commentSet);
/*     */   }
/*     */ 
/*     */   public static List<CommentSet> getCommentSetList()
/*     */   {
/*  34 */     Map map = new HashMap();
/*  35 */     return DBManager.queryFList("comment_set.getCommentSetList", map);
/*     */   }
/*     */ 
/*     */   public static int getCommentSetCount(Map<String, String> map)
/*     */   {
/*  44 */     return Integer.valueOf(((Integer)DBManager.queryFObj("comment_set.getCommentSetCount", map)).intValue()).intValue();
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentSet(CommentSet commentSet)
/*     */   {
/*  53 */     return DBManager.update("comment_set.updateCommentSet", commentSet);
/*     */   }
/*     */ 
/*     */   public static CommentSet getCommentSetByAppIdAndSiteId(String site_id, String app_id)
/*     */   {
/*  63 */     Map map = new HashMap();
/*  64 */     map.put("app_id", app_id);
/*  65 */     if ((site_id != null) && (!"".equals(site_id))) {
/*  66 */       map.put("site_id", site_id);
/*     */     }
/*  68 */     return (CommentSet)DBManager.queryFObj("comment_set.getCommentSetByAppIdAndSiteId", map);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getHotCommentInfo(Map<String, String> m)
/*     */   {
/*  80 */     return DBManager.queryFList("getHotCommentInfo", m);
/*     */   }
/*     */ 
/*     */   public static String getCommentMainCountForBrowser(Map<String, String> m)
/*     */   {
/*  90 */     return DBManager.getString("getCommentMainCountForBrowser", m);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentMainListForBrowser(Map<String, String> m)
/*     */   {
/* 101 */     return DBManager.queryFList("getCommentMainListForBrowser", m);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentListForIDS(String ids)
/*     */   {
/* 112 */     Map m = new HashMap();
/* 113 */     m.put("ids", ids);
/* 114 */     return DBManager.queryFList("getCommentListForIDS", m);
/*     */   }
/*     */ 
/*     */   public static String getCommentMainCountForInfo(Map<String, String> m)
/*     */   {
/* 124 */     return DBManager.getString("getCommentMainCountForInfo", m);
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentMainListForInfo(Map<String, String> m)
/*     */   {
/* 135 */     return DBManager.queryFList("getCommentMainListForInfo", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertCommentMain(CommentBean comB)
/*     */   {
/* 146 */     return DBManager.insert("insert_comment_main", comB);
/*     */   }
/*     */ 
/*     */   public static boolean passCommentMain(String ids, List<CommentBean> l, String is_status, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 161 */       String parent_ids = "";
/* 162 */       Map m = new HashMap();
/* 163 */       m.put("is_status", is_status);
/* 164 */       for (CommentBean cb : l)
/*     */       {
/* 166 */         m.put("id", cb.getId());
/* 167 */         DBManager.update("pass_comment_main", m);
/* 168 */         if (cb.getParent_id() != 0)
/* 169 */           parent_ids = parent_ids + "," + cb.getParent_id();
/*     */       }
/* 171 */       if ((parent_ids != null) && (!"".equals(parent_ids)))
/*     */       {
/* 173 */         m.put("parent_ids", parent_ids.substring(1));
/* 174 */         DBManager.update("update_comment_replay", m);
/*     */       }
/* 176 */       PublicTableDAO.insertSettingLogs("审核", "评论", ids, stl);
/* 177 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 181 */       e.printStackTrace();
/* 182 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentMain(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 195 */     if (DBManager.update("update_comment_main", m))
/*     */     {
/* 197 */       if (stl != null)
/* 198 */         PublicTableDAO.insertSettingLogs("修改", "评论", (String)m.get("id"), stl);
/* 199 */       return true;
/*     */     }
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean cancelCommentMain(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 212 */     if (DBManager.update("cancel_comment_main", m))
/*     */     {
/* 214 */       PublicTableDAO.insertSettingLogs("撤消", "评论", (String)m.get("ids"), stl);
/* 215 */       return true;
/*     */     }
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCommentMain(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 228 */     if (DBManager.update("delete_comment_main", m))
/*     */     {
/* 230 */       PublicTableDAO.insertSettingLogs("删除", "评论", (String)m.get("ids"), stl);
/* 231 */       return true;
/*     */     }
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getParentStr(String id)
/*     */   {
/* 244 */     return DBManager.getString("getCommentMainParentStr", id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.comment.CommentDAO
 * JD-Core Version:    0.6.2
 */