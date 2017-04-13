/*     */ package com.cicro.wcm.services.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryDicBean;
/*     */ import com.cicro.wcm.bean.query.QueryItemBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
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
/*     */ public class QueryItemManager20120214
/*     */ {
/*     */   public static List<QueryItemBean> getQueryItemLists(Map<String, String> m)
/*     */   {
/*  27 */     return queryItemDao.getQueryItemList(m);
/*     */   }
/*     */ 
/*     */   public static String getQueryItemCount(Map<String, String> m)
/*     */   {
/*  36 */     String count = queryItemDao.getQueryItemCount(m);
/*  37 */     String conf_id = "";
/*  38 */     int num = 0;
/*  39 */     int n = 0;
/*  40 */     if (m != null) {
/*  41 */       conf_id = (String)m.get("conf_id");
/*  42 */       num = Integer.parseInt(queryItemDao.getQueryCellCount(conf_id));
/*     */     }
/*  44 */     if (num != 0) {
/*  45 */       n = Integer.parseInt(count) / num;
/*     */     }
/*  47 */     return String.valueOf(n);
/*     */   }
/*     */ 
/*     */   public static QueryItemBean getQueryItemBean(int item_id)
/*     */   {
/*  56 */     throw new Error("Unresolved compilation problem: \n\tThe method getQueryItemBeanById(int) is undefined for the type queryItemDao\n");
/*     */   }
/*     */ 
/*     */   public static int getQueryItemID()
/*     */   {
/*  69 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.DZ_CHAXUN_ITEM_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryItem(int cont_id, QueryItemBean ob, SettingLogsBean stl)
/*     */   {
/*  81 */     if (queryItemDao.insertQueryItem(cont_id, ob, stl))
/*     */     {
/*  83 */       return true;
/*     */     }
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryItem(QueryItemBean ob, SettingLogsBean stl)
/*     */   {
/*  96 */     if (queryItemDao.updateQueryItem(ob, stl))
/*     */     {
/*  98 */       return true;
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItem(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 111 */     throw new Error("Unresolved compilation problem: \n\tThe method deleteQueryItemByItemId(Map<String,String>, SettingLogsBean) is undefined for the type queryItemDao\n");
/*     */   }
/*     */ 
/*     */   public static boolean writeExcel(String file_name, int conf_id, String site_id, SettingLogsBean stb)
/*     */   {
/* 121 */     List QueryDicList = new ArrayList();
/*     */     try {
/* 123 */       Workbook book = Workbook.getWorkbook(new File(file_name));
/* 124 */       if (book != null)
/*     */       {
/* 127 */         Sheet sheet = book.getSheet(0);
/* 128 */         QueryDicList = QueryDicManager.getDicListByConf_id(conf_id);
/* 129 */         int n = 0;
/* 130 */         if (QueryDicList != null)
/*     */         {
/* 132 */           for (QueryDicBean qdb : QueryDicList)
/*     */           {
/* 134 */             for (int i = 1; i < sheet.getRows(); i++)
/*     */             {
/* 136 */               QueryItemBean item = new QueryItemBean();
/* 137 */               String temp = "";
/*     */               try {
/* 139 */                 if (sheet.getRow(i)[n].getContents() != null)
/*     */                 {
/* 141 */                   temp = sheet.getRow(i)[n].getContents();
/*     */                 }
/*     */               }
/*     */               catch (Exception e) {
/* 145 */                 e.printStackTrace();
/*     */               }
/* 147 */               item.setItem_id(i);
/* 148 */               item.setSite_id(site_id);
/* 149 */               item.setConf_id(conf_id);
/* 150 */               item.setItem_key(qdb.getDic_id());
/* 151 */               item.setItem_value(temp);
/* 152 */               insertQueryItem(conf_id, item, stb);
/*     */             }
/* 154 */             n++;
/*     */           }
/*     */         }
/* 157 */         return true;
/*     */       }
/*     */ 
/* 160 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 163 */       System.out.println(e);
/* 164 */     }return false;
/*     */   }
/*     */ 
/*     */   public static List<List<QueryItemBean>> getQueryItemValueList(String conf_id, int page_size)
/*     */   {
/* 170 */     int count = Integer.parseInt(queryItemDao.getQueryCellCount(conf_id));
/* 171 */     List l = new ArrayList();
/* 172 */     List l_f_db = null;
/* 173 */     Map m = new HashMap();
/* 174 */     m.put("conf_id", conf_id);
/* 175 */     m.put("cell_count", count);
/* 176 */     m.put("page_size", page_size);
/* 177 */     m.put("start_num", "0");
/* 178 */     l_f_db = queryItemDao.getQueryItemList(m);
/*     */ 
/* 180 */     List ml = new ArrayList();
/* 181 */     if (l_f_db != null)
/*     */     {
/* 183 */       int i = 1;
/* 184 */       for (QueryItemBean qib : l_f_db)
/*     */       {
/* 186 */         l.add(qib);
/* 187 */         if (i % count == 0)
/*     */         {
/* 189 */           List l_2 = new ArrayList();
/* 190 */           l_2.addAll(l);
/* 191 */           ml.add(l_2);
/* 192 */           l.clear();
/*     */         }
/* 194 */         i++;
/*     */       }
/*     */     }
/* 197 */     return ml;
/*     */   }
/*     */ 
/*     */   public static List<List<QueryItemBean>> getQueryListBrowser(String con, String conf_id, int counts, int page_sizes)
/*     */   {
/* 202 */     int count = Integer.parseInt(queryItemDao.getQueryCellCount(conf_id));
/* 203 */     List l = new ArrayList();
/* 204 */     List l_f_db = null;
/* 205 */     Map m = new HashMap();
/* 206 */     m.put("con", con);
/* 207 */     m.put("cell_count", count);
/* 208 */     m.put("page_size", page_sizes);
/* 209 */     m.put("start_num", counts);
/* 210 */     l_f_db = queryItemDao.getQueryListBrowser(m);
/*     */ 
/* 212 */     List ml = new ArrayList();
/* 213 */     if (l_f_db != null)
/*     */     {
/* 215 */       int i = 1;
/* 216 */       for (QueryItemBean qib : l_f_db)
/*     */       {
/* 218 */         l.add(qib);
/* 219 */         if (i % count == 0)
/*     */         {
/* 221 */           List l_2 = new ArrayList();
/* 222 */           l_2.addAll(l);
/* 223 */           ml.add(l_2);
/* 224 */           l.clear();
/*     */         }
/* 226 */         i++;
/*     */       }
/*     */     }
/* 229 */     return ml;
/*     */   }
/*     */ 
/*     */   public static String getQueryListCountBrowser(String con) {
/* 233 */     System.out.println("getQueryListCountBrowser=====" + con);
/* 234 */     Map m = new HashMap();
/* 235 */     m.put("con", con);
/* 236 */     return queryItemDao.getQueryListCountBrowser(m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryItemManager20120214
 * JD-Core Version:    0.6.2
 */