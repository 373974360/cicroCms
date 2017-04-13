/*     */ package com.cicro.wcm.services.org.operate;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.operate.MenuBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class MenuManager$MenuComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 554 */     MenuBean mb1 = (MenuBean)o1;
/* 555 */     MenuBean mb2 = (MenuBean)o2;
/* 556 */     if (mb1.getMenu_sort() > mb2.getMenu_sort()) {
/* 557 */       return 1;
/*     */     }
/* 559 */     if (mb1.getMenu_sort() == mb2.getMenu_sort()) {
/* 560 */       return 0;
/*     */     }
/* 562 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.operate.MenuManager.MenuComparator
 * JD-Core Version:    0.6.2
 */