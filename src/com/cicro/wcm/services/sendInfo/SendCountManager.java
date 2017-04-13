/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.CalculateNumber;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordCount;
/*     */ import com.cicro.wcm.dao.sendInfo.SendCountDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SendCountManager
/*     */ {
/*     */   public static List<SendRecordCount> getSendRecordUserCount(Map<String, String> m)
/*     */   {
/*  29 */     List count_list = SendCountDAO.getSendUserListForRecord(m);
/*  30 */     if ((count_list != null) && (count_list.size() > 0))
/*     */     {
/*  33 */       m.put("adopt_status", "1");
/*  34 */       List publish_list = SendCountDAO.getSendUserListForRecord(m);
/*  35 */       for (SendRecordCount src : count_list) {
/*     */         try
/*     */         {
/*  38 */           src.setUser_realname(UserManager.getUserRealName(src.getUser_id()));
/*  39 */           for (SendRecordCount pub : publish_list)
/*     */           {
/*  41 */             if (src.getUser_id() == pub.getUser_id())
/*     */             {
/*  43 */               src.setAdopt_count(pub.getSend_count());
/*  44 */               break;
/*     */             }
/*     */           }
/*  47 */           src.setNot_count(src.getSend_count() - src.getAdopt_count());
/*  48 */           src.setAdopt_rate(CalculateNumber.getRate(src.getAdopt_count(), src.getSend_count()));
/*     */         }
/*     */         catch (Exception e) {
/*  51 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  56 */     return count_list;
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> getSendCateListForRecord(Map<String, String> m)
/*     */   {
/*  67 */     String site_id = (String)m.get("send_site_id");
/*  68 */     Set cat_set = new HashSet();
/*  69 */     Map count_m = new HashMap();
/*  70 */     List count_list = SendCountDAO.getSendCateListForRecord(m);
/*  71 */     if ((count_list != null) && (count_list.size() > 0))
/*     */     {
/*  74 */       m.put("adopt_status", "1");
/*  75 */       List publish_list = SendCountDAO.getSendCateListForRecord(m);
/*  76 */       for (SendRecordCount src : count_list) {
/*     */         try
/*     */         {
/*  79 */           for (SendRecordCount pub : publish_list)
/*     */           {
/*  81 */             if (src.getCat_id() == pub.getCat_id())
/*     */             {
/*  83 */               src.setAdopt_count(pub.getSend_count());
/*  84 */               break;
/*     */             }
/*     */           }
/*  87 */           src.setNot_count(src.getSend_count() - src.getAdopt_count());
/*  88 */           src.setAdopt_rate(CalculateNumber.getRate(src.getAdopt_count(), src.getSend_count()));
/*     */ 
/*  90 */           CategoryBean cb = CategoryManager.getCategoryBeanCatID(src.getCat_id(), site_id);
/*  91 */           String position = cb.getCat_position();
/*  92 */           CategoryManager.setCategoryByPosition(cat_set, position, site_id);
/*  93 */           cat_set.add(cb);
/*  94 */           src.setCat_cname(cb.getCat_cname());
/*  95 */           src.setCat_parent_id(cb.getParent_id());
/*  96 */           src.setCat_sort(cb.getCat_sort());
/*  97 */           count_m.put(Integer.valueOf(src.getCat_id()), src);
/*     */         }
/*     */         catch (Exception e) {
/* 100 */           e.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 107 */     return rankCountListForCate(count_m, cat_set, 0);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> rankCountListForCate(Map<Integer, SendRecordCount> count_m, Set<CategoryBean> cat_set, int parent_id)
/*     */   {
/* 114 */     List list2 = new ArrayList();
/*     */ 
/* 116 */     for (CategoryBean cb : cat_set)
/*     */     {
/* 118 */       if (cb.getParent_id() == parent_id)
/*     */       {
/* 120 */         if (count_m.containsKey(Integer.valueOf(cb.getCat_id())))
/*     */         {
/* 122 */           list2.add((SendRecordCount)count_m.get(Integer.valueOf(cb.getCat_id())));
/*     */         }
/*     */         else {
/* 125 */           SendRecordCount src = new SendRecordCount();
/* 126 */           src.setCat_id(cb.getCat_id());
/* 127 */           src.setCat_cname(cb.getCat_cname());
/* 128 */           src.setChild_cate_list(rankCountListForCate(count_m, cat_set, cb.getCat_id()));
/* 129 */           src.setCat_parent_id(parent_id);
/* 130 */           src.setCat_sort(cb.getCat_sort());
/* 131 */           list2.add(src);
/*     */         }
/*     */       }
/*     */     }
/* 135 */     Collections.sort(list2, new SendCountManager.sendCategoryComparator());
/* 136 */     return list2;
/*     */   }
/*     */ 
/*     */   public static List<SendRecordBean> getReceiveSiteListForRecord(String site_id)
/*     */   {
/* 163 */     return SendCountDAO.getReceiveSiteListForRecord(site_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 168 */     Map m = new HashMap();
/* 169 */     m.put("send_site_id", "HIWCMdemo");
/* 170 */     System.out.println(((ReceiveInfoBean)SendCountDAO.getSendSiteList("HIWCMdemo").get(0)).getS_site_name());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendCountManager
 * JD-Core Version:    0.6.2
 */