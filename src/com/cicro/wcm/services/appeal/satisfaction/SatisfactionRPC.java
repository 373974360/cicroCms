/*    */ package com.cicro.wcm.services.appeal.satisfaction;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.satisfaction.SatisfactionBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class SatisfactionRPC
/*    */ {
/*    */   public static List<SatisfactionBean> getSatisfactionList()
/*    */   {
/* 18 */     return SatisfactionManager.getSatisfactionList();
/*    */   }
/*    */ 
/*    */   public static SatisfactionBean getSatisfactionBean(int wf_id)
/*    */   {
/* 27 */     return SatisfactionManager.getSatisfactionBean(wf_id);
/*    */   }
/*    */ 
/*    */   public static boolean insertSatisfaction(List<SatisfactionBean> sf_list, HttpServletRequest request)
/*    */   {
/* 37 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 38 */     if (stl != null)
/*    */     {
/* 40 */       return SatisfactionManager.insertSatisfaction(sf_list, stl);
/*    */     }
/* 42 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.satisfaction.SatisfactionRPC
 * JD-Core Version:    0.6.2
 */