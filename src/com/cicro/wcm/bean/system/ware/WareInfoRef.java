/*    */ package com.cicro.wcm.bean.system.ware;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class WareInfoRef
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3527704703696990843L;
/* 14 */   private int ref_id = 0;
/* 15 */   private int ware_id = 0;
/* 16 */   private int info_id = 0;
/* 17 */   private String update_dtime = "";
/* 18 */   private int ref_user = 0;
/* 19 */   private String app_id = "";
/* 20 */   private String site_id = "";
/*    */ 
/* 22 */   public int getRef_id() { return this.ref_id; }
/*    */ 
/*    */   public void setRef_id(int refId) {
/* 25 */     this.ref_id = refId;
/*    */   }
/*    */   public int getWare_id() {
/* 28 */     return this.ware_id;
/*    */   }
/*    */   public void setWare_id(int wareId) {
/* 31 */     this.ware_id = wareId;
/*    */   }
/*    */   public int getInfo_id() {
/* 34 */     return this.info_id;
/*    */   }
/*    */   public void setInfo_id(int infoId) {
/* 37 */     this.info_id = infoId;
/*    */   }
/*    */   public String getUpdate_dtime() {
/* 40 */     return this.update_dtime;
/*    */   }
/*    */   public void setUpdate_dtime(String updateDtime) {
/* 43 */     this.update_dtime = updateDtime;
/*    */   }
/*    */   public int getRef_user() {
/* 46 */     return this.ref_user;
/*    */   }
/*    */   public void setRef_user(int refUser) {
/* 49 */     this.ref_user = refUser;
/*    */   }
/*    */   public String getApp_id() {
/* 52 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 55 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 58 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 61 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareInfoRef
 * JD-Core Version:    0.6.2
 */