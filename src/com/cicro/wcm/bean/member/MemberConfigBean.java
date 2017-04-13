/*    */ package com.cicro.wcm.bean.member;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class MemberConfigBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7634675647033476308L;
/* 12 */   private String config_id = "";
/* 13 */   private int is_allow = 0;
/* 14 */   private int is_check = 0;
/* 15 */   private String reg_pro = "";
/* 16 */   private String close_pro = "";
/* 17 */   private String forbidden_name = "";
/*    */ 
/*    */   public String getConfig_id() {
/* 20 */     return this.config_id;
/*    */   }
/*    */   public int getIs_allow() {
/* 23 */     return this.is_allow;
/*    */   }
/*    */   public int getIs_check() {
/* 26 */     return this.is_check;
/*    */   }
/*    */   public String getReg_pro() {
/* 29 */     return this.reg_pro;
/*    */   }
/*    */   public String getClose_pro() {
/* 32 */     return this.close_pro;
/*    */   }
/*    */   public String getForbidden_name() {
/* 35 */     return this.forbidden_name;
/*    */   }
/*    */ 
/*    */   public void setConfig_id(String configId) {
/* 39 */     this.config_id = configId;
/*    */   }
/*    */   public void setIs_allow(int isAllow) {
/* 42 */     this.is_allow = isAllow;
/*    */   }
/*    */   public void setIs_check(int isCheck) {
/* 45 */     this.is_check = isCheck;
/*    */   }
/*    */   public void setReg_pro(String regPro) {
/* 48 */     this.reg_pro = regPro;
/*    */   }
/*    */   public void setClose_pro(String closePro) {
/* 51 */     this.close_pro = closePro;
/*    */   }
/*    */   public void setForbidden_name(String forbiddenName) {
/* 54 */     this.forbidden_name = forbiddenName;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.member.MemberConfigBean
 * JD-Core Version:    0.6.2
 */