/*     */ package com.cicro.wcm.bean.sendInfo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ReceiveInfoBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2727928635675062713L;
/*   8 */   private int id = 0;
/*   9 */   private int cat_id = 0;
/*  10 */   private String cat_cname = "";
/*  11 */   private int model_id = 0;
/*  12 */   private String top_title = "";
/*  13 */   private String pre_title = "";
/*  14 */   private String title = "";
/*  15 */   private String subtitle = "";
/*  16 */   private String title_color = "";
/*  17 */   private String thumb_url = "";
/*  18 */   private String description = "";
/*  19 */   private String author = "";
/*  20 */   private String editor = "";
/*  21 */   private String source = "";
/*  22 */   private String info_keywords = "";
/*  23 */   private String info_description = "";
/*  24 */   private String tags = "";
/*  25 */   private int input_user = 0;
/*  26 */   private String input_dtime = "";
/*  27 */   private String app_id = "";
/*  28 */   private String site_id = "";
/*  29 */   private int page_count = 0;
/*  30 */   private int is_pic = 0;
/*  31 */   private int is_am_tupage = 0;
/*  32 */   private int tupage_num = 0;
/*  33 */   private String content = "";
/*  34 */   private String s_info_id = "";
/*  35 */   private String s_site_id = "";
/*  36 */   private String s_site_domain = "";
/*  37 */   private String s_site_name = "";
/*  38 */   private int s_user_id = 0;
/*  39 */   private String s_user_name = "";
/*  40 */   private String s_send_dtime = "";
/*  41 */   private String s_content_url = "";
/*  42 */   private int send_record_id = 0;
/*  43 */   private int is_record = 0;
/*  44 */   private int adopt_status = 0;
/*  45 */   private String adopt_dtime = "";
/*  46 */   private int adopt_user = 0;
/*  47 */   private int is_delete = 0;
/*  48 */   private String adopt_desc = "";
/*  49 */   private int score = 0;
/*     */ 
/*     */   public String getCat_cname()
/*     */   {
/*  53 */     return this.cat_cname;
/*     */   }
/*     */   public void setCat_cname(String catCname) {
/*  56 */     this.cat_cname = catCname;
/*     */   }
/*     */   public int getSend_record_id() {
/*  59 */     return this.send_record_id;
/*     */   }
/*     */   public void setSend_record_id(int sendRecordId) {
/*  62 */     this.send_record_id = sendRecordId;
/*     */   }
/*     */   public String getS_content_url() {
/*  65 */     return this.s_content_url;
/*     */   }
/*     */   public void setS_content_url(String sContentUrl) {
/*  68 */     this.s_content_url = sContentUrl;
/*     */   }
/*     */   public int getId() {
/*  71 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  74 */     this.id = id;
/*     */   }
/*     */   public int getCat_id() {
/*  77 */     return this.cat_id;
/*     */   }
/*     */   public void setCat_id(int catId) {
/*  80 */     this.cat_id = catId;
/*     */   }
/*     */   public int getModel_id() {
/*  83 */     return this.model_id;
/*     */   }
/*     */   public void setModel_id(int modelId) {
/*  86 */     this.model_id = modelId;
/*     */   }
/*     */   public String getTop_title() {
/*  89 */     return this.top_title;
/*     */   }
/*     */   public void setTop_title(String topTitle) {
/*  92 */     this.top_title = topTitle;
/*     */   }
/*     */   public String getPre_title() {
/*  95 */     return this.pre_title;
/*     */   }
/*     */   public void setPre_title(String preTitle) {
/*  98 */     this.pre_title = preTitle;
/*     */   }
/*     */   public String getTitle() {
/* 101 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/* 104 */     this.title = title;
/*     */   }
/*     */   public String getSubtitle() {
/* 107 */     return this.subtitle;
/*     */   }
/*     */   public void setSubtitle(String subtitle) {
/* 110 */     this.subtitle = subtitle;
/*     */   }
/*     */   public String getTitle_color() {
/* 113 */     return this.title_color;
/*     */   }
/*     */   public void setTitle_color(String titleColor) {
/* 116 */     this.title_color = titleColor;
/*     */   }
/*     */   public String getThumb_url() {
/* 119 */     return this.thumb_url;
/*     */   }
/*     */   public void setThumb_url(String thumbUrl) {
/* 122 */     this.thumb_url = thumbUrl;
/*     */   }
/*     */   public String getDescription() {
/* 125 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 128 */     this.description = description;
/*     */   }
/*     */   public String getAuthor() {
/* 131 */     return this.author;
/*     */   }
/*     */   public void setAuthor(String author) {
/* 134 */     this.author = author;
/*     */   }
/*     */   public String getEditor() {
/* 137 */     return this.editor;
/*     */   }
/*     */   public void setEditor(String editor) {
/* 140 */     this.editor = editor;
/*     */   }
/*     */   public String getSource() {
/* 143 */     return this.source;
/*     */   }
/*     */   public void setSource(String source) {
/* 146 */     this.source = source;
/*     */   }
/*     */   public String getInfo_keywords() {
/* 149 */     return this.info_keywords;
/*     */   }
/*     */   public void setInfo_keywords(String infoKeywords) {
/* 152 */     this.info_keywords = infoKeywords;
/*     */   }
/*     */   public String getInfo_description() {
/* 155 */     return this.info_description;
/*     */   }
/*     */   public void setInfo_description(String infoDescription) {
/* 158 */     this.info_description = infoDescription;
/*     */   }
/*     */   public String getTags() {
/* 161 */     return this.tags;
/*     */   }
/*     */   public void setTags(String tags) {
/* 164 */     this.tags = tags;
/*     */   }
/*     */   public int getInput_user() {
/* 167 */     return this.input_user;
/*     */   }
/*     */   public void setInput_user(int inputUser) {
/* 170 */     this.input_user = inputUser;
/*     */   }
/*     */   public String getInput_dtime() {
/* 173 */     return this.input_dtime;
/*     */   }
/*     */   public void setInput_dtime(String inputDtime) {
/* 176 */     this.input_dtime = inputDtime;
/*     */   }
/*     */   public String getApp_id() {
/* 179 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 182 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 185 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 188 */     this.site_id = siteId;
/*     */   }
/*     */   public int getPage_count() {
/* 191 */     return this.page_count;
/*     */   }
/*     */   public void setPage_count(int pageCount) {
/* 194 */     this.page_count = pageCount;
/*     */   }
/*     */   public int getIs_pic() {
/* 197 */     return this.is_pic;
/*     */   }
/*     */   public void setIs_pic(int isPic) {
/* 200 */     this.is_pic = isPic;
/*     */   }
/*     */   public int getIs_am_tupage() {
/* 203 */     return this.is_am_tupage;
/*     */   }
/*     */   public void setIs_am_tupage(int isAmTupage) {
/* 206 */     this.is_am_tupage = isAmTupage;
/*     */   }
/*     */   public int getTupage_num() {
/* 209 */     return this.tupage_num;
/*     */   }
/*     */   public void setTupage_num(int tupageNum) {
/* 212 */     this.tupage_num = tupageNum;
/*     */   }
/*     */   public String getContent() {
/* 215 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/* 218 */     this.content = content;
/*     */   }
/*     */   public String getS_info_id() {
/* 221 */     return this.s_info_id;
/*     */   }
/*     */   public void setS_info_id(String sInfoId) {
/* 224 */     this.s_info_id = sInfoId;
/*     */   }
/*     */   public String getS_site_id() {
/* 227 */     return this.s_site_id;
/*     */   }
/*     */   public void setS_site_id(String sSiteId) {
/* 230 */     this.s_site_id = sSiteId;
/*     */   }
/*     */   public String getS_site_domain() {
/* 233 */     return this.s_site_domain;
/*     */   }
/*     */   public void setS_site_domain(String sSiteDomain) {
/* 236 */     this.s_site_domain = sSiteDomain;
/*     */   }
/*     */   public String getS_site_name() {
/* 239 */     return this.s_site_name;
/*     */   }
/*     */   public void setS_site_name(String sSiteName) {
/* 242 */     this.s_site_name = sSiteName;
/*     */   }
/*     */   public int getS_user_id() {
/* 245 */     return this.s_user_id;
/*     */   }
/*     */   public void setS_user_id(int sUserId) {
/* 248 */     this.s_user_id = sUserId;
/*     */   }
/*     */   public String getS_user_name() {
/* 251 */     return this.s_user_name;
/*     */   }
/*     */   public void setS_user_name(String sUserName) {
/* 254 */     this.s_user_name = sUserName;
/*     */   }
/*     */   public String getS_send_dtime() {
/* 257 */     return this.s_send_dtime;
/*     */   }
/*     */   public void setS_send_dtime(String sSendDtime) {
/* 260 */     this.s_send_dtime = sSendDtime;
/*     */   }
/*     */   public int getIs_record() {
/* 263 */     return this.is_record;
/*     */   }
/*     */   public void setIs_record(int isRecord) {
/* 266 */     this.is_record = isRecord;
/*     */   }
/*     */   public int getAdopt_status() {
/* 269 */     return this.adopt_status;
/*     */   }
/*     */   public void setAdopt_status(int adoptStatus) {
/* 272 */     this.adopt_status = adoptStatus;
/*     */   }
/*     */   public String getAdopt_dtime() {
/* 275 */     return this.adopt_dtime;
/*     */   }
/*     */   public void setAdopt_dtime(String adoptDtime) {
/* 278 */     this.adopt_dtime = adoptDtime;
/*     */   }
/*     */   public int getAdopt_user() {
/* 281 */     return this.adopt_user;
/*     */   }
/*     */   public void setAdopt_user(int adoptUser) {
/* 284 */     this.adopt_user = adoptUser;
/*     */   }
/*     */   public int getIs_delete() {
/* 287 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int isDelete) {
/* 290 */     this.is_delete = isDelete;
/*     */   }
/*     */   public String getAdopt_desc() {
/* 293 */     return this.adopt_desc;
/*     */   }
/*     */   public void setAdopt_desc(String adoptDesc) {
/* 296 */     this.adopt_desc = adoptDesc;
/*     */   }
/*     */   public int getScore() {
/* 299 */     return this.score;
/*     */   }
/*     */   public void setScore(int score) {
/* 302 */     this.score = score;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.ReceiveInfoBean
 * JD-Core Version:    0.6.2
 */