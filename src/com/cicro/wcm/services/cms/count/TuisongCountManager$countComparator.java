/*     */ package com.cicro.wcm.services.cms.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.count.SiteInfoTuisongBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class TuisongCountManager$countComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 236 */     SiteInfoTuisongBean cgb1 = (SiteInfoTuisongBean)o1;
/* 237 */     SiteInfoTuisongBean cgb2 = (SiteInfoTuisongBean)o2;
/* 238 */     if (cgb1.getIcount() < cgb2.getIcount()) {
/* 239 */       return 1;
/*     */     }
/* 241 */     if (cgb1.getIcount() == cgb2.getIcount()) {
/* 242 */       return 0;
/*     */     }
/* 244 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.TuisongCountManager.countComparator
 * JD-Core Version:    0.6.2
 */