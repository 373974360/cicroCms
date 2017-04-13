/*     */ package com.cicro.wcm.bean.appeal.sq;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ProcessBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -40768329078538639L;
/*   8 */   private int pro_id = 0;
/*   9 */   private int sq_id = 0;
/*  10 */   private int user_id = 0;
/*  11 */   private String user_realname = "";
/*  12 */   private int do_dept = 0;
/*  13 */   private String dept_name = "";
/*  14 */   private String pro_dtime = "";
/*  15 */   private int pro_type = 0;
/*     */ 
/*  33 */   private String pro_note = "";
/*  34 */   private String to_dept_name = "";
/*  35 */   private int old_dept_id = 0;
/*  36 */   private int old_sq_status = 0;
/*  37 */   private int old_prove_type = 0;
/*     */ 
/*     */   public String getTo_dept_name() {
/*  40 */     return this.to_dept_name;
/*     */   }
/*     */   public void setTo_dept_name(String toDeptName) {
/*  43 */     this.to_dept_name = toDeptName;
/*     */   }
/*     */   public int getOld_dept_id() {
/*  46 */     return this.old_dept_id;
/*     */   }
/*     */   public void setOld_dept_id(int oldDeptId) {
/*  49 */     this.old_dept_id = oldDeptId;
/*     */   }
/*     */   public int getOld_sq_status() {
/*  52 */     return this.old_sq_status;
/*     */   }
/*     */   public void setOld_sq_status(int oldSqStatus) {
/*  55 */     this.old_sq_status = oldSqStatus;
/*     */   }
/*     */   public int getOld_prove_type() {
/*  58 */     return this.old_prove_type;
/*     */   }
/*     */   public void setOld_prove_type(int oldProveType) {
/*  61 */     this.old_prove_type = oldProveType;
/*     */   }
/*     */   public int getPro_id() {
/*  64 */     return this.pro_id;
/*     */   }
/*     */   public void setPro_id(int proId) {
/*  67 */     this.pro_id = proId;
/*     */   }
/*     */   public int getSq_id() {
/*  70 */     return this.sq_id;
/*     */   }
/*     */   public void setSq_id(int sqId) {
/*  73 */     this.sq_id = sqId;
/*     */   }
/*     */   public int getUser_id() {
/*  76 */     return this.user_id;
/*     */   }
/*     */   public void setUser_id(int userId) {
/*  79 */     this.user_id = userId;
/*     */   }
/*     */   public String getUser_realname() {
/*  82 */     return this.user_realname;
/*     */   }
/*     */   public void setUser_realname(String userRealname) {
/*  85 */     this.user_realname = userRealname;
/*     */   }
/*     */   public int getDo_dept() {
/*  88 */     return this.do_dept;
/*     */   }
/*     */   public void setDo_dept(int doDept) {
/*  91 */     this.do_dept = doDept;
/*     */   }
/*     */   public String getDept_name() {
/*  94 */     return this.dept_name;
/*     */   }
/*     */   public void setDept_name(String deptName) {
/*  97 */     this.dept_name = deptName;
/*     */   }
/*     */   public String getPro_dtime() {
/* 100 */     return this.pro_dtime;
/*     */   }
/*     */   public void setPro_dtime(String proDtime) {
/* 103 */     this.pro_dtime = proDtime;
/*     */   }
/*     */   public int getPro_type() {
/* 106 */     return this.pro_type;
/*     */   }
/*     */   public void setPro_type(int proType) {
/* 109 */     this.pro_type = proType;
/*     */   }
/*     */   public String getPro_note() {
/* 112 */     return this.pro_note;
/*     */   }
/*     */   public void setPro_note(String proNote) {
/* 115 */     this.pro_note = proNote;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.sq.ProcessBean
 * JD-Core Version:    0.6.2
 */