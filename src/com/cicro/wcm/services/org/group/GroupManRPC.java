/*     */ package com.cicro.wcm.services.org.group;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.group.GroupBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class GroupManRPC
/*     */ {
/*     */   public static List getGroupListForDB(Map map)
/*     */   {
/*  28 */     return GroupManager.getGroupListForDB(map);
/*     */   }
/*     */ 
/*     */   public static List<GroupBean> getGroupListByAppSiteID(String app_id, String site_id)
/*     */   {
/*  40 */     return GroupManager.getGroupListByAppSiteID(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static int getGroupCount(Map mp)
/*     */   {
/*  51 */     return GroupManager.getGroupCount(mp);
/*     */   }
/*     */ 
/*     */   public static String getUserIDSByGroupID(String id)
/*     */   {
/*  61 */     return GroupManager.getUserIDSByGroupID(id);
/*     */   }
/*     */ 
/*     */   public static String getGroupIDSByUserID(String userId)
/*     */   {
/*  72 */     return GroupManager.getGroupIDSByUserID(userId);
/*     */   }
/*     */ 
/*     */   public static Map getGroupMap()
/*     */   {
/*  83 */     return GroupManager.getGroupMap();
/*     */   }
/*     */ 
/*     */   public static GroupBean getGroupBeanByID(String group_id)
/*     */   {
/*     */     try
/*     */     {
/*  94 */       return GroupManager.getGroupBeanByID(group_id);
/*     */     }
/*     */     catch (Throwable tex) {
/*  97 */       GroupBean gb = new GroupBean();
/*  98 */       gb.setGroup_name("");
/*  99 */       gb.setGroup_memo("");
/* 100 */       return gb;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getUserNameByGroupID(String group_id)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       return GroupManager.getUserNameByGroupID(group_id);
/*     */     } catch (Throwable tex) {
/*     */     }
/* 116 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getRolesByGroupID(String group_id, String app_id, String site_id)
/*     */   {
/* 130 */     return GroupManager.getRolesByGroupID(group_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getRoleIDSByGroupID(String group_id, String app_id, String site_id)
/*     */   {
/* 143 */     return GroupManager.getRoleIDSByGroupID(group_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGroup(GroupBean gb, HttpServletRequest request)
/*     */   {
/* 154 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 155 */     if (stl != null)
/*     */     {
/* 157 */       return GroupManager.insertGroup(gb, stl);
/*     */     }
/*     */ 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGroup(GroupBean gb, HttpServletRequest request)
/*     */   {
/* 173 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 174 */     if (stl != null)
/*     */     {
/* 176 */       return GroupManager.updateGroup(gb, stl);
/*     */     }
/*     */ 
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByUGroup(String roleIDS, GroupBean gb, HttpServletRequest request)
/*     */   {
/* 191 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 192 */     if (stl != null) {
/* 193 */       return GroupManager.insertRoleUserByUGroup(roleIDS, gb, stl);
/*     */     }
/*     */ 
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroup(String group_ids, String site_id, HttpServletRequest request)
/*     */   {
/* 210 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 211 */     if (stl != null)
/*     */     {
/* 213 */       return GroupManager.deleteGroup(group_ids, site_id, stl);
/*     */     }
/*     */ 
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertGroupUser(GroupBean gb, String delete_user_ids, HttpServletRequest request)
/*     */   {
/* 230 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 231 */     if (stl != null)
/*     */     {
/* 233 */       return GroupManager.insertGroupUser(gb, delete_user_ids, stl);
/*     */     }
/*     */ 
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByGroupID(String group_id)
/*     */   {
/* 249 */     return GroupManager.deleteGroupUserByGroupID(group_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByUserID(String user_id)
/*     */   {
/* 260 */     return GroupManager.deleteGroupUserByUserID(user_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.group.GroupManRPC
 * JD-Core Version:    0.6.2
 */