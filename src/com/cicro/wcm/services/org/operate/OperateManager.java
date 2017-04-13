/*     */ package com.cicro.wcm.services.org.operate;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.org.operate.MenuBean;
/*     */ import com.cicro.wcm.bean.org.operate.OperateBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.operate.OperateDAO;
/*     */ import com.cicro.wcm.server.LicenseCheck;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class OperateManager
/*     */   implements ISyncCatch
/*     */ {
/*  36 */   private static TreeMap<String, OperateBean> opt_map = new TreeMap();
/*  37 */   private static String root_operate_id = "1";
/*  38 */   private static String cms_operate_id = JconfigUtilContainer.systemRole().getProperty("cms_operate", "", "opt_id");
/*     */ 
/*  40 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  45 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  50 */     List opt_list = OperateDAO.getAllOperateList();
/*     */ 
/*  52 */     opt_map.clear();
/*  53 */     if ((opt_list != null) && (opt_list.size() > 0))
/*  54 */       for (OperateBean ob : opt_list)
/*  55 */         if (LicenseCheck.isHaveApp(ob.getApp_id()))
/*  56 */           opt_map.put(ob.getOpt_id(), ob);
/*     */   }
/*     */ 
/*     */   public static void reloadOperate()
/*     */   {
/*  68 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.operate.OperateManager");
/*     */   }
/*     */ 
/*     */   public static Map<String, OperateBean> getOptMap()
/*     */   {
/*  78 */     return opt_map;
/*     */   }
/*     */ 
/*     */   public static int getOperateID()
/*     */   {
/*  88 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.OPT_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static String getOperateTreeJsonStr(String app_ids)
/*     */   {
/*  98 */     OperateBean op = getOperateBean(root_operate_id);
/*  99 */     if (op != null)
/*     */     {
/* 101 */       String child_str = "";
/* 102 */       String json_str = "[{\"id\":" + root_operate_id + ",\"text\":\"" + op.getOpt_name() + "\"";
/* 103 */       if (("".equals(app_ids)) || ("system".equals(app_ids))) {
/* 104 */         child_str = getOperateTreeJsonStrHandl(getChildOptList(root_operate_id));
/*     */       }
/* 107 */       else if ("zwgk".equals(app_ids))
/*     */       {
/* 109 */         child_str = getZWGKOpterTreeJsonStrHandl(getChildOptList(root_operate_id, app_ids));
/*     */       }
/* 111 */       else child_str = getOperateTreeJsonStrHandl(getChildOptList(root_operate_id, app_ids));
/*     */ 
/* 113 */       if ((child_str != null) && (!"".equals(child_str)))
/* 114 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/* 115 */       json_str = json_str + "}]";
/* 116 */       return json_str;
/*     */     }
/* 118 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getOperateTreeJsonStr2(String app_ids)
/*     */   {
/* 128 */     OperateBean op = getOperateBean(root_operate_id);
/* 129 */     if (op != null)
/*     */     {
/* 131 */       String child_str = "";
/* 132 */       String json_str = "[";
/* 133 */       child_str = getOperateTreeJsonStrHandl(getChildOptList(root_operate_id, app_ids));
/* 134 */       if ((child_str != null) && (!"".equals(child_str)))
/* 135 */         json_str = json_str + child_str;
/* 136 */       json_str = json_str + "]";
/* 137 */       return json_str;
/*     */     }
/* 139 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getZWGKOpterTreeJsonStrHandl(List<OperateBean> all_oper_list)
/*     */   {
/* 145 */     String json_str = "";
/* 146 */     if ((all_oper_list != null) && (all_oper_list.size() > 0))
/*     */     {
/* 148 */       for (int i = 0; i < all_oper_list.size(); i++)
/*     */       {
/* 150 */         json_str = json_str + "{";
/* 151 */         json_str = json_str + "\"id\":" + ((OperateBean)all_oper_list.get(i)).getOpt_id() + ",\"text\":\"" + ((OperateBean)all_oper_list.get(i)).getOpt_name() + "\"";
/* 152 */         List child_o_list = getChildOptList(((OperateBean)all_oper_list.get(i)).getOpt_id());
/* 153 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/* 154 */           json_str = json_str + ",\"children\":[" + getOperateTreeJsonStrHandl(child_o_list) + "," + getCMSOperateTreeJsonStr() + "]";
/* 155 */         json_str = json_str + "}";
/* 156 */         if (i + 1 != all_oper_list.size()) {
/* 157 */           json_str = json_str + ",";
/*     */         }
/*     */       }
/*     */     }
/* 161 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getCMSOperateTreeJsonStr()
/*     */   {
/* 167 */     List l = new ArrayList();
/* 168 */     OperateBean ob = getOperateBean(cms_operate_id);
/* 169 */     l.add(ob);
/* 170 */     return getOperateTreeJsonStrHandl(l);
/*     */   }
/*     */ 
/*     */   public static String getOperateTreeJsonStrHandl(List<OperateBean> all_oper_list)
/*     */   {
/* 180 */     String json_str = "";
/* 181 */     if ((all_oper_list != null) && (all_oper_list.size() > 0))
/*     */     {
/* 183 */       for (OperateBean ob : all_oper_list)
/*     */       {
/* 185 */         json_str = json_str + ",{";
/* 186 */         json_str = json_str + "\"id\":" + ob.getOpt_id() + ",\"text\":\"" + ob.getOpt_name() + "\"";
/* 187 */         List child_o_list = getChildOptList(ob.getOpt_id());
/* 188 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/* 189 */           json_str = json_str + ",\"children\":[" + getOperateTreeJsonStrHandl(child_o_list) + "]";
/* 190 */         json_str = json_str + "}";
/*     */       }
/* 192 */       if ((json_str != null) && (!"".equals(json_str)))
/* 193 */         json_str = json_str.substring(1);
/*     */     }
/* 195 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static OperateBean getOperateBean(String opt_id)
/*     */   {
/* 205 */     if ("0".equals(opt_id))
/* 206 */       return null;
/* 207 */     if (opt_map.containsKey(opt_id))
/*     */     {
/* 209 */       return (OperateBean)opt_map.get(opt_id);
/*     */     }
/*     */ 
/* 212 */     OperateBean ob = OperateDAO.getOperateBean(opt_id);
/* 213 */     if ((ob != null) && (LicenseCheck.isHaveApp(ob.getApp_id())))
/*     */     {
/* 215 */       opt_map.put(opt_id, ob);
/* 216 */       return ob;
/*     */     }
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getMenuBeanByOptID(String opt_id)
/*     */   {
/* 229 */     return MenuManager.getMenuBeanByOptID(opt_id);
/*     */   }
/*     */ 
/*     */   public static String getChildOptCount(String opt_id)
/*     */   {
/* 240 */     int count = 0;
/* 241 */     Iterator iter = opt_map.entrySet().iterator();
/* 242 */     while (iter.hasNext()) {
/* 243 */       Entry entry = (Entry)iter.next();
/* 244 */       String key = (String)entry.getKey();
/* 245 */       if (opt_id.equals(((OperateBean)opt_map.get(key)).getParent_id())) {
/* 246 */         count++;
/*     */       }
/*     */     }
/* 249 */     return count;
/*     */   }
/*     */ 
/*     */   public static List<OperateBean> getChildOptList(String opt_id, String app_ids)
/*     */   {
/* 260 */     List oL = new ArrayList();
/* 261 */     Iterator iter = opt_map.entrySet().iterator();
/* 262 */     while (iter.hasNext()) {
/* 263 */       Entry entry = (Entry)iter.next();
/* 264 */       String key = (String)entry.getKey();
/*     */ 
/* 266 */       if ((opt_id.equals(((OperateBean)opt_map.get(key)).getParent_id())) && (app_ids.contains(((OperateBean)opt_map.get(key)).getApp_id()))) {
/* 267 */         oL.add((OperateBean)entry.getValue());
/*     */       }
/*     */     }
/* 270 */     Collections.sort(oL, new OperateManager.OperateComparator());
/* 271 */     return oL;
/*     */   }
/*     */ 
/*     */   public static List<OperateBean> getChildOptList(String opt_id)
/*     */   {
/* 282 */     List oL = new ArrayList();
/* 283 */     Iterator iter = opt_map.entrySet().iterator();
/* 284 */     while (iter.hasNext()) {
/* 285 */       Entry entry = (Entry)iter.next();
/* 286 */       String key = (String)entry.getKey();
/*     */ 
/* 288 */       if (opt_id.equals(((OperateBean)opt_map.get(key)).getParent_id())) {
/* 289 */         oL.add((OperateBean)entry.getValue());
/*     */       }
/*     */     }
/* 292 */     Collections.sort(oL, new OperateManager.OperateComparator());
/* 293 */     return oL;
/*     */   }
/*     */ 
/*     */   public static String getALLChildOptIDSByID(String opt_ids)
/*     */   {
/* 303 */     String o_ids = "";
/* 304 */     if ((opt_ids != null) && (!"".equals(opt_ids)))
/*     */     {
/* 306 */       String[] opt_a = opt_ids.split(",");
/* 307 */       for (int i = 0; i < opt_a.length; i++)
/*     */       {
/* 309 */         OperateBean ob = getOperateBean(opt_a[i]);
/* 310 */         if (ob != null)
/*     */         {
/* 312 */           String tree_position = ob.getTree_position();
/* 313 */           Set set = opt_map.keySet();
/* 314 */           for (String j : set) {
/* 315 */             ob = (OperateBean)opt_map.get(j);
/* 316 */             if ((ob.getTree_position().startsWith(tree_position)) && (!tree_position.equals(ob.getTree_position())))
/* 317 */               o_ids = o_ids + "," + ob.getOpt_id();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 322 */     return o_ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertOperate(OperateBean ob, SettingLogsBean stl)
/*     */   {
/* 333 */     ob.setTree_position(getOperateBean(ob.getParent_id()).getTree_position());
/* 334 */     if (OperateDAO.insertOperate(ob, stl))
/*     */     {
/* 336 */       reloadOperate();
/* 337 */       return true;
/*     */     }
/* 339 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateOperate(OperateBean ob, SettingLogsBean stl)
/*     */   {
/* 352 */     if (OperateDAO.updateOperate(ob, stl))
/*     */     {
/* 354 */       reloadOperate();
/* 355 */       return true;
/*     */     }
/* 357 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveOperate(String parent_id, String opt_ids, SettingLogsBean stl)
/*     */   {
/* 369 */     String parent_tree_position = getOperateBean(parent_id).getTree_position();
/* 370 */     if ((opt_ids != null) && (!"".equals(opt_ids))) {
/*     */       try
/*     */       {
/* 373 */         String[] tempA = opt_ids.split(",");
/* 374 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 376 */           moveOperateHandl(tempA[i], parent_id, parent_tree_position, stl);
/*     */         }
/* 378 */         reloadOperate();
/* 379 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 382 */         e.printStackTrace();
/* 383 */         return false;
/*     */       }
/*     */     }
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getOptAppListbyRoleID(String role_ids)
/*     */   {
/* 396 */     return OperateDAO.getOptAppListbyRoleID(role_ids);
/*     */   }
/*     */ 
/*     */   public static void moveOperateHandl(String opt_id, String parent_id, String tree_position, SettingLogsBean stl)
/*     */   {
/* 401 */     String position = tree_position + opt_id + "$";
/* 402 */     Map new_m = new HashMap();
/* 403 */     new_m.put("opt_id", opt_id);
/* 404 */     new_m.put("parent_id", parent_id);
/* 405 */     new_m.put("tree_position", position);
/* 406 */     if (OperateDAO.moveOperate(new_m, stl))
/*     */     {
/* 408 */       List o_list = getChildOptList(opt_id);
/* 409 */       if ((o_list != null) && (o_list.size() > 0))
/*     */       {
/* 411 */         for (int i = 0; i < o_list.size(); i++)
/*     */         {
/* 413 */           moveOperateHandl(((OperateBean)o_list.get(i)).getOpt_id(), opt_id, position, stl);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean deleteOperate(String opt_id, SettingLogsBean stl)
/*     */   {
/* 427 */     opt_id = opt_id + getALLChildOptIDSByID(opt_id);
/* 428 */     if (OperateDAO.deleteOperate(opt_id, stl))
/*     */     {
/* 430 */       reloadOperate();
/* 431 */       return true;
/*     */     }
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 458 */     System.out.println(getOperateTreeJsonStr("zwgk"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.operate.OperateManager
 * JD-Core Version:    0.6.2
 */