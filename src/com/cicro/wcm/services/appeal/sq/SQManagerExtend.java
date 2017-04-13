/*     */ package com.cicro.wcm.services.appeal.sq;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ import com.cicro.wcm.dao.appeal.sq.SQDAO;
/*     */ import com.cicro.wcm.services.appeal.count.CountUtil;
/*     */ import com.cicro.wcm.services.appeal.purpose.PurposeManager;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SQManagerExtend
/*     */ {
/*     */   public static List<SQBean> getexportData(Map<String, String> m)
/*     */   {
/*  29 */     return SQDAO.getexportData(m);
/*     */   }
/*     */ 
/*     */   public static String[] getFileUrl()
/*     */   {
/*  34 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path");
/*  35 */     String path = FormatUtil.formatPath(root_path + "/cms/cmsCount/file/");
/*     */ 
/*  37 */     CountUtil.deleteFile(path);
/*     */ 
/*  39 */     String nowDate = CountUtil.getNowDayDate();
/*  40 */     String fileTemp2 = FormatUtil.formatPath(path + File.separator + nowDate);
/*     */ 
/*  42 */     File file2 = new File(fileTemp2);
/*  43 */     if (!file2.exists()) {
/*  44 */       file2.mkdir();
/*     */     }
/*  46 */     String nowTime = CountUtil.getNowDayDateTime();
/*  47 */     String xls = nowTime + CountUtil.getEnglish(1) + ".xls";
/*  48 */     String xlsFile = fileTemp2 + File.separator + xls;
/*  49 */     String urlFile = "/manager/cms/cmsCount/file/" + nowDate + File.separator + xls;
/*     */ 
/*  51 */     String[] str = { xlsFile, urlFile };
/*     */ 
/*  53 */     return str;
/*     */   }
/*     */ 
/*     */   public static String CateAccessCountsOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try {
/*  59 */       String[] fileStr = getFileUrl();
/*     */ 
/*  61 */       int size = headerList.size();
/*  62 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/*  64 */       String[][] data = new String[listBean.size()][12];
/*  65 */       for (int i = 0; i < listBean.size(); i++)
/*     */       {
/*  67 */         SQBean sqbean = (SQBean)listBean.get(i);
/*  68 */         data[i][0] = String.valueOf(sqbean.getSq_code());
/*  69 */         data[i][1] = String.valueOf(sqbean.getSq_realname());
/*  70 */         if (sqbean.getSq_sex() == 1)
/*     */         {
/*  72 */           data[i][2] = "男";
/*     */         }
/*  74 */         else data[i][2] = "女";
/*     */ 
/*  76 */         data[i][3] = String.valueOf(sqbean.getSq_phone());
/*  77 */         data[i][4] = String.valueOf(sqbean.getSq_card_id());
/*  78 */         data[i][5] = String.valueOf(sqbean.getSq_email());
/*  79 */         data[i][6] = String.valueOf(sqbean.getSq_vocation());
/*  80 */         data[i][7] = String.valueOf(sqbean.getSq_address());
/*  81 */         if (sqbean.getPur_id() > 0)
/*     */         {
/*  83 */           data[i][8] = String.valueOf(PurposeManager.getPurposeByID(sqbean.getPur_id()+"").getPur_name());
/*     */         }
/*  85 */         data[i][9] = String.valueOf(sqbean.getSq_title().replaceAll("</?[^<]+>", ""));
/*  86 */         data[i][10] = String.valueOf(sqbean.getSq_content().replaceAll("</?[^<]+>", ""));
/*  87 */         if (sqbean.getIs_open() == 1)
/*     */         {
/*  89 */           data[i][11] = "公开";
/*     */         }
/*  91 */         else data[i][11] = "不公开";
/*     */ 
/*     */       }
/*     */ 
/*  95 */       OutExcel oe = new OutExcel("诉求信息");
/*  96 */       oe.doOut(fileStr[0], head, data);
/*  97 */       return fileStr[1];
/*     */     } catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */     }return "";
/*     */   }
/*     */ 
/*     */   public static boolean batchIsNotOpenOk(Map<String, String> m)
/*     */   {
/* 112 */     return SQDAO.batchIsNotOpenOk(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.sq.SQManagerExtend
 * JD-Core Version:    0.6.2
 */