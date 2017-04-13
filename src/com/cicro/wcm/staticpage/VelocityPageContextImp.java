/*     */ package com.cicro.wcm.staticpage;
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
/*     */ public class VelocityPageContextImp extends VelocityContextAbstract
/*     */ {
/*     */   public VelocityPageContextImp()
/*     */   {
/*     */   }
/*     */ 
/*     */   public VelocityPageContextImp(HttpServletRequest request)
/*     */   {
/*  30 */     super(request);
/*     */   }
/*     */ 
/*     */   public VelocityPageContextImp(String info_id, String site_ids, String template_ids, String model_ename)
/*     */   {
/*  43 */     this.site_id = site_ids;
/*  44 */     this.template_id = template_ids;
/*  45 */     this.vcontext.put("info_id", info_id);
/*  46 */     this.vcontext.put("site_id", site_ids);
/*  47 */     if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || 
/*  48 */       ("pic".equals(model_ename)) || ("gkvideo".equals(model_ename)) || 
/*  49 */       ("gkpic".equals(model_ename)))
/*     */     {
/*  52 */       Object o = ModelUtil.select(info_id, site_ids, model_ename);
/*  53 */       String item_name = "";
/*  54 */       if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || ("gkvideo".equals(model_ename)))
/*  55 */         item_name = "info_content";
/*  56 */       if (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename))
/*  57 */         item_name = "gk_content";
/*  58 */       if (("pic".equals(model_ename)) || ("gkpic".equals(model_ename)))
/*  59 */         item_name = "pic_content";
/*     */       try {
/*  61 */         BeanUtils.setProperty(o, item_name, HotWordManager.replaceContentHotWord(BeanUtils.getProperty(o, item_name)));
/*  62 */         this.vcontext.put("InfoData", o);
/*     */       }
/*     */       catch (IllegalAccessException e) {
/*  65 */         e.printStackTrace();
/*     */       }
/*     */       catch (InvocationTargetException e) {
/*  68 */         e.printStackTrace();
/*     */       }
/*     */       catch (NoSuchMethodException e) {
/*  71 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/*  75 */       this.vcontext.put("InfoData", ModelUtil.select(info_id, site_ids, model_ename));
/*     */     }
/*     */   }
/*     */ 
/*     */   public VelocityPageContextImp(String info_id, String site_ids, String template_ids, String model_ename, int current_page, HttpServletRequest request)
/*     */   {
/*  88 */     super(request);
/*  89 */     this.site_id = site_ids;
/*  90 */     this.template_id = template_ids;
/*  91 */     this.vcontext.put("info_id", info_id);
/*  92 */     this.vcontext.put("site_id", site_ids);
/*  93 */     if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || 
/*  94 */       ("pic".equals(model_ename)) || ("gkvideo".equals(model_ename)) || 
/*  95 */       ("gkpic".equals(model_ename)))
/*     */     {
/*  98 */       Object o = ModelUtil.select(info_id, site_ids, model_ename);
/*  99 */       String item_name = "";
/* 100 */       if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || ("video".equals(model_ename)) || ("gkvideo".equals(model_ename)))
/* 101 */         item_name = "info_content";
/* 102 */       if (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename))
/* 103 */         item_name = "gk_content";
/* 104 */       if (("pic".equals(model_ename)) || ("gkpic".equals(model_ename)))
/* 105 */         item_name = "pic_content";
/*     */       try {
/* 107 */         String page_count = BeanUtils.getProperty(o, "page_count");
/* 108 */         if ("0".equals(page_count))
/*     */         {
/* 110 */           BeanUtils.setProperty(o, item_name, HotWordManager.replaceContentHotWord(BeanUtils.getProperty(o, item_name)));
/*     */         }
/*     */         else {
/* 113 */           String is_am_tupage = BeanUtils.getProperty(o, "is_am_tupage");
/* 114 */           String content = BeanUtils.getProperty(o, item_name);
/*     */ 
/* 116 */           if ("1".equals(is_am_tupage))
/*     */           {
/* 119 */             content = AutoPagerHandl.splitContent(content, current_page, Integer.parseInt(BeanUtils.getProperty(o, "tupage_num")));
/*     */           }
/*     */           else
/*     */           {
/* 124 */             String[] tempA = content.split("<p class=\"ke-pageturning\">.*?</p>");
/* 125 */             content = tempA[(current_page - 1)];
/*     */           }
/* 127 */           content = HotWordManager.replaceContentHotWord(content);
/* 128 */           BeanUtils.setProperty(o, item_name, content);
/*     */         }
/* 130 */         this.vcontext.put("InfoData", o);
/*     */       }
/*     */       catch (IllegalAccessException e) {
/* 133 */         e.printStackTrace();
/*     */       }
/*     */       catch (InvocationTargetException e) {
/* 136 */         e.printStackTrace();
/*     */       }
/*     */       catch (NoSuchMethodException e) {
/* 139 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 143 */       this.vcontext.put("InfoData", ModelUtil.select(info_id, site_ids, model_ename));
/*     */     }
/*     */   }
/*     */ 
/*     */   public VelocityPageContextImp(Object o, String site_ids, String template_ids, int cur_page_num)
/*     */   {
/* 153 */     this.site_id = site_ids;
/* 154 */     this.template_id = template_ids;
/*     */     try {
/* 156 */       this.vcontext.put("info_id", BeanUtils.getProperty(o, "info_id"));
/*     */     }
/*     */     catch (IllegalAccessException e) {
/* 159 */       e.printStackTrace();
/*     */     }
/*     */     catch (InvocationTargetException e) {
/* 162 */       e.printStackTrace();
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 165 */       e.printStackTrace();
/*     */     }
/* 167 */     this.vcontext.put("site_id", site_ids);
/* 168 */     this.vcontext.put("cur_page_num", Integer.valueOf(cur_page_num));
/* 169 */     this.vcontext.put("InfoData", o);
/*     */   }
/*     */ 
/*     */   public VelocityPageContextImp(int cat_id, String site_ids, String template_ids)
/*     */   {
/* 181 */     this.site_id = site_ids;
/* 182 */     this.template_id = template_ids;
/* 183 */     this.vcontext.put("site_id", site_ids);
/* 184 */     this.vcontext.put("cat_id", Integer.valueOf(cat_id));
/*     */   }
/*     */ 
/*     */   public void setTemplateID(String site_ids, String template_ids)
/*     */   {
/* 195 */     this.site_id = site_ids;
/* 196 */     this.template_id = template_ids;
/*     */   }
/*     */ 
/*     */   public void setTemplateID(String cat_id, String s_site_id, String node_id, String temp_type)
/*     */   {
/* 209 */     String cat_site_id = s_site_id;
/* 210 */     if ((node_id != null) && (!"".equals(node_id)))
/* 211 */       cat_site_id = node_id;
/* 212 */     this.site_id = s_site_id;
/* 213 */     this.vcontext.put("site_id", this.site_id);
/*     */ 
/* 215 */     if ("nodeIndex".equals(temp_type))
/*     */     {
/* 217 */       this.template_id = GKNodeManager.getGKNodeBeanByNodeID(node_id).getIndex_template_id();
/* 218 */       this.vcontext.put("node_id", node_id);
/* 219 */       this.vcontext.put("cat_id", cat_id);
/*     */     }
/* 222 */     else if ((cat_id != null) && (!"".equals(cat_id)) && (!"0".equals(cat_id)))
/*     */     {
/* 224 */       this.vcontext.put("cat_id", cat_id);
/*     */       try {
/* 226 */         CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), cat_site_id);
/* 227 */         if (cb != null)
/*     */         {
/* 229 */           if ("list".equals(temp_type))
/* 230 */             this.template_id = cb.getTemplate_list();
/* 231 */           if ("index".equals(temp_type)) {
/* 232 */             this.template_id = cb.getTemplate_index();
/*     */           }
/* 234 */           if ((this.template_id == null) || ("".equals(this.template_id)) || ("0".equals(this.template_id)))
/*     */           {
/* 236 */             String root_tree_id = cb.getCat_position().split("\\$")[2];
/* 237 */             CategoryBean root_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(root_tree_id), cat_site_id);
/*     */ 
/* 239 */             if (root_cb.getCat_type() == 1)
/*     */             {
/* 241 */               if ("list".equals(temp_type))
/* 242 */                 this.template_id = root_cb.getTemplate_list();
/* 243 */               if ("index".equals(temp_type))
/* 244 */                 this.template_id = root_cb.getTemplate_index();
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 250 */           cb = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 251 */           if ("list".equals(temp_type))
/* 252 */             this.template_id = cb.getTemplate_list();
/* 253 */           if ("index".equals(temp_type)) {
/* 254 */             this.template_id = cb.getTemplate_index();
/*     */           }
/*     */         }
/* 257 */         if ((this.template_id == null) || ("".equals(this.template_id)) || ("0".equals(this.template_id)))
/*     */         {
/* 259 */           if (("10".equals(cat_id)) || ("11".equals(cat_id)) || ("12".equals(cat_id)))
/*     */           {
/* 261 */             cb = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 262 */             if ("list".equals(temp_type))
/* 263 */               this.template_id = cb.getTemplate_list();
/* 264 */             if ("index".equals(temp_type)) {
/* 265 */               this.template_id = cb.getTemplate_index();
/*     */             }
/*     */           }
/*     */         }
/* 269 */         System.out.println("template_id -- template_id" + this.template_id);
/* 270 */         System.out.println("template_id -- site_id" + this.site_id);
/*     */       }
/*     */       catch (Exception e) {
/* 273 */         System.out.println("setTemplateID -- Category is null id:" + cat_id);
/* 274 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 278 */       this.template_id = CategoryManager.getBaseCategoryTemplateListID();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSharedCategoryTemplateID(String cat_id, String s_site_id, String node_id, String temp_type)
/*     */   {
/* 292 */     this.site_id = s_site_id;
/* 293 */     this.vcontext.put("site_id", this.site_id);
/* 294 */     if ((node_id != null) && (!"".equals(node_id))) {
/* 295 */       this.vcontext.put("node_id", node_id);
/*     */     }
/* 297 */     if ((cat_id != null) && (!"".equals(cat_id)) && (!"0".equals(cat_id)))
/*     */     {
/* 299 */       this.vcontext.put("cat_id", cat_id);
/*     */       try {
/* 301 */         CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), "");
/* 302 */         if (cb != null)
/*     */         {
/* 304 */           String root_tree_id = cb.getCat_position().split("\\$")[2];
/* 305 */           CategoryBean root_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(root_tree_id), "");
/* 306 */           if ("list".equals(temp_type))
/* 307 */             this.template_id = root_cb.getTemplate_list();
/*     */         } else {
/* 309 */           System.out.println("setSharedCategoryTemplateID -- Category is null id:" + cat_id);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 313 */         e.printStackTrace();
/* 314 */         System.out.println("setSharedCategoryTemplateID -- Category is null id:" + cat_id);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setGGFWTemplateID(String cat_id, String s_site_id, String temp_type)
/*     */   {
/* 329 */     String cat_site_id = "ggfw";
/*     */ 
/* 331 */     this.site_id = s_site_id;
/* 332 */     this.vcontext.put("site_id", this.site_id);
/*     */ 
/* 334 */     if ((cat_id != null) && (!"".equals(cat_id)) && (!"0".equals(cat_id)))
/*     */     {
/* 336 */       this.vcontext.put("cat_id", cat_id);
/*     */       try {
/* 338 */         if ("list".equals(temp_type))
/* 339 */           this.template_id = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), cat_site_id).getTemplate_list();
/* 340 */         if ("index".equals(temp_type))
/* 341 */           this.template_id = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), cat_site_id).getTemplate_index();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 345 */         e.printStackTrace();
/* 346 */         System.out.println("setGGFWTemplateID -- Category is null id:" + cat_id);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setGKAppCatelogTemplateID(String cata_id, String s_site_id, String temp_type)
/*     */   {
/* 360 */     this.site_id = s_site_id;
/* 361 */     this.vcontext.put("site_id", this.site_id);
/* 362 */     if ((cata_id != null) && (!"".equals(cata_id)) && (!"0".equals(cata_id)))
/*     */     {
/* 364 */       this.vcontext.put("cata_id", cata_id);
/*     */       try {
/* 366 */         AppCatalogBean acb = AppCatalogManager.getAppCatalogBean(Integer.parseInt(cata_id));
/* 367 */         if ("list".equals(temp_type))
/* 368 */           this.template_id = acb.getTemplate_list();
/* 369 */         if ("index".equals(temp_type))
/* 370 */           this.template_id = acb.getTemplate_index();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 374 */         e.printStackTrace();
/* 375 */         System.out.println("setGKAppCatelogTemplateID -- Category is null id:" + cata_id);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.staticpage.VelocityPageContextImp
 * JD-Core Version:    0.6.2
 */