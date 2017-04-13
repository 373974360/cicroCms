/*    */ package com.cicro.wcm.bean.interview;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SubjectCount
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3592364610328410655L;
/* 17 */   private String category_id = "";
/* 18 */   private String category_name = "";
/* 19 */   private String sub_id = "";
/* 20 */   private String sub_name = "";
/* 21 */   private int sub_count = 0;
/* 22 */   private int publish_count = 0;
/* 23 */   private int recommend_count = 0;
/* 24 */   private int user_count = 0;
/* 25 */   private int chat_count = 0;
/*    */ 
/* 27 */   public String getCategory_id() { return this.category_id; }
/*    */ 
/*    */   public void setCategory_id(String category_id) {
/* 30 */     this.category_id = category_id;
/*    */   }
/*    */   public String getCategory_name() {
/* 33 */     return this.category_name;
/*    */   }
/*    */   public void setCategory_name(String category_name) {
/* 36 */     this.category_name = category_name;
/*    */   }
/*    */   public int getChat_count() {
/* 39 */     return this.chat_count;
/*    */   }
/*    */   public void setChat_count(int chat_count) {
/* 42 */     this.chat_count = chat_count;
/*    */   }
/*    */   public int getPublish_count() {
/* 45 */     return this.publish_count;
/*    */   }
/*    */   public void setPublish_count(int publish_count) {
/* 48 */     this.publish_count = publish_count;
/*    */   }
/*    */   public int getRecommend_count() {
/* 51 */     return this.recommend_count;
/*    */   }
/*    */   public void setRecommend_count(int recommend_count) {
/* 54 */     this.recommend_count = recommend_count;
/*    */   }
/*    */   public int getSub_count() {
/* 57 */     return this.sub_count;
/*    */   }
/*    */   public void setSub_count(int sub_count) {
/* 60 */     this.sub_count = sub_count;
/*    */   }
/*    */   public String getSub_id() {
/* 63 */     return this.sub_id;
/*    */   }
/*    */   public void setSub_id(String sub_id) {
/* 66 */     this.sub_id = sub_id;
/*    */   }
/*    */   public String getSub_name() {
/* 69 */     return this.sub_name;
/*    */   }
/*    */   public void setSub_name(String sub_name) {
/* 72 */     this.sub_name = sub_name;
/*    */   }
/*    */   public int getUser_count() {
/* 75 */     return this.user_count;
/*    */   }
/*    */   public void setUser_count(int user_count) {
/* 78 */     this.user_count = user_count;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.SubjectCount
 * JD-Core Version:    0.6.2
 */