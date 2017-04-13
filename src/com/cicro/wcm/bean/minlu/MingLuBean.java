/*    */ package com.cicro.wcm.bean.minlu;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class MingLuBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  8 */   private int id = 0;
/*  9 */   private int template_index = 0;
/* 10 */   private int template_list = 0;
/* 11 */   private int template_content = 0;
/* 12 */   private int reinfo_temp_list = 0;
/* 13 */   private int reinfo_temp_content = 0;
/* 14 */   private int reinfo_temp_pic_list = 0;
/* 15 */   private int reinfo_temp_pic_content = 0;
/* 16 */   private String app_id = "";
/* 17 */   private String site_id = "";
/*    */ 
/* 19 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 22 */     this.id = id;
/*    */   }
/*    */   public int getTemplate_index() {
/* 25 */     return this.template_index;
/*    */   }
/*    */   public void setTemplate_index(int templateIndex) {
/* 28 */     this.template_index = templateIndex;
/*    */   }
/*    */   public int getTemplate_list() {
/* 31 */     return this.template_list;
/*    */   }
/*    */   public void setTemplate_list(int templateList) {
/* 34 */     this.template_list = templateList;
/*    */   }
/*    */   public int getTemplate_content() {
/* 37 */     return this.template_content;
/*    */   }
/*    */   public void setTemplate_content(int templateContent) {
/* 40 */     this.template_content = templateContent;
/*    */   }
/*    */   public int getReinfo_temp_list() {
/* 43 */     return this.reinfo_temp_list;
/*    */   }
/*    */   public void setReinfo_temp_list(int reinfoTempList) {
/* 46 */     this.reinfo_temp_list = reinfoTempList;
/*    */   }
/*    */   public int getReinfo_temp_content() {
/* 49 */     return this.reinfo_temp_content;
/*    */   }
/*    */   public void setReinfo_temp_content(int reinfoTempContent) {
/* 52 */     this.reinfo_temp_content = reinfoTempContent;
/*    */   }
/*    */   public int getReinfo_temp_pic_list() {
/* 55 */     return this.reinfo_temp_pic_list;
/*    */   }
/*    */   public void setReinfo_temp_pic_list(int reinfoTempPicList) {
/* 58 */     this.reinfo_temp_pic_list = reinfoTempPicList;
/*    */   }
/*    */   public int getReinfo_temp_pic_content() {
/* 61 */     return this.reinfo_temp_pic_content;
/*    */   }
/*    */   public void setReinfo_temp_pic_content(int reinfoTempPicContent) {
/* 64 */     this.reinfo_temp_pic_content = reinfoTempPicContent;
/*    */   }
/*    */   public String getApp_id() {
/* 67 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 70 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 73 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 76 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.minlu.MingLuBean
 * JD-Core Version:    0.6.2
 */