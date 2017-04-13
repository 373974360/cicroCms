/*    */ package com.cicro.wcm.dao.org.rmi;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ import com.cicro.wcm.rmi.IOrgRmi;
/*    */ import javax.naming.Context;
/*    */ import javax.naming.InitialContext;
/*    */ 
/*    */ public class GetOrgRmi
/*    */ {
/* 10 */   private static String path = "rmi://" + JconfigUtilContainer.bashConfig().getProperty("rmi_ip", "", "org_save_type") + ":" + JconfigUtilContainer.bashConfig().getProperty("rmi_port", "", "org_save_type") + "/orgRmi";
/*    */ 
/*    */   public static IOrgRmi getorgRmi()
/*    */   {
/*    */     try {
/* 15 */       Context namingContext = new InitialContext();
/* 16 */       return (IOrgRmi)namingContext.lookup(path);
/*    */     } catch (Exception e) {
/* 18 */       e.printStackTrace();
/* 19 */     }return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.rmi.GetOrgRmi
 * JD-Core Version:    0.6.2
 */