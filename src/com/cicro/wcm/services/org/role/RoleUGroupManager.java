/*     */ package com.cicro.wcm.services.org.role;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUGroupBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.role.RoleUGroupDAO;
/*     */ import com.cicro.wcm.services.org.group.GroupManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class RoleUGroupManager
/*     */   implements ISyncCatch
/*     */ {
/*  32 */   private static TreeMap<String, RoleUGroupBean> role_uGroup_map = new TreeMap();
/*     */ 
/*     */   static {
/*  35 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  40 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  45 */     List role_ugroup_list = RoleUGroupDAO.getAllRoleUGroupList();
/*     */ 
/*  47 */     role_uGroup_map.clear();
/*  48 */     if ((role_ugroup_list != null) && (role_ugroup_list.size() > 0))
/*  49 */       for (int i = 0; i < role_ugroup_list.size(); i++)
/*  50 */         role_uGroup_map.put(((RoleUGroupBean)role_ugroup_list.get(i)).getGroup_role_id(), 
/*  51 */           (RoleUGroupBean)role_ugroup_list
/*  51 */           .get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadRoleUGroup()
/*     */   {
/*  64 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.role.RoleUGroupManager");
/*     */   }
/*     */ 
/*     */   public static Set getGroupAppIDS(String group_id)
/*     */   {
/*  75 */     Set app_s = new HashSet();
/*  76 */     group_id = "," + group_id + ",";
/*  77 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/*  78 */     while (iter.hasNext()) {
/*  79 */       Map.Entry entry = (Map.Entry)iter.next();
/*  80 */       RoleUGroupBean rub = (RoleUGroupBean)entry.getValue();
/*  81 */       if (group_id.contains("," + rub.getGroup_id() + ","))
/*     */       {
/*  83 */         app_s.add(rub.getApp_id());
/*     */       }
/*     */     }
/*     */ 
/*  87 */     return app_s;
/*     */   }
/*     */ 
/*     */   public static Set getGroupSiteIDS(String group_id)
/*     */   {
/*  98 */     Set site_s = new HashSet();
/*  99 */     group_id = "," + group_id + ",";
/* 100 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/* 101 */     while (iter.hasNext()) {
/* 102 */       Map.Entry entry = (Map.Entry)iter.next();
/* 103 */       RoleUGroupBean rub = (RoleUGroupBean)entry.getValue();
/*     */ 
/* 105 */       if ((group_id.contains("," + rub.getGroup_id() + ",")) && (!"".equals(rub.getSite_id())) && (!"null".equals(rub.getSite_id().toLowerCase())))
/*     */       {
/* 107 */         site_s.add(rub.getSite_id());
/*     */       }
/*     */     }
/*     */ 
/* 111 */     return site_s;
/*     */   }
/*     */ 
/*     */   public static List getGroupListByRole(String role_id, String app_id, String site_id)
/*     */   {
/* 124 */     List user_list = new ArrayList();
/*     */ 
/* 126 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/* 127 */     while (iter.hasNext()) {
/* 128 */       Map.Entry entry = (Map.Entry)iter.next();
/* 129 */       RoleUGroupBean rugb = (RoleUGroupBean)entry.getValue();
/*     */ 
/* 131 */       if ((role_id.equals(rugb.getRole_id())) && (app_id.equals(rugb.getApp_id())) && (("".equals(site_id)) || (site_id.equals(rugb.getSite_id()))))
/*     */       {
/* 133 */         user_list.add(GroupManager.getGroupBeanByID(rugb.getGroup_id()));
/*     */       }
/*     */     }
/* 136 */     return user_list;
/*     */   }
/*     */ 
/*     */   public static String getGroupsByRole(String role_id, String app_id, String site_id)
/*     */   {
/* 149 */     String group_ids = "";
/* 150 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/* 151 */     while (iter.hasNext()) {
/* 152 */       Map.Entry entry = (Map.Entry)iter.next();
/* 153 */       RoleUGroupBean rugb = (RoleUGroupBean)entry.getValue();
/*     */ 
/* 155 */       if ((role_id.equals(rugb.getRole_id())) && (app_id.equals(rugb.getApp_id())) && (("".equals(site_id)) || (site_id.equals(rugb.getSite_id()))))
/*     */       {
/* 157 */         group_ids = group_ids + "," + rugb.getGroup_id();
/*     */       }
/*     */     }
/* 160 */     if ((group_ids != null) && (!"".equals(group_ids)))
/* 161 */       group_ids = group_ids.substring(1);
/* 162 */     return group_ids;
/*     */   }
/*     */ 
/*     */   public static Set getRoleSetByUGroupID(String group_id)
/*     */   {
/* 173 */     group_id = "," + group_id + ",";
/* 174 */     Set role_s = new HashSet();
/* 175 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/* 176 */     while (iter.hasNext()) {
/* 177 */       Map.Entry entry = (Map.Entry)iter.next();
/* 178 */       RoleUGroupBean rub = (RoleUGroupBean)entry.getValue();
/*     */ 
/* 180 */       if (group_id.contains("," + rub.getGroup_id() + ","))
/*     */       {
/* 182 */         RoleBean rb = RoleManager.getRoleBeanByRoleID(rub.getRole_id());
/* 183 */         if (rb != null)
/* 184 */           role_s.add(rb);
/*     */       }
/*     */     }
/* 187 */     return role_s;
/*     */   }
/*     */ 
/*     */   public static String getRoleIDSByUGroupID(String group_id)
/*     */   {
/* 200 */     group_id = "," + group_id + ",";
/* 201 */     String role_ids = "";
/* 202 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/* 203 */     while (iter.hasNext()) {
/* 204 */       Map.Entry entry = (Map.Entry)iter.next();
/* 205 */       RoleUGroupBean rub = (RoleUGroupBean)entry.getValue();
/*     */ 
/* 207 */       if (group_id.contains("," + rub.getGroup_id() + ","))
/*     */       {
/* 209 */         role_ids = role_ids + "," + rub.getRole_id();
/*     */       }
/*     */     }
/*     */ 
/* 213 */     if ((role_ids != null) && (!"".equals(role_ids)))
/* 214 */       role_ids = role_ids.substring(1);
/* 215 */     return role_ids;
/*     */   }
/*     */ 
/*     */   public static List getRoleIDSByUGroupAPP(String group_id, String app_id, String site_id)
/*     */   {
/* 226 */     group_id = "," + group_id + ",";
/* 227 */     List role_list = new ArrayList();
/* 228 */     Iterator iter = role_uGroup_map.entrySet().iterator();
/* 229 */     while (iter.hasNext()) {
/* 230 */       Map.Entry entry = (Map.Entry)iter.next();
/* 231 */       RoleUGroupBean rub = (RoleUGroupBean)entry.getValue();
/*     */ 
/* 233 */       if ((group_id.contains("," + rub.getGroup_id() + ",")) && ((app_id.equals(rub.getApp_id())) || ("system".equals(rub.getApp_id()))) && (("".equals(site_id)) || (site_id.equals(rub.getSite_id()))))
/*     */       {
/* 235 */         RoleBean r = RoleManager.getRoleBeanByRoleID(rub.getRole_id());
/* 236 */         if (r != null)
/* 237 */           role_list.add(r);
/*     */       }
/*     */     }
/* 240 */     return role_list;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUGroupByRole(RoleUGroupBean rugb, String delete_group_ids, SettingLogsBean stl)
/*     */   {
/* 254 */     if (RoleUGroupDAO.deleteRoleUGroupByRole(delete_group_ids, rugb.getRole_id(), rugb.getApp_id(), rugb.getSite_id())) {
/*     */       try
/*     */       {
/* 257 */         if ((rugb.getGroup_id() != null) && (!"".equals(rugb.getGroup_id().trim())))
/*     */         {
/* 259 */           String[] tempA = rugb.getGroup_id().split(",");
/* 260 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 262 */             rugb.setGroup_id(tempA[i]);
/* 263 */             RoleUGroupDAO.insertRoleUGroup(rugb, stl);
/*     */           }
/*     */         }
/* 266 */         reloadRoleUGroup();
/* 267 */         return true;
/*     */       } catch (Exception e) {
/* 269 */         e.printStackTrace();
/* 270 */         return false;
/*     */       }
/*     */     }
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByUGroup(RoleUGroupBean rugb, SettingLogsBean stl)
/*     */   {
/* 285 */     if (RoleUGroupDAO.deleteRoleUGroupByGroup(rugb.getGroup_id())) {
/*     */       try
/*     */       {
/* 288 */         if (rugb.getRole_id() != null)
/*     */         {
/* 290 */           String[] tempA = rugb.getRole_id().split(",");
/* 291 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 293 */             rugb.setRole_id(tempA[i]);
/* 294 */             RoleUGroupDAO.insertRoleUGroup(rugb, stl);
/*     */           }
/*     */         }
/* 297 */         reloadRoleUGroup();
/* 298 */         return true;
/*     */       } catch (Exception e) {
/* 300 */         e.printStackTrace();
/* 301 */         return false;
/*     */       }
/*     */     }
/* 304 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUGroupByRoleID(String role_id)
/*     */   {
/* 314 */     if (RoleUGroupDAO.deleteRoleUGroupByRoleID(role_id))
/*     */     {
/* 316 */       reloadRoleUGroup();
/* 317 */       return true;
/*     */     }
/* 319 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUGroupByGroup(String group_id)
/*     */   {
/* 329 */     if (RoleUGroupDAO.deleteRoleUGroupByGroup(group_id))
/*     */     {
/* 331 */       reloadRoleUGroup();
/* 332 */       return true;
/*     */     }
/* 334 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 340 */     System.out.println(getGroupAppIDS("3"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.role.RoleUGroupManager
 * JD-Core Version:    0.6.2
 */