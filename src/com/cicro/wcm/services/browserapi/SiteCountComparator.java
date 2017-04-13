/*    */ package com.cicro.wcm.services.browserapi;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class SiteCountComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 16 */     SiteCountBean c1 = (SiteCountBean)o1;
/* 17 */     SiteCountBean c2 = (SiteCountBean)o2;
/*    */ 
/* 19 */     if (Integer.valueOf(c2.getReleasedCount()).intValue() > Integer.valueOf(c1.getReleasedCount()).intValue()) {
/* 20 */       return 1;
/*    */     }
/* 22 */     if (Integer.valueOf(c2.getReleasedCount()) == Integer.valueOf(c1.getReleasedCount())) {
/* 23 */       return 0;
/*    */     }
/* 25 */     return -1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.browserapi.SiteCountComparator
 * JD-Core Version:    0.6.2
 */