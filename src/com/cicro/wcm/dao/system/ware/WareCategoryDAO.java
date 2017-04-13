/*     */ package com.cicro.wcm.dao.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareCategoryBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WareCategoryDAO
/*     */ {
/*     */   public static List<WareCategoryBean> getWareCataListBySiteID(String site_id)
/*     */   {
/*  21 */     return DBManager.queryFList("getWareCataListBySiteID", site_id);
/*     */   }
/*     */ 
/*     */   public static List<WareCategoryBean> getWareCategoryList()
/*     */   {
/*  31 */     return DBManager.queryFList("getWareCataList", "");
/*     */   }
/*     */ 
/*     */   public static boolean cloneWareCate(WareCategoryBean wcb)
/*     */   {
/*  41 */     String id = PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_CATEGORY_TABLE_NAME);
/*  42 */     wcb.setId(id);
/*  43 */     return DBManager.insert("insertWareCategory", wcb);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareCate(WareCategoryBean wcb, SettingLogsBean stl)
/*     */   {
/*  54 */     String id = PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_CATEGORY_TABLE_NAME);
/*  55 */     wcb.setId(id);
/*  56 */     wcb.setWcat_id(id);
/*  57 */     wcb.setWcat_position(wcb.getWcat_position() + "$" + id + "$");
/*  58 */     if (DBManager.insert("insertWareCategory", wcb))
/*     */     {
/*  60 */       PublicTableDAO.insertSettingLogs("添加", "信息标签分类", id, stl);
/*  61 */       return true;
/*     */     }
/*     */ 
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareCate(WareCategoryBean wcb, SettingLogsBean stl)
/*     */   {
/*  77 */     if (DBManager.update("updateWareCategory", wcb))
/*     */     {
/*  79 */       PublicTableDAO.insertSettingLogs("修改", "信息标签分类", wcb.getId(), stl);
/*  80 */       return true;
/*     */     }
/*     */ 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveWareCateSort(WareCategoryBean wcb, SettingLogsBean stl)
/*     */   {
/*  96 */     if (DBManager.update("saveWareCateSort", wcb))
/*     */     {
/*  98 */       PublicTableDAO.insertSettingLogs("修改", "信息标签分类排序", wcb.getId(), stl);
/*  99 */       return true;
/*     */     }
/*     */ 
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareCate(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 115 */     if (DBManager.update("deleteWareCategory", mp))
/*     */     {
/* 117 */       PublicTableDAO.insertSettingLogs("删除", "信息标签分类", (String)mp.get("id"), stl);
/* 118 */       return true;
/*     */     }
/*     */ 
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] aeg)
/*     */   {
/* 128 */     List lt = getWareCategoryList();
/* 129 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.ware.WareCategoryDAO
 * JD-Core Version:    0.6.2
 */