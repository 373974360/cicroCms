/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SurveySub
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3437139468940986426L;
/*    */   private int id;
/* 20 */   private String survey_id = "";
/* 21 */   private String subject_id = "";
/* 22 */   private String sub_name = "";
/* 23 */   private String subject_type = "";
/* 24 */   private String explains = "";
/* 25 */   private String item_value = "";
/* 26 */   private int is_required = 0;
/* 27 */   private int sort = 0;
/* 28 */   private List<SurveySuvItem> itemList = new ArrayList();
/*    */ 
/*    */   public String getExplains() {
/* 31 */     return this.explains;
/*    */   }
/*    */   public void setExplains(String explains) {
/* 34 */     this.explains = explains;
/*    */   }
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   public int getIs_required() {
/* 43 */     return this.is_required;
/*    */   }
/*    */   public void setIs_required(int is_required) {
/* 46 */     this.is_required = is_required;
/*    */   }
/*    */   public List<SurveySuvItem> getItemList() {
/* 49 */     return this.itemList;
/*    */   }
/*    */   public void setItemList(List<SurveySuvItem> itemList) {
/* 52 */     this.itemList = itemList;
/*    */   }
/*    */   public int getSort() {
/* 55 */     return this.sort;
/*    */   }
/*    */   public void setSort(int sort) {
/* 58 */     this.sort = sort;
/*    */   }
/*    */   public String getSub_name() {
/* 61 */     return this.sub_name;
/*    */   }
/*    */   public void setSub_name(String sub_name) {
/* 64 */     this.sub_name = sub_name;
/*    */   }
/*    */   public String getSubject_id() {
/* 67 */     return this.subject_id;
/*    */   }
/*    */   public void setSubject_id(String subject_id) {
/* 70 */     this.subject_id = subject_id;
/*    */   }
/*    */   public String getSubject_type() {
/* 73 */     return this.subject_type;
/*    */   }
/*    */   public void setSubject_type(String subject_type) {
/* 76 */     this.subject_type = subject_type;
/*    */   }
/*    */   public String getSurvey_id() {
/* 79 */     return this.survey_id;
/*    */   }
/*    */   public void setSurvey_id(String survey_id) {
/* 82 */     this.survey_id = survey_id;
/*    */   }
/*    */   public String getItem_value() {
/* 85 */     return this.item_value;
/*    */   }
/*    */   public void setItem_value(String item_value) {
/* 88 */     this.item_value = item_value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveySub
 * JD-Core Version:    0.6.2
 */