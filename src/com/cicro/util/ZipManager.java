/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ 
/*     */ public class ZipManager
/*     */ {
/*     */   public static boolean compress(String strZipFileName, String[] arrFile, String strBaseDir, String strTarget)
/*     */   {
/*     */     try
/*     */     {
/*  60 */       ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(
/*  61 */         strTarget + File.separator + strZipFileName));
/*     */ 
/*  64 */       Set set = new HashSet();
/*  65 */       for (int i = 0; i < arrFile.length; i++) {
/*  66 */         String strDir = strBaseDir + File.separator + arrFile[i];
/*  67 */         if (new File(strDir).isDirectory()) {
/*  68 */           set.add(arrFile[i]);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  73 */       for (Iterator it = set.iterator(); it.hasNext(); )
/*     */       {
/*  76 */         String strRntryName = (String)it.next();
/*  77 */         strRntryName = strRntryName.replace('\\', '/') + "/";
/*  78 */         JarEntry tentry = new JarEntry(strRntryName);
/*     */         try {
/*  80 */           jos.putNextEntry(tentry);
/*  81 */           jos.closeEntry();
/*     */         } catch (IOException e) {
/*  83 */           e.printStackTrace(System.out);
/*  84 */           return false;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  89 */       byte[] buffer = new byte[4096];
/*     */ 
/*  91 */       for (int i = 0; i < arrFile.length; i++)
/*     */       {
/*  93 */         File file = new File(strBaseDir + File.separator + arrFile[i]);
/*  94 */         if (!file.isDirectory())
/*     */         {
/*     */           try
/*     */           {
/*  99 */             ZipEntry je = new ZipEntry(arrFile[i]);
/* 100 */             FileInputStream fis = new FileInputStream(file);
/* 101 */             jos.putNextEntry(je);
/*     */             int bytesRead;
/* 102 */             while ((bytesRead = fis.read(buffer)) != -1)
/*     */             {
/*     */               int bytesRead;
/* 103 */               jos.write(buffer, 0, bytesRead);
/*     */             }
/* 105 */             fis.close();
/* 106 */             jos.closeEntry();
/*     */           }
/*     */           catch (Exception localException1) {
/*     */           }
/*     */         }
/*     */       }
/* 112 */       jos.close();
/* 113 */       return true;
/*     */     } catch (Exception e) {
/* 115 */       e.printStackTrace(System.out);
/* 116 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean compress(String strZipFileName, String strDir, String strBaseDir, String strTarget)
/*     */   {
/*     */     try
/*     */     {
/* 136 */       File f = new File(strDir);
/* 137 */       if (!f.exists()) {
/* 138 */         return false;
/*     */       }
/* 140 */       String[] arr = getFileList(strDir, strBaseDir);
/* 141 */       return compress(strZipFileName, arr, strBaseDir, strTarget);
/*     */     } catch (Exception e) {
/* 143 */       e.printStackTrace(System.out);
/* 144 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean compress(String strZipFileName, String strDir, String strBaseDir, String strTarget, String[] exceptDir)
/*     */   {
/*     */     try
/*     */     {
/* 169 */       File f = new File(strDir);
/* 170 */       if (!f.exists()) {
/* 171 */         return false;
/*     */       }
/*     */ 
/* 174 */       String[] arr = getFileList(strDir, strBaseDir);
/*     */ 
/* 176 */       if ((arr != null) && (exceptDir != null)) {
/* 177 */         ArrayList arrlist = new ArrayList();
/* 178 */         for (int i = 0; i < arr.length; i++) {
/* 179 */           boolean flag = false;
/* 180 */           for (int j = 0; j < exceptDir.length; j++) {
/* 181 */             if (arr[i].indexOf(exceptDir[j]) != -1) {
/* 182 */               flag = true;
/* 183 */               break;
/*     */             }
/*     */           }
/* 186 */           if (!flag) {
/* 187 */             arrlist.add(arr[i]);
/*     */           }
/*     */         }
/* 190 */         String[] ziparr = (String[])arrlist.toArray(
/* 191 */           new String[arrlist
/* 191 */           .size()]);
/* 192 */         return compress(strZipFileName, ziparr, strBaseDir, strTarget);
/*     */       }
/* 194 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insert(String strZipFile, String[] arrFile, String strBaseDir)
/*     */   {
/*     */     try
/*     */     {
/* 218 */       ArrayList arrlist1 = new ArrayList();
/* 219 */       File f = new File(strZipFile);
/* 220 */       if (!f.exists()) {
/* 221 */         return false;
/*     */       }
/*     */ 
/* 225 */       ZipFile jf = new ZipFile(f);
/* 226 */       for (Enumeration enume = jf.entries(); enume.hasMoreElements(); ) {
/* 227 */         ZipEntry je = (ZipEntry)enume.nextElement();
/* 228 */         arrlist1.add(je.getName());
/*     */       }
/* 230 */       jf.close();
/*     */ 
/* 233 */       ArrayList arrlist2 = new ArrayList();
/* 234 */       for (int i = 0; i < arrFile.length; i++)
/*     */       {
/* 236 */         int index = arrFile[i].lastIndexOf(File.separator);
/* 237 */         if (index != -1)
/*     */         {
/* 240 */           String strDir = arrFile[i].substring(0, index + 1);
/* 241 */           if (!arrlist1.contains(strDir))
/*     */           {
/* 244 */             strDir = strDir.replace('\\', '/');
/* 245 */             arrlist2.add(strDir);
/*     */           }
/*     */         }
/*     */       }
/* 249 */       ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(
/* 250 */         strZipFile + ".tmp"));
/*     */ 
/* 253 */       for (int i = 0; i < arrlist2.size(); i++) {
/* 254 */         if (arrlist1.contains(arrlist2.get(i))) {
/* 255 */           ZipEntry je = new ZipEntry((String)arrlist2.get(i));
/* 256 */           jos.putNextEntry(je);
/* 257 */           jos.closeEntry();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 263 */       byte[] buffer = new byte[4096];
/* 264 */       for (int i = 0; i < arrFile.length; i++) {
/* 265 */         f = new File(arrFile[i]);
/* 266 */         if (!f.exists()) {
/* 267 */           return false;
/*     */         }
/* 269 */         FileInputStream fis = new FileInputStream(f);
/* 270 */         ZipEntry je = new ZipEntry(arrFile[i]);
/* 271 */         jos.putNextEntry(je);
/*     */         int byteRead;
/* 272 */         while ((byteRead = fis.read(buffer)) != -1)
/*     */         {
/*     */           int byteRead;
/* 273 */           jos.write(buffer, 0, byteRead);
/*     */         }
/* 275 */         fis.close();
/* 276 */         jos.closeEntry();
/*     */       }
/*     */ 
/* 280 */       jf = new ZipFile(new File(strZipFile));
/* 281 */       for (Enumeration enume = jf.entries(); enume.hasMoreElements(); ) {
/* 282 */         ZipEntry je = (ZipEntry)enume.nextElement();
/* 283 */         InputStream is = jf.getInputStream(je);
/* 284 */         jos.putNextEntry(je);
/*     */         int byteRead;
/* 285 */         while ((byteRead = is.read(buffer)) != -1)
/*     */         {
/*     */           int byteRead;
/* 286 */           jos.write(buffer, 0, byteRead);
/*     */         }
/* 288 */         is.close();
/* 289 */         jos.closeEntry();
/*     */       }
/* 291 */       jos.close();
/* 292 */       jf.close();
/*     */ 
/* 295 */       f = new File(strZipFile);
/* 296 */       if (f.exists()) {
/* 297 */         f.delete();
/*     */       }
/* 299 */       f = new File(strZipFile + ".tmp");
/* 300 */       f.renameTo(new File(strZipFile));
/* 301 */       return true;
/*     */     } catch (Exception e) {
/* 303 */       e.printStackTrace(System.out);
/* 304 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean decompress(String strZipFile, String strTarget)
/*     */   {
/*     */     try
/*     */     {
/* 322 */       byte[] buffer = new byte[4096];
/*     */ 
/* 324 */       ZipFile jf = new ZipFile(new File(strZipFile));
/* 325 */       Enumeration enume = jf.entries();
/* 326 */       while (enume.hasMoreElements()) {
/* 327 */         ZipEntry je = new ZipEntry((ZipEntry)enume.nextElement());
/* 328 */         String strFile = strTarget + File.separator + je.getName();
/*     */ 
/* 330 */         if (File.separatorChar == '\\')
/* 331 */           strFile = strFile.replace('/', '\\');
/*     */         else {
/* 333 */           strFile = strFile.replace('\\', '/');
/*     */         }
/*     */ 
/* 336 */         File f = new File(strFile);
/* 337 */         if ((strFile.endsWith("/")) || (strFile.endsWith("\\"))) {
/* 338 */           f.mkdirs();
/*     */         }
/*     */         else {
/* 341 */           int index = strFile.lastIndexOf(File.separator);
/* 342 */           if (index != -1) {
/* 343 */             f = new File(strFile.substring(0, index + 1));
/* 344 */             f.mkdirs();
/*     */           }
/*     */ 
/*     */           try
/*     */           {
/* 349 */             FileOutputStream fos = new FileOutputStream(strFile);
/* 350 */             InputStream is = jf.getInputStream(je);
/*     */             int bytesRead;
/* 352 */             while ((bytesRead = is.read(buffer)) != -1)
/*     */             {
/*     */               int bytesRead;
/* 353 */               fos.write(buffer, 0, bytesRead);
/*     */             }
/* 355 */             is.close();
/* 356 */             fos.close();
/*     */           }
/*     */           catch (Exception ex) {
/* 359 */             ex.printStackTrace(System.out);
/*     */           }
/*     */         }
/*     */       }
/* 362 */       return true;
/*     */     } catch (Exception e) {
/* 364 */       e.printStackTrace(System.out);
/* 365 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean decompressFile(String zipfile, String entry, String target)
/*     */     throws IOException
/*     */   {
/* 391 */     InputStream is = decompressFile2Stream(zipfile, entry);
/* 392 */     OutputStream os = new BufferedOutputStream(new FileOutputStream(target));
/*     */ 
/* 395 */     byte[] buffer = new byte[4096];
/*     */     int bytesRead;
/* 397 */     while ((bytesRead = is.read(buffer)) != -1)
/*     */     {
/*     */       int bytesRead;
/* 398 */       os.write(buffer, 0, bytesRead);
/*     */     }
/* 400 */     is.close();
/* 401 */     os.close();
/* 402 */     return true;
/*     */   }
/*     */ 
/*     */   public static InputStream decompressFile2Stream(String zipfile, String entry)
/*     */     throws IOException
/*     */   {
/* 424 */     InputStream is = null;
/* 425 */     ZipFile zf = new ZipFile(new File(zipfile));
/* 426 */     ZipEntry ze = new ZipEntry(entry);
/* 427 */     is = new BufferedInputStream(zf.getInputStream(ze));
/* 428 */     return is;
/*     */   }
/*     */ 
/*     */   protected static String[] getFileList(String strDir, String strBaseDir)
/*     */   {
/* 441 */     String[] files = getFileList(strDir);
/* 442 */     int length = strBaseDir.length();
/*     */ 
/* 446 */     if (File.separator.equals("\\")) {
/* 447 */       strBaseDir = strBaseDir.replaceAll("/", "\\\\");
/*     */     }
/*     */ 
/* 451 */     for (int i = 0; i < files.length; i++) {
/* 452 */       int index = files[i].indexOf(strBaseDir);
/* 453 */       if (index != -1) {
/* 454 */         files[i] = files[i].substring(index + 1 + length);
/*     */       }
/*     */     }
/* 457 */     return files;
/*     */   }
/*     */ 
/*     */   protected static String[] getFileList(String strDir)
/*     */   {
/* 468 */     ArrayList arrlist = new ArrayList();
/* 469 */     String[] tempA = strDir.split(",");
/* 470 */     for (int k = 0; k < tempA.length; k++)
/*     */     {
/* 472 */       File f = new File(tempA[k]);
/* 473 */       if (f.exists()) {
/* 474 */         File[] arrF = f.listFiles();
/* 475 */         for (int i = 0; i < arrF.length; i++) {
/* 476 */           if (arrF[i].isFile()) {
/* 477 */             arrlist.add(arrF[i].getAbsolutePath());
/*     */           } else {
/* 479 */             arrlist.add(arrF[i].getAbsolutePath());
/* 480 */             String[] arr = getFileList(arrF[i].getAbsolutePath());
/* 481 */             for (int j = 0; j < arr.length; j++) {
/* 482 */               arrlist.add(arr[j]);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 488 */     return (String[])arrlist.toArray(new String[arrlist.size()]);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.ZipManager
 * JD-Core Version:    0.6.2
 */