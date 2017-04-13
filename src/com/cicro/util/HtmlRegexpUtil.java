//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HtmlRegexpUtil {
    private static final String regxpForHtml = "<([^>]*)>";
    public static final String REGXPFORIMGTAG = "<\\s*img\\s+([^>]*)\\s*>";
    public static final String REGXPFORIMATAGSRCATTRIB = "src=\"([^\"]+)\"";
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

    public HtmlRegexpUtil() {
    }

    public static String encodeHtml(String input) {
        if(!hasSpecialChars(input)) {
            return input;
        } else {
            StringBuffer filtered = new StringBuffer(input.length());

            for(int i = 0; i <= input.length() - 1; ++i) {
                char c = input.charAt(i);
                switch(c) {
                case '\"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                default:
                    filtered.append(c);
                }
            }

            return filtered.toString();
        }
    }

    public static boolean hasSpecialChars(String input) {
        boolean flag = false;
        if(input != null && input.length() > 0) {
            for(int i = 0; i <= input.length() - 1; ++i) {
                char c = input.charAt(i);
                switch(c) {
                case '\"':
                    flag = true;
                    break;
                case '&':
                    flag = true;
                    break;
                case '<':
                    flag = true;
                    break;
                case '>':
                    flag = true;
                }
            }
        }

        return flag;
    }

    public static String filterHtml(String str) {
        if(str != null && (str = str.trim()).length() != 0) {
            Pattern pattern = Pattern.compile("<([^>]*)>");
            Matcher matcher = pattern.matcher(str);
            StringBuffer sb = new StringBuffer();

            for(boolean result1 = matcher.find(); result1; result1 = matcher.find()) {
                matcher.appendReplacement(sb, "");
            }

            matcher.appendTail(sb);
            return sb.toString();
        } else {
            return "";
        }
    }

    public static String fiterHtmlTag(String str, String tag) {
        if(str != null && (str = str.trim()).length() != 0) {
            if(tag != null && (tag = tag.trim()).length() != 0) {
                String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
                Pattern pattern = Pattern.compile(regxp);
                Matcher matcher = pattern.matcher(str);
                StringBuffer sb = new StringBuffer();

                for(boolean result1 = matcher.find(); result1; result1 = matcher.find()) {
                    matcher.appendReplacement(sb, "");
                }

                matcher.appendTail(sb);
                return sb.toString();
            } else {
                return filterHtml(str);
            }
        } else {
            return "";
        }
    }

    public static String replaceHtmlTag(String str, String beforeTag, String tagAttrib, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();

        for(boolean result = matcherForTag.find(); result; result = matcherForTag.find()) {
            StringBuffer sbreplace = new StringBuffer();
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
            if(matcherForAttrib.find()) {
                matcherForAttrib.appendReplacement(sbreplace, startTag + matcherForAttrib.group(1) + endTag);
            }

            matcherForTag.appendReplacement(sb, sbreplace.toString());
        }

        matcherForTag.appendTail(sb);
        return sb.toString();
    }

    public static boolean tagsContainLabel(String s, String content) {
        if(!s.contains("<") && !s.contains(">")) {
            Pattern p = Pattern.compile("<[a-zA-z0-9]{0,10}[^>]*" + s + ".*?>");
            Matcher m = p.matcher(content);
            return m.find();
        } else {
            return true;
        }
    }
    
    /**
    *
    * 基本功能：过滤所有以空格换行符
    * <p>
    *
    * @param String
    * @return String
    */
   public static String filterSpace(String str) {
       if(str==null||(str=str.trim()).length()==0){
           return "";
       }
       Pattern pattern = Pattern.compile(regEx_space);
       Matcher matcher = pattern.matcher(str);
       StringBuffer sb = new StringBuffer();
       boolean result1 = matcher.find();
       while (result1) {
           matcher.appendReplacement(sb, "");
           result1 = matcher.find();
       }
       matcher.appendTail(sb);
       return sb.toString();
   }

    public static void main(String[] args) {
        Pattern p = Pattern.compile("<.*?123.*?>");
        Matcher m = p.matcher("<a>1234</a>");
        System.out.println("22222--" + m.find());
    }
}
