/*     */ package com.cicro.wcm.services.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class GKNodeManager$GKNodeComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 356 */     GKNodeBean cgb1 = (GKNodeBean)o1;
/* 357 */     GKNodeBean cgb2 = (GKNodeBean)o2;
/* 358 */     if (cgb1.getSort_id() > cgb2.getSort_id()) {
/* 359 */       return 1;
/*     */     }
/* 361 */     if (cgb1.getSort_id() == cgb2.getSort_id()) {
/* 362 */       return 0;
/*     */     }
/* 364 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.node.GKNodeManager.GKNodeComparator
 * JD-Core Version:    0.6.2
 */