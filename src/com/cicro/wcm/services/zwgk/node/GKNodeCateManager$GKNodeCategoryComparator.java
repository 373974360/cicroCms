/*     */ package com.cicro.wcm.services.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeCategory;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class GKNodeCateManager$GKNodeCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 445 */     GKNodeCategory cgb1 = (GKNodeCategory)o1;
/* 446 */     GKNodeCategory cgb2 = (GKNodeCategory)o2;
/* 447 */     if (cgb1.getSort_id() > cgb2.getSort_id()) {
/* 448 */       return 1;
/*     */     }
/* 450 */     if (cgb1.getSort_id() == cgb2.getSort_id()) {
/* 451 */       return 0;
/*     */     }
/* 453 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.node.GKNodeCateManager.GKNodeCategoryComparator
 * JD-Core Version:    0.6.2
 */