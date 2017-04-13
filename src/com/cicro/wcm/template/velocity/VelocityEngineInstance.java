/*    */ package com.cicro.wcm.template.velocity;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ import org.apache.velocity.app.VelocityEngine;
/*    */ 
/*    */ public class VelocityEngineInstance
/*    */ {
/* 18 */   private static VelocityEngine ve = null;
/*    */ 
/*    */   public static VelocityEngine getInstance() {
/* 21 */     if (ve == null) {
/* 22 */       ve = createVelocityEngineInstance();
/*    */     }
/* 24 */     return ve;
/*    */   }
/*    */ 
/*    */   private static VelocityEngine createVelocityEngineInstance() {
/* 28 */     VelocityEngine velocityEngine = new VelocityEngine();
/*    */ 
/* 31 */     String path = JconfigUtilContainer.bashConfig().getProperty("path", "", "hostRoot_path");
/* 32 */     velocityEngine.setProperty("input.encoding", "utf-8");
/* 33 */     velocityEngine.setProperty("output.encoding", "utf-8");
/* 34 */     velocityEngine.setProperty("file.resource.loader.path", path);
/* 35 */     velocityEngine.init();
/* 36 */     return velocityEngine;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.VelocityEngineInstance
 * JD-Core Version:    0.6.2
 */