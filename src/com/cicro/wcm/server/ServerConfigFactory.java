/*    */ package com.cicro.wcm.server;
/*    */ 
/*    */ public class ServerConfigFactory
/*    */ {
/*    */   private static final String DEFAULT_TYPE = "tomcat";
/*    */ 
/*    */   public static IServerConfig getServerConfig()
/*    */   {
/* 22 */     if (ServerManager.isTomcat()) {
/* 23 */       return new ServerConfigTomcatImpl();
/*    */     }
/*    */ 
/* 37 */     return getServerConfig("tomcat");
/*    */   }
/*    */ 
/*    */   public static IServerConfig getServerConfig(String type)
/*    */   {
/* 47 */     if ((type == null) || ((type = type.trim()).length() == 0)) {
/* 48 */       type = "tomcat";
/*    */     }
/* 50 */     type = type.toLowerCase();
/* 51 */     if (type.indexOf("tomcat") != -1) {
/* 52 */       return new ServerConfigTomcatImpl();
/*    */     }
/*    */ 
/* 66 */     return getServerConfig("tomcat");
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ServerConfigFactory
 * JD-Core Version:    0.6.2
 */