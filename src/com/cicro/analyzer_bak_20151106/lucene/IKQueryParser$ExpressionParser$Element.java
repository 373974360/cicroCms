/*     */ package com.cicro.analyzer_bak_20151106.lucene;

import com.cicro.analyzer.lucene.*;

/*     */
/*     */ class IKQueryParser$ExpressionParser$Element
/*     */ {
/* 963 */   char type = '\000';
/*     */ 
/* 967 */   StringBuffer eleTextBuff = new StringBuffer();
/*     */ 
/*     */   public IKQueryParser$ExpressionParser$Element(com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser paramExpressionParser) {
/*     */   }
/* 971 */   public void append(char c) { this.eleTextBuff.append(c); }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 975 */     return this.eleTextBuff.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element
 * JD-Core Version:    0.6.2
 */