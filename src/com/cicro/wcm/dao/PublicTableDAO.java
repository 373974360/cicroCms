/*     */ package com.cicro.wcm.dao;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PublicTableDAO
/*     */ {
/*  45 */   public static String APP_TABLE_NAME = "cs_org_app";
/*     */ 
/*  47 */   public static String DEPT_TABLE_NAME = "cs_org_dept";
/*     */ 
/*  49 */   public static String DEPTLEVEL_TABLE_NAME = "cs_org_deptlevel";
/*     */ 
/*  51 */   public static String DEPTMANAGER_TABLE_NAME = "cs_org_dept_manager";
/*     */ 
/*  53 */   public static String USER_TABLE_NAME = "cs_org_user";
/*     */ 
/*  55 */   public static String USERLEVEL_TABLE_NAME = "cs_org_userlevel";
/*     */ 
/*  57 */   public static String USERREGISTER_TABLE_NAME = "cs_org_register";
/*     */ 
/*  59 */   public static String GROUP_TABLE_NAME = "cs_org_group";
/*     */ 
/*  61 */   public static String GROUPUSER_TABLE_NAME = "cs_org_group_user";
/*     */ 
/*  63 */   public static String ROLE_TABLE_NAME = "cs_org_role";
/*     */ 
/*  65 */   public static String ROLEAPP_TABLE_NAME = "cs_org_role_app";
/*     */ 
/*  67 */   public static String ROLEUSER_TABLE_NAME = "cs_org_user_role";
/*     */ 
/*  69 */   public static String ROLEGROUP_TABLE_NAME = "cs_org_group_role";
/*     */ 
/*  71 */   public static String ROLEOPT_TABLE_NAME = "cs_org_role_opt";
/*     */ 
/*  73 */   public static String OPT_TABLE_NAME = "cs_org_opt";
/*     */ 
/*  75 */   public static String MENU_TABLE_NAME = "cs_org_menu";
/*     */ 
/*  77 */   public static String DESKTOP_TABLE_NAME = "cs_user_desktop";
/*     */ 
/*  81 */   public static String SITEGROUP_TABLE_NAME = "cs_site_group";
/*     */ 
/*  83 */   public static String SITESERVER_TABLE_NAME = "cs_site_server";
/*     */ 
/*  85 */   public static String SITE_TABLE_NAME = "cs_site";
/*     */ 
/*  87 */   public static String SITEDOMAIN_TABLE_NAME = "cs_site_domain";
/*     */ 
/*  89 */   public static String SITECONFIG_TABLE_NAME = "cs_site_config";
/*     */ 
/*  91 */   public static String SITEAPP_TABLE_NAME = "cs_site_app";
/*     */ 
/*  93 */   public static String SITECOUNT_TABLE_NAME = "cs_site_count";
/*     */ 
/*  97 */   public static String METADATA_TABLE_NAME = "cs_info_meta";
/*     */ 
/*  99 */   public static String FORMODEL_TABLE_NAME = "cs_info_model";
/*     */ 
/* 101 */   public static String FORMODELFIELD_TABLE_NAME = "cs_info_field";
/*     */ 
/* 103 */   public static String SYS_DICT_CATEGORY_TABLE_NAME = "cs_sys_dict_category";
/*     */ 
/* 105 */   public static String SYS_DICT_TABLE_NAME = "cs_sys_dict";
/*     */ 
/* 107 */   public static String SYS_CONFIG_TABLE_NAME = "cs_sys_config";
/*     */ 
/* 109 */   public static String HOTWORD_TABLE_NAME = "cs_info_hotword";
/*     */ 
/* 111 */   public static String TAGS_TABLE_NAME = "cs_info_tags";
/*     */ 
/* 113 */   public static String AUTHOR_TABLE_NAME = "cs_info_author";
/*     */ 
/* 115 */   public static String SOURCE_TABLE_NAME = "cs_info_source";
/*     */ 
/* 117 */   public static String COMMENT_TABLE_NAME = "cs_comment";
/*     */ 
/* 119 */   public static String WARE_CATEGORY_TABLE_NAME = "cs_ware_category";
/*     */ 
/* 121 */   public static String WARE_TABLE_NAME = "cs_ware";
/*     */ 
/* 123 */   public static String WARE_INFO_TABLE_NAME = "cs_ware_info";
/*     */ 
/* 125 */   public static String WARE_INFOS_TABLE_NAME = "cs_ware_infos";
/*     */ 
/* 127 */   public static String WARE_INFO_REF_TABLE_NAME = "cs_info_ref";
/*     */ 
/* 129 */   public static String WARE_RELE_USER_TABLE_NAME = "cs_wcat_priv";
/*     */ 
/* 133 */   public static String TEMPLATE_TABLE_NAME = "cs_template";
/*     */ 
/* 135 */   public static String TEMPLATE_CLASS_TABLE_NAME = "cs_template_class";
/*     */ 
/* 137 */   public static String TEMPLATE_CATEGORY_TABLE_NAME = "cs_template_category";
/*     */ 
/* 139 */   public static String TEMPLATE_EDIT_TABLE_NAME = "cs_template_edit";
/*     */ 
/* 141 */   public static String TEMPLATE_VER_TABLE_NAME = "cs_template_ver";
/*     */ 
/* 143 */   public static String DESIGN_CSS_TABLE_NAME = "cs_design_css";
/* 144 */   public static String DESIGN_LAYOUT_TABLE_NAME = "cs_design_layout";
/* 145 */   public static String DESIGN_MODULE_TABLE_NAME = "cs_design_module";
/* 146 */   public static String DESIGN_STYLE_TABLE_NAME = "cs_design_style";
/* 147 */   public static String DESIGN_FRAME_TABLE_NAME = "cs_design_frame";
/* 148 */   public static String DESIGN_CASE_TABLE_NAME = "cs_design_case";
/* 149 */   public static String DESIGN_CATEGORY_TABLE_NAME = "cs_design_category";
/*     */ 
/* 153 */   public static String WORKFLOW_TABLE_NAME = "cs_workflow";
/*     */ 
/* 155 */   public static String WORKFLOW_STEP_TABLE_NAME = "cs_workflow_step";
/*     */ 
/* 157 */   public static String WORKFLOW_LOG_TABLE_NAME = "cs_workflow_log";
/*     */ 
/* 159 */   public static String WORKFLOW_STATUS_TABLE_NAME = "cs_info_status";
/*     */ 
/* 161 */   public static String INFO_CLASS_TABLE_NAME = "cs_info_class";
/*     */ 
/* 163 */   public static String INFO_CATEGORY_TABLE_NAME = "cs_info_category";
/*     */ 
/* 165 */   public static String INFO_CATEGORY_REGU_TABLE_NAME = "cs_info_category_regu";
/*     */ 
/* 167 */   public static String INFO_CATEGORY_MODEL_TABLE_NAME = "cs_info_category_model";
/*     */ 
/* 169 */   public static String INFO_ZT_CATEGORY_TABLE_NAME = "cs_zt_category";
/*     */ 
/* 171 */   public static String INFO_TABLE_NAME = "cs_info";
/*     */ 
/* 173 */   public static String INFO_DIGG_LOG_TABLE_NAME = "cs_info_digg_log";
/*     */ 
/* 175 */   public static String INFO_INFO_TABLE_NAME = "cs_info_info";
/*     */ 
/* 177 */   public static String INFO_ARTICLE_CATEGORY_TABLE_NAME = "cs_info_article";
/*     */ 
/* 179 */   public static String INFO_PIC_TABLE_NAME = "cs_info_pic";
/*     */ 
/* 181 */   public static String INFO_VIDEO_TABLE_NAME = "cs_info_video";
/*     */ 
/* 185 */   public static String INFO_UDEFINED_TABLE_NAME = "cs_info_udefined";
/*     */ 
/* 187 */   public static String SETTINGLOGS_TABLE_NAME = "cs_log_setting";
/*     */ 
/* 189 */   public static String LOGINLOGS_TABLE_NAME = "cs_log_login";
/*     */ 
/* 191 */   public static String FILTERWORD_TABLE_NAME = "cs_sys_filterword";
/*     */ 
/* 195 */   public static String MEMBER_CATEGORY_TABLE_NAME = "cs_member_category";
/*     */ 
/* 197 */   public static String MEMBER_TABLE_NAME = "cs_member";
/*     */ 
/* 199 */   public static String MEMBER_REGISTER_TABLE_NAME = "cs_member_register";
/*     */ 
/* 203 */   public static String INTERVIEW_SCATEGORY_TABLE_NAME = "cp_scategory";
/*     */ 
/* 205 */   public static String INTERVIEW_SUBJECT_TABLE_NAME = "cp_subject";
/*     */ 
/* 207 */   public static String INTERVIEW_RESOUSE_TABLE_NAME = "cp_resouse";
/*     */ 
/* 209 */   public static String INTERVIEW_ACTOR_TABLE_NAME = "cp_actor";
/*     */ 
/* 211 */   public static String INTERVIEW_RELEINFO_TABLE_NAME = "cp_releinfo";
/*     */ 
/* 213 */   public static String INTERVIEW_CHAT_TABLE_NAME = "cp_chat";
/*     */ 
/* 217 */   public static String SURVEY_CATEGORY_TABLE_NAME = "cp_survey_category";
/*     */ 
/* 219 */   public static String SURVEY_TABLE_NAME = "cp_survey";
/*     */ 
/* 221 */   public static String SURVEY_SUB_TABLE_NAME = "cp_survey_sub";
/*     */ 
/* 223 */   public static String SURVEY_ITEM_TABLE_NAME = "cp_survey_item";
/*     */ 
/* 225 */   public static String SURVEY_CHILD_TABLE_NAME = "cp_survey_child";
/*     */ 
/* 227 */   public static String SURVEY_ANSWER_TABLE_NAME = "cp_survey_answer";
/*     */ 
/* 229 */   public static String SURVEY_ANSWER_ITEM_TABLE_NAME = "cp_survey_answer_item";
/*     */ 
/* 233 */   public static String MateInfo_TABLE_NAME = "cs_attachment";
/*     */ 
/* 235 */   public static String MateFolder_TABLE_NAME = "cs_attachment_folder";
/*     */ 
/* 239 */   public static String APPEAL_AREA_TABLE_NAME = "cp_area";
/*     */ 
/* 241 */   public static String APPEAL_CALENDAR_TABLE_NAME = "cp_calendar";
/*     */ 
/* 243 */   public static String APPEAL_MODEL_TABLE_NAME = "cp_model";
/*     */ 
/* 245 */   public static String APPEAL_SQ_TABLE_NAME = "cp_sq";
/*     */ 
/* 247 */   public static String APPEAL_PROCESS_TABLE_NAME = "cp_process";
/*     */ 
/* 249 */   public static String APPEAL_DEPT_TABLE_NAME = "cp_dept";
/*     */ 
/* 251 */   public static String APPEAL_USER_TABLE_NAME = "cp_user";
/*     */ 
/* 253 */   public static String APPEAL_Lead_TABLE_NAME = "cp_lead";
/*     */ 
/* 255 */   public static String APPEAL_CATEGORY_TABLE_NAME = "cp_category";
/*     */ 
/* 257 */   public static String APPEAL_PHRASAL_TABLE_NAME = "cp_phrasal";
/*     */ 
/* 259 */   public static String APPEAL_SATISFATION_TABLE_NAME = "cp_satisfaction";
/*     */ 
/* 261 */   public static String APPEAL_SATRECORD_TABLE_NAME = "cp_sat_record";
/*     */ 
/* 263 */   public static String APPEAL_SQCUSTOM_TABLE_NAME = "cp_sq_custom";
/*     */ 
/* 267 */   public static String APP_ORG = "org";
/*     */ 
/* 269 */   public static String APP_SYSTEM = "system";
/*     */ 
/* 271 */   public static String APP_CONTROL = "control";
/*     */ 
/* 273 */   public static String APP_CMS = "cms";
/*     */ 
/* 275 */   public static String APP_INFODIR = "infodir";
/*     */ 
/* 277 */   public static String APP_ZWGK = "zwgk";
/*     */ 
/* 279 */   public static String APP_STATISTICS = "statistics";
/*     */ 
/* 283 */   public static String GK_NODE_CATEGORY = "cs_gk_node_category";
/*     */ 
/* 285 */   public static String GK_NODE = "cs_gk_node";
/*     */ 
/* 287 */   public static String GK_INDEX_SEQUENCE = "cs_gk_sequence";
/*     */ 
/* 289 */   public static String INFO_RESFILE_TABLE_NAME = "cs_gk_resfile";
/*     */ 
/* 291 */   public static String SER_CATEGORY_TABLE_NAME = "cs_ser_category";
/*     */ 
/* 293 */   public static String SER_RESOUCE_TABLE_NAME = "cs_ser_resouce";
/*     */ 
/* 295 */   public static String GK_APPCATALOG_TABLE_NAME = "cs_gk_app_catalog";
/*     */ 
/* 297 */   public static String GK_APPREGU_TABLE_NAME = "cs_gk_app_regu";
/*     */ 
/* 299 */   public static String YSQGK_CONFIG_TABLE_NAME = "cs_gk_ysq_config";
/* 300 */   public static String YSQGK_INFO_TABLE_NAME = "cs_gk_ysq";
/* 301 */   public static String YSQGK_PHRASAL_TABLE_NAME = "cs_gk_phrasal";
/*     */ 
/* 305 */   public static String APPEAL_SNIPPET_TABLE_NAME = "cs_snippet";
/*     */ 
/* 309 */   public static String PAGE_TABLE_NAME = "cs_page";
/*     */ 
/* 313 */   public static String DZ_CHAXUN_CONF_NAME = "cs_dz_chaxun_conf";
/* 314 */   public static String DZ_CHAXUN_DIC_NAME = "cs_dz_chaxun_dic";
/* 315 */   public static String DZ_CHAXUN_ITEM_NAME = "cs_dz_chaxun_item";
/*     */ 
/* 318 */   public static String MINGLU_TEMPLATE_TABLE_NAME = "cs_org_minlu_template";
/*     */ 
/* 321 */   public static String ERROR_TABLE_NAME = "cs_err_report";
/*     */ 
/* 324 */   public static String GUESTBOOKSUB__TABLE_NAME = "cs_guestbook_sub";
/* 325 */   public static String GUESTBOOK__TABLE_NAME = "cs_guestbook";
/* 326 */   public static String GUESTBOOK_CATEGORY_TABLE_NAME = "cs_guestbook_cat";
/* 327 */   public static String GUESTBOOK_CLASS_TABLE_NAME = "cs_guestbook_class";
/*     */ 
/* 330 */   public static String COMSET_TABLE_NAME = "cs_comment_set";
/* 331 */   public static String COMMENT_MAIN_TABLE_NAME = "cs_comment_main";
/*     */ 
/* 334 */   public static String AccessInfoCount_TABLE_NAME = "cs_info_access";
/*     */ 
/* 337 */   public static String RECEIVE_CONFIG_TABLE_NAME = "cs_receive_config";
/* 338 */   public static String RECEIVE_CAT_TABLE_NAME = "cs_receive_cat";
/* 339 */   public static String SEND_CONFIG_TABLE_NAME = "cs_send_config";
/* 340 */   public static String RECEIVE_INFO_TABLE_NAME = "cs_receive_info";
/* 341 */   public static String SEND_RECORD_TABLE_NAME = "cs_send_record";
/*     */ 
/*     */   public static synchronized int getIDByTableName(String table_name)
/*     */   {
/* 358 */     String id = DBManager.getString("getSequence", table_name);
/*     */ 
/* 360 */     if ((id == null) || ("".equals(id)) || ("null".equals(id)))
/*     */     {
/* 364 */       Map m = new HashMap();
/*     */ 
/* 366 */       m.put("table_name", table_name);
/*     */ 
/* 368 */       m.put("seq", Integer.valueOf(1));
/*     */ 
/* 370 */       DBManager.insert("insert_sequence", m);
/*     */ 
/* 372 */       return 1;
/*     */     }
/*     */ 
/* 378 */     int id_n = Integer.parseInt(id) + 1;
/*     */ 
/* 380 */     DBManager.update("update_sequence", table_name);
/*     */ 
/* 382 */     return id_n;
/*     */   }
/*     */ 
/*     */   public static boolean insertSettingLogs(String oper_type, String oper_name, String ids, SettingLogsBean stl)
/*     */   {
/* 403 */     if (stl != null)
/*     */     {
/* 405 */       stl.setAudit_des("[" + oper_type + "]" + oper_name + "ID为： " + ids + 
/* 406 */         " 的数据");
/*     */ 
/* 408 */       stl.setAudit_id(getIDByTableName(SETTINGLOGS_TABLE_NAME));
/*     */ 
/* 410 */       stl.setAudit_time(DateUtil.getCurrentDateTime());
/*     */ 
/* 412 */       return DBManager.insert("insert_setting_logs", stl);
/*     */     }
/* 414 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean createSql(Map map)
/*     */   {
/*     */     try
/*     */     {
/* 432 */       DBManager.update("initSql_createSql", map);
/*     */ 
/* 434 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 438 */       e.printStackTrace();
/*     */     }
/* 440 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getTable(Map map)
/*     */   {
/*     */     try
/*     */     {
/* 461 */       return DBManager.getString("initSql_getCount", map);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 465 */       e.printStackTrace();
/*     */     }
/* 467 */     return "-1";
/*     */   }
/*     */ 
/*     */   public static List executeSearchSql(String sql)
/*     */   {
/* 484 */     Map m = new HashMap();
/* 485 */     m.put("sql", sql);
/* 486 */     return DBManager.queryFList("getSearchSql", m);
/*     */   }
/*     */ 
/*     */   public static boolean executeDynamicSql(String sql)
/*     */   {
/* 499 */     Map m = new HashMap();
/* 500 */     m.put("sql", sql);
/* 501 */     if (sql.startsWith("insert"))
/*     */     {
/* 503 */       return DBManager.insert("insert_dynamic_sql", m);
/*     */     }
/* 505 */     if ((sql.startsWith("update")) || (sql.startsWith("alter")) || (sql.startsWith("create")))
/*     */     {
/* 507 */       return DBManager.update("initSql_createSql", m);
/*     */     }
/* 509 */     if (sql.startsWith("delete"))
/*     */     {
/* 511 */       return DBManager.update("delete_dynamic_sql", m);
/*     */     }
/* 513 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 518 */     System.out.println(executeSearchSql("select * from cs_info").get(0));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.PublicTableDAO
 * JD-Core Version:    0.6.2
 */