/*    */ package com.cicro.util.jconfig;
/*    */ 
/*    */ public class JconfigUtilContainer
/*    */ {
/*  4 */   private static JconfigUtil bashConfig = null;
/*  5 */   private static JconfigUtil systemRole = null;
/*  6 */   private static JconfigUtil apacheConfig = null;
/*  7 */   private static JconfigUtil managerPagePath = null;
/*  8 */   private static JconfigUtil velocityTemplateConfig = null;
/*    */ 
/*    */   static
/*    */   {
/* 12 */     bashConfig = JconfigFactory.getJconfigUtilInstance("bashConfig");
/* 13 */     bashConfig.setParentConfig();
/* 14 */     systemRole = JconfigFactory.getJconfigUtilInstance("systemRole");
/* 15 */     systemRole.setParentConfig();
/* 16 */     apacheConfig = JconfigFactory.getJconfigUtilInstance("apacheConfigInfo");
/* 17 */     apacheConfig.setParentConfig();
/* 18 */     managerPagePath = JconfigFactory.getJconfigUtilInstance("managerPagePath");
/* 19 */     managerPagePath.setParentConfig();
/* 20 */     velocityTemplateConfig = JconfigFactory.getJconfigUtilInstance("velocityTemplate");
/* 21 */     velocityTemplateConfig.setParentConfig();
/*    */   }
/*    */ 
/*    */   public static JconfigUtil getJconfigUtil(String config_name)
/*    */   {
/* 26 */     return JconfigFactory.getJconfigUtilInstance(config_name);
/*    */   }
/*    */ 
/*    */   public static JconfigUtil velocityTemplateConfig()
/*    */   {
/* 31 */     return velocityTemplateConfig;
/*    */   }
/*    */ 
/*    */   public static JconfigUtil bashConfig()
/*    */   {
/* 36 */     return bashConfig;
/*    */   }
/*    */ 
/*    */   public static JconfigUtil systemRole()
/*    */   {
/* 41 */     return systemRole;
/*    */   }
/*    */ 
/*    */   public static JconfigUtil apacheConfig()
/*    */   {
/* 46 */     return apacheConfig;
/*    */   }
/*    */ 
/*    */   public static JconfigUtil managerPagePath()
/*    */   {
/* 51 */     return managerPagePath;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.jconfig.JconfigUtilContainer
 * JD-Core Version:    0.6.2
 */