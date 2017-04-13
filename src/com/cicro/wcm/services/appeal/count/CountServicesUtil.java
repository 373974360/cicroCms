/*     */ package com.cicro.wcm.services.appeal.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CPUserReleInfo;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import com.cicro.wcm.bean.appeal.satisfaction.SatisfactionBean;
/*     */ import com.cicro.wcm.services.appeal.cpDept.CpDeptManager;
/*     */ import com.cicro.wcm.services.appeal.cpUser.CpUserManager;
/*     */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*     */ import com.cicro.wcm.services.appeal.purpose.PurposeManager;
/*     */ import com.cicro.wcm.services.appeal.satisfaction.SatisfactionManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class CountServicesUtil
/*     */ {
/*     */   public static List<Map> getBusinessList()
/*     */   {
/*  38 */     List list = new ArrayList();
/*     */     try
/*     */     {
/*  43 */       List modelBeans = ModelManager.getAllSQModelList();
/*  44 */       for (ModelBean bean : modelBeans) {
/*  45 */         Map map = new HashMap();
/*  46 */         map.put("id", String.valueOf(bean.getModel_id()));
/*  47 */         map.put("name", bean.getModel_cname());
/*  48 */         list.add(map);
/*     */       }
/*     */ 
/*  51 */       return list;
/*     */     } catch (Exception e) {
/*  53 */       e.printStackTrace();
/*  54 */     }return list;
/*     */   }
/*     */ 
/*     */   public static String getBusinessName(String id)
/*     */   {
/*  65 */     String name = "";
/*     */     try
/*     */     {
/*  69 */       return "领导信箱";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  73 */       e.printStackTrace();
/*  74 */     }return name;
/*     */   }
/*     */ 
/*     */   public static List<Map> getPurposeList()
/*     */   {
/*  84 */     List list = new ArrayList();
/*     */     try
/*     */     {
/*  89 */       List listBean = PurposeManager.getAllPurposeList();
/*  90 */       for (PurposeBean bean : listBean) {
/*  91 */         Map map = new HashMap();
/*  92 */         map.put("id", String.valueOf(bean.getPur_id()));
/*  93 */         map.put("name", bean.getPur_name());
/*  94 */         list.add(map);
/*     */       }
/*     */ 
/*  97 */       return list;
/*     */     } catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<Map> getSatisfactionList()
/*     */   {
/* 110 */     List list = new ArrayList();
/*     */     try
/*     */     {
/* 123 */       List listBean = SatisfactionManager.getSatisfactionList();
/* 124 */       for (SatisfactionBean bean : listBean) {
/* 125 */         Map map = new HashMap();
/* 126 */         map.put("id", String.valueOf(bean.getSat_id()));
/* 127 */         map.put("name", bean.getSat_item());
/* 128 */         list.add(map);
/*     */       }
/*     */ 
/* 131 */       return list;
/*     */     } catch (Exception e) {
/* 133 */       e.printStackTrace();
/* 134 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getDeptmentList()
/*     */   {
/* 144 */     List list = new ArrayList();
/*     */     try {
/* 146 */       List deptBeansList = CpDeptManager.getAllCpDeptBySort();
/* 147 */       for (CpDeptBean bean : deptBeansList) {
/* 148 */         list.add(bean);
/*     */       }
/*     */ 
/* 151 */       return list;
/*     */     } catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CPUserReleInfo> getCPUserReleList()
/*     */   {
/* 163 */     List list = new ArrayList();
/*     */     try {
/* 165 */       Map m = CpUserManager.getAllReleUserMap();
/* 166 */       for (Entry entry : m.entrySet()) {
/* 167 */         CPUserReleInfo CPUserRele = (CPUserReleInfo)entry.getValue();
/* 168 */         list.add(CPUserRele);
/*     */       }
/* 170 */       return list;
/*     */     } catch (Exception e) {
/* 172 */       e.printStackTrace();
/* 173 */     }return list;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.count.CountServicesUtil
 * JD-Core Version:    0.6.2
 */