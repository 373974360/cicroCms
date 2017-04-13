/*     */ package com.cicro.wcm.services.appeal.purpose;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.purpose.PurposeDao;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class PurposeManager
/*     */   implements ISyncCatch
/*     */ {
/*  20 */   private static Map<String, PurposeBean> pur_map = new TreeMap();
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
/*  33 */     pur_map.clear();
/*  34 */     List lt = PurposeDao.getAllPurposeList();
/*  35 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  37 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  39 */         pur_map.put(((PurposeBean)lt.get(i)).getPur_id(), (PurposeBean)lt.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMap()
/*     */   {
/*  48 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.purpose.PurposeManager");
/*     */   }
/*     */ 
/*     */   public static List<PurposeBean> getAllPurposeList()
/*     */   {
/*  56 */     List ret = new ArrayList();
/*  57 */     Iterator it = pur_map.values().iterator();
/*  58 */     while (it.hasNext())
/*     */     {
/*  60 */       ret.add((PurposeBean)it.next());
/*     */     }
/*  62 */     Collections.sort(ret, new PurposeManager.CateComparator());
/*  63 */     return ret;
/*     */   }
/*     */ 
/*     */   public static PurposeBean getPurposeByID(String id)
/*     */   {
/*  72 */     PurposeBean mcb = (PurposeBean)pur_map.get(id);
/*  73 */     return mcb;
/*     */   }
/*     */ 
/*     */   public static int getAppPurposeID()
/*     */   {
/*  82 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_PROCESS_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertPurpose(PurposeBean mcb, SettingLogsBean stl)
/*     */   {
/*  91 */     if (PurposeDao.insertPurpose(mcb, stl))
/*     */     {
/*  93 */       reloadMap();
/*  94 */       return true;
/*     */     }
/*     */ 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getPurposeCount()
/*     */   {
/* 107 */     return PurposeDao.getPurposeCount();
/*     */   }
/*     */ 
/*     */   public static boolean updatePurpose(PurposeBean mcb, SettingLogsBean stl)
/*     */   {
/* 118 */     if (PurposeDao.updatePurpose(mcb, stl))
/*     */     {
/* 120 */       reloadMap();
/* 121 */       return true;
/*     */     }
/*     */ 
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean savePurposeSort(String mcat_id, SettingLogsBean stl)
/*     */   {
/* 136 */     if (PurposeDao.savePurposeSort(mcat_id, stl))
/*     */     {
/* 138 */       reloadMap();
/* 139 */       return true;
/*     */     }
/*     */ 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deletePurpose(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 154 */     if (PurposeDao.deletePurpose(mp, stl))
/*     */     {
/* 156 */       reloadMap();
/* 157 */       return true;
/*     */     }
/*     */ 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] ages)
/*     */   {
/* 191 */     getPurposeByID("1");
/* 192 */     List lt = PurposeDao.getAllPurposeList();
/* 193 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.purpose.PurposeManager
 * JD-Core Version:    0.6.2
 */