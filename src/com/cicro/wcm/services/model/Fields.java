/*     */ package com.cicro.wcm.services.model;
/*     */ 
/*     */ import com.cicro.wcm.services.model.services.FieldsUtil;
/*     */ 
/*     */ public class Fields
/*     */ {
/*     */   private int id;
/*     */   private int from_id;
/*     */   private int model_id;
/*     */   private String field_enname;
/*     */   private String field_cnname;
/*     */   private String field_type;
/*     */   private String is_sys;
/*     */   private String is_null;
/*     */   private String is_display;
/*     */   private String field_text;
/*     */   private String field_info;
/*     */   private String add_time;
/*     */   private int field_sort;
/*     */   private String field_maxnum;
/*     */   private String field_maxlength;
/*     */   private String validator;
/*     */   private String width;
/*     */   private String height;
/*     */   private String htmledit_type;
/*     */   private String default_value;
/*     */   private String select_item;
/*     */   private String select_view;
/*     */   private String max_num;
/*     */   private String min_num;
/*     */   private String data_type;
/*     */   private String data_type_id;
/*     */   private String from_field_cnname;
/*  42 */   private String field_flag = "";
/*  43 */   private String field_flag2 = "";
/*     */ 
/*     */   public int getId() {
/*  46 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  49 */     this.id = id;
/*     */   }
/*     */   public String getField_enname() {
/*  52 */     return FieldsUtil.formatQuote(this.field_enname);
/*     */   }
/*     */   public void setField_enname(String fieldEnname) {
/*  55 */     this.field_enname = fieldEnname;
/*     */   }
/*     */   public String getField_cnname() {
/*  58 */     return FieldsUtil.formatQuote(this.field_cnname);
/*     */   }
/*     */   public void setField_cnname(String fieldCnname) {
/*  61 */     this.field_cnname = fieldCnname;
/*     */   }
/*     */   public String getField_type() {
/*  64 */     return FieldsUtil.formatQuote(this.field_type);
/*     */   }
/*     */   public void setField_type(String fieldType) {
/*  67 */     this.field_type = FieldsUtil.formatQuote(fieldType);
/*     */   }
/*     */   public String getIs_sys() {
/*  70 */     return this.is_sys;
/*     */   }
/*     */   public void setIs_sys(String isSys) {
/*  73 */     this.is_sys = FieldsUtil.formatQuote(isSys);
/*     */   }
/*     */   public String getIs_null() {
/*  76 */     return this.is_null;
/*     */   }
/*     */   public void setIs_null(String isNull) {
/*  79 */     this.is_null = FieldsUtil.formatQuote(isNull);
/*     */   }
/*     */   public String getIs_display() {
/*  82 */     return FieldsUtil.formatQuote(this.is_display);
/*     */   }
/*     */   public void setIs_display(String isDisplay) {
/*  85 */     this.is_display = isDisplay;
/*     */   }
/*     */   public String getField_text() {
/*  88 */     return FieldsUtil.formatQuote(this.field_text);
/*     */   }
/*     */   public void setField_text(String fieldText) {
/*  91 */     this.field_text = fieldText;
/*     */   }
/*     */   public String getField_info() {
/*  94 */     return FieldsUtil.formatQuote(this.field_info);
/*     */   }
/*     */   public void setField_info(String fieldInfo) {
/*  97 */     this.field_info = fieldInfo;
/*     */   }
/*     */   public String getAdd_time() {
/* 100 */     return FieldsUtil.formatQuote(this.add_time);
/*     */   }
/*     */   public void setAdd_time(String addTime) {
/* 103 */     this.add_time = addTime;
/*     */   }
/*     */   public String getField_maxnum() {
/* 106 */     return FieldsUtil.formatQuote(this.field_maxnum);
/*     */   }
/*     */   public void setField_maxnum(String fieldMaxnum) {
/* 109 */     this.field_maxnum = fieldMaxnum;
/*     */   }
/*     */   public String getField_maxlength() {
/* 112 */     return FieldsUtil.formatQuote(this.field_maxlength);
/*     */   }
/*     */   public void setField_maxlength(String fieldMaxlength) {
/* 115 */     this.field_maxlength = fieldMaxlength;
/*     */   }
/*     */   public String getValidator() {
/* 118 */     return FieldsUtil.formatQuote(this.validator);
/*     */   }
/*     */   public void setValidator(String validator) {
/* 121 */     this.validator = validator;
/*     */   }
/*     */   public String getWidth() {
/* 124 */     return this.width;
/*     */   }
/*     */   public void setWidth(String width) {
/* 127 */     this.width = width;
/*     */   }
/*     */   public String getHeight() {
/* 130 */     return FieldsUtil.formatQuote(this.height);
/*     */   }
/*     */   public String getField_flag() {
/* 133 */     return this.field_flag;
/*     */   }
/*     */   public void setField_flag(String fieldFlag) {
/* 136 */     this.field_flag = fieldFlag;
/*     */   }
/*     */   public void setHeight(String height) {
/* 139 */     this.height = height;
/*     */   }
/*     */   public String getHtmledit_type() {
/* 142 */     return FieldsUtil.formatQuote(this.htmledit_type);
/*     */   }
/*     */   public void setHtmledit_type(String htmleditType) {
/* 145 */     this.htmledit_type = htmleditType;
/*     */   }
/*     */   public String getDefault_value() {
/* 148 */     return FieldsUtil.formatQuote(this.default_value);
/*     */   }
/*     */   public void setDefault_value(String defaultValue) {
/* 151 */     this.default_value = defaultValue;
/*     */   }
/*     */   public String getSelect_item() {
/* 154 */     return FieldsUtil.formatQuote(this.select_item);
/*     */   }
/*     */   public void setSelect_item(String selectItem) {
/* 157 */     this.select_item = selectItem;
/*     */   }
/*     */   public String getSelect_view() {
/* 160 */     return FieldsUtil.formatQuote(this.select_view);
/*     */   }
/*     */   public void setSelect_view(String selectView) {
/* 163 */     this.select_view = selectView;
/*     */   }
/*     */   public String getMax_num() {
/* 166 */     return FieldsUtil.formatQuote(this.max_num);
/*     */   }
/*     */   public void setMax_num(String maxNum) {
/* 169 */     this.max_num = maxNum;
/*     */   }
/*     */   public String getMin_num() {
/* 172 */     return FieldsUtil.formatQuote(this.min_num);
/*     */   }
/*     */   public void setMin_num(String minNum) {
/* 175 */     this.min_num = minNum;
/*     */   }
/*     */   public String getData_type() {
/* 178 */     return FieldsUtil.formatQuote(this.data_type);
/*     */   }
/*     */   public void setData_type(String dataType) {
/* 181 */     this.data_type = dataType;
/*     */   }
/*     */   public String getData_type_id() {
/* 184 */     return FieldsUtil.formatQuote(this.data_type_id);
/*     */   }
/*     */   public void setData_type_id(String dataTypeId) {
/* 187 */     this.data_type_id = dataTypeId;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return "Fields [add_time=" + this.add_time + ", data_type=" + this.data_type + 
/* 194 */       ", data_type_id=" + this.data_type_id + ", default_value=" + 
/* 195 */       this.default_value + ", field_cnname=" + this.field_cnname + 
/* 196 */       ", field_enname=" + this.field_enname + ", field_info=" + 
/* 197 */       this.field_info + ", field_maxlength=" + this.field_maxlength + 
/* 198 */       ", field_maxnum=" + this.field_maxnum + ", field_text=" + 
/* 199 */       this.field_text + ", field_type=" + this.field_type + ", height=" + 
/* 200 */       this.height + ", htmledit_type=" + this.htmledit_type + ", id=" + this.id + 
/* 201 */       ", is_display=" + this.is_display + ", is_null=" + this.is_null + 
/* 202 */       ", is_sys=" + this.is_sys + ", max_num=" + this.max_num + ", min_num=" + 
/* 203 */       this.min_num + ", select_item=" + this.select_item + ", select_view=" + 
/* 204 */       this.select_view + ", validator=" + this.validator + ", width=" + this.width + 
/* 205 */       "]";
/*     */   }
/*     */   public int getFrom_id() {
/* 208 */     return this.from_id;
/*     */   }
/*     */   public void setFrom_id(int fromId) {
/* 211 */     this.from_id = fromId;
/*     */   }
/*     */   public int getField_sort() {
/* 214 */     return this.field_sort;
/*     */   }
/*     */   public void setField_sort(int fieldSort) {
/* 217 */     this.field_sort = fieldSort;
/*     */   }
/*     */   public int getModel_id() {
/* 220 */     return this.model_id;
/*     */   }
/*     */   public void setModel_id(int modelId) {
/* 223 */     this.model_id = modelId;
/*     */   }
/*     */   public String getFrom_field_cnname() {
/* 226 */     return this.from_field_cnname;
/*     */   }
/*     */   public void setFrom_field_cnname(String fromFieldCnname) {
/* 229 */     this.from_field_cnname = fromFieldCnname;
/*     */   }
/*     */   public String getField_flag2() {
/* 232 */     return this.field_flag2;
/*     */   }
/*     */   public void setField_flag2(String fieldFlag2) {
/* 235 */     this.field_flag2 = fieldFlag2;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.Fields
 * JD-Core Version:    0.6.2
 */