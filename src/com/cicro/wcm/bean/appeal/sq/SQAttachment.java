/*    */ package com.cicro.wcm.bean.appeal.sq;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SQAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8108138689620847745L;
/*  8 */   private int sq_id = 0;
/*  9 */   private String att_name = "";
/* 10 */   private String att_path = "";
/* 11 */   private int relevance_type = 0;
/*    */ 
/* 13 */   public int getSq_id() { return this.sq_id; }
/*    */ 
/*    */   public void setSq_id(int sqId) {
/* 16 */     this.sq_id = sqId;
/*    */   }
/*    */   public String getAtt_name() {
/* 19 */     return this.att_name;
/*    */   }
/*    */   public void setAtt_name(String attName) {
/* 22 */     this.att_name = attName;
/*    */   }
/*    */   public String getAtt_path() {
/* 25 */     return this.att_path;
/*    */   }
/*    */   public void setAtt_path(String attPath) {
/* 28 */     this.att_path = attPath;
/*    */   }
/*    */   public int getRelevance_type() {
/* 31 */     return this.relevance_type;
/*    */   }
/*    */   public void setRelevance_type(int relevanceType) {
/* 34 */     this.relevance_type = relevanceType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.sq.SQAttachment
 * JD-Core Version:    0.6.2
 */