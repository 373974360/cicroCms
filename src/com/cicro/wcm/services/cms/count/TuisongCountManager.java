/*     */ package com.cicro.wcm.services.cms.count;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.count.SiteInfoTuisongBean;
/*     */ import com.cicro.wcm.bean.cms.count.TuisongCountBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.dao.cms.count.TuisongCountDao;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.appeal.count.CountUtil;
/*     */ import com.cicro.wcm.services.browserapi.BrowserAPIService;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;

/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TuisongCountManager
/*     */ {
/*     */   public static List<TuisongCountBean> getTuisongInfoCountByCat(Map<String, String> mp)
/*     */   {
/*  41 */     int cat_id = Integer.parseInt((String)mp.get("cat_id"));
/*  42 */     String site_id = (String)mp.get("site_id");
/*     */ 
/*  45 */     boolean isMinorNode = CategoryManager.isHasChildNode(cat_id, site_id);
/*  46 */     List ret = new ArrayList();
/*     */ 
/*  49 */     if (!isMinorNode) {
/*  50 */       TuisongCountBean bean = TuisongCountDao.getCountListByCat(mp);
/*  51 */       bean.setCat_id(cat_id);
/*  52 */       bean.setCat_name(CategoryManager.getCategoryBean(cat_id).getCat_cname());
/*  53 */       ret.add(bean);
/*     */     }
/*     */     else
/*     */     {
/*     */       List<CategoryBean> directSubNode;
/*  57 */       if (cat_id == 0)
/*  58 */         directSubNode = CategoryManager.getCategoryListBySiteID(site_id, 0);
/*     */       else {
/*  60 */         directSubNode = CategoryManager.getChildCategoryList(cat_id, site_id);
/*     */       }
/*  62 */       for (CategoryBean bean : directSubNode) {
/*  63 */         mp.remove("cat_id");
/*  64 */         String ids = CategoryManager.getAllChildCategoryIDS(bean.getCat_id(), site_id);
/*  65 */         if ((ids == null) || ("".equals(ids)))
/*  66 */           ids = bean.getCat_id()+"";
/*     */         else {
/*  68 */           ids = ids + "," + bean.getCat_id();
/*     */         }
/*  70 */         mp.put("cat_id", ids);
/*  71 */         TuisongCountBean retBean = TuisongCountDao.getCountListByCat(mp);
/*  72 */         retBean.setCat_id(bean.getCat_id());
/*  73 */         retBean.setCat_name(bean.getCat_cname());
/*  74 */         ret.add(retBean);
/*     */       }
/*     */     }
/*  77 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String tuiSongInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/*  88 */       String[] fileStr = getFileUrl();
/*     */ 
/*  90 */       int size = headerList.size();
/*  91 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/*  93 */       String[][] data = new String[listBean.size()][5];
/*  94 */       for (int i = 0; i < listBean.size(); i++)
/*     */       {
/*  96 */         TuisongCountBean countBean = (TuisongCountBean)listBean.get(i);
/*     */ 
/*  98 */         data[i][0] = countBean.getCat_name();
/*  99 */         data[i][1] = String.valueOf(countBean.getIs_host());
/* 100 */         data[i][2] = String.valueOf(countBean.getIsNot_host());
/*     */       }
/* 102 */       OutExcel oe = new OutExcel("站点栏目推送信息统计表");
/* 103 */       oe.doOut(fileStr[0], head, data);
/* 104 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 106 */       e.printStackTrace();
/* 107 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String[] getFileUrl()
/*     */   {
/* 114 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path");
/* 115 */     String path = FormatUtil.formatPath(root_path + "/cms/cmsCount/file/");
/* 116 */     CountUtil.deleteFile(path);
/*     */ 
/* 119 */     String nowDate = CountUtil.getNowDayDate();
/* 120 */     String fileTemp2 = FormatUtil.formatPath(path + File.separator + nowDate);
/* 121 */     File file2 = new File(fileTemp2);
/* 122 */     if (!file2.exists()) {
/* 123 */       file2.mkdir();
/*     */     }
/* 125 */     String nowTime = CountUtil.getNowDayDateTime();
/* 126 */     String xls = nowTime + CountUtil.getEnglish(1) + ".xls";
/* 127 */     String xlsFile = fileTemp2 + File.separator + xls;
/* 128 */     String urlFile = "/manager/cms/cmsCount/file/" + nowDate + File.separator + xls;
/* 129 */     String[] str = { xlsFile, urlFile };
/* 130 */     return str;
/*     */   }
/*     */ 
/*     */   public static List<SiteInfoTuisongBean> getOneSiteTuisCounts(Map<String, String> mp)
/*     */   {
/* 135 */     String start_day = (String)mp.get("start_day");
/* 136 */     if ((start_day != null) && (!"".equals(start_day))) {
/* 137 */       if (!start_day.contains(":")) {
/* 138 */         start_day = start_day + " 00:00:01";
/*     */       }
/* 140 */       mp.put("start_time", start_day);
/*     */     } else {
/* 142 */       mp.put("start_time", "2000-01-01 00:00:01");
/*     */     }
/* 144 */     String end_day = (String)mp.get("end_day");
/* 145 */     if ((end_day != null) && (!"".equals(end_day))) {
/* 146 */       if (!end_day.contains(":")) {
/* 147 */         end_day = start_day + " 23:59:59";
/*     */       }
/* 149 */       mp.put("end_time", end_day);
/*     */     } else {
/* 151 */       mp.put("end_time", DateUtil.getCurrentDateTime());
/*     */     }
/* 153 */     String atype = (String)mp.get("atype");
/* 154 */     if (atype == null) {
/* 155 */       atype = "";
/*     */     }
/* 157 */     if ("lastmonth".equals(atype))
/*     */     {
/* 159 */       mp.put("start_time", BrowserAPIService.getFirstDayByLastMonth());
/* 160 */       mp.put("end_time", BrowserAPIService.getLastDayByLastMonth());
/* 161 */     } else if ("currmonth".equals(atype)) {
/* 162 */       mp.put("start_time", BrowserAPIService.getFirstDayByCurrMonth());
/* 163 */       mp.put("end_time", BrowserAPIService.getLastDayByCurrMonth());
/*     */     }
/* 165 */     String site_id = (String)mp.get("site_id");
/* 166 */     List stsList = new ArrayList();
/* 167 */     if (((String)mp.get("app_id")).equals("cms"))
/*     */     {
/* 169 */       List sbl = SiteManager.getSiteChildListByID("HIWCMcgroup");
/* 170 */       for (int j = 0; j < sbl.size(); j++)
/*     */       {
/* 172 */         SiteInfoTuisongBean sitb = new SiteInfoTuisongBean();
/*     */ 
/* 174 */         int icount = 0;
/*     */ 
/* 177 */         List InfoList = DBManager.queryFList("info_count.getOneSiteInfoLists", mp);
/* 178 */         for (int i = 0; i < InfoList.size(); i++)
/*     */         {
/* 180 */           InfoBean mm = InfoBaseManager.getInfoById(((InfoBean)InfoList.get(i)).getFrom_id()+"");
/* 181 */           if (mm != null)
/*     */           {
/* 184 */             if (((SiteBean)sbl.get(j)).getSite_id().trim().equals(mm.getSite_id().trim()))
/*     */             {
/* 187 */               icount++;
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 193 */         sitb.setSite_id(((SiteBean)sbl.get(j)).getSite_id());
/* 194 */         sitb.setSite_name(((SiteBean)sbl.get(j)).getSite_name());
/* 195 */         sitb.setIcount(icount);
/*     */ 
/* 197 */         stsList.add(sitb);
/*     */       }
/*     */     }
/*     */     else {
/* 201 */       System.out.println(" \trun in tongji gongkai ");
/* 202 */       List gkl = GKNodeManager.getAllGKNodeList();
/* 203 */       for (int j = 0; j < gkl.size(); j++)
/*     */       {
/* 205 */         int icount1 = 0;
/* 206 */         SiteInfoTuisongBean sitb = new SiteInfoTuisongBean();
/* 207 */         List InfoList = DBManager.queryFList("info_count.getOneSiteInfoLists", mp);
/* 208 */         for (int i = 0; i < InfoList.size(); i++)
/*     */         {
/* 210 */           InfoBean infobean = InfoBaseManager.getInfoById(((InfoBean)InfoList.get(i)).getFrom_id()+"");
/* 211 */           if (infobean != null)
/*     */           {
/* 214 */             if (infobean.getSite_id().equals(((GKNodeBean)gkl.get(j)).getNode_id()))
/*     */             {
/* 216 */               icount1++;
/*     */             }
/*     */           }
/* 219 */           sitb.setSite_id(((GKNodeBean)gkl.get(j)).getNode_id());
/* 220 */           sitb.setSite_name(((GKNodeBean)gkl.get(j)).getNode_name());
/* 221 */           sitb.setIcount(icount1);
/*     */         }
/* 223 */         stsList.add(sitb);
/*     */       }
/*     */     }
/* 226 */     if ((stsList != null) && (stsList.size() > 0))
/* 227 */       Collections.sort(stsList, new countComparator());
/* 228 */     return stsList;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 253 */     Map mp = new HashMap();
/* 254 */     mp.put("site_id", "HIWCMdemo");
/* 255 */     mp.put("app_id", "zwgk");
/* 256 */     mp.put("start_time", "2010-01-01");
/* 257 */     mp.put("end_time", "2012-12-12 12:59:59");
/* 258 */     List l = getOneSiteTuisCounts(mp);
/* 259 */     for (int n = 0; n < l.size(); n++)
/*     */     {
/* 261 */       System.out.println(((SiteInfoTuisongBean)l.get(n)).getSite_name() + "=====count==" + ((SiteInfoTuisongBean)l.get(n)).getIcount());
/*     */     }
/*     */   }
/*     */ }

class countComparator
/*     */   implements Comparator<Object>
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/* 236 */     SiteInfoTuisongBean cgb1 = (SiteInfoTuisongBean)o1;
/* 237 */     SiteInfoTuisongBean cgb2 = (SiteInfoTuisongBean)o2;
/* 238 */     if (cgb1.getIcount() < cgb2.getIcount()) {
/* 239 */       return 1;
/*     */     }
/* 241 */     if (cgb1.getIcount() == cgb2.getIcount()) {
/* 242 */       return 0;
/*     */     }
/* 244 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.TuisongCountManager
 * JD-Core Version:    0.6.2
 */