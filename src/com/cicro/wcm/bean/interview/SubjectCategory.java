/*     */ package com.cicro.wcm.bean.interview;
/*     */ 
/*     */ import com.cicro.wcm.services.lable.data.IBean;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubjectCategory
/*     */   implements IBean, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 961774871963305808L;
/*     */   private int id;
/*  24 */   private String category_id = "";
/*  25 */   private String category_name = "";
/*  26 */   private String description = "";
/*  27 */   private int publish_status = 0;
/*  28 */   private String publish_time = "";
/*  29 */   private int is_grade = 0;
/*  30 */   private int is_comment = 0;
/*  31 */   private int is_com_audit = 0;
/*  32 */   private int is_com_filter = 0;
/*  33 */   private int is_p_audit = 0;
/*  34 */   private int is_p_filter = 0;
/*  35 */   private int is_show_text = 1;
/*  36 */   private int is_register = 0;
/*  37 */   private int is_t_flink = 0;
/*  38 */   private int is_t_keyw = 0;
/*  39 */   private int is_t_audit = 1;
/*  40 */   private String m_forecast_path = "";
/*  41 */   private String m_hlist_path = "";
/*  42 */   private String m_on_path = "";
/*  43 */   private String m_h_path = "";
/*  44 */   private String m_rlist_path = "";
/*  45 */   private String m_rcontent_list = "";
/*  46 */   private String add_time = "";
/*  47 */   private String add_user = "";
/*  48 */   private String update_time = "";
/*  49 */   private String update_user = "";
/*  50 */   private String user_name = "";
/*  51 */   private int is_delete = 0;
/*  52 */   private String site_id = "";
/*     */ 
/*  54 */   public String getSite_id() { return this.site_id; }
/*     */ 
/*     */   public void setSite_id(String siteId)
/*     */   {
/*  58 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public Map toMap()
/*     */   {
/*  64 */     Map m = new HashMap();
/*  65 */     m.put("id", Integer.valueOf(this.id));
/*  66 */     m.put("category_id", this.category_id);
/*  67 */     m.put("category_name", this.category_name);
/*  68 */     m.put("description", this.description);
/*  69 */     m.put("publish_status", Integer.valueOf(this.publish_status));
/*  70 */     m.put("is_grade", Integer.valueOf(this.is_grade));
/*  71 */     m.put("is_comment", Integer.valueOf(this.is_comment));
/*  72 */     m.put("description", this.description);
/*  73 */     m.put("is_register", Integer.valueOf(this.is_register));
/*  74 */     m.put("is_com_audit", Integer.valueOf(this.is_com_audit));
/*  75 */     m.put("is_com_filter", Integer.valueOf(this.is_com_filter));
/*  76 */     m.put("is_p_audit", Integer.valueOf(this.is_p_audit));
/*  77 */     m.put("is_p_filter", Integer.valueOf(this.is_p_filter));
/*  78 */     m.put("is_show_text", Integer.valueOf(this.is_show_text));
/*  79 */     m.put("is_register", Integer.valueOf(this.is_register));
/*  80 */     m.put("is_t_flink", Integer.valueOf(this.is_t_flink));
/*  81 */     m.put("is_t_keyw", Integer.valueOf(this.is_t_keyw));
/*  82 */     m.put("is_t_audit", Integer.valueOf(this.is_t_audit));
/*  83 */     m.put("m_forecast_path", this.m_forecast_path);
/*  84 */     m.put("m_hlist_path", this.m_hlist_path);
/*     */ 
/*  86 */     m.put("m_on_path", this.m_on_path);
/*  87 */     m.put("m_h_path", this.m_h_path);
/*  88 */     m.put("m_rlist_path", this.m_rlist_path);
/*  89 */     m.put("m_rcontent_list", this.m_rcontent_list);
/*  90 */     return m;
/*     */   }
/*     */ 
/*     */   public String getAdd_time() {
/*  94 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String add_time) {
/*  97 */     this.add_time = add_time;
/*     */   }
/*     */   public String getAdd_user() {
/* 100 */     return this.add_user;
/*     */   }
/*     */   public void setAdd_user(String add_user) {
/* 103 */     this.add_user = add_user;
/*     */   }
/*     */   public String getCategory_id() {
/* 106 */     return this.category_id;
/*     */   }
/*     */   public void setCategory_id(String category_id) {
/* 109 */     this.category_id = category_id;
/*     */   }
/*     */   public String getCategory_name() {
/* 112 */     return this.category_name;
/*     */   }
/*     */   public void setCategory_name(String category_name) {
/* 115 */     this.category_name = category_name;
/*     */   }
/*     */   public String getDescription() {
/* 118 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/* 121 */     this.description = description;
/*     */   }
/*     */   public int getId() {
/* 124 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 127 */     this.id = id;
/*     */   }
/*     */   public int getIs_com_audit() {
/* 130 */     return this.is_com_audit;
/*     */   }
/*     */   public void setIs_com_audit(int is_com_audit) {
/* 133 */     this.is_com_audit = is_com_audit;
/*     */   }
/*     */   public int getIs_com_filter() {
/* 136 */     return this.is_com_filter;
/*     */   }
/*     */   public void setIs_com_filter(int is_com_filter) {
/* 139 */     this.is_com_filter = is_com_filter;
/*     */   }
/*     */   public int getIs_comment() {
/* 142 */     return this.is_comment;
/*     */   }
/*     */   public void setIs_comment(int is_comment) {
/* 145 */     this.is_comment = is_comment;
/*     */   }
/*     */   public int getIs_delete() {
/* 148 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int is_delete) {
/* 151 */     this.is_delete = is_delete;
/*     */   }
/*     */   public int getIs_grade() {
/* 154 */     return this.is_grade;
/*     */   }
/*     */   public void setIs_grade(int is_grade) {
/* 157 */     this.is_grade = is_grade;
/*     */   }
/*     */   public int getIs_p_audit() {
/* 160 */     return this.is_p_audit;
/*     */   }
/*     */   public void setIs_p_audit(int is_p_audit) {
/* 163 */     this.is_p_audit = is_p_audit;
/*     */   }
/*     */   public int getIs_p_filter() {
/* 166 */     return this.is_p_filter;
/*     */   }
/*     */   public void setIs_p_filter(int is_p_filter) {
/* 169 */     this.is_p_filter = is_p_filter;
/*     */   }
/*     */   public int getIs_register() {
/* 172 */     return this.is_register;
/*     */   }
/*     */   public void setIs_register(int is_register) {
/* 175 */     this.is_register = is_register;
/*     */   }
/*     */   public int getIs_show_text() {
/* 178 */     return this.is_show_text;
/*     */   }
/*     */   public void setIs_show_text(int is_show_text) {
/* 181 */     this.is_show_text = is_show_text;
/*     */   }
/*     */   public int getIs_t_audit() {
/* 184 */     return this.is_t_audit;
/*     */   }
/*     */   public void setIs_t_audit(int is_t_audit) {
/* 187 */     this.is_t_audit = is_t_audit;
/*     */   }
/*     */   public int getIs_t_flink() {
/* 190 */     return this.is_t_flink;
/*     */   }
/*     */   public void setIs_t_flink(int is_t_flink) {
/* 193 */     this.is_t_flink = is_t_flink;
/*     */   }
/*     */   public int getIs_t_keyw() {
/* 196 */     return this.is_t_keyw;
/*     */   }
/*     */   public void setIs_t_keyw(int is_t_keyw) {
/* 199 */     this.is_t_keyw = is_t_keyw;
/*     */   }
/*     */   public String getM_forecast_path() {
/* 202 */     return this.m_forecast_path;
/*     */   }
/*     */   public void setM_forecast_path(String m_forecast_path) {
/* 205 */     this.m_forecast_path = m_forecast_path;
/*     */   }
/*     */   public String getM_h_path() {
/* 208 */     return this.m_h_path;
/*     */   }
/*     */   public void setM_h_path(String m_h_path) {
/* 211 */     this.m_h_path = m_h_path;
/*     */   }
/*     */   public String getM_hlist_path() {
/* 214 */     return this.m_hlist_path;
/*     */   }
/*     */   public void setM_hlist_path(String m_hlist_path) {
/* 217 */     this.m_hlist_path = m_hlist_path;
/*     */   }
/*     */   public String getM_on_path() {
/* 220 */     return this.m_on_path;
/*     */   }
/*     */   public void setM_on_path(String m_on_path) {
/* 223 */     this.m_on_path = m_on_path;
/*     */   }
/*     */   public String getM_rcontent_list() {
/* 226 */     return this.m_rcontent_list;
/*     */   }
/*     */   public void setM_rcontent_list(String m_rcontent_list) {
/* 229 */     this.m_rcontent_list = m_rcontent_list;
/*     */   }
/*     */   public String getM_rlist_path() {
/* 232 */     return this.m_rlist_path;
/*     */   }
/*     */   public void setM_rlist_path(String m_rlist_path) {
/* 235 */     this.m_rlist_path = m_rlist_path;
/*     */   }
/*     */   public int getPublish_status() {
/* 238 */     return this.publish_status;
/*     */   }
/*     */   public void setPublish_status(int publish_status) {
/* 241 */     this.publish_status = publish_status;
/*     */   }
/*     */   public String getPublish_time() {
/* 244 */     return this.publish_time;
/*     */   }
/*     */   public void setPublish_time(String publish_time) {
/* 247 */     this.publish_time = publish_time;
/*     */   }
/*     */   public String getUpdate_time() {
/* 250 */     return this.update_time;
/*     */   }
/*     */   public void setUpdate_time(String update_time) {
/* 253 */     this.update_time = update_time;
/*     */   }
/*     */   public String getUpdate_user() {
/* 256 */     return this.update_user;
/*     */   }
/*     */   public void setUpdate_user(String update_user) {
/* 259 */     this.update_user = update_user;
/*     */   }
/*     */   public String getUser_name() {
/* 262 */     return this.user_name;
/*     */   }
/*     */   public void setUser_name(String user_name) {
/* 265 */     this.user_name = user_name;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.SubjectCategory
 * JD-Core Version:    0.6.2
 */