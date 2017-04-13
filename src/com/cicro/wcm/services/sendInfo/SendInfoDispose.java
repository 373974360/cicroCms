/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.BeanToMapUtil;
/*     */ import com.cicro.util.CryptoTools;
/*     */ import com.cicro.util.xml.XmlManager;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.sendInfo.ReceiveInfoDAO;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class SendInfoDispose
/*     */ {
/*     */   public static boolean sendInfoHandl(String xml, String user)
/*     */   {
/*     */     try
/*     */     {
/*  38 */       Node node = XmlManager.createNode(xml);
/*  39 */       String s_site_id = XmlManager.queryNodeValue(node, "//s_site_id");
/*  40 */       if (!keyIsConsistent(s_site_id, user))
/*     */       {
/*  42 */         return false;
/*     */       }
/*  44 */       String site_id = XmlManager.queryNodeValue(node, "//t_site_id");
/*  45 */       String app_id = XmlManager.queryNodeValue(node, "//t_app_id");
/*  46 */       String s_site_domain = XmlManager.queryNodeValue(node, "//s_site_domain");
/*  47 */       String s_site_name = XmlManager.queryNodeValue(node, "//s_site_name");
/*  48 */       String s_user_id = XmlManager.queryNodeValue(node, "//s_user_id");
/*  49 */       String s_user_name = XmlManager.queryNodeValue(node, "//s_user_name");
/*  50 */       String s_send_dtime = XmlManager.queryNodeValue(node, "//s_send_dtime");
/*     */ 
/*  52 */       NodeList infoList = XmlManager.queryNodeList(node, "//info");
/*  53 */       if ((infoList != null) && (infoList.getLength() > 0))
/*     */       {
/*  55 */         for (int i = 0; i < infoList.getLength(); i++)
/*     */         {
/*  57 */           Node cn = infoList.item(i);
/*  58 */           ReceiveInfoBean rifb = xmlToBean(XmlManager.queryNode(cn, "r_content"));
/*  59 */           System.out.println(rifb.getTitle());
/*  60 */           rifb.setCat_id(Integer.parseInt(XmlManager.queryNodeValue(cn, "r_cat_id")));
/*  61 */           rifb.setSend_record_id(Integer.parseInt(XmlManager.queryNodeValue(cn, "send_record_id")));
/*  62 */           rifb.setContent(XmlManager.node2String(XmlManager.queryNode(cn, "r_content")));
/*  63 */           rifb.setS_info_id(XmlManager.queryNodeValue(cn, "r_content/info_id"));
/*  64 */           rifb.setS_site_id(s_site_id);
/*  65 */           rifb.setS_site_name(s_site_name);
/*  66 */           rifb.setS_site_domain(s_site_domain);
/*  67 */           rifb.setS_user_id(Integer.parseInt(s_user_id));
/*  68 */           rifb.setS_user_name(s_user_name);
/*  69 */           rifb.setS_send_dtime(s_send_dtime);
/*  70 */           rifb.setSite_id(site_id);
/*  71 */           rifb.setApp_id(app_id);
/*  72 */           rifb.setS_content_url(XmlManager.queryNodeValue(cn, "r_content/content_url"));
/*  73 */           rifb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.RECEIVE_INFO_TABLE_NAME));
/*  74 */           ReceiveInfoDAO.insertReceiveInfo(rifb);
/*     */         }
/*     */       }
/*  77 */       return true;
/*     */     }
/*     */     catch (ParserConfigurationException e) {
/*  80 */       e.printStackTrace();
/*  81 */       return false;
/*     */     }
/*     */     catch (SAXException e) {
/*  84 */       e.printStackTrace();
/*  85 */       return false;
/*     */     }
/*     */     catch (IOException e) {
/*  88 */       e.printStackTrace();
/*  89 */       return false;
/*     */     }
/*     */     catch (TransformerException e) {
/*  92 */       e.printStackTrace();
/*  93 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean sendInfoHandlFormThirdParty(String xml)
/*     */   {
/*     */     try
/*     */     {
/* 221 */       Node node = XmlManager.createNode(xml);
/*     */ 
/* 223 */       String username = XmlManager.queryNodeValue(node, "//username");
/* 224 */       String password = XmlManager.queryNodeValue(node, "//password");
/* 225 */       CryptoTools tools = new CryptoTools();
/* 226 */       String de_pass = tools.decode(password);
/* 227 */       if ((!"system".equals(username)) || (!"manager".equals(de_pass))) {
/* 228 */         return false;
/*     */       }
/*     */ 
/* 231 */       String s_site_id = "";
/* 232 */       String site_id = XmlManager.queryNodeValue(node, "//t_site_id");
/* 233 */       String s_site_domain = XmlManager.queryNodeValue(node, "//s_site_domain");
/* 234 */       String s_site_name = XmlManager.queryNodeValue(node, "//s_site_name");
/* 235 */       String s_user_name = XmlManager.queryNodeValue(node, "//s_user_name");
/* 236 */       String s_send_dtime = XmlManager.queryNodeValue(node, "//s_send_dtime");
/* 237 */       String s_user_id = "0";
/* 238 */       NodeList infoList = XmlManager.queryNodeList(node, "//info");
/* 239 */       if ((infoList != null) && (infoList.getLength() > 0))
/*     */       {
/* 241 */         for (int i = 0; i < infoList.getLength(); i++)
/*     */         {
/* 243 */           Node cn = infoList.item(i);
/*     */ 
/* 246 */           ReceiveInfoBean rifb = new ReceiveInfoBean();
/*     */ 
/* 248 */           String title = XmlManager.queryNodeValue(cn, "r_content/title");
/* 249 */           String key_words = XmlManager.queryNodeValue(cn, "r_content/info_keywords");
/* 250 */           String info_description = XmlManager.queryNodeValue(cn, "r_content/info_description");
/* 251 */           int catId = Integer.parseInt(XmlManager.queryNodeValue(cn, "r_cat_id"));
/* 252 */           String send_record_id = XmlManager.queryNodeValue(cn, "send_record_id");
/* 253 */           String catCname = XmlManager.queryNodeValue(cn, "r_cat_name");
/* 254 */           rifb.setContent(XmlManager.node2String(XmlManager.queryNode(cn, "r_content")));
/*     */ 
/* 256 */           if (key_words == "") {
/* 257 */             key_words = "";
/*     */           }
/* 259 */           if (info_description == "") {
/* 260 */             info_description = "";
/*     */           }
/* 262 */           if (send_record_id != "")
/* 263 */             rifb.setSend_record_id(Integer.parseInt(send_record_id));
/*     */           else {
/* 265 */             rifb.setSend_record_id(0);
/*     */           }
/* 267 */           rifb.setTitle(title);
/* 268 */           rifb.setCat_id(catId);
/* 269 */           rifb.setCat_cname(catCname);
/* 270 */           rifb.setInfo_description(info_description);
/* 271 */           rifb.setInfo_keywords(key_words);
/* 272 */           rifb.setS_info_id("0");
/* 273 */           rifb.setS_site_id(s_site_id);
/* 274 */           rifb.setS_site_name(s_site_name);
/* 275 */           rifb.setS_site_domain(s_site_domain);
/* 276 */           rifb.setS_user_id(0);
/* 277 */           rifb.setS_user_name(s_user_name);
/* 278 */           rifb.setS_send_dtime(s_send_dtime);
/* 279 */           rifb.setSite_id(site_id);
/* 280 */           rifb.setApp_id("cms");
/* 281 */           rifb.setS_content_url("");
/* 282 */           rifb.setAdopt_desc("");
/* 283 */           rifb.setAdopt_dtime("");
/* 284 */           rifb.setAdopt_status(0);
/* 285 */           rifb.setAdopt_user(0);
/* 286 */           rifb.setAuthor("");
/* 287 */           rifb.setEditor("");
/* 288 */           rifb.setInput_dtime("");
/* 289 */           rifb.setIs_am_tupage(0);
/* 290 */           rifb.setInput_user(0);
/* 291 */           rifb.setIs_pic(0);
/* 292 */           rifb.setPage_count(1);
/* 293 */           rifb.setIs_delete(0);
/*     */ 
/* 295 */           rifb.setSend_record_id(0);
/* 296 */           rifb.setSubtitle("");
/* 297 */           rifb.setTupage_num(0);
/* 298 */           rifb.setScore(0);
/* 299 */           rifb.setModel_id(Integer.parseInt(XmlManager.queryNodeValue(cn, "r_content/model_id")));
/* 300 */           rifb.setAuthor(XmlManager.queryNodeValue(cn, "r_content/author"));
/* 301 */           rifb.setSource(XmlManager.queryNodeValue(cn, "r_content/source"));
/* 302 */           String str1 = PublicTableDAO.RECEIVE_INFO_TABLE_NAME;
/* 303 */           int in2 = PublicTableDAO.getIDByTableName(str1);
/* 304 */           rifb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.RECEIVE_INFO_TABLE_NAME));
/* 305 */           ReceiveInfoDAO.insertReceiveInfo(rifb);
/* 306 */           NodeList filesList = XmlManager.queryNodeList(node, "//files");
/* 307 */           if ((filesList != null) && (filesList.getLength() > 0))
/*     */           {
/* 309 */             for (int m = 0; m < filesList.getLength(); m++)
/*     */             {
/* 311 */               Node im = filesList.item(m);
/* 312 */               String file_url = XmlManager.queryNodeValue(im, "f_content/file_url");
/* 313 */               String file_name = XmlManager.queryNodeValue(im, "f_content/file_name");
/* 314 */               String file_code = XmlManager.queryNodeValue(im, "f_content/file_code");
/* 315 */               if ((file_code == null) || ("".equals(file_code))) {
/*     */                 break;
/*     */               }
/* 318 */               SiteBean siteBean = SiteManager.getSiteBeanBySiteID(site_id);
/* 319 */               String uploadpath = siteBean.getSite_path();
/* 320 */               file_url = uploadpath + file_url.substring(0, file_url.lastIndexOf("/") + 1);
/* 321 */               SendInfoUtil.decoderBase64File(file_code, file_url, file_name);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 326 */       return true;
/*     */     }
/*     */     catch (ParserConfigurationException e) {
/* 329 */       e.printStackTrace();
/* 330 */       return false;
/*     */     }
/*     */     catch (SAXException e) {
/* 333 */       e.printStackTrace();
/* 334 */       return false;
/*     */     }
/*     */     catch (IOException e) {
/* 337 */       e.printStackTrace();
/* 338 */       return false;
/*     */     }
/*     */     catch (TransformerException e) {
/* 341 */       e.printStackTrace();
/* 342 */     }return false;
/*     */   }
/*     */ 
/*     */   public static ReceiveInfoBean xmlToBean(Node content_node)
/*     */   {
/* 355 */     Map m = new HashMap();
/* 356 */     xmlToMap(content_node, m);
/*     */     try {
/* 358 */       return (ReceiveInfoBean)BeanToMapUtil.convertMap(ReceiveInfoBean.class, m);
/*     */     }
/*     */     catch (IntrospectionException e) {
/* 361 */       e.printStackTrace();
/* 362 */       return null;
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 365 */       e.printStackTrace();
/* 366 */       return null;
/*     */     }
/*     */     catch (InstantiationException e) {
/* 369 */       e.printStackTrace();
/* 370 */       return null;
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 373 */       e.printStackTrace();
/* 374 */     }return null;
/*     */   }
/*     */ 
/*     */   public static void xmlToMap(Node content_node, Map<String, String> m)
/*     */   {
/*     */     try
/*     */     {
/* 387 */       System.out.println(m.size() + "  " + content_node.toString());
/* 388 */       for (int i = 0; i < content_node.getChildNodes().getLength(); i++)
/*     */       {
/* 390 */         Node n = content_node.getChildNodes().item(i);
/* 391 */         String keys = n.getNodeName();
/* 392 */         m.put(keys, XmlManager.queryNodeValue(n));
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 396 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean keyIsConsistent(String s_site_id, String user)
/*     */   {
/*     */     try
/*     */     {
/* 411 */       CryptoTools ct = new CryptoTools();
/* 412 */       return s_site_id.equals(ct.decode(user));
/*     */     } catch (Exception e) {
/* 414 */       e.printStackTrace();
/* 415 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 424 */     String xml = "<cicrowcm><t_site_id><![CDATA[CMS125]]></t_site_id><s_site_domain><![CDATA[www.cicro.com]]></s_site_domain><s_site_name><![CDATA[政务门户演示网站]]></s_site_name><s_user_name><![CDATA[系统管理员]]></s_user_name><s_send_dtime><![CDATA[2013-02-22 14:28:10]]></s_send_dtime><infoList><info><r_cat_id>10004</r_cat_id><send_record_id>87</send_record_id><r_content><info_content><![CDATA[<p><br />&nbsp;&nbsp;&nbsp; 在伟大的中国共产党成立90周年之际，我州各地的广大党员干部和普通百姓，都在以感恩的心情，学习和了解我们国家的执政党&mdash;&mdash;中国共产党的奋斗史、创业史和改革开放史。这是一件非常有意义的事。<br />&nbsp;&nbsp;&nbsp; 一位学者说，&ldquo;不懂历史的人没有根，淡忘历史的民族没有魂&rdquo;。我们应该记住这句名言。我们每个人都有了解党史、读懂党史、运用党史的责任。只有用党的&ldquo;三史&rdquo;来洗礼自己的思想，才能为&ldquo;做懂根的人，做有魂的民族&rdquo;打下思想基础。<br />&nbsp;&nbsp;&nbsp; &ldquo;以铜为镜可以正衣冠，以人为镜可以知得失，以史为镜可以知古今&rdquo;。学过党史的人知道，我们的党在已经走过的90年光辉历程中，真正做到了从无到有、由弱变强的转变，它带领全国各族人民战胜重重艰难险阻，成功地领导了两次革命，干了三件大事，实现了两次飞跃&hellip;&hellip;中国共产党的诞生和发展，扭转了近现代中国历史的走向，改变了中国人民的命运，对整个世界的格局和面貌也产生了广泛而深远的影响。<br />&nbsp;&nbsp;&nbsp; 了解了党史、学习了党史，可以得出这样的结沦：共产党没有自身的特殊利益，共产党的职能就是为人民服务。过去，没有共产党就没有新中国；今天，没有共产党就没有和谐发展的中国；未来，我们应该更加坚定一个信念：没有共产党就不会有未来屹立于世界强国之列的中国和中华民族。</p> ]]></info_content><info_keywords><![CDATA[]]></info_keywords><info_description><![CDATA[]]></info_description><title><![CDATA[铭记党史感党恩]]></title><model_id><![CDATA[11]]></model_id><info_status><![CDATA[8]]></info_status><author><![CDATA[余丽]]></author><final_status><![CDATA[0]]></final_status><content_url><![CDATA[/zwdt/bmzt/8573.htm]]></content_url><source><![CDATA[昌吉日报]]></source></r_content></info></infoList></cicrowcm>";
/* 425 */     sendInfoHandlFormThirdParty(xml);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendInfoDispose
 * JD-Core Version:    0.6.2
 */