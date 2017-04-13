/*    */ package com.cicro.wcm.dao.cms.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SiteCountDAO
/*    */ {
/*    */   public static List<SiteCountBean> getSiteCountListByInput(Map map)
/*    */   {
/* 13 */     return DBManager.queryFList("site_count.getSiteCountListByInput", map);
/*    */   }
/*    */ 
/*    */   public static List<SiteCountBean> getSiteCountListBySite(Map map)
/*    */   {
/* 18 */     return DBManager.queryFList("site_count.getSiteCountListBySite", map);
/*    */   }
/*    */ 
/*    */   public static List<SiteCountBean> getSiteCountListBySiteCate(Map map)
/*    */   {
/* 23 */     return DBManager.queryFList("site_count.getSiteCountListBySiteCate", map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.count.SiteCountDAO
 * JD-Core Version:    0.6.2
 */