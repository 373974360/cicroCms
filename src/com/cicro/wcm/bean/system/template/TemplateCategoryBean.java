/*    */ package com.cicro.wcm.bean.system.template;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TemplateCategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3183801738195540001L;
/*  8 */   private int id = 0;
/*  9 */   private int tcat_id = 0;
/* 10 */   private int tclass_id = 0;
/* 11 */   private String tcat_ename = "";
/* 12 */   private String tcat_cname = "";
/* 13 */   private int parent_id = 0;
/* 14 */   private String tcat_position = "";
/* 15 */   private String tcat_memo = "";
/* 16 */   private String app_id = "";
/* 17 */   private String site_id = "";
/* 18 */   private int sort_id = 999;
/*    */ 
/* 20 */   public int getSort_id() { return this.sort_id; }
/*    */ 
/*    */   public void setSort_id(int sortId) {
/* 23 */     this.sort_id = sortId;
/*    */   }
/*    */   public int getTcat_id() {
/* 26 */     return this.tcat_id;
/*    */   }
/*    */   public int getTclass_id() {
/* 29 */     return this.tclass_id;
/*    */   }
/*    */   public int getParent_id() {
/* 32 */     return this.parent_id;
/*    */   }
/*    */   public String getTcat_position() {
/* 35 */     return this.tcat_position;
/*    */   }
/*    */   public String getTcat_memo() {
/* 38 */     return this.tcat_memo;
/*    */   }
/*    */   public String getApp_id() {
/* 41 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 44 */     return this.site_id;
/*    */   }
/*    */   public void setTcat_id(int tcatId) {
/* 47 */     this.tcat_id = tcatId;
/*    */   }
/*    */   public void setTclass_id(int tclassId) {
/* 50 */     this.tclass_id = tclassId;
/*    */   }
/*    */   public void setParent_id(int parentId) {
/* 53 */     this.parent_id = parentId;
/*    */   }
/*    */   public void setTcat_position(String tcatPosition) {
/* 56 */     if (tcatPosition == null) {
/* 57 */       tcatPosition = " ";
/*    */     }
/* 59 */     this.tcat_position = tcatPosition;
/*    */   }
/*    */   public void setTcat_memo(String tcatMemo) {
/* 62 */     if (tcatMemo == null) {
/* 63 */       tcatMemo = " ";
/*    */     }
/* 65 */     this.tcat_memo = tcatMemo;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 68 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 71 */     this.site_id = siteId;
/*    */   }
/*    */   public int getId() {
/* 74 */     return this.id;
/*    */   }
/*    */   public String getTcat_ename() {
/* 77 */     return this.tcat_ename;
/*    */   }
/*    */   public String getTcat_cname() {
/* 80 */     return this.tcat_cname;
/*    */   }
/*    */   public void setId(int id) {
/* 83 */     this.id = id;
/*    */   }
/*    */   public void setTcat_ename(String tcatEname) {
/* 86 */     if (tcatEname == null) {
/* 87 */       tcatEname = " ";
/*    */     }
/* 89 */     this.tcat_ename = tcatEname;
/*    */   }
/*    */   public void setTcat_cname(String tcatCname) {
/* 92 */     if (tcatCname == null) {
/* 93 */       tcatCname = " ";
/*    */     }
/* 95 */     this.tcat_cname = tcatCname;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.template.TemplateCategoryBean
 * JD-Core Version:    0.6.2
 */