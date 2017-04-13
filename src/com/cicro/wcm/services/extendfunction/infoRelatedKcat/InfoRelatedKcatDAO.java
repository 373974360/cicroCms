/*     */ package com.cicro.wcm.services.extendfunction.infoRelatedKcat;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateBean;
/*     */ import com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class InfoRelatedKcatDAO
/*     */ {
/*  20 */   private static String table_name = "cs_dz_infoRelatedkcat";
/*     */ 
/*     */   public static boolean insertInfoRelatedKcat(Map<String, String> map, SettingLogsBean stl)
/*     */   {
/*  31 */     String kcat_ids = (String)map.get("kcat_id");
/*  32 */     String info_id = (String)map.get("info_id");
/*  33 */     String site_id = (String)map.get("site_id");
/*  34 */     String app_id = (String)map.get("app_id");
/*  35 */     String[] kids = (String[])null;
/*     */ 
/*  37 */     if ((kcat_ids != null) || (kcat_ids != ""))
/*     */     {
/*  40 */       deleteInfoRelatedKcat(map, stl);
/*  41 */       kids = kcat_ids.split(",");
/*  42 */       for (int i = 0; i < kids.length; i++)
/*     */       {
/*  44 */         int id = PublicTableDAO.getIDByTableName(table_name);
/*  45 */         int kcat_id = Integer.parseInt(kids[i]);
/*  46 */         KnowledgeCateBean kb = KnowledgeCateManager.getKCategoryByID(kcat_id+"");
/*  47 */         InfoRelatedKcatBean b = new InfoRelatedKcatBean();
/*     */ 
/*  49 */         b.setId(id);
/*  50 */         b.setAdd_time(DateUtil.getCurrentDateTime());
/*  51 */         b.setInfo_id(info_id);
/*  52 */         b.setSite_id(site_id);
/*  53 */         b.setKcat_id(kcat_id);
/*  54 */         b.setApp_id(app_id);
/*  55 */         b.setKcat_name(kb.getKcat_name());
/*     */ 
/*  57 */         if (DBManager.insert("infoRelatedkcat_sql.insertInfoRelatedKcat", b))
/*     */         {
/*  59 */           PublicTableDAO.insertSettingLogs("添加", "知识库标签信息", info_id, stl);
/*     */         }
/*     */       }
/*  62 */       return true;
/*     */     }
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteInfoRelatedKcat(Map<String, String> map, SettingLogsBean stl)
/*     */   {
/*  71 */     if (DBManager.update("infoRelatedkcat_sql.deleteInfoRelatedKcat_infoappid", map))
/*     */     {
/*  73 */       PublicTableDAO.insertSettingLogs("修改", "知识库标签信息", (String)map.get("info_id"), stl);
/*  74 */       return true;
/*     */     }
/*     */ 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<InfoRelatedKcatBean> getCGGRelatedKcatInfoList(Map<String, String> map)
/*     */   {
/*  85 */     String id = (String)map.get("kcat_id");
/*  86 */     if ((id == "") || (id == "0") || (id == null)) {
/*  87 */       map.remove("kcat_id");
/*     */     } else {
/*  89 */       String ids = KnowledgeCateManager.getChildListIdsByPid(id);
/*  90 */       if (ids != "") {
/*  91 */         map.remove("kcat_id");
/*  92 */         map.put("kcat_id", ids);
/*     */       }
/*     */     }
/*  95 */     List l = new ArrayList();
/*  96 */     List<InfoBean> infoL = DBManager.queryFList("infoRelatedkcat_sql.selectCGGRelatedKcatInfoList", map);
/*     */     try {
/*  98 */       for (InfoBean ib : infoL)
/*     */       {
/* 100 */         String url = "";
/* 101 */         InfoRelatedKcatBean rb = new InfoRelatedKcatBean();
/* 102 */         rb.setTitle(ib.getTitle());
/* 103 */         rb.setInfo_time(ib.getReleased_dtime());
/* 104 */         String app_id = ib.getApp_id();
/* 105 */         if (app_id.equals("cms"))
/* 106 */           url = "http://" + SiteManager.getSiteBeanBySiteID(ib.getSite_id()).getSite_domain() + ib.getContent_url();
/*     */         else {
/* 108 */           url = "http://" + SiteManager.getSiteBeanBySiteID(SiteAppRele.getSiteIDByAppID(app_id)).getSite_domain() + ib.getContent_url();
/*     */         }
/* 110 */         rb.setLinkUrl(url);
/* 111 */         rb.setSource(ib.getSource());
/* 112 */         rb.setInfo_id(ib.getInfo_id()+"");
/*     */ 		  InfoRelatedKcatBean infoRelate = InfoRelatedKcatManager.getInfoRelatedKcatBeanByinfoid(ib.getInfo_id()+"");
/* 114 */         KnowledgeCateBean kb = KnowledgeCateManager.getKCategoryByID(infoRelate.getKcat_id()+"");
/* 115 */         rb.setKcat_id(Integer.parseInt(kb.getKcat_id()));
/* 116 */         rb.setKcat_name(kb.getKcat_name());
/*     */ 
/* 118 */         String p_id = kb.getKparent_id();
/* 119 */         if (p_id.equals("0"))
/*     */         {
/* 121 */           rb.setP_kcat_id(0);
/* 122 */           rb.setP_kcat_name("知识库");
/*     */         } else {
/* 124 */           KnowledgeCateBean pkb = KnowledgeCateManager.getKCategoryByID(p_id);
/* 125 */           rb.setP_kcat_id(Integer.parseInt(pkb.getKcat_id()));
/* 126 */           rb.setP_kcat_name(pkb.getKcat_name());
/*     */         }
/* 128 */         l.add(rb);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/*     */     }
/* 134 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getCGGRelatedKcatInfoListCounts(Map<String, String> map)
/*     */   {
/* 141 */     String id = (String)map.get("kcat_id");
/* 142 */     if ((id == "") || (id == "0") || (id == null)) {
/* 143 */       map.remove("kcat_id");
/*     */     } else {
/* 145 */       String ids = KnowledgeCateManager.getChildListIdsByPid(id);
/* 146 */       if (ids != "") {
/* 147 */         map.remove("kcat_id");
/* 148 */         map.put("kcat_id", ids);
/*     */       }
/*     */     }
/* 151 */     return DBManager.getString("infoRelatedkcat_sql.selectCGGRelatedKcatInfoList_counts", map);
/*     */   }
/*     */ 
/*     */   public static InfoRelatedKcatBean getInfoRelatedKcatBeanByinfoid(String info_id)
/*     */   {
/* 157 */     return (InfoRelatedKcatBean)DBManager.queryFObj("infoRelatedkcat_sql.selectInfoRelatedKcatBeanByinfoid", info_id);
/*     */   }
/*     */ 
/*     */   public static String getRelatedKcatNames(String info_id)
/*     */   {
/* 163 */     String kcat_names = "";
/* 164 */     List<InfoRelatedKcatBean> infoL = DBManager.queryFList("infoRelatedkcat_sql.selectKCategoryListByInfo_id", info_id);
/*     */     try {
/* 166 */       for (InfoRelatedKcatBean ib : infoL)
/*     */       {
/* 168 */         kcat_names = kcat_names + ib.getKcat_name() + ",";
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 172 */       e.printStackTrace();
/*     */     }
/* 174 */     if (kcat_names.endsWith(",")) {
/* 175 */       kcat_names = kcat_names.substring(0, kcat_names.length() - 1);
/*     */     }
/* 177 */     return kcat_names;
/*     */   }
/*     */ 
/*     */   public static List<InfoRelatedKcatBean> getSQRelatedKcatInfoList(Map<String, String> map)
/*     */   {
/* 185 */     String id = (String)map.get("kcat_id");
/* 186 */     if ((id == "") || (id == "0") || (id == null))
/*     */     {
/* 188 */       map.remove("kcat_id");
/*     */     } else {
/* 190 */       String ids = KnowledgeCateManager.getChildListIdsByPid(id);
/* 191 */       if (ids != "") {
/* 192 */         map.remove("kcat_id");
/* 193 */         map.put("kcat_id", ids);
/*     */       }
/*     */     }
/* 196 */     List l = new ArrayList();
/* 197 */     List<SQBean> infoL = DBManager.queryFList("infoRelatedkcat_sql.selectSQRelatedKcatInfoList", map);
/*     */     try {
/* 199 */       for (SQBean ib : infoL)
/*     */       {
/* 201 */         InfoRelatedKcatBean rb = new InfoRelatedKcatBean();
/* 202 */         rb.setTitle(ib.getSq_title());
/* 203 */         rb.setInfo_time(ib.getSq_dtime());
/* 204 */         String url = "/appeal/view.jsp?model_id=" + ib.getModel_id() + "&sq_id=" + ib.getSq_id();
/* 205 */         rb.setLinkUrl(url);
/* 206 */         rb.setSource(ib.getSubmit_name());
/* 207 */         rb.setInfo_id(ib.getSq_id()+"");

/*     */ 		  InfoRelatedKcatBean infoRelate = InfoRelatedKcatManager.getInfoRelatedKcatBeanByinfoid(ib.getSq_id() + "");
/* 114 */         KnowledgeCateBean kb = KnowledgeCateManager.getKCategoryByID(infoRelate.getKcat_id()+"");
/* 210 */         rb.setKcat_id(Integer.parseInt(kb.getKcat_id()));
/* 211 */         rb.setKcat_name(kb.getKcat_name());
/* 212 */         String p_id = kb.getKparent_id();
/* 213 */         if (p_id.equals("0"))
/*     */         {
/* 215 */           rb.setP_kcat_id(0);
/* 216 */           rb.setP_kcat_name("知识库");
/*     */         } else {
/* 218 */           KnowledgeCateBean pkb = KnowledgeCateManager.getKCategoryByID(p_id);
/* 219 */           rb.setP_kcat_id(Integer.parseInt(pkb.getKcat_id()));
/* 220 */           rb.setP_kcat_name(pkb.getKcat_name());
/*     */         }
/* 222 */         l.add(rb);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 226 */       e.printStackTrace();
/*     */     }
/* 228 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getSQRelatedKcatInfoListCounts(Map<String, String> map)
/*     */   {
/* 235 */     String id = (String)map.get("kcat_id");
/* 236 */     if ((id == "") || (id == "0") || (id == null)) {
/* 237 */       map.remove("kcat_id");
/*     */     } else {
/* 239 */       String ids = KnowledgeCateManager.getChildListIdsByPid(id);
/* 240 */       if (ids != "") {
/* 241 */         map.remove("kcat_id");
/* 242 */         map.put("kcat_id", ids);
/*     */       }
/*     */     }
/* 245 */     return DBManager.getString("infoRelatedkcat_sql.selectSQRelatedKcatInfoList_counts", map);
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.infoRelatedKcat.InfoRelatedKcatDAO
 * JD-Core Version:    0.6.2
 */