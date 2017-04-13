/*     */ package com.cicro.wcm.bean.system.formodel;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ModelBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -9074766779513834348L;
/*     */   private int model_id;
/*   9 */   private String model_ename = "";
/*  10 */   private String model_name = "";
/*  11 */   private String table_name = "";
/*  12 */   private int model_sort = 999;
/*  13 */   private String template_list = "";
/*  14 */   private String template_show = "";
/*  15 */   private String model_icon = "";
/*  16 */   private String add_page = "";
/*  17 */   private String view_page = "";
/*  18 */   private String model_type = "0";
/*     */ 
/*  36 */   private String app_id = "";
/*  37 */   private int disabled = 0;
/*     */ 
/* 118 */   private String model_memo = "";
/*     */ 
/*     */   public String getAdd_page()
/*     */   {
/*  21 */     return this.add_page;
/*     */   }
/*     */ 
/*     */   public void setAdd_page(String addPage) {
/*  25 */     this.add_page = addPage;
/*     */   }
/*     */ 
/*     */   public String getView_page() {
/*  29 */     return this.view_page;
/*     */   }
/*     */ 
/*     */   public void setView_page(String viewPage) {
/*  33 */     this.view_page = viewPage;
/*     */   }
/*     */ 
/*     */   public String getModel_icon()
/*     */   {
/*  40 */     return this.model_icon;
/*     */   }
/*     */ 
/*     */   public void setModel_icon(String modelIcon) {
/*  44 */     this.model_icon = modelIcon;
/*     */   }
/*     */   public String getModel_ename() {
/*  47 */     return this.model_ename;
/*     */   }
/*     */ 
/*     */   public void setModel_ename(String modelEname) {
/*  51 */     this.model_ename = modelEname;
/*     */   }
/*     */ 
/*     */   public String getModel_name() {
/*  55 */     return this.model_name;
/*     */   }
/*     */ 
/*     */   public void setModel_name(String modelName) {
/*  59 */     this.model_name = modelName;
/*     */   }
/*     */ 
/*     */   public String getTable_name() {
/*  63 */     return this.table_name;
/*     */   }
/*     */ 
/*     */   public void setTable_name(String tableName) {
/*  67 */     this.table_name = tableName;
/*     */   }
/*     */ 
/*     */   public int getModel_sort() {
/*  71 */     return this.model_sort;
/*     */   }
/*     */ 
/*     */   public void setModel_sort(int modelSort) {
/*  75 */     this.model_sort = modelSort;
/*     */   }
/*     */ 
/*     */   public String getTemplate_list() {
/*  79 */     return this.template_list;
/*     */   }
/*     */ 
/*     */   public void setTemplate_list(String templateList) {
/*  83 */     this.template_list = templateList;
/*     */   }
/*     */ 
/*     */   public String getTemplate_show() {
/*  87 */     return this.template_show;
/*     */   }
/*     */ 
/*     */   public void setTemplate_show(String templateShow) {
/*  91 */     this.template_show = templateShow;
/*     */   }
/*     */ 
/*     */   public String getApp_id() {
/*  95 */     return this.app_id;
/*     */   }
/*     */ 
/*     */   public void setApp_id(String appId) {
/*  99 */     this.app_id = appId;
/*     */   }
/*     */ 
/*     */   public int getDisabled() {
/* 103 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(int disabled) {
/* 107 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public String getModel_memo() {
/* 111 */     return this.model_memo;
/*     */   }
/*     */ 
/*     */   public void setModel_memo(String modelMemo) {
/* 115 */     this.model_memo = modelMemo;
/*     */   }
/*     */ 
/*     */   public int getModel_id()
/*     */   {
/* 121 */     return this.model_id;
/*     */   }
/*     */ 
/*     */   public void setModel_id(int modelId) {
/* 125 */     this.model_id = modelId;
/*     */   }
/*     */ 
/*     */   public String getModel_type() {
/* 129 */     return this.model_type;
/*     */   }
/*     */ 
/*     */   public void setModel_type(String modelType) {
/* 133 */     this.model_type = modelType;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.system.formodel.ModelBean
 * JD-Core Version:    0.6.2
 */