/*     */ package com.cicro.wcm.services.appeal.cpDept;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class CpDeptManager$CpDeptComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 355 */     CpDeptBean dept1 = (CpDeptBean)o1;
/* 356 */     CpDeptBean dept2 = (CpDeptBean)o2;
/* 357 */     if (dept1.getSort_id() > dept2.getSort_id()) {
/* 358 */       return 1;
/*     */     }
/* 360 */     if (dept1.getSort_id() == dept2.getSort_id()) {
/* 361 */       return 0;
/*     */     }
/* 363 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpDept.CpDeptManager.CpDeptComparator
 * JD-Core Version:    0.6.2
 */