/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanXxgkInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexOrgDDaoImpl;
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
/*     */ public class IndexOrgDServiceImpl
/*     */   implements IndexService
/*     */ {
/*  33 */   static int s_count = 50;
/*  34 */   static IIndexInfoDao indexInfoDao = new IndexOrgDDaoImpl();
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
/*  91 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getOrgDListBySiteIdPages"), mapSite);
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
/* 108 */             typeField.setValue(indexBeanXxgk.getApp_id());
/* 109 */             doc.add(typeField);
/* 110 */             typedField.setValue(indexBeanXxgk.getApp_id());
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
/* 130 */           wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 131 */           doc.add(wnumberField);
/* 132 */           codeField.setValue(indexBeanXxgk.getCode());
/* 133 */           doc.add(codeField);
/* 134 */           publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 135 */           doc.add(publish_timeField);
/*     */ 
/* 137 */           c_timeField.setValue(indexBeanXxgk.getC_time());
/* 138 */           doc.add(c_timeField);
/* 139 */           take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 140 */           doc.add(take_timeField);
/* 141 */           over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 142 */           doc.add(over_timeField);
/* 143 */           object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 144 */           doc.add(object_wordsField);
/* 145 */           form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 146 */           doc.add(form_typeField);
/* 147 */           content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 148 */           doc.add(content_typeField);
/* 149 */           publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 150 */           doc.add(publish_orgField);
/*     */ 
/* 152 */           site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 153 */           doc.add(site_id_delField);
/*     */ 
/* 155 */           categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 156 */           System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 157 */           doc.add(categoryIdField);
/*     */ 
/* 159 */           is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 160 */           doc.add(is_hostField);
/*     */ 
/* 163 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 169 */       e.printStackTrace();
/* 170 */       return false;
/*     */     }
/*     */     finally {
/* 173 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 174 */       s.close();
/*     */     }
/* 176 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 186 */     IndexWriter indexWriter = null;
/* 187 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 191 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 193 */       if (infos.get("id") != null) {
/* 194 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 196 */         Map mapC = new HashMap();
/* 197 */         mapC.put("id", Integer.valueOf(id));
/* 198 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getOrgDById"), mapC);
/*     */ 
/* 200 */         if (map == null) {
/* 201 */           return false;
/*     */         }
/*     */ 
/* 204 */         IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(map);
/*     */ 
/* 207 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 208 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 209 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 210 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 211 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 212 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 213 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/* 214 */         Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/* 215 */         Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/* 216 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 217 */         Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 218 */         Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 219 */         Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 220 */         Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/* 221 */         Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 222 */         Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 223 */         Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 225 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 227 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 229 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 231 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 233 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 236 */         Document doc = new Document();
/* 237 */         if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 238 */           typeField.setValue(indexBeanXxgk.getApp_id());
/* 239 */           doc.add(typeField);
/* 240 */           typedField.setValue(indexBeanXxgk.getApp_id());
/* 241 */           doc.add(typedField);
/*     */         } else {
/* 243 */           typeField.setValue(indexBeanXxgk.getApp_id());
/* 244 */           doc.add(typeField);
/* 245 */           typedField.setValue(indexBeanXxgk.getApp_id());
/* 246 */           doc.add(typedField);
/*     */         }
/* 248 */         idField.setValue(indexBeanXxgk.getId());
/* 249 */         doc.add(idField);
/* 250 */         titleField.setValue(indexBeanXxgk.getTitle());
/* 251 */         doc.add(titleField);
/* 252 */         contentField.setValue(indexBeanXxgk.getContent());
/* 253 */         doc.add(contentField);
/* 254 */         urlField.setValue(indexBeanXxgk.getUrl());
/* 255 */         doc.add(urlField);
/* 256 */         site_idField.setValue(indexBeanXxgk.getSite_id());
/* 257 */         doc.add(site_idField);
/*     */ 
/* 259 */         node_idField.setValue(indexBeanXxgk.getNode_id());
/* 260 */         doc.add(node_idField);
/*     */ 
/* 262 */         model_idField.setValue(indexBeanXxgk.getModel_id());
/* 263 */         doc.add(model_idField);
/*     */ 
/* 265 */         wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 266 */         doc.add(wnumberField);
/* 267 */         codeField.setValue(indexBeanXxgk.getCode());
/* 268 */         doc.add(codeField);
/* 269 */         publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 270 */         doc.add(publish_timeField);
/*     */ 
/* 272 */         c_timeField.setValue(indexBeanXxgk.getC_time());
/* 273 */         doc.add(c_timeField);
/* 274 */         take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 275 */         doc.add(take_timeField);
/* 276 */         over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 277 */         doc.add(over_timeField);
/* 278 */         object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 279 */         doc.add(object_wordsField);
/* 280 */         form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 281 */         doc.add(form_typeField);
/* 282 */         content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 283 */         doc.add(content_typeField);
/* 284 */         publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 285 */         doc.add(publish_orgField);
/*     */ 
/* 287 */         site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 288 */         doc.add(site_id_delField);
/*     */ 
/* 290 */         System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 291 */         categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 292 */         doc.add(categoryIdField);
/*     */ 
/* 294 */         is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 295 */         doc.add(is_hostField);
/*     */ 
/* 298 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 303 */       e.printStackTrace();
/* 304 */       return false;
/*     */     }
/*     */     finally {
/* 307 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 308 */       s.close();
/*     */     }
/* 307 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 308 */     s.close();
/*     */ 
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 320 */     IndexWriter indexWriter = null;
/*     */     try {
/* 322 */       if (map.get("site_id") != null) {
/* 323 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 325 */         Term term = new Term("site_id", ((String)map.get("site_id")).toLowerCase());
/* 326 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 330 */       e.printStackTrace();
/* 331 */       return false;
/*     */     }
/*     */     finally {
/* 334 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 346 */     IndexWriter indexWriter = null;
/*     */     try {
/* 348 */       if (infos.get("id") != null)
/*     */       {
/* 350 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 352 */         Term term = new Term("id", (String)infos.get("id"));
/* 353 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 357 */       e.printStackTrace();
/* 358 */       return false;
/*     */     }
/*     */     finally {
/* 361 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 363 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanXxgkInfo getIndexBeanInfo(Map info)
/*     */   {
/* 368 */     IndexBeanXxgkInfo indexBeanXxgk = new IndexBeanXxgkInfo();
/*     */     try {
/* 370 */       Map infoMap = new HashMap();
/* 371 */       Iterator it = info.keySet().iterator();
/* 372 */       while (it.hasNext()) {
/* 373 */         String key = (String)it.next();
/* 374 */         Object object = info.get(key);
/* 375 */         String value = "";
/* 376 */         if ((object instanceof BigDecimal)) {
/* 377 */           value = object.toString();
/* 378 */         } else if ((object instanceof CLOB)) {
/* 379 */           CLOB clob = (CLOB)object;
/*     */ 
/* 385 */           if (clob != null) {
/* 386 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 391 */           value = String.valueOf(object);
/*     */         }
/* 393 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 395 */       indexBeanXxgk.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 396 */       indexBeanXxgk.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 397 */       indexBeanXxgk.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 398 */       StringBuffer sb = new StringBuffer();
/* 399 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_nsjg")));
/* 400 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_gzzz")));
/* 401 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_xmzw")));
/* 402 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_dz")));
/* 403 */       indexBeanXxgk.setContent(HtmlRegexpUtil.filterHtml(sb.toString()));
/* 404 */       indexBeanXxgk.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 405 */       String site_id = SiteAppRele.getSiteIDByAppID("zwgk");
/* 406 */       indexBeanXxgk.setSite_id(site_id);
/*     */ 
/* 408 */       indexBeanXxgk.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/* 409 */       indexBeanXxgk.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 411 */       indexBeanXxgk.setWnumber(FormatUtil.formatNullString((String)infoMap.get("doc_no")));
/* 412 */       indexBeanXxgk.setCode(FormatUtil.formatNullString((String)infoMap.get("gk_index")));
/* 413 */       String time = (String)infoMap.get("released_dtime");
/* 414 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 415 */       indexBeanXxgk.setPublish_time(FormatUtil.formatNullString(time));
/* 416 */       time = (String)infoMap.get("generate_dtime");
/* 417 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 418 */       indexBeanXxgk.setC_time(FormatUtil.formatNullString(time));
/* 419 */       time = (String)infoMap.get("effect_dtime");
/* 420 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 421 */       indexBeanXxgk.setTake_time(FormatUtil.formatNullString(time));
/* 422 */       time = (String)infoMap.get("aboli_dtime");
/* 423 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 424 */       indexBeanXxgk.setOver_time(FormatUtil.formatNullString(time));
/* 425 */       indexBeanXxgk.setObject_words(FormatUtil.formatNullString((String)infoMap.get("topic_key")) + " " + FormatUtil.formatNullString((String)infoMap.get("place_key")));
/* 426 */       indexBeanXxgk.setForm_type(FormatUtil.formatNullString((String)infoMap.get("theme_name")));
/* 427 */       indexBeanXxgk.setContent_type(FormatUtil.formatNullString((String)infoMap.get("topic_name")));
/* 428 */       indexBeanXxgk.setPublish_org(FormatUtil.formatNullString((String)infoMap.get("gk_input_dept")));
/* 429 */       indexBeanXxgk.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/* 430 */       indexBeanXxgk.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/* 431 */       return indexBeanXxgk;
/*     */     } catch (Exception e) {
/* 433 */       e.printStackTrace();
/* 434 */     }return indexBeanXxgk;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexOrgDServiceImpl
 * JD-Core Version:    0.6.2
 */