/*    */ package com.cicro.wcm.bean.control;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SiteVisitCountBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8413605347167871842L;
/*  8 */   private int id = 0;
/*  9 */   private String site_id = "";
/* 10 */   private String app_id = "";
/* 11 */   private int hits = 0;
/* 12 */   private int day_hits = 0;
/* 13 */   private int week_hits = 0;
/* 14 */   private int month_hits = 0;
/* 15 */   private int temp_count = 1;
/* 16 */   private boolean is_exist = false;
/* 17 */   private int click_step = 1;
/*    */ 
/* 19 */   public int getClick_step() { return this.click_step; }
/*    */ 
/*    */   public void setClick_step(int clickStep) {
/* 22 */     this.click_step = clickStep;
/*    */   }
/*    */   public boolean getIs_exist() {
/* 25 */     return this.is_exist;
/*    */   }
/*    */   public void setIs_exist(boolean isExist) {
/* 28 */     this.is_exist = isExist;
/*    */   }
/*    */   public int getTemp_count() {
/* 31 */     return this.temp_count;
/*    */   }
/*    */   public void setTemp_count(int tempCount) {
/* 34 */     this.temp_count = tempCount;
/*    */   }
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   public String getSite_id() {
/* 43 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 46 */     this.site_id = siteId;
/*    */   }
/*    */   public String getApp_id() {
/* 49 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 52 */     this.app_id = appId;
/*    */   }
/*    */   public int getHits() {
/* 55 */     return this.hits;
/*    */   }
/*    */   public void setHits(int hits) {
/* 58 */     this.hits = hits;
/*    */   }
/*    */   public int getDay_hits() {
/* 61 */     return this.day_hits;
/*    */   }
/*    */   public void setDay_hits(int dayHits) {
/* 64 */     this.day_hits = dayHits;
/*    */   }
/*    */   public int getWeek_hits() {
/* 67 */     return this.week_hits;
/*    */   }
/*    */   public void setWeek_hits(int weekHits) {
/* 70 */     this.week_hits = weekHits;
/*    */   }
/*    */   public int getMonth_hits() {
/* 73 */     return this.month_hits;
/*    */   }
/*    */   public void setMonth_hits(int monthHits) {
/* 76 */     this.month_hits = monthHits;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.control.SiteVisitCountBean
 * JD-Core Version:    0.6.2
 */