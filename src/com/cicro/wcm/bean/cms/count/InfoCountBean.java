/*    */ package com.cicro.wcm.bean.cms.count;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class InfoCountBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  8 */   private int count = 0;
/*  9 */   private int publish_count = 0;
/* 10 */   private int not_publish_count = 0;
/* 11 */   private String site_id = "";
/* 12 */   private String site_name = "";
/* 13 */   private int cat_id = 0;
/* 14 */   private String cat_cname = "";
/* 15 */   private int user_id = 0;
/* 16 */   private String user_realname = "";
/*    */ 
/*    */   public int getUser_id() {
/* 19 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 22 */     this.user_id = userId;
/*    */   }
/*    */   public String getUser_realname() {
/* 25 */     return this.user_realname;
/*    */   }
/*    */   public void setUser_realname(String userRealname) {
/* 28 */     this.user_realname = userRealname;
/*    */   }
/*    */   public int getPublish_count() {
/* 31 */     return this.publish_count;
/*    */   }
/*    */   public void setPublish_count(int publishCount) {
/* 34 */     this.publish_count = publishCount;
/*    */   }
/*    */   public int getNot_publish_count() {
/* 37 */     return this.not_publish_count;
/*    */   }
/*    */   public void setNot_publish_count(int notPublishCount) {
/* 40 */     this.not_publish_count = notPublishCount;
/*    */   }
/*    */   public int getCount() {
/* 43 */     return this.count;
/*    */   }
/*    */   public void setCount(int count) {
/* 46 */     this.count = count;
/*    */   }
/*    */   public String getSite_id() {
/* 49 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 52 */     this.site_id = siteId;
/*    */   }
/*    */   public String getSite_name() {
/* 55 */     return this.site_name;
/*    */   }
/*    */   public void setSite_name(String siteName) {
/* 58 */     this.site_name = siteName;
/*    */   }
/*    */   public int getCat_id() {
/* 61 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 64 */     this.cat_id = catId;
/*    */   }
/*    */   public String getCat_cname() {
/* 67 */     return this.cat_cname;
/*    */   }
/*    */   public void setCat_cname(String catCname) {
/* 70 */     this.cat_cname = catCname;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.count.InfoCountBean
 * JD-Core Version:    0.6.2
 */