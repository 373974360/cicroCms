/*     */ package com.cicro.wcm.services.search_bak_20151106.index;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.dao.search.impl.InfoServiceUtil;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*     */ import com.cicro.wcm.services.search.SearchForInterface;
/*     */ import com.cicro.wcm.services.search.SearchForManager;
/*     */ import com.cicro.wcm.services.search.index.*;
import com.cicro.wcm.services.search.index.IndexLuceneManager;
import com.cicro.wcm.services.search.index.impl.IndexBsznServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexFileServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexFwServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexInfoServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexLawServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexLeaderServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexOrgDServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexOrgServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexPicServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexVideoServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexXxgkServiceImpl;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class IndexManager_nxszs
/*     */ {
/*  35 */   static String indexDir = SearchForInterface.getIndexPathRoot();
/*     */ 
/*  37 */   public static com.cicro.wcm.services.search.index.IndexService indexInfoService = new IndexInfoServiceImpl();
/*  38 */   public static com.cicro.wcm.services.search.index.IndexService indexPicService = new IndexPicServiceImpl();
/*  39 */   public static com.cicro.wcm.services.search.index.IndexService indexVideoService = new IndexVideoServiceImpl();
/*  40 */   public static com.cicro.wcm.services.search.index.IndexService indexXxgkService = new IndexXxgkServiceImpl();
/*  41 */   public static com.cicro.wcm.services.search.index.IndexService indexFileService = new IndexFileServiceImpl();
/*  42 */   public static com.cicro.wcm.services.search.index.IndexService indexLeaderService = new IndexLeaderServiceImpl();
/*  43 */   public static com.cicro.wcm.services.search.index.IndexService indexOrgService = new IndexOrgServiceImpl();
/*  44 */   public static com.cicro.wcm.services.search.index.IndexService indexOrgDService = new IndexOrgDServiceImpl();
/*  45 */   public static com.cicro.wcm.services.search.index.IndexService indexLawService = new IndexLawServiceImpl();
/*  46 */   public static com.cicro.wcm.services.search.index.IndexService indexBsznService = new IndexBsznServiceImpl();
/*  47 */   public static com.cicro.wcm.services.search.index.IndexService indexFwService = new IndexFwServiceImpl();
/*     */ 
/*     */   public static void createAllIndexByAppId(Map map)
/*     */   {
/*  54 */     indexInfoService.appendALlDocument(map);
/*  55 */     indexPicService.appendALlDocument(map);
/*  56 */     indexVideoService.appendALlDocument(map);
/*  57 */     indexXxgkService.appendALlDocument(map);
/*  58 */     indexFileService.appendALlDocument(map);
/*  59 */     indexLeaderService.appendALlDocument(map);
/*  60 */     indexOrgService.appendALlDocument(map);
/*  61 */     indexOrgDService.appendALlDocument(map);
/*  62 */     indexLawService.appendALlDocument(map);
/*  63 */     indexBsznService.appendALlDocument(map);
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocumentAppId(Map map)
/*     */   {
/*  70 */     appendSingleDocumentAppIdInfo(map);
/*  71 */     appendSingleDocumentAppIdZwgk(map);
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocumentAppIdInfo(Map map)
/*     */   {
/*  76 */     if (indexInfoService.appendSingleDocument(map)) {
/*  77 */       return;
/*     */     }
/*  79 */     if (indexPicService.appendSingleDocument(map)) {
/*  80 */       return;
/*     */     }
/*  82 */     if (indexVideoService.appendSingleDocument(map));
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocumentAppIdZwgk(Map map)
/*     */   {
/*  92 */     if (indexXxgkService.appendSingleDocument(map)) {
/*  93 */       return;
/*     */     }
/*  95 */     if (indexFileService.appendSingleDocument(map)) {
/*  96 */       return;
/*     */     }
/*  98 */     if (indexLeaderService.appendSingleDocument(map)) {
/*  99 */       return;
/*     */     }
/* 101 */     if (indexOrgService.appendSingleDocument(map)) {
/* 102 */       return;
/*     */     }
/* 104 */     if (indexOrgDService.appendSingleDocument(map)) {
/* 105 */       return;
/*     */     }
/* 107 */     if (indexLawService.appendSingleDocument(map)) {
/* 108 */       return;
/*     */     }
/* 110 */     if (indexBsznService.appendSingleDocument(map));
/*     */   }
/*     */ 
/*     */   public static void createAllIndexBySite(Map mapSite)
/*     */   {
/* 120 */     indexInfoService.appendALlDocument(mapSite);
/* 121 */     indexPicService.appendALlDocument(mapSite);
/* 122 */     indexVideoService.appendALlDocument(mapSite);
/*     */ 
/* 125 */     String site_id1 = (String)mapSite.get("site_id");
/*     */ 
/* 127 */     if ((site_id1 != null) && (!"".equals(site_id1)))
/*     */     {
/* 129 */       String site_id2 = SiteAppRele.getSiteIDByAppID("zwgk");
/* 130 */       if (site_id1.equals(site_id2)) {
/* 131 */         Map map = new HashMap();
/* 132 */         indexXxgkService.appendALlDocument(map);
/* 133 */         indexFileService.appendALlDocument(map);
/* 134 */         indexLeaderService.appendALlDocument(map);
/* 135 */         indexOrgService.appendALlDocument(map);
/* 136 */         indexOrgDService.appendALlDocument(map);
/* 137 */         indexLawService.appendALlDocument(map);
/*     */ 
/* 139 */         indexBsznService.appendALlDocument(mapSite);
/*     */       }
/*     */ 
/* 143 */       String site_idFw = SiteAppRele.getSiteIDByAppID("ggfw");
/* 144 */       if (site_id1.equals(site_idFw))
/* 145 */         indexFwService.appendALlDocument(mapSite);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void deleteSingleDocument(Map map)
/*     */   {
/* 152 */     indexInfoService.deleteSingleDocument(map);
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocument(Map map)
/*     */   {
/* 157 */     deleteSingleDocument(map);
/* 158 */     String type = FormatUtil.formatNullString(InfoServiceUtil.getInfoTypeById(map));
/* 159 */     if (type.equals("cms"))
/* 160 */       appendSingleDocumentAppIdInfo(map);
/* 161 */     else if (type.equals("zwgk"))
/* 162 */       appendSingleDocumentAppIdZwgk(map);
/* 163 */     else if (type.equals("ggfw"))
/* 164 */       indexFwService.appendSingleDocument(map);
/*     */   }
/*     */ 
/*     */   public static boolean initAndCreateIndex(boolean isInit)
/*     */   {
/*     */     try
/*     */     {
/* 172 */       File file = new File(indexDir);
/* 173 */       if (!file.exists()) {
/* 174 */         initIndexSearch(isInit);
/*     */       }
/* 176 */       return true;
/*     */     } catch (Exception e) {
/* 178 */       e.printStackTrace();
/* 179 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void initIndexSearch(boolean isInit)
/*     */   {
/* 185 */     IndexLuceneManager.initIndex();
/* 186 */     if (isInit)
/* 187 */       readALLToIndex();
/*     */   }
/*     */ 
/*     */   public static void readALLToIndex()
/*     */   {
/* 196 */     List listSite = SearchForManager.getSiteList();
/* 197 */     for (Map mapSite : listSite)
/* 198 */       createAllIndexBySite(mapSite);
/*     */   }
/*     */ 
/*     */   public static boolean readToIndexBySite(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 210 */       Map mapSite = new HashMap();
/* 211 */       mapSite.put("site_id", site_id);
/* 212 */       createAllIndexBySite(mapSite);
/* 213 */       return true;
/*     */     } catch (Exception e) {
/* 215 */       e.printStackTrace();
/* 216 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean isIndexDirExists()
/*     */   {
/* 223 */     File file = new File(indexDir);
/* 224 */     if (!file.exists()) {
/* 225 */       return false;
/*     */     }
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteIndexDir()
/*     */   {
/*     */     try {
/* 233 */       File file = new File(indexDir);
/* 234 */       if (!file.exists()) {
/* 235 */         return true;
/*     */       }
/* 237 */       FileOperation.deleteDir(indexDir);
/* 238 */       return true;
/*     */     } catch (Exception e) {
/* 240 */       e.printStackTrace();
/* 241 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteIndexBySite(String site_id)
/*     */   {
/* 252 */     Map map = new HashMap();
/* 253 */     map.put("site_id", site_id);
/* 254 */     return indexInfoService.deleteALlDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.IndexManager_nxszs
 * JD-Core Version:    0.6.2
 */