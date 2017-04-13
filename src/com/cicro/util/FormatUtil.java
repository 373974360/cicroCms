//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.util;

import com.cicro.util.DateUtil;
import com.cicro.util.HtmlRegexpUtil;
import com.cicro.util.ip.Utils;
import com.cicro.wcm.server.ServerManager;
import com.cicro.wcm.services.control.site.SiteManager;
import java.io.File;
import java.text.ParseException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class FormatUtil {
    public FormatUtil() {
    }

    public static String formatSize(long size) {
        String sizeStr = "";
        if(size < 1024L) {
            sizeStr = size + " Bytes";
        } else if(size > 1024000L) {
            sizeStr = Float.toString((float)size / 1024000.0F);
            if(sizeStr.length() > 4) {
                sizeStr = sizeStr.substring(0, 4);
            }

            if(sizeStr.endsWith(".")) {
                sizeStr = sizeStr.substring(0, sizeStr.length() - 1);
            }

            sizeStr = sizeStr + " M";
        } else {
            sizeStr = Float.toString((float)size / 1024.0F);
            if(sizeStr.length() > 4) {
                sizeStr = sizeStr.substring(0, 4);
            }

            if(sizeStr.endsWith(".")) {
                sizeStr = sizeStr.substring(0, sizeStr.length() - 1);
            }

            sizeStr = sizeStr + " K";
        }

        return sizeStr;
    }

    public static String formatPath(String pathStr, boolean isEndWithSeparator) {
        pathStr = formatPath(pathStr);
        pathStr = pathStr + File.separator;
        return pathStr;
    }

    public static String formatPath(String pathStr) {
        pathStr = pathStr.replace('/', File.separatorChar);
        pathStr = pathStr.replace('\\', File.separatorChar);
        if(pathStr.endsWith(File.separator)) {
            pathStr = pathStr.substring(0, pathStr.length() - 1);
            pathStr = formatPath(pathStr);
        }

        return pathStr;
    }

    public static String formatNullString(String str) {
        if(str == null || str.trim().equals("")) {
            str = "";
        }

        return str;
    }

    public static String formatNullString(String str, String defaultStr) {
        if(str == null || str.trim().equals("")) {
            str = defaultStr;
        }

        return str;
    }

    public static boolean isNumeric(String str) {
        if(str != null && !"".equals(str) && !str.startsWith("0")) {
            int i = str.length();

            char chr;
            do {
                --i;
                if(i < 0) {
                    return true;
                }

                chr = str.charAt(i);
            } while(chr >= 48 && chr <= 57);

            return false;
        } else {
            return false;
        }
    }

    public static boolean isValiditySQL(String str) {
        String inj_str = "\'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare| or |+";
        String[] inj_stra = inj_str.split("\\|");

        for(int i = 0; i < inj_stra.length; ++i) {
            if(str.indexOf(inj_stra[i]) >= 0) {
                System.out.println("sql str is error --> " + str);
                return false;
            }
        }

        return true;
    }

    public static String getParameterStrInRequest(HttpServletRequest request) {
        String parms = "";

        String p_name;
        for(Enumeration enumOne = request.getParameterNames(); enumOne.hasMoreElements(); parms = parms + "&" + p_name + "=" + request.getParameter(p_name)) {
            p_name = (String)enumOne.nextElement();
        }

        if(parms != null && parms.length() > 0) {
            parms = parms.substring(1);
        }

        return parms;
    }

    public static String formatDate(String dateStr, String pattern) throws ParseException {
        return dateStr != null && !"".equals(dateStr)?DateUtil.formatToString(dateStr, pattern):"";
    }

    public static String cutString(String str, int length, String paddingStr) {
        if(str != null && str.length() > 0) {
            String new_str = "";
            char[] c = str.toCharArray();
            int len = 0;

            for(int i = 0; i < c.length; ++i) {
                if(len >= length * 2 - 1) {
                    return new_str + paddingStr;
                }

                ++len;
                if(!isLetter(c[i])) {
                    ++len;
                }

                new_str = new_str + c[i];
            }

            return new_str;
        } else {
            return "";
        }
    }

    public static int length(String s) {
        if(s == null) {
            return 0;
        } else {
            char[] c = s.toCharArray();
            int len = 0;

            for(int i = 0; i < c.length; ++i) {
                ++len;
                if(!isLetter(c[i])) {
                    ++len;
                }
            }

            return len;
        }
    }

    public static boolean isLetter(char c) {
        short k = 128;
        return c / k == 0;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String getAddress(String ip) {
        return Utils.getAddress(ip);
    }

    public static String intToString(int num, int digit, String symbol) {
        if(symbol.length() != 1) {
            symbol = symbol.substring(0, 1);
        }

        String strNum = String.valueOf(num);
        if(strNum.length() >= digit) {
            return strNum.length() > digit?strNum.substring(0, digit):strNum;
        } else {
            String ret = "";

            for(int i = 0; i < digit - strNum.length(); ++i) {
                ret = ret + symbol;
            }

            ret = ret + strNum;
            return ret;
        }
    }

    public static String getCurrentDate(String pattern) {
        return pattern != null && !"".equals(pattern)?DateUtil.getCurrentDateTime(pattern):DateUtil.getCurrentDateTime();
    }

    public static String getTemplatePathForSiteID(String site_id) {
        String path = formatPath(SiteManager.getSitePath(site_id) + "/WEB-INF/template");
        return ServerManager.isWindows()?path.substring(path.indexOf("\\vhost") + 8):path.substring(path.indexOf("/vhost") + 8);
    }

    public static String filterHtmlTag(String content) {
    	String result= HtmlRegexpUtil.filterHtml(content);
        result = HtmlRegexpUtil.filterSpace(result);
        return result.replaceAll("　","").replaceAll("&nbsp;"," ").replaceAll(" ","");
    }

    public static boolean contrastCurrentTime(String o_time, int h) {
        try {
            return DateUtil.compareDatetime(o_time, DateUtil.getCurrentDateTime()) > (long)h;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static boolean isNull(String str) {
        str = str.trim();
        return str == null || "".equals(str) || "null".equals(str.toLowerCase());
    }

    public static String formatJsonStr(String str) {
        return str.replaceAll("\"", "\\\\\"");
    }

    public static String getDomainForUrl(String url) {
        return url.replaceAll("http://([^/|:]+)[/|:].*", "$1");
    }

    public static void main(String[] args) {
        String path = "d:\\cicro\\cms\\vhosts\\www.cicrocms.com\\ROOT\\WEB-INF\\template";
        System.out.println(path.substring(path.indexOf("\\vhost") + 8));
    }
}
