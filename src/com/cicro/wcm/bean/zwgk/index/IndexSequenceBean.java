/*    */ package com.cicro.wcm.bean.zwgk.index;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class IndexSequenceBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4824287728668080067L;
/*    */   private int id;
/* 16 */   private String seq_item = "";
/*    */   private int seq_cur_value;
/* 18 */   private String seq_ymd = "";
/*    */   private int seq_type;
/* 20 */   private String app_id = "";
/* 21 */   private String site_id = "";
/*    */ 
/*    */   public int getId()
/*    */   {
/* 25 */     return this.id;
/*    */   }
/*    */   public String getSeq_item() {
/* 28 */     return this.seq_item;
/*    */   }
/*    */   public int getSeq_cur_value() {
/* 31 */     return this.seq_cur_value;
/*    */   }
/*    */   public String getSeq_ymd() {
/* 34 */     return this.seq_ymd;
/*    */   }
/*    */   public int getSeq_type() {
/* 37 */     return this.seq_type;
/*    */   }
/*    */   public String getApp_id() {
/* 40 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 43 */     return this.site_id;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 48 */     this.id = id;
/*    */   }
/*    */   public void setSeq_item(String seqItem) {
/* 51 */     this.seq_item = seqItem;
/*    */   }
/*    */   public void setSeq_cur_value(int seqCurValue) {
/* 54 */     this.seq_cur_value = seqCurValue;
/*    */   }
/*    */   public void setSeq_ymd(String seqYmd) {
/* 57 */     this.seq_ymd = seqYmd;
/*    */   }
/*    */   public void setSeq_type(int seqType) {
/* 60 */     this.seq_type = seqType;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 63 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 66 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.index.IndexSequenceBean
 * JD-Core Version:    0.6.2
 */