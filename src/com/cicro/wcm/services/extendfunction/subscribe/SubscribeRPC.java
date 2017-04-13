/*    */ package com.cicro.wcm.services.extendfunction.subscribe;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SubscribeRPC
/*    */ {
/*    */   public static boolean deleteSubscribeUser(String selectIDS)
/*    */   {
/* 10 */     return SubscribeManager.deleteSubscribeUser(selectIDS);
/*    */   }
/*    */ 
/*    */   public static boolean updateSubscribeUserInfo(Map<String, String> m)
/*    */   {
/* 15 */     return SubscribeManager.updateSubscribeUserInfo(m);
/*    */   }
/*    */ 
/*    */   public static boolean checkSameEmailAddress(Map<String, String> m)
/*    */   {
/* 20 */     return SubscribeManager.checkSameEmailAddress(m);
/*    */   }
/*    */ 
/*    */   public static List<SubscribeUserBean> getsubscribeBeanList(Map<String, String> m)
/*    */   {
/* 25 */     return SubscribeManager.getsubscribeUserForList(m);
/*    */   }
/*    */ 
/*    */   public static void getsubscribeBeanListForID(Map<String, String> m, String record_id, String sendTitle)
/*    */   {
/* 30 */     System.out.println("*********************sendTitle*********************" + sendTitle);
/* 31 */     SubscribeManager.getsubscribeBeanListForID(m, record_id, sendTitle);
/*    */   }
/*    */ 
/*    */   public static String getSubscribeBeanListCount()
/*    */   {
/* 36 */     return SubscribeManager.getSubscribeBeanListCount();
/*    */   }
/*    */ 
/*    */   public static List<subscribeRecordBean> getSubscribeRecordBeanList(Map<String, String> m)
/*    */   {
/* 41 */     return SubscribeManager.getSubscribeRecordBeanList(m);
/*    */   }
/*    */ 
/*    */   public static String getSubscribeRecordBeanListCount()
/*    */   {
/* 46 */     return SubscribeManager.getSubscribeRecordBeanListCount();
/*    */   }
/*    */ 
/*    */   public static boolean insertSendRecord(String senduser, String sendTitle)
/*    */   {
/* 51 */     return SubscribeManager.insertSendRecord(senduser, sendTitle);
/*    */   }
/*    */ 
/*    */   public static void getAllsubscribeBeanList(String record_id, String sendTitle)
/*    */   {
/* 56 */     SubscribeManager.getAllsubscribeBeanList(record_id, sendTitle);
/*    */   }
/*    */ 
/*    */   public static String getPreviewSendContentBean(String record_id)
/*    */   {
/* 61 */     return SubscribeManager.getPreviewSendContentBean(record_id);
/*    */   }
/*    */ 
/*    */   public static List<SubscribeUserBean> getchoiceuserBeanList(Map<String, String> m)
/*    */   {
/* 66 */     return SubscribeManager.getchoiceuserBeanList(m);
/*    */   }
/*    */ 
/*    */   public static List<SubscribeUserBean> getOldMailAddress(String id)
/*    */   {
/* 71 */     return SubscribeManager.getOldMailAddress(id);
/*    */   }
/*    */ 
/*    */   public static boolean insertSendContentRecord(String str_id)
/*    */   {
/* 76 */     return SubscribeManager.insertSendContentRecord(str_id);
/*    */   }
/*    */ 
/*    */   public static boolean deletePreviewInfobyId(String id)
/*    */   {
/* 81 */     return SubscribeManager.deletePreviewInfobyId(id);
/*    */   }
/*    */ 
/*    */   public static boolean insertAddSendInfo(String record_id, String conAdd_id)
/*    */   {
/* 86 */     return SubscribeManager.insertAddSendInfo(record_id, conAdd_id);
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.subscribe.SubscribeRPC
 * JD-Core Version:    0.6.2
 */