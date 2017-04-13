/*     */ package com.cicro.wcm.bean.comment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CommentSet
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7517668686831997255L;
/*     */   private int id;
/*     */   private String app_id;
/*     */   private String site_id;
/*     */   private String is_public;
/*     */   private String com_prefix;
/*     */   private String is_need;
/*     */   private String is_code;
/*     */   private String time_spacer;
/*     */   private String ip_time;
/*     */   private int pass_size;
/*     */   private String tact_word;
/*  24 */   private List<String> tactList = new ArrayList();
/*     */ 
/*     */   public int getId() {
/*  27 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  30 */     this.id = id;
/*     */   }
/*     */   public String getSite_id() {
/*  33 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/*  36 */     this.site_id = siteId;
/*     */   }
/*     */   public String getIs_public() {
/*  39 */     return this.is_public;
/*     */   }
/*     */   public void setIs_public(String isPublic) {
/*  42 */     this.is_public = isPublic;
/*     */   }
/*     */   public String getIs_need() {
/*  45 */     return this.is_need;
/*     */   }
/*     */   public void setIs_need(String isNeed) {
/*  48 */     this.is_need = isNeed;
/*     */   }
/*     */   public String getIs_code() {
/*  51 */     return this.is_code;
/*     */   }
/*     */   public void setIs_code(String isCode) {
/*  54 */     this.is_code = isCode;
/*     */   }
/*     */   public String getTime_spacer() {
/*  57 */     return this.time_spacer;
/*     */   }
/*     */   public void setTime_spacer(String timeSpacer) {
/*  60 */     this.time_spacer = timeSpacer;
/*     */   }
/*     */   public String getIp_time() {
/*  63 */     return this.ip_time;
/*     */   }
/*     */   public void setIp_time(String ipTime) {
/*  66 */     this.ip_time = ipTime;
/*     */   }
/*     */   public int getPass_size() {
/*  69 */     return this.pass_size;
/*     */   }
/*     */   public void setPass_size(int passSize) {
/*  72 */     this.pass_size = passSize;
/*     */   }
/*     */   public String getTact_word() {
/*  75 */     return nullToString(this.tact_word);
/*     */   }
/*     */   public void setTact_word(String tactWord) {
/*  78 */     this.tact_word = tactWord;
/*     */   }
/*     */   public String getApp_id() {
/*  81 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/*  84 */     this.app_id = appId;
/*     */   }
/*     */   public String getCom_prefix() {
/*  87 */     return nullToString(this.com_prefix);
/*     */   }
/*     */   public void setCom_prefix(String comPrefix) {
/*  90 */     this.com_prefix = comPrefix;
/*     */   }
/*     */ 
/*     */   public static String nullToString(String str)
/*     */   {
/*  95 */     if ((str == null) || ("null".equals(str))) {
/*  96 */       str = "";
/*     */     }
/*  98 */     return str;
/*     */   }
/*     */   public List<String> getTactList() {
/* 101 */     return this.tactList;
/*     */   }
/*     */   public void setTactList(List<String> tactList) {
/* 104 */     this.tactList = tactList;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.comment.CommentSet
 * JD-Core Version:    0.6.2
 */