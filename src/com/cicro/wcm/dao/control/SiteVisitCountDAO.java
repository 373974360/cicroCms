/*    */ package com.cicro.wcm.dao.control;
/*    */ 
/*    */ import com.cicro.wcm.bean.control.SiteVisitCountBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SiteVisitCountDAO
/*    */ {
/*    */   public static List<SiteVisitCountBean> getAllSiteVisitCount()
/*    */   {
/* 13 */     return DBManager.queryFList("getAllSiteVisitCount", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertSiteHits(Map<String, String> m)
/*    */   {
/* 18 */     return DBManager.insert("insert_site_hits", m);
/*    */   }
/*    */ 
/*    */   public static boolean updateSiteHits(Map<String, String> m)
/*    */   {
/* 23 */     return DBManager.update("update_site_hits", m);
/*    */   }
/*    */ 
/*    */   public static boolean clearSiteHits(Map<String, String> m)
/*    */   {
/* 28 */     return DBManager.update("clear_site_hits", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.control.SiteVisitCountDAO
 * JD-Core Version:    0.6.2
 */