/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SurveySuvItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1577994211193164622L;
/* 19 */   private String survey_id = "";
/* 20 */   private String subject_id = "";
/* 21 */   private String item_id = "";
/* 22 */   private String item_name = "";
/* 23 */   private int is_text = 0;
/* 24 */   private String text_value = "";
/* 25 */   private int sort = 0;
/* 26 */   private List<SurveyChildItem> childList = new ArrayList();
/*    */ 
/* 28 */   public int getIs_text() { return this.is_text; }
/*    */ 
/*    */   public void setIs_text(int is_text) {
/* 31 */     this.is_text = is_text;
/*    */   }
/*    */ 
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
/*    */   public int getSort() {
/* 47 */     return this.sort;
/*    */   }
/*    */   public void setSort(int sort) {
/* 50 */     this.sort = sort;
/*    */   }
/*    */   public String getSubject_id() {
/* 53 */     return this.subject_id;
/*    */   }
/*    */   public void setSubject_id(String subject_id) {
/* 56 */     this.subject_id = subject_id;
/*    */   }
/*    */   public String getSurvey_id() {
/* 59 */     return this.survey_id;
/*    */   }
/*    */   public void setSurvey_id(String survey_id) {
/* 62 */     this.survey_id = survey_id;
/*    */   }
/*    */   public String getText_value() {
/* 65 */     return this.text_value;
/*    */   }
/*    */   public void setText_value(String text_value) {
/* 68 */     this.text_value = text_value;
/*    */   }
/*    */   public List<SurveyChildItem> getChildList() {
/* 71 */     return this.childList;
/*    */   }
/*    */   public void setChildList(List<SurveyChildItem> childList) {
/* 74 */     this.childList = childList;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveySuvItem
 * JD-Core Version:    0.6.2
 */