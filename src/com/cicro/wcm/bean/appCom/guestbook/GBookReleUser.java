/*    */ package com.cicro.wcm.bean.appCom.guestbook;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GBookReleUser
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1696496233205203474L;
/*  8 */   private int id = 0;
/*  9 */   private int cat_id = 0;
/* 10 */   private int user_id = 0;
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/*    */ 
/* 14 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 17 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public int getCat_id() {
/* 21 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 24 */     this.cat_id = catId;
/*    */   }
/*    */   public int getUser_id() {
/* 27 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 30 */     this.user_id = userId;
/*    */   }
/*    */   public String getApp_id() {
/* 33 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 36 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 39 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 42 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appCom.guestbook.GBookReleUser
 * JD-Core Version:    0.6.2
 */