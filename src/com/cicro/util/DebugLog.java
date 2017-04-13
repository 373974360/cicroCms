/*    */ package com.cicro.util;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigFactory;
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.log4j.PropertyConfigurator;
/*    */ 
/*    */ public class DebugLog
/*    */ {
/* 10 */   private static Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger");
/*    */ 
/* 12 */   static { reloadLogConfig(); }
/*    */ 
/*    */ 
/*    */   public static void reloadLogConfig()
/*    */   {
/* 17 */     JconfigUtil bc = 
/* 18 */       JconfigFactory.getJconfigUtilInstance("bashConfig");
/* 19 */     System.out.println(bc.getProperty("path", "", "log4jFile"));
/* 20 */     PropertyConfigurator.configure(bc.getProperty(
/* 21 */       "path", "", "log4jFile"));
/*    */   }
/*    */ 
/*    */   public static void debug(String e)
/*    */   {
/* 26 */     mySonLogger.debug(e);
/*    */   }
/*    */ 
/*    */   public static void info(String e)
/*    */   {
/* 31 */     mySonLogger.info(e);
/*    */   }
/*    */ 
/*    */   public static void warn(String e)
/*    */   {
/* 36 */     mySonLogger.warn(e);
/*    */   }
/*    */ 
/*    */   public static void error(String e)
/*    */   {
/* 41 */     mySonLogger.error(e);
/*    */   }
/*    */ 
/*    */   public static void fatal(String e)
/*    */   {
/* 46 */     mySonLogger.fatal(e);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 51 */     info("info");
/* 52 */     fatal("aaaaaaaaaa");
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.DebugLog
 * JD-Core Version:    0.6.2
 */