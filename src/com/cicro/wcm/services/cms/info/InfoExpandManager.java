/*     */ package com.cicro.wcm.services.cms.info;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.cms.info.InfoDAO;
/*     */ import com.cicro.wcm.db.BoneDataSourceFactory;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.category.CategoryUtil;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.staticpage.VelocityPageContextImp;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class InfoExpandManager
/*     */ {
/*  25 */   public static String rs = JconfigUtilContainer.bashConfig().getProperty("on_off", "", "staticPageList");
/*     */ 
/*     */   public static boolean setInfoTop(String istop, String info_id)
/*     */   {
/*  36 */     Map m = new HashMap();
/*  37 */     m.put("istop", istop);
/*  38 */     m.put("info_id", info_id);
/*  39 */     if (InfoDAO.setInfoTop(m))
/*     */     {
/*  41 */       InfoBean b = InfoBaseManager.getInfoById(info_id);
/*  42 */       if (b != null)
/*     */       {
/*  44 */         if ("on".equals(rs)) {
/*  45 */           CreateListPage(b.getCat_id(), b.getApp_id(), b.getSite_id());
/*     */         }
/*     */       }
/*  48 */       return true;
/*     */     }
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   public static String upOrDownInfo(int info_id, String site_id, String order)
/*     */   {
/*  56 */     List list = new ArrayList();
/*  57 */     String sql = "";
/*  58 */     if ("oracle".equals(BoneDataSourceFactory.getDataTypeName()))
/*     */     {
/*  60 */       sql = "select * from (select id,title,content_url from cs_info where info_id > " + info_id + " and info_status=8 and final_status= 0 and site_id='" + site_id + "' ) where rownum = 1 union all " + 
/*  61 */         "select * from (select id,title,content_url from cs_info where info_id < " + info_id + " and info_status=8 and final_status= 0 and site_id='" + site_id + "' order by " + order + ") where rownum=1 ";
/*  62 */     } else if ("mssql".equals(BoneDataSourceFactory.getDataTypeName()))
/*     */     {
/*  64 */       sql = "select top 1 * from (select id,title,content_url from cs_info where info_id > " + info_id + " and info_status=8 and final_status= 0 and site_id='" + site_id + "') union all " + 
/*  65 */         "select top 1 * from (select id,title,content_url from cs_info where info_id < " + info_id + " and info_status=8 and final_status= 0 and site_id='" + site_id + "' order by " + order + ")";
/*     */     }
/*     */     else {
/*  68 */       sql = "select * from (select id,title,content_url from cs_info where info_id > " + info_id + " and info_status=8 and final_status= 0 and site_id='" + site_id + "') limit 0,1 union all " + 
/*  69 */         "select * from (select id,title,content_url from cs_info where info_id < " + info_id + " and info_status=8 and final_status= 0 and site_id='" + site_id + "' order by " + order + ") limit 0,1";
/*     */     }
/*     */ 
/*  72 */     list = PublicTableDAO.executeSearchSql(sql);
/*  73 */     String str = "";
/*  74 */     String upinfo = "";
/*  75 */     String downinfo = "";
/*  76 */     if (list != null)
/*     */     {
/*  78 */       for (int i = 0; i < list.size(); i++)
/*     */       {
/*  80 */         Map m = (HashMap)list.get(i);
/*  81 */         String id = (String)m.get("ID");
/*  82 */         String title = (String)m.get("TITLE");
/*  83 */         String content_url = (String)m.get("CONTENT_URL");
/*     */ 
/*  85 */         if (Integer.parseInt(id) > info_id)
/*     */         {
/*  87 */           upinfo = "<a target='_blank' href='" + content_url + "' title='" + FormatUtil.fiterHtmlTag(title) + "'> 上一篇   </a>&nbsp;&nbsp;";
/*     */         }
/*  89 */         else downinfo = "<a target='_blank' href='" + content_url + "' title ='" + FormatUtil.fiterHtmlTag(title) + "'> 下一篇   </a>";
/*     */ 
/*  91 */         str = upinfo + downinfo;
/*     */       }
/*     */     }
/*  94 */     return str;
/*     */   }
/*     */ 
/*     */   public static void CreateListPage(int cat_id, String app_id, String site_id)
/*     */   {
/* 100 */     String cur_page = "1";
/* 101 */     String node_id = "";
/* 102 */     String list_size = JconfigUtilContainer.bashConfig().getProperty("list_size", "", "staticPageList");
/*     */ 
/* 104 */     String list_Filepath = CategoryUtil.getFoldePathByCategoryID(cat_id, app_id, site_id);
/* 105 */     String savePath = FormatUtil.formatPath(SiteManager.getSitePath(site_id) + list_Filepath);
/* 106 */     int page_num = Integer.parseInt(list_size);
/* 107 */     for (int i = 1; i < page_num; i++)
/*     */     {
/* 109 */       cur_page = i+"";
/* 110 */       VelocityPageContextImp velocityPageContextImp = new VelocityPageContextImp();
/* 111 */       velocityPageContextImp.setCcontext("cur_page", cur_page);
/* 112 */       velocityPageContextImp.setCcontext("site_id", site_id);
/* 113 */       velocityPageContextImp.setCcontext("cat_id", Integer.valueOf(cat_id));
/* 114 */       velocityPageContextImp.setTemplateID(cat_id+"", site_id, node_id, "list");
/* 115 */       String content = velocityPageContextImp.parseTemplate();
/*     */       try {
/* 117 */         FileOperation.writeStringToFile(savePath + File.separator + cat_id + "_" + cur_page + ".html", content, false, "utf-8");
/*     */       }
/*     */       catch (IOException e) {
/* 120 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void CreateListPage(Set<Integer> cat_ids)
/*     */   {
/* 127 */     String cur_page = "1";
/* 128 */     String node_id = "";
/* 129 */     String list_size = JconfigUtilContainer.bashConfig().getProperty("list_size", "", "staticPageList");
/* 130 */     int page_num = Integer.parseInt(list_size) + 1;
/* 131 */     Iterator it = cat_ids.iterator();
/* 132 */     while (it.hasNext())
/*     */     {
/* 134 */       String cat_id = (String)it.next();
/* 135 */       CategoryBean catebean = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 136 */       if (catebean != null)
/*     */       {
/* 138 */         String list_Filepath = CategoryUtil.getFoldePathByCategoryID(catebean.getCat_id(), catebean.getApp_id(), catebean.getSite_id());
/* 139 */         String savePath = FormatUtil.formatPath(SiteManager.getSitePath(catebean.getSite_id()) + list_Filepath);
/* 140 */         for (int i = 1; i < page_num; i++)
/*     */         {
/* 142 */           cur_page = i+"";
/* 143 */           VelocityPageContextImp velocityPageContextImp = new VelocityPageContextImp();
/* 144 */           velocityPageContextImp.setCcontext("cur_page", cur_page);
/* 145 */           velocityPageContextImp.setCcontext("site_id", catebean.getSite_id());
/* 146 */           velocityPageContextImp.setCcontext("cat_id", cat_id);
/* 147 */           velocityPageContextImp.setTemplateID(cat_id, catebean.getSite_id(), node_id, "list");
/* 148 */           String content = velocityPageContextImp.parseTemplate();
/*     */           try {
/* 150 */             FileOperation.writeStringToFile(savePath + File.separator + cat_id + "_" + cur_page + ".html", content, false, "utf-8");
/*     */           }
/*     */           catch (IOException e) {
/* 153 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getStaticListPageUrl(String cat_id)
/*     */   {
/* 162 */     String list_Filepath = "";
/* 163 */     CategoryBean catebean = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 164 */     if (catebean != null)
/*     */     {
/* 166 */       list_Filepath = CategoryUtil.getFoldePathByCategoryID(catebean.getCat_id(), catebean.getApp_id(), catebean.getSite_id()) + cat_id + "_1.htm";
/*     */     }
/* 168 */     return list_Filepath;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.InfoExpandManager
 * JD-Core Version:    0.6.2
 */