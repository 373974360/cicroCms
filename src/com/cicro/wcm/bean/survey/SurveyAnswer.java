/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SurveyAnswer
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8517048204317594208L;
/*    */   private int id;
/* 20 */   private String answer_id = "";
/* 21 */   private String s_id = "";
/* 22 */   private String ip = "";
/* 23 */   private String answer_time = "";
/* 24 */   private int operate_time = 0;
/* 25 */   private String user_name = "";
/* 26 */   private String item_text = "";
/* 27 */   private List<SurveyAnswerItem> item_list = new ArrayList();
/*    */ 
/* 29 */   public String getAnswer_time() { return this.answer_time; }
/*    */ 
/*    */   public void setAnswer_time(String answer_time) {
/* 32 */     this.answer_time = answer_time;
/*    */   }
/*    */   public String getIp() {
/* 35 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 38 */     this.ip = ip;
/*    */   }
/*    */   public int getOperate_time() {
/* 41 */     return this.operate_time;
/*    */   }
/*    */   public void setOperate_time(int operate_time) {
/* 44 */     this.operate_time = operate_time;
/*    */   }
/*    */   public String getS_id() {
/* 47 */     return this.s_id;
/*    */   }
/*    */   public void setS_id(String s_id) {
/* 50 */     this.s_id = s_id;
/*    */   }
/*    */   public String getUser_name() {
/* 53 */     return this.user_name;
/*    */   }
/*    */   public void setUser_name(String user_name) {
/* 56 */     this.user_name = user_name;
/*    */   }
/*    */   public List<SurveyAnswerItem> getItem_list() {
/* 59 */     return this.item_list;
/*    */   }
/*    */   public void setItem_list(List<SurveyAnswerItem> item_list) {
/* 62 */     this.item_list = item_list;
/*    */   }
/*    */   public int getId() {
/* 65 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 68 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getAnswer_id() {
/* 72 */     return this.answer_id;
/*    */   }
/*    */   public void setAnswer_id(String answer_id) {
/* 75 */     this.answer_id = answer_id;
/*    */   }
/*    */   public String getItem_text() {
/* 78 */     return this.item_text;
/*    */   }
/*    */   public void setItem_text(String item_text) {
/* 81 */     this.item_text = item_text;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveyAnswer
 * JD-Core Version:    0.6.2
 */