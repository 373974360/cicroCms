/*     */ package com.cicro.wcm.bean.zwgk.ser;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SerCategoryBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -926972095646201821L;
/*  10 */   private int id = 0;
/*  11 */   private int ser_id = 0;
/*  12 */   private int parent_id = 0;
/*  13 */   private String cat_type = "";
/*  14 */   private String tree_position = "";
/*  15 */   private String cat_name = "";
/*  16 */   private String description = "";
/*  17 */   private String next_desc = "";
/*  18 */   private String url = "";
/*  19 */   private int template_index = 0;
/*  20 */   private int template_list = 0;
/*  21 */   private int template_content = 0;
/*  22 */   private int xgwt_cat_id = 0;
/*  23 */   private int cjwt_cat_id = 0;
/*  24 */   private int sort_id = 0;
/*  25 */   private int publish_status = 0;
/*  26 */   private String publish_time = "";
/*  27 */   private int res_flag = 0;
/*  28 */   private String dict_id = "";
/*  29 */   private String thumb_url = "";
/*     */ 
/* 175 */   private String add_time = "";
/* 176 */   private String add_user = "";
/* 177 */   private String update_time = "";
/* 178 */   private String update_user = "";
/*     */ 
/*     */   public int getXgwt_cat_id()
/*     */   {
/*  31 */     return this.xgwt_cat_id;
/*     */   }
/*     */   public void setXgwt_cat_id(int xgwtCatId) {
/*  34 */     this.xgwt_cat_id = xgwtCatId;
/*     */   }
/*     */   public int getCjwt_cat_id() {
/*  37 */     return this.cjwt_cat_id;
/*     */   }
/*     */   public void setCjwt_cat_id(int cjwtCatId) {
/*  40 */     this.cjwt_cat_id = cjwtCatId;
/*     */   }
/*     */   public String getThumb_url() {
/*  43 */     return this.thumb_url;
/*     */   }
/*     */   public void setThumb_url(String thumbUrl) {
/*  46 */     this.thumb_url = thumbUrl;
/*     */   }
/*     */   public int getId() {
/*  49 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  52 */     this.id = id;
/*     */   }
/*     */   public int getSer_id() {
/*  55 */     return this.ser_id;
/*     */   }
/*     */   public void setSer_id(int serId) {
/*  58 */     this.ser_id = serId;
/*     */   }
/*     */   public int getParent_id() {
/*  61 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parentId) {
/*  64 */     this.parent_id = parentId;
/*     */   }
/*     */   public String getCat_type() {
/*  67 */     return this.cat_type;
/*     */   }
/*     */   public void setCat_type(String catType) {
/*  70 */     this.cat_type = catType;
/*     */   }
/*     */   public String getTree_position() {
/*  73 */     return this.tree_position;
/*     */   }
/*     */   public void setTree_position(String treePosition) {
/*  76 */     this.tree_position = treePosition;
/*     */   }
/*     */   public String getCat_name() {
/*  79 */     return this.cat_name;
/*     */   }
/*     */   public void setCat_name(String catName) {
/*  82 */     this.cat_name = catName;
/*     */   }
/*     */   public String getDescription() {
/*  85 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  88 */     this.description = description;
/*     */   }
/*     */   public String getNext_desc() {
/*  91 */     return this.next_desc;
/*     */   }
/*     */   public void setNext_desc(String nextDesc) {
/*  94 */     this.next_desc = nextDesc;
/*     */   }
/*     */   public String getUrl() {
/*  97 */     return this.url;
/*     */   }
/*     */   public void setUrl(String url) {
/* 100 */     this.url = url;
/*     */   }
/*     */   public int getTemplate_index() {
/* 103 */     return this.template_index;
/*     */   }
/*     */   public void setTemplate_index(int templateIndex) {
/* 106 */     this.template_index = templateIndex;
/*     */   }
/*     */   public int getTemplate_list() {
/* 109 */     return this.template_list;
/*     */   }
/*     */   public void setTemplate_list(int templateList) {
/* 112 */     this.template_list = templateList;
/*     */   }
/*     */   public int getTemplate_content() {
/* 115 */     return this.template_content;
/*     */   }
/*     */   public void setTemplate_content(int templateContent) {
/* 118 */     this.template_content = templateContent;
/*     */   }
/*     */   public int getSort_id() {
/* 121 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/* 124 */     this.sort_id = sortId;
/*     */   }
/*     */   public int getPublish_status() {
/* 127 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publishStatus) {
/* 130 */     this.publish_status = publishStatus;
/*     */   }
/*     */   public String getPublish_time() {
/* 133 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publishTime) {
/* 136 */     this.publish_time = publishTime;
/*     */   }
/*     */   public int getRes_flag() {
/* 139 */     return this.res_flag;
/*     */   }
/*     */   public void setRes_flag(int resFlag) {
/* 142 */     this.res_flag = resFlag;
/*     */   }
/*     */ 
/*     */   public String getDict_id() {
/* 146 */     return this.dict_id;
/*     */   }
/*     */   public void setDict_id(String dictId) {
/* 149 */     this.dict_id = dictId;
/*     */   }
/*     */   public String getAdd_time() {
/* 152 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String addTime) {
/* 155 */     this.add_time = addTime;
/*     */   }
/*     */   public String getAdd_user() {
/* 158 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String addUser) {
/* 161 */     this.add_user = addUser;
/*     */   }
/*     */   public String getUpdate_time() {
/* 164 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String updateTime) {
/* 167 */     this.update_time = updateTime;
/*     */   }
/*     */   public String getUpdate_user() {
/* 170 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String updateUser) {
/* 173 */     this.update_user = updateUser;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.ser.SerCategoryBean
 * JD-Core Version:    0.6.2
 */