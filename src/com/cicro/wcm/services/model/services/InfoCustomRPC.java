/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*    */ import com.cicro.wcm.bean.cms.info.GKInfoBean;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class InfoCustomRPC
/*    */ {
/*    */   public static boolean addInfoCuston(String model_id, Map map)
/*    */   {
/* 33 */     return InfoCustomService.addInfoCuston(model_id, map);
/*    */   }
/*    */ 
/*    */   public static boolean updateInfoCuston(String model_id, Map map)
/*    */   {
/* 38 */     return InfoCustomService.updateInfoCuston(model_id, map);
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticle(String infoId)
/*    */   {
/* 43 */     return InfoCustomService.getArticle(infoId);
/*    */   }
/*    */ 
/*    */   public static Map getCustomInfoMap(String model_id, String info_id)
/*    */   {
/* 48 */     return InfoCustomService.getCustomInfoMap(model_id, info_id);
/*    */   }
/*    */ 
/*    */   public static boolean updateQuoteInfoCustom(ArticleBean articleBean, Map cusBean, String model_ename, HttpServletRequest request)
/*    */   {
/* 53 */     return InfoCustomService.updateQuoteInfoCustom(articleBean, cusBean, model_ename, request);
/*    */   }
/*    */ 
/*    */   public static Map getGKInfo(String infoId)
/*    */   {
/* 58 */     return InfoCustomService.getGKInfo(infoId);
/*    */   }
/*    */ 
/*    */   public static boolean updateQuoteInfoCustomGk(GKInfoBean gkInfoBean, Map cusBean, String model_ename, HttpServletRequest request)
/*    */   {
/* 63 */     return InfoCustomService.updateQuoteInfoCustomGk(gkInfoBean, cusBean, model_ename, request);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.InfoCustomRPC
 * JD-Core Version:    0.6.2
 */