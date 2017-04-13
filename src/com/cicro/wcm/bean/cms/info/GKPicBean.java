/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GKPicBean extends GKInfoBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2707966035663107954L;
/* 11 */   private List<PicItemBean> item_list = new ArrayList();
/* 12 */   private String pic_content = "";
/*    */ 
/* 14 */   public String getPic_content() { return this.pic_content; }
/*    */ 
/*    */   public void setPic_content(String picContent) {
/* 17 */     this.pic_content = picContent;
/*    */   }
/*    */   public List<PicItemBean> getItem_list() {
/* 20 */     return this.item_list;
/*    */   }
/*    */   public void setItem_list(List<PicItemBean> itemList) {
/* 23 */     this.item_list = itemList;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.GKPicBean
 * JD-Core Version:    0.6.2
 */