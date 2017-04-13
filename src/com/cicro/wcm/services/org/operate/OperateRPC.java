/*     */ package com.cicro.wcm.services.org.operate;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.org.operate.OperateBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.app.AppManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class OperateRPC
/*     */ {
/*     */   public static Map<String, OperateBean> getOptMap()
/*     */   {
/*  22 */     return OperateManager.getOptMap();
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAppList()
/*     */   {
/*  32 */     return AppManager.getAppList();
/*     */   }
/*     */ 
/*     */   public static int getOperateID()
/*     */   {
/*  42 */     return OperateManager.getOperateID();
/*     */   }
/*     */ 
/*     */   public static String getOperateTreeJsonStr(String app_id)
/*     */   {
/*  51 */     return OperateManager.getOperateTreeJsonStr(app_id);
/*     */   }
/*     */ 
/*     */   public static String getOperateTreeJsonStr2(String app_ids)
/*     */   {
/*  61 */     return OperateManager.getOperateTreeJsonStr2(app_ids);
/*     */   }
/*     */ 
/*     */   public static OperateBean getOperateBean(String opt_id)
/*     */   {
/*  71 */     return OperateManager.getOperateBean(opt_id);
/*     */   }
/*     */ 
/*     */   public static String getChildOptCount(String opt_id)
/*     */   {
/*  81 */     return OperateManager.getChildOptCount(opt_id);
/*     */   }
/*     */ 
/*     */   public static List<OperateBean> getChildOptList(String opt_id)
/*     */   {
/*  91 */     return OperateManager.getChildOptList(opt_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertOperate(OperateBean ob, HttpServletRequest request)
/*     */   {
/* 102 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 103 */     if (stl != null)
/*     */     {
/* 105 */       return OperateManager.insertOperate(ob, stl);
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateOperate(OperateBean ob, HttpServletRequest request)
/*     */   {
/* 118 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 119 */     if (stl != null)
/*     */     {
/* 121 */       return OperateManager.updateOperate(ob, stl);
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveOperate(String parent_id, String opt_id, HttpServletRequest request)
/*     */   {
/* 133 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 134 */     if (stl != null)
/*     */     {
/* 136 */       return OperateManager.moveOperate(parent_id, opt_id, stl);
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteOperate(String opt_id, HttpServletRequest request)
/*     */   {
/* 149 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 150 */     if (stl != null)
/*     */     {
/* 152 */       return OperateManager.deleteOperate(opt_id, stl);
/*     */     }
/* 154 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.operate.OperateRPC
 * JD-Core Version:    0.6.2
 */