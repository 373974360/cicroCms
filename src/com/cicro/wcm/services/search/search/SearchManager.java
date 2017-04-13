//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.search.search;

import com.cicro.wcm.bean.search.PageControl;
import com.cicro.wcm.bean.search.ResultBean;
import com.cicro.wcm.bean.search.SearchResult;
import com.cicro.wcm.services.search.search.SearchInfoManager;
import com.cicro.wcm.services.search.search.util.SearchUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class SearchManager {
    public SearchManager() {
    }

    public static SearchResult search(HttpServletRequest request) {
        String query = SearchUtil.getXmlParam(request);
        return search(query);
    }

    public static SearchResult search(String query) {
        Map map = SearchUtil.initPraToMap(query);
        return SearchInfoManager.search(map);
    }

    public static SearchResult searchGJ(HttpServletRequest request) {
        String query = SearchUtil.getXmlParam(request);
        return searchGJ(query);
    }

    public static SearchResult searchGJ(String query) {
        Map map = SearchUtil.initPraToMap(query);
        return SearchInfoManager.searchGJ(map);
    }

    public static void main(String[] arr) {
        HashMap map = new HashMap();
        map.put("q", "信息");
        map.put("p", "1");
        SearchResult result = SearchInfoManager.search(map);
        if(result == null) {
            System.out.println("没有符合条件的记录");
        } else {
            String key = result.getKey();
            String time = result.getTime();
            PageControl pageControl = result.getPageControl();
            List items = result.getItems();
            System.out.println("关键字:" + key);
            System.out.println("搜索所用时间:" + time);
            System.out.println("总条数:" + pageControl.getMaxRowCount());
            System.out.println("总页数:" + pageControl.getMaxPage());
            System.out.println("当前页数:" + pageControl.getCurPage());
            Iterator var8 = items.iterator();

            while(var8.hasNext()) {
                ResultBean bean = (ResultBean)var8.next();
                System.out.println();
                System.out.println(bean.getId());
                System.out.println(bean.getTitle());
                System.out.println(bean.getContent());
                System.out.println(bean.getUrl());
                System.out.println(bean.getTime());
                System.out.println();
            }

        }
    }
}
