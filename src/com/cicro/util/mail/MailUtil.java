/*    */ package com.cicro.util.mail;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;

import com.cicro.util.CryptoTools;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ 
/*    */ public class MailUtil
/*    */ {
/*  8 */   private static String mailserverhost = JconfigUtilContainer.bashConfig().getProperty("mailserverhost", "", "email");
/*  9 */   private static String fromemail = JconfigUtilContainer.bashConfig().getProperty("fromemail", "", "email");
/* 10 */   private static String password = JconfigUtilContainer.bashConfig().getProperty("password", "", "email");
/*    */ 
/*    */   public static void mailutil(String mail, String title, String content) {
			try {
				title = new String(title.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
/* 16 */     MailSenderInfo mailInfo = new MailSenderInfo();
/*    */ 
/* 35 */     mailInfo.setMailServerHost(mailserverhost);
/*    */ 
/* 37 */     mailInfo.setValidate(true);
/* 38 */     mailInfo.setUserName(fromemail);
/* 39 */     mailInfo.setPassword(password);
/* 40 */     mailInfo.setFromAddress(fromemail);
/*    */ 
/* 51 */     mailInfo.setToAddress(mail);
/* 52 */     mailInfo.setSubject(title);
/* 53 */     mailInfo.setContent(content);
/*    */ 
/* 57 */     MailSender.sendHtmlMail(mailInfo);
/*    */   }
/*    */ 
/*    */   public static boolean sendInfo(String email, String name)
/*    */   {
/*    */     try {
/* 63 */       CryptoTools ct = new CryptoTools();
/* 64 */       name = ct.encode(name).replaceAll("=", "DENGHAO").replaceAll("&", "ANDYU").replaceAll("#", "JINGHAO").replaceAll("\\+", "JIAHAO");
/* 65 */       String title = "感谢邮件";
			   byte[] t_iso = title.getBytes("ISO8859-1");
			   String ut_iso = new String(t_iso, "ISO8859-1");
/*    */ 
/* 67 */       String html = "<div><br/>你好：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢你使用西安市人社局网站邮件订阅系统，我们将为及时您推送西安市人社局网站的信息，请您注意查收。<br/>";
/* 71 */       html = html + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>";
/*    */ 
/* 73 */       mailutil(email, ut_iso, html);
/* 74 */       return true;
/*    */     } catch (Exception e) {
/* 76 */       e.printStackTrace();
/* 77 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] arr)
/*    */   {
/* 82 */     sendInfo("373974360@qq.com", "中国人");
/*    */   }
/*    */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.util.mail.MailUtil
 * JD-Core Version:    0.6.2
 */