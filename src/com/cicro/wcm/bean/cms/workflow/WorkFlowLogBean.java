/*    */ package com.cicro.wcm.bean.cms.workflow;
/*    */ 
/*    */ public class WorkFlowLogBean
/*    */ {
/*  4 */   private int log_id = 0;
/*  5 */   private int content_id = 0;
/*  6 */   private int wf_before = 0;
/*  7 */   private int wf_after = 0;
/*  8 */   private String wf_action = "";
/*  9 */   private String wf_reason = "";
/* 10 */   private String opt_time = "";
/* 11 */   private String user_id = "";
/* 12 */   private String ip = "";
/*    */ 
/* 14 */   public int getLog_id() { return this.log_id; }
/*    */ 
/*    */   public void setLog_id(int logId) {
/* 17 */     this.log_id = logId;
/*    */   }
/*    */   public int getContent_id() {
/* 20 */     return this.content_id;
/*    */   }
/*    */   public void setContent_id(int contentId) {
/* 23 */     this.content_id = contentId;
/*    */   }
/*    */   public int getWf_before() {
/* 26 */     return this.wf_before;
/*    */   }
/*    */   public void setWf_before(int wfBefore) {
/* 29 */     this.wf_before = wfBefore;
/*    */   }
/*    */   public int getWf_after() {
/* 32 */     return this.wf_after;
/*    */   }
/*    */   public void setWf_after(int wfAfter) {
/* 35 */     this.wf_after = wfAfter;
/*    */   }
/*    */   public String getWf_action() {
/* 38 */     return this.wf_action;
/*    */   }
/*    */   public void setWf_action(String wfAction) {
/* 41 */     this.wf_action = wfAction;
/*    */   }
/*    */   public String getWf_reason() {
/* 44 */     return this.wf_reason;
/*    */   }
/*    */   public void setWf_reason(String wfReason) {
/* 47 */     this.wf_reason = wfReason;
/*    */   }
/*    */   public String getOpt_time() {
/* 50 */     return this.opt_time;
/*    */   }
/*    */   public void setOpt_time(String optTime) {
/* 53 */     this.opt_time = optTime;
/*    */   }
/*    */   public String getUser_id() {
/* 56 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(String userId) {
/* 59 */     this.user_id = userId;
/*    */   }
/*    */   public String getIp() {
/* 62 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 65 */     this.ip = ip;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.workflow.WorkFlowLogBean
 * JD-Core Version:    0.6.2
 */