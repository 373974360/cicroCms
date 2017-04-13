/*    */ package com.cicro.wcm.bean.appeal.sq;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SQCustom
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2840611058213274637L;
/* 10 */   private int cu_id = 0;
/* 11 */   private int model_id = 0;
/* 12 */   private int sq_id = 0;
/* 13 */   private String cu_key = "";
/* 14 */   private String cu_value = "";
/*    */ 
/* 16 */   public int getCu_id() { return this.cu_id; }
/*    */ 
/*    */   public void setCu_id(int cuId) {
/* 19 */     this.cu_id = cuId;
/*    */   }
/*    */   public int getModel_id() {
/* 22 */     return this.model_id;
/*    */   }
/*    */   public void setModel_id(int modelId) {
/* 25 */     this.model_id = modelId;
/*    */   }
/*    */   public int getSq_id() {
/* 28 */     return this.sq_id;
/*    */   }
/*    */   public void setSq_id(int sqId) {
/* 31 */     this.sq_id = sqId;
/*    */   }
/*    */   public String getCu_key() {
/* 34 */     return this.cu_key;
/*    */   }
/*    */   public void setCu_key(String cuKey) {
/* 37 */     this.cu_key = cuKey;
/*    */   }
/*    */   public String getCu_value() {
/* 40 */     return this.cu_value;
/*    */   }
/*    */   public void setCu_value(String cuValue) {
/* 43 */     this.cu_value = cuValue;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.sq.SQCustom
 * JD-Core Version:    0.6.2
 */