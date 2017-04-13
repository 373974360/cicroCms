/*    */ package com.cicro.wcm.bean.control;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SiteConfigBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3695837133612316524L;
/*    */   private int config_id;
/*  9 */   private String site_id = "";
/* 10 */   private String config_key = "";
/* 11 */   private String config_value = "";
/*    */ 
/*    */   public int getConfig_id() {
/* 14 */     return this.config_id;
/*    */   }
/*    */   public void setConfig_id(int config_id) {
/* 17 */     this.config_id = config_id;
/*    */   }
/*    */   public String getConfig_key() {
/* 20 */     return this.config_key;
/*    */   }
/*    */   public void setConfig_key(String config_key) {
/* 23 */     this.config_key = config_key;
/*    */   }
/*    */   public String getConfig_value() {
/* 26 */     return this.config_value;
/*    */   }
/*    */   public void setConfig_value(String config_value) {
/* 29 */     this.config_value = config_value;
/*    */   }
/*    */   public String getSite_id() {
/* 32 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 35 */     this.site_id = site_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.control.SiteConfigBean
 * JD-Core Version:    0.6.2
 */