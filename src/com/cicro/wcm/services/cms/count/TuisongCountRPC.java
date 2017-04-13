/*    */ package com.cicro.wcm.services.cms.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.SiteInfoTuisongBean;
/*    */ import com.cicro.wcm.bean.cms.count.TuisongCountBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TuisongCountRPC
/*    */ {
/*    */   public static List<TuisongCountBean> getTuisongInfoCountByCat(Map<String, String> mp)
/*    */   {
/* 18 */     return TuisongCountManager.getTuisongInfoCountByCat(mp);
/*    */   }
/*    */ 
/*    */   public static String tuiSongInfoOutExcel(List listBean, List headerList)
/*    */   {
/* 27 */     return TuisongCountManager.tuiSongInfoOutExcel(listBean, headerList);
/*    */   }
/*    */ 
/*    */   public static List<SiteInfoTuisongBean> getOneSiteTuisCounts(Map<String, String> mp)
/*    */   {
/* 36 */     return TuisongCountManager.getOneSiteTuisCounts(mp);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.TuisongCountRPC
 * JD-Core Version:    0.6.2
 */