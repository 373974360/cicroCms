/*     */ package com.cicro.wcm.services.appeal.purpose;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class PurposeManager$CateComparator
/*     */   implements Comparator<PurposeBean>
/*     */ {
/*     */   public int compare(PurposeBean bean1, PurposeBean bean2)
/*     */   {
/* 174 */     int ret = 0;
/* 175 */     if (bean1.getSort_id() > bean2.getSort_id())
/*     */     {
/* 177 */       ret = 1;
/* 178 */     } else if (bean1.getSort_id() == bean2.getSort_id())
/*     */     {
/* 180 */       ret = 0;
/*     */     }
/*     */     else {
/* 183 */       ret = -1;
/*     */     }
/* 185 */     return ret;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.purpose.PurposeManager.CateComparator
 * JD-Core Version:    0.6.2
 */