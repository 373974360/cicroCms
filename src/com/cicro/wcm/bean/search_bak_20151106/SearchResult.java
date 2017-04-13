/*     */ package com.cicro.wcm.bean.search_bak_20151106;
/*     */ 
/*     */ import com.cicro.wcm.bean.search.*;
import com.cicro.wcm.bean.search.ResultBean;

import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SearchResult
/*     */ {
/*  19 */   private String key = "";
/*  20 */   private String time = "0.28";
/*  21 */   private long count = 0L;
/*     */   private com.cicro.wcm.bean.search.PageControl pageControl;
/*  23 */   private List<com.cicro.wcm.bean.search.ResultBean> items = new ArrayList();
/*     */ 
/*  25 */   private String keyE = "";
/*     */ 
/*     */   public void addItem(com.cicro.wcm.bean.search.ResultBean item)
/*     */   {
/*  35 */     this.items.add(item);
/*     */   }
/*     */ 
/*     */   public void removeItem(com.cicro.wcm.bean.search.ResultBean item)
/*     */   {
/*  43 */     this.items.remove(item);
/*     */   }
/*     */ 
/*     */   public void addAll(Set items)
/*     */   {
/*  51 */     items.addAll(items);
/*     */   }
/*     */ 
/*     */   public void removeAll(Set items)
/*     */   {
/*  59 */     items.removeAll(items);
/*     */   }
/*     */ 
/*     */   public boolean contains(com.cicro.wcm.bean.search.ResultBean item)
/*     */   {
/*  68 */     return this.items.contains(item);
/*     */   }
/*     */ 
/*     */   public List getItems() {
/*  72 */     return this.items;
/*     */   }
/*     */ 
/*     */   public com.cicro.wcm.bean.search.PageControl getPageControl() {
/*  76 */     return this.pageControl;
/*     */   }
/*     */ 
/*     */   public void setPageControl(com.cicro.wcm.bean.search.PageControl pageControl) {
/*  80 */     this.pageControl = pageControl;
/*     */   }
/*     */ 
/*     */   public String getKey() {
/*  84 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/*  88 */     this.key = key;
/*     */   }
/*     */ 
/*     */   public String getTime() {
/*  92 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(String time) {
/*  96 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public long getCount() {
/* 100 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(long count) {
/* 104 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public void setItems(List<ResultBean> items) {
/* 108 */     this.items = items;
/*     */   }
/*     */ 
/*     */   public String getKeyE() {
/*     */     try {
/* 113 */       return URLEncoder.encode(this.key, "utf-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/* 116 */       e.printStackTrace();
/* 117 */     }return "";
/*     */   }
/*     */ 
/*     */   public void setKeyEncode(String keyE)
/*     */   {
/* 122 */     this.keyE = keyE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search.SearchResult
 * JD-Core Version:    0.6.2
 */