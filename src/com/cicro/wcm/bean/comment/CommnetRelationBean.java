/*    */ package com.cicro.wcm.bean.comment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CommnetRelationBean
/*    */   implements Serializable
/*    */ {
/*    */   private int id;
/*    */   private int comment_id;
/*    */   private String from_list;
/*    */   private String from_list1;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 16 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 20 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public int getComment_id() {
/* 24 */     return this.comment_id;
/*    */   }
/*    */ 
/*    */   public void setComment_id(int comment_id) {
/* 28 */     this.comment_id = comment_id;
/*    */   }
/*    */ 
/*    */   public String getFrom_list() {
/* 32 */     return this.from_list;
/*    */   }
/*    */ 
/*    */   public void setFrom_list(String from_list) {
/* 36 */     this.from_list = from_list;
/*    */   }
/*    */ 
/*    */   public String getFrom_list1() {
/* 40 */     return this.from_list1;
/*    */   }
/*    */ 
/*    */   public void setFrom_list1(String from_list1) {
/* 44 */     this.from_list1 = from_list1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.comment.CommnetRelationBean
 * JD-Core Version:    0.6.2
 */