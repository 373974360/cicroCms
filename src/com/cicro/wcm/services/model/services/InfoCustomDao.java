/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import com.cicro.wcm.db.IbatisSessionFactory;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.ibatis.session.SqlSession;
/*    */ import org.apache.ibatis.session.SqlSessionFactory;
/*    */ 
/*    */ public class InfoCustomDao
/*    */ {
/*    */   public static ArticleBean getArticle(String infoId)
/*    */   {
/* 33 */     Map map = new HashMap();
/* 34 */     map.put("info_id", infoId);
/* 35 */     return (ArticleBean)DBManager.queryFObj("model.custoninfo.getInfoZBean", map);
/*    */   }
/*    */ 
/*    */   public static Map getMapBySql(String sql)
/*    */   {
/* 40 */     Map map = new HashMap();
/*    */     try {
/* 42 */       SqlSession s = IbatisSessionFactory.getInstance().openSession();
/* 43 */       map.put("sql", sql);
/*    */ 
/* 45 */       return MapManager.mapKeyToLowValueToString((Map)s.selectOne(DBManager.getSqlNameByDataType("model.custoninfo.getMapBySql"), map));
/*    */     }
/*    */     catch (Exception e) {
/* 48 */       e.printStackTrace();
/* 49 */     }return map;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.InfoCustomDao
 * JD-Core Version:    0.6.2
 */