/*    */ package com.cicro.wcm.services.extendfunction.infoRelatedKcat;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class InfoRelatedKcatRPC
/*    */ {
/*    */   public static boolean insertInfoRelatedKcat(Map<String, String> map, HttpServletRequest request)
/*    */   {
/* 22 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 23 */     if (stl != null)
/*    */     {
/* 25 */       return InfoRelatedKcatManager.insertInfoRelatedKcat(map, stl);
/*    */     }
/*    */ 
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   public static String getRelatedKcatNames(String info_id)
/*    */   {
/* 35 */     return InfoRelatedKcatManager.getRelatedKcatNames(info_id);
/*    */   }
/*    */ 
/*    */   public static List<InfoRelatedKcatBean> getCGGRelatedKcatInfoList(Map<String, String> map)
/*    */   {
/* 42 */     return InfoRelatedKcatManager.getCGGRelatedKcatInfoList(map);
/*    */   }
/*    */ 
/*    */   public static String getCGGRelatedKcatInfoListCounts(Map<String, String> map)
/*    */   {
/* 49 */     return InfoRelatedKcatManager.getCGGRelatedKcatInfoListCounts(map);
/*    */   }
/*    */ 
/*    */   public static InfoRelatedKcatBean getInfoRelatedKcatBeanByinfoid(String info_id)
/*    */   {
/* 54 */     return InfoRelatedKcatManager.getInfoRelatedKcatBeanByinfoid(info_id);
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.infoRelatedKcat.InfoRelatedKcatRPC
 * JD-Core Version:    0.6.2
 */