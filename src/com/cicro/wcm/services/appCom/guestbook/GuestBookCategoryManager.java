/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appCom.guestbook.GuestBookDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class GuestBookCategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  20 */   private static TreeMap<Integer, GuestBookCategory> cat_map = new TreeMap();
/*     */ 
/*     */   static {
/*  23 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  28 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  33 */     cat_map.clear();
/*     */     try {
/*  35 */       List l = GuestBookDAO.getAllGuestBookCategoryList();
/*  36 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  38 */         for (GuestBookCategory cat : l)
/*     */         {
/*  40 */           cat_map.put(Integer.valueOf(cat.getCat_id()), cat);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  45 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadGuestBook()
/*     */   {
/*  51 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appCom.guestbook.GuestBookCategoryManager");
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCategory> getGuestBookCategoryList(String site_id)
/*     */   {
/*  56 */     List l = new ArrayList();
/*  57 */     Set s = cat_map.keySet();
/*  58 */     if ((s != null) && (s.size() > 0))
/*     */     {
/*  60 */       for (Integer i : s)
/*     */       {
/*  62 */         GuestBookCategory cat = ((GuestBookCategory)cat_map.get(i)).clone();
/*  63 */         System.out.println(cat);
/*  64 */         if (site_id.equals(cat.getSite_id()))
/*     */         {
/*  66 */           cat.setDescription("");
/*  67 */           l.add(cat);
/*     */         }
/*     */       }
/*     */     }
/*  71 */     if ((l != null) && (l.size() > 0))
/*  72 */       Collections.sort(l, new GuestBookCategoryManager.GuestBookCategoryComparator());
/*  73 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCategory> getGuestBookCategoryListForIDS(String site_id, String cat_ids)
/*     */   {
/*  78 */     List l = new ArrayList();
/*  79 */     if ((cat_ids != null) && (!"".equals(cat_ids)))
/*     */     {
/*  81 */       String[] tempA = cat_ids.split(",");
/*  82 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  84 */         int c_id = Integer.parseInt(tempA[i]);
/*  85 */         if (cat_map.containsKey(Integer.valueOf(c_id)))
/*     */         {
/*  87 */           l.add((GuestBookCategory)cat_map.get(Integer.valueOf(c_id)));
/*     */         }
/*     */       }
/*     */     }
/*  91 */     return l;
/*     */   }
/*     */ 
/*     */   public static GuestBookCategory getGuestBookCategoryBean(int cat_id)
/*     */   {
/*  96 */     if (cat_map.containsKey(Integer.valueOf(cat_id))) {
/*  97 */       return (GuestBookCategory)cat_map.get(Integer.valueOf(cat_id));
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBookCategory(GuestBookCategory cat, SettingLogsBean stl)
/*     */   {
/* 104 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.GUESTBOOK_CATEGORY_TABLE_NAME);
/* 105 */     cat.setCat_id(id);
/* 106 */     cat.setId(id);
/* 107 */     if (GuestBookDAO.insertGuestBookCategory(cat, stl))
/*     */     {
/* 109 */       reloadGuestBook();
/* 110 */       return true;
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBookCategory(GuestBookCategory cat, SettingLogsBean stl)
/*     */   {
/* 117 */     if (GuestBookDAO.updateGuestBookCategory(cat, stl))
/*     */     {
/* 119 */       reloadGuestBook();
/* 120 */       return true;
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBookCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 133 */     if (GuestBookDAO.publishGuestBookCategory(m, stl))
/*     */     {
/* 135 */       reloadGuestBook();
/* 136 */       return true;
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGuestBookCategory(String cat_ids, SettingLogsBean stl)
/*     */   {
/* 149 */     if (GuestBookDAO.sortGuestBookCategory(cat_ids, stl))
/*     */     {
/* 151 */       reloadGuestBook();
/* 152 */       return true;
/*     */     }
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBookCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 165 */     if (GuestBookDAO.deleteGuestBookCategory(m, stl))
/*     */     {
/* 167 */       reloadGuestBook();
/* 168 */       return true;
/*     */     }
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 192 */     System.out.println(getGuestBookCategoryList("HIWCMdemo"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookCategoryManager
 * JD-Core Version:    0.6.2
 */