/*    */ package com.cicro.wcm.bean.org.siteuser;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SiteUserBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5989812128106356118L;
/* 10 */   private String user_id = "";
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/* 13 */   private String role_ids = "";
/* 14 */   private String role_names = "";
/*    */ 
/*    */   public String getRole_ids() {
/* 17 */     return this.role_ids;
/*    */   }
/*    */   public void setRole_ids(String roleIds) {
/* 20 */     this.role_ids = roleIds;
/*    */   }
/*    */   public String getRole_names() {
/* 23 */     return this.role_names;
/*    */   }
/*    */   public void setRole_names(String roleNames) {
/* 26 */     this.role_names = roleNames;
/*    */   }
/*    */   public String getUser_id() {
/* 29 */     return this.user_id;
/*    */   }
/*    */   public String getApp_id() {
/* 32 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 35 */     return this.site_id;
/*    */   }
/*    */ 
/*    */   public void setUser_id(String userId) {
/* 39 */     this.user_id = userId;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 42 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 45 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.siteuser.SiteUserBean
 * JD-Core Version:    0.6.2
 */