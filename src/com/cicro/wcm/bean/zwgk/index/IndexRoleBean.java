/*    */ package com.cicro.wcm.bean.zwgk.index;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class IndexRoleBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -39355223051427063L;
/*    */   private int id;
/* 16 */   private String ir_id = "";
/* 17 */   private String ir_item = "";
/* 18 */   private String ir_value = "";
/* 19 */   private String ir_space = "";
/*    */   private int is_valid;
/*    */   private int sort_id;
/*    */   private int ir_type;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 26 */     return this.id;
/*    */   }
/*    */   public String getIr_id() {
/* 29 */     return this.ir_id;
/*    */   }
/*    */   public String getIr_item() {
/* 32 */     return this.ir_item;
/*    */   }
/*    */   public String getIr_value() {
/* 35 */     return this.ir_value;
/*    */   }
/*    */   public String getIr_space() {
/* 38 */     return this.ir_space;
/*    */   }
/*    */   public int getIs_valid() {
/* 41 */     return this.is_valid;
/*    */   }
/*    */   public int getSort_id() {
/* 44 */     return this.sort_id;
/*    */   }
/*    */   public int getIr_type() {
/* 47 */     return this.ir_type;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 52 */     this.id = id;
/*    */   }
/*    */   public void setIr_id(String irId) {
/* 55 */     this.ir_id = irId;
/*    */   }
/*    */   public void setIr_item(String irItem) {
/* 58 */     this.ir_item = irItem;
/*    */   }
/*    */   public void setIr_value(String irValue) {
/* 61 */     this.ir_value = irValue;
/*    */   }
/*    */   public void setIr_space(String irSpace) {
/* 64 */     this.ir_space = irSpace;
/*    */   }
/*    */   public void setIs_valid(int isValid) {
/* 67 */     this.is_valid = isValid;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 70 */     this.sort_id = sortId;
/*    */   }
/*    */   public void setIr_type(int irType) {
/* 73 */     this.ir_type = irType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.index.IndexRoleBean
 * JD-Core Version:    0.6.2
 */