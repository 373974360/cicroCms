/*     */ package com.cicro.wcm.services.search_bak_20151106.search;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.search.PageControl;
/*     */ import com.cicro.wcm.bean.search.ResultBean;
/*     */ import com.cicro.wcm.bean.search.SearchResult;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.search.SearchForInterface;
/*     */ import com.cicro.wcm.services.search.search.SearchLuceneManager;
import com.cicro.wcm.services.search.search.util.Arith;
/*     */ import com.cicro.wcm.services.search.search.util.SearchUtil;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.lucene.analysis.TokenStream;
/*     */ import org.apache.lucene.analysis.standard.StandardAnalyzer;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.queryParser.MultiFieldQueryParser;
/*     */ import org.apache.lucene.queryParser.QueryParser;
/*     */ import org.apache.lucene.search.BooleanClause.Occur;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.IndexSearcher;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.QueryWrapperFilter;
/*     */ import org.apache.lucene.search.ScoreDoc;
/*     */ import org.apache.lucene.search.Sort;
/*     */ import org.apache.lucene.search.SortField;
/*     */ import org.apache.lucene.search.TopDocs;
/*     */ import org.apache.lucene.search.highlight.Highlighter;
/*     */ import org.apache.lucene.search.highlight.QueryScorer;
/*     */ import org.apache.lucene.search.highlight.SimpleFragmenter;
/*     */ import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
/*     */ import org.apache.lucene.util.Version;
/*     */ 
/*     */ public class SearchInfoManager
/*     */ {
/*     */   public static Query getQuery(String scope, String q)
/*     */   {
/*  53 */     Query query = null;
/*     */     try {
/*  55 */       List fieldsList = new ArrayList();
/*  56 */       List keysList = new ArrayList();
/*  57 */       List occurList = new ArrayList();
/*     */ 
/*  59 */       if (scope.equals("")) {
/*  60 */         fieldsList.add("title");
/*  61 */         fieldsList.add("content");
/*  62 */         keysList.add(q);
/*  63 */         keysList.add(q);
/*  64 */         occurList.add(BooleanClause.Occur.SHOULD);
/*  65 */         occurList.add(BooleanClause.Occur.SHOULD);
/*  66 */       } else if (scope.equals("title")) {
/*  67 */         fieldsList.add("title");
/*  68 */         keysList.add(q);
/*  69 */         occurList.add(BooleanClause.Occur.SHOULD);
/*     */       }
/*  71 */       else if (scope.equals("content")) {
/*  72 */         fieldsList.add("content");
/*  73 */         keysList.add(q);
/*  74 */         occurList.add(BooleanClause.Occur.SHOULD);
/*     */       }
/*  76 */       String[] fields = (String[])fieldsList.toArray(new String[fieldsList.size()]);
/*  77 */       String[] keys = (String[])keysList.toArray(new String[keysList.size()]);
/*  78 */       BooleanClause.Occur[] flags = (BooleanClause.Occur[])occurList.toArray(new BooleanClause.Occur[occurList.size()]);
/*     */ 
/*  84 */       QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_30, fields, new StandardAnalyzer(Version.LUCENE_30));
/*  85 */       query = parser.parse(q);
/*     */     }
/*     */     catch (Exception e) {
/*  88 */       e.printStackTrace();
/*     */     } finally {
/*  90 */       return query;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static SearchResult search(Map map)
/*     */   {
/* 112 */     SearchResult searchResult = new SearchResult();
/* 113 */     long timeS = System.currentTimeMillis();
/* 114 */     List listResult = new ArrayList();
/* 115 */     IndexSearcher indexSearcher = null;
/*     */     try {
/* 117 */       String q = (String)map.get("q");
/* 118 */       if (q == null) {
/* 119 */         return searchResult;
/*     */       }
/*     */ 
/* 122 */       String scope = (String)map.get("scope") == null ? "" : (String)map.get("scope");
/* 123 */       Query query = getQuery(scope, q);
/* 124 */       System.out.println("query===" + query.toString());
/*     */ 
/* 126 */       indexSearcher = SearchLuceneManager.getIndexSearcher();
/* 127 */       if (indexSearcher == null) {
/* 128 */         return searchResult;
/*     */       }
/*     */ 
/* 132 */       Sort sort = new Sort(new SortField[] { new SortField("publish_time", 3, true), new SortField("id", 4, true) });
/*     */ 
/* 135 */       String q2 = (String)map.get("q2") == null ? "" : (String)map.get("q2");
/* 136 */       BooleanQuery booleanQuery = new BooleanQuery();
/* 137 */       booleanQuery.add(query, BooleanClause.Occur.MUST);
/* 138 */       if (!q2.equals("")) {
/* 139 */         Query query2 = getQuery(scope, q2);
/* 140 */         booleanQuery.add(query2, BooleanClause.Occur.MUST);
/* 141 */         System.out.println("query2===" + query2.toString());
/*     */       }
/*     */ 
/* 146 */       String qn1 = (String)map.get("qn1") == null ? "" : (String)map.get("qn1");
/* 147 */       StringTokenizer st = new StringTokenizer(qn1, " ");
/* 148 */       while (st.hasMoreElements()) {
/* 149 */         String token = st.nextElement().toString();
/* 150 */         if (!token.equals("")) {
/* 151 */           Query query_qn1 = getQuery(scope, token);
/* 152 */           booleanQuery.add(query_qn1, BooleanClause.Occur.MUST);
/* 153 */           System.out.println("query_qn1_" + token + "===" + query_qn1.toString());
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 160 */       QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_30, new String[] { "" }, new StandardAnalyzer(Version.LUCENE_30));
/*     */ 
/* 162 */       String site_idB = (String)map.get("site_id") == null ? "" : (String)map.get("site_id");
/*     */ 
/* 164 */       String site_domain = (String)map.get("site_domain") == null ? "" : (String)map.get("site_domain");
/*     */ 
/* 167 */       int p = (String)map.get("p") == null ? 1 : Integer.valueOf((String)map.get("p")).intValue();
/* 168 */       int pz = (String)map.get("pz") == null ? 0 : Integer.valueOf((String)map.get("pz")).intValue();
/* 169 */       int length = (String)map.get("length") == null ? 100 : Integer.valueOf((String)map.get("length")).intValue();
/* 170 */       String color = (String)map.get("color");
/* 171 */       if (color == null) {
/* 172 */         color = "red";
/*     */       }
/* 174 */       map.remove("q");
/* 175 */       map.remove("q2");
/* 176 */       map.remove("p");
/* 177 */       map.remove("pz");
/* 178 */       map.remove("length");
/* 179 */       map.remove("color");
/* 180 */       map.remove("scope");
/* 181 */       map.remove("site_domain");
/* 182 */       map.remove("qn1");
/*     */ 
/* 185 */       String tempQuery = getAllQuery(map);
/* 186 */       QueryWrapperFilter filter = tempQuery.equals("") ? null : new QueryWrapperFilter(parser.parse(tempQuery.toString()));
/*     */ 
/* 189 */       PageControl pageControl = new PageControl();
/* 190 */       pageControl.setRowsPerPage(pz);
/* 191 */       pageControl.setCurPage(p);
/* 192 */       pageControl.countStart();
/* 193 */       int sn = pageControl.getStart();
/* 194 */       pz = pageControl.getRowsPerPage();
/*     */ 
/* 199 */       TopDocs hits = indexSearcher.search(booleanQuery, filter, Integer.valueOf(sn).intValue() + Integer.valueOf(pz).intValue(), sort);
/*     */ 
/* 201 */       System.out.println("找到了" + hits.totalHits + "个");
/*     */ 
/* 204 */       pageControl.setMaxRowCount(Long.valueOf(hits.totalHits));
/* 205 */       pageControl.countMaxPage();
/*     */ 
/* 209 */       Highlighter highlighter = null;
/* 210 */       SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
/* 211 */         "<font color='" + color + "'>", "</font>");
/* 212 */       highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(booleanQuery));
/*     */ 
/* 214 */       highlighter.setTextFragmenter(new SimpleFragmenter(length));
/*     */ 
/* 217 */       for (int j = sn; j < hits.scoreDocs.length; j++) {
/* 218 */         ScoreDoc sdoc = hits.scoreDocs[j];
/* 219 */         Document doc = indexSearcher.doc(sdoc.doc);
/*     */ 
/* 222 */         String content = doc.get("content");
/*     */ 
/* 224 */         String contentTemp = content;
/*     */ 
/* 226 */         TokenStream tokenStream = new StandardAnalyzer(Version.LUCENE_30).tokenStream("token", new StringReader(content));
/* 227 */         content = highlighter.getBestFragment(tokenStream, content);
/*     */ 
/* 230 */         if ((content == null) || (content.equals(""))) {
/* 231 */           int countWords = Integer.valueOf(length).intValue();
/* 232 */           if (countWords >= contentTemp.length())
/* 233 */             content = contentTemp;
/*     */           else {
/* 235 */             content = contentTemp.substring(0, countWords);
/*     */           }
/*     */         }
/*     */ 
/* 239 */         String url = doc.get("url");
/* 240 */         String title = doc.get("title");
/* 241 */         String type = doc.get("type");
/* 242 */         String time = doc.get("publish_time");
/* 243 */         String id = doc.get("id");
/* 244 */         String categoryId = doc.get("categoryId");
/* 245 */         String site_id = doc.get("site_id");
/* 246 */         String model_id = doc.get("model_id");
/*     */ 
/* 248 */         ResultBean resultBean = new ResultBean();
/* 249 */         if (!site_domain.equals("")) {
/* 250 */           url = "http://" + site_domain + url;
/*     */         }
/* 253 */         else if (site_idB.equals("")) {
/* 254 */           url = "http://" + SearchForInterface.getDomainBySiteId(site_id) + url;
/*     */         }
/*     */ 
/* 258 */         resultBean.setUrl(url);
/* 259 */         resultBean.setTitle(title);
/* 260 */         resultBean.setType(type);
/*     */ 
/* 262 */         resultBean.setTime(SearchUtil.formatTimeYYYYMMDDHHMMSS(time));
/* 263 */         resultBean.setId(id);
/* 264 */         resultBean.setCategory_id(categoryId);
/* 265 */         resultBean.setCategory_name(SearchForInterface.getCategoryNameById(categoryId));
/* 266 */         resultBean.setSite_id(site_id);
/* 267 */         resultBean.setContent(content);
/* 268 */         resultBean.setModel_id(model_id);
/*     */ 
/* 270 */         listResult.add(resultBean);
/*     */       }
/*     */ 
/* 275 */       timeS = System.currentTimeMillis() - timeS;
/* 276 */       Double timeQ = Arith.div(Double.valueOf(timeS), Double.valueOf(2000.0D), 2);
/*     */ 
/* 278 */       searchResult.setItems(listResult);
/* 279 */       searchResult.setPageControl(pageControl);
/* 280 */       searchResult.setTime(String.valueOf(timeQ));
/* 281 */       if (!q2.equals("")) {
/* 282 */         q = q + "AND" + q2;
/*     */       }
/* 284 */       searchResult.setKey(q);
/* 285 */       searchResult.setCount(pageControl.getMaxRowCount().longValue());
/* 286 */       indexSearcher.close();
/*     */ 
/* 288 */       return searchResult;
/*     */     } catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       if (indexSearcher != null)
/*     */         try {
/* 293 */           indexSearcher.close();
/*     */         } catch (IOException e1) {
/* 295 */           e1.printStackTrace();
/*     */         }
/*     */     }
/* 298 */     return searchResult;
/*     */   }
/*     */ 
/*     */   public static SearchResult searchGJ(Map map)
/*     */   {
/* 324 */     System.out.println("map====" + map);
/* 325 */     SearchResult searchResult = new SearchResult();
/* 326 */     long timeS = System.currentTimeMillis();
/* 327 */     List listResult = new ArrayList();
/* 328 */     IndexSearcher indexSearcher = null;
/*     */     try {
/* 330 */       String q = (String)map.get("q");
/* 331 */       if (q == null) {
/* 332 */         q = "";
/*     */       }
/*     */ 
/* 336 */       String scope = (String)map.get("scope") == null ? "" : (String)map.get("scope");
/*     */ 
/* 340 */       Query query = null;
/*     */ 
/* 342 */       indexSearcher = SearchLuceneManager.getIndexSearcher();
/* 343 */       if (indexSearcher == null) {
/* 344 */         return searchResult;
/*     */       }
/*     */ 
/* 349 */       QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_30, new String[] { "" }, new StandardAnalyzer(Version.LUCENE_30));
/*     */ 
/* 352 */       String site_idB = (String)map.get("site_id") == null ? "" : (String)map.get("site_id");
/*     */ 
/* 354 */       String site_domain = (String)map.get("site_domain") == null ? "" : (String)map.get("site_domain");
/*     */ 
/* 356 */       String st = (String)map.get("st") == null ? "" : (String)map.get("st");
/*     */ 
/* 358 */       Boolean sortB = Boolean.valueOf(true);
/* 359 */       if (st.equals("2")) {
/* 360 */         sortB = Boolean.valueOf(false);
/*     */       }
/* 362 */       Sort sort = new Sort(new SortField[] { new SortField("publish_time", 3, sortB.booleanValue()), new SortField("id", 4, true) });
/*     */ 
/* 365 */       BooleanQuery booleanQuery = new BooleanQuery();
/* 366 */       BooleanQuery booleanQueryHighlighter = new BooleanQuery();
/* 367 */       if (q.equals("")) {
/* 368 */         Query queryTime = parser.parse("publish_time:[20000101000001 TO " + DateUtil.getCurrentDateTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "") + "]");
/* 369 */         booleanQuery.add(queryTime, BooleanClause.Occur.MUST);
/*     */       }
/* 371 */       else if (!q.contains("AND"))
/*     */       {
/* 373 */         query = getQuery(scope, q);
/* 374 */         System.out.println("query===" + query.toString());
/* 375 */         booleanQuery.add(query, BooleanClause.Occur.MUST);
/*     */       }
/*     */ 
/* 381 */       String qn1 = (String)map.get("qn1") == null ? "" : (String)map.get("qn1");
/* 382 */       StringTokenizer stqn1 = new StringTokenizer(qn1, " ");
/* 383 */       while (stqn1.hasMoreElements()) {
/* 384 */         String token = stqn1.nextElement().toString();
/* 385 */         if (!token.equals("")) {
/* 386 */           Query query_qn1 = getQuery(scope, token);
/* 387 */           booleanQuery.add(query_qn1, BooleanClause.Occur.MUST);
/* 388 */           booleanQueryHighlighter.add(query_qn1, BooleanClause.Occur.MUST);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 395 */       boolean booleanQn2 = false;
/* 396 */       BooleanQuery booleanQueryQn2 = new BooleanQuery();
/* 397 */       String qn2 = (String)map.get("qn2") == null ? "" : (String)map.get("qn2");
/* 398 */       StringTokenizer stqn2 = new StringTokenizer(qn2, " ");
/* 399 */       while (stqn2.hasMoreElements()) {
/* 400 */         String token = stqn2.nextElement().toString();
/* 401 */         if (!token.equals("")) {
/* 402 */           booleanQn2 = true;
/* 403 */           Query query_qn2 = getQuery(scope, token);
/* 404 */           booleanQueryQn2.add(query_qn2, BooleanClause.Occur.SHOULD);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 409 */       if (booleanQn2) {
/* 410 */         System.out.println("booleanQueryQn2===" + booleanQueryQn2.toString());
/* 411 */         booleanQuery.add(booleanQueryQn2, BooleanClause.Occur.MUST);
/*     */       }
/*     */ 
/* 416 */       String qn3 = (String)map.get("qn3") == null ? "" : (String)map.get("qn3");
/* 417 */       StringTokenizer stqn3 = new StringTokenizer(qn3, " ");
/* 418 */       while (stqn3.hasMoreElements()) {
/* 419 */         String token = stqn3.nextElement().toString();
/* 420 */         if (!token.equals("")) {
/* 421 */           Query query_qn3 = getQuery(scope, token);
/* 422 */           booleanQuery.add(query_qn3, BooleanClause.Occur.MUST_NOT);
/*     */ 
/* 424 */           System.out.println("query_qn1_" + token + "===" + query_qn3.toString());
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 430 */       String ts = (String)map.get("ts") == null ? "" : (String)map.get("ts");
/* 431 */       String te = (String)map.get("te") == null ? "" : (String)map.get("te");
/* 432 */       if ((!ts.equals("")) || (!te.equals(""))) {
/* 433 */         if (ts.equals("")) {
/* 434 */           ts = "2000-01-01 00:00:01";
/* 435 */           te = te + " 59:59:59";
/*     */         }
/* 437 */         if (te.equals("")) {
/* 438 */           ts = ts + " 00:00:01";
/* 439 */           te = DateUtil.getCurrentDateTime();
/*     */         }
/* 441 */         ts = ts.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "") + "000000";
/* 442 */         te = te.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "") + "595959";
/*     */ 
/* 447 */         Query queryTime = parser.parse("publish_time:[" + ts + " TO " + te + "]");
/* 448 */         booleanQuery.add(queryTime, BooleanClause.Occur.MUST);
/*     */       }
/*     */ 
/* 452 */       String ds_id = (String)map.get("ds_id") == null ? "" : (String)map.get("ds_id");
/* 453 */       if (!ds_id.equals("")) {
/* 454 */         for (String s : ds_id.split(",")) {
/* 455 */           if ((s != null) && (!"".equals(s))) {
/* 456 */             Query queryDsId = parser.parse("site_id:" + s);
/* 457 */             booleanQuery.add(queryDsId, BooleanClause.Occur.MUST_NOT);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 463 */       String q2 = (String)map.get("q2") == null ? "" : (String)map.get("q2");
/*     */ 
/* 466 */       if (q.trim().equals("AND")) {
/* 467 */         return searchResult;
/*     */       }
/* 469 */       if (!q.contains("AND")) {
/* 470 */         if (!q.equals(""))
/* 471 */           booleanQueryHighlighter.add(query, BooleanClause.Occur.MUST);
/*     */       }
/*     */       else {
/* 474 */         List listF = Arrays.asList(q.split("AND"));
/* 475 */         for (??? = listF.iterator(); ((Iterator)???).hasNext(); ) { String str44 = (String)((Iterator)???).next();
/* 476 */           if ((str44 != null) && (!"".equals(str44))) {
/* 477 */             Query query_3 = getQuery(scope, str44);
/* 478 */             booleanQuery.add(query_3, BooleanClause.Occur.MUST);
/* 479 */             booleanQueryHighlighter.add(query_3, BooleanClause.Occur.MUST);
/* 480 */             System.out.println("query_3===" + query_3.toString());
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 485 */       if (!q2.equals("")) {
/* 486 */         Query query2 = getQuery(scope, q2);
/* 487 */         booleanQuery.add(query2, BooleanClause.Occur.MUST);
/* 488 */         booleanQueryHighlighter.add(query2, BooleanClause.Occur.MUST);
/* 489 */         System.out.println("query2===" + query2.toString());
/*     */       }
/*     */ 
/* 493 */       int p = (String)map.get("p") == null ? 1 : Integer.valueOf((String)map.get("p")).intValue();
/* 494 */       int pz = (String)map.get("pz") == null ? 0 : Integer.valueOf((String)map.get("pz")).intValue();
/* 495 */       int length = (String)map.get("length") == null ? 100 : Integer.valueOf((String)map.get("length")).intValue();
/* 496 */       String color = (String)map.get("color");
/* 497 */       if (color == null) {
/* 498 */         color = "red";
/*     */       }
/* 500 */       map.remove("q");
/* 501 */       map.remove("q2");
/* 502 */       map.remove("p");
/* 503 */       map.remove("pz");
/* 504 */       map.remove("length");
/* 505 */       map.remove("color");
/* 506 */       map.remove("scope");
/* 507 */       map.remove("ts");
/* 508 */       map.remove("te");
/* 509 */       map.remove("st");
/* 510 */       map.remove("site_domain");
/* 511 */       map.remove("ds_id");
/* 512 */       map.remove("qn1");
/* 513 */       map.remove("qn2");
/* 514 */       map.remove("qn3");
/*     */ 
/* 517 */       if ((map.get("categoryId") != null) && (!"".equals(map.get("categoryId")))) {
/* 518 */         String site_id = (String)map.get("site_id");
/* 519 */         int categoryId = Integer.parseInt((String)map.get("categoryId"));
/* 520 */         if (!site_id.equals("all")) {
/* 521 */           String cat_ids = CategoryManager.getAllChildCategoryIDS(categoryId, site_id);
/* 522 */           if ((cat_ids == null) || ("".equals(cat_ids))) {
/* 523 */             cat_ids = (String)map.get("categoryId");
/*     */           }
/* 525 */           map.put("categoryId", cat_ids);
/*     */         }
/*     */       }
/*     */ 
/* 529 */       String tempQuery = getAllQuery(map);
/* 530 */       QueryWrapperFilter filter = tempQuery.equals("") ? null : new QueryWrapperFilter(parser.parse(tempQuery.toString()));
/*     */ 
/* 533 */       PageControl pageControl = new PageControl();
/* 534 */       pageControl.setRowsPerPage(pz);
/* 535 */       pageControl.setCurPage(p);
/* 536 */       pageControl.countStart();
/* 537 */       int sn = pageControl.getStart();
/* 538 */       pz = pageControl.getRowsPerPage();
/*     */ 
/* 543 */       System.out.println("booleanQuery : " + booleanQuery);
/* 544 */       TopDocs hits = indexSearcher.search(booleanQuery, filter, Integer.valueOf(sn).intValue() + Integer.valueOf(pz).intValue(), sort);
/*     */ 
/* 546 */       System.out.println("找到了" + hits.totalHits + "个");
/*     */ 
/* 549 */       pageControl.setMaxRowCount(Long.valueOf(hits.totalHits));
/* 550 */       pageControl.countMaxPage();
/*     */ 
/* 554 */       Highlighter highlighter = null;
/* 555 */       SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
/* 556 */         "<font color='" + color + "'>", "</font>");
/* 557 */       System.out.println("booleanQueryHighlighter--------" + booleanQueryHighlighter.toString());
/* 558 */       highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(booleanQueryHighlighter));
/*     */ 
/* 561 */       highlighter.setTextFragmenter(new SimpleFragmenter(length));
/*     */ 
/* 564 */       for (int j = sn; j < hits.scoreDocs.length; j++) {
/* 565 */         ScoreDoc sdoc = hits.scoreDocs[j];
/* 566 */         Document doc = indexSearcher.doc(sdoc.doc);
/*     */ 
/* 569 */         String content = doc.get("content");
/*     */ 
/* 571 */         String contentTemp = content;
/*     */ 
/* 573 */         TokenStream tokenStream = new StandardAnalyzer(Version.LUCENE_30).tokenStream("token", new StringReader(content));
/* 574 */         content = highlighter.getBestFragment(tokenStream, content);
/*     */ 
/* 577 */         if ((content == null) || (content.equals(""))) {
/* 578 */           int countWords = Integer.valueOf(length).intValue();
/* 579 */           if (countWords >= contentTemp.length())
/* 580 */             content = contentTemp;
/*     */           else {
/* 582 */             content = contentTemp.substring(0, countWords);
/*     */           }
/*     */         }
/*     */ 
/* 586 */         String url = doc.get("url");
/* 587 */         String title = doc.get("title");
/* 588 */         String type = doc.get("type");
/* 589 */         String time = doc.get("publish_time");
/* 590 */         String id = doc.get("id");
/* 591 */         String categoryId = doc.get("categoryId");
/* 592 */         String site_id = doc.get("site_id");
/* 593 */         String model_id = doc.get("model_id");
/*     */ 
/* 595 */         ResultBean resultBean = new ResultBean();
/*     */ 
/* 598 */         if (!url.startsWith("http://")) {
/* 599 */           if (!site_domain.equals("")) {
/* 600 */             url = "http://" + site_domain + url;
/*     */           }
/* 602 */           else if (site_idB.equals(""))
/* 603 */             url = "http://" + SearchForInterface.getDomainBySiteId(site_id) + url;
/*     */           else {
/* 605 */             url = "http://" + SearchForInterface.getDomainBySiteId(site_idB) + url;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 610 */         resultBean.setUrl(url);
/* 611 */         resultBean.setTitle(title);
/* 612 */         resultBean.setType(type);
/*     */ 
/* 614 */         resultBean.setTime(SearchUtil.formatTimeYYYYMMDDHHMMSS(time));
/* 615 */         resultBean.setId(id);
/* 616 */         resultBean.setCategory_id(categoryId);
/* 617 */         System.out.println("(categoryId===" + categoryId + ")  (id===" + id + ")");
/*     */ 
/* 619 */         resultBean.setCategory_name(SearchForInterface.getCategoryNameById(categoryId));
/* 620 */         resultBean.setSite_id(site_id);
/* 621 */         resultBean.setContent(content);
/* 622 */         resultBean.setModel_id(model_id);
/*     */ 
/* 624 */         listResult.add(resultBean);
/*     */       }
/*     */ 
/* 629 */       timeS = System.currentTimeMillis() - timeS;
/* 630 */       Double timeQ = Arith.div(Double.valueOf(timeS), Double.valueOf(2000.0D), 2);
/*     */ 
/* 632 */       searchResult.setItems(listResult);
/* 633 */       searchResult.setPageControl(pageControl);
/* 634 */       searchResult.setTime(String.valueOf(timeQ));
/* 635 */       if (!q2.equals("")) {
/* 636 */         q = q + "AND" + q2;
/*     */       }
/* 638 */       searchResult.setKey(q);
/* 639 */       searchResult.setCount(pageControl.getMaxRowCount().longValue());
/* 640 */       indexSearcher.close();
/*     */ 
/* 642 */       return searchResult;
/*     */     } catch (Exception e) {
/* 644 */       e.printStackTrace();
/* 645 */       if (indexSearcher != null)
/*     */         try {
/* 647 */           indexSearcher.close();
/*     */         } catch (IOException e1) {
/* 649 */           e1.printStackTrace();
/*     */         }
/*     */     }
/* 652 */     return searchResult;
/*     */   }
/*     */ 
/*     */   public static String getAllQuery(Map map)
/*     */   {
/* 660 */     System.out.println("getAllQuery --- map = " + map);
/* 661 */     StringBuffer sb = new StringBuffer("");
/* 662 */     Iterator it = map.keySet().iterator();
/* 663 */     int i = 0;
/* 664 */     while (it.hasNext()) {
/* 665 */       i++;
/* 666 */       String key = (String)it.next();
/* 667 */       String object = (String)map.get(key);
/* 668 */       if (key.equals("type")) {
/* 669 */         if ((!sb.toString().trim().equals("")) && (sb.toString().trim().endsWith(")"))) {
/* 670 */           sb.append(" && ");
/*     */         }
/* 672 */         sb.append(getTypeQuery(key, object));
/* 673 */       } else if (key.equals("categoryId")) {
/* 674 */         if ((!sb.toString().trim().equals("")) && (sb.toString().trim().endsWith(")"))) {
/* 675 */           sb.append(" && ");
/*     */         }
/* 677 */         sb.append(getCategoryIdQuery(key, object));
/* 678 */       } else if (key.equals("site_id")) {
/* 679 */         String typef = (String)map.get("type");
/* 680 */         if (typef == null) {
/* 681 */           typef = "";
/*     */         }
/* 683 */         if (!typef.equals("info,zwgk")) {
/* 684 */           if ((!sb.toString().trim().equals("")) && (sb.toString().trim().endsWith(")"))) {
/* 685 */             sb.append(" && ");
/*     */           }
/* 687 */           if (i == map.size())
/* 688 */             sb.append("(" + key + ":" + object + ")");
/*     */           else
/* 690 */             sb.append("(" + key + ":" + object + ")");
/*     */         }
/*     */         else {
/* 693 */           List gkNodeBeans = GKNodeManager.getAllGKNodeList();
/* 694 */           String node_id = "";
/* 695 */           String site_idv = (String)map.get(key);
/* 696 */           for (GKNodeBean nodeBean : gkNodeBeans) {
/* 697 */             if (nodeBean.getRela_site_id().equals(site_idv)) {
/* 698 */               node_id = nodeBean.getNode_id();
/* 699 */               break;
/*     */             }
/*     */           }
/* 702 */           if (!node_id.equals("")) {
/* 703 */             if ((!sb.toString().trim().equals("")) && (sb.toString().trim().endsWith(")"))) {
/* 704 */               sb.append(" && ");
/*     */             }
/* 706 */             sb.append(getNodeIdAndSiteIdQuery(site_idv, node_id));
/*     */           }
/*     */         }
/*     */       } else {
/* 710 */         if (!sb.toString().trim().equals("")) {
/* 711 */           sb.append(" && ");
/*     */         }
/* 713 */         if (i == map.size())
/* 714 */           sb.append("(" + key + ":" + object + ")");
/*     */         else {
/* 716 */           sb.append("(" + key + ":" + object + ")");
/*     */         }
/*     */       }
/*     */     }
/* 720 */     System.out.println("sb.toString()====" + sb.toString());
/* 721 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getTypeQuery(String key, String str)
/*     */   {
/* 728 */     StringBuffer sb = new StringBuffer();
/* 729 */     if (str.indexOf(",") > 0) {
/* 730 */       sb.append("(");
/* 731 */       String[] temp = str.split(",");
/* 732 */       for (int i = 0; i < temp.length; i++) {
/* 733 */         if (i == temp.length - 1)
/* 734 */           sb.append("(" + key + ":" + temp[i] + ")");
/*     */         else {
/* 736 */           sb.append("(" + key + ":" + temp[i] + ") || ");
/*     */         }
/*     */       }
/* 739 */       sb.append(")");
/* 740 */       return sb.toString();
/* 741 */     }if (!str.toString().equals("")) {
/* 742 */       sb.append("(" + key + ":" + str + ")");
/* 743 */       return sb.toString();
/*     */     }
/* 745 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getCategoryIdQuery(String key, String str)
/*     */   {
/* 751 */     StringBuffer sb = new StringBuffer();
/* 752 */     if (str.indexOf(",") > 0) {
/* 753 */       sb.append("(");
/* 754 */       String[] temp = str.split(",");
/* 755 */       for (int i = 0; i < temp.length; i++) {
/* 756 */         if (i == temp.length - 1)
/* 757 */           sb.append("(" + key + ":" + temp[i] + ")");
/*     */         else {
/* 759 */           sb.append("(" + key + ":" + temp[i] + ") || ");
/*     */         }
/*     */       }
/* 762 */       sb.append(")");
/* 763 */       return sb.toString();
/* 764 */     }if (!str.toString().equals("")) {
/* 765 */       sb.append("(" + key + ":" + str + ")");
/* 766 */       return sb.toString();
/*     */     }
/* 768 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getNodeIdAndSiteIdQuery(String site_id, String node_id)
/*     */   {
/* 774 */     StringBuffer sb = new StringBuffer();
/* 775 */     if ((!site_id.equals("")) && (!node_id.equals(""))) {
/* 776 */       sb.append("(");
/* 777 */       sb.append("(site_id:" + site_id + ") || ");
/* 778 */       sb.append("(node_id:" + node_id + ")");
/* 779 */       sb.append(")");
/* 780 */       return sb.toString();
/*     */     }
/* 782 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.search.SearchInfoManager
 * JD-Core Version:    0.6.2
 */