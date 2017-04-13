/*    */ package com.cicro.wcm.services.search_bak_20151106.search;
/*    */ 
/*    */ import com.cicro.wcm.bean.search.PageControl;
/*    */ import com.cicro.wcm.bean.search.ResultBean;
/*    */ import com.cicro.wcm.bean.search.SearchResult;
/*    */ import com.cicro.wcm.services.search.search.SearchInfoManager;
import com.cicro.wcm.services.search.search.util.SearchUtil;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class SearchManager
/*    */ {
/*    */   public static SearchResult search(HttpServletRequest request)
/*    */   {
/* 22 */     String query = SearchUtil.getXmlParam(request);
/* 23 */     return search(query);
/*    */   }
/*    */ 
/*    */   public static SearchResult search(String query)
/*    */   {
/* 33 */     Map map = SearchUtil.initPraToMap(query);
/* 34 */     return SearchInfoManager.search(map);
/*    */   }
/*    */ 
/*    */   public static SearchResult searchGJ(HttpServletRequest request)
/*    */   {
/* 43 */     String query = SearchUtil.getXmlParam(request);
/* 44 */     return searchGJ(query);
/*    */   }
/*    */ 
/*    */   public static SearchResult searchGJ(String query)
/*    */   {
/* 53 */     Map map = SearchUtil.initPraToMap(query);
/* 54 */     return SearchInfoManager.searchGJ(map);
/*    */   }
/*    */ 
/*    */   public static void main(String[] arr) {
/* 58 */     Map map = new HashMap();
/* 59 */     map.put("q", "信息");
/* 60 */     map.put("p", "1");
/* 61 */     SearchResult result = SearchInfoManager.search(map);
/* 62 */     if (result == null) {
/* 63 */       System.out.println("没有符合条件的记录");
/* 64 */       return;
/*    */     }
/* 66 */     String key = result.getKey();
/* 67 */     String time = result.getTime();
/* 68 */     PageControl pageControl = result.getPageControl();
/* 69 */     List items = result.getItems();
/*    */ 
/* 71 */     System.out.println("关键字:" + key);
/* 72 */     System.out.println("搜索所用时间:" + time);
/* 73 */     System.out.println("总条数:" + pageControl.getMaxRowCount());
/* 74 */     System.out.println("总页数:" + pageControl.getMaxPage());
/* 75 */     System.out.println("当前页数:" + pageControl.getCurPage());
/* 76 */     for (ResultBean bean : items) {
/* 77 */       System.out.println();
/* 78 */       System.out.println(bean.getId());
/* 79 */       System.out.println(bean.getTitle());
/* 80 */       System.out.println(bean.getContent());
/* 81 */       System.out.println(bean.getUrl());
/* 82 */       System.out.println(bean.getTime());
/* 83 */       System.out.println();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.search.SearchManager
 * JD-Core Version:    0.6.2
 */