/*    */ package com.cicro.wcm.dao.cms.info;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ArticleDAO
/*    */ {
/*    */   public static boolean addArticleBean(ArticleBean article, SettingLogsBean stl)
/*    */   {
/* 19 */     if (InfoDAO.addInfo(article, stl)) {
/* 20 */       if (DBManager.insert("addArticle", article)) {
/* 21 */         PublicTableDAO.insertSettingLogs("添加", "主信息内容", article.getInfo_id(), stl);
/* 22 */         return true;
/*    */       }
/* 24 */       return false;
/*    */     }
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateArticle(ArticleBean article, SettingLogsBean stl) {
/* 30 */     if (InfoDAO.updateInfo(article, stl)) {
/* 31 */       if (DBManager.update("updateArticle", article)) {
/* 32 */         PublicTableDAO.insertSettingLogs("修改", "主信息内容", article.getInfo_id(), stl);
/* 33 */         return true;
/*    */       }
/* 35 */       return false;
/*    */     }
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticle(String infoId, String siteId) {
/* 41 */     Map map = new HashMap();
/* 42 */     map.put("site_id", siteId);
/* 43 */     map.put("info_id", infoId);
/* 44 */     return (ArticleBean)DBManager.queryFObj("getInfoArticleBean", map);
/*    */   }
/*    */ 
/*    */   public static ArticleBean getArticleBeanForCatSiteID(String cat_id, String site_id)
/*    */   {
/* 54 */     Map map = new HashMap();
/* 55 */     map.put("site_id", site_id);
/* 56 */     map.put("cat_id", cat_id);
/* 57 */     return (ArticleBean)DBManager.queryFObj("getArticleBeanForCatSiteID", map);
/*    */   }
/*    */ 
/*    */   public static boolean deleteArticle(Map<String, String> map, SettingLogsBean stl) {
/* 61 */     if (InfoDAO.deleteInfo(map, stl)) {
/* 62 */       if (DBManager.update("deleteArticle", map)) {
/* 63 */         PublicTableDAO.insertSettingLogs("删除", "彻底删除信息", (String)map.get("info_id"), stl);
/* 64 */         return true;
/*    */       }
/* 66 */       return false;
/*    */     }
/* 68 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 72 */     System.out.println(getArticleBeanForCatSiteID("10", "GKmzj"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.info.ArticleDAO
 * JD-Core Version:    0.6.2
 */