/*     */ package com.cicro.wcm.bean.zwgk.count;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GKCountBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8522700275190020027L;
/*     */   private int cat_id;
/*  14 */   private int info_count = 0;
/*  15 */   private int z_count = 0;
/*  16 */   private int y_count = 0;
/*  17 */   private int b_count = 0;
/*  18 */   private String update_time = "";
/*  19 */   private String app_id = "";
/*  20 */   private String site_id = "";
/*     */ 
/*  23 */   private String cat_name = "";
/*  24 */   private String site_name = "";
/*  25 */   private boolean is_leaf = true;
/*     */   private List<GKCountBean> child_list;
/*     */ 
/*     */   public int getCat_id()
/*     */   {
/*  29 */     return this.cat_id;
/*     */   }
/*     */ 
/*     */   public int getInfo_count() {
/*  33 */     return this.info_count;
/*     */   }
/*     */ 
/*     */   public int getZ_count() {
/*  37 */     return this.z_count;
/*     */   }
/*     */ 
/*     */   public int getY_count() {
/*  41 */     return this.y_count;
/*     */   }
/*     */ 
/*     */   public int getB_count() {
/*  45 */     return this.b_count;
/*     */   }
/*     */ 
/*     */   public String getUpdate_time() {
/*  49 */     return this.update_time;
/*     */   }
/*     */ 
/*     */   public String getApp_id() {
/*  53 */     return this.app_id;
/*     */   }
/*     */ 
/*     */   public String getSite_id() {
/*  57 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setCat_id(int catId) {
/*  61 */     this.cat_id = catId;
/*     */   }
/*     */ 
/*     */   public void setInfo_count(int infoCount) {
/*  65 */     this.info_count = infoCount;
/*     */   }
/*     */ 
/*     */   public void setZ_count(int zCount) {
/*  69 */     this.z_count = zCount;
/*     */   }
/*     */ 
/*     */   public void setY_count(int yCount) {
/*  73 */     this.y_count = yCount;
/*     */   }
/*     */ 
/*     */   public void setB_count(int bCount) {
/*  77 */     this.b_count = bCount;
/*     */   }
/*     */ 
/*     */   public void setUpdate_time(String updateTime) {
/*  81 */     this.update_time = updateTime;
/*     */   }
/*     */ 
/*     */   public void setApp_id(String appId) {
/*  85 */     this.app_id = appId;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId) {
/*  89 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public String getCat_name() {
/*  93 */     return this.cat_name;
/*     */   }
/*     */ 
/*     */   public void setCat_name(String catName) {
/*  97 */     this.cat_name = catName;
/*     */   }
/*     */ 
/*     */   public boolean isIs_leaf() {
/* 101 */     return this.is_leaf;
/*     */   }
/*     */ 
/*     */   public void setIs_leaf(boolean isLeaf) {
/* 105 */     this.is_leaf = isLeaf;
/*     */   }
/*     */ 
/*     */   public List<GKCountBean> getChild_list() {
/* 109 */     return this.child_list;
/*     */   }
/*     */ 
/*     */   public void setChild_list(List<GKCountBean> childList) {
/* 113 */     this.child_list = childList;
/*     */ 
/* 115 */     if (this.is_leaf)
/* 116 */       this.child_list = null;
/*     */   }
/*     */ 
/*     */   public String getSite_name() {
/* 120 */     return this.site_name;
/*     */   }
/*     */ 
/*     */   public void setSite_name(String siteName) {
/* 124 */     this.site_name = siteName;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.count.GKCountBean
 * JD-Core Version:    0.6.2
 */