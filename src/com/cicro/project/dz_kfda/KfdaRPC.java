/*    */ package com.cicro.project.dz_kfda;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class KfdaRPC
/*    */ {
/*    */   public static String getKfdaCount(Map<String, String> m)
/*    */   {
/* 14 */     return KfdaManager.getKfdaCount(m);
/*    */   }
/*    */ 
/*    */   public static List<KfdaBean> getKfdaList(Map<String, String> m)
/*    */   {
/* 19 */     return KfdaManager.getKfdaList(m);
/*    */   }
/*    */ 
/*    */   public static List<KfdaBean> getAllKfdaList()
/*    */   {
/* 24 */     return KfdaManager.getAllKfdaList();
/*    */   }
/*    */ 
/*    */   public static KfdaBean getKfdaBean(String gq_id)
/*    */   {
/* 30 */     return KfdaManager.getKfdaBean(gq_id);
/*    */   }
/*    */ 
/*    */   public static boolean updateKfda(KfdaBean hb, HttpServletRequest request)
/*    */   {
/* 35 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 36 */     if (stl != null) {
/* 37 */       return KfdaManager.updateKfda(hb, stl);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertKfda(KfdaBean hb, HttpServletRequest request)
/*    */   {
/* 44 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 45 */     if (stl != null) {
/* 46 */       return KfdaManager.insertKfda(hb, stl);
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteKfda(Map<String, String> m, HttpServletRequest request)
/*    */   {
/* 62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 63 */     if (stl != null) {
/* 64 */       return KfdaManager.deleteKfda(m, stl);
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglRPC
 * JD-Core Version:    0.6.2
 */