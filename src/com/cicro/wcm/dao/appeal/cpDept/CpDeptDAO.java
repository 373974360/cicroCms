/*     */ package com.cicro.wcm.dao.appeal.cpDept;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CpDeptDAO
/*     */ {
/*     */   public static boolean insertCpdept(CpDeptBean dept)
/*     */   {
/*  27 */     return DBManager.insert("insert_cp_dept", dept);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpdept(String deptIds)
/*     */   {
/*  36 */     Map m = new HashMap();
/*  37 */     m.put("dept_ids", deptIds);
/*  38 */     return DBManager.delete("delete_cp_dept", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateCpDept(CpDeptBean dept)
/*     */   {
/*  47 */     return DBManager.update("update_cp_dept", dept);
/*     */   }
/*     */ 
/*     */   public static boolean saveDeptSort(String dept_ids)
/*     */   {
/*  59 */     String[] tempA = dept_ids.split(",");
/*  60 */     Map m = new HashMap();
/*     */     try
/*     */     {
/*  64 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  66 */         m.put("dept_id", tempA[i]);
/*  67 */         m.put("sort_id", Integer.valueOf(i + 1));
/*  68 */         DBManager.update("savesort_cpDept", m);
/*     */       }
/*  70 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  74 */       e.printStackTrace();
/*  75 */     }return false;
/*     */   }
/*     */ 
/*     */   public static CpDeptBean getCpDeptBean(String dept_id)
/*     */   {
/*  85 */     return (CpDeptBean)DBManager.queryFObj("getCpDeptBean", dept_id);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getCpDeptList()
/*     */   {
/*  94 */     return DBManager.queryFList("getCpDeptList", "");
/*     */   }
/*     */ 
/*     */   public static String getCpDeptCount(Map<String, String> m)
/*     */   {
/* 103 */     return DBManager.getString("getCpDeptCount", m);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getAllCpDeptBySort()
/*     */   {
/* 112 */     return DBManager.queryFList("getAllCpDeptBySort", "");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.cpDept.CpDeptDAO
 * JD-Core Version:    0.6.2
 */