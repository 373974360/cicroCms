/*     */ package com.cicro.wcm.services.model.services;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.services.model.Fields;
/*     */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FormService
/*     */ {
/*  27 */   private static Map mapLsit = new HashMap();
/*     */ 
/*     */   public static void clearMapLsit()
/*     */   {
/*  31 */     mapLsit.clear();
/*     */   }
/*     */ 
/*     */   public static List<Fields> listToResultFields(List<Fields> list)
/*     */   {
/*  36 */     List result = new ArrayList();
/*  37 */     for (Fields field : list) {
/*  38 */       String fieldXml = FieldsUtil.formatQuote(field.getField_info());
/*  39 */       field = FieldsUtil.getBeanByFieldXml(field, fieldXml, field.getField_type());
/*  40 */       if ((field.getField_cnname() == null) || ("".equals(field.getField_cnname()))) {
/*  41 */         field.setField_cnname(field.getField_enname());
/*     */       }
/*  43 */       Fields fields = FieldsService.getFieldById(field.getFrom_id()+"");
/*  44 */       if ((fields == null) || (fields.getField_cnname() == null) || (fields.getField_cnname().equals("")))
/*  45 */         field.setFrom_field_cnname("");
/*     */       else {
/*  47 */         field.setFrom_field_cnname(fields.getField_cnname());
/*     */       }
/*  49 */       result.add(field);
/*     */     }
/*  51 */     return result;
/*     */   }
/*     */ 
/*     */   public static List<Fields> getFormFieldsListByModelId(Map map)
/*     */   {
/*  60 */     List list = new ArrayList();
/*  61 */     List result = new ArrayList();
/*     */     try {
/*  63 */       list = FormDao.getFormFieldsListByModelId(map);
/*  64 */       result = listToResultFields(list);
/*     */     } catch (Exception e) {
/*  66 */       e.printStackTrace();
/*     */     } finally {
/*  68 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<Fields> getFormFieldsListByModelIdN3(Map map)
/*     */   {
/*  79 */     List list = new ArrayList();
/*  80 */     List result = new ArrayList();
/*  81 */     list = FormDao.getFormFieldsListByModelId(map);
/*  82 */     result = listToResultFields(list);
/*  83 */     return result;
/*     */   }
/*     */ 
/*     */   public static List<Fields> getFormFieldsListByModelIdN(Map map)
/*     */   {
/*  92 */     List list = new ArrayList();
/*  93 */     List result = new ArrayList();
/*     */ 
/*  96 */     if (mapLsit.containsKey(map)) {
/*  97 */       System.out.println("----getFormFieldsListByModelIdN----从缓存中得到");
/*  98 */       return (List)mapLsit.get(map);
/*     */     }
/*     */     try
/*     */     {
/* 102 */       list = FormDao.getFormFieldsListByModelId(map);
/* 103 */       result = listToResultFields(list);
/*     */ 
/* 106 */       mapLsit.put(map, result);
/*     */     }
/*     */     catch (Exception e) {
/* 109 */       e.printStackTrace();
/*     */     } finally {
/* 111 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getFormFieldsListByModelIdCount(Map map)
/*     */   {
/* 122 */     int count = 0;
/*     */     try {
/* 124 */       count = FormDao.getFormFieldsListByModelIdCount(map);
/*     */     } catch (Exception e) {
/* 126 */       e.printStackTrace();
/*     */     } finally {
/* 128 */       return count;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean updateForm(Map map)
/*     */   {
/*     */     try
/*     */     {
/* 136 */       String ids = (String)map.get("ids");
/* 137 */       String cids = (String)map.get("cids");
/* 138 */       String model_id = (String)map.get("model_id");
/*     */ 
/* 140 */       List addListtemp = Arrays.asList(cids.split(","));
/* 141 */       List addList = new ArrayList(addListtemp);
/* 142 */       List deleteListTemp = new ArrayList();
/* 143 */       List deleteList = new ArrayList(deleteListTemp);
/*     */ 
/* 147 */       List<String> idsList = Arrays.asList(ids.split(","));
/* 148 */       List cidsList = Arrays.asList(cids.split(","));
/* 149 */       for (String idsStr : idsList) {
/* 150 */         if ((idsStr != null) && (!"".equals(idsStr)))
/*     */         {
/* 153 */           if (!cidsList.contains(idsStr)) {
/* 154 */             System.out.println("idsStr=====" + idsStr);
/* 155 */             deleteList.add(idsStr);
/*     */           }
/*     */         }
/*     */       }
/* 159 */       addList.removeAll(idsList);
/*     */ 
/* 161 */       System.out.println("updateForm addList :: " + addList);
/* 162 */       System.out.println("updateForm deleteList :: " + deleteList);
/* 163 */       System.out.println("updateForm model_id :: " + model_id);
/*     */ 
/* 166 */       ModelBean modelBean = ModelManager.getModelBean(Integer.parseInt(model_id));
/* 167 */       String table_name = modelBean.getTable_name();
/*     */ 
/* 170 */       StringBuffer sbDelete = new StringBuffer();
/* 171 */       System.out.println("deleteList=====" + deleteList.size());
/* 172 */       if (deleteList.size() > 0) {
/* 173 */         Fields fields = null;
/* 174 */         for (int i = 0; i < deleteList.size(); i++) {
/* 175 */           if ((deleteList.get(i) != null) && (!"".equals(deleteList.get(i))))
/*     */           {
/* 178 */             if (i != deleteList.size() - 1)
/* 179 */               sbDelete.append((String)deleteList.get(i) + ",");
/*     */             else {
/* 181 */               sbDelete.append((String)deleteList.get(i));
/*     */             }
/*     */ 
/* 187 */             fields = FieldsService.getFieldById((String)deleteList.get(i));
/*     */ 
/* 189 */             Map hmParam = new HashMap();
/* 190 */             hmParam.put("tableName", table_name);
/* 191 */             hmParam.put("fieldName", fields.getField_enname());
/*     */ 
/* 193 */             FormUtil.deleteFieldDesc(hmParam);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 198 */         System.out.println("deleteFormFieldsByModeIdAndFromId mothed start");
/* 199 */         deleteFormFieldsByModeIdAndFromId(sbDelete.toString(), model_id);
/*     */       }
/*     */ 
/* 203 */       if (addList.size() > 0) {
/* 204 */         Fields fields = null;
/* 205 */         for (int i = 0; i < addList.size(); i++) {
/* 206 */           if ((addList.get(i) != null) && (!"".equals(addList.get(i))))
/*     */           {
/* 210 */             fields = FieldsService.getFieldById((String)addList.get(i));
/* 211 */             fields.setFrom_id(Integer.valueOf((String)addList.get(i)).intValue());
/* 212 */             fields.setModel_id(Integer.valueOf(model_id).intValue());
/* 213 */             fields.setField_sort(9999);
/* 214 */             fields.setAdd_time(DateUtil.getCurrentDateTime());
/* 215 */             FormDao.addFields(fields);
/*     */ 
/* 220 */             Map hmParam = new HashMap();
/* 221 */             hmParam.put("tableName", table_name);
/* 222 */             hmParam.put("fieldName", fields.getField_enname().toLowerCase());
/* 223 */             if (fields.getField_type().equals("0")) {
/* 224 */               hmParam.put("fieldSize", "2000");
/* 225 */               FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/* 226 */             } else if ((fields.getField_type().equals("1")) || (fields.getField_type().equals("2"))) {
/* 227 */               FormUtil.updateFieldDesc("addFieldLongtext", hmParam);
/* 228 */             } else if (fields.getField_type().equals("3")) {
/* 229 */               hmParam.put("fieldSize", "2000");
/* 230 */               FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/* 231 */             } else if (fields.getField_type().equals("4")) {
/* 232 */               hmParam.put("fieldSize", "20");
/* 233 */               FormUtil.updateFieldDesc("addFieldBigint", hmParam);
/* 234 */             } else if (fields.getField_type().equals("5")) {
/* 235 */               hmParam.put("fieldSize", "255");
/* 236 */               FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/* 237 */             } else if (fields.getField_type().equals("6")) {
/* 238 */               hmParam.put("fieldSize", "2000");
/* 239 */               FormUtil.updateFieldDesc("addFieldVarchar", hmParam);
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 246 */       mapLsit.clear();
/*     */ 
/* 248 */       return true;
/*     */     } catch (Exception e) {
/* 250 */       e.printStackTrace();
/* 251 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteFormFieldsByModeIdAndFromId(String ids, String model_id)
/*     */   {
/*     */     try
/*     */     {
/* 259 */       String[] id = ids.split(",");
/* 260 */       StringBuffer sb = new StringBuffer();
/* 261 */       for (int i = 0; i < id.length; i++) {
/* 262 */         String s_id = id[i];
/* 263 */         if ((s_id != null) && (!"".equals(s_id))) {
/* 264 */           if (i != id.length - 1)
/* 265 */             sb.append(s_id + ",");
/*     */           else {
/* 267 */             sb.append(s_id);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 273 */       mapLsit.clear();
/*     */ 
/* 275 */       return FormDao.deleteFormFieldsByModeIdAndFromId(sb.toString(), model_id);
/*     */     } catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveFormSort(String ids)
/*     */   {
/*     */     try
/*     */     {
/* 289 */       mapLsit.clear();
/*     */ 
/* 291 */       return FormDao.saveFormSort(ids);
/*     */     } catch (Exception e) {
/* 293 */       e.printStackTrace();
/* 294 */     }return false;
/*     */   }
/*     */ 
/*     */   public static Fields getFormFieldById(String id)
/*     */   {
/* 302 */     Fields fields = new Fields();
/*     */     try {
/* 304 */       fields = FormDao.getFormFieldById(id);
/* 305 */       String fieldXml = FieldsUtil.formatQuote(fields.getField_info());
/* 306 */       fields = FieldsUtil.getBeanByFieldXml(fields, fieldXml, fields.getField_type());
/*     */     } catch (Exception e) {
/* 308 */       e.printStackTrace();
/*     */     } finally {
/* 310 */       return fields;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean updateFormFieldsById(Fields fields)
/*     */   {
/*     */     try
/*     */     {
/* 323 */       String xmlFieldInfo = "";
/* 324 */       System.out.println("update 1 ----" + fields.toString());
/* 325 */       String field_type = FieldsUtil.formatQuote(fields.getField_type());
/* 326 */       if (field_type.equals("0"))
/*     */       {
/* 328 */         String field_maxnum = FieldsUtil.formatQuote(fields.getField_maxnum());
/* 329 */         String field_maxlength = FieldsUtil.formatQuote(fields.getField_maxlength());
/* 330 */         String validator = FieldsUtil.formatQuote(fields.getValidator());
/* 331 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 332 */         HashMap map = new HashMap();
/* 333 */         map.put("field_maxnum", field_maxnum);
/* 334 */         map.put("field_maxlength", field_maxlength);
/* 335 */         map.put("validator", validator);
/* 336 */         map.put("default_value", default_value);
/*     */ 
/* 339 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/*     */       }
/* 341 */       else if (field_type.equals("1"))
/*     */       {
/* 343 */         String width = FieldsUtil.formatQuote(fields.getWidth());
/* 344 */         String height = FieldsUtil.formatQuote(fields.getHeight());
/* 345 */         String field_maxnum = FieldsUtil.formatQuote(fields.getField_maxnum());
/* 346 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 347 */         HashMap map = new HashMap();
/* 348 */         map.put("width", width);
/* 349 */         map.put("height", height);
/* 350 */         map.put("field_maxnum", field_maxnum);
/* 351 */         map.put("default_value", default_value);
/*     */ 
/* 354 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/*     */       }
/* 356 */       else if (field_type.equals("2"))
/*     */       {
/* 358 */         String width = FieldsUtil.formatQuote(fields.getWidth());
/* 359 */         String height = FieldsUtil.formatQuote(fields.getHeight());
/* 360 */         String field_maxnum = FieldsUtil.formatQuote(fields.getField_maxnum());
/* 361 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 362 */         HashMap map = new HashMap();
/* 363 */         map.put("width", width);
/* 364 */         map.put("height", height);
/* 365 */         map.put("field_maxnum", field_maxnum);
/* 366 */         map.put("default_value", default_value);
/*     */ 
/* 369 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/* 370 */       } else if (field_type.equals("3"))
/*     */       {
/* 372 */         String select_item = FieldsUtil.formatQuote(fields.getSelect_item());
/* 373 */         String select_view = FieldsUtil.formatQuote(fields.getSelect_view());
/* 374 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 375 */         String data_type = FieldsUtil.formatQuote(fields.getData_type());
/* 376 */         String data_type_id = FieldsUtil.formatQuote(fields.getData_type_id());
/* 377 */         HashMap map = new HashMap();
/* 378 */         map.put("select_item", select_item);
/* 379 */         map.put("select_view", select_view);
/* 380 */         map.put("default_value", default_value);
/* 381 */         map.put("data_type", data_type);
/* 382 */         map.put("data_type_id", data_type_id);
/*     */ 
/* 384 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/* 385 */       } else if (field_type.equals("4"))
/*     */       {
/* 387 */         String min_num = FieldsUtil.formatQuote(fields.getMin_num());
/* 388 */         String max_num = FieldsUtil.formatQuote(fields.getMax_num());
/* 389 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 390 */         HashMap map = new HashMap();
/* 391 */         map.put("min_num", min_num);
/* 392 */         map.put("max_num", max_num);
/* 393 */         map.put("default_value", default_value);
/*     */ 
/* 396 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/* 397 */       } else if (field_type.equals("5"))
/*     */       {
/* 399 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 400 */         HashMap map = new HashMap();
/* 401 */         map.put("default_value", default_value);
/*     */ 
/* 404 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/*     */       }
/* 406 */       else if (field_type.equals("6"))
/*     */       {
/* 408 */         String default_value = FieldsUtil.formatQuote(fields.getDefault_value());
/* 409 */         HashMap map = new HashMap();
/* 410 */         map.put("default_value", default_value);
/*     */ 
/* 413 */         xmlFieldInfo = FieldsUtil.getFieldXml(map, field_type);
/*     */       }
/*     */ 
/* 416 */       fields.setField_info(xmlFieldInfo);
/* 417 */       System.out.println("update 2 ----" + fields.toString());
/*     */ 
/* 421 */       ModelBean modelBean = ModelManager.getModelBean(fields.getModel_id());
/*     */ 
/* 423 */       if ((field_type.equals("1")) || (field_type.equals("2")))
/*     */       {
/* 425 */         String table_column = "";
/* 426 */         Map hmParam = new HashMap();
/* 427 */         hmParam.put("tableName", modelBean.getTable_name());
/* 428 */         hmParam.put("fieldName", fields.getField_enname());
/* 429 */         FormManagerDAO.updateFieldDescByUpdate(hmParam);
/*     */       }
/* 431 */       if (field_type.equals("0"))
/*     */       {
/* 433 */         Map hmParam = new HashMap();
/* 434 */         hmParam.put("tableName", modelBean.getTable_name());
/* 435 */         hmParam.put("fieldName", fields.getField_enname());
/* 436 */         String field_maxnum = fields.getField_maxnum();
/* 437 */         hmParam.put("fieldSize", "4000");
/* 438 */         FormManagerDAO.updateFieldDescByUpdateVarchar(hmParam);
/*     */       }
/*     */ 
/* 442 */       mapLsit.clear();
/*     */ 
/* 444 */       return FormDao.updateFormFieldsById(fields);
/*     */     }
/*     */     catch (Exception e) {
/* 447 */       e.printStackTrace();
/* 448 */     }return false;
/*     */   }
/*     */ 
/*     */   public static int getFormFieldsListByFromIdsCount(String ids)
/*     */   {
/* 459 */     return FormDao.getFormFieldsListByFromIdsCount(ids.toString());
/*     */   }
/*     */ 
/*     */   public static boolean deleteFormFields(String ids, String model_id)
/*     */   {
/*     */     try
/*     */     {
/* 466 */       String[] id = ids.split(",");
/* 467 */       StringBuffer sb = new StringBuffer();
/* 468 */       for (int i = 0; i < id.length; i++) {
/* 469 */         String s_id = id[i];
/* 470 */         if ((s_id != null) && (!"".equals(s_id))) {
/* 471 */           if (i != id.length - 1)
/* 472 */             sb.append(s_id + ",");
/*     */           else {
/* 474 */             sb.append(s_id);
/*     */           }
/*     */ 
/* 479 */           ModelBean modelBean = ModelManager.getModelBean(Integer.parseInt(model_id));
/* 480 */           String table_name = modelBean.getTable_name();
/* 481 */           Fields fields = getFormFieldById(s_id);
/*     */ 
/* 483 */           Map hmParam = new HashMap();
/* 484 */           hmParam.put("tableName", table_name);
/* 485 */           hmParam.put("fieldName", fields.getField_enname());
/*     */ 
/* 487 */           FormUtil.deleteFieldDesc(hmParam);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 493 */       mapLsit.clear();
/*     */ 
/* 495 */       return FormDao.deleteFormFields(sb.toString());
/*     */     } catch (Exception e) {
/* 497 */       e.printStackTrace();
/* 498 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateFormFieldFlag(String field_flag, String action, String id)
/*     */   {
/*     */     try
/*     */     {
/* 505 */       FormDao.updateFormFieldFlag(field_flag, action, id);
/* 506 */       return true;
/*     */     } catch (Exception e) {
/* 508 */       e.printStackTrace();
/* 509 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] str)
/*     */   {
/* 514 */     Map map = new HashMap();
/* 515 */     map.put("ids", "");
/* 516 */     map.put("cids", "15,21,31");
/*     */ 
/* 518 */     updateForm(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FormService
 * JD-Core Version:    0.6.2
 */