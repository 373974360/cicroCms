/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SyncBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8266136896847669175L;
/* 12 */   private String s_site_id = "";
/*    */   private int s_cat_id;
/* 14 */   private String t_site_id = "";
/*    */   private int t_cat_id;
/* 16 */   private int is_auto_publish = 0;
/* 17 */   private int cite_type = 1;
/* 18 */   private int orientation = 0;
/*    */ 
/*    */   public int getOrientation() {
/* 21 */     return this.orientation;
/*    */   }
/*    */   public void setOrientation(int orientation) {
/* 24 */     this.orientation = orientation;
/*    */   }
/*    */   public String getS_site_id() {
/* 27 */     return this.s_site_id;
/*    */   }
/*    */   public int getS_cat_id() {
/* 30 */     return this.s_cat_id;
/*    */   }
/*    */   public String getT_site_id() {
/* 33 */     return this.t_site_id;
/*    */   }
/*    */   public int getT_cat_id() {
/* 36 */     return this.t_cat_id;
/*    */   }
/*    */   public int getIs_auto_publish() {
/* 39 */     return this.is_auto_publish;
/*    */   }
/*    */   public int getCite_type() {
/* 42 */     return this.cite_type;
/*    */   }
/*    */ 
/*    */   public void setS_site_id(String sSiteId) {
/* 46 */     this.s_site_id = sSiteId;
/*    */   }
/*    */   public void setT_site_id(String tSiteId) {
/* 49 */     this.t_site_id = tSiteId;
/*    */   }
/*    */   public void setIs_auto_publish(int isAutoPublish) {
/* 52 */     this.is_auto_publish = isAutoPublish;
/*    */   }
/*    */   public void setCite_type(int citeType) {
/* 55 */     this.cite_type = citeType;
/*    */   }
/*    */   public void setS_cat_id(int sCatId) {
/* 58 */     this.s_cat_id = sCatId;
/*    */   }
/*    */   public void setT_cat_id(int tCatId) {
/* 61 */     this.t_cat_id = tCatId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.SyncBean
 * JD-Core Version:    0.6.2
 */