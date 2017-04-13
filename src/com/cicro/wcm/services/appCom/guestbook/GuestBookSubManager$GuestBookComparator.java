/*    */ package com.cicro.wcm.services.appCom.guestbook;
/*    */ 
/*    */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ class GuestBookSubManager$GuestBookComparator
/*    */   implements Comparator<Object>
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 86 */     GuestBookSub gbs1 = (GuestBookSub)o1;
/* 87 */     GuestBookSub gbs2 = (GuestBookSub)o2;
/* 88 */     if (gbs1.getId() > gbs2.getId()) {
/* 89 */       return 1;
/*    */     }
/* 91 */     if (gbs1.getGbs_id() == gbs2.getGbs_id()) {
/* 92 */       return 0;
/*    */     }
/* 94 */     return -1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookSubManager.GuestBookComparator
 * JD-Core Version:    0.6.2
 */