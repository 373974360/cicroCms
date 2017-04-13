/*    */ package com.cicro.wcm.services.cms.info;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigFactory;
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ModelConfig
/*    */ {
/* 17 */   private static Map<String, Map<String, String>> mp = new HashMap();
/*    */ 
/*    */   static {
/* 20 */     readConfig();
/*    */   }
/*    */ 
/*    */   private static void readConfig()
/*    */   {
/* 28 */     JconfigUtil jf = JconfigFactory.getJconfigUtilInstance("model_config");
/* 29 */     String[] modelNames = jf.getCategorys();
/* 30 */     for (int i = 0; i < modelNames.length; i++)
/*    */     {
/* 32 */       Map model_values_mp = new HashMap();
/* 33 */       model_values_mp.put("TableName", jf.getProperty("TableName", "", modelNames[i]));
/* 34 */       model_values_mp.put("AddSQL", jf.getProperty("AddSQL", "", modelNames[i]));
/* 35 */       model_values_mp.put("UpdateSQL", jf.getProperty("UpdateSQL", "", modelNames[i]));
/* 36 */       model_values_mp.put("SelectSQL", jf.getProperty("SelectSQL", "", modelNames[i]));
/* 37 */       model_values_mp.put("class_name", jf.getProperty("class_name", "", modelNames[i]));
/* 38 */       mp.put(modelNames[i], model_values_mp);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static Map<String, String> getModelMap(String modelName) {
/* 43 */     return (Map)mp.get(modelName);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 51 */     Map mp = getModelMap("gkvideo");
/*    */ 
/* 53 */     System.out.println("------------" + (String)mp.get("AddSQL"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.ModelConfig
 * JD-Core Version:    0.6.2
 */