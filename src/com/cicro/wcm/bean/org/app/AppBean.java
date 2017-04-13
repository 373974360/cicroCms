/*    */ package com.cicro.wcm.bean.org.app;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AppBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -441491370699683455L;
/*  8 */   private String app_id = "";
/*  9 */   private String app_name = "";
/*    */   private int app_sort;
/* 11 */   private String app_ico = "";
/* 12 */   private String app_memo = "";
/*    */ 
/* 14 */   public String getApp_ico() { return this.app_ico; }
/*    */ 
/*    */   public void setApp_ico(String app_ico) {
/* 17 */     this.app_ico = app_ico;
/*    */   }
/*    */   public String getApp_memo() {
/* 20 */     return this.app_memo;
/*    */   }
/*    */   public void setApp_memo(String app_memo) {
/* 23 */     this.app_memo = app_memo;
/*    */   }
/*    */   public String getApp_name() {
/* 26 */     return this.app_name;
/*    */   }
/*    */   public void setApp_name(String app_name) {
/* 29 */     this.app_name = app_name;
/*    */   }
/*    */   public int getApp_sort() {
/* 32 */     return this.app_sort;
/*    */   }
/*    */   public void setApp_sort(int app_sort) {
/* 35 */     this.app_sort = app_sort;
/*    */   }
/*    */   public String getApp_id() {
/* 38 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String app_id) {
/* 41 */     this.app_id = app_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.app.AppBean
 * JD-Core Version:    0.6.2
 */