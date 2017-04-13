/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareCategoryBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareReleUser;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.ware.WareReleUserDAO;
/*     */ import com.cicro.wcm.services.org.group.GroupManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class WareReleUserManager
/*     */   implements ISyncCatch
/*     */ {
/*  30 */   private static Map<Integer, WareReleUser> wru_map = new HashMap();
/*     */ 
/*     */   static {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  38 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  43 */     wru_map.clear();
/*  44 */     List wr_list = WareReleUserDAO.getWareReleUserList();
/*  45 */     if ((wr_list != null) && (wr_list.size() > 0))
/*     */     {
/*  47 */       for (int i = 0; i < wr_list.size(); i++)
/*     */       {
/*  49 */         wru_map.put(Integer.valueOf(((WareReleUser)wr_list.get(i)).getId()), (WareReleUser)wr_list.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadWareReleUser()
/*     */   {
/*  56 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.ware.WareReleUserManager");
/*     */   }
/*     */ 
/*     */   public static List<WareReleUser> getWareReleUserListByCat(int wcat_id, String site_id)
/*     */   {
/*  67 */     List r_list = new ArrayList();
/*  68 */     Set rSet = wru_map.keySet();
/*  69 */     for (Iterator localIterator = rSet.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/*  71 */       WareReleUser wru = (WareReleUser)wru_map.get(Integer.valueOf(i));
/*  72 */       if ((wru.getWcat_id() == wcat_id) && (site_id.equals(wru.getSite_id())))
/*     */       {
/*  74 */         r_list.add(wru);
/*     */       }
/*     */     }
/*  77 */     return r_list;
/*     */   }
/*     */ 
/*     */   public static Set<WareCategoryBean> getWCatIDByUser(int user_id, String site_id)
/*     */   {
/*  90 */     String grup_ids = GroupManager.getGroupIDSByUserID(user_id);
/*  91 */     if ((grup_ids != null) && (!"".equals(grup_ids)))
/*  92 */       grup_ids = "," + grup_ids + ",";
/*  93 */     Set c_set = new HashSet();
/*  94 */     Set rSet = wru_map.keySet();
/*     */ 
/*  96 */     for (Iterator localIterator = rSet.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/*  98 */       WareReleUser wru = (WareReleUser)wru_map.get(Integer.valueOf(i));
/*     */ 
/* 100 */       if ((wru.getPriv_type() == 0) && (user_id == wru.getPrv_id()) && (site_id.equals(wru.getSite_id())))
/*     */       {
/* 102 */         c_set.add(WareCategoryManager.getWareCteBeanByWID(wru.getWcat_id(), site_id));
/*     */       }
/* 104 */       if ((wru.getPriv_type() == 1) && (grup_ids.contains(wru.getPrv_id())) && (site_id.equals(wru.getSite_id())))
/*     */       {
/* 106 */         c_set.add(WareCategoryManager.getWareCteBeanByWID(wru.getWcat_id(), site_id));
/*     */       }
/*     */     }
/* 109 */     return c_set;
/*     */   }
/*     */ 
/*     */   public static boolean insertWareReleUserByCat(int wcat_id, String usre_ids, String group_ids, String app_id, String site_id, SettingLogsBean stl)
/*     */   {
/* 125 */     if (WareReleUserDAO.insertWareReleUserByCat(wcat_id, usre_ids, group_ids, app_id, site_id, stl))
/*     */     {
/* 127 */       reloadWareReleUser();
/* 128 */       return true;
/*     */     }
/*     */ 
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWRUByCat(String wcat_id, String site_id)
/*     */   {
/* 142 */     if (WareReleUserDAO.deleteWRUByCat(wcat_id, site_id))
/*     */     {
/* 144 */       reloadWareReleUser();
/* 145 */       return true;
/*     */     }
/*     */ 
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareReleUserManager
 * JD-Core Version:    0.6.2
 */