/*    */ package com.cicro.wcm.bean.search_bak_20151106;

import com.cicro.wcm.bean.search.*;

/*    */
/*    */ public class IndexBeanBsznInfo extends com.cicro.wcm.bean.search.IndexBean
/*    */ {
/*    */   private String bszn_type;
/*    */   private String bszn_org;
/*    */   private String bszn_object;
/*    */   private String publish_time;
/*    */   private String categoryId;
/*    */   private String typed;
/*    */ 
/*    */   public IndexBeanBsznInfo()
/*    */   {
/* 22 */     super("zwgk");
/* 23 */     setTyped("bszn");
/*    */   }
/*    */   public String getBszn_type() {
/* 26 */     return this.bszn_type;
/*    */   }
/*    */   public void setBszn_type(String bsznType) {
/* 29 */     this.bszn_type = bsznType;
/*    */   }
/*    */   public String getBszn_org() {
/* 32 */     return this.bszn_org;
/*    */   }
/*    */   public void setBszn_org(String bsznOrg) {
/* 35 */     this.bszn_org = bsznOrg;
/*    */   }
/*    */   public String getBszn_object() {
/* 38 */     return this.bszn_object;
/*    */   }
/*    */   public void setBszn_object(String bsznObject) {
/* 41 */     this.bszn_object = bsznObject;
/*    */   }
/*    */   public String getPublish_time() {
/* 44 */     return this.publish_time;
/*    */   }
/*    */   public void setPublish_time(String publishTime) {
/* 47 */     this.publish_time = publishTime;
/*    */   }
/*    */   public String getCategoryId() {
/* 50 */     return this.categoryId;
/*    */   }
/*    */   public void setCategoryId(String categoryId) {
/* 53 */     this.categoryId = categoryId;
/*    */   }
/*    */   public String getTyped() {
/* 56 */     return this.typed;
/*    */   }
/*    */   public void setTyped(String typed) {
/* 59 */     this.typed = typed;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search.IndexBeanBsznInfo
 * JD-Core Version:    0.6.2
 */