/*     */ package com.cicro.wcm.services.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryDicBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.query.queryDicDao;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class QueryDicManager
/*     */   implements ISyncCatch
/*     */ {
/*  30 */   private static List<QueryDicBean> QueryDic_list = new ArrayList();
/*     */ 
/*     */   static {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  38 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  43 */     QueryDic_list.clear();
/*  44 */     QueryDic_list = queryDicDao.getAllQueryDicList();
/*     */   }
/*     */ 
/*     */   public static void reloadQueryDic()
/*     */   {
/*  52 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.query.QueryDicManager");
/*     */   }
/*     */ 
/*     */   public static List<QueryDicBean> getDicListByConf_id(int conf_id)
/*     */   {
/*  63 */     List l = new ArrayList();
/*  64 */     if ((QueryDic_list != null) && (QueryDic_list.size() > 0))
/*     */     {
/*  66 */       for (int i = 0; i < QueryDic_list.size(); i++)
/*     */       {
/*  68 */         if (((QueryDicBean)QueryDic_list.get(i)).getConf_id() == conf_id)
/*     */         {
/*  70 */           l.add((QueryDicBean)QueryDic_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/*  74 */     return l;
/*     */   }
/*     */ 
/*     */   public static QueryDicBean getDicByConf_Dic_Id(int dic_id, int conf_id)
/*     */   {
/*  79 */     QueryDicBean qdb = null;
/*  80 */     List l = getDicListByConf_id(conf_id);
/*  81 */     if (l.size() > 0)
/*     */     {
/*  83 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  85 */         if (((QueryDicBean)l.get(i)).getDic_id() == dic_id)
/*     */         {
/*  87 */           qdb = (QueryDicBean)l.get(i);
/*     */         }
/*     */       }
/*     */     }
/*  91 */     return qdb;
/*     */   }
/*     */ 
/*     */   public static List<QueryDicBean> getOneTypeDicList(int conf_id, String type)
/*     */   {
/* 101 */     List l = new ArrayList();
/* 102 */     if ((QueryDic_list != null) && (QueryDic_list.size() > 0))
/*     */     {
/* 104 */       for (int i = 0; i < QueryDic_list.size(); i++)
/*     */       {
/* 106 */         if ((type.equals(Integer.valueOf(((QueryDicBean)QueryDic_list.get(i)).getIs_query()))) && (((QueryDicBean)QueryDic_list.get(i)).getConf_id() == conf_id))
/* 107 */           l.add((QueryDicBean)QueryDic_list.get(i));
/* 108 */         else if ((type.equals(Integer.valueOf(((QueryDicBean)QueryDic_list.get(i)).getIs_result()))) && (((QueryDicBean)QueryDic_list.get(i)).getConf_id() == conf_id))
/* 109 */           l.add((QueryDicBean)QueryDic_list.get(i));
/* 110 */         else if ((type.equals(Integer.valueOf(((QueryDicBean)QueryDic_list.get(i)).getIs_selected()))) && (((QueryDicBean)QueryDic_list.get(i)).getConf_id() == conf_id)) {
/* 111 */           l.add((QueryDicBean)QueryDic_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/* 115 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryDic(int conf_id, List<QueryDicBean> l, SettingLogsBean stl)
/*     */   {
/* 127 */     if (queryDicDao.insertQueryDic(conf_id, l, stl))
/*     */     {
/* 129 */       reloadQueryDic();
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryDic(String conf_id)
/*     */   {
/* 145 */     if (queryDicDao.deleteQueryDic(conf_id))
/*     */     {
/* 147 */       reloadQueryDic();
/* 148 */       return true;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<QueryDicBean> getTypeDicList(String con)
/*     */   {
/* 162 */     return queryDicDao.getTypeDicList(con);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 169 */     System.out.println(getDicByConf_Dic_Id(3, 24));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryDicManager
 * JD-Core Version:    0.6.2
 */