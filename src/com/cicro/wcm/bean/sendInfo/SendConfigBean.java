/*    */ package com.cicro.wcm.bean.sendInfo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SendConfigBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3209113476237988997L;
/*  8 */   private int id = 0;
/*  9 */   private String site_id = "";
/* 10 */   private String site_domain = "";
/* 11 */   private String site_name = "";
/* 12 */   private int range_type = 0;
/* 13 */   private String allow_site_id = "";
/* 14 */   private int is_zwgk = 0;
/*    */ 
/* 16 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 19 */     this.id = id;
/*    */   }
/*    */   public String getSite_id() {
/* 22 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 25 */     this.site_id = siteId;
/*    */   }
/*    */   public String getSite_domain() {
/* 28 */     return this.site_domain;
/*    */   }
/*    */   public void setSite_domain(String siteDomain) {
/* 31 */     this.site_domain = siteDomain;
/*    */   }
/*    */   public String getSite_name() {
/* 34 */     return this.site_name;
/*    */   }
/*    */   public void setSite_name(String siteName) {
/* 37 */     this.site_name = siteName;
/*    */   }
/*    */   public int getRange_type() {
/* 40 */     return this.range_type;
/*    */   }
/*    */   public void setRange_type(int rangeType) {
/* 43 */     this.range_type = rangeType;
/*    */   }
/*    */   public String getAllow_site_id() {
/* 46 */     return this.allow_site_id;
/*    */   }
/*    */   public void setAllow_site_id(String allowSiteId) {
/* 49 */     this.allow_site_id = allowSiteId;
/*    */   }
/*    */   public int getIs_zwgk() {
/* 52 */     return this.is_zwgk;
/*    */   }
/*    */   public void setIs_zwgk(int isZwgk) {
/* 55 */     this.is_zwgk = isZwgk;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.SendConfigBean
 * JD-Core Version:    0.6.2
 */