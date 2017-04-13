/*     */ package com.cicro.wcm.services.appeal.cpDept;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.cpDept.CpDeptDAO;
/*     */ import com.cicro.wcm.services.appeal.cpUser.CpUserManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class CpDeptManager
/*     */   implements ISyncCatch
/*     */ {
/*  28 */   private static TreeMap<String, CpDeptBean> dept_map = new TreeMap();
/*  29 */   private static int root_dept_id = 1;
/*     */ 
/*     */   static {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  37 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  43 */     List dept_list = CpDeptDAO.getAllCpDeptBySort();
/*  44 */     dept_map.clear();
/*  45 */     if ((dept_list != null) && (dept_list.size() > 0))
/*  46 */       for (int i = 0; i < dept_list.size(); i++)
/*  47 */         dept_map.put(((CpDeptBean)dept_list.get(i)).getDept_id(), (CpDeptBean)dept_list.get(i));
/*     */   }
/*     */ 
/*     */   public static void loadCpdept()
/*     */   {
/*  56 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.cpDept.CpDeptManager");
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getAllCpDeptBySort()
/*     */   {
/*  64 */     List dept_list = new ArrayList();
/*  65 */     Set set = dept_map.keySet();
/*  66 */     if ((set != null) && (set.size() > 0))
/*     */     {
/*  68 */       for (String s : set)
/*     */       {
/*  70 */         dept_list.add((CpDeptBean)dept_map.get(s));
/*     */       }
/*     */     }
/*  73 */     return dept_list;
/*     */   }
/*     */ 
/*     */   public static boolean insertCpdept(CpDeptBean dept)
/*     */   {
/*  82 */     int deptId = PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_DEPT_TABLE_NAME);
/*  83 */     dept.setDept_id(deptId);
/*  84 */     dept = setLevelAndPosition(dept);
/*  85 */     if (CpDeptDAO.insertCpdept(dept)) {
/*  86 */       loadCpdept();
/*  87 */       return true;
/*     */     }
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static CpDeptBean setLevelAndPosition(CpDeptBean dept)
/*     */   {
/* 100 */     String deptPosition = "$" + dept.getDept_id() + "$";
/* 101 */     int deptLevel = 0;
/*     */ 
/* 103 */     int p_id = dept.getParent_id();
/* 104 */     while (p_id != 0) {
/* 105 */       CpDeptBean pDept = getCpDeptBean(p_id);
/* 106 */       p_id = pDept.getParent_id();
/*     */ 
/* 109 */       deptLevel++;
/*     */ 
/* 111 */       deptPosition = "$" + pDept.getDept_id() + deptPosition;
/*     */     }
/*     */ 
/* 114 */     dept.setDept_level(deptLevel);
/* 115 */     dept.setDept_position(deptPosition);
/* 116 */     return dept;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpdept(String dept_ids)
/*     */   {
/* 126 */     String allDeptIDs = "";
/*     */ 
/* 129 */     String[] ids = dept_ids.split(",");
/*     */ 
/* 131 */     for (int i = 0; i < ids.length; i++)
/*     */     {
/* 133 */       allDeptIDs = allDeptIDs + getChildDeptIds(ids[i]);
/* 134 */       if (i < ids.length - 1) {
/* 135 */         allDeptIDs = allDeptIDs + ",";
/*     */       }
/*     */     }
/*     */ 
/* 139 */     if (CpDeptDAO.deleteCpdept(allDeptIDs)) {
/* 140 */       loadCpdept();
/*     */ 
/* 142 */       String user_ids = CpUserManager.getUserIdsByDeptID(dept_ids);
/* 143 */       if ((user_ids != null) && (!"".equals(user_ids)))
/* 144 */         CpUserManager.deleteCpUser(dept_ids, user_ids, null);
/* 145 */       return true;
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCpDept(CpDeptBean dept)
/*     */   {
/* 157 */     if (CpDeptDAO.updateCpDept(dept)) {
/* 158 */       loadCpdept();
/* 159 */       return true;
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveDeptSort(String deptIds)
/*     */   {
/* 171 */     if (CpDeptDAO.saveDeptSort(deptIds)) {
/* 172 */       loadCpdept();
/* 173 */       return true;
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getAllCpDeptList()
/*     */   {
/* 184 */     return CpDeptDAO.getCpDeptList();
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getCpDeptList()
/*     */   {
/* 193 */     return CpDeptDAO.getCpDeptList();
/*     */   }
/*     */ 
/*     */   public static String getCpDeptCount(Map<String, String> m)
/*     */   {
/* 202 */     return CpDeptDAO.getCpDeptCount(m);
/*     */   }
/*     */ 
/*     */   public static String getCpDeptName(int id)
/*     */   {
/* 214 */     CpDeptBean cdb = getCpDeptBean(id);
/* 215 */     if (cdb != null)
/*     */     {
/* 217 */       return cdb.getDept_name();
/*     */     }
/*     */ 
/* 220 */     return "";
/*     */   }
/*     */ 
/*     */   public static CpDeptBean getCpDeptBean(int id)
/*     */   {
/* 231 */     String dept_id = id;
/* 232 */     if ((dept_id == null) || ("".equals(dept_id))) {
/* 233 */       return null;
/*     */     }
/* 235 */     if (dept_map.containsKey(dept_id)) {
/* 236 */       return (CpDeptBean)dept_map.get(dept_id);
/*     */     }
/*     */ 
/* 241 */     CpDeptBean dept = CpDeptDAO.getCpDeptBean(dept_id);
/* 242 */     if (dept != null)
/*     */     {
/* 244 */       dept_map.put(dept_id, dept);
/*     */     }
/* 246 */     return dept;
/*     */   }
/*     */ 
/*     */   public static String getDeptTreeJsonStr(int node)
/*     */   {
/* 256 */     String jsonStr = "[" + getDeptTreeJsonElement(root_dept_id) + "]";
/* 257 */     return jsonStr;
/*     */   }
/*     */ 
/*     */   public static String getDeptTreeJsonElement(int root_dept_id)
/*     */   {
/* 267 */     String jsonStr = "";
/* 268 */     CpDeptBean dept = getCpDeptBean(root_dept_id);
/* 269 */     if (dept != null) {
/* 270 */       jsonStr = "{\"id\":" + dept.getDept_id() + 
/* 271 */         ",\"text\":\"" + dept.getDept_name() + "\"";
/*     */ 
/* 273 */       List childList = getChildDeptList(dept.getDept_id());
/*     */ 
/* 276 */       if ((childList != null) && (childList.size() > 0)) {
/* 277 */         jsonStr = jsonStr + ",\"children\":[";
/* 278 */         for (int i = 0; i < childList.size(); i++) {
/* 279 */           jsonStr = jsonStr + getDeptTreeJsonElement(((CpDeptBean)childList.get(i)).getDept_id());
/*     */ 
/* 282 */           if (i < childList.size() - 1) {
/* 283 */             jsonStr = jsonStr + ",";
/*     */           }
/*     */         }
/* 286 */         jsonStr = jsonStr + "]";
/*     */       }
/* 288 */       jsonStr = jsonStr + "}";
/*     */ 
/* 290 */       return jsonStr;
/*     */     }
/* 292 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getChildDeptIds(String dept_id)
/*     */   {
/* 305 */     String idStr = "";
/*     */ 
/* 308 */     List childList = getChildDeptList(dept_id);
/*     */ 
/* 311 */     if ((childList != null) && (childList.size() > 0))
/*     */     {
/* 313 */       for (int i = 0; i < childList.size(); i++) {
/* 314 */         idStr = idStr + getChildDeptIds(new StringBuilder(String.valueOf(((CpDeptBean)childList.get(i)).getDept_id())).toString()) + ", ";
/*     */       }
/*     */     }
/*     */ 
/* 318 */     idStr = idStr + dept_id;
/*     */ 
/* 320 */     return idStr;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getChildDeptList(String dept_id)
/*     */   {
/* 331 */     List childList = new ArrayList();
/*     */ 
/* 334 */     Iterator iter = dept_map.entrySet().iterator();
/* 335 */     while (iter.hasNext()) {
/* 336 */       Entry entry = (Entry)iter.next();
/* 337 */       String key = (String)entry.getKey();
/* 338 */       if (dept_id.equals(((CpDeptBean)dept_map.get(key)).getParent_id())) {
/* 339 */         childList.add((CpDeptBean)entry.getValue());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 344 */     Collections.sort(childList, new CpDeptManager.CpDeptComparator());
/* 345 */     return childList;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpDept.CpDeptManager
 * JD-Core Version:    0.6.2
 */