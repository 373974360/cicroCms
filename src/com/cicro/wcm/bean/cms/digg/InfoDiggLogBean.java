/*    */ package com.cicro.wcm.bean.cms.digg;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class InfoDiggLogBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -758741655889930244L;
/*    */   private int id;
/*    */   private int info_id;
/*    */   private int flag;
/*    */   private int user_id;
/* 17 */   private String username = "";
/* 18 */   private String ip = "";
/* 19 */   private String digg_dtime = "";
/* 20 */   private String app_id = "";
/* 21 */   private String site_id = "";
/*    */ 
/*    */   public int getInfo_id()
/*    */   {
/* 25 */     return this.info_id;
/*    */   }
/*    */ 
/*    */   public void setInfo_id(int infoId) {
/* 29 */     this.info_id = infoId;
/*    */   }
/*    */ 
/*    */   public int getFlag() {
/* 33 */     return this.flag;
/*    */   }
/*    */ 
/*    */   public void setFlag(int flag) {
/* 37 */     this.flag = flag;
/*    */   }
/*    */   public int getUser_id() {
/* 40 */     return this.user_id;
/*    */   }
/*    */ 
/*    */   public void setUser_id(int userId) {
/* 44 */     this.user_id = userId;
/*    */   }
/*    */ 
/*    */   public String getUsername() {
/* 48 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 52 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 56 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 59 */     this.ip = ip;
/*    */   }
/*    */ 
/*    */   public String getDigg_dtime() {
/* 63 */     return this.digg_dtime;
/*    */   }
/*    */ 
/*    */   public void setDigg_dtime(String diggDtime) {
/* 67 */     this.digg_dtime = diggDtime;
/*    */   }
/*    */ 
/*    */   public String getApp_id() {
/* 71 */     return this.app_id;
/*    */   }
/*    */ 
/*    */   public void setApp_id(String appId) {
/* 75 */     this.app_id = appId;
/*    */   }
/*    */ 
/*    */   public String getSite_id() {
/* 79 */     return this.site_id;
/*    */   }
/*    */ 
/*    */   public void setSite_id(String siteId) {
/* 83 */     this.site_id = siteId;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 87 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 91 */     return this.id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.digg.InfoDiggLogBean
 * JD-Core Version:    0.6.2
 */