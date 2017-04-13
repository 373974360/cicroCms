/*    */ package com.cicro.wcm.bean.system.ware;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WareInfoBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4032576061014213078L;
/* 13 */   private int winfo_id = 0;
/* 14 */   private int ware_id = 0;
/* 15 */   private int sort_id = 999;
/* 16 */   private String app_id = "";
/* 17 */   private String site_id = "";
/* 18 */   private List<WareInfos> infos_list = new ArrayList();
/*    */ 
/* 20 */   public List<WareInfos> getInfos_list() { return this.infos_list; }
/*    */ 
/*    */   public void setInfos_list(List<WareInfos> infosList) {
/* 23 */     this.infos_list = infosList;
/*    */   }
/*    */   public int getWinfo_id() {
/* 26 */     return this.winfo_id;
/*    */   }
/*    */   public void setWinfo_id(int winfoId) {
/* 29 */     this.winfo_id = winfoId;
/*    */   }
/*    */   public int getWare_id() {
/* 32 */     return this.ware_id;
/*    */   }
/*    */   public void setWare_id(int wareId) {
/* 35 */     this.ware_id = wareId;
/*    */   }
/*    */   public int getSort_id() {
/* 38 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 41 */     this.sort_id = sortId;
/*    */   }
/*    */   public String getApp_id() {
/* 44 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 47 */     this.app_id = appId;
/*    */   }
/*    */   public String getSite_id() {
/* 50 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 53 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.ware.WareInfoBean
 * JD-Core Version:    0.6.2
 */