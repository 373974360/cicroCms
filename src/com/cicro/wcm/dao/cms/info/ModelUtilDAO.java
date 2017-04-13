/*     */ package com.cicro.wcm.dao.cms.info;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.info.GKPicBean;
/*     */ import com.cicro.wcm.bean.cms.info.PicBean;
/*     */ import com.cicro.wcm.bean.cms.info.PicItemBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.info.GKInfoDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ModelUtilDAO
/*     */ {
/*     */   public static boolean addModel(Object o, String sqlName, String model_name, SettingLogsBean stl)
/*     */   {
/*  36 */     if (InfoBaseManager.addInfo(o, stl))
/*     */     {
/*  38 */       if (model_name.toLowerCase().contains("gk"))
/*     */       {
/*  40 */         GKInfoDAO.insertGKInfo(o);
/*     */       }
/*  42 */       if (!"".equals(sqlName))
/*     */       {
/*  44 */         if (sqlName.equals("insert_info_pic"))
/*     */         {
/*  46 */           return insertPicInfo(o, model_name);
/*     */         }
/*     */ 
/*  50 */         return DBManager.insert(sqlName, o);
/*     */       }
/*     */ 
/*  53 */       return true;
/*     */     }
/*     */ 
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(Object o, String sqlName, String model_name, SettingLogsBean stl)
/*     */   {
/*  72 */     if (InfoBaseManager.updateInfo(o, stl))
/*     */     {
/*  74 */       if (model_name.toLowerCase().contains("gk"))
/*     */       {
/*  76 */         GKInfoDAO.updateGKInfo(o);
/*     */       }
/*  78 */       if (!"".equals(sqlName))
/*     */       {
/*  80 */         if (sqlName.equals("update_info_pic"))
/*     */         {
/*  82 */           return insertPicInfo(o, model_name);
/*     */         }
/*     */ 
/*  85 */         return DBManager.update(sqlName, o);
/*     */       }
/*  87 */       return true;
/*     */     }
/*     */ 
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public static Object selectModel(String infoId, String siteId, String sqlName)
/*     */   {
/* 104 */     Map map = new HashMap();
/* 105 */     map.put("info_id", infoId);
/* 106 */     return DBManager.queryFObj(sqlName, map);
/*     */   }
/*     */ 
/*     */   public static boolean insertPicInfo(Object o, String model_name)
/*     */   {
/* 116 */     List list = null;
/* 117 */     int info_id = 0;
/* 118 */     if ("gkpic".equals(model_name))
/*     */     {
/* 120 */       GKPicBean pb = (GKPicBean)o;
/* 121 */       list = pb.getItem_list();
/* 122 */       info_id = pb.getInfo_id();
/*     */     }
/* 124 */     if ("pic".equals(model_name))
/*     */     {
/* 126 */       PicBean pb = (PicBean)o;
/* 127 */       list = pb.getItem_list();
/* 128 */       info_id = pb.getInfo_id();
/*     */     }
/*     */ 
/* 131 */     if (deletePicInfo(info_id))
/*     */     {
/* 133 */       if ((list != null) && (list.size() > 0)) {
/*     */         try
/*     */         {
/* 136 */           for (PicItemBean pib : list)
/*     */           {
/* 138 */             pib.setPic_id(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_PIC_TABLE_NAME));
/* 139 */             pib.setInfo_id(info_id);
/* 140 */             DBManager.insert("insert_info_pic", pib);
/*     */           }
/* 142 */           return true;
/*     */         }
/*     */         catch (Exception e) {
/* 145 */           e.printStackTrace();
/* 146 */           return false;
/*     */         }
/*     */       }
/*     */ 
/* 150 */       return false;
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deletePicInfo(int id)
/*     */   {
/* 162 */     return DBManager.delete("delete_info_pic", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 168 */     System.out.println(selectModel("10", "dd", "getPicInfoBean"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.info.ModelUtilDAO
 * JD-Core Version:    0.6.2
 */