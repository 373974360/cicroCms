/*     */ package com.cicro.wcm.services.org.dept;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptLevelBean;
/*     */ import com.cicro.wcm.bean.org.user.LoginUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.user.UserLogin;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class DeptRPC
/*     */ {
/*     */   public static String getDeptName(String dept_id)
/*     */   {
/*  19 */     return DeptManager.getDeptName(dept_id);
/*     */   }
/*     */ 
/*     */   public static int getDeptID()
/*     */   {
/*  28 */     return DeptManager.getDeptID();
/*     */   }
/*     */ 
/*     */   public static String getDeptListPage()
/*     */   {
/*  38 */     return DeptManager.getDeptListPage();
/*     */   }
/*     */ 
/*     */   public static String getAllDeptTreeJsonStr()
/*     */   {
/*  50 */     return DeptManager.deptListToJsonStrByUserID(DeptManager.getRootDeptID());
/*     */   }
/*     */ 
/*     */   public static String getDeptTreeByUser(HttpServletRequest request)
/*     */   {
/*  62 */     LoginUserBean lub = UserLogin.getUserBySession(request);
/*  63 */     return DeptManager.getDeptTreeByUser(lub.getUser_id());
/*     */   }
/*     */ 
/*     */   public static DeptBean getDeptBeanByID(String id)
/*     */   {
/*  74 */     return DeptManager.getDeptBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getChildDeptListByID(String dept_id)
/*     */   {
/*  85 */     return DeptManager.getChildDeptListByID(dept_id);
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getChildDeptListForDB(Map<String, String> con_m)
/*     */   {
/*  96 */     return DeptManager.getChildDeptListForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static boolean insertDept(DeptBean db, HttpServletRequest request)
/*     */   {
/* 108 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 109 */     if (stl != null)
/*     */     {
/* 111 */       return DeptManager.insertDept(db, stl);
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDept(DeptBean db, HttpServletRequest request)
/*     */   {
/* 125 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 126 */     if (stl != null)
/*     */     {
/* 128 */       return DeptManager.updateDept(db, stl);
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveDept(String parent_id, String dept_ids, HttpServletRequest request)
/*     */   {
/* 140 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 141 */     if (stl != null)
/*     */     {
/* 143 */       return DeptManager.moveDept(parent_id, dept_ids, stl);
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveDeptSort(String dept_ids, HttpServletRequest request)
/*     */   {
/* 158 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 159 */     if (stl != null)
/*     */     {
/* 161 */       return DeptManager.saveDeptSort(dept_ids, stl);
/*     */     }
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDept(String ids, HttpServletRequest request)
/*     */   {
/* 178 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 179 */     if (stl != null)
/*     */     {
/* 181 */       return DeptManager.deleteDept(ids, stl);
/*     */     }
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getDeptManagerUserList(String dept_id)
/*     */   {
/* 194 */     return DeptManUser.getDeptManagerUserList(dept_id);
/*     */   }
/*     */ 
/*     */   public static String getManagerIDSByDeptID(String dept_id)
/*     */   {
/* 205 */     return DeptManUser.getManagerIDSByDeptID(dept_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDetpManager(String user_ids, String dept_id, HttpServletRequest request)
/*     */   {
/* 219 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 220 */     if (stl != null)
/*     */     {
/* 222 */       return DeptManUser.insertDetpManager(user_ids, dept_id, stl);
/*     */     }
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDetpManager(String user_ids, String old_dept_manager, String dept_id, HttpServletRequest request)
/*     */   {
/* 240 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 241 */     if (stl != null)
/*     */     {
/* 243 */       return DeptManUser.updateDetpManager(user_ids, old_dept_manager, dept_id, stl);
/*     */     }
/* 245 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDeptManager(String user_ids, String dept_id, HttpServletRequest request)
/*     */   {
/* 258 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 259 */     if (stl != null)
/*     */     {
/* 261 */       return DeptManUser.deleteDeptManager(user_ids, dept_id, stl);
/*     */     }
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<DeptLevelBean> getDeptLevelList()
/*     */   {
/* 275 */     return DeptLevelManager.getDeptLevelList();
/*     */   }
/*     */ 
/*     */   public static DeptLevelBean getDeptLevelBeanByID(String id)
/*     */   {
/* 287 */     return DeptLevelManager.getDeptLevelBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDeptLevel(DeptLevelBean dlb, HttpServletRequest request)
/*     */   {
/* 299 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 300 */     if (stl != null)
/*     */     {
/* 302 */       return DeptLevelManager.insertDeptLevel(dlb, stl);
/*     */     }
/*     */ 
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDeptLevel(DeptLevelBean dlb, HttpServletRequest request)
/*     */   {
/* 318 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 319 */     if (stl != null)
/*     */     {
/* 321 */       return DeptLevelManager.updateDeptLevel(dlb, stl);
/*     */     }
/*     */ 
/* 325 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDeptLevel(String ids, HttpServletRequest request)
/*     */   {
/* 341 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 342 */     if (stl != null)
/*     */     {
/* 344 */       return DeptLevelManager.deleteVoidDeptLevel(ids, stl);
/*     */     }
/*     */ 
/* 348 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.dept.DeptRPC
 * JD-Core Version:    0.6.2
 */