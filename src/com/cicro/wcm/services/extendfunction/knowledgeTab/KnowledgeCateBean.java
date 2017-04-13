/*    */ package com.cicro.wcm.services.extendfunction.knowledgeTab;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class KnowledgeCateBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7784059694818824361L;
/* 12 */   private int id = 0;
/* 13 */   private String kcat_id = "";
/* 14 */   private String kparent_id = "";
/* 15 */   private String kcat_name = "";
/* 16 */   private String kcat_position = "";
/* 17 */   private int kcat_level = 1;
/* 18 */   private String kcat_memo = "";
/* 19 */   private int sort_id = 999;
/* 20 */   private String app_id = "";
/* 21 */   private String site_id = "";
/*    */ 
/*    */   public int getId()
/*    */   {
/* 26 */     return this.id;
/*    */   }
/*    */   public String getKcat_id() {
/* 29 */     return this.kcat_id;
/*    */   }
/*    */   public String getKparent_id() {
/* 32 */     return this.kparent_id;
/*    */   }
/*    */   public String getKcat_name() {
/* 35 */     return this.kcat_name;
/*    */   }
/*    */   public String getKcat_position() {
/* 38 */     return this.kcat_position;
/*    */   }
/*    */   public int getKcat_level() {
/* 41 */     return this.kcat_level;
/*    */   }
/*    */   public String getKcat_memo() {
/* 44 */     return this.kcat_memo;
/*    */   }
/*    */   public int getSort_id() {
/* 47 */     return this.sort_id;
/*    */   }
/*    */   public String getApp_id() {
/* 50 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 53 */     return this.site_id;
/*    */   }
/*    */   public void setId(int id) {
/* 56 */     this.id = id;
/*    */   }
/*    */   public void setKcat_id(String kcatId) {
/* 59 */     this.kcat_id = kcatId;
/*    */   }
/*    */   public void setKparent_id(String kparentId) {
/* 62 */     this.kparent_id = kparentId;
/*    */   }
/*    */   public void setKcat_name(String kcatName) {
/* 65 */     this.kcat_name = kcatName;
/*    */   }
/*    */   public void setKcat_position(String kcatPosition) {
/* 68 */     this.kcat_position = kcatPosition;
/*    */   }
/*    */   public void setKcat_level(int kcatLevel) {
/* 71 */     this.kcat_level = kcatLevel;
/*    */   }
/*    */   public void setKcat_memo(String kcatMemo) {
/* 74 */     this.kcat_memo = kcatMemo;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 77 */     this.sort_id = sortId;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 80 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 83 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.knowledgeTab.KnowledgeCateBean
 * JD-Core Version:    0.6.2
 */