/*    */ package com.cicro.wcm.services.minlu;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.minlu.MingLuBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class MingLuRPC
/*    */ {
/*    */   public static int getNewMingLuID()
/*    */   {
/* 12 */     return MingLuManager.getNewMingLuID();
/*    */   }
/*    */ 
/*    */   public static MingLuBean getMingLuBean(String site_id)
/*    */   {
/* 22 */     return MingLuManager.getMingLuBean(site_id);
/*    */   }
/*    */ 
/*    */   public static boolean insertMingLuTemplate(MingLuBean ml, HttpServletRequest request)
/*    */   {
/* 33 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 34 */     if (stl != null)
/*    */     {
/* 36 */       return MingLuManager.insertMingLuTemplate(ml, stl);
/*    */     }
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateMingLuTemplate(MingLuBean ml, HttpServletRequest request)
/*    */   {
/* 49 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 50 */     if (stl != null)
/*    */     {
/* 52 */       return MingLuManager.updateMingLuTemplate(ml, stl);
/*    */     }
/* 54 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.minlu.MingLuRPC
 * JD-Core Version:    0.6.2
 */