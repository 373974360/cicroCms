/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanFile;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexFileDaoImpl;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.db.IbatisSessionFactory;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
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
/*     */ public class IndexFileServiceImpl
/*     */   implements IndexService
/*     */ {
/*  32 */   static int s_count = 50;
/*  33 */   static IIndexInfoDao indexInfoDao = new IndexFileDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  45 */     IndexWriter indexWriter = null;
/*  46 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  48 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  49 */       int page = count / s_count + 1;
/*  50 */       System.out.println("appendALlDocument file count===" + count);
/*     */ 
/*  53 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  54 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  55 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  57 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  58 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  59 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  60 */       Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/*  61 */       Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/*  62 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  63 */       Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  64 */       Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  65 */       Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  66 */       Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/*  67 */       Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  68 */       Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  69 */       Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  71 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  73 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  75 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  77 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  79 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  81 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  84 */       for (int k = 0; k < page; k++) {
/*  85 */         int start_num = s_count * k;
/*  86 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  87 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  89 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getFileListBySiteIdPages"), mapSite);
/*     */ 
/*  91 */         for (Map infoMap : infoList)
/*     */         {
/*  94 */           IndexBeanFile indexBeanFile = getIndexBeanInfo(infoMap);
/*     */ 
/*  96 */           System.out.println("appendALlDocument id===" + indexBeanFile.getId());
/*     */ 
/*  99 */           Document doc = new Document();
/* 100 */           if (indexBeanFile.getApp_id().equals("ggfw")) {
/* 101 */             typeField.setValue(indexBeanFile.getApp_id());
/* 102 */             doc.add(typeField);
/* 103 */             typedField.setValue(indexBeanFile.getApp_id());
/* 104 */             doc.add(typedField);
/*     */           } else {
/* 106 */             typeField.setValue(IndexBeanFile.getType());
/* 107 */             doc.add(typeField);
/* 108 */             typedField.setValue(indexBeanFile.getTyped());
/* 109 */             doc.add(typedField);
/*     */           }
/* 111 */           idField.setValue(indexBeanFile.getId());
/* 112 */           doc.add(idField);
/* 113 */           titleField.setValue(indexBeanFile.getTitle());
/* 114 */           doc.add(titleField);
/* 115 */           contentField.setValue(indexBeanFile.getContent());
/* 116 */           doc.add(contentField);
/* 117 */           urlField.setValue(indexBeanFile.getUrl());
/* 118 */           doc.add(urlField);
/* 119 */           site_idField.setValue(indexBeanFile.getSite_id());
/* 120 */           doc.add(site_idField);
/*     */ 
/* 122 */           node_idField.setValue(indexBeanFile.getNode_id());
/* 123 */           doc.add(node_idField);
/*     */ 
/* 125 */           model_idField.setValue(indexBeanFile.getModel_id());
/* 126 */           doc.add(model_idField);
/*     */ 
/* 128 */           wnumberField.setValue(indexBeanFile.getWnumber());
/* 129 */           doc.add(wnumberField);
/* 130 */           codeField.setValue(indexBeanFile.getCode());
/* 131 */           doc.add(codeField);
/* 132 */           publish_timeField.setValue(indexBeanFile.getPublish_time());
/* 133 */           doc.add(publish_timeField);
/*     */ 
/* 135 */           c_timeField.setValue(indexBeanFile.getC_time());
/* 136 */           doc.add(c_timeField);
/* 137 */           take_timeField.setValue(indexBeanFile.getTake_time());
/* 138 */           doc.add(take_timeField);
/* 139 */           over_timeField.setValue(indexBeanFile.getOver_time());
/* 140 */           doc.add(over_timeField);
/* 141 */           object_wordsField.setValue(indexBeanFile.getObject_words());
/* 142 */           doc.add(object_wordsField);
/* 143 */           form_typeField.setValue(indexBeanFile.getForm_type());
/* 144 */           doc.add(form_typeField);
/* 145 */           content_typeField.setValue(indexBeanFile.getContent_type());
/* 146 */           doc.add(content_typeField);
/* 147 */           publish_orgField.setValue(indexBeanFile.getPublish_org());
/* 148 */           doc.add(publish_orgField);
/*     */ 
/* 150 */           site_id_delField.setValue(indexBeanFile.getSite_id().toLowerCase());
/* 151 */           doc.add(site_id_delField);
/*     */ 
/* 153 */           categoryIdField.setValue(indexBeanFile.getCategoryId());
/* 154 */           System.out.println("appendALlDocument id:" + indexBeanFile.getId() + "     CategoryId:" + indexBeanFile.getCategoryId());
/* 155 */           doc.add(categoryIdField);
/*     */ 
/* 157 */           is_hostField.setValue(indexBeanFile.getIs_host());
/* 158 */           doc.add(is_hostField);
/*     */ 
/* 160 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 166 */       e.printStackTrace();
/* 167 */       return false;
/*     */     }
/*     */     finally {
/* 170 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 171 */       s.close();
/*     */     }
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 183 */     IndexWriter indexWriter = null;
/* 184 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 188 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 190 */       if (infos.get("id") != null) {
/* 191 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 193 */         Map mapC = new HashMap();
/* 194 */         mapC.put("id", Integer.valueOf(id));
/* 195 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getInfoById"), mapC);
/*     */ 
/* 197 */         if (map == null) {
/* 198 */           return false;
/*     */         }
/*     */ 
/* 201 */         IndexBeanFile indexBeanFile = getIndexBeanInfo(map);
/*     */ 
/* 204 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 205 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 206 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 207 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 208 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 209 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 210 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/* 211 */         Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/* 212 */         Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/* 213 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 214 */         Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 215 */         Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 216 */         Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 217 */         Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/* 218 */         Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 219 */         Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 220 */         Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 222 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 224 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 226 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 228 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 230 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 232 */         Document doc = new Document();
/* 233 */         if (indexBeanFile.getApp_id().equals("ggfw")) {
/* 234 */           typeField.setValue(indexBeanFile.getApp_id());
/* 235 */           doc.add(typeField);
/* 236 */           typedField.setValue(indexBeanFile.getApp_id());
/* 237 */           doc.add(typedField);
/*     */         } else {
/* 239 */           typeField.setValue(IndexBeanFile.getType());
/* 240 */           doc.add(typeField);
/* 241 */           typedField.setValue(indexBeanFile.getTyped());
/* 242 */           doc.add(typedField);
/*     */         }
/* 244 */         idField.setValue(indexBeanFile.getId());
/* 245 */         doc.add(idField);
/* 246 */         titleField.setValue(indexBeanFile.getTitle());
/* 247 */         doc.add(titleField);
/* 248 */         contentField.setValue(indexBeanFile.getContent());
/* 249 */         doc.add(contentField);
/* 250 */         urlField.setValue(indexBeanFile.getUrl());
/* 251 */         doc.add(urlField);
/* 252 */         site_idField.setValue(indexBeanFile.getSite_id());
/* 253 */         doc.add(site_idField);
/*     */ 
/* 255 */         node_idField.setValue(indexBeanFile.getNode_id());
/* 256 */         doc.add(node_idField);
/*     */ 
/* 258 */         model_idField.setValue(indexBeanFile.getModel_id());
/* 259 */         doc.add(model_idField);
/*     */ 
/* 261 */         wnumberField.setValue(indexBeanFile.getWnumber());
/* 262 */         doc.add(wnumberField);
/* 263 */         codeField.setValue(indexBeanFile.getCode());
/* 264 */         doc.add(codeField);
/* 265 */         publish_timeField.setValue(indexBeanFile.getPublish_time());
/* 266 */         doc.add(publish_timeField);
/*     */ 
/* 268 */         c_timeField.setValue(indexBeanFile.getC_time());
/* 269 */         doc.add(c_timeField);
/* 270 */         take_timeField.setValue(indexBeanFile.getTake_time());
/* 271 */         doc.add(take_timeField);
/* 272 */         over_timeField.setValue(indexBeanFile.getOver_time());
/* 273 */         doc.add(over_timeField);
/* 274 */         object_wordsField.setValue(indexBeanFile.getObject_words());
/* 275 */         doc.add(object_wordsField);
/* 276 */         form_typeField.setValue(indexBeanFile.getForm_type());
/* 277 */         doc.add(form_typeField);
/* 278 */         content_typeField.setValue(indexBeanFile.getContent_type());
/* 279 */         doc.add(content_typeField);
/* 280 */         publish_orgField.setValue(indexBeanFile.getPublish_org());
/* 281 */         doc.add(publish_orgField);
/*     */ 
/* 283 */         site_id_delField.setValue(indexBeanFile.getSite_id().toLowerCase());
/* 284 */         doc.add(site_id_delField);
/*     */ 
/* 286 */         System.out.println("appendALlDocument id:" + indexBeanFile.getId() + "     CategoryId:" + indexBeanFile.getCategoryId());
/* 287 */         categoryIdField.setValue(indexBeanFile.getCategoryId());
/* 288 */         doc.add(categoryIdField);
/*     */ 
/* 290 */         is_hostField.setValue(indexBeanFile.getIs_host());
/* 291 */         doc.add(is_hostField);
/*     */ 
/* 294 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 299 */       e.printStackTrace();
/* 300 */       return false;
/*     */     }
/*     */     finally {
/* 303 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 304 */       s.close();
/*     */     }
/* 303 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 304 */     s.close();
/*     */ 
/* 306 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 316 */     IndexWriter indexWriter = null;
/*     */     try {
/* 318 */       if (map.get("site_id") != null) {
/* 319 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 321 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
/* 322 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 326 */       e.printStackTrace();
/* 327 */       return false;
/*     */     }
/*     */     finally {
/* 330 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 332 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 342 */     IndexWriter indexWriter = null;
/*     */     try {
/* 344 */       if (infos.get("id") != null)
/*     */       {
/* 346 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 348 */         Term term = new Term("id", (String)infos.get("id"));
/* 349 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 353 */       e.printStackTrace();
/* 354 */       return false;
/*     */     }
/*     */     finally {
/* 357 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 359 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanFile getIndexBeanInfo(Map info)
/*     */   {
/* 364 */     IndexBeanFile indexBeanFile = new IndexBeanFile();
/*     */     try {
/* 366 */       Map infoMap = new HashMap();
/* 367 */       Iterator it = info.keySet().iterator();
/* 368 */       while (it.hasNext()) {
/* 369 */         String key = (String)it.next();
/* 370 */         Object object = info.get(key);
/* 371 */         String value = "";
/* 372 */         if ((object instanceof BigDecimal)) {
/* 373 */           value = object.toString();
/* 374 */         } else if ((object instanceof CLOB)) {
/* 375 */           CLOB clob = (CLOB)object;
/*     */ 
/* 381 */           if (clob != null) {
/* 382 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 387 */           value = String.valueOf(object);
/*     */         }
/* 389 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 391 */       indexBeanFile.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 392 */       indexBeanFile.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 393 */       indexBeanFile.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 394 */       indexBeanFile.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString((String)infoMap.get("gk_content"))));
/* 395 */       indexBeanFile.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 396 */       String site_id = SiteAppRele.getSiteIDByAppID("zwgk");
/* 397 */       indexBeanFile.setSite_id(site_id);
/*     */ 
/* 399 */       indexBeanFile.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 401 */       indexBeanFile.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 403 */       indexBeanFile.setWnumber(FormatUtil.formatNullString((String)infoMap.get("doc_no")));
/* 404 */       indexBeanFile.setCode(FormatUtil.formatNullString((String)infoMap.get("gk_index")));
/* 405 */       String time = (String)infoMap.get("released_dtime");
/* 406 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 407 */       indexBeanFile.setPublish_time(FormatUtil.formatNullString(time));
/* 408 */       time = (String)infoMap.get("generate_dtime");
/* 409 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 410 */       indexBeanFile.setC_time(FormatUtil.formatNullString(time));
/* 411 */       time = (String)infoMap.get("effect_dtime");
/* 412 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 413 */       indexBeanFile.setTake_time(FormatUtil.formatNullString(time));
/* 414 */       time = (String)infoMap.get("aboli_dtime");
/* 415 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 416 */       indexBeanFile.setOver_time(FormatUtil.formatNullString(time));
/* 417 */       indexBeanFile.setObject_words(FormatUtil.formatNullString((String)infoMap.get("topic_key")));
/* 418 */       indexBeanFile.setForm_type(FormatUtil.formatNullString((String)infoMap.get("theme_name")));
/* 419 */       indexBeanFile.setContent_type(FormatUtil.formatNullString((String)infoMap.get("topic_name")));
/* 420 */       indexBeanFile.setPublish_org(FormatUtil.formatNullString((String)infoMap.get("gk_input_dept")));
/* 421 */       indexBeanFile.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/* 422 */       indexBeanFile.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/* 423 */       return indexBeanFile;
/*     */     } catch (Exception e) {
/* 425 */       e.printStackTrace();
/* 426 */     }return indexBeanFile;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexFileServiceImpl
 * JD-Core Version:    0.6.2
 */