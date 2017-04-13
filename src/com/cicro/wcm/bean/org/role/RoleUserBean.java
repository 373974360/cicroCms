/*    */ package com.cicro.wcm.bean.org.role;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleUserBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3555475247214674303L;
/*    */   private int user_role_id;
/*  9 */   private String role_id = "";
/* 10 */   private String user_id = "";
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/*    */ 
/* 14 */   public String getApp_id() { return this.app_id; }
/*    */ 
/*    */   public void setApp_id(String app_id) {
/* 17 */     this.app_id = app_id;
/*    */   }
/*    */ 
/*    */   public String getSite_id() {
/* 21 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 24 */     this.site_id = site_id;
/*    */   }
/*    */ 
/*    */   public int getUser_role_id() {
/* 28 */     return this.user_role_id;
/*    */   }
/*    */   public void setUser_role_id(int user_role_id) {
/* 31 */     this.user_role_id = user_role_id;
/*    */   }
/*    */ 
/*    */   public String getRole_id() {
/* 35 */     return this.role_id;
/*    */   }
/*    */   public void setRole_id(String role_id) {
/* 38 */     this.role_id = role_id;
/*    */   }
/*    */   public String getUser_id() {
/* 41 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(String user_id) {
/* 44 */     this.user_id = user_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.role.RoleUserBean
 * JD-Core Version:    0.6.2
 */