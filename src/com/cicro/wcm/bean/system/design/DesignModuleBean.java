/*    */ package com.cicro.wcm.bean.system.design;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DesignModuleBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2113322120951832705L;
/* 12 */   private int id = 0;
/* 13 */   private int module_id = 0;
/* 14 */   private int datasouce_type = 0;
/* 15 */   private String module_name = "";
/* 16 */   private String module_content = "";
/* 17 */   private String v_code = "";
/* 18 */   private String thumb_url = "";
/* 19 */   private String style_ids = "";
/* 20 */   private String attr_ids = "";
/* 21 */   private int weight = 0;
/* 22 */   private String app_id = "";
/* 23 */   private String site_id = "";
/*    */ 
/* 25 */   public String getAttr_ids() { return this.attr_ids; }
/*    */ 
/*    */   public void setAttr_ids(String attrIds) {
/* 28 */     this.attr_ids = attrIds;
/*    */   }
/*    */   public int getDatasouce_type() {
/* 31 */     return this.datasouce_type;
/*    */   }
/*    */   public void setDatasouce_type(int datasouceType) {
/* 34 */     this.datasouce_type = datasouceType;
/*    */   }
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   public int getModule_id() {
/* 43 */     return this.module_id;
/*    */   }
/*    */   public void setModule_id(int moduleId) {
/* 46 */     this.module_id = moduleId;
/*    */   }
/*    */   public String getModule_name() {
/* 49 */     return this.module_name;
/*    */   }
/*    */   public void setModule_name(String moduleName) {
/* 52 */     this.module_name = moduleName;
/*    */   }
/*    */   public String getModule_content() {
/* 55 */     return this.module_content;
/*    */   }
/*    */   public void setModule_content(String moduleContent) {
/* 58 */     this.module_content = moduleContent;
/*    */   }
/*    */   public String getV_code() {
/* 61 */     return this.v_code;
/*    */   }
/*    */   public void setV_code(String vCode) {
/* 64 */     this.v_code = vCode;
/*    */   }
/*    */   public String getThumb_url() {
/* 67 */     return this.thumb_url;
/*    */   }
/*    */   public void setThumb_url(String thumbUrl) {
/* 70 */     this.thumb_url = thumbUrl;
/*    */   }
/*    */   public String getStyle_ids() {
/* 73 */     return this.style_ids;
/*    */   }
/*    */   public void setStyle_ids(String styleIds) {
/* 76 */     this.style_ids = styleIds;
/*    */   }
/*    */   public int getWeight() {
/* 79 */     return this.weight;
/*    */   }
/*    */   public void setWeight(int weight) {
/* 82 */     this.weight = weight;
/*    */   }
/*    */   public String getApp_id() {
/* 85 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 88 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 91 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 94 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.design.DesignModuleBean
 * JD-Core Version:    0.6.2
 */