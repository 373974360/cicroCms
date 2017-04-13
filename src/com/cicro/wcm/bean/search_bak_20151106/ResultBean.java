/*    */ package com.cicro.wcm.bean.search_bak_20151106;
/*    */ 
/*    */ import com.cicro.wcm.services.search.search.util.SearchUtil;
/*    */ 
/*    */ public class ResultBean
/*    */ {
/*    */   private String url;
/*    */   private String title;
/*    */   private String content;
/*    */   private String time;
/*    */   private String type;
/*    */   private String id;
/*    */   private String site_id;
/*    */   private String category_id;
/*    */   private String category_name;
/*    */   private String model_id;
/*    */ 
/*    */   public String getUrl()
/*    */   {
/* 28 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 31 */     this.url = url;
/*    */   }
/*    */   public String getTitle() {
/* 34 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 37 */     this.title = title;
/*    */   }
/*    */   public String getContent() {
/* 40 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 43 */     this.content = SearchUtil.deleteCodeByContent(content);
/*    */   }
/*    */   public String getTime() {
/* 46 */     return this.time;
/*    */   }
/*    */   public void setTime(String time) {
/* 49 */     this.time = time;
/*    */   }
/*    */   public String getType() {
/* 52 */     return this.type;
/*    */   }
/*    */   public void setType(String type) {
/* 55 */     this.type = type;
/*    */   }
/*    */   public String getId() {
/* 58 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 61 */     this.id = id;
/*    */   }
/*    */   public String getSite_id() {
/* 64 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 67 */     this.site_id = siteId;
/*    */   }
/*    */   public String getCategory_id() {
/* 70 */     return this.category_id;
/*    */   }
/*    */   public void setCategory_id(String categoryId) {
/* 73 */     this.category_id = categoryId;
/*    */   }
/*    */   public String getCategory_name() {
/* 76 */     return this.category_name;
/*    */   }
/*    */   public void setCategory_name(String categoryName) {
/* 79 */     this.category_name = categoryName;
/*    */   }
/*    */   public String getModel_id() {
/* 82 */     return this.model_id;
/*    */   }
/*    */   public void setModel_id(String modelId) {
/* 85 */     this.model_id = modelId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search.ResultBean
 * JD-Core Version:    0.6.2
 */