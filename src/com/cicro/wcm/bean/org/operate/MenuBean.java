/*     */ package com.cicro.wcm.bean.org.operate;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MenuBean
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = -5559805340842906501L;
/*     */   private int menu_id;
/*     */   private int parent_id;
/*  12 */   private String menu_name = "";
/*  13 */   private String menu_url = "";
/*  14 */   private String handls = "";
/*     */   private int opt_id;
/*  16 */   private String opt_name = "";
/*  17 */   private String menu_level = "";
/*  18 */   private String menu_position = "";
/*  19 */   private int menu_sort = 999;
/*  20 */   private String menu_memo = "";
/*     */   private List<MenuBean> child_menu_list;
/*     */ 
/*     */   public MenuBean clone()
/*     */   {
/*  24 */     MenuBean o = null;
/*     */     try {
/*  26 */       o = (MenuBean)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/*  28 */       e.printStackTrace();
/*     */     }
/*  30 */     return o;
/*     */   }
/*     */ 
/*     */   public String getOpt_name() {
/*  34 */     return this.opt_name;
/*     */   }
/*     */   public void setOpt_name(String optName) {
/*  37 */     this.opt_name = optName;
/*     */   }
/*     */   public String getHandls() {
/*  40 */     return this.handls;
/*     */   }
/*     */   public void setHandls(String handls) {
/*  43 */     this.handls = handls;
/*     */   }
/*     */   public List<MenuBean> getChild_menu_list() {
/*  46 */     return this.child_menu_list;
/*     */   }
/*     */   public void setChild_menu_list(List<MenuBean> child_menu_list) {
/*  49 */     this.child_menu_list = child_menu_list;
/*     */   }
/*     */ 
/*     */   public String getMenu_level() {
/*  53 */     return this.menu_level;
/*     */   }
/*     */   public void setMenu_level(String menuLevel) {
/*  56 */     this.menu_level = menuLevel;
/*     */   }
/*     */   public String getMenu_memo() {
/*  59 */     return this.menu_memo;
/*     */   }
/*     */   public void setMenu_memo(String menu_memo) {
/*  62 */     this.menu_memo = menu_memo;
/*     */   }
/*     */   public String getMenu_name() {
/*  65 */     return this.menu_name;
/*     */   }
/*     */   public void setMenu_name(String menu_name) {
/*  68 */     this.menu_name = menu_name;
/*     */   }
/*     */   public String getMenu_position() {
/*  71 */     return this.menu_position;
/*     */   }
/*     */   public void setMenu_position(String menu_position) {
/*  74 */     this.menu_position = menu_position;
/*     */   }
/*     */ 
/*     */   public int getMenu_sort() {
/*  78 */     return this.menu_sort;
/*     */   }
/*     */   public void setMenu_sort(int menu_sort) {
/*  81 */     this.menu_sort = menu_sort;
/*     */   }
/*     */   public int getOpt_id() {
/*  84 */     return this.opt_id;
/*     */   }
/*     */   public void setOpt_id(int opt_id) {
/*  87 */     this.opt_id = opt_id;
/*     */   }
/*     */   public String getMenu_url() {
/*  90 */     return this.menu_url;
/*     */   }
/*     */   public void setMenu_url(String menu_url) {
/*  93 */     this.menu_url = menu_url;
/*     */   }
/*     */ 
/*     */   public int getMenu_id() {
/*  97 */     return this.menu_id;
/*     */   }
/*     */   public void setMenu_id(int menu_id) {
/* 100 */     this.menu_id = menu_id;
/*     */   }
/*     */   public int getParent_id() {
/* 103 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parent_id) {
/* 106 */     this.parent_id = parent_id;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.operate.MenuBean
 * JD-Core Version:    0.6.2
 */