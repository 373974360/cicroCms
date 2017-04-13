/*     */ package com.cicro.wcm.services.zwgk.ser;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerResouceBean;
/*     */ import com.cicro.wcm.dao.zwgk.ser.SerResouceDAO;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SerResouceManager
/*     */ {
/*     */   public static List<SerResouceBean> getSerResouceList(String ser_id)
/*     */   {
/*  28 */     return SerResouceDAO.getSerResouceList(ser_id);
/*     */   }
/*     */ 
/*     */   public static List<SerResouceBean> getSerResouceListByPublish(String ser_id)
/*     */   {
/*  38 */     return SerResouceDAO.getSerResouceListByPublish(ser_id);
/*     */   }
/*     */ 
/*     */   public static SerResouceBean getSerResouceBean(String res_id)
/*     */   {
/*  48 */     return SerResouceDAO.getSerResouceBean(res_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSerResouce(SerResouceBean srb, SettingLogsBean stl)
/*     */   {
/*  59 */     if (srb.getPublish_status() == 1)
/*     */     {
/*  61 */       srb.setPublish_time(DateUtil.getCurrentDateTime());
/*     */     }
/*  63 */     return SerResouceDAO.insertSerResouce(srb, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateSerResouce(SerResouceBean srb, SettingLogsBean stl)
/*     */   {
/*  74 */     if ((srb.getPublish_status() == 1) && ("".equals(srb.getPublish_time())))
/*     */     {
/*  76 */       srb.setPublish_time(DateUtil.getCurrentDateTime());
/*     */     }
/*  78 */     if (srb.getPublish_status() == 0)
/*  79 */       srb.setPublish_time("");
/*  80 */     return SerResouceDAO.updateSerResouce(srb, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateSerResouceStatus(String res_ids, String publish_status, SettingLogsBean stl)
/*     */   {
/*  91 */     return SerResouceDAO.updateSerResouceStatus(res_ids, publish_status, stl);
/*     */   }
/*     */ 
/*     */   public static boolean sortSerResouce(String ids, SettingLogsBean stl)
/*     */   {
/* 102 */     return SerResouceDAO.sortSerResouce(ids, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerResouce(String res_ids, SettingLogsBean stl)
/*     */   {
/* 113 */     return SerResouceDAO.deleteSerResouce(res_ids, stl);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ser.SerResouceManager
 * JD-Core Version:    0.6.2
 */