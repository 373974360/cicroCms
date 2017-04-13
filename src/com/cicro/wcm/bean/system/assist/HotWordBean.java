/*    */ package com.cicro.wcm.bean.system.assist;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class HotWordBean
/*    */ {
/*  4 */   private int hot_id = 0;
/*  5 */   private String hot_name = " ";
/*  6 */   private String hot_url = " ";
/*  7 */   private String app_id = " ";
/*  8 */   private String site_id = " ";
/*    */ 
/* 10 */   public int getHot_id() { return this.hot_id; }
/*    */ 
/*    */   public void setHot_id(int hotId) {
/* 13 */     this.hot_id = hotId;
/*    */   }
/*    */   public String getHot_name() {
/* 16 */     return this.hot_name;
/*    */   }
/*    */   public void setHot_name(String hotName) {
/* 19 */     if (hotName == null) {
/* 20 */       hotName = " ";
/*    */     }
/* 22 */     this.hot_name = hotName;
/*    */   }
/*    */   public String getHot_url() {
/* 25 */     return this.hot_url;
/*    */   }
/*    */   public void setHot_url(String hotUrl) {
/* 28 */     if (hotUrl == null) {
/* 29 */       hotUrl = " ";
/*    */     }
/* 31 */     this.hot_url = hotUrl;
/*    */   }
/*    */   public String getApp_id() {
/* 34 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 37 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 40 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 43 */     this.site_id = siteId;
/*    */   }
/*    */ 
/*    */   public void show() {
/* 47 */     System.out.println("hot_id = " + this.hot_id);
/* 48 */     System.out.println("hot_name = " + this.hot_name);
/* 49 */     System.out.println("hot_url = " + this.hot_url);
/* 50 */     System.out.println("app_id = " + this.app_id);
/* 51 */     System.out.println("site_id = " + this.site_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.assist.HotWordBean
 * JD-Core Version:    0.6.2
 */