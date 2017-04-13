/*     */ package com.cicro.wcm.services.appeal.area;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.area.AreaBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class AreaManager$AreaComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 246 */     AreaBean db1 = (AreaBean)o1;
/* 247 */     AreaBean db2 = (AreaBean)o2;
/* 248 */     if (db1.getSort_id() > db2.getSort_id()) {
/* 249 */       return 1;
/*     */     }
/* 251 */     if (db1.getSort_id() == db2.getSort_id()) {
/* 252 */       return 0;
/*     */     }
/* 254 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.area.AreaManager.AreaComparator
 * JD-Core Version:    0.6.2
 */