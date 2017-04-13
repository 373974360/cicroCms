/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.BeanToMapUtil;
/*     */ import com.cicro.util.CryptoTools;
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.Encode;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.cms.info.PicItemBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendConfigBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.sendInfo.SendRecordDAO;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import com.cicro.wcm.webServices.sendInfo.ISendInfo;
/*     */ import com.cicro.wcm.webServices.sendInfo.SendClient;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ 
/*     */ public class SendInfoManager
/*     */ {
/*  38 */   private static String empty_item = "opt_dtime,hits,cat_id,modify_user,modify_dtime,app_id,step_id,released_dtime,input_user,lasthit_dtime,is_host,up_dtime,down_dtime,is_auto_down,site_id,input_dtime,from_id,wf_id";
/*     */ 
/*     */   public static String getReceiveCategoryList(String site_domain)
/*     */   {
/*     */     try
/*     */     {
/*  48 */       InputStream in = null;
/*  49 */       in = new URL("http://" + site_domain + "/sendConfig/sendConfig").openStream();
/*  50 */       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/*  51 */       StringBuffer content = new StringBuffer();
/*  52 */       String str = "";
/*  53 */       while ((str = reader.readLine()) != null) {
/*  54 */         content.append(str).append("\n");
/*     */       }
/*     */ 
/*  57 */       return Encode.systemToUtf8(content.toString());
/*     */     }
/*     */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*     */     }
/*  62 */     return "";
/*     */   }
/*     */ 
/*     */   public static String insertSendInfo(List<SendRecordBean> sendRecordList, List<InfoBean> infoList, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  73 */       List srb_list = new ArrayList();
/*  74 */       Map info_map = new HashMap();
/*     */ 
/*  76 */       infoListToMap(infoList, info_map);
/*     */ 
/*  78 */       Map send_map = new HashMap();
/*  79 */       sendRecordListToMap(sendRecordList, send_map);
/*  80 */       Set send_set = send_map.keySet();
/*  81 */       String error_message = "";
/*  82 */       System.out.println("send_map-------" + send_map);
/*     */ 
/*  84 */       for (String site_id : send_set)
/*     */       {
/*  86 */         srb_list.clear();
/*  87 */         System.out.println("site_id-------" + site_id);
/*  88 */         StringBuffer sbf = new StringBuffer();
/*  89 */         List l = (List)send_map.get(site_id);
/*  90 */         String send_site_id = ((SendRecordBean)l.get(0)).getSend_site_id();
/*  91 */         String domain = "";
/*  92 */         String site_name = "";
/*  93 */         if (send_site_id.startsWith("GK"))
/*     */         {
/*  95 */           GKNodeBean gkb = GKNodeManager.getGKNodeBeanByNodeID(send_site_id);
/*  96 */           domain = SiteDomainManager.getDefaultSiteDomainBySiteID(gkb.getRela_site_id());
/*  97 */           site_name = gkb.getNode_name();
/*     */         }
/*     */         else {
/* 100 */           domain = SiteDomainManager.getDefaultSiteDomainBySiteID(send_site_id);
/* 101 */           site_name = SiteManager.getSiteBeanBySiteID(send_site_id).getSite_name();
/*     */         }
/* 103 */         sbf.append("<cicrowcm>");
/* 104 */         sbf.append("<t_site_id><![CDATA[" + site_id + "]]></t_site_id>");
/* 105 */         sbf.append("<t_app_id>cms</t_app_id>");
/* 106 */         sbf.append("<s_site_id><![CDATA[" + send_site_id + "]]></s_site_id>");
/* 107 */         sbf.append("<s_site_domain><![CDATA[" + domain + "]]></s_site_domain>");
/* 108 */         sbf.append("<s_site_name><![CDATA[" + site_name + "]]></s_site_name>");
/* 109 */         sbf.append("<s_user_id><![CDATA[" + ((SendRecordBean)l.get(0)).getSend_user_id() + "]]></s_user_id>");
/* 110 */         sbf.append("<s_user_name><![CDATA[" + UserManager.getUserRealName(new StringBuilder(String.valueOf(((SendRecordBean)l.get(0)).getSend_user_id())).toString()) + "]]></s_user_name>");
/* 111 */         sbf.append("<s_send_dtime><![CDATA[" + ((SendRecordBean)l.get(0)).getSend_time() + "]]></s_send_dtime>");
/* 112 */         sbf.append("<infoList>");
/*     */         Iterator localIterator3;
/* 114 */         for (Iterator localIterator2 = l.iterator(); localIterator2.hasNext(); 
/* 118 */           localIterator3.hasNext())
/*     */         {
/* 114 */           SendRecordBean srb = (SendRecordBean)localIterator2.next();
/*     */ 
/* 116 */           int r_cat_id = srb.getT_cat_id();
/*     */ 
/* 118 */           localIterator3 = infoList.iterator(); continue; InfoBean ib = (InfoBean)localIterator3.next();
/*     */ 
/* 120 */           SendRecordBean newBean = srb.clone();
/* 121 */           newBean.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.SEND_RECORD_TABLE_NAME));
/* 122 */           newBean.setSend_info_id(ib.getInfo_id());
/* 123 */           newBean.setSend_cat_id(ib.getCat_id());
/* 124 */           sbf.append("<info>");
/* 125 */           sbf.append("<r_cat_id>" + r_cat_id + "</r_cat_id>");
/* 126 */           sbf.append("<send_record_id>" + newBean.getId() + "</send_record_id>");
/* 127 */           sbf.append((String)info_map.get(Integer.valueOf(ib.getInfo_id())));
/* 128 */           sbf.append("</info>");
/* 129 */           srb_list.add(newBean);
/*     */         }
/*     */ 
/* 132 */         sbf.append("</infoList>");
/* 133 */         sbf.append("</cicrowcm>");
/* 134 */         CryptoTools ct = new CryptoTools();
/* 135 */         String m_code = ct.encode(send_site_id);
/*     */ 
/* 137 */         if (SendClient.getServicesObj(SendConfigManager.getSendConfigBean(site_id).getSite_domain()).sendInfo(sbf.toString(), m_code))
/*     */         {
/* 140 */           SendRecordDAO.insertSendRecord(srb_list);
/*     */         }
/*     */         else {
/* 143 */           error_message = error_message + "," + ((SendRecordBean)l.get(0)).getT_site_name();
/*     */         }
/*     */       }
/* 146 */       return error_message;
/*     */     }
/*     */     catch (Exception e) {
/* 149 */       e.printStackTrace();
/* 150 */     }return "false";
/*     */   }
/*     */ 
/*     */   public static void sendRecordListToMap(List<SendRecordBean> sendRecordList, Map<String, List<SendRecordBean>> send_map)
/*     */   {
/* 162 */     String current_time = DateUtil.getCurrentDateTime();
/* 163 */     for (SendRecordBean srb : sendRecordList)
/*     */     {
/* 165 */       srb.setSend_time(current_time);
/* 166 */       if (send_map.containsKey(srb.getT_site_id()))
/*     */       {
/* 168 */         ((List)send_map.get(srb.getT_site_id())).add(srb);
/*     */       }
/*     */       else {
/* 171 */         List l = new ArrayList();
/* 172 */         l.add(srb);
/* 173 */         send_map.put(srb.getT_site_id(), l);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void infoListToMap(List<InfoBean> infoList, Map<Integer, String> m)
/*     */   {
/* 186 */     if ((infoList != null) && (!"".equals(infoList)))
/*     */     {
/* 188 */       for (InfoBean info : infoList)
/*     */       {
/* 190 */         Object o = ModelUtil.select(info.getInfo_id(), info.getSite_id(), ModelManager.getModelBean(info.getModel_id()).getModel_ename());
/*     */         try
/*     */         {
/* 193 */           if ("12".equals(BeanUtils.getProperty(o, "model_id")))
/*     */           {
/* 195 */             String content_url = BeanUtils.getProperty(o, "content_url");
/* 196 */             if (content_url.startsWith("/"))
/*     */             {
/* 198 */               String site_id = BeanUtils.getProperty(o, "site_id");
/* 199 */               BeanUtils.setProperty(o, "content_url", "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id) + content_url);
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (IllegalAccessException e) {
/* 204 */           e.printStackTrace();
/*     */         }
/*     */         catch (InvocationTargetException e) {
/* 207 */           e.printStackTrace();
/*     */         }
/*     */         catch (NoSuchMethodException e) {
/* 210 */           e.printStackTrace();
/*     */         }
/* 212 */         String xml = ObjectToXMLStr(o);
/* 213 */         if ((xml != null) && (!"".equals(xml)))
/*     */         {
/* 215 */           m.put(Integer.valueOf(info.getInfo_id()), xml);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String ObjectToXMLStr(Object o)
/*     */   {
/*     */     try
/*     */     {
/* 230 */       StringBuffer sbf = new StringBuffer();
/* 231 */       Map m = BeanToMapUtil.convertBean(o);
/* 232 */       if (m != null)
/*     */       {
/* 234 */         Set set = m.keySet();
/* 235 */         sbf.append("<r_content>");
/* 236 */         for (String key : set)
/*     */         {
/* 238 */           if ("item_list".equals(key))
/*     */           {
/* 240 */             List item_list = (List)m.get(key);
/* 241 */             if ((item_list != null) && (item_list.size() > 0))
/*     */             {
/* 243 */               sbf.append("<item_list>");
/* 244 */               for (PicItemBean pitem : item_list)
/*     */               {
/* 246 */                 sbf.append("<pic_item>");
/* 247 */                 sbf.append("<info_id></info_id>");
/* 248 */                 sbf.append("<att_id></att_id>");
/* 249 */                 sbf.append("<pic_path><![CDATA[" + pitem.getPic_path() + "]]></pic_path>");
/* 250 */                 sbf.append("<pic_note><![CDATA[" + pitem.getPic_note() + "]]></pic_note>");
/* 251 */                 sbf.append("<pic_url><![CDATA[" + pitem.getPic_url() + "]]></pic_url>");
/* 252 */                 sbf.append("<pic_sort>" + pitem.getPic_sort() + "</pic_sort>");
/* 253 */                 sbf.append("<pic_title><![CDATA[" + pitem.getPic_title() + "]]></pic_title>");
/* 254 */                 sbf.append("</pic_item>");
/*     */               }
/* 256 */               sbf.append("</item_list>");
/*     */             }
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 281 */             String value = m.get(key);
/* 282 */             if (empty_item.contains(key))
/* 283 */               value = "";
/* 284 */             sbf.append("<" + key + "><![CDATA[" + value + "]]></" + key + ">");
/*     */           }
/*     */         }
/* 287 */         sbf.append("</r_content>");
/*     */       }
/* 289 */       return sbf.toString();
/*     */     }
/*     */     catch (Exception e) {
/* 292 */       e.printStackTrace();
/* 293 */     }return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendInfoManager
 * JD-Core Version:    0.6.2
 */