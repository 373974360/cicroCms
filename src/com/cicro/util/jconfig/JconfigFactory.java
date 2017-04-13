/*    */ package com.cicro.util.jconfig;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class JconfigFactory
/*    */ {
/*  7 */   private static String defaultPath = JconfigFactory.class.getClassLoader().getResource("AllConfigDescrion_WCM.xml").getFile();
/*    */ 
/*  9 */   private static JconfigUtil jfu = null;
/*    */ 
/*    */   public static JconfigUtil getJconfigUtilInstance(String configName)
/*    */   {
/* 17 */     String AllConfigPath = null;
/* 18 */     if (AllConfigPath == null)
/* 19 */       AllConfigPath = defaultPath;
/* 20 */     if (jfu == null) {
/* 21 */       jfu = new JconfigUtil(AllConfigPath);
/*    */     }
/* 23 */     String path = jfu.getProperty("path", null, configName);
/*    */ 
/* 25 */     if (path == null) {
/* 26 */       return null;
/*    */     }
/* 28 */     return new JconfigUtil(path);
/*    */   }
/*    */ 
/*    */   public static void setConfigInfo(String path, String configName) {
/* 32 */     if (jfu == null)
/* 33 */       jfu = new JconfigUtil(defaultPath);
/* 34 */     jfu.setProperty("path", path, configName);
/*    */   }
/*    */ 
/*    */   public static String getAllConfigPath()
/*    */   {
/* 39 */     return defaultPath;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 45 */     System.out.println(FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "log4jFile")));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.jconfig.JconfigFactory
 * JD-Core Version:    0.6.2
 */