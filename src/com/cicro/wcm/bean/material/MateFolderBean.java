/*    */ package com.cicro.wcm.bean.material;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class MateFolderBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -277595599113942992L;
/*    */   private int f_id;
/*    */   private int parent_id;
/*    */   private String cname;
/*    */   private String f_treeposition;
/*    */   private String creat_dtime;
/*    */   private int user_id;
/*    */   private String app_id;
/*    */   private String site_id;
/*    */ 
/*    */   public int getF_id()
/*    */   {
/* 19 */     return this.f_id;
/*    */   }
/*    */   public int getParent_id() {
/* 22 */     return this.parent_id;
/*    */   }
/*    */   public String getCname() {
/* 25 */     return this.cname;
/*    */   }
/*    */   public String getF_treeposition() {
/* 28 */     return this.f_treeposition;
/*    */   }
/*    */   public String getCreat_dtime() {
/* 31 */     return this.creat_dtime;
/*    */   }
/*    */   public int getUser_id() {
/* 34 */     return this.user_id;
/*    */   }
/*    */   public String getApp_id() {
/* 37 */     return this.app_id;
/*    */   }
/*    */   public String getSite_id() {
/* 40 */     return this.site_id;
/*    */   }
/*    */   public void setF_id(int fId) {
/* 43 */     this.f_id = fId;
/*    */   }
/*    */   public void setParent_id(int parentId) {
/* 46 */     this.parent_id = parentId;
/*    */   }
/*    */   public void setCname(String cname) {
/* 49 */     this.cname = cname;
/*    */   }
/*    */   public void setF_treeposition(String fTreeposition) {
/* 52 */     this.f_treeposition = fTreeposition;
/*    */   }
/*    */   public void setCreat_dtime(String creatDtime) {
/* 55 */     this.creat_dtime = creatDtime;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 58 */     this.user_id = userId;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 61 */     this.app_id = appId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 64 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.material.MateFolderBean
 * JD-Core Version:    0.6.2
 */