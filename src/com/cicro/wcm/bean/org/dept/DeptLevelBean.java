/*    */ package com.cicro.wcm.bean.org.dept;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DeptLevelBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 335862833803416495L;
/*    */   private int deptlevel_id;
/*    */   private int deptlevel_value;
/* 10 */   private String deptlevel_name = "";
/* 11 */   private String deptlevel_memo = "";
/* 12 */   private int is_delete = 0;
/*    */ 
/* 14 */   public int getDeptlevel_id() { return this.deptlevel_id; }
/*    */ 
/*    */   public void setDeptlevel_id(int deptlevel_id) {
/* 17 */     this.deptlevel_id = deptlevel_id;
/*    */   }
/*    */   public String getDeptlevel_memo() {
/* 20 */     return this.deptlevel_memo;
/*    */   }
/*    */   public void setDeptlevel_memo(String deptlevel_memo) {
/* 23 */     this.deptlevel_memo = deptlevel_memo;
/*    */   }
/*    */   public String getDeptlevel_name() {
/* 26 */     return this.deptlevel_name;
/*    */   }
/*    */   public void setDeptlevel_name(String deptlevel_name) {
/* 29 */     this.deptlevel_name = deptlevel_name;
/*    */   }
/*    */   public int getDeptlevel_value() {
/* 32 */     return this.deptlevel_value;
/*    */   }
/*    */   public void setDeptlevel_value(int deptlevel_value) {
/* 35 */     this.deptlevel_value = deptlevel_value;
/*    */   }
/*    */   public int getIs_delete() {
/* 38 */     return this.is_delete;
/*    */   }
/*    */   public void setIs_delete(int is_delete) {
/* 41 */     this.is_delete = is_delete;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.dept.DeptLevelBean
 * JD-Core Version:    0.6.2
 */