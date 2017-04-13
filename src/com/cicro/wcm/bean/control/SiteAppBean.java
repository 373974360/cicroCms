/*    */ package com.cicro.wcm.bean.control;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SiteAppBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5722934062833027011L;
/*    */   private int sa_id;
/*  9 */   private String site_id = "";
/* 10 */   private String app_id = "";
/* 11 */   private String mark1 = "";
/* 12 */   private String mark2 = "";
/* 13 */   private String mark3 = "";
/* 14 */   private String mark4 = "";
/* 15 */   private String mark5 = "";
/*    */ 
/*    */   public String getMark1() {
/* 18 */     return this.mark1;
/*    */   }
/*    */   public void setMark1(String mark1) {
/* 21 */     this.mark1 = mark1;
/*    */   }
/*    */   public String getMark2() {
/* 24 */     return this.mark2;
/*    */   }
/*    */   public void setMark2(String mark2) {
/* 27 */     this.mark2 = mark2;
/*    */   }
/*    */   public String getMark3() {
/* 30 */     return this.mark3;
/*    */   }
/*    */   public void setMark3(String mark3) {
/* 33 */     this.mark3 = mark3;
/*    */   }
/*    */   public String getMark4() {
/* 36 */     return this.mark4;
/*    */   }
/*    */   public void setMark4(String mark4) {
/* 39 */     this.mark4 = mark4;
/*    */   }
/*    */   public String getMark5() {
/* 42 */     return this.mark5;
/*    */   }
/*    */   public void setMark5(String mark5) {
/* 45 */     this.mark5 = mark5;
/*    */   }
/*    */   public int getSa_id() {
/* 48 */     return this.sa_id;
/*    */   }
/*    */   public void setSa_id(int saId) {
/* 51 */     this.sa_id = saId;
/*    */   }
/*    */   public String getSite_id() {
/* 54 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 57 */     this.site_id = siteId;
/*    */   }
/*    */   public String getApp_id() {
/* 60 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 63 */     this.app_id = appId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.control.SiteAppBean
 * JD-Core Version:    0.6.2
 */