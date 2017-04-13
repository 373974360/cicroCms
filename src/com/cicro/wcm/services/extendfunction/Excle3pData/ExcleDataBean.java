/*    */ package com.cicro.wcm.services.extendfunction.Excle3pData;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ExcleDataBean
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 1226033188701471610L;
/* 10 */   private int id = 0;
/* 11 */   private String info_id = "";
/* 12 */   private String from_url = "";
/* 13 */   private String add_time = "";
/* 14 */   private String site_id = "";
/* 15 */   private String cat_id = "";
/*    */ 
/*    */   public int getId()
/*    */   {
/* 19 */     return this.id;
/*    */   }
/*    */   public String getInfo_id() {
/* 22 */     return this.info_id;
/*    */   }
/*    */   public String getFrom_url() {
/* 25 */     return this.from_url;
/*    */   }
/*    */   public String getAdd_time() {
/* 28 */     return this.add_time;
/*    */   }
/*    */   public void setId(int id) {
/* 31 */     this.id = id;
/*    */   }
/*    */   public void setInfo_id(String infoId) {
/* 34 */     this.info_id = infoId;
/*    */   }
/*    */   public void setFrom_url(String fromUrl) {
/* 37 */     this.from_url = fromUrl;
/*    */   }
/*    */   public void setAdd_time(String addTime) {
/* 40 */     this.add_time = addTime;
/*    */   }
/*    */   public String getSite_id() {
/* 43 */     return this.site_id;
/*    */   }
/*    */   public String getCat_id() {
/* 46 */     return this.cat_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 49 */     this.site_id = siteId;
/*    */   }
/*    */   public void setCat_id(String catId) {
/* 52 */     this.cat_id = catId;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.Excle3pData.ExcleDataBean
 * JD-Core Version:    0.6.2
 */