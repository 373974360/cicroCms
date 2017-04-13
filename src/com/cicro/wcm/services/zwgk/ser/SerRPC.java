/*     */ package com.cicro.wcm.services.zwgk.ser;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerCategoryBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerResouceBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;

/*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SerRPC
/*     */ {
/*     */   public static String getSerCategoryRootJSONTree()
/*     */   {
/*  21 */     return SerCategoryManager.getSerCategoryRootJSONTree();
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictList(int ser_id)
/*     */   {
/*  31 */     return SerCategoryManager.getDataDictList(ser_id);
/*     */   }
/*     */ 
/*     */   public static String getSerCateJSONTree(int ser_id)
/*     */   {
/*  40 */     return SerCategoryManager.getSerCateJSONTree(ser_id);
/*     */   }
/*     */ 
/*     */   public static List<SerCategoryBean> getChildSerCategoryList(int ser_id)
/*     */   {
/*  49 */     return SerCategoryManager.getChildSerCategoryList(ser_id);
/*     */   }
/*     */ 
/*     */   public static List<SerCategoryBean> getSerCategoryRootList()
/*     */   {
/*  58 */     return SerCategoryManager.getSerCategoryRootList();
/*     */   }
/*     */ 
/*     */   public static SerCategoryBean getSerCategoryBean(int ser_id)
/*     */   {
/*  68 */     return SerCategoryManager.getSerCategoryBean(ser_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSerCategory(SerCategoryBean scb, HttpServletRequest request)
/*     */   {
/*  79 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  80 */     if (stl != null)
/*     */     {
/*  82 */       return SerCategoryManager.insertSerCategory(scb, stl);
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerCategory(SerCategoryBean scb, HttpServletRequest request)
/*     */   {
/*  95 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  96 */     if (stl != null)
/*     */     {
/*  98 */       return SerCategoryManager.updateSerCategory(scb, stl);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveSerCategory(int parent_id, String ser_ids, HttpServletRequest request)
/*     */   {
/* 111 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 112 */     if (stl != null)
/*     */     {
/* 114 */       return SerCategoryManager.moveSerCategory(parent_id, ser_ids, stl);
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerCategoryStatus(String ser_ids, String publish_status, HttpServletRequest request)
/*     */   {
/* 127 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 128 */     if (stl != null)
/*     */     {
/* 130 */       return SerCategoryManager.updateSerCategoryStatus(ser_ids, publish_status, stl);
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortSerCategory(String ids, HttpServletRequest request)
/*     */   {
/* 143 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 144 */     if (stl != null)
/*     */     {
/* 146 */       return SerCategoryManager.sortSerCategory(ids, stl);
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerCategory(String ser_ids, HttpServletRequest request)
/*     */   {
/* 159 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 160 */     if (stl != null)
/*     */     {
/* 162 */       return SerCategoryManager.deleteSerCategory(ser_ids, stl);
/*     */     }
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getNewID()
/*     */   {
/* 173 */     return SerCategoryManager.getNewID();
/*     */   }
/*     */ 
/*     */   public static boolean updateSerTemplateContent(String template_content_id)
/*     */   {
/* 183 */     return SerCategoryManager.updateSerTemplateContent(template_content_id);
/*     */   }
/*     */ 
/*     */   public static List<SerResouceBean> getSerResouceList(String ser_id)
/*     */   {
/* 195 */     return SerResouceManager.getSerResouceList(ser_id);
/*     */   }
/*     */ 
/*     */   public static SerResouceBean getSerResouceBean(String res_id)
/*     */   {
/* 205 */     return SerResouceManager.getSerResouceBean(res_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSerResouce(SerResouceBean srb, HttpServletRequest request)
/*     */   {
/* 216 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 217 */     if (stl != null)
/*     */     {
/* 219 */       return SerResouceManager.insertSerResouce(srb, stl);
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerResouce(SerResouceBean srb, HttpServletRequest request)
/*     */   {
/* 232 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 233 */     if (stl != null)
/*     */     {
/* 235 */       return SerResouceManager.updateSerResouce(srb, stl);
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerResouceStatus(String res_ids, String publish_status, HttpServletRequest request)
/*     */   {
/* 248 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 249 */     if (stl != null)
/*     */     {
/* 251 */       return SerResouceManager.updateSerResouceStatus(res_ids, publish_status, stl);
/*     */     }
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortSerResouce(String ids, HttpServletRequest request)
/*     */   {
/* 264 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 265 */     if (stl != null)
/*     */     {
/* 267 */       return SerResouceManager.sortSerResouce(ids, stl);
/*     */     }
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerResouce(String res_ids, HttpServletRequest request)
/*     */   {
/* 280 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 281 */     if (stl != null)
/*     */     {
/* 283 */       return SerResouceManager.deleteSerResouce(res_ids, stl);
/*     */     }
/* 285 */     return false;
/*     */   }

/*     */   public static List<SerResouceBean> getSerResouceListByPublish(String ser_id)
/*     */   {
/* 120 */     return SerResouceManager.getSerResouceListByPublish(ser_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ser.SerRPC
 * JD-Core Version:    0.6.2
 */