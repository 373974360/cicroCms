/*     */ package com.cicro.wcm.dao.appeal.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.count.LetterCountBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CountDAO
/*     */ {
/*     */   public static String getCountByModelIdAndPur(Map map)
/*     */   {
/*  29 */     String count = "0";
/*     */     try {
/*  31 */       return DBManager.getString("count.getCountByModelIdAndPur", map);
/*     */     }
/*     */     catch (Exception e) {
/*  34 */       e.printStackTrace();
/*  35 */     }return count;
/*     */   }
/*     */ 
/*     */   public static String getCountByModelIdAndPurStatus(Map map)
/*     */   {
/*  45 */     String count = "0";
/*     */     try {
/*  47 */       return DBManager.getString("count.getCountByModelIdAndPurStatus", map);
/*     */     }
/*     */     catch (Exception e) {
/*  50 */       e.printStackTrace();
/*  51 */     }return count;
/*     */   }
/*     */ 
/*     */   public static String getCountByModelIdAndFlag(Map map)
/*     */   {
/*  61 */     String count = "0";
/*     */     try {
/*  63 */       return DBManager.getString("count.getCountByModelIdAndFlag", map);
/*     */     }
/*     */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */     }return count;
/*     */   }
/*     */ 
/*     */   public static String getCountByModelIdAndAlarm(Map map)
/*     */   {
/*  77 */     String count = "0";
/*     */     try {
/*  79 */       return DBManager.getString("count.getCountByModelIdAndAlarm", map);
/*     */     }
/*     */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */     }return count;
/*     */   }
/*     */ 
/*     */   public static String getCountByModelIdAndDept(Map map)
/*     */   {
/*  93 */     String count = "0";
/*     */     try {
/*  95 */       return DBManager.getString("count.getCountByModelIdAndDept", map);
/*     */     }
/*     */     catch (Exception e) {
/*  98 */       e.printStackTrace();
/*  99 */     }return count;
/*     */   }
/*     */ 
/*     */   public static String getCountByModelIdAndUserID(Map map)
/*     */   {
/* 109 */     String count = "0";
/*     */     try {
/* 111 */       return DBManager.getString("count.getCountByModelIdAndUserID", map);
/*     */     }
/*     */     catch (Exception e) {
/* 114 */       e.printStackTrace();
/* 115 */     }return count;
/*     */   }
/*     */ 
/*     */   public static String getCountByModelIdAndDeptSat(Map map)
/*     */   {
/* 125 */     String count = "0";
/*     */     try {
/* 127 */       return DBManager.getString("count.getCountByModelIdAndDeptSat", map);
/*     */     }
/*     */     catch (Exception e) {
/* 130 */       e.printStackTrace();
/* 131 */     }return count;
/*     */   }
/*     */ 
/*     */   public static List<LetterCountBean> getListByModelIdAndDept(Map map)
/*     */   {
/* 141 */     List list = new ArrayList();
/*     */     try {
/* 143 */       return DBManager.queryFList("count.getListByModelIdAndDept", map);
/*     */     }
/*     */     catch (Exception e) {
/* 146 */       e.printStackTrace();
/* 147 */     }return list;
/*     */   }
/*     */ 
/*     */   public static String getListByModelIdAndDeptCount(Map map)
/*     */   {
/* 157 */     String count = "0";
/*     */     try {
/* 159 */       return DBManager.getString("count.getListByModelIdAndDeptCount", map);
/*     */     }
/*     */     catch (Exception e) {
/* 162 */       e.printStackTrace();
/* 163 */     }return count;
/*     */   }
/*     */ 
/*     */   public static List<LetterCountBean> getListByModelIdAndUserID(Map map)
/*     */   {
/* 174 */     List list = new ArrayList();
/*     */     try {
/* 176 */       return DBManager.queryFList("count.getListByModelIdAndUserID", map);
/*     */     }
/*     */     catch (Exception e) {
/* 179 */       e.printStackTrace();
/* 180 */     }return list;
/*     */   }
/*     */ 
/*     */   public static String getListByModelIdAndUserIDCount(Map map)
/*     */   {
/* 190 */     String count = "0";
/*     */     try {
/* 192 */       return DBManager.getString("count.getListByModelIdAndUserIDCount", map);
/*     */     }
/*     */     catch (Exception e) {
/* 195 */       e.printStackTrace();
/* 196 */     }return count;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.count.CountDAO
 * JD-Core Version:    0.6.2
 */