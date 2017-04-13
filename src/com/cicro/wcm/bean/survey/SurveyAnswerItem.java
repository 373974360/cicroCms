/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SurveyAnswerItem
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1771792087511284215L;
/* 17 */   private String answer_id = "";
/* 18 */   private String s_id = "";
/* 19 */   private String item_id = "";
/* 20 */   private String item_value = "";
/* 21 */   private String item_text = "";
/*    */ 
/* 23 */   public String getItem_id() { return this.item_id; }
/*    */ 
/*    */   public void setItem_id(String item_id) {
/* 26 */     this.item_id = item_id;
/*    */   }
/*    */   public String getItem_text() {
/* 29 */     return this.item_text;
/*    */   }
/*    */   public void setItem_text(String item_text) {
/* 32 */     this.item_text = item_text;
/*    */   }
/*    */   public String getItem_value() {
/* 35 */     return this.item_value;
/*    */   }
/*    */   public void setItem_value(String item_value) {
/* 38 */     this.item_value = item_value;
/*    */   }
/*    */   public String getS_id() {
/* 41 */     return this.s_id;
/*    */   }
/*    */   public void setS_id(String s_id) {
/* 44 */     this.s_id = s_id;
/*    */   }
/*    */   public String getAnswer_id() {
/* 47 */     return this.answer_id;
/*    */   }
/*    */   public void setAnswer_id(String answer_id) {
/* 50 */     this.answer_id = answer_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.SurveyAnswerItem
 * JD-Core Version:    0.6.2
 */