/*     */ package com.cicro.wcm.services.appeal.cpUser;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CPUserReleInfo;
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CpUserBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserRegisterBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.appeal.cpUser.CpUserDAO;
/*     */ import com.cicro.wcm.services.appeal.cpDept.CpDeptManager;
/*     */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*     */ import com.cicro.wcm.services.org.role.RoleManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import com.cicro.wcm.services.org.user.UserRegisterManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CpUserManager
/*     */   implements ISyncCatch
/*     */ {
/*  24 */   private static List<CpUserBean> user_list = new ArrayList();
/*     */ 
/*     */   static {
/*  27 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  37 */     user_list.clear();
/*  38 */     user_list = CpUserDAO.getAllCpUserList();
/*     */   }
/*     */ 
/*     */   public static void reloadCpUserDept()
/*     */   {
/*  43 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.cpUser.CpUserManager");
/*     */   }
/*     */ 
/*     */   public static String userISExist(String user_ids)
/*     */   {
/*  53 */     String names = "";
/*  54 */     user_ids = "," + user_ids + ",";
/*  55 */     if ((user_list != null) && (user_list.size() > 0))
/*     */     {
/*  57 */       for (int i = 0; i < user_list.size(); i++)
/*     */       {
/*  59 */         if (user_ids.contains("," + ((CpUserBean)user_list.get(i)).getUser_id() + ","))
/*     */         {
/*  61 */           names = names + "," + UserManager.getUserBeanByID(new StringBuilder(String.valueOf(((CpUserBean)user_list.get(i)).getUser_id())).toString()).getUser_realname();
/*     */         }
/*     */       }
/*  64 */       if ((names != null) && (!"".equals(names.trim())))
/*  65 */         names = names.substring(1);
/*     */     }
/*  67 */     return names;
/*     */   }
/*     */ 
/*     */   public static Map<Integer, CPUserReleInfo> getAllReleUserMap()
/*     */   {
/*  77 */     Map m = new HashMap();
/*  78 */     if ((user_list != null) && (user_list.size() > 0))
/*     */     {
/*  80 */       for (int i = 0; i < user_list.size(); i++) {
/*     */         try
/*     */         {
/*  83 */           UserBean ub = UserManager.getUserBeanByID(((CpUserBean)user_list.get(i)).getUser_id());
/*  84 */           if (ub != null)
/*     */           {
/*  86 */             CPUserReleInfo curi = new CPUserReleInfo();
/*  87 */             curi.setUser_id(ub.getUser_id());
/*  88 */             curi.setUser_realname(ub.getUser_realname());
/*  89 */             curi.setDept_id(((CpUserBean)user_list.get(i)).getDept_id());
/*  90 */             curi.setDept_treeposition(CpDeptManager.getCpDeptBean(curi.getDept_id()).getDept_position());
/*     */ 
/*  92 */             m.put(Integer.valueOf(ub.getUser_id()), curi);
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/*  96 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 100 */     return m;
/*     */   }
/*     */ 
/*     */   public static List<CPUserReleInfo> getUserListByDept(int dept_id)
/*     */   {
/* 109 */     List u_list = new ArrayList();
/* 110 */     if ((user_list != null) && (user_list.size() > 0))
/*     */     {
/* 112 */       for (int i = 0; i < user_list.size(); i++)
/*     */       {
/* 114 */         if (((CpUserBean)user_list.get(i)).getDept_id() == dept_id)
/*     */         {
/* 116 */           UserBean ub = UserManager.getUserBeanByID(((CpUserBean)user_list.get(i)).getUser_id());
/* 117 */           CPUserReleInfo curi = new CPUserReleInfo();
/* 118 */           String model_ids = "";
/* 119 */           String role_ids = "";
/* 120 */           if (ub != null)
/*     */           {
/* 122 */             curi.setUser_id(((CpUserBean)user_list.get(i)).getUser_id());
/* 123 */             curi.setUser_realname(ub.getUser_realname());
/* 124 */             UserRegisterBean urb = UserRegisterManager.getUserRegisterBeanByUserId(ub.getUser_id());
/* 125 */             if (urb != null)
/*     */             {
/* 127 */               curi.setAccnumber(urb.getUsername());
/*     */             }
/* 129 */             model_ids = ModelManager.getModelIDSByUserID(((CpUserBean)user_list.get(i)).getUser_id());
/* 130 */             curi.setModel_ids(model_ids);
/* 131 */             curi.setModel_names(ModelManager.getModelNamebyIDS(model_ids));
/* 132 */             role_ids = RoleUserManager.getRoleIDSByUserAPP(((CpUserBean)user_list.get(i)).getUser_id(), "appeal", "");
/* 133 */             curi.setRole_ids(role_ids);
/* 134 */             curi.setRole_names(RoleManager.getRoleNamesbyRoleIDS(role_ids));
/* 135 */             u_list.add(curi);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 140 */     return u_list;
/*     */   }
/*     */ 
/*     */   public static String getUserIdsByDeptID(String dept_ids)
/*     */   {
/* 150 */     String user_ids = "";
/* 151 */     dept_ids = "," + dept_ids + ",";
/* 152 */     if ((user_list != null) && (user_list.size() > 0))
/*     */     {
/* 154 */       for (int i = 0; i < user_list.size(); i++)
/*     */       {
/* 156 */         if (dept_ids.contains("," + ((CpUserBean)user_list.get(i)).getDept_id() + ","))
/*     */         {
/* 158 */           user_ids = user_ids + "," + ((CpUserBean)user_list.get(i)).getUser_id();
/*     */         }
/*     */       }
/*     */     }
/* 162 */     if ((user_ids != null) && (!"".equals(user_ids))) {
/* 163 */       user_ids = user_ids.substring(1);
/*     */     }
/* 165 */     return user_ids;
/*     */   }
/*     */ 
/*     */   public static int getSQDeptIDbyUserID(int user_id)
/*     */   {
/* 175 */     int dept_id = 0;
/* 176 */     if ((user_list != null) && (user_list.size() > 0))
/*     */     {
/* 178 */       for (int i = 0; i < user_list.size(); i++)
/*     */       {
/* 180 */         if (((CpUserBean)user_list.get(i)).getUser_id() == user_id)
/* 181 */           dept_id = ((CpUserBean)user_list.get(i)).getDept_id();
/*     */       }
/*     */     }
/* 184 */     return dept_id;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getBrotherDeptListByUserID(int user_id)
/*     */   {
/* 194 */     CpDeptBean deptB = CpDeptManager.getCpDeptBean(getSQDeptIDbyUserID(user_id));
/* 195 */     if (deptB != null)
/*     */     {
/* 197 */       return CpDeptManager.getChildDeptList(deptB.getParent_id());
/*     */     }
/*     */ 
/* 200 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getChildDeptListByUserID(int user_id)
/*     */   {
/* 210 */     return CpDeptManager.getChildDeptList(getSQDeptIDbyUserID(user_id));
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getParentDeptListByUserID(int user_id)
/*     */   {
/* 220 */     List l = new ArrayList();
/* 221 */     CpDeptBean cdb = CpDeptManager.getCpDeptBean(getSQDeptIDbyUserID(user_id));
/* 222 */     if (cdb != null)
/* 223 */       l.add(CpDeptManager.getCpDeptBean(cdb.getParent_id()));
/* 224 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean insertCpUser(int dept_id, String user_ids, SettingLogsBean stl)
/*     */   {
/* 233 */     if (CpUserDAO.insertCpUser(dept_id, user_ids, stl))
/*     */     {
/* 235 */       reloadCpUserDept();
/* 236 */       return true;
/*     */     }
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpUser(String dept_id, String userIds, SettingLogsBean stl)
/*     */   {
/* 247 */     if (CpUserDAO.deleteCpUser(dept_id, userIds, stl))
/*     */     {
/* 249 */       reloadCpUserDept();
/*     */ 
/* 251 */       ModelManager.deleteModelReleUser(userIds);
/*     */ 
/* 253 */       RoleUserManager.deleteRoleUserByUserRoleSite(userIds, "appeal", "");
/* 254 */       return true;
/*     */     }
/* 256 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getParenAndBrotherDeptListByUserID(int user_id)
/*     */   {
/* 267 */     List l = new ArrayList();
/* 268 */     CpDeptBean cdb = CpDeptManager.getCpDeptBean(getSQDeptIDbyUserID(user_id));
/* 269 */     CpDeptBean p_cdb = CpDeptManager.getCpDeptBean(cdb.getParent_id());
/*     */ 
/* 271 */     if (CpDeptManager.getCpDeptBean(p_cdb.getParent_id()) != null)
/*     */     {
/* 273 */       return CpDeptManager.getChildDeptList(p_cdb.getParent_id());
/*     */     }
/*     */ 
/* 276 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 281 */     System.out.println(getAllReleUserMap());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpUser.CpUserManager
 * JD-Core Version:    0.6.2
 */