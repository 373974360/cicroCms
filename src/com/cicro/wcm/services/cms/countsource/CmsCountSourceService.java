/*     */ package com.cicro.wcm.services.cms.countsource;
/*     */ 
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.wcm.bean.cms.count.CmsCountBean;
/*     */ import com.cicro.wcm.services.cms.count.CmsCountExcelOut;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CmsCountSourceService
/*     */ {
/*     */   public static List<CmsCountBean> getInfoCountListBySource(Map<String, String> mp)
/*     */   {
/*  19 */     List result = new ArrayList();
/*     */     try {
/*  21 */       formatDate(mp);
/*  22 */       mp.remove("is_host");
/*  23 */       List list_012 = CmsCountSourceDao.getInfoCountListBySource(mp);
/*     */ 
/*  25 */       mp.put("is_host", "0");
/*  26 */       List list_0 = CmsCountSourceDao.getInfoCountListBySource(mp);
/*     */ 
/*  28 */       mp.put("is_host", "1");
/*  29 */       List list_1 = CmsCountSourceDao.getInfoCountListBySource(mp);
/*     */ 
/*  31 */       mp.put("is_host", "2");
/*  32 */       List list_2 = CmsCountSourceDao.getInfoCountListBySource(mp);
/*     */ 
/*  34 */       for (CmsCountBean bean012 : list_012) {
/*  35 */         String source = bean012.getCat_name();
/*     */ 
/*  38 */         int allCount = bean012.getCount();
/*  39 */         bean012.setAllCount(allCount);
/*     */ 
/*  41 */         int hostInfoCount = 0;
/*  42 */         for (CmsCountBean bean0 : list_0) {
/*  43 */           if (source.equals(bean0.getCat_name()))
/*     */           {
/*  45 */             hostInfoCount = bean0.getCount();
/*  46 */             break;
/*     */           }
/*     */         }
/*  49 */         bean012.setHostInfoCount(hostInfoCount);
/*     */ 
/*  51 */         int refInfoCount = 0;
/*  52 */         for (CmsCountBean bean1 : list_1) {
/*  53 */           if (source.equals(bean1.getCat_name())) {
/*  54 */             refInfoCount = bean1.getCount();
/*  55 */             break;
/*     */           }
/*     */         }
/*  58 */         bean012.setRefInfoCount(refInfoCount);
/*     */ 
/*  60 */         int linkInfoCount = 0;
/*  61 */         for (CmsCountBean bean2 : list_2) {
/*  62 */           if (source.equals(bean2.getCat_name())) {
/*  63 */             linkInfoCount = bean2.getCount();
/*  64 */             break;
/*     */           }
/*     */         }
/*  67 */         bean012.setLinkInfoCount(linkInfoCount);
/*     */ 
/*  69 */         result.add(bean012);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  73 */       e.printStackTrace();
/*     */     } finally {
/*  75 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void formatDate(Map<String, String> mp)
/*     */   {
/*  91 */     String s_day = (String)mp.get("start_day");
/*  92 */     String e_day = (String)mp.get("end_day");
/*     */ 
/*  95 */     if (e_day == null) {
/*  96 */       return;
/*     */     }
/*     */ 
/*  99 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/* 101 */       Date date = sdf.parse(e_day);
/* 102 */       Date s_date = sdf.parse(s_day);
/* 103 */       e_day = sdf.format(date) + " 23:59:59";
/* 104 */       s_day = sdf.format(s_date) + " 00:00:01";
/*     */     }
/*     */     catch (ParseException pex) {
/* 107 */       e_day = sdf.format(new Date()) + " 23:59:59";
/* 108 */       s_day = sdf.format(new Date()) + " 00:00:01";
/*     */     }
/*     */ 
/* 111 */     mp.remove("end_day");
/* 112 */     mp.put("end_day", e_day);
/* 113 */     mp.remove("start_day");
/* 114 */     mp.put("start_day", s_day);
/*     */   }
/*     */ 
/*     */   public static String cmsInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/* 128 */       String[] fileStr = CmsCountExcelOut.getFileUrl();
/*     */ 
/* 131 */       int size = headerList.size();
/* 132 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 135 */       String[][] data = new String[listBean.size()][5];
/* 136 */       for (int i = 0; i < listBean.size(); i++) {
/* 137 */         CmsCountBean countBean = (CmsCountBean)listBean.get(i);
/* 138 */         data[i][0] = countBean.getCat_name();
/* 139 */         data[i][1] = String.valueOf(countBean.getAllCount());
/* 140 */         data[i][2] = String.valueOf(countBean.getHostInfoCount());
/* 141 */         data[i][3] = String.valueOf(countBean.getRefInfoCount());
/* 142 */         data[i][4] = String.valueOf(countBean.getLinkInfoCount());
/*     */       }
/* 144 */       OutExcel oe = new OutExcel("站点信息统计表");
/* 145 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/* 147 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 149 */       e.printStackTrace();
/* 150 */     }return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.countsource.CmsCountSourceService
 * JD-Core Version:    0.6.2
 */