/*    */ package com.cicro.wcm.bean.appeal.cpLead;
/*    */ 
/*    */ public class CpLeadBean
/*    */ {
/*    */   private int lead_id;
/*    */   private String lead_name;
/*    */   private String lead_job;
/*    */   private String lead_img;
/*    */   private String lead_url;
/*    */   private String lead_memo;
/* 18 */   private int sort_id = 9999;
/*    */   private int dept_id;
/*    */   private String dept_name;
/*    */ 
/*    */   public int getLead_id()
/*    */   {
/* 24 */     return this.lead_id;
/*    */   }
/*    */   public void setLead_id(int leadId) {
/* 27 */     this.lead_id = leadId;
/*    */   }
/*    */   public String getLead_name() {
/* 30 */     return this.lead_name;
/*    */   }
/*    */   public void setLead_name(String leadName) {
/* 33 */     this.lead_name = leadName;
/*    */   }
/*    */   public String getLead_job() {
/* 36 */     return this.lead_job;
/*    */   }
/*    */   public void setLead_job(String leadJob) {
/* 39 */     this.lead_job = leadJob;
/*    */   }
/*    */   public String getLead_img() {
/* 42 */     return this.lead_img;
/*    */   }
/*    */   public void setLead_img(String leadImg) {
/* 45 */     this.lead_img = leadImg;
/*    */   }
/*    */   public String getLead_url() {
/* 48 */     return this.lead_url;
/*    */   }
/*    */   public void setLead_url(String leadUrl) {
/* 51 */     this.lead_url = leadUrl;
/*    */   }
/*    */   public String getLead_memo() {
/* 54 */     return this.lead_memo;
/*    */   }
/*    */   public void setLead_memo(String leadMemo) {
/* 57 */     this.lead_memo = leadMemo;
/*    */   }
/*    */   public int getSort_id() {
/* 60 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 63 */     this.sort_id = sortId;
/*    */   }
/*    */   public int getDept_id() {
/* 66 */     return this.dept_id;
/*    */   }
/*    */   public void setDept_id(int deptId) {
/* 69 */     this.dept_id = deptId;
/*    */   }
/*    */   public String getDept_name() {
/* 72 */     return this.dept_name;
/*    */   }
/*    */   public void setDept_name(String deptName) {
/* 75 */     this.dept_name = deptName;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.cpLead.CpLeadBean
 * JD-Core Version:    0.6.2
 */