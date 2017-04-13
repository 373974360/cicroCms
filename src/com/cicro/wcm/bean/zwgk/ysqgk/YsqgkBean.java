/*     */ package com.cicro.wcm.bean.zwgk.ysqgk;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class YsqgkBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4157169494257766965L;
/*  16 */   private int ysq_id = 0;
/*  17 */   private String ysq_code = "";
/*  18 */   private String query_code = "";
/*  19 */   private int ysq_type = 0;
/*  20 */   private String name = "";
/*  21 */   private String company = "";
/*  22 */   private String card_name = "";
/*  23 */   private String card_code = "";
/*  24 */   private String org_code = "";
/*  25 */   private String licence = "";
/*  26 */   private String legalperson = "";
/*  27 */   private String linkman = "";
/*  28 */   private String tel = "";
/*  29 */   private String fax = "";
/*  30 */   private String phone = "";
/*  31 */   private String email = "";
/*  32 */   private String postcode = "";
/*  33 */   private String address = "";
/*  34 */   private String put_dtime = "";
/*  35 */   private String content = "";
/*  36 */   private String gk_index = "";
/*  37 */   private String description = "";
/*  38 */   private int is_derate = 0;
/*  39 */   private int do_state = 0;
/*  40 */   private String offer_type = "";
/*  41 */   private String get_method = "";
/*  42 */   private int is_other = 0;
/*  43 */   private int is_third = 0;
/*  44 */   private int is_extend = 0;
/*  45 */   private String accept_content = "";
/*  46 */   private String accept_dtime = "";
/*  47 */   private int accept_user = 0;
/*  48 */   private int reply_type = 1;
/*  49 */   private String reply_content = "";
/*  50 */   private String reply_dtime = "";
/*  51 */   private int reply_user = 0;
/*  52 */   private int is_mail = 0;
/*  53 */   private String node_id = "";
/*  54 */   private String node_name = "";
/*  55 */   private int o_state = 0;
/*  56 */   private int final_status = 0;
/*  57 */   private int publish_state = 0;
/*  58 */   private int supervise_flag = 0;
/*  59 */   private int time_limit = 15;
/*  60 */   private int timeout_flag = 0;
/*  61 */   private int sat_score = 0;
/*  62 */   private int hits = 0;
/*  63 */   private int day_hits = 0;
/*  64 */   private int week_hits = 0;
/*  65 */   private int month_hits = 0;
/*  66 */   private String lasthit_dtime = "";
/*  67 */   private int weight = 60;
/*     */ 
/*     */   public int getWeight()
/*     */   {
/*  71 */     return this.weight;
/*     */   }
/*     */   public void setWeight(int weight) {
/*  74 */     this.weight = weight;
/*     */   }
/*     */   public int getDo_state() {
/*  77 */     return this.do_state;
/*     */   }
/*     */   public void setDo_state(int doState) {
/*  80 */     this.do_state = doState;
/*     */   }
/*     */   public int getYsq_id() {
/*  83 */     return this.ysq_id;
/*     */   }
/*     */   public String getYsq_code() {
/*  86 */     return this.ysq_code;
/*     */   }
/*     */   public String getQuery_code() {
/*  89 */     return this.query_code;
/*     */   }
/*     */   public int getYsq_type() {
/*  92 */     return this.ysq_type;
/*     */   }
/*     */   public String getName() {
/*  95 */     return this.name;
/*     */   }
/*     */   public String getCompany() {
/*  98 */     return this.company;
/*     */   }
/*     */   public String getCard_name() {
/* 101 */     return this.card_name;
/*     */   }
/*     */   public String getCard_code() {
/* 104 */     return this.card_code;
/*     */   }
/*     */   public String getOrg_code() {
/* 107 */     return this.org_code;
/*     */   }
/*     */   public String getLicence() {
/* 110 */     return this.licence;
/*     */   }
/*     */   public String getLegalperson() {
/* 113 */     return this.legalperson;
/*     */   }
/*     */   public String getLinkman() {
/* 116 */     return this.linkman;
/*     */   }
/*     */   public String getTel() {
/* 119 */     return this.tel;
/*     */   }
/*     */   public String getFax() {
/* 122 */     return this.fax;
/*     */   }
/*     */   public String getPhone() {
/* 125 */     return this.phone;
/*     */   }
/*     */   public String getEmail() {
/* 128 */     return this.email;
/*     */   }
/*     */   public String getPostcode() {
/* 131 */     return this.postcode;
/*     */   }
/*     */   public String getAddress() {
/* 134 */     return this.address;
/*     */   }
/*     */   public String getPut_dtime() {
/* 137 */     return this.put_dtime;
/*     */   }
/*     */   public String getContent() {
/* 140 */     return this.content;
/*     */   }
/*     */   public String getGk_index() {
/* 143 */     return this.gk_index;
/*     */   }
/*     */   public String getDescription() {
/* 146 */     return this.description;
/*     */   }
/*     */   public int getIs_derate() {
/* 149 */     return this.is_derate;
/*     */   }
/*     */   public String getOffer_type() {
/* 152 */     return this.offer_type;
/*     */   }
/*     */   public String getGet_method() {
/* 155 */     return this.get_method;
/*     */   }
/*     */   public int getIs_other() {
/* 158 */     return this.is_other;
/*     */   }
/*     */   public int getIs_third() {
/* 161 */     return this.is_third;
/*     */   }
/*     */   public int getIs_extend() {
/* 164 */     return this.is_extend;
/*     */   }
/*     */   public String getAccept_content() {
/* 167 */     return this.accept_content;
/*     */   }
/*     */   public String getAccept_dtime() {
/* 170 */     return this.accept_dtime;
/*     */   }
/*     */   public int getAccept_user() {
/* 173 */     return this.accept_user;
/*     */   }
/*     */   public int getReply_type() {
/* 176 */     return this.reply_type;
/*     */   }
/*     */   public String getReply_content() {
/* 179 */     return this.reply_content;
/*     */   }
/*     */   public String getReply_dtime() {
/* 182 */     return this.reply_dtime;
/*     */   }
/*     */   public int getReply_user() {
/* 185 */     return this.reply_user;
/*     */   }
/*     */   public int getIs_mail() {
/* 188 */     return this.is_mail;
/*     */   }
/*     */   public String getNode_id() {
/* 191 */     return this.node_id;
/*     */   }
/*     */   public String getNode_name() {
/* 194 */     return this.node_name;
/*     */   }
/*     */   public int getO_state() {
/* 197 */     return this.o_state;
/*     */   }
/*     */   public int getFinal_status() {
/* 200 */     return this.final_status;
/*     */   }
/*     */   public int getPublish_state() {
/* 203 */     return this.publish_state;
/*     */   }
/*     */   public int getSupervise_flag() {
/* 206 */     return this.supervise_flag;
/*     */   }
/*     */   public int getTime_limit() {
/* 209 */     return this.time_limit;
/*     */   }
/*     */   public int getTimeout_flag() {
/* 212 */     return this.timeout_flag;
/*     */   }
/*     */   public int getSat_score() {
/* 215 */     return this.sat_score;
/*     */   }
/*     */   public int getHits() {
/* 218 */     return this.hits;
/*     */   }
/*     */   public int getDay_hits() {
/* 221 */     return this.day_hits;
/*     */   }
/*     */   public int getWeek_hits() {
/* 224 */     return this.week_hits;
/*     */   }
/*     */   public int getMonth_hits() {
/* 227 */     return this.month_hits;
/*     */   }
/*     */   public String getLasthit_dtime() {
/* 230 */     return this.lasthit_dtime;
/*     */   }
/*     */   public void setYsq_id(int ysqId) {
/* 233 */     this.ysq_id = ysqId;
/*     */   }
/*     */   public void setYsq_code(String ysqCode) {
/* 236 */     this.ysq_code = ysqCode;
/*     */   }
/*     */   public void setQuery_code(String queryCode) {
/* 239 */     this.query_code = queryCode;
/*     */   }
/*     */   public void setYsq_type(int ysqType) {
/* 242 */     this.ysq_type = ysqType;
/*     */   }
/*     */   public void setName(String name) {
/* 245 */     this.name = name;
/*     */   }
/*     */   public void setCompany(String company) {
/* 248 */     this.company = company;
/*     */   }
/*     */   public void setCard_name(String cardName) {
/* 251 */     this.card_name = cardName;
/*     */   }
/*     */   public void setCard_code(String cardCode) {
/* 254 */     this.card_code = cardCode;
/*     */   }
/*     */   public void setOrg_code(String orgCode) {
/* 257 */     this.org_code = orgCode;
/*     */   }
/*     */   public void setLicence(String licence) {
/* 260 */     this.licence = licence;
/*     */   }
/*     */   public void setLegalperson(String legalperson) {
/* 263 */     this.legalperson = legalperson;
/*     */   }
/*     */   public void setLinkman(String linkman) {
/* 266 */     this.linkman = linkman;
/*     */   }
/*     */   public void setTel(String tel) {
/* 269 */     this.tel = tel;
/*     */   }
/*     */   public void setFax(String fax) {
/* 272 */     this.fax = fax;
/*     */   }
/*     */   public void setPhone(String phone) {
/* 275 */     this.phone = phone;
/*     */   }
/*     */   public void setEmail(String email) {
/* 278 */     this.email = email;
/*     */   }
/*     */   public void setPostcode(String postcode) {
/* 281 */     this.postcode = postcode;
/*     */   }
/*     */   public void setAddress(String address) {
/* 284 */     this.address = address;
/*     */   }
/*     */   public void setPut_dtime(String putDtime) {
/* 287 */     this.put_dtime = putDtime;
/*     */   }
/*     */   public void setContent(String content) {
/* 290 */     this.content = content;
/*     */   }
/*     */   public void setGk_index(String gkIndex) {
/* 293 */     this.gk_index = gkIndex;
/*     */   }
/*     */   public void setDescription(String description) {
/* 296 */     this.description = description;
/*     */   }
/*     */   public void setIs_derate(int isDerate) {
/* 299 */     this.is_derate = isDerate;
/*     */   }
/*     */   public void setOffer_type(String offerType) {
/* 302 */     this.offer_type = offerType;
/*     */   }
/*     */   public void setGet_method(String getMethod) {
/* 305 */     this.get_method = getMethod;
/*     */   }
/*     */   public void setIs_other(int isOther) {
/* 308 */     this.is_other = isOther;
/*     */   }
/*     */   public void setIs_third(int isThird) {
/* 311 */     this.is_third = isThird;
/*     */   }
/*     */   public void setIs_extend(int isExtend) {
/* 314 */     this.is_extend = isExtend;
/*     */   }
/*     */   public void setAccept_content(String acceptContent) {
/* 317 */     this.accept_content = acceptContent;
/*     */   }
/*     */   public void setAccept_dtime(String acceptDtime) {
/* 320 */     this.accept_dtime = acceptDtime;
/*     */   }
/*     */   public void setAccept_user(int acceptUser) {
/* 323 */     this.accept_user = acceptUser;
/*     */   }
/*     */   public void setReply_type(int replyType) {
/* 326 */     this.reply_type = replyType;
/*     */   }
/*     */   public void setReply_content(String replyContent) {
/* 329 */     this.reply_content = replyContent;
/*     */   }
/*     */   public void setReply_dtime(String replyDtime) {
/* 332 */     this.reply_dtime = replyDtime;
/*     */   }
/*     */   public void setReply_user(int replyUser) {
/* 335 */     this.reply_user = replyUser;
/*     */   }
/*     */   public void setIs_mail(int isMail) {
/* 338 */     this.is_mail = isMail;
/*     */   }
/*     */   public void setNode_id(String nodeId) {
/* 341 */     this.node_id = nodeId;
/*     */   }
/*     */   public void setNode_name(String nodeName) {
/* 344 */     this.node_name = nodeName;
/*     */   }
/*     */   public void setO_state(int oState) {
/* 347 */     this.o_state = oState;
/*     */   }
/*     */   public void setFinal_status(int finalStatus) {
/* 350 */     this.final_status = finalStatus;
/*     */   }
/*     */   public void setPublish_state(int publishState) {
/* 353 */     this.publish_state = publishState;
/*     */   }
/*     */   public void setSupervise_flag(int superviseFlag) {
/* 356 */     this.supervise_flag = superviseFlag;
/*     */   }
/*     */   public void setTime_limit(int timeLimit) {
/* 359 */     this.time_limit = timeLimit;
/*     */   }
/*     */   public void setTimeout_flag(int timeoutFlag) {
/* 362 */     this.timeout_flag = timeoutFlag;
/*     */   }
/*     */   public void setSat_score(int satScore) {
/* 365 */     this.sat_score = satScore;
/*     */   }
/*     */   public void setHits(int hits) {
/* 368 */     this.hits = hits;
/*     */   }
/*     */   public void setDay_hits(int dayHits) {
/* 371 */     this.day_hits = dayHits;
/*     */   }
/*     */   public void setWeek_hits(int weekHits) {
/* 374 */     this.week_hits = weekHits;
/*     */   }
/*     */   public void setMonth_hits(int monthHits) {
/* 377 */     this.month_hits = monthHits;
/*     */   }
/*     */   public void setLasthit_dtime(String lasthitDtime) {
/* 380 */     this.lasthit_dtime = lasthitDtime;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.ysqgk.YsqgkBean
 * JD-Core Version:    0.6.2
 */