/*     */ package com.cicro.wcm.services.system.formodel;
/*     */ 
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class ModelManager$ModelComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 304 */     ModelBean mb1 = (ModelBean)o1;
/* 305 */     ModelBean mb2 = (ModelBean)o2;
/* 306 */     if (mb1.getModel_sort() > mb2.getModel_sort()) {
/* 307 */       return 1;
/*     */     }
/* 309 */     if (mb1.getModel_sort() == mb2.getModel_sort()) {
/* 310 */       return 0;
/*     */     }
/* 312 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.formodel.ModelManager.ModelComparator
 * JD-Core Version:    0.6.2
 */