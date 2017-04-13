/*     */ package com.cicro.wcm.services.extendfunction.Excle3pData;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.info.ArticleBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ import jxl.Workbook;
/*     */ 
/*     */ public class ExcleDataManager
/*     */ {
/*     */   public static boolean writeExcelData(String file_name, String site_id, SettingLogsBean stl)
/*     */   {
/*  28 */     Workbook book = null;
/*     */     try {
/*  30 */       book = Workbook.getWorkbook(new File(file_name));
/*     */     } catch (Exception e) {
/*  32 */       System.out.println(e);
/*  33 */       return false;
/*     */     }
/*  35 */     if (book != null)
/*     */     {
/*  38 */       Sheet sheet = book.getSheet(0);
/*  39 */       int rows = sheet.getRows();
/*  40 */       int clos = sheet.getColumns();
/*  41 */       for (int i = 1; i < rows; i++)
/*     */       {
/*  43 */         InfoBean b = new InfoBean();
/*  44 */         ArticleBean a = new ArticleBean();
/*     */ 
/*  46 */         String title = "";
/*  47 */         String content = "";
/*  48 */         String author = "";
/*  49 */         String add_time = "";
/*  50 */         String from_url = "";
/*  51 */         String source = "";
/*  52 */         String cat_id = "";
/*     */ 
/*  54 */         for (int n = 0; n < 7; n++)
/*     */         {
/*  56 */           if (sheet.getRow(i)[1].getContents() != null)
/*     */           {
/*  58 */             title = sheet.getRow(i)[1].getContents();
/*     */           }
/*  60 */           if (sheet.getRow(i)[2].getContents() != null)
/*     */           {
/*  62 */             content = sheet.getRow(i)[2].getContents();
/*     */           }
/*  64 */           if (sheet.getRow(i)[3].getContents() != null)
/*     */           {
/*  66 */             author = sheet.getRow(i)[3].getContents();
/*     */           }
/*  68 */           if (sheet.getRow(i)[4].getContents() != null)
/*     */           {
/*  70 */             add_time = sheet.getRow(i)[4].getContents();
/*     */           }
/*  72 */           if (sheet.getRow(i)[5].getContents() != null)
/*     */           {
/*  74 */             source = sheet.getRow(i)[5].getContents();
/*     */           }
/*  76 */           if (sheet.getRow(i)[6].getContents() != null)
/*     */           {
/*  78 */             cat_id = sheet.getRow(i)[6].getContents();
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*  88 */         if (sheet.getRow(i)[7].getContents() != null)
/*     */         {
/*  90 */           from_url = sheet.getRow(i)[7].getContents();
/*     */         }
/*     */ 
/*  93 */         String count = ExcleDataDAO.checkIsExit(from_url);
/*  94 */         if ((count == "") || (count == null)) {
/*  95 */           count = "0";
/*     */         }
/*  97 */         if (count.equals("0"))
/*     */         {
/*  99 */           int info_id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_TABLE_NAME);
/* 100 */           CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), site_id);
/*     */ 
/* 102 */           b.setId(info_id);
/* 103 */           b.setInfo_id(info_id);
/* 104 */           b.setTitle(title);
/* 105 */           b.setInput_dtime(add_time);
/* 106 */           b.setAuthor(author);
/* 107 */           b.setFrom_id(0);
/* 108 */           b.setModel_id(11);
/* 109 */           b.setWeight(60);
/* 110 */           b.setSource(source);
/* 111 */           b.setSite_id(site_id);
/* 112 */           b.setCat_cname(cb.getCat_cname());
/* 113 */           b.setCat_id(Integer.parseInt(cat_id));
/* 114 */           b.setInfo_status(4);
/* 115 */           b.setContent_url("");
/* 116 */           b.setPage_count(1);
/* 117 */           b.setApp_id("cms");
/*     */ 
/* 119 */           a.setId(info_id);
/* 120 */           a.setInfo_content(content);
/* 121 */           a.setInfo_id(info_id);
/* 122 */           a.setWord_count(content.length());
/* 123 */           a.setPrepage_wcount(1000);
/*     */ 
/* 125 */           Map m = new HashMap();
/* 126 */           m.put("info_id", info_id);
/* 127 */           m.put("form_url", from_url);
/*     */ 
/* 129 */           InfoBaseManager.changeInfoStatus(b);
/*     */ 
/* 131 */           if (addArticleAndInfo(b, a, stl))
/*     */           {
/* 133 */             ExcleDataBean exb = new ExcleDataBean();
/* 134 */             exb.setAdd_time(DateUtil.getCurrentDateTime());
/* 135 */             exb.setFrom_url(from_url);
/* 136 */             exb.setId(PublicTableDAO.getIDByTableName("cs_info_excleinfo"));
/* 137 */             exb.setInfo_id(info_id+"");
/* 138 */             exb.setSite_id(site_id);
/* 139 */             exb.setCat_id(cat_id);
/*     */ 
/* 141 */             insertExcleInfoMation(exb);
/* 142 */             System.out.println("第" + i + "=================条excle信息写入success ！");
/*     */           } else {
/* 144 */             System.out.println("第" + i + "=================条excle信息写入faild ！");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 151 */       book.close();
/* 152 */       return true;
/*     */     }
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertExcleInfoMation(ExcleDataBean exb)
/*     */   {
/* 159 */     return ExcleDataDAO.insertExcleInfoMation(exb);
/*     */   }
/*     */ 
/*     */   public static boolean addArticleAndInfo(InfoBean info, ArticleBean article, SettingLogsBean stl)
/*     */   {
/* 164 */     if (addInfo(info, stl)) {
/* 165 */       if (DBManager.insert("article_sql.addArticle", article)) {
/* 166 */         PublicTableDAO.insertSettingLogs("添加", "主信息内容", article.getInfo_id()+"", stl);
/* 167 */         return true;
/*     */       }
/* 169 */       return false;
/*     */     }
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addInfo(InfoBean info, SettingLogsBean stl) {
/* 175 */     if (DBManager.insert("baseInfomation.addInfo", info))
/*     */     {
/* 177 */       PublicTableDAO.insertSettingLogs("添加", "主信息", info.getInfo_id()+"", stl);
/* 178 */       return true;
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteExcleInfo(Map<String, String> map, SettingLogsBean stl) {
/* 184 */     return ExcleDataDAO.deleteExcleInfo(map, stl);
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.Excle3pData.ExcleDataManager
 * JD-Core Version:    0.6.2
 */