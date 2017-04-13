/*    */ package com.cicro.wcm.bean.appeal.area;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AreaBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -6568341357997159923L;
/* 24 */   private int area_id = 0;
/* 25 */   private int parent_id = 0;
/* 26 */   private String area_cname = "";
/* 27 */   private String area_position = "";
/* 28 */   private int area_level = 1;
/* 29 */   private int sort_id = 999;
/*    */ 
/*    */   public int getSort_id()
/*    */   {
/* 33 */     return this.sort_id;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 36 */     this.sort_id = sortId;
/*    */   }
/*    */   public int getArea_id() {
/* 39 */     return this.area_id;
/*    */   }
/*    */   public void setArea_id(int areaId) {
/* 42 */     this.area_id = areaId;
/*    */   }
/*    */   public int getParent_id() {
/* 45 */     return this.parent_id;
/*    */   }
/*    */   public void setParent_id(int parentId) {
/* 48 */     this.parent_id = parentId;
/*    */   }
/*    */   public String getArea_cname() {
/* 51 */     return this.area_cname;
/*    */   }
/*    */   public void setArea_cname(String areaCname) {
/* 54 */     this.area_cname = areaCname;
/*    */   }
/*    */   public String getArea_position() {
/* 57 */     return this.area_position;
/*    */   }
/*    */   public void setArea_position(String areaPosition) {
/* 60 */     this.area_position = areaPosition;
/*    */   }
/*    */   public int getArea_level() {
/* 63 */     return this.area_level;
/*    */   }
/*    */   public void setArea_level(int areaLevel) {
/* 66 */     this.area_level = areaLevel;
/*    */   }
/*    */   public static long getSerialversionuid() {
/* 69 */     return -6568341357997159923L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.area.AreaBean
 * JD-Core Version:    0.6.2
 */