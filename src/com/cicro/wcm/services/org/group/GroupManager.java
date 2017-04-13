/*     */ package com.cicro.wcm.services.org.group;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.group.GroupBean;
/*     */ import com.cicro.wcm.bean.org.group.GroupUserBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUGroupBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.group.GroupDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryReleManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUGroupManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class GroupManager
/*     */   implements ISyncCatch
/*     */ {
/*  35 */   private static TreeMap<String, GroupBean> group_map = new TreeMap();
/*     */ 
/*  37 */   private static TreeMap<String, String> group_user_map = new TreeMap();
/*     */ 
/*     */   static {
/*  40 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  45 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  50 */     List group_user_list = GroupDAO.getGroupUserList();
/*  51 */     group_user_map.clear();
/*  52 */     if ((group_user_list != null) && (group_user_list.size() > 0)) {
/*  53 */       for (int i = 0; i < group_user_list.size(); i++) {
/*  54 */         String group_id = ((GroupUserBean)group_user_list.get(i)).getGroup_id();
/*  55 */         String user_id = ((GroupUserBean)group_user_list.get(i)).getUser_id();
/*  56 */         if (group_user_map.containsKey(group_id))
/*     */         {
/*  58 */           group_user_map.put(group_id, (String)group_user_map.get(group_id) + user_id + ",");
/*     */         }
/*     */         else {
/*  61 */           group_user_map.put(group_id, "," + user_id + ",");
/*     */         }
/*     */       }
/*     */     }
/*  65 */     group_map.clear();
/*  66 */     List group_list = GroupDAO.getAllGroupList();
/*  67 */     if ((group_list != null) && (group_list.size() > 0))
/*  68 */       for (int i = 0; i < group_list.size(); i++) {
/*  69 */         ((GroupBean)group_list.get(i)).setUser_ids(getUserIDSByGroupIDReload(((GroupBean)group_list.get(i)).getGroup_id()));
/*  70 */         group_map.put(((GroupBean)group_list.get(i)).getGroup_id(), 
/*  71 */           (GroupBean)group_list
/*  71 */           .get(i));
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void reloadGroup()
/*     */   {
/*  85 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.group.GroupManager");
/*     */   }
/*     */ 
/*     */   public static void reloadGroupUser()
/*     */   {
/*  96 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.group.GroupManager");
/*     */   }
/*     */ 
/*     */   public static String getUserIDSByGroupIDReload(String group_id)
/*     */   {
/* 107 */     if (group_user_map.containsKey(group_id)) {
/* 108 */       return (String)group_user_map.get(group_id);
/*     */     }
/* 110 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<GroupBean> getGroupListByAppSiteID(String app_id, String site_id)
/*     */   {
/* 122 */     List list = new ArrayList();
/* 123 */     Set set = group_map.keySet();
/* 124 */     for (String i : set) {
/* 125 */       GroupBean gb = (GroupBean)group_map.get(i);
/* 126 */       if (((site_id.equals(gb.getSite_id())) || ("".equals(site_id))) && (app_id.equals(gb.getApp_id())))
/*     */       {
/* 128 */         list.add(gb);
/*     */       }
/*     */     }
/* 131 */     return list;
/*     */   }
/*     */ 
/*     */   public static int getGroupCount(Map mp)
/*     */   {
/* 141 */     return GroupDAO.getAllGroupCount(mp);
/*     */   }
/*     */ 
/*     */   public static String getUserIDSByGroupID(String group_id)
/*     */   {
/* 152 */     if (group_user_map.containsKey(group_id)) {
/* 153 */       return (String)group_user_map.get(group_id);
/*     */     }
/*     */ 
/* 156 */     GroupBean gb = getGroupBeanByID(group_id);
/* 157 */     if (gb != null) {
/* 158 */       return gb.getUser_ids();
/*     */     }
/* 160 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getUserNameByGroupID(String group_id)
/*     */   {
/* 170 */     String ret = "";
/* 171 */     String userIDS = getUserIDSByGroupID(group_id);
/*     */ 
/* 174 */     userIDS = userIDS.trim();
/* 175 */     int firstIndex = 0;
/* 176 */     int endIndex = userIDS.length();
/* 177 */     if (userIDS.startsWith(","))
/*     */     {
/* 179 */       firstIndex = 1;
/*     */     }
/* 181 */     if (userIDS.endsWith(","))
/*     */     {
/* 183 */       endIndex = userIDS.length() - 1;
/*     */     }
/*     */ 
/* 186 */     userIDS = userIDS.substring(firstIndex, endIndex);
/* 187 */     List ltUserBean = UserManager.getUserListByUserIDS(userIDS);
/*     */ 
/* 189 */     if ((ltUserBean != null) && (ltUserBean.size() > 0))
/*     */     {
/* 191 */       for (int i = 0; i < ltUserBean.size(); i++)
/*     */       {
/* 193 */         ret = ret + "," + ((UserBean)ltUserBean.get(i)).getUser_realname();
/*     */       }
/*     */ 
/* 196 */       ret = ret.substring(1);
/*     */     }
/* 198 */     return ret.toString();
/*     */   }
/*     */ 
/*     */   public static String getRolesByGroupID(String group_id, String app_id, String site_id)
/*     */   {
/* 211 */     String sb = "";
/* 212 */     List roleList = RoleUGroupManager.getRoleIDSByUGroupAPP(group_id, app_id, site_id);
/* 213 */     System.out.println(roleList);
/*     */ 
/* 215 */     if ((roleList != null) && (roleList.size() > 0))
/*     */     {
/* 217 */       for (int i = 0; i < roleList.size(); i++)
/*     */       {
/* 219 */         RoleBean rb = (RoleBean)roleList.get(i);
/* 220 */         if (rb != null)
/*     */         {
/* 222 */           sb = sb + ",";
/* 223 */           sb = sb + rb.getRole_name();
/*     */         }
/*     */       }
/* 226 */       sb = sb.substring(1);
/*     */     }
/* 228 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getRoleIDSByGroupID(String group_id, String app_id, String site_id)
/*     */   {
/* 241 */     String sb = "";
/* 242 */     List roleList = RoleUGroupManager.getRoleIDSByUGroupAPP(group_id, app_id, site_id);
/*     */ 
/* 244 */     if ((roleList != null) && (roleList.size() > 0))
/*     */     {
/* 246 */       for (int i = 0; i < roleList.size(); i++)
/*     */       {
/* 248 */         RoleBean rb = (RoleBean)roleList.get(i);
/* 249 */         if (rb != null)
/*     */         {
/* 251 */           sb = sb + ",";
/* 252 */           sb = sb + rb.getRole_id();
/*     */         }
/*     */       }
/* 255 */       sb = sb.substring(1);
/*     */     }
/* 257 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getGroupIDSByUserID(String user_id)
/*     */   {
/* 270 */     String group_ids = "";
/* 271 */     Iterator iter = group_user_map.entrySet().iterator();
/* 272 */     while (iter.hasNext()) {
/* 273 */       Entry entry = (Entry)iter.next();
/* 274 */       String user_ids = (String)group_user_map.get((String)entry.getKey());
/* 275 */       if (user_ids.contains("," + user_id + ","))
/* 276 */         group_ids = group_ids + "," + (String)entry.getKey();
/*     */     }
/* 278 */     if ((group_ids != null) && (!"".equals(group_ids))) {
/* 279 */       group_ids = group_ids.substring(1);
/*     */     }
/* 281 */     return group_ids;
/*     */   }
/*     */ 
/*     */   public static List getGroupList()
/*     */   {
/* 293 */     List group_list = new ArrayList();
/*     */ 
/* 295 */     Iterator iter = group_map.entrySet().iterator();
/* 296 */     while (iter.hasNext()) {
/* 297 */       Entry entry = (Entry)iter.next();
/* 298 */       group_list.add((GroupBean)group_map.get((String)entry.getKey()));
/*     */     }
/* 300 */     return group_list;
/*     */   }
/*     */ 
/*     */   public static List getGroupListForDB(Map mp)
/*     */   {
/* 310 */     List group_list = GroupDAO.getAllGroupListForDB(mp);
/* 311 */     return group_list;
/*     */   }
/*     */ 
/*     */   public static Map getGroupMap()
/*     */   {
/* 323 */     return group_map;
/*     */   }
/*     */ 
/*     */   public static GroupBean getGroupBeanByID(String group_id)
/*     */   {
/* 335 */     if (group_map.containsKey(group_id))
/*     */     {
/* 337 */       return (GroupBean)group_map.get(group_id);
/*     */     }
/*     */ 
/* 341 */     GroupBean gb = GroupDAO.getGroupBeanByID(group_id);
/* 342 */     if (gb != null)
/*     */     {
/* 344 */       gb.setUser_ids(getUserIDSByGroupIDReload(group_id));
/* 345 */       group_map.put(group_id, gb);
/*     */     }
/* 347 */     return gb;
/*     */   }
/*     */ 
/*     */   public static boolean insertGroup(GroupBean gb, SettingLogsBean stl)
/*     */   {
/* 358 */     if (GroupDAO.insertGroup(gb, stl))
/*     */     {
/* 360 */       reloadGroup();
/* 361 */       return true;
/*     */     }
/*     */ 
/* 364 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGroup(GroupBean gb, SettingLogsBean stl)
/*     */   {
/* 375 */     if (GroupDAO.updateGroup(gb, stl))
/*     */     {
/* 377 */       reloadGroup();
/* 378 */       return true;
/*     */     }
/*     */ 
/* 381 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByUGroup(String roleIDS, GroupBean gb, SettingLogsBean stl)
/*     */   {
/* 391 */     RoleUGroupBean rugb = new RoleUGroupBean();
/* 392 */     rugb.setApp_id(gb.getApp_id());
/* 393 */     rugb.setSite_id(gb.getSite_id());
/* 394 */     rugb.setGroup_id(String.valueOf(gb.getGroup_id()));
/* 395 */     rugb.setRole_id(roleIDS);
/* 396 */     if (stl != null) {
/* 397 */       return RoleUGroupManager.insertRoleUserByUGroup(rugb, stl);
/*     */     }
/*     */ 
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroup(String group_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 413 */     if (GroupDAO.deleteGroup(group_ids, stl))
/*     */     {
/* 415 */       reloadGroup();
/* 416 */       RoleUGroupManager.deleteRoleUGroupByGroup(group_ids);
/* 417 */       CategoryReleManager.deleteCateReleByPrv(1, group_ids, site_id);
/* 418 */       return true;
/*     */     }
/*     */ 
/* 421 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertGroupUser(GroupBean gb, String delete_user_ids, SettingLogsBean stl)
/*     */   {
/* 432 */     if (GroupDAO.insertGroupUser(gb, stl))
/*     */     {
/* 434 */       if ((delete_user_ids != null) && (!"".equals(delete_user_ids)))
/* 435 */         GroupDAO.deleteGroupUser(delete_user_ids, gb.getGroup_id());
/* 436 */       reloadGroup();
/* 437 */       return true;
/*     */     }
/*     */ 
/* 440 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByGroupID(String group_id)
/*     */   {
/* 450 */     if (GroupDAO.deleteGroupUserByGroupID(group_id))
/*     */     {
/* 452 */       reloadGroup();
/*     */ 
/* 454 */       RoleUGroupManager.deleteRoleUGroupByGroup(group_id);
/* 455 */       return true;
/*     */     }
/*     */ 
/* 458 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByGroupID(String group_user_id, SettingLogsBean stl)
/*     */   {
/* 468 */     if (GroupDAO.deleteGroupUserByGroupID(group_user_id, stl))
/*     */     {
/* 470 */       reloadGroup();
/* 471 */       return true;
/*     */     }
/*     */ 
/* 474 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByUserID(String user_id)
/*     */   {
/* 483 */     if (GroupDAO.deleteGroupUserByUserID(user_id))
/*     */     {
/* 485 */       reloadGroup();
/* 486 */       return true;
/*     */     }
/*     */ 
/* 490 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 496 */     System.out.println(getRolesByGroupID("57", "cms", "11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.group.GroupManager
 * JD-Core Version:    0.6.2
 */