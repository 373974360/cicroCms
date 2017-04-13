/*    */ package com.cicro.wcm.bean.appeal.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6320202942203932698L;
/*    */   private int cat_id;
/* 11 */   private int parent_id = 0;
/*    */   private String cat_cname;
/*    */   private String cat_position;
/*    */   private int cat_level;
/* 15 */   private int sort_id = 0;
/*    */ 
/*    */   public int getCat_id()
/*    */   {
/* 19 */     return this.cat_id;
/*    */   }
/*    */   public int getParent_id() {
/* 22 */     return this.parent_id;
/*    */   }
/*    */   public String getCat_cname() {
/* 25 */     return this.cat_cname;
/*    */   }
/*    */   public String getCat_position() {
/* 28 */     return this.cat_position;
/*    */   }
/*    */   public int getCat_level() {
/* 31 */     return this.cat_level;
/*    */   }
/*    */   public int getSort_id() {
/* 34 */     return this.sort_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 37 */     this.cat_id = catId;
/*    */   }
/*    */   public void setParent_id(int parentId) {
/* 40 */     this.parent_id = parentId;
/*    */   }
/*    */   public void setCat_cname(String catCname) {
/* 43 */     this.cat_cname = catCname;
/*    */   }
/*    */   public void setCat_position(String catPosition) {
/* 46 */     this.cat_position = catPosition;
/*    */   }
/*    */   public void setCat_level(int catLevel) {
/* 49 */     this.cat_level = catLevel;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 52 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.category.CategoryBean
 * JD-Core Version:    0.6.2
 */