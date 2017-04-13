/*     */ package com.cicro.wcm.services.zwgk.export;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.export.ExportDept;
/*     */ import com.cicro.wcm.bean.zwgk.export.ExportInfo;
/*     */ import com.cicro.wcm.dao.zwgk.export.ExportInfoDao;
/*     */ import com.cicro.wcm.services.cms.category.CategoryRPC;
/*     */ import com.cicro.wcm.services.zwgk.appcatalog.RegulationManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ 
/*     */ public class ExportInfoService
/*     */ {
/*     */   public static List<ExportDept> exportZwgkInfoByNodeCate(Map map)
/*     */   {
/*  33 */     List<ExportDept> listResult = new ArrayList();
/*     */     try {
/*  35 */       System.out.println(map);
/*  36 */       String node_id = (String)map.get("node_id");
/*  37 */       String cat_ids = (String)map.get("cat_ids");
/*  38 */       List fileSuffixList = Arrays.asList(cat_ids.split(","));
/*  39 */       String start_day = (String)map.get("start_day");
/*  40 */       String end_day = (String)map.get("end_day");
/*  41 */       String titlename = (String)map.get("titlename");
/*  42 */       String extype = (String)map.get("extype");
/*     */ 
/*  45 */       String treeStr = "";
/*  46 */       String infoType = (String)map.get("infoType");
/*  47 */       if (infoType.equals("zwgk"))
/*  48 */         treeStr = CategoryRPC.getCategoryTreeBySiteID(node_id);
/*     */       else {
/*  50 */         treeStr = CategoryRPC.getCategoryTreeByClassID(Integer.valueOf(infoType).intValue());
/*     */       }
/*     */ 
/*  54 */       JSONArray jsonArrayAll = JSONArray.fromObject(treeStr);
/*  55 */       Iterator itAll = jsonArrayAll.iterator();
/*  56 */       JSONObject jsonObjectAll = (JSONObject)itAll.next();
/*  57 */       JSONArray jsonArray = JSONArray.fromObject(jsonObjectAll.get("children"));
/*  58 */       Iterator it = jsonArray.iterator();
/*     */ 
/*  60 */       while (it.hasNext()) {
/*  61 */         JSONObject jsonObject = (JSONObject)it.next();
/*  62 */         ExportDept localExportDept1 = doOutTreeBean(jsonObject, listResult, fileSuffixList, map);
/*     */       }
/*     */ 
/*  65 */       Map mapResult = new HashMap();
/*  66 */       List<ExportDept> listResult2 = new ArrayList();
/*  67 */       for (ExportDept exportDept : listResult) {
/*  68 */         mapResult.put(exportDept.getCateId(), exportDept);
/*     */       }
/*     */ 
/*  71 */       Iterator it2 = jsonArray.iterator();
/*     */ 
/*  73 */       while (it2.hasNext()) {
/*  74 */         JSONObject jsonObject = (JSONObject)it2.next();
/*  75 */         ExportDept localExportDept2 = doOutTreeBean2(jsonObject, (List)listResult2, mapResult);
/*     */       }
/*     */ 
/*  78 */       return listResult2;
/*     */     }
/*     */     catch (Exception e) {
/*  81 */       e.printStackTrace();
/*  82 */     }return listResult;
/*     */   }
/*     */ 
/*     */   public static ExportDept doOutTreeBean(JSONObject jsonObject, List<ExportDept> listResult, List<String> fileSuffixList, Map map)
/*     */   {
/*  89 */     ExportDept exportDept = new ExportDept();
/*     */     try {
/*  91 */       int cat_id = Integer.valueOf(String.valueOf(jsonObject.get("id"))).intValue();
/*  92 */       exportDept.setCateId(String.valueOf(cat_id));
/*  93 */       if (fileSuffixList.contains(String.valueOf(cat_id))) {
/*  94 */         JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("children"));
/*  95 */         if ((!jsonArray.toString().equals("[null]")) && (jsonArray != null) && (!"".equals(jsonArray)) && (jsonArray.size() > 0)) {
/*  96 */           exportDept.setCatName(String.valueOf(jsonObject.get("text")));
/*  97 */           int inputCount = 0;
/*  98 */           Iterator it = jsonArray.iterator();
/*  99 */           while (it.hasNext()) {
/* 100 */             Object object = it.next();
/* 101 */             if (object != null)
/*     */             {
/* 103 */               JSONObject jsonObject2 = (JSONObject)object;
/* 104 */               if (jsonObject2 != null) {
/* 105 */                 ExportDept ex = doOutTreeBean(jsonObject2, listResult, fileSuffixList, map);
/* 106 */                 inputCount += ex.getCountInfo();
/*     */               }
/*     */             }
/*     */           }
/* 110 */           exportDept.setCountInfo(inputCount);
/*     */         }
/*     */         else {
/* 113 */           exportDept.setCatName(String.valueOf(jsonObject.get("text")));
/* 114 */           map.put("cat_id", Integer.valueOf(cat_id));
/*     */ 
/* 117 */           String extype = (String)map.get("extype");
/* 118 */           List exportInfo = new ArrayList();
/* 119 */           String infoType = (String)map.get("infoType");
/*     */ 
/* 122 */           if (infoType.equals("zwgk")) {
/* 123 */             if (extype.equals("1"))
/* 124 */               exportInfo = getGkinfoByNodeAndCat(map);
/*     */             else {
/* 126 */               exportInfo = getGkinfoCardByNodeAndCat(map);
/*     */             }
/*     */           }
/* 129 */           else if (extype.equals("1"))
/*     */           {
/* 131 */             exportInfo = getGkGXinfoByNodeAndCat(map);
/*     */           }
/* 133 */           else exportInfo = getGkGXinfoByNodeAndCat(map);
/*     */ 
/* 137 */           exportDept.setCountInfo(exportInfo.size());
/* 138 */           exportDept.setExportInfo(exportInfo);
/*     */         }
/*     */ 
/* 141 */         listResult.add(exportDept);
/*     */       }
/*     */ 
/* 144 */       return exportDept;
/*     */     } catch (Exception e) {
/* 146 */       e.printStackTrace();
/* 147 */     }return exportDept;
/*     */   }
/*     */ 
/*     */   public static ExportDept doOutTreeBean2(JSONObject jsonObject, List<ExportDept> listResult2, Map<String, ExportDept> mapResult)
/*     */   {
/* 153 */     ExportDept exportDept = new ExportDept();
/*     */     try {
/* 155 */       String cat_id = String.valueOf(jsonObject.get("id"));
/* 156 */       if (mapResult.containsKey(cat_id)) {
/* 157 */         JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("children"));
/* 158 */         if ((!jsonArray.toString().equals("[null]")) && (jsonArray != null) && (!"".equals(jsonArray)) && (jsonArray.size() > 0)) {
/* 159 */           listResult2.add((ExportDept)mapResult.get(cat_id));
/* 160 */           Iterator it = jsonArray.iterator();
/* 161 */           while (it.hasNext()) {
/* 162 */             Object object = it.next();
/* 163 */             if (object != null) {
/* 164 */               JSONObject jsonObject2 = (JSONObject)object;
/* 165 */               if (jsonObject2 != null) {
							ExportDept doOutTreeBean2 = doOutTreeBean2(jsonObject2, listResult2, mapResult);
}
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 171 */           listResult2.add((ExportDept)mapResult.get(cat_id));
/*     */         }
/*     */       }
/* 174 */       return exportDept;
/*     */     } catch (Exception e) {
/* 176 */       e.printStackTrace();
/* 177 */     }return exportDept;
/*     */   }
/*     */ 
/*     */   public static List<ExportInfo> getGkinfoByNodeAndCat(Map map)
/*     */   {
/* 183 */     return ExportInfoDao.getGkinfoByNodeAndCat(map);
/*     */   }
/*     */ 
/*     */   public static List<ExportInfo> getGkinfoCardByNodeAndCat(Map map)
/*     */   {
/* 188 */     return ExportInfoDao.getGkinfoCardByNodeAndCat(map);
/*     */   }
/*     */ 
/*     */   public static List<ExportInfo> getGkGXinfoByNodeAndCat(Map map)
/*     */   {
/* 193 */     String node_id = (String)map.get("node_id");
/* 194 */     String cat_id = String.valueOf(map.get("cat_id"));
/* 195 */     String sql = RegulationManager.spellConSql(cat_id, node_id, 0);
/* 196 */     map.put("catSql", sql);
/*     */ 
/* 198 */     return ExportInfoDao.getGkGXinfoByNodeAndCat(map);
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 206 */     String treeStr = "[{\"id\":0,\"text\":\"人民政府\",\"attributes\":{\"url\":\"\",\"handl\":\"\"},\"children\":[{\"id\":1834,\"text\":\"概况信息\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1834\",\"handl\":\"\"},\"state\":'closed',\"children\":[{\"id\":1835,\"text\":\"机构职能\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1835\",\"handl\":\"\"}},{\"id\":1836,\"text\":\"领导信息\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1836\",\"handl\":\"\"}},{\"id\":1837,\"text\":\"内设科室\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1837\",\"handl\":\"\"}},{\"id\":1838,\"text\":\"地区概况\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1838\",\"handl\":\"\"}}]},{\"id\":1839,\"text\":\"法规公文\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1839\",\"handl\":\"\"},\"state\":'closed',\"children\":[{\"id\":1840,\"text\":\"法规\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1840\",\"handl\":\"\"}},{\"id\":1841,\"text\":\"法规\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1841\",\"handl\":\"\"}},{\"id\":1842,\"text\":\"公文\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=1842\",\"handl\":\"\"}}]},{\"id\":2039,\"text\":\"推送测试\",\"attributes\":{\"url\":\"/manager/cms/info/article/articleDataList.jsp?app_id=zwgk&cat_id=2039\",\"handl\":\"\"}}]}]";
/* 207 */     String cat_ids = "1834,1835,1836,1837,1839,1841";
/*     */ 
/* 209 */     Map map = new HashMap();
/* 210 */     map.put("extype", treeStr);
/* 211 */     map.put("cat_ids", cat_ids);
/* 212 */     exportZwgkInfoByNodeCate(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.export.ExportInfoService
 * JD-Core Version:    0.6.2
 */