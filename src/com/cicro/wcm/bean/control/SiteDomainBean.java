/*    */ package com.cicro.wcm.bean.control;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SiteDomainBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -636274914018529420L;
/*    */   private int domain_id;
/*  9 */   private String site_id = "";
/* 10 */   private String site_domain = "";
/*    */   private int is_default;
/* 12 */   private int is_host = 0;
/*    */ 
/* 14 */   public int getIs_host() { return this.is_host; }
/*    */ 
/*    */   public void setIs_host(int is_host) {
/* 17 */     this.is_host = is_host;
/*    */   }
/*    */   public int getDomain_id() {
/* 20 */     return this.domain_id;
/*    */   }
/*    */   public void setDomain_id(int domain_id) {
/* 23 */     this.domain_id = domain_id;
/*    */   }
/*    */   public int getIs_default() {
/* 26 */     return this.is_default;
/*    */   }
/*    */   public void setIs_default(int is_default) {
/* 29 */     this.is_default = is_default;
/*    */   }
/*    */   public String getSite_domain() {
/* 32 */     return this.site_domain;
/*    */   }
/*    */   public void setSite_domain(String site_domain) {
/* 35 */     this.site_domain = site_domain;
/*    */   }
/*    */   public String getSite_id() {
/* 38 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 41 */     this.site_id = site_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.control.SiteDomainBean
 * JD-Core Version:    0.6.2
 */