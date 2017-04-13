/*    */ package com.cicro.wcm.template.velocity;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityContextUtils
/*    */ {
/* 16 */   private static VelocityContext baseContext = new VelocityContext();
/*    */ 
/*    */   static {
/* 19 */     loadPublicContext();
/* 20 */     loadClassContext();
/*    */   }
/*    */ 
/*    */   public static VelocityContext getContext()
/*    */   {
/* 28 */     return baseContext;
/*    */   }
/*    */ 
/*    */   public static void loadPublicContext()
/*    */   {
/* 37 */     baseContext.put("name", "站点ID");
/*    */   }
/*    */ 
/*    */   public static void loadClassContext()
/*    */   {
/* 45 */     Map map = getClassList();
/* 46 */     Set keys = map.keySet();
/* 47 */     for (String key : keys)
/*    */       try {
/* 49 */         Class obj = Class.forName((String)map.get(key));
/* 50 */         Object o = obj.newInstance();
/* 51 */         baseContext.put(key, o);
/*    */       } catch (ClassNotFoundException e) {
/* 53 */         e.printStackTrace();
/*    */       } catch (InstantiationException e) {
/* 55 */         e.printStackTrace();
/*    */       } catch (IllegalAccessException e) {
/* 57 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public static void updateContext(String key, Object value)
/*    */   {
/* 63 */     baseContext.remove(key);
/* 64 */     baseContext.put(key, value);
/*    */   }
/*    */ 
/*    */   public static void removeContext(String key) {
/* 68 */     baseContext.remove(key);
/*    */   }
/*    */ 
/*    */   private static Map<String, String> getClassList()
/*    */   {
/* 76 */     Map map = new HashMap();
/*    */ 
/* 78 */     map.put("person", "test.Person");
/* 79 */     map.put("msg", "test.Messages");
/* 80 */     map.put("toytool", "ToyTool");
/* 81 */     return map;
/*    */   }
/*    */   public static void main(String[] args) {
/* 84 */     loadClassContext();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.VelocityContextUtils
 * JD-Core Version:    0.6.2
 */