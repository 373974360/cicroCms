/*    */ package com.cicro.wcm.bean.cms.digg;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*    */ 
/*    */ public class InfoDiggBean extends InfoBean
/*    */ {
/*    */   private static final long serialVersionUID = -5641414916369623668L;
/*    */   private int info_id;
/*    */   private int supports;
/*    */   private int againsts;
/* 19 */   private String app_id = "";
/* 20 */   private String site_id = "";
/*    */ 
/*    */   public int getInfo_id()
/*    */   {
/* 24 */     return this.info_id;
/*    */   }
/*    */ 
/*    */   public void setInfo_id(int infoId) {
/* 28 */     this.info_id = infoId;
/*    */   }
/*    */ 
/*    */   public int getSupports() {
/* 32 */     return this.supports;
/*    */   }
/*    */ 
/*    */   public void setSupports(int supports) {
/* 36 */     this.supports = supports;
/*    */   }
/*    */ 
/*    */   public int getAgainsts() {
/* 40 */     return this.againsts;
/*    */   }
/*    */ 
/*    */   public void setAgainsts(int againsts) {
/* 44 */     this.againsts = againsts;
/*    */   }
/*    */ 
/*    */   public String getApp_id() {
/* 48 */     return this.app_id;
/*    */   }
/*    */ 
/*    */   public void setApp_id(String appId) {
/* 52 */     this.app_id = appId;
/*    */   }
/*    */ 
/*    */   public String getSite_id() {
/* 56 */     return this.site_id;
/*    */   }
/*    */ 
/*    */   public void setSite_id(String siteId) {
/* 60 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.digg.InfoDiggBean
 * JD-Core Version:    0.6.2
 */