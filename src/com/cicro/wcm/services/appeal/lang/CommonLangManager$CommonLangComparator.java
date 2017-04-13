/*     */ package com.cicro.wcm.services.appeal.lang;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.lang.CommonLangBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class CommonLangManager$CommonLangComparator
/*     */   implements Comparator<CommonLangBean>
/*     */ {
/*     */   public int compare(CommonLangBean bean1, CommonLangBean bean2)
/*     */   {
/* 208 */     int ret = 0;
/* 209 */     if (bean1.getSort_id() > bean2.getSort_id())
/*     */     {
/* 211 */       ret = 1;
/* 212 */     } else if (bean1.getSort_id() == bean2.getSort_id())
/*     */     {
/* 214 */       ret = 0;
/*     */     }
/*     */     else {
/* 217 */       ret = -1;
/*     */     }
/* 219 */     return ret;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.lang.CommonLangManager.CommonLangComparator
 * JD-Core Version:    0.6.2
 */