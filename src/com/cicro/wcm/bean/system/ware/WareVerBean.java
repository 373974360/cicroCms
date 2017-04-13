/*     */ package com.cicro.wcm.bean.system.ware;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class WareVerBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*   8 */   private String id = "";
/*   9 */   private String ware_id = "";
/*  10 */   private String wcat_id = "";
/*  11 */   private String ware_name = "";
/*  12 */   private String rss_url = "";
/*  13 */   private String ware_content = "";
/*  14 */   private int ware_type = 0;
/*     */   private int ware_ver;
/*  16 */   private int info_num = 0;
/*  17 */   private int ware_width = 0;
/*  18 */   private int ware_interval = 0;
/*  19 */   private String ware_url = "";
/*  20 */   private String ware_memo = "";
/*  21 */   private String update_dtime = "";
/*  22 */   private String next_dtime = "";
/*  23 */   private String creat_dtime = "";
/*  24 */   private int sort_id = 999;
/*  25 */   private String app_id = "";
/*  26 */   private String site_id = "";
/*  27 */   private int receive_recom = 0;
/*     */ 
/*     */   public String getId() {
/*  30 */     return this.id;
/*     */   }
/*     */   public void setId(String id) {
/*  33 */     this.id = id;
/*     */   }
/*     */   public String getWare_id() {
/*  36 */     return this.ware_id;
/*     */   }
/*     */   public void setWare_id(String wareId) {
/*  39 */     this.ware_id = wareId;
/*     */   }
/*     */   public String getWcat_id() {
/*  42 */     return this.wcat_id;
/*     */   }
/*     */   public void setWcat_id(String wcatId) {
/*  45 */     this.wcat_id = wcatId;
/*     */   }
/*     */   public String getWare_name() {
/*  48 */     return this.ware_name;
/*     */   }
/*     */   public void setWare_name(String wareName) {
/*  51 */     this.ware_name = wareName;
/*     */   }
/*     */   public String getRss_url() {
/*  54 */     return this.rss_url;
/*     */   }
/*     */   public void setRss_url(String rssUrl) {
/*  57 */     this.rss_url = rssUrl;
/*     */   }
/*     */   public String getWare_content() {
/*  60 */     return this.ware_content;
/*     */   }
/*     */   public void setWare_content(String wareContent) {
/*  63 */     this.ware_content = wareContent;
/*     */   }
/*     */   public int getWare_type() {
/*  66 */     return this.ware_type;
/*     */   }
/*     */   public void setWare_type(int wareType) {
/*  69 */     this.ware_type = wareType;
/*     */   }
/*     */   public int getWare_ver() {
/*  72 */     return this.ware_ver;
/*     */   }
/*     */   public void setWare_ver(int wareVer) {
/*  75 */     this.ware_ver = wareVer;
/*     */   }
/*     */   public int getInfo_num() {
/*  78 */     return this.info_num;
/*     */   }
/*     */   public void setInfo_num(int infoNum) {
/*  81 */     this.info_num = infoNum;
/*     */   }
/*     */   public int getWare_width() {
/*  84 */     return this.ware_width;
/*     */   }
/*     */   public void setWare_width(int wareWidth) {
/*  87 */     this.ware_width = wareWidth;
/*     */   }
/*     */   public int getWare_interval() {
/*  90 */     return this.ware_interval;
/*     */   }
/*     */   public void setWare_interval(int wareInterval) {
/*  93 */     this.ware_interval = wareInterval;
/*     */   }
/*     */   public String getWare_url() {
/*  96 */     return this.ware_url;
/*     */   }
/*     */   public void setWare_url(String wareUrl) {
/*  99 */     this.ware_url = wareUrl;
/*     */   }
/*     */   public String getWare_memo() {
/* 102 */     return this.ware_memo;
/*     */   }
/*     */   public void setWare_memo(String wareMemo) {
/* 105 */     this.ware_memo = wareMemo;
/*     */   }
/*     */   public String getUpdate_dtime() {
/* 108 */     return this.update_dtime;
/*     */   }
/*     */   public void setUpdate_dtime(String updateDtime) {
/* 111 */     this.update_dtime = updateDtime;
/*     */   }
/*     */   public String getNext_dtime() {
/* 114 */     return this.next_dtime;
/*     */   }
/*     */   public void setNext_dtime(String nextDtime) {
/* 117 */     this.next_dtime = nextDtime;
/*     */   }
/*     */   public int getSort_id() {
/* 120 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/* 123 */     this.sort_id = sortId;
/*     */   }
/*     */   public String getApp_id() {
/* 126 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 129 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 132 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 135 */     this.site_id = siteId;
/*     */   }
/*     */   public int getReceive_recom() {
/* 138 */     return this.receive_recom;
/*     */   }
/*     */   public void setReceive_recom(int receiveRecom) {
/* 141 */     this.receive_recom = receiveRecom;
/*     */   }
/*     */   public String getCreat_dtime() {
/* 144 */     return this.creat_dtime;
/*     */   }
/*     */   public void setCreat_dtime(String creatDtime) {
/* 147 */     this.creat_dtime = creatDtime;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareVerBean
 * JD-Core Version:    0.6.2
 */