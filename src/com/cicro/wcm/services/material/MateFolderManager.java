/*     */ package com.cicro.wcm.services.material;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.material.MateFolderBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.material.MateFolderDao;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class MateFolderManager
/*     */   implements ISyncCatch
/*     */ {
/*  41 */   private static String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path");
/*  42 */   private static String wcm_files_upload_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "wcm_files") + "/upload";
/*  43 */   private static String public_save_path = JconfigUtilContainer.bashConfig().getProperty("save_path", "", "resource_server");
/*  44 */   private static String img_domain = JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server");
/*  45 */   private static String site_port = JconfigUtilContainer.bashConfig().getProperty("port", "", "site_port");
/*     */ 
/*  47 */   private static TreeMap<String, MateFolderBean> mf_map = new TreeMap();
/*  48 */   private static String root_MateFolder_fid = "0";
/*     */ 
/*  50 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  55 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  60 */     List mf_list = MateFolderDao.getMateFolderBeanList();
/*  61 */     mf_map.clear();
/*  62 */     if ((mf_list != null) && (mf_list.size() > 0))
/*  63 */       for (int i = 0; i < mf_list.size(); i++)
/*  64 */         mf_map.put(((MateFolderBean)mf_list.get(i)).getF_id(), (MateFolderBean)mf_list.get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadMateFolder()
/*     */   {
/*  75 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.material.MateFolderManager");
/*     */   }
/*     */ 
/*     */   public static Map<String, MateFolderBean> getMateFolderMap()
/*     */   {
/*  84 */     return mf_map;
/*     */   }
/*     */ 
/*     */   public static String getMateFolderTreeJsonStr(String f_id, String site_id, String user_id)
/*     */   {
/*  93 */     String json_str = "[" + getMateTypeTreeJsonStr(site_id) + ",";
/*  94 */     json_str = json_str + "{\"id\":\"0\",\"text\":\"我的素材\",\"attributes\":{\"ctype\":\"custom\"}";
/*  95 */     String child_str = getMateFolderTreeJsonStrHandl(getMateFolderList(root_MateFolder_fid, site_id, user_id));
/*  96 */     if ((child_str != null) && (!"".equals(child_str)))
/*  97 */       json_str = json_str + ",\"children\":[" + child_str + "]";
/*  98 */     json_str = json_str + "}]";
/*  99 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getMFTreeJsonStrForCustom(String site_id, String user_id)
/*     */   {
/* 108 */     String json_str = "[";
/* 109 */     json_str = json_str + "{\"id\":\"0\",\"text\":\"我的素材\",\"attributes\":{\"ctype\":\"custom\"}";
/* 110 */     String child_str = getMateFolderTreeJsonStrHandl(getMateFolderList(root_MateFolder_fid, site_id, user_id));
/* 111 */     if ((child_str != null) && (!"".equals(child_str)))
/* 112 */       json_str = json_str + ",\"children\":[" + child_str + "]";
/* 113 */     json_str = json_str + "}]";
/* 114 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getMateFolderTreeJsonStrHandl(List<MateFolderBean> all_matefolder_list)
/*     */   {
/* 124 */     String json_str = "";
/* 125 */     if ((all_matefolder_list != null) && (all_matefolder_list.size() > 0))
/*     */     {
/* 127 */       for (int i = 0; i < all_matefolder_list.size(); i++)
/*     */       {
/* 129 */         json_str = json_str + "{";
/* 130 */         json_str = json_str + "\"id\":" + ((MateFolderBean)all_matefolder_list.get(i)).getF_id() + ",\"text\":\"" + ((MateFolderBean)all_matefolder_list.get(i)).getCname() + "\",\"attributes\":{\"ctype\":\"custom\"}";
/* 131 */         List child_o_list = getMateFolderList(((MateFolderBean)all_matefolder_list.get(i)).getF_id(), ((MateFolderBean)all_matefolder_list.get(i)).getSite_id(), ((MateFolderBean)all_matefolder_list.get(i)).getUser_id());
/* 132 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/* 133 */           json_str = json_str + ",\"children\":[" + getMateFolderTreeJsonStrHandl(child_o_list) + "]";
/* 134 */         json_str = json_str + "}";
/* 135 */         if (i + 1 != all_matefolder_list.size())
/* 136 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 139 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static MateFolderBean getMateFolderBean(String f_id)
/*     */   {
/* 148 */     if (mf_map.containsKey(f_id))
/*     */     {
/* 150 */       return (MateFolderBean)mf_map.get(f_id);
/*     */     }
/*     */ 
/* 153 */     MateFolderBean mf = MateFolderDao.getMateFolderBean(f_id);
/* 154 */     mf_map.put(f_id, mf);
/* 155 */     return mf;
/*     */   }
/*     */ 
/*     */   public static String getChildMateFolderCount(String f_id, String user_id)
/*     */   {
/* 166 */     int count = 0;
/* 167 */     Iterator iter = mf_map.entrySet().iterator();
/* 168 */     while (iter.hasNext()) {
/* 169 */       Entry entry = (Entry)iter.next();
/* 170 */       String key = (String)entry.getKey();
/* 171 */       MateFolderBean mf = getMateFolderBean(((MateFolderBean)mf_map.get(key)).getF_id());
/* 172 */       if ((f_id.equals(((MateFolderBean)mf_map.get(key)).getParent_id())) && (user_id.equals(mf.getUser_id())))
/*     */       {
/* 174 */         count++;
/*     */       }
/*     */     }
/* 177 */     return count;
/*     */   }
/*     */ 
/*     */   public static List<MateFolderBean> getMateFolderList(String f_id, String site_id, String user_id)
/*     */   {
/* 187 */     List oL = new ArrayList();
/* 188 */     Iterator iter = mf_map.entrySet().iterator();
/*     */ 
/* 190 */     while (iter.hasNext()) {
/* 191 */       Entry entry = (Entry)iter.next();
/* 192 */       String key = (String)entry.getKey();
/* 193 */       MateFolderBean mf = getMateFolderBean(((MateFolderBean)mf_map.get(key)).getF_id());
/* 194 */       if (mf != null)
/*     */       {
/* 196 */         if (("0".equals(Integer.valueOf(mf.getParent_id()))) || ((f_id.equals(mf.getParent_id())) && (site_id.equals(mf.getSite_id())) && (user_id.equals(mf.getUser_id()))))
/*     */         {
/* 198 */           oL.add((MateFolderBean)entry.getValue());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 203 */     Collections.sort(oL, new MateFolderManager.MateFolderComparator());
/* 204 */     return oL;
/*     */   }
/*     */ 
/*     */   public static String getALLChildOptIDSByID(String f_ids)
/*     */   {
/* 213 */     String o_ids = "";
/* 214 */     if ((f_ids != null) && (!"".equals(f_ids)))
/*     */     {
/* 216 */       String[] opt_a = f_ids.split(",");
/* 217 */       for (int i = 0; i < opt_a.length; i++)
/*     */       {
/* 219 */         MateFolderBean mf = getMateFolderBean(opt_a[i]);
/* 220 */         if (mf != null)
/*     */         {
/* 222 */           String tree_position = mf.getF_treeposition();
/* 223 */           Set set = mf_map.keySet();
/* 224 */           for (String j : set) {
/* 225 */             mf = (MateFolderBean)mf_map.get(j);
/* 226 */             if ((mf.getF_treeposition().startsWith(tree_position)) && (!tree_position.equals(mf.getF_treeposition())))
/* 227 */               o_ids = o_ids + "," + mf.getF_id();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 232 */     return o_ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertMateFolder(MateFolderBean mf, SettingLogsBean stl)
/*     */   {
/* 247 */     String father_treeposition = "";
/* 248 */     MateFolderBean f_FBean = getMateFolderBean(mf.getParent_id());
/* 249 */     System.out.println("insertMateFolder=====" + f_FBean);
/* 250 */     if (f_FBean == null)
/* 251 */       father_treeposition = "$0$";
/*     */     else {
/* 253 */       father_treeposition = f_FBean.getF_treeposition();
/*     */     }
/* 255 */     mf.setF_treeposition(father_treeposition + mf.getF_id() + "$");
/* 256 */     String creatDtime = DateUtil.getCurrentDateTime();
/* 257 */     mf.setCreat_dtime(creatDtime);
/*     */ 
/* 260 */     mf.setApp_id("cms");
/* 261 */     if (MateFolderDao.insertMateFolder(mf, stl))
/*     */     {
/* 263 */       reloadMateFolder();
/* 264 */       return true;
/*     */     }
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getMateFolderID()
/*     */   {
/* 272 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.MateFolder_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean updateMateFolder(MateFolderBean mf, SettingLogsBean stl)
/*     */   {
/* 283 */     if (MateFolderDao.updateMateFolder(mf, stl))
/*     */     {
/* 285 */       reloadMateFolder();
/* 286 */       return true;
/*     */     }
/* 288 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMateFolder(String parent_id, String f_ids, SettingLogsBean stl, String site_id, String user_id)
/*     */   {
/* 300 */     String parent_tree_position = getMateFolderBean(parent_id).getF_treeposition();
/* 301 */     if ((f_ids != null) && (!"".equals(f_ids))) {
/*     */       try
/*     */       {
/* 304 */         String[] tempA = f_ids.split(",");
/* 305 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 307 */           moveMateFolderHandl(tempA[i], parent_id, parent_tree_position, stl, site_id, user_id);
/*     */         }
/* 309 */         reloadMateFolder();
/* 310 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 313 */         e.printStackTrace();
/* 314 */         return false;
/*     */       }
/*     */     }
/* 317 */     return true;
/*     */   }
/*     */ 
/*     */   public static void moveMateFolderHandl(String f_id, String parent_id, String tree_position, SettingLogsBean stl, String site_id, String user_id)
/*     */   {
/* 322 */     String position = tree_position + f_id + "$";
/* 323 */     Map new_m = new HashMap();
/* 324 */     new_m.put("f_id", f_id);
/* 325 */     new_m.put("parent_id", parent_id);
/* 326 */     new_m.put("f_treeposition", position);
/* 327 */     if (MateFolderDao.moveMateFolder(new_m, stl))
/*     */     {
/* 329 */       List o_list = getMateFolderList(f_id, site_id, user_id);
/* 330 */       if ((o_list != null) && (o_list.size() > 0))
/*     */       {
/* 332 */         for (int i = 0; i < o_list.size(); i++)
/*     */         {
/* 334 */           moveMateFolderHandl(((MateFolderBean)o_list.get(i)).getF_id(), f_id, position, stl, site_id, user_id);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean deleteMateFolder(String f_id, SettingLogsBean stl)
/*     */   {
/* 348 */     f_id = f_id + getALLChildOptIDSByID(f_id);
/* 349 */     if (MateFolderDao.deleteMateFolder(f_id, stl))
/*     */     {
/* 351 */       reloadMateFolder();
/* 352 */       return true;
/*     */     }
/* 354 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void insertMateFolderTest()
/*     */   {
/* 381 */     MateFolderBean mf = new MateFolderBean();
/* 382 */     mf.setF_id(1);
/* 383 */     mf.setApp_id("cms");
/* 384 */     mf.setF_treeposition("$0");
/* 385 */     mf.setCname("测试信息");
/* 386 */     mf.setCreat_dtime("2011-04-08 19:20:00");
/* 387 */     mf.setParent_id(0);
/* 388 */     mf.setUser_id(3);
/* 389 */     mf.setSite_id("tt");
/* 390 */     insertMateFolder(mf, new SettingLogsBean());
/*     */   }
/*     */ 
/*     */   public static String getMateTypeTreeJsonStr(String site_id)
/*     */   {
/* 402 */     String json_str = "{\"id\":-1,\"text\":\"素材类别\",\"attributes\":{\"ctype\":\"fixed\",\"state\":\"open\"},\"children\":[{\"id\":\"type_0\",\"text\":\"图片\",\"attributes\":{\"ctype\":\"fixed\"},\"state\":\"closed\",\"children\":[" + 
/* 403 */       getFolderJSONStr(site_id, "type_0") + "]}," + 
/* 404 */       "{\"id\":\"type_1\",\"text\":\"Flash\",\"attributes\":{\"ctype\":\"fixed\"},\"state\":\"closed\",\"children\":[" + getFolderJSONStr(site_id, "type_1") + "]}," + 
/* 405 */       "{\"id\":\"type_2\",\"text\":\"视频\",\"attributes\":{\"ctype\":\"fixed\"},\"state\":\"closed\",\"children\":[" + getFolderJSONStr(site_id, "type_2") + "]}," + 
/* 406 */       "{\"id\":\"type_3\",\"text\":\"文件\",\"attributes\":{\"ctype\":\"fixed\"},\"state\":\"closed\",\"children\":[" + getFolderJSONStr(site_id, "type_3") + "]}," + 
/* 407 */       "{\"id\":\"type_4\",\"text\":\"其它\",\"attributes\":{\"ctype\":\"fixed\"},\"state\":\"closed\",\"children\":[" + getFolderJSONStr(site_id, "type_4") + "]}]";
/* 408 */     json_str = json_str + "}";
/* 409 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getUploadFilePath(String site_id)
/*     */   {
/* 415 */     String savePath = "";
/* 416 */     if ((img_domain != null) && (!"".equals(img_domain.trim())))
/*     */     {
/* 418 */       savePath = FormatUtil.formatPath(public_save_path);
/*     */     }
/*     */     else
/*     */     {
/* 422 */       savePath = wcm_files_upload_path;
/*     */     }
/* 424 */     savePath = FormatUtil.formatPath(savePath + "/" + site_id + "/");
/* 425 */     return savePath;
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStr(String site_id, String imgleibie)
/*     */   {
/* 431 */     String upload_img_path = getUploadFilePath(site_id);
/* 432 */     String json = "";
/* 433 */     json = json + getFolderJSONStrForPath(upload_img_path, imgleibie);
/* 434 */     if (ServerManager.isWindows())
/*     */     {
/* 436 */       json = json.replaceAll("\\\\", "\\\\\\\\");
/*     */     }
/* 438 */     return json;
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStrForPath(String file_path, String imgleibie)
/*     */   {
/* 443 */     String json_str = "";
/* 444 */     List list = FileOperation.getFolderList(file_path);
/* 445 */     if ((list != null) && (list.size() > 0))
/*     */     {
/* 447 */       json_str = getFolderJSONStrHandl(list, imgleibie);
/*     */     }
/* 449 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONStrHandl(List<String> l, String imgleibie)
/*     */   {
/* 454 */     String json = "";
/* 455 */     TreeMap map = new TreeMap();
/* 456 */     for (int i = 0; i < l.size(); i++)
/*     */     {
/* 458 */       String upload_years = (String)l.get(i);
/* 459 */       String upload_month = (String)l.get(i);
/* 460 */       String month = "";
/* 461 */       upload_years = upload_years.substring(upload_years.lastIndexOf(File.separator) + 1).substring(0, 4);
/* 462 */       upload_month = upload_month.substring(upload_month.lastIndexOf(File.separator) + 1).substring(4, 6);
/* 463 */       if (map.containsKey(upload_years))
/*     */       {
/* 465 */         month = (String)map.get(upload_years);
/* 466 */         map.remove(upload_years);
/* 467 */         month = month + "," + upload_month;
/* 468 */         map.put(upload_years, month);
/*     */       } else {
/* 470 */         map.put(upload_years, upload_month);
/*     */       }
/*     */     }
/* 473 */     json = getFolderJSONS(map, imgleibie);
/* 474 */     return json;
/*     */   }
/*     */ 
/*     */   public static String getFolderJSONS(Map<String, String> map, String imgleibie)
/*     */   {
/* 484 */     String json = "";
/* 485 */     for (Entry entry : map.entrySet())
/*     */     {
/* 487 */       json = json + "{";
/* 488 */       String key = ((String)entry.getKey()).toString();
/* 489 */       String value = ((String)entry.getValue()).toString();
/* 490 */       json = json + "\"id\":\"" + imgleibie + "\",\"text\":\"" + key + "\",\"attributes\":{\"ctype\":\"y\",\"year\":\"" + key + "\"},\"state\":\"closed\"";
/* 491 */       if (value.contains(","))
/*     */       {
/* 493 */         String[] monthArray = value.split(",");
/* 494 */         Arrays.sort(monthArray);
/* 495 */         json = json + ",\"children\":[";
/* 496 */         for (String monthValue : monthArray)
/*     */         {
/* 498 */           json = json + "{\"id\":\"" + imgleibie + "\",\"text\":\"" + monthValue + "\",\"attributes\":{\"ctype\":\"m\",\"year\":\"" + key + "\"}},";
/*     */         }
/* 500 */         json = json.substring(0, json.length() - 1);
/* 501 */         json = json + "]},";
/*     */       } else {
/* 503 */         json = json + ",\"children\":[{\"id\":\"" + imgleibie + "\",\"text\":\"" + value + "\",\"attributes\":{\"ctype\":\"m\",\"year\":\"" + key + "\"}}";
/* 504 */         json = json + "]},";
/*     */       }
/*     */     }
/* 507 */     json = json.substring(0, json.length() - 1);
/* 508 */     return json;
/*     */   }
/*     */ 
/*     */   public static void updateMateFolderTest()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.material.MateFolderManager
 * JD-Core Version:    0.6.2
 */