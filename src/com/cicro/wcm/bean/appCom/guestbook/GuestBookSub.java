/*     */ package com.cicro.wcm.bean.appCom.guestbook;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GuestBookSub
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6542177255255376615L;
/*  11 */   private int id = 0;
/*  12 */   private int gbs_id = 0;
/*  13 */   private int cat_id = 0;
/*  14 */   private String title = "";
/*  15 */   private int publish_status = 0;
/*  16 */   private String publish_time = "";
/*  17 */   private int direct_publish = 0;
/*  18 */   private int is_member = 0;
/*  19 */   private int is_receive_show = 0;
/*  20 */   private int is_auth_code = 0;
/*  21 */   private int template_index = 0;
/*  22 */   private int template_list = 0;
/*  23 */   private int template_form = 0;
/*  24 */   private int template_content = 0;
/*  25 */   private String site_id = "";
/*  26 */   private String description = "";
/*  27 */   private String remark = "";
/*  28 */   private String add_time = "";
/*     */ 
/*  30 */   public String getAdd_time() { return this.add_time; }
/*     */ 
/*     */   public void setAdd_time(String addTime) {
/*  33 */     this.add_time = addTime;
/*     */   }
/*     */   public int getId() {
/*  36 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  39 */     this.id = id;
/*     */   }
/*     */   public int getGbs_id() {
/*  42 */     return this.gbs_id;
/*     */   }
/*     */   public void setGbs_id(int gbsId) {
/*  45 */     this.gbs_id = gbsId;
/*     */   }
/*     */   public int getCat_id() {
/*  48 */     return this.cat_id;
/*     */   }
/*     */   public void setCat_id(int catId) {
/*  51 */     this.cat_id = catId;
/*     */   }
/*     */   public String getTitle() {
/*  54 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/*  57 */     this.title = title;
/*     */   }
/*     */   public int getPublish_status() {
/*  60 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publishStatus) {
/*  63 */     this.publish_status = publishStatus;
/*     */   }
/*     */   public String getPublish_time() {
/*  66 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publishTime) {
/*  69 */     this.publish_time = publishTime;
/*     */   }
/*     */   public int getDirect_publish() {
/*  72 */     return this.direct_publish;
/*     */   }
/*     */   public void setDirect_publish(int directPublish) {
/*  75 */     this.direct_publish = directPublish;
/*     */   }
/*     */   public int getIs_member() {
/*  78 */     return this.is_member;
/*     */   }
/*     */   public void setIs_member(int isMember) {
/*  81 */     this.is_member = isMember;
/*     */   }
/*     */   public int getIs_receive_show() {
/*  84 */     return this.is_receive_show;
/*     */   }
/*     */   public void setIs_receive_show(int isReceiveShow) {
/*  87 */     this.is_receive_show = isReceiveShow;
/*     */   }
/*     */   public int getIs_auth_code() {
/*  90 */     return this.is_auth_code;
/*     */   }
/*     */   public void setIs_auth_code(int isAuthCode) {
/*  93 */     this.is_auth_code = isAuthCode;
/*     */   }
/*     */   public int getTemplate_index() {
/*  96 */     return this.template_index;
/*     */   }
/*     */   public void setTemplate_index(int templateIndex) {
/*  99 */     this.template_index = templateIndex;
/*     */   }
/*     */   public int getTemplate_list() {
/* 102 */     return this.template_list;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/* 105 */     this.template_list = templateList;
/*     */   }
/*     */   public int getTemplate_form() {
/* 108 */     return this.template_form;
/*     */   }
/*     */   public void setTemplate_form(int templateForm) {
/* 111 */     this.template_form = templateForm;
/*     */   }
/*     */   public int getTemplate_content() {
/* 114 */     return this.template_content;
/*     */   }
/*     */   public void setTemplate_content(int templateContent) {
/* 117 */     this.template_content = templateContent;
/*     */   }
/*     */   public String getSite_id() {
/* 120 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 123 */     this.site_id = siteId;
/*     */   }
/*     */   public String getDescription() {
/* 126 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 129 */     this.description = description;
/*     */   }
/*     */   public String getRemark() {
/* 132 */     return this.remark;
/*     */   }
/*     */   public void setRemark(String remark) {
/* 135 */     this.remark = remark;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appCom.guestbook.GuestBookSub
 * JD-Core Version:    0.6.2
 */