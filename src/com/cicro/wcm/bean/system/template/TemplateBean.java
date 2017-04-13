/*    */ package com.cicro.wcm.bean.system.template;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TemplateBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8489485260713460551L;
/*    */   private int id;
/*    */   private int t_id;
/*    */   private int t_ver;
/*    */   private String app_id;
/*    */   private String site_id;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 15 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 18 */     this.id = id;
/*    */   }
/*    */   public int getT_id() {
/* 21 */     return this.t_id;
/*    */   }
/*    */   public int getT_ver() {
/* 24 */     return this.t_ver;
/*    */   }
/*    */   public String getApp_id() {
/* 27 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 30 */     return this.site_id;
/*    */   }
/*    */   public void setT_id(int tId) {
/* 33 */     this.t_id = tId;
/*    */   }
/*    */   public void setT_ver(int tVer) {
/* 36 */     this.t_ver = tVer;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 39 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 42 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.template.TemplateBean
 * JD-Core Version:    0.6.2
 */