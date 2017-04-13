/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanXxgkInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexLawDaoImpl;
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
/*     */ public class IndexLawServiceImpl
/*     */   implements IndexService
/*     */ {
/*  33 */   static int s_count = 50;
/*  34 */   static IIndexInfoDao indexInfoDao = new IndexLawDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  46 */     IndexWriter indexWriter = null;
/*  47 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  49 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  50 */       int page = count / s_count + 1;
/*  51 */       System.out.println("appendALlDocument xxgk count===" + count);
/*     */ 
/*  54 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  55 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  57 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  58 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  59 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  60 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  61 */       Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/*  62 */       Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/*  63 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  64 */       Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  65 */       Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  66 */       Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  67 */       Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/*  68 */       Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  69 */       Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  70 */       Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  72 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  74 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  76 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  78 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  80 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  83 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  86 */       for (int k = 0; k < page; k++) {
/*  87 */         int start_num = s_count * k;
/*  88 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  89 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  91 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getLawListBySiteIdPages"), mapSite);
/*     */ 
/*  93 */         for (Map infoMap : infoList)
/*     */         {
/*  96 */           IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(infoMap);
/*     */ 
/* 101 */           Document doc = new Document();
/* 102 */           if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 103 */             typeField.setValue(indexBeanXxgk.getApp_id());
/* 104 */             doc.add(typeField);
/* 105 */             typedField.setValue(indexBeanXxgk.getApp_id());
/* 106 */             doc.add(typedField);
/*     */           } else {
/* 108 */             typeField.setValue(IndexBeanXxgkInfo.getType());
/* 109 */             doc.add(typeField);
/* 110 */             typedField.setValue(indexBeanXxgk.getTyped());
/* 111 */             doc.add(typedField);
/*     */           }
/* 113 */           idField.setValue(indexBeanXxgk.getId());
/* 114 */           doc.add(idField);
/* 115 */           titleField.setValue(indexBeanXxgk.getTitle());
/* 116 */           doc.add(titleField);
/* 117 */           contentField.setValue(indexBeanXxgk.getContent());
/* 118 */           doc.add(contentField);
/* 119 */           urlField.setValue(indexBeanXxgk.getUrl());
/* 120 */           doc.add(urlField);
/* 121 */           site_idField.setValue(indexBeanXxgk.getSite_id());
/* 122 */           doc.add(site_idField);
/*     */ 
/* 124 */           node_idField.setValue(indexBeanXxgk.getNode_id());
/* 125 */           doc.add(node_idField);
/*     */ 
/* 127 */           model_idField.setValue(indexBeanXxgk.getModel_id());
/* 128 */           doc.add(model_idField);
/*     */ 
/* 130 */           model_idField.setValue(indexBeanXxgk.getModel_id());
/* 131 */           doc.add(model_idField);
/*     */ 
/* 133 */           wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 134 */           doc.add(wnumberField);
/* 135 */           codeField.setValue(indexBeanXxgk.getCode());
/* 136 */           doc.add(codeField);
/* 137 */           publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 138 */           doc.add(publish_timeField);
/*     */ 
/* 140 */           c_timeField.setValue(indexBeanXxgk.getC_time());
/* 141 */           doc.add(c_timeField);
/* 142 */           take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 143 */           doc.add(take_timeField);
/* 144 */           over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 145 */           doc.add(over_timeField);
/* 146 */           object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 147 */           doc.add(object_wordsField);
/* 148 */           form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 149 */           doc.add(form_typeField);
/* 150 */           content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 151 */           doc.add(content_typeField);
/* 152 */           publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 153 */           doc.add(publish_orgField);
/*     */ 
/* 155 */           site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 156 */           doc.add(site_id_delField);
/*     */ 
/* 158 */           categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 159 */           System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 160 */           doc.add(categoryIdField);
/*     */ 
/* 162 */           is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 163 */           doc.add(is_hostField);
/*     */ 
/* 166 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 172 */       e.printStackTrace();
/* 173 */       return false;
/*     */     }
/*     */     finally {
/* 176 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 177 */       s.close();
/*     */     }
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 189 */     IndexWriter indexWriter = null;
/* 190 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 194 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 196 */       if (infos.get("id") != null) {
/* 197 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 199 */         Map mapC = new HashMap();
/* 200 */         mapC.put("id", Integer.valueOf(id));
/* 201 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getLawById"), mapC);
/*     */ 
/* 203 */         if (map == null) {
/* 204 */           return false;
/*     */         }
/*     */ 
/* 207 */         IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(map);
/*     */ 
/* 210 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 211 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 212 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 213 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 214 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 215 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 216 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/* 217 */         Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/* 218 */         Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/* 219 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 220 */         Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 221 */         Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 222 */         Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 223 */         Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/* 224 */         Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 225 */         Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 226 */         Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 228 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 230 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 232 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 234 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 236 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 239 */         Document doc = new Document();
/* 240 */         if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 241 */           typeField.setValue(indexBeanXxgk.getApp_id());
/* 242 */           doc.add(typeField);
/* 243 */           typedField.setValue(indexBeanXxgk.getApp_id());
/* 244 */           doc.add(typedField);
/*     */         } else {
/* 246 */           typeField.setValue(IndexBeanXxgkInfo.getType());
/* 247 */           doc.add(typeField);
/* 248 */           typedField.setValue(indexBeanXxgk.getTyped());
/* 249 */           doc.add(typedField);
/*     */         }
/* 251 */         idField.setValue(indexBeanXxgk.getId());
/* 252 */         doc.add(idField);
/* 253 */         titleField.setValue(indexBeanXxgk.getTitle());
/* 254 */         doc.add(titleField);
/* 255 */         contentField.setValue(indexBeanXxgk.getContent());
/* 256 */         doc.add(contentField);
/* 257 */         urlField.setValue(indexBeanXxgk.getUrl());
/* 258 */         doc.add(urlField);
/* 259 */         site_idField.setValue(indexBeanXxgk.getSite_id());
/* 260 */         doc.add(site_idField);
/*     */ 
/* 262 */         node_idField.setValue(indexBeanXxgk.getNode_id());
/* 263 */         doc.add(node_idField);
/*     */ 
/* 265 */         model_idField.setValue(indexBeanXxgk.getModel_id());
/* 266 */         doc.add(model_idField);
/*     */ 
/* 268 */         wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 269 */         doc.add(wnumberField);
/* 270 */         codeField.setValue(indexBeanXxgk.getCode());
/* 271 */         doc.add(codeField);
/* 272 */         publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 273 */         doc.add(publish_timeField);
/*     */ 
/* 275 */         c_timeField.setValue(indexBeanXxgk.getC_time());
/* 276 */         doc.add(c_timeField);
/* 277 */         take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 278 */         doc.add(take_timeField);
/* 279 */         over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 280 */         doc.add(over_timeField);
/* 281 */         object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 282 */         doc.add(object_wordsField);
/* 283 */         form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 284 */         doc.add(form_typeField);
/* 285 */         content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 286 */         doc.add(content_typeField);
/* 287 */         publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 288 */         doc.add(publish_orgField);
/*     */ 
/* 290 */         site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 291 */         doc.add(site_id_delField);
/*     */ 
/* 293 */         System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 294 */         categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 295 */         doc.add(categoryIdField);
/*     */ 
/* 297 */         is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 298 */         doc.add(is_hostField);
/*     */ 
/* 301 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 306 */       e.printStackTrace();
/* 307 */       return false;
/*     */     }
/*     */     finally {
/* 310 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 311 */       s.close();
/*     */     }
/* 310 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 311 */     s.close();
/*     */ 
/* 313 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 323 */     IndexWriter indexWriter = null;
/*     */     try {
/* 325 */       if (map.get("site_id") != null) {
/* 326 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 328 */         Term term = new Term("site_id", ((String)map.get("site_id")).toLowerCase());
/* 329 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 333 */       e.printStackTrace();
/* 334 */       return false;
/*     */     }
/*     */     finally {
/* 337 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 339 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 349 */     IndexWriter indexWriter = null;
/*     */     try {
/* 351 */       if (infos.get("id") != null)
/*     */       {
/* 353 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 355 */         Term term = new Term("id", (String)infos.get("id"));
/* 356 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 360 */       e.printStackTrace();
/* 361 */       return false;
/*     */     }
/*     */     finally {
/* 364 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 366 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanXxgkInfo getIndexBeanInfo(Map info)
/*     */   {
/* 371 */     IndexBeanXxgkInfo indexBeanXxgk = new IndexBeanXxgkInfo();
/*     */     try {
/* 373 */       Map infoMap = new HashMap();
/* 374 */       Iterator it = info.keySet().iterator();
/* 375 */       while (it.hasNext()) {
/* 376 */         String key = (String)it.next();
/* 377 */         Object object = info.get(key);
/* 378 */         String value = "";
/* 379 */         if ((object instanceof BigDecimal)) {
/* 380 */           value = object.toString();
/* 381 */         } else if ((object instanceof CLOB)) {
/* 382 */           CLOB clob = (CLOB)object;
/*     */ 
/* 388 */           if (clob != null) {
/* 389 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 394 */           value = String.valueOf(object);
/*     */         }
/* 396 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 398 */       indexBeanXxgk.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 399 */       indexBeanXxgk.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 400 */       indexBeanXxgk.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 401 */       StringBuffer sb = new StringBuffer();
/* 402 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_xzxw")));
/* 403 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_xzcl")));
/* 404 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_flyj")));
/* 405 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_nrfj")));
/* 406 */       indexBeanXxgk.setContent(HtmlRegexpUtil.filterHtml(sb.toString()));
/* 407 */       indexBeanXxgk.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 408 */       String site_id = SiteAppRele.getSiteIDByAppID("zwgk");
/* 409 */       indexBeanXxgk.setSite_id(site_id);
/*     */ 
/* 411 */       indexBeanXxgk.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 413 */       indexBeanXxgk.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 415 */       indexBeanXxgk.setWnumber(FormatUtil.formatNullString((String)infoMap.get("doc_no")));
/* 416 */       indexBeanXxgk.setCode(FormatUtil.formatNullString((String)infoMap.get("gk_index")));
/* 417 */       String time = (String)infoMap.get("released_dtime");
/* 418 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 419 */       indexBeanXxgk.setPublish_time(FormatUtil.formatNullString(time));
/* 420 */       time = (String)infoMap.get("generate_dtime");
/* 421 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 422 */       indexBeanXxgk.setC_time(FormatUtil.formatNullString(time));
/* 423 */       time = (String)infoMap.get("effect_dtime");
/* 424 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 425 */       indexBeanXxgk.setTake_time(FormatUtil.formatNullString(time));
/* 426 */       time = (String)infoMap.get("aboli_dtime");
/* 427 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 428 */       indexBeanXxgk.setOver_time(FormatUtil.formatNullString(time));
/* 429 */       indexBeanXxgk.setObject_words(FormatUtil.formatNullString((String)infoMap.get("topic_key")) + " " + FormatUtil.formatNullString((String)infoMap.get("place_key")));
/* 430 */       indexBeanXxgk.setForm_type(FormatUtil.formatNullString((String)infoMap.get("theme_name")));
/* 431 */       indexBeanXxgk.setContent_type(FormatUtil.formatNullString((String)infoMap.get("topic_name")));
/* 432 */       indexBeanXxgk.setPublish_org(FormatUtil.formatNullString((String)infoMap.get("gk_input_dept")));
/* 433 */       indexBeanXxgk.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/* 434 */       indexBeanXxgk.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/* 435 */       return indexBeanXxgk;
/*     */     } catch (Exception e) {
/* 437 */       e.printStackTrace();
/* 438 */     }return indexBeanXxgk;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexLawServiceImpl
 * JD-Core Version:    0.6.2
 */