/*     */ package com.cicro.wcm.services.search_bak_20151106;
/*     */ 
/*     */ import com.cicro.wcm.services.search.*;
import com.cicro.wcm.services.search.index.IndexManager;
/*     */ import com.cicro.wcm.services.search.index.util.Queue;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TimerTask;
/*     */ 
/*     */ class SearchInnerManager$SearchIndexTask extends TimerTask
/*     */ {
/*     */   public void run()
/*     */   {
/*  95 */     System.out.println("Start SearchIndex Task!!!");
/*  96 */     while (!com.cicro.wcm.services.search.SearchInnerManager.access$0().isEmpty()) {
/*  97 */       Map map = (Map) com.cicro.wcm.services.search.SearchInnerManager.access$0().get();
/*     */ 
/*  99 */       String flag = (String)map.get("flag");
/* 100 */       if (flag.equals("1")) {
/* 101 */         System.out.println("Create SearchIndex map =====" + map);
/* 102 */         IndexManager.appendSingleDocument(map);
/*     */       }
/* 104 */       if (flag.equals("0")) {
/* 105 */         System.out.println("Delete SearchIndex map =====" + map);
/* 106 */         IndexManager.deleteSingleDocument(map);
/*     */       }
/*     */     }
/* 109 */     System.out.println("End SearchIndex Task!!!");
/*     */   }
/*     */ 
/*     */   public static void initSetAdd(Set set)
/*     */   {
/* 114 */     Iterator it = set.iterator();
/* 115 */     while (it.hasNext()) {
/* 116 */       Object o = it.next();
/* 117 */       if (com.cicro.wcm.services.search.SearchInnerManager.access$1().contains(o))
/* 118 */         com.cicro.wcm.services.search.SearchInnerManager.access$1().remove(o);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void initSetDelete(Set set) {
/* 123 */     Iterator it = set.iterator();
/* 124 */     while (it.hasNext()) {
/* 125 */       Object o = it.next();
/* 126 */       if (com.cicro.wcm.services.search.SearchInnerManager.access$2().contains(o))
/* 127 */         com.cicro.wcm.services.search.SearchInnerManager.access$2().remove(o);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.SearchInnerManager.SearchIndexTask
 * JD-Core Version:    0.6.2
 */