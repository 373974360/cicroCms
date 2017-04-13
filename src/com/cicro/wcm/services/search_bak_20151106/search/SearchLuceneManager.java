/*    */ package com.cicro.wcm.services.search_bak_20151106.search;
/*    */ 
/*    */ import com.cicro.wcm.services.search.SearchForInterface;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.lucene.search.IndexSearcher;
/*    */ import org.apache.lucene.store.Directory;
/*    */ import org.apache.lucene.store.SimpleFSDirectory;
/*    */ import org.wltea.analyzer.dic.Dictionary;
/*    */ import org.wltea.analyzer.lucene.IKSimilarity;
/*    */ 
/*    */ public class SearchLuceneManager
/*    */ {
/* 19 */   static String indexDir = SearchForInterface.getIndexPathRoot();
/* 20 */   static Directory dir = null;
/*    */ 
/*    */   public static IndexSearcher getIndexSearcher() throws IOException {
/* 23 */     if (dir == null)
/*    */     {
/* 25 */       if (!new File(indexDir).exists()) {
/* 26 */         return null;
/*    */       }
/* 28 */       dir = new SimpleFSDirectory(new File(indexDir));
/*    */     }
/*    */ 
/* 31 */     IndexSearcher indexSearch = new IndexSearcher(dir);
/*    */ 
/* 33 */     indexSearch.setSimilarity(new IKSimilarity());
/*    */ 
/* 36 */     List list = new ArrayList();
/*    */ 
/* 38 */     Dictionary.loadExtendStopWords(list);
/*    */ 
/* 40 */     return indexSearch;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.search.SearchLuceneManager
 * JD-Core Version:    0.6.2
 */