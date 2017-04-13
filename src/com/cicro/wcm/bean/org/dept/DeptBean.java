/*     */ package com.cicro.wcm.bean.org.dept;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class DeptBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4585630871271236668L;
/*     */   private int dept_id;
/*     */   private int parent_id;
/*     */   private String deptlevel_value;
/*  11 */   private String dept_fullname = "";
/*  12 */   private String dept_name = "";
/*  13 */   private String area_code = "";
/*  14 */   private String dept_code = "";
/*  15 */   private String functions = "";
/*  16 */   private String tel = "";
/*  17 */   private String fax = "";
/*  18 */   private String email = "";
/*  19 */   private String address = "";
/*  20 */   private int dept_sort = 999;
/*  21 */   private String dept_memo = "";
/*  22 */   private String tree_position = "";
/*  23 */   private String manager_ids = "";
/*  24 */   private int is_delete = 0;
/*     */ 
/*  26 */   private int is_publish = 0;
/*  27 */   private String postcode = "";
/*     */ 
/*     */   public int getIs_publish() {
/*  30 */     return this.is_publish;
/*     */   }
/*     */   public void setIs_publish(int isPublish) {
/*  33 */     this.is_publish = isPublish;
/*     */   }
/*     */   public String getPostcode() {
/*  36 */     return this.postcode;
/*     */   }
/*     */   public void setPostcode(String postcode) {
/*  39 */     this.postcode = postcode;
/*     */   }
/*     */   public String getAddress() {
/*  42 */     return this.address;
/*     */   }
/*     */   public void setAddress(String address) {
/*  45 */     this.address = address;
/*     */   }
/*     */   public String getArea_code() {
/*  48 */     return this.area_code;
/*     */   }
/*     */   public String getManager_ids() {
/*  51 */     return this.manager_ids;
/*     */   }
/*     */   public void setManager_ids(String manager_ids) {
/*  54 */     this.manager_ids = manager_ids;
/*     */   }
/*     */   public void setArea_code(String area_code) {
/*  57 */     this.area_code = area_code;
/*     */   }
/*     */   public String getDept_code() {
/*  60 */     return this.dept_code;
/*     */   }
/*     */   public void setDept_code(String dept_code) {
/*  63 */     this.dept_code = dept_code;
/*     */   }
/*     */   public String getDept_fullname() {
/*  66 */     return this.dept_fullname;
/*     */   }
/*     */   public void setDept_fullname(String dept_fullname) {
/*  69 */     this.dept_fullname = dept_fullname;
/*     */   }
/*     */   public int getDept_id() {
/*  72 */     return this.dept_id;
/*     */   }
/*     */   public void setDept_id(int dept_id) {
/*  75 */     this.dept_id = dept_id;
/*     */   }
/*     */   public String getDept_memo() {
/*  78 */     return this.dept_memo;
/*     */   }
/*     */   public void setDept_memo(String dept_memo) {
/*  81 */     this.dept_memo = dept_memo;
/*     */   }
/*     */   public String getDept_name() {
/*  84 */     return this.dept_name;
/*     */   }
/*     */   public void setDept_name(String dept_name) {
/*  87 */     this.dept_name = dept_name;
/*     */   }
/*     */ 
/*     */   public String getDeptlevel_value() {
/*  91 */     return this.deptlevel_value;
/*     */   }
/*     */   public void setDeptlevel_value(String deptlevelValue) {
/*  94 */     this.deptlevel_value = deptlevelValue;
/*     */   }
/*     */   public int getDept_sort() {
/*  97 */     return this.dept_sort;
/*     */   }
/*     */   public void setDept_sort(int dept_sort) {
/* 100 */     this.dept_sort = dept_sort;
/*     */   }
/*     */   public String getEmail() {
/* 103 */     return this.email;
/*     */   }
/*     */   public void setEmail(String email) {
/* 106 */     this.email = email;
/*     */   }
/*     */   public String getFax() {
/* 109 */     return this.fax;
/*     */   }
/*     */   public void setFax(String fax) {
/* 112 */     this.fax = fax;
/*     */   }
/*     */   public String getFunctions() {
/* 115 */     return this.functions;
/*     */   }
/*     */   public void setFunctions(String functions) {
/* 118 */     this.functions = functions;
/*     */   }
/*     */   public int getIs_delete() {
/* 121 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 124 */     this.is_delete = is_delete;
/*     */   }
/*     */   public int getParent_id() {
/* 127 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parent_id) {
/* 130 */     this.parent_id = parent_id;
/*     */   }
/*     */   public String getTree_position() {
/* 133 */     return this.tree_position;
/*     */   }
/*     */   public void setTree_position(String tree_position) {
/* 136 */     this.tree_position = tree_position;
/*     */   }
/*     */   public String getTel() {
/* 139 */     return this.tel;
/*     */   }
/*     */   public void setTel(String tel) {
/* 142 */     this.tel = tel;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.dept.DeptBean
 * JD-Core Version:    0.6.2
 */