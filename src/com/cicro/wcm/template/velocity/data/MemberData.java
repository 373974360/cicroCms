/*    */ package com.cicro.wcm.template.velocity.data;
/*    */ 
/*    */ import com.cicro.wcm.bean.member.MemberBean;
/*    */ import com.cicro.wcm.bean.member.MemberConfigBean;
/*    */ import com.cicro.wcm.services.member.MemberConfigManager;
/*    */ import com.cicro.wcm.services.member.MemberLogin;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class MemberData
/*    */ {
/*    */   public static MemberConfigBean getMemberConfig()
/*    */   {
/* 16 */     return MemberConfigManager.getMemberConfigBean();
/*    */   }
/*    */ 
/*    */   public static String getRegSerTerms()
/*    */   {
/* 23 */     return getMemberConfig().getReg_pro();
/*    */   }
/*    */ 
/*    */   public static MemberBean getMemberObject(HttpServletRequest request)
/*    */   {
/* 28 */     return MemberLogin.getMemberBySession(request);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.MemberData
 * JD-Core Version:    0.6.2
 */