/*     */ package com.cicro.wcm.dao.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryItemBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class queryItemDao
/*     */ {
/*     */   public static String getQueryItemCount(Map<String, String> m)
/*     */   {
/*  21 */     return DBManager.getString("getQueryItemCount", m);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemList(Map<String, String> m)
/*     */   {
/*  32 */     return DBManager.queryFList("getQueryItemList", m);
/*     */   }
/*     */ 
/*     */   public static String getQueryItemCounts(Map m)
/*     */   {
/*  37 */     return DBManager.getString("getQueryItemCounts", m);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemLists(Map m)
/*     */   {
/*  47 */     return DBManager.queryFList("getQueryItemLists", m);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemBeans(Map m)
/*     */   {
/*  58 */     return DBManager.queryFList("getQueryItemBeans", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryItem(int conf_id, QueryItemBean ob, SettingLogsBean stl)
/*     */   {
/*  69 */     if (DBManager.insert("insert_QueryItem", ob))
/*     */     {
/*  72 */       return true;
/*     */     }
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryItem(QueryItemBean ob, SettingLogsBean stl)
/*     */   {
/*  84 */     if (DBManager.update("update_QueryItem", ob))
/*     */     {
/*  86 */       PublicTableDAO.insertSettingLogs("修改", "修改信息", ob.getItem_id(), stl);
/*  87 */       return true;
/*     */     }
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItemByConfId(int conf_id, SettingLogsBean stl)
/*     */   {
/* 100 */     if (DBManager.delete("delete_QueryItem_ByConfId", Integer.valueOf(conf_id)))
/*     */     {
/* 102 */       PublicTableDAO.insertSettingLogs("删除", "查询信息", conf_id, stl);
/* 103 */       return true;
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItemByItem(Map m, SettingLogsBean stl)
/*     */   {
/* 115 */     if (DBManager.delete("delete_QueryItem", m))
/*     */     {
/* 117 */       PublicTableDAO.insertSettingLogs("删除", "查询信息", m.get("item_id"), stl);
/* 118 */       return true;
/*     */     }
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItemByConf_id(int conf_id, SettingLogsBean stl)
/*     */   {
/* 131 */     if (DBManager.delete("delete_QueryItem_ByConfId", Integer.valueOf(conf_id)))
/*     */     {
/* 133 */       PublicTableDAO.insertSettingLogs("删除", "查询信息", conf_id, stl);
/* 134 */       return true;
/*     */     }
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getQueryCellCount(String conf_id)
/*     */   {
/* 142 */     return DBManager.getString("getQueryCellCount", conf_id);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryListBrowser(Map m)
/*     */   {
/* 147 */     return DBManager.queryFList("getQueryList_Browser", m);
/*     */   }
/*     */ 
/*     */   public static String getQueryListCountBrowser(Map m)
/*     */   {
/* 152 */     return DBManager.getString("getQueryListCount_Browser", m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 156 */     Map m = new HashMap();
/* 157 */     m.put("con", "from cs_dz_cx_24 where 1=1 and item_2='1' and item_3='90'");
/* 158 */     m.put("page_size", "10");
/* 159 */     m.put("start_num", "0");
/* 160 */     System.out.println(getQueryListBrowser(m));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.query.queryItemDao
 * JD-Core Version:    0.6.2
 */