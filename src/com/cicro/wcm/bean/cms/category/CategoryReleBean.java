/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CategoryReleBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3673701023497034799L;
/*    */   private int cat_id;
/*    */   private int prv_id;
/*    */   private int priv_type;
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/*    */ 
/* 14 */   public int getCat_id() { return this.cat_id; }
/*    */ 
/*    */   public void setCat_id(int catId) {
/* 17 */     this.cat_id = catId;
/*    */   }
/*    */   public int getPrv_id() {
/* 20 */     return this.prv_id;
/*    */   }
/*    */   public void setPrv_id(int prvId) {
/* 23 */     this.prv_id = prvId;
/*    */   }
/*    */   public int getPriv_type() {
/* 26 */     return this.priv_type;
/*    */   }
/*    */   public void setPriv_type(int privType) {
/* 29 */     this.priv_type = privType;
/*    */   }
/*    */   public String getApp_id() {
/* 32 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 35 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 38 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 41 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CategoryReleBean
 * JD-Core Version:    0.6.2
 */