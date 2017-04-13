/*    */ package com.cicro.wcm.bean.sendInfo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AdoptRecordBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4692923841901090149L;
/*  8 */   private int id = 0;
/*  9 */   private int receive_info_id = 0;
/* 10 */   private int cat_id = 0;
/* 11 */   private String adopt_dtime = "";
/* 12 */   private int adopt_user = 0;
/* 13 */   private int new_info_id = 0;
/*    */ 
/* 15 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 18 */     this.id = id;
/*    */   }
/*    */   public int getReceive_info_id() {
/* 21 */     return this.receive_info_id;
/*    */   }
/*    */   public void setReceive_info_id(int receiveInfoId) {
/* 24 */     this.receive_info_id = receiveInfoId;
/*    */   }
/*    */   public int getCat_id() {
/* 27 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 30 */     this.cat_id = catId;
/*    */   }
/*    */   public String getAdopt_dtime() {
/* 33 */     return this.adopt_dtime;
/*    */   }
/*    */   public void setAdopt_dtime(String adoptDtime) {
/* 36 */     this.adopt_dtime = adoptDtime;
/*    */   }
/*    */   public int getAdopt_user() {
/* 39 */     return this.adopt_user;
/*    */   }
/*    */   public void setAdopt_user(int adoptUser) {
/* 42 */     this.adopt_user = adoptUser;
/*    */   }
/*    */   public int getNew_info_id() {
/* 45 */     return this.new_info_id;
/*    */   }
/*    */   public void setNew_info_id(int newInfoId) {
/* 48 */     this.new_info_id = newInfoId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.AdoptRecordBean
 * JD-Core Version:    0.6.2
 */