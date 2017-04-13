/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CategorySharedBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8817861135453799241L;
/*  8 */   private String s_site_id = "";
/*  9 */   private int cat_id = 0;
/* 10 */   private String t_site_id = "";
/* 11 */   private int shared_type = 0;
/* 12 */   private int range_type = 0;
/*    */ 
/* 14 */   public String getS_site_id() { return this.s_site_id; }
/*    */ 
/*    */   public void setS_site_id(String sSiteId) {
/* 17 */     this.s_site_id = sSiteId;
/*    */   }
/*    */   public int getCat_id() {
/* 20 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 23 */     this.cat_id = catId;
/*    */   }
/*    */   public String getT_site_id() {
/* 26 */     return this.t_site_id;
/*    */   }
/*    */   public void setT_site_id(String tSiteId) {
/* 29 */     this.t_site_id = tSiteId;
/*    */   }
/*    */   public int getShared_type() {
/* 32 */     return this.shared_type;
/*    */   }
/*    */   public void setShared_type(int sharedType) {
/* 35 */     this.shared_type = sharedType;
/*    */   }
/*    */   public int getRange_type() {
/* 38 */     return this.range_type;
/*    */   }
/*    */   public void setRange_type(int rangeType) {
/* 41 */     this.range_type = rangeType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CategorySharedBean
 * JD-Core Version:    0.6.2
 */