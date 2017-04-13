/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;

/*    */ import java.util.List;
/*    */ import java.util.Map;

/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class PxxxKcRPC
/*    */ {
/*    */   public static String getPxxxKcCount(Map<String, String> m)
/*    */   {
/* 14 */     return PxxxKcManager.getPxxxKcCount(m);
/*    */   }
/*    */                                                 
/*    */   public static List<PxxxKcBean> getPxxxKcList(Map<String, String> m)
/*    */   {
/* 19 */     return PxxxKcManager.getPxxxKcList(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxKcBean> getAllPxxxKcList()
/*    */   {
/* 24 */     return PxxxKcManager.getAllPxxxKcList();
/*    */   }
/*    */   public static List<PxxxKcBean> getAllPxxxKcByPxID(String pxid)
/*    */   {
/* 24 */     return PxxxKcManager.getAllPxxxKcByPxID(pxid);
/*    */   }
/*    */ 
/*    */ 
/*    */   public static PxxxKcBean getPxxxKcBean(String gq_id)
/*    */   {
/* 30 */     return PxxxKcManager.getPxxxKcBean(gq_id);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxxKc(PxxxKcBean hb, HttpServletRequest request)
/*    */   {
/* 35 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 36 */     if (stl != null) {
/* 37 */       return PxxxKcManager.updatePxxxKc(hb, stl);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxxKc(PxxxKcBean hb, HttpServletRequest request)
/*    */   {
/* 44 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 45 */     if (stl != null) {
/* 46 */       return PxxxKcManager.insertPxxxKc(hb, stl);
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxxKc(Map<String, String> m, HttpServletRequest request)
/*    */   {
/* 62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 63 */     if (stl != null) {
/* 64 */       return PxxxKcManager.deletePxxxKc(m, stl);
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglRPC
 * JD-Core Version:    0.6.2
 */