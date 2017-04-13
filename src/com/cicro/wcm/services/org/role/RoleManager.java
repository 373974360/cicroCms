/*     */ package com.cicro.wcm.services.org.role;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleAppBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.role.RoleDAO;
/*     */ import com.cicro.wcm.server.LicenseCheck;
/*     */ import com.cicro.wcm.services.cms.workflow.WorkFlowManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class RoleManager
/*     */   implements ISyncCatch
/*     */ {
/*  32 */   private static TreeMap<String, RoleBean> role_map = new TreeMap();
/*     */ 
/*  34 */   private static TreeMap<String, RoleAppBean> role_app_map = new TreeMap();
/*  35 */   private static String not_show_role_ids = JconfigUtilContainer.systemRole().getProperty("role_ids", "", "not_show_role");
/*     */ 
/*  37 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  42 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  48 */     List role_app_list = RoleDAO.getAllRoleAppList();
/*     */ 
/*  50 */     role_app_map.clear();
/*  51 */     if ((role_app_list != null) && (role_app_list.size() > 0)) {
/*  52 */       for (RoleAppBean rab : role_app_list) {
/*  53 */         if (LicenseCheck.isHaveApp(rab.getApp_id())) {
/*  54 */           role_app_map.put(rab.getRole_app_id(), rab);
/*     */         }
/*     */       }
/*     */     }
/*  58 */     List role_list = RoleDAO.getAllRoleList();
/*     */ 
/*  60 */     role_map.clear();
/*  61 */     if ((role_list != null) && (role_list.size() > 0))
/*  62 */       for (RoleBean rb : role_list)
/*  63 */         if (LicenseCheck.isHaveApp(rb.getApp_id()))
/*     */         {
/*  65 */           role_map.put(rb.getRole_id(), rb);
/*  66 */           ((RoleBean)role_map.get(rb.getRole_id())).setA_app_id(getAppIDSByRoleID(rb.getRole_id()));
/*     */         }
/*     */   }
/*     */ 
/*     */   public static void reloadRole()
/*     */   {
/*  79 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.role.RoleManager");
/*     */   }
/*     */ 
/*     */   public static void reloadRoleApp()
/*     */   {
/*  90 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.role.RoleManager");
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getAllRoleList()
/*     */   {
/* 101 */     List l = new ArrayList();
/* 102 */     Set set = role_map.keySet();
/* 103 */     for (String s : set)
/*     */     {
/* 105 */       l.add((RoleBean)role_map.get(s));
/*     */     }
/* 107 */     return l;
/*     */   }
/*     */ 
/*     */   public static Map<String, RoleBean> getRoleMap()
/*     */   {
/* 118 */     return role_map;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListByAPPAndSite(String app_id, String site_id)
/*     */   {
/* 134 */     List role_list = new ArrayList();
/*     */ 
/* 136 */     Iterator iter = role_app_map.entrySet().iterator();
/* 137 */     while (iter.hasNext()) {
/* 138 */       Entry entry = (Entry)iter.next();
/* 139 */       RoleAppBean rab = (RoleAppBean)role_app_map.get((String)entry.getKey());
/*     */ 
/* 142 */       if ((site_id == null) || ("".equals(site_id)))
/*     */       {
/* 144 */         if (app_id.equals(rab.getApp_id())) {
/* 145 */           role_list.add(getRoleBeanByRoleID(rab.getRole_id()));
/*     */         }
/*     */       }
/* 148 */       else if ((app_id.equals(rab.getApp_id())) && (("".equals(rab.getSite_id())) || (site_id.equals(rab.getSite_id())))) {
/* 149 */         role_list.add(getRoleBeanByRoleID(rab.getRole_id()));
/*     */       }
/*     */     }
/*     */ 
/* 153 */     return role_list;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListForDB(Map<String, String> m)
/*     */   {
/* 168 */     return RoleDAO.getRoleListByDB(m);
/*     */   }
/*     */ 
/*     */   public static String getRoleCount(Map<String, String> m)
/*     */   {
/* 181 */     String app_id = (String)m.get("app_id");
/* 182 */     String site_id = (String)m.get("site_id");
/* 183 */     int count = 0;
/* 184 */     Iterator iter = role_app_map.entrySet().iterator();
/* 185 */     while (iter.hasNext()) {
/* 186 */       Entry entry = (Entry)iter.next();
/* 187 */       RoleAppBean rab = (RoleAppBean)role_app_map.get((String)entry.getKey());
/* 188 */       if ((app_id.equals(rab.getApp_id())) && (("".equals(site_id)) || (site_id.equals(rab.getSite_id()))))
/* 189 */         count++;
/*     */     }
/* 191 */     return count;
/*     */   }
/*     */ 
/*     */   public static String getRoleCountForDB(Map<String, String> m)
/*     */   {
/* 203 */     if ((not_show_role_ids != null) && (!"".equals(not_show_role_ids)) && (!"system".equals(m.get("app_id"))))
/* 204 */       m.put("not_show_role_ids", not_show_role_ids);
/* 205 */     return RoleDAO.getRoleCountForDB(m);
/*     */   }
/*     */ 
/*     */   public static String getAppIDSByRoleID(String role_id)
/*     */   {
/* 218 */     String app_ids = "";
/* 219 */     RoleBean rb = getRoleBeanByRoleID(role_id);
/* 220 */     if (rb != null)
/*     */     {
/* 222 */       Iterator iter = role_app_map.entrySet().iterator();
/* 223 */       while (iter.hasNext()) {
/* 224 */         Entry entry = (Entry)iter.next();
/* 225 */         RoleAppBean rab = (RoleAppBean)role_app_map.get((String)entry.getKey());
/* 226 */         if ((role_id.equals(rab.getRole_id())) && (!rb.getApp_id().equals(rab.getApp_id())))
/* 227 */           app_ids = app_ids + "," + rab.getApp_id();
/*     */       }
/* 229 */       if ((app_ids != null) && (!"".equals(app_ids)))
/* 230 */         app_ids = app_ids.substring(1);
/*     */     }
/* 232 */     return app_ids;
/*     */   }
/*     */ 
/*     */   public static RoleBean getRoleBeanByRoleID(String role_id)
/*     */   {
/* 244 */     if (role_map.containsKey(role_id))
/*     */     {
/* 246 */       return (RoleBean)role_map.get(role_id);
/*     */     }
/*     */ 
/* 250 */     RoleBean rb = RoleDAO.getRoleBeanByRoleID(role_id);
/* 251 */     if ((rb != null) && (LicenseCheck.isHaveApp(rb.getApp_id())))
/*     */     {
/* 253 */       role_map.put(role_id, rb);
/*     */     }
/* 255 */     return rb;
/*     */   }
/*     */ 
/*     */   public static String getRoleNamesbyRoleIDS(String role_ids)
/*     */   {
/* 268 */     String names = "";
/* 269 */     if ((role_ids != null) && (!"".equals(role_ids)))
/*     */     {
/* 271 */       String[] tempA = role_ids.split(",");
/* 272 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 274 */         RoleBean rb = getRoleBeanByRoleID(tempA[i]);
/* 275 */         if (rb != null)
/* 276 */           names = names + "," + rb.getRole_name();
/*     */       }
/* 278 */       if ((names != null) && (!"".equals(names.trim())))
/* 279 */         names = names.substring(1);
/*     */     }
/* 281 */     return names;
/*     */   }
/*     */ 
/*     */   public static boolean insertRole(RoleBean rb, SettingLogsBean stl)
/*     */   {
/* 291 */     if (RoleDAO.insertRole(rb, stl))
/*     */     {
/* 293 */       reloadRole();
/* 294 */       return true;
/*     */     }
/*     */ 
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateRole(RoleBean rb, SettingLogsBean stl)
/*     */   {
/* 307 */     if (RoleDAO.updateRole(rb, stl))
/*     */     {
/* 309 */       reloadRole();
/* 310 */       return true;
/*     */     }
/*     */ 
/* 313 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRole(String role_ids, SettingLogsBean stl)
/*     */   {
/* 323 */     if (RoleDAO.deleteRole(role_ids, stl))
/*     */     {
/* 325 */       reloadRole();
/*     */ 
/* 327 */       RoleOptManager.deleteOptReleRoleByRoleID(role_ids);
/* 328 */       return true;
/*     */     }
/*     */ 
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */   public static String deleteRoleBeforeChecked(String role_id, String app_id, String site_id)
/*     */   {
/* 343 */     String msg = "";
/*     */ 
/* 345 */     if (RoleUserManager.getUserListByRole(role_id, app_id, site_id).size() > 0)
/*     */     {
/* 347 */       msg = msg + "user,";
/*     */     }
/* 349 */     if (RoleUGroupManager.getGroupListByRole(role_id, app_id, site_id).size() > 0)
/*     */     {
/* 351 */       msg = msg + "group,";
/*     */     }
/* 353 */     if (WorkFlowManager.getStepCountByRoleID(role_id) > 0)
/*     */     {
/* 355 */       msg = msg + "workflow,";
/*     */     }
/* 357 */     return msg;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 367 */     System.out.println(getRoleListByAPPAndSite("zwgk", ""));
/*     */   }
/*     */ 
/*     */   public static void insertRoleTest()
/*     */   {
/* 372 */     RoleBean rb = new RoleBean();
/*     */ 
/* 374 */     rb.setA_app_id("");
/* 375 */     rb.setApp_id("system");
/* 376 */     rb.setRele_shared(0);
/* 377 */     rb.setRole_memo("role_memo");
/* 378 */     rb.setRole_name("role_name");
/* 379 */     rb.setSite_id("");
/*     */ 
/* 381 */     insertRole(rb, new SettingLogsBean());
/*     */   }
/*     */ 
/*     */   public static void updateRoleTest()
/*     */   {
/* 386 */     RoleBean rb = new RoleBean();
/* 387 */     rb.setRole_id(1);
/* 388 */     rb.setA_app_id("control,cms");
/*     */ 
/* 390 */     rb.setApp_id("system");
/* 391 */     rb.setRele_shared(1);
/* 392 */     rb.setRole_memo("role_memo1");
/* 393 */     rb.setRole_name("role_name1");
/* 394 */     rb.setSite_id("site_id1");
/* 395 */     updateRole(rb, new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.role.RoleManager
 * JD-Core Version:    0.6.2
 */