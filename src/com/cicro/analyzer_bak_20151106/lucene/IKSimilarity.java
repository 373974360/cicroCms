/*    */ package com.cicro.analyzer_bak_20151106.lucene;
/*    */ 
/*    */ import org.apache.lucene.search.DefaultSimilarity;
/*    */ 
/*    */ public class IKSimilarity extends DefaultSimilarity
/*    */ {
/*    */   private static final long serialVersionUID = 7558565500061194774L;
/*    */ 
/*    */   public float coord(int overlap, int maxOverlap)
/*    */   {
/* 26 */     float overlap2 = (float)Math.pow(2.0D, overlap);
/* 27 */     float maxOverlap2 = (float)Math.pow(2.0D, maxOverlap);
/* 28 */     return overlap2 / maxOverlap2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKSimilarity
 * JD-Core Version:    0.6.2
 */