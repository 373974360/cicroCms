/*    */ package com.cicro.wcm.bean.org.user;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SMUserBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8417530692366924944L;
/*    */   private int user_id;
/*    */   private int dept_id;
/* 12 */   private String user_realname = "";
/* 13 */   private String dept_treeposition = "";
/*    */ 
/* 15 */   public int getUser_id() { return this.user_id; }
/*    */ 
/*    */   public void setUser_id(int userId) {
/* 18 */     this.user_id = userId;
/*    */   }
/*    */   public int getDept_id() {
/* 21 */     return this.dept_id;
/*    */   }
/*    */   public void setDept_id(int deptId) {
/* 24 */     this.dept_id = deptId;
/*    */   }
/*    */   public String getUser_realname() {
/* 27 */     return this.user_realname;
/*    */   }
/*    */   public void setUser_realname(String userRealname) {
/* 30 */     this.user_realname = userRealname;
/*    */   }
/*    */   public String getDept_treeposition() {
/* 33 */     return this.dept_treeposition;
/*    */   }
/*    */   public void setDept_treeposition(String deptTreeposition) {
/* 36 */     this.dept_treeposition = deptTreeposition;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.user.SMUserBean
 * JD-Core Version:    0.6.2
 */