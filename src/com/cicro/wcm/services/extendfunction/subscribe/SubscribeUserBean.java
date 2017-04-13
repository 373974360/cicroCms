/*    */ package com.cicro.wcm.services.extendfunction.subscribe;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SubscribeUserBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/* 10 */   private String mail_address = "";
/* 11 */   private String sub_time = "";
/*    */ 
/*    */   public int getId() {
/* 14 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 17 */     this.id = id;
/*    */   }
/*    */   public String getMail_address() {
/* 20 */     return this.mail_address;
/*    */   }
/*    */   public void setMail_address(String mailAddress) {
/* 23 */     this.mail_address = mailAddress;
/*    */   }
/*    */   public String getSub_time() {
/* 26 */     return this.sub_time;
/*    */   }
/*    */   public void setSub_time(String subTime) {
/* 29 */     this.sub_time = subTime;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.subscribe.SubscribeUserBean
 * JD-Core Version:    0.6.2
 */