/*     */ package com.cicro.wcm.dao.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ZTCategoryDAO
/*     */ {
/*     */   public static List<ZTCategoryBean> getALlZTCategoryList()
/*     */   {
/*  32 */     return DBManager.queryFList("getALlZTCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryCount(String con)
/*     */   {
/*  42 */     return DBManager.getString("getZTCategoryCount", con);
/*     */   }
/*     */ 
/*     */   public static ZTCategoryBean getZRCategoryBean(int id)
/*     */   {
/*  52 */     return (ZTCategoryBean)DBManager.queryFObj("getZRCategoryBean", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static boolean insertZTCategory(ZTCategoryBean zb, SettingLogsBean stl)
/*     */   {
/*  63 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_ZT_CATEGORY_TABLE_NAME);
/*  64 */     zb.setId(id);
/*  65 */     zb.setZt_cat_id(id);
/*  66 */     if (DBManager.insert("insert_zt_category", zb))
/*     */     {
/*  68 */       PublicTableDAO.insertSettingLogs("添加", "专题分类", id, stl);
/*  69 */       return true;
/*     */     }
/*     */ 
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateZTCategory(ZTCategoryBean zb, SettingLogsBean stl)
/*     */   {
/*  83 */     if (DBManager.update("update_zt_category", zb))
/*     */     {
/*  85 */       PublicTableDAO.insertSettingLogs("修改", "专题分类", zb.getId(), stl);
/*  86 */       return true;
/*     */     }
/*     */ 
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortZTCategory(String ids, SettingLogsBean stl)
/*     */   {
/* 100 */     String[] temp = ids.split(",");
/* 101 */     Map m = new HashMap();
/*     */     try {
/* 103 */       for (int i = 0; i < temp.length; i++)
/*     */       {
/* 105 */         m.put("sort_id", i + 1);
/* 106 */         m.put("id", temp[i]);
/* 107 */         DBManager.update("sort_zt_category", m);
/*     */       }
/* 109 */       PublicTableDAO.insertSettingLogs("保存排序", "专题分类", ids, stl);
/* 110 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 113 */       e.printStackTrace();
/* 114 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteZTCategory(int id, SettingLogsBean stl)
/*     */   {
/* 126 */     if (DBManager.delete("delete_zt_category", Integer.valueOf(id)))
/*     */     {
/* 128 */       PublicTableDAO.insertSettingLogs("删除", "专题分类", id, stl);
/* 129 */       return true;
/*     */     }
/*     */ 
/* 132 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.ZTCategoryDAO
 * JD-Core Version:    0.6.2
 */