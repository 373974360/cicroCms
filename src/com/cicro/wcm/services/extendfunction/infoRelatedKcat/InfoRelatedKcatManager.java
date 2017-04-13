/*    */ package com.cicro.wcm.services.extendfunction.infoRelatedKcat;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class InfoRelatedKcatManager
/*    */ {
/*    */   public static boolean insertInfoRelatedKcat(Map<String, String> map, SettingLogsBean stl)
/*    */   {
/* 20 */     return InfoRelatedKcatDAO.insertInfoRelatedKcat(map, stl);
/*    */   }
/*    */ 
/*    */   public static String getRelatedKcatNames(String info_id)
/*    */   {
/* 25 */     return InfoRelatedKcatDAO.getRelatedKcatNames(info_id);
/*    */   }
/*    */ 
/*    */   public static List<InfoRelatedKcatBean> getCGGRelatedKcatInfoList(Map<String, String> map)
/*    */   {
/* 32 */     return InfoRelatedKcatDAO.getCGGRelatedKcatInfoList(map);
/*    */   }
/*    */ 
/*    */   public static String getCGGRelatedKcatInfoListCounts(Map<String, String> map)
/*    */   {
/* 37 */     return InfoRelatedKcatDAO.getCGGRelatedKcatInfoListCounts(map);
/*    */   }
/*    */ 
/*    */   public static List<InfoRelatedKcatBean> getSQRelatedKcatInfoList(Map<String, String> map)
/*    */   {
/* 44 */     return InfoRelatedKcatDAO.getSQRelatedKcatInfoList(map);
/*    */   }
/*    */ 
/*    */   public static String getSQRelatedKcatInfoListCounts(Map<String, String> map)
/*    */   {
/* 49 */     return InfoRelatedKcatDAO.getSQRelatedKcatInfoListCounts(map);
/*    */   }
/*    */ 
/*    */   public static InfoRelatedKcatBean getInfoRelatedKcatBeanByinfoid(String info_id)
/*    */   {
/* 54 */     return InfoRelatedKcatDAO.getInfoRelatedKcatBeanByinfoid(info_id);
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.infoRelatedKcat.InfoRelatedKcatManager
 * JD-Core Version:    0.6.2
 */