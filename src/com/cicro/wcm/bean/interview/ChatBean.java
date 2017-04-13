/*     */ package com.cicro.wcm.bean.interview;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ChatBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3653264713070431625L;
/*     */   private int id;
/*  18 */   private String sub_id = "";
/*  19 */   private String chat_id = "";
/*  20 */   private String chat_user = "";
/*  21 */   private String content = "";
/*  22 */   private String put_time = "";
/*  23 */   private String ip = "";
/*  24 */   private int audit_status = 0;
/*  25 */   private String chat_type = "";
/*  26 */   private String chat_area = "";
/*  27 */   private int is_show = 0;
/*  28 */   private int index_num = 0;
/*  29 */   private String user_num = "";
/*     */ 
/*  31 */   public String getUser_num() { return this.user_num; }
/*     */ 
/*     */   public void setUser_num(String user_num) {
/*  34 */     this.user_num = user_num;
/*     */   }
/*     */   public int getIndex_num() {
/*  37 */     return this.index_num;
/*     */   }
/*     */   public void setIndex_num(int index_num) {
/*  40 */     this.index_num = index_num;
/*     */   }
/*     */   public int getAudit_status() {
/*  43 */     return this.audit_status;
/*     */   }
/*     */   public void setAudit_status(int audit_status) {
/*  46 */     this.audit_status = audit_status;
/*     */   }
/*     */   public String getChat_id() {
/*  49 */     return this.chat_id;
/*     */   }
/*     */   public void setChat_id(String chat_id) {
/*  52 */     this.chat_id = chat_id;
/*     */   }
/*     */   public String getChat_type() {
/*  55 */     return this.chat_type;
/*     */   }
/*     */   public void setChat_type(String chat_type) {
/*  58 */     this.chat_type = chat_type;
/*     */   }
/*     */   public String getChat_user() {
/*  61 */     return this.chat_user;
/*     */   }
/*     */   public void setChat_user(String chat_user) {
/*  64 */     this.chat_user = chat_user;
/*     */   }
/*     */   public String getContent() {
/*  67 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/*  70 */     this.content = content;
/*     */   }
/*     */   public int getId() {
/*  73 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  76 */     this.id = id;
/*     */   }
/*     */   public String getIp() {
/*  79 */     return this.ip;
/*     */   }
/*     */   public void setIp(String ip) {
/*  82 */     this.ip = ip;
/*     */   }
/*     */   public int getIs_show() {
/*  85 */     return this.is_show;
/*     */   }
/*     */   public void setIs_show(int is_show) {
/*  88 */     this.is_show = is_show;
/*     */   }
/*     */   public String getPut_time() {
/*  91 */     return this.put_time;
/*     */   }
/*     */   public void setPut_time(String put_time) {
/*  94 */     this.put_time = put_time;
/*     */   }
/*     */   public String getChat_area() {
/*  97 */     return this.chat_area;
/*     */   }
/*     */   public void setChat_area(String chat_area) {
/* 100 */     this.chat_area = chat_area;
/*     */   }
/*     */   public String getSub_id() {
/* 103 */     return this.sub_id;
/*     */   }
/*     */   public void setSub_id(String sub_id) {
/* 106 */     this.sub_id = sub_id;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.ChatBean
 * JD-Core Version:    0.6.2
 */