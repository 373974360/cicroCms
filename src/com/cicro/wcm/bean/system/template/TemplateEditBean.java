/*     */ package com.cicro.wcm.bean.system.template;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class TemplateEditBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8209912066873021042L;
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
			private int t_status = 0;
/*     */ 
/*     */   public int getId()
/*     */   {
/*  29 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  32 */     this.id = id;
/*     */   }
/*     */   public int getT_id() {
/*  35 */     return this.t_id;
/*     */   }
/*     */   public int getTcat_id() {
/*  38 */     return this.tcat_id;
/*     */   }
/*     */   public String getT_ename() {
/*  41 */     return this.t_ename;
/*     */   }
/*     */   public String getT_cname() {
/*  44 */     return this.t_cname;
/*     */   }
/*     */   public String getT_path() {
/*  47 */     return this.t_path;
/*     */   }
/*     */   public String getT_content() {
/*  50 */     return this.t_content;
/*     */   }
/*     */   public int getT_ver() {
/*  53 */     return this.t_ver;
/*     */   }
/*     */   public int getCreat_user() {
/*  56 */     return this.creat_user;
/*     */   }
/*     */   public String getCreat_dtime() {
/*  59 */     return this.creat_dtime;
/*     */   }
/*     */   public int getModify_user() {
/*  62 */     return this.modify_user;
/*     */   }
/*     */   public String getModify_dtime() {
/*  65 */     return this.modify_dtime;
/*     */   }
/*     */   public String getApp_id() {
/*  68 */     return this.app_id;
/*     */   }
/*     */   public String getSite_id() {
/*  71 */     return this.site_id;
/*     */   }
/*     */   public void setT_id(int tId) {
/*  74 */     this.t_id = tId;
/*     */   }
/*     */   public void setTcat_id(int tcatId) {
/*  77 */     this.tcat_id = tcatId;
/*     */   }
/*     */   public void setT_ename(String tEname) {
/*  80 */     if (tEname == null) {
/*  81 */       tEname = " ";
/*     */     }
/*  83 */     this.t_ename = tEname;
/*     */   }
/*     */   public void setT_cname(String tCname) {
/*  86 */     if (tCname == null) {
/*  87 */       tCname = " ";
/*     */     }
/*  89 */     this.t_cname = tCname;
/*     */   }
/*     */   public void setT_path(String tPath) {
/*  92 */     if (tPath == null) {
/*  93 */       tPath = " ";
/*     */     }
/*  95 */     this.t_path = tPath;
/*     */   }
/*     */   public void setT_content(String tContent) {
/*  98 */     if (tContent == null) {
/*  99 */       tContent = " ";
/*     */     }
/* 101 */     this.t_content = tContent;
/*     */   }
/*     */   public void setT_ver(int tVer) {
/* 104 */     this.t_ver = tVer;
/*     */   }
/*     */   public void setCreat_user(int creatUser) {
/* 107 */     this.creat_user = creatUser;
/*     */   }
/*     */   public void setCreat_dtime(String creatDtime) {
/* 110 */     if (creatDtime == null) {
/* 111 */       creatDtime = " ";
/*     */     }
/* 113 */     this.creat_dtime = creatDtime;
/*     */   }
/*     */   public void setModify_user(int modifyUser) {
/* 116 */     this.modify_user = modifyUser;
/*     */   }
/*     */   public void setModify_dtime(String modifyDtime) {
/* 119 */     if (modifyDtime == null) {
/* 120 */       modifyDtime = " ";
/*     */     }
/* 122 */     this.modify_dtime = modifyDtime;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 125 */     this.app_id = appId;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 128 */     this.site_id = siteId;
/*     */   }
			public int getT_status() {
				return t_status;
			}
			public void setT_status(int t_status) {
				this.t_status = t_status;
			}
			
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.template.TemplateEditBean
 * JD-Core Version:    0.6.2
 */