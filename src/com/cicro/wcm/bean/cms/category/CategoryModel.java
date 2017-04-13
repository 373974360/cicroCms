/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CategoryModel
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7292527114366745514L;
/*  8 */   private int cat_model_id = 0;
/*  9 */   private int cat_id = 0;
/* 10 */   private int model_id = 0;
/* 11 */   private int template_content = 0;
/* 12 */   private String app_id = "";
/* 13 */   private String site_id = "";
/*    */ 
/* 15 */   public int getCat_model_id() { return this.cat_model_id; }
/*    */ 
/*    */   public void setCat_model_id(int catModelId) {
/* 18 */     this.cat_model_id = catModelId;
/*    */   }
/*    */   public int getCat_id() {
/* 21 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 24 */     this.cat_id = catId;
/*    */   }
/*    */   public int getModel_id() {
/* 27 */     return this.model_id;
/*    */   }
/*    */   public void setModel_id(int modelId) {
/* 30 */     this.model_id = modelId;
/*    */   }
/*    */   public int getTemplate_content() {
/* 33 */     return this.template_content;
/*    */   }
/*    */   public void setTemplate_content(int templateContent) {
/* 36 */     this.template_content = templateContent;
/*    */   }
/*    */   public String getApp_id() {
/* 39 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 42 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 45 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 48 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CategoryModel
 * JD-Core Version:    0.6.2
 */