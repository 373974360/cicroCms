/*     */ package com.cicro.wcm.services.extendfunction.knowledgeTab;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class KnowledgeCateDAO
/*     */ {
/*  11 */   private static String table_name = "cs_dz_knowledgecate";
/*     */ 
/*     */   public static List<KnowledgeCateBean> getKCategoryList()
/*     */   {
/*  20 */     return DBManager.queryFList("knowledge.getKCataList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertKCate(KnowledgeCateBean wcb, SettingLogsBean stl)
/*     */   {
/*  31 */     int id = PublicTableDAO.getIDByTableName(table_name);
/*  32 */     wcb.setId(id);
/*  33 */     wcb.setKcat_id(id+"");
/*  34 */     wcb.setApp_id("cms");
/*     */ 
/*  36 */     if (wcb.getKcat_position() == "")
/*     */     {
/*  38 */       wcb.setKcat_position("$" + id + "$");
/*     */     }
/*  40 */     else wcb.setKcat_position(wcb.getKcat_position() + id + "$");
/*     */ 
/*  42 */     if (DBManager.insert("knowledge.insertKCategory", wcb))
/*     */     {
/*  44 */       PublicTableDAO.insertSettingLogs("添加", "知识库标签分类", id+"", stl);
/*  45 */       return true;
/*     */     }
/*     */ 
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateKCate(KnowledgeCateBean wcb, SettingLogsBean stl)
/*     */   {
/*  61 */     if (DBManager.update("knowledge.updateKCategory", wcb))
/*     */     {
/*  63 */       PublicTableDAO.insertSettingLogs("修改", "知识库标签分类", wcb.getId()+"", stl);
/*  64 */       return true;
/*     */     }
/*     */ 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveKCateSort(KnowledgeCateBean wcb, SettingLogsBean stl)
/*     */   {
/*  80 */     if (DBManager.update("knowledge.saveKCateSort", wcb))
/*     */     {
/*  82 */       PublicTableDAO.insertSettingLogs("修改", "知识库标签分类排序", wcb.getId()+"", stl);
/*  83 */       return true;
/*     */     }
/*     */ 
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteKCate(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/*  99 */     if (DBManager.update("knowledge.deleteKCategory", mp))
/*     */     {
/* 101 */       PublicTableDAO.insertSettingLogs("删除", "知识库标签分类", (String)mp.get("id"), stl);
/* 102 */       return true;
/*     */     }
/*     */ 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] aeg)
/*     */   {
/* 112 */     List lt = getKCategoryList();
/* 113 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.knowledgeTab.KnowledgeCateDAO
 * JD-Core Version:    0.6.2
 */