/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignCaseBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4871009405992889090L;
/* 12 */   private int id = 0;
/* 13 */   private int case_id = 0;
/* 14 */   private int css_id = 0;
/* 15 */   private String case_name = "";
/* 16 */   private String case_content = "";
/* 17 */   private String thumb_url = "";
/* 18 */   private int weight = 0;
/* 19 */   private String app_id = "";
/* 20 */   private String site_id = "";
/*    */ 
/* 22 */   public int getCss_id() { return this.css_id; }
/*    */ 
/*    */   public void setCss_id(int cssId) {
/* 25 */     this.css_id = cssId;
/*    */   }
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */   public int getCase_id() {
/* 34 */     return this.case_id;
/*    */   }
/*    */   public void setCase_id(int caseId) {
/* 37 */     this.case_id = caseId;
/*    */   }
/*    */   public String getCase_name() {
/* 40 */     return this.case_name;
/*    */   }
/*    */   public void setCase_name(String caseName) {
/* 43 */     this.case_name = caseName;
/*    */   }
/*    */   public String getCase_content() {
/* 46 */     return this.case_content;
/*    */   }
/*    */   public void setCase_content(String caseContent) {
/* 49 */     this.case_content = caseContent;
/*    */   }
/*    */   public String getThumb_url() {
/* 52 */     return this.thumb_url;
/*    */   }
/*    */   public void setThumb_url(String thumbUrl) {
/* 55 */     this.thumb_url = thumbUrl;
/*    */   }
/*    */   public int getWeight() {
/* 58 */     return this.weight;
/*    */   }
/*    */   public void setWeight(int weight) {
/* 61 */     this.weight = weight;
/*    */   }
/*    */   public String getApp_id() {
/* 64 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 67 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 70 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 73 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignCaseBean
 * JD-Core Version:    0.6.2
 */