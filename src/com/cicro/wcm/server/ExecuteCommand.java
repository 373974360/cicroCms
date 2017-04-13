/*    */ package com.cicro.wcm.server;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ public class ExecuteCommand
/*    */ {
/*    */   public static void executeCommandHandl(String[] windowsCommand, String[] linuxCommand)
/*    */     throws IOException
/*    */   {
/*  9 */     String os = System.getProperty("os.name");
/*    */     String[] command;
/* 11 */     if (ServerManager.isWindows()) {
/* 12 */       command = windowsCommand;
/*    */     }
/*    */     else
/*    */     {
/*    */       String[] command;
/* 13 */       if (os.startsWith("Linux"))
/* 14 */         command = linuxCommand;
/*    */       else
/* 16 */         throw new IOException("Unknown operating system: " + os);
/*    */     }
/*    */     String[] command;
/* 19 */     Process process = Runtime.getRuntime().exec(command);
/*    */ 
/* 21 */     new ExecuteCommand.1(process)
/* 31 */       .start();
/*    */   }
/*    */ 
/*    */   public static String executeCommandHandlRStr(String[] windowsCommand, String[] linuxCommand) throws IOException
/*    */   {
/* 36 */     String os = System.getProperty("os.name");
/*    */     String[] command;
/* 38 */     if (os.startsWith("Windows")) {
/* 39 */       command = windowsCommand;
/*    */     }
/*    */     else
/*    */     {
/*    */       String[] command;
/* 40 */       if (os.startsWith("Linux"))
/* 41 */         command = linuxCommand;
/*    */       else
/* 43 */         throw new IOException("Unknown operating system: " + os);
/*    */     }
/*    */     String[] command;
/* 46 */     Process process = Runtime.getRuntime().exec(command);
/*    */ 
/* 48 */     new ExecuteCommand.2(process)
/* 58 */       .start();
/*    */ 
/* 60 */     BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
/* 61 */     String r = reader.readLine();
/* 62 */     reader.close();
/* 63 */     return r;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ExecuteCommand
 * JD-Core Version:    0.6.2
 */