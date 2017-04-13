/*    */ package com.cicro.wcm.template;
/*    */ 
/*    */ import com.cicro.util.io.FileOperation;
/*    */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*    */ import com.cicro.wcm.rmi.file.FileRmiFactory;
/*    */ import com.cicro.wcm.services.system.template.TemplateUtils;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class TemplateBase
/*    */ {
/*    */   public static boolean saveTemplateFile(TemplateEditBean teb)
/*    */   {
/* 28 */     return FileRmiFactory.saveTemplateFile(teb.getSite_id(), teb);
/*    */   }
/*    */ 
/*    */   public static boolean saveTemplateFileHandl(TemplateEditBean teb) {
/* 32 */     TemplateUtils.showMap();
/* 33 */     System.out.println("key >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + teb.getT_id() + "_" + teb.getSite_id());
/* 34 */     String path = TemplateUtils.getTemplatePath(teb.getT_id() + "_" + teb.getSite_id());
/* 35 */     System.out.println("save file at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + path);
/* 36 */     TemplateUtils.mkDirectory(new File(path));
/*    */     try {
/* 38 */       return FileOperation.writeStringToFile(path, teb.getT_content(), false, "utf-8");
/*    */     } catch (IOException e) {
/* 40 */       e.printStackTrace();
/*    */     }
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean delTemplateFile(String t_id, String site_id, String app_id) {
/* 46 */     String path = TemplateUtils.getTemplatePath(t_id + "_" + site_id);
/* 47 */     System.out.println("delete file at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + path);
/*    */     try {
/* 49 */       return FileRmiFactory.delFile(site_id, path);
/*    */     } catch (Exception e) {
/* 51 */       e.printStackTrace();
/*    */     }
/* 53 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.TemplateBase
 * JD-Core Version:    0.6.2
 */