/*    */ package com.cicro.wcm.bean.zwgk.appcatalog;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RegulationBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  8 */   private int id = 0;
/*  9 */   private int cata_id = 0;
/* 10 */   private int regu_type = 0;
/* 11 */   private String site_ids = "";
/* 12 */   private String cat_ids = "";
/* 13 */   private String site_id_names = "";
/* 14 */   private String cat_id_names = "";
/*    */ 
/* 16 */   public String getSite_id_names() { return this.site_id_names; }
/*    */ 
/*    */   public void setSite_id_names(String siteIdNames) {
/* 19 */     this.site_id_names = siteIdNames;
/*    */   }
/*    */   public String getCat_id_names() {
/* 22 */     return this.cat_id_names;
/*    */   }
/*    */   public void setCat_id_names(String catIdNames) {
/* 25 */     this.cat_id_names = catIdNames;
/*    */   }
/*    */   public int getId() {
/* 28 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */   public int getCata_id() {
/* 34 */     return this.cata_id;
/*    */   }
/*    */   public void setCata_id(int cataId) {
/* 37 */     this.cata_id = cataId;
/*    */   }
/*    */   public int getRegu_type() {
/* 40 */     return this.regu_type;
/*    */   }
/*    */   public void setRegu_type(int reguType) {
/* 43 */     this.regu_type = reguType;
/*    */   }
/*    */   public String getSite_ids() {
/* 46 */     return this.site_ids;
/*    */   }
/*    */   public void setSite_ids(String siteIds) {
/* 49 */     this.site_ids = siteIds;
/*    */   }
/*    */   public String getCat_ids() {
/* 52 */     return this.cat_ids;
/*    */   }
/*    */   public void setCat_ids(String catIds) {
/* 55 */     this.cat_ids = catIds;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.appcatalog.RegulationBean
 * JD-Core Version:    0.6.2
 */