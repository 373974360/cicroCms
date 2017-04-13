/*    */ package com.cicro.wcm.bean.search_bak_20130909;
/*    */ 
/*    */ import com.cicro.wcm.bean.search.*;
import com.cicro.wcm.bean.search.InfoPic;

import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class IndexBeanInfo extends com.cicro.wcm.bean.search.IndexBean
/*    */ {
/*    */   private String categoryId;
/*    */   private String publish_time;
/*    */   private String typed;
/* 19 */   private List<com.cicro.wcm.bean.search.InfoPic> infoPics = new ArrayList();
/*    */ 
/*    */   public IndexBeanInfo() {
/* 22 */     super("info");
/* 23 */     setTyped("info");
/*    */   }
/*    */ 
/*    */   public String getCategoryId() {
/* 27 */     return this.categoryId;
/*    */   }
/*    */ 
/*    */   public void setCategoryId(String categoryId) {
/* 31 */     this.categoryId = categoryId;
/*    */   }
/*    */ 
/*    */   public String getPublish_time() {
/* 35 */     return this.publish_time;
/*    */   }
/*    */ 
/*    */   public void setPublish_time(String publishTime) {
/* 39 */     this.publish_time = publishTime;
/*    */   }
/*    */ 
/*    */   public String getTyped() {
/* 43 */     return this.typed;
/*    */   }
/*    */ 
/*    */   public void setTyped(String typed) {
/* 47 */     this.typed = typed;
/*    */   }
/*    */ 
/*    */   public List<com.cicro.wcm.bean.search.InfoPic> getInfoPics() {
/* 51 */     return this.infoPics;
/*    */   }
/*    */ 
/*    */   public void setInfoPics(List<InfoPic> infoPics) {
/* 55 */     this.infoPics = infoPics;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search_bak_20130909.IndexBeanInfo
 * JD-Core Version:    0.6.2
 */