/*     */ package com.cicro.wcm.bean.cms.info;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GKInfoBean extends InfoBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 601951881592800267L;
/*  13 */   protected String gk_index = "";
/*  14 */   protected String gk_year = "";
/*  15 */   protected int gk_num = 0;
/*  16 */   protected String doc_no = "";
/*  17 */   protected String gk_file = "";
/*  18 */   protected String generate_dtime = "";
/*  19 */   protected String effect_dtime = "";
/*  20 */   protected String aboli_dtime = "";
/*  21 */   protected int topic_id = 0;
/*  22 */   protected String topic_name = "";
/*  23 */   protected int theme_id = 0;
/*  24 */   protected String theme_name = "";
/*  25 */   protected int serve_id = 0;
/*  26 */   protected String serve_name = "";
/*  27 */   protected String topic_key = "";
/*  28 */   protected String place_key = "";
/*  29 */   protected String language = "";
/*  30 */   protected String carrier_type = "";
/*  31 */   protected String gk_validity = "";
/*  32 */   protected String gk_format = "";
/*  33 */   protected String gk_way = "";
/*  34 */   protected int gk_type = 0;
/*  35 */   protected String gk_no_reason = "";
/*  36 */   protected String gk_time_limit = "";
/*  37 */   protected String gk_range = "";
/*  38 */   protected String gk_proc = "";
/*  39 */   protected String gk_duty_dept = "";
/*  40 */   protected String gk_input_dept = "";
/*  41 */   protected List<GKResFileBean> file_list = new ArrayList();
/*  42 */   protected String file_head = "";
/*  43 */   protected String gk_signer = "";
/*     */ 
/*     */   public String getGk_signer() {
/*  46 */     return this.gk_signer;
/*     */   }
/*     */   public void setGk_signer(String gkSigner) {
/*  49 */     this.gk_signer = gkSigner;
/*     */   }
/*     */   public String getFile_head() {
/*  52 */     return this.file_head;
/*     */   }
/*     */   public void setFile_head(String fileHead) {
/*  55 */     this.file_head = fileHead;
/*     */   }
/*     */   public String getGk_index() {
/*  58 */     return this.gk_index;
/*     */   }
/*     */   public void setGk_index(String gkIndex) {
/*  61 */     this.gk_index = gkIndex;
/*     */   }
/*     */   public String getGk_year() {
/*  64 */     return this.gk_year;
/*     */   }
/*     */   public void setGk_year(String gkYear) {
/*  67 */     this.gk_year = gkYear;
/*     */   }
/*     */   public int getGk_num() {
/*  70 */     return this.gk_num;
/*     */   }
/*     */   public void setGk_num(int gkNum) {
/*  73 */     this.gk_num = gkNum;
/*     */   }
/*     */   public List<GKResFileBean> getFile_list() {
/*  76 */     return this.file_list;
/*     */   }
/*     */   public void setFile_list(List<GKResFileBean> fileList) {
/*  79 */     this.file_list = fileList;
/*     */   }
/*     */   public String getDoc_no() {
/*  82 */     return this.doc_no;
/*     */   }
/*     */   public void setDoc_no(String docNo) {
/*  85 */     this.doc_no = docNo;
/*     */   }
/*     */   public String getGk_file() {
/*  88 */     return this.gk_file;
/*     */   }
/*     */   public void setGk_file(String gkFile) {
/*  91 */     this.gk_file = gkFile;
/*     */   }
/*     */   public String getGenerate_dtime() {
/*  94 */     return this.generate_dtime;
/*     */   }
/*     */   public void setGenerate_dtime(String generateDtime) {
/*  97 */     this.generate_dtime = generateDtime;
/*     */   }
/*     */   public String getEffect_dtime() {
/* 100 */     return this.effect_dtime;
/*     */   }
/*     */   public void setEffect_dtime(String effectDtime) {
/* 103 */     this.effect_dtime = effectDtime;
/*     */   }
/*     */   public String getAboli_dtime() {
/* 106 */     return this.aboli_dtime;
/*     */   }
/*     */   public void setAboli_dtime(String aboliDtime) {
/* 109 */     this.aboli_dtime = aboliDtime;
/*     */   }
/*     */   public int getTopic_id() {
/* 112 */     return this.topic_id;
/*     */   }
/*     */   public void setTopic_id(int topicId) {
/* 115 */     this.topic_id = topicId;
/*     */   }
/*     */   public String getTopic_name() {
/* 118 */     return this.topic_name;
/*     */   }
/*     */   public void setTopic_name(String topicName) {
/* 121 */     this.topic_name = topicName;
/*     */   }
/*     */   public int getTheme_id() {
/* 124 */     return this.theme_id;
/*     */   }
/*     */   public void setTheme_id(int themeId) {
/* 127 */     this.theme_id = themeId;
/*     */   }
/*     */   public String getTheme_name() {
/* 130 */     return this.theme_name;
/*     */   }
/*     */   public void setTheme_name(String themeName) {
/* 133 */     this.theme_name = themeName;
/*     */   }
/*     */   public int getServe_id() {
/* 136 */     return this.serve_id;
/*     */   }
/*     */   public void setServe_id(int serveId) {
/* 139 */     this.serve_id = serveId;
/*     */   }
/*     */   public String getServe_name() {
/* 142 */     return this.serve_name;
/*     */   }
/*     */   public void setServe_name(String serveName) {
/* 145 */     this.serve_name = serveName;
/*     */   }
/*     */   public String getTopic_key() {
/* 148 */     return this.topic_key;
/*     */   }
/*     */   public void setTopic_key(String topicKey) {
/* 151 */     this.topic_key = topicKey;
/*     */   }
/*     */   public String getPlace_key() {
/* 154 */     return this.place_key;
/*     */   }
/*     */   public void setPlace_key(String placeKey) {
/* 157 */     this.place_key = placeKey;
/*     */   }
/*     */   public String getLanguage() {
/* 160 */     return this.language;
/*     */   }
/*     */   public void setLanguage(String language) {
/* 163 */     this.language = language;
/*     */   }
/*     */   public String getCarrier_type() {
/* 166 */     return this.carrier_type;
/*     */   }
/*     */   public void setCarrier_type(String carrierType) {
/* 169 */     this.carrier_type = carrierType;
/*     */   }
/*     */   public String getGk_validity() {
/* 172 */     return this.gk_validity;
/*     */   }
/*     */   public void setGk_validity(String gkValidity) {
/* 175 */     this.gk_validity = gkValidity;
/*     */   }
/*     */   public String getGk_format() {
/* 178 */     return this.gk_format;
/*     */   }
/*     */   public void setGk_format(String gkFormat) {
/* 181 */     this.gk_format = gkFormat;
/*     */   }
/*     */   public String getGk_way() {
/* 184 */     return this.gk_way;
/*     */   }
/*     */   public void setGk_way(String gkWay) {
/* 187 */     this.gk_way = gkWay;
/*     */   }
/*     */   public int getGk_type() {
/* 190 */     return this.gk_type;
/*     */   }
/*     */   public void setGk_type(int gkType) {
/* 193 */     this.gk_type = gkType;
/*     */   }
/*     */   public String getGk_no_reason() {
/* 196 */     return this.gk_no_reason;
/*     */   }
/*     */   public void setGk_no_reason(String gkNoReason) {
/* 199 */     this.gk_no_reason = gkNoReason;
/*     */   }
/*     */   public String getGk_time_limit() {
/* 202 */     return this.gk_time_limit;
/*     */   }
/*     */   public void setGk_time_limit(String gkTimeLimit) {
/* 205 */     this.gk_time_limit = gkTimeLimit;
/*     */   }
/*     */   public String getGk_range() {
/* 208 */     return this.gk_range;
/*     */   }
/*     */   public void setGk_range(String gkRange) {
/* 211 */     this.gk_range = gkRange;
/*     */   }
/*     */   public String getGk_proc() {
/* 214 */     return this.gk_proc;
/*     */   }
/*     */   public void setGk_proc(String gkProc) {
/* 217 */     this.gk_proc = gkProc;
/*     */   }
/*     */   public String getGk_duty_dept() {
/* 220 */     return this.gk_duty_dept;
/*     */   }
/*     */   public void setGk_duty_dept(String gkDutyDept) {
/* 223 */     this.gk_duty_dept = gkDutyDept;
/*     */   }
/*     */   public String getGk_input_dept() {
/* 226 */     return this.gk_input_dept;
/*     */   }
/*     */   public void setGk_input_dept(String gkInputDept) {
/* 229 */     this.gk_input_dept = gkInputDept;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.GKInfoBean
 * JD-Core Version:    0.6.2
 */