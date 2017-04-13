/*     */ package com.cicro.wcm.services.appeal.model;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpLead.CpLeadBean;
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CPUserReleInfo;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelReleDept;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelReleUser;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.model.ModelDAO;
/*     */ import com.cicro.wcm.dao.appeal.sq.SQDAO;
/*     */ import com.cicro.wcm.services.appeal.cpDept.CpDeptManager;
/*     */ import com.cicro.wcm.services.appeal.cpDept.CpDeptManager.CpDeptComparator;
/*     */ import com.cicro.wcm.services.appeal.cpLead.CpLeadManager;
/*     */ import com.cicro.wcm.services.appeal.cpUser.CpUserManager;
/*     */ import com.cicro.wcm.services.appeal.sq.SQManager;
/*     */ import com.cicro.wcm.services.cms.workflow.WorkFlowManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class ModelManager
/*     */   implements ISyncCatch
/*     */ {
/*  43 */   private static TreeMap<Integer, ModelBean> model_map = new TreeMap();
/*  44 */   private static List<ModelReleDept> m_dept_list = new ArrayList();
/*  45 */   private static List<ModelReleUser> m_user_list = new ArrayList();
/*     */ 
/*     */   static {
/*  48 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  57 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  62 */     List model_list = ModelDAO.getAllModelList();
/*  63 */     model_map.clear();
/*  64 */     if ((model_list != null) && (model_list.size() > 0)) {
/*  65 */       for (int i = 0; i < model_list.size(); i++) {
/*  66 */         model_map.put(Integer.valueOf(((ModelBean)model_list.get(i)).getModel_id()), (ModelBean)model_list.get(i));
/*     */       }
/*     */     }
/*     */ 
/*  70 */     m_dept_list.clear();
/*  71 */     m_dept_list = ModelDAO.getModelReleDeptList();
/*     */ 
/*  73 */     m_user_list.clear();
/*  74 */     m_user_list = ModelDAO.getModelReleUserList();
/*     */   }
/*     */ 
/*     */   public static void reloadModel()
/*     */   {
/*  82 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.model.ModelManager");
/*     */   }
/*     */ 
/*     */   public static int getWFIDByMIDUserID(String model_id, String user_id)
/*     */   {
/*     */     try
/*     */     {
/*  94 */       ModelBean mb = getModelBean(Integer.parseInt(model_id));
/*  95 */       if (mb != null) {
/*  96 */         return WorkFlowManager.getMaxStepIDByUserID(mb.getWf_id(), user_id, "appeal", "");
/*     */       }
/*  98 */       return 0;
/*     */     }
/*     */     catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */     }return 0;
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getAllSQModelList()
/*     */   {
/* 113 */     List model_list = new ArrayList();
/* 114 */     Set set = model_map.keySet();
/*     */ 
/* 116 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 117 */       model_list.add((ModelBean)model_map.get(Integer.valueOf(i)));
/*     */     }
/* 119 */     return model_list;
/*     */   }
/*     */ 
/*     */   public static String getSQModelCount(Map<String, String> m)
/*     */   {
/* 129 */     return ModelDAO.getSQModelCount(m);
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getSQModelListForDB(Map<String, String> m)
/*     */   {
/* 139 */     return ModelDAO.getSQModelListForDB(m);
/*     */   }
/*     */ 
/*     */   public static String getModelNamebyIDS(String model_ids)
/*     */   {
/* 149 */     String names = "";
/* 150 */     if ((model_ids != null) && (!"".equals(model_ids)))
/*     */     {
/* 152 */       String[] tempA = model_ids.split(",");
/* 153 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 155 */         ModelBean mb = getModelBean(Integer.parseInt(tempA[i]));
/* 156 */         if (mb != null)
/*     */         {
/* 158 */           names = names + "," + mb.getModel_cname();
/*     */         }
/*     */       }
/* 161 */       if ((names != null) && (!"".equals(names.trim())))
/* 162 */         names = names.substring(1);
/*     */     }
/* 164 */     return names;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getModelDeptMapByMID(int model_id)
/*     */   {
/* 174 */     Map m = new HashMap();
/* 175 */     if ((m_dept_list != null) && (m_dept_list.size() > 0))
/*     */     {
/* 177 */       for (int i = 0; i < m_dept_list.size(); i++)
/*     */       {
/* 179 */         if (((ModelReleDept)m_dept_list.get(i)).getModel_id() == model_id)
/*     */         {
/* 181 */           CpDeptBean cdb = CpDeptManager.getCpDeptBean(((ModelReleDept)m_dept_list.get(i)).getDept_id());
/* 182 */           if (cdb != null)
/* 183 */             m.put(cdb.getDept_id(), cdb.getDept_name());
/*     */         }
/*     */       }
/*     */     }
/* 187 */     return m;
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBean(int model_id)
/*     */   {
/* 197 */     if (model_map.containsKey(Integer.valueOf(model_id)))
/*     */     {
/* 199 */       return (ModelBean)model_map.get(Integer.valueOf(model_id));
/*     */     }
/* 201 */     ModelBean ob = ModelDAO.getModelBean(model_id);
/* 202 */     if (ob != null)
/* 203 */       model_map.put(Integer.valueOf(model_id), ob);
/* 204 */     return ob;
/*     */   }
/*     */ 
/*     */   public static int getModelID()
/*     */   {
/* 214 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_MODEL_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertModel(ModelBean ob, String dept_ids, SettingLogsBean stl)
/*     */   {
/* 225 */     if (ModelDAO.insertModel(ob, stl))
/*     */     {
/* 227 */       reloadModel();
/* 228 */       insertModelReleDept(ob.getModel_id(), dept_ids);
/* 229 */       return true;
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(ModelBean ob, String dept_ids, SettingLogsBean stl)
/*     */   {
/* 243 */     if (ModelDAO.updateModel(ob, stl))
/*     */     {
/* 245 */       reloadModel();
/* 246 */       insertModelReleDept(ob.getModel_id(), dept_ids);
/* 247 */       return true;
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModel(String model_ids, SettingLogsBean stl)
/*     */   {
/* 261 */     if (ModelDAO.deleteModel(model_ids, stl))
/*     */     {
/* 263 */       reloadModel();
/* 264 */       SQDAO.deleteSQByModel(model_ids);
/* 265 */       CPFromManager.deleteCPFrom(model_ids);
/* 266 */       deleteModelReleDept(model_ids);
/* 267 */       deleteModelReleUserByModel(model_ids);
/* 268 */       return true;
/*     */     }
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */   public static void reloadModelDeptList()
/*     */   {
/* 282 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.model.ModelManager");
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getModelDeptListByMID(int model_id)
/*     */   {
/* 292 */     ModelBean mb = getModelBean(model_id);
/* 293 */     if (mb != null)
/*     */     {
/* 295 */       List cdList = new ArrayList();
/* 296 */       if ((m_dept_list != null) && (m_dept_list.size() > 0))
/*     */       {
/* 298 */         for (int i = 0; i < m_dept_list.size(); i++)
/*     */         {
/* 300 */           if (((ModelReleDept)m_dept_list.get(i)).getModel_id() == model_id)
/*     */           {
/* 302 */             if (mb.getRelevance_type() == 0)
/*     */             {
/* 304 */               CpDeptBean cdb = CpDeptManager.getCpDeptBean(((ModelReleDept)m_dept_list.get(i)).getDept_id());
/* 305 */               if (cdb != null)
/* 306 */                 cdList.add(cdb);
/*     */             }
/*     */             else {
/* 309 */               CpLeadBean lead = CpLeadManager.getCpLeadById(((ModelReleDept)m_dept_list.get(i)).getDept_id());
/* 310 */               if (lead != null)
/*     */               {
/* 313 */                 CpDeptBean cdb = new CpDeptBean();
/* 314 */                 cdb.setDept_id(lead.getDept_id());
/* 315 */                 cdb.setDept_name(lead.getLead_name());
/* 316 */                 cdb.setSort_id(lead.getSort_id());
/* 317 */                 cdb.setDept_memo(lead.getLead_memo());
/* 318 */                 cdList.add(cdb);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 323 */         Collections.sort(cdList, new CpDeptManager.CpDeptComparator());
/*     */       }
/* 325 */       return cdList;
/*     */     }
/* 327 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<CpLeadBean> getModelLeadListByMID(int model_id)
/*     */   {
/* 337 */     List l = new ArrayList();
/* 338 */     if ((m_dept_list != null) && (m_dept_list.size() > 0))
/*     */     {
/* 340 */       for (int i = 0; i < m_dept_list.size(); i++)
/*     */       {
/* 342 */         if (((ModelReleDept)m_dept_list.get(i)).getModel_id() == model_id)
/*     */         {
/* 344 */           CpLeadBean lead = CpLeadManager.getCpLeadById(((ModelReleDept)m_dept_list.get(i)).getDept_id());
/* 345 */           if (lead != null)
/* 346 */             l.add(lead);
/*     */         }
/*     */       }
/*     */     }
/* 350 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getModelDeptIDSByMID(int model_id)
/*     */   {
/* 360 */     String dept_ids = "";
/* 361 */     if ((m_dept_list != null) && (m_dept_list.size() > 0))
/*     */     {
/* 363 */       for (int i = 0; i < m_dept_list.size(); i++)
/*     */       {
/* 365 */         if (((ModelReleDept)m_dept_list.get(i)).getModel_id() == model_id)
/* 366 */           dept_ids = dept_ids + "," + ((ModelReleDept)m_dept_list.get(i)).getDept_id();
/*     */       }
/* 368 */       if (dept_ids.length() > 0)
/* 369 */         dept_ids = dept_ids.substring(1);
/*     */     }
/* 371 */     return dept_ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleDept(int model_id, String dept_ids)
/*     */   {
/* 382 */     Map m = new HashMap();
/* 383 */     m.put("model_ids", model_id);
/* 384 */     if (ModelDAO.deleteModelReleDept(m))
/*     */     {
/* 386 */       if ((dept_ids != null) && (!"".equals(dept_ids))) {
/*     */         try
/*     */         {
/* 389 */           String[] tempA = dept_ids.split(",");
/* 390 */           ModelReleDept drd = new ModelReleDept();
/* 391 */           drd.setModel_id(model_id);
/* 392 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 394 */             drd.setDept_id(Integer.parseInt(tempA[i]));
/* 395 */             ModelDAO.insertModelReleDept(drd);
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 399 */           e.printStackTrace();
/* 400 */           return false;
/*     */         }
/*     */       }
/* 403 */       reloadModelDeptList();
/* 404 */       return true;
/*     */     }
/* 406 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelReleDept(String model_ids)
/*     */   {
/* 411 */     Map m = new HashMap();
/* 412 */     m.put("model_ids", model_ids);
/* 413 */     if (ModelDAO.deleteModelReleDept(m))
/*     */     {
/* 415 */       reloadModelDeptList();
/* 416 */       return true;
/*     */     }
/* 418 */     return false;
/*     */   }
/*     */ 
/*     */   public static void reloadModelUserList()
/*     */   {
/* 430 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.model.ModelManager");
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getModelUserMapByMID(int model_id)
/*     */   {
/* 440 */     Map m = new HashMap();
/* 441 */     if ((m_dept_list != null) && (m_dept_list.size() > 0))
/*     */     {
/* 443 */       for (int i = 0; i < m_dept_list.size(); i++)
/*     */       {
/* 445 */         if (((ModelReleDept)m_dept_list.get(i)).getModel_id() == model_id)
/*     */         {
/* 447 */           UserBean ub = UserManager.getUserBeanByID(((ModelReleDept)m_dept_list.get(i)).getDept_id());
/* 448 */           if (ub != null)
/* 449 */             m.put(ub.getUser_id(), ub.getUser_realname());
/*     */         }
/*     */       }
/*     */     }
/* 453 */     return m;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getModelLeadMapByMID(int model_id)
/*     */   {
/* 463 */     Map m = new HashMap();
/* 464 */     if ((m_dept_list != null) && (m_dept_list.size() > 0))
/*     */     {
/* 466 */       for (int i = 0; i < m_dept_list.size(); i++)
/*     */       {
/* 468 */         if (((ModelReleDept)m_dept_list.get(i)).getModel_id() == model_id)
/*     */         {
/* 470 */           CpLeadBean lead = CpLeadManager.getCpLeadById(((ModelReleDept)m_dept_list.get(i)).getDept_id());
/* 471 */           if (lead != null)
/* 472 */             m.put(lead.getLead_id(), lead.getLead_name());
/*     */         }
/*     */       }
/*     */     }
/* 476 */     return m;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getAdminUserByMID(int model_id)
/*     */   {
/* 486 */     List uList = new ArrayList();
/* 487 */     if ((m_user_list != null) && (m_user_list.size() > 0))
/*     */     {
/* 489 */       for (int i = 0; i < m_user_list.size(); i++)
/*     */       {
/* 491 */         if ((((ModelReleUser)m_user_list.get(i)).getModel_id() == model_id) && (SQManager.isAppealManager(((ModelReleUser)m_user_list.get(i)).getUser_id())))
/* 492 */           uList.add(UserManager.getUserBeanByID(((ModelReleUser)m_user_list.get(i)).getUser_id()));
/*     */       }
/*     */     }
/* 495 */     return uList;
/*     */   }
/*     */ 
/*     */   public static String getModelUserIDSByMID(int model_id)
/*     */   {
/* 505 */     String user_ids = "";
/* 506 */     if ((m_user_list != null) && (m_user_list.size() > 0))
/*     */     {
/* 508 */       for (int i = 0; i < m_user_list.size(); i++)
/*     */       {
/* 510 */         if (((ModelReleUser)m_user_list.get(i)).getModel_id() == model_id)
/* 511 */           user_ids = user_ids + "," + ((ModelReleUser)m_user_list.get(i)).getUser_id();
/*     */       }
/* 513 */       if (user_ids.length() > 0)
/* 514 */         user_ids = user_ids.substring(1);
/*     */     }
/* 516 */     return user_ids;
/*     */   }
/*     */ 
/*     */   public static String getModelIDSByUserID(int user_id)
/*     */   {
/* 526 */     String model_ids = "";
/* 527 */     if ((m_user_list != null) && (m_user_list.size() > 0))
/*     */     {
/* 529 */       for (int i = 0; i < m_user_list.size(); i++)
/*     */       {
/* 531 */         if (((ModelReleUser)m_user_list.get(i)).getUser_id() == user_id)
/* 532 */           model_ids = model_ids + "," + ((ModelReleUser)m_user_list.get(i)).getModel_id();
/*     */       }
/* 534 */       if (model_ids.length() > 0)
/* 535 */         model_ids = model_ids.substring(1);
/*     */     }
/* 537 */     if ("".equals(model_ids))
/* 538 */       model_ids = "0";
/* 539 */     return model_ids;
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getModelListSByUserID(int user_id)
/*     */   {
/* 549 */     List m_list = new ArrayList();
/* 550 */     if ((m_user_list != null) && (m_user_list.size() > 0))
/*     */     {
/* 552 */       for (int i = 0; i < m_user_list.size(); i++)
/*     */       {
/* 554 */         if (((ModelReleUser)m_user_list.get(i)).getUser_id() == user_id)
/*     */         {
/* 556 */           m_list.add((ModelBean)model_map.get(Integer.valueOf(((ModelReleUser)m_user_list.get(i)).getModel_id())));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 561 */     return m_list;
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleUser(String model_ids, int user_id, SettingLogsBean stl)
/*     */   {
/* 571 */     if (ModelDAO.insertModelReleUser(model_ids, user_id, stl))
/*     */     {
/* 573 */       reloadModelUserList();
/* 574 */       return true;
/*     */     }
/*     */ 
/* 577 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleUserByModel(int model_id, String user_ids, SettingLogsBean stl)
/*     */   {
/* 587 */     if (ModelDAO.insertModelReleUserByModel(model_id, user_ids, stl))
/*     */     {
/* 589 */       reloadModelUserList();
/* 590 */       return true;
/*     */     }
/*     */ 
/* 593 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelReleUser(String user_id)
/*     */   {
/* 603 */     Map m = new HashMap();
/* 604 */     m.put("user_ids", user_id);
/* 605 */     if (ModelDAO.deleteModelReleUser(m))
/*     */     {
/* 607 */       reloadModelUserList();
/* 608 */       return true;
/*     */     }
/*     */ 
/* 611 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelReleUserByModel(String model_ids)
/*     */   {
/* 617 */     Map m = new HashMap();
/* 618 */     m.put("model_ids", model_ids);
/* 619 */     if (ModelDAO.deleteModelReleUser(m))
/*     */     {
/* 621 */       reloadModelUserList();
/* 622 */       return true;
/*     */     }
/*     */ 
/* 625 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getUserPhoneByModelID(int model_id)
/*     */   {
/* 636 */     String phones = "";
/* 637 */     List ul = getAdminUserByMID(model_id);
/* 638 */     if ((ul != null) && (ul.size() > 0))
/*     */     {
/* 640 */       for (UserBean ub : ul)
/*     */       {
/* 642 */         if ((ub.getPhone() != null) && (!"".equals(ub.getPhone()))) {
/* 643 */           phones = ";" + ub.getPhone();
/*     */         }
/*     */       }
/* 646 */       if ((phones != null) && (!"".equals(phones)))
/*     */       {
/* 648 */         phones = phones.substring(1);
/*     */       }
/*     */     }
/* 651 */     return phones;
/*     */   }
/*     */ 
/*     */   public static String getUserPhoneByDeptID(int dept_id, int model_id)
/*     */   {
/* 662 */     String phones = "";
/*     */ 
/* 664 */     List cul = CpUserManager.getUserListByDept(dept_id);
/* 665 */     if ((cul != null) && (cul.size() > 0))
/*     */     {
/* 667 */       String model_user = "," + getModelUserIDSByMID(model_id) + ",";
/* 668 */       for (CPUserReleInfo cub : cul)
/*     */       {
/* 670 */         if (model_user.contains("," + cub.getUser_id() + ","))
/*     */         {
/* 672 */           String phone = UserManager.getUserBeanByID(cub.getUser_id()).getPhone();
/* 673 */           if ((phone != null) && (!"".equals(phone)))
/* 674 */             phones = ";" + phone;
/*     */         }
/*     */       }
/* 677 */       if ((phones != null) && (!"".equals(phones)))
/*     */       {
/* 679 */         phones = phones.substring(1);
/*     */       }
/*     */     }
/* 682 */     return phones;
/*     */   }
/*     */ 
/*     */   public static String getModelTemplate(String template_type)
/*     */   {
/* 695 */     Set s = model_map.keySet();
/* 696 */     for (Iterator localIterator = s.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 698 */       ModelBean mb = (ModelBean)model_map.get(Integer.valueOf(i));
/* 699 */       if ("form".equals(template_type))
/*     */       {
/* 701 */         if (mb.getTemplate_form() != 0) {
/* 702 */           return mb.getTemplate_form();
/*     */         }
/*     */       }
/* 705 */       if ("list".equals(template_type))
/*     */       {
/* 707 */         if (mb.getTemplate_list() != 0) {
/* 708 */           return mb.getTemplate_list();
/*     */         }
/*     */       }
/* 711 */       if ("content".equals(template_type))
/*     */       {
/* 713 */         if (mb.getTemplate_content() != 0) {
/* 714 */           return mb.getTemplate_content();
/*     */         }
/*     */       }
/* 717 */       if ("print".equals(template_type))
/*     */       {
/* 719 */         if (mb.getTemplate_print() != 0) {
/* 720 */           return mb.getTemplate_print();
/*     */         }
/*     */       }
/* 723 */       if ("search".equals(template_type))
/*     */       {
/* 725 */         if (mb.getTemplate_search_list() != 0)
/* 726 */           return mb.getTemplate_search_list();
/*     */       }
/*     */     }
/* 729 */     return "";
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 733 */     System.out.println(getAdminUserByMID(25));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.model.ModelManager
 * JD-Core Version:    0.6.2
 */