/*    */ package com.cicro.wcm.bean.system.assist;
/*    */ 
/*    */ public class TagsBean
/*    */ {
/*    */   private int tag_id;
/*    */   private String tag_name;
/*    */   private String tag_color;
/*  7 */   private int font_size = 12;
/*  8 */   private int tag_times = 0;
/*    */   private String app_id;
/*    */   private String site_id;
/*    */ 
/*    */   public int getTag_id()
/*    */   {
/* 12 */     return this.tag_id;
/*    */   }
/*    */   public String getTag_name() {
/* 15 */     return this.tag_name;
/*    */   }
/*    */   public String getTag_color() {
/* 18 */     return this.tag_color;
/*    */   }
/*    */   public int getFont_size() {
/* 21 */     return this.font_size;
/*    */   }
/*    */   public int getTag_times() {
/* 24 */     return this.tag_times;
/*    */   }
/*    */   public String getApp_id() {
/* 27 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 30 */     return this.site_id;
/*    */   }
/*    */   public void setTag_id(int tagId) {
/* 33 */     this.tag_id = tagId;
/*    */   }
/*    */   public void setTag_name(String tagName) {
/* 36 */     if (tagName == null) {
/* 37 */       tagName = " ";
/*    */     }
/* 39 */     this.tag_name = tagName;
/*    */   }
/*    */   public void setTag_color(String tagColor) {
/* 42 */     if (tagColor == null) {
/* 43 */       tagColor = " ";
/*    */     }
/* 45 */     this.tag_color = tagColor;
/*    */   }
/*    */   public void setFont_size(int fontSize) {
/* 48 */     this.font_size = fontSize;
/*    */   }
/*    */   public void setTag_times(int tagTimes) {
/* 51 */     this.tag_times = tagTimes;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 54 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 57 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.assist.TagsBean
 * JD-Core Version:    0.6.2
 */