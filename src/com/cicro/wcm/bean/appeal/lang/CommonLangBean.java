/*    */ package com.cicro.wcm.bean.appeal.lang;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CommonLangBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2936891631036288499L;
/* 12 */   private String ph_id = "";
/* 13 */   private String ph_title = "";
/* 14 */   private String ph_content = "";
/* 15 */   private int ph_type = 0;
/* 16 */   private int sort_id = 999;
/*    */ 
/*    */   public String getPh_id() {
/* 19 */     return this.ph_id;
/*    */   }
/*    */   public String getPh_title() {
/* 22 */     return this.ph_title;
/*    */   }
/*    */   public String getPh_content() {
/* 25 */     return this.ph_content;
/*    */   }
/*    */   public int getPh_type() {
/* 28 */     return this.ph_type;
/*    */   }
/*    */   public int getSort_id() {
/* 31 */     return this.sort_id;
/*    */   }
/*    */ 
/*    */   public void setPh_id(String phId) {
/* 35 */     this.ph_id = phId;
/*    */   }
/*    */   public void setPh_title(String phTitle) {
/* 38 */     this.ph_title = phTitle;
/*    */   }
/*    */   public void setPh_content(String phContent) {
/* 41 */     this.ph_content = phContent;
/*    */   }
/*    */   public void setPh_type(int phType) {
/* 44 */     this.ph_type = phType;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 47 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.lang.CommonLangBean
 * JD-Core Version:    0.6.2
 */