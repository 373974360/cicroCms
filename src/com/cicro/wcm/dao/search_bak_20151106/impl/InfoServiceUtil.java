/*    */ package com.cicro.wcm.dao.search_bak_20151106.impl;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class InfoServiceUtil
/*    */ {
/*    */   public static String getInfoTypeById(int id)
/*    */   {
/* 17 */     Map map = new HashMap();
/* 18 */     map.put("id", Integer.valueOf(id));
/* 19 */     return getInfoTypeById(map);
/*    */   }
/*    */ 
/*    */   public static String getInfoTypeById(Map map)
/*    */   {
/* 28 */     return (String)DBManager.queryFObj("search.getInfoCountById", map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.impl.InfoServiceUtil
 * JD-Core Version:    0.6.2
 */