/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.system.ware.WareCategoryBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class WareCategoryManager$WareCateComparator
/*     */   implements Comparator<WareCategoryBean>
/*     */ {
/*     */   public int compare(WareCategoryBean o1, WareCategoryBean o2)
/*     */   {
/* 585 */     int flg = 0;
/* 586 */     if (o1.getSort_id() > o2.getSort_id())
/*     */     {
/* 588 */       flg = 1;
/*     */     }
/* 590 */     else if (o1.getSort_id() == o2.getSort_id())
/*     */     {
/* 592 */       flg = 0;
/*     */     }
/*     */     else
/*     */     {
/* 596 */       flg = -1;
/*     */     }
/* 598 */     return flg;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareCategoryManager.WareCateComparator
 * JD-Core Version:    0.6.2
 */