/*    */ package com.cicro.wcm.bean.org.role;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleUGroupBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 201420895409757842L;
/*    */   private int group_role_id;
/*  9 */   private String role_id = "";
/* 10 */   private String group_id = "";
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/*    */ 
/* 14 */   public String getApp_id() { return this.app_id; }
/*    */ 
/*    */   public void setApp_id(String app_id) {
/* 17 */     this.app_id = app_id;
/*    */   }
/*    */   public String getGroup_id() {
/* 20 */     return this.group_id;
/*    */   }
/*    */   public void setGroup_id(String group_id) {
/* 23 */     this.group_id = group_id;
/*    */   }
/*    */   public int getGroup_role_id() {
/* 26 */     return this.group_role_id;
/*    */   }
/*    */   public void setGroup_role_id(int group_role_id) {
/* 29 */     this.group_role_id = group_role_id;
/*    */   }
/*    */   public String getRole_id() {
/* 32 */     return this.role_id;
/*    */   }
/*    */   public void setRole_id(String role_id) {
/* 35 */     this.role_id = role_id;
/*    */   }
/*    */   public String getSite_id() {
/* 38 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 41 */     this.site_id = site_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.role.RoleUGroupBean
 * JD-Core Version:    0.6.2
 */