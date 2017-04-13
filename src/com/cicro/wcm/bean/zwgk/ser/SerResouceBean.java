/*     */ package com.cicro.wcm.bean.zwgk.ser;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SerResouceBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5657511190071930719L;
/*  10 */   private int id = 0;
/*  11 */   private int res_id = 0;
/*  12 */   private int ser_id = 0;
/*  13 */   private String title = "";
/*  14 */   private String content = "";
/*  15 */   private String url = "";
/*  16 */   private int sort_id = 0;
/*  17 */   private int res_status = 0;
/*  18 */   private int publish_status = 0;
/*     */ 
/* 103 */   private String publish_time = "";
/* 104 */   private String add_time = "";
/* 105 */   private String add_user = "";
/* 106 */   private String update_time = "";
/* 107 */   private String update_user = "";
/*     */ 
/*     */   public int getId()
/*     */   {
/*  20 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  23 */     this.id = id;
/*     */   }
/*     */   public int getRes_id() {
/*  26 */     return this.res_id;
/*     */   }
/*     */   public void setRes_id(int resId) {
/*  29 */     this.res_id = resId;
/*     */   }
/*     */   public int getSer_id() {
/*  32 */     return this.ser_id;
/*     */   }
/*     */   public void setSer_id(int serId) {
/*  35 */     this.ser_id = serId;
/*     */   }
/*     */   public String getTitle() {
/*  38 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/*  41 */     this.title = title;
/*     */   }
/*     */   public String getContent() {
/*  44 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/*  47 */     this.content = content;
/*     */   }
/*     */   public String getUrl() {
/*  50 */     return this.url;
/*     */   }
/*     */   public void setUrl(String url) {
/*  53 */     this.url = url;
/*     */   }
/*     */   public int getSort_id() {
/*  56 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/*  59 */     this.sort_id = sortId;
/*     */   }
/*     */   public int getRes_status() {
/*  62 */     return this.res_status;
/*     */   }
/*     */   public void setRes_status(int resStatus) {
/*  65 */     this.res_status = resStatus;
/*     */   }
/*     */   public int getPublish_status() {
/*  68 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publishStatus) {
/*  71 */     this.publish_status = publishStatus;
/*     */   }
/*     */   public String getPublish_time() {
/*  74 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publishTime) {
/*  77 */     this.publish_time = publishTime;
/*     */   }
/*     */   public String getAdd_time() {
/*  80 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String addTime) {
/*  83 */     this.add_time = addTime;
/*     */   }
/*     */   public String getAdd_user() {
/*  86 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String addUser) {
/*  89 */     this.add_user = addUser;
/*     */   }
/*     */   public String getUpdate_time() {
/*  92 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String updateTime) {
/*  95 */     this.update_time = updateTime;
/*     */   }
/*     */   public String getUpdate_user() {
/*  98 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String updateUser) {
/* 101 */     this.update_user = updateUser;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.ser.SerResouceBean
 * JD-Core Version:    0.6.2
 */