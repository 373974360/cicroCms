/*     */ package com.cicro.analyzer_bak_20151106;
/*     */ 
/*     */ import com.cicro.analyzer.*;
import com.cicro.analyzer.Lexeme;
import com.cicro.analyzer.cfg.Configuration;
/*     */ import com.cicro.analyzer.help.CharacterHelper;
/*     */ import com.cicro.analyzer.seg.ISegmenter;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class IKSegmentation
/*     */ {
/*     */   private Reader input;
/*     */   private static final int BUFF_SIZE = 3072;
/*     */   private static final int BUFF_EXHAUST_CRITICAL = 48;
/*     */   private char[] segmentBuff;
/*     */   private com.cicro.analyzer.Context context;
/*     */   private List<ISegmenter> segmenters;
/*     */ 
/*     */   public IKSegmentation(Reader input)
/*     */   {
/*  42 */     this(input, false);
/*     */   }
/*     */ 
/*     */   public IKSegmentation(Reader input, boolean isMaxWordLength)
/*     */   {
/*  51 */     this.input = input;
/*  52 */     this.segmentBuff = new char[3072];
/*  53 */     this.context = new com.cicro.analyzer.Context(this.segmentBuff, isMaxWordLength);
/*  54 */     this.segmenters = Configuration.loadSegmenter();
/*     */   }
/*     */ 
/*     */   public synchronized com.cicro.analyzer.Lexeme next()
/*     */     throws IOException
/*     */   {
/*  63 */     if (this.context.getResultSize() == 0)
/*     */     {
/*  69 */       int available = fillBuffer(this.input);
/*     */ 
/*  71 */       if (available <= 0) {
/*  72 */         this.context.resetContext();
/*  73 */         return null;
/*     */       }
/*     */ 
/*  76 */       int analyzedLength = 0;
/*     */       ISegmenter segmenter;
/*  77 */       for (int buffIndex = 0; buffIndex < available; buffIndex++)
/*     */       {
/*  79 */         this.context.setCursor(buffIndex);
/*     */ 
/*  81 */         this.segmentBuff[buffIndex] = CharacterHelper.regularize(this.segmentBuff[buffIndex]);
/*     */ 
/*  83 */         for (Iterator localIterator = this.segmenters.iterator(); localIterator.hasNext(); ) { segmenter = (ISegmenter)localIterator.next();
/*  84 */           segmenter.nextLexeme(this.segmentBuff, this.context);
/*     */         }
/*  86 */         analyzedLength++;
/*     */ 
/*  94 */         if ((available == 3072) && 
/*  95 */           (buffIndex < available - 1) && 
/*  96 */           (buffIndex > available - 48) && 
/*  97 */           (!this.context.isBufferLocked()))
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/*     */ 
/* 103 */       for (ISegmenter segmenter : this.segmenters) {
/* 104 */         segmenter.reset();
/*     */       }
/*     */ 
/* 108 */       this.context.setLastAnalyzed(analyzedLength);
/*     */ 
/* 110 */       this.context.setBuffOffset(this.context.getBuffOffset() + analyzedLength);
/*     */ 
/* 112 */       if (this.context.isMaxWordLength()) {
/* 113 */         this.context.excludeOverlap();
/*     */       }
/*     */ 
/* 116 */       return buildLexeme(this.context.firstLexeme());
/*     */     }
/*     */ 
/* 120 */     return buildLexeme(this.context.firstLexeme());
/*     */   }
/*     */ 
/*     */   private int fillBuffer(Reader reader)
/*     */     throws IOException
/*     */   {
/* 131 */     int readCount = 0;
/* 132 */     if (this.context.getBuffOffset() == 0)
/*     */     {
/* 134 */       readCount = reader.read(this.segmentBuff);
/*     */     } else {
/* 136 */       int offset = this.context.getAvailable() - this.context.getLastAnalyzed();
/* 137 */       if (offset > 0)
/*     */       {
/* 139 */         System.arraycopy(this.segmentBuff, this.context.getLastAnalyzed(), this.segmentBuff, 0, offset);
/* 140 */         readCount = offset;
/*     */       }
/*     */ 
/* 143 */       readCount += reader.read(this.segmentBuff, offset, 3072 - offset);
/*     */     }
/*     */ 
/* 146 */     this.context.setAvailable(readCount);
/* 147 */     return readCount;
/*     */   }
/*     */ 
/*     */   private com.cicro.analyzer.Lexeme buildLexeme(Lexeme lexeme)
/*     */   {
/* 155 */     if (lexeme != null)
/*     */     {
/* 157 */       lexeme.setLexemeText(String.valueOf(this.segmentBuff, lexeme.getBegin(), lexeme.getLength()));
/* 158 */       return lexeme;
/*     */     }
/*     */ 
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */   public synchronized void reset(Reader input)
/*     */   {
/* 170 */     this.input = input;
/* 171 */     this.context.resetContext();
/* 172 */     for (ISegmenter segmenter : this.segmenters)
/* 173 */       segmenter.reset();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.IKSegmentation
 * JD-Core Version:    0.6.2
 */