/*     */ package com.cicro.wcm.services.cms.workflow;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.workflow.WorkFlowBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class WorkFlowRPC
/*     */ {
/*     */   public static int getNextStepIDByUserID(int wf_id, String user_id, String app_id, String site_id)
/*     */   {
/*  22 */     return WorkFlowManager.getNextStepIDByUserID(wf_id, user_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static int getMaxStepIDByUserID(int wf_id, String user_id, String app_id, String site_id)
/*     */   {
/*  35 */     return WorkFlowManager.getMaxStepIDByUserID(wf_id, user_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<WorkFlowBean> getWorkFlowList()
/*     */   {
/*  46 */     return WorkFlowManager.getWorkFlowList();
/*     */   }
/*     */ 
/*     */   public static WorkFlowBean getWorkFlowBean(int wf_id)
/*     */   {
/*  56 */     return WorkFlowManager.getWorkFlowBean(wf_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertWorkFlow(WorkFlowBean wfb, HttpServletRequest request)
/*     */   {
/*  67 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  68 */     if (stl != null)
/*     */     {
/*  70 */       return WorkFlowManager.insertWorkFlow(wfb, stl);
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWorkFlow(WorkFlowBean wfb, HttpServletRequest request)
/*     */   {
/*  82 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  83 */     if (stl != null)
/*     */     {
/*  85 */       return WorkFlowManager.updateWorkFlow(wfb, stl);
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWorkFlow(String wf_ids, HttpServletRequest request)
/*     */   {
/*  97 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  98 */     if (stl != null)
/*     */     {
/* 100 */       return WorkFlowManager.deleteWorkFlow(wf_ids, stl);
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.workflow.WorkFlowRPC
 * JD-Core Version:    0.6.2
 */