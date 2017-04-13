/*     */ package com.app.fileupload;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.JPEGCodec;
/*     */ import com.sun.image.codec.jpeg.JPEGEncodeParam;
/*     */ import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class MyUtils
/*     */ {
/*     */   public static String randomRename(String fileName, String dir)
/*     */   {
/*  32 */     String[] split = fileName.split("\\.");
/*  33 */     String extendFile = "." + split[(split.length - 1)].toLowerCase();
/*     */ 
/*  35 */     Random random = new Random();
/*  36 */     int add = random.nextInt(1000000);
/*  37 */     String ret = add + extendFile;
/*  38 */     while (isFileExist(ret, dir)) {
/*  39 */       add = random.nextInt(1000000);
/*  40 */       ret = fileName + add + extendFile;
/*     */     }
/*  42 */     File file = new File(dir + fileName);
/*  43 */     System.out.println("fileName ---- " + fileName);
/*  44 */     File reFile = new File(dir + ret);
/*  45 */     file.renameTo(reFile);
/*  46 */     System.out.println("reFile ---- " + reFile);
/*  47 */     String name = reFile.getName();
/*  48 */     file = null;
/*  49 */     reFile = null;
/*  50 */     return name;
/*     */   }
/*     */ 
/*     */   public static boolean delFile(String filePathAndName)
/*     */   {
/*  63 */     File myDelFile = new File(filePathAndName);
/*  64 */     if (!myDelFile.exists()) {
/*  65 */       return true;
/*     */     }
/*  67 */     return myDelFile.delete();
/*     */   }
/*     */ 
/*     */   public static String upload(String uploadFileName, String savePath, File uploadFile)
/*     */   {
/*  82 */     String newFileName = getRandomName(uploadFileName, savePath);
/*     */     try {
/*  84 */       FileOutputStream fos = new FileOutputStream(savePath + newFileName);
/*  85 */       FileInputStream fis = new FileInputStream(uploadFile);
/*  86 */       byte[] buffer = new byte[1024];
/*  87 */       int len = 0;
/*  88 */       while ((len = fis.read(buffer)) > 0)
/*  89 */         fos.write(buffer, 0, len);
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  92 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  94 */       e.printStackTrace();
/*     */     }
/*  96 */     return newFileName;
/*     */   }
/*     */ 
/*     */   public static void mkDirectory(String path)
/*     */   {
/*     */     File file;
/*     */     try
/*     */     {
/* 107 */       file = new File(path);
/* 108 */       if (!file.exists())
/* 109 */         file.mkdirs();
/*     */     }
/*     */     catch (RuntimeException e) {
/* 112 */       e.printStackTrace();
/*     */     }
/*     */     finally
/*     */     {
/*     */       File file;
/* 114 */       file = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void addToCollection(Collection collection, Object[] arr)
/*     */   {
/* 127 */     if ((collection != null) && (arr != null))
/* 128 */       CollectionUtils.addAll(collection, arr);
/*     */   }
/*     */ 
/*     */   public static String[] split(String str, String separatorChars)
/*     */   {
/* 148 */     return StringUtils.split(str, separatorChars);
/*     */   }
/*     */ 
/*     */   public static boolean invokeSetMethod(String fieldName, Object invokeObj, Object[] args)
/*     */   {
/* 169 */     boolean flag = false;
/* 170 */     Field[] fields = invokeObj.getClass().getDeclaredFields();
/* 171 */     Method[] methods = invokeObj.getClass().getDeclaredMethods();
/* 172 */     for (Field f : fields) {
/* 173 */       String fname = f.getName();
/* 174 */       if (fname.equals(fieldName)) {
/* 175 */         String mname = "set" + fname.substring(0, 1).toUpperCase() + fname.substring(1);
/* 176 */         for (Method m : methods) {
/* 177 */           String name = m.getName();
/* 178 */           if (mname.equals(name))
/*     */           {
/* 180 */             if ((f.getType().getSimpleName().equalsIgnoreCase("integer")) && (args.length > 0)) {
/* 181 */               args[0] = Integer.valueOf(args[0].toString());
/*     */             }
/*     */ 
/* 184 */             if ((f.getType().getSimpleName().equalsIgnoreCase("boolean")) && (args.length > 0))
/* 185 */               args[0] = Boolean.valueOf(args[0].toString());
/*     */             try
/*     */             {
/* 188 */               m.invoke(invokeObj, args);
/* 189 */               flag = true;
/*     */             } catch (IllegalArgumentException e) {
/* 191 */               flag = false;
/* 192 */               e.printStackTrace();
/*     */             } catch (IllegalAccessException e) {
/* 194 */               flag = false;
/* 195 */               e.printStackTrace();
/*     */             } catch (InvocationTargetException e) {
/* 197 */               flag = false;
/* 198 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 204 */     return flag;
/*     */   }
/*     */ 
/*     */   public static boolean isFileExist(String fileName, String dir)
/*     */   {
/* 215 */     File files = new File(dir + fileName);
/* 216 */     return files.exists();
/*     */   }
/*     */ 
/*     */   public static String getRandomName(String fileName, String dir)
/*     */   {
/* 227 */     String[] split = fileName.split("\\.");
/* 228 */     String extendFile = "." + split[(split.length - 1)].toLowerCase();
/*     */ 
/* 230 */     Random random = new Random();
/* 231 */     int add = random.nextInt(1000000);
/* 232 */     String ret = add + extendFile;
/* 233 */     while (isFileExist(ret, dir)) {
/* 234 */       add = random.nextInt(1000000);
/* 235 */       ret = fileName + add + extendFile;
/*     */     }
/* 237 */     return ret;
/*     */   }
/*     */ 
/*     */   public static void createMiniPic(File file, float width, float height)
/*     */     throws IOException
/*     */   {
/* 250 */     Image src = ImageIO.read(file);
/* 251 */     int old_w = src.getWidth(null);
/* 252 */     int old_h = src.getHeight(null);
/* 253 */     int new_w = 0;
/* 254 */     int new_h = 0;
/*     */     float tempdouble;
/*     */     float tempdouble;
/* 256 */     if (old_w >= old_h)
/* 257 */       tempdouble = old_w / width;
/*     */     else {
/* 259 */       tempdouble = old_h / height;
/*     */     }
/*     */ 
/* 262 */     if ((old_w >= width) || (old_h >= height)) {
/* 263 */       new_w = Math.round(old_w / tempdouble);
/* 264 */       new_h = Math.round(old_h / tempdouble);
/* 265 */       while ((new_w > width) && (new_h > height)) {
/* 266 */         if (new_w > width) {
/* 267 */           tempdouble = new_w / width;
/* 268 */           new_w = Math.round(new_w / tempdouble);
/* 269 */           new_h = Math.round(new_h / tempdouble);
/*     */         }
/* 271 */         if (new_h > height) {
/* 272 */           tempdouble = new_h / height;
/* 273 */           new_w = Math.round(new_w / tempdouble);
/* 274 */           new_h = Math.round(new_h / tempdouble);
/*     */         }
/*     */       }
/* 277 */       BufferedImage tag = new BufferedImage(new_w, new_h, 1);
/* 278 */       tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null);
/* 279 */       FileOutputStream newimage = new FileOutputStream(file);
/* 280 */       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
/* 281 */       JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
/* 282 */       param.setQuality(1.0F, true);
/* 283 */       encoder.encode(tag, param);
/* 284 */       encoder.encode(tag);
/* 285 */       newimage.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isValid(String contentType, String[] allowTypes)
/*     */   {
/* 299 */     if ((contentType == null) || ("".equals(contentType))) {
/* 300 */       return false;
/*     */     }
/* 302 */     for (String type : allowTypes) {
/* 303 */       if (contentType.equals(type)) {
/* 304 */         return true;
/*     */       }
/*     */     }
/* 307 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.app.fileupload.MyUtils
 * JD-Core Version:    0.6.2
 */