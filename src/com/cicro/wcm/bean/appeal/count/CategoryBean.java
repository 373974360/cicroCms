/*    */ package com.cicro.wcm.bean.appeal.count;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class CategoryBean
/*    */ {
/*  6 */   private String category_id = "";
/*  7 */   private String category_name = "";
/*  8 */   private String category_type = "sub";
/*    */   private String p_id;
/*    */   private List handle_list;
/*    */   private List child_list;
/*    */ 
/*    */   public String getCategory_id()
/*    */   {
/* 13 */     return this.category_id;
/*    */   }
/*    */   public void setCategory_id(String category_id) {
/* 16 */     this.category_id = category_id;
/*    */   }
/*    */   public String getCategory_name() {
/* 19 */     return this.category_name;
/*    */   }
/*    */   public void setCategory_name(String category_name) {
/* 22 */     this.category_name = category_name;
/*    */   }
/*    */   public List getChild_list() {
/* 25 */     return this.child_list;
/*    */   }
/*    */   public void setChild_list(List child_list) {
/* 28 */     this.child_list = child_list;
/*    */   }
/*    */   public List getHandle_list() {
/* 31 */     return this.handle_list;
/*    */   }
/*    */   public void setHandle_list(List handle_list) {
/* 34 */     this.handle_list = handle_list;
/*    */   }
/*    */   public String getCategory_type() {
/* 37 */     return this.category_type;
/*    */   }
/*    */   public void setCategory_type(String category_type) {
/* 40 */     this.category_type = category_type;
/*    */   }
/*    */   public String getP_id() {
/* 43 */     return this.p_id;
/*    */   }
/*    */   public void setP_id(String pId) {
/* 46 */     this.p_id = pId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.count.CategoryBean
 * JD-Core Version:    0.6.2
 */