/*    */ package com.cicro.wcm.services.extendfunction.subscribe;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class subscribeRecordBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int record_id;
/*    */   private String send_user;
/*    */   private String send_time;
/*    */   private String sendTitle;
/*    */ 
/*    */   public int getRecord_id()
/*    */   {
/* 17 */     return this.record_id;
/*    */   }
/*    */   public void setRecord_id(int recordId) {
/* 20 */     this.record_id = recordId;
/*    */   }
/*    */   public String getSend_user() {
/* 23 */     return this.send_user;
/*    */   }
/*    */   public void setSend_user(String sendUser) {
/* 26 */     this.send_user = sendUser;
/*    */   }
/*    */   public String getSend_time() {
/* 29 */     return this.send_time;
/*    */   }
/*    */   public void setSend_time(String sendTime) {
/* 32 */     this.send_time = sendTime;
/*    */   }
/*    */   public String getSendTitle() {
/* 35 */     return this.sendTitle;
/*    */   }
/*    */   public void setSendTitle(String sendTitle) {
/* 38 */     this.sendTitle = sendTitle;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.subscribe.subscribeRecordBean
 * JD-Core Version:    0.6.2
 */