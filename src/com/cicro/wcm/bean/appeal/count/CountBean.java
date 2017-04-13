/*     */ package com.cicro.wcm.bean.appeal.count;
/*     */ 
/*     */ public class CountBean
/*     */ {
/*     */   private String business_name;
/*     */   private String purpose_name;
/*     */   private String dept_name;
/*     */   private int dept_id;
/*     */   private Integer node_level;
/*     */   private String user_realname;
/*     */   private String user_id;
/*     */   private String countall;
/*     */   private String countdai;
/*     */   private String countchu;
/*     */   private String countshen;
/*     */   private String countend;
/*     */   private String countwei;
/*     */   private String countendp;
/*     */   private String count;
/*     */   private String count_normal;
/*     */   private String count_invalid;
/*     */   private String count_repeat;
/*     */   private String count_nohandle;
/*     */   private String count_over;
/*     */   private String count_warn;
/*     */   private String count_yellow;
/*     */   private String count_red;
/*     */   private String count_supervise;
/*     */   private String count_limit;
/*  48 */   private float f_temp_count = 0.0F;
/*     */ 
/*     */   public int getDept_id()
/*     */   {
/*  52 */     return this.dept_id;
/*     */   }
/*     */   public void setDept_id(int deptId) {
/*  55 */     this.dept_id = deptId;
/*     */   }
/*     */   public String getUser_id() {
/*  58 */     return this.user_id;
/*     */   }
/*     */   public void setUser_id(String userId) {
/*  61 */     this.user_id = userId;
/*     */   }
/*     */ 
/*     */   public String getUser_realname() {
/*  65 */     return this.user_realname;
/*     */   }
/*     */   public void setUser_realname(String userRealname) {
/*  68 */     this.user_realname = userRealname;
/*     */   }
/*     */ 
/*     */   public String getCount_over() {
/*  72 */     return this.count_over;
/*     */   }
/*     */   public void setCount_over(String countOver) {
/*  75 */     this.count_over = countOver;
/*     */   }
/*     */   public String getCount_warn() {
/*  78 */     return this.count_warn;
/*     */   }
/*     */   public void setCount_warn(String countWarn) {
/*  81 */     this.count_warn = countWarn;
/*     */   }
/*     */   public String getCount_yellow() {
/*  84 */     return this.count_yellow;
/*     */   }
/*     */   public void setCount_yellow(String countYellow) {
/*  87 */     this.count_yellow = countYellow;
/*     */   }
/*     */   public String getCount_red() {
/*  90 */     return this.count_red;
/*     */   }
/*     */   public void setCount_red(String countRed) {
/*  93 */     this.count_red = countRed;
/*     */   }
/*     */   public String getCount_supervise() {
/*  96 */     return this.count_supervise;
/*     */   }
/*     */   public void setCount_supervise(String countSupervise) {
/*  99 */     this.count_supervise = countSupervise;
/*     */   }
/*     */ 
/*     */   public String getCountall() {
/* 103 */     return this.countall;
/*     */   }
/*     */   public void setCountall(String countall) {
/* 106 */     this.countall = countall;
/*     */   }
/*     */   public String getCountdai() {
/* 109 */     return this.countdai;
/*     */   }
/*     */   public void setCountdai(String countdai) {
/* 112 */     this.countdai = countdai;
/*     */   }
/*     */   public String getCountchu() {
/* 115 */     return this.countchu;
/*     */   }
/*     */   public void setCountchu(String countchu) {
/* 118 */     this.countchu = countchu;
/*     */   }
/*     */   public String getCountshen() {
/* 121 */     return this.countshen;
/*     */   }
/*     */   public void setCountshen(String countshen) {
/* 124 */     this.countshen = countshen;
/*     */   }
/*     */   public String getCountend() {
/* 127 */     return this.countend;
/*     */   }
/*     */   public void setCountend(String countend) {
/* 130 */     this.countend = countend;
/*     */   }
/*     */   public String getCountendp() {
/* 133 */     return this.countendp;
/*     */   }
/*     */   public void setCountendp(String countendp) {
/* 136 */     this.countendp = countendp;
/*     */   }
/*     */   public String getBusiness_name() {
/* 139 */     return this.business_name;
/*     */   }
/*     */   public void setBusiness_name(String businessName) {
/* 142 */     this.business_name = businessName;
/*     */   }
/*     */   public String getPurpose_name() {
/* 145 */     return this.purpose_name;
/*     */   }
/*     */   public void setPurpose_name(String purposeName) {
/* 148 */     this.purpose_name = purposeName;
/*     */   }
/*     */   public String getCount() {
/* 151 */     return this.count;
/*     */   }
/*     */   public void setCount(String count) {
/* 154 */     this.count = count;
/*     */   }
/*     */   public String getCount_normal() {
/* 157 */     return this.count_normal;
/*     */   }
/*     */   public void setCount_normal(String countNormal) {
/* 160 */     this.count_normal = countNormal;
/*     */   }
/*     */   public String getCount_invalid() {
/* 163 */     return this.count_invalid;
/*     */   }
/*     */   public void setCount_invalid(String countInvalid) {
/* 166 */     this.count_invalid = countInvalid;
/*     */   }
/*     */   public String getCount_repeat() {
/* 169 */     return this.count_repeat;
/*     */   }
/*     */   public void setCount_repeat(String countRepeat) {
/* 172 */     this.count_repeat = countRepeat;
/*     */   }
/*     */   public String getCount_nohandle() {
/* 175 */     return this.count_nohandle;
/*     */   }
/*     */   public void setCount_nohandle(String countNohandle) {
/* 178 */     this.count_nohandle = countNohandle;
/*     */   }
/*     */   public String getCount_limit() {
/* 181 */     return this.count_limit;
/*     */   }
/*     */   public void setCount_limit(String countLimit) {
/* 184 */     this.count_limit = countLimit;
/*     */   }
/*     */   public String getDept_name() {
/* 187 */     return this.dept_name;
/*     */   }
/*     */   public void setDept_name(String deptName) {
/* 190 */     this.dept_name = deptName;
/*     */   }
/*     */   public String getCountwei() {
/* 193 */     return this.countwei;
/*     */   }
/*     */   public void setCountwei(String countwei) {
/* 196 */     this.countwei = countwei;
/*     */   }
/*     */   public Integer getNode_level() {
/* 199 */     return this.node_level;
/*     */   }
/*     */   public void setNode_level(Integer nodeLevel) {
/* 202 */     this.node_level = nodeLevel;
/*     */   }
/*     */   public float getF_temp_count() {
/* 205 */     return this.f_temp_count;
/*     */   }
/*     */   public void setF_temp_count(float fTempCount) {
/* 208 */     this.f_temp_count = fTempCount;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.count.CountBean
 * JD-Core Version:    0.6.2
 */