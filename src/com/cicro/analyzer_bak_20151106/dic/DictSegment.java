/*     */ package com.cicro.analyzer_bak_20151106.dic;
/*     */ 
/*     */ import com.cicro.analyzer.dic.*;

import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DictSegment
/*     */ {
/*  23 */   private static final Map<Character, Character> charMap = new HashMap(16, 0.95F);
/*     */   private static final int ARRAY_LENGTH_LIMIT = 3;
/*     */   private Character nodeChar;
/*     */   private Map<Character, DictSegment> childrenMap;
/*     */   private DictSegment[] childrenArray;
/*  39 */   private int storeSize = 0;
/*     */ 
/*  42 */   private int nodeState = 0;
/*     */ 
/*     */   public DictSegment(Character nodeChar) {
/*  45 */     if (nodeChar == null) {
/*  46 */       throw new IllegalArgumentException("参数为空异常，字符不能为空");
/*     */     }
/*  48 */     this.nodeChar = nodeChar;
/*     */   }
/*     */ 
/*     */   public Character getNodeChar() {
/*  52 */     return this.nodeChar;
/*     */   }
/*     */ 
/*     */   public boolean hasNextNode()
/*     */   {
/*  59 */     return this.storeSize > 0;
/*     */   }
/*     */ 
/*     */   public com.cicro.analyzer.dic.Hit match(char[] charArray)
/*     */   {
/*  68 */     return match(charArray, 0, charArray.length, null);
/*     */   }
/*     */ 
/*     */   public com.cicro.analyzer.dic.Hit match(char[] charArray, int begin, int length)
/*     */   {
/*  79 */     return match(charArray, begin, length, null);
/*     */   }
/*     */ 
/*     */   public com.cicro.analyzer.dic.Hit match(char[] charArray, int begin, int length, com.cicro.analyzer.dic.Hit searchHit)
/*     */   {
/*  92 */     if (searchHit == null)
/*     */     {
/*  94 */       searchHit = new com.cicro.analyzer.dic.Hit();
/*     */ 
/*  96 */       searchHit.setBegin(begin);
/*     */     }
/*     */     else {
/*  99 */       searchHit.setUnmatch();
/*     */     }
/*     */ 
/* 102 */     searchHit.setEnd(begin);
/*     */ 
/* 104 */     Character keyChar = new Character(charArray[begin]);
/* 105 */     DictSegment ds = null;
/*     */ 
/* 108 */     DictSegment[] segmentArray = this.childrenArray;
/* 109 */     Map segmentMap = this.childrenMap;
/*     */ 
/* 112 */     if (segmentArray != null)
/*     */     {
/* 114 */       for (DictSegment seg : segmentArray)
/* 115 */         if ((seg != null) && (seg.nodeChar.equals(keyChar)))
/*     */         {
/* 117 */           ds = seg;
/*     */         }
/*     */     }
/* 120 */     else if (segmentMap != null)
/*     */     {
/* 122 */       ds = (DictSegment)segmentMap.get(keyChar);
/*     */     }
/*     */ 
/* 126 */     if (ds != null) {
/* 127 */       if (length > 1)
/*     */       {
/* 129 */         return ds.match(charArray, begin + 1, length - 1, searchHit);
/* 130 */       }if (length == 1)
/*     */       {
/* 133 */         if (ds.nodeState == 1)
/*     */         {
/* 135 */           searchHit.setMatch();
/*     */         }
/* 137 */         if (ds.hasNextNode())
/*     */         {
/* 139 */           searchHit.setPrefix();
/*     */ 
/* 141 */           searchHit.setMatchedDictSegment(ds);
/*     */         }
/* 143 */         return searchHit;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 148 */     return searchHit;
/*     */   }
/*     */ 
/*     */   public void fillSegment(char[] charArray)
/*     */   {
/* 156 */     fillSegment(charArray, 0, charArray.length);
/*     */   }
/*     */ 
/*     */   public synchronized void fillSegment(char[] charArray, int begin, int length)
/*     */   {
/* 167 */     Character beginChar = new Character(charArray[begin]);
/* 168 */     Character keyChar = (Character)charMap.get(beginChar);
/*     */ 
/* 170 */     if (keyChar == null) {
/* 171 */       charMap.put(beginChar, beginChar);
/* 172 */       keyChar = beginChar;
/*     */     }
/*     */ 
/* 176 */     DictSegment ds = lookforSegment(keyChar);
/*     */ 
/* 178 */     if (length > 1)
/*     */     {
/* 180 */       ds.fillSegment(charArray, begin + 1, length - 1);
/* 181 */     } else if (length == 1)
/*     */     {
/* 183 */       ds.nodeState = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private DictSegment lookforSegment(Character keyChar)
/*     */   {
/* 196 */     DictSegment ds = null;
/*     */ 
/* 198 */     if (this.storeSize <= 3)
/*     */     {
/* 200 */       DictSegment[] segmentArray = getChildrenArray();
/*     */ 
/* 202 */       for (DictSegment segment : segmentArray) {
/* 203 */         if ((segment != null) && (segment.nodeChar.equals(keyChar)))
/*     */         {
/* 205 */           ds = segment;
/* 206 */           break;
/*     */         }
/*     */       }
/*     */ 
/* 210 */       if (ds == null)
/*     */       {
/* 212 */         ds = new DictSegment(keyChar);
/* 213 */         if (this.storeSize < 3)
/*     */         {
/* 215 */           segmentArray[this.storeSize] = ds;
/*     */ 
/* 217 */           this.storeSize += 1;
/*     */         }
/*     */         else
/*     */         {
/* 221 */           Map segmentMap = getChildrenMap();
/*     */ 
/* 223 */           migrate(segmentArray, segmentMap);
/*     */ 
/* 225 */           segmentMap.put(keyChar, ds);
/*     */ 
/* 227 */           this.storeSize += 1;
/*     */ 
/* 229 */           this.childrenArray = null;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 236 */       Map segmentMap = getChildrenMap();
/*     */ 
/* 238 */       ds = (DictSegment)segmentMap.get(keyChar);
/* 239 */       if (ds == null)
/*     */       {
/* 241 */         ds = new DictSegment(keyChar);
/* 242 */         segmentMap.put(keyChar, ds);
/*     */ 
/* 244 */         this.storeSize += 1;
/*     */       }
/*     */     }
/*     */ 
/* 248 */     return ds;
/*     */   }
/*     */ 
/*     */   private DictSegment[] getChildrenArray()
/*     */   {
/* 257 */     if (this.childrenArray == null) {
/* 258 */       synchronized (this) {
/* 259 */         if (this.childrenArray == null) {
/* 260 */           this.childrenArray = new DictSegment[3];
/*     */         }
/*     */       }
/*     */     }
/* 264 */     return this.childrenArray;
/*     */   }
/*     */ 
/*     */   private Map<Character, DictSegment> getChildrenMap()
/*     */   {
/* 272 */     if (this.childrenMap == null) {
/* 273 */       synchronized (this) {
/* 274 */         if (this.childrenMap == null) {
/* 275 */           this.childrenMap = new HashMap(6, 0.8F);
/*     */         }
/*     */       }
/*     */     }
/* 279 */     return this.childrenMap;
/*     */   }
/*     */ 
/*     */   private void migrate(DictSegment[] segmentArray, Map<Character, DictSegment> segmentMap)
/*     */   {
/* 287 */     for (DictSegment segment : segmentArray)
/* 288 */       if (segment != null)
/* 289 */         segmentMap.put(segment.nodeChar, segment);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.dic.DictSegment
 * JD-Core Version:    0.6.2
 */