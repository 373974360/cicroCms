/*    */ package com.cicro.wcm.services.zwgk.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GKvisitorCountRPC
/*    */ {
/*    */   public static List<GKCountBean> getAllSiteGKCountList(String startDate, String endDate, String node_ids)
/*    */   {
/* 18 */     return GKAccessCountManager.getAllSiteGKCountList(startDate, endDate, node_ids);
/*    */   }
/*    */ 
/*    */   public static String gkInfoOutExcel(List listBean, List headerList)
/*    */   {
/* 26 */     return GKAccessCountManager.gkInfoOutExcel(listBean, headerList);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKvisitorCountRPC
 * JD-Core Version:    0.6.2
 */