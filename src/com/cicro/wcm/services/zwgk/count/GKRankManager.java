/*     */ package com.cicro.wcm.services.zwgk.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.dao.zwgk.count.GKRankDAO;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GKRankManager
/*     */ {
/*     */   public static List<List> GKWorkLoadRank(Map<String, String> mp)
/*     */   {
/*  33 */     List retLt = new ArrayList();
/*  34 */     List ranklt = GKRankDAO.GKWorkLoadRank(mp);
/*     */     try
/*     */     {
/*  37 */       String num = (String)mp.get("num");
/*  38 */       int i_num = 10;
/*  39 */       if ((num != null) && (!"".equals(num))) {
/*  40 */         i_num = Integer.valueOf(num).intValue();
/*     */ 
/*  42 */         if ("0".equals(num)) {
/*  43 */           i_num = ranklt.size() + 1;
/*     */         }
/*     */       }
/*     */ 
/*  47 */       for (int i = 0; (i < ranklt.size()) && (i < i_num); i++) {
/*  48 */         Map totalMap = (Map)ranklt.get(i);
/*     */ 
/*  50 */         List subList = new ArrayList();
/*     */ 
/*  52 */         String site_id = (String)totalMap.get("SITE_ID");
/*  53 */         GKNodeBean nodebean = GKNodeManager.getGKNodeBeanByNodeID(site_id);
/*  54 */         String node_name = nodebean == null ? site_id : nodebean.getNode_name();
/*  55 */         subList.add(node_name);
/*     */ 
/*  58 */         int userr_id = Integer.valueOf(String.valueOf(totalMap.get("INPUT_USER"))).intValue();
/*  59 */         UserBean ub = UserManager.getUserBeanByID(userr_id);
/*  60 */         if (ub != null)
/*  61 */           subList.add(ub.getUser_realname());
/*     */         else {
/*  63 */           subList.add(userr_id);
/*     */         }
/*     */ 
/*  68 */         int record_count = Integer.valueOf(String.valueOf(totalMap.get("RECORD_COUNT"))).intValue();
/*  69 */         subList.add(record_count);
/*     */ 
/*  73 */         int pub_count = Integer.valueOf(String.valueOf(totalMap.get("PUB_COUNT"))).intValue();
/*  74 */         subList.add(pub_count);
/*     */ 
/*  78 */         double rate = Double.valueOf(String.valueOf(totalMap.get("RATE"))).doubleValue() * 100.0D;
/*     */ 
/*  80 */         NumberFormat nf = new DecimalFormat();
/*  81 */         nf.setMaximumFractionDigits(2);
/*  82 */         nf.setRoundingMode(RoundingMode.HALF_UP);
/*  83 */         subList.add(nf.format(rate));
/*     */ 
/*  85 */         retLt.add(subList);
/*     */       }
/*  87 */       return retLt;
/*     */     } catch (Exception e) {
/*  89 */       e.printStackTrace();
/*  90 */     }return retLt;
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> GKInfoCountRank(Map<String, String> mp)
/*     */   {
/* 104 */     String startDate = (String)mp.get("start_day");
/* 105 */     String endDate = (String)mp.get("end_day");
/* 106 */     String node_ids = (String)mp.get("site_ids");
/* 107 */     List ranklt = GKCountManager.getAllSiteGKCountList(startDate, endDate, node_ids);
/*     */ 
/* 110 */     String num = (String)mp.get("num");
/* 111 */     int i_num = 10;
/* 112 */     if ((num != null) && (!"".equals(num))) {
/* 113 */       i_num = Integer.valueOf(num).intValue();
/*     */ 
/* 115 */       if ("0".equals(num)) {
/* 116 */         i_num = ranklt.size() + 1;
/*     */       }
/*     */     }
/* 119 */     int index = ranklt.size() > i_num ? i_num : ranklt.size();
/* 120 */     ranklt = ranklt.subList(0, index);
/*     */ 
/* 122 */     for (GKCountBean bean : ranklt) {
/* 123 */       GKNodeBean nodebean = GKNodeManager.getGKNodeBeanByNodeID(bean.getSite_id());
/* 124 */       String node_name = nodebean == null ? bean.getSite_id() : nodebean.getNode_name();
/* 125 */       bean.setSite_name(node_name);
/*     */     }
/*     */ 
/* 128 */     return ranklt;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg) {
/* 132 */     double rate = 0.9876D;
/* 133 */     double rates = BigDecimal.valueOf(rate).doubleValue() * 100.0D;
/* 134 */     System.out.println("rates = " + rates);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKRankManager
 * JD-Core Version:    0.6.2
 */