<%@ page import="com.cicro.wcm.db.DBManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%
    String url = "jdbc:mysql://10.120.66.66:3306/wcm?useUnicode=true&characterEncoding=utf8";
    String user = "wcmuser";
    String password = "wcmpassword";
    Connection connection = DriverManager.getConnection(url, user, password);
    Statement st = connection.createStatement();
    String contentSql = "select a.info_content from cs_info_article a right join cs_info b on a.info_id=b.id where b.site_id = 'CMSnyj' and a.info_content like '%<img%' limit 0,1000";
    ResultSet rs = st.executeQuery(contentSql);
    while (rs.next()) {
        String info_content = rs.getString("info_content");
        String regex = "<img.*?>";
        Pattern pt = Pattern.compile(regex);
        Matcher mt = pt.matcher(info_content);
        while (mt.find()) {
            String s3 = "src=\".*?\"";
            String s4 = "src='.*?'";
            Pattern pt3 = Pattern.compile(s3);
            Matcher mt3 = pt3.matcher(mt.group());
            while (mt3.find()) {
                String img = mt3.group().replaceAll("src=", "").replaceAll("\"", "");
                if (img.indexOf("wcm.files") < 0) {
                    if(img.indexOf("http://") >= 0){
                        out.println(img);
                    }else{
                        out.println("http://nyj.yanan.gov.cn" + img);
                    }
                }
            }
            Pattern pt4 = Pattern.compile(s4);
            Matcher mt4 = pt4.matcher(mt.group());
            while (mt4.find()) {
                String img = mt4.group().replaceAll("src=", "").replaceAll("\"", "");
                if (img.indexOf("wcm.files") < 0) {
                    if(img.indexOf("http://") >= 0){
                        out.println(img);
                    }else{
                        out.println("http://nyj.yanan.gov.cn" + img);
                    }
                }
            }
        }
    }
    //关闭资源
    rs.close();
    st.close();
    connection.close();
%>

