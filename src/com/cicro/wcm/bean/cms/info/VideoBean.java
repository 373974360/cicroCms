/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ public class VideoBean extends InfoBean
/*    */ {
/*    */   private static final long serialVersionUID = -4389963487358178950L;
/*    */   private int att_id;
/*  9 */   private String video_path = "";
/*    */   private int play_time;
/* 11 */   private String info_content = "";
/*    */ 
/*    */   public int getAtt_id()
/*    */   {
/* 15 */     return this.att_id;
/*    */   }
/*    */   public String getVideo_path() {
/* 18 */     return this.video_path;
/*    */   }
/*    */   public int getPlay_time() {
/* 21 */     return this.play_time;
/*    */   }
/*    */   public String getInfo_content() {
/* 24 */     return this.info_content;
/*    */   }
/*    */ 
/*    */   public void setAtt_id(int attId)
/*    */   {
/* 29 */     this.att_id = attId;
/*    */   }
/*    */   public void setVideo_path(String videoPath) {
/* 32 */     this.video_path = videoPath;
/*    */   }
/*    */   public void setPlay_time(int playTime) {
/* 35 */     this.play_time = playTime;
/*    */   }
/*    */   public void setInfo_content(String infoContent) {
/* 38 */     this.info_content = infoContent;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.VideoBean
 * JD-Core Version:    0.6.2
 */