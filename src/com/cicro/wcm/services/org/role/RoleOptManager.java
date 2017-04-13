/*     */ package com.cicro.wcm.services.org.role;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.operate.OperateBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleOptBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.role.RoleOptDAO;
/*     */ import com.cicro.wcm.server.LicenseCheck;
/*     */ import com.cicro.wcm.services.org.operate.OperateManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class RoleOptManager
/*     */   implements ISyncCatch
/*     */ {
/*  29 */   private static TreeMap<String, List> role_opt_map = new TreeMap();
/*     */ 
/*     */   static {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  37 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  42 */     List role_opt_list = RoleOptDAO.getAllRoleReleOptList();
/*     */ 
/*  44 */     role_opt_map.clear();
/*  45 */     if ((role_opt_list != null) && (role_opt_list.size() > 0))
/*  46 */       for (int i = 0; i < role_opt_list.size(); i++) {
/*  47 */         RoleOptBean rob = (RoleOptBean)role_opt_list.get(i);
/*  48 */         OperateBean ob = OperateManager.getOperateBean(rob.getOpt_id());
/*  49 */         if (role_opt_map.containsKey(rob.getRole_id()))
/*     */         {
/*  51 */           if (ob != null) {
/*  52 */             ((List)role_opt_map.get(rob.getRole_id())).add(ob);
/*     */           }
/*     */         }
/*  55 */         else if (ob != null)
/*     */         {
/*  57 */           List oL = new ArrayList();
/*  58 */           oL.add(OperateManager.getOperateBean(rob.getOpt_id()));
/*  59 */           role_opt_map.put(rob.getRole_id(), oL);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void reloadRoleOpt()
/*     */   {
/*  75 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.role.RoleOptManager");
/*     */   }
/*     */ 
/*     */   public static List<OperateBean> getOptListByRoleID(String role_id)
/*     */   {
/*  89 */     if (role_opt_map.containsKey(role_id))
/*     */     {
/*  91 */       return (List)role_opt_map.get(role_id);
/*     */     }
/*     */ 
/*  94 */     List l = new ArrayList();
/*  95 */     List oL = new ArrayList();
/*  96 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  98 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 100 */         oL.add(OperateManager.getOperateBean(((RoleOptBean)l.get(i)).getOpt_id()));
/*     */       }
/*     */     }
/* 103 */     role_opt_map.put(role_id, oL);
/* 104 */     return oL;
/*     */   }
/*     */ 
/*     */   public static String getOptIDSByRoleID(String role_id)
/*     */   {
/* 117 */     String opt_ids = "";
/* 118 */     if (role_opt_map.containsKey(role_id))
/*     */     {
/* 120 */       List l = (List)role_opt_map.get(role_id);
/* 121 */       if ((l != null) && (l.size() > 0))
/*     */       {
/* 123 */         for (OperateBean ob : l)
/*     */         {
/* 125 */           if (LicenseCheck.isHaveApp(ob.getApp_id()))
/*     */           {
/* 127 */             opt_ids = opt_ids + "," + ob.getOpt_id();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 133 */       List l = new ArrayList();
/* 134 */       if ((l != null) && (l.size() > 0))
/*     */       {
/* 136 */         for (int i = 0; i < l.size(); i++)
/*     */         {
/* 138 */           opt_ids = opt_ids + "," + ((RoleOptBean)l.get(i)).getOpt_id();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 143 */     if ((opt_ids != null) && (!"".equals(opt_ids))) {
/* 144 */       opt_ids = opt_ids.substring(1);
/*     */     }
/* 146 */     return opt_ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertOptReleRole(String role_id, String opt_ids, SettingLogsBean stl)
/*     */   {
/* 158 */     if (RoleOptDAO.insertOptReleRole(role_id, opt_ids, stl))
/*     */     {
/* 160 */       reloadRoleOpt();
/* 161 */       return true;
/*     */     }
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteOptReleRoleByRoleID(String role_id)
/*     */   {
/* 172 */     if (RoleOptDAO.deleteOptReleRoleByRoleID(role_id))
/*     */     {
/* 174 */       reloadRoleOpt();
/* 175 */       return true;
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 182 */     insertOptReleRole("3", "2,3,4,5,6,7,8", new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.role.RoleOptManager
 * JD-Core Version:    0.6.2
 */