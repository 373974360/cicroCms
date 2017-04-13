/*    */ package com.cicro.wcm.bean.zwgk.ysqgk;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class YsqgkPhrasalBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 830170940056984419L;
/*    */   private int gph_id;
/* 18 */   private String gph_title = "";
/* 19 */   private String gph_content = "";
/* 20 */   private int gph_type = 0;
/* 21 */   private int sort_id = 0;
/*    */ 
/*    */   public int getGph_id()
/*    */   {
/* 25 */     return this.gph_id;
/*    */   }
/*    */   public String getGph_title() {
/* 28 */     return this.gph_title;
/*    */   }
/*    */   public String getGph_content() {
/* 31 */     return this.gph_content;
/*    */   }
/*    */   public int getGph_type() {
/* 34 */     return this.gph_type;
/*    */   }
/*    */   public int getSort_id() {
/* 37 */     return this.sort_id;
/*    */   }
/*    */   public void setGph_id(int gphId) {
/* 40 */     this.gph_id = gphId;
/*    */   }
/*    */   public void setGph_title(String gphTitle) {
/* 43 */     this.gph_title = gphTitle;
/*    */   }
/*    */   public void setGph_content(String gphContent) {
/* 46 */     this.gph_content = gphContent;
/*    */   }
/*    */   public void setGph_type(int gphType) {
/* 49 */     this.gph_type = gphType;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 52 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.ysqgk.YsqgkPhrasalBean
 * JD-Core Version:    0.6.2
 */