/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanXxgkInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexXxgkDaoImpl;
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
/*     */ public class IndexXxgkServiceImpl
/*     */   implements IndexService
/*     */ {
/*  32 */   static int s_count = 50;
/*  33 */   static IIndexInfoDao indexInfoDao = new IndexXxgkDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  45 */     IndexWriter indexWriter = null;
/*  46 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  48 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  49 */       int page = count / s_count + 1;
/*  50 */       System.out.println("appendALlDocument xxgk count===" + count);
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
/*  63 */       Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  64 */       Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
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
/*  82 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  85 */       for (int k = 0; k < page; k++) {
/*  86 */         int start_num = s_count * k;
/*  87 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  88 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  90 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getXxgkListBySiteIdPages"), mapSite);
/*     */ 
/*  92 */         for (Map infoMap : infoList)
/*     */         {
/*  95 */           IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(infoMap);
/*     */ 
/* 100 */           Document doc = new Document();
/* 101 */           if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 102 */             typeField.setValue(indexBeanXxgk.getApp_id());
/* 103 */             doc.add(typeField);
/* 104 */             typedField.setValue(indexBeanXxgk.getApp_id());
/* 105 */             doc.add(typedField);
/*     */           } else {
/* 107 */             typeField.setValue(IndexBeanXxgkInfo.getType());
/* 108 */             doc.add(typeField);
/* 109 */             typedField.setValue(indexBeanXxgk.getTyped());
/* 110 */             doc.add(typedField);
/*     */           }
/* 112 */           idField.setValue(indexBeanXxgk.getId());
/* 113 */           doc.add(idField);
/* 114 */           titleField.setValue(indexBeanXxgk.getTitle());
/* 115 */           doc.add(titleField);
/* 116 */           contentField.setValue(indexBeanXxgk.getContent());
/* 117 */           doc.add(contentField);
/* 118 */           urlField.setValue(indexBeanXxgk.getUrl());
/* 119 */           doc.add(urlField);
/* 120 */           site_idField.setValue(indexBeanXxgk.getSite_id());
/* 121 */           doc.add(site_idField);
/*     */ 
/* 123 */           node_idField.setValue(indexBeanXxgk.getNode_id());
/* 124 */           doc.add(node_idField);
/*     */ 
/* 126 */           model_idField.setValue(indexBeanXxgk.getModel_id());
/* 127 */           doc.add(model_idField);
/*     */ 
/* 129 */           wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 130 */           doc.add(wnumberField);
/* 131 */           codeField.setValue(indexBeanXxgk.getCode());
/* 132 */           doc.add(codeField);
/* 133 */           publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 134 */           doc.add(publish_timeField);
/* 135 */           c_timeField.setValue(indexBeanXxgk.getC_time());
/* 136 */           doc.add(c_timeField);
/*     */ 
/* 138 */           take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 139 */           doc.add(take_timeField);
/* 140 */           over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 141 */           doc.add(over_timeField);
/* 142 */           object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 143 */           doc.add(object_wordsField);
/* 144 */           form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 145 */           doc.add(form_typeField);
/* 146 */           content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 147 */           doc.add(content_typeField);
/* 148 */           publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 149 */           doc.add(publish_orgField);
/*     */ 
/* 151 */           site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 152 */           doc.add(site_id_delField);
/*     */ 
/* 154 */           categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 155 */           System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 156 */           doc.add(categoryIdField);
/*     */ 
/* 158 */           is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 159 */           doc.add(is_hostField);
/*     */ 
/* 162 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 168 */       e.printStackTrace();
/* 169 */       return false;
/*     */     }
/*     */     finally {
/* 172 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 173 */       s.close();
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 185 */     IndexWriter indexWriter = null;
/* 186 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 190 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 192 */       if (infos.get("id") != null) {
/* 193 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 195 */         Map mapC = new HashMap();
/* 196 */         mapC.put("id", Integer.valueOf(id));
/* 197 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getXxgkById"), mapC);
/*     */ 
/* 199 */         if (map == null) {
/* 200 */           return false;
/*     */         }
/*     */ 
/* 203 */         IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(map);
/*     */ 
/* 206 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 207 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 208 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 209 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 210 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 211 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 212 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/* 213 */         Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/* 214 */         Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/* 215 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 216 */         Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 217 */         Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 218 */         Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 219 */         Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/* 220 */         Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 221 */         Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 222 */         Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 224 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 226 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 228 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 230 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 232 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 235 */         Document doc = new Document();
/* 236 */         if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 237 */           typeField.setValue(indexBeanXxgk.getApp_id());
/* 238 */           doc.add(typeField);
/* 239 */           typedField.setValue(indexBeanXxgk.getApp_id());
/* 240 */           doc.add(typedField);
/*     */         } else {
/* 242 */           typeField.setValue(IndexBeanXxgkInfo.getType());
/* 243 */           doc.add(typeField);
/* 244 */           typedField.setValue(indexBeanXxgk.getTyped());
/* 245 */           doc.add(typedField);
/*     */         }
/* 247 */         idField.setValue(indexBeanXxgk.getId());
/* 248 */         doc.add(idField);
/* 249 */         titleField.setValue(indexBeanXxgk.getTitle());
/* 250 */         doc.add(titleField);
/* 251 */         contentField.setValue(indexBeanXxgk.getContent());
/* 252 */         doc.add(contentField);
/* 253 */         urlField.setValue(indexBeanXxgk.getUrl());
/* 254 */         doc.add(urlField);
/* 255 */         site_idField.setValue(indexBeanXxgk.getSite_id());
/* 256 */         doc.add(site_idField);
/*     */ 
/* 258 */         node_idField.setValue(indexBeanXxgk.getNode_id());
/* 259 */         doc.add(node_idField);
/*     */ 
/* 261 */         model_idField.setValue(indexBeanXxgk.getModel_id());
/* 262 */         doc.add(model_idField);
/*     */ 
/* 264 */         wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 265 */         doc.add(wnumberField);
/* 266 */         codeField.setValue(indexBeanXxgk.getCode());
/* 267 */         doc.add(codeField);
/* 268 */         publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 269 */         doc.add(publish_timeField);
/* 270 */         c_timeField.setValue(indexBeanXxgk.getC_time());
/* 271 */         doc.add(c_timeField);
/*     */ 
/* 273 */         take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 274 */         doc.add(take_timeField);
/* 275 */         over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 276 */         doc.add(over_timeField);
/* 277 */         object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 278 */         doc.add(object_wordsField);
/* 279 */         form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 280 */         doc.add(form_typeField);
/* 281 */         content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 282 */         doc.add(content_typeField);
/* 283 */         publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 284 */         doc.add(publish_orgField);
/*     */ 
/* 286 */         site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 287 */         doc.add(site_id_delField);
/*     */ 
/* 289 */         System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 290 */         categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 291 */         doc.add(categoryIdField);
/*     */ 
/* 293 */         is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 294 */         doc.add(is_hostField);
/*     */ 
/* 297 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 302 */       e.printStackTrace();
/* 303 */       return false;
/*     */     }
/*     */     finally {
/* 306 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 307 */       s.close();
/*     */     }
/* 306 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 307 */     s.close();
/*     */ 
/* 309 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 319 */     IndexWriter indexWriter = null;
/*     */     try {
/* 321 */       if (map.get("site_id") != null) {
/* 322 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 324 */         Term term = new Term("site_id", ((String)map.get("site_id")).toLowerCase());
/* 325 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 329 */       e.printStackTrace();
/* 330 */       return false;
/*     */     }
/*     */     finally {
/* 333 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 335 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 345 */     IndexWriter indexWriter = null;
/*     */     try {
/* 347 */       if (infos.get("id") != null)
/*     */       {
/* 349 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 351 */         Term term = new Term("id", (String)infos.get("id"));
/* 352 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 356 */       e.printStackTrace();
/* 357 */       return false;
/*     */     }
/*     */     finally {
/* 360 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 362 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanXxgkInfo getIndexBeanInfo(Map info)
/*     */   {
/* 367 */     IndexBeanXxgkInfo indexBeanXxgk = new IndexBeanXxgkInfo();
/*     */     try {
/* 369 */       Map infoMap = new HashMap();
/* 370 */       Iterator it = info.keySet().iterator();
/* 371 */       while (it.hasNext()) {
/* 372 */         String key = (String)it.next();
/* 373 */         Object object = info.get(key);
/* 374 */         String value = "";
/* 375 */         if ((object instanceof BigDecimal)) {
/* 376 */           value = object.toString();
/* 377 */         } else if ((object instanceof CLOB)) {
/* 378 */           CLOB clob = (CLOB)object;
/*     */ 
/* 384 */           if (clob != null) {
/* 385 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 390 */           value = String.valueOf(object);
/*     */         }
/* 392 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 394 */       indexBeanXxgk.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 395 */       indexBeanXxgk.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 396 */       indexBeanXxgk.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 397 */       indexBeanXxgk.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString((String)infoMap.get("gk_content"))));
/* 398 */       indexBeanXxgk.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 399 */       String site_id = SiteAppRele.getSiteIDByAppID("zwgk");
/* 400 */       indexBeanXxgk.setSite_id(site_id);
/*     */ 
/* 402 */       indexBeanXxgk.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/* 403 */       indexBeanXxgk.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 405 */       indexBeanXxgk.setWnumber(FormatUtil.formatNullString((String)infoMap.get("doc_no")));
/* 406 */       indexBeanXxgk.setCode(FormatUtil.formatNullString((String)infoMap.get("gk_index")));
/* 407 */       String time = (String)infoMap.get("released_dtime");
/* 408 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 409 */       indexBeanXxgk.setPublish_time(FormatUtil.formatNullString(time));
/* 410 */       time = (String)infoMap.get("generate_dtime");
/* 411 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 412 */       indexBeanXxgk.setC_time(FormatUtil.formatNullString(time));
/* 413 */       time = (String)infoMap.get("effect_dtime");
/* 414 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 415 */       indexBeanXxgk.setTake_time(FormatUtil.formatNullString(time));
/* 416 */       time = (String)infoMap.get("aboli_dtime");
/* 417 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 418 */       indexBeanXxgk.setOver_time(FormatUtil.formatNullString(time));
/* 419 */       indexBeanXxgk.setObject_words(FormatUtil.formatNullString((String)infoMap.get("topic_key")) + " " + FormatUtil.formatNullString((String)infoMap.get("place_key")));
/* 420 */       indexBeanXxgk.setForm_type(FormatUtil.formatNullString((String)infoMap.get("theme_name")));
/* 421 */       indexBeanXxgk.setContent_type(FormatUtil.formatNullString((String)infoMap.get("topic_name")));
/* 422 */       indexBeanXxgk.setPublish_org(FormatUtil.formatNullString((String)infoMap.get("gk_input_dept")));
/* 423 */       indexBeanXxgk.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/*     */ 
/* 425 */       indexBeanXxgk.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/*     */ 
/* 427 */       return indexBeanXxgk;
/*     */     } catch (Exception e) {
/* 429 */       e.printStackTrace();
/* 430 */     }return indexBeanXxgk;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexXxgkServiceImpl
 * JD-Core Version:    0.6.2
 */