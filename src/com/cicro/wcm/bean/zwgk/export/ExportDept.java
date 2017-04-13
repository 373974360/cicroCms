/*    */ package com.cicro.wcm.bean.zwgk.export;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ExportDept
/*    */ {
/*  8 */   private String cateId = "";
/*  9 */   private String catName = "";
/* 10 */   private int countInfo = 0;
/* 11 */   private List<ExportInfo> exportInfo = new ArrayList();
/*    */ 
/*    */   public String getCatName() {
/* 14 */     return this.catName;
/*    */   }
/*    */   public void setCatName(String catName) {
/* 17 */     this.catName = catName;
/*    */   }
/*    */   public int getCountInfo() {
/* 20 */     return this.countInfo;
/*    */   }
/*    */   public void setCountInfo(int countInfo) {
/* 23 */     this.countInfo = countInfo;
/*    */   }
/*    */   public List<ExportInfo> getExportInfo() {
/* 26 */     return this.exportInfo;
/*    */   }
/*    */   public void setExportInfo(List<ExportInfo> exportInfo) {
/* 29 */     this.exportInfo = exportInfo;
/*    */   }
/*    */   public String getCateId() {
/* 32 */     return this.cateId;
/*    */   }
/*    */   public void setCateId(String cateId) {
/* 35 */     this.cateId = cateId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.export.ExportDept
 * JD-Core Version:    0.6.2
 */