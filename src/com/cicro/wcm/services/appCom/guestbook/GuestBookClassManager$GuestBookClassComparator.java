/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookClass;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class GuestBookClassManager$GuestBookClassComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 155 */     GuestBookClass gbc1 = (GuestBookClass)o1;
/* 156 */     GuestBookClass gbc2 = (GuestBookClass)o2;
/* 157 */     if (gbc1.getSort_id() > gbc2.getSort_id()) {
/* 158 */       return 1;
/*     */     }
/* 160 */     if (gbc1.getSort_id() == gbc2.getSort_id()) {
/* 161 */       return 0;
/*     */     }
/* 163 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookClassManager.GuestBookClassComparator
 * JD-Core Version:    0.6.2
 */