/*    */ package com.cicro.wcm.bean.logs;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SettingLogsBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6815990262857142840L;
/*    */   private int audit_id;
/*    */   private int user_id;
/* 10 */   private String user_name = "";
/* 11 */   private String audit_des = "";
/* 12 */   private String audit_time = "";
/* 13 */   private String ip = "";
/* 14 */   private String app_id = "";
/* 15 */   private String site_id = "";
/*    */ 
/* 17 */   public String getApp_id() { return this.app_id; }
/*    */ 
/*    */   public void setApp_id(String app_id) {
/* 20 */     this.app_id = app_id;
/*    */   }
/*    */   public String getAudit_des() {
/* 23 */     return this.audit_des;
/*    */   }
/*    */   public void setAudit_des(String audit_des) {
/* 26 */     this.audit_des = audit_des;
/*    */   }
/*    */   public int getAudit_id() {
/* 29 */     return this.audit_id;
/*    */   }
/*    */   public void setAudit_id(int audit_id) {
/* 32 */     this.audit_id = audit_id;
/*    */   }
/*    */   public String getAudit_time() {
/* 35 */     return this.audit_time;
/*    */   }
/*    */   public void setAudit_time(String audit_time) {
/* 38 */     this.audit_time = audit_time;
/*    */   }
/*    */   public String getIp() {
/* 41 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 44 */     this.ip = ip;
/*    */   }
/*    */   public String getSite_id() {
/* 47 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 50 */     this.site_id = site_id;
/*    */   }
/*    */   public int getUser_id() {
/* 53 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int user_id) {
/* 56 */     this.user_id = user_id;
/*    */   }
/*    */   public String getUser_name() {
/* 59 */     return this.user_name;
/*    */   }
/*    */   public void setUser_name(String user_name) {
/* 62 */     this.user_name = user_name;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.logs.SettingLogsBean
 * JD-Core Version:    0.6.2
 */