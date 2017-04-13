/*     */ package com.cicro.wcm.bean.appeal.sq;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SQBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7089029015588100211L;
/*   8 */   private int sq_id = 0;
/*   9 */   private String sq_code = "";
/*  10 */   private int model_id = 0;
/*  11 */   private String model_cname = "";
/*  12 */   private int me_id = 0;
/*  13 */   private String sq_realname = "";
/*  14 */   private int sq_sex = 0;
/*  15 */   private String sq_vocation = "";
/*  16 */   private int sq_age = 0;
/*  17 */   private String sq_address = "";
/*  18 */   private String sq_email = "";
/*  19 */   private String sq_tel = "";
/*  20 */   private String sq_phone = "";
/*  21 */   private String sq_card_id = "";
/*  22 */   private String sq_dtime = "";
/*  23 */   private int is_open = 0;
/*  24 */   private String sq_ip = "";
/*  25 */   private String sq_title = "";
/*  26 */   private String sq_title2 = "";
/*  27 */   private String sq_content = "";
/*  28 */   private String sq_content2 = "";
/*  29 */   private String sq_reply = "";
/*  30 */   private int sq_type = 0;
/*  31 */   private String query_code = "";
/*  32 */   private int cat_id = 0;
/*  33 */   private int area_id = 0;
/*  34 */   private String area_name = "";
/*  35 */   private int pur_id = 0;
/*  36 */   private String pur_name = "";
/*  37 */   private String accept_dtime = "";
/*  38 */   private String over_dtime = "";
/*  39 */   private int sq_flag = 0;
/*  40 */   private int initial_sq_id = 0;
/*  41 */   private int sq_status = 0;
/*  42 */   private String sq_status_name = "";
/*  43 */   private int step_id = 0;
/*  44 */   private int publish_status = 0;
/*  45 */   private int supervise_flag = 0;
/*  46 */   private int is_back = 0;
/*  47 */   private int time_limit = 0;
/*  48 */   private int limit_flag = 0;
/*  49 */   private int alarm_flag = 0;
/*  50 */   private int timeout_flag = 0;
/*  51 */   private int prove_type = 1;
/*  52 */   private int people_num = 1;
/*  53 */   private int do_dept = 0;
/*  54 */   private String do_dept_name = "";
/*  55 */   private String submit_name = "";
/*  56 */   private int publish_user = 0;
/*  57 */   private String publish_dtime = "";
/*  58 */   private int sat_score = 0;
/*  59 */   private int hits = 0;
/*  60 */   private int day_hits = 0;
/*  61 */   private int week_hits = 0;
/*  62 */   private int month_hits = 0;
/*  63 */   private String lasthit_dtime = "";
/*  64 */   private int weight = 60;
/*  65 */   private int sq_all_number = 0;
/*  66 */   private int sq_number = 0;
/*  67 */   private int submit_dept_id = 0;
/*     */ 
/*  69 */   public int getSubmit_dept_id() { return this.submit_dept_id; }
/*     */ 
/*     */   public void setSubmit_dept_id(int submitDeptId) {
/*  72 */     this.submit_dept_id = submitDeptId;
/*     */   }
/*     */   public String getArea_name() {
/*  75 */     return this.area_name;
/*     */   }
/*     */   public void setArea_name(String areaName) {
/*  78 */     this.area_name = areaName;
/*     */   }
/*     */   public int getSq_all_number() {
/*  81 */     return this.sq_all_number;
/*     */   }
/*     */   public void setSq_all_number(int sqAllNumber) {
/*  84 */     this.sq_all_number = sqAllNumber;
/*     */   }
/*     */   public int getSq_number() {
/*  87 */     return this.sq_number;
/*     */   }
/*     */   public void setSq_number(int sqNumber) {
/*  90 */     this.sq_number = sqNumber;
/*     */   }
/*     */   public int getWeight() {
/*  93 */     return this.weight;
/*     */   }
/*     */   public void setWeight(int weight) {
/*  96 */     this.weight = weight;
/*     */   }
/*     */   public int getHits() {
/*  99 */     return this.hits;
/*     */   }
/*     */   public void setHits(int hits) {
/* 102 */     this.hits = hits;
/*     */   }
/*     */   public int getDay_hits() {
/* 105 */     return this.day_hits;
/*     */   }
/*     */   public void setDay_hits(int dayHits) {
/* 108 */     this.day_hits = dayHits;
/*     */   }
/*     */   public int getWeek_hits() {
/* 111 */     return this.week_hits;
/*     */   }
/*     */   public void setWeek_hits(int weekHits) {
/* 114 */     this.week_hits = weekHits;
/*     */   }
/*     */   public int getMonth_hits() {
/* 117 */     return this.month_hits;
/*     */   }
/*     */   public void setMonth_hits(int monthHits) {
/* 120 */     this.month_hits = monthHits;
/*     */   }
/*     */   public String getLasthit_dtime() {
/* 123 */     return this.lasthit_dtime;
/*     */   }
/*     */   public void setLasthit_dtime(String lasthitDtime) {
/* 126 */     this.lasthit_dtime = lasthitDtime;
/*     */   }
/*     */   public String getPur_name() {
/* 129 */     return this.pur_name;
/*     */   }
/*     */   public void setPur_name(String purName) {
/* 132 */     this.pur_name = purName;
/*     */   }
/*     */   public String getDo_dept_name() {
/* 135 */     return this.do_dept_name;
/*     */   }
/*     */   public void setDo_dept_name(String doDeptName) {
/* 138 */     this.do_dept_name = doDeptName;
/*     */   }
/*     */   public String getSq_status_name() {
/* 141 */     return this.sq_status_name;
/*     */   }
/*     */   public void setSq_status_name(String sqStatusName) {
/* 144 */     this.sq_status_name = sqStatusName;
/*     */   }
/*     */   public String getModel_cname() {
/* 147 */     return this.model_cname;
/*     */   }
/*     */   public void setModel_cname(String modelCname) {
/* 150 */     this.model_cname = modelCname;
/*     */   }
/*     */   public int getSq_id() {
/* 153 */     return this.sq_id;
/*     */   }
/*     */   public void setSq_id(int sqId) {
/* 156 */     this.sq_id = sqId;
/*     */   }
/*     */   public String getSq_code() {
/* 159 */     return this.sq_code;
/*     */   }
/*     */   public void setSq_code(String sqCode) {
/* 162 */     this.sq_code = sqCode;
/*     */   }
/*     */   public int getModel_id() {
/* 165 */     return this.model_id;
/*     */   }
/*     */   public void setModel_id(int modelId) {
/* 168 */     this.model_id = modelId;
/*     */   }
/*     */   public int getMe_id() {
/* 171 */     return this.me_id;
/*     */   }
/*     */   public void setMe_id(int meId) {
/* 174 */     this.me_id = meId;
/*     */   }
/*     */   public String getSq_realname() {
/* 177 */     return this.sq_realname;
/*     */   }
/*     */   public void setSq_realname(String sqRealname) {
/* 180 */     this.sq_realname = sqRealname;
/*     */   }
/*     */   public int getSq_sex() {
/* 183 */     return this.sq_sex;
/*     */   }
/*     */   public void setSq_sex(int sqSex) {
/* 186 */     this.sq_sex = sqSex;
/*     */   }
/*     */   public String getSq_vocation() {
/* 189 */     return this.sq_vocation;
/*     */   }
/*     */   public void setSq_vocation(String sqVocation) {
/* 192 */     this.sq_vocation = sqVocation;
/*     */   }
/*     */   public int getSq_age() {
/* 195 */     return this.sq_age;
/*     */   }
/*     */   public void setSq_age(int sqAge) {
/* 198 */     this.sq_age = sqAge;
/*     */   }
/*     */   public String getSq_address() {
/* 201 */     return this.sq_address;
/*     */   }
/*     */   public void setSq_address(String sqAddress) {
/* 204 */     this.sq_address = sqAddress;
/*     */   }
/*     */   public String getSq_email() {
/* 207 */     return this.sq_email;
/*     */   }
/*     */   public void setSq_email(String sqEmail) {
/* 210 */     this.sq_email = sqEmail;
/*     */   }
/*     */   public String getSq_tel() {
/* 213 */     return this.sq_tel;
/*     */   }
/*     */   public void setSq_tel(String sqTel) {
/* 216 */     this.sq_tel = sqTel;
/*     */   }
/*     */   public String getSq_phone() {
/* 219 */     return this.sq_phone;
/*     */   }
/*     */   public void setSq_phone(String sqPhone) {
/* 222 */     this.sq_phone = sqPhone;
/*     */   }
/*     */   public String getSq_card_id() {
/* 225 */     return this.sq_card_id;
/*     */   }
/*     */   public void setSq_card_id(String sqCardId) {
/* 228 */     this.sq_card_id = sqCardId;
/*     */   }
/*     */   public String getSq_dtime() {
/* 231 */     return this.sq_dtime;
/*     */   }
/*     */   public void setSq_dtime(String sqDtime) {
/* 234 */     this.sq_dtime = sqDtime;
/*     */   }
/*     */   public int getIs_open() {
/* 237 */     return this.is_open;
/*     */   }
/*     */   public void setIs_open(int isOpen) {
/* 240 */     this.is_open = isOpen;
/*     */   }
/*     */   public String getSq_ip() {
/* 243 */     return this.sq_ip;
/*     */   }
/*     */   public void setSq_ip(String sqIp) {
/* 246 */     this.sq_ip = sqIp;
/*     */   }
/*     */   public String getSq_title() {
/* 249 */     return this.sq_title;
/*     */   }
/*     */   public void setSq_title(String sqTitle) {
/* 252 */     this.sq_title = sqTitle;
/*     */   }
/*     */   public String getSq_title2() {
/* 255 */     return this.sq_title2;
/*     */   }
/*     */   public void setSq_title2(String sqTitle2) {
/* 258 */     this.sq_title2 = sqTitle2;
/*     */   }
/*     */   public String getSq_content() {
/* 261 */     return this.sq_content;
/*     */   }
/*     */   public void setSq_content(String sqContent) {
/* 264 */     this.sq_content = sqContent;
/*     */   }
/*     */   public String getSq_content2() {
/* 267 */     return this.sq_content2;
/*     */   }
/*     */   public void setSq_content2(String sqContent2) {
/* 270 */     this.sq_content2 = sqContent2;
/*     */   }
/*     */   public String getSq_reply() {
/* 273 */     return this.sq_reply;
/*     */   }
/*     */   public void setSq_reply(String sqReply) {
/* 276 */     this.sq_reply = sqReply;
/*     */   }
/*     */   public int getSq_type() {
/* 279 */     return this.sq_type;
/*     */   }
/*     */   public void setSq_type(int sqType) {
/* 282 */     this.sq_type = sqType;
/*     */   }
/*     */   public String getQuery_code() {
/* 285 */     return this.query_code;
/*     */   }
/*     */   public void setQuery_code(String queryCode) {
/* 288 */     this.query_code = queryCode;
/*     */   }
/*     */   public int getCat_id() {
/* 291 */     return this.cat_id;
/*     */   }
/*     */   public void setCat_id(int catId) {
/* 294 */     this.cat_id = catId;
/*     */   }
/*     */   public int getArea_id() {
/* 297 */     return this.area_id;
/*     */   }
/*     */   public void setArea_id(int areaId) {
/* 300 */     this.area_id = areaId;
/*     */   }
/*     */   public int getPur_id() {
/* 303 */     return this.pur_id;
/*     */   }
/*     */   public void setPur_id(int purId) {
/* 306 */     this.pur_id = purId;
/*     */   }
/*     */   public String getAccept_dtime() {
/* 309 */     return this.accept_dtime;
/*     */   }
/*     */   public void setAccept_dtime(String acceptDtime) {
/* 312 */     this.accept_dtime = acceptDtime;
/*     */   }
/*     */   public String getOver_dtime() {
/* 315 */     return this.over_dtime;
/*     */   }
/*     */   public void setOver_dtime(String overDtime) {
/* 318 */     this.over_dtime = overDtime;
/*     */   }
/*     */   public int getSq_flag() {
/* 321 */     return this.sq_flag;
/*     */   }
/*     */   public void setSq_flag(int sqFlag) {
/* 324 */     this.sq_flag = sqFlag;
/*     */   }
/*     */   public int getInitial_sq_id() {
/* 327 */     return this.initial_sq_id;
/*     */   }
/*     */   public void setInitial_sq_id(int initialSqId) {
/* 330 */     this.initial_sq_id = initialSqId;
/*     */   }
/*     */   public int getSq_status() {
/* 333 */     return this.sq_status;
/*     */   }
/*     */   public void setSq_status(int sqStatus) {
/* 336 */     this.sq_status = sqStatus;
/*     */   }
/*     */   public int getStep_id() {
/* 339 */     return this.step_id;
/*     */   }
/*     */   public void setStep_id(int stepId) {
/* 342 */     this.step_id = stepId;
/*     */   }
/*     */   public int getPublish_status() {
/* 345 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publishStatus) {
/* 348 */     this.publish_status = publishStatus;
/*     */   }
/*     */   public int getSupervise_flag() {
/* 351 */     return this.supervise_flag;
/*     */   }
/*     */   public void setSupervise_flag(int superviseFlag) {
/* 354 */     this.supervise_flag = superviseFlag;
/*     */   }
/*     */   public int getIs_back() {
/* 357 */     return this.is_back;
/*     */   }
/*     */   public void setIs_back(int isBack) {
/* 360 */     this.is_back = isBack;
/*     */   }
/*     */   public int getTime_limit() {
/* 363 */     return this.time_limit;
/*     */   }
/*     */   public void setTime_limit(int timeLimit) {
/* 366 */     this.time_limit = timeLimit;
/*     */   }
/*     */   public int getLimit_flag() {
/* 369 */     return this.limit_flag;
/*     */   }
/*     */   public void setLimit_flag(int limitFlag) {
/* 372 */     this.limit_flag = limitFlag;
/*     */   }
/*     */   public int getAlarm_flag() {
/* 375 */     return this.alarm_flag;
/*     */   }
/*     */   public void setAlarm_flag(int alarmFlag) {
/* 378 */     this.alarm_flag = alarmFlag;
/*     */   }
/*     */   public int getTimeout_flag() {
/* 381 */     return this.timeout_flag;
/*     */   }
/*     */   public void setTimeout_flag(int timeoutFlag) {
/* 384 */     this.timeout_flag = timeoutFlag;
/*     */   }
/*     */   public int getProve_type() {
/* 387 */     return this.prove_type;
/*     */   }
/*     */   public void setProve_type(int proveType) {
/* 390 */     this.prove_type = proveType;
/*     */   }
/*     */   public int getPeople_num() {
/* 393 */     return this.people_num;
/*     */   }
/*     */   public void setPeople_num(int peopleNum) {
/* 396 */     this.people_num = peopleNum;
/*     */   }
/*     */   public int getDo_dept() {
/* 399 */     return this.do_dept;
/*     */   }
/*     */   public void setDo_dept(int doDept) {
/* 402 */     this.do_dept = doDept;
/*     */   }
/*     */   public String getSubmit_name() {
/* 405 */     return this.submit_name;
/*     */   }
/*     */   public void setSubmit_name(String submitName) {
/* 408 */     this.submit_name = submitName;
/*     */   }
/*     */   public int getPublish_user() {
/* 411 */     return this.publish_user;
/*     */   }
/*     */   public void setPublish_user(int publishUser) {
/* 414 */     this.publish_user = publishUser;
/*     */   }
/*     */   public String getPublish_dtime() {
/* 417 */     return this.publish_dtime;
/*     */   }
/*     */   public void setPublish_dtime(String publishDtime) {
/* 420 */     this.publish_dtime = publishDtime;
/*     */   }
/*     */   public int getSat_score() {
/* 423 */     return this.sat_score;
/*     */   }
/*     */   public void setSat_score(int satScore) {
/* 426 */     this.sat_score = satScore;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.sq.SQBean
 * JD-Core Version:    0.6.2
 */