/*    */ package com.cicro.wcm.dao.org;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ import com.cicro.wcm.dao.org.dept.DeptDAODBImpl;
/*    */ import com.cicro.wcm.dao.org.dept.DeptDAORMIImpl;
/*    */ import com.cicro.wcm.dao.org.dept.IDeptDAO;
/*    */ import com.cicro.wcm.dao.org.user.IUserDAO;
/*    */ import com.cicro.wcm.dao.org.user.UserDAODBImpl;
/*    */ import com.cicro.wcm.dao.org.user.UserDAORMIImpl;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class OrgDAOFactory
/*    */ {
/* 13 */   private static String org_save_type = JconfigUtilContainer.bashConfig().getProperty("type", "db", "org_save_type");
/*    */ 
/*    */   public static IDeptDAO getDeptDao()
/*    */   {
/* 17 */     if ("db".equals(org_save_type))
/*    */     {
/* 19 */       return new DeptDAODBImpl();
/*    */     }
/* 21 */     return new DeptDAORMIImpl();
/*    */   }
/*    */ 
/*    */   public static IUserDAO getUserDao()
/*    */   {
/* 26 */     if ("db".equals(org_save_type))
/*    */     {
/* 28 */       return new UserDAODBImpl();
/*    */     }
/* 30 */     return new UserDAORMIImpl();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 35 */     System.out.println("org_save_type-------" + org_save_type);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.OrgDAOFactory
 * JD-Core Version:    0.6.2
 */