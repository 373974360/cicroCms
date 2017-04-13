/*    */ package com.cicro.wcm.bean.search_bak_20130909;
/*    */ 
/*    */ public class IndexBean
/*    */ {
/*    */   private static String type;
/*    */   private String id;
/*    */   private String title;
/*    */   private String content;
/*    */   private String url;
/*    */   private String site_id;
/*    */   private String app_id;
/*    */   private String node_id;
/*    */   private String model_id;
/*    */ 
/*    */   public IndexBean(String type)
/*    */   {
/* 26 */     setType(type);
/*    */   }
/*    */ 
/*    */   public String getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 33 */     this.id = id;
/*    */   }
/*    */   public String getTitle() {
/* 36 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 39 */     this.title = title;
/*    */   }
/*    */   public String getContent() {
/* 42 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 45 */     this.content = content;
/*    */   }
/*    */   public String getUrl() {
/* 48 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 51 */     this.url = url;
/*    */   }
/*    */   public static String getType() {
/* 54 */     return type;
/*    */   }
/*    */   public static void setType(String type) {
/* 57 */     type = type;
/*    */   }
/*    */ 
/*    */   public String getSite_id() {
/* 61 */     return this.site_id;
/*    */   }
/*    */ 
/*    */   public void setSite_id(String siteId) {
/* 65 */     this.site_id = siteId;
/*    */   }
/*    */ 
/*    */   public String getApp_id() {
/* 69 */     return this.app_id;
/*    */   }
/*    */ 
/*    */   public void setApp_id(String appId) {
/* 73 */     this.app_id = appId;
/*    */   }
/*    */ 
/*    */   public String getNode_id() {
/* 77 */     return this.node_id;
/*    */   }
/*    */ 
/*    */   public void setNode_id(String nodeId) {
/* 81 */     this.node_id = nodeId;
/*    */   }
/*    */ 
/*    */   public String getModel_id() {
/* 85 */     return this.model_id;
/*    */   }
/*    */ 
/*    */   public void setModel_id(String modelId) {
/* 89 */     this.model_id = modelId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search_bak_20130909.IndexBean
 * JD-Core Version:    0.6.2
 */