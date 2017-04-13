/*     */ package com.cicro.wcm.services.Log;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.LoginLogsBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.LoginUserBean;
/*     */ import com.cicro.wcm.dao.logs.CsLogDAO;
/*     */ import com.cicro.wcm.dao.logs.CsLoginLogDAO;
/*     */ import com.cicro.wcm.services.org.user.UserLogin;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class LogManager
/*     */ {
/*     */   public static SettingLogsBean getSettingLogsByRequest(HttpServletRequest request)
/*     */   {
/*  39 */     LoginUserBean lub = UserLogin.getUserBySession(request);
/*  40 */     if (lub == null)
/*     */     {
/*  42 */       return null;
/*     */     }
/*     */ 
/*  45 */     SettingLogsBean slb = new SettingLogsBean();
/*  46 */     slb.setIp(lub.getIp());
/*  47 */     slb.setUser_id(lub.getUser_id());
/*  48 */     slb.setUser_name(lub.getUser_realname());
/*  49 */     return slb;
/*     */   }
/*     */ 
/*     */   public static LoginLogsBean getLoginLogsBean(LoginUserBean lub)
/*     */   {
/*  60 */     LoginLogsBean llb = new LoginLogsBean();
/*  61 */     llb.setIp(lub.getIp());
/*  62 */     llb.setUser_id(lub.getUser_id());
/*  63 */     llb.setUser_name(lub.getUser_realname());
/*     */ 
/*  65 */     return llb;
/*     */   }
/*     */ 
/*     */   public static boolean insertLogoutLog(LoginUserBean lub)
/*     */   {
/*  74 */     LoginLogsBean llb = getLoginLogsBean(lub);
/*  75 */     llb.setAudit_des("退出");
/*     */ 
/*  77 */     return CsLoginLogDAO.insertLog(llb);
/*     */   }
/*     */ 
/*     */   public static boolean insertLoginLog(LoginUserBean lub)
/*     */   {
/*  86 */     LoginLogsBean llb = getLoginLogsBean(lub);
/*  87 */     llb.setAudit_des("登录");
/*     */ 
/*  89 */     return CsLoginLogDAO.insertLog(llb);
/*     */   }
/*     */ 
/*     */   public static List<SettingLogsBean> searchSettingLog(Map<String, String> mp)
/*     */   {
/* 102 */     List ret = CsLogDAO.searchLogSetting(mp);
/* 103 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String searchSettingLogCnt(Map<String, String> mp)
/*     */   {
/* 112 */     String ret = CsLogDAO.searchLogSettingCount(mp);
/* 113 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<LoginLogsBean> searchLoginLogs(Map<String, String> mp)
/*     */   {
/* 127 */     List ret = CsLoginLogDAO.searchLog(mp);
/* 128 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String searchLoginLogsCnt(Map<String, String> mp)
/*     */   {
/* 137 */     String ret = CsLoginLogDAO.searchLogCnt(mp);
/* 138 */     return ret;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg) {
/* 142 */     Map mp = new HashMap();
/* 143 */     mp.put("start_num", "0");
/* 144 */     mp.put("page_size", "15");
/* 145 */     mp.put("sortType", "desc");
/*     */ 
/* 147 */     List lt = searchLoginLogs(mp);
/*     */ 
/* 149 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.Log.LogManager
 * JD-Core Version:    0.6.2
 */