/*    */ package com.cicro.wcm.bean.system.template;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TemplateResourcesBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5959524680485539641L;
/* 17 */   private String file_name = "";
/* 18 */   private String file_type = "";
/* 19 */   private String file_size = "";
/* 20 */   private String file_path = "";
/*    */ 
/* 22 */   public String getFile_path() { return this.file_path; }
/*    */ 
/*    */   public void setFile_path(String filePath) {
/* 25 */     this.file_path = filePath;
/*    */   }
/*    */   public String getFile_name() {
/* 28 */     return this.file_name;
/*    */   }
/*    */   public void setFile_name(String fileName) {
/* 31 */     this.file_name = fileName;
/*    */   }
/*    */   public String getFile_type() {
/* 34 */     return this.file_type;
/*    */   }
/*    */   public void setFile_type(String fileType) {
/* 37 */     this.file_type = fileType;
/*    */   }
/*    */   public String getFile_size() {
/* 40 */     return this.file_size;
/*    */   }
/*    */   public void setFile_size(String fileSize) {
/* 43 */     this.file_size = fileSize;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.template.TemplateResourcesBean
 * JD-Core Version:    0.6.2
 */