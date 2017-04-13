/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class GuestBookCategoryManager$GuestBookCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 176 */     GuestBookCategory gbc1 = (GuestBookCategory)o1;
/* 177 */     GuestBookCategory gbc2 = (GuestBookCategory)o2;
/* 178 */     if (gbc1.getSort_id() > gbc2.getSort_id()) {
/* 179 */       return 1;
/*     */     }
/* 181 */     if (gbc1.getSort_id() == gbc2.getSort_id()) {
/* 182 */       return 0;
/*     */     }
/* 184 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookCategoryManager.GuestBookCategoryComparator
 * JD-Core Version:    0.6.2
 */