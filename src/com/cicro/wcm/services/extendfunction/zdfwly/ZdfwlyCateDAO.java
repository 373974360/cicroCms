/*     */ package com.cicro.wcm.services.extendfunction.zdfwly;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ZdfwlyCateDAO
/*     */ {
/*  11 */   private static String table_name = "cs_dz_zdfwlycate";
/*     */ 
/*     */   public static List<ZdfwlyCateBean> getZdCategoryList()
/*     */   {
/*  20 */     return DBManager.queryFList("zdfwly.getZDCateList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertZdCate(ZdfwlyCateBean wcb, SettingLogsBean stl)
/*     */   {
/*  31 */     int id = PublicTableDAO.getIDByTableName(table_name);
/*  32 */     wcb.setId(id);
/*  33 */     wcb.setZdcat_id(id+"");
/*  34 */     wcb.setApp_id("cms");
/*     */ 
/*  36 */     if (wcb.getZdcat_position() == "")
/*     */     {
/*  38 */       wcb.setZdcat_position("$" + id + "$");
/*     */     }
/*  40 */     else wcb.setZdcat_position(wcb.getZdcat_position() + id + "$");
/*     */ 
/*  42 */     if (DBManager.insert("zdfwly.insertZdCategory", wcb))
/*     */     {
/*  44 */       PublicTableDAO.insertSettingLogs("添加", "重点服务领域分类", id + "", stl);
/*  45 */       return true;
/*     */     }
/*     */ 
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateZdCate(ZdfwlyCateBean wcb, SettingLogsBean stl)
/*     */   {
/*  61 */     if (DBManager.update("zdfwly.updateZdCategory", wcb))
/*     */     {
/*  63 */       PublicTableDAO.insertSettingLogs("修改", "重点服务领域分类", wcb.getId()+"", stl);
/*  64 */       return true;
/*     */     }
/*     */ 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveZdCateSort(ZdfwlyCateBean wcb, SettingLogsBean stl)
/*     */   {
/*  80 */     if (DBManager.update("zdfwly.saveZdCateSort", wcb))
/*     */     {
/*  82 */       PublicTableDAO.insertSettingLogs("修改", "重点服务领域分类排序", wcb.getId()+"", stl);
/*  83 */       return true;
/*     */     }
/*     */ 
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteZdCate(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/*  99 */     if (DBManager.update("zdfwly.deleteZdCategory", mp))
/*     */     {
/* 101 */       PublicTableDAO.insertSettingLogs("删除", "重点服务领域分类", (String)mp.get("id"), stl);
/* 102 */       return true;
/*     */     }
/*     */ 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] aeg)
/*     */   {
/* 112 */     List lt = getZdCategoryList();
/* 113 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateDAO
 * JD-Core Version:    0.6.2
 */