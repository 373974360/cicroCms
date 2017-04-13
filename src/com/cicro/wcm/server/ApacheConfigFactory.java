/*    */ package com.cicro.wcm.server;
/*    */ 
/*    */ public class ApacheConfigFactory
/*    */ {
/*    */   private static final String DEFAULT_TYPE = "tomcat";
/*    */ 
/*    */   public static IApacheConfig getApacheConfig()
/*    */   {
/* 23 */     if (ServerManager.isTomcat())
/*    */     {
/* 25 */       if ("nginx".equals(ServerManager.getProxyServer()))
/*    */       {
/* 27 */         return new NginxConfigTomcatImpl();
/*    */       }
/*    */ 
/* 30 */       return new ApacheConfigTomcatImpl();
/*    */     }
/*    */ 
/* 44 */     return getApacheConfig("tomcat");
/*    */   }
/*    */ 
/*    */   public static IApacheConfig getApacheConfig(String type)
/*    */   {
/* 54 */     if ((type == null) || ((type = type.trim()).length() == 0)) {
/* 55 */       type = "tomcat";
/*    */     }
/* 57 */     type = type.toLowerCase();
/* 58 */     if (type.indexOf("tomcat") != -1) {
/* 59 */       return new ApacheConfigTomcatImpl();
/*    */     }
/*    */ 
/* 74 */     return getApacheConfig("tomcat");
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ApacheConfigFactory
 * JD-Core Version:    0.6.2
 */