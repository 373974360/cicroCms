/*    */ package com.cicro.wcm.dao.search_bak_20151106.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.search.IndexBeanInfo;
/*    */ import com.cicro.wcm.dao.search.IIndexInfoPicDao;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IndexPicDaoImpl
/*    */   implements IIndexInfoPicDao
/*    */ {
/*    */   public List getInfoListBySiteIdPages(Map map)
/*    */   {
/* 28 */     return DBManager.queryFList("search.getPicListBySiteIdPages", map);
/*    */   }
/*    */ 
/*    */   public int getInfoListBySiteIdCount(Map map)
/*    */   {
/* 37 */     return Integer.valueOf(((Integer)DBManager.queryFObj("search.getPicListBySiteIdCount", map)).intValue()).intValue();
/*    */   }
/*    */ 
/*    */   public Map getInfoListBySiteIdCount(int id)
/*    */   {
/* 46 */     Map map = new HashMap();
/* 47 */     map.put("id", Integer.valueOf(id));
/* 48 */     return (Map)DBManager.queryFObj("search.getPicById", map);
/*    */   }
/*    */ 
/*    */   public IndexBeanInfo getInfoById(int id)
/*    */   {
/* 57 */     Map map = new HashMap();
/* 58 */     map.put("id", Integer.valueOf(id));
/* 59 */     return (IndexBeanInfo)DBManager.queryFObj("search.getPicById", map);
/*    */   }
/*    */ 
/*    */   public List<Map> getInfosById(int id)
/*    */   {
/* 68 */     Map map = new HashMap();
/* 69 */     map.put("id", Integer.valueOf(id));
/* 70 */     return DBManager.queryFList("search.getPicById2", map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.impl.IndexPicDaoImpl
 * JD-Core Version:    0.6.2
 */