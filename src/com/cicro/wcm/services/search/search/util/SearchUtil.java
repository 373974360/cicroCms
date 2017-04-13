//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.search.search.util;

import com.cicro.util.Encode;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.util.xml.XmlManager;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchUtil {
    public SearchUtil() {
    }

    public static String formatTimeYYYYMMDDHHMMSS(String time) {
        try {
            if(time.length() >= 14) {
                String e = time.substring(0, 4);
                String MM = time.substring(4, 6);
                String DD = time.substring(6, 8);
                String HH = time.substring(8, 10);
                String M = time.substring(10, 12);
                String SS = time.substring(12, 14);
                return e + "-" + MM + "-" + DD + " " + HH + ":" + M + ":" + SS;
            } else {
                return formatTimeYYYYMMDD(time);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            return "";
        }
    }

    public static String formatTimeYYYYMMDD(String time) {
        try {
            if(time.length() >= 8) {
                String e = time.substring(0, 4);
                String MM = time.substring(4, 6);
                String DD = time.substring(6, 8);
                return e + "-" + MM + "-" + DD;
            } else {
                return "";
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return "";
        }
    }

    public static Map initPraToMap(String str) {
        try {
            HashMap e = new HashMap();
            Node node = XmlManager.createNode(str);
            NodeList nodeList = XmlManager.queryNodeList(node, "//param");

            for(int i = 0; i < nodeList.getLength(); ++i) {
                Node node2 = nodeList.item(i);
                String key = XmlManager.queryNodeValue(node2, "./key");
                String value = XmlManager.queryNodeValue(node2, "./value");
                e.put(key, value);
            }

            return e;
        } catch (Exception var8) {
            return new HashMap();
        }
    }

    public static String deleteCodeByContent(String stringInfo) {
        StringBuffer sb = new StringBuffer();
        String temp1 = "";
        String temp2 = "";
        if(stringInfo.length() < 10) {
            temp1 = stringInfo;
        } else {
            temp1 = stringInfo.substring(0, 10);
            temp2 = stringInfo.substring(10, stringInfo.length());
        }

        Pattern p = Pattern.compile("[.。,，\"“\\?？!！:：\'‘《》（(）);；、`~@#￥%……&*$]");
        Matcher m = p.matcher(temp1);
        String first = m.replaceAll("");
        sb.append(first);
        sb.append(temp2);
        return sb.toString();
    }

    public static String getXmlParam(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer("");

        try {
            sb.append("<cicro>");
            String e = request.getParameter("type");
            if(e != null && !e.trim().equals("")) {
                sb.append("<param><key>type</key><value><![CDATA[" + e + "]]></value></param>");
            } else {
                e = (String)request.getAttribute("type");
                if(e != null && !e.trim().equals("")) {
                    sb.append("<param><key>type</key><value><![CDATA[" + e + "]]></value></param>");
                }
            }

            String typed = request.getParameter("typed");
            if(typed != null && !typed.equals("")) {
                sb.append("<param><key>typed</key><value><![CDATA[" + typed + "]]></value></param>");
            }

            String code = JconfigUtilContainer.bashConfig().getProperty("code", "utf8", "searchCode");
            String q = request.getParameter("q");
            if(q != null && !q.equals("")) {
                if(code.equals("utf8")) {
                    q = Encode.iso_8859_1ToUtf8(q);
                } else if(code.equals("gbk")) {
                    q = Encode.iso_8859_1ToGbk(q);
                } else if(code.equals("system")) {
                    q = Encode.iso_8859_1ToSystem(q);
                }

                q = q.replaceAll("&", "AND");
                sb.append("<param><key>q</key><value><![CDATA[" + q + "]]></value></param>");
            }

            String q2 = request.getParameter("q2");
            if(q2 != null && !q2.equals("")) {
                if(code.equals("utf8")) {
                    q2 = Encode.iso_8859_1ToUtf8(q2);
                } else if(code.equals("gbk")) {
                    q2 = Encode.iso_8859_1ToGbk(q2);
                } else if(code.equals("system")) {
                    q2 = Encode.iso_8859_1ToSystem(q2);
                }

                q2 = q2.replaceAll("&", "AND");
                sb.append("<param><key>q2</key><value><![CDATA[" + q2 + "]]></value></param>");
            }

            String p = request.getParameter("p");
            if(p == null || p.equals("")) {
                p = "1";
            }

            sb.append("<param><key>p</key><value><![CDATA[" + p + "]]></value></param>");
            String pz = request.getParameter("pz");
            if(pz != null && !pz.equals("")) {
                sb.append("<param><key>pz</key><value><![CDATA[" + pz + "]]></value></param>");
            }

            String length = request.getParameter("length");
            if(length != null && !length.equals("")) {
                sb.append("<param><key>length</key><value><![CDATA[" + length + "]]></value></param>");
            }

            String color = request.getParameter("color");
            if(color != null && !color.equals("")) {
                sb.append("<param><key>color</key><value><![CDATA[" + color + "]]></value></param>");
            }

            String site_id = request.getParameter("site_id") == null?"":request.getParameter("site_id");
            System.out.println("****************site_id1111*****************" + site_id);
            if(site_id.equals("")) {
                site_id = (String)request.getAttribute("site_id") == null?"":(String)request.getAttribute("site_id");
            }

            if(site_id.equals("")) {
                sb.append("<param><key>site_domain</key><value><![CDATA[" + request.getLocalName() + "]]></value></param>");
                site_id = SiteDomainManager.getSiteIDByDomain(request.getLocalName());
                sb.append("<param><key>site_id</key><value><![CDATA[" + site_id + "]]></value></param>");
            } 
            if(!site_id.equals("") && !site_id.equals("all")){
            	sb.append("<param><key>site_id</key><value><![CDATA[" + site_id + "]]></value></param>");
            }
            
            /*
            else if(!site_id.equals("all")) {
                site_id = SiteDomainManager.getSiteIDByDomain(request.getLocalName());
                sb.append("<param><key>site_id</key><value><![CDATA[" + site_id + "]]></value></param>");
            }
            */
            System.out.println("****************site_id2222*****************" + site_id);
            
            String ds_id = request.getParameter("ds_id");
            if(ds_id != null && !ds_id.trim().equals("")) {
                sb.append("<param><key>ds_id</key><value><![CDATA[" + ds_id + "]]></value></param>");
            } else {
                ds_id = (String)request.getAttribute("ds_id");
                if(ds_id != null && !ds_id.trim().equals("")) {
                    sb.append("<param><key>ds_id</key><value><![CDATA[" + ds_id + "]]></value></param>");
                }
            }

            String categoryId = request.getParameter("categoryId");
            if(categoryId != null && !categoryId.equals("")) {
                sb.append("<param><key>categoryId</key><value><![CDATA[" + categoryId + "]]></value></param>");
            }

            String scope = request.getParameter("scope");
            if(scope != null && !scope.equals("")) {
                sb.append("<param><key>scope</key><value><![CDATA[" + scope + "]]></value></param>");
            }

            String ts = request.getParameter("ts");
            if(ts != null && !ts.equals("")) {
                sb.append("<param><key>ts</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(ts) + "]]></value></param>");
            }

            String te = request.getParameter("te");
            if(te != null && !ts.equals("")) {
                sb.append("<param><key>te</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(te) + "]]></value></param>");
            }

            String st = request.getParameter("st");
            if(st != null && !st.equals("")) {
                sb.append("<param><key>st</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(st) + "]]></value></param>");
            }

            String wnumber = request.getParameter("wnumber");
            if(wnumber != null && !wnumber.equals("")) {
                sb.append("<param><key>wnumber</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(wnumber) + "]]></value></param>");
            }

            String object_words = request.getParameter("object_words");
            if(object_words != null && !object_words.equals("")) {
                sb.append("<param><key>object_words</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(object_words) + "]]></value></param>");
            }

            String form_type = request.getParameter("form_type");
            if(form_type != null && !form_type.equals("")) {
                sb.append("<param><key>form_type</key><value><![CDATA[" + form_type + "]]></value></param>");
            }

            String content_type = request.getParameter("content_type");
            if(content_type != null && !content_type.equals("")) {
                sb.append("<param><key>content_type</key><value><![CDATA[" + content_type + "]]></value></param>");
            }

            String publish_org = request.getParameter("publish_org");
            if(publish_org != null && !publish_org.equals("")) {
                sb.append("<param><key>publish_org</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(publish_org) + "]]></value></param>");
            }

            String is_host = request.getParameter("is_host");
            if(is_host != null && !is_host.equals("")) {
                sb.append("<param><key>is_host</key><value><![CDATA[" + is_host + "]]></value></param>");
            }

            String qn1 = request.getParameter("qn1");
            if(qn1 != null && !qn1.equals("")) {
                sb.append("<param><key>qn1</key><value><![CDATA[" + codeString(qn1) + "]]></value></param>");
            }

            String qn2 = request.getParameter("qn2");
            if(qn2 != null && !qn2.equals("")) {
                sb.append("<param><key>qn2</key><value><![CDATA[" + codeString(qn2) + "]]></value></param>");
            }

            String qn3 = request.getParameter("qn3");
            if(qn3 != null && !qn3.equals("")) {
                sb.append("<param><key>qn3</key><value><![CDATA[" + codeString(qn3) + "]]></value></param>");
            }

            sb.append("</cicro>");
            return sb.toString();
        } catch (Exception var27) {
            var27.printStackTrace();
            return "";
        }
    }

    public static String codeString(String q) {
        String code = JconfigUtilContainer.bashConfig().getProperty("code", "utf8", "searchCode");
        if(q != null && !q.equals("")) {
            if(code.equals("utf8")) {
                q = Encode.iso_8859_1ToUtf8(q);
            } else if(code.equals("gbk")) {
                q = Encode.iso_8859_1ToGbk(q);
            } else if(code.equals("system")) {
                q = Encode.iso_8859_1ToSystem(q);
            }
        } else {
            q = "";
        }

        return q;
    }

    public static void main(String[] arr) {
        String str = "。继续推进国产动漫振兴工程、国家数字电影制作基地建设工程、多媒体数据库和经济信息平台、“中华字库”工程、国家“知识资源数据库”出版工程等重大文化建设项目。选择一批具备实施条件的重点项目给予支持";
        System.out.println(deleteCodeByContent(str));
    }
}
