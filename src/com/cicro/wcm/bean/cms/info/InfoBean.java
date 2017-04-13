/*     */ package com.cicro.wcm.bean.cms.info;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class InfoBean
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 7656316283038335035L;
/*     */   protected int id;
/*     */   protected int info_id;
/*     */   protected int cat_id;
/*  17 */   protected String cat_cname = "";
/*     */   protected int model_id;
/*     */   protected int from_id;
/*  20 */   protected String top_title = "";
/*  21 */   protected String title = "";
/*  22 */   protected String subtitle = "";
/*  23 */   protected String title_color = "";
/*  24 */   protected String thumb_url = "";
/*  25 */   protected String description = "";
/*  26 */   protected String author = "";
/*  27 */   protected String editor = "";
/*  28 */   protected String source = "";
/*  29 */   protected String info_keywords = "";
/*  30 */   protected String info_description = "";
/*  31 */   protected String tags = "";
/*  32 */   protected String content_url = "";
/*     */   protected int wf_id;
/*     */   protected int step_id;
/*     */   protected int info_status;
/*     */   protected int final_status;
/*     */   protected int weight;
/*     */   protected int hits;
/*     */   protected int day_hits;
/*     */   protected int week_hits;
/*     */   protected int month_hits;
/*  42 */   protected String lasthit_dtime = "";
/*     */   protected int is_allow_comment;
/*     */   protected int comment_num;
/*  45 */   protected String released_dtime = "";
/*     */   protected int input_user;
/*  47 */   protected String input_dtime = "";
/*     */   protected int modify_user;
/*  49 */   protected String modify_dtime = "";
/*  50 */   protected String opt_dtime = "";
/*     */   protected int is_auto_up;
/*  52 */   protected String up_dtime = "";
/*     */   protected int is_auto_down;
/*  54 */   protected String down_dtime = "";
/*     */   protected int is_host;
/*     */   protected int title_hashkey;
/*  57 */   protected String app_id = "";
/*  58 */   protected String site_id = "";
/*     */   protected int i_ver;
/*     */   protected int page_count;
/*  61 */   protected int is_pic = 0;
/*  62 */   protected String pre_title = "";
/*  63 */   protected String auto_desc = "";
/*  64 */   protected int is_am_tupage = 0;
/*  65 */   protected int tupage_num = 1000;

			private String input_user_name = "";
			private String modify_user_name = "";
/*     */ 
/*  68 */   protected int istop = 0;
/*     */ 
/*     */   public int getIs_am_tupage() {
/*  71 */     return this.is_am_tupage;
/*     */   }
/*     */ 
/*     */   public int getIstop() {
/*  75 */     return this.istop;
/*     */   }
/*     */ 
/*     */   public void setIstop(int istop) {
/*  79 */     this.istop = istop;
/*     */   }
/*     */ 
/*     */   public void setIs_am_tupage(int isAmTupage) {
/*  83 */     this.is_am_tupage = isAmTupage;
/*     */   }
/*     */ 
/*     */   public int getTupage_num() {
/*  87 */     return this.tupage_num;
/*     */   }
/*     */ 
/*     */   public void setTupage_num(int tupageNum) {
/*  91 */     this.tupage_num = tupageNum;
/*     */   }
/*     */ 
/*     */   public String getAuto_desc() {
/*  95 */     return this.auto_desc;
/*     */   }
/*     */ 
/*     */   public void setAuto_desc(String autoDesc) {
/*  99 */     this.auto_desc = autoDesc;
/*     */   }
/*     */ 
/*     */   public String getPre_title() {
/* 103 */     return this.pre_title;
/*     */   }
/*     */ 
/*     */   public void setPre_title(String preTitle) {
/* 107 */     this.pre_title = preTitle;
/*     */   }
/*     */ 
/*     */   public String getEditor() {
/* 111 */     return this.editor;
/*     */   }
/*     */ 
/*     */   public void setEditor(String editor) {
/* 115 */     this.editor = editor;
/*     */   }
/*     */ 
/*     */   public String getTop_title() {
/* 119 */     return this.top_title;
/*     */   }
/*     */ 
/*     */   public void setTop_title(String topTitle) {
/* 123 */     this.top_title = topTitle;
/*     */   }
/*     */ 
/*     */   public int getIs_pic() {
/* 127 */     return this.is_pic;
/*     */   }
/*     */ 
/*     */   public void setIs_pic(int isPic) {
/* 131 */     this.is_pic = isPic;
/*     */   }
/*     */ 
/*     */   public int getPage_count() {
/* 135 */     return this.page_count;
/*     */   }
/*     */ 
/*     */   public void setPage_count(int pageCount) {
/* 139 */     this.page_count = pageCount;
/*     */   }
/*     */ 
/*     */   public InfoBean clone() {
/* 143 */     InfoBean o = null;
/*     */     try {
/* 145 */       o = (InfoBean)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 147 */       e.printStackTrace();
/*     */     }
/* 149 */     return o;
/*     */   }
/*     */ 
/*     */   public String getCat_cname() {
/* 153 */     return this.cat_cname;
/*     */   }
/*     */ 
/*     */   public void setCat_cname(String catCname) {
/* 157 */     this.cat_cname = catCname;
/*     */   }
/*     */ 
/*     */   public int getId() {
/* 161 */     return this.id;
/*     */   }
/*     */   public int getInfo_id() {
/* 164 */     return this.info_id;
/*     */   }
/*     */   public int getCat_id() {
/* 167 */     return this.cat_id;
/*     */   }
/*     */   public int getModel_id() {
/* 170 */     return this.model_id;
/*     */   }
/*     */   public int getFrom_id() {
/* 173 */     return this.from_id;
/*     */   }
/*     */   public String getTitle() {
/* 176 */     return this.title;
/*     */   }
/*     */   public String getSubtitle() {
/* 179 */     return this.subtitle;
/*     */   }
/*     */   public String getTitle_color() {
/* 182 */     return this.title_color;
/*     */   }
/*     */   public String getThumb_url() {
/* 185 */     return this.thumb_url;
/*     */   }
/*     */   public String getDescription() {
/* 188 */     return this.description;
/*     */   }
/*     */   public String getAuthor() {
/* 191 */     return this.author;
/*     */   }
/*     */   public String getSource() {
/* 194 */     return this.source;
/*     */   }
/*     */   public String getInfo_keywords() {
/* 197 */     return this.info_keywords;
/*     */   }
/*     */   public String getInfo_description() {
/* 200 */     return this.info_description;
/*     */   }
/*     */   public String getTags() {
/* 203 */     return this.tags;
/*     */   }
/*     */   public String getContent_url() {
/* 206 */     return this.content_url;
/*     */   }
/*     */   public int getWf_id() {
/* 209 */     return this.wf_id;
/*     */   }
/*     */   public int getStep_id() {
/* 212 */     return this.step_id;
/*     */   }
/*     */   public int getInfo_status() {
/* 215 */     return this.info_status;
/*     */   }
/*     */   public int getFinal_status() {
/* 218 */     return this.final_status;
/*     */   }
/*     */   public int getWeight() {
/* 221 */     return this.weight;
/*     */   }
/*     */   public int getHits() {
/* 224 */     return this.hits;
/*     */   }
/*     */   public int getDay_hits() {
/* 227 */     return this.day_hits;
/*     */   }
/*     */   public int getWeek_hits() {
/* 230 */     return this.week_hits;
/*     */   }
/*     */   public int getMonth_hits() {
/* 233 */     return this.month_hits;
/*     */   }
/*     */   public String getLasthit_dtime() {
/* 236 */     return this.lasthit_dtime;
/*     */   }
/*     */   public int getIs_allow_comment() {
/* 239 */     return this.is_allow_comment;
/*     */   }
/*     */   public int getComment_num() {
/* 242 */     return this.comment_num;
/*     */   }
/*     */   public String getReleased_dtime() {
/* 245 */     return this.released_dtime;
/*     */   }
/*     */   public int getInput_user() {
/* 248 */     return this.input_user;
/*     */   }
/*     */   public String getInput_dtime() {
/* 251 */     return this.input_dtime;
/*     */   }
/*     */   public int getModify_user() {
/* 254 */     return this.modify_user;
/*     */   }
/*     */   public String getModify_dtime() {
/* 257 */     return this.modify_dtime;
/*     */   }
/*     */   public String getOpt_dtime() {
/* 260 */     return this.opt_dtime;
/*     */   }
/*     */   public int getIs_auto_up() {
/* 263 */     return this.is_auto_up;
/*     */   }
/*     */   public String getUp_dtime() {
/* 266 */     return this.up_dtime;
/*     */   }
/*     */   public int getIs_auto_down() {
/* 269 */     return this.is_auto_down;
/*     */   }
/*     */   public String getDown_dtime() {
/* 272 */     return this.down_dtime;
/*     */   }
/*     */   public int getIs_host() {
/* 275 */     return this.is_host;
/*     */   }
/*     */   public int getTitle_hashkey() {
/* 278 */     return this.title_hashkey;
/*     */   }
/*     */   public String getApp_id() {
/* 281 */     return this.app_id;
/*     */   }
/*     */   public String getSite_id() {
/* 284 */     return this.site_id;
/*     */   }
/*     */   public int getI_ver() {
/* 287 */     return this.i_ver;
/*     */   }
/*     */   public void setId(int id) {
/* 290 */     this.id = id;
/*     */   }
/*     */   public void setInfo_id(int info_id) {
/* 293 */     this.info_id = info_id;
/*     */   }
/*     */   public void setCat_id(int cat_id) {
/* 296 */     this.cat_id = cat_id;
/*     */   }
/*     */   public void setModel_id(int model_id) {
/* 299 */     this.model_id = model_id;
/*     */   }
/*     */   public void setFrom_id(int from_id) {
/* 302 */     this.from_id = from_id;
/*     */   }
/*     */   public void setTitle(String title) {
/* 305 */     this.title = title;
/*     */   }
/*     */   public void setSubtitle(String subtitle) {
/* 308 */     this.subtitle = subtitle;
/*     */   }
/*     */   public void setTitle_color(String title_color) {
/* 311 */     this.title_color = title_color;
/*     */   }
/*     */   public void setThumb_url(String thumb_url) {
/* 314 */     this.thumb_url = thumb_url;
/*     */   }
/*     */   public void setDescription(String description) {
/* 317 */     this.description = description;
/*     */   }
/*     */   public void setAuthor(String author) {
/* 320 */     this.author = author;
/*     */   }
/*     */   public void setSource(String source) {
/* 323 */     this.source = source;
/*     */   }
/*     */   public void setInfo_keywords(String info_keywords) {
/* 326 */     this.info_keywords = info_keywords;
/*     */   }
/*     */   public void setInfo_description(String info_description) {
/* 329 */     this.info_description = info_description;
/*     */   }
/*     */   public void setTags(String tags) {
/* 332 */     this.tags = tags;
/*     */   }
/*     */   public void setContent_url(String content_url) {
/* 335 */     this.content_url = content_url;
/*     */   }
/*     */   public void setWf_id(int wf_id) {
/* 338 */     this.wf_id = wf_id;
/*     */   }
/*     */   public void setStep_id(int step_id) {
/* 341 */     this.step_id = step_id;
/*     */   }
/*     */   public void setInfo_status(int info_status) {
/* 344 */     this.info_status = info_status;
/*     */   }
/*     */   public void setFinal_status(int final_status) {
/* 347 */     this.final_status = final_status;
/*     */   }
/*     */   public void setWeight(int weight) {
/* 350 */     this.weight = weight;
/*     */   }
/*     */   public void setHits(int hits) {
/* 353 */     this.hits = hits;
/*     */   }
/*     */   public void setDay_hits(int day_hits) {
/* 356 */     this.day_hits = day_hits;
/*     */   }
/*     */   public void setWeek_hits(int week_hits) {
/* 359 */     this.week_hits = week_hits;
/*     */   }
/*     */   public void setMonth_hits(int month_hits) {
/* 362 */     this.month_hits = month_hits;
/*     */   }
/*     */   public void setLasthit_dtime(String lasthit_dtime) {
/* 365 */     this.lasthit_dtime = lasthit_dtime;
/*     */   }
/*     */   public void setIs_allow_comment(int is_allow_comment) {
/* 368 */     this.is_allow_comment = is_allow_comment;
/*     */   }
/*     */   public void setComment_num(int comment_num) {
/* 371 */     this.comment_num = comment_num;
/*     */   }
/*     */   public void setReleased_dtime(String released_dtime) {
/* 374 */     this.released_dtime = released_dtime;
/*     */   }
/*     */   public void setInput_user(int input_user) {
/* 377 */     this.input_user = input_user;
/*     */   }
/*     */   public void setInput_dtime(String input_dtime) {
/* 380 */     this.input_dtime = input_dtime;
/*     */   }
/*     */   public void setModify_user(int modify_user) {
/* 383 */     this.modify_user = modify_user;
/*     */   }
/*     */   public void setModify_dtime(String modify_dtime) {
/* 386 */     this.modify_dtime = modify_dtime;
/*     */   }
/*     */   public void setOpt_dtime(String opt_dtime) {
/* 389 */     this.opt_dtime = opt_dtime;
/*     */   }
/*     */   public void setIs_auto_up(int is_auto_up) {
/* 392 */     this.is_auto_up = is_auto_up;
/*     */   }
/*     */   public void setUp_dtime(String up_dtime) {
/* 395 */     this.up_dtime = up_dtime;
/*     */   }
/*     */   public void setIs_auto_down(int is_auto_down) {
/* 398 */     this.is_auto_down = is_auto_down;
/*     */   }
/*     */   public void setDown_dtime(String down_dtime) {
/* 401 */     this.down_dtime = down_dtime;
/*     */   }
/*     */   public void setIs_host(int is_host) {
/* 404 */     this.is_host = is_host;
/*     */   }
/*     */   public void setTitle_hashkey(int title_hashkey) {
/* 407 */     this.title_hashkey = title_hashkey;
/*     */   }
/*     */   public void setApp_id(String app_id) {
/* 410 */     this.app_id = app_id;
/*     */   }
/*     */   public void setSite_id(String site_id) {
/* 413 */     this.site_id = site_id;
/*     */   }
/*     */   public void setI_ver(int i_ver) {
/* 416 */     this.i_ver = i_ver;
/*     */   }
			public String getInput_user_name() {
				return input_user_name;
			}
			public void setInput_user_name(String input_user_name) {
				this.input_user_name = input_user_name;
			}
			public String getModify_user_name() {
				return modify_user_name;
			}
			public void setModify_user_name(String modify_user_name) {
				this.modify_user_name = modify_user_name;
			}
						
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.InfoBean
 * JD-Core Version:    0.6.2
 */