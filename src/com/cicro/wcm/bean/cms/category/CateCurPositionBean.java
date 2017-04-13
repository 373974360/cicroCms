/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CateCurPositionBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7767356558078529129L;
/*    */   private int cat_id;
/* 11 */   private String cat_cname = "";
/* 12 */   private String url = "";
/* 13 */   private String jump_url = "";
/*    */ 
/* 15 */   public String getJump_url() { return this.jump_url; }
/*    */ 
/*    */   public void setJump_url(String jumpUrl) {
/* 18 */     this.jump_url = jumpUrl;
/*    */   }
/*    */   public int getCat_id() {
/* 21 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 24 */     this.cat_id = catId;
/*    */   }
/*    */   public String getCat_cname() {
/* 27 */     return this.cat_cname;
/*    */   }
/*    */   public void setCat_cname(String catCname) {
/* 30 */     this.cat_cname = catCname;
/*    */   }
/*    */   public String getUrl() {
/* 33 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 36 */     this.url = url;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CateCurPositionBean
 * JD-Core Version:    0.6.2
 */