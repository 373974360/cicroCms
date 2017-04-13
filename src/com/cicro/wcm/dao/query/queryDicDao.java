/*     */ package com.cicro.wcm.dao.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryDicBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.BoneDataSourceFactory;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class queryDicDao
/*     */ {
/*     */   public static List<QueryDicBean> getAllQueryDicList()
/*     */   {
/*  23 */     return DBManager.queryFList("getAllQueryDicList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryDic(int conf_id, List<QueryDicBean> l, SettingLogsBean stl)
/*     */   {
/*  34 */     if (deleteQueryDic(conf_id))
/*     */     {
/*  36 */       Map m = new HashMap();
/*  37 */       String temp = "";
/*  38 */       if ((l != null) && (l.size() > 0)) {
/*     */         try
/*     */         {
/*  41 */           for (int i = 0; i < l.size(); i++)
/*     */           {
/*  43 */             int n = i + 1;
/*  44 */             ((QueryDicBean)l.get(i)).setDic_id(n);
/*  45 */             ((QueryDicBean)l.get(i)).setConf_id(conf_id);
/*  46 */             if ("mssql".equals(BoneDataSourceFactory.getDataTypeName()))
/*     */             {
/*  48 */               temp = temp + ",isnull(max(case item_key when " + n + " then item_value end),'') as item_" + n;
/*     */             }
/*  50 */             else temp = temp + ",nvl(max(case item_key when " + n + " then item_value end),'') as item_" + n;
/*  51 */             DBManager.insert("insert_QueryDic", l.get(i));
/*     */           }
/*  53 */           if ("mssql".equals(BoneDataSourceFactory.getDataTypeName()))
/*     */           {
/*  55 */             m.put("sql", "create view cs_dz_cx_" + conf_id + " as select top 100000 item_id " + temp + " from cs_dz_chaxun_item where conf_id=" + conf_id + " group by item_id order by item_id");
/*     */           }
/*     */           else
/*     */           {
/*  59 */             m.put("sql", "create or replace view cs_dz_cx_" + conf_id + " as " + 
/*  60 */               "select item_id " + temp + " from  cs_dz_chaxun_item where conf_id=" + conf_id + " group by item_id order by item_id");
/*     */           }
/*     */ 
/*  63 */           PublicTableDAO.createSql(m);
/*     */         }
/*     */         catch (Exception e) {
/*  66 */           e.printStackTrace();
/*  67 */           return false;
/*     */         }
/*     */       }
/*  70 */       PublicTableDAO.insertSettingLogs("新增", "字段 业务", conf_id, stl);
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryDic(String conf_id)
/*     */   {
/*  84 */     Map m = new HashMap();
/*  85 */     m.put("conf_id", conf_id);
/*     */ 
/*  87 */     Map m2 = new HashMap();
/*  88 */     m2.put("sql", "drop view cs_dz_cx_" + conf_id);
/*  89 */     PublicTableDAO.createSql(m2);
/*  90 */     if (DBManager.delete("delete_QueryDic", m))
/*     */     {
/*  92 */       return true;
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<QueryDicBean> getTypeDicList(String con)
/*     */   {
/* 105 */     Map m = new HashMap();
/* 106 */     m.put("con", con);
/* 107 */     return DBManager.queryFList("getTypeDicList_browser", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.query.queryDicDao
 * JD-Core Version:    0.6.2
 */