/*    */ package com.cicro.wcm.dao.search_bak_20151106.impl;
/*    */ 
/*    */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IndexCustomDaoImpl
/*    */   implements IIndexInfoDao
/*    */ {
/*    */   public List getInfoListBySiteIdPages(Map map)
/*    */   {
/* 26 */     return DBManager.queryFList("search.getCostomListBySiteIdPages", map);
/*    */   }
/*    */ 
/*    */   public int getInfoListBySiteIdCount(Map map)
/*    */   {
/* 35 */     return Integer.valueOf(((Integer)DBManager.queryFObj("search.getCostomListBySiteIdCount", map)).intValue()).intValue();
/*    */   }
/*    */ 
/*    */   public Map getInfoById(int id)
/*    */   {
/* 44 */     Map map = new HashMap();
/* 45 */     map.put("id", Integer.valueOf(id));
/* 46 */     return (Map)DBManager.queryFObj("search.getCustomById", map);
/*    */   }
/*    */ 
/*    */   public Map getInfoById(Map map)
/*    */   {
/* 53 */     return (Map)DBManager.queryFObj("search.getCustomById", map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.impl.IndexCustomDaoImpl
 * JD-Core Version:    0.6.2
 */