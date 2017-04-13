/*     */ package com.cicro.wcm.services.appeal.cpUser;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CPUserReleInfo;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CpUserRPC
/*     */ {
/*     */   public static String userISExist(String user_ids)
/*     */   {
/*  28 */     return CpUserManager.userISExist(user_ids);
/*     */   }
/*     */ 
/*     */   public static Map<Integer, CPUserReleInfo> getAllReleUserMap()
/*     */   {
/*  38 */     return CpUserManager.getAllReleUserMap();
/*     */   }
/*     */ 
/*     */   public static boolean insertCpUser(int dept_id, String user_ids, HttpServletRequest request)
/*     */   {
/*  47 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  48 */     if (stl != null)
/*     */     {
/*  50 */       return CpUserManager.insertCpUser(dept_id, user_ids, stl);
/*     */     }
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpUser(String dept_id, String userIds, HttpServletRequest request)
/*     */   {
/*  61 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  62 */     if (stl != null)
/*     */     {
/*  64 */       return CpUserManager.deleteCpUser(dept_id, userIds, stl);
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<CPUserReleInfo> getUserListByDept(int dept_id)
/*     */   {
/*  75 */     return CpUserManager.getUserListByDept(dept_id);
/*     */   }
/*     */ 
/*     */   public static int getSQDeptIDbyUserID(int user_id)
/*     */   {
/*  85 */     return CpUserManager.getSQDeptIDbyUserID(user_id);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getBrotherDeptListByUserID(int user_id)
/*     */   {
/*  95 */     return CpUserManager.getBrotherDeptListByUserID(user_id);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getChildDeptListByUserID(int user_id)
/*     */   {
/* 105 */     return CpUserManager.getChildDeptListByUserID(user_id);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getParentDeptListByUserID(int user_id)
/*     */   {
/* 115 */     return CpUserManager.getParentDeptListByUserID(user_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpUser.CpUserRPC
 * JD-Core Version:    0.6.2
 */