/*    */ package com.cicro.wcm.bean.cms.workflow;
/*    */ 
/*    */ public class WorkFlowStepBean
/*    */ {
/*  4 */   private int step_id = 1;
/*  5 */   private int wf_id = 0;
/*  6 */   private String step_name = "";
/*  7 */   private String role_id = "";
/*    */ 
/*  9 */   public int getStep_id() { return this.step_id; }
/*    */ 
/*    */   public void setStep_id(int stepId) {
/* 12 */     this.step_id = stepId;
/*    */   }
/*    */   public int getWf_id() {
/* 15 */     return this.wf_id;
/*    */   }
/*    */   public void setWf_id(int wfId) {
/* 18 */     this.wf_id = wfId;
/*    */   }
/*    */   public String getStep_name() {
/* 21 */     return this.step_name;
/*    */   }
/*    */   public void setStep_name(String stepName) {
/* 24 */     this.step_name = stepName;
/*    */   }
/*    */   public String getRole_id() {
/* 27 */     return this.role_id;
/*    */   }
/*    */   public void setRole_id(String roleId) {
/* 30 */     this.role_id = roleId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.workflow.WorkFlowStepBean
 * JD-Core Version:    0.6.2
 */