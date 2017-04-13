/*     */ package com.cicro.wcm.services.extendfunction.infoRelatedKcat;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class InfoRelatedKcatBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9191090866336252325L;
/*  12 */   private int id = 0;
/*  13 */   private int kcat_id = 0;
/*  14 */   private String info_id = "";
/*  15 */   private String site_id = "";
/*  16 */   private String app_id = "";
/*  17 */   private String add_time = "";
/*  18 */   private String kcat_name = "";
/*     */ 
/*  20 */   private String title = "";
/*  21 */   private String linkUrl = "";
/*  22 */   private String info_time = "";
/*  23 */   private String source = "";
/*  24 */   private int p_kcat_id = 0;
/*  25 */   private String p_kcat_name = "";
/*     */ 
/*  27 */   public int getId() { return this.id; }
/*     */ 
/*     */   public void setId(int id) {
/*  30 */     this.id = id;
/*     */   }
/*     */   public int getKcat_id() {
/*  33 */     return this.kcat_id;
/*     */   }
/*     */   public void setKcat_id(int kcatId) {
/*  36 */     this.kcat_id = kcatId;
/*     */   }
/*     */   public String getInfo_id() {
/*  39 */     return this.info_id;
/*     */   }
/*     */   public void setInfo_id(String infoId) {
/*  42 */     this.info_id = infoId;
/*     */   }
/*     */   public String getSite_id() {
/*  45 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/*  48 */     this.site_id = siteId;
/*     */   }
/*     */   public String getApp_id() {
/*  51 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/*  54 */     this.app_id = appId;
/*     */   }
/*     */   public String getAdd_time() {
/*  57 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String addTime) {
/*  60 */     this.add_time = addTime;
/*     */   }
/*     */   public String getKcat_name() {
/*  63 */     return this.kcat_name;
/*     */   }
/*     */   public void setKcat_name(String kcatName) {
/*  66 */     this.kcat_name = kcatName;
/*     */   }
/*     */   public String getTitle() {
/*  69 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/*  72 */     this.title = title;
/*     */   }
/*     */   public String getLinkUrl() {
/*  75 */     return this.linkUrl;
/*     */   }
/*     */   public void setLinkUrl(String linkUrl) {
/*  78 */     this.linkUrl = linkUrl;
/*     */   }
/*     */   public String getInfo_time() {
/*  81 */     return this.info_time;
/*     */   }
/*     */   public void setInfo_time(String infoTime) {
/*  84 */     this.info_time = infoTime;
/*     */   }
/*     */   public String getSource() {
/*  87 */     return this.source;
/*     */   }
/*     */   public void setSource(String source) {
/*  90 */     this.source = source;
/*     */   }
/*     */   public int getP_kcat_id() {
/*  93 */     return this.p_kcat_id;
/*     */   }
/*     */   public void setP_kcat_id(int pKcatId) {
/*  96 */     this.p_kcat_id = pKcatId;
/*     */   }
/*     */   public String getP_kcat_name() {
/*  99 */     return this.p_kcat_name;
/*     */   }
/*     */   public void setP_kcat_name(String pKcatName) {
/* 102 */     this.p_kcat_name = pKcatName;
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.infoRelatedKcat.InfoRelatedKcatBean
 * JD-Core Version:    0.6.2
 */