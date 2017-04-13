/*     */ package com.cicro.wcm.bean.system.ware;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class WareBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3072423803402743093L;
/*  12 */   private String id = "";
/*  13 */   private String ware_id = "";
/*  14 */   private String wcat_id = "";
/*  15 */   private String ware_name = "";
/*  16 */   private String rss_url = "";
/*  17 */   private String ware_content = "";
/*  18 */   private int ware_type = 0;
/*     */   private int ware_ver;
/*  20 */   private int info_num = 0;
/*  21 */   private int ware_width = 0;
/*  22 */   private int ware_interval = 0;
/*  23 */   private String ware_url = "";
/*  24 */   private String ware_memo = "";
/*  25 */   private String update_dtime = "";
/*  26 */   private String next_dtime = "";
/*  27 */   private int sort_id = 999;
/*  28 */   private String app_id = "";
/*  29 */   private String site_id = "";
/*  30 */   private int receive_recom = 0;
/*     */ 
/*     */   public String getId() {
/*  33 */     return this.id;
/*     */   }
/*     */   public void setId(String id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   public String getWare_id() {
/*  39 */     return this.ware_id;
/*     */   }
/*     */   public void setWare_id(String wareId) {
/*  42 */     this.ware_id = wareId;
/*     */   }
/*     */   public String getWcat_id() {
/*  45 */     return this.wcat_id;
/*     */   }
/*     */   public void setWcat_id(String wcatId) {
/*  48 */     this.wcat_id = wcatId;
/*     */   }
/*     */   public String getWare_name() {
/*  51 */     return this.ware_name;
/*     */   }
/*     */   public void setWare_name(String wareName) {
/*  54 */     this.ware_name = wareName;
/*     */   }
/*     */   public String getRss_url() {
/*  57 */     return this.rss_url;
/*     */   }
/*     */   public void setRss_url(String rssUrl) {
/*  60 */     this.rss_url = rssUrl;
/*     */   }
/*     */   public String getWare_content() {
/*  63 */     return this.ware_content;
/*     */   }
/*     */   public void setWare_content(String wareContent) {
/*  66 */     this.ware_content = wareContent;
/*     */   }
/*     */   public int getWare_type() {
/*  69 */     return this.ware_type;
/*     */   }
/*     */   public void setWare_type(int wareType) {
/*  72 */     this.ware_type = wareType;
/*     */   }
/*     */   public int getWare_ver() {
/*  75 */     return this.ware_ver;
/*     */   }
/*     */   public void setWare_ver(int wareVer) {
/*  78 */     this.ware_ver = wareVer;
/*     */   }
/*     */   public int getInfo_num() {
/*  81 */     return this.info_num;
/*     */   }
/*     */   public void setInfo_num(int infoNum) {
/*  84 */     this.info_num = infoNum;
/*     */   }
/*     */   public int getWare_width() {
/*  87 */     return this.ware_width;
/*     */   }
/*     */   public void setWare_width(int wareWidth) {
/*  90 */     this.ware_width = wareWidth;
/*     */   }
/*     */   public int getWare_interval() {
/*  93 */     return this.ware_interval;
/*     */   }
/*     */   public void setWare_interval(int wareInterval) {
/*  96 */     this.ware_interval = wareInterval;
/*     */   }
/*     */   public String getWare_url() {
/*  99 */     return this.ware_url;
/*     */   }
/*     */   public void setWare_url(String wareUrl) {
/* 102 */     this.ware_url = wareUrl;
/*     */   }
/*     */   public String getWare_memo() {
/* 105 */     return this.ware_memo;
/*     */   }
/*     */   public void setWare_memo(String wareMemo) {
/* 108 */     this.ware_memo = wareMemo;
/*     */   }
/*     */   public String getUpdate_dtime() {
/* 111 */     return this.update_dtime;
/*     */   }
/*     */   public void setUpdate_dtime(String updateDtime) {
/* 114 */     this.update_dtime = updateDtime;
/*     */   }
/*     */   public String getNext_dtime() {
/* 117 */     return this.next_dtime;
/*     */   }
/*     */   public void setNext_dtime(String nextDtime) {
/* 120 */     this.next_dtime = nextDtime;
/*     */   }
/*     */   public int getSort_id() {
/* 123 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/* 126 */     this.sort_id = sortId;
/*     */   }
/*     */   public String getApp_id() {
/* 129 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 132 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 135 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 138 */     this.site_id = siteId;
/*     */   }
/*     */   public int getReceive_recom() {
/* 141 */     return this.receive_recom;
/*     */   }
/*     */   public void setReceive_recom(int receiveRecom) {
/* 144 */     this.receive_recom = receiveRecom;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareBean
 * JD-Core Version:    0.6.2
 */