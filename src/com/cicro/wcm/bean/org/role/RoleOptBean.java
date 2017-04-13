/*    */ package com.cicro.wcm.bean.org.role;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RoleOptBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8478608025019117515L;
/*    */   private int role_opt_id;
/*    */   private int role_id;
/*    */   private int opt_id;
/*    */ 
/*    */   public int getOpt_id()
/*    */   {
/* 12 */     return this.opt_id;
/*    */   }
/*    */   public void setOpt_id(int opt_id) {
/* 15 */     this.opt_id = opt_id;
/*    */   }
/*    */   public int getRole_id() {
/* 18 */     return this.role_id;
/*    */   }
/*    */   public void setRole_id(int role_id) {
/* 21 */     this.role_id = role_id;
/*    */   }
/*    */   public int getRole_opt_id() {
/* 24 */     return this.role_opt_id;
/*    */   }
/*    */   public void setRole_opt_id(int role_opt_id) {
/* 27 */     this.role_opt_id = role_opt_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.role.RoleOptBean
 * JD-Core Version:    0.6.2
 */