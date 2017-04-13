/*    */ package com.cicro.project.dz_ddmf;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class DdmfRPC
/*    */ {
/*    */   public static String getDdmfCount(Map<String, String> m)
/*    */   {
/* 14 */     return DdmfManager.getDdmfCount(m);
/*    */   }
/*    */ 
/*    */   public static List<DdmfBean> getDdmfList(Map<String, String> m)
/*    */   {
/* 19 */     return DdmfManager.getDdmfList(m);
/*    */   }
/*    */ 
/*    */   public static List<DdmfBean> getAllDdmfList()
/*    */   {
/* 24 */     return DdmfManager.getAllDdmfList();
/*    */   }
/*    */ 
/*    */   public static DdmfBean getDdmfBean(String gq_id, boolean is_browser)
/*    */   {
/* 30 */     return DdmfManager.getDdmfBean(gq_id, is_browser);
/*    */   }
/*    */ 
/*    */   public static boolean updateDdmf(DdmfBean hb, HttpServletRequest request)
/*    */   {
/* 35 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 36 */     if (stl != null) {
/* 37 */       return DdmfManager.updateDdmf(hb, stl);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertDdmf(DdmfBean hb, HttpServletRequest request)
/*    */   {
/* 44 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 45 */     if (stl != null) {
/* 46 */       return DdmfManager.insertDdmf(hb, stl);
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteDdmf(Map<String, String> m, HttpServletRequest request)
/*    */   {
/* 62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 63 */     if (stl != null) {
/* 64 */       return DdmfManager.deleteDdmf(m, stl);
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglRPC
 * JD-Core Version:    0.6.2
 */