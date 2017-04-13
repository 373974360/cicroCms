/*     */ package com.cicro.wcm.dao.appeal.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CategoryDao
/*     */ {
/*     */   public static List<CategoryBean> getAllApp_categroyList()
/*     */   {
/*  24 */     return DBManager.queryFList("getAllApp_categroyList", "");
/*     */   }
/*     */ 
/*     */   public static CategoryBean getapp_categoryBean(String cat_id)
/*     */   {
/*  34 */     return (CategoryBean)DBManager.queryFObj("getApp_categoryBean", cat_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertApp_categroy(CategoryBean ob, SettingLogsBean stl)
/*     */   {
/*  45 */     ob.setCat_position(ob.getCat_position() + ob.getCat_id() + "$");
/*  46 */     ob.setCat_id(ob.getCat_id());
/*     */ 
/*  48 */     if (DBManager.insert("insert_app_categroy", ob))
/*     */     {
/*  50 */       PublicTableDAO.insertSettingLogs("添加", "诉求分类", ob.getCat_id(), stl);
/*  51 */       return true;
/*     */     }
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateApp_categroy(CategoryBean ob, SettingLogsBean stl)
/*     */   {
/*  64 */     if (DBManager.update("update_app_categroy", ob))
/*     */     {
/*  66 */       PublicTableDAO.insertSettingLogs("添加", "修改", ob.getCat_id(), stl);
/*  67 */       return true;
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveApp_categroySort(String cat_id, SettingLogsBean stl)
/*     */   {
/*  81 */     if ((cat_id != null) && (!"".equals(cat_id))) {
/*     */       try
/*     */       {
/*  84 */         Map m = new HashMap();
/*  85 */         String[] tempA = cat_id.split(",");
/*  86 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  88 */           m.put("sort_id", Integer.valueOf(i + 1));
/*  89 */           m.put("cat_id", tempA[i]);
/*  90 */           DBManager.update("update_cat_id_sort", m);
/*     */         }
/*  92 */         PublicTableDAO.insertSettingLogs("保存排序", "菜单", cat_id, stl);
/*  93 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/*  96 */         e.printStackTrace();
/*  97 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean moveApp_categroy(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 111 */     if (DBManager.update("move_app_categroy", m))
/*     */     {
/* 113 */       PublicTableDAO.insertSettingLogs("移动", "诉求分类", (String)m.get("cat_id"), stl);
/* 114 */       return true;
/*     */     }
/*     */ 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteApp_categroy(String cat_id, SettingLogsBean stl)
/*     */   {
/* 128 */     Map m = new HashMap();
/* 129 */     m.put("cat_id", cat_id);
/* 130 */     if (DBManager.delete("delete_app_categroy", m))
/*     */     {
/* 134 */       PublicTableDAO.insertSettingLogs("删除", "诉求分类", cat_id, stl);
/* 135 */       return true;
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 141 */     getAllApp_categroyList();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.category.CategoryDao
 * JD-Core Version:    0.6.2
 */