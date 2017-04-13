/*    */ package com.cicro.analyzer_bak_20151106.lucene;
/*    */ 
/*    */ import com.cicro.analyzer.IKSegmentation;
/*    */ import com.cicro.analyzer.Lexeme;
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ import org.apache.lucene.analysis.Tokenizer;
/*    */ import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
/*    */ import org.apache.lucene.analysis.tokenattributes.TermAttribute;
/*    */ 
/*    */ public final class IKTokenizer extends Tokenizer
/*    */ {
/*    */   private IKSegmentation _IKImplement;
/*    */   private TermAttribute termAtt;
/*    */   private OffsetAttribute offsetAtt;
/*    */   private int finalOffset;
/*    */ 
/*    */   public IKTokenizer(Reader in, boolean isMaxWordLength)
/*    */   {
/* 41 */     super(in);
/* 42 */     this.offsetAtt = ((OffsetAttribute)addAttribute(OffsetAttribute.class));
/* 43 */     this.termAtt = ((TermAttribute)addAttribute(TermAttribute.class));
/* 44 */     this._IKImplement = new IKSegmentation(in, isMaxWordLength);
/*    */   }
/*    */ 
/*    */   public final boolean incrementToken()
/*    */     throws IOException
/*    */   {
/* 50 */     clearAttributes();
/* 51 */     Lexeme nextLexeme = this._IKImplement.next();
/* 52 */     if (nextLexeme != null)
/*    */     {
/* 55 */       this.termAtt.setTermBuffer(nextLexeme.getLexemeText());
/*    */ 
/* 57 */       this.termAtt.setTermLength(nextLexeme.getLength());
/*    */ 
/* 59 */       this.offsetAtt.setOffset(nextLexeme.getBeginPosition(), nextLexeme.getEndPosition());
/*    */ 
/* 61 */       this.finalOffset = nextLexeme.getEndPosition();
/*    */ 
/* 63 */       return true;
/*    */     }
/*    */ 
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   public void reset(Reader input)
/*    */     throws IOException
/*    */   {
/* 74 */     super.reset(input);
/* 75 */     this._IKImplement.reset(input);
/*    */   }
/*    */ 
/*    */   public final void end()
/*    */   {
/* 81 */     this.offsetAtt.setOffset(this.finalOffset, this.finalOffset);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKTokenizer
 * JD-Core Version:    0.6.2
 */