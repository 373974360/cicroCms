/*     */ package com.cicro.wcm.services.model.services;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.cms.info.InfoDAO;
/*     */ import com.cicro.wcm.dao.cms.info.ModelUtilDAO;
/*     */ import com.cicro.wcm.dao.zwgk.info.GKInfoDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.rmi.IFileRmi;
/*     */ import com.cicro.wcm.rmi.file.FileRmiFactory;
/*     */ import com.cicro.wcm.services.cms.info.InfoPublishManager;
/*     */ import com.cicro.wcm.services.cms.info.ModelConfig;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*     */ import com.cicro.wcm.services.control.server.SiteServerManager;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CustomFormRMIImplUtil
/*     */ {
/*     */   private static final String httpstr = "http://xxzyk.xjbt.gov.cn/manager/file/";
/*     */   private static final String resStr = "/manager/file/";
/*     */ 
/*     */   public static String replaceAllStr(String content)
/*     */   {
/*  26 */     return content.replaceAll("/manager/file/", "http://xxzyk.xjbt.gov.cn/manager/file/");
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(Object o, String sqlName, String model_name, SettingLogsBean stl)
/*     */   {
/*  40 */     if (updateInfo(o, stl))
/*     */     {
/*  42 */       if (model_name.toLowerCase().contains("gk"))
/*     */       {
/*  44 */         GKInfoDAO.updateGKInfo(o);
/*     */       }
/*  46 */       if (!"".equals(sqlName))
/*     */       {
/*  48 */         if (sqlName.equals("update_info_pic"))
/*     */         {
/*  50 */           return ModelUtilDAO.insertPicInfo(o, model_name);
/*     */         }
/*     */ 
/*  53 */         return DBManager.update(sqlName, o);
/*     */       }
/*  55 */       return true;
/*     */     }
/*     */ 
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateInfo(Object ob, SettingLogsBean stl)
/*     */   {
/*  70 */     InfoBean info = (InfoBean)ob;
/*  71 */     if ((info.getInput_dtime() != null) && (!"".equals(info.getInput_dtime()))) {
/*  72 */       info.setModify_dtime(info.getInput_dtime());
/*  73 */       info.setOpt_dtime(info.getInput_dtime());
/*  74 */       info.setReleased_dtime(info.getInput_dtime());
/*     */     }
/*  76 */     CustomFormRMIImpl.changeInfoStatus(info);
/*  77 */     return InfoDAO.updateInfo(info, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateInfo(String rmi_site_id, Object ob, String model_name, SettingLogsBean stl)
/*     */   {
/*  89 */     if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
/*     */     {
/*  92 */       return update(ob, model_name, stl);
/*     */     }
/*     */ 
/*  95 */     IFileRmi ifr = FileRmiFactory.getFileRmiObj(rmi_site_id);
/*     */     try {
/*  97 */       return ifr.updateInfo(ob, model_name, stl);
/*     */     }
/*     */     catch (RemoteException e) {
/* 100 */       e.printStackTrace();
/* 101 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean update(Object ob, String model_name, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 116 */       Map mp = ModelConfig.getModelMap(model_name);
/* 117 */       ModelUtil.setPageCountInObject(ob, model_name);
/*     */ 
/* 119 */       String UpdateSQL = "";
/* 120 */       if (mp != null) {
/* 121 */         UpdateSQL = (String)mp.get("UpdateSQL");
/*     */       }
/* 123 */       if (updateModel(ob, UpdateSQL, model_name, stl))
/*     */       {
/* 125 */         InfoBean info = (InfoBean)ob;
/*     */ 
/* 127 */         if (info.getInput_dtime().equals("")) {
/* 128 */           info.setInput_dtime(DateUtil.getCurrentDateTime());
/*     */         }
/* 130 */         info.setReleased_dtime(info.getInput_dtime());
/*     */ 
/* 132 */         CustomFormRMIImpl.changeInfoStatus(info);
/* 133 */         if (info.getInfo_status() == 8) {
/* 134 */           InfoPublishManager.publishAfterEvent(info);
/*     */         }
/* 136 */         return true;
/*     */       }
/* 138 */       return false;
/*     */     } catch (Exception e) {
/* 140 */       e.printStackTrace();
/* 141 */     }return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.CustomFormRMIImplUtil
 * JD-Core Version:    0.6.2
 */