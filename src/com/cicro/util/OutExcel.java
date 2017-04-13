/*     */ package com.cicro.util;
/*     */ 
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import jxl.Workbook;
/*     */ import jxl.write.Label;
/*     */ import jxl.write.WritableSheet;
/*     */ import jxl.write.WritableWorkbook;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ 
/*     */ public class OutExcel
/*     */ {
/*     */   private String SHEET;
/*     */   private int SHEETCOUNT;
/*     */ 
/*     */   public OutExcel()
/*     */   {
/*  26 */     this.SHEET = "Sheet";
/*  27 */     this.SHEETCOUNT = 0;
/*     */   }
/*     */ 
/*     */   public OutExcel(String sheet)
/*     */   {
/*  33 */     this.SHEET = sheet;
/*  34 */     this.SHEETCOUNT = 0;
/*     */   }
/*     */ 
/*     */   public OutExcel(String sheet, int count)
/*     */   {
/*  40 */     this.SHEET = sheet;
/*  41 */     this.SHEETCOUNT = count;
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[][] excel)
/*     */   {
/*     */     try
/*     */     {
/*  52 */       File fl = new File(fileName);
/*  53 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/*  54 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*  55 */       Label labelC = null;
/*  56 */       int line = excel.length;
/*  57 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/*  59 */         int row = excel[i].length;
/*  60 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/*  62 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/*  64 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/*  68 */       wwb.write();
/*  69 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  73 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[][] excel, int cell_start, int cell_end)
/*     */   {
/*     */     try
/*     */     {
/*  87 */       File fl = new File(fileName);
/*  88 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/*  89 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*  90 */       ws.mergeCells(0, cell_start, 0, cell_end);
/*  91 */       Label labelC = null;
/*  92 */       int line = excel.length;
/*  93 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/*  95 */         int row = excel[i].length;
/*  96 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/*  98 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 100 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 104 */       wwb.write();
/* 105 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 109 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[] head, String[][] content)
/*     */   {
/*     */     try
/*     */     {
/* 122 */       File fl = new File(fileName);
/* 123 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 124 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/* 125 */       Label labelC = null;
/* 126 */       for (int k = 0; k < head.length; k++)
/*     */       {
/* 128 */         labelC = new Label(k, 0, head[k]);
/*     */ 
/* 130 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 133 */       int line = content.length;
/* 134 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 136 */         int row = content[i].length;
/* 137 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 139 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 141 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 145 */       wwb.write();
/* 146 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 150 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[] head, String[][] content, Map map)
/*     */   {
/*     */     try
/*     */     {
/* 165 */       File fl = new File(fileName);
/* 166 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 167 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/* 168 */       Label labelC = null;
/*     */ 
/* 170 */       ws.mergeCells(1, 0, head.length - 1, 0);
/* 171 */       int mun = 0;
/* 172 */       Iterator it = map.keySet().iterator();
/* 173 */       while (it.hasNext()) {
/* 174 */         String key = (String)it.next();
/* 175 */         String value = (String)map.get(key);
/* 176 */         labelC = new Label(0, mun, key);
/* 177 */         ws.addCell(labelC);
/* 178 */         labelC = new Label(1, mun, value);
/* 179 */         ws.addCell(labelC);
/* 180 */         mun++;
/*     */       }
/*     */ 
/* 184 */       for (int k = 0; k < head.length; k++)
/*     */       {
/* 186 */         labelC = new Label(k, mun, head[k]);
/*     */ 
/* 188 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 191 */       int line = content.length;
/* 192 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 194 */         int row = content[i].length;
/* 195 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 197 */           labelC = new Label(j, i + mun + 1, content[i][j]);
/*     */ 
/* 199 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 203 */       wwb.write();
/* 204 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 208 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[][] head, String[][] content)
/*     */   {
/*     */     try
/*     */     {
/* 221 */       File fl = new File(fileName);
/* 222 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 223 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/* 224 */       Label labelC = null;
/* 225 */       for (int k = 0; k < head[0].length; k++)
/*     */       {
/* 227 */         labelC = new Label(k, 0, head[0][k]);
/*     */ 
/* 229 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 232 */       int line = content.length;
/* 233 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 235 */         int row = content[i].length;
/* 236 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 238 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 240 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 244 */       wwb.write();
/* 245 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 249 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut2(String fileName, String[] head, String[][] content, int row2)
/*     */   {
/*     */     try
/*     */     {
/* 264 */       File fl = new File(fileName);
/* 265 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 266 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 270 */       int totalRows = content.length;
/* 271 */       for (int i = 0; i < totalRows / row2; i++) {
/* 272 */         if (i == 0)
/* 273 */           ws.mergeCells(0, 1, 0, row2);
/*     */         else {
/* 275 */           ws.mergeCells(0, i * row2 + 1, 0, i * row2 + row2);
/*     */         }
/*     */       }
/*     */ 
/* 279 */       Label labelC = null;
/* 280 */       for (int k = 0; k < head.length; k++)
/*     */       {
/* 282 */         labelC = new Label(k, 0, head[k]);
/*     */ 
/* 284 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 287 */       int line = content.length;
/* 288 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 290 */         int row = content[i].length;
/* 291 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 293 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 295 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 299 */       wwb.write();
/* 300 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 304 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut3(String fileName, String[] head, String[][] content, int col2)
/*     */   {
/*     */     try
/*     */     {
/* 319 */       File fl = new File(fileName);
/* 320 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 321 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 323 */       ws.mergeCells(0, 0, 0, 1);
/*     */ 
/* 325 */       int totalCols = head.length - 1;
/* 326 */       for (int i = 0; i < totalCols / col2; i++) {
/* 327 */         if (i == 0)
/* 328 */           ws.mergeCells(1, 0, col2, 0);
/*     */         else {
/* 330 */           ws.mergeCells(i * col2 + 1, 0, i * col2 + col2, 0);
/*     */         }
/*     */       }
/*     */ 
/* 334 */       Label labelC = null;
/* 335 */       for (int k = 0; k < head.length; k++)
/*     */       {
/* 337 */         labelC = new Label(k, 0, head[k]);
/*     */ 
/* 339 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 342 */       int line = content.length;
/* 343 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 345 */         int row = content[i].length;
/* 346 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 348 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 350 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 354 */       wwb.write();
/* 355 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 359 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutDep(String fileName, String[][] excel, int column1)
/*     */   {
/*     */     try
/*     */     {
/* 371 */       File fl = new File(fileName);
/* 372 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 373 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 375 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 376 */       ws.mergeCells(0, 1, 0, 2);
/* 377 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 378 */       ws.mergeCells(1, 2, column1 - 1, 2);
/* 379 */       Label labelC = null;
/* 380 */       int line = excel.length;
/* 381 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 383 */         int row = excel[i].length;
/* 384 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 386 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 388 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 392 */       wwb.write();
/* 393 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 397 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutB(String fileName, String[][] excel, int column1)
/*     */   {
/*     */     try
/*     */     {
/* 406 */       File fl = new File(fileName);
/* 407 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 408 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 410 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 411 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 412 */       Label labelC = null;
/* 413 */       int line = excel.length;
/* 414 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 416 */         int row = excel[i].length;
/* 417 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 419 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 421 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 425 */       wwb.write();
/* 426 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 430 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutDepYear(String fileName, String[][] excel, int column1, int row2)
/*     */   {
/*     */     try
/*     */     {
/* 440 */       File fl = new File(fileName);
/* 441 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 442 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 445 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 446 */       ws.mergeCells(0, 1, 0, 2);
/* 447 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 448 */       ws.mergeCells(1, 2, column1 - 1, 2);
/*     */ 
/* 450 */       int totalRows = excel.length;
/* 451 */       for (int i = 0; i < totalRows - 4 / row2; i++) {
/* 452 */         if (i == 0)
/* 453 */           ws.mergeCells(0, 4, 0, row2 + 3);
/*     */         else {
/* 455 */           ws.mergeCells(0, i * row2 + 1 + 3, 0, i * row2 + row2 + 3);
/*     */         }
/*     */       }
/*     */ 
/* 459 */       Label labelC = null;
/* 460 */       int line = excel.length;
/* 461 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 463 */         int row = excel[i].length;
/* 464 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 466 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 468 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 472 */       wwb.write();
/* 473 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 477 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutDepRedYellow(String fileName, String[][] excel, int column1, int col2)
/*     */   {
/*     */     try
/*     */     {
/* 487 */       File fl = new File(fileName);
/* 488 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 489 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 492 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 493 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 494 */       ws.mergeCells(0, 2, 0, 3);
/*     */ 
/* 496 */       int totalCols = excel[0].length - 1;
/* 497 */       for (int i = 0; i < totalCols / col2; i++) {
/* 498 */         if (i == 0)
/* 499 */           ws.mergeCells(1, 2, col2, 2);
/*     */         else {
/* 501 */           ws.mergeCells(i * col2 + 1, 2, i * col2 + col2, 2);
/*     */         }
/*     */       }
/*     */ 
/* 505 */       Label labelC = null;
/* 506 */       int line = excel.length;
/* 507 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 509 */         int row = excel[i].length;
/* 510 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 512 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 514 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 518 */       wwb.write();
/* 519 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 523 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void deleteFile(String path)
/*     */   {
/*     */     try
/*     */     {
/* 535 */       File fileRoot = new File(path);
/* 536 */       if (fileRoot.exists()) {
/* 537 */         File[] fileAll = fileRoot.listFiles();
/* 538 */         for (File file : fileAll) {
/* 539 */           if ((file.isDirectory()) && 
/* 540 */             (file.getName().startsWith("2"))) {
/* 541 */             String nowDate = DateUtil.getCurrentDate();
/* 542 */             if (!file.getName().equals(nowDate))
/* 543 */               FileOperation.deleteDir(file.getPath());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 550 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutTree(String fileName, String[] head, String[][] content, int countnum)
/*     */   {
/*     */     try
/*     */     {
/* 566 */       File fl = new File(fileName);
/* 567 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 568 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 570 */       if (content == null) {
/* 571 */         return;
/*     */       }
/* 573 */       int clos = content[0].length;
/*     */ 
/* 575 */       ws.mergeCells(0, 0, countnum - 1, 0);
/* 576 */       int num = 1;
/* 577 */       Label labelC = null;
/* 578 */       for (int k = 0; k < clos; k++)
/*     */       {
/* 580 */         if (k == 0) {
/* 581 */           labelC = new Label(0, 0, head[k]);
/* 582 */           ws.addCell(labelC);
/* 583 */         } else if (k >= countnum)
/*     */         {
/* 585 */           labelC = new Label(k, 0, head[num]);
/* 586 */           num++;
/* 587 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 591 */       int line = content.length;
/* 592 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 594 */         int row = content[i].length;
/* 595 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 597 */           labelC = new Label(j, i + 1, content[i][j]);
/* 598 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/* 601 */       wwb.write();
/* 602 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 606 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 626 */     String strTree = "[{\"b_count\": 0, \"z_count\": 28, \"site_id\": \"\", \"is_leaf\": false, \"site_name\": \"\", \"cat_name\": \"概况信息\", \"cat_id\": 1834, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 28, \"child_list\": [{\"b_count\": 0, \"z_count\": 13, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"机构职能\", \"cat_id\": 1835, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 13, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 6, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"领导信息\", \"cat_id\": 1836, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 6, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 3, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"内设科室\", \"cat_id\": 1837, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 3, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 6, \"site_id\": \"\", \"is_leaf\": false, \"site_name\": \"\", \"cat_name\": \"地区概况\", \"cat_id\": 1838, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 6, \"child_list\": [{\"b_count\": 0, \"z_count\": 13, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"机构职能1\", \"cat_id\": 1835, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 13, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 6, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"领导信息1\", \"cat_id\": 1836, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 6, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 3, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"内设科室\", \"cat_id\": 1837, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 3, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 6, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"地区概况1\", \"cat_id\": 1838, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 6, \"child_list\": null}]}]},{\"b_count\": 0, \"z_count\": 10, \"site_id\": \"\", \"is_leaf\": false, \"site_name\": \"\", \"cat_name\": \"法规公文\", \"cat_id\": 1839, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 10, \"child_list\": [{\"b_count\": 0, \"z_count\": 10, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"法规\", \"cat_id\": 1840, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 10, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 0, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"法规\", \"cat_id\": 1841, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 0, \"child_list\": null}, {\"b_count\": 0, \"z_count\": 0, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"公文\", \"cat_id\": 1842, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 0, \"child_list\": null}]},{\"b_count\": 0, \"z_count\": 3, \"site_id\": \"\", \"is_leaf\": true, \"site_name\": \"\", \"cat_name\": \"推送测试\", \"cat_id\": 2039, \"update_time\": \"\", \"y_count\": 0, \"javaClass\": \"com.cicro.wcm.bean.zwgk.count.GKCountBean\", \"app_id\": \"\", \"info_count\": 3, \"child_list\": null}]";
/* 627 */     JSONArray jsonArray = JSONArray.fromObject(strTree);
/* 628 */     Iterator it = jsonArray.iterator();
/* 629 */     Map map = new HashMap();
/* 630 */     List list = new ArrayList();
/* 631 */     map.put("numcount", Integer.valueOf(1));
/* 632 */     while (it.hasNext()) {
/* 633 */       JSONObject jsonObject = (JSONObject)it.next();
/* 634 */       int mun = 1;
/* 635 */       doOutTreeBean(jsonObject, mun, map, list);
/*     */     }
/* 637 */     System.out.println("numcount = " + map.get("numcount").toString());
/* 638 */     System.out.println("list.size()==" + list.size());
/*     */ 
/* 640 */     List listResult = new ArrayList();
/* 641 */     int numcount = Integer.valueOf(map.get("numcount").toString()).intValue();
/* 642 */     for (int i = 0; i < list.size(); i++) {
/* 643 */       Map mapO = (Map)list.get(i);
/* 644 */       int numO = Integer.valueOf(mapO.get("num").toString()).intValue();
/* 645 */       String cat_name = mapO.get("cat_name").toString();
/* 646 */       List listCur = new ArrayList();
/* 647 */       for (int j = 1; j <= numcount; j++) {
/* 648 */         if (j == numO)
/* 649 */           listCur.add(cat_name);
/*     */         else {
/* 651 */           listCur.add("");
/*     */         }
/*     */       }
/* 654 */       listCur.add("10");
/* 655 */       listCur.add("10");
/* 656 */       listCur.add("10");
/* 657 */       listCur.add("10");
/* 658 */       listResult.add(listCur);
/*     */     }
/* 660 */     String[][] data = new String[listResult.size()][numcount + 4];
/* 661 */     for (int i = 0; i < listResult.size(); i++) {
/* 662 */       List listCur = (List)listResult.get(i);
/* 663 */       for (int j = 0; j < numcount + 4; j++) {
/* 664 */         data[i][j] = ((String)listCur.get(j));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 669 */     String[] head = { "栏目名称", "信息总数", "主动公开数目", "依申请公开数目", "不公开数目" };
/* 670 */     OutExcel oe = new OutExcel("业务统计表");
/* 671 */     oe.doOutTree("C:\\aa.xls", head, data, numcount);
/*     */   }
/*     */ 
/*     */   public static void setNumCount(Map map, int num)
/*     */   {
/* 676 */     if (num > Integer.valueOf(map.get("numcount").toString()).intValue())
/* 677 */       map.put("numcount", Integer.valueOf(num));
/*     */   }
/*     */ 
/*     */   public static void doOutTreeBean(JSONObject jsonObject, int num, Map map, List list)
/*     */   {
/* 682 */     String is_leaf = String.valueOf(jsonObject.get("is_leaf"));
/* 683 */     if (is_leaf.equals("true")) {
/* 684 */       for (int i = 1; i < num; i++) {
/* 685 */         System.out.print("  ");
/*     */       }
/* 687 */       System.out.print(String.valueOf(num) + jsonObject.get("cat_name"));
/* 688 */       Map map1 = new HashMap();
/* 689 */       map1.put("num", Integer.valueOf(num));
/* 690 */       map1.put("cat_name", jsonObject.get("cat_name"));
/* 691 */       list.add(map1);
/* 692 */       System.out.println();
/*     */     } else {
/* 694 */       for (int i = 1; i < num; i++) {
/* 695 */         System.out.print("  ");
/*     */       }
/* 697 */       System.out.print(String.valueOf(num) + jsonObject.get("cat_name"));
/* 698 */       Map map1 = new HashMap();
/* 699 */       map1.put("num", Integer.valueOf(num));
/* 700 */       map1.put("cat_name", jsonObject.get("cat_name"));
/* 701 */       list.add(map1);
/* 702 */       System.out.println();
/* 703 */       JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("child_list"));
/* 704 */       if ((jsonArray != null) && (!"".equals(jsonArray)) && (jsonArray.size() > 0)) {
/* 705 */         num++;
/* 706 */         setNumCount(map, num);
/* 707 */         Iterator it = jsonArray.iterator();
/* 708 */         while (it.hasNext()) {
/* 709 */           JSONObject jsonObject2 = (JSONObject)it.next();
/*     */ 
/* 711 */           if (jsonObject2 != null)
/* 712 */             doOutTreeBean(jsonObject2, num, map, list);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.OutExcel
 * JD-Core Version:    0.6.2
 */