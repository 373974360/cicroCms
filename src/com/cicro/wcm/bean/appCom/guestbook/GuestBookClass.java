/*    */ package com.cicro.wcm.bean.appCom.guestbook;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GuestBookClass
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4182086551176027141L;
/* 10 */   private int id = 0;
/* 11 */   private int class_id = 0;
/* 12 */   private int cat_id = 0;
/* 13 */   private String name = "";
/* 14 */   private String site_id = "";
/* 15 */   private String description = "";
/* 16 */   private int sort_id = 999;
/*    */ 
/* 18 */   public String getSite_id() { return this.site_id; }
/*    */ 
/*    */   public void setSite_id(String siteId) {
/* 21 */     this.site_id = siteId;
/*    */   }
/*    */   public int getId() {
/* 24 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 27 */     this.id = id;
/*    */   }
/*    */   public int getClass_id() {
/* 30 */     return this.class_id;
/*    */   }
/*    */   public void setClass_id(int classId) {
/* 33 */     this.class_id = classId;
/*    */   }
/*    */   public int getCat_id() {
/* 36 */     return this.cat_id;
/*    */   }
/*    */   public void setCat_id(int catId) {
/* 39 */     this.cat_id = catId;
/*    */   }
/*    */   public String getName() {
/* 42 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 45 */     this.name = name;
/*    */   }
/*    */   public String getDescription() {
/* 48 */     return this.description;
/*    */   }
/*    */   public void setDescription(String description) {
/* 51 */     this.description = description;
/*    */   }
/*    */   public int getSort_id() {
/* 54 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 57 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appCom.guestbook.GuestBookClass
 * JD-Core Version:    0.6.2
 */