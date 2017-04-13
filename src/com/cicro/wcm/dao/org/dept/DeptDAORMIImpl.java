/*     */ package com.cicro.wcm.dao.org.dept;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptLevelBean;
/*     */ import com.cicro.wcm.dao.org.rmi.GetOrgRmi;
/*     */ import com.cicro.wcm.rmi.IOrgRmi;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DeptDAORMIImpl
/*     */   implements IDeptDAO
/*     */ {
/*     */   public List getAllDeptList()
/*     */   {
/*     */     try
/*     */     {
/*  23 */       return GetOrgRmi.getorgRmi().getAllDeptList();
/*     */     }
/*     */     catch (RemoteException re) {
/*  26 */       re.printStackTrace();
/*  27 */     }return null;
/*     */   }
/*     */ 
/*     */   public int getDeptID()
/*     */   {
/*     */     try
/*     */     {
/*  38 */       return GetOrgRmi.getorgRmi().getDeptID();
/*     */     }
/*     */     catch (RemoteException re) {
/*  41 */       re.printStackTrace();
/*  42 */     }return 0;
/*     */   }
/*     */ 
/*     */   public List<DeptBean> getChildDeptListForDB(Map<String, String> con_m)
/*     */   {
/*     */     try
/*     */     {
/*  56 */       return GetOrgRmi.getorgRmi().getChildDeptListForDB(con_m);
/*     */     }
/*     */     catch (RemoteException re) {
/*  59 */       re.printStackTrace();
/*  60 */     }return null;
/*     */   }
/*     */ 
/*     */   public DeptBean getDeptBeanByID(String id)
/*     */   {
/*     */     try
/*     */     {
/*  72 */       return GetOrgRmi.getorgRmi().getDeptBeanByID(id);
/*     */     }
/*     */     catch (RemoteException re) {
/*  75 */       re.printStackTrace();
/*  76 */     }return null;
/*     */   }
/*     */ 
/*     */   public boolean insertDept(DeptBean db, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  88 */       return GetOrgRmi.getorgRmi().insertDept(db, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/*  91 */       re.printStackTrace();
/*  92 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateDept(DeptBean db, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 104 */       return GetOrgRmi.getorgRmi().updateDept(db, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 107 */       re.printStackTrace();
/* 108 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean moveDept(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 121 */       return GetOrgRmi.getorgRmi().moveDept(m, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 124 */       re.printStackTrace();
/* 125 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteDept(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 137 */       return GetOrgRmi.getorgRmi().deleteDept(ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 140 */       re.printStackTrace();
/* 141 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean saveDeptSort(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 155 */       return GetOrgRmi.getorgRmi().saveDeptSort(ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 158 */       re.printStackTrace();
/* 159 */     }return false;
/*     */   }
/*     */ 
/*     */   public List getAllDeptManagerList()
/*     */   {
/*     */     try
/*     */     {
/* 174 */       return GetOrgRmi.getorgRmi().getAllDeptManagerList();
/*     */     }
/*     */     catch (RemoteException re) {
/* 177 */       re.printStackTrace();
/* 178 */     }return null;
/*     */   }
/*     */ 
/*     */   public boolean insertDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 194 */       return GetOrgRmi.getorgRmi().insertDetpManager(user_ids, dept_id, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 197 */       re.printStackTrace();
/* 198 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 214 */       return GetOrgRmi.getorgRmi().updateDetpManager(user_ids, dept_id, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 217 */       re.printStackTrace();
/* 218 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteDeptManager(String user_ids, String dept_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 231 */       return GetOrgRmi.getorgRmi().deleteDeptManager(user_ids, dept_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 234 */       re.printStackTrace();
/* 235 */     }return false;
/*     */   }
/*     */ 
/*     */   public List getAllDeptLevelList()
/*     */   {
/*     */     try
/*     */     {
/* 251 */       return GetOrgRmi.getorgRmi().getAllDeptLevelList();
/*     */     }
/*     */     catch (RemoteException re) {
/* 254 */       re.printStackTrace();
/* 255 */     }return null;
/*     */   }
/*     */ 
/*     */   public DeptLevelBean getDeptLevelBeanByID(String id)
/*     */   {
/*     */     try
/*     */     {
/* 267 */       return GetOrgRmi.getorgRmi().getDeptLevelBeanByID(id);
/*     */     }
/*     */     catch (RemoteException re) {
/* 270 */       re.printStackTrace();
/* 271 */     }return null;
/*     */   }
/*     */ 
/*     */   public boolean insertDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 283 */       return GetOrgRmi.getorgRmi().insertDeptLevel(dlb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 286 */       re.printStackTrace();
/* 287 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 299 */       return GetOrgRmi.getorgRmi().updateDeptLevel(dlb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 302 */       re.printStackTrace();
/* 303 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteDeptLevel(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 315 */       return GetOrgRmi.getorgRmi().deleteDeptLevel(ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 318 */       re.printStackTrace();
/* 319 */     }return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.dept.DeptDAORMIImpl
 * JD-Core Version:    0.6.2
 */