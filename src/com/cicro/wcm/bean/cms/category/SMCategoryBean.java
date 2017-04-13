/*    */ package com.cicro.wcm.bean.cms.category;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SMCategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4476253177179214909L;
/* 11 */   private int cat_id = 0;
/* 12 */   private int parent_id = 0;
/* 13 */   private String cat_position = "";
/* 14 */   private String cat_cname = "";
/* 15 */   private String site_id = "";
/* 16 */   private List<SMCategoryBean> sm_list = new ArrayList();
/*    */ 
/* 18 */   public int getParent_id() { return this.parent_id; }
/*    */ 
/*    */   public void setParent_id(int parentId) {
/* 21 */     this.parent_id = parentId;
/*    */   }
/*    */   public String getCat_position() {
/* 24 */     return this.cat_position;
/*    */   }
/*    */   public void setCat_position(String catPosition) {
/* 27 */     this.cat_position = catPosition;
/*    */   }
/*    */   public int getCat_id() {
/* 30 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 33 */     this.cat_id = catId;
/*    */   }
/*    */   public String getSite_id() {
/* 36 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 39 */     this.site_id = siteId;
/*    */   }
/*    */   public String getCat_cname() {
/* 42 */     return this.cat_cname;
/*    */   }
/*    */   public void setCat_cname(String catCname) {
/* 45 */     this.cat_cname = catCname;
/*    */   }
/*    */   public List<SMCategoryBean> getSm_list() {
/* 48 */     return this.sm_list;
/*    */   }
/*    */   public void setSm_list(List<SMCategoryBean> smList) {
/* 51 */     this.sm_list = smList;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.category.SMCategoryBean
 * JD-Core Version:    0.6.2
 */