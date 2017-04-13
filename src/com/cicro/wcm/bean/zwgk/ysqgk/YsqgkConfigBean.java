/*     */ package com.cicro.wcm.bean.zwgk.ysqgk;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class YsqgkConfigBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7652375947373208898L;
/*     */   private int id;
/*  19 */   private int time_limit = 15;
/*  20 */   private String code_pre = "YSQ";
/*  21 */   private String code_rule = "yyyyMMdd";
/*  22 */   private int code_num = 4;
/*  23 */   private int query_num = 4;
/*  24 */   private String file_url = "";
/*  25 */   private int must_member = 0;
/*  26 */   private String remind_type = "";
/*  27 */   private int user_secret = 0;
/*  28 */   private int is_auto_publish = 0;
/*  29 */   private int template_form = 0;
/*  30 */   private int template_list = 0;
/*  31 */   private int template_content = 0;
/*  32 */   private int template_over = 0;
/*  33 */   private int template_print = 0;
/*  34 */   private int template_query = 0;
/*     */ 
/*     */   public int getId()
/*     */   {
/*  38 */     return this.id;
/*     */   }
/*     */   public int getTime_limit() {
/*  41 */     return this.time_limit;
/*     */   }
/*     */   public String getCode_pre() {
/*  44 */     return this.code_pre;
/*     */   }
/*     */   public String getCode_rule() {
/*  47 */     return this.code_rule;
/*     */   }
/*     */   public int getCode_num() {
/*  50 */     return this.code_num;
/*     */   }
/*     */   public int getQuery_num() {
/*  53 */     return this.query_num;
/*     */   }
/*     */   public String getFile_url() {
/*  56 */     return this.file_url;
/*     */   }
/*     */   public int getMust_member() {
/*  59 */     return this.must_member;
/*     */   }
/*     */   public String getRemind_type() {
/*  62 */     return this.remind_type;
/*     */   }
/*     */   public int getUser_secret() {
/*  65 */     return this.user_secret;
/*     */   }
/*     */   public int getIs_auto_publish() {
/*  68 */     return this.is_auto_publish;
/*     */   }
/*     */   public int getTemplate_form() {
/*  71 */     return this.template_form;
/*     */   }
/*     */   public int getTemplate_list() {
/*  74 */     return this.template_list;
/*     */   }
/*     */   public int getTemplate_content() {
/*  77 */     return this.template_content;
/*     */   }
/*     */   public int getTemplate_over() {
/*  80 */     return this.template_over;
/*     */   }
/*     */   public int getTemplate_print() {
/*  83 */     return this.template_print;
/*     */   }
/*     */   public int getTemplate_query() {
/*  86 */     return this.template_query;
/*     */   }
/*     */   public void setId(int id) {
/*  89 */     this.id = id;
/*     */   }
/*     */   public void setTime_limit(int timeLimit) {
/*  92 */     this.time_limit = timeLimit;
/*     */   }
/*     */   public void setCode_pre(String codePre) {
/*  95 */     this.code_pre = codePre;
/*     */   }
/*     */   public void setCode_rule(String codeRule) {
/*  98 */     this.code_rule = codeRule;
/*     */   }
/*     */   public void setCode_num(int codeNum) {
/* 101 */     this.code_num = codeNum;
/*     */   }
/*     */   public void setQuery_num(int queryNum) {
/* 104 */     this.query_num = queryNum;
/*     */   }
/*     */   public void setFile_url(String fileUrl) {
/* 107 */     this.file_url = fileUrl;
/*     */   }
/*     */   public void setMust_member(int mustMember) {
/* 110 */     this.must_member = mustMember;
/*     */   }
/*     */   public void setRemind_type(String remindType) {
/* 113 */     this.remind_type = remindType;
/*     */   }
/*     */   public void setUser_secret(int userSecret) {
/* 116 */     this.user_secret = userSecret;
/*     */   }
/*     */   public void setIs_auto_publish(int isAutoPublish) {
/* 119 */     this.is_auto_publish = isAutoPublish;
/*     */   }
/*     */   public void setTemplate_form(int templateForm) {
/* 122 */     this.template_form = templateForm;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/* 125 */     this.template_list = templateList;
/*     */   }
/*     */   public void setTemplate_content(int templateContent) {
/* 128 */     this.template_content = templateContent;
/*     */   }
/*     */   public void setTemplate_over(int templateOver) {
/* 131 */     this.template_over = templateOver;
/*     */   }
/*     */   public void setTemplate_print(int templatePrint) {
/* 134 */     this.template_print = templatePrint;
/*     */   }
/*     */   public void setTemplate_query(int templateQuery) {
/* 137 */     this.template_query = templateQuery;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.ysqgk.YsqgkConfigBean
 * JD-Core Version:    0.6.2
 */