/*     */ package com.cicro.wcm.dao.zwgk.ysqgk;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkPhrasalBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class YsqgkPhrasalDAO
/*     */ {
/*     */   public static List<YsqgkPhrasalBean> getYsqgkPhrasaList()
/*     */   {
/*  39 */     return DBManager.queryFList("getYsqgkPhrasalLists", "");
/*     */   }
/*     */ 
/*     */   public static YsqgkPhrasalBean getYsqgkPhrasaBean(String gph_id)
/*     */   {
/*  49 */     return (YsqgkPhrasalBean)DBManager.queryFObj("getYsqgkPhrasalBean", gph_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertYPhrasal(YsqgkPhrasalBean ypb, SettingLogsBean stl)
/*     */   {
/*  61 */     if (DBManager.insert("insert_ysqgk_phrasal", ypb))
/*     */     {
/*  63 */       PublicTableDAO.insertSettingLogs("添加", "常用语", ypb.getGph_id(), stl);
/*  64 */       return true;
/*     */     }
/*     */ 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateYPhrasal(YsqgkPhrasalBean ypb, SettingLogsBean stl)
/*     */   {
/*  80 */     if (DBManager.update("update_ysqgk_phrasal", ypb))
/*     */     {
/*  82 */       PublicTableDAO.insertSettingLogs("修改", "常用语", ypb.getGph_id(), stl);
/*  83 */       return true;
/*     */     }
/*     */ 
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteYPhrasal(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/*  99 */     if (DBManager.delete("delete_ysqgk_phrasal", mp))
/*     */     {
/* 101 */       PublicTableDAO.insertSettingLogs("删除", "常用语", (String)mp.get("yph_id"), stl);
/* 102 */       return true;
/*     */     }
/*     */ 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveYPhrasalSort(String gph_id, SettingLogsBean stl)
/*     */   {
/* 119 */     if ((gph_id != null) && (!"".equals(gph_id))) {
/*     */       try
/*     */       {
/* 122 */         Map m = new HashMap();
/* 123 */         String[] tempA = gph_id.split(",");
/* 124 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 126 */           m.put("sort_id", Integer.valueOf(i + 1));
/* 127 */           m.put("gph_id", tempA[i]);
/* 128 */           DBManager.update("update_ysqgk_sort", m);
/*     */         }
/* 130 */         PublicTableDAO.insertSettingLogs("保存排序", "常用语", gph_id, stl);
/* 131 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 134 */         e.printStackTrace();
/* 135 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 140 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.ysqgk.YsqgkPhrasalDAO
 * JD-Core Version:    0.6.2
 */