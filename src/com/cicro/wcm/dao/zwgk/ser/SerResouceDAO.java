/*     */ package com.cicro.wcm.dao.zwgk.ser;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerResouceBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SerResouceDAO
/*     */ {
/*     */   public static List<SerResouceBean> getSerResouceList(String ser_id)
/*     */   {
/*  32 */     return DBManager.queryFList("getSerResouceList", ser_id);
/*     */   }
/*     */ 
/*     */   public static List<SerResouceBean> getSerResouceListByPublish(String ser_id)
/*     */   {
/*  43 */     return DBManager.queryFList("getSerResouceListByPublish", ser_id);
/*     */   }
/*     */ 
/*     */   public static SerResouceBean getSerResouceBean(String res_id)
/*     */   {
/*  53 */     return (SerResouceBean)DBManager.queryFObj("getSerResouceBean", res_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSerResouce(SerResouceBean srb, SettingLogsBean stl)
/*     */   {
/*  64 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.SER_RESOUCE_TABLE_NAME);
/*  65 */     srb.setId(id);
/*  66 */     srb.setRes_id(id);
/*  67 */     if (DBManager.insert("insert_ser_resouce", srb))
/*     */     {
/*  69 */       PublicTableDAO.insertSettingLogs("添加", "场景式服务资源信息", srb.getRes_id(), stl);
/*  70 */       return true;
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerResouce(SerResouceBean srb, SettingLogsBean stl)
/*     */   {
/*  83 */     if (DBManager.update("update_ser_resouce", srb))
/*     */     {
/*  85 */       PublicTableDAO.insertSettingLogs("添加", "场景式服务资源信息", srb.getRes_id(), stl);
/*  86 */       return true;
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerResouceStatus(String res_ids, String publish_status, SettingLogsBean stl)
/*     */   {
/*  99 */     Map m = new HashMap();
/* 100 */     m.put("res_ids", res_ids);
/* 101 */     m.put("publish_status", publish_status);
/* 102 */     if ("0".equals(publish_status))
/* 103 */       m.put("publish_time", "");
/*     */     else
/* 105 */       m.put("publish_time", DateUtil.getCurrentDateTime());
/* 106 */     if (DBManager.update("update_ser_resouce_status", m))
/*     */     {
/* 108 */       PublicTableDAO.insertSettingLogs("修改", "场景式服务资源信息", res_ids, stl);
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortSerResouce(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 123 */       Map m = new HashMap();
/* 124 */       String[] tempA = ids.split(",");
/* 125 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 127 */         m.put("sort_id", Integer.valueOf(i + 1));
/* 128 */         m.put("res_id", tempA[i]);
/* 129 */         DBManager.update("sort_ser_resouce", m);
/*     */       }
/* 131 */       PublicTableDAO.insertSettingLogs("保存排序", "场景式服务资源信息", ids, stl);
/* 132 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 136 */       e.printStackTrace();
/* 137 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerResouce(String res_ids, SettingLogsBean stl)
/*     */   {
/* 149 */     Map m = new HashMap();
/* 150 */     m.put("res_ids", res_ids);
/* 151 */     if (DBManager.delete("delete_ser_resouce", m))
/*     */     {
/* 153 */       PublicTableDAO.insertSettingLogs("删除", "场景式服务资源信息", res_ids, stl);
/* 154 */       return true;
/*     */     }
/*     */ 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerResouceByCategory(String ser_ids, SettingLogsBean stl)
/*     */   {
/* 168 */     Map m = new HashMap();
/* 169 */     m.put("ser_ids", ser_ids);
/* 170 */     if (DBManager.delete("delete_ser_resouce", m))
/*     */     {
/* 172 */       PublicTableDAO.insertSettingLogs("删除", "场景式服务资源信息 分类为", ser_ids, stl);
/* 173 */       return true;
/*     */     }
/*     */ 
/* 176 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.ser.SerResouceDAO
 * JD-Core Version:    0.6.2
 */