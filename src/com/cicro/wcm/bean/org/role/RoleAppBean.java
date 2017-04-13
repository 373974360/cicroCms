/*    */ package com.cicro.wcm.bean.org.role;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleAppBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4077817677544556645L;
/*    */   private int role_app_id;
/*    */   private int role_id;
/* 10 */   private String app_id = "";
/* 11 */   private String site_id = "";
/*    */ 
/* 13 */   public String getApp_id() { return this.app_id; }
/*    */ 
/*    */   public void setApp_id(String app_id) {
/* 16 */     this.app_id = app_id;
/*    */   }
/*    */   public int getRole_app_id() {
/* 19 */     return this.role_app_id;
/*    */   }
/*    */   public void setRole_app_id(int role_app_id) {
/* 22 */     this.role_app_id = role_app_id;
/*    */   }
/*    */   public int getRole_id() {
/* 25 */     return this.role_id;
/*    */   }
/*    */   public void setRole_id(int role_id) {
/* 28 */     this.role_id = role_id;
/*    */   }
/*    */   public String getSite_id() {
/* 31 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 34 */     this.site_id = site_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.role.RoleAppBean
 * JD-Core Version:    0.6.2
 */