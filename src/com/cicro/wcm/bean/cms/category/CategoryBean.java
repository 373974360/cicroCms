/*     */ package com.cicro.wcm.bean.cms.category;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CategoryBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6833159982899502073L;
/*   8 */   private int id = 0;
/*   9 */   private int cat_id = 0;
/*  10 */   private int class_id = 0;
/*  11 */   private int parent_id = 0;
/*  12 */   private int wf_id = 0;
/*  13 */   private int is_wf_publish = 0;
/*  14 */   private String cat_code = "";
/*  15 */   private String cat_ename = "";
/*  16 */   private String cat_cname = "";
/*  17 */   private String cat_position = "";
/*  18 */   private int cat_level = 0;
/*  19 */   private int is_mutilpage = 0;
/*  20 */   private String jump_url = "";
/*  21 */   private int template_index = 0;
/*  22 */   private int template_list = 0;
/*  23 */   private int is_generate_index = 0;
/*  24 */   private String urlrule_index = "";
/*  25 */   private String urlrule_list = "";
/*  26 */   private String urlrule_content = "";
/*  27 */   private int is_allow_submit = 0;
/*  28 */   private int is_allow_comment = 0;
/*  29 */   private int is_comment_checked = 0;
/*  30 */   private int is_show = 1;
/*  31 */   private int is_show_tree = 1;
/*  32 */   private String cat_keywords = "";
/*  33 */   private String cat_description = "";
/*  34 */   private int cat_sort = 9999;
/*  35 */   private int is_sync = 0;
/*  36 */   private int cat_source_id = 0;
/*  37 */   private int cat_class_id = 0;
/*  38 */   private int is_disabled = 1;
/*  39 */   private String cat_memo = "";
/*  40 */   private String app_id = "";
/*  41 */   private String site_id = "";
/*  42 */   private int cat_type = 0;
/*  43 */   private int zt_cat_id = 0;
/*  44 */   private int is_archive = 0;
/*  45 */   private boolean is_sub = false;
/*  46 */   private String hj_sql = "";
/*     */ 
/*  48 */   public int getIs_show_tree() { return this.is_show_tree; }
/*     */ 
/*     */   public void setIs_show_tree(int isShowTree) {
/*  51 */     this.is_show_tree = isShowTree;
/*     */   }
/*     */   public String getHj_sql() {
/*  54 */     return this.hj_sql;
/*     */   }
/*     */   public void setHj_sql(String hjSql) {
/*  57 */     this.hj_sql = hjSql;
/*     */   }
/*     */   public boolean isIs_sub() {
/*  60 */     return this.is_sub;
/*     */   }
/*     */   public void setIs_sub(boolean isSub) {
/*  63 */     this.is_sub = isSub;
/*     */   }
/*     */   public String getJump_url() {
/*  66 */     return this.jump_url;
/*     */   }
/*     */   public void setJump_url(String jumpUrl) {
/*  69 */     this.jump_url = jumpUrl;
/*     */   }
/*     */   public int getId() {
/*  72 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  75 */     this.id = id;
/*     */   }
/*     */   public int getCat_type() {
/*  78 */     return this.cat_type;
/*     */   }
/*     */   public void setCat_type(int catType) {
/*  81 */     this.cat_type = catType;
/*     */   }
/*     */   public int getZt_cat_id() {
/*  84 */     return this.zt_cat_id;
/*     */   }
/*     */   public void setZt_cat_id(int ztCatId) {
/*  87 */     this.zt_cat_id = ztCatId;
/*     */   }
/*     */   public int getIs_archive() {
/*  90 */     return this.is_archive;
/*     */   }
/*     */   public void setIs_archive(int isArchive) {
/*  93 */     this.is_archive = isArchive;
/*     */   }
/*     */   public int getCat_id() {
/*  96 */     return this.cat_id;
/*     */   }
/*     */   public void setCat_id(int catId) {
/*  99 */     this.cat_id = catId;
/*     */   }
/*     */   public int getClass_id() {
/* 102 */     return this.class_id;
/*     */   }
/*     */   public void setClass_id(int classId) {
/* 105 */     this.class_id = classId;
/*     */   }
/*     */   public int getParent_id() {
/* 108 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parentId) {
/* 111 */     this.parent_id = parentId;
/*     */   }
/*     */   public int getWf_id() {
/* 114 */     return this.wf_id;
/*     */   }
/*     */   public void setWf_id(int wfId) {
/* 117 */     this.wf_id = wfId;
/*     */   }
/*     */   public int getIs_wf_publish() {
/* 120 */     return this.is_wf_publish;
/*     */   }
/*     */   public void setIs_wf_publish(int isWfPublish) {
/* 123 */     this.is_wf_publish = isWfPublish;
/*     */   }
/*     */   public String getCat_code() {
/* 126 */     return this.cat_code;
/*     */   }
/*     */   public void setCat_code(String catCode) {
/* 129 */     this.cat_code = catCode;
/*     */   }
/*     */   public String getCat_ename() {
/* 132 */     return this.cat_ename;
/*     */   }
/*     */   public void setCat_ename(String catEname) {
/* 135 */     this.cat_ename = catEname;
/*     */   }
/*     */   public String getCat_cname() {
/* 138 */     return this.cat_cname;
/*     */   }
/*     */   public void setCat_cname(String catCname) {
/* 141 */     this.cat_cname = catCname;
/*     */   }
/*     */   public String getCat_position() {
/* 144 */     return this.cat_position;
/*     */   }
/*     */   public void setCat_position(String catPosition) {
/* 147 */     this.cat_position = catPosition;
/*     */   }
/*     */   public int getCat_level() {
/* 150 */     return this.cat_level;
/*     */   }
/*     */   public void setCat_level(int catLevel) {
/* 153 */     this.cat_level = catLevel;
/*     */   }
/*     */   public int getIs_mutilpage() {
/* 156 */     return this.is_mutilpage;
/*     */   }
/*     */   public void setIs_mutilpage(int isMutilpage) {
/* 159 */     this.is_mutilpage = isMutilpage;
/*     */   }
/*     */ 
/*     */   public int getTemplate_index() {
/* 163 */     return this.template_index;
/*     */   }
/*     */   public void setTemplate_index(int templateIndex) {
/* 166 */     this.template_index = templateIndex;
/*     */   }
/*     */   public int getTemplate_list() {
/* 169 */     return this.template_list;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/* 172 */     this.template_list = templateList;
/*     */   }
/*     */   public int getIs_generate_index() {
/* 175 */     return this.is_generate_index;
/*     */   }
/*     */   public void setIs_generate_index(int isGenerateIndex) {
/* 178 */     this.is_generate_index = isGenerateIndex;
/*     */   }
/*     */   public String getUrlrule_index() {
/* 181 */     return this.urlrule_index;
/*     */   }
/*     */   public void setUrlrule_index(String urlruleIndex) {
/* 184 */     this.urlrule_index = urlruleIndex;
/*     */   }
/*     */   public String getUrlrule_list() {
/* 187 */     return this.urlrule_list;
/*     */   }
/*     */   public void setUrlrule_list(String urlruleList) {
/* 190 */     this.urlrule_list = urlruleList;
/*     */   }
/*     */   public String getUrlrule_content() {
/* 193 */     return this.urlrule_content;
/*     */   }
/*     */   public void setUrlrule_content(String urlruleContent) {
/* 196 */     this.urlrule_content = urlruleContent;
/*     */   }
/*     */   public int getIs_allow_submit() {
/* 199 */     return this.is_allow_submit;
/*     */   }
/*     */   public void setIs_allow_submit(int isAllowSubmit) {
/* 202 */     this.is_allow_submit = isAllowSubmit;
/*     */   }
/*     */   public int getIs_allow_comment() {
/* 205 */     return this.is_allow_comment;
/*     */   }
/*     */   public void setIs_allow_comment(int isAllowComment) {
/* 208 */     this.is_allow_comment = isAllowComment;
/*     */   }
/*     */   public int getIs_comment_checked() {
/* 211 */     return this.is_comment_checked;
/*     */   }
/*     */   public void setIs_comment_checked(int isCommentChecked) {
/* 214 */     this.is_comment_checked = isCommentChecked;
/*     */   }
/*     */   public int getIs_show() {
/* 217 */     return this.is_show;
/*     */   }
/*     */   public void setIs_show(int isShow) {
/* 220 */     this.is_show = isShow;
/*     */   }
/*     */   public String getCat_keywords() {
/* 223 */     return this.cat_keywords;
/*     */   }
/*     */   public void setCat_keywords(String catKeywords) {
/* 226 */     this.cat_keywords = catKeywords;
/*     */   }
/*     */   public String getCat_description() {
/* 229 */     return this.cat_description;
/*     */   }
/*     */   public void setCat_description(String catDescription) {
/* 232 */     this.cat_description = catDescription;
/*     */   }
/*     */   public int getCat_sort() {
/* 235 */     return this.cat_sort;
/*     */   }
/*     */   public void setCat_sort(int catSort) {
/* 238 */     this.cat_sort = catSort;
/*     */   }
/*     */   public int getIs_sync() {
/* 241 */     return this.is_sync;
/*     */   }
/*     */   public void setIs_sync(int isSync) {
/* 244 */     this.is_sync = isSync;
/*     */   }
/*     */   public int getCat_source_id() {
/* 247 */     return this.cat_source_id;
/*     */   }
/*     */   public void setCat_source_id(int catSourceId) {
/* 250 */     this.cat_source_id = catSourceId;
/*     */   }
/*     */   public int getCat_class_id() {
/* 253 */     return this.cat_class_id;
/*     */   }
/*     */   public void setCat_class_id(int catClassId) {
/* 256 */     this.cat_class_id = catClassId;
/*     */   }
/*     */   public int getIs_disabled() {
/* 259 */     return this.is_disabled;
/*     */   }
/*     */   public void setIs_disabled(int isDisabled) {
/* 262 */     this.is_disabled = isDisabled;
/*     */   }
/*     */   public String getCat_memo() {
/* 265 */     return this.cat_memo;
/*     */   }
/*     */   public void setCat_memo(String catMemo) {
/* 268 */     this.cat_memo = catMemo;
/*     */   }
/*     */   public String getApp_id() {
/* 271 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 274 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 277 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 280 */     this.site_id = siteId;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CategoryBean
 * JD-Core Version:    0.6.2
 */