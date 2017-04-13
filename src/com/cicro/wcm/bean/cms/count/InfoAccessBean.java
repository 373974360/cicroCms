/*     */ package com.cicro.wcm.bean.cms.count;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class InfoAccessBean
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 5805744790380742181L;
/*     */   private int id;
/*     */   private int info_id;
/*  13 */   private String info_title = "";
/*     */   private int cat_id;
/*  15 */   private String cat_name = "";
/*  16 */   private String app_id = "";
/*  17 */   private String access_time = "";
/*  18 */   private String access_ip = "";
/*  19 */   private String access_url = "";
/*  20 */   private String access_day = "";
/*  21 */   private String access_month = "";
/*  22 */   private String access_year = "";
/*  23 */   private String site_id = "";
/*  24 */   private String site_domain = "";
/*     */ 
/*  26 */   private int icount = 0;
/*     */ 
/*  28 */   private int count = 0;
/*  29 */   private String site_name = "";
/*     */ 
/*     */   public String getCat_name()
/*     */   {
/*  35 */     return this.cat_name;
/*     */   }
/*     */   public void setCat_name(String catName) {
/*  38 */     this.cat_name = catName;
/*     */   }
/*     */   public int getIcount() {
/*  41 */     return this.icount;
/*     */   }
/*     */   public void setIcount(int icount) {
/*  44 */     this.icount = icount;
/*     */   }
/*     */   public int getCount() {
/*  47 */     return this.count;
/*     */   }
/*     */   public String getSite_name() {
/*  50 */     return this.site_name;
/*     */   }
/*     */   public void setCount(int count) {
/*  53 */     this.count = count;
/*     */   }
/*     */   public void setSite_name(String siteName) {
/*  56 */     this.site_name = siteName;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  62 */     return this.id;
/*     */   }
/*     */   public int getInfo_id() {
/*  65 */     return this.info_id;
/*     */   }
/*     */   public String getInfo_title() {
/*  68 */     return this.info_title;
/*     */   }
/*     */   public int getCat_id() {
/*  71 */     return this.cat_id;
/*     */   }
/*     */   public String getApp_id() {
/*  74 */     return this.app_id;
/*     */   }
/*     */   public String getAccess_time() {
/*  77 */     return this.access_time;
/*     */   }
/*     */   public String getAccess_ip() {
/*  80 */     return this.access_ip;
/*     */   }
/*     */   public String getAccess_url() {
/*  83 */     return this.access_url;
/*     */   }
/*     */   public String getAccess_day() {
/*  86 */     return this.access_day;
/*     */   }
/*     */   public String getAccess_month() {
/*  89 */     return this.access_month;
/*     */   }
/*     */   public String getAccess_year() {
/*  92 */     return this.access_year;
/*     */   }
/*     */   public String getSite_id() {
/*  95 */     return this.site_id;
/*     */   }
/*     */   public String getSite_domain() {
/*  98 */     return this.site_domain;
/*     */   }
/*     */   public void setId(int id) {
/* 101 */     this.id = id;
/*     */   }
/*     */   public void setInfo_id(int infoId) {
/* 104 */     this.info_id = infoId;
/*     */   }
/*     */   public void setInfo_title(String infoTitle) {
/* 107 */     this.info_title = infoTitle;
/*     */   }
/*     */   public void setCat_id(int catId) {
/* 110 */     this.cat_id = catId;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 113 */     this.app_id = appId;
/*     */   }
/*     */   public void setAccess_time(String accessTime) {
/* 116 */     this.access_time = accessTime;
/*     */   }
/*     */   public void setAccess_ip(String accessIp) {
/* 119 */     this.access_ip = accessIp;
/*     */   }
/*     */   public void setAccess_url(String accessUrl) {
/* 122 */     this.access_url = accessUrl;
/*     */   }
/*     */   public void setAccess_day(String accessDay) {
/* 125 */     this.access_day = accessDay;
/*     */   }
/*     */   public void setAccess_month(String accessMonth) {
/* 128 */     this.access_month = accessMonth;
/*     */   }
/*     */   public void setAccess_year(String accessYear) {
/* 131 */     this.access_year = accessYear;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 134 */     this.site_id = siteId;
/*     */   }
/*     */   public void setSite_domain(String siteDomain) {
/* 137 */     this.site_domain = siteDomain;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.count.InfoAccessBean
 * JD-Core Version:    0.6.2
 */