/*     */ package com.cicro.wcm.services.appeal.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.count.CategoryBean;
/*     */ import com.cicro.wcm.bean.appeal.count.CountBean;
/*     */ import com.cicro.wcm.bean.appeal.count.LetterCountBean;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CountServicesRPC
/*     */ {
/*     */   public static List<CountBean> getCountAimHandle(String s, String e, String b_ids)
/*     */   {
/*  31 */     return CountServices.getCountAimHandle(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListAll(String s, String e, String b_ids)
/*     */   {
/*  43 */     return CountDeptTreeServices.getListNew(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListAllType(String s, String e, String b_ids)
/*     */   {
/*  54 */     return CountDeptTreeServices.getListType(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListAllCaution(String s, String e, String b_ids)
/*     */   {
/*  65 */     return CountDeptTreeServices.getListCaution(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListAllPur(String s, String e, String b_ids)
/*     */   {
/*  76 */     return CountDeptTreeServices.getListPur(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListAllSatisfactionDept(String s, String e, String b_ids)
/*     */   {
/*  87 */     return CountDeptTreeServices.getListAllSatisfactionDept(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<LetterCountBean> getListByModelIdAndDept(Map map)
/*     */   {
/*  96 */     return CountServices.getListByModelIdAndDept(map);
/*     */   }
/*     */ 
/*     */   public static String getListByModelIdAndDeptCount(Map map)
/*     */   {
/* 105 */     return CountServices.getListByModelIdAndDeptCount(map);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountUsersHandle(String s, String e, String b_ids)
/*     */   {
/* 115 */     return CountServices.getCountUsersHandle(s, e, b_ids);
/*     */   }
/*     */ 
/*     */   public static List<LetterCountBean> getListByModelIdAndUserId(Map map)
/*     */   {
/* 124 */     return CountServices.getListByModelIdAndUserId(map);
/*     */   }
/*     */ 
/*     */   public static String getListByModelIdAndUserCount(Map map)
/*     */   {
/* 133 */     return CountServices.getListByModelIdAndUserCount(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.count.CountServicesRPC
 * JD-Core Version:    0.6.2
 */