/*     */ package com.cicro.wcm.services.org.operate;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.operate.MenuBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.operate.MenuDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class MenuManager
/*     */   implements ISyncCatch
/*     */ {
/*  24 */   private static TreeMap<String, MenuBean> menu_map = new TreeMap();
/*  25 */   private static String menu_list_path = JconfigUtilContainer.managerPagePath().getProperty("menu_list", "", "m_org_path");
/*  26 */   public static int ROOT_MENU_ID = 1;
/*  27 */   public static int CMS_MENU_ID = 6;
/*  28 */   public static int GKNODE_MENU_ID = 8;
/*  29 */   public static int ZWGK_MENU_ID = 4;
/*     */ 
/*  31 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  36 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  41 */     List menu_List = MenuDAO.getAllMenuList();
/*     */ 
/*  43 */     menu_map.clear();
/*  44 */     if ((menu_List != null) && (menu_List.size() > 0))
/*  45 */       for (int i = 0; i < menu_List.size(); i++)
/*  46 */         menu_map.put(((MenuBean)menu_List.get(i)).getMenu_id(), (MenuBean)menu_List.get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadMenu()
/*     */   {
/*  59 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.operate.MenuManager");
/*     */   }
/*     */ 
/*     */   public static int getMenuID()
/*     */   {
/*  69 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.MENU_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static String getMenuTreeJsonStr()
/*     */   {
/*  79 */     MenuBean mb = getMenuBean(ROOT_MENU_ID);
/*  80 */     if (mb != null)
/*     */     {
/*  82 */       String json_str = "[{\"id\":" + ROOT_MENU_ID + ",\"text\":\"" + mb.getMenu_name() + "\",\"attributes\":{\"url\":\"" + 
/*  83 */         menu_list_path + "?menuID=" + ROOT_MENU_ID + "\"}";
/*  84 */       String child_str = getMenuTreeJsonStrHandl(getChildMenuListByDeep(ROOT_MENU_ID));
/*  85 */       if ((child_str != null) && (!"".equals(child_str)))
/*  86 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/*  87 */       json_str = json_str + "}]";
/*  88 */       return json_str;
/*     */     }
/*  90 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getMenuTreeJsonStrHandl(List<MenuBean> all_menu_list)
/*     */   {
/* 100 */     String json_str = "";
/* 101 */     if ((all_menu_list != null) && (all_menu_list.size() > 0))
/*     */     {
/* 103 */       for (int i = 0; i < all_menu_list.size(); i++)
/*     */       {
/* 105 */         json_str = json_str + "{";
/* 106 */         json_str = json_str + "\"id\":" + ((MenuBean)all_menu_list.get(i)).getMenu_id() + ",\"text\":\"" + ((MenuBean)all_menu_list.get(i)).getMenu_name() + "\",\"attributes\":{\"url\":\"" + menu_list_path + "?menuID=" + ((MenuBean)all_menu_list.get(i)).getMenu_id() + "\"}";
/* 107 */         List child_m_list = ((MenuBean)all_menu_list.get(i)).getChild_menu_list();
/* 108 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/* 109 */           json_str = json_str + ",\"children\":[" + getMenuTreeJsonStrHandl(child_m_list) + "]";
/* 110 */         json_str = json_str + "}";
/* 111 */         if (i + 1 != all_menu_list.size())
/* 112 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 115 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static MenuBean getMenuBean(int menu_id)
/*     */   {
/* 125 */     if (menu_map.containsKey(menu_id))
/*     */     {
/* 127 */       return (MenuBean)menu_map.get(menu_id);
/*     */     }
/*     */ 
/* 130 */     MenuBean mb = MenuDAO.getMenuBean(menu_id);
/* 131 */     if (mb != null)
/*     */     {
/* 133 */       menu_map.put(menu_id, mb);
/* 134 */       return mb;
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getMenuBeanByOptID(String opt_id)
/*     */   {
/* 147 */     List menu_list = new ArrayList();
/* 148 */     Iterator iter = menu_map.entrySet().iterator();
/* 149 */     while (iter.hasNext()) {
/* 150 */       Entry entry = (Entry)iter.next();
/* 151 */       String key = (String)entry.getKey();
/* 152 */       if (opt_id.equals(((MenuBean)menu_map.get(key)).getOpt_id())) {
/* 153 */         menu_list.add((MenuBean)entry.getValue());
/*     */       }
/*     */     }
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getChildMenuCount(String menu_id)
/*     */   {
/* 167 */     int count = 0;
/* 168 */     Iterator iter = menu_map.entrySet().iterator();
/* 169 */     while (iter.hasNext()) {
/* 170 */       Entry entry = (Entry)iter.next();
/* 171 */       String key = (String)entry.getKey();
/* 172 */       if (menu_id.equals(((MenuBean)menu_map.get(key)).getParent_id())) {
/* 173 */         count++;
/*     */       }
/*     */     }
/* 176 */     return count;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getChildMenuList(String menu_id)
/*     */   {
/* 187 */     List m_List = new ArrayList();
/* 188 */     Iterator iter = menu_map.entrySet().iterator();
/* 189 */     while (iter.hasNext()) {
/* 190 */       Entry entry = (Entry)iter.next();
/* 191 */       MenuBean mb = (MenuBean)entry.getValue();
/* 192 */       if (menu_id.equals(mb.getParent_id())) {
/* 193 */         m_List.add((MenuBean)entry.getValue());
/*     */       }
/*     */     }
/* 196 */     Collections.sort(m_List, new MenuManager.MenuComparator());
/* 197 */     return m_List;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getChildMenuListByDeep(int menu_id)
/*     */   {
/* 208 */     List m_List = new ArrayList();
/* 209 */     Iterator iter = menu_map.entrySet().iterator();
/* 210 */     while (iter.hasNext()) {
/* 211 */       Entry entry = (Entry)iter.next();
/* 212 */       MenuBean mb = (MenuBean)entry.getValue();
/* 213 */       if (menu_id == mb.getParent_id()) {
/* 214 */         mb.setChild_menu_list(getChildMenuListByDeep(mb.getMenu_id()));
/* 215 */         m_List.add((MenuBean)entry.getValue());
/*     */       }
/*     */     }
/* 218 */     Collections.sort(m_List, new MenuManager.MenuComparator());
/* 219 */     return m_List;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getALLChildMenuListByID(int menu_id)
/*     */   {
/* 229 */     MenuBean mb = getMenuBean(menu_id);
/* 230 */     if (mb != null) {
/* 231 */       return getALLChildMenuListByID(mb);
/*     */     }
/* 233 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getALLChildMenuIDSByID(String menu_ids)
/*     */   {
/* 243 */     String m_ids = "";
/* 244 */     if ((menu_ids != null) && (!"".equals(menu_ids)))
/*     */     {
/* 246 */       String[] menu_a = menu_ids.split(",");
/* 247 */       for (int i = 0; i < menu_a.length; i++)
/*     */       {
/* 249 */         List m_List = getALLChildMenuListByID(Integer.parseInt(menu_a[i]));
/* 250 */         if ((m_List != null) && (m_List.size() > 0))
/*     */         {
/* 252 */           for (int j = 0; j < m_List.size(); j++)
/*     */           {
/* 254 */             m_ids = m_ids + "," + ((MenuBean)m_List.get(j)).getMenu_id();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 259 */     return m_ids;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getALLChildMenuListByID(MenuBean mb)
/*     */   {
/* 269 */     String menu_position = mb.getMenu_position();
/* 270 */     List m_List = new ArrayList();
/* 271 */     Iterator iter = menu_map.entrySet().iterator();
/* 272 */     while (iter.hasNext()) {
/* 273 */       Entry entry = (Entry)iter.next();
/* 274 */       String key = (String)entry.getKey();
/* 275 */       if ((((MenuBean)menu_map.get(key)).getMenu_position().startsWith(menu_position)) && (!((MenuBean)menu_map.get(key)).getMenu_position().equals(menu_position))) {
/* 276 */         m_List.add((MenuBean)entry.getValue());
/*     */       }
/*     */     }
/* 279 */     Collections.sort(m_List, new MenuManager.MenuComparator());
/* 280 */     return m_List;
/*     */   }
/*     */ 
/*     */   public static boolean insertMenu(MenuBean mb, SettingLogsBean stl)
/*     */   {
/* 291 */     mb.setMenu_position(getMenuBean(mb.getParent_id()).getMenu_position());
/* 292 */     if (MenuDAO.insertMenu(mb, stl))
/*     */     {
/* 294 */       reloadMenu();
/* 295 */       return true;
/*     */     }
/*     */ 
/* 298 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMenu(MenuBean mb, SettingLogsBean stl)
/*     */   {
/* 309 */     if (MenuDAO.updateMenu(mb, stl))
/*     */     {
/* 311 */       reloadMenu();
/* 312 */       return true;
/*     */     }
/*     */ 
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveMenuSort(String menu_id, SettingLogsBean stl)
/*     */   {
/* 326 */     if (MenuDAO.saveMenuSort(menu_id, stl))
/*     */     {
/* 328 */       reloadMenu();
/* 329 */       return true;
/*     */     }
/*     */ 
/* 332 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMenu(String parent_id, String menu_ids, SettingLogsBean stl)
/*     */   {
/* 343 */     String parent_tree_position = getMenuBean(Integer.parseInt(parent_id)).getMenu_position();
/*     */ 
/* 345 */     if ((menu_ids != null) && (!"".equals(menu_ids))) {
/*     */       try
/*     */       {
/* 348 */         String[] tempA = menu_ids.split(",");
/* 349 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 351 */           moveMenuHandl(tempA[i], parent_id, parent_tree_position, stl);
/*     */         }
/* 353 */         reloadMenu();
/* 354 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 357 */         e.printStackTrace();
/* 358 */         return false;
/*     */       }
/*     */     }
/* 361 */     return true;
/*     */   }
/*     */ 
/*     */   public static void moveMenuHandl(String menu_id, String parent_id, String menu_position, SettingLogsBean stl)
/*     */   {
/* 366 */     String position = menu_position + menu_id + "$";
/* 367 */     Map new_m = new HashMap();
/* 368 */     new_m.put("menu_id", menu_id);
/* 369 */     new_m.put("parent_id", parent_id);
/* 370 */     new_m.put("menu_position", position);
/* 371 */     if (MenuDAO.moveMenu(new_m, stl))
/*     */     {
/* 373 */       List m_list = getChildMenuList(menu_id);
/* 374 */       if ((m_list != null) && (m_list.size() > 0))
/*     */       {
/* 376 */         for (int i = 0; i < m_list.size(); i++)
/*     */         {
/* 378 */           moveMenuHandl(((MenuBean)m_list.get(i)).getMenu_id(), menu_id, position, stl);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean deleteMenu(String menu_id, SettingLogsBean stl)
/*     */   {
/* 393 */     menu_id = menu_id + getALLChildMenuIDSByID(menu_id);
/* 394 */     if (MenuDAO.deleteMenu(menu_id, stl))
/*     */     {
/* 396 */       reloadMenu();
/* 397 */       return true;
/*     */     }
/*     */ 
/* 400 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getMenuListByOptID(String opt_ids, boolean is_cmszwgk, int menu_id)
/*     */   {
/* 414 */     Set m_set = getMenuSetOptID(opt_ids);
/*     */ 
/* 416 */     List menu_List = new ArrayList();
/* 417 */     if (!is_cmszwgk)
/* 418 */       getMenuChildByParentForSet(menu_List, m_set, menu_id, is_cmszwgk);
/*     */     else
/* 420 */       getCMSMenuChildList(menu_List, m_set, menu_id);
/* 421 */     return menu_List;
/*     */   }
/*     */ 
/*     */   public static void getMenuChildByParentForSet(List<MenuBean> menu_List, Set<MenuBean> menu_set, int parent_id, boolean is_cms)
/*     */   {
/* 431 */     Iterator it = menu_set.iterator();
/* 432 */     while (it.hasNext()) {
/* 433 */       MenuBean mb = ((MenuBean)it.next()).clone();
/*     */ 
/* 435 */       if (parent_id == mb.getParent_id())
/*     */       {
/* 437 */         if ((mb.getParent_id() > 1) || (mb.getParent_id() == 1))
/*     */         {
/* 440 */           if (!menuIDISCms(mb.getMenu_id()))
/*     */           {
/* 443 */             List child_list = new ArrayList();
/* 444 */             getMenuChildByParentForSet(child_list, menu_set, mb.getMenu_id(), is_cms);
/* 445 */             mb.setChild_menu_list(child_list);
/*     */           }
/* 447 */           menu_List.add(mb);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 452 */     Collections.sort(menu_List, new MenuManager.MenuComparator());
/*     */   }
/*     */ 
/*     */   public static void getCMSMenuChildList(List<MenuBean> menu_List, Set<MenuBean> menu_set, int parent_id)
/*     */   {
/* 457 */     Iterator it = menu_set.iterator();
/* 458 */     while (it.hasNext()) {
/* 459 */       MenuBean mb = ((MenuBean)it.next()).clone();
/*     */ 
/* 461 */       if (parent_id == mb.getParent_id())
/*     */       {
/* 463 */         if ((mb.getParent_id() > 6) || (mb.getParent_id() == 6))
/*     */         {
/* 466 */           List child_list = new ArrayList();
/* 467 */           getCMSMenuChildList(child_list, menu_set, mb.getMenu_id());
/* 468 */           mb.setChild_menu_list(child_list);
/* 469 */           menu_List.add(mb);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 475 */     Collections.sort(menu_List, new MenuManager.MenuComparator());
/*     */   }
/*     */ 
/*     */   public static boolean menuIDISCms(int menu_id)
/*     */   {
/* 485 */     return menu_id == 6;
/*     */   }
/*     */ 
/*     */   public static Set<MenuBean> getMenuSetOptID(String opt_ids)
/*     */   {
/* 495 */     opt_ids = "," + opt_ids + ",";
/*     */ 
/* 497 */     Set menu_set = new HashSet();
/* 498 */     Iterator iter = menu_map.entrySet().iterator();
/* 499 */     while (iter.hasNext()) {
/* 500 */       Entry entry = (Entry)iter.next();
/* 501 */       String key = (String)entry.getKey();
/*     */ 
/* 503 */       if (opt_ids.contains("," + ((MenuBean)menu_map.get(key)).getOpt_id() + ",")) {
/* 504 */         menu_set.add((MenuBean)menu_map.get(key));
/* 505 */         setMenuByMenuPosition(menu_set, ((MenuBean)menu_map.get(key)).getMenu_position());
/*     */       }
/*     */     }
/*     */ 
/* 509 */     return menu_set;
/*     */   }
/*     */ 
/*     */   public static void setMenuByMenuPosition(Set<MenuBean> menu_set, String menu_position)
/*     */   {
/* 520 */     String[] tempA = menu_position.split("\\$");
/*     */ 
/* 524 */     for (int i = 2; i < tempA.length - 1; i++)
/*     */     {
/* 526 */       if ((tempA[i] != null) && (!"".equals(tempA[i])))
/* 527 */         menu_set.add((MenuBean)menu_map.get(tempA[i]));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getMyPlatform()
/*     */   {
/* 537 */     List l = getChildMenuList(ROOT_MENU_ID);
/* 538 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 540 */       MenuBean mb = (MenuBean)l.get(l.size() - 1);
/*     */ 
/* 542 */       mb.setChild_menu_list(getChildMenuListByDeep(mb.getMenu_id()));
/* 543 */       l.clear();
/* 544 */       l.add(mb);
/* 545 */       return l;
/*     */     }
/* 547 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 574 */     System.out.println("--------" + getMyPlatform());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.operate.MenuManager
 * JD-Core Version:    0.6.2
 */