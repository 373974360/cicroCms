/*      */ package com.cicro.wcm.services.model.services;
/*      */ 
/*      */ import com.cicro.iresource.service.resourceService.dto.DataElementCollectionDTO;
/*      */ import com.cicro.iresource.service.resourceService.dto.DataElementCollectionDataElementDTO;
/*      */ import com.cicro.iresource.service.resourceService.dto.DataElementDTO;
/*      */ import com.cicro.util.DateUtil;
/*      */ import com.cicro.util.jconfig.JconfigUtil;
/*      */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*      */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*      */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*      */ import com.cicro.wcm.bean.cms.info.GKFbsznBean;
/*      */ import com.cicro.wcm.bean.cms.info.GKFldcyBean;
/*      */ import com.cicro.wcm.bean.cms.info.GKFtygsBean;
/*      */ import com.cicro.wcm.bean.cms.info.GKResFileBean;
/*      */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*      */ import com.cicro.wcm.bean.cms.info.PicBean;
/*      */ import com.cicro.wcm.bean.cms.info.PicItemBean;
/*      */ import com.cicro.wcm.bean.cms.info.VideoBean;
/*      */ import com.cicro.wcm.bean.control.SiteBean;
/*      */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*      */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*      */ import com.cicro.wcm.dao.PublicTableDAO;
/*      */ import com.cicro.wcm.dao.cms.info.InfoDAO;
/*      */ import com.cicro.wcm.dao.cms.info.ModelUtilDAO;
/*      */ import com.cicro.wcm.dao.zwgk.info.GKInfoDAO;
/*      */ import com.cicro.wcm.db.DBManager;
/*      */ import com.cicro.wcm.rmi.ICustomFormRMI;
/*      */ import com.cicro.wcm.rmi.file.FileRmiFactory;
/*      */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*      */ import com.cicro.wcm.services.cms.category.CategoryUtil;
/*      */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*      */ import com.cicro.wcm.services.cms.info.InfoBaseRPC;
/*      */ import com.cicro.wcm.services.cms.info.ModelConfig;
/*      */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*      */ import com.cicro.wcm.services.control.site.SiteManager;
/*      */ import com.cicro.wcm.services.model.Fields;
/*      */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*      */ import com.cicro.wcm.services.system.formodel.ModelRPC;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.math.BigDecimal;
/*      */ import java.rmi.RemoteException;
/*      */ import java.rmi.server.UnicastRemoteObject;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import net.sf.json.JSONArray;
/*      */ import net.sf.json.JSONObject;
/*      */ import org.apache.commons.beanutils.BeanUtils;
/*      */ import org.codehaus.jackson.map.ObjectMapper;
/*      */ 
/*      */ public class CustomFormRMIImpl extends UnicastRemoteObject
/*      */   implements ICustomFormRMI
/*      */ {
/*   57 */   static String info_state = JconfigUtilContainer.bashConfig().getProperty("info_state", "8", "wcm_zyk");
/*      */ 
/*      */   public CustomFormRMIImpl()
/*      */     throws RemoteException
/*      */   {
/*      */   }
/*      */ 
/*      */   public boolean isState()
/*      */   {
/*   66 */     String str = JconfigUtilContainer.bashConfig().getProperty("state", "off", "wcm_zyk");
/*   67 */     System.out.println("wcm_zyk--state--" + str);
/*   68 */     if (str.equals("off")) {
/*   69 */       return false;
/*      */     }
/*   71 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean addCustomForm(DataElementCollectionDTO dataElementCollection)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/*   80 */       if (!isState()) {
/*   81 */         return true;
/*      */       }
/*      */ 
/*   85 */       ModelBean mb = getModelBeanByDataElementCollection(dataElementCollection);
/*      */ 
/*   88 */       List listFields = dataElementCollection.getDataElements();
/*   89 */       for (DataElementCollectionDataElementDTO dataElementCollectionDataElement : listFields) {
/*   90 */         addCustomForm(dataElementCollectionDataElement, mb);
/*      */       }
/*      */ 
/*   95 */       return true;
/*      */     } catch (Exception e) {
/*   97 */       e.printStackTrace();
/*   98 */     }return false;
/*      */   }
/*      */ 
/*      */   public static ModelBean getModelBeanByDataElementCollection(DataElementCollectionDTO dataElementCollection)
/*      */   {
/*      */     try
/*      */     {
/*  107 */       String model_ename = dataElementCollection.getDecEnName().toLowerCase();
/*  108 */       System.out.println(" getModelBeanByDataElementCollection model_ename : " + model_ename);
/*  109 */       String model_name = dataElementCollection.getDecName();
/*  110 */       System.out.println(" getModelBeanByDataElementCollection model_name : " + model_name);
/*  111 */       String table_name = dataElementCollection.getDecEnName().toLowerCase();
/*  112 */       System.out.println(" getModelBeanByDataElementCollection table_name : " + table_name);
/*  113 */       String template_list = "";
/*  114 */       String template_show = "";
/*  115 */       String app_id = "cms";
/*  116 */       String model_memo = dataElementCollection.getNote();
/*  117 */       if (model_memo == null) {
/*  118 */         model_memo = "";
/*      */       }
/*  120 */       System.out.println(" getModelBeanByDataElementCollection model_memo : " + model_memo);
/*  121 */       int disabled = 0;
/*  122 */       String model_icon = "ico_custom";
/*  123 */       String add_page = "m_article_custom.jsp";
/*  124 */       String view_page = "";
/*  125 */       String model_type = "2";
/*  126 */       ModelBean mb = new ModelBean();
/*  127 */       mb.setModel_ename(model_ename);
/*  128 */       mb.setModel_name(model_name);
/*  129 */       mb.setTable_name(table_name);
/*  130 */       mb.setTemplate_list(template_list);
/*  131 */       mb.setTemplate_show(template_show);
/*  132 */       mb.setApp_id(app_id);
/*  133 */       mb.setModel_memo(model_memo);
/*  134 */       mb.setDisabled(disabled);
/*  135 */       mb.setModel_icon(model_icon);
/*  136 */       mb.setAdd_page(add_page);
/*  137 */       mb.setView_page(view_page);
/*  138 */       mb.setModel_type(model_type);
/*  139 */       ModelManager.insertModel(mb, null);
/*      */ 
/*  141 */       return ModelManager.getModelBeanByEName(model_ename);
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  145 */       e.printStackTrace();
/*  146 */     }return new ModelBean();
/*      */   }
/*      */ 
/*      */   public static boolean addCustomForm(DataElementCollectionDataElementDTO dataElementCollectionDataElement, ModelBean mb)
/*      */   {
/*      */     try
/*      */     {
/*  155 */       DataElementDTO dataElement = dataElementCollectionDataElement.getDataElement();
/*  156 */       String field_cnname = dataElement.getDeName();
/*  157 */       String field_enname = dataElement.getDeShortName().toLowerCase();
/*  158 */       String field_type = dataElementCollectionDataElement.getDeDataType();
/*  159 */       System.out.println(field_cnname + " -- " + field_enname + "--- field_type --- " + field_type);
/*  160 */       String select_view = dataElementCollectionDataElement.getDeShow();
/*  161 */       if (field_type.equals("string")) {
/*  162 */         field_type = "0";
/*  163 */         if (select_view.equals("selectElement")) {
/*  164 */           select_view = "0";
/*  165 */           field_type = "3";
/*  166 */         } else if (select_view.equals("radioElement")) {
/*  167 */           select_view = "2";
/*  168 */           field_type = "3";
/*      */         }
/*  170 */       } else if (field_type.equals("text")) {
/*  171 */         field_type = "2";
/*  172 */       } else if ((field_type.equals("int")) || (field_type.equals("float"))) {
/*  173 */         field_type = "4";
/*  174 */       } else if (field_type.equals("date")) {
/*  175 */         field_type = "5";
/*  176 */       } else if (field_type.equals("file")) {
/*  177 */         field_type = "6";
/*      */       }
/*  179 */       String is_sys = "1";
/*  180 */       String is_null = "0";
/*  181 */       String is_display = "1";
/*  182 */       String field_text = "";
/*  183 */       String field_info = "";
/*  184 */       if (field_type.equals("0"))
/*  185 */         field_info = "<fieldInfo><field_maxnum><![CDATA[]]></field_maxnum><field_maxlength><![CDATA[300]]></field_maxlength><validator><![CDATA[0]]></validator><default_value><![CDATA[]]></default_value></fieldInfo>";
/*  186 */       else if (field_type.equals("2"))
/*  187 */         field_info = "<fieldInfo><width><![CDATA[620]]></width><height><![CDATA[300]]></height><field_maxnum><![CDATA[]]></field_maxnum><default_value><![CDATA[]]></default_value></fieldInfo>";
/*  188 */       else if (field_type.equals("3"))
/*  189 */         field_info = "<fieldInfo><data_type><![CDATA[]]></data_type><data_type_id><![CDATA[]]></data_type_id><select_item><![CDATA[]]></select_item><select_view><![CDATA[" + select_view + "]]></select_view><default_value><![CDATA[]]></default_value></fieldInfo>";
/*  190 */       else if (field_type.equals("4"))
/*  191 */         field_info = "<fieldInfo><min_num><![CDATA[]]></min_num><max_num><![CDATA[]]></max_num><default_value><![CDATA[]]></default_value></fieldInfo>";
/*  192 */       else if (field_type.equals("5"))
/*  193 */         field_info = "<fieldInfo><default_value><![CDATA[]]></default_value></fieldInfo>";
/*  194 */       else if (field_type.equals("6")) {
/*  195 */         field_info = "<fieldInfo><default_value><![CDATA[]]></default_value></fieldInfo>";
/*      */       }
/*      */ 
/*  199 */       Fields fields = new Fields();
/*  200 */       fields.setFrom_id(0);
/*  201 */       fields.setModel_id(mb.getModel_id());
/*  202 */       fields.setField_sort(9999);
/*  203 */       fields.setAdd_time(DateUtil.getCurrentDateTime());
/*  204 */       fields.setField_cnname(field_cnname);
/*  205 */       fields.setField_enname(field_enname);
/*  206 */       fields.setField_type(field_type);
/*  207 */       fields.setIs_sys(is_sys);
/*  208 */       fields.setIs_null(is_null);
/*  209 */       fields.setIs_display(is_display);
/*  210 */       fields.setField_text(field_text);
/*  211 */       fields.setField_info(field_info);
/*  212 */       FormDao.addFields(fields);
/*      */ 
/*  216 */       Map hmParam = new HashMap();
/*  217 */       hmParam.put("tableName", mb.getTable_name());
/*  218 */       hmParam.put("fieldName", fields.getField_enname().toLowerCase());
/*  219 */       if (fields.getField_type().equals("0")) {
/*  220 */         hmParam.put("fieldSize", "4000");
/*  221 */         FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/*  222 */       } else if ((fields.getField_type().equals("1")) || (fields.getField_type().equals("2"))) {
/*  223 */         FormUtil.updateFieldDesc("addFieldLongtext", hmParam);
/*  224 */       } else if (fields.getField_type().equals("3")) {
/*  225 */         hmParam.put("fieldSize", "3000");
/*  226 */         FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/*  227 */       } else if (fields.getField_type().equals("4")) {
/*  228 */         hmParam.put("fieldSize", "20");
/*  229 */         FormUtil.updateFieldDesc("addFieldBigint", hmParam);
/*  230 */       } else if (fields.getField_type().equals("5")) {
/*  231 */         hmParam.put("fieldSize", "255");
/*  232 */         FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/*  233 */       } else if (fields.getField_type().equals("6")) {
/*  234 */         hmParam.put("fieldSize", "3000");
/*  235 */         FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/*      */       }
/*      */ 
/*  238 */       FormService.clearMapLsit();
/*      */ 
/*  240 */       return true;
/*      */     } catch (Exception e) {
/*  242 */       e.printStackTrace();
/*  243 */       FormService.clearMapLsit();
/*  244 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean updateCustomForm(DataElementCollectionDTO dataElementCollection)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/*  252 */       if (!isState()) {
/*  253 */         return true;
/*      */       }
/*      */ 
/*  256 */       String model_ename = dataElementCollection.getDecEnName().toLowerCase();
/*  257 */       String model_name = dataElementCollection.getDecName();
/*  258 */       String table_name = dataElementCollection.getDecEnName().toLowerCase();
/*      */ 
/*  261 */       List listFieldsOld = new ArrayList();
/*  262 */       Map mapFieldsOld = new HashMap();
/*  263 */       ModelBean mb = ModelManager.getModelBeanByEName(model_ename);
/*      */ 
/*  266 */       if (mb == null) {
/*  267 */         return addCustomForm(dataElementCollection);
/*      */       }
/*      */ 
/*  270 */       Map map = new HashMap();
/*  271 */       map.put("model_id", Integer.valueOf(mb.getModel_id()));
/*  272 */       List fieldsList = FormRPC.getFormFieldsListByModelIdN(map);
/*  273 */       for (Fields fields : fieldsList) {
/*  274 */         listFieldsOld.add(fields.getField_enname());
/*  275 */         mapFieldsOld.put(fields.getField_enname(), String.valueOf(fields.getId()));
/*      */       }
/*      */ 
/*  279 */       List listFieldsNew = new ArrayList();
/*  280 */       List listFields = dataElementCollection.getDataElements();
/*  281 */       for (DataElementCollectionDataElementDTO dataElementCollectionDataElement : listFields) {
/*  282 */         DataElementDTO dataElement = dataElementCollectionDataElement.getDataElement();
/*  283 */         String field_enname = dataElement.getDeShortName();
/*  284 */         listFieldsNew.add(field_enname);
/*      */       }
/*      */ 
/*  288 */       List addList = new ArrayList(listFieldsNew);
/*  289 */       Object deleteListTemp = new ArrayList();
/*  290 */       List deleteList = new ArrayList((Collection)deleteListTemp);
/*      */ 
/*  292 */       List idsList = new ArrayList(listFieldsOld);
/*  293 */       List cidsList = new ArrayList(listFieldsNew);
/*  294 */       for (String idsStr : idsList) {
/*  295 */         if ((idsStr != null) && (!"".equals(idsStr)))
/*      */         {
/*  298 */           if (!cidsList.contains(idsStr))
/*  299 */             deleteList.add(idsStr);
/*      */         }
/*      */       }
/*  302 */       addList.removeAll(idsList);
/*      */ 
/*  304 */       System.out.println("CustomFormRMIImpl.java updateCustomForm addList :: " + addList);
/*  305 */       System.out.println("CustomFormRMIImpl.java updateCustomForm deleteList :: " + deleteList);
/*      */ 
/*  309 */       for (DataElementCollectionDataElementDTO dataElementCollectionDataElement : listFields) {
/*  310 */         DataElementDTO dataElement = dataElementCollectionDataElement.getDataElement();
/*  311 */         String field_enname = dataElement.getDeShortName();
/*  312 */         if (addList.contains(field_enname)) {
/*  313 */           addCustomForm(dataElementCollectionDataElement, mb);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  318 */       for (String del_name : deleteList) {
/*  319 */         String d_id = (String)mapFieldsOld.get(del_name);
/*      */ 
/*  322 */         FormService.deleteFormFields(d_id, mb.getModel_id());
/*      */       }
/*      */ 
/*  325 */       FormService.clearMapLsit();
/*      */ 
/*  327 */       return true;
/*      */     } catch (Exception e) {
/*  329 */       e.printStackTrace();
/*  330 */       FormService.clearMapLsit();
/*  331 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean deleteCustomForm(String ids, String enames)
/*      */   {
/*      */     try
/*      */     {
/*  341 */       if (!isState()) {
/*  342 */         return true;
/*      */       }
/*      */ 
/*  346 */       List listEnames = Arrays.asList(enames.split(","));
/*  347 */       for (String ename : listEnames) {
/*  348 */         ename = ename.toLowerCase();
/*  349 */         if ((ename != null) && (!ename.equals(""))) {
/*  350 */           ModelBean mb = ModelManager.getModelBeanByEName(ename);
/*  351 */           ModelManager.deleteModel(mb.getModel_id(), null);
/*      */         }
/*      */       }
/*      */ 
/*  355 */       return true;
/*      */     } catch (Exception e) {
/*  357 */       e.printStackTrace();
/*  358 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean addCustomInfo(String info)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/*  367 */       if (!isState()) {
/*  368 */         return true;
/*      */       }
/*      */ 
/*  371 */       Map mapResult = getInfoBeanByJson(info);
/*      */ 
/*  373 */       String dept_position = (String)mapResult.get("deptTreePath");
/*  374 */       System.out.println("deptTreePath === " + dept_position);
/*      */ 
/*  377 */       List list = new ArrayList();
/*  378 */       System.out.println("list.size() ---- " + list.size());
/*  379 */       if (list.size() > 0) {
/*  380 */         for (String site_id : list)
/*  381 */           addPublicInfo(mapResult, site_id);
/*      */       }
/*      */       else {
/*  384 */         List listSite = SiteManager.getSiteList();
/*  385 */         System.out.println("listSite.size() ---- " + listSite.size());
/*  386 */         for (SiteBean site : listSite) {
/*  387 */           String parent_id = site.getParent_id();
/*  388 */           if (!parent_id.equals("0")) {
/*  389 */             String category_id = (String)mapResult.get("category_id");
/*  390 */             System.out.println(site.getSite_id() + " ---- " + category_id);
/*  391 */             CategoryBean cb = CategoryManager.getCategoryBean(Integer.valueOf(category_id).intValue());
/*  392 */             if (cb.getSite_id().equals(site.getSite_id())) {
/*  393 */               addPublicInfo(mapResult, site.getSite_id());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  398 */       return true;
/*      */     } catch (Exception e) {
/*  400 */       e.printStackTrace();
/*  401 */     }return false;
/*      */   }
/*      */ 
/*      */   public static Map mapKeyValueDeleDesc(Map m)
/*      */   {
/*  413 */     if ((m != null) && (m.size() > 0))
/*      */     {
/*  415 */       Map new_m = new HashMap();
/*  416 */       Iterator iter = m.entrySet().iterator();
/*  417 */       while (iter.hasNext()) {
/*  418 */         Entry entry = (Entry)iter.next();
/*  419 */         String key = (String)entry.getKey();
/*  420 */         Object val = entry.getValue();
/*  421 */         if (key.startsWith("dec")) {
/*  422 */           key = key.substring(3, key.length());
/*      */         }
/*  424 */         new_m.put(key, val);
/*      */       }
/*  426 */       return new_m;
/*      */     }
/*      */ 
/*  429 */     return m;
/*      */   }
/*      */ 
/*      */   public static boolean addPublicInfo(Map mapResult, String site_id)
/*      */   {
/*      */     try {
/*  435 */       System.out.println("----addPublicInfo---- == " + mapResult);
/*  436 */       String category_id = (String)mapResult.get("category_id");
/*  437 */       String table_name = (String)mapResult.get("table_name");
/*  438 */       String from_id = (String)mapResult.get("from_id");
/*  439 */       List listData = (List)mapResult.get("mapResult");
/*      */ 
/*  441 */       Map listDataMap = (Map)mapResult.get("listDataMap");
/*      */ 
/*  444 */       listDataMap = mapKeyValueDeleDesc(listDataMap);
/*      */ 
/*  447 */       List listFiles = (List)mapResult.get("listFiles");
/*      */ 
/*  450 */       ModelBean modelBean = ModelRPC.getModelBeanByEName(table_name);
/*      */ 
/*  452 */       Map map = new HashMap();
/*  453 */       map.put("model_id", Integer.valueOf(modelBean.getModel_id()));
/*  454 */       List fieldsList = FormRPC.getFormFieldsListByModelIdN(map);
/*  455 */       String title_str = "";
/*  456 */       String time_str = "";
/*  457 */       for (Fields fields : fieldsList) {
/*  458 */         if ("title".equals(fields.getField_flag())) {
/*  459 */           title_str = fields.getField_enname();
/*      */         }
/*  461 */         if ("publish_time".equals(fields.getField_flag2())) {
/*  462 */           time_str = fields.getField_enname();
/*      */         }
/*      */       }
/*  465 */       if ((title_str.equals("")) || (time_str.equals(""))) {
/*  466 */         System.out.println(" Not set 'title' or 'publish_time' hearder , please set it!");
/*  467 */         return true;
/*      */       }
/*      */ 
/*  470 */       if (modelBean == null) {
/*  471 */         return true;
/*      */       }
/*      */ 
/*  474 */       System.out.println("modelBean.getModel_id()========" + modelBean.getModel_id());
/*      */ 
/*  478 */       ArticleBean articleBean = new ArticleBean();
/*  479 */       int id = InfoBaseRPC.getInfoId();
/*  480 */       articleBean.setId(id);
/*  481 */       articleBean.setInfo_id(id);
/*  482 */       articleBean.setCat_id(Integer.valueOf(category_id).intValue());
/*  483 */       articleBean.setModel_id(modelBean.getModel_id());
/*      */ 
/*  485 */       articleBean.setTop_title("");
/*  486 */       articleBean.setPre_title("");
/*  487 */       articleBean.setTitle((String)listDataMap.get(title_str.toString()));
/*  488 */       articleBean.setSubtitle("");
/*  489 */       articleBean.setTitle_color("");
/*  490 */       articleBean.setThumb_url("");
/*  491 */       articleBean.setDescription("");
/*  492 */       articleBean.setAuthor("");
/*  493 */       articleBean.setEditor("");
/*  494 */       articleBean.setSource("");
/*  495 */       articleBean.setInfo_keywords("");
/*  496 */       articleBean.setInfo_description("");
/*  497 */       articleBean.setTags("");
/*  498 */       articleBean.setContent_url("");
/*  499 */       articleBean.setWf_id(0);
/*  500 */       articleBean.setStep_id(100);
/*  501 */       articleBean.setInfo_status(Integer.valueOf(info_state).intValue());
/*  502 */       articleBean.setFinal_status(0);
/*  503 */       articleBean.setWeight(60);
/*  504 */       articleBean.setHits(0);
/*  505 */       articleBean.setDay_hits(0);
/*  506 */       articleBean.setWeek_hits(0);
/*  507 */       articleBean.setMonth_hits(0);
/*  508 */       articleBean.setLasthit_dtime("");
/*  509 */       articleBean.setIs_allow_comment(0);
/*  510 */       articleBean.setComment_num(0);
/*  511 */       String time_str2 = (String)listDataMap.get(time_str.toString()) + " 00:00:00";
/*  512 */       articleBean.setReleased_dtime(time_str2);
/*  513 */       articleBean.setInput_user(0);
/*  514 */       articleBean.setInput_dtime(articleBean.getReleased_dtime());
/*  515 */       articleBean.setModify_user(0);
/*  516 */       articleBean.setModify_dtime("");
/*  517 */       articleBean.setOpt_dtime(articleBean.getReleased_dtime());
/*  518 */       articleBean.setUp_dtime("");
/*  519 */       articleBean.setIs_auto_down(0);
/*  520 */       articleBean.setDown_dtime("");
/*  521 */       articleBean.setIs_host(0);
/*  522 */       articleBean.setTitle_hashkey(0);
/*  523 */       articleBean.setApp_id("cms");
/*      */ 
/*  526 */       articleBean.setSite_id(site_id);
/*  527 */       articleBean.setPage_count(0);
/*  528 */       articleBean.setI_ver(0);
/*  529 */       articleBean.setIs_pic(0);
/*  530 */       articleBean.setIs_am_tupage(0);
/*  531 */       articleBean.setTupage_num(1000);
/*      */ 
/*  535 */       listDataMap.put("id", Integer.valueOf(articleBean.getInfo_id()));
/*  536 */       InfoCustomRPC.addInfoCuston(modelBean.getModel_id(), listDataMap);
/*      */ 
/*  539 */       WcmZykInfoDao.deleteWcminfo_zykinfoById(from_id, articleBean.getSite_id());
/*      */ 
/*  541 */       WcmZykInfoDao.addWcminfo_zykinfo(from_id, articleBean.getInfo_id(), articleBean.getSite_id());
/*      */ 
/*  545 */       WcmZykInfoDao.deleteZykinfoFileById(articleBean.getInfo_id());
/*      */ 
/*  548 */       for (Map mapF : listFiles) {
/*  549 */         mapF.put("info_id", Integer.valueOf(articleBean.getInfo_id()));
/*  550 */         mapF.put("file_id", String.valueOf(mapF.get("id")));
/*  551 */         WcmZykInfoDao.addZykinfoFile(mapF);
/*      */       }
/*      */ 
/*  555 */       insert(articleBean, "article_custom");
/*      */ 
/*  557 */       return true;
/*      */     } catch (Exception e) {
/*  559 */       e.printStackTrace();
/*  560 */     }return false;
/*      */   }
/*      */ 
/*      */   public static boolean updatePublicInfo(Map mapResult, String site_id)
/*      */   {
/*      */     try
/*      */     {
/*  568 */       String category_id = (String)mapResult.get("category_id");
/*  569 */       String table_name = (String)mapResult.get("table_name");
/*  570 */       String from_id = (String)mapResult.get("from_id");
/*  571 */       List listData = (List)mapResult.get("mapResult");
/*      */ 
/*  573 */       Map listDataMap = (Map)mapResult.get("listDataMap");
/*      */ 
/*  576 */       listDataMap = mapKeyValueDeleDesc(listDataMap);
/*      */ 
/*  579 */       List listFiles = (List)mapResult.get("listFiles");
/*      */ 
/*  583 */       ModelBean modelBean = ModelRPC.getModelBeanByEName(table_name);
/*      */ 
/*  585 */       Map map = new HashMap();
/*  586 */       map.put("model_id", Integer.valueOf(modelBean.getModel_id()));
/*  587 */       List fieldsList = FormRPC.getFormFieldsListByModelIdN(map);
/*  588 */       String title_str = "";
/*  589 */       String time_str = "";
/*  590 */       for (Fields fields : fieldsList) {
/*  591 */         if ("title".equals(fields.getField_flag())) {
/*  592 */           title_str = fields.getField_enname();
/*      */         }
/*  594 */         if ("publish_time".equals(fields.getField_flag2())) {
/*  595 */           time_str = fields.getField_enname();
/*      */         }
/*      */       }
/*  598 */       if ((title_str.equals("")) || (time_str.equals(""))) {
/*  599 */         System.out.println(" Not set 'title' or 'publish_time' hearder , please set it!");
/*  600 */         return true;
/*      */       }
/*      */ 
/*  603 */       if (modelBean == null) {
/*  604 */         return true;
/*      */       }
/*      */ 
/*  608 */       String info_id = WcmZykInfoService.getWcminfo_zykinfoById(from_id, site_id);
/*  609 */       if ((info_id == null) || ("".equals(info_id))) {
/*  610 */         return addPublicInfo(mapResult, site_id);
/*      */       }
/*      */ 
/*  613 */       System.out.println("---updatePublicInfo----info_id::" + info_id);
/*  614 */       InfoBean infoBean = InfoBaseManager.getInfoById(info_id);
/*  615 */       if ((infoBean == null) || ("".equals(Integer.valueOf(infoBean.getInfo_id())))) {
/*  616 */         return addPublicInfo(mapResult, site_id);
/*      */       }
/*      */ 
/*  621 */       ArticleBean articleBean = new ArticleBean();
/*  622 */       int id = Integer.valueOf(info_id).intValue();
/*  623 */       articleBean.setId(id);
/*  624 */       articleBean.setInfo_id(id);
/*  625 */       articleBean.setCat_id(Integer.valueOf(category_id).intValue());
/*  626 */       articleBean.setModel_id(modelBean.getModel_id());
/*  627 */       articleBean.setTop_title("");
/*  628 */       articleBean.setPre_title("");
/*  629 */       articleBean.setTitle((String)listDataMap.get(title_str.toString()));
/*  630 */       articleBean.setSubtitle("");
/*  631 */       articleBean.setTitle_color("");
/*  632 */       articleBean.setThumb_url("");
/*  633 */       articleBean.setDescription("");
/*  634 */       articleBean.setAuthor("");
/*  635 */       articleBean.setEditor("");
/*  636 */       articleBean.setSource("");
/*  637 */       articleBean.setInfo_keywords("");
/*  638 */       articleBean.setInfo_description("");
/*  639 */       articleBean.setTags("");
/*  640 */       articleBean.setContent_url("");
/*  641 */       articleBean.setWf_id(0);
/*  642 */       articleBean.setStep_id(100);
/*  643 */       articleBean.setInfo_status(Integer.valueOf(info_state).intValue());
/*  644 */       articleBean.setFinal_status(0);
/*  645 */       articleBean.setWeight(60);
/*  646 */       articleBean.setHits(0);
/*  647 */       articleBean.setDay_hits(0);
/*  648 */       articleBean.setWeek_hits(0);
/*  649 */       articleBean.setMonth_hits(0);
/*  650 */       articleBean.setLasthit_dtime("");
/*  651 */       articleBean.setIs_allow_comment(0);
/*  652 */       articleBean.setComment_num(0);
/*  653 */       String time_str2 = (String)listDataMap.get(time_str.toString()) + " 00:00:00";
/*  654 */       articleBean.setReleased_dtime(time_str2);
/*  655 */       articleBean.setInput_user(0);
/*  656 */       articleBean.setInput_dtime(articleBean.getReleased_dtime());
/*  657 */       articleBean.setModify_user(0);
/*  658 */       articleBean.setModify_dtime("");
/*  659 */       articleBean.setOpt_dtime(articleBean.getReleased_dtime());
/*  660 */       articleBean.setUp_dtime("");
/*  661 */       articleBean.setIs_auto_down(0);
/*  662 */       articleBean.setDown_dtime("");
/*  663 */       articleBean.setIs_host(0);
/*  664 */       articleBean.setTitle_hashkey(0);
/*  665 */       articleBean.setApp_id("cms");
/*      */ 
/*  668 */       articleBean.setSite_id(site_id);
/*  669 */       articleBean.setPage_count(0);
/*  670 */       articleBean.setI_ver(0);
/*  671 */       articleBean.setIs_pic(0);
/*  672 */       articleBean.setIs_am_tupage(0);
/*  673 */       articleBean.setTupage_num(1000);
/*      */ 
/*  676 */       listDataMap.put("id", Integer.valueOf(articleBean.getInfo_id()));
/*  677 */       Map mapTemp = InfoCustomRPC.getCustomInfoMap(modelBean.getModel_id(), articleBean.getInfo_id());
/*  678 */       if (mapTemp == null) {
/*  679 */         System.out.println("mapTemp-----add---");
/*  680 */         InfoCustomRPC.addInfoCuston(modelBean.getModel_id(), listDataMap);
/*      */       }
/*  682 */       else if (mapTemp.keySet().size() <= 0) {
/*  683 */         System.out.println("mapTemp-----add-2--");
/*  684 */         InfoCustomRPC.addInfoCuston(modelBean.getModel_id(), listDataMap);
/*      */       }
/*      */       else {
/*  687 */         System.out.println("mapTemp-----update-2--");
/*      */ 
/*  689 */         InfoCustomRPC.updateInfoCuston(modelBean.getModel_id(), listDataMap);
/*      */       }
/*      */ 
/*  697 */       WcmZykInfoDao.deleteZykinfoFileById(articleBean.getInfo_id());
/*      */ 
/*  699 */       for (Map mapF : listFiles) {
/*  700 */         mapF.put("info_id", Integer.valueOf(articleBean.getInfo_id()));
/*  701 */         mapF.put("file_id", (String)mapF.get("id"));
/*  702 */         WcmZykInfoDao.addZykinfoFile(mapF);
/*      */       }
/*      */ 
/*  707 */       update(articleBean, "article_custom");
/*      */ 
/*  709 */       return true;
/*      */     } catch (Exception e) {
/*  711 */       e.printStackTrace();
/*  712 */     }return false;
/*      */   }
/*      */ 
/*      */   public static boolean insert(Object ob, String model_name)
/*      */   {
/*  726 */     SettingLogsBean stl = null;
/*      */     try {
/*  728 */       String info_status = "0";
/*  729 */       String site_id = "";
/*  730 */       String app_id = "";
/*      */       try {
/*  732 */         info_status = BeanUtils.getProperty(ob, "info_status");
/*  733 */         site_id = BeanUtils.getProperty(ob, "site_id");
/*  734 */         app_id = BeanUtils.getProperty(ob, "app_id");
/*      */       }
/*      */       catch (IllegalAccessException e) {
/*  737 */         e.printStackTrace();
/*      */       }
/*      */       catch (InvocationTargetException e) {
/*  740 */         e.printStackTrace();
/*      */       }
/*      */       catch (NoSuchMethodException e) {
/*  743 */         e.printStackTrace();
/*      */       }
/*      */ 
/*  746 */       if ("8".equals(info_status))
/*      */       {
/*  749 */         return FileRmiFactory.insertInfo(InfoBaseManager.getInfoReleSiteID(site_id, app_id), ob, model_name, stl);
/*      */       }
/*  751 */       return ModelUtil.insert(ob, model_name, stl);
/*      */     }
/*      */     catch (Exception e) {
/*  754 */       e.printStackTrace();
/*  755 */     }return false;
/*      */   }
/*      */ 
/*      */   public static boolean update(Object ob, String model_name)
/*      */   {
/*      */     try
/*      */     {
/*  770 */       System.out.println("ModelUtilRPC :: update :: start");
/*  771 */       String info_status = "0";
/*  772 */       String site_id = "";
/*  773 */       String app_id = "";
/*  774 */       SettingLogsBean stl = null;
/*      */       try {
/*  776 */         info_status = BeanUtils.getProperty(ob, "info_status");
/*  777 */         site_id = BeanUtils.getProperty(ob, "site_id");
/*  778 */         app_id = BeanUtils.getProperty(ob, "app_id");
/*      */       }
/*      */       catch (IllegalAccessException e) {
/*  781 */         e.printStackTrace();
/*      */       }
/*      */       catch (InvocationTargetException e) {
/*  784 */         e.printStackTrace();
/*      */       }
/*      */       catch (NoSuchMethodException e) {
/*  787 */         e.printStackTrace();
/*      */       }
/*      */ 
/*  790 */       if ("8".equals(info_status))
/*      */       {
/*  793 */         return FileRmiFactory.updateInfo(InfoBaseManager.getInfoReleSiteID(site_id, app_id), ob, model_name, stl);
/*      */       }
/*  795 */       return ModelUtil.update(ob, model_name, stl);
/*      */     } catch (Exception e) {
/*  797 */       e.printStackTrace();
/*  798 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean updateCustomInfo(String info)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/*  806 */       if (!isState()) {
/*  807 */         return true;
/*      */       }
/*      */ 
/*  810 */       Map mapResult = getInfoBeanByJson(info);
/*      */ 
/*  813 */       String dept_position = (String)mapResult.get("deptTreePath");
/*      */ 
/*  815 */       List list = new ArrayList();
/*  816 */       System.out.println("list.size() ---- " + list.size());
/*  817 */       if (list.size() > 0) {
/*  818 */         for (String site_id : list)
/*  819 */           updatePublicInfo(mapResult, site_id);
/*      */       }
/*      */       else {
/*  822 */         List listSite = SiteManager.getSiteList();
/*  823 */         System.out.println("listSite.size() ---- " + listSite.size());
/*  824 */         for (SiteBean site : listSite) {
/*  825 */           String parent_id = site.getParent_id();
/*  826 */           if (!parent_id.equals("0")) {
/*  827 */             String category_id = (String)mapResult.get("category_id");
/*  828 */             System.out.println(site.getSite_id() + " ---- " + category_id);
/*  829 */             CategoryBean cb = CategoryManager.getCategoryBean(Integer.valueOf(category_id).intValue());
/*  830 */             if (cb.getSite_id().equals(site.getSite_id())) {
/*  831 */               updatePublicInfo(mapResult, site.getSite_id());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  837 */       return true;
/*      */     } catch (Exception e) {
/*  839 */       e.printStackTrace();
/*  840 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean deleteCustomInfo(String ids)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/*  849 */       if (!isState()) {
/*  850 */         return true;
/*      */       }
/*      */ 
/*  854 */       List list = Arrays.asList(ids.split(","));
/*      */       Iterator localIterator2;
/*  855 */       label259: for (Iterator localIterator1 = list.iterator(); localIterator1.hasNext(); 
/*  862 */         localIterator2.hasNext())
/*      */       {
/*  855 */         String id = (String)localIterator1.next();
/*  856 */         if ((id == null) || ("".equals(id))) {
/*      */           break label259;
/*      */         }
/*  859 */         System.out.println("id ---- " + id);
/*  860 */         List listSite = WcmZykInfoService.getWcminfo_zykinfosById(id);
/*  861 */         System.out.println("listSite.size() ---- " + listSite.size());
/*  862 */         localIterator2 = listSite.iterator(); continue; Map map = (Map)localIterator2.next();
/*  863 */         String site_id = (String)MapManager.getValue(map, "site_id");
/*  864 */         String info_id = "";
/*  865 */         Object object = MapManager.getValue(map, "info_id");
/*  866 */         if ((object instanceof BigDecimal))
/*  867 */           info_id = object.toString();
/*      */         else {
/*  869 */           info_id = object.toString();
/*      */         }
/*  871 */         System.out.println("info_id:" + info_id + " --- site_id:" + site_id);
/*  872 */         deleteCustionInfoPublic(info_id, site_id);
/*      */       }
/*      */ 
/*  876 */       return true;
/*      */     } catch (Exception e) {
/*  878 */       e.printStackTrace();
/*  879 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean deleteCustionInfoPublic(String info_id, String site_id)
/*      */   {
/*      */     try
/*      */     {
/*  887 */       List list = Arrays.asList(info_id.split(","));
/*  888 */       for (String id : list) {
/*  889 */         if ((id != null) && (!"".equals(id)))
/*      */         {
/*  897 */           InfoBean infoBean = InfoBaseManager.getInfoById(id);
/*  898 */           List listInfoBean = new ArrayList();
/*  899 */           listInfoBean.add(infoBean);
/*  900 */           InfoBaseManager.updateInfoStatus(listInfoBean, "3", null);
/*      */ 
/*  906 */           Map map = new HashMap();
/*  907 */           map.put("info_ids", info_id);
/*  908 */           InfoDAO.deleteInfo(map);
/*      */ 
/*  910 */           WcmZykInfoDao.deleteZykinfoFileById(info_id);
/*      */ 
/*  913 */           WcmZykInfoDao.deleteWcminfo_zykinfoById(id, site_id);
/*      */         }
/*      */       }
/*  916 */       return true;
/*      */     } catch (Exception e) {
/*  918 */       e.printStackTrace();
/*  919 */     }return false;
/*      */   }
/*      */ 
/*      */   public static Map getInfoBeanByJson(String info)
/*      */   {
/*      */     try
/*      */     {
/*  928 */       Map mapResult = new HashMap();
/*  929 */       System.out.println("info==" + info);
/*  930 */       JSONObject joAll = JSONObject.fromObject(info);
/*  931 */       Iterator itAll = joAll.keys();
/*  932 */       Map mapAll = new HashMap();
/*  933 */       while (itAll.hasNext()) {
/*  934 */         String key = itAll.next().toString();
/*  935 */         String value = joAll.getString(key);
/*  936 */         mapAll.put(key, value);
/*      */       }
/*      */ 
/*  940 */       String category_id = (String)mapAll.get("treeId");
/*  941 */       category_id = CmsIresourceCategoryUtil.getCmscatidByResourceid(category_id.trim());
/*      */ 
/*  943 */       if (category_id.equals("")) {
/*  944 */         System.out.println("--------cms_iresource_cat.properties is not set----------------");
/*  945 */         return mapResult;
/*      */       }
/*      */ 
/*  948 */       mapResult.put("category_id", category_id);
/*      */ 
/*  950 */       String table_name = mapAll.get("mdcEnName").toString();
/*  951 */       mapResult.put("table_name", table_name);
/*      */ 
/*  953 */       String deptTreePath = (String)mapAll.get("deptTreePath");
/*  954 */       mapResult.put("deptTreePath", deptTreePath);
/*      */ 
/*  956 */       String from_id = (String)mapAll.get("id");
/*  957 */       mapResult.put("from_id", from_id);
/*  958 */       String jsData = (String)mapAll.get("data");
/*      */ 
/*  960 */       JSONArray joData = JSONArray.fromObject(jsData);
/*  961 */       Iterator itData = joData.iterator();
/*      */ 
/*  964 */       List listData = new ArrayList();
/*  965 */       mapResult.put("listData", listData);
/*      */       Map mapField;
/*  966 */       while (itData.hasNext()) {
/*  967 */         JSONObject jsonObject = (JSONObject)itData.next();
/*  968 */         Iterator itField = jsonObject.keys();
/*  969 */         mapField = new HashMap();
/*  970 */         while (itField.hasNext()) {
/*  971 */           String key = itField.next().toString();
/*  972 */           String value = jsonObject.getString(key);
/*  973 */           mapField.put(key, value);
/*      */         }
/*  975 */         listData.add(mapField);
/*      */       }
/*      */ 
/*  978 */       Map listDataMap = new HashMap();
/*  979 */       mapResult.put("listDataMap", listDataMap);
/*  980 */       for (Map map2 : listData) {
/*  981 */         String key = (String)map2.get("enName");
/*  982 */         String value = (String)map2.get("value");
/*  983 */         listDataMap.put(key, value);
/*      */       }
/*      */ 
/*  987 */       String jsFile = (String)mapAll.get("file");
/*      */ 
/*  989 */       List listFiles = new ArrayList();
/*  990 */       mapResult.put("listFiles", listFiles);
/*  991 */       JSONObject joFile = JSONObject.fromObject(jsFile);
/*  992 */       Iterator itFile = joFile.keys();
/*      */       Iterator itjsonFile;
/*  993 */       for (; itFile.hasNext(); 
/*  998 */         itjsonFile.hasNext())
/*      */       {
/*  994 */         String key = itFile.next().toString();
/*  995 */         String jsonFile = joFile.getString(key);
/*  996 */         JSONArray jsonFileAy = JSONArray.fromObject(jsonFile);
/*  997 */         itjsonFile = jsonFileAy.iterator();
/*  998 */         continue;
/*  999 */         JSONObject jsonObject = (JSONObject)itjsonFile.next();
/* 1000 */         Iterator itField = jsonObject.keys();
/* 1001 */         Map mapField = new HashMap();
/* 1002 */         while (itField.hasNext()) {
/* 1003 */           String key_1 = itField.next().toString();
/* 1004 */           String value = jsonObject.getString(key_1);
/* 1005 */           mapField.put(key_1, value);
/*      */         }
/* 1007 */         listFiles.add(mapField);
/*      */       }
/*      */ 
/* 1011 */       return mapResult;
/*      */     } catch (Exception e) {
/* 1013 */       e.printStackTrace();
/* 1014 */     }return null;
/*      */   }
/*      */ 
/*      */   public boolean addCustomForm(String info)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/* 1027 */       DataElementCollectionDTO dataElementCollection = (DataElementCollectionDTO)new ObjectMapper().readValue(info, DataElementCollectionDTO.class);
/*      */ 
/* 1029 */       if (!isState()) {
/* 1030 */         return true;
/*      */       }
/*      */ 
/* 1034 */       ModelBean mb = getModelBeanByDataElementCollection(dataElementCollection);
/*      */ 
/* 1037 */       List listFields = dataElementCollection.getDataElements();
/* 1038 */       for (DataElementCollectionDataElementDTO dataElementCollectionDataElement : listFields) {
/* 1039 */         addCustomForm(dataElementCollectionDataElement, mb);
/*      */       }
/*      */ 
/* 1044 */       return true;
/*      */     } catch (Exception e) {
/* 1046 */       e.printStackTrace();
/* 1047 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean updateCustomForm(String info)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/* 1058 */       DataElementCollectionDTO dataElementCollection = (DataElementCollectionDTO)new ObjectMapper().readValue(info, DataElementCollectionDTO.class);
/*      */ 
/* 1060 */       if (!isState()) {
/* 1061 */         return true;
/*      */       }
/*      */ 
/* 1064 */       String model_ename = dataElementCollection.getDecEnName().toLowerCase();
/* 1065 */       String model_name = dataElementCollection.getDecName();
/* 1066 */       String table_name = dataElementCollection.getDecEnName().toLowerCase();
/*      */ 
/* 1069 */       List listFieldsOld = new ArrayList();
/* 1070 */       Map mapFieldsOld = new HashMap();
/* 1071 */       ModelBean mb = ModelManager.getModelBeanByEName(model_ename);
/*      */ 
/* 1074 */       if (mb == null) {
/* 1075 */         return addCustomForm(dataElementCollection);
/*      */       }
/*      */ 
/* 1078 */       Map map = new HashMap();
/* 1079 */       map.put("model_id", Integer.valueOf(mb.getModel_id()));
/* 1080 */       List fieldsList = FormRPC.getFormFieldsListByModelIdN(map);
/* 1081 */       for (Fields fields : fieldsList) {
/* 1082 */         listFieldsOld.add(fields.getField_enname());
/* 1083 */         mapFieldsOld.put(fields.getField_enname(), String.valueOf(fields.getId()));
/*      */       }
/*      */ 
/* 1087 */       List listFieldsNew = new ArrayList();
/* 1088 */       List listFields = dataElementCollection.getDataElements();
/* 1089 */       for (DataElementCollectionDataElementDTO dataElementCollectionDataElement : listFields) {
/* 1090 */         DataElementDTO dataElement = dataElementCollectionDataElement.getDataElement();
/* 1091 */         String field_enname = dataElement.getDeShortName();
/* 1092 */         listFieldsNew.add(field_enname);
/*      */       }
/*      */ 
/* 1096 */       List addList = new ArrayList(listFieldsNew);
/* 1097 */       Object deleteListTemp = new ArrayList();
/* 1098 */       List deleteList = new ArrayList((Collection)deleteListTemp);
/*      */ 
/* 1100 */       List idsList = new ArrayList(listFieldsOld);
/* 1101 */       List cidsList = new ArrayList(listFieldsNew);
/* 1102 */       for (String idsStr : idsList) {
/* 1103 */         if ((idsStr != null) && (!"".equals(idsStr)))
/*      */         {
/* 1106 */           if (!cidsList.contains(idsStr))
/* 1107 */             deleteList.add(idsStr);
/*      */         }
/*      */       }
/* 1110 */       addList.removeAll(idsList);
/*      */ 
/* 1112 */       System.out.println("CustomFormRMIImpl.java updateCustomForm addList :: " + addList);
/* 1113 */       System.out.println("CustomFormRMIImpl.java updateCustomForm deleteList :: " + deleteList);
/*      */ 
/* 1117 */       for (DataElementCollectionDataElementDTO dataElementCollectionDataElement : listFields) {
/* 1118 */         DataElementDTO dataElement = dataElementCollectionDataElement.getDataElement();
/* 1119 */         String field_enname = dataElement.getDeShortName();
/* 1120 */         if (addList.contains(field_enname)) {
/* 1121 */           addCustomForm(dataElementCollectionDataElement, mb);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1126 */       for (String del_name : deleteList) {
/* 1127 */         String d_id = (String)mapFieldsOld.get(del_name);
/*      */ 
/* 1130 */         FormService.deleteFormFields(d_id, mb.getModel_id());
/*      */       }
/*      */ 
/* 1133 */       FormService.clearMapLsit();
/*      */ 
/* 1135 */       return true;
/*      */     } catch (Exception e) {
/* 1137 */       e.printStackTrace();
/* 1138 */       FormService.clearMapLsit();
/* 1139 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean insertForm(Object ob, String model_name, String from_id)
/*      */     throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/* 1148 */       if (!isState()) {
/* 1149 */         return true;
/*      */       }
/*      */ 
/* 1152 */       String site_id = BeanUtils.getProperty(ob, "site_id");
/* 1153 */       String info_id = WcmZykInfoService.getWcminfo_zykinfoById(from_id, site_id);
/* 1154 */       if ((info_id != null) && (!"".equals(info_id))) {
/* 1155 */         System.out.println("WcmZykInfo --- id is exist");
/* 1156 */         return false;
/*      */       }
/*      */ 
/* 1174 */       if (model_name.endsWith("article")) {
/* 1175 */         ArticleBean articleBean = (ArticleBean)ob;
/* 1176 */         articleBean.setPage_count(1);
/* 1177 */         articleBean.setId(InfoBaseRPC.getInfoId());
/* 1178 */         articleBean.setInfo_id(articleBean.getId());
/* 1179 */         articleBean.setStep_id(100);
/* 1180 */         articleBean.setInfo_status(4);
/* 1181 */         articleBean.setWeight(60);
/* 1182 */         articleBean.setModel_id(11);
/* 1183 */         String tags = InfoBaseRPC.getTagsForTitle(articleBean.getTitle());
/* 1184 */         articleBean.setTags(tags);
/* 1185 */         articleBean.setApp_id("cms");
/* 1186 */         articleBean.setInfo_content(CustomFormRMIImplUtil.replaceAllStr(articleBean.getInfo_content()));
/* 1187 */         articleBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(articleBean.getThumb_url()));
/* 1188 */         ob = articleBean;
/*      */       }
/*      */ 
/* 1219 */       if (model_name.endsWith("pic")) {
/* 1220 */         PicBean picBean = (PicBean)ob;
/* 1221 */         picBean.setPage_count(1);
/* 1222 */         picBean.setId(InfoBaseRPC.getInfoId());
/* 1223 */         picBean.setInfo_id(picBean.getId());
/* 1224 */         picBean.setInfo_status(4);
/* 1225 */         picBean.setWeight(60);
/* 1226 */         picBean.setStep_id(100);
/* 1227 */         picBean.setModel_id(10);
/* 1228 */         picBean.setIs_pic(1);
/* 1229 */         String tags = InfoBaseRPC.getTagsForTitle(picBean.getTitle());
/* 1230 */         picBean.setTags(tags);
/* 1231 */         List item_list_temp = new ArrayList();
/* 1232 */         int i = 1;
/* 1233 */         for (PicItemBean bean : picBean.getItem_list()) {
/* 1234 */           bean.setInfo_id(picBean.getInfo_id());
/* 1235 */           if (i == 1) {
/* 1236 */             bean.setPic_content(CustomFormRMIImplUtil.replaceAllStr(picBean.getPic_content()));
/*      */           }
/* 1238 */           bean.setPic_sort(i);
/* 1239 */           bean.setPic_path(CustomFormRMIImplUtil.replaceAllStr(bean.getPic_path()));
/* 1240 */           i++;
/* 1241 */           item_list_temp.add(bean);
/*      */         }
/* 1243 */         picBean.setItem_list(item_list_temp);
/* 1244 */         picBean.setApp_id("cms");
/*      */ 
/* 1246 */         picBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(picBean.getThumb_url()));
/* 1247 */         ob = picBean;
/*      */       }
/*      */ 
/* 1266 */       if (model_name.endsWith("link")) {
/* 1267 */         InfoBean infoBean = (InfoBean)ob;
/* 1268 */         infoBean.setPage_count(1);
/* 1269 */         infoBean.setId(InfoBaseRPC.getInfoId());
/* 1270 */         infoBean.setInfo_id(infoBean.getId());
/* 1271 */         infoBean.setInfo_status(4);
/* 1272 */         infoBean.setWeight(60);
/* 1273 */         infoBean.setStep_id(100);
/* 1274 */         infoBean.setModel_id(12);
/* 1275 */         String tags = InfoBaseRPC.getTagsForTitle(infoBean.getTitle());
/* 1276 */         infoBean.setTags(tags);
/* 1277 */         infoBean.setApp_id("cms");
/* 1278 */         infoBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(infoBean.getThumb_url()));
/* 1279 */         ob = infoBean;
/*      */       }
/*      */ 
/* 1300 */       if (model_name.endsWith("video")) {
/* 1301 */         VideoBean videoBean = (VideoBean)ob;
/* 1302 */         videoBean.setPage_count(1);
/* 1303 */         videoBean.setId(InfoBaseRPC.getInfoId());
/* 1304 */         videoBean.setInfo_id(videoBean.getId());
/* 1305 */         videoBean.setInfo_status(4);
/* 1306 */         videoBean.setWeight(60);
/* 1307 */         videoBean.setStep_id(100);
/* 1308 */         videoBean.setModel_id(13);
/* 1309 */         String tags = InfoBaseRPC.getTagsForTitle(videoBean.getTitle());
/* 1310 */         videoBean.setTags(tags);
/* 1311 */         videoBean.setApp_id("cms");
/* 1312 */         videoBean.setInfo_content(CustomFormRMIImplUtil.replaceAllStr(videoBean.getInfo_content()));
/* 1313 */         videoBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(videoBean.getThumb_url()));
/* 1314 */         ob = videoBean;
/*      */       }
/*      */ 
/* 1364 */       if (model_name.endsWith("gkftygs")) {
/* 1365 */         GKFtygsBean gkFtygsBean = (GKFtygsBean)ob;
/* 1366 */         gkFtygsBean.setPage_count(1);
/* 1367 */         gkFtygsBean.setId(InfoBaseRPC.getInfoId());
/* 1368 */         gkFtygsBean.setInfo_id(gkFtygsBean.getId());
/* 1369 */         gkFtygsBean.setInfo_status(4);
/* 1370 */         gkFtygsBean.setModel_id(14);
/*      */ 
/* 1372 */         gkFtygsBean.setWeight(60);
/* 1373 */         gkFtygsBean.setStep_id(100);
/* 1374 */         String tags = InfoBaseRPC.getTagsForTitle(gkFtygsBean.getTitle());
/* 1375 */         gkFtygsBean.setTags(tags);
/* 1376 */         gkFtygsBean.setTopic_key(tags);
/* 1377 */         gkFtygsBean.setApp_id("zwgk");
/*      */ 
/* 1379 */         List file_list_temp = new ArrayList();
/* 1380 */         int i = 1;
/* 1381 */         for (GKResFileBean bean : gkFtygsBean.getFile_list()) {
/* 1382 */           bean.setInfo_id(gkFtygsBean.getInfo_id());
/* 1383 */           bean.setSort_id(i);
/* 1384 */           bean.setRes_url(CustomFormRMIImplUtil.replaceAllStr(bean.getRes_url()));
/* 1385 */           i++;
/* 1386 */           file_list_temp.add(bean);
/*      */         }
/* 1388 */         gkFtygsBean.setFile_list(file_list_temp);
/* 1389 */         gkFtygsBean.setGk_content(CustomFormRMIImplUtil.replaceAllStr(gkFtygsBean.getGk_content()));
/* 1390 */         gkFtygsBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(gkFtygsBean.getThumb_url()));
/* 1391 */         ob = gkFtygsBean;
/*      */       }
/*      */ 
/* 1449 */       if (model_name.endsWith("gkfldcy")) {
/* 1450 */         GKFldcyBean gkFldcyBean = (GKFldcyBean)ob;
/* 1451 */         gkFldcyBean.setPage_count(1);
/* 1452 */         gkFldcyBean.setId(InfoBaseRPC.getInfoId());
/* 1453 */         gkFldcyBean.setInfo_id(gkFldcyBean.getId());
/* 1454 */         gkFldcyBean.setInfo_status(4);
/* 1455 */         gkFldcyBean.setModel_id(16);
/*      */ 
/* 1457 */         gkFldcyBean.setWeight(60);
/* 1458 */         gkFldcyBean.setStep_id(100);
/* 1459 */         String tags = InfoBaseRPC.getTagsForTitle(gkFldcyBean.getTitle());
/* 1460 */         gkFldcyBean.setTags(tags);
/* 1461 */         gkFldcyBean.setTopic_key(tags);
/* 1462 */         gkFldcyBean.setApp_id("zwgk");
/*      */ 
/* 1464 */         List file_list_temp = new ArrayList();
/* 1465 */         int i = 1;
/* 1466 */         for (GKResFileBean bean : gkFldcyBean.getFile_list()) {
/* 1467 */           bean.setInfo_id(gkFldcyBean.getInfo_id());
/* 1468 */           bean.setSort_id(i);
/* 1469 */           bean.setRes_url(CustomFormRMIImplUtil.replaceAllStr(bean.getRes_url()));
/* 1470 */           i++;
/* 1471 */           file_list_temp.add(bean);
/*      */         }
/* 1473 */         gkFldcyBean.setFile_list(file_list_temp);
/* 1474 */         gkFldcyBean.setGk_content(CustomFormRMIImplUtil.replaceAllStr(gkFldcyBean.getGk_content()));
/* 1475 */         gkFldcyBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(gkFldcyBean.getThumb_url()));
/* 1476 */         ob = gkFldcyBean;
/*      */       }
/*      */ 
/* 1547 */       if (model_name.endsWith("gkfbszn")) {
/* 1548 */         GKFbsznBean gkFbsznBean = (GKFbsznBean)ob;
/* 1549 */         gkFbsznBean.setPage_count(1);
/* 1550 */         gkFbsznBean.setId(InfoBaseRPC.getInfoId());
/* 1551 */         gkFbsznBean.setInfo_id(gkFbsznBean.getId());
/* 1552 */         gkFbsznBean.setInfo_status(4);
/* 1553 */         gkFbsznBean.setModel_id(20);
/*      */ 
/* 1555 */         gkFbsznBean.setWeight(60);
/* 1556 */         gkFbsznBean.setStep_id(100);
/* 1557 */         String tags = InfoBaseRPC.getTagsForTitle(gkFbsznBean.getTitle());
/* 1558 */         gkFbsznBean.setTags(tags);
/* 1559 */         gkFbsznBean.setTopic_key(tags);
/* 1560 */         gkFbsznBean.setApp_id("zwgk");
/*      */ 
/* 1562 */         List file_list_temp = new ArrayList();
/* 1563 */         int i = 1;
/* 1564 */         for (GKResFileBean bean : gkFbsznBean.getFile_list()) {
/* 1565 */           bean.setInfo_id(gkFbsznBean.getInfo_id());
/* 1566 */           bean.setSort_id(i);
/* 1567 */           bean.setRes_url(CustomFormRMIImplUtil.replaceAllStr(bean.getRes_url()));
/* 1568 */           i++;
/* 1569 */           file_list_temp.add(bean);
/*      */         }
/* 1571 */         gkFbsznBean.setFile_list(file_list_temp);
/*      */ 
/* 1573 */         gkFbsznBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(gkFbsznBean.getThumb_url()));
/* 1574 */         ob = gkFbsznBean;
/*      */       }
/*      */ 
/* 1577 */       String temp_info_id = BeanUtils.getProperty(ob, "info_id");
/*      */ 
/* 1579 */       Map mp = ModelConfig.getModelMap(model_name);
/* 1580 */       System.out.println("insert ---- model_name :: " + model_name);
/* 1581 */       ModelUtil.setPageCountInObject(ob, model_name);
/* 1582 */       String addSql = "";
/* 1583 */       if (mp != null) {
/* 1584 */         addSql = (String)mp.get("AddSQL");
/*      */       }
/* 1586 */       if (addModel(ob, addSql, model_name, null))
/*      */       {
/* 1588 */         WcmZykInfoDao.addWcminfo_zykinfo(from_id, temp_info_id, site_id);
/*      */       }
/* 1590 */       return true;
/*      */     } catch (Exception e) {
/* 1592 */       e.printStackTrace();
/* 1593 */     }return false;
/*      */   }
/*      */ 
/*      */   public static boolean addModel(Object o, String sqlName, String model_name, SettingLogsBean stl)
/*      */   {
/* 1609 */     if (addInfo(o, stl))
/*      */     {
/* 1611 */       if (model_name.toLowerCase().contains("gk"))
/*      */       {
/* 1613 */         GKInfoDAO.insertGKInfo(o);
/*      */       }
/* 1615 */       if (!"".equals(sqlName))
/*      */       {
/* 1617 */         if (sqlName.equals("insert_info_pic"))
/*      */         {
/* 1619 */           return ModelUtilDAO.insertPicInfo(o, model_name);
/*      */         }
/*      */ 
/* 1623 */         return DBManager.insert(sqlName, o);
/*      */       }
/*      */ 
/* 1626 */       return true;
/*      */     }
/*      */ 
/* 1630 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean addInfo(Object ob, SettingLogsBean stl)
/*      */   {
/* 1641 */     InfoBean info = (InfoBean)ob;
/* 1642 */     if (info.getId() == 0) {
/* 1643 */       int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_TABLE_NAME);
/* 1644 */       info.setId(id);
/* 1645 */       info.setInfo_id(id);
/*      */     }
/* 1647 */     if (info.getInput_dtime().equals("")) {
/* 1648 */       info.setInput_dtime(DateUtil.getCurrentDateTime());
/*      */     }
/* 1650 */     info.setReleased_dtime(info.getInput_dtime());
/* 1651 */     changeInfoStatus(info);
/*      */ 
/* 1653 */     return InfoDAO.addInfo(info, stl);
/*      */   }
/*      */ 
/*      */   public static void changeInfoStatus(InfoBean info)
/*      */   {
/* 1664 */     if (info.getInfo_status() == 4) {
/* 1665 */       info.setStep_id(100);
/*      */ 
/* 1668 */       CategoryBean cb = CategoryManager.getCategoryBeanCatID(info.getCat_id(), info.getSite_id());
/*      */ 
/* 1670 */       if ((cb != null) && (cb.getIs_wf_publish() == 1))
/*      */       {
/* 1672 */         info.setInfo_status(8);
/* 1673 */         if ((info.getReleased_dtime() == null) || ("".equals(info.getReleased_dtime())))
/* 1674 */           info.setReleased_dtime(DateUtil.getCurrentDateTime());
/*      */       }
/*      */     }
/* 1677 */     if (info.getInfo_status() == 8) {
/* 1678 */       if ((info.getReleased_dtime() == null) || ("".equals(info.getReleased_dtime())))
/* 1679 */         info.setReleased_dtime(DateUtil.getCurrentDateTime());
/* 1680 */       info.setStep_id(100);
/*      */     }
/* 1682 */     if ((info.getContent_url() == null) || ("".equals(info.getContent_url())))
/* 1683 */       info.setContent_url(CategoryUtil.getFoldePathByCategoryID(info.getCat_id(), info.getApp_id(), info.getSite_id()) + info.getInfo_id() + ".htm");
/*      */   }
/*      */ 
/*      */   public boolean updateForm(Object ob, String model_name, String from_id) throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/* 1690 */       if (!isState()) {
/* 1691 */         return true;
/*      */       }
/*      */ 
/* 1694 */       String site_id = BeanUtils.getProperty(ob, "site_id");
/* 1695 */       String info_id = WcmZykInfoService.getWcminfo_zykinfoById(from_id, site_id);
/* 1696 */       if ((info_id == null) || ("".equals(info_id))) {
/* 1697 */         System.out.println("WcmZykInfo -- id is not esist!");
/* 1698 */         return insertForm(ob, model_name, from_id);
/*      */       }
/* 1700 */       String app_id = "cms";
/*      */ 
/* 1704 */       InfoBean infoBean = InfoBaseManager.getInfoById(info_id);
/* 1705 */       if (model_name.endsWith("article")) {
/* 1706 */         ArticleBean articleBean = (ArticleBean)ob;
/*      */ 
/* 1709 */         String tags = InfoBaseRPC.getTagsForTitle(articleBean.getTitle());
/* 1710 */         articleBean.setTags(tags);
/* 1711 */         app_id = articleBean.getApp_id();
/* 1712 */         site_id = articleBean.getSite_id();
/* 1713 */         if (infoBean == null) {
/* 1714 */           info_id = articleBean.getInfo_id();
/* 1715 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1717 */         articleBean.setInfo_id(Integer.parseInt(info_id));
/* 1718 */         articleBean.setSite_id(site_id);
/* 1719 */         articleBean.setPage_count(1);
/* 1720 */         articleBean.setStep_id(100);
/* 1721 */         articleBean.setWeight(60);
/* 1722 */         articleBean.setModel_id(11);
/* 1723 */         articleBean.setCat_id(infoBean.getCat_id());
/* 1724 */         articleBean.setApp_id(infoBean.getApp_id());
/* 1725 */         if ("8".equals(infoBean.getInfo_status()))
/* 1726 */           articleBean.setInfo_status(8);
/*      */         else {
/* 1728 */           articleBean.setInfo_status(4);
/*      */         }
/* 1730 */         System.out.println("articleBean.getInfo_status()--" + articleBean.getInfo_status());
/* 1731 */         articleBean.setInfo_content(CustomFormRMIImplUtil.replaceAllStr(articleBean.getInfo_content()));
/* 1732 */         articleBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(articleBean.getThumb_url()));
/* 1733 */         ob = articleBean;
/*      */       }
/* 1735 */       if (model_name.endsWith("pic")) {
/* 1736 */         PicBean picBean = (PicBean)ob;
/*      */ 
/* 1739 */         String tags = InfoBaseRPC.getTagsForTitle(picBean.getTitle());
/* 1740 */         picBean.setTags(tags);
/* 1741 */         app_id = picBean.getApp_id();
/* 1742 */         site_id = picBean.getSite_id();
/* 1743 */         if (infoBean == null) {
/* 1744 */           info_id = picBean.getInfo_id();
/* 1745 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1747 */         picBean.setInfo_id(Integer.parseInt(info_id));
/* 1748 */         picBean.setSite_id(site_id);
/* 1749 */         picBean.setPage_count(1);
/* 1750 */         picBean.setStep_id(100);
/* 1751 */         picBean.setWeight(60);
/* 1752 */         picBean.setModel_id(10);
/* 1753 */         picBean.setCat_id(infoBean.getCat_id());
/* 1754 */         picBean.setApp_id(infoBean.getApp_id());
/* 1755 */         if ("8".equals(Integer.valueOf(infoBean.getInfo_status())))
/* 1756 */           picBean.setInfo_status(8);
/*      */         else {
/* 1758 */           picBean.setInfo_status(4);
/*      */         }
/* 1760 */         System.out.println("articleBean.getInfo_status()--" + picBean.getInfo_status());
/* 1761 */         List item_list_temp = new ArrayList();
/* 1762 */         int i = 1;
/* 1763 */         for (PicItemBean bean : picBean.getItem_list()) {
/* 1764 */           bean.setInfo_id(picBean.getInfo_id());
/* 1765 */           if (i == 1) {
/* 1766 */             bean.setPic_content(CustomFormRMIImplUtil.replaceAllStr(picBean.getPic_content()));
/*      */           }
/* 1768 */           bean.setPic_sort(i);
/* 1769 */           picBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(picBean.getThumb_url()));
/* 1770 */           i++;
/* 1771 */           item_list_temp.add(bean);
/*      */         }
/* 1773 */         picBean.setItem_list(item_list_temp);
/* 1774 */         ob = picBean;
/*      */       }
/* 1776 */       if (model_name.endsWith("link")) {
/* 1777 */         InfoBean infoBean2 = (InfoBean)ob;
/*      */ 
/* 1780 */         String tags = InfoBaseRPC.getTagsForTitle(infoBean2.getTitle());
/* 1781 */         infoBean2.setTags(tags);
/* 1782 */         app_id = infoBean2.getApp_id();
/* 1783 */         site_id = infoBean2.getSite_id();
/* 1784 */         if (infoBean == null) {
/* 1785 */           info_id = infoBean2.getInfo_id();
/* 1786 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1788 */         infoBean2.setInfo_id(Integer.parseInt(info_id));
/* 1789 */         infoBean2.setSite_id(site_id);
/* 1790 */         infoBean2.setPage_count(1);
/* 1791 */         infoBean2.setStep_id(100);
/* 1792 */         infoBean2.setWeight(60);
/* 1793 */         infoBean2.setModel_id(12);
/* 1794 */         infoBean2.setCat_id(infoBean.getCat_id());
/* 1795 */         infoBean2.setApp_id(infoBean.getApp_id());
/* 1796 */         if ("8".equals(infoBean.getInfo_status()))
/* 1797 */           infoBean2.setInfo_status(8);
/*      */         else {
/* 1799 */           infoBean2.setInfo_status(4);
/*      */         }
/* 1801 */         System.out.println("articleBean.getInfo_status()--" + infoBean2.getInfo_status());
/* 1802 */         infoBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(infoBean.getThumb_url()));
/* 1803 */         ob = infoBean2;
/*      */       }
/* 1805 */       if (model_name.endsWith("video")) {
/* 1806 */         VideoBean videoBean = (VideoBean)ob;
/*      */ 
/* 1809 */         String tags = InfoBaseRPC.getTagsForTitle(videoBean.getTitle());
/* 1810 */         videoBean.setTags(tags);
/* 1811 */         app_id = videoBean.getApp_id();
/* 1812 */         site_id = videoBean.getSite_id();
/* 1813 */         if (infoBean == null) {
/* 1814 */           info_id = videoBean.getInfo_id();
/* 1815 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1817 */         videoBean.setInfo_id(Integer.parseInt(info_id));
/* 1818 */         videoBean.setSite_id(site_id);
/* 1819 */         videoBean.setPage_count(1);
/* 1820 */         videoBean.setStep_id(100);
/* 1821 */         videoBean.setWeight(60);
/* 1822 */         videoBean.setModel_id(13);
/* 1823 */         videoBean.setCat_id(infoBean.getCat_id());
/* 1824 */         videoBean.setApp_id(infoBean.getApp_id());
/* 1825 */         if ("8".equals(infoBean.getInfo_status()))
/* 1826 */           videoBean.setInfo_status(8);
/*      */         else {
/* 1828 */           videoBean.setInfo_status(4);
/*      */         }
/* 1830 */         System.out.println("articleBean.getInfo_status()--" + videoBean.getInfo_status());
/* 1831 */         videoBean.setInfo_content(CustomFormRMIImplUtil.replaceAllStr(videoBean.getInfo_content()));
/* 1832 */         videoBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(videoBean.getThumb_url()));
/* 1833 */         ob = videoBean;
/*      */       }
/* 1835 */       if (model_name.endsWith("gkftygs")) {
/* 1836 */         GKFtygsBean gkFtygsBean = (GKFtygsBean)ob;
/*      */ 
/* 1839 */         String tags = InfoBaseRPC.getTagsForTitle(gkFtygsBean.getTitle());
/* 1840 */         gkFtygsBean.setTags(tags);
/* 1841 */         gkFtygsBean.setTopic_key(tags);
/* 1842 */         app_id = gkFtygsBean.getApp_id();
/* 1843 */         site_id = gkFtygsBean.getSite_id();
/* 1844 */         if (infoBean == null) {
/* 1845 */           info_id = gkFtygsBean.getInfo_id();
/* 1846 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1848 */         gkFtygsBean.setInfo_id(Integer.parseInt(info_id));
/* 1849 */         gkFtygsBean.setSite_id(site_id);
/* 1850 */         gkFtygsBean.setPage_count(1);
/* 1851 */         gkFtygsBean.setStep_id(100);
/* 1852 */         gkFtygsBean.setWeight(60);
/* 1853 */         gkFtygsBean.setModel_id(14);
/* 1854 */         gkFtygsBean.setCat_id(infoBean.getCat_id());
/* 1855 */         gkFtygsBean.setApp_id(infoBean.getApp_id());
/* 1856 */         if ("8".equals(infoBean.getInfo_status()))
/* 1857 */           gkFtygsBean.setInfo_status(8);
/*      */         else {
/* 1859 */           gkFtygsBean.setInfo_status(4);
/*      */         }
/*      */ 
/* 1862 */         List file_list_temp = new ArrayList();
/* 1863 */         int i = 1;
/* 1864 */         for (GKResFileBean bean : gkFtygsBean.getFile_list()) {
/* 1865 */           bean.setInfo_id(gkFtygsBean.getInfo_id());
/* 1866 */           bean.setSort_id(i);
/* 1867 */           bean.setRes_url(CustomFormRMIImplUtil.replaceAllStr(bean.getRes_url()));
/* 1868 */           i++;
/* 1869 */           file_list_temp.add(bean);
/*      */         }
/* 1871 */         gkFtygsBean.setFile_list(file_list_temp);
/*      */ 
/* 1873 */         System.out.println("articleBean.getInfo_status()--" + gkFtygsBean.getInfo_status());
/* 1874 */         gkFtygsBean.setGk_content(CustomFormRMIImplUtil.replaceAllStr(gkFtygsBean.getGk_content()));
/* 1875 */         gkFtygsBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(gkFtygsBean.getThumb_url()));
/* 1876 */         ob = gkFtygsBean;
/*      */       }
/* 1878 */       if (model_name.endsWith("gkfldcy")) {
/* 1879 */         GKFldcyBean gkFldcyBean = (GKFldcyBean)ob;
/*      */ 
/* 1882 */         String tags = InfoBaseRPC.getTagsForTitle(gkFldcyBean.getTitle());
/* 1883 */         gkFldcyBean.setTags(tags);
/* 1884 */         gkFldcyBean.setTopic_key(tags);
/* 1885 */         app_id = gkFldcyBean.getApp_id();
/* 1886 */         site_id = gkFldcyBean.getSite_id();
/* 1887 */         if (infoBean == null) {
/* 1888 */           info_id = gkFldcyBean.getInfo_id();
/* 1889 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1891 */         gkFldcyBean.setInfo_id(Integer.parseInt(info_id));
/* 1892 */         gkFldcyBean.setSite_id(site_id);
/* 1893 */         gkFldcyBean.setPage_count(1);
/* 1894 */         gkFldcyBean.setStep_id(100);
/* 1895 */         gkFldcyBean.setWeight(60);
/* 1896 */         gkFldcyBean.setModel_id(16);
/* 1897 */         gkFldcyBean.setCat_id(infoBean.getCat_id());
/* 1898 */         gkFldcyBean.setApp_id(infoBean.getApp_id());
/* 1899 */         if ("8".equals(infoBean.getInfo_status()))
/* 1900 */           gkFldcyBean.setInfo_status(8);
/*      */         else {
/* 1902 */           gkFldcyBean.setInfo_status(4);
/*      */         }
/*      */ 
/* 1905 */         List file_list_temp = new ArrayList();
/* 1906 */         int i = 1;
/* 1907 */         for (GKResFileBean bean : gkFldcyBean.getFile_list()) {
/* 1908 */           bean.setInfo_id(gkFldcyBean.getInfo_id());
/* 1909 */           bean.setSort_id(i);
/* 1910 */           bean.setRes_url(CustomFormRMIImplUtil.replaceAllStr(bean.getRes_url()));
/* 1911 */           i++;
/* 1912 */           file_list_temp.add(bean);
/*      */         }
/* 1914 */         gkFldcyBean.setFile_list(file_list_temp);
/*      */ 
/* 1916 */         System.out.println("articleBean.getInfo_status()--" + gkFldcyBean.getInfo_status());
/* 1917 */         gkFldcyBean.setGk_content(CustomFormRMIImplUtil.replaceAllStr(gkFldcyBean.getGk_content()));
/* 1918 */         gkFldcyBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(gkFldcyBean.getThumb_url()));
/* 1919 */         ob = gkFldcyBean;
/*      */       }
/* 1921 */       if (model_name.endsWith("gkfbszn")) {
/* 1922 */         GKFbsznBean gkFbsznBean = (GKFbsznBean)ob;
/*      */ 
/* 1925 */         String tags = InfoBaseRPC.getTagsForTitle(gkFbsznBean.getTitle());
/* 1926 */         gkFbsznBean.setTags(tags);
/* 1927 */         gkFbsznBean.setTopic_key(tags);
/* 1928 */         app_id = gkFbsznBean.getApp_id();
/* 1929 */         site_id = gkFbsznBean.getSite_id();
/* 1930 */         if (infoBean == null) {
/* 1931 */           info_id = gkFbsznBean.getInfo_id();
/* 1932 */           infoBean = InfoBaseManager.getInfoById(info_id);
/*      */         }
/* 1934 */         gkFbsznBean.setInfo_id(Integer.parseInt(info_id));
/* 1935 */         gkFbsznBean.setSite_id(site_id);
/* 1936 */         gkFbsznBean.setPage_count(1);
/* 1937 */         gkFbsznBean.setStep_id(100);
/* 1938 */         gkFbsznBean.setWeight(60);
/* 1939 */         gkFbsznBean.setModel_id(20);
/* 1940 */         gkFbsznBean.setCat_id(infoBean.getCat_id());
/* 1941 */         gkFbsznBean.setApp_id(infoBean.getApp_id());
/* 1942 */         if ("8".equals(infoBean.getInfo_status()))
/* 1943 */           gkFbsznBean.setInfo_status(8);
/*      */         else {
/* 1945 */           gkFbsznBean.setInfo_status(4);
/*      */         }
/*      */ 
/* 1948 */         List file_list_temp = new ArrayList();
/* 1949 */         int i = 1;
/* 1950 */         for (GKResFileBean bean : gkFbsznBean.getFile_list()) {
/* 1951 */           bean.setInfo_id(gkFbsznBean.getInfo_id());
/* 1952 */           bean.setSort_id(i);
/* 1953 */           bean.setRes_url(CustomFormRMIImplUtil.replaceAllStr(bean.getRes_url()));
/* 1954 */           i++;
/* 1955 */           file_list_temp.add(bean);
/*      */         }
/* 1957 */         gkFbsznBean.setFile_list(file_list_temp);
/*      */ 
/* 1959 */         System.out.println("articleBean.getInfo_status()--" + gkFbsznBean.getInfo_status());
/* 1960 */         gkFbsznBean.setThumb_url(CustomFormRMIImplUtil.replaceAllStr(gkFbsznBean.getThumb_url()));
/* 1961 */         ob = gkFbsznBean;
/*      */       }
/* 1963 */       Map mp = ModelConfig.getModelMap(model_name);
/* 1964 */       System.out.println("ModelUtilRPC :: update :: start---" + infoBean.getInfo_status());
/* 1965 */       if ("8".equals(infoBean.getInfo_status().trim()))
/*      */       {
/* 1967 */         System.out.println("ModelUtilRPC :: info_status :: start3---" + BeanUtils.getProperty(ob, "info_status"));
/* 1968 */         System.out.println("ModelUtilRPC :: site_id :: start3---" + BeanUtils.getProperty(ob, "site_id"));
/*      */ 
/* 1970 */         return CustomFormRMIImplUtil.updateInfo(InfoBaseManager.getInfoReleSiteID(site_id, app_id), ob, model_name, null);
/*      */       }
/* 1972 */       return CustomFormRMIImplUtil.update(ob, model_name, null);
/*      */     }
/*      */     catch (Exception e) {
/* 1975 */       e.printStackTrace();
/* 1976 */     }return false;
/*      */   }
/*      */ 
/*      */   public boolean deleteForm(List<String> list, String site_id) throws RemoteException
/*      */   {
/*      */     try
/*      */     {
/* 1983 */       if (!isState()) {
/* 1984 */         return true;
/*      */       }
/* 1986 */       if (list.size() < 0) {
/* 1987 */         return true;
/*      */       }
/* 1989 */       for (String id : list)
/* 1990 */         if ((id != null) && (!"".equals(id)))
/*      */         {
/* 1992 */           String info_id = WcmZykInfoService.getWcminfo_zykinfoById(id, site_id);
/* 1993 */           if ((info_id != null) && (!"".equals(info_id)))
/*      */           {
/* 1996 */             InfoBean infoBean = InfoBaseManager.getInfoById(info_id);
/* 1997 */             if (infoBean != null)
/*      */             {
/* 2000 */               List infoList = new ArrayList();
/* 2001 */               infoList.add(infoBean);
/* 2002 */               FileRmiFactory.deleteInfo(InfoBaseManager.getInfoReleSiteID(((InfoBean)infoList.get(0)).getSite_id(), ((InfoBean)infoList.get(0)).getApp_id()), infoList, null);
/*      */ 
/* 2004 */               WcmZykInfoDao.deleteWcminfo_zykinfoById(id, site_id);
/*      */             }
/*      */           }
/*      */         }
/* 2007 */       return true;
/*      */     } catch (Exception e) {
/* 2009 */       e.printStackTrace();
/* 2010 */     }return false;
/*      */   }
/*      */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.CustomFormRMIImpl
 * JD-Core Version:    0.6.2
 */