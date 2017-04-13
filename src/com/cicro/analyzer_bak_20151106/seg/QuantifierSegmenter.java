/*     */ package com.cicro.analyzer_bak_20151106.seg;
/*     */ 
/*     */ import com.cicro.analyzer.Context;
/*     */ import com.cicro.analyzer.Lexeme;
/*     */ import com.cicro.analyzer.dic.Dictionary;
/*     */ import com.cicro.analyzer.dic.Hit;
/*     */ import com.cicro.analyzer.help.CharacterHelper;
import com.cicro.analyzer.seg.*;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class QuantifierSegmenter
/*     */   implements com.cicro.analyzer.seg.ISegmenter
/*     */ {
/*     */   public static final int NC_ARABIC = 2;
/*  40 */   public static String Arabic_Num_Mid = ",./:Ee";
/*  41 */   private static Set<Character> ArabicNumMidChars = new HashSet();
/*     */   public static final int NC_ANM = 3;
/*     */   public static String Num_Pre;
/*     */   public static final int NC_NP = 11;
/*     */   public static String Chn_Num;
/*     */   private static Set<Character> ChnNumberChars;
/*     */   public static final int NC_CHINESE = 12;
/*     */   public static String Chn_Num_Mid;
/*     */   public static final int NC_CNM = 13;
/*     */   public static String Num_End;
/*     */   private static Set<Character> NumEndChars;
/*     */   public static final int NC_NE = 14;
/*     */   public static final int NaN = -99;
/*     */   private static Set<Character> AllNumberChars;
/*     */   private int nStart;
/*     */   private int nEnd;
/*     */   private int nStatus;
/*     */   private boolean fCaN;
/*     */   private int countStart;
/*     */   private int countEnd;
/*     */ 
/*     */   static
/*     */   {
/*  43 */     char[] ca = Arabic_Num_Mid.toCharArray();
/*  44 */     char[] arrayOfChar1 = ca; int j = ca.length; for (int i = 0; i < j; i++) { char nChar = arrayOfChar1[i];
/*  45 */       ArabicNumMidChars.add(Character.valueOf(nChar));
/*     */     }
/*     */ 
/*  54 */     Num_Pre = "第";
/*     */ 
/*  57 */     Chn_Num = "○一二两三四五六七八九十零壹贰叁肆伍陆柒捌玖拾百千万亿拾佰仟萬億兆卅廿";
/*  58 */     ChnNumberChars = new HashSet();
/*     */ 
/*  60 */     char[] ca = Chn_Num.toCharArray();
/*  61 */     arrayOfChar1 = ca; j = ca.length; for (i = 0; i < j; i++) { char nChar = arrayOfChar1[i];
/*  62 */       ChnNumberChars.add(Character.valueOf(nChar));
/*     */     }
/*     */ 
/*  67 */     Chn_Num_Mid = "点";
/*     */ 
/*  71 */     Num_End = "几多余半";
/*  72 */     NumEndChars = new HashSet();
/*     */ 
/*  74 */     char[] ca = Num_End.toCharArray();
/*  75 */     arrayOfChar1 = ca; j = ca.length; for (i = 0; i < j; i++) { char nChar = arrayOfChar1[i];
/*  76 */       NumEndChars.add(Character.valueOf(nChar));
/*     */     }
/*     */ 
/*  96 */     AllNumberChars = new HashSet(256);
/*     */ 
/*  98 */     char[] ca = (char[])null;
/*     */ 
/* 102 */     for (char nChar = '0'; nChar <= '9'; nChar = (char)(nChar + '\001')) {
/* 103 */       AllNumberChars.add(Character.valueOf(nChar));
/*     */     }
/*     */ 
/* 106 */     AllNumberChars.addAll(ArabicNumMidChars);
/*     */ 
/* 113 */     ca = Num_Pre.toCharArray();
/* 114 */     arrayOfChar1 = ca; j = ca.length; for (i = 0; i < j; i++) { char nChar = arrayOfChar1[i];
/* 115 */       AllNumberChars.add(Character.valueOf(nChar));
/*     */     }
/*     */ 
/* 118 */     AllNumberChars.addAll(ChnNumberChars);
/*     */ 
/* 120 */     ca = Chn_Num_Mid.toCharArray();
/* 121 */     arrayOfChar1 = ca; j = ca.length; for (i = 0; i < j; i++) { char nChar = arrayOfChar1[i];
/* 122 */       AllNumberChars.add(Character.valueOf(nChar));
/*     */     }
/*     */ 
/* 125 */     AllNumberChars.addAll(NumEndChars);
/*     */   }
/*     */ 
/*     */   public QuantifierSegmenter()
/*     */   {
/* 163 */     this.nStart = -1;
/* 164 */     this.nEnd = -1;
/* 165 */     this.nStatus = -99;
/* 166 */     this.fCaN = false;
/*     */ 
/* 168 */     this.countStart = -1;
/* 169 */     this.countEnd = -1;
/*     */   }
/*     */ 
/*     */   public void nextLexeme(char[] segmentBuff, Context context)
/*     */   {
/* 176 */     this.fCaN = false;
/*     */ 
/* 178 */     processNumber(segmentBuff, context);
/*     */ 
/* 181 */     if (this.countStart == -1)
/*     */     {
/* 183 */       if (((this.fCaN) && (this.nStart == -1)) || (
/* 184 */         (this.nEnd != -1) && (this.nEnd == context.getCursor() - 1)))
/*     */       {
/* 187 */         processCount(segmentBuff, context);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 192 */       processCount(segmentBuff, context);
/*     */     }
/*     */ 
/* 196 */     if ((this.nStart == -1) && (this.nEnd == -1) && (-99 == this.nStatus) && 
/* 197 */       (this.countStart == -1) && (this.countEnd == -1))
/*     */     {
/* 199 */       context.unlockBuffer(this);
/*     */     }
/* 201 */     else context.lockBuffer(this);
/*     */   }
/*     */ 
/*     */   private void processNumber(char[] segmentBuff, Context context)
/*     */   {
/* 212 */     int inputStatus = nIdentify(segmentBuff, context);
/*     */ 
/* 214 */     if (-99 == this.nStatus)
/*     */     {
/* 216 */       onNaNStatus(inputStatus, context);
/*     */     }
/* 222 */     else if (2 == this.nStatus)
/*     */     {
/* 224 */       onARABICStatus(inputStatus, context);
/*     */     }
/* 226 */     else if (3 == this.nStatus)
/*     */     {
/* 228 */       onANMStatus(inputStatus, context);
/*     */     }
/* 234 */     else if (11 == this.nStatus)
/*     */     {
/* 236 */       onNPStatus(inputStatus, context);
/*     */     }
/* 238 */     else if (12 == this.nStatus)
/*     */     {
/* 240 */       onCHINESEStatus(inputStatus, context);
/*     */     }
/* 242 */     else if (13 == this.nStatus)
/*     */     {
/* 244 */       onCNMStatus(inputStatus, context);
/*     */     }
/* 246 */     else if (14 == this.nStatus)
/*     */     {
/* 248 */       onCNEStatus(inputStatus, context);
/*     */     }
/*     */ 
/* 257 */     if (context.getCursor() == context.getAvailable() - 1) {
/* 258 */       if ((this.nStart != -1) && (this.nEnd != -1))
/*     */       {
/* 260 */         outputNumLexeme(context);
/*     */       }
/*     */ 
/* 263 */       nReset();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onNaNStatus(int inputStatus, Context context)
/*     */   {
/* 273 */     if (-99 == inputStatus) {
/* 274 */       return;
/*     */     }
/* 276 */     if (11 == inputStatus)
/*     */     {
/* 278 */       this.nStart = context.getCursor();
/*     */ 
/* 280 */       this.nStatus = inputStatus;
/*     */     }
/* 282 */     else if (12 == inputStatus)
/*     */     {
/* 284 */       this.nStart = context.getCursor();
/*     */ 
/* 286 */       this.nStatus = inputStatus;
/*     */ 
/* 288 */       this.nEnd = context.getCursor();
/*     */     }
/* 290 */     else if (14 == inputStatus)
/*     */     {
/* 292 */       this.nStart = context.getCursor();
/*     */ 
/* 294 */       this.nStatus = inputStatus;
/*     */ 
/* 296 */       this.nEnd = context.getCursor();
/*     */     }
/* 304 */     else if (2 == inputStatus)
/*     */     {
/* 306 */       this.nStart = context.getCursor();
/*     */ 
/* 308 */       this.nStatus = inputStatus;
/*     */ 
/* 310 */       this.nEnd = context.getCursor();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onARABICStatus(int inputStatus, Context context)
/*     */   {
/* 356 */     if (2 == inputStatus)
/*     */     {
/* 359 */       this.nEnd = context.getCursor();
/*     */     }
/* 361 */     else if (3 == inputStatus)
/*     */     {
/* 363 */       this.nStatus = inputStatus;
/*     */     }
/* 374 */     else if (12 == inputStatus)
/*     */     {
/* 376 */       this.nEnd = context.getCursor();
/*     */ 
/* 378 */       this.nStatus = inputStatus;
/*     */     }
/* 380 */     else if (14 == inputStatus)
/*     */     {
/* 382 */       this.nEnd = context.getCursor();
/*     */ 
/* 384 */       this.nStatus = inputStatus;
/*     */     }
/*     */     else
/*     */     {
/* 388 */       outputNumLexeme(context);
/*     */ 
/* 390 */       nReset();
/*     */ 
/* 392 */       onNaNStatus(inputStatus, context);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onANMStatus(int inputStatus, Context context)
/*     */   {
/* 404 */     if (2 == inputStatus)
/*     */     {
/* 406 */       this.nStatus = inputStatus;
/*     */ 
/* 408 */       this.nEnd = context.getCursor();
/*     */     }
/*     */     else
/*     */     {
/* 416 */       outputNumLexeme(context);
/*     */ 
/* 418 */       nReset();
/*     */ 
/* 420 */       onNaNStatus(inputStatus, context);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onNPStatus(int inputStatus, Context context)
/*     */   {
/* 448 */     if (12 == inputStatus)
/*     */     {
/* 450 */       this.nEnd = context.getCursor();
/*     */ 
/* 452 */       this.nStatus = inputStatus;
/*     */     }
/* 455 */     else if (2 == inputStatus)
/*     */     {
/* 457 */       this.nEnd = context.getCursor();
/*     */ 
/* 459 */       this.nStatus = inputStatus;
/*     */     }
/*     */     else
/*     */     {
/* 473 */       nReset();
/*     */ 
/* 475 */       onNaNStatus(inputStatus, context);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onCHINESEStatus(int inputStatus, Context context)
/*     */   {
/* 486 */     if (12 == inputStatus)
/*     */     {
/* 488 */       this.nEnd = context.getCursor();
/*     */     }
/* 490 */     else if (13 == inputStatus)
/*     */     {
/* 492 */       this.nStatus = inputStatus;
/*     */     }
/* 494 */     else if (14 == inputStatus)
/*     */     {
/* 496 */       this.nStatus = inputStatus;
/*     */ 
/* 498 */       this.nEnd = context.getCursor();
/*     */     }
/*     */     else
/*     */     {
/* 502 */       outputNumLexeme(context);
/*     */ 
/* 504 */       nReset();
/*     */ 
/* 506 */       onNaNStatus(inputStatus, context);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onCNMStatus(int inputStatus, Context context)
/*     */   {
/* 517 */     if (12 == inputStatus)
/*     */     {
/* 519 */       this.nStatus = inputStatus;
/*     */ 
/* 521 */       this.nEnd = context.getCursor();
/*     */     }
/* 523 */     else if (14 == inputStatus)
/*     */     {
/* 525 */       this.nStatus = inputStatus;
/*     */ 
/* 527 */       this.nEnd = context.getCursor();
/*     */     }
/*     */     else
/*     */     {
/* 531 */       outputNumLexeme(context);
/*     */ 
/* 533 */       nReset();
/*     */ 
/* 535 */       onNaNStatus(inputStatus, context);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onCNEStatus(int inputStatus, Context context)
/*     */   {
/* 547 */     outputNumLexeme(context);
/*     */ 
/* 549 */     nReset();
/*     */ 
/* 551 */     onNaNStatus(inputStatus, context);
/*     */   }
/*     */ 
/*     */   private void outputNumLexeme(Context context)
/*     */   {
/* 581 */     if ((this.nStart > -1) && (this.nEnd > -1))
/*     */     {
/* 583 */       Lexeme newLexeme = new Lexeme(context.getBuffOffset(), this.nStart, this.nEnd - this.nStart + 1, 10);
/* 584 */       context.addLexeme(newLexeme);
/* 585 */       this.fCaN = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void outputCountLexeme(Context context)
/*     */   {
/* 594 */     if ((this.countStart > -1) && (this.countEnd > -1))
/*     */     {
/* 596 */       Lexeme countLexeme = new Lexeme(context.getBuffOffset(), this.countStart, this.countEnd - this.countStart + 1, 11);
/* 597 */       context.addLexeme(countLexeme);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void nReset()
/*     */   {
/* 606 */     this.nStart = -1;
/* 607 */     this.nEnd = -1;
/* 608 */     this.nStatus = -99;
/*     */   }
/*     */ 
/*     */   private int nIdentify(char[] segmentBuff, Context context)
/*     */   {
/* 619 */     char input = segmentBuff[context.getCursor()];
/*     */ 
/* 621 */     int type = -99;
/* 622 */     if (!AllNumberChars.contains(Character.valueOf(input))) {
/* 623 */       return type;
/*     */     }
/*     */ 
/* 626 */     if (CharacterHelper.isArabicNumber(input)) {
/* 627 */       type = 2;
/*     */     }
/* 629 */     else if (ChnNumberChars.contains(Character.valueOf(input))) {
/* 630 */       type = 12;
/*     */     }
/* 632 */     else if (Num_Pre.indexOf(input) >= 0) {
/* 633 */       type = 11;
/*     */     }
/* 635 */     else if (Chn_Num_Mid.indexOf(input) >= 0) {
/* 636 */       type = 13;
/*     */     }
/* 638 */     else if (NumEndChars.contains(Character.valueOf(input))) {
/* 639 */       type = 14;
/*     */     }
/* 644 */     else if (ArabicNumMidChars.contains(Character.valueOf(input))) {
/* 645 */       type = 3;
/*     */     }
/*     */ 
/* 654 */     return type;
/*     */   }
/*     */ 
/*     */   private void processCount(char[] segmentBuff, Context context)
/*     */   {
/* 663 */     Hit hit = null;
/*     */ 
/* 665 */     if (this.countStart == -1)
/* 666 */       hit = Dictionary.matchInQuantifierDict(segmentBuff, context.getCursor(), 1);
/*     */     else {
/* 668 */       hit = Dictionary.matchInQuantifierDict(segmentBuff, this.countStart, context.getCursor() - this.countStart + 1);
/*     */     }
/*     */ 
/* 671 */     if (hit != null) {
/* 672 */       if ((hit.isPrefix()) && 
/* 673 */         (this.countStart == -1))
/*     */       {
/* 675 */         this.countStart = context.getCursor();
/*     */       }
/*     */ 
/* 679 */       if (hit.isMatch()) {
/* 680 */         if (this.countStart == -1) {
/* 681 */           this.countStart = context.getCursor();
/*     */         }
/*     */ 
/* 684 */         this.countEnd = context.getCursor();
/*     */ 
/* 686 */         outputCountLexeme(context);
/*     */       }
/*     */ 
/* 689 */       if ((hit.isUnmatch()) && 
/* 690 */         (this.countStart != -1))
/*     */       {
/* 692 */         this.countStart = -1;
/* 693 */         this.countEnd = -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 699 */     if (context.getCursor() == context.getAvailable() - 1)
/*     */     {
/* 701 */       this.countStart = -1;
/* 702 */       this.countEnd = -1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void reset() {
/* 707 */     this.nStart = -1;
/* 708 */     this.nEnd = -1;
/* 709 */     this.nStatus = -99;
/* 710 */     this.fCaN = false;
/*     */ 
/* 712 */     this.countStart = -1;
/* 713 */     this.countEnd = -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.seg.QuantifierSegmenter
 * JD-Core Version:    0.6.2
 */