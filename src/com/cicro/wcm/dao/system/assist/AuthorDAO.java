/*     */ package com.cicro.wcm.dao.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.AuthorBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AuthorDAO
/*     */ {
/*     */   public static List<AuthorBean> getAllAuthorList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllAuthorList", "");
/*     */   }
/*     */ 
/*     */   public static AuthorBean getAuthorBean(int author_id)
/*     */   {
/*  42 */     return (AuthorBean)DBManager.queryFObj("getAuthorBean", Integer.valueOf(author_id));
/*     */   }
/*     */ 
/*     */   public static String getAuthorCount(Map<String, String> con_map)
/*     */   {
/*  52 */     return DBManager.getString("getAuthorCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<AuthorBean> getAuthorListForDB(Map<String, String> con_map)
/*     */   {
/*  63 */     return DBManager.queryFList("getAuthorListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateAuthor(AuthorBean author, SettingLogsBean stl)
/*     */   {
/*  72 */     if (DBManager.update("updateAuthor", author))
/*     */     {
/*  74 */       PublicTableDAO.insertSettingLogs("修改", "作者", author.getAuthor_id(), stl);
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addAuthor(AuthorBean author, SettingLogsBean stl)
/*     */   {
/*  86 */     int authorId = PublicTableDAO.getIDByTableName(PublicTableDAO.AUTHOR_TABLE_NAME);
/*  87 */     author.setAuthor_id(authorId);
/*  88 */     if (DBManager.insert("insertAuthor", author))
/*     */     {
/*  90 */       PublicTableDAO.insertSettingLogs("添加", "作者", authorId, stl);
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delAuthor(String author_ids, SettingLogsBean stl)
/*     */   {
/* 103 */     Map map = new HashMap();
/* 104 */     map.put("author_ids", author_ids);
/* 105 */     if (DBManager.delete("deleteAuthor", map))
/*     */     {
/* 107 */       PublicTableDAO.insertSettingLogs("删除", "作者", author_ids, stl);
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.assist.AuthorDAO
 * JD-Core Version:    0.6.2
 */