/*     */ package com.cicro.analyzer_bak_20151106.lucene;
/*     */ 
/*     */ import com.cicro.analyzer.IKSegmentation;
/*     */ import com.cicro.analyzer.Lexeme;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.search.BooleanClause.Occur;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ 
/*     */ public final class IKQueryParser
/*     */ {
/*  38 */   private static ThreadLocal<Map<String, IKQueryParser.TokenBranch>> keywordCacheThreadLocal = new ThreadLocal();
/*     */ 
/*  42 */   private static boolean isMaxWordLength = false;
/*     */ 
/*     */   public static void setMaxWordLength(boolean isMaxWordLength)
/*     */   {
/*  50 */     isMaxWordLength = isMaxWordLength;
/*     */   }
/*     */ 
/*     */   private static Query optimizeQueries(List<Query> queries)
/*     */   {
/*  61 */     if (queries.size() == 0)
/*  62 */       return null;
/*  63 */     if (queries.size() == 1) {
/*  64 */       return (Query)queries.get(0);
/*     */     }
/*  66 */     BooleanQuery mustQueries = new BooleanQuery();
/*  67 */     for (Query q : queries) {
/*  68 */       mustQueries.add(q, BooleanClause.Occur.MUST);
/*     */     }
/*  70 */     return mustQueries;
/*     */   }
/*     */ 
/*     */   private static Map<String, IKQueryParser.TokenBranch> getTheadLocalCache()
/*     */   {
/*  79 */     Map keywordCache = (Map)keywordCacheThreadLocal.get();
/*  80 */     if (keywordCache == null) {
/*  81 */       keywordCache = new HashMap(4);
/*  82 */       keywordCacheThreadLocal.set(keywordCache);
/*     */     }
/*  84 */     return keywordCache;
/*     */   }
/*     */ 
/*     */   private static IKQueryParser.TokenBranch getCachedTokenBranch(String query)
/*     */   {
/*  93 */     Map keywordCache = getTheadLocalCache();
/*  94 */     return (IKQueryParser.TokenBranch)keywordCache.get(query);
/*     */   }
/*     */ 
/*     */   private static void cachedTokenBranch(String query, IKQueryParser.TokenBranch tb)
/*     */   {
/* 103 */     Map keywordCache = getTheadLocalCache();
/* 104 */     keywordCache.put(query, tb);
/*     */   }
/*     */ 
/*     */   private static Query _parse(String field, String query)
/*     */     throws IOException
/*     */   {
/* 116 */     if (field == null) {
/* 117 */       throw new IllegalArgumentException("parameter \"field\" is null");
/*     */     }
/*     */ 
/* 120 */     if ((query == null) || ("".equals(query.trim()))) {
/* 121 */       return new TermQuery(new Term(field));
/*     */     }
/*     */ 
/* 125 */     IKQueryParser.TokenBranch root = getCachedTokenBranch(query);
/* 126 */     if (root != null) {
/* 127 */       return optimizeQueries(root.toQueries(field));
/*     */     }
/*     */ 
/* 130 */     root = new IKQueryParser.TokenBranch(null);
/*     */ 
/* 132 */     StringReader input = new StringReader(query.trim());
/* 133 */     IKSegmentation ikSeg = new IKSegmentation(input, isMaxWordLength);
/* 134 */     for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next())
/*     */     {
/* 136 */       root.accept(lexeme);
/*     */     }
/*     */ 
/* 139 */     cachedTokenBranch(query, root);
/* 140 */     return optimizeQueries(root.toQueries(field));
/*     */   }
/*     */ 
/*     */   public static Query parse(String ikQueryExp)
/*     */   {
/* 150 */     IKQueryParser.ExpressionParser ikExpParser = new IKQueryParser.ExpressionParser();
/* 151 */     return ikExpParser.parserExp(ikQueryExp);
/*     */   }
/*     */ 
/*     */   public static Query parse(String field, String query)
/*     */     throws IOException
/*     */   {
/* 162 */     if (field == null) {
/* 163 */       throw new IllegalArgumentException("parameter \"field\" is null");
/*     */     }
/* 165 */     String[] qParts = query.split("\\s");
/* 166 */     if (qParts.length > 1) {
/* 167 */       BooleanQuery resultQuery = new BooleanQuery();
/* 168 */       for (String q : qParts)
/*     */       {
/* 170 */         if (!"".equals(q))
/*     */         {
/* 173 */           Query partQuery = _parse(field, q);
/* 174 */           if ((partQuery != null) && (
/* 175 */             (!(partQuery instanceof BooleanQuery)) || (((BooleanQuery)partQuery).getClauses().length > 0)))
/* 176 */             resultQuery.add(partQuery, BooleanClause.Occur.SHOULD);
/*     */         }
/*     */       }
/* 179 */       return resultQuery;
/*     */     }
/* 181 */     return _parse(field, query);
/*     */   }
/*     */ 
/*     */   public static Query parseMultiField(String[] fields, String query)
/*     */     throws IOException
/*     */   {
/* 193 */     if (fields == null) {
/* 194 */       throw new IllegalArgumentException("parameter \"fields\" is null");
/*     */     }
/* 196 */     BooleanQuery resultQuery = new BooleanQuery();
/* 197 */     String[] arrayOfString = fields; int j = fields.length; for (int i = 0; i < j; i++) { String field = arrayOfString[i];
/* 198 */       if (field != null) {
/* 199 */         Query partQuery = parse(field, query);
/* 200 */         if ((partQuery != null) && (
/* 201 */           (!(partQuery instanceof BooleanQuery)) || (((BooleanQuery)partQuery).getClauses().length > 0))) {
/* 202 */           resultQuery.add(partQuery, BooleanClause.Occur.SHOULD);
/*     */         }
/*     */       }
/*     */     }
/* 206 */     return resultQuery;
/*     */   }
/*     */ 
/*     */   public static Query parseMultiField(String[] fields, String query, BooleanClause.Occur[] flags)
/*     */     throws IOException
/*     */   {
/* 218 */     if (fields == null) {
/* 219 */       throw new IllegalArgumentException("parameter \"fields\" is null");
/*     */     }
/* 221 */     if (flags == null) {
/* 222 */       throw new IllegalArgumentException("parameter \"flags\" is null");
/*     */     }
/*     */ 
/* 225 */     if (flags.length != fields.length) {
/* 226 */       throw new IllegalArgumentException("flags.length != fields.length");
/*     */     }
/*     */ 
/* 229 */     BooleanQuery resultQuery = new BooleanQuery();
/* 230 */     for (int i = 0; i < fields.length; i++) {
/* 231 */       if (fields[i] != null) {
/* 232 */         Query partQuery = parse(fields[i], query);
/* 233 */         if ((partQuery != null) && (
/* 234 */           (!(partQuery instanceof BooleanQuery)) || (((BooleanQuery)partQuery).getClauses().length > 0))) {
/* 235 */           resultQuery.add(partQuery, flags[i]);
/*     */         }
/*     */       }
/*     */     }
/* 239 */     return resultQuery;
/*     */   }
/*     */ 
/*     */   public static Query parseMultiField(String[] fields, String[] queries)
/*     */     throws IOException
/*     */   {
/* 250 */     if (fields == null) {
/* 251 */       throw new IllegalArgumentException("parameter \"fields\" is null");
/*     */     }
/* 253 */     if (queries == null) {
/* 254 */       throw new IllegalArgumentException("parameter \"queries\" is null");
/*     */     }
/* 256 */     if (queries.length != fields.length) {
/* 257 */       throw new IllegalArgumentException("queries.length != fields.length");
/*     */     }
/* 259 */     BooleanQuery resultQuery = new BooleanQuery();
/* 260 */     for (int i = 0; i < fields.length; i++) {
/* 261 */       if (fields[i] != null) {
/* 262 */         Query partQuery = parse(fields[i], queries[i]);
/* 263 */         if ((partQuery != null) && (
/* 264 */           (!(partQuery instanceof BooleanQuery)) || (((BooleanQuery)partQuery).getClauses().length > 0))) {
/* 265 */           resultQuery.add(partQuery, BooleanClause.Occur.SHOULD);
/*     */         }
/*     */       }
/*     */     }
/* 269 */     return resultQuery;
/*     */   }
/*     */ 
/*     */   public static Query parseMultiField(String[] fields, String[] queries, BooleanClause.Occur[] flags)
/*     */     throws IOException
/*     */   {
/* 281 */     if (fields == null) {
/* 282 */       throw new IllegalArgumentException("parameter \"fields\" is null");
/*     */     }
/* 284 */     if (queries == null) {
/* 285 */       throw new IllegalArgumentException("parameter \"queries\" is null");
/*     */     }
/* 287 */     if (flags == null) {
/* 288 */       throw new IllegalArgumentException("parameter \"flags\" is null");
/*     */     }
/*     */ 
/* 291 */     if ((queries.length != fields.length) || (queries.length != flags.length)) {
/* 292 */       throw new IllegalArgumentException("queries, fields, and flags array have have different length");
/*     */     }
/*     */ 
/* 295 */     BooleanQuery resultQuery = new BooleanQuery();
/* 296 */     for (int i = 0; i < fields.length; i++) {
/* 297 */       if (fields[i] != null) {
/* 298 */         Query partQuery = parse(fields[i], queries[i]);
/* 299 */         if ((partQuery != null) && (
/* 300 */           (!(partQuery instanceof BooleanQuery)) || (((BooleanQuery)partQuery).getClauses().length > 0))) {
/* 301 */           resultQuery.add(partQuery, flags[i]);
/*     */         }
/*     */       }
/*     */     }
/* 305 */     return resultQuery;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 981 */     String ikQueryExp = "(id='1231231' && title:'MYNAMEmonkey') || (content:'你好吗'  || ulr='www.ik.com') - name:'林良益'";
/* 982 */     Query result = parse(ikQueryExp);
/* 983 */     System.out.println(result);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKQueryParser
 * JD-Core Version:    0.6.2
 */