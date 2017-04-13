/*     */ package com.cicro.wcm.services.appeal.cpDept;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CpDeptRPC
/*     */ {
/*     */   public static String getCpDeptName(int id)
/*     */   {
/*  31 */     return CpDeptManager.getCpDeptName(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCpdept(CpDeptBean dept)
/*     */   {
/*  40 */     return CpDeptManager.insertCpdept(dept);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpdept(String dept_ids)
/*     */   {
/*  49 */     return CpDeptManager.deleteCpdept(dept_ids);
/*     */   }
/*     */ 
/*     */   public static boolean updateCpDept(CpDeptBean dept)
/*     */   {
/*  58 */     return CpDeptManager.updateCpDept(dept);
/*     */   }
/*     */ 
/*     */   public static boolean saveDeptSort(String dept_ids) {
/*  62 */     return CpDeptManager.saveDeptSort(dept_ids);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getAllCpDeptList()
/*     */   {
/*  71 */     return CpDeptManager.getCpDeptList();
/*     */   }
/*     */ 
/*     */   public static String getCpDeptCount(Map<String, String> m)
/*     */   {
/*  80 */     return CpDeptManager.getCpDeptCount(m);
/*     */   }
/*     */ 
/*     */   public static CpDeptBean getCpDeptBean(int dept_id)
/*     */   {
/*  90 */     return CpDeptManager.getCpDeptBean(dept_id);
/*     */   }
/*     */ 
/*     */   public static String getDeptTreeJsonStr(int node)
/*     */   {
/*  98 */     return CpDeptManager.getDeptTreeJsonStr(node);
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getChildDeptList(String dept_id)
/*     */   {
/* 107 */     System.out.println("91 >>>>>>>>>" + dept_id);
/* 108 */     return CpDeptManager.getChildDeptList(dept_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpDept.CpDeptRPC
 * JD-Core Version:    0.6.2
 */