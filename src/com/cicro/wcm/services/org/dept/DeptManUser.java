/*     */ package com.cicro.wcm.services.org.dept;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.OrgDAOFactory;
/*     */ import com.cicro.wcm.dao.org.dept.IDeptDAO;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class DeptManUser
/*     */ {
/*  37 */   private static IDeptDAO deptDao = OrgDAOFactory.getDeptDao();
/*     */ 
/*     */   public static List<UserBean> getDeptManagerUserList(String dept_id)
/*     */   {
/*  47 */     String user_ids = getManagerIDSByDeptID(dept_id);
/*  48 */     List user_list = new ArrayList();
/*  49 */     if ((user_ids != null) && (!"".equals(user_ids)))
/*     */     {
/*  51 */       String[] user_a = user_ids.split(",");
/*  52 */       for (int i = 0; i < user_a.length; i++) {
/*     */         try
/*     */         {
/*  55 */           UserBean ub = UserManager.getUserBeanByID(user_a[i]);
/*  56 */           if (ub != null) {
/*  57 */             ub.setDept_name(DeptManager.getDeptBeanByID(ub.getDept_id()).getDept_name());
/*  58 */             user_list.add(ub);
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/*  62 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  67 */     return user_list;
/*     */   }
/*     */ 
/*     */   public static String getManagerIDSByDeptID(String dept_id)
/*     */   {
/*  78 */     return (String)DeptManager.getDeptMUserMap().get(dept_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/*  93 */     if (deptDao.insertDetpManager(user_ids, dept_id, stl))
/*     */     {
/*  95 */       saveDeptManagerRoleUser(user_ids, "", stl);
/*  96 */       DeptManager.reloadDept();
/*  97 */       return true;
/*     */     }
/*     */ 
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDetpManager(String user_ids, String old_dept_manager, String dept_id, SettingLogsBean stl)
/*     */   {
/* 118 */     if (deptDao.updateDetpManager(user_ids, dept_id, stl))
/*     */     {
/* 120 */       DeptManager.reloadDept();
/* 121 */       saveDeptManagerRoleUser(user_ids, old_dept_manager, stl);
/* 122 */       return true;
/*     */     }
/*     */ 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDeptManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/* 139 */     if (deptDao.deleteDeptManager(user_ids, dept_id, stl))
/*     */     {
/* 141 */       DeptManager.reloadDept();
/*     */ 
/* 143 */       deleteDeptManagerRoleUser(user_ids);
/* 144 */       return true;
/*     */     }
/*     */ 
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveDeptManagerRoleUser(String new_user_ids, String old_dept_manager, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 165 */       deleteDeptManagerRoleUser(old_dept_manager);
/*     */ 
/* 167 */       RoleUserBean rub = new RoleUserBean();
/* 168 */       rub.setApp_id(PublicTableDAO.APP_ORG);
/* 169 */       rub.setRole_id(JconfigUtilContainer.systemRole().getProperty("dept_manager", "", "role_id"));
/* 170 */       rub.setUser_id(new_user_ids);
/* 171 */       RoleUserManager.insertRoloUserHandl(rub, stl);
/* 172 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void deleteDeptManagerRoleUser(String user_ids)
/*     */   {
/* 191 */     if ((user_ids != null) && (!"".equals(user_ids)))
/*     */     {
/* 193 */       String u_ids = "";
/* 194 */       String[] tempA = user_ids.split(",");
/* 195 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 197 */         String dept_ids = DeptManager.getDeptByUserManager(tempA[i]);
/* 198 */         if ((dept_ids == null) || ("".equals(dept_ids.trim())))
/* 199 */           u_ids = u_ids + "," + tempA[i];
/*     */       }
/* 201 */       if (!"".equals(u_ids))
/*     */       {
/* 203 */         u_ids = u_ids.replaceAll("^\\D+", "");
/*     */ 
/* 205 */         RoleUserManager.deleteRoleUserByUserAndRoleID(u_ids, JconfigUtilContainer.systemRole().getProperty("dept_manager", "", "role_id"));
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.dept.DeptManUser
 * JD-Core Version:    0.6.2
 */