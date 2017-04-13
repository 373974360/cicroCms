/*     */ package com.cicro.wcm.services.extendfunction.zdfwly;
/*     */ 
/*     */ import com.cicro.util.CryptoTools;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.services.appeal.count.CountUtil;
/*     */ import com.cicro.wcm.services.org.user.SessionManager;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ZdfwlyInfoManager
/*     */ {
/*     */   public static String getZdfwlyInfoCount(Map<String, String> m) {
/*  55 */     if (m.containsKey("key_word"))
/*     */     {
/*  57 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/*  58 */         return "0";
/*     */     }
/*  60 */     return ZdfwlyInfoDAO.getZdfwlyInfoCount(m);
/*     */   }
/*     */ 
/*     */   public static List<ZdfwlyInfoBean> getAllZdfwlyInfoList(Map<String, String> m)
/*     */   {
/*  65 */     if (m.containsKey("key_word"))
/*     */     {
/*  67 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/*  68 */         return null;
/*     */     }
/*  70 */     return ZdfwlyInfoDAO.getZdfwlyInfoList(m);
/*     */   }
/*     */ 
/*     */   public static ZdfwlyInfoBean getZdfwlyInfoBean(String id)
/*     */   {
/*  75 */     return ZdfwlyInfoDAO.getZdfwlyInfoBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertZdfwlyInfo(ZdfwlyInfoBean mb)
/*     */   {
/*  80 */     mb.setId(PublicTableDAO.getIDByTableName("cs_project_Jdy"));
/*     */ 
/*  82 */     boolean insertZdfwlyInfo = ZdfwlyInfoDAO.insertZdfwlyInfo(mb);
/*  84 */     return insertZdfwlyInfo;
/*     */   }
/*     */ 
/*     */   public static boolean updateZdfwlyInfo(ZdfwlyInfoBean mb)
/*     */   {
/*  89 */     boolean updateZdfwlyInfo = ZdfwlyInfoDAO.updateZdfwlyInfo(mb);
/*  91 */     return updateZdfwlyInfo;
/*     */   }
/*     */ 
/*     */   public static boolean publishZdfwlyInfo(Map<String, String> m)
/*     */   {
/*  96 */     boolean publishZdfwlyInfo = ZdfwlyInfoDAO.publishZdfwlyInfo(m);
/*  98 */     return publishZdfwlyInfo;
/*     */   }
/*     */ 
/*     */   public static boolean deleteJdy(Map<String, String> m)
/*     */   {
/* 103 */     boolean deleteZdfwlyInfo = ZdfwlyInfoDAO.deleteZdfwlyInfo(m);
/* 105 */     return deleteZdfwlyInfo;
/*     */   }
/*     */ 
/*     */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.project.wzjc.JdyManager
 * JD-Core Version:    0.6.2
 */