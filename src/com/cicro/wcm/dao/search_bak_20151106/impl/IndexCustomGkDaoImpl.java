/*    */ package com.cicro.wcm.dao.search_bak_20151106.impl;
/*    */ 
/*    */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IndexCustomGkDaoImpl
/*    */   implements IIndexInfoDao
/*    */ {
/*    */   public List getInfoListBySiteIdPages(Map map)
/*    */   {
/* 26 */     return DBManager.queryFList("search.getCostomGkListBySiteIdPages", map);
/*    */   }
/*    */ 
/*    */   public int getInfoListBySiteIdCount(Map map)
/*    */   {
/* 35 */     return Integer.valueOf(((Integer)DBManager.queryFObj("search.getCostomGkListBySiteIdCount", map)).intValue()).intValue();
/*    */   }
/*    */ 
/*    */   public Map getInfoById(Map map)
/*    */   {
/* 42 */     return (Map)DBManager.queryFObj("search.getCustomGkById", map);
/*    */   }
/*    */ 
/*    */   public Map getInfoById(int id)
/*    */   {
/* 48 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.impl.IndexCustomGkDaoImpl
 * JD-Core Version:    0.6.2
 */