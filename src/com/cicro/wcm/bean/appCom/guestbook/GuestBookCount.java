/*    */ package com.cicro.wcm.bean.appCom.guestbook;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GuestBookCount
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6608541640393760804L;
/* 10 */   private int gbs_id = 0;
/* 11 */   private int cat_id = 0;
/* 12 */   private String site_id = "";
/* 13 */   private String title = "";
/* 14 */   private int count = 0;
/* 15 */   private int reply_count = 0;
/* 16 */   private int publish_count = 0;
/*    */ 
/* 18 */   public int getCat_id() { return this.cat_id; }
/*    */ 
/*    */   public void setCat_id(int catId) {
/* 21 */     this.cat_id = catId;
/*    */   }
/*    */   public int getGbs_id() {
/* 24 */     return this.gbs_id;
/*    */   }
/*    */   public void setGbs_id(int gbsId) {
/* 27 */     this.gbs_id = gbsId;
/*    */   }
/*    */   public String getSite_id() {
/* 30 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 33 */     this.site_id = siteId;
/*    */   }
/*    */   public String getTitle() {
/* 36 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 39 */     this.title = title;
/*    */   }
/*    */   public int getCount() {
/* 42 */     return this.count;
/*    */   }
/*    */   public void setCount(int count) {
/* 45 */     this.count = count;
/*    */   }
/*    */   public int getReply_count() {
/* 48 */     return this.reply_count;
/*    */   }
/*    */   public void setReply_count(int replyCount) {
/* 51 */     this.reply_count = replyCount;
/*    */   }
/*    */   public int getPublish_count() {
/* 54 */     return this.publish_count;
/*    */   }
/*    */   public void setPublish_count(int publishCount) {
/* 57 */     this.publish_count = publishCount;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appCom.guestbook.GuestBookCount
 * JD-Core Version:    0.6.2
 */