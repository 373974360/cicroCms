/*    */ package com.cicro.wcm.bean.system.template;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TemplateClassBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1331183539900698192L;
/*    */   private int id;
/*    */   private int tclass_id;
/*    */   private String tclass_ename;
/*    */   private String tclass_cname;
/*    */   private String tclass_memo;
/*    */   private String app_id;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 21 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 24 */     this.id = id;
/*    */   }
/*    */   public int getTclass_id() {
/* 27 */     return this.tclass_id;
/*    */   }
/*    */   public String getTclass_ename() {
/* 30 */     return this.tclass_ename;
/*    */   }
/*    */   public String getTclass_cname() {
/* 33 */     return this.tclass_cname;
/*    */   }
/*    */   public String getTclass_memo() {
/* 36 */     return this.tclass_memo;
/*    */   }
/*    */   public String getApp_id() {
/* 39 */     return this.app_id;
/*    */   }
/*    */   public void setTclass_id(int tclassId) {
/* 42 */     this.tclass_id = tclassId;
/*    */   }
/*    */   public void setTclass_ename(String tclassEname) {
/* 45 */     if (tclassEname == null) {
/* 46 */       tclassEname = " ";
/*    */     }
/* 48 */     this.tclass_ename = tclassEname;
/*    */   }
/*    */   public void setTclass_cname(String tclassCname) {
/* 51 */     if (tclassCname == null) {
/* 52 */       tclassCname = " ";
/*    */     }
/* 54 */     this.tclass_cname = tclassCname;
/*    */   }
/*    */   public void setTclass_memo(String tclassMemo) {
/* 57 */     if (tclassMemo == null) {
/* 58 */       tclassMemo = " ";
/*    */     }
/* 60 */     this.tclass_memo = tclassMemo;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 63 */     this.app_id = appId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.template.TemplateClassBean
 * JD-Core Version:    0.6.2
 */