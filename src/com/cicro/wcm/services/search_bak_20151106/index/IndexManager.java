/*     */ package com.cicro.wcm.services.search_bak_20151106.index;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.dao.search.impl.InfoServiceUtil;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*     */ import com.cicro.wcm.services.search.SearchForInterface;
/*     */ import com.cicro.wcm.services.search.SearchForManager;
/*     */ import com.cicro.wcm.services.search.index.IndexLuceneManager;
import com.cicro.wcm.services.search.index.IndexService;
import com.cicro.wcm.services.search.index.impl.IndexBsznServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexCustomGkServiceImpl;
/*     */ import com.cicro.wcm.services.search.index.impl.IndexCustomServiceImpl;
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
/*     */ public class IndexManager
/*     */ {
/*  37 */   static String indexDir = SearchForInterface.getIndexPathRoot();
/*     */ 
/*  39 */   public static IndexService indexInfoService = new IndexInfoServiceImpl();
/*  40 */   public static IndexService indexPicService = new IndexPicServiceImpl();
/*  41 */   public static IndexService indexVideoService = new IndexVideoServiceImpl();
/*  42 */   public static IndexService indexXxgkService = new IndexXxgkServiceImpl();
/*  43 */   public static IndexService indexFileService = new IndexFileServiceImpl();
/*  44 */   public static IndexService indexLeaderService = new IndexLeaderServiceImpl();
/*  45 */   public static IndexService indexOrgService = new IndexOrgServiceImpl();
/*  46 */   public static IndexService indexOrgDService = new IndexOrgDServiceImpl();
/*  47 */   public static IndexService indexLawService = new IndexLawServiceImpl();
/*  48 */   public static IndexService indexBsznService = new IndexBsznServiceImpl();
/*  49 */   public static IndexService indexFwService = new IndexFwServiceImpl();
/*     */ 
/*  51 */   public static IndexService indexCustomService = new IndexCustomServiceImpl();
/*  52 */   public static IndexService indexCustomGkService = new IndexCustomGkServiceImpl();
/*     */ 
/*     */   public static void createAllIndexByAppId(Map map)
/*     */   {
/*  56 */     indexInfoService.appendALlDocument(map);
/*  57 */     indexPicService.appendALlDocument(map);
/*  58 */     indexVideoService.appendALlDocument(map);
/*  59 */     indexXxgkService.appendALlDocument(map);
/*  60 */     indexFileService.appendALlDocument(map);
/*  61 */     indexLeaderService.appendALlDocument(map);
/*  62 */     indexOrgService.appendALlDocument(map);
/*  63 */     indexOrgDService.appendALlDocument(map);
/*  64 */     indexLawService.appendALlDocument(map);
/*  65 */     indexBsznService.appendALlDocument(map);
/*  66 */     indexCustomService.appendALlDocument(map);
/*  67 */     indexCustomGkService.appendALlDocument(map);
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocumentAppId(Map map)
/*     */   {
/*  72 */     appendSingleDocumentAppIdInfo(map);
/*  73 */     appendSingleDocumentAppIdZwgk(map);
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocumentAppIdInfo(Map map)
/*     */   {
/*  78 */     if (indexInfoService.appendSingleDocument(map)) {
/*  79 */       return;
/*     */     }
/*  81 */     if (indexPicService.appendSingleDocument(map)) {
/*  82 */       return;
/*     */     }
/*  84 */     if (indexVideoService.appendSingleDocument(map)) {
/*  85 */       return;
/*     */     }
/*  87 */     if (indexCustomService.appendSingleDocument(map));
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocumentAppIdZwgk(Map map)
/*     */   {
/*  94 */     if (indexXxgkService.appendSingleDocument(map)) {
/*  95 */       return;
/*     */     }
/*  97 */     if (indexFileService.appendSingleDocument(map)) {
/*  98 */       return;
/*     */     }
/* 100 */     if (indexLeaderService.appendSingleDocument(map)) {
/* 101 */       return;
/*     */     }
/* 103 */     if (indexOrgService.appendSingleDocument(map)) {
/* 104 */       return;
/*     */     }
/* 106 */     if (indexOrgDService.appendSingleDocument(map)) {
/* 107 */       return;
/*     */     }
/* 109 */     if (indexLawService.appendSingleDocument(map)) {
/* 110 */       return;
/*     */     }
/* 112 */     if (indexBsznService.appendSingleDocument(map)) {
/* 113 */       return;
/*     */     }
/* 115 */     if (indexCustomGkService.appendSingleDocument(map));
/*     */   }
/*     */ 
/*     */   public static void createAllIndexBySite(Map mapSite)
/*     */   {
/* 122 */     indexInfoService.appendALlDocument(mapSite);
/* 123 */     indexPicService.appendALlDocument(mapSite);
/* 124 */     indexVideoService.appendALlDocument(mapSite);
/* 125 */     indexCustomService.appendALlDocument(mapSite);
/*     */ 
/* 127 */     String site_id1 = (String)mapSite.get("site_id");
/*     */ 
/* 129 */     if ((site_id1 != null) && (!"".equals(site_id1)))
/*     */     {
/* 131 */       String site_id2 = SiteAppRele.getSiteIDByAppID("zwgk");
/* 132 */       if (site_id1.equals(site_id2)) {
/* 133 */         Map map = new HashMap();
/* 134 */         indexXxgkService.appendALlDocument(map);
/* 135 */         indexFileService.appendALlDocument(map);
/* 136 */         indexLeaderService.appendALlDocument(map);
/* 137 */         indexOrgService.appendALlDocument(map);
/* 138 */         indexOrgDService.appendALlDocument(map);
/* 139 */         indexLawService.appendALlDocument(map);
/* 140 */         indexCustomGkService.appendALlDocument(map);
/* 141 */         indexBsznService.appendALlDocument(mapSite);
/*     */       }
/*     */ 
/* 145 */       String site_idFw = SiteAppRele.getSiteIDByAppID("ggfw");
/* 146 */       if (site_id1.equals(site_idFw))
/* 147 */         indexFwService.appendALlDocument(mapSite);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void deleteSingleDocument(Map map)
/*     */   {
/* 154 */     indexInfoService.deleteSingleDocument(map);
/*     */   }
/*     */ 
/*     */   public static void appendSingleDocument(Map map)
/*     */   {
/* 159 */     deleteSingleDocument(map);
/* 160 */     String type = FormatUtil.formatNullString(InfoServiceUtil.getInfoTypeById(map));
/* 161 */     if (type.equals("cms"))
/* 162 */       appendSingleDocumentAppIdInfo(map);
/* 163 */     else if (type.equals("zwgk"))
/* 164 */       appendSingleDocumentAppIdZwgk(map);
/* 165 */     else if (type.equals("ggfw"))
/* 166 */       indexFwService.appendSingleDocument(map);
/*     */   }
/*     */ 
/*     */   public static boolean initAndCreateIndex(boolean isInit)
/*     */   {
/*     */     try
/*     */     {
/* 174 */       File file = new File(indexDir);
/* 175 */       if (!file.exists()) {
/* 176 */         initIndexSearch(isInit);
/*     */       }
/* 178 */       return true;
/*     */     } catch (Exception e) {
/* 180 */       e.printStackTrace();
/* 181 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void initIndexSearch(boolean isInit)
/*     */   {
/* 187 */     IndexLuceneManager.initIndex();
/* 188 */     if (isInit)
/* 189 */       readALLToIndex();
/*     */   }
/*     */ 
/*     */   public static void readALLToIndex()
/*     */   {
/* 198 */     List listSite = SearchForManager.getSiteList();
/* 199 */     for (Map mapSite : listSite)
/* 200 */       createAllIndexBySite(mapSite);
/*     */   }
/*     */ 
/*     */   public static boolean readToIndexBySite(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 212 */       Map mapSite = new HashMap();
/* 213 */       mapSite.put("site_id", site_id);
/* 214 */       createAllIndexBySite(mapSite);
/* 215 */       return true;
/*     */     } catch (Exception e) {
/* 217 */       e.printStackTrace();
/* 218 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean isIndexDirExists()
/*     */   {
/* 225 */     File file = new File(indexDir);
/* 226 */     if (!file.exists()) {
/* 227 */       return false;
/*     */     }
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteIndexDir()
/*     */   {
/*     */     try {
/* 235 */       File file = new File(indexDir);
/* 236 */       if (!file.exists()) {
/* 237 */         return true;
/*     */       }
/* 239 */       FileOperation.deleteDir(indexDir);
/* 240 */       return true;
/*     */     } catch (Exception e) {
/* 242 */       e.printStackTrace();
/* 243 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteIndexBySite(String site_id)
/*     */   {
/* 254 */     Map map = new HashMap();
/* 255 */     map.put("site_id", site_id);
/* 256 */     return indexInfoService.deleteALlDocument(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.IndexManager
 * JD-Core Version:    0.6.2
 */