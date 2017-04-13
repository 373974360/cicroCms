/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class ZTCategoryManager$ZTCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 234 */     ZTCategoryBean cgb1 = (ZTCategoryBean)o1;
/* 235 */     ZTCategoryBean cgb2 = (ZTCategoryBean)o2;
/* 236 */     if (cgb1.getSort_id() > cgb2.getSort_id()) {
/* 237 */       return 1;
/*     */     }
/* 239 */     if (cgb1.getSort_id() == cgb2.getSort_id()) {
/* 240 */       return 0;
/*     */     }
/* 242 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.ZTCategoryManager.ZTCategoryComparator
 * JD-Core Version:    0.6.2
 */