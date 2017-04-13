/*     */ package com.cicro.wcm.services.appeal.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.category.CategoryBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class CategoryManager$AppCateComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 524 */     CategoryBean mb1 = (CategoryBean)o1;
/* 525 */     CategoryBean mb2 = (CategoryBean)o2;
/* 526 */     if (mb1.getSort_id() > mb2.getSort_id()) {
/* 527 */       return 1;
/*     */     }
/* 529 */     if (mb1.getSort_id() == mb2.getSort_id()) {
/* 530 */       return 0;
/*     */     }
/* 532 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.category.CategoryManager.AppCateComparator
 * JD-Core Version:    0.6.2
 */