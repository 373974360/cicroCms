/*    */ package com.cicro.project.dz_aqwzxx;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class WzxxRPC
/*    */ {
/*    */   public static String getWzxxCount(Map<String, String> m)
/*    */   {
/* 14 */     return WzxxManager.getWzxxCount(m);
/*    */   }
/*    */ 
/*    */   public static List<WzxxBean> getWzxxList(Map<String, String> m)
/*    */   {
/* 19 */     return WzxxManager.getWzxxList(m);
/*    */   }
/*    */ 
/*    */   public static List<WzxxBean> getAllWzxxList()
/*    */   {
/* 24 */     return WzxxManager.getAllWzxxList();
/*    */   }
/*    */ 
/*    */   public static WzxxBean getWzxxBean(String gq_id, boolean is_browser)
/*    */   {
/* 30 */     return WzxxManager.getWzxxBean(gq_id, is_browser);
/*    */   }
/*    */ 
/*    */   public static boolean updateWzxx(WzxxBean hb, HttpServletRequest request)
/*    */   {
/* 35 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 36 */     if (stl != null) {
/* 37 */       return WzxxManager.updateWzxx(hb, stl);
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertWzxx(WzxxBean hb, HttpServletRequest request)
/*    */   {
/* 44 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 45 */     if (stl != null) {
/* 46 */       return WzxxManager.insertWzxx(hb, stl);
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteWzxx(Map<String, String> m, HttpServletRequest request)
/*    */   {
/* 62 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 63 */     if (stl != null) {
/* 64 */       return WzxxManager.deleteWzxx(m, stl);
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglRPC
 * JD-Core Version:    0.6.2
 */