/*     */ package com.cicro.wcm.services.appeal.area;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.area.AreaBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class AreaRPC
/*     */ {
/*     */   public static List<AreaBean> getChildAreaList(int area_id)
/*     */   {
/*  24 */     return AreaManager.getChildAreaList(area_id);
/*     */   }
/*     */ 
/*     */   public static String getAreaTreeJsonStr()
/*     */   {
/*  32 */     return AreaManager.getAreaTreeJsonStr();
/*     */   }
/*     */ 
/*     */   public static String getChildAreaCount(int area_id)
/*     */   {
/*  41 */     return AreaManager.getChildAreaCount(area_id);
/*     */   }
/*     */ 
/*     */   public static int getAreaID()
/*     */   {
/*  50 */     return AreaManager.getAreaID();
/*     */   }
/*     */ 
/*     */   public static boolean insertArea(AreaBean ob, HttpServletRequest request)
/*     */   {
/*  60 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  61 */     if (stl != null)
/*     */     {
/*  63 */       return AreaManager.insertArea(ob, stl);
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   public static AreaBean getAreaBean(int area_id)
/*     */   {
/*  74 */     return AreaManager.getAreaBean(area_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateArea(AreaBean ob, HttpServletRequest request)
/*     */   {
/*  85 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  86 */     if (stl != null)
/*     */     {
/*  88 */       return AreaManager.updateArea(ob, stl);
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteArea(String area_id, HttpServletRequest request)
/*     */   {
/* 100 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 101 */     if (stl != null)
/*     */     {
/* 103 */       return AreaManager.deleteArea(area_id, stl);
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveAreaSort(String area_id, HttpServletRequest request)
/*     */   {
/* 116 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 117 */     if (stl != null)
/*     */     {
/* 119 */       return AreaManager.saveAreaSort(area_id, stl);
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.area.AreaRPC
 * JD-Core Version:    0.6.2
 */