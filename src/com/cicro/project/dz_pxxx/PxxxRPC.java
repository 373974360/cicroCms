/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class PxxxRPC
/*    */ {
/*    */   public static String getPxxxCount(Map<String, String> m)
/*    */   {
/* 14 */     return PxxxManager.getPxxxCount(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBean> getPxxxList(Map<String, String> m)
/*    */   {
/* 19 */     return PxxxManager.getPxxxList(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBean> getAllPxxxList()
/*    */   {
/* 24 */     return PxxxManager.getAllPxxxList();
/*    */   }
/*    */ 
/*    */   public static PxxxBean getPxxxBean(String gq_id)
/*    */   {
/* 30 */     return PxxxManager.getPxxxBean(gq_id);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxx(PxxxBean hb, HttpServletRequest request)
/*    */   {
/* 35 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 36 */     if (stl != null) {
/* 37 */       return PxxxManager.updatePxxx(hb, stl);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxx(PxxxBean hb, HttpServletRequest request)
/*    */   {
/* 44 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 45 */     if (stl != null) {
/* 46 */       return PxxxManager.insertPxxx(hb, stl);
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxx(Map<String, String> m, HttpServletRequest request)
/*    */   {
/* 62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 63 */     if (stl != null) {
/* 64 */       return PxxxManager.deletePxxx(m, stl);
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglRPC
 * JD-Core Version:    0.6.2
 */