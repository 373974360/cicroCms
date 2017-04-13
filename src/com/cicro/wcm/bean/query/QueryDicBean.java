/*    */ package com.cicro.wcm.bean.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class QueryDicBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7832061740840162338L;
/* 10 */   private int dic_id = 0;
/* 11 */   private int conf_id = 0;
/* 12 */   private String field_cname = "";
/* 13 */   private int is_selected = 0;
/* 14 */   private int is_query = 0;
/* 15 */   private int is_result = 0;
/* 16 */   private int sort_id = 0;
/* 17 */   private String site_id = "";
/*    */ 
/*    */   public int getDic_id()
/*    */   {
/* 22 */     return this.dic_id;
/*    */   }
/*    */   public int getConf_id() {
/* 25 */     return this.conf_id;
/*    */   }
/*    */   public String getField_cname() {
/* 28 */     return this.field_cname;
/*    */   }
/*    */   public int getIs_selected() {
/* 31 */     return this.is_selected;
/*    */   }
/*    */   public int getIs_query() {
/* 34 */     return this.is_query;
/*    */   }
/*    */   public int getIs_result() {
/* 37 */     return this.is_result;
/*    */   }
/*    */   public int getSort_id() {
/* 40 */     return this.sort_id;
/*    */   }
/*    */   public String getSite_id() {
/* 43 */     return this.site_id;
/*    */   }
/*    */   public void setDic_id(int dicId) {
/* 46 */     this.dic_id = dicId;
/*    */   }
/*    */   public void setConf_id(int confId) {
/* 49 */     this.conf_id = confId;
/*    */   }
/*    */   public void setField_cname(String fieldCname) {
/* 52 */     this.field_cname = fieldCname;
/*    */   }
/*    */   public void setIs_selected(int isSelected) {
/* 55 */     this.is_selected = isSelected;
/*    */   }
/*    */   public void setIs_query(int isQuery) {
/* 58 */     this.is_query = isQuery;
/*    */   }
/*    */   public void setIs_result(int isResult) {
/* 61 */     this.is_result = isResult;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 64 */     this.sort_id = sortId;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 67 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.query.QueryDicBean
 * JD-Core Version:    0.6.2
 */