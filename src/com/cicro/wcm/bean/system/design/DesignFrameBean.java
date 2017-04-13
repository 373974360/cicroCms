/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignFrameBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -5377355581519465675L;
/* 12 */   private int id = 0;
/* 13 */   private int frame_id = 0;
/* 14 */   private String frame_name = "";
/* 15 */   private String frame_content = "";
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
/*    */   public int getFrame_id() {
/* 27 */     return this.frame_id;
/*    */   }
/*    */   public void setFrame_id(int frameId) {
/* 30 */     this.frame_id = frameId;
/*    */   }
/*    */   public String getFrame_name() {
/* 33 */     return this.frame_name;
/*    */   }
/*    */   public void setFrame_name(String frameName) {
/* 36 */     this.frame_name = frameName;
/*    */   }
/*    */   public String getFrame_content() {
/* 39 */     return this.frame_content;
/*    */   }
/*    */   public void setFrame_content(String frameContent) {
/* 42 */     this.frame_content = frameContent;
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
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignFrameBean
 * JD-Core Version:    0.6.2
 */