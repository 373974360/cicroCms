/*    */ package com.cicro.wcm.bean.sendInfo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ReceiveConfigBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  8 */   private int id = 0;
/*  9 */   private String site_id = "";
/* 10 */   private String user_name = "";
/* 11 */   private String pass_word = "";
/* 12 */   private int receive_status = 0;
/* 13 */   private int sort_id = 0;
/* 14 */   private String site_name = "";
/*    */ 
/* 16 */   public String getSite_name() { return this.site_name; }
/*    */ 
/*    */   public void setSite_name(String siteName) {
/* 19 */     this.site_name = siteName;
/*    */   }
/*    */   public int getId() {
/* 22 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 25 */     this.id = id;
/*    */   }
/*    */   public String getSite_id() {
/* 28 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 31 */     this.site_id = siteId;
/*    */   }
/*    */   public String getUser_name() {
/* 34 */     return this.user_name;
/*    */   }
/*    */   public void setUser_name(String userName) {
/* 37 */     this.user_name = userName;
/*    */   }
/*    */   public String getPass_word() {
/* 40 */     return this.pass_word;
/*    */   }
/*    */   public void setPass_word(String passWord) {
/* 43 */     this.pass_word = passWord;
/*    */   }
/*    */   public int getReceive_status() {
/* 46 */     return this.receive_status;
/*    */   }
/*    */   public void setReceive_status(int receiveStatus) {
/* 49 */     this.receive_status = receiveStatus;
/*    */   }
/*    */   public int getSort_id() {
/* 52 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 55 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.ReceiveConfigBean
 * JD-Core Version:    0.6.2
 */