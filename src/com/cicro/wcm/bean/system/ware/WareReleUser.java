/*    */ package com.cicro.wcm.bean.system.ware;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class WareReleUser
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1062841104290636541L;
/*  8 */   private int id = 0;
/*  9 */   private int wcat_id = 0;
/* 10 */   private int prv_id = 0;
/*    */ 
/* 47 */   private int priv_type = 0;
/* 48 */   private String app_id = "";
/* 49 */   private String site_id = "";
/*    */ 
/*    */   public int getId()
/*    */   {
/* 12 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 15 */     this.id = id;
/*    */   }
/*    */   public int getWcat_id() {
/* 18 */     return this.wcat_id;
/*    */   }
/*    */   public void setWcat_id(int wcatId) {
/* 21 */     this.wcat_id = wcatId;
/*    */   }
/*    */   public int getPrv_id() {
/* 24 */     return this.prv_id;
/*    */   }
/*    */   public void setPrv_id(int prvId) {
/* 27 */     this.prv_id = prvId;
/*    */   }
/*    */   public int getPriv_type() {
/* 30 */     return this.priv_type;
/*    */   }
/*    */   public void setPriv_type(int privType) {
/* 33 */     this.priv_type = privType;
/*    */   }
/*    */   public String getApp_id() {
/* 36 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 39 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 42 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 45 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareReleUser
 * JD-Core Version:    0.6.2
 */