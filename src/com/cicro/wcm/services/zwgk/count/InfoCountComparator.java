/*    */ package com.cicro.wcm.services.zwgk.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class InfoCountComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 12 */     GKCountBean c1 = (GKCountBean)o1;
/* 13 */     GKCountBean c2 = (GKCountBean)o2;
/*    */ 
/* 15 */     if (Integer.valueOf(c2.getInfo_count()).intValue() > Integer.valueOf(c1.getInfo_count()).intValue()) {
/* 16 */       return 1;
/*    */     }
/* 18 */     if (Integer.valueOf(c2.getInfo_count()) == Integer.valueOf(c1.getInfo_count())) {
/* 19 */       return 0;
/*    */     }
/* 21 */     return -1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.InfoCountComparator
 * JD-Core Version:    0.6.2
 */