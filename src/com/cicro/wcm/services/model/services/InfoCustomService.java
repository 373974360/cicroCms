/*     */ package com.cicro.wcm.services.model.services;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*     */ import com.cicro.wcm.bean.cms.info.GKInfoBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseRPC;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtilRPC;
/*     */ import com.cicro.wcm.services.model.Fields;
/*     */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class InfoCustomService
/*     */ {
/*     */   public static boolean addInfoCuston(String model_id, Map map)
/*     */   {
/*     */     try
/*     */     {
/*  37 */       ModelBean modelBean = ModelManager.getModelBean(Integer.valueOf(model_id).intValue());
/*  38 */       String table_name = modelBean.getTable_name();
/*  39 */       insterInfoCustonByMapTableName(model_id, table_name, map);
/*     */ 
/*  41 */       return true;
/*     */     } catch (Exception e) {
/*  43 */       e.printStackTrace();
/*  44 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean insterInfoCustonByMapTableName(String model_id, String tableName, Map map)
/*     */   {
/*     */     try
/*     */     {
/* 105 */       Map mapFileType = new HashMap();
/* 106 */       Map mapModel = new HashMap();
/* 107 */       mapModel.put("model_id", model_id);
/* 108 */       List fieldsList = FormRPC.getFormFieldsListByModelIdN(mapModel);
/* 109 */       for (Fields f : fieldsList) {
/* 110 */         mapFileType.put(f.getField_enname().toLowerCase(), f.getField_type());
/*     */       }
/*     */ 
/* 113 */       StringBuffer sb_key = new StringBuffer("");
/* 114 */       StringBuffer sb_val = new StringBuffer("");
/*     */ 
/* 116 */       StringBuffer sb_val_files = new StringBuffer("");
/* 117 */       Map sqlMap = new HashMap();
/*     */ 
/* 119 */       Map map3 = MapManager.mapKeyValueToLow(map);
/*     */ 
/* 121 */       Iterator it = map3.keySet().iterator();
/* 122 */       while (it.hasNext()) {
/* 123 */         String key = (String)it.next();
/* 124 */         Object valO = map3.get(key);
/* 125 */         if (mapFileType.containsKey(key)) {
/* 126 */           sb_key.append(key + ",");
/* 127 */           sb_val_files.append("#{" + key + "},");
/* 128 */           if (((String)mapFileType.get(key)).equals("4")) {
/* 129 */             sb_val.append((String)valO + ",");
/* 130 */             sqlMap.put(key, (String)valO);
/*     */           } else {
/* 132 */             sb_val.append("'" + (String)valO + "',");
/* 133 */             sqlMap.put(key, (String)valO);
/*     */           }
/*     */         }
/* 136 */         else if (key.equals("id")) {
/* 137 */           sb_key.append(key + ",");
/* 138 */           sb_val_files.append("#{" + key + "},");
/* 139 */           sb_val.append((Integer)valO + ",");
/* 140 */           sqlMap.put(key, (Integer)valO);
/*     */         }
/*     */       }
/*     */ 
/* 144 */       String insertSql = "insert into " + tableName + " (" + sb_key.substring(0, sb_key.length() - 1) + ") values (" + sb_val_files.substring(0, sb_val_files.length() - 1) + ")";
/* 145 */       System.out.println("sql : " + insertSql);
/* 146 */       System.out.println("sqlMap : " + sqlMap);
/*     */ 
/* 148 */       sqlMap.put("sql", insertSql);
/* 149 */       PublicTableDAO.createSql(sqlMap);
/*     */ 
/* 151 */       return true;
/*     */     } catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateInfoCuston(String model_id, Map map)
/*     */   {
/*     */     try
/*     */     {
/* 163 */       ModelBean modelBean = ModelManager.getModelBean(Integer.valueOf(model_id).intValue());
/* 164 */       String table_name = modelBean.getTable_name();
/* 165 */       updateInfoCustonByMapTableName(model_id, table_name, map);
/*     */ 
/* 167 */       return true;
/*     */     } catch (Exception e) {
/* 169 */       e.printStackTrace();
/* 170 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateInfoCustonByMapTableName(String model_id, String tableName, Map map)
/*     */   {
/*     */     try
/*     */     {
/* 230 */       Map sqlMap = new HashMap();
/*     */ 
/* 233 */       Map mapFileType = new HashMap();
/* 234 */       Map mapModel = new HashMap();
/* 235 */       mapModel.put("model_id", model_id);
/* 236 */       List fieldsList = FormRPC.getFormFieldsListByModelIdN(mapModel);
/* 237 */       for (Fields f : fieldsList) {
/* 238 */         mapFileType.put(f.getField_enname().toLowerCase(), f.getField_type());
/*     */       }
/*     */ 
/* 241 */       String info_id = "0";
/* 242 */       StringBuffer sb_update = new StringBuffer("");
/* 243 */       Map map3 = MapManager.mapKeyValueToLow(map);
/*     */ 
/* 245 */       Iterator it = map3.keySet().iterator();
/*     */ 
/* 247 */       while (it.hasNext()) {
/* 248 */         String key = (String)it.next();
/* 249 */         Object valO = map3.get(key);
/* 250 */         if (key.equals("id")) {
/* 251 */           info_id = (Integer)valO;
/*     */         }
/* 253 */         else if (mapFileType.containsKey(key)) {
/* 254 */           if (((String)mapFileType.get(key)).equals("4"))
/*     */           {
/* 256 */             sb_update.append(" " + key + "=#{" + key + "},");
/* 257 */             sqlMap.put(key, (String)valO);
/*     */           }
/*     */           else {
/* 260 */             sb_update.append(" " + key + "=#{" + key + "},");
/* 261 */             sqlMap.put(key, (String)valO);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 267 */       if (!sb_update.toString().trim().equals("")) {
/* 268 */         String sql = "update " + tableName + " set " + sb_update.substring(0, sb_update.length() - 1) + " where id=" + info_id;
/* 269 */         System.out.println("sql info_id : " + info_id);
/*     */ 
/* 271 */         sqlMap.put("sql", sql);
/* 272 */         PublicTableDAO.createSql(sqlMap);
/*     */       }
/* 274 */       return true;
/*     */     } catch (Exception e) {
/* 276 */       e.printStackTrace();
/* 277 */     }return false;
/*     */   }
/*     */ 
/*     */   public static ArticleBean getArticle(String infoId)
/*     */   {
/* 284 */     return InfoCustomDao.getArticle(infoId);
/*     */   }
/*     */ 
/*     */   public static Map getCustomInfoMap(String model_id, String info_id)
/*     */   {
/* 289 */     Map map = new HashMap();
/*     */     try {
/* 291 */       ModelBean modelBean = ModelManager.getModelBean(Integer.valueOf(model_id).intValue());
/* 292 */       String table_name = modelBean.getTable_name();
/* 293 */       String sql = "select * from " + table_name + " where id=" + info_id;
/* 294 */       return InfoCustomDao.getMapBySql(sql);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 298 */       e.printStackTrace();
/* 299 */     }return map;
/*     */   }
/*     */ 
/*     */   public static boolean updateQuoteInfoCustom(ArticleBean articleBean, Map cusBean, String model_ename, HttpServletRequest request)
/*     */   {
/*     */     try
/*     */     {
/* 308 */       String info_id = articleBean.getInfo_id();
/*     */ 
/* 310 */       List quote_list = InfoBaseRPC.getQuoteInfoList(info_id);
/* 311 */       if ((quote_list != null) && (quote_list.size() > 0))
/*     */       {
/* 313 */         for (InfoBean infoBean : quote_list) {
/* 314 */           System.out.println("infoBean.getInfo_id() = " + infoBean.getInfo_id());
/* 315 */           Object o = ModelUtilRPC.select(String.valueOf(infoBean.getInfo_id()), infoBean.getSite_id(), model_ename);
/* 316 */           if (o != null) {
/* 317 */             Map mapO = (Map)o;
/*     */ 
/* 319 */             int id = infoBean.getInfo_id();
/* 320 */             articleBean.setId(infoBean.getInfo_id());
/* 321 */             articleBean.setInfo_id(Integer.valueOf(String.valueOf(mapO.get("info_id"))).intValue());
/* 322 */             articleBean.setCat_id(Integer.valueOf(String.valueOf(mapO.get("cat_id"))).intValue());
/* 323 */             articleBean.setFrom_id(Integer.valueOf(String.valueOf(mapO.get("from_id"))).intValue());
/* 324 */             articleBean.setContent_url((String)mapO.get("content_url"));
/* 325 */             articleBean.setWf_id(Integer.valueOf(String.valueOf(mapO.get("wf_id"))).intValue());
/* 326 */             articleBean.setStep_id(Integer.valueOf(String.valueOf(mapO.get("step_id"))).intValue());
/* 327 */             articleBean.setInfo_status(Integer.valueOf(String.valueOf(mapO.get("info_status"))).intValue());
/* 328 */             articleBean.setFinal_status(Integer.valueOf(String.valueOf(mapO.get("final_status"))).intValue());
/* 329 */             articleBean.setReleased_dtime((String)mapO.get("released_dtime"));
/* 330 */             articleBean.setIs_auto_up(Integer.valueOf(String.valueOf(mapO.get("is_auto_up"))).intValue());
/* 331 */             articleBean.setUp_dtime((String)mapO.get("up_dtime"));
/* 332 */             articleBean.setIs_auto_down(Integer.valueOf(String.valueOf(mapO.get("is_auto_down"))).intValue());
/* 333 */             articleBean.setDown_dtime((String)mapO.get("down_dtime"));
/* 334 */             articleBean.setSite_id((String)mapO.get("site_id"));
/*     */ 
/* 337 */             ModelUtilRPC.update(articleBean, model_ename, request);
/*     */ 
/* 340 */             cusBean.put("id", Integer.valueOf(id));
/* 341 */             InfoCustomRPC.updateInfoCuston(String.valueOf(mapO.get("model_id")), cusBean);
/*     */           }
/*     */         }
/*     */       }
/* 345 */       return true;
/*     */     } catch (Exception e) {
/* 347 */       e.printStackTrace();
/* 348 */     }return false;
/*     */   }
/*     */ 
/*     */   public static Map getGKInfo(String infoId)
/*     */   {
/* 354 */     String sql = "select * from cs_gk_info where info_id=" + infoId;
/* 355 */     return InfoCustomDao.getMapBySql(sql);
/*     */   }
/*     */ 
/*     */   public static boolean updateQuoteInfoCustomGk(GKInfoBean gkInfoBean, Map cusBean, String model_ename, HttpServletRequest request)
/*     */   {
/*     */     try
/*     */     {
/* 363 */       String info_id = gkInfoBean.getInfo_id();
/*     */ 
/* 365 */       List quote_list = InfoBaseRPC.getQuoteInfoList(info_id);
/* 366 */       if ((quote_list != null) && (quote_list.size() > 0))
/*     */       {
/* 368 */         for (InfoBean infoBean : quote_list) {
/* 369 */           System.out.println("infoBean.getInfo_id() = " + infoBean.getInfo_id());
/* 370 */           Object o = ModelUtilRPC.select(String.valueOf(infoBean.getInfo_id()), infoBean.getSite_id(), model_ename);
/* 371 */           if (o != null) {
/* 372 */             Map mapO = (Map)o;
/*     */ 
/* 374 */             int id = infoBean.getInfo_id();
/* 375 */             gkInfoBean.setId(infoBean.getInfo_id());
/* 376 */             gkInfoBean.setInfo_id(Integer.valueOf(String.valueOf(mapO.get("info_id"))).intValue());
/* 377 */             gkInfoBean.setCat_id(Integer.valueOf(String.valueOf(mapO.get("cat_id"))).intValue());
/* 378 */             gkInfoBean.setFrom_id(Integer.valueOf(String.valueOf(mapO.get("from_id"))).intValue());
/* 379 */             gkInfoBean.setContent_url((String)mapO.get("content_url"));
/* 380 */             gkInfoBean.setWf_id(Integer.valueOf(String.valueOf(mapO.get("wf_id"))).intValue());
/* 381 */             gkInfoBean.setStep_id(Integer.valueOf(String.valueOf(mapO.get("step_id"))).intValue());
/* 382 */             gkInfoBean.setInfo_status(Integer.valueOf(String.valueOf(mapO.get("info_status"))).intValue());
/* 383 */             gkInfoBean.setFinal_status(Integer.valueOf(String.valueOf(mapO.get("final_status"))).intValue());
/* 384 */             gkInfoBean.setReleased_dtime((String)mapO.get("released_dtime"));
/* 385 */             gkInfoBean.setIs_auto_up(Integer.valueOf(String.valueOf(mapO.get("is_auto_up"))).intValue());
/* 386 */             gkInfoBean.setUp_dtime((String)mapO.get("up_dtime"));
/* 387 */             gkInfoBean.setIs_auto_down(Integer.valueOf(String.valueOf(mapO.get("is_auto_down"))).intValue());
/* 388 */             gkInfoBean.setDown_dtime((String)mapO.get("down_dtime"));
/* 389 */             gkInfoBean.setSite_id((String)mapO.get("site_id"));
/*     */ 
/* 392 */             ModelUtilRPC.update(gkInfoBean, model_ename, request);
/*     */ 
/* 395 */             cusBean.put("id", Integer.valueOf(id));
/* 396 */             InfoCustomRPC.updateInfoCuston(String.valueOf(mapO.get("model_id")), cusBean);
/*     */           }
/*     */         }
/*     */       }
/* 400 */       return true;
/*     */     } catch (Exception e) {
/* 402 */       e.printStackTrace();
/* 403 */     }return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.InfoCustomService
 * JD-Core Version:    0.6.2
 */