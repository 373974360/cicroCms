/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookBean;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookClass;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCount;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class GuestBookRPC
/*     */ {
/*     */   public static List<GuestBookCategory> getGuestBookCategoryList(String site_id)
/*     */   {
/*  20 */     return GuestBookCategoryManager.getGuestBookCategoryList(site_id);
/*     */   }
/*     */ 
/*     */   public static GuestBookCategory getGuestBookCategoryBean(int cat_id)
/*     */   {
/*  25 */     return GuestBookCategoryManager.getGuestBookCategoryBean(cat_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookCategory(GuestBookCategory cat, HttpServletRequest request)
/*     */   {
/*  30 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  31 */     if (stl != null)
/*     */     {
/*  33 */       return GuestBookCategoryManager.insertGuestBookCategory(cat, stl);
/*     */     }
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookCategory(GuestBookCategory cat, HttpServletRequest request)
/*     */   {
/*  40 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  41 */     if (stl != null)
/*     */     {
/*  43 */       return GuestBookCategoryManager.updateGuestBookCategory(cat, stl);
/*     */     }
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookCategory(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  50 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  51 */     if (stl != null)
/*     */     {
/*  53 */       return GuestBookCategoryManager.publishGuestBookCategory(m, stl);
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGuestBookCategory(String cat_ids, HttpServletRequest request)
/*     */   {
/*  60 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  61 */     if (stl != null)
/*     */     {
/*  63 */       return GuestBookCategoryManager.sortGuestBookCategory(cat_ids, stl);
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookCategory(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  76 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  77 */     if (stl != null)
/*     */     {
/*  79 */       return GuestBookCategoryManager.deleteGuestBookCategory(m, stl);
/*     */     }
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookClass> getGuestBookClassList(String site_id, int cat_id)
/*     */   {
/*  88 */     return GuestBookClassManager.getGuestBookClassList(site_id, cat_id);
/*     */   }
/*     */ 
/*     */   public static GuestBookClass getGuestBookClassBean(int cat_id)
/*     */   {
/*  93 */     return GuestBookClassManager.getGuestBookClassBean(cat_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookClass(GuestBookClass cat, HttpServletRequest request)
/*     */   {
/*  98 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  99 */     if (stl != null)
/*     */     {
/* 101 */       return GuestBookClassManager.insertGuestBookClass(cat, stl);
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookClass(GuestBookClass cat, HttpServletRequest request)
/*     */   {
/* 108 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 109 */     if (stl != null)
/*     */     {
/* 111 */       return GuestBookClassManager.updateGuestBookClass(cat, stl);
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookClass(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 118 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 119 */     if (stl != null)
/*     */     {
/* 121 */       return GuestBookClassManager.publishGuestBookClass(m, stl);
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGuestBookClass(String cat_ids, HttpServletRequest request)
/*     */   {
/* 128 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 129 */     if (stl != null)
/*     */     {
/* 131 */       return GuestBookClassManager.sortGuestBookClass(cat_ids, stl);
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookClass(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 144 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 145 */     if (stl != null)
/*     */     {
/* 147 */       return GuestBookClassManager.deleteGuestBookClass(m, stl);
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookSub> getGuestBookSubListForDB(Map<String, String> m)
/*     */   {
/* 156 */     return GuestBookSubManager.getGuestBookSubListForDB(m);
/*     */   }
/*     */ 
/*     */   public static String getGuestBookSubCount(Map<String, String> m)
/*     */   {
/* 166 */     return GuestBookSubManager.getGuestBookSubCount(m);
/*     */   }
/*     */ 
/*     */   public static GuestBookSub getGuestBookSub(int sub_id)
/*     */   {
/* 176 */     return GuestBookSubManager.getGuestBookSub(sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookSub(GuestBookSub gbs, HttpServletRequest request)
/*     */   {
/* 187 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 188 */     if (stl != null)
/*     */     {
/* 190 */       return GuestBookSubManager.insertGuestBookSub(gbs, stl);
/*     */     }
/*     */ 
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookSub(GuestBookSub gbs, HttpServletRequest request)
/*     */   {
/* 204 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 205 */     if (stl != null)
/*     */     {
/* 207 */       return GuestBookSubManager.updateGuestBookSub(gbs, stl);
/*     */     }
/*     */ 
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookSub(String sub_ids, String publish_status, HttpServletRequest request)
/*     */   {
/* 222 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 223 */     if (stl != null)
/*     */     {
/* 225 */       return GuestBookSubManager.publishGuestBookSub(sub_ids, publish_status, stl);
/*     */     }
/*     */ 
/* 228 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookSub(String sub_ids, HttpServletRequest request)
/*     */   {
/* 239 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 240 */     if (stl != null)
/*     */     {
/* 242 */       return GuestBookSubManager.deleteGuestBookSub(sub_ids, stl);
/*     */     }
/*     */ 
/* 245 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getGuestbookCount(Map<String, String> m)
/*     */   {
/* 255 */     return GuestBookManager.getGuestbookCount(m);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookBean> getGuestbookList(Map<String, String> m)
/*     */   {
/* 265 */     return GuestBookManager.getGuestbookList(m);
/*     */   }
/*     */ 
/*     */   public static GuestBookBean getGuestBookBean(String id)
/*     */   {
/* 275 */     return GuestBookManager.getGuestBookBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBook(GuestBookBean gb, HttpServletRequest request)
/*     */   {
/* 286 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 287 */     if (stl != null)
/*     */     {
/* 289 */       return GuestBookManager.updateGuestBook(gb, stl);
/*     */     }
/*     */ 
/* 292 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean replayGuestBook(GuestBookBean gb, HttpServletRequest request)
/*     */   {
/* 303 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 304 */     if (stl != null)
/*     */     {
/* 306 */       return GuestBookManager.replayGuestBook(gb, stl);
/*     */     }
/*     */ 
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBook(String ids, String publish_status, HttpServletRequest request)
/*     */   {
/* 321 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 322 */     if (stl != null)
/*     */     {
/* 324 */       return GuestBookManager.publishGuestBook(ids, publish_status, stl);
/*     */     }
/*     */ 
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean clickGuestBook(String id)
/*     */   {
/* 332 */     return GuestBookManager.clickGuestBook(id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBook(String ids, HttpServletRequest request)
/*     */   {
/* 343 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 344 */     if (stl != null)
/*     */     {
/* 346 */       return GuestBookManager.deleteGuestBook(ids, stl);
/*     */     }
/*     */ 
/* 349 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCount> getGuestbookStatistics(Map<String, String> m)
/*     */   {
/* 359 */     return GuestBookManager.getGuestbookStatistics(m);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCount> getGBCategoryStatistics(Map<String, String> m)
/*     */   {
/* 369 */     return GuestBookManager.getGBCategoryStatistics(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookRPC
 * JD-Core Version:    0.6.2
 */