<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cicro.project.dz_szb.SzbData" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.cicro.project.dz_szb.SzbBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.cicro.wcm.bean.cms.info.ArticleBean" %>
<%@ page import="com.cicro.wcm.services.cms.info.article.ArticleManager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String action_type = request.getParameter("action_type");
    String result = "";
    if(action_type.equals("list")){
        result = getSzbList(request);
    }
    if(action_type.equals("page")){
        result = getSzb(request);
    }
    if(action_type.equals("article")){
        result = getArticle(request);
    }
    String callback = request.getParameter("callback");
    if(callback != null && !"".equals(callback)){
        out.println(callback + "(" + result + ")");
    }else{
        out.println(result);
    }
%>
<%!

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getSzbList(HttpServletRequest request)
    {
        SzbData szbData = new SzbData();
        String json = "";
        String pageSize = request.getParameter("size");
        if(StringUtils.isEmpty(pageSize)){
            pageSize = "10";
        }
        int total = szbData.getSzbCount();
        int pageCount = (total + Integer.parseInt(pageSize)- 1) / Integer.parseInt(pageSize);

        String pageNum = request.getParameter("page");
        if(StringUtils.isEmpty(pageNum)){
            pageNum = "1";
        }
        int currentPageInt = Integer.valueOf(pageNum);
        if (currentPageInt > pageCount) {
            currentPageInt = 1;
        }
        List<SzbBean> szbList = szbData.getSzbList(currentPageInt, Integer.parseInt(pageSize));
        if(szbList != null && szbList.size() > 0)
        {
            json+="{\"total\":\""+total+"\",\"pageCount\":\""+pageCount+"\",\"currPage\":\""+currentPageInt+"\",\"list\":[";
            for(SzbBean szb : szbList)
            {
                json += "{\"id\":\""+szb.getId()+"\",\"create_time\":\""+df.format(szb.getCreateTime())+"\",\"title\":\""+szb.getTitle()+"\",\"json_data\":"+szb.getJsonData()+",\"pub_date\":\""+df.format(szb.getPubDate())+"\"},";
            }
            json = json.substring(0,json.length()-1);
            json += "]}";
        }
        return "["+json+"]";
    }

    public String getSzb(HttpServletRequest request)
    {
        SzbData szbData = new SzbData();
        String json = "";
        //显示期
        String id = request.getParameter("id");
        //显示版面
        String i = request.getParameter("i");
        int show = 0;
        if (StringUtils.isNotBlank(i)) {
            show = Integer.parseInt(i);
        }
        SzbBean szbBean = null;
        if (StringUtils.isBlank(id)) {
            // 最新一期的 id
            szbBean = szbData.getNewestSzb();
            if (szbBean != null) {
                id = String.valueOf(szbBean.getId());
            }
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("id", id);
            szbBean = szbData.getSzb(params);
        }
        if (szbBean != null) {
            json+="{\"id\":\""+szbBean.getId()+"\",\"show\":\""+show+"\",\"create_time\":\""+df.format(szbBean.getCreateTime())+"\",\"title\":\""+szbBean.getTitle()+"\",\"json_data\":"+szbBean.getJsonData()+",\"pub_date\":\""+df.format(szbBean.getPubDate())+"\"}";
        }
        return "["+json+"]";
    }

    public String getArticle(HttpServletRequest request)
    {
        SzbData szbData = new SzbData();
        String json = "";
        //显示期
        String id = request.getParameter("id");
        //显示版面
        String i = request.getParameter("i");
        //第几篇文章
        String j = request.getParameter("j");

        int show = 0;
        if (StringUtils.isNotBlank(i)) {
            show = Integer.parseInt(i);
        }
        SzbBean szbBean = null;
        if (StringUtils.isBlank(id)) {
            // 最新一期的 id
            szbBean = szbData.getNewestSzb();
            if (szbBean != null) {
                id = String.valueOf(szbBean.getId());
            }
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("id", id);
            szbBean = szbData.getSzb(params);
        }
        if (szbBean != null) {
            //查询文章信息
            String a = request.getParameter("a");
            ArticleBean article = ArticleManager.getArticle(a, null);
            json+="{\"szb\":[{\"id\":\""+szbBean.getId()+"\",\"show\":\""+show+"\",\"create_time\":\""+df.format(szbBean.getCreateTime())+"\",\"title\":\""+szbBean.getTitle()+"\",\"json_data\":"+szbBean.getJsonData()+",\"pub_date\":\""+df.format(szbBean.getPubDate())+"\"}],\"article\":[{\"title\":\""+article.getTitle()+"\",\"releaseddtime\":\""+article.getReleased_dtime()+"\",\"source\":\""+article.getSource()+"\",\"content\":\""+replaceStr(article.getInfo_content())+"\"}]}";
        }
        return "["+json+"]";
    }
    public static String replaceStr(String str)
    {
        return str.replaceAll("\"","'").replaceAll("\r|\n|\r\n","").replaceAll("<p\\+.*?[^>]>|</p\\+.*?[^>]>", "<p>");
    }
%>