/*     */ package com.cicro.wcm.services.extendfunction.subscribe;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubscribeDAO
/*     */ {
/*  15 */   private static String FIELD_TABLE_NAME = "cs_dz_subscribeuser";
/*  16 */   private static String FIELD_TABLE_NAME_RECORD = "cs_dz_subscriberecord";
/*  17 */   private static String FIELD_TABLE_NAME_RECORD_SENDCONTENT = "cs_dz_sendcontentrecord";
/*     */   private static int record_id;
/*     */ 
/*     */   public static boolean registerSubEmail(String email)
/*     */   {
/*  27 */     Map m = new HashMap();
/*  28 */     String register_date = DateUtil.getDateTimeString(new Date());
/*  29 */     SubscribeUserBean sub = new SubscribeUserBean();
/*  30 */     sub.setId(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME));
/*  31 */     m.put("id", Integer.valueOf(sub.getId()));
/*  32 */     m.put("mail_address", email);
/*  33 */     m.put("registerDate", register_date);
/*  34 */     return DBManager.insert("registerSubEmail", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubscribeUser(Map<String, String> m)
/*     */   {
/*  44 */     return DBManager.delete("deleteSubscribeUser", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubscribeUserInfo(Map<String, String> m)
/*     */   {
/*  54 */     return DBManager.update("updateSubscribeUserInfo", m);
/*     */   }
/*     */ 
/*     */   public static boolean unsubscribeByMailAdd(String mail_address)
/*     */   {
/*  64 */     Map m = new HashMap();
/*  65 */     m.put("mail_address", mail_address);
/*  66 */     return DBManager.delete("unsubscribeByMailAdd", m);
/*     */   }
/*     */ 
/*     */   public static String checkSameEmailAddress(Map<String, String> m)
/*     */   {
/*  76 */     return DBManager.getString("checkSameEmailAddress", m);
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getsubscribeUserForList(Map<String, String> m)
/*     */   {
/*  86 */     return DBManager.queryFList("subscriber.getsubscribeUserBeanList", m);
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getsubscribeBeanListForID(Map<String, String> m)
/*     */   {
/*  96 */     return DBManager.queryFList("getsubscribeBeanListForID", m);
/*     */   }
/*     */ 
/*     */   public static String getSubscribeBeanListCount()
/*     */   {
/* 105 */     return DBManager.getString("getsubscribeUserBeanListCount", "");
/*     */   }
/*     */ 
/*     */   public static List<subscribeRecordBean> getSubscribeRecordBeanList(Map<String, String> m)
/*     */   {
/* 117 */     return DBManager.queryFList("getSubscribeRecordBeanList", m);
/*     */   }
/*     */ 
/*     */   public static String getSubscribeRecordBeanListCount()
/*     */   {
/* 125 */     return DBManager.getString("getSubscribeRecordBeanListCount", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSendRecord(String senduser, String sendTitle)
/*     */   {
/* 136 */     Map m = new HashMap();
/* 137 */     String recordDate = DateUtil.getDateTimeString(new Date());
/* 138 */     subscribeRecordBean srb = new subscribeRecordBean();
/* 139 */     srb.setRecord_id(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME_RECORD));
/* 140 */     record_id = srb.getRecord_id();
/* 141 */     m.put("send_user", senduser);
/* 142 */     m.put("recordDate", recordDate);
/* 143 */     m.put("record_id", Integer.valueOf(srb.getRecord_id()));
/* 144 */     m.put("sendTitle", sendTitle);
/* 145 */     return DBManager.insert("insertSendRecord", m);
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getAllsubscribeBeanList()
/*     */   {
/* 155 */     return DBManager.queryFList("getAllsubscribeBeanList", "");
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getPreviewSendContentBean(String record_id)
/*     */   {
/* 165 */     Map m = new HashMap();
/* 166 */     m.put("record_id", record_id);
/* 167 */     return DBManager.queryFList("getPreviewSendContentBean", m);
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getchoiceuserBeanList(Map<String, String> m)
/*     */   {
/* 177 */     return DBManager.queryFList("getchoiceuserBeanList", m);
/*     */   }
/*     */ 
/*     */   public static List<SubscribeUserBean> getOldMailAddress(String id)
/*     */   {
/* 187 */     Map m = new HashMap();
/* 188 */     m.put("id", id);
/* 189 */     return DBManager.queryFList("getOldMailAddress", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertAddSendInfo(String record_id, String conAdd_id)
/*     */   {
/* 200 */     SubscribeSendRecordBean srd = new SubscribeSendRecordBean();
/* 201 */     Map m = new HashMap();
/* 202 */     m.put("record_id", record_id);
/* 203 */     String[] strid = conAdd_id.split(",");
/* 204 */     if ((strid != null) && (strid.length > 0))
/*     */     {
/* 206 */       for (String sinfo_id : strid)
/*     */       {
/* 208 */         srd.setId(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME_RECORD_SENDCONTENT));
/* 209 */         m.put("id", Integer.valueOf(srd.getId()));
/* 210 */         m.put("sinfo_id", sinfo_id);
/* 211 */         DBManager.insert("insertAddSendInfo", m);
/*     */       }
/* 213 */       return true;
/*     */     }
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSendContentRecord(String str_id)
/*     */   {
/* 226 */     SubscribeSendRecordBean srd = new SubscribeSendRecordBean();
/* 227 */     Map m = new HashMap();
/* 228 */     m.put("record_id", Integer.valueOf(record_id));
/*     */ 
/* 230 */     String[] strid = str_id.split(",");
/* 231 */     if ((strid != null) && (strid.length > 0))
/*     */     {
/* 233 */       for (String sinfo_id : strid)
/*     */       {
/* 235 */         srd.setId(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME_RECORD_SENDCONTENT));
/* 236 */         m.put("id", Integer.valueOf(srd.getId()));
/* 237 */         m.put("sinfo_id", sinfo_id);
/* 238 */         DBManager.insert("insertSendContentRecord", m);
/*     */       }
/* 240 */       return true;
/*     */     }
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deletePreviewInfobyId(String id)
/*     */   {
/* 253 */     Map m = new HashMap();
/* 254 */     m.put("id", id);
/* 255 */     return DBManager.insert("deletePreviewInfobyId", m);
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.subscribe.SubscribeDAO
 * JD-Core Version:    0.6.2
 */