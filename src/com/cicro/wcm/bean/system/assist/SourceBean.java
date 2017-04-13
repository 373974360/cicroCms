/*    */ package com.cicro.wcm.bean.system.assist;
/*    */ 
/*    */ public class SourceBean
/*    */ {
/*    */   private int source_id;
/*    */   private String source_name;
/*    */   private String source_initial;
/*    */   private String source_url;
/*    */   private String logo_path;
/*    */   private String app_id;
/*    */   private String site_id;
/*    */ 
/*    */   public int getSource_id()
/*    */   {
/* 12 */     return this.source_id;
/*    */   }
/*    */   public String getSource_name() {
/* 15 */     return this.source_name;
/*    */   }
/*    */   public String getSource_initial() {
/* 18 */     return this.source_initial;
/*    */   }
/*    */   public String getSource_url() {
/* 21 */     return this.source_url;
/*    */   }
/*    */   public String getLogo_path() {
/* 24 */     return this.logo_path;
/*    */   }
/*    */   public String getApp_id() {
/* 27 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 30 */     return this.site_id;
/*    */   }
/*    */   public void setSource_id(int sourceId) {
/* 33 */     this.source_id = sourceId;
/*    */   }
/*    */   public void setSource_name(String sourceName) {
/* 36 */     if (sourceName == null) {
/* 37 */       sourceName = " ";
/*    */     }
/* 39 */     this.source_name = sourceName;
/*    */   }
/*    */   public void setSource_initial(String sourceInitial) {
/* 42 */     if (sourceInitial == null) {
/* 43 */       sourceInitial = " ";
/*    */     }
/* 45 */     this.source_initial = sourceInitial;
/*    */   }
/*    */   public void setSource_url(String sourceUrl) {
/* 48 */     if (sourceUrl == null) {
/* 49 */       sourceUrl = " ";
/*    */     }
/* 51 */     this.source_url = sourceUrl;
/*    */   }
/*    */   public void setLogo_path(String logoPath) {
/* 54 */     if (logoPath == null) {
/* 55 */       logoPath = " ";
/*    */     }
/* 57 */     this.logo_path = logoPath;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 60 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 63 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.assist.SourceBean
 * JD-Core Version:    0.6.2
 */