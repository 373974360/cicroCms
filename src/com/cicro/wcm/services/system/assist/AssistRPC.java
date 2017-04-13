/*     */ package com.cicro.wcm.services.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.AuthorBean;
/*     */ import com.cicro.wcm.bean.system.assist.HotWordBean;
/*     */ import com.cicro.wcm.bean.system.assist.SourceBean;
/*     */ import com.cicro.wcm.bean.system.assist.TagsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class AssistRPC
/*     */ {
/*     */   public static List<HotWordBean> getAllHotWordList()
/*     */   {
/*  24 */     return HotWordManager.getAllHotWordList();
/*     */   }
/*     */ 
/*     */   public static List<HotWordBean> getHotWordList(Map<String, String> con_map) {
/*  28 */     return HotWordManager.getHotWordListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getHotWordCount(Map<String, String> con_map) {
/*  32 */     return HotWordManager.getHotWordCount(con_map);
/*     */   }
/*     */ 
/*     */   public static HotWordBean getHotWordById(int hot_id) {
/*  36 */     return HotWordManager.getHotWordBean(hot_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateHotWordById(HotWordBean hw, HttpServletRequest request) {
/*  40 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  41 */     if (stl != null)
/*     */     {
/*  43 */       return HotWordManager.updateHotWord(hw, stl);
/*     */     }
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addHotWordById(HotWordBean hw, HttpServletRequest request) {
/*  49 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  50 */     if (stl != null)
/*     */     {
/*  52 */       return HotWordManager.addHotWord(hw, stl);
/*     */     }
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delHotWordById(String hot_id, HttpServletRequest request) {
/*  58 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  59 */     if (stl != null)
/*     */     {
/*  61 */       return HotWordManager.delHotWordById(hot_id, stl);
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getTagListByAPPSite(String app_id, String site_id)
/*     */   {
/*  75 */     return TagsManager.getTagListByAPPSite(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getAllTagsList() {
/*  79 */     return TagsManager.getAllTagsList();
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getTagsList(Map<String, String> con_map) {
/*  83 */     return TagsManager.getTagsListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getTagsCount(Map<String, String> con_map) {
/*  87 */     return TagsManager.getTagsCount(con_map);
/*     */   }
/*     */ 
/*     */   public static TagsBean getTagsById(int tag_id) {
/*  91 */     return TagsManager.getTagsBean(tag_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateTagsById(TagsBean tag, HttpServletRequest request) {
/*  95 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  96 */     if (stl != null)
/*     */     {
/*  98 */       return TagsManager.updateTags(tag, stl);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTagsById(TagsBean tag, HttpServletRequest request) {
/* 104 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 105 */     if (stl != null)
/*     */     {
/* 107 */       return TagsManager.addTags(tag, stl);
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTagsById(String tag_id, HttpServletRequest request) {
/* 113 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 114 */     if (stl != null)
/*     */     {
/* 116 */       return TagsManager.delTagsById(tag_id, stl);
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<AuthorBean> getAllAuthorList()
/*     */   {
/* 123 */     return AuthorManager.getAllAuthorList();
/*     */   }
/*     */ 
/*     */   public static List<AuthorBean> getAuthorList(Map<String, String> con_map) {
/* 127 */     return AuthorManager.getAuthorListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getAuthorCount(Map<String, String> con_map) {
/* 131 */     return AuthorManager.getAuthorCount(con_map);
/*     */   }
/*     */ 
/*     */   public static AuthorBean getAuthorById(int author_id) {
/* 135 */     return AuthorManager.getAuthorBean(author_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateAuthorById(AuthorBean author, HttpServletRequest request) {
/* 139 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 140 */     if (stl != null)
/*     */     {
/* 142 */       return AuthorManager.updateAuthor(author, stl);
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addAuthorById(AuthorBean author, HttpServletRequest request) {
/* 148 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 149 */     if (stl != null)
/*     */     {
/* 151 */       return AuthorManager.addAuthor(author, stl);
/*     */     }
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delAuthorById(String author_id, HttpServletRequest request) {
/* 157 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 158 */     if (stl != null)
/*     */     {
/* 160 */       return AuthorManager.delAuthorById(author_id, stl);
/*     */     }
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SourceBean> getAllSourceList()
/*     */   {
/* 167 */     return SourceManager.getAllSourceList();
/*     */   }
/*     */ 
/*     */   public static List<SourceBean> getSourceList(Map<String, String> con_map) {
/* 171 */     return SourceManager.getSourceListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static String getSourceCount(Map<String, String> con_map) {
/* 175 */     return SourceManager.getSourceCount(con_map);
/*     */   }
/*     */ 
/*     */   public static SourceBean getSourceById(int source_id) {
/* 179 */     return SourceManager.getSourceBean(source_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSourceById(SourceBean source, HttpServletRequest request) {
/* 183 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 184 */     if (stl != null)
/*     */     {
/* 186 */       return SourceManager.updateSource(source, stl);
/*     */     }
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSourceById(SourceBean source, HttpServletRequest request) {
/* 192 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 193 */     if (stl != null)
/*     */     {
/* 195 */       return SourceManager.addSource(source, stl);
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delSourceById(String source_id, HttpServletRequest request) {
/* 201 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 202 */     if (stl != null)
/*     */     {
/* 204 */       return SourceManager.delSourceById(source_id, stl);
/*     */     }
/* 206 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.assist.AssistRPC
 * JD-Core Version:    0.6.2
 */