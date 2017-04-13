/*    */ package com.cicro.wcm.bean.org.user;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserLevelBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8386388832262443398L;
/*    */   private int userlevel_id;
/*    */   private int userlevel_value;
/* 10 */   private String userlevel_name = "";
/* 11 */   private String userlevel_memo = "";
/* 12 */   private int is_delete = 0;
/*    */ 
/* 14 */   public int getIs_delete() { return this.is_delete; }
/*    */ 
/*    */   public void setIs_delete(int is_delete) {
/* 17 */     this.is_delete = is_delete;
/*    */   }
/*    */   public int getUserlevel_id() {
/* 20 */     return this.userlevel_id;
/*    */   }
/*    */   public void setUserlevel_id(int userlevel_id) {
/* 23 */     this.userlevel_id = userlevel_id;
/*    */   }
/*    */   public String getUserlevel_memo() {
/* 26 */     return this.userlevel_memo;
/*    */   }
/*    */   public void setUserlevel_memo(String userlevel_memo) {
/* 29 */     this.userlevel_memo = userlevel_memo;
/*    */   }
/*    */   public String getUserlevel_name() {
/* 32 */     return this.userlevel_name;
/*    */   }
/*    */   public void setUserlevel_name(String userlevel_name) {
/* 35 */     this.userlevel_name = userlevel_name;
/*    */   }
/*    */   public int getUserlevel_value() {
/* 38 */     return this.userlevel_value;
/*    */   }
/*    */   public void setUserlevel_value(int userlevel_value) {
/* 41 */     this.userlevel_value = userlevel_value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.user.UserLevelBean
 * JD-Core Version:    0.6.2
 */