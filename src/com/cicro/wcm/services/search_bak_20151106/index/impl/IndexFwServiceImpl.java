/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexInfoDaoImpl;
/*     */ import com.cicro.wcm.services.search.index.IndexLuceneManager;
/*     */ import com.cicro.wcm.services.search.index.IndexManager;
/*     */ import com.cicro.wcm.services.search.index.IndexService;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.Term;
/*     */ 
/*     */ public class IndexFwServiceImpl
/*     */   implements IndexService
/*     */ {
/*  31 */   static int s_count = 50;
/*  32 */   static IIndexInfoDao indexInfoDao = new IndexInfoDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  45 */     mapSite.remove("site_id");
/*  46 */     mapSite.put("app_id", "ggfw");
/*  47 */     IndexManager.createAllIndexByAppId(mapSite);
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/*  58 */     IndexManager.appendSingleDocumentAppId(infos);
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/*  69 */     IndexWriter indexWriter = null;
/*     */     try {
/*  71 */       if (map.get("site_id") != null) {
/*  72 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  74 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
/*  75 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  79 */       e.printStackTrace();
/*  80 */       return false;
/*     */     }
/*     */     finally {
/*  83 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/*  95 */     IndexWriter indexWriter = null;
/*     */     try {
/*  97 */       if (infos.get("id") != null)
/*     */       {
/*  99 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 101 */         Term term = new Term("id", (String)infos.get("id"));
/* 102 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 106 */       e.printStackTrace();
/* 107 */       return false;
/*     */     }
/*     */     finally {
/* 110 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 117 */     IndexFwServiceImpl impl = new IndexFwServiceImpl();
/* 118 */     Map map = new HashMap();
/* 119 */     map.put("id", "1111");
/* 120 */     impl.appendSingleDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexFwServiceImpl
 * JD-Core Version:    0.6.2
 */