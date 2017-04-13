/*     */ package com.cicro.wcm.services.cms.workflow;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.workflow.WorkFlowBean;
/*     */ import com.cicro.wcm.bean.cms.workflow.WorkFlowLogBean;
/*     */ import com.cicro.wcm.bean.cms.workflow.WorkFlowStatusBean;
/*     */ import com.cicro.wcm.bean.cms.workflow.WorkFlowStepBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.cms.workflow.WorkFlowDAO;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import com.cicro.wcm.services.org.user.UserLogin;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class WorkFlowManager
/*     */   implements ISyncCatch
/*     */ {
/*  37 */   private static TreeMap<Integer, WorkFlowBean> wf_map = new TreeMap();
/*     */ 
/*  39 */   private static int last_verify = 100;
/*     */ 
/*     */   static {
/*  42 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  47 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  53 */     wf_map.clear();
/*     */     try {
/*  55 */       List wf_list = WorkFlowDAO.getAllWorkFlowList();
/*  56 */       if ((wf_list != null) && (wf_list.size() > 0))
/*     */       {
/*  58 */         for (int i = 0; i < wf_list.size(); i++)
/*     */         {
/*  60 */           wf_map.put(Integer.valueOf(((WorkFlowBean)wf_list.get(i)).getWf_id()), (WorkFlowBean)wf_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadWorkFlow()
/*     */   {
/*  77 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.workflow.WorkFlowManager");
/*     */   }
/*     */ 
/*     */   public static List<WorkFlowBean> getWorkFlowList()
/*     */   {
/*  88 */     List wf_list = new ArrayList();
/*  89 */     Iterator iter = wf_map.entrySet().iterator();
/*  90 */     while (iter.hasNext()) {
/*  91 */       Map.Entry entry = (Map.Entry)iter.next();
/*  92 */       wf_list.add((WorkFlowBean)entry.getValue());
/*     */     }
/*  94 */     return wf_list;
/*     */   }
/*     */ 
/*     */   public static WorkFlowBean getWorkFlowBean(int wf_id)
/*     */   {
/* 104 */     if (wf_id == 0)
/* 105 */       return null;
/* 106 */     if (wf_map.containsKey(Integer.valueOf(wf_id)))
/*     */     {
/* 108 */       return (WorkFlowBean)wf_map.get(Integer.valueOf(wf_id));
/*     */     }
/*     */ 
/* 111 */     WorkFlowBean wfb = WorkFlowDAO.getWorkFlowBean(wf_id);
/* 112 */     if (wfb != null) {
/* 113 */       wf_map.put(Integer.valueOf(wf_id), wfb);
/* 114 */       return wfb;
/*     */     }
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getMaxStepIDByUserID(int wf_id, String user_id, String app_id, String site_id)
/*     */   {
/* 131 */     if (UserLogin.isSiteManager(user_id, app_id, site_id))
/*     */     {
/* 134 */       return 100;
/*     */     }
/*     */ 
/* 137 */     int step_id = 0;
/*     */ 
/* 139 */     List role_list = RoleUserManager.getRoleListByUserAppSite(user_id, app_id, site_id);
/*     */ 
/* 141 */     if ((role_list != null) && (role_list.size() > 0))
/*     */     {
/* 143 */       String step_ids = "";
/* 144 */       for (int i = 0; i < role_list.size(); i++)
/*     */       {
/* 146 */         String s_ids = getStepIDSbyEFIDAndRoleID(wf_id, ((RoleBean)role_list.get(i)).getRole_id());
/* 147 */         if ((s_ids != null) && (!"".equals(s_ids))) {
/* 148 */           step_ids = step_ids + "," + s_ids;
/*     */         }
/*     */       }
/* 151 */       if ((step_ids != null) && (!"".equals(step_ids)))
/*     */       {
/* 153 */         step_ids = step_ids.substring(1);
/* 154 */         if (!"".equals(step_ids))
/*     */         {
/* 156 */           String[] tempA = step_ids.split(",");
/* 157 */           Arrays.sort(tempA);
/* 158 */           step_id = Integer.parseInt(tempA[(tempA.length - 1)]);
/*     */         }
/*     */       }
/* 161 */       WorkFlowBean wfb = getWorkFlowBean(wf_id);
/* 162 */       if (wfb != null)
/*     */       {
/* 164 */         if (step_id == wfb.getWf_steps())
/*     */         {
/* 166 */           return last_verify;
/*     */         }
/*     */       }
/*     */     }
/* 170 */     return step_id;
/*     */   }
/*     */ 
/*     */   public static int getNextStepIDByUserID(int wf_id, String user_id, String app_id, String site_id)
/*     */   {
/* 184 */     if (UserLogin.isSiteManager(user_id, app_id, site_id))
/*     */     {
/* 187 */       return last_verify;
/*     */     }
/*     */ 
/* 190 */     int step_id = getMaxStepIDByUserID(wf_id, user_id, app_id, site_id);
/* 191 */     if (step_id == last_verify) {
/* 192 */       return last_verify;
/*     */     }
/* 194 */     return getNextStepID(wf_id, step_id);
/*     */   }
/*     */ 
/*     */   public static int getNextStepID(int wf_id, int step_id)
/*     */   {
/* 206 */     WorkFlowBean wfb = getWorkFlowBean(wf_id);
/* 207 */     if (wfb != null)
/*     */     {
/* 209 */       if (step_id == wfb.getWf_steps())
/*     */       {
/* 211 */         return last_verify;
/*     */       }
/* 213 */       return step_id + 1;
/*     */     }
/* 215 */     return last_verify;
/*     */   }
/*     */ 
/*     */   public static String getStepIDSbyEFIDAndRoleID(int wf_id, String role_id)
/*     */   {
/* 226 */     String step_ids = "";
/* 227 */     role_id = "," + role_id + ",";
/*     */ 
/* 229 */     WorkFlowBean wfb = getWorkFlowBean(wf_id);
/* 230 */     if (wfb != null)
/*     */     {
/* 232 */       List wfs_list = wfb.getWorkFlowStep_list();
/* 233 */       if ((wfs_list != null) && (wfs_list.size() > 0))
/*     */       {
/* 235 */         for (int i = 0; i < wfs_list.size(); i++)
/*     */         {
/* 237 */           if (((WorkFlowStepBean)wfs_list.get(i)).getRole_id().contains(role_id))
/*     */           {
/* 239 */             step_ids = step_ids + "," + ((WorkFlowStepBean)wfs_list.get(i)).getStep_id();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 244 */     if (step_ids.length() > 0) {
/* 245 */       step_ids = step_ids.substring(1);
/*     */     }
/* 247 */     return step_ids;
/*     */   }
/*     */ 
/*     */   public static int getStepCountByRoleID(String role_id)
/*     */   {
/* 257 */     role_id = "," + role_id + ",";
/* 258 */     Set s = wf_map.keySet();
/*     */     List wfs_list;
/*     */     int j;
/* 259 */     label130: for (Iterator localIterator = s.iterator(); localIterator.hasNext(); 
/* 264 */       j < wfs_list.size())
/*     */     {
/* 259 */       int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 261 */       wfs_list = ((WorkFlowBean)wf_map.get(Integer.valueOf(i))).getWorkFlowStep_list();
/* 262 */       if ((wfs_list == null) || (wfs_list.size() <= 0))
/*     */         break label130;
/* 264 */       j = 0; continue;
/*     */ 
/* 266 */       if (((WorkFlowStepBean)wfs_list.get(j)).getRole_id().contains(role_id))
/*     */       {
/* 268 */         return 1;
/*     */       }
/* 264 */       j++;
/*     */     }
/*     */ 
/* 273 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean insertWorkFlow(WorkFlowBean wfb, SettingLogsBean stl)
/*     */   {
/* 286 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.WORKFLOW_TABLE_NAME);
/* 287 */     wfb.setWf_id(id);
/* 288 */     if (WorkFlowDAO.insertWorkFlow(wfb, stl))
/*     */     {
/* 290 */       if (!insertWorkFlowStep(id, wfb.getWorkFlowStep_list()))
/*     */       {
/* 293 */         WorkFlowDAO.deleteWorkFlow(id, stl);
/* 294 */         WorkFlowDAO.deleteWorkFlowStep(id);
/* 295 */         return false;
/*     */       }
/*     */ 
/* 298 */       reloadWorkFlow();
/* 299 */       return true;
/*     */     }
/*     */ 
/* 303 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWorkFlow(WorkFlowBean wfb, SettingLogsBean stl)
/*     */   {
/* 313 */     if (WorkFlowDAO.updateWorkFlow(wfb, stl))
/*     */     {
/* 315 */       WorkFlowDAO.deleteWorkFlowStep(wfb.getWf_id());
/*     */ 
/* 317 */       if (!insertWorkFlowStep(wfb.getWf_id(), wfb.getWorkFlowStep_list()))
/*     */       {
/* 319 */         WorkFlowDAO.deleteWorkFlowStep(wfb.getWf_id());
/* 320 */         return false;
/*     */       }
/* 322 */       reloadWorkFlow();
/* 323 */       return true;
/*     */     }
/*     */ 
/* 326 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWorkFlow(String wf_ids, SettingLogsBean stl)
/*     */   {
/* 336 */     if (WorkFlowDAO.deleteWorkFlow(wf_ids, stl)) {
/* 337 */       WorkFlowDAO.deleteWorkFlowStep(wf_ids);
/* 338 */       reloadWorkFlow();
/* 339 */       return true;
/*     */     }
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertWorkFlowStep(int wf_id, List<WorkFlowStepBean> wfs_list)
/*     */   {
/* 352 */     if ((wfs_list != null) && (wfs_list.size() > 0))
/*     */     {
/* 354 */       for (int i = 0; i < wfs_list.size(); i++)
/*     */       {
/* 356 */         ((WorkFlowStepBean)wfs_list.get(i)).setWf_id(wf_id);
/* 357 */         if (!((WorkFlowStepBean)wfs_list.get(i)).getRole_id().startsWith(","))
/* 358 */           ((WorkFlowStepBean)wfs_list.get(i)).setRole_id("," + ((WorkFlowStepBean)wfs_list.get(i)).getRole_id());
/* 359 */         if (!((WorkFlowStepBean)wfs_list.get(i)).getRole_id().endsWith(","))
/* 360 */           ((WorkFlowStepBean)wfs_list.get(i)).setRole_id(((WorkFlowStepBean)wfs_list.get(i)).getRole_id() + ",");
/* 361 */         if (!WorkFlowDAO.insertWorkFlowStep((WorkFlowStepBean)wfs_list.get(i)))
/* 362 */           return false;
/*     */       }
/*     */     }
/* 365 */     return true;
/*     */   }
/*     */ 
/*     */   public static List<WorkFlowLogBean> getWorkFlowLogList()
/*     */   {
/* 377 */     List wfl_list = WorkFlowDAO.getAllWorkFlowLogList();
/* 378 */     return wfl_list;
/*     */   }
/*     */ 
/*     */   public static WorkFlowLogBean getWorkFlowLogBean(int log_id)
/*     */   {
/* 388 */     WorkFlowLogBean wflb = WorkFlowDAO.getWorkFlowLogBean(log_id);
/* 389 */     return wflb;
/*     */   }
/*     */ 
/*     */   public static boolean insertWorkFlowLog(WorkFlowLogBean wflb)
/*     */   {
/* 399 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.WORKFLOW_LOG_TABLE_NAME);
/* 400 */     wflb.setLog_id(id);
/* 401 */     if (WorkFlowDAO.insertWorkFlowLog(wflb)) {
/* 402 */       return true;
/*     */     }
/* 404 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWorkFlowLog(WorkFlowLogBean wflb)
/*     */   {
/* 415 */     if (WorkFlowDAO.updateWorkFlowLog(wflb)) {
/* 416 */       return true;
/*     */     }
/* 418 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWorkFlowLog(String log_ids)
/*     */   {
/* 427 */     if (WorkFlowDAO.deleteWorkFlowLog(log_ids)) {
/* 428 */       return true;
/*     */     }
/* 430 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WorkFlowStatusBean> getWorkFlowStatusList()
/*     */   {
/* 445 */     List wfsl_list = WorkFlowDAO.getAllInfoStatus();
/* 446 */     return wfsl_list;
/*     */   }
/*     */ 
/*     */   public static WorkFlowStatusBean getWorkFlowStatusBean(int status_id)
/*     */   {
/* 456 */     WorkFlowStatusBean wflb = WorkFlowDAO.getInfoStatusBean(status_id);
/* 457 */     return wflb;
/*     */   }
/*     */ 
/*     */   public static boolean insertWorkFlowStatus(WorkFlowStatusBean wfsb)
/*     */   {
/* 467 */     if (WorkFlowDAO.insert_infoStatus(wfsb)) {
/* 468 */       return true;
/*     */     }
/* 470 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWorkFlowStatus(WorkFlowStatusBean wfsb)
/*     */   {
/* 481 */     if (WorkFlowDAO.update_infoStatus(wfsb)) {
/* 482 */       return true;
/*     */     }
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWorkFlowStatus(String status_ids)
/*     */   {
/* 493 */     if (WorkFlowDAO.delete_infoStatus(status_ids)) {
/* 494 */       return true;
/*     */     }
/* 496 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 501 */     System.out.println(getMaxStepIDByUserID(33, "116", "cms", ""));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.workflow.WorkFlowManager
 * JD-Core Version:    0.6.2
 */