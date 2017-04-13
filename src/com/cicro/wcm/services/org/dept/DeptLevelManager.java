/*     */ package com.cicro.wcm.services.org.dept;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptLevelBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.OrgDAOFactory;
/*     */ import com.cicro.wcm.dao.org.dept.IDeptDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class DeptLevelManager
/*     */   implements ISyncCatch
/*     */ {
/*  36 */   private static TreeMap<String, DeptLevelBean> deptLevel_Map = new TreeMap();
/*     */ 
/*  38 */   private static IDeptDAO deptDao = OrgDAOFactory.getDeptDao();
/*     */ 
/*     */   static {
/*  41 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  46 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  51 */     deptLevel_Map.clear();
/*  52 */     List deptLevel_list = deptDao.getAllDeptLevelList();
/*     */ 
/*  54 */     if ((deptLevel_list != null) && (deptLevel_list.size() > 0))
/*  55 */       for (int i = 0; i < deptLevel_list.size(); i++)
/*  56 */         deptLevel_Map.put(((DeptLevelBean)deptLevel_list.get(i)).getDeptlevel_id(), 
/*  57 */           (DeptLevelBean)deptLevel_list
/*  57 */           .get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadDeptLevel()
/*     */   {
/*  70 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.dept.DeptLevelManager");
/*     */   }
/*     */ 
/*     */   public static String getDeptLevelCount()
/*     */   {
/*  82 */     return deptLevel_Map.size();
/*     */   }
/*     */ 
/*     */   public static List<DeptLevelBean> getDeptLevelList()
/*     */   {
/*  95 */     List deptLevel_list = new ArrayList();
/*     */ 
/*  97 */     TreeMap temp_dept_map = new TreeMap();
/*     */ 
/*  99 */     Iterator iter = deptLevel_Map.entrySet().iterator();
/* 100 */     while (iter.hasNext()) {
/* 101 */       Entry entry = (Entry)iter.next();
/* 102 */       DeptLevelBean dbl = (DeptLevelBean)deptLevel_Map.get((String)entry.getKey());
/* 103 */       temp_dept_map.put(Integer.valueOf(dbl.getDeptlevel_value()), dbl);
/*     */     }
/*     */ 
/* 106 */     Iterator iter2 = temp_dept_map.entrySet().iterator();
/* 107 */     while (iter2.hasNext()) {
/* 108 */       Entry entry = (Entry)iter2.next();
/* 109 */       deptLevel_list.add((DeptLevelBean)entry.getValue());
/*     */     }
/*     */ 
/* 112 */     return deptLevel_list;
/*     */   }
/*     */ 
/*     */   public static Map<String, DeptLevelBean> getDeptLevelMap()
/*     */   {
/* 124 */     return deptLevel_Map;
/*     */   }
/*     */ 
/*     */   public static DeptLevelBean getDeptLevelBeanByID(String id)
/*     */   {
/* 137 */     if (deptLevel_Map.containsKey(id)) {
/* 138 */       return (DeptLevelBean)deptLevel_Map.get(id);
/*     */     }
/* 140 */     DeptLevelBean dlb = deptDao.getDeptLevelBeanByID(id);
/* 141 */     if (dlb != null)
/* 142 */       deptLevel_Map.put(id, dlb);
/* 143 */     return dlb;
/*     */   }
/*     */ 
/*     */   public static boolean insertDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */   {
/* 158 */     if (getDeptLevelCountByLevel(0, dlb.getDeptlevel_value()))
/*     */     {
/* 160 */       System.out.println("updateDeptLevel : this deptLevel value is exist");
/* 161 */       return false;
/*     */     }
/* 163 */     if (deptDao.insertDeptLevel(dlb, stl)) {
/* 164 */       reloadDeptLevel();
/* 165 */       return true;
/*     */     }
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */   {
/* 179 */     if (getDeptLevelCountByLevel(dlb.getDeptlevel_id(), dlb.getDeptlevel_value()))
/*     */     {
/* 181 */       System.out.println("updateDeptLevel : this deptLevel value is exist");
/* 182 */       return false;
/*     */     }
/*     */ 
/* 186 */     if (deptDao.updateDeptLevel(dlb, stl)) {
/* 187 */       reloadDeptLevel();
/* 188 */       return true;
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDeptLevel(String ids, SettingLogsBean stl)
/*     */   {
/* 201 */     if (deptDao.deleteDeptLevel(ids, stl)) {
/* 202 */       reloadDeptLevel();
/* 203 */       return true;
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteVoidDeptLevel(String ids, SettingLogsBean stl)
/*     */   {
/* 218 */     boolean flg = true;
/* 219 */     String[] arrIDS = ids.split(",");
/* 220 */     for (int i = 0; i < arrIDS.length; i++)
/*     */     {
/* 223 */       if (isExistUser(arrIDS[i]) != 0)
/*     */       {
/* 225 */         flg = false;
/*     */       }
/*     */     }
/* 228 */     if (flg)
/*     */     {
/* 230 */       flg = deleteDeptLevel(ids, stl);
/*     */     }
/* 232 */     return flg;
/*     */   }
/*     */ 
/*     */   public static int isExistUser(String id)
/*     */   {
/* 244 */     String value = ((DeptLevelBean)deptLevel_Map.get(id)).getDeptlevel_value();
/* 245 */     int cnt = 0;
/* 246 */     Map map = DeptManager.getDeptMap();
/* 247 */     Iterator it = map.values().iterator();
/* 248 */     while (it.hasNext())
/*     */     {
/* 250 */       DeptBean ub = (DeptBean)it.next();
/* 251 */       if (value.equals(ub.getDeptlevel_value()))
/*     */       {
/* 253 */         cnt++;
/*     */       }
/*     */     }
/* 256 */     return cnt;
/*     */   }
/*     */ 
/*     */   public static boolean getDeptLevelCountByLevel(int id, int level_value)
/*     */   {
/* 268 */     Iterator iter = deptLevel_Map.entrySet().iterator();
/* 269 */     int count = 0;
/* 270 */     while (iter.hasNext()) {
/* 271 */       Entry entry = (Entry)iter.next();
/* 272 */       DeptLevelBean dlb = (DeptLevelBean)deptLevel_Map.get((String)entry.getKey());
/* 273 */       if ((dlb.getDeptlevel_value() == level_value) && ((id == 0) || (dlb.getDeptlevel_id() != id)))
/* 274 */         count++;
/*     */     }
/* 276 */     return count > 0;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 283 */     List l = getDeptLevelList();
/* 284 */     for (int i = 0; i < l.size(); i++)
/*     */     {
/* 286 */       System.out.println(((DeptLevelBean)l.get(i)).getDeptlevel_id());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void insertDeptLevelTest()
/*     */   {
/* 292 */     DeptLevelBean dlb = new DeptLevelBean();
/* 293 */     dlb.setDeptlevel_value(2);
/* 294 */     dlb.setDeptlevel_name("第一级");
/* 295 */     dlb.setDeptlevel_memo("第一级备注");
/* 296 */     insertDeptLevel(dlb, new SettingLogsBean());
/*     */   }
/*     */ 
/*     */   public static void updateDeptLevelTest() {
/* 300 */     DeptLevelBean dlb = new DeptLevelBean();
/* 301 */     dlb.setDeptlevel_id(6);
/* 302 */     dlb.setDeptlevel_value(2);
/* 303 */     dlb.setDeptlevel_name("第二级");
/* 304 */     dlb.setDeptlevel_memo("第二级备注");
/* 305 */     updateDeptLevel(dlb, new SettingLogsBean());
/*     */   }
/*     */ 
/*     */   public static void deleteDeptLevelTest()
/*     */   {
/* 310 */     deleteDeptLevel("1,6", new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.dept.DeptLevelManager
 * JD-Core Version:    0.6.2
 */