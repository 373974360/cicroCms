/*     */ package com.cicro.wcm.services.appeal.count;
/*     */ 
/*     */ import java.io.File;
/*     */ import jxl.Workbook;
/*     */ import jxl.write.Label;
/*     */ import jxl.write.WritableSheet;
/*     */ import jxl.write.WritableWorkbook;
/*     */ 
/*     */ public class OutExcel
/*     */ {
/*     */   private String SHEET;
/*     */   private int SHEETCOUNT;
/*     */ 
/*     */   public OutExcel()
/*     */   {
/*  19 */     this.SHEET = "Sheet";
/*  20 */     this.SHEETCOUNT = 0;
/*     */   }
/*     */ 
/*     */   public OutExcel(String sheet)
/*     */   {
/*  26 */     this.SHEET = sheet;
/*  27 */     this.SHEETCOUNT = 0;
/*     */   }
/*     */ 
/*     */   public OutExcel(String sheet, int count)
/*     */   {
/*  33 */     this.SHEET = sheet;
/*  34 */     this.SHEETCOUNT = count;
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[][] excel)
/*     */   {
/*     */     try
/*     */     {
/*  45 */       File fl = new File(fileName);
/*  46 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/*  47 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*  48 */       Label labelC = null;
/*  49 */       int line = excel.length;
/*  50 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/*  52 */         int row = excel[i].length;
/*  53 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/*  55 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/*  57 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/*  61 */       wwb.write();
/*  62 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  66 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[] head, String[][] content)
/*     */   {
/*     */     try
/*     */     {
/*  79 */       File fl = new File(fileName);
/*  80 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/*  81 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*  82 */       Label labelC = null;
/*  83 */       for (int k = 0; k < head.length; k++)
/*     */       {
/*  85 */         labelC = new Label(k, 0, head[k]);
/*     */ 
/*  87 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/*  90 */       int line = content.length;
/*  91 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/*  93 */         int row = content[i].length;
/*  94 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/*  96 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/*  98 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 102 */       wwb.write();
/* 103 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 107 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut(String fileName, String[][] head, String[][] content)
/*     */   {
/*     */     try
/*     */     {
/* 120 */       File fl = new File(fileName);
/* 121 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 122 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/* 123 */       Label labelC = null;
/* 124 */       for (int k = 0; k < head[0].length; k++)
/*     */       {
/* 126 */         labelC = new Label(k, 0, head[0][k]);
/*     */ 
/* 128 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 131 */       int line = content.length;
/* 132 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 134 */         int row = content[i].length;
/* 135 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 137 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 139 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 143 */       wwb.write();
/* 144 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 148 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut2(String fileName, String[] head, String[][] content, int row2)
/*     */   {
/*     */     try
/*     */     {
/* 163 */       File fl = new File(fileName);
/* 164 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 165 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 169 */       int totalRows = content.length;
/* 170 */       for (int i = 0; i < totalRows / row2; i++) {
/* 171 */         if (i == 0)
/* 172 */           ws.mergeCells(0, 1, 0, row2);
/*     */         else {
/* 174 */           ws.mergeCells(0, i * row2 + 1, 0, i * row2 + row2);
/*     */         }
/*     */       }
/*     */ 
/* 178 */       Label labelC = null;
/* 179 */       for (int k = 0; k < head.length; k++)
/*     */       {
/* 181 */         labelC = new Label(k, 0, head[k]);
/*     */ 
/* 183 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 186 */       int line = content.length;
/* 187 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 189 */         int row = content[i].length;
/* 190 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 192 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 194 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 198 */       wwb.write();
/* 199 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 203 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOut3(String fileName, String[] head, String[][] content, int col2)
/*     */   {
/*     */     try
/*     */     {
/* 218 */       File fl = new File(fileName);
/* 219 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 220 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 222 */       ws.mergeCells(0, 0, 0, 1);
/*     */ 
/* 224 */       int totalCols = head.length - 1;
/* 225 */       for (int i = 0; i < totalCols / col2; i++) {
/* 226 */         if (i == 0)
/* 227 */           ws.mergeCells(1, 0, col2, 0);
/*     */         else {
/* 229 */           ws.mergeCells(i * col2 + 1, 0, i * col2 + col2, 0);
/*     */         }
/*     */       }
/*     */ 
/* 233 */       Label labelC = null;
/* 234 */       for (int k = 0; k < head.length; k++)
/*     */       {
/* 236 */         labelC = new Label(k, 0, head[k]);
/*     */ 
/* 238 */         ws.addCell(labelC);
/*     */       }
/*     */ 
/* 241 */       int line = content.length;
/* 242 */       for (int i = 0; (i < line) && (content != null); i++)
/*     */       {
/* 244 */         int row = content[i].length;
/* 245 */         for (int j = 0; (j < row) && (content[i] != null); j++)
/*     */         {
/* 247 */           labelC = new Label(j, i + 1, content[i][j]);
/*     */ 
/* 249 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 253 */       wwb.write();
/* 254 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 258 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutDep(String fileName, String[][] excel, int column1)
/*     */   {
/*     */     try
/*     */     {
/* 270 */       File fl = new File(fileName);
/* 271 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 272 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 274 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 275 */       ws.mergeCells(0, 1, 0, 2);
/* 276 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 277 */       ws.mergeCells(1, 2, column1 - 1, 2);
/* 278 */       Label labelC = null;
/* 279 */       int line = excel.length;
/* 280 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 282 */         int row = excel[i].length;
/* 283 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 285 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 287 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 291 */       wwb.write();
/* 292 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 296 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutB(String fileName, String[][] excel, int column1)
/*     */   {
/*     */     try
/*     */     {
/* 305 */       File fl = new File(fileName);
/* 306 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 307 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 309 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 310 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 311 */       Label labelC = null;
/* 312 */       int line = excel.length;
/* 313 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 315 */         int row = excel[i].length;
/* 316 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 318 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 320 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 324 */       wwb.write();
/* 325 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 329 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutDepYear(String fileName, String[][] excel, int column1, int row2)
/*     */   {
/*     */     try
/*     */     {
/* 339 */       File fl = new File(fileName);
/* 340 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 341 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 344 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 345 */       ws.mergeCells(0, 1, 0, 2);
/* 346 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 347 */       ws.mergeCells(1, 2, column1 - 1, 2);
/*     */ 
/* 349 */       int totalRows = excel.length;
/* 350 */       for (int i = 0; i < totalRows - 4 / row2; i++) {
/* 351 */         if (i == 0)
/* 352 */           ws.mergeCells(0, 4, 0, row2 + 3);
/*     */         else {
/* 354 */           ws.mergeCells(0, i * row2 + 1 + 3, 0, i * row2 + row2 + 3);
/*     */         }
/*     */       }
/*     */ 
/* 358 */       Label labelC = null;
/* 359 */       int line = excel.length;
/* 360 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 362 */         int row = excel[i].length;
/* 363 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 365 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 367 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 371 */       wwb.write();
/* 372 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 376 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doOutDepRedYellow(String fileName, String[][] excel, int column1, int col2)
/*     */   {
/*     */     try
/*     */     {
/* 386 */       File fl = new File(fileName);
/* 387 */       WritableWorkbook wwb = Workbook.createWorkbook(fl);
/* 388 */       WritableSheet ws = wwb.createSheet(this.SHEET, this.SHEETCOUNT);
/*     */ 
/* 391 */       ws.mergeCells(1, 0, column1 - 1, 0);
/* 392 */       ws.mergeCells(1, 1, column1 - 1, 1);
/* 393 */       ws.mergeCells(0, 2, 0, 3);
/*     */ 
/* 395 */       int totalCols = excel[0].length - 1;
/* 396 */       for (int i = 0; i < totalCols / col2; i++) {
/* 397 */         if (i == 0)
/* 398 */           ws.mergeCells(1, 2, col2, 2);
/*     */         else {
/* 400 */           ws.mergeCells(i * col2 + 1, 2, i * col2 + col2, 2);
/*     */         }
/*     */       }
/*     */ 
/* 404 */       Label labelC = null;
/* 405 */       int line = excel.length;
/* 406 */       for (int i = 0; (i < line) && (excel != null); i++)
/*     */       {
/* 408 */         int row = excel[i].length;
/* 409 */         for (int j = 0; (j < row) && (excel[i] != null); j++)
/*     */         {
/* 411 */           labelC = new Label(j, i, excel[i][j]);
/*     */ 
/* 413 */           ws.addCell(labelC);
/*     */         }
/*     */       }
/*     */ 
/* 417 */       wwb.write();
/* 418 */       wwb.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 422 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.count.OutExcel
 * JD-Core Version:    0.6.2
 */