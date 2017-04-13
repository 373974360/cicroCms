/*    */ package com.cicro.wcm.bean.cms.count;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TuisongCountBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4357978566686491115L;
/* 11 */   private int info_id = 0;
/* 12 */   private int is_host = 0;
/* 13 */   private int isNot_host = 0;
/* 14 */   private String site_id = "";
/* 15 */   private String site_name = "";
/* 16 */   private int cat_id = 0;
/* 17 */   private String cat_name = "";
/* 18 */   private int from_id = 0;
/* 19 */   private int user_id = 0;
/* 20 */   private String app_id = "";
/*    */ 
/* 22 */   private int icount = 0;
/*    */ 
/*    */   public int getInfo_id()
/*    */   {
/* 28 */     return this.info_id;
/*    */   }
/*    */   public int getIcount() {
/* 31 */     return this.icount;
/*    */   }
/*    */   public void setIcount(int icount) {
/* 34 */     this.icount = icount;
/*    */   }
/*    */   public void setInfo_id(int infoId) {
/* 37 */     this.info_id = infoId;
/*    */   }
/*    */   public String getCat_name() {
/* 40 */     return this.cat_name;
/*    */   }
/*    */   public void setCat_name(String catName) {
/* 43 */     this.cat_name = catName;
/*    */   }
/*    */   public int getIs_host() {
/* 46 */     return this.is_host;
/*    */   }
/*    */   public int getIsNot_host() {
/* 49 */     return this.isNot_host;
/*    */   }
/*    */   public String getSite_id() {
/* 52 */     return this.site_id;
/*    */   }
/*    */   public String getSite_name() {
/* 55 */     return this.site_name;
/*    */   }
/*    */   public int getCat_id() {
/* 58 */     return this.cat_id;
/*    */   }
/*    */   public int getUser_id() {
/* 61 */     return this.user_id;
/*    */   }
/*    */   public String getApp_id() {
/* 64 */     return this.app_id;
/*    */   }
/*    */   public int getFrom_id() {
/* 67 */     return this.from_id;
/*    */   }
/*    */   public void setIs_host(int isHost) {
/* 70 */     this.is_host = isHost;
/*    */   }
/*    */   public void setIsNot_host(int isNotHost) {
/* 73 */     this.isNot_host = isNotHost;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 76 */     this.site_id = siteId;
/*    */   }
/*    */   public void setSite_name(String siteName) {
/* 79 */     this.site_name = siteName;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 82 */     this.cat_id = catId;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 85 */     this.user_id = userId;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 88 */     this.app_id = appId;
/*    */   }
/*    */   public void setFrom_id(int fromId) {
/* 91 */     this.from_id = fromId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.count.TuisongCountBean
 * JD-Core Version:    0.6.2
 */