/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignStyleBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -620424896356897870L;
/* 12 */   private int id = 0;
/* 13 */   private int style_id = 0;
/* 14 */   private String style_name = "";
/* 15 */   private String class_name = "";
/* 16 */   private String thumb_url = "";
/* 17 */   private int weight = 0;
/* 18 */   private String app_id = "";
/* 19 */   private String site_id = "";
/*    */ 
/* 21 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 24 */     this.id = id;
/*    */   }
/*    */   public int getStyle_id() {
/* 27 */     return this.style_id;
/*    */   }
/*    */   public void setStyle_id(int styleId) {
/* 30 */     this.style_id = styleId;
/*    */   }
/*    */   public String getStyle_name() {
/* 33 */     return this.style_name;
/*    */   }
/*    */   public void setStyle_name(String styleName) {
/* 36 */     this.style_name = styleName;
/*    */   }
/*    */   public String getClass_name() {
/* 39 */     return this.class_name;
/*    */   }
/*    */   public void setClass_name(String className) {
/* 42 */     this.class_name = className;
/*    */   }
/*    */   public String getThumb_url() {
/* 45 */     return this.thumb_url;
/*    */   }
/*    */   public void setThumb_url(String thumbUrl) {
/* 48 */     this.thumb_url = thumbUrl;
/*    */   }
/*    */   public int getWeight() {
/* 51 */     return this.weight;
/*    */   }
/*    */   public void setWeight(int weight) {
/* 54 */     this.weight = weight;
/*    */   }
/*    */   public String getApp_id() {
/* 57 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 60 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 63 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 66 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignStyleBean
 * JD-Core Version:    0.6.2
 */