/*     */ package com.cicro.wcm.services.zwgk.infoToFw;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.info.GKInfoBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.category.CategoryUtil;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*     */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ 
/*     */ public class infoToFwManager
/*     */ {
/*     */   public static boolean infoToFwCate(String ids, String s_site_id, String s_app_id, String cat_id, int get_type, boolean is_publish, int user_id)
/*     */   {
/*  39 */     List l = new ArrayList();
/*  40 */     String[] id = ids.split(",");
/*  41 */     for (int i = 0; i < id.length; i++)
/*     */     {
/*  43 */       InfoBean b = InfoBaseManager.getInfoById(id[i]);
/*  44 */       if (b != null)
/*  45 */         l.add(b);
/*     */     }
/*  47 */     String[] cat_ids = cat_id.split(",");
/*     */ 
/*  49 */     for (String catid : cat_ids) {
/*  50 */       int c_id = Integer.parseInt(catid);
/*  51 */       infoGet(l, s_site_id, s_app_id, c_id, get_type, is_publish, user_id);
/*     */     }
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean infoGet(List<InfoBean> l, String s_site_id, String s_app_id, int cat_id, int get_type, boolean is_publish, int user_id)
/*     */   {
/*  70 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  72 */       for (InfoBean info : l) {
/*     */         try
/*     */         {
/*  75 */           int id = InfoBaseManager.getNextInfoId();
/*     */ 
/*  77 */           String model_ename = ModelManager.getModelEName(info.getModel_id());
/*  78 */           Object o = ModelUtil.select(info.getInfo_id()+"", info.getSite_id(), model_ename);
/*     */ 
/*  81 */           if ("zwgk".equals(BeanUtils.getProperty(o, "app_id")))
/*     */           {
/*  83 */             GKInfoBean gkinfo = (GKInfoBean)o;
/*  84 */             List file_list = gkinfo.getFile_list();
/*  85 */             if ((file_list != null) && (file_list.size() > 0))
/*     */             {
/*  87 */               for (int i = 0; i < file_list.size(); i++) {
/*  88 */                 BeanUtils.setProperty(o, "file_list[" + i + "].info_id", Integer.valueOf(id));
/*     */               }
/*     */             }
/*     */           }
/*  92 */           if (user_id != 0)
/*  93 */             BeanUtils.setProperty(o, "input_user", Integer.valueOf(user_id));
/*  94 */           BeanUtils.setProperty(o, "site_id", s_site_id);
/*  95 */           BeanUtils.setProperty(o, "cat_id", Integer.valueOf(cat_id));
/*  96 */           BeanUtils.setProperty(o, "app_id", s_app_id);
/*  97 */           BeanUtils.setProperty(o, "id", Integer.valueOf(id));
/*  98 */           BeanUtils.setProperty(o, "info_id", Integer.valueOf(id));
/*     */ 
/* 100 */           if (is_publish)
/*     */           {
/* 102 */             BeanUtils.setProperty(o, "info_status", Integer.valueOf(8));
/* 103 */             BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
/*     */           }
/*     */           else
/*     */           {
/* 107 */             CategoryBean cb = CategoryManager.getCategoryBeanCatID(cat_id, s_site_id);
/*     */ 
/* 109 */             BeanUtils.setProperty(o, "cat_id", Integer.valueOf(cat_id));
/* 110 */             if (cb.getWf_id() != 0)
/*     */             {
/* 112 */               BeanUtils.setProperty(o, "info_status", Integer.valueOf(2));
/* 113 */               BeanUtils.setProperty(o, "step_id", Integer.valueOf(0));
/* 114 */               BeanUtils.setProperty(o, "wf_id", Integer.valueOf(cb.getWf_id()));
/*     */             }
/*     */             else
/*     */             {
/* 118 */               BeanUtils.setProperty(o, "info_status", Integer.valueOf(4));
/* 119 */               BeanUtils.setProperty(o, "step_id", Integer.valueOf(100));
/*     */             }
/*     */           }
/* 122 */           if ((get_type == 0) && (info.getIs_host() == 0))
/*     */           {
/* 124 */             BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + id + ".html");
/*     */           }
/* 126 */           if ((get_type == 1) && (info.getIs_host() == 0))
/*     */           {
/* 128 */             BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
/* 129 */             BeanUtils.setProperty(o, "is_host", Integer.valueOf(get_type));
/* 130 */             BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + id + ".html");
/*     */           }
/* 132 */           if ((get_type == 2) && (info.getIs_host() == 0))
/*     */           {
/* 134 */             BeanUtils.setProperty(o, "model_id", Integer.valueOf(ModelManager.getModelBeanByEName("link").getModel_id()));
/* 135 */             BeanUtils.setProperty(o, "from_id", Integer.valueOf(info.getInfo_id()));
/* 136 */             BeanUtils.setProperty(o, "is_host", Integer.valueOf(get_type));
/* 137 */             String content_url = BeanUtils.getProperty(o, "content_url");
/*     */ 
/* 139 */             if (!s_site_id.equals(info.getSite_id()))
/*     */             {
/* 141 */               String temp_site_id = info.getSite_id();
/* 142 */               if (temp_site_id.startsWith("GK"))
/*     */               {
/* 144 */                 temp_site_id = SiteAppRele.getSiteIDByAppID(info.getApp_id());
/*     */               }
/* 146 */               content_url = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(temp_site_id) + content_url;
/*     */             }
/*     */ 
/* 149 */             BeanUtils.setProperty(o, "content_url", content_url);
/*     */           }
/*     */ 
/* 152 */           if (info.getIs_host() == 1)
/*     */           {
/* 155 */             BeanUtils.setProperty(o, "content_url", CategoryUtil.getFoldePathByCategoryID(cat_id, s_app_id, s_site_id) + id + ".html");
/*     */           }
/* 157 */           if (info.getIs_host() == 2)
/*     */           {
/* 160 */             if (!s_site_id.equals(info.getSite_id()))
/*     */             {
/* 163 */               String sourch_content_url = BeanUtils.getProperty(o, "content_url");
/* 164 */               if (!sourch_content_url.startsWith("http://"))
/*     */               {
/* 166 */                 String temp_site_id = info.getSite_id();
/* 167 */                 if (temp_site_id.startsWith("GK"))
/*     */                 {
/* 169 */                   temp_site_id = SiteAppRele.getSiteIDByAppID(info.getApp_id());
/*     */                 }
/* 171 */                 sourch_content_url = "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(temp_site_id) + sourch_content_url;
/* 172 */                 BeanUtils.setProperty(o, "content_url", sourch_content_url);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/* 177 */           ModelUtil.insert(o, model_ename, null);
/*     */ 
/* 179 */           Thread.sleep(1000L);
/*     */         }
/*     */         catch (IllegalAccessException e) {
/* 182 */           e.printStackTrace();
/*     */         }
/*     */         catch (InvocationTargetException e)
/*     */         {
/* 186 */           e.printStackTrace();
/*     */         }
/*     */         catch (NoSuchMethodException e) {
/* 189 */           e.printStackTrace();
/*     */         }
/*     */         catch (InterruptedException e) {
/* 192 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 195 */       return true;
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.infoToFw.infoToFwManager
 * JD-Core Version:    0.6.2
 */