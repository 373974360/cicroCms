/*    */ package com.cicro.wcm.bean.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class QueryConfBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1520737041025282140L;
/* 17 */   private int conf_id = 0;
/* 18 */   private String conf_title = "";
/* 19 */   private String conf_description = "";
/* 20 */   private int t_list_id = 0;
/* 21 */   private int t_content_id = 0;
/*    */ 
/* 23 */   private String site_id = "";
/* 24 */   private String app_id = "";
/*    */ 
/*    */   public int getConf_id()
/*    */   {
/* 29 */     return this.conf_id;
/*    */   }
/*    */   public String getConf_title() {
/* 32 */     return this.conf_title;
/*    */   }
/*    */   public String getConf_description() {
/* 35 */     return this.conf_description;
/*    */   }
/*    */   public String getSite_id() {
/* 38 */     return this.site_id;
/*    */   }
/*    */   public String getApp_id() {
/* 41 */     return this.app_id;
/*    */   }
/*    */   public void setConf_id(int confId) {
/* 44 */     this.conf_id = confId;
/*    */   }
/*    */   public void setConf_title(String confTitle) {
/* 47 */     this.conf_title = confTitle;
/*    */   }
/*    */   public void setConf_description(String confDescription) {
/* 50 */     this.conf_description = confDescription;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 53 */     this.site_id = siteId;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 56 */     this.app_id = appId;
/*    */   }
/*    */   public int getT_list_id() {
/* 59 */     return this.t_list_id;
/*    */   }
/*    */   public int getT_content_id() {
/* 62 */     return this.t_content_id;
/*    */   }
/*    */   public void setT_list_id(int tListId) {
/* 65 */     this.t_list_id = tListId;
/*    */   }
/*    */   public void setT_content_id(int tContentId) {
/* 68 */     this.t_content_id = tContentId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.query.QueryConfBean
 * JD-Core Version:    0.6.2
 */