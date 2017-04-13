/*     */ package com.cicro.wcm.cmsiresource;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.model.services.WcmZykInfoService;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class IResourcInfoManager
/*     */ {
/*     */   public static Map getIResourceInfoList(Map map)
/*     */   {
/*  42 */     Map mapResult = new HashMap();
/*     */     try {
/*  44 */       return IResourceRMIService.getIResourceInfoList(map);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  48 */       e.printStackTrace();
/*  49 */     }return mapResult;
/*     */   }
/*     */ 
/*     */   public static Map getIResourceInfoListAndUrl(Map map)
/*     */   {
/*  67 */     Map mapResult = new HashMap();
/*     */     try {
/*  69 */       String site_id = (String)map.get("site_id");
/*  70 */       mapResult = IResourceRMIService.getIResourceInfoList(map);
/*  71 */       List listMap = (List)map.get("list");
/*  72 */       for (Map map2 : listMap) {
/*  73 */         String i_id = (String)map2.get("id");
/*  74 */         System.out.println("i_id = " + i_id);
/*  75 */         String info_id = WcmZykInfoService.getWcminfo_zykinfoById(i_id, site_id);
/*  76 */         InfoBean infoBean = InfoBaseManager.getInfoById(info_id);
/*  77 */         String url = infoBean.getContent_url();
/*  78 */         map2.put("content_url", url);
/*     */       }
/*  80 */       return mapResult;
/*     */     } catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */     }return mapResult;
/*     */   }
/*     */ 
/*     */   public static int getCountByDeptTree(String dept_tree)
/*     */   {
/*  90 */     int count = 0;
/*     */     try {
/*  92 */       Map m = new HashMap();
/*  93 */       m.put("pageNo", "1");
/*  94 */       m.put("pageSize", "1");
/*     */ 
/*  99 */       m.put("desFields", "id");
/* 100 */       m.put("deptTreePath", dept_tree);
/* 101 */       Map map = getIResourceInfoList(m);
/* 102 */       String countstr = (String)map.get("count");
/* 103 */       if ((countstr != null) && (!"".equals(countstr)))
/*     */       {
/* 106 */         count = Integer.parseInt(countstr);
/*     */       }
/*     */     } catch (Exception e) { e.printStackTrace();
/*     */     } finally {
/* 110 */       return count;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 120 */     Map m = new HashMap();
/* 121 */     m.put("pageNo", "1");
/* 122 */     m.put("pageSize", "9");
/*     */ 
/* 124 */     m.put("desFields", "mc");
/*     */ 
/* 127 */     m.put("deptTreePath", "#1#");
/*     */ 
/* 131 */     m.put("decEnName", "csfj");
/*     */ 
/* 136 */     m.put("treeId", "40288c9a3a908ae9013a90909a8d002c");
/* 137 */     System.out.println("m======" + m);
/* 138 */     Map map = getIResourceInfoList(m);
/*     */ 
/* 142 */     List listMap = (List)map.get("list");
/* 143 */     System.out.println("listMap======" + listMap.size());
/* 144 */     System.out.println("总条数" + map.get("count"));
/* 145 */     System.out.println("当前第几页" + map.get("cur_page"));
/* 146 */     System.out.println("共多少页" + map.get("page_count"));
/* 147 */     System.out.println("上一页" + map.get("prev_num"));
/* 148 */     System.out.println("下一页" + map.get("next_num"));
/* 149 */     System.out.println("末页" + map.get("page_count"));
/* 150 */     for (Map map3 : listMap)
/* 151 */       System.out.println(map3);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.cmsiresource.IResourcInfoManager
 * JD-Core Version:    0.6.2
 */