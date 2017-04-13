/*     */ package com.cicro.wcm.dao.zwgk.ser;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerCategoryBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SerCategoryDAO
/*     */ {
/*     */   public static List<SerCategoryBean> getAllSerCategoryList()
/*     */   {
/*  31 */     return DBManager.queryFList("getAllSerCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static SerCategoryBean getSerCategoryBean(int ser_id)
/*     */   {
/*  41 */     return (SerCategoryBean)DBManager.queryFObj("getSerCategoryBean", Integer.valueOf(ser_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertSerCategory(SerCategoryBean scb, SettingLogsBean stl)
/*     */   {
/*  52 */     if (DBManager.insert("insert_ser_category", scb))
/*     */     {
/*  54 */       PublicTableDAO.insertSettingLogs("添加", "场景式服务主题节点", scb.getSer_id(), stl);
/*  55 */       return true;
/*     */     }
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerCategory(SerCategoryBean scb, SettingLogsBean stl)
/*     */   {
/*  68 */     if (DBManager.update("update_ser_category", scb))
/*     */     {
/*  70 */       PublicTableDAO.insertSettingLogs("修改", "场景式服务主题节点", scb.getSer_id(), stl);
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerCategoryStatus(String ser_ids, String publish_status, SettingLogsBean stl)
/*     */   {
/*  84 */     Map m = new HashMap();
/*  85 */     m.put("ser_ids", ser_ids);
/*  86 */     m.put("publish_status", publish_status);
/*  87 */     if ("0".equals(publish_status))
/*  88 */       m.put("publish_time", "");
/*     */     else
/*  90 */       m.put("publish_time", DateUtil.getCurrentDateTime());
/*  91 */     if (DBManager.update("update_ser_category_status", m))
/*     */     {
/*  93 */       PublicTableDAO.insertSettingLogs("修改", "场景式服务主题节点发布状态", ser_ids, stl);
/*  94 */       return true;
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortSerCategory(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 108 */       Map m = new HashMap();
/* 109 */       String[] tempA = ids.split(",");
/* 110 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 112 */         m.put("sort_id", Integer.valueOf(i + 1));
/* 113 */         m.put("ser_id", tempA[i]);
/* 114 */         DBManager.update("sort_ser_category", m);
/*     */       }
/* 116 */       PublicTableDAO.insertSettingLogs("保存排序", "场景式服务主题节点", ids, stl);
/* 117 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 121 */       e.printStackTrace();
/* 122 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveSerCategory(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 134 */     if (DBManager.update("move_ser_category", m))
/*     */     {
/* 136 */       PublicTableDAO.insertSettingLogs("移动", "场景导航目录", (String)m.get("ser_id"), stl);
/* 137 */       return true;
/*     */     }
/*     */ 
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerCategory(String ser_ids, SettingLogsBean stl)
/*     */   {
/* 151 */     Map m = new HashMap();
/* 152 */     m.put("ser_ids", ser_ids);
/*     */ 
/* 154 */     if (DBManager.delete("delete_ser_category", m))
/*     */     {
/* 157 */       DBManager.delete("delete_info_category_forSer", m);
/* 158 */       DBManager.delete("delete_info_category_model_forSer", m);
/* 159 */       PublicTableDAO.insertSettingLogs("删除", "场景式服务主题节点", ser_ids, stl);
/* 160 */       return true;
/*     */     }
/*     */ 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerTemplateContent(String template_content_id)
/*     */   {
/* 173 */     return DBManager.delete("update_category_model_forSer", template_content_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.ser.SerCategoryDAO
 * JD-Core Version:    0.6.2
 */