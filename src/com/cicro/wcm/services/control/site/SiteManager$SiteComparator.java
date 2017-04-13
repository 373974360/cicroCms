/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class SiteManager$SiteComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 406 */     SiteBean sb1 = (SiteBean)o1;
/* 407 */     SiteBean sb2 = (SiteBean)o2;
/* 408 */     if (sb1.getSite_sort() > sb2.getSite_sort()) {
/* 409 */       return 1;
/*     */     }
/* 411 */     if (sb1.getSite_sort() == sb2.getSite_sort()) {
/* 412 */       return 0;
/*     */     }
/* 414 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteManager.SiteComparator
 * JD-Core Version:    0.6.2
 */