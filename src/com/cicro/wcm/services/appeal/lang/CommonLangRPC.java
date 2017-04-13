/*     */ package com.cicro.wcm.services.appeal.lang;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.lang.CommonLangBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CommonLangRPC
/*     */ {
/*     */   public static List<CommonLangBean> getCommonLangList(Map<String, String> mp)
/*     */   {
/*  22 */     return CommonLangManager.getCommonLangList(mp);
/*     */   }
/*     */ 
/*     */   public static String getCommonLangCnt(Map<String, String> mp)
/*     */   {
/*  32 */     return CommonLangManager.getCommonLangCnt(mp);
/*     */   }
/*     */ 
/*     */   public static CommonLangBean getCommonLangByID(String id)
/*     */   {
/*  42 */     return CommonLangManager.getCommonLangByID(id);
/*     */   }
/*     */ 
/*     */   public static List<CommonLangBean> getCommonLangListByType(int type)
/*     */   {
/*  52 */     return CommonLangManager.getCommonLangListByType(type);
/*     */   }
/*     */ 
/*     */   public static boolean insertCommonLang(CommonLangBean clb, HttpServletRequest request)
/*     */   {
/*  63 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  64 */     if (stl != null)
/*     */     {
/*  66 */       return CommonLangManager.insertCommonLang(clb, stl);
/*     */     }
/*     */ 
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommonLang(CommonLangBean clb, HttpServletRequest request)
/*     */   {
/*  82 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  83 */     if (stl != null)
/*     */     {
/*  85 */       return CommonLangManager.updateCommonLang(clb, stl);
/*     */     }
/*     */ 
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSort(String ids, HttpServletRequest request)
/*     */   {
/* 101 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 102 */     if (stl != null)
/*     */     {
/* 104 */       return CommonLangManager.saveSort(ids, stl);
/*     */     }
/*     */ 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCommonLang(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 120 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 121 */     if (stl != null)
/*     */     {
/* 123 */       return CommonLangManager.deleteCommonLang(mp, stl);
/*     */     }
/*     */ 
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 133 */     Map mp = new HashMap();
/* 134 */     mp.put("ph_type", "2");
/* 135 */     List lt = getCommonLangList(mp);
/*     */ 
/* 138 */     System.out.println(getCommonLangCnt(mp));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.lang.CommonLangRPC
 * JD-Core Version:    0.6.2
 */