/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CategoryGetRegu
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1535550491759355273L;
/*  8 */   private int id = 0;
/*  9 */   private int cat_id = 0;
/* 10 */   private int regu_type = 0;
/* 11 */   private String site_ids = "";
/* 12 */   private String cat_ids = "";
/* 13 */   private String site_id_names = "";
/* 14 */   private String cat_id_names = "";
/* 15 */   private String site_id = "";
/* 16 */   private String app_id = "";
/*    */ 
/* 18 */   public String getSite_id() { return this.site_id; }
/*    */ 
/*    */   public void setSite_id(String siteId) {
/* 21 */     this.site_id = siteId;
/*    */   }
/*    */   public String getApp_id() {
/* 24 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 27 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id_names() {
/* 30 */     return this.site_id_names;
/*    */   }
/*    */   public void setSite_id_names(String siteIdNames) {
/* 33 */     this.site_id_names = siteIdNames;
/*    */   }
/*    */   public String getCat_id_names() {
/* 36 */     return this.cat_id_names;
/*    */   }
/*    */   public void setCat_id_names(String catIdNames) {
/* 39 */     this.cat_id_names = catIdNames;
/*    */   }
/*    */   public int getId() {
/* 42 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 45 */     this.id = id;
/*    */   }
/*    */   public int getCat_id() {
/* 48 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 51 */     this.cat_id = catId;
/*    */   }
/*    */   public int getRegu_type() {
/* 54 */     return this.regu_type;
/*    */   }
/*    */   public void setRegu_type(int reguType) {
/* 57 */     this.regu_type = reguType;
/*    */   }
/*    */   public String getSite_ids() {
/* 60 */     return this.site_ids;
/*    */   }
/*    */   public void setSite_ids(String siteIds) {
/* 63 */     this.site_ids = siteIds;
/*    */   }
/*    */   public String getCat_ids() {
/* 66 */     return this.cat_ids;
/*    */   }
/*    */   public void setCat_ids(String catIds) {
/* 69 */     this.cat_ids = catIds;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CategoryGetRegu
 * JD-Core Version:    0.6.2
 */