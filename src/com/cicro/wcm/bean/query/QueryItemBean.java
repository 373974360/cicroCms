/*    */ package com.cicro.wcm.bean.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class QueryItemBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1660757468238567665L;
/* 10 */   public int item_id = 0;
/* 11 */   private int conf_id = 0;
/* 12 */   private int item_key = 0;
/* 13 */   private String item_value = "";
/* 14 */   private String site_id = "";
/*    */ 
/*    */   public int getItem_id()
/*    */   {
/* 19 */     return this.item_id;
/*    */   }
/*    */   public int getConf_id() {
/* 22 */     return this.conf_id;
/*    */   }
/*    */   public int getItem_key() {
/* 25 */     return this.item_key;
/*    */   }
/*    */   public String getItem_value() {
/* 28 */     return this.item_value;
/*    */   }
/*    */   public String getSite_id() {
/* 31 */     return this.site_id;
/*    */   }
/*    */   public void setItem_id(int itemId) {
/* 34 */     this.item_id = itemId;
/*    */   }
/*    */   public void setConf_id(int confId) {
/* 37 */     this.conf_id = confId;
/*    */   }
/*    */   public void setItem_key(int itemKey) {
/* 40 */     this.item_key = itemKey;
/*    */   }
/*    */   public void setItem_value(String itemValue) {
/* 43 */     this.item_value = itemValue;
/*    */   }
/*    */   public void setSite_id(String siteId) {
/* 46 */     this.site_id = siteId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.query.QueryItemBean
 * JD-Core Version:    0.6.2
 */