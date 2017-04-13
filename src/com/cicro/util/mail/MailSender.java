/*     */ package com.cicro.util.mail;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.BodyPart;
/*     */ import javax.mail.Message;
/*     */ import javax.mail.Message.RecipientType;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.Multipart;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.InternetAddress;
/*     */ import javax.mail.internet.MimeBodyPart;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import javax.mail.internet.MimeMultipart;
/*     */ 
/*     */ public class MailSender
/*     */ {
/*     */   public boolean sendTextMail(MailSenderInfo mailInfo)
/*     */   {
/*  26 */     MyAuthenticator authenticator = null;
/*  27 */     Properties pro = mailInfo.getProperties();
/*  28 */     if (mailInfo.isValidate())
/*     */     {
/*  30 */       authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
/*     */     }
/*     */ 
/*  33 */     Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
/*     */     try
/*     */     {
/*  36 */       Message mailMessage = new MimeMessage(sendMailSession);
/*     */ 
/*  38 */       Address from = new InternetAddress(mailInfo.getFromAddress());
/*     */ 
/*  40 */       mailMessage.setFrom(from);
/*     */ 
/*  42 */       Address to = new InternetAddress(mailInfo.getToAddress());
/*  43 */       mailMessage.setRecipient(Message.RecipientType.TO, to);
/*     */ 
/*  45 */       mailMessage.setSubject(mailInfo.getSubject());
/*     */ 
/*  47 */       mailMessage.setSentDate(new Date());
/*     */ 
/*  49 */       String mailContent = mailInfo.getContent();
/*  50 */       mailMessage.setText(mailContent);
/*     */ 
/*  52 */       Transport.send(mailMessage);
/*  53 */       return true;
/*     */     } catch (MessagingException ex) {
/*  55 */       ex.printStackTrace();
/*     */     }
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sendHtmlMail(MailSenderInfo mailInfo)
/*     */   {
/*  66 */     MyAuthenticator authenticator = null;
/*  67 */     Properties pro = mailInfo.getProperties();
/*     */ 
/*  69 */     if (mailInfo.isValidate()) {
/*  70 */       authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
/*     */     }
/*     */ 
/*  73 */     Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
/*     */     try
/*     */     {
/*  76 */       Message mailMessage = new MimeMessage(sendMailSession);
/*     */ 
/*  78 */       Address from = new InternetAddress(mailInfo.getFromAddress());
/*     */ 
/*  80 */       mailMessage.setFrom(from);
/*     */ 
/*  82 */       Address to = new InternetAddress(mailInfo.getToAddress());
/*     */ 
/*  84 */       mailMessage.setRecipient(Message.RecipientType.TO, to);
/*     */ 
/*  86 */       mailMessage.setSubject(mailInfo.getSubject());
/*     */ 
/*  88 */       mailMessage.setSentDate(new Date());
/*     */ 
/*  90 */       Multipart mainPart = new MimeMultipart();
/*     */ 
/*  92 */       BodyPart html = new MimeBodyPart();
/*     */ 
/*  94 */       html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
/*  95 */       mainPart.addBodyPart(html);
/*     */ 
/*  97 */       mailMessage.setContent(mainPart);
/*     */ 
/*  99 */       Transport.send(mailMessage);
/* 100 */       return true;
/*     */     } catch (MessagingException ex) {
/* 102 */       ex.printStackTrace();
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.util.mail.MailSender
 * JD-Core Version:    0.6.2
 */