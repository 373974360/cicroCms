/*     */ package com.cicro.wcm.services.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryDicBean;
/*     */ import com.cicro.wcm.bean.query.QueryItemBean;
/*     */ import com.cicro.wcm.dao.query.queryItemDao;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ import jxl.Workbook;
/*     */ 
/*     */ public class QueryItemManager
/*     */ {
/*     */   public static String getQueryItemCount(Map<String, String> m)
/*     */   {
/*  28 */     String count = queryItemDao.getQueryItemCount(m);
/*  29 */     String conf_id = "";
/*  30 */     int num = 0;
/*  31 */     int n = 0;
/*  32 */     if (m != null) {
/*  33 */       conf_id = (String)m.get("conf_id");
/*  34 */       num = Integer.parseInt(queryItemDao.getQueryCellCount(conf_id));
/*     */     }
/*  36 */     if (num != 0) {
/*  37 */       n = Integer.parseInt(count) / num;
/*     */     }
/*  39 */     return String.valueOf(n);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemBeans(int item_id, int conf_id)
/*     */   {
/*  48 */     Map m = new HashMap();
/*  49 */     m.put("item_id", Integer.valueOf(item_id));
/*  50 */     m.put("conf_id", Integer.valueOf(conf_id));
/*  51 */     return queryItemDao.getQueryItemBeans(m);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemLists(Map m)
/*     */   {
/*  61 */     String conf_id = (String)m.get("conf_id");
/*  62 */     String table_name = " from cs_dz_cx_" + conf_id + " ";
/*  63 */     m.put("table_name", table_name);
/*  64 */     return queryItemDao.getQueryItemLists(m);
/*     */   }
/*     */ 
/*     */   public static String getQueryItemCounts(Map m)
/*     */   {
/*  69 */     String conf_id = (String)m.get("conf_id");
/*  70 */     String table_name = " from cs_dz_cx_" + conf_id + " ";
/*  71 */     m.put("table_name", table_name);
/*  72 */     return queryItemDao.getQueryItemCounts(m);
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryItem(int cont_id, QueryItemBean ob, SettingLogsBean stl)
/*     */   {
/*  83 */     if (queryItemDao.insertQueryItem(cont_id, ob, stl))
/*     */     {
/*  85 */       return true;
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryItemByConf_id(int conf_id, List<QueryItemBean> l, SettingLogsBean stl)
/*     */   {
/*  99 */     if (l != null) {
/* 100 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 102 */         QueryItemBean item = new QueryItemBean();
/*     */ 
/* 106 */         item.setItem_id(((QueryItemBean)l.get(i)).getItem_id());
/* 107 */         item.setSite_id(((QueryItemBean)l.get(i)).getSite_id());
/* 108 */         item.setConf_id(conf_id);
/* 109 */         item.setItem_key(((QueryItemBean)l.get(i)).getItem_key());
/* 110 */         item.setItem_value(((QueryItemBean)l.get(i)).getItem_value());
/*     */ 
/* 112 */         queryItemDao.insertQueryItem(conf_id, item, stl);
/*     */       }
/* 114 */       return true;
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryItem(List<QueryItemBean> l, SettingLogsBean stl)
/*     */   {
/* 127 */     if (l != null)
/*     */     {
/* 129 */       for (int i = 0; i < l.size(); i++) {
/* 130 */         QueryItemBean item = new QueryItemBean();
/* 131 */         item.setItem_id(((QueryItemBean)l.get(i)).getItem_id());
/* 132 */         item.setConf_id(((QueryItemBean)l.get(i)).getConf_id());
/* 133 */         item.setItem_key(((QueryItemBean)l.get(i)).getItem_key());
/* 134 */         item.setItem_value(((QueryItemBean)l.get(i)).getItem_value());
/*     */ 
/* 136 */         queryItemDao.updateQueryItem(item, stl);
/*     */       }
/* 138 */       return true;
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItem(Map m, SettingLogsBean stl)
/*     */   {
/* 153 */     if (queryItemDao.deleteQueryItemByItem(m, stl))
/*     */     {
/* 155 */       return true;
/*     */     }
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItemByConf_id(int conf_id, SettingLogsBean stl)
/*     */   {
/* 169 */     if (queryItemDao.deleteQueryItemByConf_id(conf_id, stl))
/*     */     {
/* 171 */       return true;
/*     */     }
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean writeExcel(String file_name, int conf_id, String site_id, SettingLogsBean stb)
/*     */   {
/* 179 */     List QueryDicList = new ArrayList();
/*     */     try {
/* 181 */       Workbook book = Workbook.getWorkbook(new File(file_name));
/* 182 */       deleteQueryItemByConf_id(conf_id, stb);
/* 183 */       if (book != null)
/*     */       {
/* 186 */         Sheet sheet = book.getSheet(0);
/* 187 */         QueryDicList = QueryDicManager.getDicListByConf_id(conf_id);
/* 188 */         int n = 0;
/* 189 */         if (QueryDicList != null)
/*     */         {
/* 191 */           for (QueryDicBean qdb : QueryDicList)
/*     */           {
/* 193 */             for (int i = 1; i < sheet.getRows(); i++)
/*     */             {
/* 195 */               QueryItemBean item = new QueryItemBean();
/* 196 */               String temp = "";
/*     */               try {
/* 198 */                 if (sheet.getRow(i)[n].getContents() != null)
/*     */                 {
/* 200 */                   temp = sheet.getRow(i)[n].getContents();
/*     */                 }
/*     */               }
/*     */               catch (Exception e) {
/* 204 */                 e.printStackTrace();
/*     */               }
/* 206 */               item.setItem_id(i);
/* 207 */               item.setSite_id(site_id);
/* 208 */               item.setConf_id(conf_id);
/* 209 */               item.setItem_key(qdb.getDic_id());
/* 210 */               item.setItem_value(temp);
/* 211 */               insertQueryItem(conf_id, item, stb);
/*     */             }
/* 213 */             n++;
/*     */           }
/*     */         }
/* 216 */         return true;
/*     */       }
/*     */ 
/* 219 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 222 */       System.out.println(e);
/* 223 */     }return false;
/*     */   }
/*     */ 
/*     */   public static List<List<QueryItemBean>> getQueryItemValueList(String conf_id, int page_size)
/*     */   {
/* 229 */     int count = Integer.parseInt(queryItemDao.getQueryCellCount(conf_id));
/* 230 */     List l = new ArrayList();
/* 231 */     List l_f_db = null;
/* 232 */     Map m = new HashMap();
/* 233 */     m.put("conf_id", conf_id);
/* 234 */     m.put("cell_count", count);
/* 235 */     m.put("page_size", page_size);
/* 236 */     m.put("start_num", "0");
/* 237 */     l_f_db = queryItemDao.getQueryItemList(m);
/*     */ 
/* 239 */     List ml = new ArrayList();
/* 240 */     if (l_f_db != null)
/*     */     {
/* 242 */       int i = 1;
/* 243 */       for (QueryItemBean qib : l_f_db)
/*     */       {
/* 245 */         l.add(qib);
/* 246 */         if (i % count == 0)
/*     */         {
/* 248 */           List l_2 = new ArrayList();
/* 249 */           l_2.addAll(l);
/* 250 */           ml.add(l_2);
/* 251 */           l.clear();
/*     */         }
/* 253 */         i++;
/*     */       }
/*     */     }
/* 256 */     return ml;
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryListBrowser(String con, String conf_id, int counts, int page_sizes)
/*     */   {
/* 262 */     List l = null;
/* 263 */     int count = Integer.parseInt(queryItemDao.getQueryCellCount(conf_id));
/* 264 */     Map m = new HashMap();
/*     */ 
/* 266 */     m.put("con", con);
/* 267 */     m.put("page_size", page_sizes);
/* 268 */     m.put("start_num", counts);
/*     */ 
/* 270 */     return l = queryItemDao.getQueryListBrowser(m);
/*     */   }
/*     */ 
/*     */   public static String getQueryListCountBrowser(String con) {
/* 274 */     Map m = new HashMap();
/*     */ 
/* 276 */     m.put("con", con);
/* 277 */     return queryItemDao.getQueryListCountBrowser(m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryItemManager
 * JD-Core Version:    0.6.2
 */