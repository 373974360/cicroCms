/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.BeanToMapUtil;
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.xml.XmlManager;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.info.PicItemBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*     */ import com.cicro.wcm.dao.sendInfo.ReceiveInfoDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.cms.info.ModelConfig;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*     */ import com.cicro.wcm.webServices.sendInfo.ISendInfo;
/*     */ import com.cicro.wcm.webServices.sendInfo.SendClient;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class ReceiveInfoManager
/*     */ {
/*     */   public static List<ReceiveInfoBean> getReceiveCateInfoList(String site_id)
/*     */   {
/*  44 */     List l = ReceiveInfoDAO.getReceiveCateInfoList(site_id);
/*  45 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  47 */       for (ReceiveInfoBean rib : l) {
/*     */         try
/*     */         {
/*  50 */           CategoryBean cb = CategoryManager.getCategoryBeanCatID(rib.getCat_id(), site_id);
/*  51 */           rib.setCat_cname(cb.getCat_cname());
/*     */         }
/*     */         catch (Exception e) {
/*  54 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  59 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getReceiveInfoCount(Map<String, String> m)
/*     */   {
/*  69 */     return ReceiveInfoDAO.getReceiveInfoCount(m);
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getReceiveInfoList(Map<String, String> m)
/*     */   {
/*  79 */     return ReceiveInfoDAO.getReceiveInfoList(m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteReceiveInfo(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  89 */     return ReceiveInfoDAO.deleteReceiveInfo(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean adoptReceiveInfo(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  99 */     Map result_map = new HashMap();
/*     */ 
/* 101 */     m.put("adopt_user", stl.getUser_id());
/* 102 */     m.put("adopt_dtime", DateUtil.getCurrentDateTime());
/* 103 */     if ("1".equals(m.get("adopt_status")))
/*     */     {
/* 106 */       if (!adoptReceiveInfoHandl(m, result_map, stl))
/*     */       {
/* 108 */         return false;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 113 */       dontAdoptRInfoHandl(m, result_map, stl);
/*     */     }
/* 115 */     if (ReceiveInfoDAO.adoptReceiveInfo(m, stl))
/*     */     {
/* 117 */       returnRecord(m, result_map);
/* 118 */       return true;
/*     */     }
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   public static void dontAdoptRInfoHandl(Map<String, String> m, Map<String, String> result_map, SettingLogsBean stl)
/*     */   {
/* 131 */     List l = ReceiveInfoDAO.getReceiveInfoListForIDS(m);
/* 132 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 134 */       for (ReceiveInfoBean rib : l)
/*     */       {
/* 137 */         if (rib.getIs_record() == 0)
/*     */         {
/* 139 */           if (result_map.containsKey("s_site_domain"))
/*     */           {
/* 141 */             String temp_id = (String)result_map.get("domain") + "," + rib.getSend_record_id();
/* 142 */             result_map.put(rib.getS_site_domain(), temp_id);
/*     */           } else {
/* 144 */             result_map.put(rib.getS_site_domain(), rib.getSend_record_id());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean adoptReceiveInfoHandl(Map<String, String> m, Map<String, String> result_map, SettingLogsBean stl)
/*     */   {
/* 158 */     List l = ReceiveInfoDAO.getReceiveInfoListForIDS(m);
/* 159 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 161 */       for (ReceiveInfoBean rib : l) {
/*     */         try
/*     */         {
/* 164 */           int new_id = InfoBaseManager.getNextInfoId();
/* 165 */           String model_ename = ModelManager.getModelEName(rib.getModel_id());
/* 166 */           Object o = xmlToObject(rib.getContent(), model_ename, new_id);
/* 167 */           if (o != null)
/*     */           {
/* 169 */             BeanUtils.setProperty(o, "id", Integer.valueOf(new_id));
/* 170 */             BeanUtils.setProperty(o, "info_id", Integer.valueOf(new_id));
/* 171 */             BeanUtils.setProperty(o, "site_id", rib.getSite_id());
/* 172 */             BeanUtils.setProperty(o, "cat_id", Integer.valueOf(rib.getCat_id()));
/* 173 */             BeanUtils.setProperty(o, "input_user", m.get("adopt_user"));
/* 174 */             BeanUtils.setProperty(o, "editor", UserManager.getUserRealName((String)m.get("adopt_user")));
/* 175 */             BeanUtils.setProperty(o, "input_dtime", m.get("adopt_dtime"));
/* 176 */             BeanUtils.setProperty(o, "weight", Integer.valueOf(60));
/* 177 */             BeanUtils.setProperty(o, "info_status", m.get("info_status"));
/* 178 */             BeanUtils.setProperty(o, "app_id", "cms");
/* 179 */             BeanUtils.setProperty(o, "from_id", "0");
/*     */ 
/* 181 */             if (!"12".equals(BeanUtils.getProperty(o, "model_id")))
/* 182 */               BeanUtils.setProperty(o, "content_url", "");
/* 183 */             if (ModelUtil.insert(o, model_ename, stl))
/*     */             {
/* 186 */               if (rib.getIs_record() == 0)
/*     */               {
/* 188 */                 if (result_map.containsKey("s_site_domain"))
/*     */                 {
/* 190 */                   String temp_id = (String)result_map.get("domain") + "," + rib.getSend_record_id();
/* 191 */                   result_map.put(rib.getS_site_domain(), temp_id);
/*     */                 } else {
/* 193 */                   result_map.put(rib.getS_site_domain(), rib.getSend_record_id());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 200 */           e.printStackTrace();
/* 201 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 205 */     return true;
/*     */   }
/*     */ 
/*     */   public static Object xmlToObject(String xml, String model_ename, int new_id)
/*     */   {
/*     */     try
/*     */     {
/* 216 */       xml = xml.replaceAll("\n|\r\n", "");
/* 217 */       Node node = XmlManager.createNode(xml);
/*     */ 
/* 219 */       Map info_map = new HashMap();
/* 220 */       xmlToMap(node, info_map, new_id);
/* 221 */       System.out.println("info_map=============" + info_map);
/*     */ 
/* 223 */       System.out.println("getModelMap=============" + ModelConfig.getModelMap(model_ename));
/* 224 */       return BeanToMapUtil.convertMap(Class.forName((String)ModelConfig.getModelMap(model_ename).get("class_name")), info_map);
/*     */     }
/*     */     catch (ParserConfigurationException e) {
/* 227 */       e.printStackTrace();
/*     */     }
/*     */     catch (SAXException e)
/*     */     {
/* 231 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/* 234 */       e.printStackTrace();
/*     */     }
/*     */     catch (TransformerException e) {
/* 237 */       e.printStackTrace();
/*     */     }
/*     */     catch (IntrospectionException e) {
/* 240 */       e.printStackTrace();
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 243 */       e.printStackTrace();
/*     */     }
/*     */     catch (InstantiationException e) {
/* 246 */       e.printStackTrace();
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 249 */       e.printStackTrace();
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/* 252 */       e.printStackTrace();
/*     */     }
/* 254 */     return null;
/*     */   }
/*     */ 
/*     */   public static void xmlToMap(Node content_node, Map<String, Object> m, int new_id)
/*     */   {
/*     */     try
/*     */     {
/* 268 */       for (int i = 0; i < content_node.getChildNodes().getLength(); i++)
/*     */       {
/* 270 */         Node n = content_node.getChildNodes().item(i);
/* 271 */         String keys = n.getNodeName();
/* 272 */         if ("item_list".equals(keys))
/*     */         {
/* 274 */           List item_list = new ArrayList();
/* 275 */           for (int j = 0; j < n.getChildNodes().getLength(); j++)
/*     */           {
/* 277 */             Node item_node = n.getChildNodes().item(j);
/* 278 */             PicItemBean pib = new PicItemBean();
/* 279 */             pib.setInfo_id(new_id);
/* 280 */             pib.setPic_path(FormatUtil.formatNullString(XmlManager.queryNodeValue(item_node, "pic_path")));
/* 281 */             pib.setPic_note(FormatUtil.formatNullString(XmlManager.queryNodeValue(item_node, "pic_note")));
/* 282 */             pib.setPic_sort(Integer.parseInt(XmlManager.queryNodeValue(item_node, "pic_sort")));
/* 283 */             pib.setPic_title(FormatUtil.formatNullString(XmlManager.queryNodeValue(item_node, "pic_title")));
/* 284 */             pib.setPic_url(FormatUtil.formatNullString(XmlManager.queryNodeValue(item_node, "pic_url")));
/* 285 */             System.out.println(pib.getPic_url() + " " + pib.getPic_path() + " " + pib.getPic_note() + " " + pib.getPic_sort() + " " + pib.getPic_title());
/* 286 */             item_list.add(pib);
/*     */           }
/* 288 */           m.put("item_list", item_list);
/*     */         }
/*     */         else
/*     */         {
/* 308 */           String values = XmlManager.queryNodeValue(n, "//" + keys);
/* 309 */           if (values != null)
/* 310 */             m.put(keys, values);
/*     */         }
/*     */       }
/* 313 */       if (m.containsKey("item_list"))
/*     */       {
/* 315 */         List item_list = (List)m.get("item_list");
/* 316 */         if ((item_list != null) && (item_list.size() > 0))
/*     */         {
/* 318 */           ((PicItemBean)item_list.get(0)).setPic_content(m.get("pic_content"));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 323 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void returnRecord(Map<String, String> m, Map<String, String> result_map)
/*     */   {
/* 334 */     if ((result_map != null) && (result_map.size() > 0))
/*     */     {
/* 336 */       String adopt_dtime = (String)m.get("adopt_dtime");
/* 337 */       String adopt_desc = (String)m.get("adopt_desc");
/* 338 */       String adopt_status = (String)m.get("adopt_status");
/*     */ 
/* 340 */       Set domain_set = result_map.keySet();
/* 341 */       for (String domain : domain_set)
/*     */       {
/* 343 */         StringBuffer xml = new StringBuffer();
/* 344 */         String result_ids = (String)result_map.get(domain);
/* 345 */         xml.append("<cicrowcm>");
/* 346 */         xml.append("<result_ids>" + result_ids + "</result_ids>");
/* 347 */         xml.append("<adopt_dtime>" + adopt_dtime + "</adopt_dtime>");
/* 348 */         xml.append("<adopt_desc>" + adopt_desc + "</adopt_desc>");
/* 349 */         xml.append("<adopt_status>" + adopt_status + "</adopt_status>");
/* 350 */         xml.append("</cicrowcm>");
/* 351 */         SendClient.getServicesObj(domain).recordAdoptStatus(xml.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 358 */     xmlToObject("<r_content><pre_title/><info_keywords/><opt_dtime/><month_hits>1</month_hits><info_description/><i_ver>0</i_ver><cat_id/><modify_user/><cat_cname/><description/><title_color/><title_hashkey>0</title_hashkey><is_pic>0</is_pic><item_list><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121007.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>1</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121022.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>2</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121024.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>3</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121027.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>4</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121030.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>5</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121033.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>6</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121036.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>7</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121040.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>8</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121043.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>9</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121047.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>10</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121051.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>11</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121055.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>12</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011121059.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>13</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122002.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>14</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122004.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>15</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122007.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>16</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122010.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>17</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122014.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>18</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122017.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>19</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122021.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>20</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122024.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>21</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122027.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>22</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122030.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>23</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122033.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>24</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122037.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>25</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122041.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>26</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122045.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>27</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122048.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>28</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item><pic_item><info_id/><att_id/><pic_path>http://img.cicro.net/HIWCMdemo/201110/201110011122051.jpg</pic_path><pic_note>09款雷克萨斯LS</pic_note><pic_url/><pic_sort>29</pic_sort><pic_title>雷克萨斯LS</pic_title></pic_item></item_list><modify_dtime/><pic_content>&lt;div&gt;雷克萨斯（Lexus）是日本丰田汽车公司旗下的豪华车品牌，它于1983年被首次提出，但仅用十几年的时间，自1999年起，在美国的销量超过奔驰、宝马，成为全美豪华车销量最大的品牌。过去，Lexus在国内的中文译名是凌志，2004年6月8日，丰田公司在北京宣布将Lexus的中文译名由“凌志”改为“雷克萨斯”，并开始在中国建立特许经销店，开始全面进军中国豪华车市。&lt;/div&gt;&lt;div&gt;&lt;div&gt;用椭圆环绕的L字母，根据美国丰田汽车销售公司的官方说法，这个椭圆弧度依照精确的数学公式修 &amp;nbsp;雷克萨斯标志&lt;/div&gt;&lt;div&gt;饰，动用三个以上的设计商和广告商，花了半年多的时间才完成：这个脱颖而出的标志，击败了五个设计比稿。1987年，摩利设计公司(Molly DesignsInc.)负责人摩利·山德斯(MollySanders)，花了三个月的时间精巧制作出这个别具特色的椭圆和L，取代原先最有希望获选的版本——一个没有圆圈环绕，看起来像海鸥翅膀的L。&amp;nbsp;&lt;/div&gt;&lt;/div&gt;&lt;div&gt;&lt;div&gt;豪华硬顶敞篷运动型轿车-雷克萨斯IS 300C(图库论坛)近乎完美的诠释了精致运动之美。精致，大到四 &amp;nbsp;雷克萨斯IS 300C&lt;/div&gt;&lt;div&gt;年十万公里免费保修保养、Mark Levinson顶级音响，硬顶敞篷双模式；小到为安全可单开的驾驶门，更有超越想象的G-BOOK智能副驾，LEXUS招牌式的精致与豪华体贴到骨头里。运动，它前225后245的宽胎，前双叉臂后多连杆的悬架系统，加上拨片换挡等跑车元素，让您更深刻的感受驾驭的乐趣。 　　激情，舒适，优雅，随性，雷克萨斯IS300C，为都市时尚女性准备的完美座驾。&lt;/div&gt;&lt;div&gt;&lt;br /&gt;&lt;/div&gt;&lt;/div&gt;&lt;div&gt;&lt;br /&gt;&lt;/div&gt;</pic_content><hits/><editor/><model_id>10</model_id><info_status>8</info_status><comment_num>0</comment_num><app_id/><is_allow_comment>0</is_allow_comment><weight>60</weight><thumb_url/><day_hits>1</day_hits><auto_desc/><page_count>0</page_count><top_title/><id/><author>宋玉锋</author><title>雷克萨斯LS 豪华硬顶敞篷运动型轿车</title><is_auto_up>0</is_auto_up><week_hits>1</week_hits><step_id/><is_am_tupage>0</is_am_tupage><input_user/><released_dtime/><lasthit_dtime/><is_host/><tags/><up_dtime/><is_auto_down/><down_dtime/><info_id>1282</info_id><site_id/><input_dtime/><final_status>0</final_status><content_url>/tmzf/zwdt/tpxw/1282.htm</content_url><source>时光研发布</source><from_id/><wf_id/><subtitle/><tupage_num>0</tupage_num></r_content>", "article", 15);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.ReceiveInfoManager
 * JD-Core Version:    0.6.2
 */