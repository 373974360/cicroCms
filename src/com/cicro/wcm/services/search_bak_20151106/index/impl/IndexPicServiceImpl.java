/*     */ package com.cicro.wcm.services.search_bak_20151106.index.impl;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import com.cicro.wcm.bean.search.IndexBeanInfo;
/*     */ import com.cicro.wcm.bean.search.InfoPic;
/*     */ import com.cicro.wcm.dao.search.IIndexInfoPicDao;
/*     */ import com.cicro.wcm.dao.search.impl.IndexPicDaoImpl;
/*     */ import com.cicro.wcm.services.search.index.IndexLuceneManager;
/*     */ import com.cicro.wcm.services.search.index.IndexService;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.document.Field;
/*     */ import org.apache.lucene.document.Field.Index;
/*     */ import org.apache.lucene.document.Field.Store;
/*     */ import org.apache.lucene.index.IndexWriter;
/*     */ import org.apache.lucene.index.Term;
/*     */ 
/*     */ public class IndexPicServiceImpl
/*     */   implements IndexService
/*     */ {
/*  30 */   static int s_count = 50;
/*  31 */   static IIndexInfoPicDao indexInfoDao = new IndexPicDaoImpl();
/*     */ 
/*     */   public boolean appendALlDocument(Map mapSite)
/*     */   {
/*  43 */     IndexWriter indexWriter = null;
/*     */     try {
/*  45 */       int count = indexInfoDao.getInfoListBySiteIdCount(mapSite);
/*  46 */       int page = count / s_count + 1;
/*  47 */       System.out.println("appendALlDocument pic count===" + count);
/*     */ 
/*  50 */       Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  51 */       Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  52 */       Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  53 */       Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/*  54 */       Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/*  55 */       Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*  56 */       Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*  57 */       Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/*  58 */       Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  60 */       Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  62 */       Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  64 */       Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/*  66 */       Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/*  68 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/*  71 */       for (int k = 0; k < page; k++) {
/*  72 */         int start_num = s_count * k;
/*  73 */         mapSite.put("start_num", Integer.valueOf(start_num));
/*  74 */         mapSite.put("page_size", Integer.valueOf(s_count));
/*     */ 
/*  76 */         List infoList = indexInfoDao.getInfoListBySiteIdPages(mapSite);
/*     */ 
/*  78 */         for (IndexBeanInfo infoMap : infoList)
/*     */         {
/*  81 */           List infoListMap = indexInfoDao.getInfosById(Integer.valueOf(infoMap.getId()).intValue());
/*  82 */           if ((infoListMap != null) && (infoListMap.size() >= 1))
/*     */           {
/*  87 */             IndexBeanInfo indexBeanInfo = getIndexBeanInfo(infoMap);
/*     */ 
/*  90 */             Document doc = new Document();
/*  91 */             if (indexBeanInfo.getApp_id().equals("ggfw")) {
/*  92 */               typeField.setValue(indexBeanInfo.getApp_id());
/*  93 */               doc.add(typeField);
/*  94 */               typedField.setValue(indexBeanInfo.getApp_id());
/*  95 */               doc.add(typedField);
/*     */             } else {
/*  97 */               typeField.setValue(IndexBeanInfo.getType());
/*  98 */               doc.add(typeField);
/*  99 */               typedField.setValue(indexBeanInfo.getTyped());
/* 100 */               doc.add(typedField);
/*     */             }
/* 102 */             idField.setValue(indexBeanInfo.getId());
/* 103 */             doc.add(idField);
/* 104 */             titleField.setValue(indexBeanInfo.getTitle());
/* 105 */             doc.add(titleField);
/* 106 */             contentField.setValue(indexBeanInfo.getContent());
/* 107 */             doc.add(contentField);
/* 108 */             urlField.setValue(indexBeanInfo.getUrl());
/* 109 */             doc.add(urlField);
/* 110 */             site_idField.setValue(indexBeanInfo.getSite_id());
/* 111 */             doc.add(site_idField);
/*     */ 
/* 113 */             node_idField.setValue(indexBeanInfo.getNode_id());
/* 114 */             doc.add(node_idField);
/*     */ 
/* 116 */             model_idField.setValue(indexBeanInfo.getModel_id());
/* 117 */             doc.add(model_idField);
/*     */ 
/* 120 */             site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 121 */             doc.add(site_id_delField);
/*     */ 
/* 123 */             categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 124 */             System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 125 */             doc.add(categoryIdField);
/* 126 */             publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 127 */             doc.add(publish_timeField);
/*     */ 
/* 129 */             is_hostField.setValue(indexBeanInfo.getIs_host());
/* 130 */             doc.add(is_hostField);
/*     */ 
/* 133 */             IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 139 */       e.printStackTrace();
/* 140 */       return false;
/*     */     }
/*     */     finally {
/* 143 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean appendSingleDocument(Map infos)
/*     */   {
/* 155 */     IndexWriter indexWriter = null;
/*     */     try
/*     */     {
/* 159 */       indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 161 */       if (infos.get("id") != null) {
/* 162 */         int id = Integer.valueOf((String)infos.get("id")).intValue();
/*     */ 
/* 165 */         List infoListMap = indexInfoDao.getInfosById(Integer.valueOf(id).intValue());
/* 166 */         if ((infoListMap == null) || (infoListMap.size() < 1)) {
/* 167 */           return false;
/*     */         }
/*     */ 
/* 170 */         IndexBeanInfo infoMap = indexInfoDao.getInfoById(id);
/* 171 */         if (infoMap == null) {
/* 172 */           return false;
/*     */         }
/*     */ 
/* 175 */         IndexBeanInfo indexBeanInfo = getIndexBeanInfo(infoMap);
/*     */ 
/* 177 */         Field typeField = new Field("type", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 178 */         Field typedField = new Field("typed", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 179 */         Field idField = new Field("id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 180 */         Field titleField = new Field("title", "", Field.Store.YES, Field.Index.ANALYZED);
/* 181 */         Field contentField = new Field("content", "", Field.Store.YES, Field.Index.ANALYZED);
/* 182 */         Field urlField = new Field("url", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 183 */         Field site_idField = new Field("site_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 185 */         Field site_id_delField = new Field("site_id_del", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/* 186 */         Field categoryIdField = new Field("categoryId", "", Field.Store.YES, Field.Index.ANALYZED);
/* 187 */         Field publish_timeField = new Field("publish_time", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 189 */         Field node_idField = new Field("node_id", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 191 */         Field model_idField = new Field("model_id", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
/*     */ 
/* 193 */         Field is_hostField = new Field("is_host", "", Field.Store.YES, Field.Index.ANALYZED);
/*     */ 
/* 196 */         Document doc = new Document();
/* 197 */         if (indexBeanInfo.getApp_id().equals("ggfw")) {
/* 198 */           typeField.setValue(indexBeanInfo.getApp_id());
/* 199 */           doc.add(typeField);
/* 200 */           typedField.setValue(indexBeanInfo.getApp_id());
/* 201 */           doc.add(typedField);
/*     */         } else {
/* 203 */           typeField.setValue(IndexBeanInfo.getType());
/* 204 */           doc.add(typeField);
/* 205 */           typedField.setValue(indexBeanInfo.getTyped());
/* 206 */           doc.add(typedField);
/*     */         }
/* 208 */         idField.setValue(indexBeanInfo.getId());
/* 209 */         doc.add(idField);
/* 210 */         titleField.setValue(indexBeanInfo.getTitle());
/* 211 */         doc.add(titleField);
/* 212 */         contentField.setValue(indexBeanInfo.getContent());
/* 213 */         doc.add(contentField);
/* 214 */         urlField.setValue(indexBeanInfo.getUrl());
/* 215 */         doc.add(urlField);
/* 216 */         site_idField.setValue(indexBeanInfo.getSite_id());
/* 217 */         doc.add(site_idField);
/*     */ 
/* 219 */         node_idField.setValue(indexBeanInfo.getNode_id());
/* 220 */         doc.add(node_idField);
/*     */ 
/* 222 */         model_idField.setValue(indexBeanInfo.getModel_id());
/* 223 */         doc.add(model_idField);
/*     */ 
/* 226 */         site_id_delField.setValue(indexBeanInfo.getSite_id().toLowerCase());
/* 227 */         doc.add(site_id_delField);
/*     */ 
/* 229 */         System.out.println("appendALlDocument id:" + indexBeanInfo.getId() + "     CategoryId:" + indexBeanInfo.getCategoryId());
/* 230 */         categoryIdField.setValue(indexBeanInfo.getCategoryId());
/* 231 */         doc.add(categoryIdField);
/* 232 */         publish_timeField.setValue(indexBeanInfo.getPublish_time());
/* 233 */         doc.add(publish_timeField);
/*     */ 
/* 235 */         is_hostField.setValue(indexBeanInfo.getIs_host());
/* 236 */         doc.add(is_hostField);
/*     */ 
/* 239 */         IndexLuceneManager.AddDocument(indexWriter, doc);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 244 */       e.printStackTrace();
/* 245 */       return false;
/*     */     }
/*     */     finally {
/* 248 */       IndexLuceneManager.closeIndexWriter(indexWriter); } IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */ 
/* 250 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteALlDocument(Map map)
/*     */   {
/* 260 */     IndexWriter indexWriter = null;
/*     */     try {
/* 262 */       if (map.get("site_id") != null) {
/* 263 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 265 */         Term term = new Term("site_id_del", ((String)map.get("site_id")).toLowerCase());
/* 266 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 270 */       e.printStackTrace();
/* 271 */       return false;
/*     */     }
/*     */     finally {
/* 274 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 276 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteSingleDocument(Map infos)
/*     */   {
/* 286 */     IndexWriter indexWriter = null;
/*     */     try {
/* 288 */       if (infos.get("id") != null)
/*     */       {
/* 290 */         indexWriter = IndexLuceneManager.getIndexModifier(false);
/*     */ 
/* 292 */         Term term = new Term("id", (String)infos.get("id"));
/* 293 */         IndexLuceneManager.DeleteDocument(indexWriter, term);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 297 */       e.printStackTrace();
/* 298 */       return false;
/*     */     }
/*     */     finally {
/* 301 */       IndexLuceneManager.closeIndexWriter(indexWriter);
/*     */     }
/* 303 */     return true;
/*     */   }
/*     */ 
/*     */   public static IndexBeanInfo getIndexBeanInfo(IndexBeanInfo info)
/*     */   {
/* 308 */     IndexBeanInfo indexBeanInfo = new IndexBeanInfo();
/*     */     try
/*     */     {
/* 333 */       indexBeanInfo.setId(FormatUtil.formatNullString(info.getId()));
/* 334 */       indexBeanInfo.setTitle(FormatUtil.formatNullString(info.getTitle()));
/* 335 */       indexBeanInfo.setApp_id(FormatUtil.formatNullString(info.getApp_id()));
/* 336 */       indexBeanInfo.setUrl(FormatUtil.formatNullString(info.getUrl()));
/* 337 */       indexBeanInfo.setSite_id(FormatUtil.formatNullString(info.getSite_id()));
/*     */ 
/* 339 */       indexBeanInfo.setNode_id(FormatUtil.formatNullString(info.getSite_id()));
/* 340 */       indexBeanInfo.setModel_id(FormatUtil.formatNullString(info.getModel_id()));
/*     */ 
/* 342 */       indexBeanInfo.setCategoryId(FormatUtil.formatNullString(info.getCategoryId()));
/* 343 */       String time = info.getPublish_time();
/* 344 */       time = time == null ? "" : time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
/*     */ 
/* 346 */       indexBeanInfo.setPublish_time(FormatUtil.formatNullString(time));
/* 347 */       List infoPics = info.getInfoPics();
/* 348 */       StringBuffer sb = new StringBuffer();
/* 349 */       for (InfoPic pic : infoPics) {
/* 350 */         if (pic != null)
/*     */         {
/* 352 */           sb.append(FormatUtil.formatNullString(pic.getPic_title()) + FormatUtil.formatNullString(pic.getPic_content()));
/*     */         }
/*     */       }
/* 355 */       indexBeanInfo.setContent(HtmlRegexpUtil.filterHtml(FormatUtil.formatNullString(sb.toString())));
/*     */ 
/* 357 */       indexBeanInfo.setIs_host(FormatUtil.formatNullString(info.getIs_host()));
/*     */ 
/* 359 */       return indexBeanInfo;
/*     */     } catch (Exception e) {
/* 361 */       e.printStackTrace();
/* 362 */     }return indexBeanInfo;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 369 */     IndexPicServiceImpl impl = new IndexPicServiceImpl();
/* 370 */     Map map = new HashMap();
/* 371 */     map.put("id", "1111");
/* 372 */     impl.appendSingleDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.impl.IndexPicServiceImpl
 * JD-Core Version:    0.6.2
 */