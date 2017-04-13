/*     */ package com.cicro.wcm.bean.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.services.lable.data.IBean;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SurveyBean
/*     */   implements IBean, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3100850564172389597L;
/*     */   private int id;
/*  22 */   private String s_id = "";
/*  23 */   private String category_id = "";
/*  24 */   private String c_name = "";
/*  25 */   private String s_name = "";
/*  26 */   private String description = "";
/*  27 */   private int is_register = 0;
/*  28 */   private int ip_fre = 1;
/*  29 */   private String start_time = "";
/*  30 */   private String end_time = "";
/*  31 */   private int is_show_subsort = 0;
/*  32 */   private String model_path = "";
/*  33 */   private String pic_path = "";
/*  34 */   private int show_result_status = 1;
/*  35 */   private String verdict = "";
/*  36 */   private String ip_restrict = "";
/*  37 */   private int publish_status = 0;
/*  38 */   private String publish_time = "";
/*  39 */   private int is_show_title = 0;
/*  40 */   private int is_show_result = 0;
/*  41 */   private String survey_content = "";
/*  42 */   private String add_time = "";
/*  43 */   private String add_user = "";
/*  44 */   private String update_time = "";
/*  45 */   private String update_user = "";
/*  46 */   private int Is_delete = 0;
/*  47 */   private int sort = 0;
/*  48 */   private String user_name = "";
/*  49 */   private int answer_counts = 0;
/*  50 */   private int recommend_flag = 0;
/*  51 */   private String recommend_time = "";
/*  52 */   private String spacing_interval = "";
/*  53 */   private String is_end = "0";
/*  54 */   private String site_id = "";
/*  55 */   private int is_checkcode = 0;
/*     */ 
/*     */   public int getIs_checkcode()
/*     */   {
/*  59 */     return this.is_checkcode;
/*     */   }
/*     */ 
/*     */   public void setIs_checkcode(int isCheckcode) {
/*  63 */     this.is_checkcode = isCheckcode;
/*     */   }
/*     */ 
/*     */   public String getSite_id() {
/*  67 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId) {
/*  71 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public Map<String, String> toMap() {
/*  75 */     Map m = new HashMap();
/*  76 */     m.put("id", this.id);
/*  77 */     m.put("s_id", this.s_id);
/*  78 */     m.put("category_id", this.category_id);
/*  79 */     m.put("s_name", this.s_name);
/*  80 */     m.put("start_time", this.start_time);
/*  81 */     m.put("end_time", this.end_time);
/*  82 */     m.put("c_name", this.c_name);
/*  83 */     m.put("description", this.description);
/*  84 */     m.put("is_register", this.is_register);
/*  85 */     m.put("ip_fre", this.ip_fre);
/*  86 */     m.put("model_path", this.model_path);
/*  87 */     m.put("show_result_status", this.show_result_status);
/*  88 */     m.put("ip_restrict", this.ip_restrict);
/*  89 */     m.put("is_show_result", this.is_show_result);
/*  90 */     m.put("survey_content", this.survey_content);
/*  91 */     m.put("is_end", getIs_end());
/*  92 */     return m;
/*     */   }
/*     */ 
/*     */   public String getIs_end()
/*     */   {
/*  97 */     if (!"".equals(this.end_time))
/*     */     {
/*  99 */       String current_date = DateUtil.getCurrentDate();
/* 100 */       if (!current_date.equals(this.end_time))
/*     */       {
/* 102 */         if (!DateUtil.compare_date(current_date, this.end_time))
/* 103 */           this.is_end = "1";
/*     */         else {
/* 105 */           this.is_end = "0";
/*     */         }
/*     */       }
/*     */       else {
/* 109 */         this.is_end = "0";
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 114 */       this.is_end = "0";
/*     */     }
/* 116 */     return this.is_end;
/*     */   }
/*     */ 
/*     */   public String getUser_name() {
/* 120 */     return this.user_name;
/*     */   }
/*     */   public void setUser_name(String user_name) {
/* 123 */     this.user_name = user_name;
/*     */   }
/*     */   public String getAdd_time() {
/* 126 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String add_time) {
/* 129 */     this.add_time = add_time;
/*     */   }
/*     */   public String getAdd_user() {
/* 132 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String add_user) {
/* 135 */     this.add_user = add_user;
/*     */   }
/*     */   public String getDescription() {
/* 138 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 141 */     this.description = description;
/*     */   }
/*     */   public String getEnd_time() {
/* 144 */     return this.end_time;
/*     */   }
/*     */   public void setEnd_time(String end_time) {
/* 147 */     this.end_time = end_time;
/*     */   }
/*     */   public int getId() {
/* 150 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 153 */     this.id = id;
/*     */   }
/*     */   public int getIp_fre() {
/* 156 */     return this.ip_fre;
/*     */   }
/*     */   public void setIp_fre(int ip_fre) {
/* 159 */     this.ip_fre = ip_fre;
/*     */   }
/*     */   public String getIp_restrict() {
/* 162 */     return this.ip_restrict;
/*     */   }
/*     */   public void setIp_restrict(String ip_restrict) {
/* 165 */     this.ip_restrict = ip_restrict;
/*     */   }
/*     */   public int getIs_delete() {
/* 168 */     return this.Is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 171 */     this.Is_delete = is_delete;
/*     */   }
/*     */   public int getIs_register() {
/* 174 */     return this.is_register;
/*     */   }
/*     */   public void setIs_register(int is_register) {
/* 177 */     this.is_register = is_register;
/*     */   }
/*     */   public String getModel_path() {
/* 180 */     return this.model_path;
/*     */   }
/*     */   public void setModel_path(String model_path) {
/* 183 */     this.model_path = model_path;
/*     */   }
/*     */   public int getPublish_status() {
/* 186 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publish_status) {
/* 189 */     this.publish_status = publish_status;
/*     */   }
/*     */   public String getS_id() {
/* 192 */     return this.s_id;
/*     */   }
/*     */   public void setS_id(String s_id) {
/* 195 */     this.s_id = s_id;
/*     */   }
/*     */   public String getS_name() {
/* 198 */     return this.s_name;
/*     */   }
/*     */   public void setS_name(String s_name) {
/* 201 */     this.s_name = s_name;
/*     */   }
/*     */   public int getSort() {
/* 204 */     return this.sort;
/*     */   }
/*     */   public void setSort(int sort) {
/* 207 */     this.sort = sort;
/*     */   }
/*     */   public String getStart_time() {
/* 210 */     return this.start_time;
/*     */   }
/*     */   public void setStart_time(String start_time) {
/* 213 */     this.start_time = start_time;
/*     */   }
/*     */   public String getUpdate_time() {
/* 216 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 219 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 222 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 225 */     this.update_user = update_user;
/*     */   }
/*     */   public String getPublish_time() {
/* 228 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publish_time) {
/* 231 */     this.publish_time = publish_time;
/*     */   }
/*     */   public int getIs_show_subsort() {
/* 234 */     return this.is_show_subsort;
/*     */   }
/*     */   public void setIs_show_subsort(int is_show_subsort) {
/* 237 */     this.is_show_subsort = is_show_subsort;
/*     */   }
/*     */   public String getSurvey_content() {
/* 240 */     return this.survey_content;
/*     */   }
/*     */   public void setSurvey_content(String survey_content) {
/* 243 */     this.survey_content = survey_content;
/*     */   }
/*     */   public int getAnswer_counts() {
/* 246 */     return this.answer_counts;
/*     */   }
/*     */   public void setAnswer_counts(int answer_counts) {
/* 249 */     this.answer_counts = answer_counts;
/*     */   }
/*     */   public int getIs_show_result() {
/* 252 */     return this.is_show_result;
/*     */   }
/*     */   public void setIs_show_result(int is_show_result) {
/* 255 */     this.is_show_result = is_show_result;
/*     */   }
/*     */   public int getIs_show_title() {
/* 258 */     return this.is_show_title;
/*     */   }
/*     */   public void setIs_show_title(int is_show_title) {
/* 261 */     this.is_show_title = is_show_title;
/*     */   }
/*     */   public String getCategory_id() {
/* 264 */     return this.category_id;
/*     */   }
/*     */   public void setCategory_id(String category_id) {
/* 267 */     this.category_id = category_id;
/*     */   }
/*     */   public String getPic_path() {
/* 270 */     return this.pic_path;
/*     */   }
/*     */   public void setPic_path(String pic_path) {
/* 273 */     this.pic_path = pic_path;
/*     */   }
/*     */   public int getShow_result_status() {
/* 276 */     return this.show_result_status;
/*     */   }
/*     */   public void setShow_result_status(int show_result_status) {
/* 279 */     this.show_result_status = show_result_status;
/*     */   }
/*     */   public String getVerdict() {
/* 282 */     return this.verdict;
/*     */   }
/*     */   public void setVerdict(String verdict) {
/* 285 */     this.verdict = verdict;
/*     */   }
/*     */   public String getC_name() {
/* 288 */     return this.c_name;
/*     */   }
/*     */   public void setC_name(String c_name) {
/* 291 */     this.c_name = c_name;
/*     */   }
/*     */ 
/*     */   public int getRecommend_flag() {
/* 295 */     return this.recommend_flag;
/*     */   }
/*     */ 
/*     */   public void setRecommend_flag(int recommend_flag) {
/* 299 */     this.recommend_flag = recommend_flag;
/*     */   }
/*     */ 
/*     */   public String getRecommend_time() {
/* 303 */     return this.recommend_time;
/*     */   }
/*     */ 
/*     */   public void setRecommend_time(String recommend_time) {
/* 307 */     this.recommend_time = recommend_time;
/*     */   }
/*     */ 
/*     */   public String getSpacing_interval() {
/* 311 */     return this.spacing_interval;
/*     */   }
/*     */ 
/*     */   public void setSpacing_interval(String spacing_interval) {
/* 315 */     this.spacing_interval = spacing_interval;
/*     */   }
/*     */ 
/*     */   public void setIs_end(String is_end) {
/* 319 */     this.is_end = is_end;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveyBean
 * JD-Core Version:    0.6.2
 */