/*     */ package com.cicro.wcm.cmsiresource;
/*     */ 
/*     */ import com.cicro.iresource.service.resourceService.dto.PropertyFilterExtDTO;
/*     */ import com.cicro.iresource.service.resourceService.dto.SearchInfoDTO;
/*     */ import com.cicro.iresource.service.resourceService.dto.TreeDTO;
/*     */ import com.cicro.iresource.service.resourceService.rmi.ResourceRmiI;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ 
/*     */ public class IResourceRMIService
/*     */ {
/*  25 */   public static final String urlRmi = JconfigUtilContainer.bashConfig().getProperty("urlRmi", "", "wcm_zyk");
/*  26 */   public static final String urlFile = JconfigUtilContainer.bashConfig().getProperty("urlFile", "", "wcm_zyk");
/*     */ 
/*     */   public static Map getIResourceInfoList(Map map)
/*     */   {
/*  40 */     List mapLsitSearch = new ArrayList();
/*  41 */     Map resultMap = new HashMap();
/*  42 */     List mapList = new ArrayList();
/*  43 */     Context namingContext = null;
/*     */     try {
/*  45 */       String pageNo = getMapString(map, "pageNo");
/*  46 */       String pageSize = getMapString(map, "pageSize");
/*  47 */       String orderBy = getMapString(map, "orderBy");
/*  48 */       String orderByDir = getMapString(map, "orderByDir");
/*  49 */       String desFields = getMapString(map, "desFields");
/*  50 */       String treeId = getMapString(map, "treeId");
/*  51 */       String decEnName = getMapString(map, "decEnName");
/*  52 */       String fields = getMapString(map, "fields");
/*  53 */       String keys = getMapString(map, "keys");
/*     */ 
/*  55 */       String dept_id = getMapString(map, "dept_id");
/*  56 */       String deptTreePath = getMapString(map, "deptTreePath");
/*     */ 
/*  58 */       namingContext = new InitialContext();
/*  59 */       System.out.println("urlRmi : " + urlRmi);
/*  60 */       ResourceRmiI rmi = (ResourceRmiI)namingContext.lookup(urlRmi);
/*     */ 
/*  62 */       SearchInfoDTO searchInfoDTO = new SearchInfoDTO();
/*  63 */       if (!"".equals(treeId)) {
/*  64 */         searchInfoDTO.setTreeId(treeId);
/*     */       }
/*  66 */       if (!"".equals(decEnName)) {
/*  67 */         searchInfoDTO.setDecEnName(decEnName);
/*     */       }
/*  69 */       if (!"".equals(pageNo)) {
/*  70 */         searchInfoDTO.setPageNo(Integer.parseInt(pageNo));
/*     */       }
/*  72 */       if (!"".equals(pageSize)) {
/*  73 */         searchInfoDTO.setPageSize(Integer.parseInt(pageSize));
/*     */       }
/*  75 */       if (!"".equals(orderBy)) {
/*  76 */         searchInfoDTO.setOrderBy(orderBy);
/*     */       }
/*  78 */       if (!"".equals(orderByDir)) {
/*  79 */         searchInfoDTO.setOrderByDir(orderByDir);
/*     */       }
/*  81 */       List propertyFilterExts = new ArrayList();
/*  82 */       if ((!"".equals(fields)) && (!"".equals(keys))) {
/*  83 */         String[] strs = fields.split(",");
/*  84 */         String str = "LIKES_";
/*  85 */         for (String s : strs) {
/*  86 */           if ((s != null) && (!"".equals(s))) {
/*  87 */             str = str + "dec" + s + "_OR_";
/*     */           }
/*     */         }
/*     */ 
/*  91 */         if ((str.length() > 5) && (str.endsWith("_OR_"))) {
/*  92 */           str = str.substring(0, str.length() - 4);
/*     */         }
/*  94 */         System.out.println(str);
/*  95 */         System.out.println(keys);
/*  96 */         PropertyFilterExtDTO ext = new PropertyFilterExtDTO(str, keys);
/*  97 */         propertyFilterExts.add(ext);
/*     */ 
/*  99 */         Object mapSearch = new HashMap();
/* 100 */         ((Map)mapSearch).put("filterName", str);
/* 101 */         ((Map)mapSearch).put("value", keys);
/* 102 */         mapLsitSearch.add(mapSearch);
/*     */       }
/*     */ 
/* 105 */       if (!"".equals(dept_id)) {
/* 106 */         String str = "EQS_deptId";
/* 107 */         PropertyFilterExtDTO ext = new PropertyFilterExtDTO(str, dept_id);
/* 108 */         propertyFilterExts.add(ext);
/*     */ 
/* 110 */         Map mapSearch = new HashMap();
/* 111 */         mapSearch.put("filterName", str);
/* 112 */         mapSearch.put("value", dept_id);
/* 113 */         mapLsitSearch.add(mapSearch);
/*     */       }
/* 115 */       if (!"".equals(deptTreePath)) {
/* 116 */         String str = "LIKES_deptTreePath";
/* 117 */         PropertyFilterExtDTO ext = new PropertyFilterExtDTO(str, deptTreePath);
/* 118 */         propertyFilterExts.add(ext);
/*     */ 
/* 120 */         Map mapSearch = new HashMap();
/* 121 */         mapSearch.put("filterName", str);
/* 122 */         mapSearch.put("value", deptTreePath);
/* 123 */         mapLsitSearch.add(mapSearch);
/*     */       }
/*     */ 
/* 126 */       searchInfoDTO.setDeEnNames(desFields);
/*     */ 
/* 131 */       String searchInfo = JSONObject.fromObject(searchInfoDTO).toString();
/* 132 */       String sbE = JSONArray.fromObject(mapLsitSearch).toString();
/* 133 */       System.out.println(searchInfo);
/* 134 */       System.out.println(sbE);
/* 135 */       String info = rmi.findCustomformDataByJsonFilter(searchInfo, sbE);
/*     */ 
/* 138 */       JSONObject joAll = JSONObject.fromObject(info);
/* 139 */       Iterator itAll = joAll.keys();
/* 140 */       Object mapAll = new HashMap();
/* 141 */       while (itAll.hasNext()) {
/* 142 */         String key = itAll.next().toString();
/* 143 */         String value = joAll.getString(key);
/* 144 */         ((Map)mapAll).put(key, value);
/*     */       }
/*     */ 
/* 147 */       String totalItems = (String)((Map)mapAll).get("totalItems");
/*     */ 
/* 149 */       String item = (String)((Map)mapAll).get("item");
/*     */ 
/* 151 */       JSONArray joitem = JSONArray.fromObject(item);
/* 152 */       Iterator ititem = joitem.iterator();
/*     */ 
/* 155 */       while (ititem.hasNext()) {
/* 156 */         JSONObject jsonObject = (JSONObject)ititem.next();
/* 157 */         Iterator itField = jsonObject.keys();
/* 158 */         Map mapField = new HashMap();
/* 159 */         while (itField.hasNext()) {
/* 160 */           String key = itField.next().toString();
/* 161 */           String value = jsonObject.getString(key);
/* 162 */           if (key.equals("file"))
/*     */           {
/* 164 */             String jsFile = value;
/* 165 */             JSONObject joFile = JSONObject.fromObject(jsFile);
/* 166 */             Iterator itFile = joFile.keys();
/* 167 */             List listMapFiles = new ArrayList();
/*     */             String jsonFile;
/*     */             Iterator itjsonFile;
/* 168 */             for (; itFile.hasNext(); 
/* 173 */               itjsonFile.hasNext())
/*     */             {
/* 169 */               String key2 = itFile.next().toString();
/* 170 */               jsonFile = joFile.getString(key2);
/* 171 */               JSONArray jsonFileAy = JSONArray.fromObject(jsonFile);
/* 172 */               itjsonFile = jsonFileAy.iterator();
/* 173 */               continue;
/* 174 */               JSONObject jsonObject2 = (JSONObject)itjsonFile.next();
/* 175 */               Iterator itField2 = jsonObject2.keys();
/* 176 */               Map mapField2 = new HashMap();
/* 177 */               while (itField2.hasNext()) {
/* 178 */                 String key_1 = itField2.next().toString();
/* 179 */                 String value_1 = jsonObject2.getString(key_1);
/* 180 */                 mapField2.put(key_1, value_1);
/*     */               }
/* 182 */               listMapFiles.add(mapField2);
/*     */             }
/*     */ 
/* 187 */             for (Map mapField2 : listMapFiles)
/* 188 */               if (mapField2.containsKey("fileSufix")) {
/* 189 */                 String fileSufix = (String)mapField2.get("fileSufix");
/*     */ 
/* 191 */                 String id = (String)mapField2.get("id");
/* 192 */                 if (fileSufix.equals("epub"))
/* 193 */                   mapField.put("epubFile", urlFile + id);
/* 194 */                 else if ((fileSufix.equals("jpg")) || (fileSufix.equals("JPG")) || (fileSufix.equals("JPEG")))
/* 195 */                   mapField.put("jpgFile", urlFile + id);
/* 196 */                 else if ((fileSufix.equals("pdf")) || (fileSufix.equals("PDF")))
/* 197 */                   mapField.put("pdfFile", urlFile + id);
/* 198 */                 else if ((fileSufix.equals("flv")) || (fileSufix.equals("FLV")))
/* 199 */                   mapField.put("flvFile", urlFile + id);
/*     */               }
/* 201 */               else if (mapField2.containsKey("fileName")) {
/* 202 */                 String fileName = (String)mapField2.get("fileName");
/* 203 */                 String id = (String)mapField2.get("id");
/* 204 */                 if (fileName.endsWith(".epub"))
/* 205 */                   mapField.put("epubFile", urlFile + id);
/* 206 */                 else if ((fileName.endsWith(".jpg")) || (fileName.endsWith(".JPG")) || (fileName.endsWith(".JPEG")))
/* 207 */                   mapField.put("jpgFile", urlFile + id);
/* 208 */                 else if ((fileName.endsWith(".pdf")) || (fileName.endsWith(".PDF")))
/* 209 */                   mapField.put("pdfFile", urlFile + id);
/* 210 */                 else if ((fileName.endsWith(".flv")) || (fileName.endsWith(".FLV")))
/* 211 */                   mapField.put("flvFile", urlFile + id);
/* 212 */                 else if ((fileName.endsWith(".flv")) || (fileName.endsWith(".FLV")))
/* 213 */                   mapField.put("flvFile", urlFile + id);
/*     */               }
/*     */           }
/*     */           else
/*     */           {
/* 218 */             mapField.put(key, value);
/*     */           }
/*     */         }
/*     */ 
/* 222 */         mapList.add(mapField);
/*     */       }
/*     */ 
/* 225 */       if (mapList == null) {
/* 226 */         mapList = new ArrayList();
/*     */       }
/* 228 */       resultMap.put("list", mapList);
/*     */ 
/* 231 */       if (totalItems == null) {
/* 232 */         totalItems = "";
/*     */       }
/* 234 */       resultMap.put("count", totalItems);
/*     */ 
/* 237 */       if (pageNo == null) {
/* 238 */         pageNo = "0";
/*     */       }
/* 240 */       resultMap.put("cur_page", pageNo);
/*     */ 
/* 243 */       int page_size = Integer.parseInt(pageSize);
/* 244 */       int page_count = Integer.parseInt(totalItems) / page_size + 1;
/* 245 */       if ((page_count == "null") || (page_count == null)) {
/* 246 */         page_count = 0;
/*     */       }
/* 248 */       if (Integer.parseInt(totalItems) == 0) {
/* 249 */         page_count = 0;
/*     */       }
/* 251 */       resultMap.put("page_count", page_count);
/*     */ 
/* 254 */       int prev_num = 1;
/* 255 */       if (Integer.parseInt(pageNo) > 1) {
/* 256 */         prev_num = Integer.parseInt(pageNo) - 1;
/*     */       }
/*     */ 
/* 259 */       resultMap.put("prev_num", prev_num);
/*     */ 
/* 261 */       int next_num = page_count;
/*     */ 
/* 263 */       if (Integer.parseInt(pageNo) < page_count) {
/* 264 */         next_num = Integer.parseInt(pageNo) + 1;
/*     */       }
/*     */ 
/* 267 */       if (next_num == 0) {
/* 268 */         next_num = 1;
/*     */       }
/* 270 */       resultMap.put("next_num", next_num);
/*     */ 
/* 273 */       resultMap.put("page_count", page_count);
/* 274 */       return resultMap;
/*     */     } catch (Exception e) {
/* 276 */       e.printStackTrace();
/* 277 */     }return resultMap;
/*     */   }
/*     */ 
/*     */   public static String getMapString(Map map, String key)
/*     */   {
/* 284 */     String value = "";
/*     */     try {
/* 286 */       value = (String)map.get(key);
/* 287 */       if (value == null);
/* 288 */       return "";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 293 */       e.printStackTrace();
/* 294 */     }return value;
/*     */   }
/*     */ 
/*     */   public static List<TreeDTO> findTreeByPIdAndType(String treeId)
/*     */   {
/* 301 */     List list = new ArrayList();
/* 302 */     Context namingContext = null;
/*     */     try
/*     */     {
/* 305 */       namingContext = new InitialContext();
/* 306 */       System.out.println("urlRmi : " + urlRmi);
/* 307 */       ResourceRmiI rmi = (ResourceRmiI)namingContext.lookup(urlRmi);
/* 308 */       return rmi.findTreeByPIdAndType(treeId, 1, null);
/*     */     }
/*     */     catch (Exception e) {
/* 311 */       e.printStackTrace();
/* 312 */     }return list;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> totalByGroupTreeId(String treeId)
/*     */   {
/* 318 */     Map map = new HashMap();
/* 319 */     Context namingContext = null;
/*     */     try {
/* 321 */       SearchInfoDTO searchInfoDTO = new SearchInfoDTO();
/* 322 */       List propertyFilterExts = new ArrayList();
/* 323 */       searchInfoDTO.setFilters(propertyFilterExts);
/* 324 */       namingContext = new InitialContext();
/* 325 */       System.out.println("urlRmi : " + urlRmi);
/* 326 */       ResourceRmiI rmi = (ResourceRmiI)namingContext.lookup(urlRmi);
/* 327 */       return rmi.totalByGroupTreeId(treeId, searchInfoDTO);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 331 */       e.printStackTrace();
/* 332 */     }return map;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.cmsiresource.IResourceRMIService
 * JD-Core Version:    0.6.2
 */