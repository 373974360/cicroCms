/*    */ package com.cicro.wcm.dao.search_bak_20151106.impl;
/*    */ 
/*    */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IndexLawDaoImpl
/*    */   implements IIndexInfoDao
/*    */ {
/*    */   public List getInfoListBySiteIdPages(Map map)
/*    */   {
/* 26 */     return DBManager.queryFList("search.getLawListBySiteIdPages", map);
/*    */   }
/*    */ 
/*    */   public int getInfoListBySiteIdCount(Map map)
/*    */   {
/* 35 */     return Integer.valueOf(((Integer)DBManager.queryFObj("search.getLawListBySiteIdCount", map)).intValue()).intValue();
/*    */   }
/*    */ 
/*    */   public Map getInfoById(int id)
/*    */   {
/* 44 */     Map map = new HashMap();
/* 45 */     map.put("id", Integer.valueOf(id));
/* 46 */     return (Map)DBManager.queryFObj("search.getLawById", map);
/*    */   }
/*    */ 
/*    */   public Map getInfoById(Map map)
/*    */   {
/* 52 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.impl.IndexLawDaoImpl
 * JD-Core Version:    0.6.2
 */