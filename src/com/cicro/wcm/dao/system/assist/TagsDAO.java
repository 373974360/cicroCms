/*     */ package com.cicro.wcm.dao.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.TagsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TagsDAO
/*     */ {
/*     */   public static List<TagsBean> getAllTagsList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllTagsList", "");
/*     */   }
/*     */ 
/*     */   public static TagsBean getTagsBean(int tag_id)
/*     */   {
/*  42 */     return (TagsBean)DBManager.queryFObj("getTagsBean", Integer.valueOf(tag_id));
/*     */   }
/*     */ 
/*     */   public static String getTagsCount(Map<String, String> con_map)
/*     */   {
/*  52 */     return DBManager.getString("getTagsCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getTagsListForDB(Map<String, String> con_map)
/*     */   {
/*  63 */     return DBManager.queryFList("getTagsListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTags(TagsBean tag, SettingLogsBean stl)
/*     */   {
/*  72 */     if (DBManager.update("updateTags", tag))
/*     */     {
/*  74 */       PublicTableDAO.insertSettingLogs("修改", "Tag", tag.getTag_id(), stl);
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTags(TagsBean tag, SettingLogsBean stl)
/*     */   {
/*  86 */     int tagId = PublicTableDAO.getIDByTableName(PublicTableDAO.TAGS_TABLE_NAME);
/*  87 */     tag.setTag_id(tagId);
/*  88 */     if (DBManager.insert("insertTags", tag))
/*     */     {
/*  90 */       PublicTableDAO.insertSettingLogs("添加", "Tag", tagId, stl);
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTags(String tag_ids, SettingLogsBean stl)
/*     */   {
/* 103 */     Map map = new HashMap();
/* 104 */     map.put("tag_ids", tag_ids);
/* 105 */     if (DBManager.delete("deleteTags", map))
/*     */     {
/* 107 */       PublicTableDAO.insertSettingLogs("删除", "Tag", tag_ids, stl);
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.assist.TagsDAO
 * JD-Core Version:    0.6.2
 */