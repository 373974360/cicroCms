/*    */ package com.cicro.wcm.bean.sendInfo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ReceiveCatConf
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -9004072344337605935L;
/*  8 */   private int id = 0;
/*  9 */   private String site_id = "";
/* 10 */   private int cat_id = 0;
/* 11 */   private int sort_id = 0;
/*    */ 
/* 13 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 16 */     this.id = id;
/*    */   }
/*    */   public String getSite_id() {
/* 19 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 22 */     this.site_id = siteId;
/*    */   }
/*    */   public int getCat_id() {
/* 25 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 28 */     this.cat_id = catId;
/*    */   }
/*    */   public int getSort_id() {
/* 31 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 34 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.ReceiveCatConf
 * JD-Core Version:    0.6.2
 */