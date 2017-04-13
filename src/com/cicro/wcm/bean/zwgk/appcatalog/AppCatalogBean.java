/*     */ package com.cicro.wcm.bean.zwgk.appcatalog;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class AppCatalogBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 57811879513553903L;
/*   8 */   private int id = 0;
/*   9 */   private int cata_id = 0;
/*  10 */   private String cata_cname = "";
/*  11 */   private int parent_id = 0;
/*  12 */   private String tree_position = "";
/*  13 */   private int template_index = 0;
/*  14 */   private int template_list = 0;
/*  15 */   private int is_mutilpage = 0;
/*  16 */   private String jump_url = "";
/*  17 */   private String cat_keywords = "";
/*  18 */   private String cat_description = "";
/*  19 */   private String cat_memo = "";
/*  20 */   private int cat_sort = 999;
/*  21 */   private String c_sql = "";
/*     */ 
/*  23 */   public int getCata_id() { return this.cata_id; }
/*     */ 
/*     */   public void setCata_id(int cataId) {
/*  26 */     this.cata_id = cataId;
/*     */   }
/*     */   public int getId() {
/*  29 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  32 */     this.id = id;
/*     */   }
/*     */   public String getCata_cname() {
/*  35 */     return this.cata_cname;
/*     */   }
/*     */   public void setCata_cname(String cataCname) {
/*  38 */     this.cata_cname = cataCname;
/*     */   }
/*     */   public int getParent_id() {
/*  41 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parentId) {
/*  44 */     this.parent_id = parentId;
/*     */   }
/*     */   public String getTree_position() {
/*  47 */     return this.tree_position;
/*     */   }
/*     */   public void setTree_position(String treePosition) {
/*  50 */     this.tree_position = treePosition;
/*     */   }
/*     */   public int getTemplate_index() {
/*  53 */     return this.template_index;
/*     */   }
/*     */   public void setTemplate_index(int templateIndex) {
/*  56 */     this.template_index = templateIndex;
/*     */   }
/*     */   public int getTemplate_list() {
/*  59 */     return this.template_list;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/*  62 */     this.template_list = templateList;
/*     */   }
/*     */   public int getIs_mutilpage() {
/*  65 */     return this.is_mutilpage;
/*     */   }
/*     */   public void setIs_mutilpage(int isMutilpage) {
/*  68 */     this.is_mutilpage = isMutilpage;
/*     */   }
/*     */   public String getJump_url() {
/*  71 */     return this.jump_url;
/*     */   }
/*     */   public void setJump_url(String jumpUrl) {
/*  74 */     this.jump_url = jumpUrl;
/*     */   }
/*     */   public String getCat_keywords() {
/*  77 */     return this.cat_keywords;
/*     */   }
/*     */   public void setCat_keywords(String catKeywords) {
/*  80 */     this.cat_keywords = catKeywords;
/*     */   }
/*     */   public String getCat_description() {
/*  83 */     return this.cat_description;
/*     */   }
/*     */   public void setCat_description(String catDescription) {
/*  86 */     this.cat_description = catDescription;
/*     */   }
/*     */   public String getCat_memo() {
/*  89 */     return this.cat_memo;
/*     */   }
/*     */   public void setCat_memo(String catMemo) {
/*  92 */     this.cat_memo = catMemo;
/*     */   }
/*     */   public int getCat_sort() {
/*  95 */     return this.cat_sort;
/*     */   }
/*     */   public void setCat_sort(int catSort) {
/*  98 */     this.cat_sort = catSort;
/*     */   }
/*     */   public String getC_sql() {
/* 101 */     return this.c_sql;
/*     */   }
/*     */   public void setC_sql(String cSql) {
/* 104 */     this.c_sql = cSql;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean
 * JD-Core Version:    0.6.2
 */