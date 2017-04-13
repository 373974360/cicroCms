/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanBsznInfo;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexBsznDaoImpl;
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
/*     */ public class IndexBsznServiceImpl
/*     */   implements IndexService
/*     */ {
/*  34 */   static int s_count = 50;
/*  35 */   static IIndexInfoDao indexInfoDao = new IndexBsznDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  47 */     IndexWriter indexWriter = null;
/*  48 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  50 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  51 */       int page = count / s_count + 1;
/*  52 */       System.out.println("appendALlDocument bszn count===" + count);
/*     */ 
/*  55 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  57 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  58 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  59 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  60 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  61 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  62 */       Field bszn_typeField = new Field("bszn_type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  63 */       Field bszn_orgField = new Field("bszn_org", "", Field.Store.YES, Field.Index.ANALYZED);
/*  64 */       Field bszn_objectField = new Field("bszn_object", "", Field.Store.YES, Field.Index.ANALYZED);
/*  65 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  67 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  68 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  70 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  71 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  73 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  76 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  79 */       for (int k = 0; k < page; k++) {
/*  80 */         int start_num = s_count * k;
/*  81 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  82 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  85 */         List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getBsznListBySiteIdPages"), mapSite);
/*     */ 
/*  87 */         for (Map infoMap : infoList)
/*     */         {
/*  90 */           IndexBeanBsznInfo indexBeanBsznInfo = getIndexBeanInfo(infoMap);
/*     */ 
/*  92 */           System.out.println("appendALlDocument id===" + indexBeanBsznInfo.getId());
/*     */ 
/*  95 */           Document doc = new Document();
/*  96 */           if (indexBeanBsznInfo.getApp_id().equals("ggfw")) {
/*  97 */             typeField.setValue(indexBeanBsznInfo.getApp_id());
/*  98 */             doc.add(typeField);
/*  99 */             typedField.setValue(indexBeanBsznInfo.getApp_id());
/* 100 */             doc.add(typedField);
/*     */           } else {
/* 102 */             typeField.setValue(IndexBeanBsznInfo.getType());
/* 103 */             doc.add(typeField);
/* 104 */             typedField.setValue(indexBeanBsznInfo.getTyped());
/* 105 */             doc.add(typedField);
/*     */           }
/* 107 */           idField.setValue(indexBeanBsznInfo.getId());
/* 108 */           doc.add(idField);
/* 109 */           titleField.setValue(indexBeanBsznInfo.getTitle());
/* 110 */           doc.add(titleField);
/* 111 */           contentField.setValue(indexBeanBsznInfo.getContent());
/* 112 */           doc.add(contentField);
/* 113 */           urlField.setValue(indexBeanBsznInfo.getUrl());
/* 114 */           doc.add(urlField);
/* 115 */           site_idField.setValue(indexBeanBsznInfo.getSite_id());
/* 116 */           doc.add(site_idField);
/*     */ 
/* 118 */           node_idField.setValue(indexBeanBsznInfo.getNode_id());
/* 119 */           doc.add(node_idField);
/*     */ 
/* 121 */           model_idField.setValue(indexBeanBsznInfo.getModel_id());
/* 122 */           doc.add(model_idField);
/*     */ 
/* 124 */           bszn_typeField.setValue(indexBeanBsznInfo.getBszn_type());
/* 125 */           doc.add(bszn_typeField);
/* 126 */           bszn_orgField.setValue(indexBeanBsznInfo.getBszn_org());
/* 127 */           doc.add(bszn_orgField);
/* 128 */           bszn_objectField.setValue(indexBeanBsznInfo.getBszn_object());
/* 129 */           doc.add(bszn_objectField);
/* 130 */           publish_timeField.setValue(indexBeanBsznInfo.getPublish_time());
/* 131 */           doc.add(publish_timeField);
/*     */ 
/* 133 */           site_id_delField.setValue(indexBeanBsznInfo.getSite_id().toLowerCase());
/* 134 */           doc.add(site_id_delField);
/*     */ 
/* 136 */           categoryIdField.setValue(indexBeanBsznInfo.getCategoryId());
/* 137 */           System.out.println("appendALlDocument id:" + indexBeanBsznInfo.getId() + "     CategoryId:" + indexBeanBsznInfo.getCategoryId());
/* 138 */           doc.add(categoryIdField);
/*     */ 
/* 140 */           is_hostField.setValue(indexBeanBsznInfo.getIs_host());
/* 141 */           doc.add(is_hostField);
/*     */ 
/* 144 */           IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 150 */       e.printStackTrace();
/* 151 */       return false;
/*     */     }
/*     */     finally {
/* 154 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 155 */       s.close();
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 167 */     IndexWriter indexWriter = null;
/* 168 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 172 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 174 */       if (infos.get("id") != null) {
/* 175 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 177 */         Map mapC = new HashMap();
/* 178 */         mapC.put("id", Integer.valueOf(id));
/* 179 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getBsznById"), mapC);
/*     */ 
/* 181 */         if (map == null) {
/* 182 */           return false;
/*     */         }
/*     */ 
/* 185 */         IndexBeanBsznInfo indexBeanBsznInfo = getIndexBeanInfo(map);
/*     */ 
/* 188 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 189 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 190 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 191 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 192 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 193 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 194 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/* 195 */         Field bszn_typeField = new Field("bszn_type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 196 */         Field bszn_orgField = new Field("bszn_org", "", Field.Store.YES, Field.Index.ANALYZED);
/* 197 */         Field bszn_objectField = new Field("bszn_object", "", Field.Store.YES, Field.Index.ANALYZED);
/* 198 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 200 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 202 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 204 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 206 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 209 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 212 */         Document doc = new Document();
/* 213 */         if (indexBeanBsznInfo.getApp_id().equals("ggfw")) {
/* 214 */           typeField.setValue(indexBeanBsznInfo.getApp_id());
/* 215 */           doc.add(typeField);
/* 216 */           typedField.setValue(indexBeanBsznInfo.getApp_id());
/* 217 */           doc.add(typedField);
/*     */         } else {
/* 219 */           typeField.setValue(IndexBeanBsznInfo.getType());
/* 220 */           doc.add(typeField);
/* 221 */           typedField.setValue(indexBeanBsznInfo.getTyped());
/* 222 */           doc.add(typedField);
/*     */         }
/* 224 */         idField.setValue(indexBeanBsznInfo.getId());
/* 225 */         doc.add(idField);
/* 226 */         titleField.setValue(indexBeanBsznInfo.getTitle());
/* 227 */         doc.add(titleField);
/* 228 */         contentField.setValue(indexBeanBsznInfo.getContent());
/* 229 */         doc.add(contentField);
/* 230 */         urlField.setValue(indexBeanBsznInfo.getUrl());
/* 231 */         doc.add(urlField);
/* 232 */         site_idField.setValue(indexBeanBsznInfo.getSite_id());
/* 233 */         doc.add(site_idField);
/*     */ 
/* 235 */         node_idField.setValue(indexBeanBsznInfo.getNode_id());
/* 236 */         doc.add(node_idField);
/*     */ 
/* 238 */         model_idField.setValue(indexBeanBsznInfo.getModel_id());
/* 239 */         doc.add(model_idField);
/*     */ 
/* 241 */         bszn_typeField.setValue(indexBeanBsznInfo.getBszn_type());
/* 242 */         doc.add(bszn_typeField);
/* 243 */         bszn_orgField.setValue(indexBeanBsznInfo.getBszn_org());
/* 244 */         doc.add(bszn_orgField);
/* 245 */         bszn_objectField.setValue(indexBeanBsznInfo.getBszn_object());
/* 246 */         doc.add(bszn_objectField);
/* 247 */         publish_timeField.setValue(indexBeanBsznInfo.getPublish_time());
/* 248 */         doc.add(publish_timeField);
/*     */ 
/* 250 */         site_id_delField.setValue(indexBeanBsznInfo.getSite_id().toLowerCase());
/* 251 */         doc.add(site_id_delField);
/*     */ 
/* 253 */         System.out.println("appendALlDocument id:" + indexBeanBsznInfo.getId() + "     CategoryId:" + indexBeanBsznInfo.getCategoryId());
/* 254 */         categoryIdField.setValue(indexBeanBsznInfo.getCategoryId());
/* 255 */         doc.add(categoryIdField);
/*     */ 
/* 257 */         is_hostField.setValue(indexBeanBsznInfo.getIs_host());
/* 258 */         doc.add(is_hostField);
/*     */ 
/* 261 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 266 */       e.printStackTrace();
/* 267 */       return false;
/*     */     }
/*     */     finally {
/* 270 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 271 */       s.close();
/*     */     }
/* 270 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 271 */     s.close();
/*     */ 
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 283 */     IndexWriter indexWriter = null;
/*     */     try {
/* 285 */       if (map.get("site_id") != null) {
/* 286 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 288 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
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
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 309 */     IndexWriter indexWriter = null;
/*     */     try {
/* 311 */       if (infos.get("id") != null)
/*     */       {
/* 313 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 315 */         Term term = new Term("id", (String)infos.get("id"));
/* 316 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 320 */       e.printStackTrace();
/* 321 */       return false;
/*     */     }
/*     */     finally {
/* 324 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 326 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanBsznInfo getIndexBeanInfo(Map info)
/*     */   {
/* 331 */     IndexBeanBsznInfo indexBeanBsznInfo = new IndexBeanBsznInfo();
/*     */     try {
/* 333 */       Map infoMap = new HashMap();
/* 334 */       Iterator it = info.keySet().iterator();
/* 335 */       while (it.hasNext()) {
/* 336 */         String key = (String)it.next();
/* 337 */         Object object = info.get(key);
/* 338 */         String value = "";
/* 339 */         if ((object instanceof BigDecimal)) {
/* 340 */           value = object.toString();
/* 341 */         } else if ((object instanceof CLOB)) {
/* 342 */           CLOB clob = (CLOB)object;
/*     */ 
/* 348 */           if (clob != null) {
/* 349 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 354 */           value = String.valueOf(object);
/*     */         }
/* 356 */         infoMap.put(key.toLowerCase(), value);
/*     */       }
/* 358 */       indexBeanBsznInfo.setId(FormatUtil.formatNullString((String)infoMap.get("info_id")));
/* 359 */       indexBeanBsznInfo.setTitle(FormatUtil.formatNullString((String)infoMap.get("title")));
/* 360 */       indexBeanBsznInfo.setApp_id(FormatUtil.formatNullString((String)infoMap.get("app_id")));
/* 361 */       StringBuffer sb = new StringBuffer();
/* 362 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_bsjg")));
/* 363 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_sxyj")));
/* 364 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_bldx")));
/* 365 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_bltj")));
/* 366 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_blsx")));
/* 367 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_bllc")));
/* 368 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_gsfs")));
/* 369 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_sfbz")));
/* 370 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_sfyj")));
/* 371 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_jdjc")));
/* 372 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_zrzj")));
/* 373 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_wszx")));
/* 374 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_wsbl")));
/* 375 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_ztcx")));
/* 376 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_wsts")));
/* 377 */       sb.append(FormatUtil.formatNullString((String)infoMap.get("gk_memo")));
/* 378 */       indexBeanBsznInfo.setContent(HtmlRegexpUtil.filterHtml(sb.toString()));
/* 379 */       indexBeanBsznInfo.setUrl(FormatUtil.formatNullString((String)infoMap.get("content_url")));
/* 380 */       String site_id = SiteAppRele.getSiteIDByAppID("zwgk");
/* 381 */       indexBeanBsznInfo.setSite_id(site_id);
/*     */ 
/* 383 */       indexBeanBsznInfo.setNode_id(FormatUtil.formatNullString((String)infoMap.get("site_id")));
/*     */ 
/* 385 */       indexBeanBsznInfo.setModel_id(FormatUtil.formatNullString((String)infoMap.get("model_id")));
/*     */ 
/* 387 */       indexBeanBsznInfo.setBszn_type(FormatUtil.formatNullString((String)infoMap.get("gk_fwlb")));
/* 388 */       indexBeanBsznInfo.setBszn_org(FormatUtil.formatNullString((String)infoMap.get("gk_bsjg")));
/* 389 */       indexBeanBsznInfo.setBszn_object(FormatUtil.formatNullString((String)infoMap.get("gk_bldx")));
/* 390 */       String time = (String)infoMap.get("released_dtime");
/* 391 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/* 392 */       indexBeanBsznInfo.setPublish_time(FormatUtil.formatNullString(time));
/* 393 */       indexBeanBsznInfo.setCategoryId(FormatUtil.formatNullString((String)infoMap.get("cat_id")));
/* 394 */       indexBeanBsznInfo.setIs_host(FormatUtil.formatNullString((String)infoMap.get("is_host")));
/* 395 */       return indexBeanBsznInfo;
/*     */     } catch (Exception e) {
/* 397 */       e.printStackTrace();
/* 398 */     }return indexBeanBsznInfo;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 405 */     IndexBsznServiceImpl impl = new IndexBsznServiceImpl();
/* 406 */     Map map = new HashMap();
/* 407 */     map.put("id", "1111");
/* 408 */     impl.appendSingleDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexBsznServiceImpl
 * JD-Core Version:    0.6.2
 */