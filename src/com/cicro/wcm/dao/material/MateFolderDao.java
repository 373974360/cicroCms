/*     */ package com.cicro.wcm.dao.material;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.material.MateFolderBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MateFolderDao
/*     */ {
/*     */   public static List<MateFolderBean> getMateFolderBeanList()
/*     */   {
/*  27 */     return DBManager.queryFList("getMateFolderList", "");
/*     */   }
/*     */ 
/*     */   public static MateFolderBean getMateFolderBean(String f_id)
/*     */   {
/*  36 */     return (MateFolderBean)DBManager.queryFObj("getMateFolderBean", f_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMateFolder(MateFolderBean mf, SettingLogsBean stl)
/*     */   {
/*  46 */     if (DBManager.insert("insert_MateFolder", mf))
/*     */     {
/*  48 */       PublicTableDAO.insertSettingLogs("添加", "文件夹", mf.getClass(), stl);
/*  49 */       return true;
/*     */     }
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMateFolder(MateFolderBean mf, SettingLogsBean stl)
/*     */   {
/*  61 */     mf.setApp_id("cms");
/*  62 */     if (DBManager.update("update_MateFolder", mf))
/*     */     {
/*  64 */       PublicTableDAO.insertSettingLogs("添加", "修改", mf.getF_id(), stl);
/*  65 */       return true;
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMateFolder(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  77 */     if (DBManager.update("move_MateFolder", m))
/*     */     {
/*  79 */       PublicTableDAO.insertSettingLogs("移动", "文件夹", (String)m.get("f_id"), stl);
/*  80 */       return true;
/*     */     }
/*     */ 
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMateFolder(String f_id, SettingLogsBean stl)
/*     */   {
/*  93 */     Map m = new HashMap();
/*  94 */     m.put("f_id", f_id);
/*  95 */     if (DBManager.delete("delete_MateFolder", m))
/*     */     {
/*  99 */       PublicTableDAO.insertSettingLogs("删除", "文件夹", f_id, stl);
/* 100 */       return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.material.MateFolderDao
 * JD-Core Version:    0.6.2
 */