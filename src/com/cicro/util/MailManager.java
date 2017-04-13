/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import javax.activation.DataHandler;
/*     */ import javax.activation.FileDataSource;
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
/*     */ public class MailManager
/*     */ {
/*  34 */   private String strTo = "";
/*  35 */   private String strFrom = "";
/*  36 */   private String strHost = "";
/*  37 */   private String strFilename = "";
/*     */ 
/*  39 */   private String strSubject = "";
/*  40 */   private String strEncoding = "utf-8";
/*  41 */   private boolean sendHtmlFormat = false;
/*     */ 
/*  43 */   private boolean debug = false;
/*     */ 
/*  45 */   String strMessage = "";
/*  46 */   Vector<String> vfile = new Vector(10, 10);
/*  47 */   private static String mailAuthor = null;
/*  48 */   private static String mailAuthorPassword = null;
/*     */ 
/*     */   static {
/*     */     try {
/*  52 */       URL url = MailManager.class.getClassLoader().getResource(
/*  53 */         "service.properties");
/*     */ 
/*  55 */       if (url == null) {
/*  56 */         System.out.println(Encode.gbkToSystem("系统配置错误： 属性文件不存在。"));
/*     */       }
/*  58 */       File serviceFile = new File(url.getFile());
/*     */ 
/*  60 */       Properties prop = new Properties();
/*  61 */       prop.load(new FileInputStream(serviceFile));
/*  62 */       mailAuthor = prop.getProperty("mail.sender");
/*  63 */       mailAuthorPassword = prop.getProperty("mail.auth.password");
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*  67 */       ex.printStackTrace(System.out);
/*     */     }
/*     */   }
/*     */ 
/*     */   public MailManager(String strTo, String strFrom, String smtpServer, String strSubject, String strEncoding)
/*     */   {
/*  85 */     this.strTo = strTo;
/*  86 */     this.strFrom = strFrom;
/*  87 */     this.strHost = smtpServer;
/*  88 */     this.strSubject = strSubject;
/*  89 */     this.strEncoding = strEncoding;
/*     */   }
/*     */ 
/*     */   public MailManager(String strTo, String strFrom, String smtpServer, String strSubject, String strEncoding, boolean sendHtmlFormat)
/*     */   {
/* 108 */     this.strTo = strTo;
/* 109 */     this.strFrom = strFrom;
/* 110 */     this.strHost = smtpServer;
/* 111 */     this.strSubject = strSubject;
/* 112 */     this.strEncoding = strEncoding;
/* 113 */     this.sendHtmlFormat = sendHtmlFormat;
/*     */   }
/*     */ 
/*     */   public void attachfile(String strFileNameP)
/*     */   {
/* 120 */     this.vfile.addElement(strFileNameP);
/*     */   }
/*     */ 
/*     */   public void setMessage(String strMsgP)
/*     */   {
/* 127 */     this.strMessage = strMsgP;
/*     */   }
/*     */ 
/*     */   public void setDebug(boolean debug)
/*     */   {
/* 134 */     this.debug = debug;
/*     */   }
/*     */ 
/*     */   public boolean startSend()
/*     */   {
/* 143 */     Properties props = System.getProperties();
/* 144 */     props.put("mail.smtp.host", this.strHost);
/*     */ 
/* 147 */     props.put("mail.smtp.auth", "true");
/*     */ 
/* 150 */     Session session = Session.getDefaultInstance(props, null);
/* 151 */     session.setDebug(this.debug);
/*     */ 
/* 155 */     Transport transport = null;
/*     */     try {
/* 157 */       transport = session.getTransport("smtp");
/* 158 */       transport.connect(this.strHost, 25, mailAuthor, mailAuthorPassword);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 162 */       ex.printStackTrace(System.out);
/* 163 */       return false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 169 */       MimeMessage msg = new MimeMessage(session);
/* 170 */       msg.setFrom(new InternetAddress(this.strFrom));
/* 171 */       InternetAddress[] address = { 
/* 172 */         new InternetAddress(this.strTo) };
/* 173 */       msg.setRecipients(Message.RecipientType.TO, address);
/*     */ 
/* 176 */       if (this.strSubject != null)
/*     */       {
/* 178 */         this.strSubject = 
/* 180 */           ("=?" + this.strEncoding + "?B?" + 
/* 179 */           MailEncode.encode(this.strSubject) + 
/* 180 */           "?=");
/*     */       }
/*     */       else
/*     */       {
/* 184 */         this.strSubject = "";
/*     */       }
/*     */ 
/* 189 */       msg.setSubject(this.strSubject);
/*     */ 
/* 191 */       Multipart mp = new MimeMultipart();
/*     */ 
/* 193 */       MimeBodyPart mbp1 = new MimeBodyPart();
/*     */ 
/* 195 */       if (this.sendHtmlFormat) {
/* 196 */         mbp1.setContent(this.strMessage, 
/* 197 */           "text/html;charset=" + this.strEncoding);
/*     */       }
/*     */       else {
/* 200 */         mbp1.setText(this.strMessage, this.strEncoding);
/*     */       }
/*     */ 
/* 203 */       mp.addBodyPart(mbp1);
/*     */ 
/* 208 */       Enumeration efile = this.vfile.elements();
/* 209 */       while (efile.hasMoreElements()) {
/* 210 */         MimeBodyPart mbp2 = new MimeBodyPart();
/* 211 */         this.strFilename = efile.nextElement().toString();
/* 212 */         FileDataSource fds = new FileDataSource(this.strFilename);
/* 213 */         mbp2.setDataHandler(new DataHandler(fds));
/*     */ 
/* 215 */         String strTempFileName = fds.getName();
/*     */         try {
/* 217 */           strTempFileName = new String(strTempFileName.getBytes(
/* 218 */             "ISO8859-1"), this.strEncoding);
/*     */         }
/*     */         catch (Exception ex) {
/* 221 */           ex.printStackTrace(System.out);
/*     */         }
/*     */ 
/* 224 */         mbp2.setFileName(strTempFileName);
/* 225 */         mp.addBodyPart(mbp2);
/*     */       }
/* 227 */       this.vfile.removeAllElements();
/* 228 */       msg.setContent(mp);
/* 229 */       msg.setSentDate(new Date());
/*     */       try
/*     */       {
/* 235 */         transport.sendMessage(msg, 
/* 236 */           msg.getRecipients(
/* 237 */           Message.RecipientType.TO));
/*     */       }
/*     */       catch (MessagingException ex1) {
/* 240 */         ex1.printStackTrace(System.out);
/* 241 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (MessagingException mex)
/*     */     {
/* 248 */       mex.printStackTrace();
/* 249 */       return false;
/*     */     }
/* 251 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean startSend(boolean isEncodingSubject)
/*     */   {
/* 260 */     Properties props = System.getProperties();
/* 261 */     props.put("mail.smtp.host", this.strHost);
/*     */ 
/* 264 */     props.put("mail.smtp.auth", "true");
/*     */ 
/* 267 */     Session session = Session.getDefaultInstance(props, null);
/* 268 */     session.setDebug(this.debug);
/*     */ 
/* 272 */     Transport transport = null;
/*     */     try {
/* 274 */       transport = session.getTransport("smtp");
/* 275 */       transport.connect(this.strHost, 25, mailAuthor, mailAuthorPassword);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 279 */       ex.printStackTrace(System.out);
/* 280 */       return false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 286 */       MimeMessage msg = new MimeMessage(session);
/* 287 */       msg.setFrom(new InternetAddress(this.strFrom));
/* 288 */       InternetAddress[] address = { 
/* 289 */         new InternetAddress(this.strTo) };
/* 290 */       msg.setRecipients(Message.RecipientType.TO, address);
/*     */ 
/* 293 */       if (this.strSubject != null) {
/* 294 */         this.strSubject = Encode.encode(this.strSubject, this.strEncoding, 
/* 295 */           System.getProperty("file.encoding"));
/*     */       }
/*     */       else
/*     */       {
/* 299 */         this.strSubject = "";
/*     */       }
/*     */ 
/* 305 */       msg.setSubject(this.strSubject);
/*     */ 
/* 307 */       Multipart mp = new MimeMultipart();
/*     */ 
/* 309 */       MimeBodyPart mbp1 = new MimeBodyPart();
/*     */ 
/* 311 */       if (this.sendHtmlFormat) {
/* 312 */         mbp1.setContent(this.strMessage, 
/* 313 */           "text/html;charset=" + this.strEncoding);
/*     */       }
/*     */       else {
/* 316 */         mbp1.setText(this.strMessage, this.strEncoding);
/*     */       }
/*     */ 
/* 319 */       mp.addBodyPart(mbp1);
/*     */ 
/* 324 */       Enumeration efile = this.vfile.elements();
/* 325 */       while (efile.hasMoreElements()) {
/* 326 */         MimeBodyPart mbp2 = new MimeBodyPart();
/* 327 */         this.strFilename = efile.nextElement().toString();
/* 328 */         FileDataSource fds = new FileDataSource(this.strFilename);
/* 329 */         mbp2.setDataHandler(new DataHandler(fds));
/*     */ 
/* 331 */         String strTempFileName = fds.getName();
/*     */         try {
/* 333 */           strTempFileName = new String(strTempFileName.getBytes(
/* 334 */             "ISO8859-1"), this.strEncoding);
/*     */         }
/*     */         catch (Exception ex) {
/* 337 */           ex.printStackTrace(System.out);
/*     */         }
/*     */ 
/* 340 */         mbp2.setFileName(strTempFileName);
/* 341 */         mp.addBodyPart(mbp2);
/*     */       }
/* 343 */       this.vfile.removeAllElements();
/* 344 */       msg.setContent(mp);
/* 345 */       msg.setSentDate(new Date());
/*     */       try
/*     */       {
/* 351 */         transport.sendMessage(msg, 
/* 352 */           msg.getRecipients(
/* 353 */           Message.RecipientType.TO));
/*     */       }
/*     */       catch (MessagingException ex1) {
/* 356 */         ex1.printStackTrace(System.out);
/* 357 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (MessagingException mex)
/*     */     {
/* 364 */       mex.printStackTrace();
/* 365 */       return false;
/*     */     }
/* 367 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 371 */     MailManager mail = new MailManager("anlensony@163.net", "yft@cdms.com", 
/* 372 */       "192.168.60.14", "nihao", 
/* 373 */       "utf-8");
/* 374 */     mail.setMessage("<font size=\"\" color=\"#FF66FF\">这里是邮件内容</font>");
/* 375 */     mail.setDebug(false);
/* 376 */     boolean isSuccess = mail.startSend();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.MailManager
 * JD-Core Version:    0.6.2
 */