/*     */ package com.cicro.wcm.dao.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookBean;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookClass;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCount;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GuestBookDAO
/*     */ {
/*     */   public static List<GuestBookCategory> getAllGuestBookCategoryList()
/*     */   {
/*  34 */     return DBManager.queryFList("getAllGuestBookCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookCategory(GuestBookCategory cat, SettingLogsBean stl)
/*     */   {
/*  39 */     if (DBManager.insert("insert_guestbook_category", cat))
/*     */     {
/*  41 */       PublicTableDAO.insertSettingLogs("添加", "留言分类", cat.getCat_id(), stl);
/*  42 */       return true;
/*     */     }
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookCategory(GuestBookCategory cat, SettingLogsBean stl)
/*     */   {
/*  49 */     if (DBManager.update("update_guestbook_category", cat))
/*     */     {
/*  51 */       PublicTableDAO.insertSettingLogs("修改", "留言分类", cat.getCat_id(), stl);
/*  52 */       return true;
/*     */     }
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  65 */     if (DBManager.update("publish_guestbook_category", m))
/*     */     {
/*  67 */       PublicTableDAO.insertSettingLogs("彻底删除", "留言分类", (String)m.get("cat_ids"), stl);
/*  68 */       return true;
/*     */     }
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGuestBookCategory(String cat_ids, SettingLogsBean stl)
/*     */   {
/*  81 */     if ((cat_ids != null) && (!"".equals(cat_ids))) {
/*     */       try
/*     */       {
/*  84 */         String[] tempA = cat_ids.split(",");
/*  85 */         Map m = new HashMap();
/*  86 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  88 */           m.put("sort_id", i + 1);
/*  89 */           m.put("cat_id", tempA[i]);
/*  90 */           DBManager.update("sort_guestbook_category", m);
/*     */         }
/*  92 */         PublicTableDAO.insertSettingLogs("修改", "留言分类排序", (String)m.get("cat_ids"), stl);
/*  93 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/*  96 */         e.printStackTrace();
/*  97 */         return false;
/*     */       }
/*     */     }
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 111 */     if (DBManager.delete("delete_guestbook_category", m))
/*     */     {
/* 113 */       PublicTableDAO.insertSettingLogs("修改", "留言分类", (String)m.get("cat_ids"), stl);
/* 114 */       return true;
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookClass> getAllGuestBookClassList()
/*     */   {
/* 129 */     return DBManager.queryFList("getAllGuestBookClassList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookClass(GuestBookClass cat, SettingLogsBean stl)
/*     */   {
/* 134 */     if (DBManager.insert("insert_guestbook_class", cat))
/*     */     {
/* 136 */       PublicTableDAO.insertSettingLogs("添加", "留言类别", cat.getCat_id(), stl);
/* 137 */       return true;
/*     */     }
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookClass(GuestBookClass cat, SettingLogsBean stl)
/*     */   {
/* 144 */     if (DBManager.update("update_guestbook_class", cat))
/*     */     {
/* 146 */       PublicTableDAO.insertSettingLogs("修改", "留言类别", cat.getCat_id(), stl);
/* 147 */       return true;
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookClass(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 160 */     if (DBManager.update("publish_guestbook_class", m))
/*     */     {
/* 162 */       PublicTableDAO.insertSettingLogs("彻底删除", "留言类别", (String)m.get("cat_ids"), stl);
/* 163 */       return true;
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGuestBookClass(String class_ids, SettingLogsBean stl)
/*     */   {
/* 176 */     if ((class_ids != null) && (!"".equals(class_ids))) {
/*     */       try
/*     */       {
/* 179 */         String[] tempA = class_ids.split(",");
/* 180 */         Map m = new HashMap();
/* 181 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 183 */           m.put("sort_id", i + 1);
/* 184 */           m.put("class_id", tempA[i]);
/* 185 */           DBManager.update("sort_guestbook_class", m);
/*     */         }
/* 187 */         PublicTableDAO.insertSettingLogs("修改", "留言类别排序", class_ids, stl);
/* 188 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 191 */         e.printStackTrace();
/* 192 */         return false;
/*     */       }
/*     */     }
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookClass(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 206 */     if (DBManager.delete("delete_guestbook_class", m))
/*     */     {
/* 208 */       PublicTableDAO.insertSettingLogs("修改", "留言类别", (String)m.get("cat_ids"), stl);
/* 209 */       return true;
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookSub> getAllGuestBookSubList()
/*     */   {
/* 224 */     return DBManager.queryFList("getAllGuestBookSubList", "");
/*     */   }
/*     */ 
/*     */   public static List<GuestBookSub> getGuestBookSubListForDB(Map<String, String> m)
/*     */   {
/* 235 */     return DBManager.queryFList("getGuestBookSubListForDB", m);
/*     */   }
/*     */ 
/*     */   public static String getGuestBookSubCount(Map<String, String> m)
/*     */   {
/* 240 */     return DBManager.getString("getGuestBookSubCount", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookSub(GuestBookSub gbs, SettingLogsBean stl)
/*     */   {
/* 251 */     if (DBManager.insert("insert_guestbook_sub", gbs))
/*     */     {
/* 253 */       PublicTableDAO.insertSettingLogs("添加", "留言板主题", gbs.getGbs_id(), stl);
/* 254 */       return true;
/*     */     }
/* 256 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookSub(GuestBookSub gbs, SettingLogsBean stl)
/*     */   {
/* 267 */     if (DBManager.update("update_guestbook_sub", gbs))
/*     */     {
/* 269 */       PublicTableDAO.insertSettingLogs("修改", "留言板主题", gbs.getGbs_id(), stl);
/* 270 */       return true;
/*     */     }
/* 272 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookSub(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 283 */     if (DBManager.update("publish_guestbook_sub", m))
/*     */     {
/* 285 */       PublicTableDAO.insertSettingLogs("彻底删除", "留言板主题发布状态", (String)m.get("gbs_ids"), stl);
/* 286 */       return true;
/*     */     }
/* 288 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookSub(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 299 */     if (DBManager.delete("delete_guestbook_sub", m))
/*     */     {
/* 301 */       PublicTableDAO.insertSettingLogs("修改", "留言板主题", (String)m.get("gbs_ids"), stl);
/* 302 */       return true;
/*     */     }
/* 304 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getGuestbookCount(Map<String, String> m)
/*     */   {
/* 316 */     return DBManager.getString("getGuestbookCount", m);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookBean> getGuestbookList(Map<String, String> m)
/*     */   {
/* 327 */     return DBManager.queryFList("getGuestbookList", m);
/*     */   }
/*     */ 
/*     */   public static GuestBookBean getGuestBookBean(String id)
/*     */   {
/* 337 */     return (GuestBookBean)DBManager.queryFObj("getGuestBookBean", id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBook(GuestBookBean gb)
/*     */   {
/* 347 */     return DBManager.insert("insert_guestbook", gb);
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBook(GuestBookBean gb, SettingLogsBean stl)
/*     */   {
/* 358 */     if (DBManager.update("update_guestbook", gb))
/*     */     {
/* 360 */       PublicTableDAO.insertSettingLogs("修改", "留言板内容", gb.getId(), stl);
/* 361 */       return true;
/*     */     }
/* 363 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBook(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 374 */     if (DBManager.update("publish_guestbook", m))
/*     */     {
/* 376 */       PublicTableDAO.insertSettingLogs("彻底删除", "留言板发布状态", (String)m.get("ids"), stl);
/* 377 */       return true;
/*     */     }
/* 379 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean replayGuestBook(GuestBookBean gb, SettingLogsBean stl)
/*     */   {
/* 390 */     if (DBManager.update("replay_guestbook", gb))
/*     */     {
/* 392 */       PublicTableDAO.insertSettingLogs("处理", "留言板内容", gb.getId(), stl);
/* 393 */       return true;
/*     */     }
/* 395 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean clickGuestBook(String id)
/*     */   {
/* 400 */     return DBManager.update("click_guestbook", id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBook(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 411 */     if (DBManager.update("delete_guestbook", m))
/*     */     {
/* 413 */       PublicTableDAO.insertSettingLogs("处理", "留言板内容", (String)m.get("id"), stl);
/* 414 */       return true;
/*     */     }
/* 416 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCount> getGuestbookStatistics(Map<String, String> m)
/*     */   {
/* 430 */     return DBManager.queryFList("getGuestbookStatistics", m);
/*     */   }
/*     */ 
/*     */   public static String getGBCategoryStatistics(Map<String, String> m)
/*     */   {
/* 440 */     return DBManager.getString("getGuestbookCount_catgory", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appCom.guestbook.GuestBookDAO
 * JD-Core Version:    0.6.2
 */