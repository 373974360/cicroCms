/*    */ package com.cicro.analyzer_bak_20151106.dic;

import com.cicro.analyzer.dic.DictSegment;

/*    */
/*    */ public class Hit
/*    */ {
/*    */   private static final int UNMATCH = 0;
/*    */   private static final int MATCH = 1;
/*    */   private static final int PREFIX = 16;
/* 22 */   private int hitState = 0;
/*    */   private DictSegment matchedDictSegment;
/*    */   private int begin;
/*    */   private int end;
/*    */ 
/*    */   public boolean isMatch()
/*    */   {
/* 40 */     return (this.hitState & 0x1) > 0;
/*    */   }
/*    */ 
/*    */   public void setMatch()
/*    */   {
/* 46 */     this.hitState |= 1;
/*    */   }
/*    */ 
/*    */   public boolean isPrefix()
/*    */   {
/* 53 */     return (this.hitState & 0x10) > 0;
/*    */   }
/*    */ 
/*    */   public void setPrefix()
/*    */   {
/* 59 */     this.hitState |= 16;
/*    */   }
/*    */ 
/*    */   public boolean isUnmatch()
/*    */   {
/* 65 */     return this.hitState == 0;
/*    */   }
/*    */ 
/*    */   public void setUnmatch()
/*    */   {
/* 71 */     this.hitState = 0;
/*    */   }
/*    */ 
/*    */   public DictSegment getMatchedDictSegment() {
/* 75 */     return this.matchedDictSegment;
/*    */   }
/*    */ 
/*    */   public void setMatchedDictSegment(DictSegment matchedDictSegment) {
/* 79 */     this.matchedDictSegment = matchedDictSegment;
/*    */   }
/*    */ 
/*    */   public int getBegin() {
/* 83 */     return this.begin;
/*    */   }
/*    */ 
/*    */   public void setBegin(int begin) {
/* 87 */     this.begin = begin;
/*    */   }
/*    */ 
/*    */   public int getEnd() {
/* 91 */     return this.end;
/*    */   }
/*    */ 
/*    */   public void setEnd(int end) {
/* 95 */     this.end = end;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.dic.Hit
 * JD-Core Version:    0.6.2
 */