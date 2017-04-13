/*    */ package com.cicro.wcm.services.cms.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class SiteCountComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 12 */     SiteCountBean c1 = (SiteCountBean)o1;
/* 13 */     SiteCountBean c2 = (SiteCountBean)o2;
/*    */ 
/* 15 */     if (Integer.valueOf(c2.getInputCount()).intValue() > Integer.valueOf(c1.getInputCount()).intValue()) {
/* 16 */       return 1;
/*    */     }
/* 18 */     if (Integer.valueOf(c2.getInputCount()) == Integer.valueOf(c1.getInputCount())) {
/* 19 */       return 0;
/*    */     }
/* 21 */     return -1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.SiteCountComparator
 * JD-Core Version:    0.6.2
 */