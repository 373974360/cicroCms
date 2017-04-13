/*     */ package com.cicro.wcm.bean.appCom.guestbook;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GuestBookCategory
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 648042059446308929L;
/*  11 */   private int id = 0;
/*  12 */   private int cat_id = 0;
/*  13 */   private String title = "";
/*  14 */   private int publish_status = 0;
/*  15 */   private int direct_publish = 0;
/*  16 */   private int is_member = 0;
/*  17 */   private int is_receive_show = 0;
/*  18 */   private int is_auth_code = 0;
/*  19 */   private int sort_id = 999;
/*  20 */   private int template_index = 0;
/*  21 */   private int template_list = 0;
/*  22 */   private int template_form = 0;
/*  23 */   private int template_content = 0;
/*  24 */   private String site_id = "";
/*  25 */   private String description = "";
/*     */ 
/*     */   public int getTemplate_content() {
/*  28 */     return this.template_content;
/*     */   }
/*     */ 
/*     */   public void setTemplate_content(int templateContent) {
/*  32 */     this.template_content = templateContent;
/*     */   }
/*     */ 
/*     */   public GuestBookCategory clone() {
/*  36 */     GuestBookCategory o = null;
/*     */     try {
/*  38 */       o = (GuestBookCategory)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/*  40 */       e.printStackTrace();
/*     */     }
/*  42 */     return o;
/*     */   }
/*     */ 
/*     */   public int getId() {
/*  46 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  49 */     this.id = id;
/*     */   }
/*     */   public int getCat_id() {
/*  52 */     return this.cat_id;
/*     */   }
/*     */   public void setCat_id(int catId) {
/*  55 */     this.cat_id = catId;
/*     */   }
/*     */   public String getTitle() {
/*  58 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/*  61 */     this.title = title;
/*     */   }
/*     */   public int getPublish_status() {
/*  64 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publishStatus) {
/*  67 */     this.publish_status = publishStatus;
/*     */   }
/*     */   public int getDirect_publish() {
/*  70 */     return this.direct_publish;
/*     */   }
/*     */   public void setDirect_publish(int directPublish) {
/*  73 */     this.direct_publish = directPublish;
/*     */   }
/*     */   public int getIs_member() {
/*  76 */     return this.is_member;
/*     */   }
/*     */   public void setIs_member(int isMember) {
/*  79 */     this.is_member = isMember;
/*     */   }
/*     */   public int getIs_receive_show() {
/*  82 */     return this.is_receive_show;
/*     */   }
/*     */   public void setIs_receive_show(int isReceiveShow) {
/*  85 */     this.is_receive_show = isReceiveShow;
/*     */   }
/*     */   public int getIs_auth_code() {
/*  88 */     return this.is_auth_code;
/*     */   }
/*     */   public void setIs_auth_code(int isAuthCode) {
/*  91 */     this.is_auth_code = isAuthCode;
/*     */   }
/*     */   public int getSort_id() {
/*  94 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/*  97 */     this.sort_id = sortId;
/*     */   }
/*     */   public int getTemplate_index() {
/* 100 */     return this.template_index;
/*     */   }
/*     */   public void setTemplate_index(int templateIndex) {
/* 103 */     this.template_index = templateIndex;
/*     */   }
/*     */   public int getTemplate_list() {
/* 106 */     return this.template_list;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/* 109 */     this.template_list = templateList;
/*     */   }
/*     */   public int getTemplate_form() {
/* 112 */     return this.template_form;
/*     */   }
/*     */   public void setTemplate_form(int templateForm) {
/* 115 */     this.template_form = templateForm;
/*     */   }
/*     */   public String getSite_id() {
/* 118 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 121 */     this.site_id = siteId;
/*     */   }
/*     */   public String getDescription() {
/* 124 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 127 */     this.description = description;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory
 * JD-Core Version:    0.6.2
 */