/*     */ package com.cicro.wcm.services.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.node.GKNodeDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GKNodeManager
/*     */   implements ISyncCatch
/*     */ {
/*  31 */   private static Map<String, GKNodeBean> node_map = new HashMap();
/*     */ 
/*     */   static {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  39 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  44 */     node_map.clear();
/*  45 */     List l = GKNodeDAO.getAllGKNodeList();
/*  46 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  48 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  50 */         node_map.put(((GKNodeBean)l.get(i)).getNode_id(), (GKNodeBean)l.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadGKNode()
/*     */   {
/*  57 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.zwgk.node.GKNodeManager");
/*     */   }
/*     */ 
/*     */   public static List<GKNodeBean> getAllGKNodeList()
/*     */   {
/*  67 */     List l = new ArrayList();
/*  68 */     Set s = node_map.keySet();
/*  69 */     if ((s != null) && (s.size() > 0))
/*     */     {
/*  71 */       for (String i : s)
/*     */       {
/*  73 */         l.add((GKNodeBean)node_map.get(i));
/*     */       }
/*     */     }
/*  76 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<GKNodeBean> getGKNodeListByCateID(int nodcat_id)
/*     */   {
/*  86 */     List l = new ArrayList();
/*  87 */     Set s = node_map.keySet();
/*  88 */     for (String i : s)
/*     */     {
/*  90 */       GKNodeBean gnb = (GKNodeBean)node_map.get(i);
/*  91 */       if (nodcat_id == gnb.getNodcat_id())
/*     */       {
/*  93 */         l.add(gnb);
/*     */       }
/*     */     }
/*  96 */     if ((l != null) && (l.size() > 0))
/*  97 */       Collections.sort(l, new GKNodeManager.GKNodeComparator());
/*  98 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getGKNodeJSONStrByCateID(int nodcat_id)
/*     */   {
/* 108 */     String json_str = "";
/* 109 */     List l = getGKNodeListByCateID(nodcat_id);
/*     */ 
/* 111 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 114 */       int tree_node_id = Integer.parseInt(nodcat_id + 10000);
/* 115 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 117 */         if (i > 0)
/* 118 */           json_str = json_str + ",";
/* 119 */         json_str = json_str + "{\"id\":" + tree_node_id + ",\"iconCls\":\"icon-gknode\",\"text\":\"" + ((GKNodeBean)l.get(i)).getNode_name() + "\",\"attributes\":{\"t_node_id\":\"" + ((GKNodeBean)l.get(i)).getNode_id() + "\",\"url\":\"\",\"handl\":\"\"}}";
/* 120 */         tree_node_id++;
/*     */       }
/*     */     }
/*     */ 
/* 124 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static boolean hasNodeByCatID(int nodcat_id)
/*     */   {
/* 134 */     Set s = node_map.keySet();
/* 135 */     for (String i : s)
/*     */     {
/* 137 */       GKNodeBean gnb = (GKNodeBean)node_map.get(i);
/* 138 */       if (nodcat_id == gnb.getNodcat_id())
/*     */       {
/* 140 */         return true;
/*     */       }
/*     */     }
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean nodeIdIsExist(String node_id)
/*     */   {
/* 152 */     return node_map.containsKey(node_id);
/*     */   }
/*     */ 
/*     */   public static GKNodeBean getGKNodeBeanByID(int id)
/*     */   {
/* 163 */     Set s = node_map.keySet();
/* 164 */     for (String i : s)
/*     */     {
/* 166 */       GKNodeBean gnb = (GKNodeBean)node_map.get(i);
/* 167 */       if (gnb.getId() == id)
/* 168 */         return gnb;
/*     */     }
/* 170 */     GKNodeBean gnb = GKNodeDAO.getGKNodeBeanByID(id);
/* 171 */     if (gnb != null)
/*     */     {
/* 173 */       node_map.put(gnb.getNode_id(), gnb);
/* 174 */       return gnb;
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   public static GKNodeBean getGKNodeBeanByNodeID(String node_id)
/*     */   {
/* 186 */     if (node_map.containsKey(node_id))
/*     */     {
/* 188 */       return (GKNodeBean)node_map.get(node_id);
/*     */     }
/*     */ 
/* 191 */     GKNodeBean gnb = GKNodeDAO.getGKNodeBeanByNodeID(node_id);
/* 192 */     if (gnb != null)
/*     */     {
/* 194 */       node_map.put(node_id, gnb);
/* 195 */       return gnb;
/*     */     }
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getNodeNameByNodeID(String node_id)
/*     */   {
/* 208 */     GKNodeBean gb = getGKNodeBeanByNodeID(node_id);
/* 209 */     if (gb != null) {
/* 210 */       return gb.getNode_name();
/*     */     }
/* 212 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getMutilNodeNames(String site_ids)
/*     */   {
/* 222 */     String names = "";
/* 223 */     if ((site_ids != null) && (!"".equals(site_ids)))
/*     */     {
/* 225 */       String[] tempA = site_ids.split(",");
/* 226 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 228 */         String s = getNodeNameByNodeID(tempA[i]);
/* 229 */         if ((s != null) && (!"".equals(s)))
/* 230 */           names = names + "," + s;
/*     */       }
/* 232 */       if ((names != null) && (!"".equals(names)))
/* 233 */         names = names.substring(1);
/*     */     }
/* 235 */     return names;
/*     */   }
/*     */ 
/*     */   public static boolean insertGKNode(GKNodeBean gbn, SettingLogsBean stl)
/*     */   {
/* 246 */     gbn.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.GK_NODE));
/* 247 */     if (GKNodeDAO.insertGKNode(gbn, stl))
/*     */     {
/* 249 */       CategoryManager.insertGKDefaultCategory(gbn.getNode_id());
/* 250 */       reloadGKNode();
/* 251 */       return true;
/*     */     }
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNode(GKNodeBean gbn, SettingLogsBean stl)
/*     */   {
/* 264 */     if (GKNodeDAO.updateGKNode(gbn, stl))
/*     */     {
/* 266 */       reloadGKNode();
/* 267 */       return true;
/*     */     }
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeSimple(GKNodeBean gbn, SettingLogsBean stl)
/*     */   {
/* 280 */     if (GKNodeDAO.updateGKNodeSimple(gbn, stl))
/*     */     {
/* 282 */       reloadGKNode();
/* 283 */       return true;
/*     */     }
/* 285 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeStatus(String ids, int node_status, SettingLogsBean stl)
/*     */   {
/* 297 */     if (GKNodeDAO.updateGKNodeStatus(ids, node_status, stl))
/*     */     {
/* 299 */       reloadGKNode();
/* 300 */       return true;
/*     */     }
/* 302 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKNode(String ids, int nodcat_id, SettingLogsBean stl)
/*     */   {
/* 313 */     if (GKNodeDAO.moveGKNode(ids, nodcat_id, stl))
/*     */     {
/* 315 */       reloadGKNode();
/* 316 */       return true;
/*     */     }
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKNode(String ids, SettingLogsBean stl)
/*     */   {
/* 329 */     if (GKNodeDAO.sortGKNode(ids, stl))
/*     */     {
/* 331 */       reloadGKNode();
/* 332 */       return true;
/*     */     }
/* 334 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKNode(String ids, SettingLogsBean stl)
/*     */   {
/* 345 */     if (GKNodeDAO.deleteGKNode(ids, stl))
/*     */     {
/* 347 */       reloadGKNode();
/* 348 */       return true;
/*     */     }
/* 350 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 372 */     System.out.println(getGKNodeJSONStrByCateID(0));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.node.GKNodeManager
 * JD-Core Version:    0.6.2
 */