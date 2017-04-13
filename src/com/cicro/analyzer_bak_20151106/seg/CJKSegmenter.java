/*     */ package com.cicro.analyzer_bak_20151106.seg;
/*     */ 
/*     */ import com.cicro.analyzer.Context;
/*     */ import com.cicro.analyzer.Lexeme;
/*     */ import com.cicro.analyzer.dic.Dictionary;
/*     */ import com.cicro.analyzer.dic.Hit;
/*     */ import com.cicro.analyzer.help.CharacterHelper;
import com.cicro.analyzer.seg.*;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CJKSegmenter
/*     */   implements com.cicro.analyzer.seg.ISegmenter
/*     */ {
/*     */   private int doneIndex;
/*     */   private List<Hit> hitList;
/*     */ 
/*     */   public CJKSegmenter()
/*     */   {
/*  36 */     this.doneIndex = -1;
/*     */ 
/*  38 */     this.hitList = new LinkedList();
/*     */   }
/*     */ 
/*     */   public void nextLexeme(char[] segmentBuff, Context context)
/*     */   {
/*  47 */     char input = segmentBuff[context.getCursor()];
/*     */     Lexeme newLexeme;
/*  49 */     if (CharacterHelper.isCJKCharacter(input)) {
/*  50 */       if (this.hitList.size() > 0)
/*     */       {
/*  52 */         Hit[] tmpArray = (Hit[])this.hitList.toArray(new Hit[this.hitList.size()]);
/*  53 */         for (Hit hit : tmpArray) {
/*  54 */           hit = Dictionary.matchWithHit(segmentBuff, context.getCursor(), hit);
/*     */ 
/*  56 */           if (hit.isMatch())
/*     */           {
/*  58 */             if (hit.getBegin() > this.doneIndex + 1)
/*     */             {
/*  60 */               processUnknown(segmentBuff, context, this.doneIndex + 1, hit.getBegin() - 1);
/*     */             }
/*     */ 
/*  63 */             Lexeme newLexeme = new Lexeme(context.getBuffOffset(), hit.getBegin(), context.getCursor() - hit.getBegin() + 1, 0);
/*  64 */             context.addLexeme(newLexeme);
/*     */ 
/*  66 */             if (this.doneIndex < context.getCursor()) {
/*  67 */               this.doneIndex = context.getCursor();
/*     */             }
/*     */ 
/*  70 */             if (!hit.isPrefix())
/*     */             {
/*  74 */               this.hitList.remove(hit);
/*     */             }
/*     */           }
/*  77 */           else if (!hit.isPrefix())
/*     */           {
/*  79 */             if (hit.isUnmatch())
/*     */             {
/*  81 */               this.hitList.remove(hit);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*  87 */       Hit hit = Dictionary.matchInMainDict(segmentBuff, context.getCursor(), 1);
/*  88 */       if (hit.isMatch())
/*     */       {
/*  90 */         if (context.getCursor() > this.doneIndex + 1)
/*     */         {
/*  92 */           processUnknown(segmentBuff, context, this.doneIndex + 1, context.getCursor() - 1);
/*     */         }
/*     */ 
/*  95 */         newLexeme = new Lexeme(context.getBuffOffset(), context.getCursor(), 1, 0);
/*  96 */         context.addLexeme(newLexeme);
/*     */ 
/*  98 */         if (this.doneIndex < context.getCursor()) {
/*  99 */           this.doneIndex = context.getCursor();
/*     */         }
/*     */ 
/* 102 */         if (hit.isPrefix())
/*     */         {
/* 104 */           this.hitList.add(hit);
/*     */         }
/*     */       }
/* 107 */       else if (hit.isPrefix())
/*     */       {
/* 109 */         this.hitList.add(hit);
/*     */       }
/* 111 */       else if (hit.isUnmatch()) {
/* 112 */         if (this.doneIndex >= context.getCursor())
/*     */         {
/* 114 */           return;
/*     */         }
/*     */ 
/* 118 */         processUnknown(segmentBuff, context, this.doneIndex + 1, context.getCursor());
/*     */ 
/* 120 */         this.doneIndex = context.getCursor();
/*     */       }
/*     */     }
/*     */     else {
/* 124 */       if ((this.hitList.size() > 0) && 
/* 125 */         (this.doneIndex < context.getCursor() - 1)) {
/* 126 */         for (Hit hit : this.hitList)
/*     */         {
/* 128 */           if (this.doneIndex < hit.getEnd())
/*     */           {
/* 130 */             processUnknown(segmentBuff, context, this.doneIndex + 1, hit.getEnd());
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 135 */       this.hitList.clear();
/*     */ 
/* 137 */       if (this.doneIndex < context.getCursor()) {
/* 138 */         this.doneIndex = context.getCursor();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 143 */     if (context.getCursor() == context.getAvailable() - 1) {
/* 144 */       if ((this.hitList.size() > 0) && 
/* 145 */         (this.doneIndex < context.getCursor())) {
/* 146 */         for (Hit hit : this.hitList)
/*     */         {
/* 148 */           if (this.doneIndex < hit.getEnd())
/*     */           {
/* 150 */             processUnknown(segmentBuff, context, this.doneIndex + 1, hit.getEnd());
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 155 */       this.hitList.clear();
/*     */     }
/*     */ 
/* 159 */     if (this.hitList.size() == 0) {
/* 160 */       context.unlockBuffer(this);
/*     */     }
/*     */     else
/* 163 */       context.lockBuffer(this);
/*     */   }
/*     */ 
/*     */   private void processUnknown(char[] segmentBuff, Context context, int uBegin, int uEnd)
/*     */   {
/* 175 */     Lexeme newLexeme = null;
/*     */ 
/* 177 */     Hit hit = Dictionary.matchInPrepDict(segmentBuff, uBegin, 1);
/* 178 */     if ((hit.isUnmatch()) && 
/* 179 */       (uBegin > 0)) {
/* 180 */       hit = Dictionary.matchInSurnameDict(segmentBuff, uBegin - 1, 1);
/* 181 */       if (hit.isMatch())
/*     */       {
/* 183 */         newLexeme = new Lexeme(context.getBuffOffset(), uBegin - 1, 1, 1);
/* 184 */         context.addLexeme(newLexeme);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 190 */     for (int i = uBegin; i <= uEnd; i++) {
/* 191 */       newLexeme = new Lexeme(context.getBuffOffset(), i, 1, 3);
/* 192 */       context.addLexeme(newLexeme);
/*     */     }
/*     */ 
/* 195 */     hit = Dictionary.matchInPrepDict(segmentBuff, uEnd, 1);
/* 196 */     if (hit.isUnmatch()) {
/* 197 */       int length = 1;
/* 198 */       while (uEnd < context.getAvailable() - length) {
/* 199 */         hit = Dictionary.matchInSuffixDict(segmentBuff, uEnd + 1, length);
/* 200 */         if (hit.isMatch())
/*     */         {
/* 202 */           newLexeme = new Lexeme(context.getBuffOffset(), uEnd + 1, length, 2);
/* 203 */           context.addLexeme(newLexeme);
/* 204 */           break;
/*     */         }
/* 206 */         if (hit.isUnmatch()) {
/*     */           break;
/*     */         }
/* 209 */         length++;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 216 */     this.doneIndex = -1;
/* 217 */     this.hitList.clear();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.seg.CJKSegmenter
 * JD-Core Version:    0.6.2
 */