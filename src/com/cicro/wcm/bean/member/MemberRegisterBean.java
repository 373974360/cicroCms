/*    */ package com.cicro.wcm.bean.member;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class MemberRegisterBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7346556839717611345L;
/* 12 */   private String register_id = "";
/* 13 */   private String me_id = "";
/* 14 */   private String me_account = "";
/* 15 */   private String me_password = "";
/* 16 */   private int register_type = 0;
/*    */ 
/*    */   public String getRegister_id() {
/* 19 */     return this.register_id;
/*    */   }
/*    */   public String getMe_id() {
/* 22 */     return this.me_id;
/*    */   }
/*    */   public String getMe_account() {
/* 25 */     return this.me_account;
/*    */   }
/*    */   public String getMe_password() {
/* 28 */     return this.me_password;
/*    */   }
/*    */   public int getRegister_type() {
/* 31 */     return this.register_type;
/*    */   }
/*    */ 
/*    */   public void setRegister_id(String registerId) {
/* 35 */     this.register_id = registerId;
/*    */   }
/*    */   public void setMe_id(String meId) {
/* 38 */     this.me_id = meId;
/*    */   }
/*    */   public void setMe_account(String meAccount) {
/* 41 */     this.me_account = meAccount;
/*    */   }
/*    */   public void setMe_password(String mePassword) {
/* 44 */     this.me_password = mePassword;
/*    */   }
/*    */   public void setRegister_type(int registerType) {
/* 47 */     this.register_type = registerType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.member.MemberRegisterBean
 * JD-Core Version:    0.6.2
 */