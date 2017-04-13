/*     */ package com.cicro.wcm.bean.interview;
/*     */ 
/*     */ import com.cicro.wcm.services.lable.data.IBean;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubReleInfo
/*     */   implements IBean, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5874123383884786932L;
/*     */   private int id;
/*  24 */   private String info_id = "";
/*  25 */   private String sub_id = "";
/*  26 */   private String info_name = "";
/*  27 */   private String info_type = "html";
/*  28 */   private String content = "";
/*  29 */   private String curl = "";
/*  30 */   private String affix_path = "";
/*  31 */   private String add_time = "";
/*  32 */   private String add_user = "";
/*  33 */   private String update_time = "";
/*  34 */   private String update_user = "";
/*  35 */   private int sort = 999;
/*  36 */   private int is_delete = 0;
/*  37 */   private int publish_flag = 0;
/*     */ 
/*     */   public Map toMap()
/*     */   {
/*  42 */     Map m = new HashMap();
/*  43 */     m.put("id", this.id);
/*  44 */     m.put("info_id", this.info_id);
/*  45 */     m.put("sub_id", this.sub_id);
/*  46 */     m.put("info_name", this.info_name);
/*  47 */     m.put("info_type", this.info_type);
/*  48 */     m.put("content", this.content);
/*  49 */     m.put("curl", this.curl);
/*  50 */     m.put("affix_path", this.affix_path);
/*  51 */     m.put("publish_flag", this.publish_flag);
/*  52 */     return m;
/*     */   }
/*     */ 
/*     */   public int getPublish_flag() {
/*  56 */     return this.publish_flag;
/*     */   }
/*     */   public void setPublish_flag(int publishFlag) {
/*  59 */     this.publish_flag = publishFlag;
/*     */   }
/*     */   public String getInfo_id() {
/*  62 */     return this.info_id;
/*     */   }
/*     */   public void setInfo_id(String info_id) {
/*  65 */     this.info_id = info_id;
/*     */   }
/*     */   public String getAdd_time() {
/*  68 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String add_time) {
/*  71 */     this.add_time = add_time;
/*     */   }
/*     */   public String getAdd_user() {
/*  74 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String add_user) {
/*  77 */     this.add_user = add_user;
/*     */   }
/*     */   public String getContent() {
/*  80 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/*  83 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getCurl() {
/*  87 */     return this.curl;
/*     */   }
/*     */   public void setCurl(String curl) {
/*  90 */     this.curl = curl;
/*     */   }
/*     */   public String getAffix_path() {
/*  93 */     return this.affix_path;
/*     */   }
/*     */   public void setAffix_path(String affixPath) {
/*  96 */     this.affix_path = affixPath;
/*     */   }
/*     */ 
/*     */   public int getId() {
/* 100 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 103 */     this.id = id;
/*     */   }
/*     */   public String getInfo_name() {
/* 106 */     return this.info_name;
/*     */   }
/*     */   public void setInfo_name(String info_name) {
/* 109 */     this.info_name = info_name;
/*     */   }
/*     */   public String getInfo_type() {
/* 112 */     return this.info_type;
/*     */   }
/*     */   public void setInfo_type(String info_type) {
/* 115 */     this.info_type = info_type;
/*     */   }
/*     */   public int getIs_delete() {
/* 118 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 121 */     this.is_delete = is_delete;
/*     */   }
/*     */   public int getSort() {
/* 124 */     return this.sort;
/*     */   }
/*     */   public void setSort(int sort) {
/* 127 */     this.sort = sort;
/*     */   }
/*     */   public String getSub_id() {
/* 130 */     return this.sub_id;
/*     */   }
/*     */   public void setSub_id(String sub_id) {
/* 133 */     this.sub_id = sub_id;
/*     */   }
/*     */   public String getUpdate_time() {
/* 136 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 139 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 142 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 145 */     this.update_user = update_user;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.SubReleInfo
 * JD-Core Version:    0.6.2
 */