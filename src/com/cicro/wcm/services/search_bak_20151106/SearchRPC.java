/*    */ package com.cicro.wcm.services.search_bak_20151106;
/*    */ 
/*    */ import com.cicro.wcm.bean.search.SiteInfo;
import com.cicro.wcm.services.search.SearchForManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SearchRPC
/*    */ {
/*    */   public static List<SiteInfo> getSiteListByMap(Map mapParam)
/*    */   {
/* 23 */     return SearchForManager.getSiteListByMap(mapParam);
/*    */   }
/*    */ 
/*    */   public static int getSiteListByMapCount()
/*    */   {
/* 31 */     return SearchForManager.getSiteListByMapCount();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.SearchRPC
 * JD-Core Version:    0.6.2
 */