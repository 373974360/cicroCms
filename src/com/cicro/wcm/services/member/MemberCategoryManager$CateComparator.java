/*     */ package com.cicro.wcm.services.member;
/*     */ 
/*     */ import com.cicro.wcm.bean.member.MemberCategoryBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class MemberCategoryManager$CateComparator
/*     */   implements Comparator<MemberCategoryBean>
/*     */ {
/*     */   public int compare(MemberCategoryBean bean1, MemberCategoryBean bean2)
/*     */   {
/* 163 */     int ret = 0;
/* 164 */     if (bean1.getSort_id() > bean2.getSort_id())
/*     */     {
/* 166 */       ret = 1;
/* 167 */     } else if (bean1.getSort_id() == bean2.getSort_id())
/*     */     {
/* 169 */       ret = 0;
/*     */     }
/*     */     else {
/* 172 */       ret = -1;
/*     */     }
/* 174 */     return ret;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.member.MemberCategoryManager.CateComparator
 * JD-Core Version:    0.6.2
 */