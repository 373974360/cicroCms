/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.Cookie;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class CookieUtil
/*    */ {
/*    */   public static void main(String[] arr)
/*    */   {
/* 10 */     System.out.println(Integer.parseInt("0x100F", 16));
/*    */   }
/*    */ 
/*    */   public static Cookie getCookie(HttpServletRequest request, String name)
/*    */   {
/* 19 */     Cookie[] cookies = request.getCookies();
/* 20 */     if ((cookies == null) || (name == null) || (name.length() == 0)) {
/* 21 */       return null;
/*    */     }
/* 23 */     for (int i = 0; i < cookies.length; i++) {
/* 24 */       System.out.println("cookies[i].getName()---" + cookies[i].getName() + "--" + cookies[i].getDomain() + "--" + request.getServerName());
/* 25 */       if (name.equals(cookies[i].getName()))
/*    */       {
/* 28 */         return cookies[i];
/*    */       }
/*    */     }
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   public static String getCookieValue(HttpServletRequest request, String name)
/*    */   {
/* 36 */     Cookie cookie = getCookie(request, name);
/* 37 */     if (cookie == null) {
/* 38 */       return "";
/*    */     }
/* 40 */     return cookie.getValue();
/*    */   }
/*    */ 
/*    */   public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie)
/*    */   {
/* 45 */     if (cookie != null) {
/* 46 */       cookie.setPath(getPath(request));
/* 47 */       cookie.setValue("");
/* 48 */       cookie.setMaxAge(0);
/* 49 */       response.addCookie(cookie);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value)
/*    */   {
/* 55 */     setCookie(request, response, name, value, 2592000);
/*    */   }
/*    */ 
/*    */   public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge)
/*    */   {
/* 60 */     Cookie cookie = new Cookie(name, value == null ? "" : value);
/* 61 */     cookie.setMaxAge(maxAge);
/* 62 */     cookie.setPath(getPath(request));
/* 63 */     response.addCookie(cookie);
/*    */   }
/*    */ 
/*    */   private static String getPath(HttpServletRequest request) {
/* 67 */     String path = request.getContextPath();
/* 68 */     return (path == null) || (path.length() == 0) ? "/" : path;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.CookieUtil
 * JD-Core Version:    0.6.2
 */