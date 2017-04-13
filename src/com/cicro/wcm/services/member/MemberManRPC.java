/*     */ package com.cicro.wcm.services.member;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.member.MemberBean;
/*     */ import com.cicro.wcm.bean.member.MemberCategoryBean;
/*     */ import com.cicro.wcm.bean.member.MemberConfigBean;
/*     */ import com.cicro.wcm.bean.member.MemberRegisterBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.user.SessionManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class MemberManRPC
/*     */ {
/*     */   public static boolean passWordCheck(String password, HttpServletRequest request)
/*     */   {
/*  25 */     return MemberManager.passWordCheck(getMemberBySession(request), password);
/*     */   }
/*     */ 
/*     */   public static String memberLogin(String me_account, String me_password, HttpServletRequest request)
/*     */   {
/*  33 */     return MemberLogin.memberLogin(me_account, me_password, request);
/*     */   }
/*     */ 
/*     */   public static void setUrlToSession(String url, HttpServletRequest request)
/*     */   {
/*  41 */     SessionManager.set(request, "cicro_wcm_member_pro_url", url);
/*     */   }
/*     */ 
/*     */   public static String getUrlFoSesson(HttpServletRequest request)
/*     */   {
/*  49 */     return (String)SessionManager.get(request, "cicro_wcm_member_pro_url");
/*     */   }
/*     */ 
/*     */   public static MemberBean getMemberBySession(HttpServletRequest request)
/*     */   {
/*  60 */     return MemberLogin.getMemberBySession(request);
/*     */   }
/*     */ 
/*     */   public static boolean logout(HttpServletRequest request)
/*     */   {
/*  70 */     return MemberLogin.logout(request);
/*     */   }
/*     */ 
/*     */   public static MemberConfigBean getMemberConfigBean()
/*     */   {
/*  79 */     return MemberConfigManager.getMemberConfigBean();
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberConfigBean(MemberConfigBean mcb, HttpServletRequest request)
/*     */   {
/*  90 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  91 */     if (stl != null)
/*     */     {
/*  93 */       return MemberConfigManager.updateMemberConfig(mcb, stl);
/*     */     }
/*     */ 
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<String> getForbiddenName()
/*     */   {
/* 107 */     return MemberConfigManager.getForbiddenName();
/*     */   }
/*     */ 
/*     */   public static List<MemberCategoryBean> getMemberCategoryList()
/*     */   {
/* 116 */     return MemberCategoryManager.getAllMemberCateGoryList();
/*     */   }
/*     */ 
/*     */   public static MemberCategoryBean getMemberCategoryByID(String id)
/*     */   {
/* 126 */     return MemberCategoryManager.getMemberCategoryByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMemberCategory(MemberCategoryBean mcb, HttpServletRequest request)
/*     */   {
/* 137 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 138 */     if (stl != null)
/*     */     {
/* 140 */       return MemberCategoryManager.insertMemberCategory(mcb, stl);
/*     */     }
/*     */ 
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveMemberCategorySort(String mcat_id, HttpServletRequest request)
/*     */   {
/* 156 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 157 */     if (stl != null)
/*     */     {
/* 159 */       return MemberCategoryManager.saveMemberCategorySort(mcat_id, stl);
/*     */     }
/*     */ 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberCategory(MemberCategoryBean mcb, HttpServletRequest request)
/*     */   {
/* 175 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 176 */     if (stl != null)
/*     */     {
/* 178 */       return MemberCategoryManager.updateMemberCategory(mcb, stl);
/*     */     }
/*     */ 
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMemberCategory(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 194 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 195 */     if (stl != null)
/*     */     {
/* 197 */       return MemberCategoryManager.deleteMemberCategory(mp, stl);
/*     */     }
/*     */ 
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<MemberBean> getMemberList(Map<String, String> mp)
/*     */   {
/* 212 */     return MemberManager.getMemberList(mp);
/*     */   }
/*     */ 
/*     */   public static MemberBean getMemberBeanByID(String id)
/*     */   {
/* 222 */     return MemberManager.getMemberBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static String getMemberCnt(Map<String, String> mp)
/*     */   {
/* 232 */     return MemberManager.getMemberCnt(mp);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getMemberAccountList(List<MemberBean> lt)
/*     */   {
/* 242 */     return MemberManager.getMemberAccountList(lt);
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberInfo(MemberBean mb, MemberRegisterBean mrb, HttpServletRequest request)
/*     */   {
/* 253 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 254 */     if (stl != null)
/*     */     {
/* 256 */       return MemberManager.updateMemberInfo(mb, mrb, stl);
/*     */     }
/*     */ 
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMember(MemberBean mb, HttpServletRequest request)
/*     */   {
/* 272 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 273 */     if (stl != null)
/*     */     {
/* 275 */       return MemberManager.updateMember(mb, stl);
/*     */     }
/*     */ 
/* 279 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean checkMemberByIDS(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 290 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 291 */     if (stl != null)
/*     */     {
/* 293 */       return MemberManager.checkMemberByIDS(mp, stl);
/*     */     }
/*     */ 
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */   public static MemberRegisterBean getMemberRegisterBeanByID(String id)
/*     */   {
/* 308 */     return MemberManager.getMemberRegisterBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean RegisterMember(MemberBean mb, MemberRegisterBean mrb, HttpServletRequest request)
/*     */   {
/* 320 */     return MemberManager.RegisterMember(mb, mrb);
/*     */   }
/*     */ 
/*     */   public static boolean isAccountExisted(String account)
/*     */   {
/* 330 */     return MemberManager.isAccountExisted(account);
/*     */   }
/*     */ 
/*     */   public static boolean deleteMember(String me_ids, HttpServletRequest request)
/*     */   {
/* 341 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 342 */     if (stl != null)
/*     */     {
/* 344 */       return MemberManager.deleteMember(me_ids, stl);
/*     */     }
/*     */ 
/* 348 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 354 */     List lt = getMemberCategoryList();
/* 355 */     for (int i = 0; i < lt.size(); i++)
/*     */     {
/* 357 */       System.out.println(((MemberCategoryBean)lt.get(i)).getSort_id() + "-*-" + ((MemberCategoryBean)lt.get(i)).getMcat_id());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.member.MemberManRPC
 * JD-Core Version:    0.6.2
 */