/*    */ package com.cicro.wcm.services.cms.countsource;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.CmsCountBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CmsCountSourceRPC
/*    */ {
/*    */   public static List<CmsCountBean> getInfoCountListBySource(Map<String, String> mp)
/*    */   {
/* 12 */     return CmsCountSourceService.getInfoCountListBySource(mp);
/*    */   }
/*    */ 
/*    */   public static String cmsInfoOutExcel(List listBean, List headerList)
/*    */   {
/* 23 */     return CmsCountSourceService.cmsInfoOutExcel(listBean, headerList);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.countsource.CmsCountSourceRPC
 * JD-Core Version:    0.6.2
 */