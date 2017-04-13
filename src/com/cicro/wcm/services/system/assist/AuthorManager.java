/*     */ package com.cicro.wcm.services.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.AuthorBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.assist.AuthorDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class AuthorManager
/*     */   implements ISyncCatch
/*     */ {
/*  21 */   private static Map<Integer, AuthorBean> author_map = new HashMap();
/*     */ 
/*     */   static {
/*  24 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  34 */     List author_list = AuthorDAO.getAllAuthorList();
/*  35 */     author_map.clear();
/*  36 */     if ((author_list != null) && (author_list.size() > 0))
/*     */     {
/*  38 */       for (int i = 0; i < author_list.size(); i++)
/*  39 */         author_map.put(Integer.valueOf(((AuthorBean)author_list.get(i)).getAuthor_id()), (AuthorBean)author_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadAuthor()
/*     */   {
/*  46 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.assist.AuthorManager");
/*     */   }
/*     */ 
/*     */   public static AuthorBean getAuthorBean(int author_id)
/*     */   {
/*  56 */     if (author_map.containsKey(Integer.valueOf(author_id)))
/*     */     {
/*  58 */       return (AuthorBean)author_map.get(Integer.valueOf(author_id));
/*     */     }
/*     */ 
/*  61 */     AuthorBean authorb = AuthorDAO.getAuthorBean(author_id);
/*  62 */     if (authorb != null)
/*     */     {
/*  64 */       author_map.put(Integer.valueOf(author_id), authorb);
/*  65 */       return authorb;
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getAuthorCount(Map<String, String> con_map)
/*     */   {
/*  78 */     return AuthorDAO.getAuthorCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateAuthor(AuthorBean author, SettingLogsBean stl)
/*     */   {
/*  87 */     if (AuthorDAO.updateAuthor(author, stl)) {
/*  88 */       author_map.remove(Integer.valueOf(author.getAuthor_id()));
/*  89 */       author_map.put(Integer.valueOf(author.getAuthor_id()), author);
/*  90 */       reloadAuthor();
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addAuthor(AuthorBean author, SettingLogsBean stl)
/*     */   {
/* 102 */     if (AuthorDAO.addAuthor(author, stl)) {
/* 103 */       author_map.put(Integer.valueOf(author.getAuthor_id()), author);
/* 104 */       reloadAuthor();
/* 105 */       return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<AuthorBean> getAllAuthorList()
/*     */   {
/* 116 */     Set set = author_map.keySet();
/* 117 */     List list = new ArrayList();
/* 118 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 119 */       list.add((AuthorBean)author_map.get(Integer.valueOf(i)));
/*     */     }
/* 121 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<AuthorBean> getAuthorListForDB(Map<String, String> con_map)
/*     */   {
/* 131 */     return AuthorDAO.getAuthorListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delAuthorById(String author_ids, SettingLogsBean stl)
/*     */   {
/* 140 */     if (AuthorDAO.delAuthor(author_ids, stl)) {
/* 141 */       if (author_ids != null) {
/* 142 */         if (author_ids.indexOf(",") != -1) {
/* 143 */           for (String id : author_ids.split(","))
/* 144 */             author_map.remove(Integer.valueOf(Integer.parseInt(id)));
/*     */         }
/*     */         else {
/* 147 */           author_map.remove(Integer.valueOf(Integer.parseInt(author_ids)));
/*     */         }
/*     */       }
/* 150 */       reloadAuthor();
/* 151 */       return true;
/*     */     }
/* 153 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.assist.AuthorManager
 * JD-Core Version:    0.6.2
 */