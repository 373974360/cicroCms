/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SurveyChildItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4464549401179900173L;
/* 20 */   private String subject_id = "";
/* 21 */   private String item_id = "";
/* 22 */   private int item_num = 0;
/* 23 */   private String item_name = "";
/* 24 */   private int is_text = 0;
/* 25 */   private String text_value = "";
/* 26 */   private int score = 0;
/* 27 */   private int sort = 0;
/*    */ 
/* 29 */   public int getIs_text() { return this.is_text; }
/*    */ 
/*    */   public void setIs_text(int is_text) {
/* 32 */     this.is_text = is_text;
/*    */   }
/*    */   public String getItem_id() {
/* 35 */     return this.item_id;
/*    */   }
/*    */   public void setItem_id(String item_id) {
/* 38 */     this.item_id = item_id;
/*    */   }
/*    */   public String getItem_name() {
/* 41 */     return this.item_name;
/*    */   }
/*    */   public void setItem_name(String item_name) {
/* 44 */     this.item_name = item_name;
/*    */   }
/*    */   public int getItem_num() {
/* 47 */     return this.item_num;
/*    */   }
/*    */   public void setItem_num(int item_num) {
/* 50 */     this.item_num = item_num;
/*    */   }
/*    */   public int getSort() {
/* 53 */     return this.sort;
/*    */   }
/*    */   public void setSort(int sort) {
/* 56 */     this.sort = sort;
/*    */   }
/*    */   public String getText_value() {
/* 59 */     return this.text_value;
/*    */   }
/*    */   public void setText_value(String text_value) {
/* 62 */     this.text_value = text_value;
/*    */   }
/*    */   public String getSubject_id() {
/* 65 */     return this.subject_id;
/*    */   }
/*    */   public void setSubject_id(String subject_id) {
/* 68 */     this.subject_id = subject_id;
/*    */   }
/*    */   public int getScore() {
/* 71 */     return this.score;
/*    */   }
/*    */   public void setScore(int score) {
/* 74 */     this.score = score;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveyChildItem
 * JD-Core Version:    0.6.2
 */