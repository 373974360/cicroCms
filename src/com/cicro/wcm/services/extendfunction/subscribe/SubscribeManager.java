/*     */ package com.cicro.wcm.services.extendfunction.subscribe;
/*     */ 
/*     */ import com.cicro.util.mail.MailUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubscribeManager
/*     */ {
/*     */   public static boolean registerSubEmail(String email)
/*     */   {
/*  18 */     return SubscribeDAO.registerSubEmail(email);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubscribeUser(String selectIDS)
/*     */   {
/*  28 */     Map m = new HashMap();
/*  29 */     m.put("ids", selectIDS);
/*  30 */     return SubscribeDAO.deleteSubscribeUser(m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubscribeUserInfo(Map<String, String> m)
/*     */   {
/*  40 */     return SubscribeDAO.updateSubscribeUserInfo(m);
/*     */   }
/*     */ 
/*     */   public static boolean unsubscribeByMailAdd(String mail_address)
/*     */   {
/*  50 */     return SubscribeDAO.unsubscribeByMailAdd(mail_address);
/*     */   }
/*     */ 
/*     */   public static boolean checkSameEmailAddress(Map<String, String> m)
/*     */   {
/*  61 */     String count = SubscribeDAO.checkSameEmailAddress(m);
/*  62 */     if (Integer.parseInt(count) > 0) {
/*  63 */       return true;
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getsubscribeUserForList(Map<String, String> m)
/*     */   {
/*  74 */     return SubscribeDAO.getsubscribeUserForList(m);
/*     */   }
/*     */ 
/*     */   public static void getsubscribeBeanListForID(Map<String, String> m, String record_id, String sendTitle)
/*     */   {
/*  79 */     List<SubscribeUserBean> choiceuserlist = SubscribeDAO.getsubscribeBeanListForID(m);
/*  80 */     String sendMail_address = "";
/*  81 */     for (SubscribeUserBean choiceUS : choiceuserlist)
/*     */     {
/*  83 */       if (sendMail_address != "")
/*     */       {
/*  85 */         sendMail_address = sendMail_address + ",";
/*     */       }
/*  87 */       sendMail_address = sendMail_address + choiceUS.getMail_address();
/*     */     }
/*  89 */     createTableHtml(record_id, sendMail_address, sendTitle);
/*     */   }
/*     */ 
/*     */   public static String getSubscribeBeanListCount()
/*     */   {
/*  98 */     return SubscribeDAO.getSubscribeBeanListCount();
/*     */   }
/*     */ 
/*     */   public static List<subscribeRecordBean> getSubscribeRecordBeanList(Map<String, String> m)
/*     */   {
/* 105 */     return SubscribeDAO.getSubscribeRecordBeanList(m);
/*     */   }
/*     */ 
/*     */   public static String getSubscribeRecordBeanListCount()
/*     */   {
/* 113 */     return SubscribeDAO.getSubscribeRecordBeanListCount();
/*     */   }
/*     */ 
/*     */   public static boolean insertSendRecord(String senduser, String sendTitle)
/*     */   {
/* 124 */     return SubscribeDAO.insertSendRecord(senduser, sendTitle);
/*     */   }
/*     */ 
/*     */   public static void getAllsubscribeBeanList(String record_id, String sendTitle) {
/* 128 */     List<SubscribeUserBean> list = SubscribeDAO.getAllsubscribeBeanList();
/* 129 */     String sendAll_Address = "";
/* 130 */     for (SubscribeUserBean sendMailAdd : list)
/*     */     {
/* 132 */       if (sendAll_Address != "")
/*     */       {
/* 134 */         sendAll_Address = sendAll_Address + ",";
/*     */       }
/* 136 */       sendAll_Address = sendAll_Address + sendMailAdd.getMail_address();
/*     */     }
/* 138 */     createTableHtml(record_id, sendAll_Address, sendTitle);
/*     */   }
/*     */ 
/*     */   public static String getPreviewSendContentBean(String record_id)
/*     */   {
/* 143 */     List previewlist = SubscribeDAO.getPreviewSendContentBean(record_id);
/* 144 */     String sub_send_table = "";
/* 145 */     String strHtml_cms_table = "<table style='border-collapse:collapse; border-spacing:0; border-left:1px solid #888; border-top:1px solid #888' width='750'><thead><tr>    <th style='font-weight:bold;background:#ccc; border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' colspan='4'>新闻</th> </tr><tr>    <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='50%'>标题</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='15%'>栏目</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='25%'>发布时间</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='10%'>操作</th> </tr></thead><tbody>";
/*     */ 
/* 159 */     String strHtml_GK_table = "<table style='border-collapse:collapse; border-spacing:0; border-left:1px solid #888; border-top:1px solid #888' width='750'><thead><tr>    <th style='font-weight:bold;background:#ccc; border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' colspan='4'>公开</th> </tr><tr>    <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='50%'>标题</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='15%'>栏目</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='25%'>发布时间</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;' width='10%'>操作</th> </tr></thead><tbody>";
/*     */ 
/* 174 */     if ((previewlist != null) && (previewlist.size() > 0))
/*     */     {
/* 176 */       for (int i = 0; i < previewlist.size(); i++)
/*     */       {
/* 178 */         if (((InfoBean)previewlist.get(i)).getApp_id().equals("cms"))
/*     */         {
/* 180 */           strHtml_cms_table = strHtml_cms_table + "<tr><td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + 
/* 181 */             ((InfoBean)previewlist.get(i)).getTitle() + "</td>" + 
/* 182 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)previewlist.get(i)).getCat_cname() + "</td>  " + 
/* 183 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)previewlist.get(i)).getInput_dtime() + "</td>" + 
/* 184 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'><a href='javascript:deletePreviewInfo(" + ((InfoBean)previewlist.get(i)).getId() + ")' style='text-decoration:none'>删除</td>" + 
/* 185 */             "</tr>";
/*     */         }
/* 187 */         else strHtml_GK_table = strHtml_GK_table + "<tr><td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + 
/* 188 */             ((InfoBean)previewlist.get(i)).getTitle() + "</td>" + 
/* 189 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)previewlist.get(i)).getCat_cname() + "</td>  " + 
/* 190 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)previewlist.get(i)).getInput_dtime() + "</td>" + 
/* 191 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'><a href='javascript:deletePreviewInfo(" + ((InfoBean)previewlist.get(i)).getId() + ")' style='text-decoration:none'>删除</td>" + 
/* 192 */             "</tr>";
/*     */       }
/*     */ 
/* 195 */       strHtml_cms_table = strHtml_cms_table + "</tbody></table>";
/* 196 */       strHtml_GK_table = strHtml_GK_table + "</tbody></table>";
/* 197 */       sub_send_table = strHtml_cms_table + strHtml_GK_table;
/*     */     }
/*     */     else {
/* 200 */       sub_send_table = strHtml_cms_table + strHtml_GK_table;
/*     */     }
/* 202 */     return sub_send_table;
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getchoiceuserBeanList(Map<String, String> m)
/*     */   {
/* 207 */     return SubscribeDAO.getchoiceuserBeanList(m);
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getOldMailAddress(String id)
/*     */   {
/* 212 */     return SubscribeDAO.getOldMailAddress(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertAddSendInfo(String record_id, String conAdd_id)
/*     */   {
/* 217 */     return SubscribeDAO.insertAddSendInfo(record_id, conAdd_id);
/*     */   }
/*     */ 
/*     */   public static void createTableHtml(String record_id, String mail_address, String sendTitle)
/*     */   {
/* 224 */     List sendAllMailList = SubscribeDAO.getPreviewSendContentBean(record_id);
/* 225 */     String strHtml_cms_table = "<table style='border-collapse:collapse; border-spacing:0; border-left:1px solid #888; border-top:1px solid #888' width='750'><thead><tr>    <th style='font-weight:bold;background:#ccc; border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' colspan='3'>新闻</th> </tr><tr>    <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' width='60%'>标题</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' width='15%'>栏目</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' width='25%'>发布时间</th> </tr></thead><tbody>";
/*     */ 
/* 238 */     String strHtml_GK_table = "<table style='border-collapse:collapse; border-spacing:0; border-left:1px solid #888; border-top:1px solid #888' width='750'><thead><tr>    <th style='font-weight:bold;background:#ccc; border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' colspan='3'>公开</th> </tr><tr>    <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' width='60%'>标题</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' width='15%'>栏目</th>   <th style='font-weight:bold;border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;' width='25%'>发布时间</th> </tr></thead><tbody>";
/*     */ 
/* 250 */     String content_utl = "http://www.xahrss.gov.cn";  
/* 251 */     if ((sendAllMailList != null) && (sendAllMailList.size() > 0))
/*     */     {
/* 253 */       for (int i = 0; i < sendAllMailList.size(); i++)
/*     */       {
/* 255 */         if (((InfoBean)sendAllMailList.get(i)).getApp_id().equals("cms"))
/*     */         {
/* 257 */           strHtml_cms_table = strHtml_cms_table + "<tr><td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'><a href=" + 
/* 258 */             content_utl + ((InfoBean)sendAllMailList.get(i)).getContent_url() + " target='blank' style='text-decoration:none'>" + ((InfoBean)sendAllMailList.get(i)).getTitle() + "</a></td>   " + 
/* 259 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)sendAllMailList.get(i)).getCat_cname() + "</td>  " + 
/* 260 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)sendAllMailList.get(i)).getInput_dtime() + "</td></tr>";
/*     */         }
/* 262 */         else strHtml_GK_table = strHtml_GK_table + "<tr><td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'><a href=" + 
/* 263 */             content_utl + ((InfoBean)sendAllMailList.get(i)).getContent_url() + " target='blank' style='text-decoration:none'>" + ((InfoBean)sendAllMailList.get(i)).getTitle() + "</a></td>   " + 
/* 264 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)sendAllMailList.get(i)).getCat_cname() + "</td>  " + 
/* 265 */             "<td style='border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;text-align:center;'>" + ((InfoBean)sendAllMailList.get(i)).getInput_dtime() + "</td></tr>";
/*     */       }
/*     */ 
/* 268 */       strHtml_cms_table = strHtml_cms_table + "</tbody></table>";
/* 269 */       strHtml_GK_table = strHtml_GK_table + "</tbody></table>";
/* 270 */       String sub_send_table = strHtml_cms_table + strHtml_GK_table;
/*     */ 
/* 272 */       sendMailInfo(sub_send_table, mail_address, sendTitle);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendMailInfo(String infoTable, String mailAddress, String sendTitle)
/*     */   {
/* 281 */     String[] mail_Address = mailAddress.split(",");
/* 282 */     for (String mailAdd : mail_Address)
/*     */     {
/* 284 */       MailUtil.mailutil(mailAdd, sendTitle, infoTable);
/*     */       try {
/* 286 */         Thread.sleep(60000L);
/*     */       } catch (InterruptedException e) {
/* 288 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean insertSendContentRecord(String str_id)
/*     */   {
/* 296 */     return SubscribeDAO.insertSendContentRecord(str_id);
/*     */   }
/*     */ 
/*     */   public static boolean deletePreviewInfobyId(String id)
/*     */   {
/* 301 */     return SubscribeDAO.deletePreviewInfobyId(id);
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.subscribe.SubscribeManager
 * JD-Core Version:    0.6.2
 */