/*    */ package com.cicro.util;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class CheckAuthCode
/*    */ {
/*    */   public static boolean isMatch(HttpServletRequest request)
/*    */   {
/* 20 */     HttpSession session = request.getSession();
/*    */ 
/* 22 */     String inputAuthCode = request.getParameter("authcode");
/*    */ 
/* 24 */     if ((inputAuthCode == null) || (session.getAttribute("AUTHCODE") == null)) {
/* 25 */       return false;
/*    */     }
/*    */ 
/* 28 */     String sessionAuthCode = (String)session.getAttribute("AUTHCODE");
/*    */ 
/* 30 */     if (!inputAuthCode.equals(sessionAuthCode)) {
/* 31 */       return false;
/*    */     }
/* 33 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.CheckAuthCode
 * JD-Core Version:    0.6.2
 */