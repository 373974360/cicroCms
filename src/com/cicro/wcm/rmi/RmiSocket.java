/*    */ package com.cicro.wcm.rmi;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ import java.rmi.server.RMISocketFactory;
/*    */ 
/*    */ public class RmiSocket extends RMISocketFactory
/*    */ {
/*    */   public Socket createSocket(String host, int port)
/*    */     throws IOException
/*    */   {
/* 10 */     return new Socket(host, port);
/*    */   }
/*    */ 
/*    */   public ServerSocket createServerSocket(int port) throws IOException {
/* 14 */     if (port == 0)
/* 15 */       port = 5099;
/* 16 */     return new ServerSocket(port);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.RmiSocket
 * JD-Core Version:    0.6.2
 */