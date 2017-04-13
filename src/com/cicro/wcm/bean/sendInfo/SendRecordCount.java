/*     */ package com.cicro.wcm.bean.sendInfo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SendRecordCount
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 993499666621437090L;
/*  11 */   private int user_id = 0;
/*  12 */   private String user_realname = "";
/*  13 */   private int send_count = 0;
/*  14 */   private int adopt_count = 0;
/*  15 */   private int not_count = 0;
/*  16 */   private String adopt_rate = "";
/*  17 */   private int cat_parent_id = 0;
/*  18 */   private int cat_id = 0;
/*  19 */   private int cat_sort = 0;
/*  20 */   private String cat_cname = "'";
/*  21 */   private List<SendRecordCount> child_cate_list = new ArrayList();
/*  22 */   private String site_name = "";
/*  23 */   private String site_id = "";
/*     */ 
/*  25 */   public String getSite_name() { return this.site_name; }
/*     */ 
/*     */   public void setSite_name(String siteName) {
/*  28 */     this.site_name = siteName;
/*     */   }
/*     */   public String getSite_id() {
/*  31 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/*  34 */     this.site_id = siteId;
/*     */   }
/*     */   public int getCat_sort() {
/*  37 */     return this.cat_sort;
/*     */   }
/*     */   public void setCat_sort(int catSort) {
/*  40 */     this.cat_sort = catSort;
/*     */   }
/*     */   public int getCat_parent_id() {
/*  43 */     return this.cat_parent_id;
/*     */   }
/*     */   public void setCat_parent_id(int catParentId) {
/*  46 */     this.cat_parent_id = catParentId;
/*     */   }
/*     */   public List<SendRecordCount> getChild_cate_list() {
/*  49 */     return this.child_cate_list;
/*     */   }
/*     */   public void setChild_cate_list(List<SendRecordCount> childCateList) {
/*  52 */     this.child_cate_list = childCateList;
/*     */   }
/*     */   public int getCat_id() {
/*  55 */     return this.cat_id;
/*     */   }
/*     */   public void setCat_id(int catId) {
/*  58 */     this.cat_id = catId;
/*     */   }
/*     */   public String getCat_cname() {
/*  61 */     return this.cat_cname;
/*     */   }
/*     */   public void setCat_cname(String catCname) {
/*  64 */     this.cat_cname = catCname;
/*     */   }
/*     */   public int getUser_id() {
/*  67 */     return this.user_id;
/*     */   }
/*     */   public void setUser_id(int userId) {
/*  70 */     this.user_id = userId;
/*     */   }
/*     */   public String getUser_realname() {
/*  73 */     return this.user_realname;
/*     */   }
/*     */   public void setUser_realname(String userRealname) {
/*  76 */     this.user_realname = userRealname;
/*     */   }
/*     */ 
/*     */   public int getSend_count() {
/*  80 */     return this.send_count;
/*     */   }
/*     */   public void setSend_count(int sendCount) {
/*  83 */     this.send_count = sendCount;
/*     */   }
/*     */   public int getAdopt_count() {
/*  86 */     return this.adopt_count;
/*     */   }
/*     */   public void setAdopt_count(int adoptCount) {
/*  89 */     this.adopt_count = adoptCount;
/*     */   }
/*     */   public int getNot_count() {
/*  92 */     return this.not_count;
/*     */   }
/*     */   public void setNot_count(int notCount) {
/*  95 */     this.not_count = notCount;
/*     */   }
/*     */   public String getAdopt_rate() {
/*  98 */     return this.adopt_rate;
/*     */   }
/*     */   public void setAdopt_rate(String adoptRate) {
/* 101 */     this.adopt_rate = adoptRate;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.sendInfo.SendRecordCount
 * JD-Core Version:    0.6.2
 */