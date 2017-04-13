/*     */ package com.cicro.wcm.bean.control;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SiteBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7192101090521919673L;
/*  10 */   private String site_id = "";
/*  11 */   private String site_domain = "";
/*     */   private String parent_id;
/*     */   private int server_id;
/*     */   private int dept_id;
/*  15 */   private String site_name = "";
/*  16 */   private String site_cdkey = "";
/*  17 */   private String site_createtime = "";
/*  18 */   private String site_pausetime = "";
/*  19 */   private String site_position = "";
/*  20 */   private int site_status = 0;
/*  21 */   private int site_sort = 0;
/*  22 */   private String sgroup_id = "";
/*  23 */   private String site_demo = "";
/*  24 */   private String site_path = "";
/*  25 */   private String site_manager = "";
/*     */   private List<SiteBean> child_list;
/*  27 */   private String clone_site_id = "";
/*     */ 
/*     */   public String getClone_site_id() {
/*  30 */     return this.clone_site_id;
/*     */   }
/*     */   public void setClone_site_id(String cloneSiteId) {
/*  33 */     this.clone_site_id = cloneSiteId;
/*     */   }
/*     */   public String getParent_id() {
/*  36 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(String parent_id) {
/*  39 */     this.parent_id = parent_id;
/*     */   }
/*     */   public int getServer_id() {
/*  42 */     return this.server_id;
/*     */   }
/*     */   public void setServer_id(int server_id) {
/*  45 */     this.server_id = server_id;
/*     */   }
/*     */   public String getSgroup_id() {
/*  48 */     return this.sgroup_id;
/*     */   }
/*     */   public void setSgroup_id(String sgroup_id) {
/*  51 */     this.sgroup_id = sgroup_id;
/*     */   }
/*     */   public String getSite_cdkey() {
/*  54 */     return this.site_cdkey;
/*     */   }
/*     */   public void setSite_cdkey(String site_cdkey) {
/*  57 */     this.site_cdkey = site_cdkey;
/*     */   }
/*     */   public String getSite_createtime() {
/*  60 */     return this.site_createtime;
/*     */   }
/*     */   public void setSite_createtime(String site_createtime) {
/*  63 */     this.site_createtime = site_createtime;
/*     */   }
/*     */   public String getSite_demo() {
/*  66 */     return this.site_demo;
/*     */   }
/*     */   public void setSite_demo(String site_demo) {
/*  69 */     this.site_demo = site_demo;
/*     */   }
/*     */   public String getSite_id() {
/*  72 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String site_id) {
/*  75 */     this.site_id = site_id;
/*     */   }
/*     */   public String getSite_name() {
/*  78 */     return this.site_name;
/*     */   }
/*     */   public void setSite_name(String site_name) {
/*  81 */     this.site_name = site_name;
/*     */   }
/*     */   public String getSite_pausetime() {
/*  84 */     return this.site_pausetime;
/*     */   }
/*     */   public void setSite_pausetime(String site_pausetime) {
/*  87 */     this.site_pausetime = site_pausetime;
/*     */   }
/*     */   public String getSite_position() {
/*  90 */     return this.site_position;
/*     */   }
/*     */   public void setSite_position(String site_position) {
/*  93 */     this.site_position = site_position;
/*     */   }
/*     */   public int getDept_id() {
/*  96 */     return this.dept_id;
/*     */   }
/*     */   public void setDept_id(int dept_id) {
/*  99 */     this.dept_id = dept_id;
/*     */   }
/*     */   public int getSite_sort() {
/* 102 */     return this.site_sort;
/*     */   }
/*     */   public void setSite_sort(int site_sort) {
/* 105 */     this.site_sort = site_sort;
/*     */   }
/*     */   public int getSite_status() {
/* 108 */     return this.site_status;
/*     */   }
/*     */   public void setSite_status(int site_status) {
/* 111 */     this.site_status = site_status;
/*     */   }
/*     */   public String getSite_domain() {
/* 114 */     return this.site_domain;
/*     */   }
/*     */   public void setSite_domain(String site_domain) {
/* 117 */     this.site_domain = site_domain;
/*     */   }
/*     */   public String getSite_path() {
/* 120 */     return this.site_path;
/*     */   }
/*     */   public void setSite_path(String site_path) {
/* 123 */     this.site_path = site_path;
/*     */   }
/*     */   public String getSite_manager() {
/* 126 */     return this.site_manager;
/*     */   }
/*     */   public void setSite_manager(String site_manager) {
/* 129 */     this.site_manager = site_manager;
/*     */   }
/*     */   public List<SiteBean> getChild_list() {
/* 132 */     return this.child_list;
/*     */   }
/*     */   public void setChild_list(List<SiteBean> childList) {
/* 135 */     this.child_list = childList;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.control.SiteBean
 * JD-Core Version:    0.6.2
 */