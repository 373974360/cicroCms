/*    */ package com.cicro.util.mail;
/*    */ 
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class MailSenderInfo
/*    */ {
/*    */   private String mailServerHost;
/* 10 */   private String mailServerPort = "25";
/*    */   private String fromAddress;
/*    */   private String toAddress;
/*    */   private String userName;
/*    */   private String password;
/* 19 */   private boolean validate = false;
/*    */   private String subject;
/*    */   private String content;
/*    */   private String[] attachFileNames;
/*    */ 
/*    */   public Properties getProperties()
/*    */   {
/* 30 */     Properties p = new Properties();
/* 31 */     p.put("mail.smtp.host", this.mailServerHost);
/* 32 */     p.put("mail.smtp.port", this.mailServerPort);
/* 33 */     p.put("mail.smtp.auth", this.validate ? "true" : "false");
/* 34 */     return p;
/*    */   }
/*    */   public String getMailServerHost() {
/* 37 */     return this.mailServerHost;
/*    */   }
/*    */   public void setMailServerHost(String mailServerHost) {
/* 40 */     this.mailServerHost = mailServerHost;
/*    */   }
/*    */   public String getMailServerPort() {
/* 43 */     return this.mailServerPort;
/*    */   }
/*    */   public void setMailServerPort(String mailServerPort) {
/* 46 */     this.mailServerPort = mailServerPort;
/*    */   }
/*    */   public boolean isValidate() {
/* 49 */     return this.validate;
/*    */   }
/*    */   public void setValidate(boolean validate) {
/* 52 */     this.validate = validate;
/*    */   }
/*    */   public String[] getAttachFileNames() {
/* 55 */     return this.attachFileNames;
/*    */   }
/*    */   public void setAttachFileNames(String[] fileNames) {
/* 58 */     this.attachFileNames = fileNames;
/*    */   }
/*    */   public String getFromAddress() {
/* 61 */     return this.fromAddress;
/*    */   }
/*    */   public void setFromAddress(String fromAddress) {
/* 64 */     this.fromAddress = fromAddress;
/*    */   }
/*    */   public String getPassword() {
/* 67 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 70 */     this.password = password;
/*    */   }
/*    */   public String getToAddress() {
/* 73 */     return this.toAddress;
/*    */   }
/*    */   public void setToAddress(String toAddress) {
/* 76 */     this.toAddress = toAddress;
/*    */   }
/*    */   public String getUserName() {
/* 79 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 82 */     this.userName = userName;
/*    */   }
/*    */   public String getSubject() {
/* 85 */     return this.subject;
/*    */   }
/*    */   public void setSubject(String subject) {
/* 88 */     this.subject = subject;
/*    */   }
/*    */   public String getContent() {
/* 91 */     return this.content;
/*    */   }
/*    */   public void setContent(String textContent) {
/* 94 */     this.content = textContent;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.util.mail.MailSenderInfo
 * JD-Core Version:    0.6.2
 */