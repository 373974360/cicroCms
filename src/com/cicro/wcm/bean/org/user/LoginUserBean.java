/*    */ package com.cicro.wcm.bean.org.user;
/*    */ 
/*    */ public class LoginUserBean
/*    */ {
/*    */   private int user_id;
/*    */   private int dept_id;
/*    */   private String userlevel_value;
/*  7 */   private String user_realname = "";
/*  8 */   private String user_aliasname = "";
/*    */   private String user_name;
/* 10 */   private String ip = "";
/*    */ 
/* 12 */   public int getUser_id() { return this.user_id; }
/*    */ 
/*    */   public void setUser_id(int userId) {
/* 15 */     this.user_id = userId;
/*    */   }
/*    */   public int getDept_id() {
/* 18 */     return this.dept_id;
/*    */   }
/*    */   public void setDept_id(int deptId) {
/* 21 */     this.dept_id = deptId;
/*    */   }
/*    */   public String getUserlevel_value() {
/* 24 */     return this.userlevel_value;
/*    */   }
/*    */   public void setUserlevel_value(String userlevelValue) {
/* 27 */     this.userlevel_value = userlevelValue;
/*    */   }
/*    */   public String getUser_realname() {
/* 30 */     return this.user_realname;
/*    */   }
/*    */   public void setUser_realname(String userRealname) {
/* 33 */     this.user_realname = userRealname;
/*    */   }
/*    */   public String getUser_aliasname() {
/* 36 */     return this.user_aliasname;
/*    */   }
/*    */   public void setUser_aliasname(String userAliasname) {
/* 39 */     this.user_aliasname = userAliasname;
/*    */   }
/*    */   public String getUser_name() {
/* 42 */     return this.user_name;
/*    */   }
/*    */   public void setUser_name(String userName) {
/* 45 */     this.user_name = userName;
/*    */   }
/*    */   public String getIp() {
/* 48 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 51 */     this.ip = ip;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.user.LoginUserBean
 * JD-Core Version:    0.6.2
 */