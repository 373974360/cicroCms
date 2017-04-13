/*     */ package com.cicro.wcm.bean.page;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PageBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5221827487623932524L;
/*  11 */   private int id = 0;
/*  12 */   private int page_id = 0;
/*  13 */   private int parent_id = 0;
/*  14 */   private String page_ename = "";
/*  15 */   private String page_cname = "";
/*  16 */   private int template_id = 0;
/*  17 */   private String page_path = "";
/*  18 */   private int page_interval = 0;
/*  19 */   private String last_dtime = "";
/*  20 */   private String next_dtime = "";
/*  21 */   private int sort_id = 999;
/*  22 */   private String page_memo = "";
/*  23 */   private String app_id = "";
/*  24 */   private String site_id = "";
/*  25 */   private List<PageBean> child_list = null;
/*     */ 
/*  27 */   public List<PageBean> getChild_list() { return this.child_list; }
/*     */ 
/*     */   public void setChild_list(List<PageBean> childList) {
/*  30 */     this.child_list = childList;
/*     */   }
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   public int getPage_id() {
/*  39 */     return this.page_id;
/*     */   }
/*     */   public void setPage_id(int pageId) {
/*  42 */     this.page_id = pageId;
/*     */   }
/*     */   public int getParent_id() {
/*  45 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parentId) {
/*  48 */     this.parent_id = parentId;
/*     */   }
/*     */   public String getPage_ename() {
/*  51 */     return this.page_ename;
/*     */   }
/*     */   public void setPage_ename(String pageEname) {
/*  54 */     this.page_ename = pageEname;
/*     */   }
/*     */   public String getPage_cname() {
/*  57 */     return this.page_cname;
/*     */   }
/*     */   public void setPage_cname(String pageCname) {
/*  60 */     this.page_cname = pageCname;
/*     */   }
/*     */   public int getTemplate_id() {
/*  63 */     return this.template_id;
/*     */   }
/*     */   public void setTemplate_id(int templateId) {
/*  66 */     this.template_id = templateId;
/*     */   }
/*     */   public String getPage_path() {
/*  69 */     return this.page_path;
/*     */   }
/*     */   public void setPage_path(String pagePath) {
/*  72 */     this.page_path = pagePath;
/*     */   }
/*     */   public int getPage_interval() {
/*  75 */     return this.page_interval;
/*     */   }
/*     */   public void setPage_interval(int pageInterval) {
/*  78 */     this.page_interval = pageInterval;
/*     */   }
/*     */   public String getLast_dtime() {
/*  81 */     return this.last_dtime;
/*     */   }
/*     */   public void setLast_dtime(String lastDtime) {
/*  84 */     this.last_dtime = lastDtime;
/*     */   }
/*     */   public String getNext_dtime() {
/*  87 */     return this.next_dtime;
/*     */   }
/*     */   public void setNext_dtime(String nextDtime) {
/*  90 */     this.next_dtime = nextDtime;
/*     */   }
/*     */   public int getSort_id() {
/*  93 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/*  96 */     this.sort_id = sortId;
/*     */   }
/*     */   public String getPage_memo() {
/*  99 */     return this.page_memo;
/*     */   }
/*     */   public void setPage_memo(String pageMemo) {
/* 102 */     this.page_memo = pageMemo;
/*     */   }
/*     */   public String getApp_id() {
/* 105 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 108 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 111 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 114 */     this.site_id = siteId;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.page.PageBean
 * JD-Core Version:    0.6.2
 */