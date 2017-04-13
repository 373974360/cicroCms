/*    */ package com.cicro.wcm.services.cms.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SiteCountRPC
/*    */ {
/*    */   public static List<SiteCountBean> getSiteCountListByInput(Map map)
/*    */   {
/* 13 */     return SiteCountManager.getSiteCountListByInput(map);
/*    */   }
/*    */ 
/*    */   public static List<SiteCountBean> getSiteCountListByInputUser(Map mp)
/*    */   {
/* 18 */     return SiteCountManager.getSiteCountListByInputUser(mp);
/*    */   }
/*    */ 
/*    */   public static List<SiteCountBean> getSiteCountListBySite(Map mp)
/*    */   {
/* 23 */     return SiteCountManager.getSiteCountListBySite(mp);
/*    */   }
/*    */ 
/*    */   public static List<SiteCountBean> getSiteCountListByCate(Map mp)
/*    */   {
/* 28 */     return SiteCountManager.getSiteCountListByCate(mp);
/*    */   }
/*    */ 
/*    */   public static String siteInfoOutExcelByUser(List listBean, List headerList)
/*    */   {
/* 38 */     return SiteCountExcelOut.siteInfoOutExcelByUser(listBean, headerList);
/*    */   }
/*    */ 
/*    */   public static String orgTreeInfoOutExcel(List headerList, Map mp)
/*    */   {
/* 51 */     return SiteCountExcelOut.orgTreeInfoOutExcel(headerList, mp);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.SiteCountRPC
 * JD-Core Version:    0.6.2
 */