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
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.JarOutputStream;
/*     */ import java.util.jar.Manifest;
/*     */ 
/*     */ public class JarManager extends ZipManager
/*     */ {
/*     */   public static boolean compress(String strJarFileName, String[] arrFile, String strBaseDir, String strTarget)
/*     */   {
/*  40 */     JarOutputStream jos = null;
/*     */     try {
/*  42 */       jos = new JarOutputStream(
/*  43 */         new FileOutputStream(
/*  44 */         strTarget + File.separator + strJarFileName));
/*     */     } catch (IOException e) {
/*  46 */       e.printStackTrace(System.out);
/*  47 */       return false;
/*     */     }
/*     */ 
/*  51 */     Set set = new HashSet();
/*  52 */     for (int i = 0; i < arrFile.length; i++) {
/*  53 */       String strDir = strBaseDir + File.separator + arrFile[i];
/*  54 */       if (new File(strDir).isDirectory()) {
/*  55 */         set.add(arrFile[i]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  60 */     for (Iterator it = set.iterator(); it.hasNext(); )
/*     */     {
/*  63 */       String strRntryName = (String)it.next();
/*  64 */       strRntryName = strRntryName.replace('\\', '/') + "/";
/*  65 */       JarEntry tentry = new JarEntry(strRntryName);
/*     */       try {
/*  67 */         jos.putNextEntry(tentry);
/*  68 */         jos.closeEntry();
/*     */       } catch (IOException e) {
/*  70 */         e.printStackTrace(System.out);
/*  71 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  76 */     byte[] buffer = new byte[4096];
/*     */ 
/*  78 */     for (int i = 0; i < arrFile.length; i++)
/*     */     {
/*  81 */       File file = new File(strBaseDir + File.separator + arrFile[i]);
/*  82 */       if (!file.isDirectory())
/*     */       {
/*  85 */         JarEntry je = new JarEntry(arrFile[i]);
/*     */         try {
/*  87 */           FileInputStream fis = new FileInputStream(file);
/*  88 */           jos.putNextEntry(je);
/*     */           int bytesRead;
/*  89 */           while ((bytesRead = fis.read(buffer)) != -1)
/*     */           {
/*     */             int bytesRead;
/*  90 */             jos.write(buffer, 0, bytesRead);
/*     */           }
/*  92 */           fis.close();
/*  93 */           jos.closeEntry();
/*     */         } catch (Exception ex) {
/*  95 */           ex.printStackTrace(System.out);
/*  96 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     try { jos.close();
/*     */     } catch (IOException e) {
/* 102 */       e.printStackTrace(System.out);
/* 103 */       return false;
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean compress(String strJarFileName, String strDir, String strBaseDir, String strTarget)
/*     */   {
/*     */     try
/*     */     {
/* 127 */       String[] arr = getFileList(strDir, strBaseDir);
/* 128 */       compress(strJarFileName, arr, strBaseDir, strTarget);
/* 129 */       return true;
/*     */     } catch (Exception e) {
/* 131 */       e.printStackTrace(System.out);
/* 132 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean insert(String strJarFile, String[] arrFile, String strBaseDir)
/*     */   {
/*     */     try
/*     */     {
/* 150 */       List arrlist1 = new ArrayList();
/* 151 */       File f = new File(strJarFile);
/* 152 */       if (!f.exists()) {
/* 153 */         return false;
/*     */       }
/*     */ 
/* 157 */       JarFile jf = new JarFile(f);
/* 158 */       for (Enumeration enume = jf.entries(); enume.hasMoreElements(); ) {
/* 159 */         JarEntry je = (JarEntry)enume.nextElement();
/* 160 */         arrlist1.add(je.getName());
/*     */       }
/* 162 */       jf.close();
/*     */ 
/* 165 */       Set set = new HashSet();
/* 166 */       for (int i = 0; i < arrFile.length; i++)
/*     */       {
/* 168 */         int index = arrFile[i].lastIndexOf(File.separator);
/* 169 */         if (index != -1)
/*     */         {
/* 172 */           String strDir = arrFile[i].substring(0, index + 1);
/* 173 */           strDir = strDir.replace('\\', '/');
/* 174 */           set.add(strDir);
/*     */         }
/*     */       }
/*     */ 
/* 178 */       Manifest m = new Manifest();
/* 179 */       JarOutputStream jos = 
/* 180 */         new JarOutputStream(
/* 181 */         new FileOutputStream(
/* 182 */         strJarFile + ".tmp"), m);
/*     */ 
/* 185 */       for (Iterator it = set.iterator(); it.hasNext(); ) {
/* 186 */         if (arrlist1.contains(it.next())) {
/* 187 */           JarEntry je = new JarEntry((String)it.next());
/* 188 */           jos.putNextEntry(je);
/* 189 */           jos.closeEntry();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 195 */       byte[] buffer = new byte[4096];
/* 196 */       for (int i = 0; i < arrFile.length; i++) {
/* 197 */         f = new File(strBaseDir + File.separator + arrFile[i]);
/* 198 */         if (!f.exists()) {
/* 199 */           return false;
/*     */         }
/* 201 */         FileInputStream fis = new FileInputStream(f);
/* 202 */         JarEntry je = new JarEntry(arrFile[i]);
/* 203 */         jos.putNextEntry(je);
/*     */         int byteRead;
/* 204 */         while ((byteRead = fis.read(buffer)) != -1)
/*     */         {
/*     */           int byteRead;
/* 205 */           jos.write(buffer, 0, byteRead);
/*     */         }
/* 207 */         fis.close();
/* 208 */         jos.closeEntry();
/*     */       }
/*     */ 
/* 212 */       jf = new JarFile(new File(strJarFile));
/* 213 */       for (Enumeration enume = jf.entries(); enume.hasMoreElements(); ) {
/* 214 */         JarEntry je = (JarEntry)enume.nextElement();
/* 215 */         InputStream is = jf.getInputStream(je);
/* 216 */         jos.putNextEntry(je);
/*     */         int byteRead;
/* 217 */         while ((byteRead = is.read(buffer)) != -1)
/*     */         {
/*     */           int byteRead;
/* 218 */           jos.write(buffer, 0, byteRead);
/*     */         }
/* 220 */         is.close();
/* 221 */         jos.closeEntry();
/*     */       }
/* 223 */       jos.close();
/* 224 */       jf.close();
/*     */ 
/* 227 */       f = new File(strJarFile);
/* 228 */       if (f.exists()) {
/* 229 */         f.delete();
/*     */       }
/* 231 */       f = new File(strJarFile + ".tmp");
/* 232 */       f.renameTo(new File(strJarFile));
/* 233 */       return true;
/*     */     } catch (Exception e) {
/* 235 */       e.printStackTrace(System.out);
/* 236 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean decompress(String strJarFile, String strTarget)
/*     */   {
/*     */     try
/*     */     {
/* 252 */       byte[] buffer = new byte[4096];
/*     */ 
/* 254 */       JarFile jf = new JarFile(new File(strJarFile));
/* 255 */       Enumeration enume = jf.entries();
/* 256 */       while (enume.hasMoreElements()) {
/* 257 */         JarEntry je = new JarEntry((JarEntry)enume.nextElement());
/* 258 */         String strFile = strTarget + File.separator + je.getName();
/*     */ 
/* 260 */         if (File.separatorChar == '\\') {
/* 261 */           strFile = strFile.replace('/', '\\');
/*     */         }
/*     */         else {
/* 264 */           strFile = strFile.replace('\\', '/');
/*     */         }
/*     */ 
/* 267 */         File f = new File(strFile);
/* 268 */         if ((strFile.endsWith("/")) || (strFile.endsWith("\\"))) {
/* 269 */           f.mkdirs();
/*     */         }
/*     */         else
/*     */         {
/* 273 */           int index = strFile.lastIndexOf(File.separator);
/* 274 */           if (index != -1) {
/* 275 */             f = new File(strFile.substring(0, index + 1));
/* 276 */             f.mkdirs();
/*     */           }
/*     */ 
/* 280 */           FileOutputStream fos = new FileOutputStream(strFile);
/* 281 */           InputStream is = jf.getInputStream(je);
/*     */           int bytesRead;
/* 283 */           while ((bytesRead = is.read(buffer)) != -1)
/*     */           {
/*     */             int bytesRead;
/* 284 */             fos.write(buffer, 0, bytesRead);
/*     */           }
/* 286 */           is.close();
/* 287 */           fos.close();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 292 */       return true;
/*     */     } catch (Exception e) {
/* 294 */       e.printStackTrace(System.out);
/* 295 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean decompressFile(String jarfile, String entry, String target)
/*     */     throws IOException
/*     */   {
/* 314 */     InputStream is = decompressFile2Stream(jarfile, entry);
/* 315 */     if (is == null) {
/* 316 */       return false;
/*     */     }
/* 318 */     OutputStream os = new BufferedOutputStream(new FileOutputStream(target));
/*     */ 
/* 320 */     byte[] buffer = new byte[4096];
/*     */     int bytesRead;
/* 321 */     while ((bytesRead = is.read(buffer)) != -1)
/*     */     {
/*     */       int bytesRead;
/* 322 */       os.write(buffer, 0, bytesRead);
/*     */     }
/* 324 */     is.close();
/* 325 */     os.close();
/* 326 */     return true;
/*     */   }
/*     */ 
/*     */   public static InputStream decompressFile2Stream(String jarfile, String entry)
/*     */     throws IOException
/*     */   {
/* 343 */     InputStream is = null;
/* 344 */     JarFile jf = new JarFile(new File(jarfile));
/* 345 */     JarEntry je = new JarEntry(entry);
/* 346 */     is = new BufferedInputStream(jf.getInputStream(je));
/* 347 */     return is;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.JarManager
 * JD-Core Version:    0.6.2
 */