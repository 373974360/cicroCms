/*    */ package com.cicro.wcm.bean.appeal.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CPFrom
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8556711491953809992L;
/* 11 */   private int field_id = 1;
/* 12 */   private int model_id = 0;
/* 13 */   private String field_ename = "";
/* 14 */   private String field_cname = "";
/*    */ 
/* 16 */   public int getField_id() { return this.field_id; }
/*    */ 
/*    */   public void setField_id(int fieldId) {
/* 19 */     this.field_id = fieldId;
/*    */   }
/*    */   public int getModel_id() {
/* 22 */     return this.model_id;
/*    */   }
/*    */   public void setModel_id(int modelId) {
/* 25 */     this.model_id = modelId;
/*    */   }
/*    */   public String getField_ename() {
/* 28 */     return this.field_ename;
/*    */   }
/*    */   public void setField_ename(String fieldEname) {
/* 31 */     this.field_ename = fieldEname;
/*    */   }
/*    */   public String getField_cname() {
/* 34 */     return this.field_cname;
/*    */   }
/*    */   public void setField_cname(String fieldCname) {
/* 37 */     this.field_cname = fieldCname;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.model.CPFrom
 * JD-Core Version:    0.6.2
 */