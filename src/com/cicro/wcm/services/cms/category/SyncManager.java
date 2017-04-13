/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.SyncBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.cms.category.SyncDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SyncManager
/*     */   implements ISyncCatch
/*     */ {
/*  22 */   private static List<SyncBean> sync_list = new ArrayList();
/*     */ 
/*     */   static
/*     */   {
/*  26 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  31 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  36 */     sync_list.clear();
/*  37 */     sync_list = SyncDAO.getAllSyncBeanList();
/*     */   }
/*     */ 
/*     */   public static void reloadSync()
/*     */   {
/*  45 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.SyncManager");
/*     */   }
/*     */ 
/*     */   public static List<SyncBean> getToInfoCategoryList(String s_site_id, int cat_id)
/*     */   {
/*  56 */     List s_list = new ArrayList();
/*  57 */     if ((sync_list != null) && (sync_list.size() > 0))
/*     */     {
/*  59 */       for (SyncBean sb : sync_list)
/*     */       {
/*  61 */         if ((s_site_id.equals(sb.getS_site_id())) && (sb.getS_cat_id() == cat_id) && (sb.getOrientation() == 1))
/*  62 */           s_list.add(sb);
/*     */       }
/*     */     }
/*  65 */     return s_list;
/*     */   }
/*     */ 
/*     */   public static List<SyncBean> getSyncListBySiteCatID(String s_site_id, int cat_id)
/*     */   {
/*  76 */     List s_list = new ArrayList();
/*  77 */     if ((sync_list != null) && (sync_list.size() > 0))
/*     */     {
/*  79 */       for (SyncBean sb : sync_list)
/*     */       {
/*  81 */         if ((s_site_id.equals(sb.getS_site_id())) && (sb.getS_cat_id() == cat_id) && (sb.getOrientation() == 0))
/*  82 */           s_list.add(sb);
/*     */       }
/*     */     }
/*  85 */     return s_list;
/*     */   }
/*     */ 
/*     */   public static List<SyncBean> getSyncCatListBySiteCatID(String t_site_id, int t_cat_id)
/*     */   {
/*  96 */     List s_list = new ArrayList();
/*  97 */     if ((sync_list != null) && (sync_list.size() > 0))
/*     */     {
/*  99 */       for (SyncBean sb : sync_list)
/*     */       {
/* 101 */         if ((t_site_id.equals(sb.getT_site_id())) && (sb.getT_cat_id() == t_cat_id) && (sb.getOrientation() == 0))
/* 102 */           s_list.add(sb);
/*     */       }
/*     */     }
/* 105 */     return s_list;
/*     */   }
/*     */ 
/*     */   public static boolean insertSync(List<SyncBean> sList, String s_cat_id, String s_site_id, String orientation)
/*     */   {
/* 118 */     SyncDAO.deleteSync(s_site_id, s_cat_id, orientation);
/* 119 */     if ((sList != null) && (sList.size() > 0)) {
/*     */       try
/*     */       {
/* 122 */         for (SyncBean sb : sList)
/*     */         {
/* 124 */           sb.setOrientation(Integer.parseInt(orientation));
/* 125 */           SyncDAO.insertSync(sb);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 129 */         return false;
/*     */       }
/*     */     }
/* 132 */     reloadSync();
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSyncForCatID(String site_id, String cat_ids)
/*     */   {
/* 145 */     if (SyncDAO.deleteSyncForCatID(site_id, cat_ids))
/*     */     {
/* 147 */       reloadSync();
/* 148 */       return true;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 155 */     System.out.println(((SyncBean)getSyncCatListBySiteCatID("HIWCMkjj", 340).get(0)).getT_site_id());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.SyncManager
 * JD-Core Version:    0.6.2
 */