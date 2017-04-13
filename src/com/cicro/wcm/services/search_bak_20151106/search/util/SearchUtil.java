/*     */ package com.cicro.wcm.services.search_bak_20151106.search.util;
/*     */ 
/*     */ import com.cicro.util.Encode;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.util.xml.XmlManager;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class SearchUtil
/*     */ {
/*     */   public static String formatTimeYYYYMMDDHHMMSS(String time)
/*     */   {
/*     */     try
/*     */     {
/*  23 */       if (time.length() >= 14) {
/*  24 */         String YYYY = time.substring(0, 4);
/*  25 */         String MM = time.substring(4, 6);
/*  26 */         String DD = time.substring(6, 8);
/*  27 */         String HH = time.substring(8, 10);
/*  28 */         String M = time.substring(10, 12);
/*  29 */         String SS = time.substring(12, 14);
/*     */ 
/*  31 */         return YYYY + "-" + MM + "-" + DD + " " + HH + ":" + M + ":" + SS;
/*     */       }
/*  33 */       return formatTimeYYYYMMDD(time);
/*     */     }
/*     */     catch (Exception e) {
/*  36 */       e.printStackTrace();
/*  37 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String formatTimeYYYYMMDD(String time)
/*     */   {
/*     */     try
/*     */     {
/*  45 */       if (time.length() >= 8) {
/*  46 */         String YYYY = time.substring(0, 4);
/*  47 */         String MM = time.substring(4, 6);
/*  48 */         String DD = time.substring(6, 8);
/*     */ 
/*  50 */         return YYYY + "-" + MM + "-" + DD;
/*     */       }
/*  52 */       return "";
/*     */     }
/*     */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*  56 */     }return "";
/*     */   }
/*     */ 
/*     */   public static Map initPraToMap(String str)
/*     */   {
/*     */     try
/*     */     {
/*  63 */       Map map = new HashMap();
/*  64 */       Node node = XmlManager.createNode(str);
/*  65 */       NodeList nodeList = XmlManager.queryNodeList(node, "//param");
/*  66 */       for (int i = 0; i < nodeList.getLength(); i++) {
/*  67 */         Node node2 = nodeList.item(i);
/*  68 */         String key = XmlManager.queryNodeValue(node2, "./key");
/*  69 */         String value = XmlManager.queryNodeValue(node2, "./value");
/*  70 */         map.put(key, value);
/*     */       }
/*  72 */       return map;
/*     */     } catch (Exception e) {
/*     */     }
/*  75 */     return new HashMap();
/*     */   }
/*     */ 
/*     */   public static String deleteCodeByContent(String stringInfo)
/*     */   {
/*  82 */     StringBuffer sb = new StringBuffer();
/*  83 */     String temp1 = "";
/*  84 */     String temp2 = "";
/*  85 */     if (stringInfo.length() < 10) {
/*  86 */       temp1 = stringInfo;
/*     */     } else {
/*  88 */       temp1 = stringInfo.substring(0, 10);
/*  89 */       temp2 = stringInfo.substring(10, stringInfo.length());
/*     */     }
/*  91 */     Pattern p = Pattern.compile("[.。,，\"“\\?？!！:：'‘《》（(）);；、`~@#￥%……&*$]");
/*  92 */     Matcher m = p.matcher(temp1);
/*  93 */     String first = m.replaceAll("");
/*  94 */     sb.append(first);
/*  95 */     sb.append(temp2);
/*  96 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getXmlParam(HttpServletRequest request)
/*     */   {
/* 102 */     StringBuffer sb = new StringBuffer("");
/*     */     try {
/* 104 */       sb.append("<cicro>");
/* 105 */       String type = request.getParameter("type");
/* 106 */       if ((type != null) && (!type.trim().equals(""))) {
/* 107 */         sb.append("<param><key>type</key><value><![CDATA[" + type + "]]></value></param>");
/*     */       } else {
/* 109 */         type = (String)request.getAttribute("type");
/* 110 */         if ((type != null) && (!type.trim().equals(""))) {
/* 111 */           sb.append("<param><key>type</key><value><![CDATA[" + type + "]]></value></param>");
/*     */         }
/*     */       }
/* 114 */       String typed = request.getParameter("typed");
/* 115 */       if ((typed != null) && (!typed.equals(""))) {
/* 116 */         sb.append("<param><key>typed</key><value><![CDATA[" + typed + "]]></value></param>");
/*     */       }
/*     */ 
/* 119 */       String code = JconfigUtilContainer.bashConfig().getProperty("code", "utf8", "searchCode");
/*     */ 
/* 121 */       String q = request.getParameter("q");
/* 122 */       if ((q != null) && (!q.equals(""))) {
/* 123 */         if (code.equals("utf8"))
/* 124 */           q = Encode.iso_8859_1ToUtf8(q);
/* 125 */         else if (code.equals("gbk"))
/* 126 */           q = Encode.iso_8859_1ToGbk(q);
/* 127 */         else if (code.equals("system")) {
/* 128 */           q = Encode.iso_8859_1ToSystem(q);
/*     */         }
/* 130 */         q = q.replaceAll("&", "AND");
				  q = q.replaceAll(" ", "AND");
/* 131 */         sb.append("<param><key>q</key><value><![CDATA[" + q + "]]></value></param>");
/*     */       }
/* 133 */       String q2 = request.getParameter("q2");
/* 134 */       if ((q2 != null) && (!q2.equals(""))) {
/* 135 */         if (code.equals("utf8"))
/* 136 */           q2 = Encode.iso_8859_1ToUtf8(q2);
/* 137 */         else if (code.equals("gbk"))
/* 138 */           q2 = Encode.iso_8859_1ToGbk(q2);
/* 139 */         else if (code.equals("system")) {
/* 140 */           q2 = Encode.iso_8859_1ToSystem(q2);
/*     */         }
/* 142 */         q2 = q2.replaceAll("&", "AND");
				  q2 = q2.replaceAll(" ", "AND");
/* 143 */         sb.append("<param><key>q2</key><value><![CDATA[" + q2 + "]]></value></param>");
/*     */       }
/*     */ 
/* 147 */       String p = request.getParameter("p");
/* 148 */       if ((p == null) || (p.equals(""))) {
/* 149 */         p = "1";
/*     */       }
/* 151 */       sb.append("<param><key>p</key><value><![CDATA[" + p + "]]></value></param>");
/* 152 */       String pz = request.getParameter("pz");
/* 153 */       if ((pz != null) && (!pz.equals(""))) {
/* 154 */         sb.append("<param><key>pz</key><value><![CDATA[" + pz + "]]></value></param>");
/*     */       }
/* 156 */       String length = request.getParameter("length");
/* 157 */       if ((length != null) && (!length.equals(""))) {
/* 158 */         sb.append("<param><key>length</key><value><![CDATA[" + length + "]]></value></param>");
/*     */       }
/* 160 */       String color = request.getParameter("color");
/* 161 */       if ((color != null) && (!color.equals(""))) {
/* 162 */         sb.append("<param><key>color</key><value><![CDATA[" + color + "]]></value></param>");
/*     */       }
/*     */ 
/* 165 */       String site_id = request.getParameter("site_id") == null ? "" : request.getParameter("site_id");
/*     */ 
/* 167 */       if (site_id.equals("")) {
/* 168 */         site_id = (String)request.getAttribute("site_id") == null ? "" : (String)request.getAttribute("site_id");
/*     */       }
/* 170 */       if (site_id.equals(""))
/*     */       {
/* 173 */         sb.append("<param><key>site_domain</key><value><![CDATA[" + request.getLocalName() + "]]></value></param>");
/* 174 */         site_id = SiteDomainManager.getSiteIDByDomain(request.getLocalName());
/* 175 */         sb.append("<param><key>site_id</key><value><![CDATA[" + site_id + "]]></value></param>");
/*     */       }
/* 177 */       else if (!site_id.equals("all"))
/*     */       {
/* 179 */         site_id = SiteDomainManager.getSiteIDByDomain(request.getLocalName());
/* 180 */         sb.append("<param><key>site_id</key><value><![CDATA[" + site_id + "]]></value></param>");
/*     */       }
/*     */ 
/* 185 */       String ds_id = request.getParameter("ds_id");
/* 186 */       if ((ds_id != null) && (!ds_id.trim().equals(""))) {
/* 187 */         sb.append("<param><key>ds_id</key><value><![CDATA[" + ds_id + "]]></value></param>");
/*     */       } else {
/* 189 */         ds_id = (String)request.getAttribute("ds_id");
/* 190 */         if ((ds_id != null) && (!ds_id.trim().equals(""))) {
/* 191 */           sb.append("<param><key>ds_id</key><value><![CDATA[" + ds_id + "]]></value></param>");
/*     */         }
/*     */       }
/*     */ 
/* 195 */       String categoryId = request.getParameter("categoryId");
/* 196 */       if ((categoryId != null) && (!categoryId.equals(""))) {
/* 197 */         sb.append("<param><key>categoryId</key><value><![CDATA[" + categoryId + "]]></value></param>");
/*     */       }
/* 199 */       String scope = request.getParameter("scope");
/* 200 */       if ((scope != null) && (!scope.equals(""))) {
/* 201 */         sb.append("<param><key>scope</key><value><![CDATA[" + scope + "]]></value></param>");
/*     */       }
/*     */ 
/* 205 */       String ts = request.getParameter("ts");
/* 206 */       if ((ts != null) && (!ts.equals(""))) {
/* 207 */         sb.append("<param><key>ts</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(ts) + "]]></value></param>");
/*     */       }
/* 209 */       String te = request.getParameter("te");
/* 210 */       if ((te != null) && (!ts.equals(""))) {
/* 211 */         sb.append("<param><key>te</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(te) + "]]></value></param>");
/*     */       }
/* 213 */       String st = request.getParameter("st");
/* 214 */       if ((st != null) && (!st.equals(""))) {
/* 215 */         sb.append("<param><key>st</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(st) + "]]></value></param>");
/*     */       }
/*     */ 
/* 219 */       String wnumber = request.getParameter("wnumber");
/* 220 */       if ((wnumber != null) && (!wnumber.equals(""))) {
/* 221 */         sb.append("<param><key>wnumber</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(wnumber) + "]]></value></param>");
/*     */       }
/* 223 */       String object_words = request.getParameter("object_words");
/* 224 */       if ((object_words != null) && (!object_words.equals(""))) {
/* 225 */         sb.append("<param><key>object_words</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(object_words) + "]]></value></param>");
/*     */       }
/* 227 */       String form_type = request.getParameter("form_type");
/* 228 */       if ((form_type != null) && (!form_type.equals(""))) {
/* 229 */         sb.append("<param><key>form_type</key><value><![CDATA[" + form_type + "]]></value></param>");
/*     */       }
/* 231 */       String content_type = request.getParameter("content_type");
/* 232 */       if ((content_type != null) && (!content_type.equals(""))) {
/* 233 */         sb.append("<param><key>content_type</key><value><![CDATA[" + content_type + "]]></value></param>");
/*     */       }
/* 235 */       String publish_org = request.getParameter("publish_org");
/* 236 */       if ((publish_org != null) && (!publish_org.equals(""))) {
/* 237 */         sb.append("<param><key>publish_org</key><value><![CDATA[" + Encode.iso_8859_1ToUtf8(publish_org) + "]]></value></param>");
/*     */       }
/* 239 */       String is_host = request.getParameter("is_host");
/* 240 */       if ((is_host != null) && (!is_host.equals(""))) {
/* 241 */         sb.append("<param><key>is_host</key><value><![CDATA[" + is_host + "]]></value></param>");
/*     */       }
/*     */ 
/* 245 */       String qn1 = request.getParameter("qn1");
/* 246 */       if ((qn1 != null) && (!qn1.equals(""))) {
/* 247 */         sb.append("<param><key>qn1</key><value><![CDATA[" + codeString(qn1) + "]]></value></param>");
/*     */       }
/*     */ 
/* 250 */       String qn2 = request.getParameter("qn2");
/* 251 */       if ((qn2 != null) && (!qn2.equals(""))) {
/* 252 */         sb.append("<param><key>qn2</key><value><![CDATA[" + codeString(qn2) + "]]></value></param>");
/*     */       }
/*     */ 
/* 255 */       String qn3 = request.getParameter("qn3");
/* 256 */       if ((qn3 != null) && (!qn3.equals(""))) {
/* 257 */         sb.append("<param><key>qn3</key><value><![CDATA[" + codeString(qn3) + "]]></value></param>");
/*     */       }
/*     */ 
/* 260 */       sb.append("</cicro>");
/*     */ 
/* 262 */       return sb.toString();
/*     */     } catch (Exception e) {
/* 264 */       e.printStackTrace();
/* 265 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String codeString(String q)
/*     */   {
/* 272 */     String code = JconfigUtilContainer.bashConfig().getProperty("code", "utf8", "searchCode");
/* 273 */     if ((q != null) && (!q.equals(""))) {
/* 274 */       if (code.equals("utf8"))
/* 275 */         q = Encode.iso_8859_1ToUtf8(q);
/* 276 */       else if (code.equals("gbk"))
/* 277 */         q = Encode.iso_8859_1ToGbk(q);
/* 278 */       else if (code.equals("system"))
/* 279 */         q = Encode.iso_8859_1ToSystem(q);
/*     */     }
/*     */     else {
/* 282 */       q = "";
/*     */     }
/* 284 */     return q;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr) {
/* 288 */     String str = "。继续推进国产动漫振兴工程、国家数字电影制作基地建设工程、多媒体数据库和经济信息平台、“中华字库”工程、国家“知识资源数据库”出版工程等重大文化建设项目。选择一批具备实施条件的重点项目给予支持";
/* 289 */     System.out.println(deleteCodeByContent(str));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.search.util.SearchUtil
 * JD-Core Version:    0.6.2
 */