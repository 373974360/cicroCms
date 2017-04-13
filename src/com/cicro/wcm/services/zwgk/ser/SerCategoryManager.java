/*     */ package com.cicro.wcm.services.zwgk.ser;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.category.CateCurPositionBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryModel;
/*     */ import com.cicro.wcm.bean.control.SiteAppBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.dict.DataDictBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerCategoryBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.ser.SerCategoryDAO;
/*     */ import com.cicro.wcm.dao.zwgk.ser.SerResouceDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.category.CategoryModelManager;
/*     */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*     */ import com.cicro.wcm.services.system.dict.DataDictionaryManager;

/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SerCategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  41 */   public static Map<Integer, SerCategoryBean> scate_map = new HashMap();
/*  42 */   private static int ROOT_ID = 0;
/*     */ 
/*     */   static {
/*  45 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  50 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  55 */     scate_map.clear();
/*  56 */     List<SerCategoryBean> l = SerCategoryDAO.getAllSerCategoryList();
/*  57 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  59 */       for (SerCategoryBean s : l)
/*     */       {
/*  61 */         scate_map.put(Integer.valueOf(s.getSer_id()), s);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSerCategory()
/*     */   {
/*  68 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.zwgk.ser.SerCategoryManager");
/*     */   }
/*     */ 
/*     */   public static String getSerCategoryRootJSONTree()
/*     */   {
/*  77 */     String str = "";
/*  78 */     List<SerCategoryBean> s_list = getSerCategoryRootListForPublish();
/*  79 */     if ((s_list != null) && (s_list.size() > 0))
/*     */     {
/*  81 */       for (SerCategoryBean s : s_list)
/*     */       {
/*  83 */         str = str + ",{\"id\":" + s.getSer_id() + ",\"text\":\"" + s.getCat_name() + "\",\"attributes\":{\"url\":\"/manager/ggfw/ser/serCateList.jsp?ser_id=" + s.getSer_id() + "\",\"handl\":\"\"}}";
/*     */       }
/*  85 */       str = str.substring(1);
/*     */     }
/*  87 */     return "[" + str + "]";
/*     */   }
/*     */ 
/*     */   public static String getSerCateJSONTree(int ser_id)
/*     */   {
/*  96 */     String str = "";
/*  97 */     SerCategoryBean s = getSerCategoryBean(ser_id);
/*  98 */     if (s != null)
/*     */     {
/* 100 */       str = str + "{\"id\":" + s.getSer_id() + ",\"text\":\"" + s.getCat_name() + "\"";
/* 101 */       String child_str = getChildSerCateJsonStr(s.getSer_id());
/* 102 */       if ((child_str != null) && (!"".equals(child_str)))
/*     */       {
/* 104 */         str = str + ",\"children\":[" + child_str + "]";
/*     */       }
/* 106 */       str = str + "}";
/*     */     }
/* 108 */     return "[" + str + "]";
/*     */   }
/*     */ 
/*     */   public static String getChildSerCateJsonStr(int ser_id)
/*     */   {
/* 113 */     String str = "";
/* 114 */     List<SerCategoryBean> child_l = getChildSerCategoryList(ser_id);
/* 115 */     if ((child_l != null) && (child_l.size() > 0))
/*     */     {
/* 117 */       for (SerCategoryBean scb : child_l)
/*     */       {
/* 119 */         String icon_str = "";
/* 120 */         if ("leaf".equals(scb.getCat_type()))
/* 121 */           icon_str = ",\"iconCls\":\"icon-ser-resouce\"";
/* 122 */         str = str + ",{\"id\":" + scb.getSer_id() + ",\"text\":\"" + scb.getCat_name() + "\"" + icon_str + ",\"attributes\":{\"cat_type\":\"" + scb.getCat_type() + "\"}";
/* 123 */         String child_str = getChildSerCateJsonStr(scb.getSer_id());
/* 124 */         if ((child_str != null) && (!"".equals(child_str)))
/*     */         {
/* 126 */           str = str + ",\"children\":[" + child_str + "]";
/*     */         }
/* 128 */         str = str + "}";
/*     */       }
/* 130 */       str = str.substring(1);
/*     */     }
/* 132 */     return str;
/*     */   }
/*     */ 
/*     */   public static List<SerCategoryBean> getChildSerCategoryList(int ser_id)
/*     */   {
/* 141 */     List s_list = new ArrayList();
/* 142 */     Set set = scate_map.keySet();
/* 143 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 145 */       SerCategoryBean scb = (SerCategoryBean)scate_map.get(Integer.valueOf(i));
/* 146 */       if (scb.getParent_id() == ser_id)
/*     */       {
/* 148 */         s_list.add(scb);
/*     */       }
/*     */     }
/* 151 */     if ((s_list != null) && (s_list.size() > 0))
/* 152 */       Collections.sort(s_list, new SerCategoryComparator());
/* 153 */     return s_list;
/*     */   }
/*     */ 
/*     */   public static String getAllChildSerCategoryIDS(int ser_id)
/*     */   {
/* 163 */     String ids = "";
/* 164 */     SerCategoryBean s = getSerCategoryBean(ser_id);
/* 165 */     if (s != null)
/*     */     {
/* 167 */       String tree_position = s.getTree_position();
/* 168 */       Set set = scate_map.keySet();
/* 169 */       for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 171 */         SerCategoryBean scb = (SerCategoryBean)scate_map.get(Integer.valueOf(i));
/* 172 */         if ((scb.getTree_position().startsWith(tree_position)) && (scb.getSer_id() != ser_id))
/*     */         {
/* 174 */           ids = ids + "," + scb.getSer_id();
/*     */         }
/*     */       }
/* 177 */       if (!"".equals(ids)) {
/* 178 */         ids = ids.substring(1);
/*     */       }
/*     */     }
/* 181 */     return ids;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCategoryIDS(String ser_ids)
/*     */   {
/* 191 */     String ids = "";
/* 192 */     String[] tempA = ser_ids.split(",");
/* 193 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 195 */       String c_ids = getAllChildSerCategoryIDS(Integer.parseInt(tempA[i]));
/* 196 */       if ((c_ids != null) && (!"".equals(c_ids)))
/* 197 */         ids = ids + "," + c_ids;
/*     */     }
/* 199 */     if (ids.length() > 0) {
/* 200 */       ids = ids.substring(1);
/*     */     }
/* 202 */     return ids;
/*     */   }
/*     */ 
/*     */   public static List<SerCategoryBean> getSerCategoryRootListForPublish()
/*     */   {
/* 211 */     List s_list = new ArrayList();
/* 212 */     Set set = scate_map.keySet();
/* 213 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 215 */       SerCategoryBean scb = (SerCategoryBean)scate_map.get(Integer.valueOf(i));
/* 216 */       if ((scb.getParent_id() == ROOT_ID) && (scb.getPublish_status() == 1))
/*     */       {
/* 218 */         s_list.add(scb);
/*     */       }
/*     */     }
/* 221 */     if ((s_list != null) && (s_list.size() > 0))
/* 222 */       Collections.sort(s_list, new SerCategoryComparator());
/* 223 */     return s_list;
/*     */   }
/*     */ 
/*     */   public static List<SerCategoryBean> getSerCategoryRootList()
/*     */   {
/* 232 */     return getChildSerCategoryList(ROOT_ID);
/*     */   }
/*     */ 
/*     */   public static SerCategoryBean getSerCategoryBean(int ser_id)
/*     */   {
/* 242 */     if (ser_id == ROOT_ID)
/*     */     {
/* 244 */       SerCategoryBean scb = new SerCategoryBean();
/* 245 */       scb.setSer_id(ROOT_ID);
/* 246 */       scb.setTree_position("$" + ROOT_ID + "$");
/* 247 */       return scb;
/*     */     }
/* 249 */     if (scate_map.containsKey(Integer.valueOf(ser_id)))
/*     */     {
/* 251 */       return (SerCategoryBean)scate_map.get(Integer.valueOf(ser_id));
/*     */     }
/*     */ 
/* 255 */     SerCategoryBean scb = SerCategoryDAO.getSerCategoryBean(ser_id);
/* 256 */     if (scb != null)
/*     */     {
/* 258 */       scate_map.put(Integer.valueOf(ser_id), scb);
/* 259 */       return scb;
/*     */     }
/*     */ 
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */   public static SerCategoryBean getRootSerCategoryBean(int ser_id)
/*     */   {
/* 273 */     SerCategoryBean scb = getSerCategoryBean(ser_id);
/* 274 */     if (scb != null)
/*     */     {
/* 276 */       String tree_position = scb.getTree_position();
/* 277 */       String[] tempA = tree_position.split("\\$");
/* 278 */       return getSerCategoryBean(Integer.parseInt(tempA[2]));
/*     */     }
/*     */ 
/* 281 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getResouceClassBySerID(int ser_id)
/*     */   {
/* 291 */     SerCategoryBean root_b = getRootSerCategoryBean(ser_id);
/* 292 */     if (root_b != null)
/*     */     {
/* 294 */       return root_b.getDict_id();
/*     */     }
/* 296 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<DataDictBean> getDataDictList(int ser_id)
/*     */   {
/* 306 */     String dict_type = getResouceClassBySerID(ser_id);
/* 307 */     if ((dict_type != null) && (!"".equals(dict_type)))
/*     */     {
/* 309 */       return DataDictionaryManager.getDataDictionaryListOfCategory(dict_type, "");
/*     */     }
/* 311 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertSerCategory(SerCategoryBean scb, SettingLogsBean stl)
/*     */   {
/* 322 */     if ("root".equals(scb.getCat_type()))
/*     */     {
/* 325 */       insertPeculiarCategory(scb, stl);
/*     */     }
/* 327 */     SerCategoryBean parent_b = getSerCategoryBean(scb.getParent_id());
/* 328 */     scb.setTree_position(parent_b.getTree_position() + scb.getSer_id() + "$");
/* 329 */     if (scb.getPublish_status() == 1)
/*     */     {
/* 331 */       scb.setPublish_time(DateUtil.getCurrentDateTime());
/*     */     }
/* 333 */     if (SerCategoryDAO.insertSerCategory(scb, stl))
/*     */     {
/* 335 */       reloadSerCategory();
/* 336 */       return true;
/*     */     }
/* 338 */     return false;
/*     */   }
/*     */ 
/*     */   public static void insertPeculiarCategory(SerCategoryBean scb, SettingLogsBean stl)
/*     */   {
/* 348 */     CategoryModel xgxw_cm = new CategoryModel();
/* 349 */     CategoryModel cjwt_cm = new CategoryModel();
/*     */ 
/* 351 */     CategoryBean cgb = new CategoryBean();
/* 352 */     int id = CategoryManager.getNewCategoryID();
/* 353 */     cgb.setCat_cname("相关信息");
/* 354 */     cgb.setCat_ename("xgxw");
/* 355 */     cgb.setId(id);
/* 356 */     cgb.setCat_id(id);
/* 357 */     cgb.setApp_id("ggfw");
/* 358 */     cgb.setSite_id("ggfw");
/* 359 */     cgb.setIs_generate_index(0);
/* 360 */     cgb.setIs_show(0);
/* 361 */     if (CategoryManager.insertCategory(cgb, true, stl))
/*     */     {
/* 363 */       scb.setXgwt_cat_id(id);
/* 364 */       xgxw_cm.setCat_id(id);
/*     */     }
/*     */ 
/* 367 */     cgb = new CategoryBean();
/* 368 */     id = CategoryManager.getNewCategoryID();
/* 369 */     cgb.setCat_cname("常见问题");
/* 370 */     cgb.setCat_ename("cjwt");
/* 371 */     cgb.setId(id);
/* 372 */     cgb.setCat_id(id);
/* 373 */     cgb.setApp_id("ggfw");
/* 374 */     cgb.setSite_id("ggfw");
/* 375 */     cgb.setIs_generate_index(0);
/* 376 */     cgb.setIs_show(0);
/* 377 */     if (CategoryManager.insertCategory(cgb, true, stl))
/*     */     {
/* 379 */       scb.setCjwt_cat_id(id);
/* 380 */       cjwt_cm.setCat_id(id);
/*     */     }
/*     */ 
/* 383 */     List cm = new ArrayList();
/* 384 */     xgxw_cm.setApp_id("ggfw");
/* 385 */     xgxw_cm.setModel_id(11);
/* 386 */     xgxw_cm.setSite_id("ggfw");
/* 387 */     xgxw_cm.setTemplate_content(getSerTemplateID("content"));
/*     */ 
/* 389 */     cjwt_cm.setApp_id("ggfw");
/* 390 */     cjwt_cm.setModel_id(11);
/* 391 */     cjwt_cm.setSite_id("ggfw");
/* 392 */     cjwt_cm.setTemplate_content(getSerTemplateID("content"));
/* 393 */     cm.add(xgxw_cm);
/* 394 */     cm.add(cjwt_cm);
/* 395 */     CategoryModelManager.insertCategoryModel(cm);
/*     */   }
/*     */ 
/*     */   public static boolean updateSerCategory(SerCategoryBean scb, SettingLogsBean stl)
/*     */   {
/* 406 */     if ((scb.getPublish_status() == 1) && ("".equals(scb.getPublish_time())))
/*     */     {
/* 408 */       scb.setPublish_time(DateUtil.getCurrentDateTime());
/*     */     }
/* 410 */     if (scb.getPublish_status() == 0)
/* 411 */       scb.setPublish_time("");
/* 412 */     if (SerCategoryDAO.updateSerCategory(scb, stl))
/*     */     {
/* 414 */       reloadSerCategory();
/* 415 */       return true;
/*     */     }
/* 417 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerCategoryStatus(String ser_ids, String publish_status, SettingLogsBean stl)
/*     */   {
/* 428 */     if (SerCategoryDAO.updateSerCategoryStatus(ser_ids, publish_status, stl))
/*     */     {
/* 430 */       reloadSerCategory();
/* 431 */       return true;
/*     */     }
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortSerCategory(String ids, SettingLogsBean stl)
/*     */   {
/* 444 */     if (SerCategoryDAO.sortSerCategory(ids, stl))
/*     */     {
/* 446 */       reloadSerCategory();
/* 447 */       return true;
/*     */     }
/* 449 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSerCategory(String ser_ids, SettingLogsBean stl)
/*     */   {
/* 460 */     String all_child_ids = getAllChildCategoryIDS(ser_ids);
/* 461 */     if (!"".equals(all_child_ids))
/* 462 */       ser_ids = ser_ids + "," + all_child_ids;
/* 463 */     if (SerCategoryDAO.deleteSerCategory(ser_ids, stl))
/*     */     {
/* 465 */       reloadSerCategory();
/* 466 */       SerResouceDAO.deleteSerResouceByCategory(ser_ids, stl);
/* 467 */       return true;
/*     */     }
/* 469 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveSerCategory(int parent_id, String ser_ids, SettingLogsBean stl)
/*     */   {
/* 480 */     SerCategoryBean parent_scb = getSerCategoryBean(parent_id);
/* 481 */     if (parent_scb != null)
/*     */     {
/* 483 */       String parent_tree_position = parent_scb.getTree_position();
/* 484 */       if ((ser_ids != null) && (!"".equals(ser_ids))) {
/*     */         try
/*     */         {
/* 487 */           String[] tempA = ser_ids.split(",");
/* 488 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 490 */             moveSerCategoryHandl(tempA[i], parent_id+"", parent_tree_position, stl);
/*     */           }
/* 492 */           reloadSerCategory();
/* 493 */           return true;
/*     */         }
/*     */         catch (Exception e) {
/* 496 */           e.printStackTrace();
/* 497 */           return false;
/*     */         }
/*     */       }
/* 500 */       return true;
/*     */     }
/* 502 */     return false;
/*     */   }
/*     */ 
/*     */   public static void moveSerCategoryHandl(String ser_id, String parent_id, String tree_position, SettingLogsBean stl)
/*     */   {
/* 508 */     String position = tree_position + ser_id + "$";
/* 509 */     Map new_m = new HashMap();
/* 510 */     new_m.put("ser_id", ser_id);
/* 511 */     new_m.put("parent_id", parent_id);
/* 512 */     new_m.put("tree_position", position);
/* 513 */     if (SerCategoryDAO.moveSerCategory(new_m, stl))
/*     */     {
/* 515 */       List c_list = getChildSerCategoryList(Integer.parseInt(ser_id));
/* 516 */       if ((c_list != null) && (c_list.size() > 0))
/*     */       {
/* 518 */         for (int i = 0; i < c_list.size(); i++)
/*     */         {
/* 520 */           moveSerCategoryHandl(((SerCategoryBean)c_list.get(i)).getSer_id()+"", ser_id, position, stl);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getNewID()
/*     */   {
/* 532 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.SER_CATEGORY_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static int getSerTemplateID(String template_type)
/*     */   {
/* 542 */     SiteAppBean sab = SiteAppRele.getSiteAppBean("ggfw");
/* 543 */     if (sab != null)
/*     */     {
/* 545 */       if ("list".equals(template_type))
/*     */       {
/* 547 */         if ((sab.getMark1() != null) && (!"".equals(sab.getMark1()))) {
/* 548 */           return Integer.parseInt(sab.getMark1());
/*     */         }
/* 550 */         return 0;
/*     */       }
/* 552 */       if ("content".equals(template_type))
/*     */       {
/* 554 */         if ((sab.getMark2() != null) && (!"".equals(sab.getMark2()))) {
/* 555 */           return Integer.parseInt(sab.getMark2());
/*     */         }
/* 557 */         return 0;
/*     */       }
/* 559 */       return 0;
/*     */     }
/* 561 */     return 0;
/*     */   }
/*     */ 
/*     */   public static List<CateCurPositionBean> getSerCategoryTreeposition(int ser_id)
/*     */   {
/* 567 */     List ccpb_list = new ArrayList();
/* 568 */     SerCategoryBean scb = getSerCategoryBean(ser_id);
/* 569 */     String tree_position = scb.getTree_position();
/* 570 */     String[] tempA = tree_position.split("\\$");
/* 571 */     if ((tempA != null) && (tempA.length > 0))
/*     */     {
/* 573 */       for (int i = 2; i < tempA.length; i++)
/*     */       {
/* 575 */         SerCategoryBean s = getSerCategoryBean(Integer.parseInt(tempA[i]));
/* 576 */         if (s != null)
/*     */         {
/* 578 */           CateCurPositionBean ccpb = new CateCurPositionBean();
/* 579 */           ccpb.setCat_cname(s.getCat_name());
/* 580 */           ccpb.setCat_id(s.getSer_id());
/* 581 */           if ("root".equals(s.getCat_type()))
/* 582 */             ccpb.setUrl("serIndex.jsp?ser_id=" + s.getSer_id());
/*     */           else {
/* 584 */             ccpb.setUrl("serList.jsp?ser_id=" + s.getSer_id());
/*     */           }
/* 586 */           ccpb_list.add(ccpb);
/*     */         }
/*     */       }
/*     */     }
/* 590 */     return ccpb_list;
/*     */   }
/*     */ 
/*     */   public static boolean updateSerTemplateContent(String template_content_id)
/*     */   {
/* 600 */     return SerCategoryDAO.updateSerTemplateContent(template_content_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 624 */     String ss = "$0$1$";
/* 625 */     String[] a = ss.split("\\$");
/* 626 */     System.out.println(a[2]);
/*     */   }
/*     */ }

class SerCategoryComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 606 */     SerCategoryBean scb1 = (SerCategoryBean)o1;
/* 607 */     SerCategoryBean scb2 = (SerCategoryBean)o2;
/* 608 */     if (scb1.getSort_id() > scb2.getSort_id()) {
/* 609 */       return 1;
/*     */     }
/* 611 */     if (scb1.getSort_id() == scb2.getSort_id()) {
/* 612 */       return 0;
/*     */     }
/* 614 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ser.SerCategoryManager
 * JD-Core Version:    0.6.2
 */