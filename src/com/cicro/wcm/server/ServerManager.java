/*     */ package com.cicro.wcm.server;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ 
/*     */ public final class ServerManager
/*     */ {
/*  16 */   private static String SERVER_TYPE = null;
/*  17 */   private static String IS_LINUX = null;
/*  18 */   private static String IS_WINDOWS = null;
/*  19 */   private static String IS_TOMCAT = null;
/*  20 */   private static String IS_JBOSS = null;
/*  21 */   private static String IS_TONGWEB = null;
/*  22 */   private static String IS_WEBLOGIC = null;
/*  23 */   private static String IS_WEBLOGIC7 = null;
/*  24 */   private static String IS_WEBLOGIC8 = null;
/*  25 */   private static String IS_WEBLOGIC_UNDER7 = null;
/*  26 */   private static String IS_WE = null;
/*  27 */   private static String IS_WEBSPHERE = null;
/*  28 */   private static String APP_SERVER_VERSION = null;
/*  29 */   private static int APP_SERVER_MAJOR_VERSION = -1;
/*  30 */   private static JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("bashConfig");
/*  31 */   public static final String LOCAL_IP = bc.getProperty("ip", "", "local_ip");
/*     */ 
/*     */   public static String getServerType()
/*     */   {
/*  38 */     if (SERVER_TYPE != null) {
/*  39 */       return SERVER_TYPE;
/*     */     }
/*     */ 
/*  42 */     String serverType = bc.getProperty("type", "", "application_server_type");
/*  43 */     if ((serverType == null) || (serverType.trim().length() == 0)) {
/*  44 */       serverType = "tomcat";
/*     */     }
/*  46 */     serverType = serverType.trim().toLowerCase();
/*  47 */     if (serverType.indexOf("weblogic") != -1) {
/*  48 */       return ServerManager.SERVER_TYPE = "weblogic";
/*     */     }
/*  50 */     if (serverType.indexOf("tomcat") != -1) {
/*  51 */       return ServerManager.SERVER_TYPE = "tomcat";
/*     */     }
/*  53 */     if (serverType.indexOf("jboss") != -1) {
/*  54 */       return ServerManager.SERVER_TYPE = "jboss";
/*     */     }
/*  56 */     if (serverType.indexOf("websphere") != -1) {
/*  57 */       return ServerManager.SERVER_TYPE = "websphere";
/*     */     }
/*  59 */     if (serverType.indexOf("tongweb") != -1) {
/*  60 */       return ServerManager.SERVER_TYPE = "tongweb";
/*     */     }
/*  62 */     SERVER_TYPE = serverType; return serverType;
/*     */   }
/*     */ 
/*     */   public static boolean isWebsphere()
/*     */   {
/*  70 */     if (IS_WEBSPHERE != null) {
/*  71 */       return "true".equals(IS_WEBSPHERE);
/*     */     }
/*  73 */     String serverType = getServerType();
/*  74 */     if ("websphere" == serverType) {
/*  75 */       IS_WEBSPHERE = "true";
/*  76 */       return true;
/*     */     }
/*  78 */     IS_WEBSPHERE = "false";
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isTomcat()
/*     */   {
/*  87 */     if (IS_TOMCAT != null) {
/*  88 */       return "true".equals(IS_TOMCAT);
/*     */     }
/*  90 */     String serverType = getServerType();
/*  91 */     if ("tomcat" == serverType) {
/*  92 */       IS_TOMCAT = "true";
/*  93 */       return true;
/*     */     }
/*  95 */     IS_TOMCAT = "false";
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isJBoss()
/*     */   {
/* 104 */     if (IS_JBOSS != null) {
/* 105 */       return "true".equals(IS_JBOSS);
/*     */     }
/* 107 */     String serverType = getServerType();
/* 108 */     if ("jboss" == serverType) {
/* 109 */       IS_JBOSS = "true";
/* 110 */       return true;
/*     */     }
/* 112 */     IS_JBOSS = "false";
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isTongWeb()
/*     */   {
/* 121 */     if (IS_TONGWEB != null) {
/* 122 */       return "true".equals(IS_TONGWEB);
/*     */     }
/* 124 */     String serverType = getServerType();
/* 125 */     if ("tongweb" == serverType) {
/* 126 */       IS_TONGWEB = "true";
/* 127 */       return true;
/*     */     }
/* 129 */     IS_TONGWEB = "false";
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isWeblogic()
/*     */   {
/* 138 */     if (IS_WEBLOGIC != null) {
/* 139 */       return "true".equals(IS_WEBLOGIC);
/*     */     }
/* 141 */     String serverType = getServerType();
/* 142 */     if ("weblogic" == serverType) {
/* 143 */       IS_WEBLOGIC = "true";
/* 144 */       return true;
/*     */     }
/* 146 */     IS_WEBLOGIC = "false";
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isWindows()
/*     */   {
/* 156 */     if (IS_WINDOWS != null) {
/* 157 */       return "true".equals(IS_WINDOWS);
/*     */     }
/*     */ 
/* 160 */     if (System.getProperty("os.name").toLowerCase().contains("windows")) {
/* 161 */       IS_WINDOWS = "true";
/* 162 */       return true;
/*     */     }
/*     */ 
/* 165 */     IS_WINDOWS = "false";
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getWCMRoot()
/*     */   {
/* 176 */     return bc.getProperty("path", "", "root_path");
/*     */   }
/*     */ 
/*     */   public static String getWCMVersion()
/*     */   {
/* 184 */     return bc.getProperty("value", "6.2", "application_server_version");
/*     */   }
/*     */ 
/*     */   public static String getServerRoot()
/*     */   {
/* 192 */     return bc.getProperty("path", "", "application_server_path");
/*     */   }
/*     */ 
/*     */   public static String getApacheRoot()
/*     */   {
/* 200 */     return bc.getProperty("path", "", "application_server_path");
/*     */   }
/*     */ 
/*     */   public static String getHostRoot()
/*     */   {
/* 208 */     return bc.getProperty("path", "", "hostRoot_path");
/*     */   }
/*     */ 
/*     */   public static String getProxyServer()
/*     */   {
/* 216 */     return bc.getProperty("value", "apache", "proxy_server");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ServerManager
 * JD-Core Version:    0.6.2
 */