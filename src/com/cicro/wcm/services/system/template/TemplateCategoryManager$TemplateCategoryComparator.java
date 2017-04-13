/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class TemplateCategoryManager$TemplateCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 314 */     TemplateCategoryBean cgb1 = (TemplateCategoryBean)o1;
/* 315 */     TemplateCategoryBean cgb2 = (TemplateCategoryBean)o2;
/* 316 */     if (cgb1.getSort_id() > cgb2.getSort_id()) {
/* 317 */       return 1;
/*     */     }
/* 319 */     if (cgb1.getSort_id() == cgb2.getSort_id()) {
/* 320 */       return 0;
/*     */     }
/* 322 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateCategoryManager.TemplateCategoryComparator
 * JD-Core Version:    0.6.2
 */