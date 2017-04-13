/*    */ package com.cicro.wcm.bean.appeal.cpUser;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CpUserBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1967980864292154584L;
/*    */   private int user_id;
/*    */   private int dept_id;
/*    */ 
/*    */   public int getUser_id()
/*    */   {
/* 24 */     return this.user_id;
/*    */   }
/*    */ 
/*    */   public void setUser_id(int userId) {
/* 28 */     this.user_id = userId;
/*    */   }
/*    */ 
/*    */   public int getDept_id() {
/* 32 */     return this.dept_id;
/*    */   }
/*    */ 
/*    */   public void setDept_id(int deptId) {
/* 36 */     this.dept_id = deptId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.cpUser.CpUserBean
 * JD-Core Version:    0.6.2
 */