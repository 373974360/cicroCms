/*    */ package com.cicro.license.server;
/*    */ 
/*    */ import com.cicro.license.tools.CPUInfo;
/*    */ import com.cicro.license.tools.CryptoTools;
/*    */ import com.cicro.license.tools.MacAddress;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class GetServerInfo
/*    */ {
/*  5 */   private static String keyStrPrefix = "wcmkisslan";
/*    */ 
/*    */   public static String getServerInfoCodeByEncrypt()
/*    */   {
/* 12 */     String code = "";
/*    */     try {
/* 14 */       String cpu = new CPUInfo().getCPUID();
/* 15 */       String mac = MacAddress.getMacAddress();
/* 16 */       code = keyStrPrefix + "$*$" + cpu + "$*$" + mac;
/* 17 */       CryptoTools ct = new CryptoTools();
/* 18 */       code = ct.encode(code);
/* 19 */       return code;
/*    */     }
/*    */     catch (Throwable e) {
/* 22 */       System.out.println(e);
/* 23 */     }return null;
/*    */   }
/*    */ 
/*    */   public static String getCPUID()
/*    */   {
/* 29 */     return new CPUInfo().getCPUID();
/*    */   }
/*    */ 
/*    */   public static String getMac()
/*    */   {
/* 34 */     return MacAddress.getMacAddress();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 42 */     System.out.println("\r\n code:   " + getServerInfoCodeByEncrypt() + "\r\n");
/*    */   }
/*    */ }

/* Location:           C:\Users\hp\Desktop\WCMLicense.jar
 * Qualified Name:     com.cicro.license.server.GetServerInfo
 * JD-Core Version:    0.6.0
 */