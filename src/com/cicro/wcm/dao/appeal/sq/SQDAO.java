/*     */ package com.cicro.wcm.dao.appeal.sq;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.appeal.count.CountBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.ProcessBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQAttachment;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQCustom;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.TagsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SQDAO
/*     */ {
/*     */   public static List<CountBean> getSQStatisticsForModel(Map<String, String> m)
/*     */   {
/*  38 */     return DBManager.queryFList("getSQStatisticsForModel", m);
/*     */   }
/*     */ 
/*     */   public static String getSQStatistics(Map<String, String> m)
/*     */   {
/*  48 */     return DBManager.getString("getSQStatistics", m);
/*     */   }
/*     */ 
/*     */   public static String getSQSatisfactionCount(Map<String, String> m)
/*     */   {
/*  58 */     return DBManager.getString("getSQSatisfactionCount", m);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getSQSatisfaction(Map<String, String> m)
/*     */   {
/*  69 */     return DBManager.queryFList("getSQSatisfaction", m);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getSqFinishCountForDept(Map<String, String> m)
/*     */   {
/*  80 */     return DBManager.queryFList("getSqFinishCountForDept", m);
/*     */   }
/*     */ 
/*     */   public static synchronized int getSQYearNum()
/*     */   {
/*  90 */     Map m = new HashMap();
/*  91 */     m.put("current_year", DateUtil.getCurrentDateTime("yyyy"));
/*  92 */     String num = DBManager.getString("getSQYearNum", m);
/*  93 */     if ((num != null) && (!"".equals(num))) {
/*  94 */       return Integer.parseInt(num);
/*     */     }
/*  96 */     return 1;
/*     */   }
/*     */ 
/*     */   public static synchronized int getSQNumber(int model_id, String year)
/*     */   {
/* 106 */     Map m = new HashMap();
/* 107 */     m.put("model_id", model_id);
/* 108 */     m.put("current_year", year);
/* 109 */     String num = DBManager.getString("getSQNumber", m);
/* 110 */     if ((num != null) && (!"".equals(num))) {
/* 111 */       return Integer.parseInt(num);
/*     */     }
/* 113 */     return 1;
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getBroSQListByMemberID(String me_id)
/*     */   {
/* 126 */     return DBManager.queryFList("getBroSQListByMemberID", me_id);
/*     */   }
/*     */ 
/*     */   public static String getBrowserSQCount(Map<String, String> m)
/*     */   {
/* 136 */     return DBManager.getString("getBrowserSQCount", m);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getBrowserSQList(Map<String, String> m)
/*     */   {
/* 147 */     return DBManager.queryFList("getBrowserSQList", m);
/*     */   }
/*     */ 
/*     */   public static SQBean getBrowserSQBean(int sq_id, String me_id)
/*     */   {
/* 158 */     Map m = new HashMap();
/* 159 */     m.put("sq_id", sq_id);
/* 160 */     if (!"".equals(me_id))
/* 161 */       m.put("me_id", me_id);
/* 162 */     return (SQBean)DBManager.queryFObj("getBrowserSQBean", m);
/*     */   }
/*     */ 
/*     */   public static SQBean getSQSimpleBean(int sq_id)
/*     */   {
/* 167 */     return (SQBean)DBManager.queryFObj("getSQSimpleBean", Integer.valueOf(sq_id));
/*     */   }
/*     */ 
/*     */   public static SQBean searchBrowserSQBean(Map<String, String> m)
/*     */   {
/* 177 */     return (SQBean)DBManager.queryFObj("searchBrowserSQBean", m);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getNotEndSQList()
/*     */   {
/* 188 */     return DBManager.queryFList("getNotEndSQList", "");
/*     */   }
/*     */ 
/*     */   public static String getSqCount(Map<String, String> m)
/*     */   {
/* 198 */     return DBManager.getString("getSqCount", m);
/*     */   }
/*     */ 
/*     */   public static String getTransactSQCount(Map<String, String> m)
/*     */   {
/* 208 */     return DBManager.getString("getTransactSQCount", m);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getTransactSQList(Map<String, String> m)
/*     */   {
/* 219 */     return DBManager.queryFList("getTransactSQList", m);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getSqList(Map<String, String> m)
/*     */   {
/* 230 */     return DBManager.queryFList("getSqList", m);
/*     */   }
/*     */ 
/*     */   public static SQBean getSqBean(int sq_id)
/*     */   {
/* 240 */     return (SQBean)DBManager.queryFObj("getSqBean", Integer.valueOf(sq_id));
/*     */   }
/*     */ 
/*     */   public static List<SQCustom> getSQCustomList(int sq_id)
/*     */   {
/* 251 */     return DBManager.queryFList("getSQCustomList", Integer.valueOf(sq_id));
/*     */   }
/*     */ 
/*     */   public static String getSQCustomValue(int sq_id, String cu_key)
/*     */   {
/* 262 */     Map m = new HashMap();
/* 263 */     m.put("sq_id", sq_id);
/* 264 */     m.put("cu_key", cu_key);
/* 265 */     return DBManager.getString("getSQCustomValue", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertSQ(SQBean sb)
/*     */   {
/* 275 */     return DBManager.insert("insert_sq", sb);
/*     */   }
/*     */ 
/*     */   public static boolean insertSQCursom(List<SQCustom> l)
/*     */   {
/* 285 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 287 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 289 */         ((SQCustom)l.get(i)).setCu_id(PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_SQCUSTOM_TABLE_NAME));
/* 290 */         DBManager.insert("insert_sq_custom", l.get(i));
/*     */       }
/*     */     }
/* 293 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean setSQClickCount(Map<String, Object> m)
/*     */   {
/* 303 */     return DBManager.update("setSQClickCount", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSQ(SQBean sb, SettingLogsBean stl)
/*     */   {
/* 314 */     if (DBManager.update("update_sq", sb))
/*     */     {
/* 316 */       PublicTableDAO.insertSettingLogs("修改", "信件", sb.getSq_id(), stl);
/* 317 */       return true;
/*     */     }
/* 319 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateProcessNote(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 330 */     if (DBManager.update("update_sq_process", m))
/*     */     {
/* 332 */       PublicTableDAO.insertSettingLogs("修改", "信件审核内容", (String)m.get("pro_id"), stl);
/* 333 */       return true;
/*     */     }
/* 335 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSQCustom(List<SQCustom> l)
/*     */   {
/* 345 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 347 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 349 */         DBManager.update("update_sq_custom", l.get(i));
/*     */       }
/*     */     }
/* 352 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean updateStatus(Map<String, String> m)
/*     */   {
/* 362 */     return DBManager.update("update_sq_status", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSQ(String sq_ids, SettingLogsBean stl)
/*     */   {
/* 373 */     Map m = new HashMap();
/* 374 */     m.put("sq_ids", sq_ids);
/*     */ 
/* 376 */     if (DBManager.delete("delete_sq_byID", m))
/*     */     {
/* 378 */       DBManager.delete("delete_sq_tag", m);
/* 379 */       DBManager.delete("delete_sq_process", m);
/* 380 */       DBManager.delete("delete_sq_atta", m);
/* 381 */       PublicTableDAO.insertSettingLogs("删除", "信件", sq_ids, stl);
/* 382 */       return true;
/*     */     }
/* 384 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getReduplicateSQList(Map<String, String> m)
/*     */   {
/* 395 */     return DBManager.queryFList("getReduplicateSQList", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSQByModel(String model_ids)
/*     */   {
/* 405 */     Map m = new HashMap();
/* 406 */     m.put("model_ids", model_ids);
/*     */     try {
/* 408 */       DBManager.delete("delete_tag_byModelID", m);
/* 409 */       DBManager.delete("delete_atta_byModelID", m);
/* 410 */       DBManager.delete("delete_proce_byModelID", m);
/* 411 */       DBManager.delete("delete_sq_byModelID", m);
/* 412 */       DBManager.delete("delete_sq_custom", m);
/* 413 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 416 */       e.printStackTrace();
/* 417 */     }return false;
/*     */   }
/*     */ 
/*     */   public static List<ProcessBean> getPro()
/*     */   {
/* 424 */     return DBManager.queryFList("getPro", "");
/*     */   }
/*     */ 
/*     */   public static List<ProcessBean> getProcessListBySqID(int sq_id)
/*     */   {
/* 436 */     return DBManager.queryFList("getProcessListBySqID", Integer.valueOf(sq_id));
/*     */   }
/*     */ 
/*     */   public static ProcessBean getLastProcessBySqID(String sq_id)
/*     */   {
/* 446 */     return (ProcessBean)DBManager.queryFObj("getLastProcessBySqID", sq_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertProcess(ProcessBean pb)
/*     */   {
/* 456 */     return DBManager.insert("insert_sq_process", pb);
/*     */   }
/*     */ 
/*     */   public static boolean deleteProcessByProID(int pro_id)
/*     */   {
/* 466 */     return DBManager.delete("delete_sq_process_byProid", Integer.valueOf(pro_id));
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getSQTagList(int sq_id)
/*     */   {
/* 480 */     return DBManager.queryFList("getSQTagList", Integer.valueOf(sq_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertSQTag(String sq_id, String tag_ids)
/*     */   {
/* 491 */     if ((tag_ids != null) && (!"".equals(tag_ids))) {
/*     */       try
/*     */       {
/* 494 */         Map m = new HashMap();
/* 495 */         m.put("sq_id", sq_id);
/* 496 */         String[] tempA = tag_ids.split(",");
/* 497 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 499 */           m.put("tag_id", tempA[i]);
/* 500 */           DBManager.insert("insert_sq_tag", m);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 504 */         e.printStackTrace();
/* 505 */         return false;
/*     */       }
/*     */     }
/* 508 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSQTag(String sq_ids)
/*     */   {
/* 518 */     Map m = new HashMap();
/* 519 */     m.put("sq_ids", sq_ids);
/* 520 */     return DBManager.delete("delete_sq_tag", m);
/*     */   }
/*     */ 
/*     */   public static List<SQAttachment> getSQAttachmentList(String sq_ids, String relevance_type)
/*     */   {
/* 534 */     Map m = new HashMap();
/* 535 */     m.put("sq_ids", sq_ids);
/* 536 */     m.put("relevance_type", relevance_type);
/* 537 */     return DBManager.queryFList("getSQAttachmentList", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertSQAtta(SQAttachment sqa)
/*     */   {
/* 547 */     return DBManager.insert("insert_sq_atta", sqa);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSQAtta(String sq_id, String relevance_type)
/*     */   {
/* 558 */     Map m = new HashMap();
/* 559 */     m.put("sq_ids", sq_id);
/* 560 */     m.put("relevance_type", relevance_type);
/* 561 */     return DBManager.delete("delete_sq_atta", m);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getAllSQListByYear(String year)
/*     */   {
/* 574 */     Map m = new HashMap();
/* 575 */     m.put("c_year", year);
/* 576 */     return DBManager.queryFList("getAllSQListByYear", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSQNumber(Map<String, String> m)
/*     */   {
/* 581 */     return DBManager.update("update_sq_number", m);
/*     */   }
/*     */ 
/*     */   public static String getModelIdByDept_id(String dept_id)
/*     */   {
/* 587 */     return DBManager.getString("getModelIdByDept_id", dept_id);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getexportData(Map<String, String> m)
/*     */   {
/* 598 */     return DBManager.queryFList("getexportData", m);
/*     */   }
/*     */ 
/*     */   public static boolean batchIsNotOpenOk(Map<String, String> m)
/*     */   {
/* 608 */     return DBManager.update("batchIsNotOpenOk", m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 612 */     System.out.println(getSQCustomList(10));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.sq.SQDAO
 * JD-Core Version:    0.6.2
 */