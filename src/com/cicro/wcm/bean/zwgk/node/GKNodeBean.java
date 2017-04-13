/*     */ package com.cicro.wcm.bean.zwgk.node;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GKNodeBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6222724034207244504L;
/*  10 */   private int id = 0;
/*  11 */   private String node_id = "";
/*  12 */   private int nodcat_id = 0;
/*  13 */   private int dept_id = 0;
/*  14 */   private String node_name = "";
/*  15 */   private String node_fullname = "";
/*  16 */   private int node_status = 0;
/*  17 */   private int sort_id = 999;
/*  18 */   private String dept_code = "";
/*  19 */   private int is_apply = 0;
/*  20 */   private String apply_name = "";
/*  21 */   private String node_demo = "";
/*  22 */   private String rela_site_id = "";
/*  23 */   private String address = "";
/*  24 */   private String postcode = "";
/*  25 */   private String office_dtime = "";
/*  26 */   private String tel = "";
/*  27 */   private String fax = "";
/*  28 */   private String email = "";
/*  29 */   String index_template_id = "";
/*     */ 
/*  31 */   public String getIndex_template_id() { return this.index_template_id; }
/*     */ 
/*     */   public void setIndex_template_id(String indexTemplateId) {
/*  34 */     this.index_template_id = indexTemplateId;
/*     */   }
/*     */   public String getAddress() {
/*  37 */     return this.address;
/*     */   }
/*     */   public void setAddress(String address) {
/*  40 */     this.address = address;
/*     */   }
/*     */   public String getPostcode() {
/*  43 */     return this.postcode;
/*     */   }
/*     */   public void setPostcode(String postcode) {
/*  46 */     this.postcode = postcode;
/*     */   }
/*     */   public String getOffice_dtime() {
/*  49 */     return this.office_dtime;
/*     */   }
/*     */   public void setOffice_dtime(String officeDtime) {
/*  52 */     this.office_dtime = officeDtime;
/*     */   }
/*     */   public String getTel() {
/*  55 */     return this.tel;
/*     */   }
/*     */   public void setTel(String tel) {
/*  58 */     this.tel = tel;
/*     */   }
/*     */   public String getFax() {
/*  61 */     return this.fax;
/*     */   }
/*     */   public void setFax(String fax) {
/*  64 */     this.fax = fax;
/*     */   }
/*     */   public String getEmail() {
/*  67 */     return this.email;
/*     */   }
/*     */   public void setEmail(String email) {
/*  70 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getRela_site_id()
/*     */   {
/*  75 */     return this.rela_site_id;
/*     */   }
/*     */   public void setRela_site_id(String relaSiteId) {
/*  78 */     this.rela_site_id = relaSiteId;
/*     */   }
/*     */   public int getId() {
/*  81 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/*  84 */     this.id = id;
/*     */   }
/*     */   public String getNode_id() {
/*  87 */     return this.node_id;
/*     */   }
/*     */   public void setNode_id(String nodeId) {
/*  90 */     this.node_id = nodeId;
/*     */   }
/*     */   public int getNodcat_id() {
/*  93 */     return this.nodcat_id;
/*     */   }
/*     */   public void setNodcat_id(int nodcatId) {
/*  96 */     this.nodcat_id = nodcatId;
/*     */   }
/*     */   public int getDept_id() {
/*  99 */     return this.dept_id;
/*     */   }
/*     */   public void setDept_id(int deptId) {
/* 102 */     this.dept_id = deptId;
/*     */   }
/*     */   public String getNode_name() {
/* 105 */     return this.node_name;
/*     */   }
/*     */   public void setNode_name(String nodeName) {
/* 108 */     this.node_name = nodeName;
/*     */   }
/*     */   public String getNode_fullname() {
/* 111 */     return this.node_fullname;
/*     */   }
/*     */   public void setNode_fullname(String nodeFullname) {
/* 114 */     this.node_fullname = nodeFullname;
/*     */   }
/*     */   public int getNode_status() {
/* 117 */     return this.node_status;
/*     */   }
/*     */   public void setNode_status(int nodeStatus) {
/* 120 */     this.node_status = nodeStatus;
/*     */   }
/*     */   public int getSort_id() {
/* 123 */     return this.sort_id;
/*     */   }
/*     */   public void setSort_id(int sortId) {
/* 126 */     this.sort_id = sortId;
/*     */   }
/*     */   public String getDept_code() {
/* 129 */     return this.dept_code;
/*     */   }
/*     */   public void setDept_code(String deptCode) {
/* 132 */     this.dept_code = deptCode;
/*     */   }
/*     */   public int getIs_apply() {
/* 135 */     return this.is_apply;
/*     */   }
/*     */   public void setIs_apply(int isApply) {
/* 138 */     this.is_apply = isApply;
/*     */   }
/*     */   public String getApply_name() {
/* 141 */     return this.apply_name;
/*     */   }
/*     */   public void setApply_name(String applyName) {
/* 144 */     this.apply_name = applyName;
/*     */   }
/*     */   public String getNode_demo() {
/* 147 */     return this.node_demo;
/*     */   }
/*     */   public void setNode_demo(String nodeDemo) {
/* 150 */     this.node_demo = nodeDemo;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.node.GKNodeBean
 * JD-Core Version:    0.6.2
 */