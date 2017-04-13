/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignCategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1022186835853600221L;
/* 12 */   private int id = 0;
/* 13 */   private int cat_id = 0;
/* 14 */   private int css_id = 0;
/* 15 */   private int template_id = 0;
/* 16 */   private String design_content = "";
/* 17 */   private String page_type = "";
/* 18 */   private int publish_status = 0;
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
/*    */   public int getCat_id() {
/* 34 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 37 */     this.cat_id = catId;
/*    */   }
/*    */   public int getTemplate_id() {
/* 40 */     return this.template_id;
/*    */   }
/*    */   public void setTemplate_id(int templateId) {
/* 43 */     this.template_id = templateId;
/*    */   }
/*    */   public String getDesign_content() {
/* 46 */     return this.design_content;
/*    */   }
/*    */   public void setDesign_content(String designContent) {
/* 49 */     this.design_content = designContent;
/*    */   }
/*    */   public String getPage_type() {
/* 52 */     return this.page_type;
/*    */   }
/*    */   public void setPage_type(String pageType) {
/* 55 */     this.page_type = pageType;
/*    */   }
/*    */   public int getPublish_status() {
/* 58 */     return this.publish_status;
/*    */   }
/*    */   public void setPublish_status(int publishStatus) {
/* 61 */     this.publish_status = publishStatus;
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
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignCategoryBean
 * JD-Core Version:    0.6.2
 */