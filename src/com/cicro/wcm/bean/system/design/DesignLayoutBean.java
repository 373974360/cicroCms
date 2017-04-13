/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignLayoutBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2906157949026416738L;
/* 12 */   private int id = 0;
/* 13 */   private int layout_id = 0;
/* 14 */   private String layout_name = "";
/* 15 */   private String layout_content = "";
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
/*    */   public int getLayout_id() {
/* 27 */     return this.layout_id;
/*    */   }
/*    */   public void setLayout_id(int layoutId) {
/* 30 */     this.layout_id = layoutId;
/*    */   }
/*    */   public String getLayout_name() {
/* 33 */     return this.layout_name;
/*    */   }
/*    */   public void setLayout_name(String layoutName) {
/* 36 */     this.layout_name = layoutName;
/*    */   }
/*    */   public String getLayout_content() {
/* 39 */     return this.layout_content;
/*    */   }
/*    */   public void setLayout_content(String layoutContent) {
/* 42 */     this.layout_content = layoutContent;
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
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignLayoutBean
 * JD-Core Version:    0.6.2
 */