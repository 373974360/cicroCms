/*     */ package com.cicro.wcm.services.search_bak_20151106;
/*     */ 
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.xml.XmlManager;
/*     */ import com.cicro.wcm.bean.search.SiteInfo;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainRPC;
/*     */ import com.cicro.wcm.services.search.SearchForInterface;
import com.cicro.wcm.services.search.index.IndexManager;
/*     */ import com.cicro.wcm.services.search.index.util.IndexUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class SearchForManager
/*     */ {
/*  29 */   private static List<Map> listIndex = new ArrayList();
/*     */ 
/*     */   static {
/*  32 */     setSiteList();
/*     */   }
/*     */ 
/*     */   public static void clearList() {
/*  36 */     listIndex.clear();
/*     */   }
/*     */ 
/*     */   public static boolean initAndCreateIndex()
/*     */   {
/*  41 */     boolean result = IndexManager.initAndCreateIndex(true);
/*  42 */     IndexUtil.writeSiteToFile();
/*  43 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean deleteIndexDir()
/*     */   {
/*  48 */     IndexUtil.clearList();
/*  49 */     return IndexManager.deleteIndexDir();
/*     */   }
/*     */ 
/*     */   public static boolean createIndexBySite(String site_id)
/*     */   {
/*  54 */     boolean result = IndexManager.initAndCreateIndex(false);
/*  55 */     if (result) {
/*  56 */       deleteIndexBySite(site_id);
/*  57 */       IndexUtil.addSiteToFile(site_id);
/*  58 */       return IndexManager.readToIndexBySite(site_id);
/*     */     }
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteIndexBySite(String site_id)
/*     */   {
/*  71 */     IndexUtil.delelteSiteToFile(site_id);
/*  72 */     return IndexManager.deleteIndexBySite(site_id);
/*     */   }
/*     */ 
/*     */   public static List<Map> getSiteList()
/*     */   {
/*     */     try
/*     */     {
/*  81 */       if (listIndex.size() == 0) {
/*  82 */         String searchFile = SearchForInterface.getSearchXmlPath();
/*  83 */         Document document = XmlManager.createDOM(searchFile);
/*  84 */         NodeList nodeList = XmlManager.queryNodeList(document, "search/sites/site");
/*  85 */         for (int i = 0; i < nodeList.getLength(); i++) {
/*  86 */           Node node = nodeList.item(i);
/*  87 */           String site_id = XmlManager.queryNodeValue(node, "site_id");
/*  88 */           String site_name = XmlManager.queryNodeValue(node, "site_name");
/*  89 */           String state = XmlManager.queryNodeValue(node, "state");
/*     */ 
/*  95 */           Map map = new HashMap();
/*  96 */           map.put("site_id", site_id);
/*  97 */           map.put("site_name", site_name);
/*  98 */           map.put("state", state);
/*  99 */           listIndex.add(map);
/*     */         }
/*     */       }
/* 102 */       return listIndex;
/*     */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */     }return listIndex;
/*     */   }
/*     */ 
/*     */   public static List<SiteInfo> getSiteListByMap(Map mapParam)
/*     */   {
/* 115 */     List listIndexResult = new ArrayList();
/*     */     try
/*     */     {
/* 118 */       List siteList = SearchForInterface.getSiteList();
/* 119 */       if (siteList.size() != listIndex.size()) {
/* 120 */         setSiteList();
/*     */       }
/* 122 */       clearList();
/* 123 */       if (listIndex.size() == 0) {
/* 124 */         String searchFile = SearchForInterface.getSearchXmlPath();
/* 125 */         Document document = XmlManager.createDOM(searchFile);
/* 126 */         NodeList nodeList = XmlManager.queryNodeList(document, "search/sites/site");
/* 127 */         for (int i = 0; i < nodeList.getLength(); i++) {
/* 128 */           Node node = nodeList.item(i);
/* 129 */           String site_id = XmlManager.queryNodeValue(node, "site_id");
/* 130 */           String site_name = XmlManager.queryNodeValue(node, "site_name");
/* 131 */           String state = XmlManager.queryNodeValue(node, "state");
/*     */ 
/* 137 */           state = "0";
/* 138 */           Map map = new HashMap();
/* 139 */           map.put("site_id", site_id);
/* 140 */           map.put("site_name", site_name);
/* 141 */           map.put("state", state);
/* 142 */           listIndex.add(map);
/*     */         }
/*     */       }
/*     */ 
/* 146 */       int start_num = mapParam.get("start_num") == null ? 0 : Integer.valueOf(String.valueOf(mapParam.get("start_num"))).intValue();
/* 147 */       int page_size = mapParam.get("page_size") == null ? 15 : Integer.valueOf(String.valueOf(mapParam.get("page_size"))).intValue();
/*     */ 
/* 151 */       for (int i = start_num; (i < start_num + page_size) && (i < listIndex.size()); i++) {
/* 152 */         Map map = (Map)listIndex.get(i);
/* 153 */         System.out.println("map===" + map);
/* 154 */         SiteInfo siteInfo = new SiteInfo();
/* 155 */         siteInfo.setSite_id((String)map.get("site_id"));
/* 156 */         siteInfo.setSite_name((String)map.get("site_name"));
/* 157 */         if (IndexUtil.isHasIndex(siteInfo.getSite_id()))
/* 158 */           siteInfo.setState("1");
/*     */         else {
/* 160 */           siteInfo.setState("0");
/*     */         }
/*     */ 
/* 163 */         siteInfo.setSite_domain(SiteDomainRPC.getSiteDomainBySiteID((String)map.get("site_id")));
/*     */ 
/* 165 */         System.out.println("state===========" + siteInfo.getState());
/* 166 */         System.out.println("Site_name===========" + siteInfo.getSite_name());
/* 167 */         System.out.println("Site_id===========" + siteInfo.getSite_id());
/* 168 */         System.out.println("Site_domain===========" + siteInfo.getSite_domain());
/*     */ 
/* 170 */         listIndexResult.add(siteInfo);
/*     */       }
/*     */ 
/* 173 */       return listIndexResult;
/*     */     } catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */     }return listIndexResult;
/*     */   }
/*     */ 
/*     */   public static int getSiteListByMapCount()
/*     */   {
/*     */     try
/*     */     {
/* 186 */       return listIndex.size();
/*     */     } catch (Exception e) {
/* 188 */       e.printStackTrace();
/* 189 */     }return listIndex.size();
/*     */   }
/*     */ 
/*     */   public static synchronized boolean setSiteList()
/*     */   {
/* 198 */     List indexList = new ArrayList();
/*     */     try {
/* 200 */       String searchFile = SearchForInterface.getSearchXmlPath();
/* 201 */       Document document = XmlManager.createDOM(searchFile);
/* 202 */       NodeList nodeList = XmlManager.queryNodeList(document, "search/sites/site");
/*     */       String state;
/* 203 */       for (int i = 0; i < nodeList.getLength(); i++) {
/* 204 */         Node node = nodeList.item(i);
/* 205 */         String site_id = XmlManager.queryNodeValue(node, "site_id");
/* 206 */         String site_name = XmlManager.queryNodeValue(node, "site_name");
/* 207 */         state = XmlManager.queryNodeValue(node, "state");
/* 208 */         Map map = new HashMap();
/* 209 */         map.put("site_id", site_id);
/* 210 */         map.put("site_name", site_name);
/* 211 */         map.put("state", state);
/* 212 */         indexList.add(map);
/*     */       }
/*     */ 
/* 215 */       List siteList = SearchForInterface.getSiteList();
/* 216 */       System.out.println(siteList + " ==== " + indexList);
/*     */       Object parentnode;
/* 217 */       if (siteList.size() > indexList.size()) {
/* 218 */         String site_id = "";
/* 219 */         boolean result = true;
/* 220 */         for (Map mapSite : siteList) {
/* 221 */           site_id = (String)mapSite.get("site_id");
/* 222 */           String site_idIndex = "";
/* 223 */           result = true;
/* 224 */           for (Map mapIndex : indexList) {
/* 225 */             site_idIndex = (String)mapIndex.get("site_id");
/*     */ 
/* 227 */             if (site_id.equals(site_idIndex)) {
/* 228 */               result = false;
/*     */             }
/*     */           }
/*     */ 
/* 232 */           if (result) {
/* 233 */             String siteName = (String)mapSite.get("site_name");
/* 234 */             parentnode = XmlManager.queryNode(document, "search/sites");
/* 235 */             String siteXml = "<site><site_id>" + site_id + "</site_id><site_name>" + siteName + "</site_name><state>0</state></site>";
/* 236 */             XmlManager.insertNode((Node)parentnode, siteXml);
/* 237 */             FileOperation.writeStringToFile(searchFile, XmlManager.node2String(document), false, "utf-8");
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 243 */       if (siteList.size() < indexList.size()) {
/* 244 */         String site_idIndex = "";
/* 245 */         boolean result = true;
/* 246 */         for (Map mapIndex : indexList) {
/* 247 */           site_idIndex = (String)mapIndex.get("site_id");
/* 248 */           String site_idSite = "";
/* 249 */           result = true;
/* 250 */           for (parentnode = siteList.iterator(); ((Iterator)parentnode).hasNext(); ) { Map mapSite = (Map)((Iterator)parentnode).next();
/* 251 */             site_idSite = (String)mapSite.get("site_id");
/*     */ 
/* 253 */             if (site_idIndex.equals(site_idSite)) {
/* 254 */               result = false;
/*     */             }
/*     */           }
/*     */ 
/* 258 */           if (result) {
/* 259 */             for (int i = 0; i < nodeList.getLength(); i++) {
/* 260 */               Node node = nodeList.item(i);
/* 261 */               String site_id = XmlManager.queryNodeValue(node, "site_id");
/* 262 */               if (site_idIndex.equals(site_id)) {
/* 263 */                 XmlManager.removeNode(node);
/*     */               }
/*     */             }
/* 266 */             FileOperation.writeStringToFile(searchFile, XmlManager.node2String(document), false, "utf-8");
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 272 */       getSiteList();
/*     */ 
/* 274 */       return true;
/*     */     } catch (Exception e) {
/* 276 */       e.printStackTrace();
/* 277 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 284 */     setSiteList();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.SearchForManager
 * JD-Core Version:    0.6.2
 */