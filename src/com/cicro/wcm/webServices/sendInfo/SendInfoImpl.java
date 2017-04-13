/*    */ package com.cicro.wcm.webServices.sendInfo;
/*    */ 
/*    */ import com.cicro.wcm.services.sendInfo.ReceiveConfigManager;
/*    */ import com.cicro.wcm.services.sendInfo.SendInfoDispose;
/*    */ import com.cicro.wcm.services.sendInfo.SendRecordManager;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class SendInfoImpl
/*    */   implements ISendInfo
/*    */ {
/*    */   public String getReceiveConfigForJSON()
/*    */   {
/* 14 */     return ReceiveConfigManager.getReceiveConfigForJSON();
/*    */   }
/*    */ 
/*    */   public String getReceiveConfigForXML()
/*    */   {
/* 24 */     return ReceiveConfigManager.getReceiveConfigForXML();
/*    */   }
/*    */ 
/*    */   public boolean sendInfo(String xml, String user)
/*    */   {
/* 35 */     if ((xml == null) || ("".equals(xml)) || (user == null) || ("".equals(user)))
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */ 
/* 40 */     System.out.println(xml.toString());
/* 41 */     SendInfoDispose.sendInfoHandl(xml, user);
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean recordAdoptStatus(String xml)
/*    */   {
/* 54 */     if ((xml == null) || ("".equals(xml)))
/*    */     {
/* 56 */       return false;
/*    */     }
/*    */ 
/* 59 */     return SendRecordManager.recordAdoptStatusHandl(xml);
/*    */   }
/*    */ 
/*    */   public boolean sendInfoFormThirdParty(String xml, String username, String password)
/*    */   {
/* 71 */     if ((xml == null) || ("".equals(xml)))
/*    */     {
/* 73 */       return false;
/*    */     }
/*    */ 
/* 76 */     SendInfoDispose.sendInfoHandlFormThirdParty(xml);
/* 77 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.webServices.sendInfo.SendInfoImpl
 * JD-Core Version:    0.6.2
 */