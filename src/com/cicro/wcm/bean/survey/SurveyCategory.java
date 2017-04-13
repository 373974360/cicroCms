/*     */ package com.cicro.wcm.bean.survey;
/*     */ 
/*     */ import com.cicro.wcm.services.lable.data.IBean;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SurveyCategory
/*     */   implements IBean, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8010505842065440635L;
/*     */   private int id;
/*  10 */   private String category_id = "";
/*  11 */   private String c_name = "";
/*  12 */   private String description = "";
/*  13 */   private String model_path = "";
/*  14 */   private int publish_status = 0;
/*  15 */   private String publish_time = "";
/*  16 */   private String add_time = "";
/*  17 */   private String add_user = "";
/*  18 */   private String update_time = "";
/*  19 */   private String update_user = "";
/*  20 */   private int Is_delete = 0;
/*  21 */   private int sort = 0;
/*  22 */   private String template_list_path = "0";
/*  23 */   private String template_content_path = "0";
/*  24 */   private String template_result_path = "0";
/*  25 */   private String site_id = "";
/*     */ 
/*     */   public String getSite_id() {
/*  28 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId) {
/*  32 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public Map toMap()
/*     */   {
/*  37 */     Map m = new HashMap();
/*  38 */     m.put("id", Integer.valueOf(this.id));
/*  39 */     m.put("category_id", this.category_id);
/*  40 */     m.put("c_name", this.c_name);
/*  41 */     m.put("description", this.description);
/*  42 */     m.put("model_path", this.model_path);
/*  43 */     m.put("publish_status", Integer.valueOf(this.publish_status));
/*  44 */     return m;
/*     */   }
/*     */ 
/*     */   public String getTemplate_result_path() {
/*  48 */     return this.template_result_path;
/*     */   }
/*     */ 
/*     */   public void setTemplate_result_path(String templateResultPath) {
/*  52 */     this.template_result_path = templateResultPath;
/*     */   }
/*     */ 
/*     */   public String getTemplate_list_path() {
/*  56 */     return this.template_list_path;
/*     */   }
/*     */ 
/*     */   public void setTemplate_list_path(String templateListPath) {
/*  60 */     this.template_list_path = templateListPath;
/*     */   }
/*     */ 
/*     */   public String getTemplate_content_path() {
/*  64 */     return this.template_content_path;
/*     */   }
/*     */ 
/*     */   public void setTemplate_content_path(String templateContentPath) {
/*  68 */     this.template_content_path = templateContentPath;
/*     */   }
/*     */ 
/*     */   public String getAdd_time() {
/*  72 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String add_time) {
/*  75 */     this.add_time = add_time;
/*     */   }
/*     */   public String getAdd_user() {
/*  78 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String add_user) {
/*  81 */     this.add_user = add_user;
/*     */   }
/*     */   public String getC_name() {
/*  84 */     return this.c_name;
/*     */   }
/*     */   public void setC_name(String c_name) {
/*  87 */     this.c_name = c_name;
/*     */   }
/*     */   public String getCategory_id() {
/*  90 */     return this.category_id;
/*     */   }
/*     */   public void setCategory_id(String category_id) {
/*  93 */     this.category_id = category_id;
/*     */   }
/*     */   public String getDescription() {
/*  96 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  99 */     this.description = description;
/*     */   }
/*     */   public int getId() {
/* 102 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 105 */     this.id = id;
/*     */   }
/*     */   public int getIs_delete() {
/* 108 */     return this.Is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 111 */     this.Is_delete = is_delete;
/*     */   }
/*     */   public String getModel_path() {
/* 114 */     return this.model_path;
/*     */   }
/*     */   public void setModel_path(String model_path) {
/* 117 */     this.model_path = model_path;
/*     */   }
/*     */   public int getPublish_status() {
/* 120 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publish_status) {
/* 123 */     this.publish_status = publish_status;
/*     */   }
/*     */   public String getPublish_time() {
/* 126 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publish_time) {
/* 129 */     this.publish_time = publish_time;
/*     */   }
/*     */   public int getSort() {
/* 132 */     return this.sort;
/*     */   }
/*     */   public void setSort(int sort) {
/* 135 */     this.sort = sort;
/*     */   }
/*     */   public String getUpdate_time() {
/* 138 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 141 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 144 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 147 */     this.update_user = update_user;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveyCategory
 * JD-Core Version:    0.6.2
 */