/*    */ package com.cicro.wcm.dao.cms.category;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.category.SyncBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SyncDAO
/*    */ {
/*    */   public static List<SyncBean> getAllSyncBeanList()
/*    */   {
/* 29 */     return DBManager.queryFList("getAllSyncList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertSync(SyncBean sb)
/*    */   {
/* 41 */     return DBManager.insert("insertSync", sb);
/*    */   }
/*    */ 
/*    */   public static boolean deleteSync(String s_site_id, String cat_ids, String orientation)
/*    */   {
/* 53 */     Map m = new HashMap();
/* 54 */     m.put("site_id", s_site_id);
/* 55 */     m.put("cat_ids", cat_ids);
/* 56 */     m.put("orientation", orientation);
/* 57 */     return DBManager.delete("deleteSync", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteSyncForCatID(String site_id, String cat_ids)
/*    */   {
/* 68 */     Map m = new HashMap();
/* 69 */     m.put("site_id", site_id);
/* 70 */     m.put("cat_ids", cat_ids);
/* 71 */     return DBManager.delete("deleteSync_forCat_id", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.SyncDAO
 * JD-Core Version:    0.6.2
 */