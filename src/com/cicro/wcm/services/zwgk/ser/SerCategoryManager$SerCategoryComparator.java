/*     */ package com.cicro.wcm.services.zwgk.ser;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerCategoryBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class SerCategoryManager$SerCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 606 */     SerCategoryBean scb1 = (SerCategoryBean)o1;
/* 607 */     SerCategoryBean scb2 = (SerCategoryBean)o2;
/* 608 */     if (scb1.getSort_id() > scb2.getSort_id()) {
/* 609 */       return 1;
/*     */     }
/* 611 */     if (scb1.getSort_id() == scb2.getSort_id()) {
/* 612 */       return 0;
/*     */     }
/* 614 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ser.SerCategoryManager.SerCategoryComparator
 * JD-Core Version:    0.6.2
 */