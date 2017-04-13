/*     */ package com.cicro.wcm.services.org.dept;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class DeptManager$DeptComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 795 */     DeptBean db1 = (DeptBean)o1;
/* 796 */     DeptBean db2 = (DeptBean)o2;
/* 797 */     if (db1.getDept_sort() > db2.getDept_sort()) {
/* 798 */       return 1;
/*     */     }
/* 800 */     if (db1.getDept_sort() == db2.getDept_sort()) {
/* 801 */       return 0;
/*     */     }
/* 803 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.dept.DeptManager.DeptComparator
 * JD-Core Version:    0.6.2
 */