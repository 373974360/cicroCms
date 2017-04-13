/*     */ package com.cicro.wcm.services.minlu;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.minlu.MingLuBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.minlu.MingLuDAO;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MingLuManager
/*     */   implements ISyncCatch
/*     */ {
/*  15 */   public static Map<String, MingLuBean> ml_map = new HashMap();
/*     */ 
/*  17 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  22 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  27 */     ml_map.clear();
/*  28 */     List l = MingLuDAO.getMingLuReleTemplateList();
/*  29 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  31 */       for (MingLuBean mlb : l)
/*     */       {
/*  33 */         ml_map.put(mlb.getSite_id(), mlb);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMingLu()
/*     */   {
/*  40 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.minlu.MingLuManager");
/*     */   }
/*     */ 
/*     */   public static int getNewMingLuID()
/*     */   {
/*  45 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.MINGLU_TEMPLATE_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static MingLuBean getMingLuBean(String site_id)
/*     */   {
/*  55 */     if (ml_map.containsKey(site_id))
/*     */     {
/*  57 */       return (MingLuBean)ml_map.get(site_id);
/*     */     }
/*     */ 
/*  60 */     MingLuBean mlb = MingLuDAO.getMingLuBean(site_id);
/*  61 */     if (mlb != null)
/*     */     {
/*  63 */       ml_map.put(mlb.getSite_id(), mlb);
/*  64 */       return mlb;
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertMingLuTemplate(MingLuBean ml, SettingLogsBean stl)
/*     */   {
/*  78 */     ml.setId(getNewMingLuID());
/*  79 */     if (MingLuDAO.insertMingLuTemplate(ml, stl))
/*     */     {
/*  81 */       reloadMingLu();
/*  82 */       return true;
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMingLuTemplate(MingLuBean ml, SettingLogsBean stl)
/*     */   {
/*  95 */     if (MingLuDAO.updateMingLuTemplate(ml, stl))
/*     */     {
/*  97 */       reloadMingLu();
/*  98 */       return true;
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMingLuTemplate(String site_id)
/*     */   {
/* 110 */     if (MingLuDAO.deleteMingLuTemplate(site_id))
/*     */     {
/* 112 */       reloadMingLu();
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.minlu.MingLuManager
 * JD-Core Version:    0.6.2
 */