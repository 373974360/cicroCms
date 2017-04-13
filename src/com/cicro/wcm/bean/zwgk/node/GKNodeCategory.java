/*    */ package com.cicro.wcm.bean.zwgk.node;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GKNodeCategory
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7126214808820217969L;
/* 12 */   private int nodcat_id = 0;
/* 13 */   private int parent_id = 0;
/* 14 */   private String nodcat_name = "";
/* 15 */   private String nod_position = "";
/* 16 */   private int sort_id = 999;
/* 17 */   private String app_id = "";
/* 18 */   private List<GKNodeCategory> class_list = new ArrayList();
/* 19 */   private List<GKNodeBean> node_list = new ArrayList();
/*    */ 
/* 21 */   public List<GKNodeBean> getNode_list() { return this.node_list; }
/*    */ 
/*    */   public void setNode_list(List<GKNodeBean> nodeList) {
/* 24 */     this.node_list = nodeList;
/*    */   }
/*    */   public List<GKNodeCategory> getClass_list() {
/* 27 */     return this.class_list;
/*    */   }
/*    */   public void setClass_list(List<GKNodeCategory> classList) {
/* 30 */     this.class_list = classList;
/*    */   }
/*    */   public int getNodcat_id() {
/* 33 */     return this.nodcat_id;
/*    */   }
/*    */   public void setNodcat_id(int nodcatId) {
/* 36 */     this.nodcat_id = nodcatId;
/*    */   }
/*    */   public int getParent_id() {
/* 39 */     return this.parent_id;
/*    */   }
/*    */   public void setParent_id(int parentId) {
/* 42 */     this.parent_id = parentId;
/*    */   }
/*    */   public String getNodcat_name() {
/* 45 */     return this.nodcat_name;
/*    */   }
/*    */   public void setNodcat_name(String nodcatName) {
/* 48 */     this.nodcat_name = nodcatName;
/*    */   }
/*    */   public String getNod_position() {
/* 51 */     return this.nod_position;
/*    */   }
/*    */   public void setNod_position(String nodPosition) {
/* 54 */     this.nod_position = nodPosition;
/*    */   }
/*    */   public int getSort_id() {
/* 57 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 60 */     this.sort_id = sortId;
/*    */   }
/*    */   public String getApp_id() {
/* 63 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String appId) {
/* 66 */     this.app_id = appId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.node.GKNodeCategory
 * JD-Core Version:    0.6.2
 */