/*    */ package com.cicro.wcm.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ class ExecuteCommand$1 extends Thread
/*    */ {
/*    */   ExecuteCommand$1(Process paramProcess)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     try
/*    */     {
/* 24 */       InputStream errorStream = this.val$process.getErrorStream();
/* 25 */       while (errorStream.read() != -1);
/* 26 */       errorStream.close();
/*    */     } catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ExecuteCommand.1
 * JD-Core Version:    0.6.2
 */