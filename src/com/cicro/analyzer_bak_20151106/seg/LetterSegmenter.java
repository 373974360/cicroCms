/*     */ package com.cicro.analyzer_bak_20151106.seg;
/*     */ 
/*     */ import com.cicro.analyzer.Context;
/*     */ import com.cicro.analyzer.Lexeme;
/*     */ import com.cicro.analyzer.help.CharacterHelper;
import com.cicro.analyzer.seg.*;

/*     */
/*     */ public class LetterSegmenter
/*     */   implements com.cicro.analyzer.seg.ISegmenter
/*     */ {
/*  21 */   public static final char[] Sign_Connector = { '+', '-', '_', '.', '@', '&', '/', '\\' };
/*     */   private int start;
/*     */   private int end;
/*     */   private int letterStart;
/*     */   private int letterEnd;
/*     */ 
/*     */   public LetterSegmenter()
/*     */   {
/*  56 */     this.start = -1;
/*  57 */     this.end = -1;
/*  58 */     this.letterStart = -1;
/*  59 */     this.letterEnd = -1;
/*     */   }
/*     */ 
/*     */   public void nextLexeme(char[] segmentBuff, Context context)
/*     */   {
/*  70 */     char input = segmentBuff[context.getCursor()];
/*     */ 
/*  72 */     boolean bufferLockFlag = false;
/*     */ 
/*  74 */     bufferLockFlag = (processMixLetter(input, context)) || (bufferLockFlag);
/*     */ 
/*  76 */     bufferLockFlag = (processEnglishLetter(input, context)) || (bufferLockFlag);
/*     */ 
/*  81 */     if (bufferLockFlag) {
/*  82 */       context.lockBuffer(this);
/*     */     }
/*     */     else
/*  85 */       context.unlockBuffer(this);
/*     */   }
/*     */ 
/*     */   private boolean processMixLetter(char input, Context context)
/*     */   {
/*  97 */     boolean needLock = false;
/*     */ 
/*  99 */     if (this.start == -1) {
/* 100 */       if (isAcceptedCharStart(input))
/*     */       {
/* 102 */         this.start = context.getCursor();
/* 103 */         this.end = this.start;
/*     */       }
/*     */ 
/*     */     }
/* 107 */     else if (isAcceptedChar(input))
/*     */     {
/* 114 */       this.end = context.getCursor();
/*     */     }
/*     */     else
/*     */     {
/* 118 */       Lexeme newLexeme = new Lexeme(context.getBuffOffset(), this.start, this.end - this.start + 1, 20);
/* 119 */       context.addLexeme(newLexeme);
/*     */ 
/* 121 */       this.start = -1;
/* 122 */       this.end = -1;
/*     */     }
/*     */ 
/* 127 */     if (context.getCursor() == context.getAvailable() - 1) {
/* 128 */       if ((this.start != -1) && (this.end != -1))
/*     */       {
/* 130 */         Lexeme newLexeme = new Lexeme(context.getBuffOffset(), this.start, this.end - this.start + 1, 20);
/* 131 */         context.addLexeme(newLexeme);
/*     */       }
/*     */ 
/* 134 */       this.start = -1;
/* 135 */       this.end = -1;
/*     */     }
/*     */ 
/* 139 */     if ((this.start == -1) && (this.end == -1))
/*     */     {
/* 141 */       needLock = false;
/*     */     }
/* 143 */     else needLock = true;
/*     */ 
/* 145 */     return needLock;
/*     */   }
/*     */ 
/*     */   private boolean processEnglishLetter(char input, Context context)
/*     */   {
/* 206 */     boolean needLock = false;
/*     */ 
/* 208 */     if (this.letterStart == -1) {
/* 209 */       if (CharacterHelper.isEnglishLetter(input))
/*     */       {
/* 211 */         this.letterStart = context.getCursor();
/* 212 */         this.letterEnd = this.letterStart;
/*     */       }
/*     */     }
/* 215 */     else if (CharacterHelper.isEnglishLetter(input))
/*     */     {
/* 217 */       this.letterEnd = context.getCursor();
/*     */     }
/*     */     else {
/* 220 */       Lexeme newLexeme = new Lexeme(context.getBuffOffset(), this.letterStart, this.letterEnd - this.letterStart + 1, 20);
/* 221 */       context.addLexeme(newLexeme);
/*     */ 
/* 223 */       this.letterStart = -1;
/* 224 */       this.letterEnd = -1;
/*     */     }
/*     */ 
/* 229 */     if (context.getCursor() == context.getAvailable() - 1) {
/* 230 */       if ((this.letterStart != -1) && (this.letterEnd != -1))
/*     */       {
/* 232 */         Lexeme newLexeme = new Lexeme(context.getBuffOffset(), this.letterStart, this.letterEnd - this.letterStart + 1, 20);
/* 233 */         context.addLexeme(newLexeme);
/*     */       }
/*     */ 
/* 236 */       this.letterStart = -1;
/* 237 */       this.letterEnd = -1;
/*     */     }
/*     */ 
/* 241 */     if ((this.letterStart == -1) && (this.letterEnd == -1))
/*     */     {
/* 243 */       needLock = false;
/*     */     }
/* 245 */     else needLock = true;
/*     */ 
/* 247 */     return needLock;
/*     */   }
/*     */ 
/*     */   private boolean isLetterConnector(char input)
/*     */   {
/* 256 */     for (char c : Sign_Connector) {
/* 257 */       if (c == input) {
/* 258 */         return true;
/*     */       }
/*     */     }
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean isAcceptedCharStart(char input)
/*     */   {
/* 270 */     return (CharacterHelper.isEnglishLetter(input)) || 
/* 270 */       (CharacterHelper.isArabicNumber(input));
/*     */   }
/*     */ 
/*     */   private boolean isAcceptedChar(char input)
/*     */   {
/* 280 */     return (isLetterConnector(input)) || 
/* 279 */       (CharacterHelper.isEnglishLetter(input)) || 
/* 280 */       (CharacterHelper.isArabicNumber(input));
/*     */   }
/*     */ 
/*     */   public void reset() {
/* 284 */     this.start = -1;
/* 285 */     this.end = -1;
/* 286 */     this.letterStart = -1;
/* 287 */     this.letterEnd = -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.seg.LetterSegmenter
 * JD-Core Version:    0.6.2
 */