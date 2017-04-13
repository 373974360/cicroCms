/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RelatedInfoBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2864646229263835438L;
/*    */   protected int info_id;
/*    */   protected int related_info_id;
/* 15 */   protected String title = "";
/* 16 */   protected String thumb_url = "";
/* 17 */   protected String content_url = "";
/*    */   protected int sort_id;
/*    */   protected int model_id;
/* 22 */   protected String released_dtime = "";
/*    */ 
/*    */   public String getReleased_dtime() {
/* 25 */     return this.released_dtime;
/*    */   }
/*    */   public void setReleased_dtime(String releasedDtime) {
/* 28 */     this.released_dtime = releasedDtime;
/*    */   }
/*    */   public int getInfo_id() {
/* 31 */     return this.info_id;
/*    */   }
/*    */   public int getRelated_info_id() {
/* 34 */     return this.related_info_id;
/*    */   }
/*    */   public String getTitle() {
/* 37 */     return this.title;
/*    */   }
/*    */   public String getThumb_url() {
/* 40 */     return this.thumb_url;
/*    */   }
/*    */   public String getContent_url() {
/* 43 */     return this.content_url;
/*    */   }
/*    */   public int getSort_id() {
/* 46 */     return this.sort_id;
/*    */   }
/*    */   public int getModel_id() {
/* 49 */     return this.model_id;
/*    */   }
/*    */   public void setInfo_id(int info_id) {
/* 52 */     this.info_id = info_id;
/*    */   }
/*    */   public void setRelated_info_id(int related_info_id) {
/* 55 */     this.related_info_id = related_info_id;
/*    */   }
/*    */   public void setTitle(String title) {
/* 58 */     this.title = title;
/*    */   }
/*    */   public void setThumb_url(String thumb_url) {
/* 61 */     this.thumb_url = thumb_url;
/*    */   }
/*    */   public void setContent_url(String content_url) {
/* 64 */     this.content_url = content_url;
/*    */   }
/*    */   public void setSort_id(int sort_id) {
/* 67 */     this.sort_id = sort_id;
/*    */   }
/*    */   public void setModel_id(int model_id) {
/* 70 */     this.model_id = model_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.RelatedInfoBean
 * JD-Core Version:    0.6.2
 */