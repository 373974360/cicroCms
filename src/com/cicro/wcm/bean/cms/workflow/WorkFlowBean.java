/*    */ package com.cicro.wcm.bean.cms.workflow;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WorkFlowBean
/*    */ {
/*  7 */   private int wf_id = 0;
/*  8 */   private String wf_name = "";
/*  9 */   private String wf_memo = "";
/*    */   private int wf_steps;
/* 11 */   private List<WorkFlowStepBean> workFlowStep_list = new ArrayList();
/*    */ 
/* 13 */   public List<WorkFlowStepBean> getWorkFlowStep_list() { return this.workFlowStep_list; }
/*    */ 
/*    */   public void setWorkFlowStep_list(List<WorkFlowStepBean> workFlowStepList) {
/* 16 */     this.workFlowStep_list = workFlowStepList;
/*    */   }
/*    */   public int getWf_id() {
/* 19 */     return this.wf_id;
/*    */   }
/*    */   public void setWf_id(int wfId) {
/* 22 */     this.wf_id = wfId;
/*    */   }
/*    */   public String getWf_name() {
/* 25 */     return this.wf_name;
/*    */   }
/*    */   public void setWf_name(String wfName) {
/* 28 */     this.wf_name = wfName;
/*    */   }
/*    */   public String getWf_memo() {
/* 31 */     return this.wf_memo;
/*    */   }
/*    */   public void setWf_memo(String wfMemo) {
/* 34 */     this.wf_memo = wfMemo;
/*    */   }
/*    */   public int getWf_steps() {
/* 37 */     return this.wf_steps;
/*    */   }
/*    */   public void setWf_steps(int wfSteps) {
/* 40 */     this.wf_steps = wfSteps;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.workflow.WorkFlowBean
 * JD-Core Version:    0.6.2
 */