/*     */ package com.cicro.analyzer_bak_20151106;
/*     */ 
/*     */ import com.cicro.analyzer.*;
import com.cicro.analyzer.dic.Dictionary;
/*     */ import com.cicro.analyzer.seg.ISegmenter;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Context
/*     */ {
/*  17 */   private boolean isMaxWordLength = false;
/*     */   private int buffOffset;
/*     */   private int available;
/*     */   private int lastAnalyzed;
/*     */   private int cursor;
/*     */   private char[] segmentBuff;
/*     */   private Set<ISegmenter> buffLocker;
/*     */   private Context.IKSortedLinkSet lexemeSet;
/*     */ 
/*     */   Context(char[] segmentBuff, boolean isMaxWordLength)
/*     */   {
/*  41 */     this.isMaxWordLength = isMaxWordLength;
/*  42 */     this.segmentBuff = segmentBuff;
/*  43 */     this.buffLocker = new HashSet(4);
/*  44 */     this.lexemeSet = new Context.IKSortedLinkSet(this, null);
/*     */   }
/*     */ 
/*     */   public void resetContext()
/*     */   {
/*  51 */     this.buffLocker.clear();
/*  52 */     this.lexemeSet = new Context.IKSortedLinkSet(this, null);
/*  53 */     this.buffOffset = 0;
/*  54 */     this.available = 0;
/*  55 */     this.lastAnalyzed = 0;
/*  56 */     this.cursor = 0;
/*     */   }
/*     */ 
/*     */   public boolean isMaxWordLength() {
/*  60 */     return this.isMaxWordLength;
/*     */   }
/*     */ 
/*     */   public void setMaxWordLength(boolean isMaxWordLength) {
/*  64 */     this.isMaxWordLength = isMaxWordLength;
/*     */   }
/*     */ 
/*     */   public int getBuffOffset() {
/*  68 */     return this.buffOffset;
/*     */   }
/*     */ 
/*     */   public void setBuffOffset(int buffOffset)
/*     */   {
/*  73 */     this.buffOffset = buffOffset;
/*     */   }
/*     */ 
/*     */   public int getLastAnalyzed() {
/*  77 */     return this.lastAnalyzed;
/*     */   }
/*     */ 
/*     */   public void setLastAnalyzed(int lastAnalyzed)
/*     */   {
/*  82 */     this.lastAnalyzed = lastAnalyzed;
/*     */   }
/*     */ 
/*     */   public int getCursor()
/*     */   {
/*  87 */     return this.cursor;
/*     */   }
/*     */ 
/*     */   public void setCursor(int cursor)
/*     */   {
/*  92 */     this.cursor = cursor;
/*     */   }
/*     */ 
/*     */   public void lockBuffer(ISegmenter segmenter) {
/*  96 */     this.buffLocker.add(segmenter);
/*     */   }
/*     */ 
/*     */   public void unlockBuffer(ISegmenter segmenter) {
/* 100 */     this.buffLocker.remove(segmenter);
/*     */   }
/*     */ 
/*     */   public boolean isBufferLocked()
/*     */   {
/* 109 */     return this.buffLocker.size() > 0;
/*     */   }
/*     */ 
/*     */   public int getAvailable() {
/* 113 */     return this.available;
/*     */   }
/*     */ 
/*     */   public void setAvailable(int available) {
/* 117 */     this.available = available;
/*     */   }
/*     */ 
/*     */   public com.cicro.analyzer.Lexeme firstLexeme()
/*     */   {
/* 127 */     return Context.IKSortedLinkSet.access$1(this.lexemeSet);
/*     */   }
/*     */ 
/*     */   public com.cicro.analyzer.Lexeme lastLexeme()
/*     */   {
/* 135 */     return Context.IKSortedLinkSet.access$2(this.lexemeSet);
/*     */   }
/*     */ 
/*     */   public void addLexeme(com.cicro.analyzer.Lexeme lexeme)
/*     */   {
/* 143 */     if (!Dictionary.isStopWord(this.segmentBuff, lexeme.getBegin(), lexeme.getLength()))
/* 144 */       Context.IKSortedLinkSet.access$3(this.lexemeSet, lexeme);
/*     */   }
/*     */ 
/*     */   public int getResultSize()
/*     */   {
/* 153 */     return Context.IKSortedLinkSet.access$4(this.lexemeSet);
/*     */   }
/*     */ 
/*     */   public void excludeOverlap()
/*     */   {
/* 161 */     Context.IKSortedLinkSet.access$5(this.lexemeSet);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.Context
 * JD-Core Version:    0.6.2
 */