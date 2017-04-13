/*    */ package com.cicro.wcm.bean.system.assist;
/*    */ 
/*    */ public class AuthorBean
/*    */ {
/*    */   private int author_id;
/*    */   private String author_name;
/*    */   private String author_initial;
/*    */   private String author_url;
/*    */   private String app_id;
/*    */   private String site_id;
/*    */ 
/*    */   public int getAuthor_id()
/*    */   {
/* 11 */     return this.author_id;
/*    */   }
/*    */   public String getAuthor_name() {
/* 14 */     return this.author_name;
/*    */   }
/*    */   public String getAuthor_initial() {
/* 17 */     return this.author_initial;
/*    */   }
/*    */   public String getAuthor_url() {
/* 20 */     return this.author_url;
/*    */   }
/*    */   public String getApp_id() {
/* 23 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 26 */     return this.site_id;
/*    */   }
/*    */   public void setAuthor_id(int authorId) {
/* 29 */     this.author_id = authorId;
/*    */   }
/*    */   public void setAuthor_name(String authorName) {
/* 32 */     if (authorName == null) {
/* 33 */       authorName = " ";
/*    */     }
/* 35 */     this.author_name = authorName;
/*    */   }
/*    */   public void setAuthor_initial(String authorInitial) {
/* 38 */     if (authorInitial == null) {
/* 39 */       authorInitial = " ";
/*    */     }
/* 41 */     this.author_initial = authorInitial;
/*    */   }
/*    */   public void setAuthor_url(String authorUrl) {
/* 44 */     if (authorUrl == null) {
/* 45 */       authorUrl = " ";
/*    */     }
/* 47 */     this.author_url = authorUrl;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 50 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 53 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.assist.AuthorBean
 * JD-Core Version:    0.6.2
 */