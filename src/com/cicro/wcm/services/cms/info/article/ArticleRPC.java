/*    */ package com.cicro.wcm.services.cms.info.article;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ArticleRPC
/*    */ {
/*    */   public static boolean addArticle(ArticleBean article, HttpServletRequest request)
/*    */   {
/* 19 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 20 */     if (stl != null) {
/* 21 */       return ArticleManager.addArticle(article, stl);
/*    */     }
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateArticle(ArticleBean article, HttpServletRequest request) {
/* 27 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 28 */     if (stl != null) {
/* 29 */       return ArticleManager.updateArticle(article, stl);
/*    */     }
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticle(String infoId, String siteId) {
/* 35 */     return ArticleManager.getArticle(infoId, siteId);
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticleBeanForCatSiteID(String cat_id, String site_id)
/*    */   {
/* 45 */     return ArticleManager.getArticleBeanForCatSiteID(cat_id, site_id);
/*    */   }
/*    */ 
/*    */   public static boolean deleteArticle(Map<String, String> map, HttpServletRequest request) {
/* 49 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 50 */     if (stl != null) {
/* 51 */       return ArticleManager.deleteArticle(map, stl);
/*    */     }
/* 53 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.article.ArticleRPC
 * JD-Core Version:    0.6.2
 */