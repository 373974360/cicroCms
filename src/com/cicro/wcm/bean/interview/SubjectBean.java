/*     */ package com.cicro.wcm.bean.interview;
/*     */ 
/*     */ import com.cicro.wcm.services.interview.SubjectActorServices;
/*     */ import com.cicro.wcm.services.lable.data.IBean;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubjectBean
/*     */   implements IBean, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7088726010521403119L;
/*     */   private int id;
/*  23 */   private String sub_id = "";
/*  24 */   private String category_id = "";
/*  25 */   private String category_name = "";
/*  26 */   private String sub_name = "";
/*  27 */   private String summary = "";
/*  28 */   private String intro = "";
/*  29 */   private String info = "";
/*  30 */   private String start_time = "";
/*  31 */   private String end_time = "";
/*  32 */   private int status = 0;
/*  33 */   private int audit_status = 0;
/*  34 */   private int publish_status = 0;
/*  35 */   private String publish_time = "";
/*  36 */   private int is_auto_audit = 0;
/*  37 */   private int auto_audit_time = 0;
/*  38 */   private String apply_time = "";
/*  39 */   private String apply_user = "";
/*  40 */   private String apply_dept = "";
/*     */ 
/*  42 */   private String update_time = "";
/*  43 */   private String update_user = "";
/*  44 */   private String audit_time = "";
/*  45 */   private String audit_user = "";
/*  46 */   private int is_delete = 0;
/*  47 */   private int sort = 0;
/*  48 */   private int click_count = 0;
/*  49 */   private int submit_status = 0;
/*  50 */   private String user_name = "";
/*  51 */   private String s_for_video = "";
/*     */ 
/*  53 */   private String s_for_pic = "";
/*  54 */   private String s_live_video = "";
/*  55 */   private String s_history_video = "";
/*  56 */   private int recommend_flag = 0;
/*  57 */   private String recommend_time = "";
/*  58 */   private String history_record_pic = "";
/*  59 */   private String history_record_text = "";
/*  60 */   private int count_user = 0;
/*     */ 
/*  62 */   private String actor_name = "";
/*  63 */   private String site_id = "";
/*     */ 
/*     */   public String getSite_id() {
/*  66 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId) {
/*  70 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public Map toMap()
/*     */   {
/*  76 */     Map m = new HashMap();
/*  77 */     m.put("id", this.id);
/*  78 */     m.put("sub_id", this.sub_id);
/*  79 */     m.put("category_id", this.category_id);
/*  80 */     m.put("sub_name", this.sub_name);
/*  81 */     m.put("start_time", this.start_time);
/*  82 */     m.put("end_time", this.end_time);
/*  83 */     m.put("intro", this.intro);
/*  84 */     m.put("info", this.info);
/*  85 */     m.put("start_time", this.start_time);
/*  86 */     m.put("end_time", this.end_time);
/*  87 */     m.put("status", this.status);
/*  88 */     m.put("publish_status", this.publish_status);
/*  89 */     m.put("s_for_video", this.s_for_video);
/*  90 */     m.put("s_for_pic", this.s_for_pic);
/*  91 */     m.put("s_live_video", this.s_live_video);
/*  92 */     m.put("s_history_video", this.s_history_video);
/*  93 */     m.put("history_record_pic", this.history_record_pic);
/*  94 */     m.put("history_record_text", this.history_record_text);
/*  95 */     m.put("count_user", this.count_user);
/*  96 */     m.put("actor_name", getActor_Name(this.sub_id));
/*  97 */     return m;
/*     */   }
/*     */ 
/*     */   public String getActor_Name(String sub_id)
/*     */   {
/* 103 */     String a_name = "";
/* 104 */     List l = SubjectActorServices.getAllActorName(sub_id);
/* 105 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 107 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 109 */         a_name = a_name + "," + ((SubjectActor)l.get(i)).getActor_name();
/*     */       }
/* 111 */       a_name = a_name.substring(1).replace(",", "&nbsp;");
/* 112 */       this.actor_name = a_name;
/*     */     }
/* 114 */     return a_name;
/*     */   }
/*     */ 
/*     */   public String getActor_name() {
/* 118 */     return this.actor_name;
/*     */   }
/*     */ 
/*     */   public void setActor_name(String actorName) {
/* 122 */     this.actor_name = actorName;
/*     */   }
/*     */ 
/*     */   public int getRecommend_flag() {
/* 126 */     return this.recommend_flag;
/*     */   }
/*     */   public void setRecommend_flag(int recommend_flag) {
/* 129 */     this.recommend_flag = recommend_flag;
/*     */   }
/*     */   public String getUser_name() {
/* 132 */     return this.user_name;
/*     */   }
/*     */   public void setUser_name(String user_name) {
/* 135 */     this.user_name = user_name;
/*     */   }
/*     */   public String getApply_dept() {
/* 138 */     return this.apply_dept;
/*     */   }
/*     */   public void setApply_dept(String apply_dept) {
/* 141 */     this.apply_dept = apply_dept;
/*     */   }
/*     */   public String getApply_time() {
/* 144 */     return this.apply_time;
/*     */   }
/*     */   public void setApply_time(String apply_time) {
/* 147 */     this.apply_time = apply_time;
/*     */   }
/*     */   public String getApply_user() {
/* 150 */     return this.apply_user;
/*     */   }
/*     */   public void setApply_user(String apply_user) {
/* 153 */     this.apply_user = apply_user;
/*     */   }
/*     */   public int getAudit_status() {
/* 156 */     return this.audit_status;
/*     */   }
/*     */   public void setAudit_status(int audit_status) {
/* 159 */     this.audit_status = audit_status;
/*     */   }
/*     */   public String getAudit_time() {
/* 162 */     return this.audit_time;
/*     */   }
/*     */   public void setAudit_time(String audit_time) {
/* 165 */     this.audit_time = audit_time;
/*     */   }
/*     */   public String getAudit_user() {
/* 168 */     return this.audit_user;
/*     */   }
/*     */   public void setAudit_user(String audit_user) {
/* 171 */     this.audit_user = audit_user;
/*     */   }
/*     */   public int getAuto_audit_time() {
/* 174 */     return this.auto_audit_time;
/*     */   }
/*     */   public void setAuto_audit_time(int auto_audit_time) {
/* 177 */     this.auto_audit_time = auto_audit_time;
/*     */   }
/*     */   public String getCategory_id() {
/* 180 */     return this.category_id;
/*     */   }
/*     */   public void setCategory_id(String category_id) {
/* 183 */     this.category_id = category_id;
/*     */   }
/*     */   public int getClick_count() {
/* 186 */     return this.click_count;
/*     */   }
/*     */   public void setClick_count(int click_count) {
/* 189 */     this.click_count = click_count;
/*     */   }
/*     */   public String getEnd_time() {
/* 192 */     return this.end_time;
/*     */   }
/*     */   public void setEnd_time(String end_time) {
/* 195 */     this.end_time = end_time;
/*     */   }
/*     */   public int getId() {
/* 198 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 201 */     this.id = id;
/*     */   }
/*     */   public String getInfo() {
/* 204 */     return this.info;
/*     */   }
/*     */   public void setInfo(String info) {
/* 207 */     this.info = info;
/*     */   }
/*     */   public String getIntro() {
/* 210 */     return this.intro;
/*     */   }
/*     */   public void setIntro(String intro) {
/* 213 */     this.intro = intro;
/*     */   }
/*     */   public int getIs_auto_audit() {
/* 216 */     return this.is_auto_audit;
/*     */   }
/*     */   public void setIs_auto_audit(int is_auto_audit) {
/* 219 */     this.is_auto_audit = is_auto_audit;
/*     */   }
/*     */   public int getIs_delete() {
/* 222 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 225 */     this.is_delete = is_delete;
/*     */   }
/*     */   public int getPublish_status() {
/* 228 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publish_status) {
/* 231 */     this.publish_status = publish_status;
/*     */   }
/*     */   public String getS_for_pic() {
/* 234 */     return this.s_for_pic;
/*     */   }
/*     */   public void setS_for_pic(String s_for_pic) {
/* 237 */     this.s_for_pic = s_for_pic;
/*     */   }
/*     */   public String getS_for_video() {
/* 240 */     return this.s_for_video;
/*     */   }
/*     */   public void setS_for_video(String s_for_video) {
/* 243 */     this.s_for_video = s_for_video;
/*     */   }
/*     */   public int getSort() {
/* 246 */     return this.sort;
/*     */   }
/*     */   public void setSort(int sort) {
/* 249 */     this.sort = sort;
/*     */   }
/*     */   public String getStart_time() {
/* 252 */     return this.start_time;
/*     */   }
/*     */   public void setStart_time(String start_time) {
/* 255 */     this.start_time = start_time;
/*     */   }
/*     */   public int getStatus() {
/* 258 */     return this.status;
/*     */   }
/*     */   public void setStatus(int status) {
/* 261 */     this.status = status;
/*     */   }
/*     */   public String getSub_id() {
/* 264 */     return this.sub_id;
/*     */   }
/*     */   public void setSub_id(String sub_id) {
/* 267 */     this.sub_id = sub_id;
/*     */   }
/*     */   public String getSub_name() {
/* 270 */     return this.sub_name;
/*     */   }
/*     */   public void setSub_name(String sub_name) {
/* 273 */     this.sub_name = sub_name;
/*     */   }
/*     */   public String getSummary() {
/* 276 */     return this.summary;
/*     */   }
/*     */   public void setSummary(String summary) {
/* 279 */     this.summary = summary;
/*     */   }
/*     */   public String getUpdate_time() {
/* 282 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 285 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 288 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 291 */     this.update_user = update_user;
/*     */   }
/*     */   public String getPublish_time() {
/* 294 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publish_time) {
/* 297 */     this.publish_time = publish_time;
/*     */   }
/*     */   public String getCategory_name() {
/* 300 */     return this.category_name;
/*     */   }
/*     */   public void setCategory_name(String category_name) {
/* 303 */     this.category_name = category_name;
/*     */   }
/*     */   public int getSubmit_status() {
/* 306 */     return this.submit_status;
/*     */   }
/*     */   public void setSubmit_status(int submit_status) {
/* 309 */     this.submit_status = submit_status;
/*     */   }
/*     */ 
/*     */   public String getS_history_video() {
/* 313 */     return this.s_history_video;
/*     */   }
/*     */   public void setS_history_video(String s_history_video) {
/* 316 */     this.s_history_video = s_history_video;
/*     */   }
/*     */   public String getS_live_video() {
/* 319 */     return this.s_live_video;
/*     */   }
/*     */   public void setS_live_video(String s_live_video) {
/* 322 */     this.s_live_video = s_live_video;
/*     */   }
/*     */   public String getRecommend_time() {
/* 325 */     return this.recommend_time;
/*     */   }
/*     */   public void setRecommend_time(String recommend_time) {
/* 328 */     this.recommend_time = recommend_time;
/*     */   }
/*     */ 
/*     */   public int getCount_user() {
/* 332 */     return this.count_user;
/*     */   }
/*     */ 
/*     */   public void setCount_user(int count_user) {
/* 336 */     this.count_user = count_user;
/*     */   }
/*     */ 
/*     */   public String getHistory_record_pic() {
/* 340 */     return this.history_record_pic;
/*     */   }
/*     */ 
/*     */   public void setHistory_record_pic(String history_record_pic) {
/* 344 */     this.history_record_pic = history_record_pic;
/*     */   }
/*     */ 
/*     */   public String getHistory_record_text() {
/* 348 */     return this.history_record_text;
/*     */   }
/*     */ 
/*     */   public void setHistory_record_text(String history_record_text) {
/* 352 */     this.history_record_text = history_record_text;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.SubjectBean
 * JD-Core Version:    0.6.2
 */