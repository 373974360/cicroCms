/*     */ package com.cicro.wcm.services.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.TagsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.assist.TagsDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TagsManager
/*     */   implements ISyncCatch
/*     */ {
/*  21 */   private static Map<Integer, TagsBean> tag_map = new HashMap();
/*     */ 
/*     */   static {
/*  24 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  34 */     List tag_list = TagsDAO.getAllTagsList();
/*  35 */     tag_map.clear();
/*  36 */     if ((tag_list != null) && (tag_list.size() > 0))
/*     */     {
/*  38 */       for (int i = 0; i < tag_list.size(); i++)
/*  39 */         tag_map.put(Integer.valueOf(((TagsBean)tag_list.get(i)).getTag_id()), (TagsBean)tag_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadTags()
/*     */   {
/*  46 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.assist.TagsManager");
/*     */   }
/*     */ 
/*     */   public static TagsBean getTagsBean(int tag_id)
/*     */   {
/*  56 */     if (tag_map.containsKey(Integer.valueOf(tag_id)))
/*     */     {
/*  58 */       return (TagsBean)tag_map.get(Integer.valueOf(tag_id));
/*     */     }
/*     */ 
/*  61 */     TagsBean tagb = TagsDAO.getTagsBean(tag_id);
/*  62 */     if (tagb != null)
/*     */     {
/*  64 */       tag_map.put(Integer.valueOf(tag_id), tagb);
/*  65 */       return tagb;
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getTagsCount(Map<String, String> con_map)
/*     */   {
/*  78 */     return TagsDAO.getTagsCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTags(TagsBean tag, SettingLogsBean stl)
/*     */   {
/*  87 */     if (TagsDAO.updateTags(tag, stl)) {
/*  88 */       tag_map.remove(Integer.valueOf(tag.getTag_id()));
/*  89 */       tag_map.put(Integer.valueOf(tag.getTag_id()), tag);
/*  90 */       reloadTags();
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTags(TagsBean tag, SettingLogsBean stl)
/*     */   {
/* 102 */     if (TagsDAO.addTags(tag, stl)) {
/* 103 */       tag_map.put(Integer.valueOf(tag.getTag_id()), tag);
/* 104 */       reloadTags();
/* 105 */       return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getTagListByAPPSite(String app_id, String site_id)
/*     */   {
/* 118 */     Set set = tag_map.keySet();
/* 119 */     List list = new ArrayList();
/* 120 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 121 */       TagsBean tb = (TagsBean)tag_map.get(Integer.valueOf(i));
/* 122 */       if ((app_id.equals(tb.getApp_id())) && ((site_id.equals(tb.getSite_id())) || (site_id == null) || ("".equals(site_id))))
/* 123 */         list.add((TagsBean)tag_map.get(Integer.valueOf(i)));
/*     */     }
/* 125 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getAllTagsList()
/*     */   {
/* 140 */     Set set = tag_map.keySet();
/* 141 */     List list = new ArrayList();
/* 142 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 143 */       list.add((TagsBean)tag_map.get(Integer.valueOf(i)));
/*     */     }
/* 145 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getTagsListForDB(Map<String, String> con_map)
/*     */   {
/* 155 */     return TagsDAO.getTagsListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delTagsById(String tag_ids, SettingLogsBean stl)
/*     */   {
/* 164 */     if (TagsDAO.delTags(tag_ids, stl)) {
/* 165 */       if (tag_ids != null) {
/* 166 */         if (tag_ids.indexOf(",") != -1) {
/* 167 */           for (String id : tag_ids.split(","))
/* 168 */             tag_map.remove(Integer.valueOf(Integer.parseInt(id)));
/*     */         }
/*     */         else {
/* 171 */           tag_map.remove(Integer.valueOf(Integer.parseInt(tag_ids)));
/*     */         }
/*     */       }
/* 174 */       reloadTags();
/* 175 */       return true;
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.assist.TagsManager
 * JD-Core Version:    0.6.2
 */