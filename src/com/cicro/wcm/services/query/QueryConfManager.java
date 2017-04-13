/*     */ package com.cicro.wcm.services.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryConfBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.query.queryConfDao;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class QueryConfManager
/*     */   implements ISyncCatch
/*     */ {
/*  33 */   private static TreeMap<Integer, QueryConfBean> QueryConf_map = new TreeMap();
/*     */ 
/*     */   static {
/*  36 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  41 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  46 */     List queryConf_list = queryConfDao.getAllQueryConfList();
/*  47 */     QueryConf_map.clear();
/*  48 */     if ((queryConf_list != null) && (queryConf_list.size() > 0))
/*  49 */       for (int i = 0; i < queryConf_list.size(); i++)
/*  50 */         QueryConf_map.put(Integer.valueOf(((QueryConfBean)queryConf_list.get(i)).getConf_id()), (QueryConfBean)queryConf_list.get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadQueryConf()
/*     */   {
/*  62 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.query.QueryConfManager");
/*     */   }
/*     */ 
/*     */   public static List<QueryConfBean> getAllQueryConfList()
/*     */   {
/*  72 */     List QueryConf_list = new ArrayList();
/*  73 */     Set set = QueryConf_map.keySet();
/*     */ 
/*  75 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  76 */       QueryConf_list.add((QueryConfBean)QueryConf_map.get(Integer.valueOf(i)));
/*     */     }
/*  78 */     return QueryConf_list;
/*     */   }
/*     */ 
/*     */   public static String getAllQueryConfCounts()
/*     */   {
/*  88 */     return queryConfDao.getAllQueryConfCounts();
/*     */   }
/*     */ 
/*     */   public static String getQueryConfCount(Map<String, String> m)
/*     */   {
/*  98 */     return queryConfDao.getQueryConfCount(m);
/*     */   }
/*     */ 
/*     */   public static List<QueryConfBean> getQueryConfLists(Map<String, String> m)
/*     */   {
/* 109 */     return queryConfDao.getQueryConfList(m);
/*     */   }
/*     */ 
/*     */   public static QueryConfBean getQueryConfBean(int conf_id)
/*     */   {
/* 120 */     if (QueryConf_map.containsKey(Integer.valueOf(conf_id)))
/*     */     {
/* 122 */       return (QueryConfBean)QueryConf_map.get(Integer.valueOf(conf_id));
/*     */     }
/* 124 */     QueryConfBean ob = queryConfDao.getQueryConfBeanById(conf_id);
/* 125 */     if (ob != null)
/* 126 */       QueryConf_map.put(Integer.valueOf(conf_id), ob);
/* 127 */     return ob;
/*     */   }
/*     */ 
/*     */   public static int getQueryConfID()
/*     */   {
/* 138 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.DZ_CHAXUN_CONF_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryConf(QueryConfBean ob, SettingLogsBean stl)
/*     */   {
/* 150 */     if (queryConfDao.insertQueryConf(ob, stl))
/*     */     {
/* 152 */       reloadQueryConf();
/* 153 */       return true;
/*     */     }
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryConf(QueryConfBean ob, SettingLogsBean stl)
/*     */   {
/* 166 */     if (queryConfDao.updateQueryConf(ob, stl))
/*     */     {
/* 168 */       reloadQueryConf();
/* 169 */       return true;
/*     */     }
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryConf(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 183 */     if (queryConfDao.deleteQueryConf(m, stl))
/*     */     {
/* 185 */       reloadQueryConf();
/* 186 */       return true;
/*     */     }
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 194 */     QueryConfBean ob = new QueryConfBean();
/* 195 */     ob.setApp_id("cms");
/* 196 */     ob.setConf_id(40);
/* 197 */     ob.setConf_title("33333333333");
/* 198 */     ob.setT_list_id(1252);
/* 199 */     SettingLogsBean stl = new SettingLogsBean();
/*     */ 
/* 201 */     insertQueryConf(ob, stl);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryConfManager
 * JD-Core Version:    0.6.2
 */