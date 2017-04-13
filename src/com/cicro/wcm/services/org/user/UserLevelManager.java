/*     */ package com.cicro.wcm.services.org.user;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserLevelBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.OrgDAOFactory;
/*     */ import com.cicro.wcm.dao.org.user.IUserDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class UserLevelManager
/*     */   implements ISyncCatch
/*     */ {
/*  30 */   private static Map<String, UserLevelBean> userLevel_Map = new HashMap();
/*     */ 
/*  32 */   private static IUserDAO userDao = OrgDAOFactory.getUserDao();
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
/*  45 */     List userLevel_list = userDao.getAllUserLevelList();
/*     */ 
/*  47 */     userLevel_Map.clear();
/*  48 */     if ((userLevel_list != null) && (userLevel_list.size() > 0))
/*  49 */       for (int i = 0; i < userLevel_list.size(); i++)
/*  50 */         userLevel_Map.put(((UserLevelBean)userLevel_list.get(i)).getUserlevel_id(), 
/*  51 */           (UserLevelBean)userLevel_list
/*  51 */           .get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadUserLevel()
/*     */   {
/*  64 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.user.UserLevelManager");
/*     */   }
/*     */ 
/*     */   public static String getUserLevelCount()
/*     */   {
/*  76 */     return userLevel_Map.size();
/*     */   }
/*     */ 
/*     */   public static List<UserLevelBean> getUserLevelList()
/*     */   {
/*  89 */     List userLevel_list = new ArrayList();
/*     */ 
/*  92 */     TreeMap temp_dept_map = new TreeMap();
/*     */ 
/*  94 */     Iterator iter = userLevel_Map.entrySet().iterator();
/*  95 */     while (iter.hasNext()) {
/*  96 */       Entry entry = (Entry)iter.next();
/*  97 */       UserLevelBean ulb = (UserLevelBean)userLevel_Map.get((String)entry.getKey());
/*  98 */       temp_dept_map.put(Integer.valueOf(ulb.getUserlevel_value()), ulb);
/*     */     }
/*     */ 
/* 101 */     Iterator iter2 = temp_dept_map.entrySet().iterator();
/* 102 */     while (iter2.hasNext()) {
/* 103 */       Entry entry = (Entry)iter2.next();
/* 104 */       userLevel_list.add((UserLevelBean)entry.getValue());
/*     */     }
/* 106 */     return userLevel_list;
/*     */   }
/*     */ 
/*     */   public static Map<String, UserLevelBean> getUserMap()
/*     */   {
/* 118 */     return userLevel_Map;
/*     */   }
/*     */ 
/*     */   public static UserLevelBean getUserLevelBeanByID(String id)
/*     */   {
/* 127 */     if (userLevel_Map.containsKey(id)) {
/* 128 */       return (UserLevelBean)userLevel_Map.get(id);
/*     */     }
/* 130 */     UserLevelBean ulb = userDao.getUserLevelBeanByID(id);
/* 131 */     if (ulb != null)
/* 132 */       userLevel_Map.put(id, ulb);
/* 133 */     return ulb;
/*     */   }
/*     */ 
/*     */   public static boolean insertUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */   {
/* 145 */     if (getUserLevelCountByLevel(0, ulb.getUserlevel_value()))
/*     */     {
/* 147 */       System.out.println("insertUserLevel : this userLevel value is exist");
/* 148 */       return false;
/*     */     }
/* 150 */     if (userDao.insertUserLevel(ulb, stl)) {
/* 151 */       reloadUserLevel();
/* 152 */       return true;
/*     */     }
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */   {
/* 167 */     if (getUserLevelCountByLevel(ulb.getUserlevel_id(), ulb.getUserlevel_value()))
/*     */     {
/* 169 */       System.out.println("updateUserLevel : this userLevel value is exist");
/* 170 */       return false;
/*     */     }
/*     */ 
/* 174 */     if (userDao.updateUserLevel(ulb, stl)) {
/* 175 */       reloadUserLevel();
/* 176 */       return true;
/*     */     }
/* 178 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteUserLevel(String ids, SettingLogsBean stl)
/*     */   {
/* 191 */     if (userDao.deleteUserLevel(ids, stl))
/*     */     {
/* 193 */       reloadUserLevel();
/* 194 */       return true;
/*     */     }
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteVoidUserLevel(String ids, SettingLogsBean stl)
/*     */   {
/* 223 */     return deleteUserLevel(ids, stl);
/*     */   }
/*     */ 
/*     */   public static int isExistUser(String id)
/*     */   {
/* 235 */     String value = ((UserLevelBean)userLevel_Map.get(id)).getUserlevel_value();
/* 236 */     int cnt = 0;
/* 237 */     Map map = UserManager.getUserMap();
/* 238 */     Iterator it = map.values().iterator();
/* 239 */     while (it.hasNext())
/*     */     {
/* 241 */       UserBean ub = (UserBean)it.next();
/* 242 */       if (value.equals(ub.getUserlevel_value()))
/*     */       {
/* 244 */         cnt++;
/*     */       }
/*     */     }
/* 247 */     return cnt;
/*     */   }
/*     */ 
/*     */   public static boolean getUserLevelCountByLevel(int id, int level_value)
/*     */   {
/* 260 */     Iterator iter = userLevel_Map.entrySet().iterator();
/* 261 */     int count = 0;
/* 262 */     while (iter.hasNext()) {
/* 263 */       Entry entry = (Entry)iter.next();
/* 264 */       UserLevelBean ulb = (UserLevelBean)userLevel_Map.get((String)entry.getKey());
/* 265 */       System.out.println(ulb.getUserlevel_value() + " " + level_value + "  " + id + "  " + ulb.getUserlevel_id());
/* 266 */       if ((ulb.getUserlevel_value() == level_value) && ((id == 0) || (ulb.getUserlevel_id() != id)))
/* 267 */         count++;
/*     */     }
/* 269 */     return count > 0;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 278 */     List l = getUserLevelList();
/* 279 */     for (int i = 0; i < l.size(); i++)
/*     */     {
/* 281 */       System.out.println(((UserLevelBean)l.get(i)).getUserlevel_id());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void insertUserLevelTest()
/*     */   {
/* 287 */     UserLevelBean ulb = new UserLevelBean();
/* 288 */     ulb.setUserlevel_memo("第一级用户");
/* 289 */     ulb.setUserlevel_name("第一级");
/* 290 */     ulb.setUserlevel_value(1);
/* 291 */     insertUserLevel(ulb, new SettingLogsBean());
/*     */   }
/*     */ 
/*     */   public static void updateUserLevelTest()
/*     */   {
/* 296 */     UserLevelBean ulb = new UserLevelBean();
/* 297 */     ulb.setUserlevel_id(9);
/* 298 */     ulb.setUserlevel_memo("第二２级用户");
/* 299 */     ulb.setUserlevel_name("第二２级");
/* 300 */     ulb.setUserlevel_value(1);
/* 301 */     updateUserLevel(ulb, new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.user.UserLevelManager
 * JD-Core Version:    0.6.2
 */