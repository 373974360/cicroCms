/*     */ package com.cicro.analyzer_bak_20151106;
/*     */ 
/*     */ import com.cicro.analyzer.*;
import com.cicro.analyzer.Lexeme;
import com.cicro.analyzer.SorterMap;
import com.cicro.analyzer.lucene.IKAnalyzer;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.lucene.analysis.TokenStream;
/*     */ import org.apache.lucene.analysis.tokenattributes.TermAttribute;
/*     */ 
/*     */ public class AnalyzerManager
/*     */ {
/*     */   public static List getWordsByTile(String title)
/*     */   {
/*  24 */     List list = new ArrayList();
/*     */     try {
/*  26 */       IKAnalyzer ka = new IKAnalyzer(true);
/*  27 */       Reader r = new StringReader(title);
/*  28 */       TokenStream ts = ka.tokenStream("title", r);
/*  29 */       ts.addAttribute(TermAttribute.class);
/*  30 */       System.out.println("------------------------");
/*  31 */       while (ts.incrementToken()) {
/*  32 */         TermAttribute ta = (TermAttribute)ts.getAttribute(TermAttribute.class);
/*  33 */         String str = ta.term();
/*  34 */         if (!list.contains(str)) {
/*  35 */           System.out.println(ta.term());
/*  36 */           list.add(str);
/*     */         }
/*     */       }
/*  39 */       System.out.println("------------------------" + list.size());
/*  40 */       return list;
/*     */     } catch (Exception e) {
/*  42 */       e.printStackTrace();
/*  43 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<Map> findMaxOfenWord(String text, int top)
/*     */   {
/*  53 */     Map words = new HashMap();
/*  54 */     com.cicro.analyzer.IKSegmentation seg = new com.cicro.analyzer.IKSegmentation(new StringReader(text), true);
/*     */     try {
/*  56 */       Lexeme l = null;
/*  57 */       while ((l = seg.next()) != null)
/*  58 */         if (words.containsKey(l.getLexemeText()))
/*  59 */           words.put(l.getLexemeText(), Integer.valueOf(((Integer)words.get(l.getLexemeText())).intValue() + 1));
/*     */         else
/*  61 */           words.put(l.getLexemeText(), Integer.valueOf(1));
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  65 */       e.printStackTrace();
/*     */     }
/*  67 */     int max = 0;
/*  68 */     String maxKey = null;
/*  69 */     List ofenwords = new ArrayList();
/*  70 */     for (int i = 0; (i < top) && (i < words.size()); i++) {
/*  71 */       for (String key : words.keySet()) {
/*  72 */         if (((Integer)words.get(key)).intValue() > max) {
/*  73 */           max = ((Integer)words.get(key)).intValue();
/*  74 */           maxKey = key;
/*     */         }
/*     */       }
/*     */ 
/*  78 */       Map map = new HashMap();
/*  79 */       map.put(maxKey, words.get(maxKey));
/*  80 */       ofenwords.add(map);
/*     */ 
/*  82 */       max = 0;
/*  83 */       words.put(maxKey, Integer.valueOf(-1));
/*     */     }
/*     */ 
/*  86 */     return ofenwords;
/*     */   }
/*     */ 
/*     */   public static String getWordsByTitleAndText(String title, String content) {
/*  90 */     StringBuffer sb = new StringBuffer("");
/*     */     try {
/*  92 */       content = HtmlRegexpUtil.filterHtml(content);
/*  93 */       Map mapWords = new HashMap();
/*  94 */       List list = findMaxOfenWord(content, 10);
/*  95 */       for (Map map : list) {
/*  96 */         Iterator it = map.keySet().iterator();
/*  97 */         if (it.hasNext()) {
/*  98 */           String key = (String)it.next();
/*  99 */           int value = ((Integer)map.get(key)).intValue();
/* 100 */           System.out.println(key + ":" + value);
/* 101 */           mapWords.put(key, Integer.valueOf(value));
/*     */         }
/*     */       }
/*     */ 
/* 105 */       Map resultMap = new HashMap();
/* 106 */       List listTitle = getWordsByTile(title);
/* 107 */       for (int i = 0; i < listTitle.size(); i++) {
/* 108 */         String t = (String)listTitle.get(i);
/* 109 */         if (mapWords.containsKey(t)) {
/* 110 */           resultMap.put(t, (Integer)mapWords.get(t));
/*     */         }
/*     */       }
/*     */ 
/* 114 */       List info = SorterMap.sortMapValue(resultMap);
/* 115 */       int size = info.size() > 3 ? 3 : info.size();
/* 116 */       for (int j = 0; j < size; j++) {
/* 117 */         System.out.println((String)((Entry)info.get(j)).getKey() + "------->" + ((Entry)info.get(j)).getValue());
/* 118 */         sb.append((String)((Entry)info.get(j)).getKey() + " ");
/*     */       }
/*     */ 
/* 121 */       return sb.toString();
/*     */     } catch (Exception e) {
/* 123 */       e.printStackTrace();
/* 124 */     }return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getWordsByTitle(String s)
/*     */   {
/* 130 */     StringBuffer sb = new StringBuffer("");
/*     */     try
/*     */     {
/* 133 */       List list = new ArrayList();
/* 134 */       IKAnalyzer ka = new IKAnalyzer(true);
/* 135 */       Reader r = new StringReader(s);
/* 136 */       TokenStream ts = ka.tokenStream("title", r);
/* 137 */       ts.addAttribute(TermAttribute.class);
/* 138 */       while (ts.incrementToken()) {
/* 139 */         TermAttribute ta = (TermAttribute)ts.getAttribute(TermAttribute.class);
/* 140 */         String str = ta.term();
/* 141 */         if (!list.contains(str)) {
/* 142 */           System.out.println(ta.term());
/* 143 */           list.add(str);
/* 144 */           if (str.length() >= 2) {
/* 145 */             sb.append(str + " ");
/*     */           }
/*     */         }
/*     */       }
/* 149 */       return sb.toString();
/*     */     } catch (Exception e) {
/* 151 */       e.printStackTrace();
/* 152 */     }return sb.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws IOException
/*     */   {
/* 162 */     String title = "统计局：2012年2月份CPI同比涨3.2% PPI同比持平  ";
/*     */ 
/* 168 */     String streing = getWordsByTitle(title);
/* 169 */     System.out.println(streing);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.AnalyzerManager
 * JD-Core Version:    0.6.2
 */