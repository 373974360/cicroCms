/*     */ package com.cicro.wcm.dao.appeal.area;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.area.AreaBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AreaDAO
/*     */ {
/*     */   public static List<AreaBean> getAllAreaList()
/*     */   {
/*  31 */     return DBManager.queryFList("getAllAreaList", "");
/*     */   }
/*     */ 
/*     */   public static AreaBean getAreaBean(int area_id)
/*     */   {
/*  40 */     return (AreaBean)DBManager.queryFObj("getAreaBean", Integer.valueOf(area_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertArea(AreaBean areabean, SettingLogsBean stl)
/*     */   {
/*  49 */     areabean.setArea_position(areabean.getArea_position() + areabean.getArea_id() + "$");
/*     */ 
/*  51 */     if (DBManager.insert("insert_Area", areabean)) {
/*  52 */       PublicTableDAO.insertSettingLogs("添加", "地区分类", areabean.getArea_id(), stl);
/*  53 */       return true;
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateArea(AreaBean ob, SettingLogsBean stl)
/*     */   {
/*  67 */     if (DBManager.update("update_Area", ob))
/*     */     {
/*  69 */       PublicTableDAO.insertSettingLogs("添加", "修改", ob.getArea_id(), stl);
/*  70 */       return true;
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteArea(String area_id, SettingLogsBean stl)
/*     */   {
/*  82 */     boolean isCorrect = false;
/*  83 */     Map m = new HashMap();
/*  84 */     m.put("area_id", area_id);
/*  85 */     if (DBManager.delete("delete_Area_ids", m))
/*     */     {
/*  87 */       if (DBManager.delete("delete_Parent_ids", m))
/*     */       {
/*  89 */         isCorrect = true;
/*     */       }
/*     */     }
/*  92 */     return isCorrect;
/*     */   }
/*     */ 
/*     */   public static boolean saveAreaSort(String area_id, SettingLogsBean stl)
/*     */   {
/* 103 */     if ((area_id != null) && (!"".equals(area_id))) {
/*     */       try
/*     */       {
/* 106 */         Map m = new HashMap();
/* 107 */         String[] tempA = area_id.split(",");
/* 108 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 110 */           m.put("sort_id", Integer.valueOf(i + 1));
/* 111 */           m.put("area_id", tempA[i]);
/* 112 */           DBManager.update("update_Area_sort", m);
/*     */         }
/* 114 */         PublicTableDAO.insertSettingLogs("保存排序", "菜单", area_id, stl);
/* 115 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 118 */         e.printStackTrace();
/* 119 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 123 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.area.AreaDAO
 * JD-Core Version:    0.6.2
 */