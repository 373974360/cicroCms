/*    */ package com.cicro.wcm.bean.appeal.cpUser;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CPUserReleInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4455554902621237357L;
/*    */   private int user_id;
/*    */   private int dept_id;
/* 13 */   private String user_realname = "";
/* 14 */   private String role_ids = "";
/* 15 */   private String role_names = "";
/* 16 */   private String model_ids = "";
/* 17 */   private String model_names = "";
/* 18 */   private String dept_treeposition = "";
/*    */ 
/* 20 */   private String accnumber = "";
/*    */ 
/*    */   public int getUser_id() {
/* 23 */     return this.user_id;
/*    */   }
/*    */ 
/*    */   public void setUser_id(int userId) {
/* 27 */     this.user_id = userId;
/*    */   }
/*    */ 
/*    */   public int getDept_id() {
/* 31 */     return this.dept_id;
/*    */   }
/*    */ 
/*    */   public void setDept_id(int deptId) {
/* 35 */     this.dept_id = deptId;
/*    */   }
/*    */ 
/*    */   public String getUser_realname() {
/* 39 */     return this.user_realname;
/*    */   }
/*    */ 
/*    */   public void setUser_realname(String userRealname) {
/* 43 */     this.user_realname = userRealname;
/*    */   }
/*    */ 
/*    */   public String getRole_ids() {
/* 47 */     return this.role_ids;
/*    */   }
/*    */ 
/*    */   public void setRole_ids(String roleIds) {
/* 51 */     this.role_ids = roleIds;
/*    */   }
/*    */ 
/*    */   public String getRole_names() {
/* 55 */     return this.role_names;
/*    */   }
/*    */ 
/*    */   public void setRole_names(String roleNames) {
/* 59 */     this.role_names = roleNames;
/*    */   }
/*    */ 
/*    */   public String getModel_ids() {
/* 63 */     return this.model_ids;
/*    */   }
/*    */ 
/*    */   public void setModel_ids(String modelIds) {
/* 67 */     this.model_ids = modelIds;
/*    */   }
/*    */ 
/*    */   public String getModel_names() {
/* 71 */     return this.model_names;
/*    */   }
/*    */ 
/*    */   public void setModel_names(String modelNames) {
/* 75 */     this.model_names = modelNames;
/*    */   }
/*    */ 
/*    */   public String getDept_treeposition() {
/* 79 */     return this.dept_treeposition;
/*    */   }
/*    */ 
/*    */   public void setDept_treeposition(String deptTreeposition) {
/* 83 */     this.dept_treeposition = deptTreeposition;
/*    */   }
/*    */ 
/*    */   public String getAccnumber() {
/* 87 */     return this.accnumber;
/*    */   }
/*    */ 
/*    */   public void setAccnumber(String accnumber) {
/* 91 */     this.accnumber = accnumber;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.cpUser.CPUserReleInfo
 * JD-Core Version:    0.6.2
 */