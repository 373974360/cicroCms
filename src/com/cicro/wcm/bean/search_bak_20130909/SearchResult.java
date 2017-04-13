/*     */ package com.cicro.wcm.bean.search_bak_20130909;
/*     */ 
/*     */ import com.cicro.wcm.bean.search.*;
import com.cicro.wcm.bean.search.ResultBean;

import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SearchResult
/*     */ {
/*  18 */   private String key = "";
/*  19 */   private String time = "0.28";
/*  20 */   private long count = 0L;
/*     */   private com.cicro.wcm.bean.search.PageControl pageControl;
/*  22 */   private List<com.cicro.wcm.bean.search.ResultBean> items = new ArrayList();
/*     */ 
/*     */   public void addItem(com.cicro.wcm.bean.search.ResultBean item)
/*     */   {
/*  31 */     this.items.add(item);
/*     */   }
/*     */ 
/*     */   public void removeItem(com.cicro.wcm.bean.search.ResultBean item)
/*     */   {
/*  39 */     this.items.remove(item);
/*     */   }
/*     */ 
/*     */   public void addAll(Set items)
/*     */   {
/*  47 */     items.addAll(items);
/*     */   }
/*     */ 
/*     */   public void removeAll(Set items)
/*     */   {
/*  55 */     items.removeAll(items);
/*     */   }
/*     */ 
/*     */   public boolean contains(com.cicro.wcm.bean.search.ResultBean item)
/*     */   {
/*  64 */     return this.items.contains(item);
/*     */   }
/*     */ 
/*     */   public List getItems() {
/*  68 */     return this.items;
/*     */   }
/*     */ 
/*     */   public com.cicro.wcm.bean.search.PageControl getPageControl() {
/*  72 */     return this.pageControl;
/*     */   }
/*     */ 
/*     */   public void setPageControl(com.cicro.wcm.bean.search.PageControl pageControl) {
/*  76 */     this.pageControl = pageControl;
/*     */   }
/*     */ 
/*     */   public String getKey() {
/*  80 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/*  84 */     this.key = key;
/*     */   }
/*     */ 
/*     */   public String getTime() {
/*  88 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(String time) {
/*  92 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public long getCount() {
/*  96 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(long count) {
/* 100 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public void setItems(List<ResultBean> items) {
/* 104 */     this.items = items;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search_bak_20130909.SearchResult
 * JD-Core Version:    0.6.2
 */