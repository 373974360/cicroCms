/*    */ package com.cicro.wcm.bean.control;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SiteGroupBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5359757397691218711L;
/* 10 */   private String sgroup_id = "";
/* 11 */   private String parent_id = "";
/* 12 */   private String sgroup_name = "";
/* 13 */   private String sgroup_ip = "";
/* 14 */   private String sgroup_prot = "";
/*    */   private int dept_id;
/*    */   private int sgroup_sort;
/* 17 */   private String sgroup_memo = "";
/*    */   private List<SiteGroupBean> child_menu_list;
/*    */ 
/*    */   public int getDept_id()
/*    */   {
/* 21 */     return this.dept_id;
/*    */   }
/*    */   public void setDept_id(int dept_id) {
/* 24 */     this.dept_id = dept_id;
/*    */   }
/*    */   public String getParent_id() {
/* 27 */     return this.parent_id;
/*    */   }
/*    */   public void setParent_id(String parent_id) {
/* 30 */     this.parent_id = parent_id;
/*    */   }
/*    */   public String getSgroup_id() {
/* 33 */     return this.sgroup_id;
/*    */   }
/*    */   public void setSgroup_id(String sgroup_id) {
/* 36 */     this.sgroup_id = sgroup_id;
/*    */   }
/*    */   public String getSgroup_ip() {
/* 39 */     return this.sgroup_ip;
/*    */   }
/*    */   public void setSgroup_ip(String sgroup_ip) {
/* 42 */     this.sgroup_ip = sgroup_ip;
/*    */   }
/*    */   public String getSgroup_memo() {
/* 45 */     return this.sgroup_memo;
/*    */   }
/*    */   public void setSgroup_memo(String sgroup_memo) {
/* 48 */     this.sgroup_memo = sgroup_memo;
/*    */   }
/*    */   public String getSgroup_name() {
/* 51 */     return this.sgroup_name;
/*    */   }
/*    */   public void setSgroup_name(String sgroup_name) {
/* 54 */     this.sgroup_name = sgroup_name;
/*    */   }
/*    */   public String getSgroup_prot() {
/* 57 */     return this.sgroup_prot;
/*    */   }
/*    */   public void setSgroup_prot(String sgroup_prot) {
/* 60 */     this.sgroup_prot = sgroup_prot;
/*    */   }
/*    */   public int getSgroup_sort() {
/* 63 */     return this.sgroup_sort;
/*    */   }
/*    */   public void setSgroup_sort(int sgroup_sort) {
/* 66 */     this.sgroup_sort = sgroup_sort;
/*    */   }
/*    */   public List<SiteGroupBean> getChild_menu_list() {
/* 69 */     return this.child_menu_list;
/*    */   }
/*    */   public void setChild_menu_list(List<SiteGroupBean> childMenuList) {
/* 72 */     this.child_menu_list = childMenuList;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.control.SiteGroupBean
 * JD-Core Version:    0.6.2
 */