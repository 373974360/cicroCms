/*    */ package com.cicro.wcm.bean.org.group;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupUserBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3619452827109848593L;
/*    */   private int group_user_id;
/*    */   private int group_id;
/*    */   private int user_id;
/* 11 */   private String app_id = "";
/* 12 */   private String site_id = "";
/*    */ 
/* 14 */   public String getApp_id() { return this.app_id; }
/*    */ 
/*    */   public void setApp_id(String app_id) {
/* 17 */     this.app_id = app_id;
/*    */   }
/*    */   public int getGroup_id() {
/* 20 */     return this.group_id;
/*    */   }
/*    */   public void setGroup_id(int group_id) {
/* 23 */     this.group_id = group_id;
/*    */   }
/*    */   public int getGroup_user_id() {
/* 26 */     return this.group_user_id;
/*    */   }
/*    */   public void setGroup_user_id(int group_user_id) {
/* 29 */     this.group_user_id = group_user_id;
/*    */   }
/*    */   public int getUser_id() {
/* 32 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int user_id) {
/* 35 */     this.user_id = user_id;
/*    */   }
/*    */   public String getSite_id() {
/* 38 */     return this.site_id;
/*    */   }
/*    */   public void setSite_id(String site_id) {
/* 41 */     this.site_id = site_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.group.GroupUserBean
 * JD-Core Version:    0.6.2
 */