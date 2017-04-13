/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GKResFileBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7866022055826858640L;
/*  8 */   private int res_id = 0;
/*  9 */   private int info_id = 0;
/* 10 */   private String res_name = "";
/* 11 */   private String res_url = "";
/* 12 */   private int sort_id = 999;
/*    */ 
/* 14 */   public int getRes_id() { return this.res_id; }
/*    */ 
/*    */   public void setRes_id(int resId) {
/* 17 */     this.res_id = resId;
/*    */   }
/*    */   public int getInfo_id() {
/* 20 */     return this.info_id;
/*    */   }
/*    */   public void setInfo_id(int infoId) {
/* 23 */     this.info_id = infoId;
/*    */   }
/*    */   public String getRes_name() {
/* 26 */     return this.res_name;
/*    */   }
/*    */   public void setRes_name(String resName) {
/* 29 */     this.res_name = resName;
/*    */   }
/*    */   public String getRes_url() {
/* 32 */     return this.res_url;
/*    */   }
/*    */   public void setRes_url(String resUrl) {
/* 35 */     this.res_url = resUrl;
/*    */   }
/*    */   public int getSort_id() {
/* 38 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 41 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.GKResFileBean
 * JD-Core Version:    0.6.2
 */