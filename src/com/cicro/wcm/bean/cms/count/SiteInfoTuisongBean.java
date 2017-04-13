/*    */ package com.cicro.wcm.bean.cms.count;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SiteInfoTuisongBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5465262522972206364L;
/* 12 */   private String site_name = "";
/* 13 */   private String site_id = "";
/* 14 */   private int icount = 0;
/*    */ 
/*    */   public String getSite_name()
/*    */   {
/* 18 */     return this.site_name;
/*    */   }
/*    */   public String getSite_id() {
/* 21 */     return this.site_id;
/*    */   }
/*    */   public int getIcount() {
/* 24 */     return this.icount;
/*    */   }
/*    */   public void setSite_name(String siteName) {
/* 27 */     this.site_name = siteName;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 30 */     this.site_id = siteId;
/*    */   }
/*    */   public void setIcount(int icount) {
/* 33 */     this.icount = icount;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.count.SiteInfoTuisongBean
 * JD-Core Version:    0.6.2
 */