/*    */ package com.cicro.wcm.dao.system.formodel;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.system.formodel.ModelFieldBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ModelFieldDAO
/*    */ {
/*    */   public static List<ModelFieldBean> getAllModelFieldList()
/*    */   {
/* 32 */     return DBManager.queryFList("getAllModelFieldList", "");
/*    */   }
/*    */ 
/*    */   public static ModelFieldBean getModelFieldBean(int modelField_id)
/*    */   {
/* 42 */     return (ModelFieldBean)DBManager.queryFObj("getModelFieldBean", Integer.valueOf(modelField_id));
/*    */   }
/*    */ 
/*    */   public static boolean insertModelField(ModelFieldBean mfb, SettingLogsBean stl)
/*    */   {
/* 52 */     int modelField_id = PublicTableDAO.getIDByTableName(PublicTableDAO.FORMODELFIELD_TABLE_NAME);
/* 53 */     mfb.setField_id(modelField_id);
/* 54 */     if (DBManager.insert("insert_modelField", mfb))
/*    */     {
/* 56 */       PublicTableDAO.insertSettingLogs("添加", "内容模型字段", modelField_id, stl);
/* 57 */       return true;
/*    */     }
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateModelField(ModelFieldBean mfb, SettingLogsBean stl)
/*    */   {
/* 69 */     if (DBManager.update("update_modelField", mfb))
/*    */     {
/* 71 */       PublicTableDAO.insertSettingLogs("修改", "内容模型字段", mfb.getField_id(), stl);
/* 72 */       return true;
/*    */     }
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteModelField(String field_ids, SettingLogsBean stl)
/*    */   {
/* 84 */     Map m = new HashMap();
/* 85 */     m.put("field_ids", field_ids);
/* 86 */     m.put("table_name", PublicTableDAO.INFO_UDEFINED_TABLE_NAME);
/* 87 */     if (DBManager.delete("delete_modelField", m))
/*    */     {
/* 89 */       PublicTableDAO.insertSettingLogs("删除", "内容模型字段", field_ids, stl);
/* 90 */       return true;
/*    */     }
/* 92 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.formodel.ModelFieldDAO
 * JD-Core Version:    0.6.2
 */