/*     */ package com.cicro.wcm.dao.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeCategory;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GKNodeCategoryDAO
/*     */ {
/*     */   public static List<GKNodeCategory> getAllNodeCategoryList()
/*     */   {
/*  31 */     return DBManager.queryFList("getAllNodeCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static GKNodeCategory getNodeCategoryBean(int id)
/*     */   {
/*  41 */     return (GKNodeCategory)DBManager.queryFObj("getNodeCategoryBean", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static boolean insertGKNodeCategory(GKNodeCategory gnc, SettingLogsBean stl)
/*     */   {
/*  52 */     if (DBManager.insert("insert_node_category", gnc))
/*     */     {
/*  54 */       PublicTableDAO.insertSettingLogs("添加", "公开节点分类", gnc.getNodcat_id(), stl);
/*  55 */       return true;
/*     */     }
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeCategory(GKNodeCategory gnc, SettingLogsBean stl)
/*     */   {
/*  68 */     if (DBManager.update("update_node_category", gnc))
/*     */     {
/*  70 */       PublicTableDAO.insertSettingLogs("修改", "公开节点分类", gnc.getNodcat_id(), stl);
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKNodeCategory(Map<String, String> m)
/*     */   {
/*  83 */     if (DBManager.update("move_node_category", m)) {
/*  84 */       return true;
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKNodeCategory(String nodcat_ids, SettingLogsBean stl)
/*     */   {
/*  97 */     if ((nodcat_ids != null) && (!"".equals(nodcat_ids))) {
/*     */       try
/*     */       {
/* 100 */         Map m = new HashMap();
/* 101 */         String[] tempA = nodcat_ids.split(",");
/* 102 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 104 */           m.put("sort_id", i + 1);
/* 105 */           m.put("nodcat_id", tempA[i]);
/* 106 */           DBManager.update("sort_node_category", m);
/*     */         }
/* 108 */         PublicTableDAO.insertSettingLogs("保存排序", "公开节点分类", nodcat_ids, stl);
/* 109 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 112 */         e.printStackTrace();
/* 113 */         return false;
/*     */       }
/*     */     }
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKNodeCategory(String nodcat_ids, SettingLogsBean stl)
/*     */   {
/* 127 */     Map m = new HashMap();
/* 128 */     m.put("nodcat_ids", nodcat_ids);
/* 129 */     if (DBManager.delete("delete_node_category", m))
/*     */     {
/* 131 */       PublicTableDAO.insertSettingLogs("删除", "公开节点分类", nodcat_ids, stl);
/* 132 */       return true;
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.node.GKNodeCategoryDAO
 * JD-Core Version:    0.6.2
 */