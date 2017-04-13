/*    */ package com.cicro.wcm.services.member;
/*    */ 
/*    */ import com.cicro.wcm.bean.member.MemberBean;
/*    */ import com.cicro.wcm.services.org.user.SessionManager;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class MemberLogin
/*    */ {
/*    */   public static String memberLogin(String me_account, String me_password, HttpServletRequest request)
/*    */   {
/* 15 */     String result = MemberManager.memberLogin(me_account, me_password);
/* 16 */     if ("true".equals(result))
/*    */     {
/* 18 */       SessionManager.set(request, "cicro_wcm_member", MemberManager.getMemberBenaByAccount(me_account));
/*    */     }
/* 20 */     return result;
/*    */   }
/*    */ 
/*    */   public static MemberBean getMemberBySession(HttpServletRequest request)
/*    */   {
/* 32 */     return (MemberBean)SessionManager.get(request, "cicro_wcm_member");
/*    */   }
/*    */ 
/*    */   public static boolean logout(HttpServletRequest request)
/*    */   {
/* 42 */     SessionManager.remove(request, "cicro_wcm_member");
/* 43 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.member.MemberLogin
 * JD-Core Version:    0.6.2
 */