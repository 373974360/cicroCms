/*     */ package com.cicro.analyzer_bak_20151106.dic;
/*     */ 
/*     */ import com.cicro.analyzer.AnalyzerManager;
/*     */ import com.cicro.analyzer.cfg.Configuration;
import com.cicro.analyzer.dic.*;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Dictionary
/*     */ {
/*     */   public static final String PATH_DIC_MAIN = "/com/cicro/analyzer/dic/main.dic";
/*     */   public static final String PATH_DIC_SURNAME = "/com/cicro/analyzer/dic/surname.dic";
/*     */   public static final String PATH_DIC_QUANTIFIER = "/com/cicro/analyzer/dic/quantifier.dic";
/*     */   public static final String PATH_DIC_SUFFIX = "/com/cicro/analyzer/dic/suffix.dic";
/*     */   public static final String PATH_DIC_PREP = "/com/cicro/analyzer/dic/preposition.dic";
/*     */   public static final String PATH_DIC_STOP = "/com/cicro/analyzer/dic/stopword.dic";
/*     */   public static final String PATH_DIC_FILES = "/com/cicro/analyzer/dic/files/";
/*  46 */   private static final Dictionary singleton = new Dictionary();
/*     */   private DictSegment _MainDict;
/*     */   private DictSegment _SurnameDict;
/*     */   private DictSegment _QuantifierDict;
/*     */   private DictSegment _SuffixDict;
/*     */   private DictSegment _PrepDict;
/*     */   private DictSegment _StopWords;
/*     */ 
/*     */   private Dictionary()
/*     */   {
/*  76 */     loadMainDict();
/*  77 */     loadSurnameDict();
/*  78 */     loadQuantifierDict();
/*  79 */     loadSuffixDict();
/*  80 */     loadPrepDict();
/*  81 */     loadStopWordDict();
/*     */ 
/*  84 */     loadMainFiles();
/*     */   }
/*     */ 
/*     */   private void loadMainFiles()
/*     */   {
/*     */     try
/*     */     {
/*  91 */       String fileRoot = "";
/*  92 */       System.out.println("/com/cicro/analyzer/dic/files/");
/*  93 */       fileRoot = new AnalyzerManager()
/*  94 */         .getClass()
/*  95 */         .getResource("/com/cicro/analyzer/dic/files/")
/*  96 */         .getPath();
/*  97 */       System.out.println(fileRoot);
/*     */ 
/*  99 */       File fileR = new File(fileRoot);
/* 100 */       if (fileR.exists()) {
/* 101 */         File[] file = fileR.listFiles();
/* 102 */         for (File f : file) {
/* 103 */           String strFile = f.getCanonicalFile().toString();
/* 104 */           System.out.println(strFile);
/* 105 */           InputStream is = new FileInputStream(strFile);
/* 106 */           if (is == null)
/* 107 */             throw new RuntimeException("Main Dictionary not found!!!");
/*     */           try
/*     */           {
/* 110 */             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 111 */             String theWord = null;
/* 112 */             System.out.println("in---" + strFile);
/*     */             do {
/* 114 */               theWord = br.readLine();
/* 115 */               if ((theWord != null) && (!"".equals(theWord.trim())))
/* 116 */                 this._MainDict.fillSegment(theWord.trim().toCharArray());
/*     */             }
/* 118 */             while (theWord != null);
/*     */           }
/*     */           catch (IOException ioe) {
/* 121 */             System.err.println("Main Dictionary loading exception.");
/* 122 */             ioe.printStackTrace();
/*     */             try
/*     */             {
/* 126 */               if (is != null) {
/* 127 */                 is.close();
/* 128 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 131 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */           finally
/*     */           {
/*     */             try
/*     */             {
/* 126 */               if (is != null) {
/* 127 */                 is.close();
/* 128 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 131 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 137 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadMainDict()
/*     */   {
/* 146 */     this._MainDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 148 */     InputStream is = Dictionary.class.getResourceAsStream("/com/cicro/analyzer/dic/main.dic");
/* 149 */     if (is == null) {
/* 150 */       throw new RuntimeException("Main Dictionary not found!!!");
/*     */     }
/*     */     try
/*     */     {
/* 154 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 155 */       String theWord = null;
/*     */       do {
/* 157 */         theWord = br.readLine();
/* 158 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 159 */           this._MainDict.fillSegment(theWord.trim().toCharArray());
/*     */       }
/* 161 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 164 */       System.err.println("Main Dictionary loading exception.");
/* 165 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 169 */         if (is != null) {
/* 170 */           is.close();
/* 171 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 174 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 169 */         if (is != null) {
/* 170 */           is.close();
/* 171 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 174 */         e.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 179 */     List extDictFiles = Configuration.getExtDictionarys();
/* 180 */     if (extDictFiles != null)
/* 181 */       for (String extDictName : extDictFiles)
/*     */       {
/* 183 */         is = Dictionary.class.getResourceAsStream(extDictName);
/*     */ 
/* 185 */         if (is != null)
/*     */         {
/*     */           try
/*     */           {
/* 189 */             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 190 */             String theWord = null;
/*     */             do {
/* 192 */               theWord = br.readLine();
/* 193 */               if ((theWord != null) && (!"".equals(theWord.trim())))
/*     */               {
/* 196 */                 this._MainDict.fillSegment(theWord.trim().toCharArray());
/*     */               }
/*     */             }
/* 198 */             while (theWord != null);
/*     */           }
/*     */           catch (IOException ioe) {
/* 201 */             System.err.println("Extension Dictionary loading exception.");
/* 202 */             ioe.printStackTrace();
/*     */             try
/*     */             {
/* 206 */               if (is != null) {
/* 207 */                 is.close();
/* 208 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 211 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */           finally
/*     */           {
/*     */             try
/*     */             {
/* 206 */               if (is != null) {
/* 207 */                 is.close();
/* 208 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 211 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   private void loadSurnameDict()
/*     */   {
/* 223 */     this._SurnameDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 225 */     InputStream is = Dictionary.class.getResourceAsStream("/com/cicro/analyzer/dic/surname.dic");
/* 226 */     if (is == null)
/* 227 */       throw new RuntimeException("Surname Dictionary not found!!!");
/*     */     try
/*     */     {
/* 230 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 231 */       String theWord = null;
/*     */       do {
/* 233 */         theWord = br.readLine();
/* 234 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 235 */           this._SurnameDict.fillSegment(theWord.trim().toCharArray());
/*     */       }
/* 237 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 240 */       System.err.println("Surname Dictionary loading exception.");
/* 241 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 245 */         if (is != null) {
/* 246 */           is.close();
/* 247 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 250 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 245 */         if (is != null) {
/* 246 */           is.close();
/* 247 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 250 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadQuantifierDict()
/*     */   {
/* 260 */     this._QuantifierDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 262 */     InputStream is = Dictionary.class.getResourceAsStream("/com/cicro/analyzer/dic/quantifier.dic");
/* 263 */     if (is == null)
/* 264 */       throw new RuntimeException("Quantifier Dictionary not found!!!");
/*     */     try
/*     */     {
/* 267 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 268 */       String theWord = null;
/*     */       do {
/* 270 */         theWord = br.readLine();
/* 271 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 272 */           this._QuantifierDict.fillSegment(theWord.trim().toCharArray());
/*     */       }
/* 274 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 277 */       System.err.println("Quantifier Dictionary loading exception.");
/* 278 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 282 */         if (is != null) {
/* 283 */           is.close();
/* 284 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 287 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 282 */         if (is != null) {
/* 283 */           is.close();
/* 284 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 287 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadSuffixDict()
/*     */   {
/* 297 */     this._SuffixDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 299 */     InputStream is = Dictionary.class.getResourceAsStream("/com/cicro/analyzer/dic/suffix.dic");
/* 300 */     if (is == null)
/* 301 */       throw new RuntimeException("Suffix Dictionary not found!!!");
/*     */     try
/*     */     {
/* 304 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 305 */       String theWord = null;
/*     */       do {
/* 307 */         theWord = br.readLine();
/* 308 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 309 */           this._SuffixDict.fillSegment(theWord.trim().toCharArray());
/*     */       }
/* 311 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 314 */       System.err.println("Suffix Dictionary loading exception.");
/* 315 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 319 */         if (is != null) {
/* 320 */           is.close();
/* 321 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 324 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 319 */         if (is != null) {
/* 320 */           is.close();
/* 321 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 324 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadPrepDict()
/*     */   {
/* 334 */     this._PrepDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 336 */     InputStream is = Dictionary.class.getResourceAsStream("/com/cicro/analyzer/dic/preposition.dic");
/* 337 */     if (is == null)
/* 338 */       throw new RuntimeException("Preposition Dictionary not found!!!");
/*     */     try
/*     */     {
/* 341 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 342 */       String theWord = null;
/*     */       do {
/* 344 */         theWord = br.readLine();
/* 345 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/*     */         {
/* 347 */           this._PrepDict.fillSegment(theWord.trim().toCharArray());
/*     */         }
/*     */       }
/* 349 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 352 */       System.err.println("Preposition Dictionary loading exception.");
/* 353 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 357 */         if (is != null) {
/* 358 */           is.close();
/* 359 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 362 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 357 */         if (is != null) {
/* 358 */           is.close();
/* 359 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 362 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadStopWordDict()
/*     */   {
/* 372 */     this._StopWords = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 374 */     InputStream is = Dictionary.class.getResourceAsStream("/com/cicro/analyzer/dic/stopword.dic");
/* 375 */     if (is == null)
/* 376 */       throw new RuntimeException("Stopword Dictionary not found!!!");
/*     */     try
/*     */     {
/* 379 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 380 */       String theWord = null;
/*     */       do {
/* 382 */         theWord = br.readLine();
/* 383 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 384 */           this._StopWords.fillSegment(theWord.trim().toCharArray());
/*     */       }
/* 386 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 389 */       System.err.println("Stopword Dictionary loading exception.");
/* 390 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 394 */         if (is != null) {
/* 395 */           is.close();
/* 396 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 399 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 394 */         if (is != null) {
/* 395 */           is.close();
/* 396 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 399 */         e.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 404 */     List extStopWordDictFiles = Configuration.getExtStopWordDictionarys();
/* 405 */     if (extStopWordDictFiles != null)
/* 406 */       for (String extStopWordDictName : extStopWordDictFiles)
/*     */       {
/* 408 */         is = Dictionary.class.getResourceAsStream(extStopWordDictName);
/*     */ 
/* 410 */         if (is != null)
/*     */         {
/*     */           try
/*     */           {
/* 414 */             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 415 */             String theWord = null;
/*     */             do {
/* 417 */               theWord = br.readLine();
/* 418 */               if ((theWord != null) && (!"".equals(theWord.trim())))
/*     */               {
/* 421 */                 this._StopWords.fillSegment(theWord.trim().toCharArray());
/*     */               }
/*     */             }
/* 423 */             while (theWord != null);
/*     */           }
/*     */           catch (IOException ioe) {
/* 426 */             System.err.println("Extension Stop word Dictionary loading exception.");
/* 427 */             ioe.printStackTrace();
/*     */             try
/*     */             {
/* 431 */               if (is != null) {
/* 432 */                 is.close();
/* 433 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 436 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */           finally
/*     */           {
/*     */             try
/*     */             {
/* 431 */               if (is != null) {
/* 432 */                 is.close();
/* 433 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 436 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public static Dictionary getInstance()
/*     */   {
/* 454 */     return singleton;
/*     */   }
/*     */ 
/*     */   public static void loadExtendWords(Collection<String> extWords)
/*     */   {
/* 462 */     if (extWords != null)
/* 463 */       for (String extWord : extWords)
/* 464 */         if (extWord != null)
/*     */         {
/* 466 */           singleton._MainDict.fillSegment(extWord.trim().toCharArray());
/*     */         }
/*     */   }
/*     */ 
/*     */   public static void loadExtendStopWords(Collection<String> extStopWords)
/*     */   {
/* 477 */     if (extStopWords != null)
/* 478 */       for (String extStopWord : extStopWords)
/* 479 */         if (extStopWord != null)
/*     */         {
/* 481 */           singleton._StopWords.fillSegment(extStopWord.trim().toCharArray());
/*     */         }
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchInMainDict(char[] charArray)
/*     */   {
/* 493 */     return singleton._MainDict.match(charArray);
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchInMainDict(char[] charArray, int begin, int length)
/*     */   {
/* 504 */     return singleton._MainDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchWithHit(char[] charArray, int currentIndex, com.cicro.analyzer.dic.Hit matchedHit)
/*     */   {
/* 516 */     DictSegment ds = matchedHit.getMatchedDictSegment();
/* 517 */     return ds.match(charArray, currentIndex, 1, matchedHit);
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchInSurnameDict(char[] charArray, int begin, int length)
/*     */   {
/* 528 */     return singleton._SurnameDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchInQuantifierDict(char[] charArray, int begin, int length)
/*     */   {
/* 559 */     return singleton._QuantifierDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchInSuffixDict(char[] charArray, int begin, int length)
/*     */   {
/* 570 */     return singleton._SuffixDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static com.cicro.analyzer.dic.Hit matchInPrepDict(char[] charArray, int begin, int length)
/*     */   {
/* 602 */     return singleton._PrepDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static boolean isStopWord(char[] charArray, int begin, int length)
/*     */   {
/* 613 */     return singleton._StopWords.match(charArray, begin, length).isMatch();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.dic.Dictionary
 * JD-Core Version:    0.6.2
 */