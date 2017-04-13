/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CateClassBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -411032022574587570L;
/*    */   private int class_id;
/*  9 */   private String class_ename = "";
/* 10 */   private String class_cname = "";
/* 11 */   private String class_codo = "";
/* 12 */   private String class_define = "";
/* 13 */   private int class_type = 1;
/* 14 */   private String app_ids = "";
/*    */ 
/* 16 */   public int getClass_id() { return this.class_id; }
/*    */ 
/*    */   public void setClass_id(int classId) {
/* 19 */     this.class_id = classId;
/*    */   }
/*    */   public String getClass_ename() {
/* 22 */     return this.class_ename;
/*    */   }
/*    */   public void setClass_ename(String classEname) {
/* 25 */     this.class_ename = classEname;
/*    */   }
/*    */   public String getClass_cname() {
/* 28 */     return this.class_cname;
/*    */   }
/*    */   public void setClass_cname(String classCname) {
/* 31 */     this.class_cname = classCname;
/*    */   }
/*    */   public String getClass_codo() {
/* 34 */     return this.class_codo;
/*    */   }
/*    */   public void setClass_codo(String classCodo) {
/* 37 */     this.class_codo = classCodo;
/*    */   }
/*    */   public String getClass_define() {
/* 40 */     return this.class_define;
/*    */   }
/*    */   public void setClass_define(String classDefine) {
/* 43 */     this.class_define = classDefine;
/*    */   }
/*    */   public int getClass_type() {
/* 46 */     return this.class_type;
/*    */   }
/*    */   public void setClass_type(int classType) {
/* 49 */     this.class_type = classType;
/*    */   }
/*    */   public String getApp_ids() {
/* 52 */     return this.app_ids;
/*    */   }
/*    */   public void setApp_ids(String appIds) {
/* 55 */     this.app_ids = appIds;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.CateClassBean
 * JD-Core Version:    0.6.2
 */