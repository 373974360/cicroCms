/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignCSSBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5713248001058843094L;
/* 12 */   private int id = 0;
/* 13 */   private int css_id = 0;
/* 14 */   private String css_ename = "";
/* 15 */   private String css_name = "";
/* 16 */   private String thumb_url = "";
/* 17 */   private int weight = 0;
/* 18 */   private String app_id = "";
/* 19 */   private String site_id = "";
/*    */ 
/* 21 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 24 */     this.id = id;
/*    */   }
/*    */   public int getCss_id() {
/* 27 */     return this.css_id;
/*    */   }
/*    */   public void setCss_id(int cssId) {
/* 30 */     this.css_id = cssId;
/*    */   }
/*    */   public String getCss_ename() {
/* 33 */     return this.css_ename;
/*    */   }
/*    */   public void setCss_ename(String cssEname) {
/* 36 */     this.css_ename = cssEname;
/*    */   }
/*    */   public String getCss_name() {
/* 39 */     return this.css_name;
/*    */   }
/*    */   public void setCss_name(String cssName) {
/* 42 */     this.css_name = cssName;
/*    */   }
/*    */   public String getThumb_url() {
/* 45 */     return this.thumb_url;
/*    */   }
/*    */   public void setThumb_url(String thumbUrl) {
/* 48 */     this.thumb_url = thumbUrl;
/*    */   }
/*    */   public int getWeight() {
/* 51 */     return this.weight;
/*    */   }
/*    */   public void setWeight(int weight) {
/* 54 */     this.weight = weight;
/*    */   }
/*    */   public String getApp_id() {
/* 57 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 60 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 63 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 66 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignCSSBean
 * JD-Core Version:    0.6.2
 */