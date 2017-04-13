/*     */ package com.cicro.wcm.dao.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CateClassBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CateClassDao
/*     */ {
/*     */   private static final String INSERT_OPERATE = "添加";
/*     */   private static final String UPDATE_OPERATE = "修改";
/*     */   private static final String DELETE_OPERATE = "删除";
/*     */   private static final String TABLE_NAME = "分类方式";
/*     */ 
/*     */   public static List<CateClassBean> getAllCateClassList()
/*     */   {
/*  38 */     return DBManager.queryFList("getAllCateClassList", "");
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getCateClassListByApp(String app_id)
/*     */   {
/*  50 */     return DBManager.queryFList("getCateClassListByApp", app_id);
/*     */   }
/*     */ 
/*     */   public static CateClassBean getCateClassBeanByID(String id)
/*     */   {
/*  61 */     return (CateClassBean)DBManager.queryFObj("getCateClassBeanByID", id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCateClass(CateClassBean ccb, SettingLogsBean stl)
/*     */   {
/*  73 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CLASS_TABLE_NAME);
/*  74 */     ccb.setClass_id(id);
/*  75 */     if (DBManager.insert("insertCateClass", ccb))
/*     */     {
/*  77 */       PublicTableDAO.insertSettingLogs("添加", "分类方式", id, stl);
/*  78 */       return true;
/*     */     }
/*     */ 
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCateClass(CateClassBean ccb, SettingLogsBean stl)
/*     */   {
/*  95 */     if (DBManager.update("updateCateClass", ccb))
/*     */     {
/*  97 */       PublicTableDAO.insertSettingLogs("修改", "分类方式", ccb.getClass_id(), stl);
/*  98 */       return true;
/*     */     }
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCateClass(String ids, SettingLogsBean stl)
/*     */   {
/* 116 */     Map mp = new HashMap();
/* 117 */     mp.put("class_ids", ids);
/* 118 */     if (DBManager.update("deleteCateClass", mp))
/*     */     {
/* 120 */       PublicTableDAO.insertSettingLogs("删除", "分类方式", ids, stl);
/* 121 */       return true;
/*     */     }
/*     */ 
/* 125 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.category.CateClassDao
 * JD-Core Version:    0.6.2
 */