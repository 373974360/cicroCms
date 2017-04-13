/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.template.TemplateCategoryDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TemplateCategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  25 */   private static String right_main_page_path = JconfigFactory.getJconfigUtilInstance("velocityTemplate").getProperty("path", "", "templatePagePath");
/*     */ 
/*  27 */   private static Map<String, TemplateCategoryBean> templateCategory = new HashMap();
/*     */ 
/*     */   static {
/*  30 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  35 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  40 */     templateCategory.clear();
/*     */     try {
/*  42 */       List tc_list = getAllTemplateCategoryList();
/*  43 */       if ((tc_list != null) && (tc_list.size() > 0))
/*     */       {
/*  45 */         for (TemplateCategoryBean tcb : tc_list)
/*     */         {
/*  47 */           templateCategory.put(tcb.getTcat_id() + "_" + tcb.getSite_id(), tcb);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  52 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadTemplateCategory()
/*     */   {
/*  58 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.template.TemplateCategoryManager");
/*     */   }
/*     */ 
/*     */   public static int getNewID()
/*     */   {
/*  63 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_CATEGORY_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static TemplateCategoryBean getTemplateCategoryBean(int t_id, String site_id, String app_id)
/*     */   {
/*  73 */     if (templateCategory.containsKey(t_id + "_" + site_id))
/*     */     {
/*  75 */       return (TemplateCategoryBean)templateCategory.get(t_id + "_" + site_id);
/*     */     }
/*     */ 
/*  78 */     TemplateCategoryBean tcb = TemplateCategoryDAO.getTemplateCategoryBean(t_id, site_id);
/*  79 */     if (tcb != null)
/*     */     {
/*  81 */       templateCategory.put(t_id + "_" + site_id, tcb);
/*  82 */       return tcb;
/*     */     }
/*     */ 
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getTemplateCategoryCount(Map<String, String> con_map)
/*     */   {
/*  96 */     return TemplateCategoryDAO.getTemplateCategoryCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateCategory(TemplateCategoryBean template, SettingLogsBean stl)
/*     */   {
/* 105 */     if (TemplateCategoryDAO.updateTemplateCategory(template, stl)) {
/* 106 */       reloadTemplateCategory();
/* 107 */       return true;
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateCategory(TemplateCategoryBean template, SettingLogsBean stl)
/*     */   {
/* 118 */     if (template == null) {
/* 119 */       return false;
/*     */     }
/* 121 */     if (TemplateCategoryDAO.addTemplateCategory(template, stl)) {
/* 122 */       reloadTemplateCategory();
/* 123 */       return true;
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortTemplateCategory(String ids, SettingLogsBean stl)
/*     */   {
/* 136 */     if (TemplateCategoryDAO.sortTemplateCategory(ids, stl))
/*     */     {
/* 138 */       reloadTemplateCategory();
/* 139 */       return true;
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getAllTemplateCategoryList()
/*     */   {
/* 150 */     return TemplateCategoryDAO.getAllTemplateCategoryList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getTemplateCategoryListForDB(Map<String, String> con_map)
/*     */   {
/* 160 */     return TemplateCategoryDAO.getTemplateCategoryListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateCategoryById(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 171 */     if ((t_ids == null) || (t_ids.equals(""))) {
/* 172 */       return false;
/*     */     }
/*     */ 
/* 175 */     t_ids = getAllChildTemplateCategory(t_ids, site_id, "");
/*     */ 
/* 177 */     if (TemplateCategoryDAO.delTemplateCategory(t_ids, site_id, stl)) {
/* 178 */       if (t_ids.indexOf(",") != -1)
/* 179 */         for (String id : t_ids.split(",")) {
/* 180 */           templateCategory.remove(id + "_" + site_id);
/* 181 */           List list = TemplateEditManager.getAllTemplateEditList(id, site_id, "app_id");
/* 182 */           String tids = "";
/* 183 */           for (TemplateEditBean teb : list) {
/* 184 */             tids = tids + "," + teb.getT_id();
/*     */           }
/* 186 */           TemplateEditManager.delTemplateEditById(tids.substring(1), site_id, stl);
/*     */         }
/*     */       else {
/* 189 */         for (String t_id : t_ids.split(",")) {
/* 190 */           templateCategory.remove(t_id + "_" + site_id);
/* 191 */           List list = TemplateEditManager.getAllTemplateEditList(t_id, site_id, "app_id");
/* 192 */           String tids = "";
/* 193 */           for (TemplateEditBean teb : list) {
/* 194 */             tids = tids + "," + teb.getT_id();
/*     */           }
/* 196 */           if (tids.startsWith(",")) {
/* 197 */             tids = tids.substring(1);
/*     */           }
/* 199 */           TemplateEditManager.delTemplateEditById(tids, site_id, stl);
/*     */         }
/*     */       }
/* 202 */       reloadTemplateCategory();
/* 203 */       return true;
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getAllChildTemplateCategory(String id, String site_id, String appid) {
/* 209 */     List db = getChildList(id, site_id);
/* 210 */     if (db != null)
/*     */     {
/* 212 */       String json_str = id;
/* 213 */       String child_str = getAllChildTemplateCategory2(db, "");
/* 214 */       if ((child_str != null) && (!"".equals(child_str)))
/* 215 */         json_str = json_str + "," + child_str;
/* 216 */       return json_str;
/*     */     }
/* 218 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getAllChildTemplateCategory2(List<TemplateCategoryBean> dl, String loop_type)
/*     */   {
/* 223 */     String json_str = "";
/* 224 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 226 */       for (int i = 0; i < dl.size(); i++)
/*     */       {
/* 228 */         List child_d_list = getChildList(((TemplateCategoryBean)dl.get(i)).getTcat_id(), ((TemplateCategoryBean)dl.get(i)).getSite_id());
/*     */ 
/* 230 */         json_str = json_str + ((TemplateCategoryBean)dl.get(i)).getTcat_id();
/*     */ 
/* 232 */         if ((child_d_list != null) && (child_d_list.size() > 0))
/* 233 */           json_str = json_str + "," + getAllChildTemplateCategory2(child_d_list, "");
/* 234 */         if (i + 1 != dl.size())
/* 235 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 238 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getChildList(String parent_id, String site_id)
/*     */   {
/* 243 */     List tc_list = new ArrayList();
/* 244 */     Set s = templateCategory.keySet();
/* 245 */     for (String i : s)
/*     */     {
/* 247 */       if (i.substring(i.indexOf("_") + 1).equals(site_id))
/*     */       {
/* 249 */         TemplateCategoryBean tcb = (TemplateCategoryBean)templateCategory.get(i);
/* 250 */         if (parent_id.equals(tcb.getParent_id()))
/* 251 */           tc_list.add(tcb);
/*     */       }
/*     */     }
/* 254 */     if ((tc_list != null) && (tc_list.size() > 0))
/* 255 */       Collections.sort(tc_list, new TemplateCategoryManager.TemplateCategoryComparator());
/* 256 */     return tc_list;
/*     */   }
/*     */ 
/*     */   public static String templateCategoryListToJsonStr(String id, String site_id, String appid)
/*     */   {
/* 262 */     List db = getChildList(id, site_id);
/* 263 */     if (db != null)
/*     */     {
/* 265 */       String json_str = "[{\"id\":" + id + ",\"iconCls\":\"icon-org\", \"text\":\"模板管理\",\"attributes\":{\"url\":\"" + 
/* 266 */         right_main_page_path + "?tid=" + id + "\"}";
/* 267 */       String child_str = tcListToStrHandl(db, "");
/* 268 */       if ((child_str != null) && (!"".equals(child_str)))
/* 269 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/* 270 */       json_str = json_str + "}]";
/* 271 */       return json_str;
/*     */     }
/* 273 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String tcListToStrHandl(List<TemplateCategoryBean> dl, String loop_type)
/*     */   {
/* 278 */     String json_str = "";
/* 279 */     String icon_str = "\"iconCls\":\"icon-user\",";
/* 280 */     String child_state = "";
/* 281 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 283 */       for (int i = 0; i < dl.size(); i++)
/*     */       {
/* 289 */         icon_str = "\"iconCls\":\"icon-templateFolder\"";
/*     */ 
/* 291 */         json_str = json_str + "{";
/* 292 */         List child_d_list = getChildList(((TemplateCategoryBean)dl.get(i)).getTcat_id(), ((TemplateCategoryBean)dl.get(i)).getSite_id());
/* 293 */         if ((child_d_list != null) && (child_d_list.size() > 0) && (!"one".equals(loop_type)))
/* 294 */           child_state = "\"state\":'closed',";
/*     */         else {
/* 296 */           child_state = "";
/*     */         }
/* 298 */         json_str = json_str + "\"id\":" + ((TemplateCategoryBean)dl.get(i)).getTcat_id() + "," + icon_str + "," + child_state + "\"text\":\"" + ((TemplateCategoryBean)dl.get(i)).getTcat_cname() + "\",\"attributes\":{\"url\":\"" + 
/* 299 */           right_main_page_path + "?tid=" + ((TemplateCategoryBean)dl.get(i)).getTcat_id() + "\"}";
/*     */ 
/* 301 */         if ((child_d_list != null) && (child_d_list.size() > 0))
/* 302 */           json_str = json_str + ",\"children\":[" + tcListToStrHandl(child_d_list, "") + "]";
/* 303 */         json_str = json_str + "}";
/* 304 */         if (i + 1 != dl.size())
/* 305 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 308 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static boolean addDefaultZTCategory(String site_id)
/*     */   {
/* 335 */     TemplateCategoryBean cgb = new TemplateCategoryBean();
/* 336 */     cgb.setId(getNewID());
/* 337 */     cgb.setTcat_id(1);
/* 338 */     cgb.setTcat_cname("专题类");
/* 339 */     cgb.setTcat_position("/");
/* 340 */     cgb.setApp_id("0");
/* 341 */     cgb.setSite_id(site_id);
/* 342 */     return addTemplateCategory(cgb, null);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 347 */     String str = templateCategoryListToJsonStr("0", "CMSlik", "");
/* 348 */     System.out.println(str);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateCategoryManager
 * JD-Core Version:    0.6.2
 */