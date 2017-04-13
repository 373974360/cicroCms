/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ import com.cicro.wcm.services.model.WcmZykFile;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class WcmZykInfoService
/*    */ {
/* 25 */   private static String filepath = JconfigUtilContainer.bashConfig().getProperty("filepath", "", "wcm_zyk");
/*    */ 
/*    */   public static List<WcmZykFile> getZykinfoFileListByInfoId(String info_id, String fieldName)
/*    */   {
/* 29 */     List list = new ArrayList();
/*    */     try {
/* 31 */       return WcmZykInfoDao.getZykinfoFileListByInfoId(info_id, fieldName);
/*    */     }
/*    */     catch (Exception e) {
/* 34 */       e.printStackTrace();
/* 35 */     }return list;
/*    */   }
/*    */ 
/*    */   public static WcmZykFile getZykinfoFileByInfoId(String id)
/*    */   {
/*    */     try
/*    */     {
/* 42 */       WcmZykFile wcmZykFile = WcmZykInfoDao.getZykinfoFileByInfoId(id);
/* 43 */       if (wcmZykFile != null) {
/* 44 */         wcmZykFile.setFile_id(filepath + wcmZykFile.getFile_id());
/*    */       }
/* 46 */       return wcmZykFile;
/*    */     } catch (Exception e) {
/* 48 */       e.printStackTrace();
/* 49 */     }return null;
/*    */   }
/*    */ 
/*    */   public static String getWcminfo_zykinfoById(String id, String site_id)
/*    */   {
/* 59 */     return WcmZykInfoDao.getWcminfo_zykinfoById(id, site_id);
/*    */   }
/*    */ 
/*    */   public static List<Map> getWcminfo_zykinfosById(String id)
/*    */   {
/* 68 */     return WcmZykInfoDao.getWcminfo_zykinfosById(id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.WcmZykInfoService
 * JD-Core Version:    0.6.2
 */