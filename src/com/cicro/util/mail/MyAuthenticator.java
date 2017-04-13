/*    */ package com.cicro.util.mail;
/*    */ 
/*    */ import javax.mail.Authenticator;
/*    */ import javax.mail.PasswordAuthentication;
/*    */ 
/*    */ public class MyAuthenticator extends Authenticator
/*    */ {
/*  6 */   String userName = null;
/*  7 */   String password = null;
/*    */ 
/*    */   public MyAuthenticator() {
/*    */   }
/*    */   public MyAuthenticator(String username, String password) {
/* 12 */     this.userName = username;
/* 13 */     this.password = password;
/*    */   }
/*    */   protected PasswordAuthentication getPasswordAuthentication() {
/* 16 */     return new PasswordAuthentication(this.userName, this.password);
/*    */   }
/*    */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.util.mail.MyAuthenticator
 * JD-Core Version:    0.6.2
 */