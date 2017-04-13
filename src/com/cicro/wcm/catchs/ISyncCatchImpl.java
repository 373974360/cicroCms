/*    */ package com.cicro.wcm.catchs;
/*    */ 
/*    */ import com.cicro.wcm.rmi.ISyncCatchRmi;
/*    */ import java.rmi.RemoteException;
/*    */ import java.rmi.server.UnicastRemoteObject;
/*    */ 
/*    */ public class ISyncCatchImpl extends UnicastRemoteObject
/*    */   implements ISyncCatchRmi
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public ISyncCatchImpl()
/*    */     throws RemoteException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void reloadCateh(String class_name)
/*    */   {
/*    */     try
/*    */     {
/* 23 */       ISyncCatch ic = (ISyncCatch)Class.forName(class_name).newInstance();
/* 24 */       ic.reloadCatch();
/*    */     }
/*    */     catch (InstantiationException e) {
/* 27 */       e.printStackTrace();
/*    */     }
/*    */     catch (IllegalAccessException e) {
/* 30 */       e.printStackTrace();
/*    */     }
/*    */     catch (ClassNotFoundException e) {
/* 33 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.catchs.ISyncCatchImpl
 * JD-Core Version:    0.6.2
 */