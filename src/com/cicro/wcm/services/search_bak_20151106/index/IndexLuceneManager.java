/*     */ package com.cicro.wcm.services.search_bak_20151106.index;
/*     */ 
/*     */ import com.cicro.wcm.services.search.SearchForInterface;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import org.apache.lucene.analysis.standard.StandardAnalyzer;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.index.CorruptIndexException;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.IndexWriter.MaxFieldLength;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.LockObtainFailedException;
/*     */ import org.apache.lucene.store.SimpleFSDirectory;
/*     */ import org.apache.lucene.util.Version;
/*     */ 
/*     */ public class IndexLuceneManager
/*     */ {
/*  25 */   static String indexDir = SearchForInterface.getIndexPathRoot();
/*  26 */   static Directory dir = null;
/*     */ 
/*     */   public static void initIndex()
/*     */   {
/*  34 */     File file = new File(indexDir);
/*  35 */     if (!file.exists()) {
/*  36 */       file.mkdir();
/*     */     }
/*     */     try
/*     */     {
/*  40 */       getIndexModifier(true);
/*     */     } catch (Exception e) {
/*  42 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static IndexWriter getIndexModifier(boolean isNew)
/*     */     throws CorruptIndexException, LockObtainFailedException, IOException
/*     */   {
/*  49 */     if (dir == null)
/*     */     {
/*  51 */       dir = new SimpleFSDirectory(new File(indexDir));
/*  52 */       if (!isNew) {
/*  53 */         File file = new File(indexDir);
/*  54 */         if (!file.exists()) {
/*  55 */           file.mkdir();
/*  56 */           initIndex();
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  62 */     File lockFile = new File(indexDir + File.separator + "write.lock");
/*  63 */     System.out.println("lockFile-----" + lockFile);
/*  64 */     if (lockFile.exists()) {
/*  65 */       System.out.println("lockFile delete -----" + lockFile);
/*  66 */       lockFile.deleteOnExit();
/*     */     }
/*     */ 
/*  72 */     IndexWriter indexWriter = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), isNew, IndexWriter.MaxFieldLength.UNLIMITED);
/*     */ 
/*  75 */     indexWriter.setMergeFactor(10);
/*  76 */     indexWriter.setRAMBufferSizeMB(500.0D);
/*  77 */     if (isNew) {
/*  78 */       indexWriter.close();
/*     */     }
/*  80 */     return indexWriter;
/*     */   }
/*     */ 
/*     */   public static void closeIndexWriter(IndexWriter indexWriter)
/*     */   {
/*     */     try {
/*  86 */       if (indexWriter != null)
/*  87 */         indexWriter.close();
/*     */     }
/*     */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void AddDocument(IndexWriter indexWriter, Document doc)
/*     */   {
/*     */     try
/*     */     {
/* 107 */       indexWriter.addDocument(doc);
/*     */     } catch (Exception e) {
/* 109 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void DeleteDocument(IndexWriter indexWriter, Term term)
/*     */   {
/*     */     try {
/* 116 */       indexWriter.deleteDocuments(term);
/*     */     } catch (Exception e) {
/* 118 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.IndexLuceneManager
 * JD-Core Version:    0.6.2
 */