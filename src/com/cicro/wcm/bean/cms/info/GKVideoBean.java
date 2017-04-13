/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GKVideoBean extends GKInfoBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6936506210209352607L;
/*    */   private int pic_id;
/*    */   private int att_id;
/*    */   private String video_path;
/*    */   private int play_time;
/*    */   private String info_content;
/*    */ 
/*    */   public int getPic_id()
/*    */   {
/* 20 */     return this.pic_id;
/*    */   }
/*    */   public int getAtt_id() {
/* 23 */     return this.att_id;
/*    */   }
/*    */   public String getVideo_path() {
/* 26 */     return this.video_path;
/*    */   }
/*    */   public int getPlay_time() {
/* 29 */     return this.play_time;
/*    */   }
/*    */   public String getInfo_content() {
/* 32 */     return this.info_content;
/*    */   }
/*    */ 
/*    */   public void setPic_id(int picId)
/*    */   {
/* 37 */     this.pic_id = picId;
/*    */   }
/*    */   public void setAtt_id(int attId) {
/* 40 */     this.att_id = attId;
/*    */   }
/*    */   public void setVideo_path(String videoPath) {
/* 43 */     this.video_path = videoPath;
/*    */   }
/*    */   public void setPlay_time(int playTime) {
/* 46 */     this.play_time = playTime;
/*    */   }
/*    */   public void setInfo_content(String infoContent) {
/* 49 */     this.info_content = infoContent;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.GKVideoBean
 * JD-Core Version:    0.6.2
 */