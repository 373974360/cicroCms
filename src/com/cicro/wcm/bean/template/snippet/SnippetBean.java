/*    */ package com.cicro.wcm.bean.template.snippet;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SnippetBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6244627846606999601L;
/*    */   private int id;
/*    */   private int sni_id;
/*    */   private String sni_name;
/*    */   private String sni_content;
/*    */   private String app_id;
/*    */   private String site_id;
/*    */ 
/*    */   public int getId()
/*    */   {
/* 30 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 33 */     this.id = id;
/*    */   }
/*    */   public int getSni_id() {
/* 36 */     return this.sni_id;
/*    */   }
/*    */   public void setSni_id(int sniId) {
/* 39 */     this.sni_id = sniId;
/*    */   }
/*    */   public String getSni_name() {
/* 42 */     return this.sni_name;
/*    */   }
/*    */   public void setSni_name(String sniName) {
/* 45 */     this.sni_name = sniName;
/*    */   }
/*    */   public String getSni_content() {
/* 48 */     return this.sni_content;
/*    */   }
/*    */   public void setSni_content(String sniContent) {
/* 51 */     this.sni_content = sniContent;
/*    */   }
/*    */   public String getApp_id() {
/* 54 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 57 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 60 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 63 */     this.site_id = siteId;
/*    */   }
/*    */   public static long getSerialversionuid() {
/* 66 */     return 6244627846606999601L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.template.snippet.SnippetBean
 * JD-Core Version:    0.6.2
 */