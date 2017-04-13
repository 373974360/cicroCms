/*    */ package com.cicro.wcm.bean.org.role;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 374115978782437202L;
/*    */   private int role_id;
/*  9 */   private int rele_shared = 0;
/* 10 */   private String role_name = "";
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/* 13 */   private String a_app_id = "";
/*    */ 
/* 15 */   private String role_memo = "";
/*    */ 
/* 17 */   public String getApp_id() { return this.app_id; }
/*    */ 
/*    */   public void setApp_id(String app_id) {
/* 20 */     this.app_id = app_id;
/*    */   }
/*    */   public int getRele_shared() {
/* 23 */     return this.rele_shared;
/*    */   }
/*    */   public void setRele_shared(int rele_shared) {
/* 26 */     this.rele_shared = rele_shared;
/*    */   }
/*    */   public int getRole_id() {
/* 29 */     return this.role_id;
/*    */   }
/*    */   public void setRole_id(int role_id) {
/* 32 */     this.role_id = role_id;
/*    */   }
/*    */   public String getRole_memo() {
/* 35 */     return this.role_memo;
/*    */   }
/*    */   public void setRole_memo(String role_memo) {
/* 38 */     this.role_memo = role_memo;
/*    */   }
/*    */   public String getRole_name() {
/* 41 */     return this.role_name;
/*    */   }
/*    */   public void setRole_name(String role_name) {
/* 44 */     this.role_name = role_name;
/*    */   }
/*    */   public String getSite_id() {
/* 47 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 50 */     this.site_id = site_id;
/*    */   }
/*    */   public String getA_app_id() {
/* 53 */     return this.a_app_id;
/*    */   }
/*    */   public void setA_app_id(String a_app_id) {
/* 56 */     this.a_app_id = a_app_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.role.RoleBean
 * JD-Core Version:    0.6.2
 */