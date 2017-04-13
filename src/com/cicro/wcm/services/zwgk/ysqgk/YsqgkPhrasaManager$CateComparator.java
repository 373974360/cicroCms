/*     */ package com.cicro.wcm.services.zwgk.ysqgk;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkPhrasalBean;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class YsqgkPhrasaManager$CateComparator
/*     */   implements Comparator<YsqgkPhrasalBean>
/*     */ {
/*     */   public int compare(YsqgkPhrasalBean bean1, YsqgkPhrasalBean bean2)
/*     */   {
/* 195 */     int ret = 0;
/* 196 */     if (bean1.getSort_id() > bean2.getSort_id())
/*     */     {
/* 198 */       ret = 1;
/* 199 */     } else if (bean1.getSort_id() == bean2.getSort_id())
/*     */     {
/* 201 */       ret = 0;
/*     */     }
/*     */     else {
/* 204 */       ret = -1;
/*     */     }
/* 206 */     return ret;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ysqgk.YsqgkPhrasaManager.CateComparator
 * JD-Core Version:    0.6.2
 */