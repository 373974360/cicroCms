/*     */ package com.cicro.wcm.bean.sendInfo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SendRecordBean
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = -43736106645308592L;
/*   9 */   private int id = 0;
/*  10 */   private int send_info_id = 0;
/*  11 */   private int send_cat_id = 0;
/*  12 */   private int send_model_id = 0;
/*  13 */   private String title = "";
/*  14 */   private String send_site_id = "";
/*  15 */   private String send_app_id = "";
/*  16 */   private String send_time = "";
/*  17 */   private int send_user_id = 0;
/*  18 */   private String t_site_id = "";
/*  19 */   private String t_site_name = "";
/*  20 */   private int t_cat_id = 0;
/*  21 */   private String t_cat_cname = "";
/*  22 */   private int adopt_status = 0;
/*  23 */   private String adopt_dtime = "";
/*  24 */   private String adopt_desc = "";
/*     */ 
/*     */   public SendRecordBean clone() {
/*  27 */     SendRecordBean o = null;
/*     */     try {
/*  29 */       o = (SendRecordBean)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/*  31 */       e.printStackTrace();
/*     */     }
/*  33 */     return o;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/*  37 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  41 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public int getSend_model_id() {
/*  45 */     return this.send_model_id;
/*     */   }
/*     */   public void setSend_model_id(int sendModelId) {
/*  48 */     this.send_model_id = sendModelId;
/*     */   }
/*     */   public int getId() {
/*  51 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  54 */     this.id = id;
/*     */   }
/*     */   public int getSend_info_id() {
/*  57 */     return this.send_info_id;
/*     */   }
/*     */   public void setSend_info_id(int sendInfoId) {
/*  60 */     this.send_info_id = sendInfoId;
/*     */   }
/*     */   public int getSend_cat_id() {
/*  63 */     return this.send_cat_id;
/*     */   }
/*     */   public void setSend_cat_id(int sendCatId) {
/*  66 */     this.send_cat_id = sendCatId;
/*     */   }
/*     */   public String getSend_site_id() {
/*  69 */     return this.send_site_id;
/*     */   }
/*     */   public void setSend_site_id(String sendSiteId) {
/*  72 */     this.send_site_id = sendSiteId;
/*     */   }
/*     */   public String getSend_app_id() {
/*  75 */     return this.send_app_id;
/*     */   }
/*     */   public void setSend_app_id(String sendAppId) {
/*  78 */     this.send_app_id = sendAppId;
/*     */   }
/*     */   public String getSend_time() {
/*  81 */     return this.send_time;
/*     */   }
/*     */   public void setSend_time(String sendTime) {
/*  84 */     this.send_time = sendTime;
/*     */   }
/*     */   public int getSend_user_id() {
/*  87 */     return this.send_user_id;
/*     */   }
/*     */   public void setSend_user_id(int sendUserId) {
/*  90 */     this.send_user_id = sendUserId;
/*     */   }
/*     */   public String getT_site_id() {
/*  93 */     return this.t_site_id;
/*     */   }
/*     */   public void setT_site_id(String tSiteId) {
/*  96 */     this.t_site_id = tSiteId;
/*     */   }
/*     */   public String getT_site_name() {
/*  99 */     return this.t_site_name;
/*     */   }
/*     */   public void setT_site_name(String tSiteName) {
/* 102 */     this.t_site_name = tSiteName;
/*     */   }
/*     */   public int getT_cat_id() {
/* 105 */     return this.t_cat_id;
/*     */   }
/*     */   public void setT_cat_id(int tCatId) {
/* 108 */     this.t_cat_id = tCatId;
/*     */   }
/*     */   public String getT_cat_cname() {
/* 111 */     return this.t_cat_cname;
/*     */   }
/*     */   public void setT_cat_cname(String tCatCname) {
/* 114 */     this.t_cat_cname = tCatCname;
/*     */   }
/*     */   public int getAdopt_status() {
/* 117 */     return this.adopt_status;
/*     */   }
/*     */   public void setAdopt_status(int adoptStatus) {
/* 120 */     this.adopt_status = adoptStatus;
/*     */   }
/*     */   public String getAdopt_dtime() {
/* 123 */     return this.adopt_dtime;
/*     */   }
/*     */   public void setAdopt_dtime(String adoptDtime) {
/* 126 */     this.adopt_dtime = adoptDtime;
/*     */   }
/*     */   public String getAdopt_desc() {
/* 129 */     return this.adopt_desc;
/*     */   }
/*     */   public void setAdopt_desc(String adoptDesc) {
/* 132 */     this.adopt_desc = adoptDesc;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.SendRecordBean
 * JD-Core Version:    0.6.2
 */