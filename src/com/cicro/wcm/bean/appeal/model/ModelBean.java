/*     */ package com.cicro.wcm.bean.appeal.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ModelBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -91709240422419253L;
/*     */   private int model_id;
/*  23 */   private String model_cname = "";
/*  24 */   private int relevance_type = 0;
/*  25 */   private int is_sort = 0;
/*  26 */   private int time_limit = 15;
/*  27 */   private int warn_num = -5;
/*  28 */   private int yellow_num = -2;
/*  29 */   private int red_num = 2;
/*  30 */   private String code_pre = "GPPS";
/*  31 */   private String code_rule = "YYYYMMDD";
/*  32 */   private int code_num = 4;
/*  33 */   private int query_num = 4;
/*  34 */   private int must_member = 0;
/*     */   private int wf_id;
/*  36 */   private String remind_type = "";
/*     */   private int is_allow_comment;
/*     */   private int is_comment_checked;
/*  39 */   private int user_secret = 0;
/*  40 */   private int is_auto_publish = 0;
/*  41 */   private String model_memo = "";
/*  42 */   private int template_form = 0;
/*  43 */   private int template_list = 0;
/*  44 */   private int template_content = 0;
/*  45 */   private int template_comment = 0;
/*  46 */   private int template_print = 0;
/*  47 */   private int template_search_list = 0;
/*     */ 
/*     */   public int getTemplate_search_list() {
/*  50 */     return this.template_search_list;
/*     */   }
/*     */   public void setTemplate_search_list(int templateSearchList) {
/*  53 */     this.template_search_list = templateSearchList;
/*     */   }
/*     */   public int getTemplate_print() {
/*  56 */     return this.template_print;
/*     */   }
/*     */   public void setTemplate_print(int templatePrint) {
/*  59 */     this.template_print = templatePrint;
/*     */   }
/*     */   public int getTemplate_comment() {
/*  62 */     return this.template_comment;
/*     */   }
/*     */   public void setTemplate_comment(int templateComment) {
/*  65 */     this.template_comment = templateComment;
/*     */   }
/*     */   public int getTemplate_form() {
/*  68 */     return this.template_form;
/*     */   }
/*     */   public void setTemplate_form(int templateForm) {
/*  71 */     this.template_form = templateForm;
/*     */   }
/*     */   public int getTemplate_list() {
/*  74 */     return this.template_list;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/*  77 */     this.template_list = templateList;
/*     */   }
/*     */   public int getTemplate_content() {
/*  80 */     return this.template_content;
/*     */   }
/*     */   public void setTemplate_content(int templateContent) {
/*  83 */     this.template_content = templateContent;
/*     */   }
/*     */   public int getUser_secret() {
/*  86 */     return this.user_secret;
/*     */   }
/*     */   public void setUser_secret(int userSecret) {
/*  89 */     this.user_secret = userSecret;
/*     */   }
/*     */   public int getIs_auto_publish() {
/*  92 */     return this.is_auto_publish;
/*     */   }
/*     */   public void setIs_auto_publish(int isAutoPublish) {
/*  95 */     this.is_auto_publish = isAutoPublish;
/*     */   }
/*     */   public String getModel_memo() {
/*  98 */     return this.model_memo;
/*     */   }
/*     */   public void setModel_memo(String modelMemo) {
/* 101 */     this.model_memo = modelMemo;
/*     */   }
/*     */   public int getModel_id() {
/* 104 */     return this.model_id;
/*     */   }
/*     */   public void setModel_id(int modelId) {
/* 107 */     this.model_id = modelId;
/*     */   }
/*     */   public String getModel_cname() {
/* 110 */     return this.model_cname;
/*     */   }
/*     */   public void setModel_cname(String modelCname) {
/* 113 */     this.model_cname = modelCname;
/*     */   }
/*     */   public int getRelevance_type() {
/* 116 */     return this.relevance_type;
/*     */   }
/*     */   public void setRelevance_type(int relevanceType) {
/* 119 */     this.relevance_type = relevanceType;
/*     */   }
/*     */   public int getIs_sort() {
/* 122 */     return this.is_sort;
/*     */   }
/*     */   public void setIs_sort(int isSort) {
/* 125 */     this.is_sort = isSort;
/*     */   }
/*     */   public int getTime_limit() {
/* 128 */     return this.time_limit;
/*     */   }
/*     */   public void setTime_limit(int timeLimit) {
/* 131 */     this.time_limit = timeLimit;
/*     */   }
/*     */   public int getWarn_num() {
/* 134 */     return this.warn_num;
/*     */   }
/*     */   public void setWarn_num(int warnNum) {
/* 137 */     this.warn_num = warnNum;
/*     */   }
/*     */   public int getYellow_num() {
/* 140 */     return this.yellow_num;
/*     */   }
/*     */   public void setYellow_num(int yellowNum) {
/* 143 */     this.yellow_num = yellowNum;
/*     */   }
/*     */   public int getRed_num() {
/* 146 */     return this.red_num;
/*     */   }
/*     */   public void setRed_num(int redNum) {
/* 149 */     this.red_num = redNum;
/*     */   }
/*     */   public String getCode_pre() {
/* 152 */     return this.code_pre;
/*     */   }
/*     */   public void setCode_pre(String codePre) {
/* 155 */     this.code_pre = codePre;
/*     */   }
/*     */   public String getCode_rule() {
/* 158 */     return this.code_rule;
/*     */   }
/*     */   public void setCode_rule(String codeRule) {
/* 161 */     this.code_rule = codeRule;
/*     */   }
/*     */   public int getCode_num() {
/* 164 */     return this.code_num;
/*     */   }
/*     */   public void setCode_num(int codeNum) {
/* 167 */     this.code_num = codeNum;
/*     */   }
/*     */   public int getQuery_num() {
/* 170 */     return this.query_num;
/*     */   }
/*     */   public void setQuery_num(int queryNum) {
/* 173 */     this.query_num = queryNum;
/*     */   }
/*     */   public int getMust_member() {
/* 176 */     return this.must_member;
/*     */   }
/*     */   public void setMust_member(int mustMember) {
/* 179 */     this.must_member = mustMember;
/*     */   }
/*     */   public int getWf_id() {
/* 182 */     return this.wf_id;
/*     */   }
/*     */   public void setWf_id(int wfId) {
/* 185 */     this.wf_id = wfId;
/*     */   }
/*     */   public String getRemind_type() {
/* 188 */     return this.remind_type;
/*     */   }
/*     */   public void setRemind_type(String remindType) {
/* 191 */     this.remind_type = remindType;
/*     */   }
/*     */   public void setIs_allow_comment(int is_allow_comment) {
/* 194 */     this.is_allow_comment = is_allow_comment;
/*     */   }
/*     */   public int getIs_allow_comment() {
/* 197 */     return this.is_allow_comment;
/*     */   }
/*     */   public void setIs_comment_checked(int is_comment_checked) {
/* 200 */     this.is_comment_checked = is_comment_checked;
/*     */   }
/*     */   public int getIs_comment_checked() {
/* 203 */     return this.is_comment_checked;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.model.ModelBean
 * JD-Core Version:    0.6.2
 */