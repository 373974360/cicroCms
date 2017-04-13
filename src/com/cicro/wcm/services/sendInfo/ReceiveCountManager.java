/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.CalculateNumber;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordCount;
/*     */ import com.cicro.wcm.dao.sendInfo.SendCountDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ReceiveCountManager
/*     */ {
/*     */   public static List<SendRecordCount> getSendSiteCountForReceive(Map<String, String> m)
/*     */   {
/*  24 */     List count_list = SendCountDAO.getSendSiteCountForReceive(m);
/*  25 */     if ((count_list != null) && (count_list.size() > 0))
/*     */     {
/*  28 */       m.put("adopt_status", "1");
/*  29 */       List publish_list = SendCountDAO.getSendSiteCountForReceive(m);
/*  30 */       for (SendRecordCount src : count_list) {
/*     */         try
/*     */         {
/*  33 */           for (SendRecordCount pub : publish_list)
/*     */           {
/*  35 */             if (src.getUser_id() == pub.getUser_id())
/*     */             {
/*  37 */               src.setAdopt_count(pub.getSend_count());
/*  38 */               break;
/*     */             }
/*     */           }
/*  41 */           src.setNot_count(src.getSend_count() - src.getAdopt_count());
/*  42 */           src.setAdopt_rate(CalculateNumber.getRate(src.getAdopt_count(), src.getSend_count()));
/*     */         }
/*     */         catch (Exception e) {
/*  45 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  50 */     return count_list;
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getSendSiteList(String site_id)
/*     */   {
/*  60 */     return SendCountDAO.getSendSiteList(site_id);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> getReceiveCateListForRecord(Map<String, String> m)
/*     */   {
/*  70 */     String site_id = (String)m.get("site_id");
/*  71 */     Set cat_set = new HashSet();
/*  72 */     Map count_m = new HashMap();
/*  73 */     List count_list = SendCountDAO.getReceiveCateListForRecord(m);
/*  74 */     if ((count_list != null) && (count_list.size() > 0))
/*     */     {
/*  77 */       m.put("adopt_status", "1");
/*  78 */       List publish_list = SendCountDAO.getReceiveCateListForRecord(m);
/*  79 */       for (SendRecordCount src : count_list) {
/*     */         try
/*     */         {
/*  82 */           for (SendRecordCount pub : publish_list)
/*     */           {
/*  84 */             if (src.getCat_id() == pub.getCat_id())
/*     */             {
/*  86 */               src.setAdopt_count(pub.getSend_count());
/*  87 */               break;
/*     */             }
/*     */           }
/*  90 */           src.setNot_count(src.getSend_count() - src.getAdopt_count());
/*  91 */           src.setAdopt_rate(CalculateNumber.getRate(src.getAdopt_count(), src.getSend_count()));
/*  92 */           CategoryBean cb = CategoryManager.getCategoryBeanCatID(src.getCat_id(), site_id);
/*  93 */           String position = cb.getCat_position();
/*  94 */           CategoryManager.setCategoryByPosition(cat_set, position, site_id);
/*  95 */           cat_set.add(cb);
/*  96 */           src.setCat_cname(cb.getCat_cname());
/*  97 */           src.setCat_parent_id(cb.getParent_id());
/*  98 */           src.setCat_sort(cb.getCat_sort());
/*  99 */           count_m.put(Integer.valueOf(src.getCat_id()), src);
/*     */         }
/*     */         catch (Exception e) {
/* 102 */           e.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 109 */     return SendCountManager.rankCountListForCate(count_m, cat_set, 0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.ReceiveCountManager
 * JD-Core Version:    0.6.2
 */