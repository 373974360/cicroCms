/*    */ package com.cicro.analyzer_bak_20151106.lucene;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import com.cicro.analyzer.lucene.IKTokenizer;
import org.apache.lucene.analysis.Analyzer;
/*    */ import org.apache.lucene.analysis.TokenStream;
/*    */ 
/*    */ public final class IKAnalyzer extends Analyzer
/*    */ {
/* 20 */   private boolean isMaxWordLength = false;
/* 21 */   private boolean isRead = false;
/*    */ 
/*    */   public IKAnalyzer()
/*    */   {
/* 28 */     this(false);
/*    */   }
/*    */ 
/*    */   public IKAnalyzer(boolean isMaxWordLength)
/*    */   {
/* 38 */     setMaxWordLength(isMaxWordLength);
/*    */   }
/*    */ 
/*    */   public TokenStream tokenStream(String fieldName, Reader reader)
/*    */   {
/* 47 */     return new IKTokenizer(reader, isMaxWordLength());
/*    */   }
/*    */ 
/*    */   public void setMaxWordLength(boolean isMaxWordLength) {
/* 51 */     this.isMaxWordLength = isMaxWordLength;
/*    */   }
/*    */ 
/*    */   public boolean isMaxWordLength() {
/* 55 */     return this.isMaxWordLength;
/*    */   }
/*    */ 
/*    */   public boolean isRead() {
/* 59 */     return this.isRead;
/*    */   }
/*    */ 
/*    */   public void setRead(boolean isRead) {
/* 63 */     this.isRead = isRead;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKAnalyzer
 * JD-Core Version:    0.6.2
 */