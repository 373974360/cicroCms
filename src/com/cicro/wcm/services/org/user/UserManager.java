/*     */ package com.cicro.wcm.services.org.user;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.user.SMUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserRegisterBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.OrgDAOFactory;
/*     */ import com.cicro.wcm.dao.org.user.IUserDAO;
/*     */ import com.cicro.wcm.services.org.dept.DeptManager;
/*     */ import com.cicro.wcm.services.org.group.GroupManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class UserManager
/*     */   implements ISyncCatch
/*     */ {
/*  37 */   private static Map<String, UserBean> user_Map = new HashMap();
/*     */ 
/*  39 */   private static IUserDAO userDao = OrgDAOFactory.getUserDao();
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
/*  52 */     List user_list = userDao.getAllUserList();
/*     */ 
/*  54 */     user_Map.clear();
/*  55 */     if ((user_list != null) && (user_list.size() > 0))
/*  56 */       for (int i = 0; i < user_list.size(); i++)
/*  57 */         user_Map.put(((UserBean)user_list.get(i)).getUser_id(), 
/*  58 */           (UserBean)user_list
/*  58 */           .get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadUser()
/*     */   {
/*  71 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.user.UserManager");
/*     */   }
/*     */ 
/*     */   public static Map<Integer, String> getUserDeptList(List<UserRegisterBean> paraList)
/*     */   {
/*  83 */     Map retMap = new HashMap();
/*  84 */     for (UserRegisterBean u : paraList)
/*     */     {
/*  86 */       UserBean ub = getUserBeanByID(u.getUser_id());
/*  87 */       if (ub != null)
/*     */       {
/*  89 */         DeptBean dbean = 
/*  90 */           DeptManager.getDeptBeanByID(String.valueOf(ub.getDept_id()));
/*  91 */         retMap.put(Integer.valueOf(u.getUser_id()), dbean.getDept_name());
/*     */       }
/*     */       else
/*     */       {
/*  95 */         retMap.put(Integer.valueOf(u.getUser_id()), "");
/*     */       }
/*     */     }
/*  98 */     return retMap;
/*     */   }
/*     */ 
/*     */   public static List getUserList()
/*     */   {
/* 111 */     List user_list = new ArrayList();
/*     */ 
/* 113 */     Iterator iter = user_Map.entrySet().iterator();
/* 114 */     while (iter.hasNext()) {
/* 115 */       Entry entry = (Entry)iter.next();
/* 116 */       user_list.add((UserBean)user_Map.get((String)entry.getKey()));
/*     */     }
/* 118 */     return user_list;
/*     */   }
/*     */ 
/*     */   public static Map<String, UserBean> getUserMap()
/*     */   {
/* 130 */     return user_Map;
/*     */   }
/*     */ 
/*     */   public static Map<String, SMUserBean> getSimpleUserMap()
/*     */   {
/* 142 */     Map s_map = new HashMap();
/* 143 */     Set set = user_Map.keySet();
/* 144 */     for (String i : set) {
/* 145 */       SMUserBean sub = new SMUserBean();
/* 146 */       UserBean u = (UserBean)user_Map.get(i);
/* 147 */       DeptBean db = DeptManager.getDeptBeanByID(u.getDept_id());
/* 148 */       if (db != null)
/*     */       {
/* 150 */         sub.setDept_id(u.getDept_id());
/* 151 */         sub.setUser_id(u.getUser_id());
/* 152 */         sub.setUser_realname(u.getUser_realname());
/* 153 */         sub.setDept_treeposition(db.getTree_position());
/* 154 */         s_map.put(i, sub);
/*     */       }
/*     */     }
/* 157 */     return s_map;
/*     */   }
/*     */ 
/*     */   public static boolean saveUserSort(String user_ids, SettingLogsBean stl)
/*     */   {
/* 170 */     if ((user_ids != null) && (!"".equals(user_ids)))
/*     */     {
/* 172 */       if (userDao.saveUserSort(user_ids, stl))
/*     */       {
/* 174 */         reloadUser();
/* 175 */         return true;
/*     */       }
/* 177 */       return false;
/*     */     }
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/* 189 */     return userDao.getUserListByDeptIDForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static String getUserCountByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/* 199 */     return userDao.getUserCountByDeptIDForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static String getUserCountByDeptID(String dept_id)
/*     */   {
/* 212 */     dept_id = "," + dept_id + ",";
/* 213 */     int count = 0;
/* 214 */     Iterator iter = user_Map.entrySet().iterator();
/* 215 */     while (iter.hasNext()) {
/* 216 */       Entry entry = (Entry)iter.next();
/* 217 */       UserBean ub = (UserBean)user_Map.get((String)entry.getKey());
/* 218 */       if (dept_id.contains("," + ub.getDept_id() + ","))
/*     */       {
/* 220 */         count++;
/*     */       }
/*     */     }
/* 223 */     return count;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByDeptID(String dept_id)
/*     */   {
/* 240 */     List user_list = new ArrayList();
/* 241 */     dept_id = "," + dept_id + ",";
/* 242 */     Iterator iter = user_Map.entrySet().iterator();
/* 243 */     while (iter.hasNext()) {
/* 244 */       Entry entry = (Entry)iter.next();
/* 245 */       UserBean ub = (UserBean)user_Map.get((String)entry.getKey());
/*     */ 
/* 247 */       if (dept_id.contains("," + ub.getDept_id() + ","))
/*     */       {
/* 249 */         user_list.add(ub);
/*     */       }
/*     */     }
/* 252 */     return user_list;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListForPublishByDeptID(String dept_id)
/*     */   {
/* 269 */     List user_list = new ArrayList();
/* 270 */     dept_id = "," + dept_id + ",";
/* 271 */     Iterator iter = user_Map.entrySet().iterator();
/* 272 */     while (iter.hasNext()) {
/* 273 */       Entry entry = (Entry)iter.next();
/* 274 */       UserBean ub = (UserBean)user_Map.get((String)entry.getKey());
/*     */ 
/* 276 */       if ((ub.getIs_publish() == 1) && (dept_id.contains("," + ub.getDept_id() + ",")))
/*     */       {
/* 278 */         user_list.add(ub);
/*     */       }
/*     */     }
/* 281 */     Collections.sort(user_list, new UserManager.UserComparator());
/* 282 */     return user_list;
/*     */   }
/*     */ 
/*     */   public static String getUserIDSByDeptID(String dept_id)
/*     */   {
/* 295 */     String user_ids = "";
/* 296 */     dept_id = "," + dept_id + ",";
/* 297 */     Iterator iter = user_Map.entrySet().iterator();
/* 298 */     while (iter.hasNext()) {
/* 299 */       Entry entry = (Entry)iter.next();
/* 300 */       UserBean ub = (UserBean)user_Map.get((String)entry.getKey());
/*     */ 
/* 302 */       if (dept_id.contains("," + ub.getDept_id() + ","))
/*     */       {
/* 304 */         user_ids = user_ids + "," + ub.getUser_id();
/*     */       }
/*     */     }
/* 307 */     if ((user_ids != null) && (!"".equals(user_ids)))
/* 308 */       user_ids = user_ids.substring(1);
/* 309 */     return user_ids;
/*     */   }
/*     */ 
/*     */   public static String getDeptIDByUserID(String user_id)
/*     */   {
/* 321 */     UserBean ub = getUserBeanByID(user_id);
/* 322 */     if (ub != null)
/*     */     {
/* 324 */       return ub.getDept_id();
/*     */     }
/*     */ 
/* 327 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getDeptByUserManager(String user_id)
/*     */   {
/* 339 */     return getDeptByUserManager(user_id);
/*     */   }
/*     */ 
/*     */   public static UserBean getAllUserInfoByID(String id)
/*     */   {
/* 348 */     return userDao.getUserBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static UserBean getUserBeanByID(String id)
/*     */   {
/* 358 */     if ((id == null) || ("".equals(id))) {
/* 359 */       return null;
/*     */     }
/* 361 */     if (user_Map.containsKey(id)) {
/* 362 */       return (UserBean)user_Map.get(id);
/*     */     }
/* 364 */     UserBean ub = getAllUserInfoByID(id);
/* 365 */     if (ub != null)
/* 366 */       user_Map.put(id, ub);
/* 367 */     return ub;
/*     */   }
/*     */ 
/*     */   public static String getUserRealName(String id)
/*     */   {
/* 378 */     UserBean ub = getUserBeanByID(id);
/* 379 */     if (ub != null) {
/* 380 */       return ub.getUser_realname();
/*     */     }
/* 382 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByUserIDS(String user_ids)
/*     */   {
/* 393 */     if ((user_ids != null) && (!"".equals(user_ids)))
/*     */     {
/* 395 */       List user_list = new ArrayList();
/* 396 */       String[] tempA = user_ids.split(",");
/* 397 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 399 */         UserBean ub = getUserBeanByID(tempA[i]);
/* 400 */         if (ub != null)
/* 401 */           user_list.add(ub);
/*     */       }
/* 403 */       return user_list;
/*     */     }
/*     */ 
/* 406 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertUser(UserBean ub, UserRegisterBean urb, boolean isAddReg, SettingLogsBean stl)
/*     */   {
/* 419 */     if (userDao.insertUser(ub, urb, isAddReg, stl))
/*     */     {
/* 421 */       reloadUser();
/* 422 */       if (isAddReg)
/* 423 */         UserRegisterManager.reloadUserRegister();
/* 424 */       return true;
/*     */     }
/* 426 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateUser(UserBean ub, SettingLogsBean stl)
/*     */   {
/* 436 */     if (userDao.updateUser(ub, stl))
/*     */     {
/* 438 */       reloadUser();
/* 439 */       return true;
/*     */     }
/* 441 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateUserStatus(int user_status, String user_ids, SettingLogsBean stl)
/*     */   {
/* 452 */     if (userDao.updateUserStatus(user_status, user_ids, stl))
/*     */     {
/* 454 */       reloadUser();
/* 455 */       return true;
/*     */     }
/* 457 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveUser(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 467 */     if (userDao.moveUser(m, stl))
/*     */     {
/* 469 */       reloadUser();
/* 470 */       return true;
/*     */     }
/* 472 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteUser(String user_ids, SettingLogsBean stl)
/*     */   {
/* 483 */     if (userDao.deleteUser(user_ids, stl))
/*     */     {
/* 486 */       UserRegisterManager.deleteRegisterByUserID(user_ids, stl);
/* 487 */       deleteOtherRelaUser(user_ids);
/* 488 */       reloadUser();
/* 489 */       return true;
/*     */     }
/* 491 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteUserByDeptID(String dept_ids, SettingLogsBean stl)
/*     */   {
/* 502 */     String user_ids = getUserIDSByDeptID(dept_ids);
/* 503 */     if (userDao.deleteUserByDeptID(dept_ids, stl))
/*     */     {
/* 505 */       reloadUser();
/*     */ 
/* 507 */       UserRegisterManager.deleteRegisterByDeptID(dept_ids, stl);
/*     */ 
/* 509 */       deleteOtherRelaUser(user_ids);
/* 510 */       return true;
/*     */     }
/* 512 */     return false;
/*     */   }
/*     */ 
/*     */   public static void deleteOtherRelaUser(String user_id)
/*     */   {
/* 523 */     GroupManager.deleteGroupUserByUserID(user_id);
/*     */ 
/* 525 */     RoleUserManager.deleteRoleUserByUser(user_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 553 */     System.out.println(getSimpleUserMap());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.user.UserManager
 * JD-Core Version:    0.6.2
 */