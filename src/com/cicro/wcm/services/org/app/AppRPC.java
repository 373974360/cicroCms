/*    */ package com.cicro.wcm.services.org.app;
/*    */ 
/*    */ import com.cicro.wcm.bean.org.app.AppBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AppRPC
/*    */ {
/*    */   public static List<AppBean> getAppList()
/*    */   {
/* 17 */     return AppManager.getAppList();
/*    */   }
/*    */ 
/*    */   public static boolean appIsPeculiar(String app_id)
/*    */   {
/* 28 */     return AppManager.appIsPeculiar(app_id);
/*    */   }
/*    */ 
/*    */   public static List executeSearchSql(String sql)
/*    */   {
/* 42 */     return PublicTableDAO.executeSearchSql(sql);
/*    */   }
/*    */ 
/*    */   public static boolean executeDynamicSql(String sql)
/*    */   {
/* 55 */     return PublicTableDAO.executeDynamicSql(sql);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.app.AppRPC
 * JD-Core Version:    0.6.2
 */