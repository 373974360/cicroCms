/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexInfoDaoImpl;
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
/*     */ public class IndexInfoServiceImpl
/*     */   implements IndexService
/*     */ {
/*  32 */   static int s_count = 50;
/*  33 */   static IIndexInfoDao indexInfoDao = new IndexInfoDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  45 */     IndexWriter indexWriter = null;
/*  46 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  48 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  49 */       int page = count / s_count + 1;
/*  50 */       System.out.println(mapSite + "appendALlDocument info count===" + count);
/*     */ 
/*  53 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  54 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  55 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  57 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  58 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  59 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  61 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  63 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  65 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*  66 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  68 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  70 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  73 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  76 */       for (int k = 0; k < page; k++) {
/*  77 */         int start_num = s_count * k;
/*  78 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  79 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  83 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getInfoListBySiteIdPages"), mapSite);
/*     */ 
/*  85 */         for (Map infoMap : infoList)
/*     */         {
/*  88 */           IndexBeanInfo indexBeanInfo = getIndexBeanInfo(infoMap);
/*     */ 
/*  91 */           Document doc = new Document();
/*  92 */           if (indexBeanInfo.getApp_id().equals("ggfw")) {
/*  93 */             typeField.setValue(indexBeanInfo.getApp_id());
/*  94 */             doc.add(typeField);
/*  95 */             typedField.setValue(indexBeanInfo.getApp_id());
/*  96 */             doc.add(typedField);
/*     */           } else {
/*  98 */             typeField.setValue(IndexBeanInfo.getType());
/*  99 */             doc.add(typeField);
/* 100 */             typedField.setValue(indexBeanInfo.getTyped());
/* 101 */             doc.add(typedField);
/*     */           }
/* 103 */           idField.setValue(indexBeanInfo.getId());
/* 104 */           doc.add(idField);
/* 105 */           titleField.setValue(indexBeanInfo.getTitle());
/* 106 */           doc.add(titleField);
/* 107 */           contentField.setValue(indexBeanInfo.getContent());
/* 108 */           doc.add(contentField);
/* 109 */           urlField.setValue(indexBeanInfo.getUrl());
/* 110 */           doc.add(urlField);
/* 111 */           site_idField.setValue(indexBeanInfo.getSite_id());
/* 112 */           doc.add(site_idField);
/*     */ 
/* 114 */           node_idField.setValue(indexBeanInfo.getNode_id());
/* 115 */           doc.add(node_idField);
/*     */ 
/* 117 */           model_idField.setValue(indexBeanInfo.getModel_id());
/* 118 */           doc.add(model_idField);
/*     */ 
/* 121 */           site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 122 */           doc.add(site_id_delField);
/*     */ 
/* 124 */           categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 125 */           System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 126 */           doc.add(categoryIdField);
/* 127 */           publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 128 */           doc.add(publish_timeField);
/*     */ 
/* 131 */           is_hostField.setValue(indexBeanInfo.getIs_host());
/* 132 */           doc.add(is_hostField);
/*     */ 
/* 135 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 141 */       e.printStackTrace();
/* 142 */       return false;
/*     */     }
/*     */     finally {
/* 145 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 146 */       s.close();
/*     */     }
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 158 */     IndexWriter indexWriter = null;
/* 159 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 163 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 165 */       if (infos.get("id") != null) {
/* 166 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 168 */         Map mapC = new HashMap();
/* 169 */         mapC.put("id", Integer.valueOf(id));
/* 170 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getInfoById"), mapC);
/*     */ 
/* 172 */         if (map == null) {
/* 173 */           return false;
/*     */         }
/*     */ 
/* 176 */         IndexBeanInfo indexBeanInfo = getIndexBeanInfo(map);
/*     */ 
/* 179 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 180 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 181 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 182 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 183 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 184 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 185 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 187 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 188 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/* 189 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 191 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 193 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 195 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 198 */         Document doc = new Document();
/* 199 */         if (indexBeanInfo.getApp_id().equals("ggfw")) {
/* 200 */           typeField.setValue(indexBeanInfo.getApp_id());
/* 201 */           doc.add(typeField);
/* 202 */           typedField.setValue(indexBeanInfo.getApp_id());
/* 203 */           doc.add(typedField);
/*     */         } else {
/* 205 */           typeField.setValue(IndexBeanInfo.getType());
/* 206 */           doc.add(typeField);
/* 207 */           typedField.setValue(indexBeanInfo.getTyped());
/* 208 */           doc.add(typedField);
/*     */         }
/* 210 */         idField.setValue(indexBeanInfo.getId());
/* 211 */         doc.add(idField);
/* 212 */         titleField.setValue(indexBeanInfo.getTitle());
/* 213 */         doc.add(titleField);
/* 214 */         contentField.setValue(indexBeanInfo.getContent());
/* 215 */         doc.add(contentField);
/* 216 */         urlField.setValue(indexBeanInfo.getUrl());
/* 217 */         doc.add(urlField);
/* 218 */         site_idField.setValue(indexBeanInfo.getSite_id());
/* 219 */         doc.add(site_idField);
/*     */ 
/* 221 */         node_idField.setValue(indexBeanInfo.getNode_id());
/* 222 */         doc.add(node_idField);
/*     */ 
/* 224 */         model_idField.setValue(indexBeanInfo.getModel_id());
/* 225 */         doc.add(model_idField);
/*     */ 
/* 228 */         site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 229 */         doc.add(site_id_delField);
/*     */ 
/* 231 */         System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 232 */         categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 233 */         doc.add(categoryIdField);
/* 234 */         publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 235 */         doc.add(publish_timeField);
/*     */ 
/* 237 */         is_hostField.setValue(indexBeanInfo.getIs_host());
/* 238 */         doc.add(is_hostField);
/*     */ 
/* 241 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 246 */       e.printStackTrace();
/* 247 */       return false;
/*     */     }
/*     */     finally {
/* 250 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 251 */       s.close();
/*     */     }
/* 250 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 251 */     s.close();
/*     */ 
/* 253 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 263 */     IndexWriter indexWriter = null;
/*     */     try {
/* 265 */       if (map.get("site_id") != null) {
/* 266 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 268 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
/* 269 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 273 */       e.printStackTrace();
/* 274 */       return false;
/*     */     }
/*     */     finally {
/* 277 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 279 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 289 */     IndexWriter indexWriter = null;
/*     */     try {
/* 291 */       if (infos.get("id") != null)
/*     */       {
/* 293 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 295 */         Term term = new Term("id", (String)infos.get("id"));
/* 296 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 300 */       e.printStackTrace();
/* 301 */       return false;
/*     */     }
/*     */     finally {
/* 304 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 306 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanInfo getIndexBeanInfo(Map info)
/*     */   {
/* 311 */     IndexBeanInfo indexBeanInfo = new IndexBeanInfo();
/*     */     try
/*     */     {
/* 314 */       Map infoMap = new HashMap();
/* 315 */       Iterator it = info.keySet().iterator();
/* 316 */       while (it.hasNext()) {
/* 317 */         String key = (String)it.next();
/* 318 */         Object object = info.get(key);
/* 319 */         String value = "";
/* 320 */         if ((object instanceof BigDecimal)) {
/* 321 */           value = object.toString();
/* 322 */         } else if ((object instanceof CLOB)) {
/* 323 */           CLOB clob = (CLOB)object;
/*     */ 
/* 329 */           if (clob != null) {
/* 330 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 335 */           value = String.valueOf(object);
/*     */         }
/* 337 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/*     */ 
/* 340 */       indexBeanInfo.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 341 */       indexBeanInfo.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 342 */       indexBeanInfo.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 343 */       indexBeanInfo.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString((String)infoMap.get("info_content"))));
/* 344 */       indexBeanInfo.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 345 */       indexBeanInfo.setSite_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 347 */       indexBeanInfo.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 349 */       indexBeanInfo.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 351 */       indexBeanInfo.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/*     */ 
/* 353 */       indexBeanInfo.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/*     */ 
/* 355 */       String time = (String)infoMap.get("released_dtime");
/* 356 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/*     */ 
/* 358 */       indexBeanInfo.setPublish_time(FormatUtil.formatNullString(time));
/* 359 */       return indexBeanInfo;
/*     */     } catch (Exception e) {
/* 361 */       e.printStackTrace();
/* 362 */     }return indexBeanInfo;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 369 */     IndexInfoServiceImpl impl = new IndexInfoServiceImpl();
/* 370 */     Map map = new HashMap();
/* 371 */     map.put("id", "1111");
/* 372 */     impl.appendSingleDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexInfoServiceImpl
 * JD-Core Version:    0.6.2
 */