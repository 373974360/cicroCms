/*    */ package com.cicro.wcm.services.member;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.member.MemberConfigBean;
/*    */ import com.cicro.wcm.catchs.ISyncCatch;
/*    */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*    */ import com.cicro.wcm.dao.member.MemberDAO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MemberConfigManager
/*    */   implements ISyncCatch
/*    */ {
/* 14 */   private static MemberConfigBean config_bean = null;
/*    */ 
/*    */   static {
/* 17 */     reloadCatchHandl();
/*    */   }
/*    */ 
/*    */   public void reloadCatch()
/*    */   {
/* 22 */     reloadCatchHandl();
/*    */   }
/*    */ 
/*    */   public static void reloadCatchHandl()
/*    */   {
/* 27 */     config_bean = null;
/* 28 */     List lt = MemberDAO.getAllMemberConfigList();
/* 29 */     if ((lt != null) && (lt.size() > 0))
/*    */     {
/* 31 */       config_bean = (MemberConfigBean)lt.get(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void reload()
/*    */   {
/* 40 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.member.MemberConfigManager");
/*    */   }
/*    */ 
/*    */   public static MemberConfigBean getMemberConfigBean()
/*    */   {
/* 49 */     MemberConfigBean ret = config_bean;
/* 50 */     return ret;
/*    */   }
/*    */ 
/*    */   public static List<String> getForbiddenName()
/*    */   {
/* 59 */     List ret = new ArrayList();
/* 60 */     if (config_bean != null)
/*    */     {
/* 62 */       String[] names = config_bean.getForbidden_name().trim().split(" ");
/* 63 */       for (int i = 0; i < names.length; i++)
/*    */       {
/* 65 */         ret.add(names[i]);
/*    */       }
/*    */     }
/* 68 */     return ret;
/*    */   }
/*    */ 
/*    */   public static boolean updateMemberConfig(MemberConfigBean mcb, SettingLogsBean stl)
/*    */   {
/* 79 */     if (MemberDAO.updateMemberConfig(mcb, stl))
/*    */     {
/* 81 */       reload();
/* 82 */       return true;
/*    */     }
/*    */ 
/* 86 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.member.MemberConfigManager
 * JD-Core Version:    0.6.2
 */