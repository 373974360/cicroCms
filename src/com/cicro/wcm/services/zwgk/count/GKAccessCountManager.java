/*     */ package com.cicro.wcm.services.zwgk.count;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.cms.count.InfoAccessBean;
/*     */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.dao.cms.count.AccessCountDao;
/*     */ import com.cicro.wcm.services.appeal.count.CountUtil;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeCateManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GKAccessCountManager
/*     */ {
/*     */   public static List<GKCountBean> getAllSiteGKCountList(String startDate, String endDate, String node_ids)
/*     */   {
/*     */     try
/*     */     {
/*  36 */       Set set = new HashSet();
/*  37 */       Map mp = new HashMap();
/*  38 */       mp.put("start_day", startDate);
/*  39 */       mp.put("end_day", endDate);
/*  40 */       if ((node_ids != null) && (node_ids != "")) {
/*  41 */         mp.put("site_ids", node_ids);
/*  42 */         String[] arr = node_ids.replace("'", "").split(",");
/*  43 */         for (String s : arr) {
/*  44 */           set.add(s);
/*     */         }
/*     */       }
/*  47 */       Map cnt_mp = new HashMap();
/*  48 */       return addSiteInfo(cnt_mp, set);
/*     */     }
/*     */     catch (Exception e) {
/*  51 */       e.printStackTrace();
/*  52 */     }return null;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> addSiteInfo(Map<String, GKCountBean> cnt_mp, Set<String> set)
/*     */   {
/*  63 */     List ret_lt = new ArrayList();
/*  64 */     List<GKNodeBean> node_lt = new ArrayList();
/*  65 */     String node_cates = GKNodeCateManager.getAllChildCategoryIDS("0");
/*  66 */     String[] arr_node = node_cates.split(",");
/*  67 */     for (int i = 0; i < arr_node.length; i++) {
/*  68 */       List lt = GKNodeManager.getGKNodeListByCateID(
/*  69 */         Integer.valueOf(arr_node[i]).intValue());
/*  70 */       node_lt.addAll(lt);
/*     */     }
/*  72 */     //List<InfoAccessBean> v_count = AccessCountDao.getGkVisitorCount(cnt_mp);
				List<InfoAccessBean> v_count = null;
/*  73 */     for (GKNodeBean bean : node_lt)
/*     */     {
/*  77 */       if ((set.size() == 0) || (set.contains(bean.getNode_id())))
/*     */       {
/*     */         GKCountBean retbean;
/*  80 */         if (cnt_mp.containsKey(bean.getNode_id())) {
/*  81 */           retbean = (GKCountBean)cnt_mp.get(bean.getNode_id());
/*     */         } else {
/*  83 */           retbean = new GKCountBean();
/*  84 */           retbean.setSite_id(bean.getNode_id());
/*     */         }
/*  86 */         retbean.setSite_name(bean.getNode_name());
/*  87 */         if ((v_count != null) && (v_count.size() > 0)) {
/*  88 */           for (InfoAccessBean be : v_count)
/*     */           {
/*  90 */             if (retbean.getSite_id().equals(be.getSite_id())) {
/*  91 */               retbean.setInfo_count(be.getIcount());
/*  92 */               break;
/*     */             }
/*  94 */             retbean.setInfo_count(0);
/*     */           }
/*     */         }
/*     */         else {
/*  98 */           retbean.setInfo_count(0);
/*     */         }
/* 100 */         ret_lt.add(retbean);
/*     */       }
/*     */     }
/* 102 */     return ret_lt;
/*     */   }
/*     */ 
/*     */   public static String gkInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/* 109 */       String[] fileStr = getFileUrl();
/*     */ 
/* 112 */       int size = headerList.size();
/* 113 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 116 */       String[][] data = new String[listBean.size()][6];
/* 117 */       for (int i = 0; i < listBean.size(); i++) {
/* 118 */         GKCountBean countBean = (GKCountBean)listBean.get(i);
/* 119 */         data[i][0] = countBean.getSite_name();
/* 120 */         data[i][1] = String.valueOf(countBean.getInfo_count());
/*     */       }
/* 122 */       OutExcel oe = new OutExcel("政务公开访问量统计");
/* 123 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/* 125 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String[] getFileUrl()
/*     */   {
/* 136 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path");
/* 137 */     String path = FormatUtil.formatPath(root_path + "/zwgk/count/file/");
/* 138 */     File filePath = new File(path);
/* 139 */     if (!filePath.exists()) {
/* 140 */       filePath.mkdir();
/*     */     }
/*     */ 
/* 143 */     CountUtil.deleteFile(path);
/*     */ 
/* 146 */     String nowDate = CountUtil.getNowDayDate();
/* 147 */     String fileTemp2 = FormatUtil.formatPath(path + File.separator + nowDate);
/*     */ 
/* 149 */     File file2 = new File(fileTemp2);
/* 150 */     if (!file2.exists()) {
/* 151 */       file2.mkdir();
/*     */     }
/* 153 */     String nowTime = CountUtil.getNowDayDateTime();
/* 154 */     String xls = nowTime + CountUtil.getEnglish(1) + ".xls";
/* 155 */     String xlsFile = fileTemp2 + File.separator + xls;
/* 156 */     String urlFile = "/manager/zwgk/count/file/" + nowDate + File.separator + xls;
/*     */ 
/* 158 */     String[] str = { xlsFile, urlFile };
/*     */ 
/* 160 */     return str;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKAccessCountManager
 * JD-Core Version:    0.6.2
 */