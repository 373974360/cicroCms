/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ZTCategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3288469410148245786L;
/*  8 */   private int id = 0;
/*  9 */   private int zt_cat_id = 0;
/* 10 */   private String zt_cat_name = "";
/* 11 */   private int sort_id = 999;
/* 12 */   private String site_id = "";
/* 13 */   private String app_id = "";
/*    */ 
/* 15 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 18 */     this.id = id;
/*    */   }
/*    */   public int getZt_cat_id() {
/* 21 */     return this.zt_cat_id;
/*    */   }
/*    */   public void setZt_cat_id(int ztCatId) {
/* 24 */     this.zt_cat_id = ztCatId;
/*    */   }
/*    */   public String getZt_cat_name() {
/* 27 */     return this.zt_cat_name;
/*    */   }
/*    */   public void setZt_cat_name(String ztCatName) {
/* 30 */     this.zt_cat_name = ztCatName;
/*    */   }
/*    */   public int getSort_id() {
/* 33 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 36 */     this.sort_id = sortId;
/*    */   }
/*    */   public String getSite_id() {
/* 39 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 42 */     this.site_id = siteId;
/*    */   }
/*    */   public String getApp_id() {
/* 45 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 48 */     this.app_id = appId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.ZTCategoryBean
 * JD-Core Version:    0.6.2
 */