/*     */ package com.cicro.wcm.services.appeal.purpose;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class PurposeRPC
/*     */ {
/*     */   public static List<PurposeBean> getPurposeList()
/*     */   {
/*  22 */     return PurposeManager.getAllPurposeList();
/*     */   }
/*     */ 
/*     */   public static PurposeBean getPurposeByID(String id)
/*     */   {
/*  32 */     return PurposeManager.getPurposeByID(id);
/*     */   }
/*     */ 
/*     */   public static int getAppPurposeID()
/*     */   {
/*  42 */     return PurposeManager.getAppPurposeID();
/*     */   }
/*     */ 
/*     */   public static String getPurposeCount()
/*     */   {
/*  52 */     return PurposeManager.getPurposeCount();
/*     */   }
/*     */ 
/*     */   public static boolean insertPurpose(PurposeBean mcb, HttpServletRequest request)
/*     */   {
/*  63 */     System.out.println("\t\tRPC insertPurpose============");
/*  64 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  65 */     if (stl != null)
/*     */     {
/*  67 */       return PurposeManager.insertPurpose(mcb, stl);
/*     */     }
/*     */ 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean savePurposeSort(String mcat_id, HttpServletRequest request)
/*     */   {
/*  83 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  84 */     if (stl != null)
/*     */     {
/*  86 */       return PurposeManager.savePurposeSort(mcat_id, stl);
/*     */     }
/*     */ 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updatePurpose(PurposeBean mcb, HttpServletRequest request)
/*     */   {
/* 102 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 103 */     if (stl != null)
/*     */     {
/* 105 */       return PurposeManager.updatePurpose(mcb, stl);
/*     */     }
/*     */ 
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deletePurpose(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 120 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 121 */     if (stl != null)
/*     */     {
/* 123 */       return PurposeManager.deletePurpose(mp, stl);
/*     */     }
/*     */ 
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.purpose.PurposeRPC
 * JD-Core Version:    0.6.2
 */