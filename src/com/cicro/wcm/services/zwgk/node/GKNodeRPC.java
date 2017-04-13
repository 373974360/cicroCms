/*     */ package com.cicro.wcm.services.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeCategory;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class GKNodeRPC
/*     */ {
/*     */   public static int getNewNodCatID()
/*     */   {
/*  20 */     return GKNodeCateManager.getNewNodCatID();
/*     */   }
/*     */ 
/*     */   public static String getGKNodeTreebyCateID(int nodcat_id)
/*     */   {
/*  30 */     return GKNodeCateManager.getGKNodeTreebyCateID(nodcat_id);
/*     */   }
/*     */ 
/*     */   public static String getGKNodeCategroyJSONROOTTreeStr()
/*     */   {
/*  39 */     return GKNodeCateManager.getGKNodeCategroyJSONROOTTreeStr();
/*     */   }
/*     */ 
/*     */   public static String getGKNodeCategroyJSONTreeStr()
/*     */   {
/*  48 */     return GKNodeCateManager.getGKNodeCategroyJSONTreeStr();
/*     */   }
/*     */ 
/*     */   public static List<GKNodeCategory> getChildCategoryList(int nodcat_id)
/*     */   {
/*  58 */     return GKNodeCateManager.getChildCategoryList(nodcat_id);
/*     */   }
/*     */ 
/*     */   public static GKNodeCategory getNodeCategoryBean(int id)
/*     */   {
/*  68 */     return GKNodeCateManager.getNodeCategoryBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGKNodeCategory(GKNodeCategory gnc, HttpServletRequest request)
/*     */   {
/*  79 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  80 */     if (stl != null)
/*     */     {
/*  82 */       return GKNodeCateManager.insertGKNodeCategory(gnc, stl);
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeCategory(GKNodeCategory gnc, HttpServletRequest request)
/*     */   {
/*  95 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  96 */     if (stl != null)
/*     */     {
/*  98 */       return GKNodeCateManager.updateGKNodeCategory(gnc, stl);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKNodeCategory(String nodcat_ids, int parent_id, HttpServletRequest request)
/*     */   {
/* 111 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 112 */     if (stl != null)
/*     */     {
/* 114 */       return GKNodeCateManager.moveGKNodeCategory(nodcat_ids, parent_id, stl);
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKNodeCategory(String nodcat_ids, HttpServletRequest request)
/*     */   {
/* 127 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 128 */     if (stl != null)
/*     */     {
/* 130 */       return GKNodeCateManager.sortGKNodeCategory(nodcat_ids, stl);
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean hasChildNodeByCategory(int nodcat_id)
/*     */   {
/* 142 */     return GKNodeCateManager.hasChildNodeByCategory(nodcat_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKNodeCategory(String nodcat_ids, HttpServletRequest request)
/*     */   {
/* 153 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 154 */     if (stl != null)
/*     */     {
/* 156 */       return GKNodeCateManager.deleteGKNodeCategory(nodcat_ids, stl);
/*     */     }
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GKNodeBean> getAllGKNodeList()
/*     */   {
/* 170 */     return GKNodeManager.getAllGKNodeList();
/*     */   }
/*     */ 
/*     */   public static List<GKNodeBean> getGKNodeListByCateID(int nodcat_id)
/*     */   {
/* 179 */     return GKNodeManager.getGKNodeListByCateID(nodcat_id);
/*     */   }
/*     */ 
/*     */   public static String getNodeNameByNodeID(String node_id)
/*     */   {
/* 189 */     return GKNodeManager.getNodeNameByNodeID(node_id);
/*     */   }
/*     */ 
/*     */   public static boolean hasNodeByCatID(int nodcat_id)
/*     */   {
/* 199 */     return GKNodeManager.hasNodeByCatID(nodcat_id);
/*     */   }
/*     */ 
/*     */   public static boolean nodeIdIsExist(String node_id)
/*     */   {
/* 208 */     return GKNodeManager.nodeIdIsExist(node_id);
/*     */   }
/*     */ 
/*     */   public static GKNodeBean getGKNodeBeanByID(int id)
/*     */   {
/* 218 */     return GKNodeManager.getGKNodeBeanByID(id);
/*     */   }
/*     */ 
/*     */   public static GKNodeBean getGKNodeBeanByNodeID(String node_id)
/*     */   {
/* 228 */     return GKNodeManager.getGKNodeBeanByNodeID(node_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGKNode(GKNodeBean gbn, HttpServletRequest request)
/*     */   {
/* 239 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 240 */     if (stl != null)
/*     */     {
/* 242 */       return GKNodeManager.insertGKNode(gbn, stl);
/*     */     }
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNode(GKNodeBean gbn, HttpServletRequest request)
/*     */   {
/* 255 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 256 */     if (stl != null)
/*     */     {
/* 258 */       return GKNodeManager.updateGKNode(gbn, stl);
/*     */     }
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeSimple(GKNodeBean gbn, HttpServletRequest request)
/*     */   {
/* 271 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 272 */     if (stl != null)
/*     */     {
/* 274 */       return GKNodeManager.updateGKNodeSimple(gbn, stl);
/*     */     }
/* 276 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeStatus(String ids, int node_status, HttpServletRequest request)
/*     */   {
/* 288 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 289 */     if (stl != null)
/*     */     {
/* 291 */       return GKNodeManager.updateGKNodeStatus(ids, node_status, stl);
/*     */     }
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKNode(String ids, int nodcat_id, HttpServletRequest request)
/*     */   {
/* 304 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 305 */     if (stl != null)
/*     */     {
/* 307 */       return GKNodeManager.moveGKNode(ids, nodcat_id, stl);
/*     */     }
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKNode(String ids, HttpServletRequest request)
/*     */   {
/* 320 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 321 */     if (stl != null)
/*     */     {
/* 323 */       return GKNodeManager.sortGKNode(ids, stl);
/*     */     }
/* 325 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKNode(String ids, HttpServletRequest request)
/*     */   {
/* 336 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 337 */     if (stl != null)
/*     */     {
/* 339 */       return GKNodeManager.deleteGKNode(ids, stl);
/*     */     }
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 346 */     System.out.println(getGKNodeCategroyJSONTreeStr());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.node.GKNodeRPC
 * JD-Core Version:    0.6.2
 */