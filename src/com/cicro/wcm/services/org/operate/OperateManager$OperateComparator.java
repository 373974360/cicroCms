/*     */ package com.cicro.wcm.services.org.operate;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.operate.OperateBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class OperateManager$OperateComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 440 */     OperateBean ob1 = (OperateBean)o1;
/* 441 */     OperateBean ob2 = (OperateBean)o2;
/* 442 */     if (ob1.getOpt_id() > ob2.getOpt_id()) {
/* 443 */       return 1;
/*     */     }
/* 445 */     if (ob1.getOpt_id() == ob2.getOpt_id()) {
/* 446 */       return 0;
/*     */     }
/* 448 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.operate.OperateManager.OperateComparator
 * JD-Core Version:    0.6.2
 */