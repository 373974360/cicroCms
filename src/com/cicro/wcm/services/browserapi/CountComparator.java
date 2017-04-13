/*    */ package com.cicro.wcm.services.browserapi;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.count.CountBean;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class CountComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 12 */     CountBean c1 = (CountBean)o1;
/* 13 */     CountBean c2 = (CountBean)o2;
/*    */ 
/* 15 */     if (Double.valueOf(c2.getCountendp().replaceAll("%", "")).doubleValue() > Double.valueOf(c1.getCountendp().replaceAll("%", "")).doubleValue()) {
/* 16 */       return 1;
/*    */     }
/* 18 */     if (Double.valueOf(c2.getCountendp().replaceAll("%", "")) == Double.valueOf(c1.getCountendp().replaceAll("%", ""))) {
/* 19 */       return 0;
/*    */     }
/* 21 */     return -1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.browserapi.CountComparator
 * JD-Core Version:    0.6.2
 */