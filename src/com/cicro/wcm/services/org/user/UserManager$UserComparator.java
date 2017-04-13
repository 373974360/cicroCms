/*     */ package com.cicro.wcm.services.org.user;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class UserManager$UserComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 531 */     UserBean u1 = (UserBean)o1;
/* 532 */     UserBean u2 = (UserBean)o2;
/* 533 */     if (u1.getSort() > u2.getSort()) {
/* 534 */       return 1;
/*     */     }
/* 536 */     if (u1.getSort() == u2.getSort()) {
/* 537 */       return 0;
/*     */     }
/* 539 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.user.UserManager.UserComparator
 * JD-Core Version:    0.6.2
 */