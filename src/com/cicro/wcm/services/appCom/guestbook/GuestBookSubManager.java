/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appCom.guestbook.GuestBookDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GuestBookSubManager
/*     */   implements ISyncCatch
/*     */ {
/*  28 */   public static Map<Integer, GuestBookSub> gbs_map = new HashMap();
/*     */ 
/*     */   static {
/*  31 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  36 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  41 */     gbs_map.clear();
/*     */     try {
/*  43 */       List l = GuestBookDAO.getAllGuestBookSubList();
/*  44 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  46 */         for (GuestBookSub gbs : l)
/*     */         {
/*  48 */           gbs_map.put(Integer.valueOf(gbs.getGbs_id()), gbs);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  53 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadGuestBook()
/*     */   {
/*  59 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appCom.guestbook.GuestBookSubManager");
/*     */   }
/*     */ 
/*     */   public static List<GuestBookSub> getGuestBookSubListForDB(Map<String, String> m)
/*     */   {
/*  69 */     return GuestBookDAO.getGuestBookSubListForDB(m);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookSub> getGuestBookSubListForIDS(String site_id, String gbs_ids)
/*     */   {
/*  80 */     List l = new ArrayList();
/*  81 */     if ((gbs_ids != null) && (!"".equals(gbs_ids)))
/*     */     {
/*  83 */       String[] tempA = gbs_ids.split(",");
/*  84 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  86 */         int g_id = Integer.parseInt(tempA[i]);
/*  87 */         if (gbs_map.containsKey(Integer.valueOf(g_id)))
/*     */         {
/*  89 */           l.add((GuestBookSub)gbs_map.get(Integer.valueOf(g_id)));
/*     */         }
/*     */       }
/*     */     }
/*  93 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getGuestBookSubCount(Map<String, String> m)
/*     */   {
/* 103 */     return GuestBookDAO.getGuestBookSubCount(m);
/*     */   }
/*     */ 
/*     */   public static GuestBookSub getGuestBookSub(int gbs_id)
/*     */   {
/* 113 */     return (GuestBookSub)gbs_map.get(Integer.valueOf(gbs_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookSub(GuestBookSub gbs, SettingLogsBean stl)
/*     */   {
/* 124 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.GUESTBOOKSUB__TABLE_NAME);
/* 125 */     gbs.setId(id);
/* 126 */     gbs.setGbs_id(id);
/* 127 */     gbs.setAdd_time(DateUtil.getCurrentDateTime());
/* 128 */     if (gbs.getPublish_status() == 1)
/* 129 */       gbs.setPublish_time(DateUtil.getCurrentDateTime());
/* 130 */     if (GuestBookDAO.insertGuestBookSub(gbs, stl))
/*     */     {
/* 132 */       reloadGuestBook();
/* 133 */       return true;
/*     */     }
/*     */ 
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookSub(GuestBookSub gbs, SettingLogsBean stl)
/*     */   {
/* 147 */     if (gbs.getPublish_status() == 1)
/* 148 */       gbs.setPublish_time(DateUtil.getCurrentDateTime());
/*     */     else
/* 150 */       gbs.setPublish_time("");
/* 151 */     if (GuestBookDAO.updateGuestBookSub(gbs, stl))
/*     */     {
/* 153 */       reloadGuestBook();
/* 154 */       return true;
/*     */     }
/*     */ 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookSub(String gbs_ids, SettingLogsBean stl)
/*     */   {
/* 168 */     Map m = new HashMap();
/* 169 */     m.put("gbs_ids", gbs_ids);
/* 170 */     if (GuestBookDAO.deleteGuestBookSub(m, stl))
/*     */     {
/* 172 */       reloadGuestBook();
/*     */ 
/* 174 */       GuestBookDAO.deleteGuestBook(m, null);
/* 175 */       return true;
/* 176 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookSub(String gbs_ids, String publish_status, SettingLogsBean stl)
/*     */   {
/* 188 */     Map m = new HashMap();
/* 189 */     m.put("gbs_ids", gbs_ids);
/* 190 */     m.put("publish_status", publish_status);
/* 191 */     if ("1".equals(publish_status))
/* 192 */       m.put("publish_time", DateUtil.getCurrentDateTime());
/*     */     else
/* 194 */       m.put("publish_time", "");
/* 195 */     if (GuestBookDAO.publishGuestBookSub(m, stl))
/*     */     {
/* 197 */       reloadGuestBook();
/* 198 */       return true;
/*     */     }
/*     */ 
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookSubForSite(String site_id)
/*     */   {
/* 212 */     Map m = new HashMap();
/* 213 */     m.put("site_id", site_id);
/* 214 */     if (GuestBookDAO.deleteGuestBookSub(m, null))
/*     */     {
/* 216 */       reloadGuestBook();
/*     */ 
/* 218 */       GuestBookDAO.deleteGuestBook(m, null);
/* 219 */       return true;
/* 220 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookSubManager
 * JD-Core Version:    0.6.2
 */