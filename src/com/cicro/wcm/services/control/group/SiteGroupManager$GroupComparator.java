/*     */ package com.cicro.wcm.services.control.group;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteGroupBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class SiteGroupManager$GroupComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 316 */     SiteGroupBean mb1 = (SiteGroupBean)o1;
/* 317 */     SiteGroupBean mb2 = (SiteGroupBean)o2;
/* 318 */     if (mb1.getSgroup_sort() > mb2.getSgroup_sort()) {
/* 319 */       return 1;
/*     */     }
/* 321 */     if (mb1.getSgroup_sort() == mb2.getSgroup_sort()) {
/* 322 */       return 0;
/*     */     }
/* 324 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.group.SiteGroupManager.GroupComparator
 * JD-Core Version:    0.6.2
 */