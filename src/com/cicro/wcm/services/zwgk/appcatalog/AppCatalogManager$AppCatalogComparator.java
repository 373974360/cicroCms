/*     */ package com.cicro.wcm.services.zwgk.appcatalog;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class AppCatalogManager$AppCatalogComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 362 */     AppCatalogBean cgb1 = (AppCatalogBean)o1;
/* 363 */     AppCatalogBean cgb2 = (AppCatalogBean)o2;
/* 364 */     if (cgb1.getCat_sort() > cgb2.getCat_sort()) {
/* 365 */       return 1;
/*     */     }
/* 367 */     if (cgb1.getCat_sort() == cgb2.getCat_sort()) {
/* 368 */       return 0;
/*     */     }
/* 370 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.appcatalog.AppCatalogManager.AppCatalogComparator
 * JD-Core Version:    0.6.2
 */