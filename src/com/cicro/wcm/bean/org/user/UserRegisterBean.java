/*    */ package com.cicro.wcm.bean.org.user;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserRegisterBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4635985492419904717L;
/*  8 */   private int register_id = 0;
/*    */   private int user_id;
/* 10 */   private String username = "";
/* 11 */   private String password = "";
/* 12 */   private String user_realname = "";
/* 13 */   private int register_status = -1;
/*    */ 
/* 15 */   public String getUser_realname() { return this.user_realname; }
/*    */ 
/*    */   public void setUser_realname(String userRealname) {
/* 18 */     this.user_realname = userRealname;
/*    */   }
/*    */ 
/*    */   public String getPassword() {
/* 22 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 25 */     this.password = password;
/*    */   }
/*    */   public int getRegister_id() {
/* 28 */     return this.register_id;
/*    */   }
/*    */   public void setRegister_id(int register_id) {
/* 31 */     this.register_id = register_id;
/*    */   }
/*    */   public int getRegister_status() {
/* 34 */     return this.register_status;
/*    */   }
/*    */   public void setRegister_status(int register_status) {
/* 37 */     this.register_status = register_status;
/*    */   }
/*    */   public int getUser_id() {
/* 40 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int user_id) {
/* 43 */     this.user_id = user_id;
/*    */   }
/*    */   public String getUsername() {
/* 46 */     return this.username;
/*    */   }
/*    */   public void setUsername(String username) {
/* 49 */     this.username = username;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.user.UserRegisterBean
 * JD-Core Version:    0.6.2
 */