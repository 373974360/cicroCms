/*     */ package com.cicro.wcm.services.appeal.satisfaction;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.appeal.satisfaction.SatRecordBean;
/*     */ import com.cicro.wcm.bean.appeal.satisfaction.SatisfactionBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.satisfaction.SatisfactionDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class SatisfactionManager
/*     */   implements ISyncCatch
/*     */ {
/*  29 */   private static TreeMap<Integer, SatisfactionBean> sf_map = new TreeMap();
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
/*  42 */     List sf_list = SatisfactionDAO.getAllSatisfactionList();
/*  43 */     sf_map.clear();
/*  44 */     if ((sf_list != null) && (sf_list.size() > 0))
/*     */     {
/*  46 */       for (int i = 0; i < sf_list.size(); i++)
/*     */       {
/*  48 */         sf_map.put(Integer.valueOf(((SatisfactionBean)sf_list.get(i)).getSat_id()), (SatisfactionBean)sf_list.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSatisfaction()
/*     */   {
/*  61 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.satisfaction.SatisfactionManager");
/*     */   }
/*     */ 
/*     */   public static List<SatisfactionBean> getSatisfactionList()
/*     */   {
/*  71 */     List sf_list = new ArrayList();
/*  72 */     if (sf_map != null) {
/*  73 */       Iterator iter = sf_map.entrySet().iterator();
/*  74 */       while (iter.hasNext()) {
/*  75 */         Map.Entry entry = (Map.Entry)iter.next();
/*  76 */         sf_list.add((SatisfactionBean)entry.getValue());
/*     */       }
/*     */     } else {
/*  79 */       sf_list = SatisfactionDAO.getAllSatisfactionList();
/*     */     }
/*  81 */     return sf_list;
/*     */   }
/*     */ 
/*     */   public static boolean insertSatisfaction(List<SatisfactionBean> sf_list, SettingLogsBean stl)
/*     */   {
/*  92 */     if (deleteSatisfactionBean(stl))
/*     */     {
/*  94 */       if ((sf_list != null) && (sf_list.size() > 0))
/*     */       {
/*  96 */         for (int i = 0; i < sf_list.size(); i++)
/*     */         {
/*  99 */           ((SatisfactionBean)sf_list.get(i)).setSat_id(i + 1);
/* 100 */           if (SatisfactionDAO.insertSatisfaction((SatisfactionBean)sf_list.get(i)))
/*     */           {
/* 102 */             PublicTableDAO.insertSettingLogs("添加", "满意度指标", i + 1, stl);
/*     */           }
/*     */         }
/* 105 */         reloadSatisfaction();
/*     */       }
/* 107 */       return true;
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSatisfactionBean(SettingLogsBean stl)
/*     */   {
/* 119 */     if (SatisfactionDAO.deleteSatisfaction())
/*     */     {
/* 121 */       PublicTableDAO.insertSettingLogs("删除", "满意度指标", "", stl);
/*     */ 
/* 123 */       return true;
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static SatisfactionBean getSatisfactionBean(int sf_id)
/*     */   {
/* 135 */     if (sf_map.containsKey(Integer.valueOf(sf_id)))
/*     */     {
/* 137 */       return (SatisfactionBean)sf_map.get(Integer.valueOf(sf_id));
/*     */     }
/*     */ 
/* 140 */     SatisfactionBean sf = SatisfactionDAO.getSatisfactionBean(sf_id);
/* 141 */     if (sf != null) {
/* 142 */       sf_map.put(Integer.valueOf(sf_id), sf);
/* 143 */       return sf;
/*     */     }
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertSatRecord(String sq_id, String[] temp_raty)
/*     */   {
/* 156 */     List sf_list = getSatisfactionList();
/* 157 */     if ((sf_list != null) && (sf_list.size() > 0))
/*     */     {
/* 159 */       String add_time = DateUtil.getCurrentDateTime();
/* 160 */       for (int i = 0; i < sf_list.size(); i++) {
/*     */         try
/*     */         {
/* 163 */           SatRecordBean srb = new SatRecordBean();
/* 164 */           srb.setSq_id(Integer.parseInt(sq_id));
/* 165 */           srb.setSat_id(((SatisfactionBean)sf_list.get(i)).getSat_id());
/* 166 */           srb.setSat_score(Integer.parseInt(temp_raty[i]));
/* 167 */           srb.setAdd_dtime(add_time);
/* 168 */           SatisfactionDAO.insertSatRecord(srb);
/*     */         }
/*     */         catch (Exception e) {
/* 171 */           e.printStackTrace();
/* 172 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 176 */     return true;
/*     */   }
/*     */ 
/*     */   public static String getSatScoreBySQID(String sq_id)
/*     */   {
/* 186 */     return SatisfactionDAO.getSatScoreBySQID(sq_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 191 */     System.out.println(getSatScoreBySQID("84"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.satisfaction.SatisfactionManager
 * JD-Core Version:    0.6.2
 */