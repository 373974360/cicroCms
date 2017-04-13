/*     */ package com.cicro.wcm.template.velocity.impl;
/*     */ 
/*     */ import com.cicro.util.labelUtil.AutoPagerHandl;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*     */ import com.cicro.wcm.services.system.assist.HotWordManager;
/*     */ import com.cicro.wcm.services.zwgk.appcatalog.AppCatalogManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.velocity.VelocityContext;
/*     */ 
/*     */ public class VelocityInfoContextImp extends VelocityContextAbstract
/*     */ {
/*     */   public VelocityInfoContextImp(HttpServletRequest request)
/*     */   {
/*  28 */     super(request);
/*     */   }
/*     */ 
/*     */   public VelocityInfoContextImp(String info_id, String site_ids, String template_ids, String model_ename)
/*     */   {
/*  41 */     this.site_id = site_ids;
/*  42 */     this.template_id = template_ids;
/*  43 */     this.vcontext.put("info_id", info_id);
/*  44 */     this.vcontext.put("site_id", site_ids);
/*  45 */     if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || 
/*  46 */       ("pic".equals(model_ename)) || ("gkvideo".equals(model_ename)) || 
/*  47 */       ("gkpic".equals(model_ename)))
/*     */     {
/*  50 */       Object o = ModelUtil.select(info_id, site_ids, model_ename);
/*  51 */       String item_name = "";
/*  52 */       if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || ("gkvideo".equals(model_ename)))
/*  53 */         item_name = "info_content";
/*  54 */       if (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename))
/*  55 */         item_name = "gk_content";
/*  56 */       if (("pic".equals(model_ename)) || ("gkpic".equals(model_ename)))
/*  57 */         item_name = "pic_content";
/*     */       try {
/*  59 */         BeanUtils.setProperty(o, item_name, HotWordManager.replaceContentHotWord(BeanUtils.getProperty(o, item_name)));
/*  60 */         this.vcontext.put("InfoData", o);
/*     */       }
/*     */       catch (IllegalAccessException e) {
/*  63 */         e.printStackTrace();
/*     */       }
/*     */       catch (InvocationTargetException e) {
/*  66 */         e.printStackTrace();
/*     */       }
/*     */       catch (NoSuchMethodException e) {
/*  69 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/*  73 */       this.vcontext.put("InfoData", ModelUtil.select(info_id, site_ids, model_ename));
/*     */     }
/*     */   }
/*     */ 
/*     */   public VelocityInfoContextImp(String info_id, String site_ids, String template_ids, String model_ename, int current_page, HttpServletRequest request)
/*     */   {
/*  86 */     super(request);
/*  87 */     this.site_id = site_ids;
/*  88 */     this.template_id = template_ids;
/*  89 */     this.vcontext.put("info_id", info_id);
/*  90 */     this.vcontext.put("site_id", site_ids);
/*  91 */     if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || 
/*  92 */       ("pic".equals(model_ename)) || ("gkvideo".equals(model_ename)) || 
/*  93 */       ("gkpic".equals(model_ename)))
/*     */     {
/*  96 */       Object o = ModelUtil.select(info_id, site_ids, model_ename);
/*  97 */       String item_name = "";
/*  98 */       if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || ("gkvideo".equals(model_ename)))
/*  99 */         item_name = "info_content";
/* 100 */       if (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename))
/* 101 */         item_name = "gk_content";
/* 102 */       if (("pic".equals(model_ename)) || ("gkpic".equals(model_ename)))
/* 103 */         item_name = "pic_content";
/*     */       try {
/* 105 */         String page_count = BeanUtils.getProperty(o, "page_count");
/* 106 */         if ("0".equals(page_count))
/*     */         {
/* 108 */           BeanUtils.setProperty(o, item_name, HotWordManager.replaceContentHotWord(BeanUtils.getProperty(o, item_name)));
/*     */         }
/*     */         else {
/* 111 */           String is_am_tupage = BeanUtils.getProperty(o, "is_am_tupage");
/* 112 */           String content = BeanUtils.getProperty(o, item_name);
/*     */ 
/* 114 */           if ("1".equals(is_am_tupage))
/*     */           {
/* 117 */             content = AutoPagerHandl.splitContent(content, current_page, Integer.parseInt(BeanUtils.getProperty(o, "tupage_num")));
/*     */           }
/*     */           else
/*     */           {
/* 122 */             String[] tempA = content.split("<p class=\"ke-pageturning\">.*?</p>");
/* 123 */             content = tempA[(current_page - 1)];
/*     */           }
/* 125 */           content = HotWordManager.replaceContentHotWord(content);
/* 126 */           BeanUtils.setProperty(o, item_name, content);
/*     */         }
/* 128 */         this.vcontext.put("InfoData", o);
/*     */       }
/*     */       catch (IllegalAccessException e) {
/* 131 */         e.printStackTrace();
/*     */       }
/*     */       catch (InvocationTargetException e) {
/* 134 */         e.printStackTrace();
/*     */       }
/*     */       catch (NoSuchMethodException e) {
/* 137 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 141 */       this.vcontext.put("InfoData", ModelUtil.select(info_id, site_ids, model_ename));
/*     */     }
/*     */   }
/*     */ 
/*     */   public VelocityInfoContextImp(Object o, String site_ids, String template_ids, int cur_page_num)
/*     */   {
/* 151 */     this.site_id = site_ids;
/* 152 */     this.template_id = template_ids;
/*     */     try {
/* 154 */       this.vcontext.put("info_id", BeanUtils.getProperty(o, "info_id"));
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 157 */       e.printStackTrace();
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 160 */       e.printStackTrace();
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 163 */       e.printStackTrace();
/*     */     }
/* 165 */     this.vcontext.put("site_id", site_ids);
/* 166 */     this.vcontext.put("cur_page_num", Integer.valueOf(cur_page_num));
/* 167 */     this.vcontext.put("InfoData", o);
/*     */   }
/*     */ 
/*     */   public VelocityInfoContextImp(int cat_id, String site_ids, String template_ids)
/*     */   {
/* 179 */     this.site_id = site_ids;
/* 180 */     this.template_id = template_ids;
/* 181 */     this.vcontext.put("site_id", site_ids);
/* 182 */     this.vcontext.put("cat_id", Integer.valueOf(cat_id));
/*     */   }
/*     */ 
/*     */   public void setTemplateID(String site_ids, String template_ids)
/*     */   {
/* 193 */     this.site_id = site_ids;
/* 194 */     this.template_id = template_ids;
/*     */   }
/*     */ 
/*     */   public void setTemplateID(String cat_id, String s_site_id, String node_id, String temp_type)
/*     */   {
/* 207 */     String cat_site_id = s_site_id;
/* 208 */     if ((node_id != null) && (!"".equals(node_id)))
/* 209 */       cat_site_id = node_id;
/* 210 */     this.site_id = s_site_id;
/* 211 */     this.vcontext.put("site_id", this.site_id);
/*     */ 
/* 213 */     if ("nodeIndex".equals(temp_type))
/*     */     {
/* 215 */       this.template_id = GKNodeManager.getGKNodeBeanByNodeID(node_id).getIndex_template_id();
/* 216 */       this.vcontext.put("node_id", node_id);
/* 217 */       this.vcontext.put("cat_id", cat_id);
/*     */     }
/* 220 */     else if ((cat_id != null) && (!"".equals(cat_id)) && (!"0".equals(cat_id)))
/*     */     {
/* 222 */       this.vcontext.put("cat_id", cat_id);
/*     */       try {
/* 224 */         CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), cat_site_id);
/* 225 */         if (cb != null)
/*     */         {
/* 227 */           if ("list".equals(temp_type))
/* 228 */             this.template_id = cb.getTemplate_list();
/* 229 */           if ("index".equals(temp_type)) {
/* 230 */             this.template_id = cb.getTemplate_index();
/*     */           }
/* 232 */           if ((this.template_id == null) || ("".equals(this.template_id)) || ("0".equals(this.template_id)))
/*     */           {
/* 234 */             String root_tree_id = cb.getCat_position().split("\\$")[2];
/* 235 */             CategoryBean root_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(root_tree_id), cat_site_id);
/*     */ 
/* 237 */             if (root_cb.getCat_type() == 1)
/*     */             {
/* 239 */               if ("list".equals(temp_type))
/* 240 */                 this.template_id = root_cb.getTemplate_list();
/* 241 */               if ("index".equals(temp_type))
/* 242 */                 this.template_id = root_cb.getTemplate_index();
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 248 */           cb = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 249 */           if ("list".equals(temp_type))
/* 250 */             this.template_id = cb.getTemplate_list();
/* 251 */           if ("index".equals(temp_type)) {
/* 252 */             this.template_id = cb.getTemplate_index();
/*     */           }
/*     */         }
/* 255 */         if ((this.template_id == null) || ("".equals(this.template_id)) || ("0".equals(this.template_id)))
/*     */         {
/* 257 */           if (("10".equals(cat_id)) || ("11".equals(cat_id)) || ("12".equals(cat_id)))
/*     */           {
/* 259 */             cb = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 260 */             if ("list".equals(temp_type))
/* 261 */               this.template_id = cb.getTemplate_list();
/* 262 */             if ("index".equals(temp_type)) {
/* 263 */               this.template_id = cb.getTemplate_index();
/*     */             }
/*     */           }
/*     */         }
/* 267 */         System.out.println("template_id -- template_id" + this.template_id);
/* 268 */         System.out.println("template_id -- site_id" + this.site_id);
/*     */       }
/*     */       catch (Exception e) {
/* 271 */         System.out.println("setTemplateID -- Category is null id:" + cat_id);
/* 272 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 276 */       this.template_id = CategoryManager.getBaseCategoryTemplateListID();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSharedCategoryTemplateID(String cat_id, String s_site_id, String node_id, String temp_type)
/*     */   {
/* 290 */     this.site_id = s_site_id;
/* 291 */     this.vcontext.put("site_id", this.site_id);
/* 292 */     if ((node_id != null) && (!"".equals(node_id))) {
/* 293 */       this.vcontext.put("node_id", node_id);
/*     */     }
/* 295 */     if ((cat_id != null) && (!"".equals(cat_id)) && (!"0".equals(cat_id)))
/*     */     {
/* 297 */       this.vcontext.put("cat_id", cat_id);
/*     */       try {
/* 299 */         CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), "");
/* 300 */         if (cb != null)
/*     */         {
/* 302 */           String root_tree_id = cb.getCat_position().split("\\$")[2];
/* 303 */           CategoryBean root_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(root_tree_id), "");
/* 304 */           if ("list".equals(temp_type))
/* 305 */             this.template_id = root_cb.getTemplate_list();
/*     */         } else {
/* 307 */           System.out.println("setSharedCategoryTemplateID -- Category is null id:" + cat_id);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 311 */         e.printStackTrace();
/* 312 */         System.out.println("setSharedCategoryTemplateID -- Category is null id:" + cat_id);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setGGFWTemplateID(String cat_id, String s_site_id, String temp_type)
/*     */   {
/* 327 */     String cat_site_id = "ggfw";
/*     */ 
/* 329 */     this.site_id = s_site_id;
/* 330 */     this.vcontext.put("site_id", this.site_id);
/*     */ 
/* 332 */     if ((cat_id != null) && (!"".equals(cat_id)) && (!"0".equals(cat_id)))
/*     */     {
/* 334 */       this.vcontext.put("cat_id", cat_id);
/*     */       try {
/* 336 */         if ("list".equals(temp_type))
/* 337 */           this.template_id = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), cat_site_id).getTemplate_list();
/* 338 */         if ("index".equals(temp_type))
/* 339 */           this.template_id = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), cat_site_id).getTemplate_index();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 343 */         e.printStackTrace();
/* 344 */         System.out.println("setGGFWTemplateID -- Category is null id:" + cat_id);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setGKAppCatelogTemplateID(String cata_id, String s_site_id, String temp_type)
/*     */   {
/* 358 */     this.site_id = s_site_id;
/* 359 */     this.vcontext.put("site_id", this.site_id);
/* 360 */     if ((cata_id != null) && (!"".equals(cata_id)) && (!"0".equals(cata_id)))
/*     */     {
/* 362 */       this.vcontext.put("cata_id", cata_id);
/*     */       try {
/* 364 */         AppCatalogBean acb = AppCatalogManager.getAppCatalogBean(Integer.parseInt(cata_id));
/* 365 */         if ("list".equals(temp_type))
/* 366 */           this.template_id = acb.getTemplate_list();
/* 367 */         if ("index".equals(temp_type))
/* 368 */           this.template_id = acb.getTemplate_index();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 372 */         e.printStackTrace();
/* 373 */         System.out.println("setGKAppCatelogTemplateID -- Category is null id:" + cata_id);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityInfoContextImp
 * JD-Core Version:    0.6.2
 */