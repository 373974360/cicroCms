/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PicItemBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4977207423763042235L;
/*  8 */   private int info_id = 0;
/*  9 */   private int pic_id = 0;
/* 10 */   private int att_id = 0;
/* 11 */   private String pic_path = "";
/* 12 */   private String pic_note = "";
/* 13 */   private String pic_url = "";
/* 14 */   private int pic_sort = 0;
/* 15 */   private String pic_title = "";
/* 16 */   private String pic_content = "";
/*    */ 
/*    */   public String getPic_title()
/*    */   {
/* 20 */     return this.pic_title;
/*    */   }
/*    */   public void setPic_title(String picTitle) {
/* 23 */     this.pic_title = picTitle;
/*    */   }
/*    */   public String getPic_content() {
/* 26 */     return this.pic_content;
/*    */   }
/*    */   public void setPic_content(String picContent) {
/* 29 */     this.pic_content = picContent;
/*    */   }
/*    */   public int getInfo_id() {
/* 32 */     return this.info_id;
/*    */   }
/*    */   public void setInfo_id(int infoId) {
/* 35 */     this.info_id = infoId;
/*    */   }
/*    */   public int getPic_id() {
/* 38 */     return this.pic_id;
/*    */   }
/*    */   public void setPic_id(int picId) {
/* 41 */     this.pic_id = picId;
/*    */   }
/*    */   public int getAtt_id() {
/* 44 */     return this.att_id;
/*    */   }
/*    */   public void setAtt_id(int attId) {
/* 47 */     this.att_id = attId;
/*    */   }
/*    */   public String getPic_path() {
/* 50 */     return this.pic_path;
/*    */   }
/*    */   public void setPic_path(String picPath) {
/* 53 */     this.pic_path = picPath;
/*    */   }
/*    */   public String getPic_note() {
/* 56 */     return this.pic_note;
/*    */   }
/*    */   public void setPic_note(String picNote) {
/* 59 */     this.pic_note = picNote;
/*    */   }
/*    */   public String getPic_url() {
/* 62 */     return this.pic_url;
/*    */   }
/*    */   public void setPic_url(String picUrl) {
/* 65 */     this.pic_url = picUrl;
/*    */   }
/*    */   public int getPic_sort() {
/* 68 */     return this.pic_sort;
/*    */   }
/*    */   public void setPic_sort(int picSort) {
/* 71 */     this.pic_sort = picSort;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.PicItemBean
 * JD-Core Version:    0.6.2
 */