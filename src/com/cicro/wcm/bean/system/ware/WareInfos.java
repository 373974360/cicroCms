/*     */ package com.cicro.wcm.bean.system.ware;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class WareInfos
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6336369043896828862L;
/*  11 */   private int id = 0;
/*  12 */   private int ware_id = 0;
/*  13 */   private int winfo_id = 0;
/*  14 */   private String title = "";
/*  15 */   private String subtitle = "";
/*  16 */   private String title_color = "";
/*  17 */   private String description = "";
/*  18 */   private String thumb_url = "";
/*  19 */   private String content_url = "";
/*  20 */   private int sort_id = 999;
/*  21 */   private String publish_dtime = "";
/*  22 */   private String app_id = "";
/*  23 */   private String site_id = "";
/*  24 */   protected String pre_title = "";
/*     */ 
/*     */   public String getPre_title() {
/*  27 */     return this.pre_title;
/*     */   }
/*     */   public void setPre_title(String preTitle) {
/*  30 */     this.pre_title = preTitle;
/*     */   }
/*     */   public int getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   public int getWare_id() {
/*  39 */     return this.ware_id;
/*     */   }
/*     */   public void setWare_id(int wareId) {
/*  42 */     this.ware_id = wareId;
/*     */   }
/*     */   public int getWinfo_id() {
/*  45 */     return this.winfo_id;
/*     */   }
/*     */   public void setWinfo_id(int winfoId) {
/*  48 */     this.winfo_id = winfoId;
/*     */   }
/*     */   public String getTitle() {
/*  51 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/*  54 */     this.title = title;
/*     */   }
/*     */   public String getSubtitle() {
/*  57 */     return this.subtitle;
/*     */   }
/*     */   public void setSubtitle(String subtitle) {
/*  60 */     this.subtitle = subtitle;
/*     */   }
/*     */   public String getTitle_color() {
/*  63 */     return this.title_color;
/*     */   }
/*     */   public void setTitle_color(String titleColor) {
/*  66 */     this.title_color = titleColor;
/*     */   }
/*     */   public String getDescription() {
/*  69 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  72 */     this.description = description;
/*     */   }
/*     */   public String getThumb_url() {
/*  75 */     return this.thumb_url;
/*     */   }
/*     */   public void setThumb_url(String thumbUrl) {
/*  78 */     this.thumb_url = thumbUrl;
/*     */   }
/*     */   public String getContent_url() {
/*  81 */     return this.content_url;
/*     */   }
/*     */   public void setContent_url(String contentUrl) {
/*  84 */     this.content_url = contentUrl;
/*     */   }
/*     */   public int getSort_id() {
/*  87 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/*  90 */     this.sort_id = sortId;
/*     */   }
/*     */   public String getPublish_dtime() {
/*  93 */     return this.publish_dtime;
/*     */   }
/*     */   public void setPublish_dtime(String publishDtime) {
/*  96 */     this.publish_dtime = publishDtime;
/*     */   }
/*     */   public String getApp_id() {
/*  99 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 102 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 105 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 108 */     this.site_id = siteId;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareInfos
 * JD-Core Version:    0.6.2
 */