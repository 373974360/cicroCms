/*     */ package com.cicro.wcm.bean.org.user;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class UserBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6678713125659864514L;
/*   8 */   private int user_id = 0;
/*     */   private int dept_id;
/*  10 */   private String dept_name = "";
/*  11 */   private String userlevel_value = "";
/*  12 */   private String user_realname = "";
/*  13 */   private String user_aliasname = "";
/*  14 */   private String user_photo = "";
/*     */   private int sex;
/*  16 */   private String birthday = "";
/*  17 */   private String nation = "";
/*     */   private String age;
/*     */   private int is_marriage;
/*  20 */   private String natives = "";
/*  21 */   private String functions = "";
/*  22 */   private String degree = "";
/*  23 */   private String colleges = "";
/*  24 */   private String graduation_time = "";
/*  25 */   private String professional = "";
/*  26 */   private String health = "";
/*  27 */   private String tel = "";
/*  28 */   private String phone = "";
/*  29 */   private String email = "";
/*  30 */   private String address = "";
/*  31 */   private String postcode = "";
/*  32 */   private String idcard = "";
/*  33 */   private int user_status = 0;
/*  34 */   private String resume = "";
/*  35 */   private String user_memo = "";
/*  36 */   private int is_publish = 0;
/*  37 */   private String photo = "";
/*  38 */   private String politics_status = "";
/*  39 */   private String bein_dept = "";
/*  40 */   private String work_desc = "";
/*  41 */   private String summary = "";
/*  42 */   private int sort = 999;
/*  43 */   private String to_work_time = "";
/*     */ 
/*     */   public String getTo_work_time() {
/*  46 */     return this.to_work_time;
/*     */   }
/*     */   public void setTo_work_time(String toWorkTime) {
/*  49 */     this.to_work_time = toWorkTime;
/*     */   }
/*     */   public int getIs_publish() {
/*  52 */     return this.is_publish;
/*     */   }
/*     */   public void setIs_publish(int isPublish) {
/*  55 */     this.is_publish = isPublish;
/*     */   }
/*     */   public String getPhoto() {
/*  58 */     return this.photo;
/*     */   }
/*     */   public void setPhoto(String photo) {
/*  61 */     this.photo = photo;
/*     */   }
/*     */   public String getPolitics_status() {
/*  64 */     return this.politics_status;
/*     */   }
/*     */   public void setPolitics_status(String politicsStatus) {
/*  67 */     this.politics_status = politicsStatus;
/*     */   }
/*     */   public String getBein_dept() {
/*  70 */     return this.bein_dept;
/*     */   }
/*     */   public void setBein_dept(String beinDept) {
/*  73 */     this.bein_dept = beinDept;
/*     */   }
/*     */   public String getWork_desc() {
/*  76 */     return this.work_desc;
/*     */   }
/*     */   public void setWork_desc(String workDesc) {
/*  79 */     this.work_desc = workDesc;
/*     */   }
/*     */   public String getSummary() {
/*  82 */     return this.summary;
/*     */   }
/*     */   public void setSummary(String summary) {
/*  85 */     this.summary = summary;
/*     */   }
/*     */   public int getSort() {
/*  88 */     return this.sort;
/*     */   }
/*     */   public void setSort(int sort) {
/*  91 */     this.sort = sort;
/*     */   }
/*     */   public String getDept_name() {
/*  94 */     return this.dept_name;
/*     */   }
/*     */   public void setDept_name(String deptName) {
/*  97 */     this.dept_name = deptName;
/*     */   }
/*     */   public String getAddress() {
/* 100 */     return this.address;
/*     */   }
/*     */   public void setAddress(String address) {
/* 103 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getAge() {
/* 107 */     return this.age;
/*     */   }
/*     */   public void setAge(String age) {
/* 110 */     this.age = age;
/*     */   }
/*     */   public String getBirthday() {
/* 113 */     return this.birthday;
/*     */   }
/*     */   public void setBirthday(String birthday) {
/* 116 */     this.birthday = birthday;
/*     */   }
/*     */   public String getColleges() {
/* 119 */     return this.colleges;
/*     */   }
/*     */   public void setColleges(String colleges) {
/* 122 */     this.colleges = colleges;
/*     */   }
/*     */   public String getDegree() {
/* 125 */     return this.degree;
/*     */   }
/*     */   public void setDegree(String degree) {
/* 128 */     this.degree = degree;
/*     */   }
/*     */   public int getDept_id() {
/* 131 */     return this.dept_id;
/*     */   }
/*     */   public void setDept_id(int dept_id) {
/* 134 */     this.dept_id = dept_id;
/*     */   }
/*     */   public String getEmail() {
/* 137 */     return this.email;
/*     */   }
/*     */   public void setEmail(String email) {
/* 140 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getFunctions() {
/* 144 */     return this.functions;
/*     */   }
/*     */   public void setFunctions(String functions) {
/* 147 */     this.functions = functions;
/*     */   }
/*     */   public String getGraduation_time() {
/* 150 */     return this.graduation_time;
/*     */   }
/*     */   public void setGraduation_time(String graduation_time) {
/* 153 */     this.graduation_time = graduation_time;
/*     */   }
/*     */   public String getHealth() {
/* 156 */     return this.health;
/*     */   }
/*     */   public void setHealth(String health) {
/* 159 */     this.health = health;
/*     */   }
/*     */   public String getIdcard() {
/* 162 */     return this.idcard;
/*     */   }
/*     */   public void setIdcard(String idcard) {
/* 165 */     this.idcard = idcard;
/*     */   }
/*     */ 
/*     */   public int getIs_marriage() {
/* 169 */     return this.is_marriage;
/*     */   }
/*     */   public void setIs_marriage(int is_marriage) {
/* 172 */     this.is_marriage = is_marriage;
/*     */   }
/*     */   public String getNation() {
/* 175 */     return this.nation;
/*     */   }
/*     */   public void setNation(String nation) {
/* 178 */     this.nation = nation;
/*     */   }
/*     */   public String getNatives() {
/* 181 */     return this.natives;
/*     */   }
/*     */   public void setNatives(String natives) {
/* 184 */     this.natives = natives;
/*     */   }
/*     */   public String getPhone() {
/* 187 */     return this.phone;
/*     */   }
/*     */   public void setPhone(String phone) {
/* 190 */     this.phone = phone;
/*     */   }
/*     */   public String getPostcode() {
/* 193 */     return this.postcode;
/*     */   }
/*     */   public void setPostcode(String postcode) {
/* 196 */     this.postcode = postcode;
/*     */   }
/*     */   public String getProfessional() {
/* 199 */     return this.professional;
/*     */   }
/*     */   public void setProfessional(String professional) {
/* 202 */     this.professional = professional;
/*     */   }
/*     */   public String getResume() {
/* 205 */     return this.resume;
/*     */   }
/*     */   public void setResume(String resume) {
/* 208 */     this.resume = resume;
/*     */   }
/*     */   public int getSex() {
/* 211 */     return this.sex;
/*     */   }
/*     */   public void setSex(int sex) {
/* 214 */     this.sex = sex;
/*     */   }
/*     */   public String getTel() {
/* 217 */     return this.tel;
/*     */   }
/*     */   public void setTel(String tel) {
/* 220 */     this.tel = tel;
/*     */   }
/*     */   public String getUser_aliasname() {
/* 223 */     return this.user_aliasname;
/*     */   }
/*     */   public void setUser_aliasname(String user_aliasname) {
/* 226 */     this.user_aliasname = user_aliasname;
/*     */   }
/*     */   public int getUser_id() {
/* 229 */     return this.user_id;
/*     */   }
/*     */   public void setUser_id(int user_id) {
/* 232 */     this.user_id = user_id;
/*     */   }
/*     */   public String getUser_memo() {
/* 235 */     return this.user_memo;
/*     */   }
/*     */   public void setUser_memo(String user_memo) {
/* 238 */     this.user_memo = user_memo;
/*     */   }
/*     */   public String getUser_photo() {
/* 241 */     return this.user_photo;
/*     */   }
/*     */   public void setUser_photo(String user_photo) {
/* 244 */     this.user_photo = user_photo;
/*     */   }
/*     */   public String getUser_realname() {
/* 247 */     return this.user_realname;
/*     */   }
/*     */   public void setUser_realname(String user_realname) {
/* 250 */     this.user_realname = user_realname;
/*     */   }
/*     */   public int getUser_status() {
/* 253 */     return this.user_status;
/*     */   }
/*     */   public void setUser_status(int user_status) {
/* 256 */     this.user_status = user_status;
/*     */   }
/*     */   public String getUserlevel_value() {
/* 259 */     return this.userlevel_value;
/*     */   }
/*     */   public void setUserlevel_value(String userlevelValue) {
/* 262 */     this.userlevel_value = userlevelValue;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.user.UserBean
 * JD-Core Version:    0.6.2
 */