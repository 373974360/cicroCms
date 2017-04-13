/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookClass;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appCom.guestbook.GuestBookDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class GuestBookClassManager
/*     */   implements ISyncCatch
/*     */ {
/*  19 */   private static TreeMap<Integer, GuestBookClass> cat_map = new TreeMap();
/*     */ 
/*     */   static {
/*  22 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  27 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  32 */     cat_map.clear();
/*     */     try {
/*  34 */       List l = GuestBookDAO.getAllGuestBookClassList();
/*  35 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  37 */         for (GuestBookClass cat : l)
/*     */         {
/*  39 */           cat_map.put(Integer.valueOf(cat.getClass_id()), cat);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  44 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadGuestBook()
/*     */   {
/*  50 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appCom.guestbook.GuestBookClassManager");
/*     */   }
/*     */ 
/*     */   public static List<GuestBookClass> getGuestBookClassList(String site_id, int cat_id)
/*     */   {
/*  55 */     List l = new ArrayList();
/*  56 */     Set s = cat_map.keySet();
/*  57 */     if ((s != null) && (s.size() > 0))
/*     */     {
/*  59 */       for (Integer i : s)
/*     */       {
/*  61 */         GuestBookClass cat = (GuestBookClass)cat_map.get(i);
/*  62 */         if ((cat.getCat_id() == cat_id) && (site_id.equals(cat.getSite_id())))
/*     */         {
/*  64 */           l.add(cat);
/*     */         }
/*     */       }
/*     */     }
/*  68 */     if ((l != null) && (l.size() > 0))
/*  69 */       Collections.sort(l, new GuestBookClassManager.GuestBookClassComparator());
/*  70 */     return l;
/*     */   }
/*     */ 
/*     */   public static GuestBookClass getGuestBookClassBean(int class_id)
/*     */   {
/*  75 */     if (cat_map.containsKey(Integer.valueOf(class_id))) {
/*  76 */       return (GuestBookClass)cat_map.get(Integer.valueOf(class_id));
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookClass(GuestBookClass cat, SettingLogsBean stl)
/*     */   {
/*  83 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.GUESTBOOK_CLASS_TABLE_NAME);
/*  84 */     cat.setClass_id(id);
/*  85 */     cat.setId(id);
/*  86 */     if (GuestBookDAO.insertGuestBookClass(cat, stl))
/*     */     {
/*  88 */       reloadGuestBook();
/*  89 */       return true;
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookClass(GuestBookClass cat, SettingLogsBean stl)
/*     */   {
/*  96 */     if (GuestBookDAO.updateGuestBookClass(cat, stl))
/*     */     {
/*  98 */       reloadGuestBook();
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookClass(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 112 */     if (GuestBookDAO.publishGuestBookClass(m, stl))
/*     */     {
/* 114 */       reloadGuestBook();
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGuestBookClass(String class_ids, SettingLogsBean stl)
/*     */   {
/* 128 */     if (GuestBookDAO.sortGuestBookClass(class_ids, stl))
/*     */     {
/* 130 */       reloadGuestBook();
/* 131 */       return true;
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookClass(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 144 */     if (GuestBookDAO.deleteGuestBookClass(m, stl))
/*     */     {
/* 146 */       reloadGuestBook();
/* 147 */       return true;
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookClassManager
 * JD-Core Version:    0.6.2
 */