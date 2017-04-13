/*     */ package com.cicro.wcm.bean.system.comment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CommentBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7728368101821151808L;
/*  12 */   private String cmt_id = "";
/*  13 */   private String item_id = "";
/*  14 */   private String parent_id = "";
/*  15 */   private String cmt_content = "";
/*  16 */   private String me_id = "";
/*  17 */   private String me_nickname = "";
/*  18 */   private String add_dtime = "";
/*  19 */   private String cmt_ip = "";
/*  20 */   private int support_num = 0;
/*  21 */   private int cmt_status = 0;
/*  22 */   private int is_deleted = 0;
/*  23 */   private String app_id = "";
/*  24 */   private String site_id = "";
/*  25 */   private String rele_title = "";
/*  26 */   private int model_id = 0;
/*  27 */   private int dept_id = 0;
/*  28 */   private String ip_addr = "";
/*     */ 
/*     */   public int getModel_id() {
/*  31 */     return this.model_id;
/*     */   }
/*     */   public void setModel_id(int modelId) {
/*  34 */     this.model_id = modelId;
/*     */   }
/*     */   public int getDept_id() {
/*  37 */     return this.dept_id;
/*     */   }
/*     */   public void setDept_id(int deptId) {
/*  40 */     this.dept_id = deptId;
/*     */   }
/*     */   public String getIp_addr() {
/*  43 */     return this.ip_addr;
/*     */   }
/*     */   public void setIp_addr(String ipAddr) {
/*  46 */     this.ip_addr = ipAddr;
/*     */   }
/*     */   public String getRele_title() {
/*  49 */     return this.rele_title;
/*     */   }
/*     */   public void setRele_title(String releTitle) {
/*  52 */     this.rele_title = releTitle;
/*     */   }
/*     */   public String getCmt_id() {
/*  55 */     return this.cmt_id;
/*     */   }
/*     */   public String getItem_id() {
/*  58 */     return this.item_id;
/*     */   }
/*     */   public String getParent_id() {
/*  61 */     return this.parent_id;
/*     */   }
/*     */   public String getCmt_content() {
/*  64 */     return this.cmt_content;
/*     */   }
/*     */   public String getMe_id() {
/*  67 */     return this.me_id;
/*     */   }
/*     */   public String getMe_nickname() {
/*  70 */     return this.me_nickname;
/*     */   }
/*     */   public String getAdd_dtime() {
/*  73 */     return this.add_dtime;
/*     */   }
/*     */   public String getCmt_ip() {
/*  76 */     return this.cmt_ip;
/*     */   }
/*     */   public int getSupport_num() {
/*  79 */     return this.support_num;
/*     */   }
/*     */   public int getCmt_status() {
/*  82 */     return this.cmt_status;
/*     */   }
/*     */   public int getIs_deleted() {
/*  85 */     return this.is_deleted;
/*     */   }
/*     */   public String getApp_id() {
/*  88 */     return this.app_id;
/*     */   }
/*     */   public String getSite_id() {
/*  91 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setCmt_id(String cmtId)
/*     */   {
/*  96 */     this.cmt_id = cmtId;
/*     */   }
/*     */   public void setItem_id(String itemId) {
/*  99 */     this.item_id = itemId;
/*     */   }
/*     */   public void setParent_id(String parentId) {
/* 102 */     this.parent_id = parentId;
/*     */   }
/*     */   public void setCmt_content(String cmtContent) {
/* 105 */     this.cmt_content = cmtContent;
/*     */   }
/*     */   public void setMe_id(String meId) {
/* 108 */     this.me_id = meId;
/*     */   }
/*     */   public void setMe_nickname(String meNickname) {
/* 111 */     this.me_nickname = meNickname;
/*     */   }
/*     */   public void setAdd_dtime(String addDtime) {
/* 114 */     this.add_dtime = addDtime;
/*     */   }
/*     */   public void setCmt_ip(String cmtIp) {
/* 117 */     this.cmt_ip = cmtIp;
/*     */   }
/*     */   public void setSupport_num(int supportNum) {
/* 120 */     this.support_num = supportNum;
/*     */   }
/*     */   public void setCmt_status(int cmtStatus) {
/* 123 */     this.cmt_status = cmtStatus;
/*     */   }
/*     */   public void setIs_deleted(int isDeleted) {
/* 126 */     this.is_deleted = isDeleted;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 129 */     this.app_id = appId;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 132 */     this.site_id = siteId;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.comment.CommentBean
 * JD-Core Version:    0.6.2
 */