/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanXxgkInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexOrgDaoImpl;
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
/*     */ public class IndexOrgServiceImpl
/*     */   implements IndexService
/*     */ {
/*  34 */   static int s_count = 50;
/*  35 */   static IIndexInfoDao indexInfoDao = new IndexOrgDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  47 */     IndexWriter indexWriter = null;
/*  48 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  50 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  51 */       int page = count / s_count + 1;
/*  52 */       System.out.println("appendALlDocument xxgk count===" + count);
/*     */ 
/*  55 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  57 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  58 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  59 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  60 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  61 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  62 */       Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/*  63 */       Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/*  64 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  65 */       Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  66 */       Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  67 */       Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  68 */       Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/*  69 */       Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  70 */       Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  71 */       Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  73 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  75 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  77 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  79 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  81 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  84 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  87 */       for (int k = 0; k < page; k++) {
/*  88 */         int start_num = s_count * k;
/*  89 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  90 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  92 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getOrgListBySiteIdPages"), mapSite);
/*     */ 
/*  94 */         for (Map infoMap : infoList)
/*     */         {
/*  97 */           IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(infoMap);
/*     */ 
/* 102 */           Document doc = new Document();
/* 103 */           if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 104 */             typeField.setValue(indexBeanXxgk.getApp_id());
/* 105 */             doc.add(typeField);
/* 106 */             typedField.setValue(indexBeanXxgk.getApp_id());
/* 107 */             doc.add(typedField);
/*     */           } else {
/* 109 */             typeField.setValue(IndexBeanXxgkInfo.getType());
/* 110 */             doc.add(typeField);
/* 111 */             typedField.setValue(indexBeanXxgk.getTyped());
/* 112 */             doc.add(typedField);
/*     */           }
/* 114 */           idField.setValue(indexBeanXxgk.getId());
/* 115 */           doc.add(idField);
/* 116 */           titleField.setValue(indexBeanXxgk.getTitle());
/* 117 */           doc.add(titleField);
/* 118 */           contentField.setValue(indexBeanXxgk.getContent());
/* 119 */           doc.add(contentField);
/* 120 */           urlField.setValue(indexBeanXxgk.getUrl());
/* 121 */           doc.add(urlField);
/* 122 */           site_idField.setValue(indexBeanXxgk.getSite_id());
/* 123 */           doc.add(site_idField);
/*     */ 
/* 125 */           node_idField.setValue(indexBeanXxgk.getNode_id());
/* 126 */           doc.add(node_idField);
/*     */ 
/* 128 */           model_idField.setValue(indexBeanXxgk.getModel_id());
/* 129 */           doc.add(model_idField);
/*     */ 
/* 131 */           wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 132 */           doc.add(wnumberField);
/* 133 */           codeField.setValue(indexBeanXxgk.getCode());
/* 134 */           doc.add(codeField);
/* 135 */           publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 136 */           doc.add(publish_timeField);
/*     */ 
/* 138 */           c_timeField.setValue(indexBeanXxgk.getC_time());
/* 139 */           doc.add(c_timeField);
/* 140 */           take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 141 */           doc.add(take_timeField);
/* 142 */           over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 143 */           doc.add(over_timeField);
/* 144 */           object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 145 */           doc.add(object_wordsField);
/* 146 */           form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 147 */           doc.add(form_typeField);
/* 148 */           content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 149 */           doc.add(content_typeField);
/* 150 */           publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 151 */           doc.add(publish_orgField);
/*     */ 
/* 153 */           site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 154 */           doc.add(site_id_delField);
/*     */ 
/* 156 */           categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 157 */           System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 158 */           doc.add(categoryIdField);
/*     */ 
/* 160 */           is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 161 */           doc.add(is_hostField);
/*     */ 
/* 164 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 170 */       e.printStackTrace();
/* 171 */       return false;
/*     */     }
/*     */     finally {
/* 174 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 175 */       s.close();
/*     */     }
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 187 */     IndexWriter indexWriter = null;
/* 188 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 192 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 194 */       if (infos.get("id") != null) {
/* 195 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 197 */         Map mapC = new HashMap();
/* 198 */         mapC.put("id", Integer.valueOf(id));
/* 199 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getOrgById"), mapC);
/*     */ 
/* 201 */         if (map == null) {
/* 202 */           return false;
/*     */         }
/*     */ 
/* 205 */         IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(map);
/*     */ 
/* 208 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 209 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 210 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 211 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 212 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 213 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 214 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/* 215 */         Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/* 216 */         Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/* 217 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 218 */         Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 219 */         Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 220 */         Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 221 */         Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/* 222 */         Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 223 */         Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 224 */         Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 226 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 228 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 230 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 232 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 234 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 237 */         Document doc = new Document();
/* 238 */         if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 239 */           typeField.setValue(indexBeanXxgk.getApp_id());
/* 240 */           doc.add(typeField);
/* 241 */           typedField.setValue(indexBeanXxgk.getApp_id());
/* 242 */           doc.add(typedField);
/*     */         } else {
/* 244 */           typeField.setValue(IndexBeanXxgkInfo.getType());
/* 245 */           doc.add(typeField);
/* 246 */           typedField.setValue(indexBeanXxgk.getTyped());
/* 247 */           doc.add(typedField);
/*     */         }
/* 249 */         idField.setValue(indexBeanXxgk.getId());
/* 250 */         doc.add(idField);
/* 251 */         titleField.setValue(indexBeanXxgk.getTitle());
/* 252 */         doc.add(titleField);
/* 253 */         contentField.setValue(indexBeanXxgk.getContent());
/* 254 */         doc.add(contentField);
/* 255 */         urlField.setValue(indexBeanXxgk.getUrl());
/* 256 */         doc.add(urlField);
/* 257 */         site_idField.setValue(indexBeanXxgk.getSite_id());
/* 258 */         doc.add(site_idField);
/*     */ 
/* 260 */         node_idField.setValue(indexBeanXxgk.getNode_id());
/* 261 */         doc.add(node_idField);
/*     */ 
/* 263 */         model_idField.setValue(indexBeanXxgk.getModel_id());
/* 264 */         doc.add(model_idField);
/*     */ 
/* 266 */         wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 267 */         doc.add(wnumberField);
/* 268 */         codeField.setValue(indexBeanXxgk.getCode());
/* 269 */         doc.add(codeField);
/* 270 */         publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 271 */         doc.add(publish_timeField);
/*     */ 
/* 273 */         c_timeField.setValue(indexBeanXxgk.getC_time());
/* 274 */         doc.add(c_timeField);
/* 275 */         take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 276 */         doc.add(take_timeField);
/* 277 */         over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 278 */         doc.add(over_timeField);
/* 279 */         object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 280 */         doc.add(object_wordsField);
/* 281 */         form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 282 */         doc.add(form_typeField);
/* 283 */         content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 284 */         doc.add(content_typeField);
/* 285 */         publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 286 */         doc.add(publish_orgField);
/*     */ 
/* 288 */         site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 289 */         doc.add(site_id_delField);
/*     */ 
/* 291 */         System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 292 */         categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 293 */         doc.add(categoryIdField);
/*     */ 
/* 295 */         is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 296 */         doc.add(is_hostField);
/*     */ 
/* 299 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 304 */       e.printStackTrace();
/* 305 */       return false;
/*     */     }
/*     */     finally {
/* 308 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 309 */       s.close();
/*     */     }
/* 308 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 309 */     s.close();
/*     */ 
/* 311 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 321 */     IndexWriter indexWriter = null;
/*     */     try {
/* 323 */       if (map.get("site_id") != null) {
/* 324 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 326 */         Term term = new Term("site_id", ((String)map.get("site_id")).toLowerCase());
/* 327 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 331 */       e.printStackTrace();
/* 332 */       return false;
/*     */     }
/*     */     finally {
/* 335 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 337 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 347 */     IndexWriter indexWriter = null;
/*     */     try {
/* 349 */       if (infos.get("id") != null)
/*     */       {
/* 351 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 353 */         Term term = new Term("id", (String)infos.get("id"));
/* 354 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 358 */       e.printStackTrace();
/* 359 */       return false;
/*     */     }
/*     */     finally {
/* 362 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanXxgkInfo getIndexBeanInfo(Map info)
/*     */   {
/* 369 */     IndexBeanXxgkInfo indexBeanXxgk = new IndexBeanXxgkInfo();
/*     */     try {
/* 371 */       Map infoMap = new HashMap();
/* 372 */       Iterator it = info.keySet().iterator();
/* 373 */       while (it.hasNext()) {
/* 374 */         String key = (String)it.next();
/* 375 */         Object object = info.get(key);
/* 376 */         String value = "";
/* 377 */         if ((object instanceof BigDecimal)) {
/* 378 */           value = object.toString();
/* 379 */         } else if ((object instanceof CLOB)) {
/* 380 */           CLOB clob = (CLOB)object;
/*     */ 
/* 386 */           if (clob != null) {
/* 387 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 392 */           value = String.valueOf(object);
/*     */         }
/* 394 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 396 */       indexBeanXxgk.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 397 */       indexBeanXxgk.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 398 */       indexBeanXxgk.setApp_id(FormatUtil.formatNullString((String)infoMap.get("add_id")));
/* 399 */       StringBuffer sb = new StringBuffer();
/* 400 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_gzzz")));
/* 401 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_fzr")));
/* 402 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_bgdz")));
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
/*     */ 
/* 431 */       indexBeanXxgk.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/* 432 */       return indexBeanXxgk;
/*     */     } catch (Exception e) {
/* 434 */       e.printStackTrace();
/* 435 */     }return indexBeanXxgk;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexOrgServiceImpl
 * JD-Core Version:    0.6.2
 */