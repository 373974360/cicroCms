/*     */ package com.cicro.wcm.services.org.dept;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptManBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.OrgDAOFactory;
/*     */ import com.cicro.wcm.dao.org.dept.IDeptDAO;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
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
/*     */ public class DeptManager
/*     */   implements ISyncCatch
/*     */ {
/*  44 */   private static TreeMap<String, DeptBean> dept_Map = new TreeMap();
/*  45 */   private static TreeMap<String, String> main_Map = new TreeMap();
/*  46 */   private static String dept_manager_path = JconfigUtilContainer.managerPagePath().getProperty("detp_list", "", "m_org_path");
/*  47 */   private static IDeptDAO deptDao = OrgDAOFactory.getDeptDao();
/*     */ 
/*  49 */   private static int root_node_id = 1;
/*     */ 
/*     */   static {
/*  52 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  57 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  62 */     List manager_list = deptDao.getAllDeptManagerList();
/*  63 */     main_Map.clear();
/*  64 */     if ((manager_list != null) && (manager_list.size() > 0)) {
/*  65 */       for (DeptManBean dmb : manager_list) {
/*  66 */         String dept_id = dmb.getDept_id();
/*  67 */         String user_id = dmb.getUser_id();
/*  68 */         if (main_Map.containsKey(dept_id))
/*     */         {
/*  70 */           main_Map.put(dept_id, (String)main_Map.get(dept_id) + user_id + ",");
/*     */         }
/*     */         else {
/*  73 */           main_Map.put(dept_id, "," + user_id + ",");
/*     */         }
/*     */       }
/*     */     }
/*  77 */     List dept_list = deptDao.getAllDeptList();
/*  78 */     dept_Map.clear();
/*  79 */     if ((dept_list != null) && (dept_list.size() > 0))
/*  80 */       for (int i = 0; i < dept_list.size(); i++)
/*     */       {
/*  84 */         dept_Map.put(((DeptBean)dept_list.get(i)).getDept_id(), 
/*  85 */           (DeptBean)dept_list
/*  85 */           .get(i));
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void reloadDept()
/*     */   {
/*  98 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.dept.DeptManager");
/*     */   }
/*     */ 
/*     */   public static void reloadDeptManager()
/*     */   {
/* 111 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.dept.DeptManager");
/*     */   }
/*     */ 
/*     */   public static TreeMap<String, String> getDeptMUserMap()
/*     */   {
/* 116 */     return main_Map;
/*     */   }
/*     */ 
/*     */   public static String getDeptName(String dept_id)
/*     */   {
/* 121 */     DeptBean db = getDeptBeanByID(dept_id);
/* 122 */     if (db != null) {
/* 123 */       return db.getDept_name();
/*     */     }
/* 125 */     return "";
/*     */   }
/*     */ 
/*     */   public static int getDeptID()
/*     */   {
/* 134 */     return deptDao.getDeptID();
/*     */   }
/*     */ 
/*     */   public static String getDeptListPage()
/*     */   {
/* 144 */     return dept_manager_path;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getDeptListByUserManager(String user_id)
/*     */   {
/* 156 */     List dept_list = new ArrayList();
/* 157 */     String dept_ids = getDeptByUserManager(user_id);
/* 158 */     if ((dept_ids != null) && (!"".equals(dept_ids)))
/*     */     {
/* 160 */       String[] d_arr = dept_ids.split(",");
/* 161 */       for (int i = 0; i < d_arr.length; i++)
/*     */       {
/* 163 */         DeptBean db = getDeptBeanByID(d_arr[i]);
/* 164 */         if (db != null)
/* 165 */           dept_list.add(db);
/*     */       }
/* 167 */       Collections.sort(dept_list, new DeptManager.DeptComparator());
/*     */     }
/* 169 */     return dept_list;
/*     */   }
/*     */ 
/*     */   public static String getDeptByUserManager(String user_id)
/*     */   {
/* 182 */     String dept_ids = "";
/* 183 */     Iterator iter = main_Map.entrySet().iterator();
/* 184 */     while (iter.hasNext()) {
/* 185 */       Entry entry = (Entry)iter.next();
/* 186 */       String user_ids = (String)main_Map.get((String)entry.getKey());
/* 187 */       if (user_ids.contains("," + user_id + ","))
/* 188 */         dept_ids = dept_ids + "," + (String)entry.getKey();
/*     */     }
/* 190 */     if ((dept_ids != null) && (!"".equals(dept_ids))) {
/* 191 */       dept_ids = dept_ids.substring(1);
/*     */     }
/* 193 */     return dept_ids;
/*     */   }
/*     */ 
/*     */   public static String getDeptTreeByUser(String user_id)
/*     */   {
/* 205 */     String str = "[]";
/* 206 */     List dept_list = getDeptListByUserManager(user_id);
/* 207 */     if ((dept_list != null) && (dept_list.size() > 0)) {
/* 208 */       str = "[" + deptListToStrHandl(dept_list, "one") + "]";
/*     */     }
/* 210 */     return str;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getDeptList()
/*     */   {
/* 223 */     List dept_list = new ArrayList();
/*     */ 
/* 225 */     Iterator iter = dept_Map.entrySet().iterator();
/* 226 */     while (iter.hasNext()) {
/* 227 */       Entry entry = (Entry)iter.next();
/* 228 */       dept_list.add((DeptBean)dept_Map.get((String)entry.getKey()));
/*     */     }
/* 230 */     return dept_list;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getChildDeptListForDB(Map<String, String> con_m)
/*     */   {
/* 241 */     return deptDao.getChildDeptListForDB(con_m);
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getDeptListByDeptIDS(String dept_ids)
/*     */   {
/* 251 */     if ((dept_ids != null) && (!"".equals(dept_ids)))
/*     */     {
/* 253 */       List dept_list = new ArrayList();
/* 254 */       String[] tempA = dept_ids.split(",");
/* 255 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 257 */         DeptBean db = getDeptBeanByID(tempA[i]);
/* 258 */         if (db != null)
/* 259 */           dept_list.add(db);
/*     */       }
/* 261 */       return dept_list;
/*     */     }
/*     */ 
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getRootDeptID()
/*     */   {
/* 276 */     return root_node_id;
/*     */   }
/*     */ 
/*     */   public static DeptBean getRootDeptBean()
/*     */   {
/* 288 */     return getDeptBeanByID(root_node_id);
/*     */   }
/*     */ 
/*     */   public static Map<String, DeptBean> getDeptMap()
/*     */   {
/* 300 */     return dept_Map;
/*     */   }
/*     */ 
/*     */   public static boolean insertDept(DeptBean db, SettingLogsBean stl)
/*     */   {
/* 313 */     db.setTree_position(getDeptBeanByID(db.getParent_id()).getTree_position());
/* 314 */     if (deptDao.insertDept(db, stl)) {
/* 315 */       reloadDept();
/* 316 */       return true;
/*     */     }
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDept(DeptBean db, SettingLogsBean stl)
/*     */   {
/* 332 */     if (OrgDAOFactory.getDeptDao().updateDept(db, stl))
/*     */     {
/* 334 */       reloadDept();
/* 335 */       return true;
/*     */     }
/* 337 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveDept(String parent_id, String dept_ids, SettingLogsBean stl)
/*     */   {
/* 348 */     String parent_tree_position = getDeptBeanByID(parent_id).getTree_position();
/* 349 */     if ((dept_ids != null) && (!"".equals(dept_ids))) {
/*     */       try
/*     */       {
/* 352 */         String[] tempA = dept_ids.split(",");
/* 353 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 355 */           moveDeptHandl(tempA[i], parent_id, parent_tree_position, stl);
/*     */         }
/* 357 */         reloadDept();
/* 358 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 361 */         e.printStackTrace();
/* 362 */         return false;
/*     */       }
/*     */     }
/* 365 */     return true;
/*     */   }
/*     */ 
/*     */   public static void moveDeptHandl(String dept_id, String parent_id, String tree_position, SettingLogsBean stl)
/*     */   {
/* 370 */     String position = tree_position + dept_id + "$";
/* 371 */     Map new_m = new HashMap();
/* 372 */     new_m.put("dept_id", dept_id);
/* 373 */     new_m.put("parent_id", parent_id);
/* 374 */     new_m.put("tree_position", position);
/* 375 */     if (OrgDAOFactory.getDeptDao().moveDept(new_m, stl))
/*     */     {
/* 377 */       List d_list = getChildDeptListByID(dept_id);
/* 378 */       if ((d_list != null) && (d_list.size() > 0))
/*     */       {
/* 380 */         for (int i = 0; i < d_list.size(); i++)
/*     */         {
/* 382 */           moveDeptHandl(((DeptBean)d_list.get(i)).getDept_id(), dept_id, position, stl);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static DeptBean getDeptBeanByID(String id)
/*     */   {
/* 397 */     if ((id == null) || ("".equals(id))) {
/* 398 */       return null;
/*     */     }
/* 400 */     if (dept_Map.containsKey(id)) {
/* 401 */       return (DeptBean)dept_Map.get(id);
/*     */     }
/* 403 */     DeptBean db = deptDao.getDeptBeanByID(id);
/* 404 */     if (db != null)
/*     */     {
/* 407 */       dept_Map.put(id, db);
/*     */     }
/* 409 */     return db;
/*     */   }
/*     */ 
/*     */   public static DeptBean getParentDeptBeanByID(String id)
/*     */   {
/* 421 */     DeptBean db = getDeptBeanByID(id);
/* 422 */     if (db != null) {
/* 423 */       return getDeptBeanByID(db.getParent_id());
/*     */     }
/* 425 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getChildDeptCountByID(String dept_id)
/*     */   {
/* 439 */     int count = 0;
/* 440 */     Iterator iter = dept_Map.entrySet().iterator();
/* 441 */     while (iter.hasNext()) {
/* 442 */       Entry entry = (Entry)iter.next();
/* 443 */       String key = (String)entry.getKey();
/* 444 */       if (dept_id.equals(((DeptBean)dept_Map.get(key)).getParent_id())) {
/* 445 */         count++;
/*     */       }
/*     */     }
/* 448 */     return count;
/*     */   }
/*     */ 
/*     */   public static String deptListToJsonStrByUserID(String dept_id)
/*     */   {
/* 459 */     DeptBean db = getDeptBeanByID(dept_id);
/* 460 */     if (db != null)
/*     */     {
/* 462 */       String json_str = "[{\"id\":" + dept_id + ",\"text\":\"" + db.getDept_name() + "\",\"attributes\":{\"url\":\"" + 
/* 463 */         dept_manager_path + "?deptID=" + dept_id + "\"}";
/* 464 */       String child_str = deptListToStrHandl(getChildDeptListByID(dept_id), "");
/* 465 */       if ((child_str != null) && (!"".equals(child_str)))
/* 466 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/* 467 */       json_str = json_str + "}]";
/* 468 */       return json_str;
/*     */     }
/* 470 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String deptListToStrHandl(List<DeptBean> dl, String loop_type)
/*     */   {
/* 482 */     String json_str = "";
/* 483 */     String icon_str = "\"iconCls\":\"icon-user\",";
/* 484 */     String child_state = "";
/* 485 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 487 */       for (DeptBean db : dl)
/*     */       {
/* 489 */         if (db.getDept_id() == root_node_id)
/*     */         {
/* 491 */           icon_str = "\"iconCls\":\"icon-org\"";
/*     */         }
/* 493 */         else icon_str = "\"iconCls\":\"icon-dept\"";
/*     */ 
/* 495 */         json_str = json_str + ",{";
/* 496 */         List child_d_list = getChildDeptListByID(db.getDept_id());
/* 497 */         if ((child_d_list != null) && (child_d_list.size() > 0) && (!"one".equals(loop_type)))
/* 498 */           child_state = "\"state\":'closed',";
/*     */         else {
/* 500 */           child_state = "";
/*     */         }
/* 502 */         json_str = json_str + "\"id\":" + db.getDept_id() + "," + icon_str + "," + child_state + "\"text\":\"" + db.getDept_name() + "\",\"attributes\":{\"url\":\"" + 
/* 503 */           dept_manager_path + "?deptID=" + db.getDept_id() + "\"}";
/*     */ 
/* 505 */         if ((child_d_list != null) && (child_d_list.size() > 0))
/* 506 */           json_str = json_str + ",\"children\":[" + deptListToStrHandl(child_d_list, "") + "]";
/* 507 */         json_str = json_str + "}";
/*     */       }
/* 509 */       if ((json_str != null) && (!"".equals(json_str)))
/* 510 */         json_str = json_str.substring(1);
/*     */     }
/* 512 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getChildDeptListByID(String dept_id)
/*     */   {
/* 524 */     List child_dept_list = new ArrayList();
/*     */ 
/* 526 */     Iterator iter = dept_Map.entrySet().iterator();
/* 527 */     while (iter.hasNext()) {
/* 528 */       Entry entry = (Entry)iter.next();
/* 529 */       String key = (String)entry.getKey();
/* 530 */       DeptBean temp_db = (DeptBean)dept_Map.get(key);
/* 531 */       if (dept_id.equals(temp_db.getParent_id()))
/*     */       {
/* 533 */         child_dept_list.add(temp_db);
/*     */       }
/*     */     }
/* 536 */     Collections.sort(child_dept_list, new DeptManager.DeptComparator());
/*     */ 
/* 538 */     return child_dept_list;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getChildDeptListForPublish(String dept_id)
/*     */   {
/* 550 */     List child_dept_list = new ArrayList();
/*     */ 
/* 552 */     Iterator iter = dept_Map.entrySet().iterator();
/* 553 */     while (iter.hasNext()) {
/* 554 */       Entry entry = (Entry)iter.next();
/* 555 */       String key = (String)entry.getKey();
/* 556 */       DeptBean temp_db = (DeptBean)dept_Map.get(key);
/* 557 */       if ((dept_id.equals(temp_db.getParent_id())) && (temp_db.getIs_publish() == 1)) {
/* 558 */         child_dept_list.add(temp_db);
/*     */       }
/*     */     }
/* 561 */     Collections.sort(child_dept_list, new DeptManager.DeptComparator());
/*     */ 
/* 563 */     return child_dept_list;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getChildDeptListByBean(DeptBean db)
/*     */   {
/* 574 */     return getChildDeptListByID(db.getDept_id());
/*     */   }
/*     */ 
/*     */   public static String getChildDeptIDSByBean(DeptBean db)
/*     */   {
/* 585 */     return getChildDeptIDSByID(db.getDept_id());
/*     */   }
/*     */ 
/*     */   public static String getChildDeptIDSByID(String id)
/*     */   {
/* 598 */     String ids = "";
/*     */ 
/* 600 */     Iterator iter = dept_Map.entrySet().iterator();
/* 601 */     while (iter.hasNext()) {
/* 602 */       Entry entry = (Entry)iter.next();
/* 603 */       String key = (String)entry.getKey();
/* 604 */       DeptBean temp_db = (DeptBean)dept_Map.get(key);
/* 605 */       if (id.equals(temp_db.getParent_id())) {
/* 606 */         ids = ids + "," + temp_db.getDept_id();
/*     */       }
/*     */     }
/* 609 */     if (!"".equals(ids))
/* 610 */       ids = ids.substring(1);
/* 611 */     return ids;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getAllChildDeptListByID(String id)
/*     */   {
/* 622 */     DeptBean db = getDeptBeanByID(id);
/* 623 */     if (db != null) {
/* 624 */       return getAllChildDeptListByBean(db);
/*     */     }
/* 626 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getAllChildDeptListByBean(DeptBean db)
/*     */   {
/* 639 */     List child_dept_list = new ArrayList();
/*     */ 
/* 641 */     Iterator iter = dept_Map.entrySet().iterator();
/* 642 */     while (iter.hasNext()) {
/* 643 */       Entry entry = (Entry)iter.next();
/* 644 */       String key = (String)entry.getKey();
/* 645 */       DeptBean temp_db = (DeptBean)dept_Map.get(key);
/* 646 */       if ((temp_db.getTree_position().startsWith(db.getTree_position())) && (!temp_db.getTree_position().equals(db.getTree_position()))) {
/* 647 */         child_dept_list.add(temp_db);
/*     */       }
/*     */     }
/* 650 */     return child_dept_list;
/*     */   }
/*     */ 
/*     */   public static String getAllChildDeptIDSByBean(DeptBean db)
/*     */   {
/* 662 */     String ids = "";
/*     */ 
/* 664 */     Iterator iter = dept_Map.entrySet().iterator();
/* 665 */     while (iter.hasNext()) {
/* 666 */       Entry entry = (Entry)iter.next();
/* 667 */       String key = (String)entry.getKey();
/* 668 */       DeptBean temp_db = (DeptBean)dept_Map.get(key);
/* 669 */       if ((temp_db.getTree_position().startsWith(db.getTree_position())) && (!temp_db.getTree_position().equals(db.getTree_position()))) {
/* 670 */         ids = ids + "," + temp_db.getDept_id();
/*     */       }
/*     */     }
/* 673 */     if (!"".equals(ids)) {
/* 674 */       ids = ids.substring(1);
/*     */     }
/* 676 */     return ids;
/*     */   }
/*     */ 
/*     */   public static String getAllChildDeptIDSByID(String id)
/*     */   {
/* 687 */     DeptBean db = getDeptBeanByID(id);
/*     */ 
/* 689 */     if (db != null) {
/* 690 */       return getAllChildDeptIDSByBean(db);
/*     */     }
/* 692 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDept(String ids, SettingLogsBean stl)
/*     */   {
/* 707 */     String delete_ids = "";
/* 708 */     if ((ids != null) && (!"".equals(ids)))
/*     */     {
/* 710 */       String[] tempA = ids.split(",");
/* 711 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 713 */         String all_child_dept = getAllChildDeptIDSByID(tempA[i]);
/* 714 */         if (!"".equals(all_child_dept))
/* 715 */           all_child_dept = "," + all_child_dept;
/* 716 */         delete_ids = delete_ids + "," + tempA[i] + all_child_dept;
/*     */       }
/* 718 */       delete_ids = delete_ids.substring(1);
/* 719 */       if (deptDao.deleteDept(delete_ids, stl))
/*     */       {
/* 722 */         UserManager.reloadUser();
/*     */ 
/* 724 */         reloadDept();
/* 725 */         return true;
/*     */       }
/*     */ 
/* 728 */       return false;
/*     */     }
/*     */ 
/* 733 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean saveDeptSort(String dept_ids, SettingLogsBean stl)
/*     */   {
/* 749 */     if ((dept_ids != null) && (!"".equals(dept_ids)))
/*     */     {
/* 751 */       if (deptDao.saveDeptSort(dept_ids, stl))
/*     */       {
/* 753 */         reloadDept();
/* 754 */         return true;
/*     */       }
/* 756 */       return false;
/*     */     }
/* 758 */     return true;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByDeptID(String dept_id)
/*     */   {
/* 770 */     return UserManager.getUserListByDeptID(dept_id);
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getAllChildUserByDeptID(String dept_id)
/*     */   {
/* 782 */     String child_dept_ids = getAllChildDeptIDSByID(dept_id);
/* 783 */     if ((child_dept_ids == null) || ("".equals(child_dept_ids)))
/*     */     {
/* 785 */       child_dept_ids = dept_id;
/*     */     }
/* 787 */     else child_dept_ids = dept_id + "," + child_dept_ids;
/*     */ 
/* 789 */     return getUserListByDeptID(dept_id + "," + child_dept_ids);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 833 */     System.out.println(deptDao.getAllDeptManagerList());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.dept.DeptManager
 * JD-Core Version:    0.6.2
 */