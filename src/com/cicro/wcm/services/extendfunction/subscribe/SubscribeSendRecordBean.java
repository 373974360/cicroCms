/*    */ package com.cicro.wcm.services.extendfunction.subscribe;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SubscribeSendRecordBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private String sinfo_id;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 15 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 18 */     this.id = id;
/*    */   }
/*    */   public String getSinfo_id() {
/* 21 */     return this.sinfo_id;
/*    */   }
/*    */   public void setSinfo_id(String sinfoId) {
/* 24 */     this.sinfo_id = sinfoId;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.subscribe.SubscribeSendRecordBean
 * JD-Core Version:    0.6.2
 */