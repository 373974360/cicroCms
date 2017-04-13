/*     */ package com.cicro.wcm.services.org.user;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.desktop.DeskTopBean;
/*     */ import com.cicro.wcm.bean.org.user.SMUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserLevelBean;
/*     */ import com.cicro.wcm.bean.org.user.UserRegisterBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.server.LicenseCheck;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.desktop.DesktopManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class UserManRPC
/*     */ {
/*     */   public static String getAppForLicense()
/*     */   {
/*  30 */     return LicenseCheck.getAppForLicense();
/*     */   }
/*     */ 
/*     */   public static String getAppJSONStrByUserID(String user_id)
/*     */   {
/*  41 */     return UserOptManager.getAppJSONStrByUserID(user_id);
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByDeptID(String dept_id)
/*     */   {
/*  57 */     return UserManager.getUserListByDeptID(dept_id);
/*     */   }
/*     */ 
/*     */   public static boolean checkUserLogin2(String user_name, String pass_word)
/*     */   {
/*  69 */     return UserRegisterManager.checkUserLogin2(user_name, pass_word);
/*     */   }
/*     */ 
/*     */   public static boolean updatePasswordByUserID(int user_id, String password, HttpServletRequest request)
/*     */   {
/*  81 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  82 */     if (stl != null)
/*     */     {
/*  84 */       return UserRegisterManager.updatePasswordByUserID(user_id, password, stl);
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   public static Map<String, SMUserBean> getSimpleUserMap()
/*     */   {
/*  97 */     return UserManager.getSimpleUserMap();
/*     */   }
/*     */ 
/*     */   public static String getUserRealName(String id)
/*     */   {
/* 107 */     return UserManager.getUserRealName(id);
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/* 117 */     return UserManager.getUserListByDeptIDForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static Map<String, UserBean> getUserMap()
/*     */   {
/* 129 */     return UserManager.getUserMap();
/*     */   }
/*     */ 
/*     */   public static Map<Integer, String> getUserDeptList(List<UserRegisterBean> paraList)
/*     */   {
/* 141 */     return UserManager.getUserDeptList(paraList);
/*     */   }
/*     */ 
/*     */   public static String getUserCountByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/* 151 */     return UserManager.getUserCountByDeptIDForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static String getUserCountByDeptID(String dept_id)
/*     */   {
/* 163 */     return UserManager.getUserCountByDeptID(dept_id);
/*     */   }
/*     */ 
/*     */   public static UserBean getAllUserInfoByID(String user_id)
/*     */   {
/* 172 */     return UserManager.getAllUserInfoByID(user_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertUser(UserBean ub, UserRegisterBean urb, boolean isAddReg, HttpServletRequest request)
/*     */   {
/* 185 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 186 */     if (stl != null)
/*     */     {
/* 188 */       return UserManager.insertUser(ub, urb, isAddReg, stl);
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateUser(UserBean ub, HttpServletRequest request)
/*     */   {
/* 200 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 201 */     if (stl != null)
/*     */     {
/* 203 */       return UserManager.updateUser(ub, stl);
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveUser(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 216 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 217 */     if (stl != null)
/*     */     {
/* 219 */       return UserManager.moveUser(m, stl);
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateUserStatus(int user_status, String user_ids, HttpServletRequest request)
/*     */   {
/* 232 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 233 */     if (stl != null)
/*     */     {
/* 235 */       return UserManager.updateUserStatus(user_status, user_ids, stl);
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteUser(String user_ids, HttpServletRequest request)
/*     */   {
/* 248 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 249 */     if (stl != null)
/*     */     {
/* 251 */       return UserManager.deleteUser(user_ids, stl);
/*     */     }
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<UserRegisterBean> getUserRegisterListByUserID(int user_id)
/*     */   {
/* 265 */     return UserRegisterManager.getUserRegisterListByUserID(user_id);
/*     */   }
/*     */ 
/*     */   public static boolean registerISExist(String user_name, int reg_id)
/*     */   {
/* 277 */     return UserRegisterManager.registerISExist(user_name, reg_id);
/*     */   }
/*     */ 
/*     */   public static boolean saveUserSort(String user_ids, HttpServletRequest request)
/*     */   {
/* 290 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 291 */     if (stl != null)
/*     */     {
/* 293 */       return UserManager.saveUserSort(user_ids, stl);
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getUserRegisterCount(Map<String, String> con_m)
/*     */   {
/* 305 */     return UserRegisterManager.getUserRegisterCount(con_m);
/*     */   }
/*     */ 
/*     */   public List<UserRegisterBean> getAllUserRegisterForDB(Map<String, String> con_m)
/*     */   {
/* 315 */     return UserRegisterManager.getAllUserRegisterForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static boolean insertRegister(UserRegisterBean urb, HttpServletRequest request)
/*     */   {
/* 325 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 326 */     if (stl != null)
/*     */     {
/* 328 */       return UserRegisterManager.insertRegister(urb, stl);
/*     */     }
/* 330 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateRegister(UserRegisterBean urb, HttpServletRequest request)
/*     */   {
/* 340 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 341 */     if (stl != null)
/*     */     {
/* 343 */       return UserRegisterManager.updateRegister(urb, stl);
/*     */     }
/* 345 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateRegisterStatus(int reg_status, String reg_ids, HttpServletRequest request)
/*     */   {
/* 355 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 356 */     if (stl != null)
/*     */     {
/* 358 */       return UserRegisterManager.updateRegisterStatus(reg_status, reg_ids, stl);
/*     */     }
/* 360 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRegisterByRID(String reg_ids, HttpServletRequest request)
/*     */   {
/* 370 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 371 */     if (stl != null)
/*     */     {
/* 373 */       return UserRegisterManager.deleteRegisterByRID(reg_ids, stl);
/*     */     }
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<UserLevelBean> getUserLevelList()
/*     */   {
/* 385 */     return UserLevelManager.getUserLevelList();
/*     */   }
/*     */ 
/*     */   public static String getUserLevelCount()
/*     */   {
/* 395 */     return UserLevelManager.getUserLevelCount();
/*     */   }
/*     */ 
/*     */   public static boolean insertUserLevel(UserLevelBean ulb, HttpServletRequest request)
/*     */   {
/* 406 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 407 */     if (stl != null)
/*     */     {
/* 409 */       return UserLevelManager.insertUserLevel(ulb, stl);
/*     */     }
/*     */ 
/* 413 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateUserLevel(UserLevelBean ulb, HttpServletRequest request)
/*     */   {
/* 427 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 428 */     if (stl != null)
/*     */     {
/* 430 */       return UserLevelManager.updateUserLevel(ulb, stl);
/*     */     }
/*     */ 
/* 434 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteUserLevel(String ids, HttpServletRequest request)
/*     */   {
/* 450 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 451 */     if (stl != null)
/*     */     {
/* 453 */       return UserLevelManager.deleteVoidUserLevel(ids, stl);
/*     */     }
/*     */ 
/* 457 */     return false;
/*     */   }
/*     */ 
/*     */   public static UserLevelBean getUserLevelBeanByID(String id)
/*     */   {
/* 470 */     return UserLevelManager.getUserLevelBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean getUserLevelCountByLevel(int id, int level_value)
/*     */   {
/* 481 */     return UserLevelManager.getUserLevelCountByLevel(id, level_value);
/*     */   }
/*     */ 
/*     */   public static List<DeskTopBean> getUserDesktop(int user_id)
/*     */   {
/* 492 */     return DesktopManager.getUserDesktop(user_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertUserDesktop(String user_id, List<DeskTopBean> l)
/*     */   {
/* 503 */     return DesktopManager.insertUserDesktop(user_id, l);
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getAllUserSiteObjList(String user_id, String app_id)
/*     */   {
/* 514 */     return UserLogin.getAllUserSiteObjList(user_id, app_id);
/*     */   }
/*     */ 
/*     */   public static List<GKNodeBean> getAllUserGKNodeObjList(String user_id, String app_id)
/*     */   {
/* 525 */     return UserLogin.getAllUserGKNodeObjList(user_id, app_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 533 */     System.out.println(getUserDesktop(1));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.user.UserManRPC
 * JD-Core Version:    0.6.2
 */