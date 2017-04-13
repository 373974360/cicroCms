/*     */ package com.cicro.wcm.services.org.app;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.operate.OperateDAO;
/*     */ import com.cicro.wcm.server.LicenseCheck;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AppManager
/*     */   implements ISyncCatch
/*     */ {
/*  24 */   private static List<AppBean> app_List = new ArrayList();
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
/*  37 */     app_List.clear();
/*  38 */     List l = OperateDAO.getAppList();
/*  39 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  41 */       for (AppBean ab : l)
/*     */       {
/*  43 */         if (LicenseCheck.isHaveApp(ab.getApp_id()))
/*     */         {
/*  45 */           app_List.add(ab);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadApp()
/*     */   {
/*  59 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.app.AppManager");
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAppList()
/*     */   {
/*  70 */     return app_List;
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAppListByIDS(String app_ids)
/*     */   {
/*  82 */     app_ids = "," + app_ids + ",";
/*  83 */     List l = new ArrayList();
/*  84 */     if ((app_List != null) && (app_List.size() > 0))
/*     */     {
/*  86 */       for (int i = 0; i < app_List.size(); i++)
/*     */       {
/*  88 */         if (app_ids.contains("," + ((AppBean)app_List.get(i)).getApp_id() + ","))
/*  89 */           l.add((AppBean)app_List.get(i));
/*     */       }
/*     */     }
/*  92 */     return l;
/*     */   }
/*     */ 
/*     */   public static AppBean getAppBean(String app_id)
/*     */   {
/* 103 */     if ((app_List != null) && (app_List.size() > 0))
/*     */     {
/* 105 */       AppBean ab = new AppBean();
/* 106 */       for (int i = 0; i < app_List.size(); i++)
/*     */       {
/* 108 */         if (app_id.equals(((AppBean)app_List.get(i)).getApp_id()))
/* 109 */           ab = (AppBean)app_List.get(i);
/*     */       }
/* 111 */       return ab;
/*     */     }
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean appIsPeculiar(String app_id)
/*     */   {
/* 124 */     return ("cms".equals(app_id)) || ("zwgk".equals(app_id));
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.app.AppManager
 * JD-Core Version:    0.6.2
 */