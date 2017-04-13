/*    */ package com.cicro.wcm.bean.system.ware;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class WareCategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1040597672816207753L;
/* 17 */   private String id = "";
/* 18 */   private String wcat_id = "";
/* 19 */   private String parent_id = "";
/* 20 */   private String wcat_name = "";
/* 21 */   private String wcat_position = "";
/* 22 */   private int wcat_level = 1;
/* 23 */   private String wcat_memo = "";
/* 24 */   private int sort_id = 999;
/* 25 */   private String app_id = "";
/* 26 */   private String site_id = "";
/*    */ 
/*    */   public String getWcat_id() {
/* 29 */     return this.wcat_id;
/*    */   }
/*    */   public String getParent_id() {
/* 32 */     return this.parent_id;
/*    */   }
/*    */   public String getWcat_name() {
/* 35 */     return this.wcat_name;
/*    */   }
/*    */   public String getWcat_position() {
/* 38 */     return this.wcat_position;
/*    */   }
/*    */   public String getWcat_memo() {
/* 41 */     return this.wcat_memo;
/*    */   }
/*    */   public int getSort_id() {
/* 44 */     return this.sort_id;
/*    */   }
/*    */   public String getApp_id() {
/* 47 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 50 */     return this.site_id;
/*    */   }
/*    */ 
/*    */   public void setWcat_id(String wcatId) {
/* 54 */     this.wcat_id = wcatId;
/*    */   }
/*    */   public void setParent_id(String parentId) {
/* 57 */     this.parent_id = parentId;
/*    */   }
/*    */   public void setWcat_name(String wcatName) {
/* 60 */     this.wcat_name = wcatName;
/*    */   }
/*    */   public void setWcat_position(String wcatPosition) {
/* 63 */     this.wcat_position = wcatPosition;
/*    */   }
/*    */   public void setWcat_memo(String wcatMemo) {
/* 66 */     this.wcat_memo = wcatMemo;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 69 */     this.sort_id = sortId;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 72 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 75 */     this.site_id = siteId;
/*    */   }
/*    */   public String getId() {
/* 78 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 81 */     this.id = id;
/*    */   }
/*    */   public int getWcat_level() {
/* 84 */     return this.wcat_level;
/*    */   }
/*    */   public void setWcat_level(int wcatLevel) {
/* 87 */     this.wcat_level = wcatLevel;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareCategoryBean
 * JD-Core Version:    0.6.2
 */