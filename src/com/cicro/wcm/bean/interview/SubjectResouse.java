/*     */ package com.cicro.wcm.bean.interview;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SubjectResouse
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3045801488671957872L;
/*     */   private int id;
/*  18 */   private String sub_id = "";
/*  19 */   private String affix_type = "";
/*  20 */   private String affix_path = "";
/*  21 */   private String affix_name = "";
/*  22 */   private String description = "";
/*  23 */   private String affix_status = "";
/*  24 */   private String add_time = "";
/*  25 */   private String add_user = "";
/*  26 */   private String update_time = "";
/*  27 */   private String update_user = "";
/*  28 */   private int sotr = 0;
/*  29 */   private int is_delete = 0;
/*  30 */   private String user_name = "";
/*     */ 
/*     */   public String getAdd_time()
/*     */   {
/*  48 */     return this.add_time;
/*     */   }
/*     */   public String getUser_name() {
/*  51 */     return this.user_name;
/*     */   }
/*     */ 
/*     */   public void setUser_name(String user_name) {
/*  55 */     this.user_name = user_name;
/*     */   }
/*     */ 
/*     */   public void setAdd_time(String add_time) {
/*  59 */     this.add_time = add_time;
/*     */   }
/*     */   public String getAdd_user() {
/*  62 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String add_user) {
/*  65 */     this.add_user = add_user;
/*     */   }
/*     */   public String getAffix_name() {
/*  68 */     return this.affix_name;
/*     */   }
/*     */   public void setAffix_name(String affix_name) {
/*  71 */     this.affix_name = affix_name;
/*     */   }
/*     */   public String getAffix_path() {
/*  74 */     return this.affix_path;
/*     */   }
/*     */   public void setAffix_path(String affix_path) {
/*  77 */     this.affix_path = affix_path;
/*     */   }
/*     */   public String getAffix_status() {
/*  80 */     return this.affix_status;
/*     */   }
/*     */   public void setAffix_status(String affix_status) {
/*  83 */     this.affix_status = affix_status;
/*     */   }
/*     */   public String getAffix_type() {
/*  86 */     return this.affix_type;
/*     */   }
/*     */   public void setAffix_type(String affix_type) {
/*  89 */     this.affix_type = affix_type;
/*     */   }
/*     */   public int getId() {
/*  92 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  95 */     this.id = id;
/*     */   }
/*     */   public int getIs_delete() {
/*  98 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 101 */     this.is_delete = is_delete;
/*     */   }
/*     */   public int getSotr() {
/* 104 */     return this.sotr;
/*     */   }
/*     */   public void setSotr(int sotr) {
/* 107 */     this.sotr = sotr;
/*     */   }
/*     */   public String getSub_id() {
/* 110 */     return this.sub_id;
/*     */   }
/*     */   public void setSub_id(String sub_id) {
/* 113 */     this.sub_id = sub_id;
/*     */   }
/*     */   public String getUpdate_time() {
/* 116 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 119 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 122 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 125 */     this.update_user = update_user;
/*     */   }
/*     */   public String getDescription() {
/* 128 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 131 */     this.description = description;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.SubjectResouse
 * JD-Core Version:    0.6.2
 */