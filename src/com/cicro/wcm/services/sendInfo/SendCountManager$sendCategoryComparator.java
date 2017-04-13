/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordCount;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ class SendCountManager$sendCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 142 */     SendRecordCount cgb1 = (SendRecordCount)o1;
/* 143 */     SendRecordCount cgb2 = (SendRecordCount)o2;
/* 144 */     if (cgb1.getCat_sort() > cgb2.getCat_sort()) {
/* 145 */       return 1;
/*     */     }
/* 147 */     if (cgb1.getCat_sort() == cgb2.getCat_sort()) {
/* 148 */       return 0;
/*     */     }
/* 150 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendCountManager.sendCategoryComparator
 * JD-Core Version:    0.6.2
 */