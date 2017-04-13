/*    */ package com.cicro.wcm.bean.org.group;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8260908770697656610L;
/*    */   private int group_id;
/*    */   private int parent_id;
/* 10 */   private String group_name = "";
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/* 13 */   private String group_memo = "";
/* 14 */   private String user_ids = "";
/*    */ 
/* 16 */   public String getUser_ids() { return this.user_ids; }
/*    */ 
/*    */   public void setUser_ids(String user_ids) {
/* 19 */     this.user_ids = user_ids;
/*    */   }
/*    */   public String getApp_id() {
/* 22 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String app_id) {
/* 25 */     this.app_id = app_id;
/*    */   }
/*    */   public int getGroup_id() {
/* 28 */     return this.group_id;
/*    */   }
/*    */   public void setGroup_id(int group_id) {
/* 31 */     this.group_id = group_id;
/*    */   }
/*    */   public String getGroup_memo() {
/* 34 */     return this.group_memo;
/*    */   }
/*    */   public void setGroup_memo(String group_memo) {
/* 37 */     this.group_memo = group_memo;
/*    */   }
/*    */   public String getGroup_name() {
/* 40 */     return this.group_name;
/*    */   }
/*    */   public void setGroup_name(String group_name) {
/* 43 */     this.group_name = group_name;
/*    */   }
/*    */   public int getParent_id() {
/* 46 */     return this.parent_id;
/*    */   }
/*    */   public void setParent_id(int parent_id) {
/* 49 */     this.parent_id = parent_id;
/*    */   }
/*    */   public String getSite_id() {
/* 52 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 55 */     this.site_id = site_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.group.GroupBean
 * JD-Core Version:    0.6.2
 */