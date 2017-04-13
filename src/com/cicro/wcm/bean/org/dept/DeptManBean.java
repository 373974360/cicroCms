/*    */ package com.cicro.wcm.bean.org.dept;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DeptManBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1666477749976803799L;
/*    */   private int dept_manager_id;
/*    */   private int dept_id;
/*    */   private int user_id;
/*    */ 
/*    */   public int getDept_id()
/*    */   {
/* 12 */     return this.dept_id;
/*    */   }
/*    */   public void setDept_id(int dept_id) {
/* 15 */     this.dept_id = dept_id;
/*    */   }
/*    */   public int getDept_manager_id() {
/* 18 */     return this.dept_manager_id;
/*    */   }
/*    */   public void setDept_manager_id(int dept_manager_id) {
/* 21 */     this.dept_manager_id = dept_manager_id;
/*    */   }
/*    */   public int getUser_id() {
/* 24 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int user_id) {
/* 27 */     this.user_id = user_id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.dept.DeptManBean
 * JD-Core Version:    0.6.2
 */