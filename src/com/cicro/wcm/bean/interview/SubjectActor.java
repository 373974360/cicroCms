/*     */ package com.cicro.wcm.bean.interview;
/*     */ 
/*     */ import com.cicro.wcm.services.lable.data.IBean;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubjectActor
/*     */   implements IBean, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7034610093652182437L;
/*     */   private int id;
/*  24 */   private String actor_id = "";
/*  25 */   private String sub_id = "";
/*  26 */   private String actor_name = "";
/*  27 */   private String age = "";
/*  28 */   private String sex = "";
/*  29 */   private String email = "";
/*  30 */   private String company = "";
/*  31 */   private String a_post = "";
/*  32 */   private String address = "";
/*  33 */   private String description = "";
/*  34 */   private String pic_path = "";
/*  35 */   private String add_time = "";
/*  36 */   private String add_user = "";
/*  37 */   private String update_time = "";
/*  38 */   private String update_user = "";
/*  39 */   private int sort = 999;
/*  40 */   private int is_delete = 0;
/*  41 */   private int is_db = 0;
/*  42 */   private String user_name = "";
/*     */ 
/*     */   public Map toMap()
/*     */   {
/*  47 */     Map m = new HashMap();
/*  48 */     m.put("id", Integer.valueOf(this.id));
/*  49 */     m.put("actor_id", this.actor_id);
/*  50 */     m.put("actor_name", this.actor_name);
/*  51 */     m.put("age", this.age);
/*  52 */     m.put("sex", this.sex);
/*  53 */     m.put("email", this.email);
/*  54 */     m.put("company", this.company);
/*  55 */     m.put("a_post", this.a_post);
/*  56 */     m.put("address", this.address);
/*  57 */     m.put("description", this.description);
/*  58 */     m.put("pic_path", this.pic_path);
/*  59 */     return m;
/*     */   }
/*     */ 
/*     */   public String getActor_id() {
/*  63 */     return this.actor_id;
/*     */   }
/*     */   public void setActor_id(String actor_id) {
/*  66 */     this.actor_id = actor_id;
/*     */   }
/*     */   public String getActor_name() {
/*  69 */     return this.actor_name;
/*     */   }
/*     */   public void setActor_name(String actor_name) {
/*  72 */     this.actor_name = actor_name;
/*     */   }
/*     */   public String getAdd_time() {
/*  75 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String add_time) {
/*  78 */     this.add_time = add_time;
/*     */   }
/*     */   public String getAdd_user() {
/*  81 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String add_user) {
/*  84 */     this.add_user = add_user;
/*     */   }
/*     */   public String getDescription() {
/*  87 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  90 */     this.description = description;
/*     */   }
/*     */   public int getId() {
/*  93 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  96 */     this.id = id;
/*     */   }
/*     */   public int getIs_db() {
/*  99 */     return this.is_db;
/*     */   }
/*     */   public void setIs_db(int is_db) {
/* 102 */     this.is_db = is_db;
/*     */   }
/*     */   public int getIs_delete() {
/* 105 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 108 */     this.is_delete = is_delete;
/*     */   }
/*     */   public String getPic_path() {
/* 111 */     return this.pic_path;
/*     */   }
/*     */   public void setPic_path(String pic_path) {
/* 114 */     this.pic_path = pic_path;
/*     */   }
/*     */   public String getSub_id() {
/* 117 */     return this.sub_id;
/*     */   }
/*     */   public void setSub_id(String sub_id) {
/* 120 */     this.sub_id = sub_id;
/*     */   }
/*     */   public String getUpdate_time() {
/* 123 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 126 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 129 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 132 */     this.update_user = update_user;
/*     */   }
/*     */   public String getA_post() {
/* 135 */     return this.a_post;
/*     */   }
/*     */   public void setA_post(String a_post) {
/* 138 */     this.a_post = a_post;
/*     */   }
/*     */   public String getAddress() {
/* 141 */     return this.address;
/*     */   }
/*     */   public void setAddress(String address) {
/* 144 */     this.address = address;
/*     */   }
/*     */   public String getAge() {
/* 147 */     return this.age;
/*     */   }
/*     */   public void setAge(String age) {
/* 150 */     this.age = age;
/*     */   }
/*     */   public String getCompany() {
/* 153 */     return this.company;
/*     */   }
/*     */   public void setCompany(String company) {
/* 156 */     this.company = company;
/*     */   }
/*     */   public String getEmail() {
/* 159 */     return this.email;
/*     */   }
/*     */   public void setEmail(String email) {
/* 162 */     this.email = email;
/*     */   }
/*     */   public String getSex() {
/* 165 */     return this.sex;
/*     */   }
/*     */   public void setSex(String sex) {
/* 168 */     this.sex = sex;
/*     */   }
/*     */   public int getSort() {
/* 171 */     return this.sort;
/*     */   }
/*     */   public void setSort(int sort) {
/* 174 */     this.sort = sort;
/*     */   }
/*     */   public String getUser_name() {
/* 177 */     return this.user_name;
/*     */   }
/*     */   public void setUser_name(String user_name) {
/* 180 */     this.user_name = user_name;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.SubjectActor
 * JD-Core Version:    0.6.2
 */