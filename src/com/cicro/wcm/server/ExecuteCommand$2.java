/*    */ package com.cicro.wcm.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ class ExecuteCommand$2 extends Thread
/*    */ {
/*    */   ExecuteCommand$2(Process paramProcess)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     try
/*    */     {
/* 51 */       InputStream errorStream = this.val$process.getErrorStream();
/* 52 */       while (errorStream.read() != -1);
/* 53 */       errorStream.close();
/*    */     } catch (IOException e) {
/* 55 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.ExecuteCommand.2
 * JD-Core Version:    0.6.2
 */