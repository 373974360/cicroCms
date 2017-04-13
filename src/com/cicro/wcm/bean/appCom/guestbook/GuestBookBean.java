/*     */ package com.cicro.wcm.bean.appCom.guestbook;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GuestBookBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7672051674429369870L;
/*  10 */   private int id = 0;
/*  11 */   private int gbs_id = 0;
/*  12 */   private String nickname = "";
/*  13 */   private String title = "";
/*  14 */   private String address = "";
/*  15 */   private String content = "";
/*  16 */   private int member_id = 0;
/*  17 */   private String ip = "";
/*  18 */   private String class_id = "";
/*  19 */   private int publish_status = 0;
/*  20 */   private int reply_status = 0;
/*  21 */   private String add_time = "";
/*  22 */   private String reply_time = "";
/*  23 */   private String reply_content = "";
/*  24 */   private String realname = "";
/*  25 */   private String phone = "";
/*  26 */   private String tel = "";
/*  27 */   private String post_code = "";
/*  28 */   private String idcard = "";
/*  29 */   private String vocation = "";
/*  30 */   private String age = "";
/*  31 */   private int sex = 0;
/*  32 */   private int hits = 0;
/*  33 */   private String site_id = "";
/*     */ 
/*     */   public String getRealname() {
/*  36 */     return this.realname;
/*     */   }
/*     */   public void setRealname(String realname) {
/*  39 */     this.realname = realname;
/*     */   }
/*     */   public String getPhone() {
/*  42 */     return this.phone;
/*     */   }
/*     */   public void setPhone(String phone) {
/*  45 */     this.phone = phone;
/*     */   }
/*     */   public String getTel() {
/*  48 */     return this.tel;
/*     */   }
/*     */   public void setTel(String tel) {
/*  51 */     this.tel = tel;
/*     */   }
/*     */   public String getPost_code() {
/*  54 */     return this.post_code;
/*     */   }
/*     */   public void setPost_code(String postCode) {
/*  57 */     this.post_code = postCode;
/*     */   }
/*     */   public String getIdcard() {
/*  60 */     return this.idcard;
/*     */   }
/*     */   public void setIdcard(String idcard) {
/*  63 */     this.idcard = idcard;
/*     */   }
/*     */   public String getVocation() {
/*  66 */     return this.vocation;
/*     */   }
/*     */   public void setVocation(String vocation) {
/*  69 */     this.vocation = vocation;
/*     */   }
/*     */   public String getAge() {
/*  72 */     return this.age;
/*     */   }
/*     */   public void setAge(String age) {
/*  75 */     this.age = age;
/*     */   }
/*     */   public int getSex() {
/*  78 */     return this.sex;
/*     */   }
/*     */   public void setSex(int sex) {
/*  81 */     this.sex = sex;
/*     */   }
/*     */   public int getHits() {
/*  84 */     return this.hits;
/*     */   }
/*     */   public void setHit(int hits) {
/*  87 */     this.hits = hits;
/*     */   }
/*     */   public int getGbs_id() {
/*  90 */     return this.gbs_id;
/*     */   }
/*     */   public void setGbs_id(int gbsId) {
/*  93 */     this.gbs_id = gbsId;
/*     */   }
/*     */   public String getSite_id() {
/*  96 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/*  99 */     this.site_id = siteId;
/*     */   }
/*     */   public int getId() {
/* 102 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 105 */     this.id = id;
/*     */   }
/*     */   public String getNickname() {
/* 108 */     return this.nickname;
/*     */   }
/*     */   public void setNickname(String nickname) {
/* 111 */     this.nickname = nickname;
/*     */   }
/*     */   public String getTitle() {
/* 114 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/* 117 */     this.title = title;
/*     */   }
/*     */   public String getAddress() {
/* 120 */     return this.address;
/*     */   }
/*     */   public void setAddress(String address) {
/* 123 */     this.address = address;
/*     */   }
/*     */   public String getContent() {
/* 126 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/* 129 */     this.content = content;
/*     */   }
/*     */   public int getMember_id() {
/* 132 */     return this.member_id;
/*     */   }
/*     */   public void setMember_id(int memberId) {
/* 135 */     this.member_id = memberId;
/*     */   }
/*     */   public String getIp() {
/* 138 */     return this.ip;
/*     */   }
/*     */   public void setIp(String ip) {
/* 141 */     this.ip = ip;
/*     */   }
/*     */   public String getClass_id() {
/* 144 */     return this.class_id;
/*     */   }
/*     */   public void setClass_id(String classId) {
/* 147 */     this.class_id = classId;
/*     */   }
/*     */   public int getPublish_status() {
/* 150 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publishStatus) {
/* 153 */     this.publish_status = publishStatus;
/*     */   }
/*     */   public int getReply_status() {
/* 156 */     return this.reply_status;
/*     */   }
/*     */   public void setReply_status(int replyStatus) {
/* 159 */     this.reply_status = replyStatus;
/*     */   }
/*     */   public String getAdd_time() {
/* 162 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String addTime) {
/* 165 */     this.add_time = addTime;
/*     */   }
/*     */   public String getReply_time() {
/* 168 */     return this.reply_time;
/*     */   }
/*     */   public void setReply_time(String replyTime) {
/* 171 */     this.reply_time = replyTime;
/*     */   }
/*     */   public String getReply_content() {
/* 174 */     return this.reply_content;
/*     */   }
/*     */   public void setReply_content(String replyContent) {
/* 177 */     this.reply_content = replyContent;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appCom.guestbook.GuestBookBean
 * JD-Core Version:    0.6.2
 */