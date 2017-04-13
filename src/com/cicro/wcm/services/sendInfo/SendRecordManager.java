/*    */ package com.cicro.wcm.services.sendInfo;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.util.xml.XmlManager;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*    */ import com.cicro.wcm.dao.sendInfo.SendRecordDAO;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.xml.parsers.ParserConfigurationException;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.w3c.dom.Node;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ public class SendRecordManager
/*    */ {
/*    */   public static String getSendRecordCount(Map<String, String> m)
/*    */   {
/* 28 */     return SendRecordDAO.getSendRecordCount(m);
/*    */   }
/*    */ 
/*    */   public static List<SendRecordBean> getSendRecordList(Map<String, String> m)
/*    */   {
/* 38 */     return SendRecordDAO.getSendRecordList(m);
/*    */   }
/*    */ 
/*    */   public static boolean updateSendRecord(Map<String, String> m)
/*    */   {
/* 48 */     return SendRecordDAO.updateSendRecord(m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteSendRecord(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 59 */     return SendRecordDAO.deleteSendRecord(m, stl);
/*    */   }
/*    */ 
/*    */   public static boolean recordAdoptStatusHandl(String xml)
/*    */   {
/*    */     try
/*    */     {
/* 71 */       Node node = XmlManager.createNode(xml);
/* 72 */       Map m = new HashMap();
/* 73 */       m.put("ids", XmlManager.queryNodeValue(node, "//result_ids"));
/* 74 */       m.put("adopt_status", XmlManager.queryNodeValue(node, "//adopt_status"));
/* 75 */       m.put("adopt_dtime", XmlManager.queryNodeValue(node, "//adopt_dtime"));
/* 76 */       m.put("adopt_desc", FormatUtil.formatNullString(XmlManager.queryNodeValue(node, "//adopt_desc")));
/* 77 */       return updateSendRecord(m);
/*    */     }
/*    */     catch (ParserConfigurationException e) {
/* 80 */       e.printStackTrace();
/*    */     }
/*    */     catch (SAXException e) {
/* 83 */       e.printStackTrace();
/*    */     }
/*    */     catch (IOException e) {
/* 86 */       e.printStackTrace();
/*    */     }
/*    */     catch (TransformerException e) {
/* 89 */       e.printStackTrace();
/*    */     }
/* 91 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendRecordManager
 * JD-Core Version:    0.6.2
 */