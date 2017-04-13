/*     */ package com.cicro.analyzer_bak_20151106;
/*     */ 
/*     */ public final class Lexeme
/*     */   implements Comparable<Lexeme>
/*     */ {
/*     */   public static final int TYPE_CJK_NORMAL = 0;
/*     */   public static final int TYPE_CJK_SN = 1;
/*     */   public static final int TYPE_CJK_SF = 2;
/*     */   public static final int TYPE_CJK_UNKNOWN = 3;
/*     */   public static final int TYPE_NUM = 10;
/*     */   public static final int TYPE_NUMCOUNT = 11;
/*     */   public static final int TYPE_LETTER = 20;
/*     */   private int offset;
/*     */   private int begin;
/*     */   private int length;
/*     */   private String lexemeText;
/*     */   private int lexemeType;
/*     */   private Lexeme prev;
/*     */   private Lexeme next;
/*     */ 
/*     */   public Lexeme(int offset, int begin, int length, int lexemeType)
/*     */   {
/*  46 */     this.offset = offset;
/*  47 */     this.begin = begin;
/*  48 */     if (length < 0) {
/*  49 */       throw new IllegalArgumentException("length < 0");
/*     */     }
/*  51 */     this.length = length;
/*  52 */     this.lexemeType = lexemeType;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  61 */     if (o == null) {
/*  62 */       return false;
/*     */     }
/*     */ 
/*  65 */     if (this == o) {
/*  66 */       return true;
/*     */     }
/*     */ 
/*  69 */     if ((o instanceof Lexeme)) {
/*  70 */       Lexeme other = (Lexeme)o;
/*  71 */       if ((this.offset == other.getOffset()) && 
/*  72 */         (this.begin == other.getBegin()) && 
/*  73 */         (this.length == other.getLength())) {
/*  74 */         return true;
/*     */       }
/*  76 */       return false;
/*     */     }
/*     */ 
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  88 */     int absBegin = getBeginPosition();
/*  89 */     int absEnd = getEndPosition();
/*  90 */     return absBegin * 37 + absEnd * 31 + absBegin * absEnd % getLength() * 11;
/*     */   }
/*     */ 
/*     */   public int compareTo(Lexeme other)
/*     */   {
/*  99 */     if (this.begin < other.getBegin())
/* 100 */       return -1;
/* 101 */     if (this.begin == other.getBegin())
/*     */     {
/* 103 */       if (this.length > other.getLength())
/* 104 */         return -1;
/* 105 */       if (this.length == other.getLength()) {
/* 106 */         return 0;
/*     */       }
/* 108 */       return 1;
/*     */     }
/*     */ 
/* 112 */     return 1;
/*     */   }
/*     */ 
/*     */   public boolean isOverlap(Lexeme other)
/*     */   {
/* 122 */     if (other != null) {
/* 123 */       if ((getBeginPosition() <= other.getBeginPosition()) && 
/* 124 */         (getEndPosition() >= other.getEndPosition())) {
/* 125 */         return true;
/*     */       }
/* 127 */       if ((getBeginPosition() >= other.getBeginPosition()) && 
/* 128 */         (getEndPosition() <= other.getEndPosition())) {
/* 129 */         return true;
/*     */       }
/*     */ 
/* 132 */       return false;
/*     */     }
/*     */ 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public int getOffset() {
/* 139 */     return this.offset;
/*     */   }
/*     */ 
/*     */   public void setOffset(int offset) {
/* 143 */     this.offset = offset;
/*     */   }
/*     */ 
/*     */   public int getBegin() {
/* 147 */     return this.begin;
/*     */   }
/*     */ 
/*     */   public int getBeginPosition()
/*     */   {
/* 154 */     return this.offset + this.begin;
/*     */   }
/*     */ 
/*     */   public void setBegin(int begin) {
/* 158 */     this.begin = begin;
/*     */   }
/*     */ 
/*     */   public int getEndPosition()
/*     */   {
/* 166 */     return this.offset + this.begin + this.length;
/*     */   }
/*     */ 
/*     */   public int getLength()
/*     */   {
/* 174 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void setLength(int length) {
/* 178 */     if (this.length < 0) {
/* 179 */       throw new IllegalArgumentException("length < 0");
/*     */     }
/* 181 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public String getLexemeText()
/*     */   {
/* 189 */     if (this.lexemeText == null) {
/* 190 */       return "";
/*     */     }
/* 192 */     return this.lexemeText;
/*     */   }
/*     */ 
/*     */   public void setLexemeText(String lexemeText) {
/* 196 */     if (lexemeText == null) {
/* 197 */       this.lexemeText = "";
/* 198 */       this.length = 0;
/*     */     } else {
/* 200 */       this.lexemeText = lexemeText;
/* 201 */       this.length = lexemeText.length();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getLexemeType()
/*     */   {
/* 210 */     return this.lexemeType;
/*     */   }
/*     */ 
/*     */   public void setLexemeType(int lexemeType) {
/* 214 */     this.lexemeType = lexemeType;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 218 */     StringBuffer strbuf = new StringBuffer();
/* 219 */     strbuf.append(getBeginPosition()).append("-").append(getEndPosition());
/* 220 */     strbuf.append(" : ").append(this.lexemeText).append(" : \t");
/* 221 */     switch (this.lexemeType) {
/*     */     case 0:
/* 223 */       strbuf.append("CJK_NORMAL");
/* 224 */       break;
/*     */     case 2:
/* 226 */       strbuf.append("CJK_SUFFIX");
/* 227 */       break;
/*     */     case 1:
/* 229 */       strbuf.append("CJK_NAME");
/* 230 */       break;
/*     */     case 3:
/* 232 */       strbuf.append("UNKNOWN");
/* 233 */       break;
/*     */     case 10:
/* 235 */       strbuf.append("NUMEBER");
/* 236 */       break;
/*     */     case 11:
/* 238 */       strbuf.append("COUNT");
/* 239 */       break;
/*     */     case 20:
/* 241 */       strbuf.append("LETTER");
/*     */     }
/*     */ 
/* 245 */     return strbuf.toString();
/*     */   }
/*     */ 
/*     */   Lexeme getPrev() {
/* 249 */     return this.prev;
/*     */   }
/*     */ 
/*     */   void setPrev(Lexeme prev) {
/* 253 */     this.prev = prev;
/*     */   }
/*     */ 
/*     */   Lexeme getNext() {
/* 257 */     return this.next;
/*     */   }
/*     */ 
/*     */   void setNext(Lexeme next) {
/* 261 */     this.next = next;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.Lexeme
 * JD-Core Version:    0.6.2
 */