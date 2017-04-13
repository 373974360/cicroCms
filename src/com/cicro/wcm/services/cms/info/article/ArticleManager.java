/*    */ package com.cicro.wcm.services.cms.info.article;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.dao.cms.info.ArticleDAO;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ArticleManager
/*    */ {
/*    */   public static boolean addArticle(ArticleBean article, SettingLogsBean stl)
/*    */   {
/* 19 */     int infid = article.getInfo_id();
/*    */ 
/* 21 */     if (infid <= 0) {
/* 22 */       int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_TABLE_NAME);
/* 23 */       article.setId(id);
/* 24 */       article.setInfo_id(id);
/*    */     }
/* 26 */     if (article.getInfo_content() == null) {
/* 27 */       article.setInfo_content("");
/*    */     }
/* 29 */     article.setInput_dtime(DateUtil.getCurrentDateTime());
/* 30 */     article.setWord_count(article.getInfo_content().length());
/* 31 */     if (article.getInfo_status() == 8) {
/* 32 */       article.setReleased_dtime(DateUtil.getCurrentDateTime());
/*    */     }
/* 34 */     return ArticleDAO.addArticleBean(article, stl);
/*    */   }
/*    */ 
/*    */   public static boolean updateArticle(ArticleBean article, SettingLogsBean stl) {
/* 38 */     article.setModify_dtime(DateUtil.getCurrentDateTime());
/* 39 */     article.setOpt_dtime(DateUtil.getCurrentDateTime());
/* 40 */     return ArticleDAO.updateArticle(article, stl);
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticle(String infoId, String siteId) {
/* 44 */     return ArticleDAO.getArticle(infoId, siteId);
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticleBeanForCatSiteID(String cat_id, String site_id)
/*    */   {
/* 54 */     return ArticleDAO.getArticleBeanForCatSiteID(cat_id, site_id);
/*    */   }
/*    */ 
/*    */   public static boolean deleteArticle(Map<String, String> map, SettingLogsBean stl) {
/* 58 */     return ArticleDAO.deleteArticle(map, stl);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.article.ArticleManager
 * JD-Core Version:    0.6.2
 */