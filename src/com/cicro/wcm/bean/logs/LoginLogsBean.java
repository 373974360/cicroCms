/*    */ package com.cicro.wcm.bean.logs;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LoginLogsBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8241697055835775966L;
/*  8 */   private int audit_id = 0;
/*  9 */   private int user_id = 0;
/* 10 */   private String user_name = "";
/* 11 */   private String audit_des = "";
/* 12 */   private String audit_time = "";
/* 13 */   private String ip = "";
/* 14 */   private String app_id = "";
/* 15 */   private String site_id = "";
/*    */ 
/* 17 */   public int getAudit_id() { return this.audit_id; }
/*    */ 
/*    */   public void setAudit_id(int auditId) {
/* 20 */     this.audit_id = auditId;
/*    */   }
/*    */   public int getUser_id() {
/* 23 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 26 */     this.user_id = userId;
/*    */   }
/*    */   public String getUser_name() {
/* 29 */     return this.user_name;
/*    */   }
/*    */   public void setUser_name(String userName) {
/* 32 */     this.user_name = userName;
/*    */   }
/*    */   public String getAudit_des() {
/* 35 */     return this.audit_des;
/*    */   }
/*    */   public void setAudit_des(String auditDes) {
/* 38 */     this.audit_des = auditDes;
/*    */   }
/*    */   public String getAudit_time() {
/* 41 */     return this.audit_time;
/*    */   }
/*    */   public void setAudit_time(String auditTime) {
/* 44 */     this.audit_time = auditTime;
/*    */   }
/*    */   public String getIp() {
/* 47 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 50 */     this.ip = ip;
/*    */   }
/*    */   public String getApp_id() {
/* 53 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 56 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 59 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 62 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.logs.LoginLogsBean
 * JD-Core Version:    0.6.2
 */