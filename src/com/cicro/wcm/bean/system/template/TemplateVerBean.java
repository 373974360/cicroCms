/*     */ package com.cicro.wcm.bean.system.template;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class TemplateVerBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int id;
/*     */   private int t_id;
/*     */   private int tcat_id;
/*     */   private String t_ename;
/*     */   private String t_cname;
/*     */   private String t_path;
/*     */   private String t_content;
/*     */   private int t_ver;
/*     */   private int creat_user;
/*     */   private String creat_dtime;
/*     */   private int modify_user;
/*     */   private String modify_dtime;
/*     */   private String app_id;
/*     */   private String site_id;
/*  22 */   private int t_status = 0;
/*     */ 
/*     */   public int getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  28 */     this.id = id;
/*     */   }
/*     */   public int getT_id() {
/*  31 */     return this.t_id;
/*     */   }
/*     */   public int getTcat_id() {
/*  34 */     return this.tcat_id;
/*     */   }
/*     */   public String getT_ename() {
/*  37 */     return this.t_ename;
/*     */   }
/*     */   public String getT_cname() {
/*  40 */     return this.t_cname;
/*     */   }
/*     */   public String getT_path() {
/*  43 */     return this.t_path;
/*     */   }
/*     */   public String getT_content() {
/*  46 */     return this.t_content;
/*     */   }
/*     */   public int getT_ver() {
/*  49 */     return this.t_ver;
/*     */   }
/*     */   public int getCreat_user() {
/*  52 */     return this.creat_user;
/*     */   }
/*     */   public String getCreat_dtime() {
/*  55 */     return this.creat_dtime;
/*     */   }
/*     */   public int getModify_user() {
/*  58 */     return this.modify_user;
/*     */   }
/*     */   public String getModify_dtime() {
/*  61 */     return this.modify_dtime;
/*     */   }
/*     */   public String getApp_id() {
/*  64 */     return this.app_id;
/*     */   }
/*     */   public String getSite_id() {
/*  67 */     return this.site_id;
/*     */   }
/*     */   public void setT_id(int tId) {
/*  70 */     this.t_id = tId;
/*     */   }
/*     */   public void setTcat_id(int tcatId) {
/*  73 */     this.tcat_id = tcatId;
/*     */   }
/*     */   public void setT_ename(String tEname) {
/*  76 */     if (tEname == null) {
/*  77 */       tEname = " ";
/*     */     }
/*  79 */     this.t_ename = tEname;
/*     */   }
/*     */   public void setT_cname(String tCname) {
/*  82 */     if (tCname == null) {
/*  83 */       tCname = " ";
/*     */     }
/*  85 */     this.t_cname = tCname;
/*     */   }
/*     */   public void setT_path(String tPath) {
/*  88 */     if (tPath == null) {
/*  89 */       tPath = " ";
/*     */     }
/*  91 */     this.t_path = tPath;
/*     */   }
/*     */   public void setT_content(String tContent) {
/*  94 */     if (tContent == null) {
/*  95 */       tContent = " ";
/*     */     }
/*  97 */     this.t_content = tContent;
/*     */   }
/*     */   public void setT_ver(int tVer) {
/* 100 */     this.t_ver = tVer;
/*     */   }
/*     */   public void setCreat_user(int creatUser) {
/* 103 */     this.creat_user = creatUser;
/*     */   }
/*     */   public void setCreat_dtime(String creatDtime) {
/* 106 */     if (creatDtime == null) {
/* 107 */       creatDtime = " ";
/*     */     }
/* 109 */     this.creat_dtime = creatDtime;
/*     */   }
/*     */   public void setModify_user(int modifyUser) {
/* 112 */     this.modify_user = modifyUser;
/*     */   }
/*     */   public void setModify_dtime(String modifyDtime) {
/* 115 */     if (modifyDtime == null) {
/* 116 */       modifyDtime = " ";
/*     */     }
/* 118 */     this.modify_dtime = modifyDtime;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 121 */     this.app_id = appId;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 124 */     this.site_id = siteId;
/*     */   }
/*     */   public int getT_status() {
/* 127 */     return this.t_status;
/*     */   }
/*     */   public void setT_status(int tStatus) {
/* 130 */     this.t_status = tStatus;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.template.TemplateVerBean
 * JD-Core Version:    0.6.2
 */