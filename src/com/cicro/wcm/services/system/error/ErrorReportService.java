/*     */ package com.cicro.wcm.services.system.error;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.system.error.ErrorReportBean;
/*     */ import com.cicro.wcm.dao.system.error.ErrorReportDao;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ErrorReportService
/*     */ {
/*     */   public static List<ErrorReportBean> getErrorReportList(Map map)
/*     */   {
/*  24 */     return ErrorReportDao.getErrorReportList(map);
/*     */   }
/*     */ 
/*     */   public static int getErrorReportListCount(Map map)
/*     */   {
/*  33 */     return ErrorReportDao.getErrorReportListCount(map);
/*     */   }
/*     */ 
/*     */   public static boolean addErrorReport(ErrorReportBean errorReportBean, HttpServletRequest request)
/*     */   {
/*     */     try
/*     */     {
/*  43 */       String ip = FormatUtil.getIpAddr(request);
/*  44 */       String site_id = SiteDomainManager.getSiteIDByDomain(request.getLocalName());
/*  45 */       errorReportBean.setErr_name_ip(ip);
/*  46 */       errorReportBean.setErr_time(DateUtil.getCurrentDateTime());
/*  47 */       errorReportBean.setSite_id(site_id);
/*  48 */       return ErrorReportDao.addErrorReport(errorReportBean);
/*     */     } catch (Exception e) {
/*  50 */       e.printStackTrace();
/*  51 */     }return false;
/*     */   }
/*     */ 
/*     */   public static ErrorReportBean getBeforeAddErrorReportBean(String info_id)
/*     */   {
/*  62 */     ErrorReportBean errorReportBean = new ErrorReportBean();
/*  63 */     InfoBean infoBean = InfoBaseManager.getInfoById(info_id);
/*  64 */     errorReportBean.setInfo_id(Integer.valueOf(info_id).intValue());
/*     */ 
/*  66 */     return errorReportBean;
/*     */   }
/*     */ 
/*     */   public static boolean deleteErrorReports(String obtids)
/*     */   {
/*     */     try
/*     */     {
/*  78 */       String[] eacuseid = obtids.split(",");
/*  79 */       StringBuffer sb = new StringBuffer();
/*  80 */       for (int i = 0; i < eacuseid.length; i++) {
/*  81 */         String id = eacuseid[i];
/*  82 */         if ((id != null) && (!"".equals(id))) {
/*  83 */           if (i != eacuseid.length - 1)
/*  84 */             sb.append(id + ",");
/*     */           else {
/*  86 */             sb.append(id);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*  91 */       ErrorReportDao.deleteErrorReports(sb.toString());
/*  92 */       return true;
/*     */     } catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */     }return false;
/*     */   }
/*     */ 
/*     */   public static ErrorReportBean getErrorReportById(int id)
/*     */   {
/* 106 */     return ErrorReportDao.getErrorReportById(id);
/*     */   }
/*     */ 
/*     */   public static boolean updateErrorReportById(ErrorReportBean errorReportBean)
/*     */   {
/* 115 */     return ErrorReportDao.updateErrorReportById(errorReportBean);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.error.ErrorReportService
 * JD-Core Version:    0.6.2
 */