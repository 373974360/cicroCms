/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SurveyCount
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2648767611181549872L;
/*  8 */   private String category_id = "";
/*  9 */   private String c_name = "";
/* 10 */   private String s_id = "";
/* 11 */   private String s_name = "";
/* 12 */   private int sur_count = 0;
/* 13 */   private int publish_count = 0;
/* 14 */   private int subject_count = 0;
/* 15 */   private int answer_count = 0;
/*    */ 
/* 17 */   public int getAnswer_count() { return this.answer_count; }
/*    */ 
/*    */   public void setAnswer_count(int answer_count) {
/* 20 */     this.answer_count = answer_count;
/*    */   }
/*    */   public String getCategory_id() {
/* 23 */     return this.category_id;
/*    */   }
/*    */   public void setCategory_id(String category_id) {
/* 26 */     this.category_id = category_id;
/*    */   }
/*    */ 
/*    */   public String getC_name() {
/* 30 */     return this.c_name;
/*    */   }
/*    */   public void setC_name(String c_name) {
/* 33 */     this.c_name = c_name;
/*    */   }
/*    */   public int getPublish_count() {
/* 36 */     return this.publish_count;
/*    */   }
/*    */   public void setPublish_count(int publish_count) {
/* 39 */     this.publish_count = publish_count;
/*    */   }
/*    */   public String getS_id() {
/* 42 */     return this.s_id;
/*    */   }
/*    */   public void setS_id(String s_id) {
/* 45 */     this.s_id = s_id;
/*    */   }
/*    */   public String getS_name() {
/* 48 */     return this.s_name;
/*    */   }
/*    */   public void setS_name(String s_name) {
/* 51 */     this.s_name = s_name;
/*    */   }
/*    */   public int getSubject_count() {
/* 54 */     return this.subject_count;
/*    */   }
/*    */   public void setSubject_count(int subject_count) {
/* 57 */     this.subject_count = subject_count;
/*    */   }
/*    */   public int getSur_count() {
/* 60 */     return this.sur_count;
/*    */   }
/*    */   public void setSur_count(int sur_count) {
/* 63 */     this.sur_count = sur_count;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveyCount
 * JD-Core Version:    0.6.2
 */