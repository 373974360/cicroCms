/*    */ package com.cicro.wcm.services.templet;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.StringWriter;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ import org.apache.velocity.app.Velocity;
/*    */ import org.apache.velocity.app.VelocityEngine;
/*    */ 
/*    */ public class TempletHandl
/*    */ {
/* 11 */   private static VelocityEngine ve = new VelocityEngine();
/*    */ 
/*    */   public static String getTempletPreviewStr(String templet_content)
/*    */   {
/* 15 */     StringWriter html = new StringWriter();
/*    */     try
/*    */     {
/* 18 */       ve.setProperty("resource.loader", "class");
/* 19 */       ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
/* 20 */       VelocityContext context = new VelocityContext();
/* 21 */       context.put("title", "title");
/* 22 */       context.put("content", "content");
/* 23 */       Velocity.evaluate(context, html, "mystring", templet_content);
/*    */     } catch (Exception e) {
/* 25 */       e.printStackTrace();
/*    */     }
/* 27 */     return html.toString();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 31 */     String ss = "<table> <tr><td>$title</td></tr><tr><td>$content</td></tr></table>";
/* 32 */     System.out.println(getTempletPreviewStr(ss));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.templet.TempletHandl
 * JD-Core Version:    0.6.2
 */