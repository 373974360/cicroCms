/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexVideoDaoImpl;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.db.IbatisSessionFactory;
/*     */ import com.cicro.wcm.services.search.index.IndexLuceneManager;
/*     */ import com.cicro.wcm.services.search.index.IndexService;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import oracle.sql.CLOB;
/*     */ import org.apache.ibatis.session.SqlSession;
/*     */ import org.apache.ibatis.session.SqlSessionFactory;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.document.Field.Index;
/*     */ import org.apache.lucene.document.Field.Store;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.Term;
/*     */ 
/*     */ public class IndexVideoServiceImpl
/*     */   implements IndexService
/*     */ {
/*  32 */   static int s_count = 50;
/*  33 */   static IIndexInfoDao indexInfoDao = new IndexVideoDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  45 */     IndexWriter indexWriter = null;
/*  46 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  48 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  49 */       int page = count / s_count + 1;
/*  50 */       System.out.println("appendALlDocument video count===" + count);
/*     */ 
/*  53 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  54 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  55 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  57 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  58 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  59 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  60 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*  61 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  63 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  65 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  67 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  69 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  72 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  75 */       for (int k = 0; k < page; k++) {
/*  76 */         int start_num = s_count * k;
/*  77 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  78 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  80 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getVideoListBySiteIdPages"), mapSite);
/*     */ 
/*  82 */         for (Map infoMap : infoList)
/*     */         {
/*  85 */           IndexBeanInfo indexBeanInfo = getIndexBeanInfo(infoMap);
/*     */ 
/*  88 */           Document doc = new Document();
/*  89 */           if (indexBeanInfo.getApp_id().equals("ggfw")) {
/*  90 */             typeField.setValue(indexBeanInfo.getApp_id());
/*  91 */             doc.add(typeField);
/*  92 */             typedField.setValue(indexBeanInfo.getApp_id());
/*  93 */             doc.add(typedField);
/*     */           } else {
/*  95 */             typeField.setValue(IndexBeanInfo.getType());
/*  96 */             doc.add(typeField);
/*  97 */             typedField.setValue(indexBeanInfo.getTyped());
/*  98 */             doc.add(typedField);
/*     */           }
/* 100 */           idField.setValue(indexBeanInfo.getId());
/* 101 */           doc.add(idField);
/* 102 */           titleField.setValue(indexBeanInfo.getTitle());
/* 103 */           doc.add(titleField);
/* 104 */           contentField.setValue(indexBeanInfo.getContent());
/* 105 */           doc.add(contentField);
/* 106 */           urlField.setValue(indexBeanInfo.getUrl());
/* 107 */           doc.add(urlField);
/* 108 */           site_idField.setValue(indexBeanInfo.getSite_id());
/* 109 */           doc.add(site_idField);
/*     */ 
/* 111 */           node_idField.setValue(indexBeanInfo.getNode_id());
/* 112 */           doc.add(node_idField);
/*     */ 
/* 114 */           model_idField.setValue(indexBeanInfo.getModel_id());
/* 115 */           doc.add(model_idField);
/*     */ 
/* 118 */           site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 119 */           doc.add(site_id_delField);
/*     */ 
/* 121 */           categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 122 */           System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 123 */           doc.add(categoryIdField);
/* 124 */           publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 125 */           doc.add(publish_timeField);
/*     */ 
/* 127 */           is_hostField.setValue(indexBeanInfo.getIs_host());
/* 128 */           doc.add(is_hostField);
/*     */ 
/* 131 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 137 */       e.printStackTrace();
/* 138 */       return false;
/*     */     }
/*     */     finally {
/* 141 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 142 */       s.close();
/*     */     }
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 154 */     IndexWriter indexWriter = null;
/* 155 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 159 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 161 */       if (infos.get("id") != null) {
/* 162 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 164 */         Map mapC = new HashMap();
/* 165 */         mapC.put("id", Integer.valueOf(id));
/* 166 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getVideoById"), mapC);
/*     */ 
/* 168 */         if (map == null) {
/* 169 */           return false;
/*     */         }
/*     */ 
/* 172 */         IndexBeanInfo indexBeanInfo = getIndexBeanInfo(map);
/*     */ 
/* 174 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 175 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 176 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 177 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 178 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 179 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 180 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 182 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 183 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/* 184 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 186 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 188 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 189 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 191 */         Document doc = new Document();
/* 192 */         if (indexBeanInfo.getApp_id().equals("ggfw")) {
/* 193 */           typeField.setValue(indexBeanInfo.getApp_id());
/* 194 */           doc.add(typeField);
/* 195 */           typedField.setValue(indexBeanInfo.getApp_id());
/* 196 */           doc.add(typedField);
/*     */         } else {
/* 198 */           typeField.setValue(IndexBeanInfo.getType());
/* 199 */           doc.add(typeField);
/* 200 */           typedField.setValue(indexBeanInfo.getTyped());
/* 201 */           doc.add(typedField);
/*     */         }
/* 203 */         idField.setValue(indexBeanInfo.getId());
/* 204 */         doc.add(idField);
/* 205 */         titleField.setValue(indexBeanInfo.getTitle());
/* 206 */         doc.add(titleField);
/* 207 */         contentField.setValue(indexBeanInfo.getContent());
/* 208 */         doc.add(contentField);
/* 209 */         urlField.setValue(indexBeanInfo.getUrl());
/* 210 */         doc.add(urlField);
/* 211 */         site_idField.setValue(indexBeanInfo.getSite_id());
/* 212 */         doc.add(site_idField);
/*     */ 
/* 214 */         node_idField.setValue(indexBeanInfo.getNode_id());
/* 215 */         doc.add(node_idField);
/*     */ 
/* 217 */         model_idField.setValue(indexBeanInfo.getModel_id());
/* 218 */         doc.add(model_idField);
/*     */ 
/* 221 */         site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 222 */         doc.add(site_id_delField);
/*     */ 
/* 224 */         System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 225 */         categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 226 */         doc.add(categoryIdField);
/* 227 */         publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 228 */         doc.add(publish_timeField);
/*     */ 
/* 230 */         is_hostField.setValue(indexBeanInfo.getIs_host());
/* 231 */         doc.add(is_hostField);
/*     */ 
/* 234 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 239 */       e.printStackTrace();
/* 240 */       return false;
/*     */     }
/*     */     finally {
/* 243 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 244 */       s.close();
/*     */     }
/* 243 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 244 */     s.close();
/*     */ 
/* 246 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 256 */     IndexWriter indexWriter = null;
/*     */     try {
/* 258 */       if (map.get("site_id") != null) {
/* 259 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 261 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
/* 262 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 266 */       e.printStackTrace();
/* 267 */       return false;
/*     */     }
/*     */     finally {
/* 270 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 282 */     IndexWriter indexWriter = null;
/*     */     try {
/* 284 */       if (infos.get("id") != null)
/*     */       {
/* 286 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 288 */         Term term = new Term("id", (String)infos.get("id"));
/* 289 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 293 */       e.printStackTrace();
/* 294 */       return false;
/*     */     }
/*     */     finally {
/* 297 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 299 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanInfo getIndexBeanInfo(Map info)
/*     */   {
/* 304 */     IndexBeanInfo indexBeanInfo = new IndexBeanInfo();
/*     */     try {
/* 306 */       Map infoMap = new HashMap();
/* 307 */       Iterator it = info.keySet().iterator();
/* 308 */       while (it.hasNext()) {
/* 309 */         String key = (String)it.next();
/* 310 */         Object object = info.get(key);
/* 311 */         String value = "";
/* 312 */         if ((object instanceof BigDecimal)) {
/* 313 */           value = object.toString();
/* 314 */         } else if ((object instanceof CLOB)) {
/* 315 */           CLOB clob = (CLOB)object;
/*     */ 
/* 321 */           if (clob != null) {
/* 322 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 327 */           value = String.valueOf(object);
/*     */         }
/* 329 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 331 */       indexBeanInfo.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 332 */       indexBeanInfo.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 333 */       indexBeanInfo.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 334 */       indexBeanInfo.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString((String)infoMap.get("info_content"))));
/* 335 */       indexBeanInfo.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 336 */       indexBeanInfo.setSite_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 338 */       indexBeanInfo.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/* 339 */       indexBeanInfo.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 341 */       indexBeanInfo.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/* 342 */       String time = (String)infoMap.get("released_dtime");
/* 343 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/*     */ 
/* 345 */       indexBeanInfo.setPublish_time(FormatUtil.formatNullString(time));
/*     */ 
/* 347 */       indexBeanInfo.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/*     */ 
/* 349 */       return indexBeanInfo;
/*     */     } catch (Exception e) {
/* 351 */       e.printStackTrace();
/* 352 */     }return indexBeanInfo;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 359 */     IndexVideoServiceImpl impl = new IndexVideoServiceImpl();
/* 360 */     Map map = new HashMap();
/* 361 */     map.put("id", "1111");
/* 362 */     impl.appendSingleDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexVideoServiceImpl
 * JD-Core Version:    0.6.2
 */