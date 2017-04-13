/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.search.IndexBeanInfo;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexCustomDaoImpl;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.db.IbatisSessionFactory;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseRPC;
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
/*     */ public class IndexCustomServiceImpl
/*     */   implements IndexService
/*     */ {
/*  41 */   static int s_count = 50;
/*  42 */   static IIndexInfoDao indexInfoDao = new IndexCustomDaoImpl();
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
/*  87 */             Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*  88 */             Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  90 */             Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  92 */             Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  93 */             Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  95 */             indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  98 */             for (int k = 0; k < page; k++) {
/*  99 */               int start_num = s_count * k;
/* 100 */               mapSite.put("start_num", Integer.valueOf(start_num));
/* 101 */               mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/* 103 */               List infoList = s.selectList(DBManager.getSqlNameByDataType("search.getCostomListBySiteIdPages"), mapSite);
/*     */ 
/* 105 */               for (Map infoMap : infoList)
/*     */               {
/* 108 */                 IndexBeanInfo indexBeanInfo = getIndexBeanInfo(infoMap);
/*     */ 
/* 111 */                 Document doc = new Document();
/* 112 */                 if (indexBeanInfo.getApp_id().equals("ggfw")) {
/* 113 */                   typeField.setValue(indexBeanInfo.getApp_id());
/* 114 */                   doc.add(typeField);
/* 115 */                   typedField.setValue(indexBeanInfo.getApp_id());
/* 116 */                   doc.add(typedField);
/*     */                 } else {
/* 118 */                   typeField.setValue(IndexBeanInfo.getType());
/* 119 */                   doc.add(typeField);
/* 120 */                   typedField.setValue(indexBeanInfo.getTyped());
/* 121 */                   doc.add(typedField);
/*     */                 }
/* 123 */                 idField.setValue(indexBeanInfo.getId());
/* 124 */                 doc.add(idField);
/* 125 */                 titleField.setValue(indexBeanInfo.getTitle());
/* 126 */                 doc.add(titleField);
/* 127 */                 contentField.setValue(indexBeanInfo.getContent());
/* 128 */                 doc.add(contentField);
/* 129 */                 urlField.setValue(indexBeanInfo.getUrl());
/* 130 */                 doc.add(urlField);
/* 131 */                 site_idField.setValue(indexBeanInfo.getSite_id());
/* 132 */                 doc.add(site_idField);
/*     */ 
/* 134 */                 node_idField.setValue(indexBeanInfo.getNode_id());
/* 135 */                 doc.add(node_idField);
/*     */ 
/* 138 */                 site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 139 */                 doc.add(site_id_delField);
/*     */ 
/* 141 */                 categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 142 */                 System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 143 */                 doc.add(categoryIdField);
/* 144 */                 publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 145 */                 doc.add(publish_timeField);
/*     */ 
/* 147 */                 is_hostField.setValue(indexBeanInfo.getIs_host());
/* 148 */                 doc.add(is_hostField);
/*     */ 
/* 151 */                 IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) { e.printStackTrace();
/* 158 */       return false;
/*     */     } finally
/*     */     {
/* 161 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 162 */       s.close();
/*     */     }
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 174 */     IndexWriter indexWriter = null;
/* 175 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try
/*     */     {
/* 179 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 181 */       if (infos.get("id") != null) {
/* 182 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 185 */         InfoBean infoBean = InfoBaseRPC.getInfoById(String.valueOf(id));
/* 186 */         ModelBean modelBean = ModelRPC.getModelBean(infoBean.getModel_id());
/* 187 */         String table_name = modelBean.getTable_name();
/*     */ 
/* 189 */         Map maptemp = new HashMap();
/* 190 */         maptemp.put("id", Integer.valueOf(id));
/* 191 */         maptemp.put("table_name", table_name);
/*     */ 
/* 193 */         Map map = (Map)s.selectOne(DBManager.getSqlNameByDataType("search.getCustomById"), maptemp);
/*     */ 
/* 195 */         if (map == null) {
/* 196 */           return false;
/*     */         }
/*     */ 
/* 199 */         IndexBeanInfo indexBeanInfo = getIndexBeanInfo(map);
/*     */ 
/* 201 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 202 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 203 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 204 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 205 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 206 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 207 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 209 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 210 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/* 211 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 213 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 215 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 217 */         Document doc = new Document();
/* 218 */         if (indexBeanInfo.getApp_id().equals("ggfw")) {
/* 219 */           typeField.setValue(indexBeanInfo.getApp_id());
/* 220 */           doc.add(typeField);
/* 221 */           typedField.setValue(indexBeanInfo.getApp_id());
/* 222 */           doc.add(typedField);
/*     */         } else {
/* 224 */           typeField.setValue(IndexBeanInfo.getType());
/* 225 */           doc.add(typeField);
/* 226 */           typedField.setValue(indexBeanInfo.getTyped());
/* 227 */           doc.add(typedField);
/*     */         }
/* 229 */         idField.setValue(indexBeanInfo.getId());
/* 230 */         doc.add(idField);
/* 231 */         titleField.setValue(indexBeanInfo.getTitle());
/* 232 */         doc.add(titleField);
/* 233 */         contentField.setValue(indexBeanInfo.getContent());
/* 234 */         doc.add(contentField);
/* 235 */         urlField.setValue(indexBeanInfo.getUrl());
/* 236 */         doc.add(urlField);
/* 237 */         site_idField.setValue(indexBeanInfo.getSite_id());
/* 238 */         doc.add(site_idField);
/*     */ 
/* 240 */         node_idField.setValue(indexBeanInfo.getNode_id());
/* 241 */         doc.add(node_idField);
/*     */ 
/* 244 */         site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 245 */         doc.add(site_id_delField);
/*     */ 
/* 247 */         System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 248 */         categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 249 */         doc.add(categoryIdField);
/* 250 */         publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 251 */         doc.add(publish_timeField);
/*     */ 
/* 253 */         is_hostField.setValue(indexBeanInfo.getIs_host());
/* 254 */         doc.add(is_hostField);
/*     */ 
/* 256 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 261 */       e.printStackTrace();
/* 262 */       return false;
/*     */     }
/*     */     finally {
/* 265 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/* 266 */       s.close();
/*     */     }
/* 265 */     IndexLuceneManager.closeIndexWriter(indexWriter);
/* 266 */     s.close();
/*     */ 
/* 268 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 278 */     IndexWriter indexWriter = null;
/*     */     try {
/* 280 */       if (map.get("site_id") != null) {
/* 281 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 283 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
/* 284 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 288 */       e.printStackTrace();
/* 289 */       return false;
/*     */     }
/*     */     finally {
/* 292 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 294 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 304 */     IndexWriter indexWriter = null;
/*     */     try {
/* 306 */       if (infos.get("id") != null)
/*     */       {
/* 308 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 310 */         Term term = new Term("id", (String)infos.get("id"));
/* 311 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 315 */       e.printStackTrace();
/* 316 */       return false;
/*     */     }
/*     */     finally {
/* 319 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 321 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanInfo getIndexBeanInfo(Map info)
/*     */   {
/* 326 */     info = MapManager.mapKeyValueToLow(info);
/* 327 */     IndexBeanInfo indexBeanInfo = new IndexBeanInfo();
/*     */ 
/* 331 */     List customFields = new ArrayList();
/*     */ 
/* 333 */     InfoBean infoBean = InfoBaseRPC.getInfoById(String.valueOf(info.get("info_id")));
/* 334 */     ModelBean modelBean = ModelRPC.getModelBean(infoBean.getModel_id());
/* 335 */     Map mapForm = new HashMap();
/* 336 */     mapForm.put("model_id", Integer.valueOf(modelBean.getModel_id()));
/* 337 */     List modelFieldBeans = FormRPC.getFormFieldsListByModelIdN(mapForm);
/* 338 */     for (Fields fieldBean : modelFieldBeans) {
/* 339 */       customFields.add(fieldBean.getField_enname().toLowerCase());
/*     */     }
/*     */ 
/* 347 */     StringBuffer sbCustom = new StringBuffer("");
/*     */     try {
/* 349 */       Object infoMap = new HashMap();
/* 350 */       Iterator it = info.keySet().iterator();
/* 351 */       while (it.hasNext()) {
/* 352 */         String key = (String)it.next();
/* 353 */         Object object = info.get(key);
/* 354 */         String value = "";
/* 355 */         if ((object instanceof BigDecimal)) {
/* 356 */           value = object.toString();
/* 357 */         } else if ((object instanceof CLOB)) {
/* 358 */           CLOB clob = (CLOB)object;
/*     */ 
/* 364 */           if (clob != null) {
/* 365 */             value = clob.getSubString(1L, (int)clob.length());
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 370 */           value = String.valueOf(object);
/*     */         }
/* 372 */         ((Map)infoMap).put(key.toLowerCase(), value);
/*     */ 
/* 379 */         if (customFields.contains(key)) {
/* 380 */           sbCustom.append(value);
/*     */         }
/*     */       }
/* 383 */       indexBeanInfo.setId(FormatUtil.formatNullString((String)((Map)infoMap).get("info_id")));
/* 384 */       indexBeanInfo.setTitle(FormatUtil.formatNullString((String)((Map)infoMap).get("title")));
/* 385 */       indexBeanInfo.setApp_id(FormatUtil.formatNullString((String)((Map)infoMap).get("app_id")));
/*     */ 
/* 388 */       indexBeanInfo.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString(sbCustom.toString())));
/*     */ 
/* 390 */       indexBeanInfo.setUrl(FormatUtil.formatNullString((String)((Map)infoMap).get("content_url")));
/* 391 */       indexBeanInfo.setSite_id(FormatUtil.formatNullString((String)((Map)infoMap).get("site_id")));
/*     */ 
/* 393 */       indexBeanInfo.setNode_id(FormatUtil.formatNullString((String)((Map)infoMap).get("site_id")));
/*     */ 
/* 395 */       indexBeanInfo.setCategoryId(FormatUtil.formatNullString((String)((Map)infoMap).get("cat_id")));
/* 396 */       String time = (String)((Map)infoMap).get("released_dtime");
/* 397 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/*     */ 
/* 399 */       indexBeanInfo.setPublish_time(FormatUtil.formatNullString(time));
/* 400 */       indexBeanInfo.setIs_host(FormatUtil.formatNullString((String)((Map)infoMap).get("is_host")));
/* 401 */       return indexBeanInfo;
/*     */     } catch (Exception e) {
/* 403 */       e.printStackTrace();
/* 404 */     }return indexBeanInfo;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 411 */     IndexCustomServiceImpl impl = new IndexCustomServiceImpl();
/* 412 */     Map map = new HashMap();
/* 413 */     map.put("id", "1111");
/* 414 */     impl.appendSingleDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexCustomServiceImpl
 * JD-Core Version:    0.6.2
 */