/*     */ package com.cicro.wcm.services.material;
/*     */ 
/*     */ import com.cicro.wcm.bean.material.MateFolderBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class MateFolderManager$MateFolderComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 361 */     MateFolderBean mf1 = (MateFolderBean)o1;
/* 362 */     MateFolderBean mf2 = (MateFolderBean)o2;
/* 363 */     if (mf1.getF_id() > mf2.getF_id()) {
/* 364 */       return 1;
/*     */     }
/* 366 */     if (mf1.getF_id() == mf2.getF_id()) {
/* 367 */       return 0;
/*     */     }
/* 369 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.material.MateFolderManager.MateFolderComparator
 * JD-Core Version:    0.6.2
 */