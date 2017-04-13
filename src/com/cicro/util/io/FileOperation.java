/*     */ package com.cicro.util.io;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FileOperation
/*     */ {
/*     */   public static boolean copyFile(String strSource, String strTarget, boolean blnOverWrite)
/*     */     throws IOException
/*     */   {
/*  50 */     File fSource = new File(strSource);
/*  51 */     File fTarget = new File(strTarget);
/*     */ 
/*  53 */     return copyFile(fSource, fTarget, blnOverWrite);
/*     */   }
/*     */ 
/*     */   public static boolean copyFile(File fSource, File fTarget, boolean blnOverWrite)
/*     */     throws IOException
/*     */   {
/*  80 */     if ((fTarget.exists()) && (!blnOverWrite)) {
/*  81 */       return true;
/*     */     }
/*     */ 
/*  84 */     File fParent = new File(fTarget.getParent());
/*  85 */     if (!fParent.exists()) {
/*  86 */       fParent.mkdirs();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  91 */       InputStream is = new BufferedInputStream(new FileInputStream(
/*  92 */         fSource));
/*     */ 
/*  94 */       if (!fTarget.exists()) {
/*  95 */         fTarget.createNewFile();
/*     */       }
/*     */ 
/*  99 */       OutputStream os = new BufferedOutputStream(new FileOutputStream(
/* 100 */         fTarget));
/*     */ 
/* 103 */       byte[] buffer = new byte[4096];
/*     */       int bytes;
/* 104 */       while ((bytes = is.read(buffer)) != -1)
/*     */       {
/*     */         int bytes;
/* 105 */         os.write(buffer, 0, bytes);
/*     */       }
/*     */ 
/* 108 */       is.close();
/* 109 */       os.close();
/* 110 */       return true;
/*     */     } catch (Exception ex) {
/* 112 */       ex.printStackTrace(System.out);
/* 113 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean copyDir(String strSource, String strTarget, boolean blnOverWrite)
/*     */     throws IOException
/*     */   {
/* 141 */     boolean isWildcard = false;
/* 142 */     if (strSource.endsWith("*")) {
/* 143 */       isWildcard = true;
/* 144 */       strSource = strSource.substring(0, strSource.length() - 2);
/*     */     }
/*     */ 
/* 147 */     if (File.separator.equals("\\")) {
/* 148 */       strSource = strSource.replaceAll("/", "\\\\");
/* 149 */       strTarget = strTarget.replaceAll("/", "\\\\");
/*     */     }
/*     */ 
/* 152 */     File fSource = new File(strSource);
/*     */ 
/* 155 */     String[] arr = getFileList(strSource);
/*     */ 
/* 158 */     String strBaseDir = fSource.getParent();
/* 159 */     int iLength = strBaseDir.length();
/*     */ 
/* 161 */     if (isWildcard) {
/* 162 */       iLength += fSource.getName().length();
/*     */     }
/*     */ 
/* 166 */     int iCount = arr.length;
/* 167 */     int iErr = 0;
/* 168 */     File file = null;
/*     */ 
/* 171 */     for (int i = 0; i < iCount; i++) {
/* 172 */       file = new File(arr[i]);
/* 173 */       if (file.isDirectory())
/*     */       {
/* 175 */         String strT = strTarget + File.separator + 
/* 176 */           arr[i].substring(iLength, arr[i].length());
/* 177 */         file = new File(strT);
/* 178 */         if (!file.exists()) {
/* 179 */           iErr += (file.mkdirs() ? 0 : 1);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 185 */     for (int i = 0; i < iCount; i++) {
/* 186 */       file = new File(arr[i]);
/* 187 */       if (file.isFile())
/*     */       {
/* 189 */         String strT = strTarget + File.separator + 
/* 190 */           arr[i].substring(iLength, arr[i].length());
/* 191 */         iErr += (copyFile(arr[i], strT, blnOverWrite) ? 0 : 1);
/*     */       }
/*     */     }
/* 194 */     return iErr == 0;
/*     */   }
/*     */ 
/*     */   public static boolean moveFile(String strSource, String strTarget, boolean blnOverWrite)
/*     */     throws IOException
/*     */   {
/* 212 */     File fSource = new File(strSource);
/* 213 */     File fTarget = new File(strTarget);
/*     */ 
/* 215 */     return moveFile(fSource, fTarget, blnOverWrite);
/*     */   }
/*     */ 
/*     */   public static boolean moveFile(File fSource, File fTarget, boolean blnOverWrite)
/*     */     throws IOException
/*     */   {
/* 235 */     boolean bln = copyFile(fSource, fTarget, blnOverWrite);
/* 236 */     if (!bln) {
/* 237 */       return false;
/*     */     }
/*     */ 
/* 240 */     fSource.deleteOnExit();
/* 241 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean moveDir(String strSource, String strTarget, boolean blnOverWrite)
/*     */     throws IOException
/*     */   {
/* 260 */     File fSource = new File(strSource);
/* 261 */     boolean bln = copyDir(strSource, strTarget, blnOverWrite);
/* 262 */     if (!bln) {
/* 263 */       return false;
/*     */     }
/*     */ 
/* 266 */     fSource.deleteOnExit();
/* 267 */     return true;
/*     */   }
/*     */ 
/*     */   public static String readFileToString(String strFile)
/*     */     throws IOException
/*     */   {
/* 281 */     File file = new File(strFile);
/* 282 */     return readFileToString(file);
/*     */   }
/*     */ 
/*     */   public static String readFileToString(File file)
/*     */     throws IOException
/*     */   {
/* 297 */     String strR = null;
/* 298 */     InputStream is = new BufferedInputStream(new FileInputStream(file));
/* 299 */     int iLength = is.available();
/* 300 */     byte[] b = new byte[iLength];
/* 301 */     is.read(b);
/* 302 */     is.close();
/* 303 */     strR = new String(b);
/* 304 */     return strR;
/*     */   }
/*     */ 
/*     */   public static byte[] readFileToBytes(File file)
/*     */     throws IOException
/*     */   {
/* 314 */     InputStream is = new BufferedInputStream(new FileInputStream(file));
/* 315 */     int iLength = is.available();
/* 316 */     byte[] b = new byte[iLength];
/* 317 */     is.read(b);
/* 318 */     is.close();
/* 319 */     return b;
/*     */   }
/*     */ 
/*     */   public static byte[] readFileToBytes(String strFile)
/*     */     throws IOException
/*     */   {
/* 329 */     File file = new File(strFile);
/* 330 */     return readFileToBytes(file);
/*     */   }
/*     */ 
/*     */   public static String readFileToString(File file, String charsetName)
/*     */     throws IOException
/*     */   {
/* 346 */     String strR = null;
/* 347 */     InputStream is = new BufferedInputStream(new FileInputStream(file));
/* 348 */     int iLength = is.available();
/* 349 */     byte[] b = new byte[iLength];
/* 350 */     is.read(b);
/* 351 */     is.close();
/* 352 */     strR = new String(b, charsetName);
/* 353 */     return strR;
/*     */   }
/*     */ 
/*     */   public static String readFileToString(String strFile, String charsetName)
/*     */     throws IOException
/*     */   {
/* 369 */     File file = new File(strFile);
/* 370 */     return readFileToString(file, charsetName);
/*     */   }
/*     */ 
/*     */   public static boolean writeStringToFile(String strFile, String strContent, boolean blnAppend)
/*     */     throws IOException
/*     */   {
/* 389 */     File file = new File(strFile);
/* 390 */     return writeStringToFile(file, strContent, blnAppend);
/*     */   }
/*     */ 
/*     */   public static boolean writeStringToFile(String strFile, String strContent, boolean blnAppend, String charsetName)
/*     */     throws IOException
/*     */   {
/* 406 */     File file = new File(strFile);
/* 407 */     return writeStringToFile(file, strContent, blnAppend, charsetName);
/*     */   }
/*     */ 
/*     */   public static boolean writeStringToFile(File file, String strContent, boolean blnAppend)
/*     */     throws IOException
/*     */   {
/* 427 */     if (!file.exists()) {
/* 428 */       file.createNewFile();
/*     */     }
/* 430 */     FileWriter fw = new FileWriter(file, blnAppend);
/* 431 */     fw.write(strContent);
/* 432 */     fw.close();
/* 433 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean writeStringToFile(File file, String strContent, boolean blnAppend, String charsetName)
/*     */     throws IOException
/*     */   {
/* 449 */     if (!file.exists()) {
/* 450 */       file.getParentFile().mkdirs();
/* 451 */       file.createNewFile();
/*     */     }
/* 453 */     OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file, blnAppend), 
/* 454 */       charsetName);
/* 455 */     fw.write(strContent);
/* 456 */     fw.close();
/* 457 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean writeBytesToFile(File file, byte[] bytes, boolean blnAppend)
/*     */     throws IOException
/*     */   {
/* 472 */     if (!file.exists()) {
/* 473 */       file.createNewFile();
/*     */     }
/* 475 */     FileOutputStream fw = new FileOutputStream(file, blnAppend);
/* 476 */     fw.write(bytes);
/* 477 */     fw.close();
/* 478 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean writeBytesToFile(String filePath, byte[] bytes, boolean blnAppend)
/*     */     throws IOException
/*     */   {
/* 493 */     File file = new File(filePath);
/* 494 */     return writeBytesToFile(file, bytes, blnAppend);
/*     */   }
/*     */ 
/*     */   public static boolean deleteDir(String strDir)
/*     */   {
/* 504 */     String[] arrFile = getFileList(strDir);
/* 505 */     File file = null;
/* 506 */     for (int i = 0; i < arrFile.length; i++) {
/* 507 */       file = new File(arrFile[i]);
/* 508 */       if (file.isFile()) {
/* 509 */         file.delete();
/*     */       }
/* 511 */       else if (file.isDirectory()) {
/* 512 */         deleteDir(arrFile[i]);
/*     */       }
/*     */     }
/* 515 */     file = new File(strDir);
/* 516 */     file.delete();
/* 517 */     return true;
/*     */   }
/*     */ 
/*     */   public static String[] getFileList(String strDir)
/*     */   {
/* 530 */     ArrayList arrlist = new ArrayList();
/* 531 */     File f = new File(strDir);
/* 532 */     if (f.exists()) {
/* 533 */       File[] arrF = f.listFiles();
/* 534 */       for (int i = 0; i < arrF.length; i++) {
/* 535 */         if (arrF[i].isFile()) {
/* 536 */           arrlist.add(arrF[i].getAbsolutePath());
/*     */         }
/*     */         else {
/* 539 */           arrlist.add(arrF[i].getAbsolutePath());
/* 540 */           String[] arr = getFileList(arrF[i].getAbsolutePath());
/* 541 */           for (int j = 0; j < arr.length; j++) {
/* 542 */             arrlist.add(arr[j]);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 547 */     return (String[])arrlist.toArray(new String[arrlist.size()]);
/*     */   }
/*     */ 
/*     */   public static long getFileSize(String strfile)
/*     */   {
/* 556 */     return getFileSize(new File(strfile));
/*     */   }
/*     */ 
/*     */   public static long getFileSize(File file)
/*     */   {
/* 565 */     long size = 0L;
/* 566 */     if (file.isFile()) {
/*     */       try {
/* 568 */         InputStream is = new FileInputStream(file);
/* 569 */         size = is.available();
/*     */       } catch (FileNotFoundException ex) {
/* 571 */         ex.printStackTrace();
/*     */       } catch (IOException ex) {
/* 573 */         ex.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 577 */       String[] stafiles = getFileList(file.getAbsolutePath());
/* 578 */       for (int i = 0; i < stafiles.length; i++) {
/* 579 */         File f = new File(stafiles[i]);
/* 580 */         if (f.isDirectory())
/* 581 */           size += 4096L;
/*     */         else {
/*     */           try
/*     */           {
/* 585 */             InputStream is = new FileInputStream(f);
/* 586 */             size += is.available();
/*     */           } catch (FileNotFoundException ex) {
/* 588 */             ex.printStackTrace();
/*     */           } catch (IOException ex) {
/* 590 */             ex.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 595 */     return size;
/*     */   }
/*     */ 
/*     */   public static boolean writeObjectToFile(File file, Object object)
/*     */   {
/* 605 */     if (object == null) {
/* 606 */       return false;
/*     */     }
/* 608 */     ObjectOutputStream out = null;
/*     */     try {
/* 610 */       out = new ObjectOutputStream(new FileOutputStream(file));
/* 611 */       out.writeObject(object);
/* 612 */       return true;
/*     */     } catch (Exception e) {
/* 614 */       e.printStackTrace(System.out);
/* 615 */       return false;
/*     */     } finally {
/*     */       try {
/* 618 */         out.close();
/*     */       } catch (Exception e) {
/* 620 */         e.printStackTrace(System.out);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean writeObjectToFile(String file_path, Object object)
/*     */   {
/* 632 */     File file = new File(file_path);
/* 633 */     return writeObjectToFile(file, object);
/*     */   }
/*     */ 
/*     */   public static Object readFileToObject(File file)
/*     */   {
/* 642 */     if (!file.exists()) {
/* 643 */       return null;
/*     */     }
/* 645 */     ObjectInputStream in = null;
/*     */     try {
/* 647 */       in = new ObjectInputStream(new FileInputStream(file));
/* 648 */       return in.readObject();
/*     */     } catch (Exception e) {
/* 650 */       e.printStackTrace(System.out);
/* 651 */       return null;
/*     */     } finally {
/*     */       try {
/* 654 */         in.close();
/*     */       } catch (Exception e) {
/* 656 */         e.printStackTrace(System.out);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object readFileToObject(String file_path)
/*     */   {
/* 667 */     File file = new File(file_path);
/* 668 */     return readFileToObject(file);
/*     */   }
/*     */ 
/*     */   public static List<String> getFileSinglList(String strDir)
/*     */   {
/* 674 */     ArrayList arrlist = new ArrayList();
/* 675 */     File f = new File(strDir);
/* 676 */     if (f.exists()) {
/* 677 */       File[] arrF = f.listFiles();
/* 678 */       for (int i = 0; i < arrF.length; i++) {
/* 679 */         if (arrF[i].isFile()) {
/* 680 */           arrlist.add(arrF[i].getAbsolutePath());
/*     */         }
/*     */       }
/*     */     }
/* 684 */     return arrlist;
/*     */   }
/*     */ 
/*     */   public static List<String> getFolderList(String strDir)
/*     */   {
/* 689 */     ArrayList arrlist = new ArrayList();
/* 690 */     File f = new File(strDir);
/* 691 */     if (f.exists()) {
/* 692 */       File[] arrF = f.listFiles();
/* 693 */       for (int i = 0; i < arrF.length; i++) {
/* 694 */         if (!arrF[i].isFile()) {
/* 695 */           arrlist.add(arrF[i].getAbsolutePath());
/*     */         }
/*     */       }
/*     */     }
/* 699 */     return arrlist;
/*     */   }
/*     */ 
/*     */   public static boolean deleteFile(String sPath)
/*     */   {
/* 707 */     boolean flag = false;
/* 708 */     File file = new File(sPath);
/* 709 */     if ((file.isFile()) && (file.exists())) {
/* 710 */       file.delete();
/* 711 */       flag = true;
/*     */     }
/* 713 */     return flag;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 718 */     byte[] b = readFileToBytes("F:\\temp\\ttt.jar");
/* 719 */     writeBytesToFile("F:\\temp\\222.jar", b, true);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.io.FileOperation
 * JD-Core Version:    0.6.2
 */