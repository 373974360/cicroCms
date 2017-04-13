/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.search.IndexBeanXxgkInfo;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexCustomGkDaoImpl;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.db.IbatisSessionFactory;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseRPC;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*     */ import com.cicro.wcm.services.model.Fields;
/*     */ import com.cicro.wcm.services.model.services.FormRPC;
/*     */ import com.cicro.wcm.services.model.services.MapManager;
/*     */ import com.cicro.wcm.services.search.index.IndexLuceneManager;
/*     */ import com.cicro.wcm.services.search.index.IndexService;
/*     */ import com.cicro.wcm.services.system.formodel.ModelRPC;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
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
/*     */ public class IndexCustomGkServiceImpl
/*     */   implements IndexService
/*     */ {
/*  41 */   static int s_count = 50;
/*  42 */   static IIndexInfoDao indexInfoDao = new IndexCustomGkDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  54 */     IndexWriter indexWriter = null;
/*  55 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/*  59 */       List modelBeanList = ModelRPC.getCANModelList();
/*  60 */       for (ModelBean modelBean : modelBeanList) {
/*  61 */         String type = modelBean.getModel_type();
/*  62 */         String tableName = modelBean.getTable_name();
/*  63 */         System.out.println("tableName = " + tableName);
/*  64 */         if (!type.toString().equals("0"))
/*     */         {
/*  68 */           mapSite.put("table_name", tableName);
/*  69 */           System.out.println("mapSite = " + mapSite);
/*     */ 
/*  71 */           int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  72 */           int page = count / s_count + 1;
/*  73 */           System.out.println("appendALlDocument info count===" + count);
/*     */ 
/*  75 */           if (count != 0)
/*     */           {
/*  80 */             Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  81 */             Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  82 */             Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  83 */             Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  84 */             Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  85 */             Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  86 */             Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  87 */             Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/*  88 */             Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/*  89 */             Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  90 */             Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  91 */             Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  92 */             Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  93 */             Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/*  94 */             Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  95 */             Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/*  96 */             Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  98 */             Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 100 */             Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 102 */             indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 104 */             Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 106 */             Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 109 */             for (int k = 0; k < page; k++) {
/* 110 */               int start_num = s_count * k;
/* 111 */               mapSite.put("start_num", Integer.valueOf(start_num));
/* 112 */               mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/* 115 */               List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getCostomGkListBySiteIdPages"), mapSite);
/*     */ 
/* 117 */               for (Map infoMap : infoList)
/*     */               {
/* 120 */                 IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(infoMap);
/*     */ 
/* 125 */                 Document doc = new Document();
/* 126 */                 if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 127 */                   typeField.setValue(indexBeanXxgk.getApp_id());
/* 128 */                   doc.add(typeField);
/* 129 */                   typedField.setValue(indexBeanXxgk.getApp_id());
/* 130 */                   doc.add(typedField);
/*     */                 } else {
/* 132 */                   typeField.setValue(IndexBeanXxgkInfo.getType());
/* 133 */                   doc.add(typeField);
/* 134 */                   typedField.setValue(indexBeanXxgk.getTyped());
/* 135 */                   doc.add(typedField);
/*     */                 }
/* 137 */                 idField.setValue(indexBeanXxgk.getId());
/* 138 */                 doc.add(idField);
/* 139 */                 titleField.setValue(indexBeanXxgk.getTitle());
/* 140 */                 doc.add(titleField);
/* 141 */                 contentField.setValue(indexBeanXxgk.getContent());
/* 142 */                 doc.add(contentField);
/* 143 */                 urlField.setValue(indexBeanXxgk.getUrl());
/* 144 */                 doc.add(urlField);
/* 145 */                 site_idField.setValue(indexBeanXxgk.getSite_id());
/* 146 */                 doc.add(site_idField);
/*     */ 
/* 148 */                 node_idField.setValue(indexBeanXxgk.getNode_id());
/* 149 */                 doc.add(node_idField);
/*     */ 
/* 151 */                 wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 152 */                 doc.add(wnumberField);
/* 153 */                 codeField.setValue(indexBeanXxgk.getCode());
/* 154 */                 doc.add(codeField);
/* 155 */                 publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 156 */                 doc.add(publish_timeField);
/* 157 */                 c_timeField.setValue(indexBeanXxgk.getC_time());
/* 158 */                 doc.add(c_timeField);
/*     */ 
/* 160 */                 take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 161 */                 doc.add(take_timeField);
/* 162 */                 over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 163 */                 doc.add(over_timeField);
/* 164 */                 object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 165 */                 doc.add(object_wordsField);
/* 166 */                 form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 167 */                 doc.add(form_typeField);
/* 168 */                 content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 169 */                 doc.add(content_typeField);
/* 170 */                 publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 171 */                 doc.add(publish_orgField);
/*     */ 
/* 173 */                 site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 174 */                 doc.add(site_id_delField);
/*     */ 
/* 176 */                 categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 177 */                 System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 178 */                 doc.add(categoryIdField);
/*     */ 
/* 180 */                 is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 181 */                 doc.add(is_hostField);
/*     */ 
/* 184 */                 IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 191 */       e.printStackTrace();
/* 192 */       return false;
/*     */     }
/*     */     finally {
/* 195 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 196 */       s.close();
/*     */     }
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 208 */     IndexWriter indexWriter = null;
/* 209 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 213 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 215 */       if (infos.get("id") != null) {
/* 216 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 219 */         InfoBean infoBean = InfoBaseRPC.getInfoById(String.valueOf(id));
/* 220 */         ModelBean modelBean = ModelRPC.getModelBean(infoBean.getModel_id());
/* 221 */         String table_name = modelBean.getTable_name();
/*     */ 
/* 223 */         Map maptemp = new HashMap();
/* 224 */         maptemp.put("id", Integer.valueOf(id));
/* 225 */         maptemp.put("table_name", table_name);
/*     */ 
/* 228 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getCustomGkById"), maptemp);
/*     */ 
/* 230 */         if (map == null) {
/* 231 */           return false;
/*     */         }
/*     */ 
/* 234 */         IndexBeanXxgkInfo indexBeanXxgk = getIndexBeanInfo(map);
/*     */ 
/* 237 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 238 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 239 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 240 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 241 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 242 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 243 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 245 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 247 */         Field wnumberField = new Field("wnumber", "", Field.Store.YES, Field.Index.ANALYZED);
/* 248 */         Field codeField = new Field("code", "", Field.Store.YES, Field.Index.ANALYZED);
/* 249 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 250 */         Field c_timeField = new Field("c_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 251 */         Field take_timeField = new Field("take_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 252 */         Field over_timeField = new Field("over_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 253 */         Field object_wordsField = new Field("object_words", "", Field.Store.YES, Field.Index.ANALYZED);
/* 254 */         Field form_typeField = new Field("form_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 255 */         Field content_typeField = new Field("content_type", "", Field.Store.YES, Field.Index.ANALYZED);
/* 256 */         Field publish_orgField = new Field("publish_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 258 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 260 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 262 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 265 */         Document doc = new Document();
/* 266 */         if (indexBeanXxgk.getApp_id().equals("ggfw")) {
/* 267 */           typeField.setValue(indexBeanXxgk.getApp_id());
/* 268 */           doc.add(typeField);
/* 269 */           typedField.setValue(indexBeanXxgk.getApp_id());
/* 270 */           doc.add(typedField);
/*     */         } else {
/* 272 */           typeField.setValue(IndexBeanXxgkInfo.getType());
/* 273 */           doc.add(typeField);
/* 274 */           typedField.setValue(indexBeanXxgk.getTyped());
/* 275 */           doc.add(typedField);
/*     */         }
/* 277 */         idField.setValue(indexBeanXxgk.getId());
/* 278 */         doc.add(idField);
/* 279 */         titleField.setValue(indexBeanXxgk.getTitle());
/* 280 */         doc.add(titleField);
/* 281 */         contentField.setValue(indexBeanXxgk.getContent());
/* 282 */         doc.add(contentField);
/* 283 */         urlField.setValue(indexBeanXxgk.getUrl());
/* 284 */         doc.add(urlField);
/* 285 */         site_idField.setValue(indexBeanXxgk.getSite_id());
/* 286 */         doc.add(site_idField);
/*     */ 
/* 288 */         node_idField.setValue(indexBeanXxgk.getNode_id());
/* 289 */         doc.add(node_idField);
/*     */ 
/* 291 */         wnumberField.setValue(indexBeanXxgk.getWnumber());
/* 292 */         doc.add(wnumberField);
/* 293 */         codeField.setValue(indexBeanXxgk.getCode());
/* 294 */         doc.add(codeField);
/* 295 */         publish_timeField.setValue(indexBeanXxgk.getPublish_time());
/* 296 */         doc.add(publish_timeField);
/* 297 */         c_timeField.setValue(indexBeanXxgk.getC_time());
/* 298 */         doc.add(c_timeField);
/*     */ 
/* 300 */         take_timeField.setValue(indexBeanXxgk.getTake_time());
/* 301 */         doc.add(take_timeField);
/* 302 */         over_timeField.setValue(indexBeanXxgk.getOver_time());
/* 303 */         doc.add(over_timeField);
/* 304 */         object_wordsField.setValue(indexBeanXxgk.getObject_words());
/* 305 */         doc.add(object_wordsField);
/* 306 */         form_typeField.setValue(indexBeanXxgk.getForm_type());
/* 307 */         doc.add(form_typeField);
/* 308 */         content_typeField.setValue(indexBeanXxgk.getContent_type());
/* 309 */         doc.add(content_typeField);
/* 310 */         publish_orgField.setValue(indexBeanXxgk.getPublish_org());
/* 311 */         doc.add(publish_orgField);
/*     */ 
/* 313 */         site_id_delField.setValue(indexBeanXxgk.getSite_id().toLowerCase());
/* 314 */         doc.add(site_id_delField);
/*     */ 
/* 316 */         System.out.println("appendALlDocument id:" + indexBeanXxgk.getId() + "     CategoryId:" + indexBeanXxgk.getCategoryId());
/* 317 */         categoryIdField.setValue(indexBeanXxgk.getCategoryId());
/* 318 */         doc.add(categoryIdField);
/*     */ 
/* 320 */         is_hostField.setValue(indexBeanXxgk.getIs_host());
/* 321 */         doc.add(is_hostField);
/*     */ 
/* 324 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 329 */       e.printStackTrace();
/* 330 */       return false;
/*     */     }
/*     */     finally {
/* 333 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 334 */       s.close();
/*     */     }
/* 333 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 334 */     s.close();
/*     */ 
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 346 */     IndexWriter indexWriter = null;
/*     */     try {
/* 348 */       if (map.get("site_id") != null) {
/* 349 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 351 */         Term term = new Term("site_id", ((String)map.get("site_id")).toLowerCase());
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
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 372 */     IndexWriter indexWriter = null;
/*     */     try {
/* 374 */       if (infos.get("id") != null)
/*     */       {
/* 376 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 378 */         Term term = new Term("id", (String)infos.get("id"));
/* 379 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 383 */       e.printStackTrace();
/* 384 */       return false;
/*     */     }
/*     */     finally {
/* 387 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 389 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanXxgkInfo getIndexBeanInfo(Map info)
/*     */   {
/* 394 */     info = MapManager.mapKeyValueToLow(info);
/* 395 */     IndexBeanXxgkInfo indexBeanXxgk = new IndexBeanXxgkInfo();
/*     */ 
/* 397 */     StringBuffer sbCustom = new StringBuffer("");
/*     */     try
/*     */     {
/* 402 */       List customFields = new ArrayList();
/*     */ 
/* 404 */       InfoBean infoBean = InfoBaseRPC.getInfoById(String.valueOf(info.get("info_id")));
/* 405 */       ModelBean modelBean = ModelRPC.getModelBean(infoBean.getModel_id());
/* 406 */       Map mapForm = new HashMap();
/* 407 */       mapForm.put("model_id", Integer.valueOf(modelBean.getModel_id()));
/* 408 */       List modelFieldBeans = FormRPC.getFormFieldsListByModelIdN(mapForm);
/* 409 */       for (Fields fieldBean : modelFieldBeans) {
/* 410 */         customFields.add(fieldBean.getField_enname().toLowerCase());
/*     */       }
/*     */ 
/* 413 */       Map infoMap = new HashMap();
/* 414 */       Iterator it = info.keySet().iterator();
/* 415 */       while (it.hasNext()) {
/* 416 */         String key = (String)it.next();
/* 417 */         Object object = info.get(key);
/* 418 */         String value = "";
/* 419 */         if ((object instanceof BigDecimal)) {
/* 420 */           value = object.toString();
/* 421 */         } else if ((object instanceof CLOB)) {
/* 422 */           CLOB clob = (CLOB)object;
/*     */ 
/* 428 */           if (clob != null) {
/* 429 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 434 */           value = String.valueOf(object);
/*     */         }
/* 436 */         infoMap.put(key.toLowerCase(), value);
/*     */ 
/* 442 */         if (customFields.contains(key)) {
/* 443 */           sbCustom.append(value);
/*     */         }
/*     */       }
/* 446 */       indexBeanXxgk.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 447 */       indexBeanXxgk.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 448 */       indexBeanXxgk.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/*     */ 
/* 450 */       indexBeanXxgk.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString(sbCustom.toString())));
/*     */ 
/* 452 */       indexBeanXxgk.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 453 */       String site_id = SiteAppRele.getSiteIDByAppID("zwgk");
/* 454 */       indexBeanXxgk.setSite_id(site_id);
/*     */ 
/* 456 */       indexBeanXxgk.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 458 */       indexBeanXxgk.setWnumber(FormatUtil.formatNullString((String)infoMap.get("doc_no")));
/* 459 */       indexBeanXxgk.setCode(FormatUtil.formatNullString((String)infoMap.get("gk_index")));
/* 460 */       String time = (String)infoMap.get("released_dtime");
/* 461 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 462 */       indexBeanXxgk.setPublish_time(FormatUtil.formatNullString(time));
/* 463 */       time = (String)infoMap.get("generate_dtime");
/* 464 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 465 */       indexBeanXxgk.setC_time(FormatUtil.formatNullString(time));
/* 466 */       time = (String)infoMap.get("effect_dtime");
/* 467 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 468 */       indexBeanXxgk.setTake_time(FormatUtil.formatNullString(time));
/* 469 */       time = (String)infoMap.get("aboli_dtime");
/* 470 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 471 */       indexBeanXxgk.setOver_time(FormatUtil.formatNullString(time));
/* 472 */       indexBeanXxgk.setObject_words(FormatUtil.formatNullString((String)infoMap.get("topic_key")) + " " + FormatUtil.formatNullString((String)infoMap.get("place_key")));
/* 473 */       indexBeanXxgk.setForm_type(FormatUtil.formatNullString((String)infoMap.get("theme_name")));
/* 474 */       indexBeanXxgk.setContent_type(FormatUtil.formatNullString((String)infoMap.get("topic_name")));
/* 475 */       indexBeanXxgk.setPublish_org(FormatUtil.formatNullString((String)infoMap.get("gk_input_dept")));
/* 476 */       indexBeanXxgk.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/* 477 */       indexBeanXxgk.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/* 478 */       return indexBeanXxgk;
/*     */     } catch (Exception e) {
/* 480 */       e.printStackTrace();
/* 481 */     }return indexBeanXxgk;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexCustomGkServiceImpl
 * JD-Core Version:    0.6.2
 */