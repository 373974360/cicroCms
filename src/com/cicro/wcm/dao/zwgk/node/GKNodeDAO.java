/*     */ package com.cicro.wcm.dao.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GKNodeDAO
/*     */ {
/*     */   public static List<GKNodeBean> getAllGKNodeList()
/*     */   {
/*  31 */     return DBManager.queryFList("getAllGKNodeList", "");
/*     */   }
/*     */ 
/*     */   public static GKNodeBean getGKNodeBeanByID(int id)
/*     */   {
/*  41 */     return (GKNodeBean)DBManager.queryFObj("getGKNodeBeanByID", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static GKNodeBean getGKNodeBeanByNodeID(String node_id)
/*     */   {
/*  51 */     return (GKNodeBean)DBManager.queryFObj("getGKNodeBeanByNodeID", node_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGKNode(GKNodeBean gbn, SettingLogsBean stl)
/*     */   {
/*  62 */     if (DBManager.insert("insert_gk_node", gbn))
/*     */     {
/*  64 */       PublicTableDAO.insertSettingLogs("添加", "公开节点", gbn.getNode_id(), stl);
/*  65 */       return true;
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNode(GKNodeBean gbn, SettingLogsBean stl)
/*     */   {
/*  78 */     if (DBManager.update("update_gk_node", gbn))
/*     */     {
/*  80 */       PublicTableDAO.insertSettingLogs("修改", "公开节点", gbn.getNode_id(), stl);
/*  81 */       return true;
/*     */     }
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeSimple(GKNodeBean gbn, SettingLogsBean stl)
/*     */   {
/*  94 */     if (DBManager.update("update_gk_node_simple", gbn))
/*     */     {
/*  96 */       PublicTableDAO.insertSettingLogs("修改", "公开节点", gbn.getNode_id(), stl);
/*  97 */       return true;
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeStatus(String ids, int node_status, SettingLogsBean stl)
/*     */   {
/* 111 */     Map m = new HashMap();
/* 112 */     m.put("ids", ids);
/* 113 */     m.put("node_status", node_status);
/* 114 */     if (DBManager.update("update_gk_node_status", m))
/*     */     {
/* 116 */       PublicTableDAO.insertSettingLogs("修改", "公开节点状态", ids, stl);
/* 117 */       return true;
/*     */     }
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKNode(String ids, int nodcat_id, SettingLogsBean stl)
/*     */   {
/* 130 */     Map m = new HashMap();
/* 131 */     m.put("ids", ids);
/* 132 */     m.put("nodcat_id", nodcat_id);
/* 133 */     if (DBManager.update("move_gk_node", m))
/*     */     {
/* 135 */       PublicTableDAO.insertSettingLogs("移动", "公开节点", ids, stl);
/* 136 */       return true;
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKNode(String ids, SettingLogsBean stl)
/*     */   {
/* 149 */     if ((ids != null) && (!"".equals(ids))) {
/*     */       try
/*     */       {
/* 152 */         Map m = new HashMap();
/* 153 */         String[] tempA = ids.split(",");
/* 154 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 156 */           m.put("sort_id", i + 1);
/* 157 */           m.put("id", tempA[i]);
/* 158 */           DBManager.update("sort_gk_node", m);
/*     */         }
/* 160 */         PublicTableDAO.insertSettingLogs("保存排序", "公开节点", ids, stl);
/* 161 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 164 */         e.printStackTrace();
/* 165 */         return false;
/*     */       }
/*     */     }
/* 168 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKNode(String ids, SettingLogsBean stl)
/*     */   {
/* 179 */     Map m = new HashMap();
/* 180 */     m.put("ids", ids);
/* 181 */     m.put("node_status", "-1");
/* 182 */     if (DBManager.update("update_gk_node_status", m))
/*     */     {
/* 184 */       PublicTableDAO.insertSettingLogs("删除", "公开节点", ids, stl);
/* 185 */       return true;
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.node.GKNodeDAO
 * JD-Core Version:    0.6.2
 */