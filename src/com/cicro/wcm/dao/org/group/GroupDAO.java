/*     */ package com.cicro.wcm.dao.org.group;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.group.GroupBean;
/*     */ import com.cicro.wcm.bean.org.group.GroupUserBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GroupDAO
/*     */ {
/*     */   public static List<GroupBean> getAllGroupList()
/*     */   {
/*  33 */     return DBManager.queryFList("getAllGroupList", "");
/*     */   }
/*     */ 
/*     */   public static List<GroupBean> getAllGroupListForDB(Map mp)
/*     */   {
/*  44 */     return DBManager.queryFList("getAllGroupListForDB", mp);
/*     */   }
/*     */ 
/*     */   public static int getAllGroupCount(Map<String, String> mp)
/*     */   {
/*  55 */     String cnt = (String)DBManager.queryFObj("getAllGroupCount", mp);
/*  56 */     return Integer.valueOf(cnt).intValue();
/*     */   }
/*     */ 
/*     */   public static GroupBean getGroupBeanByID(String id)
/*     */   {
/*  65 */     return (GroupBean)DBManager.queryFObj("getGroupBeanByID", id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGroup(GroupBean gb, SettingLogsBean stl)
/*     */   {
/*  75 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.GROUP_TABLE_NAME);
/*  76 */     gb.setGroup_id(id);
/*  77 */     if (DBManager.insert("insert_group", gb))
/*     */     {
/*  79 */       PublicTableDAO.insertSettingLogs("添加", "用户组", id, stl);
/*  80 */       return true;
/*     */     }
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGroup(GroupBean gb, SettingLogsBean stl)
/*     */   {
/*  92 */     if (DBManager.update("update_group", gb))
/*     */     {
/*  94 */       PublicTableDAO.insertSettingLogs("修改", "用户组", gb.getGroup_id(), stl);
/*  95 */       return true;
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroup(String group_ids, SettingLogsBean stl)
/*     */   {
/* 108 */     Map mp = new HashMap();
/* 109 */     mp.put("group_id", group_ids);
/* 110 */     if (DBManager.delete("delete_group", mp))
/*     */     {
/* 112 */       deleteGroupUserByGroupID(group_ids);
/* 113 */       PublicTableDAO.insertSettingLogs("删除", "用户组", group_ids, stl);
/* 114 */       return true;
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static List getGroupUserList()
/*     */   {
/* 128 */     return DBManager.queryFList("getGroupUserList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertGroupUser(GroupBean gb, SettingLogsBean stl)
/*     */   {
/* 137 */     GroupUserBean gub = new GroupUserBean();
/* 138 */     gub.setGroup_id(gb.getGroup_id());
/* 139 */     gub.setSite_id(gb.getSite_id());
/* 140 */     gub.setApp_id(gb.getApp_id());
/*     */     try
/*     */     {
/* 143 */       if ((gb.getUser_ids() != null) && (!"".equals(gb.getUser_ids())))
/*     */       {
/* 145 */         String[] tempA = gb.getUser_ids().split(",");
/* 146 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 148 */           if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */           {
/* 150 */             gub.setGroup_user_id(PublicTableDAO.getIDByTableName(PublicTableDAO.GROUPUSER_TABLE_NAME));
/* 151 */             gub.setUser_id(Integer.parseInt(tempA[i]));
/* 152 */             DBManager.insert("insert_group_user", gub);
/*     */           }
/*     */         }
/*     */       }
/* 156 */       PublicTableDAO.insertSettingLogs("添加", "用户组用户关联ID", gb.getGroup_id(), stl);
/* 157 */       return true;
/*     */     } catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByGroupID(String group_id)
/*     */   {
/* 171 */     Map m = new HashMap();
/* 172 */     m.put("group_id", group_id);
/* 173 */     return DBManager.delete("delete_group_user_byGroupID", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByGroupID(String group_user_id, SettingLogsBean stl)
/*     */   {
/* 183 */     Map m = new HashMap();
/* 184 */     m.put("group_id", group_user_id);
/* 185 */     if (DBManager.delete("delete_group_user", m))
/*     */     {
/* 187 */       PublicTableDAO.insertSettingLogs("删除", "用户组用户关联ID", group_user_id, stl);
/* 188 */       return true;
/*     */     }
/*     */ 
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUserByUserID(String user_id)
/*     */   {
/* 201 */     Map m = new HashMap();
/* 202 */     m.put("user_id", user_id);
/* 203 */     return DBManager.delete("delete_group_user", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGroupUser(String user_id, String group_id)
/*     */   {
/* 214 */     if ((user_id == null) || ("".equals(user_id)))
/* 215 */       return true;
/* 216 */     Map m = new HashMap();
/* 217 */     m.put("user_id", user_id);
/* 218 */     m.put("group_id", group_id);
/* 219 */     return DBManager.delete("delete_group_user", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.group.GroupDAO
 * JD-Core Version:    0.6.2
 */