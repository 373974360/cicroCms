/*     */ package com.cicro.util.img;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.UploadManager;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public class RemoteImage
/*     */ {
/*     */   public static boolean saveImgUrlAs(String fileUrl, String site_id)
/*     */   {
/*  34 */     String savePath = UploadManager.getUploadFilePath(site_id);
/*     */     try {
/*  36 */       String fileName = DateUtil.getCurrentDateTime("yyyyMMddhhmmsss");
/*  37 */       String extName = "";
/*  38 */       if (fileUrl.lastIndexOf(".") >= 0) {
/*  39 */         extName = fileUrl.substring(fileUrl.lastIndexOf("."))
/*  40 */           .toLowerCase();
/*     */       }
/*     */ 
/*  43 */       URL url = new URL(fileUrl);
/*  44 */       BufferedImage image = ImageIO.read(url);
/*     */ 
/*  46 */       processImage(new File(FormatUtil.formatPath(savePath + "/" + fileName + extName)), image, fileName, extName, savePath, "0", "0");
/*     */ 
/*  48 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/*  51 */       System.out.println(e + fileUrl + savePath);
/*  52 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void processImage(File file, BufferedImage bis, String name, String extName, String savePath, String is_press, String press_osition)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/*  60 */       String pressImg = "/cicro/wcm/uploadFile/201009/logo.png";
/*     */ 
/*  62 */       int w = bis.getWidth();
/*     */       File saveFile;
/*  65 */       if (w > ImageUtils.getImgWidth()) {
/*  66 */         File saveFile = new File(savePath + "/" + name + "_b" + extName);
/*  67 */         ImageIO.write(bis, extName.substring(1), saveFile);
/*     */ 
/*  69 */         if (extName.equals(".gif"))
/*  70 */           ImageUtils.CreatetHumbnailByGif(saveFile, file, 
/*  71 */             ImageUtils.getImgWidth());
/*     */         else {
/*  73 */           ImageUtils.CreatetHumbnail(saveFile, file, 
/*  74 */             ImageUtils.getImgWidth());
/*     */         }
/*  76 */         if ("1".equals(is_press))
/*     */         {
/*  78 */           ImageUtils.pressImage(pressImg, file, Integer.parseInt(press_osition), extName);
/*     */         }
/*     */       } else {
/*  81 */         saveFile = file;
/*  82 */         ImageIO.write(bis, extName.substring(1), saveFile);
/*     */       }
/*     */ 
/*  85 */       if ("1".equals(is_press))
/*     */       {
/*  87 */         ImageUtils.pressImage(pressImg, saveFile, Integer.parseInt(press_osition), extName);
/*     */       }
/*     */ 
/*  92 */       if (w > ImageUtils.getImgPreWidth())
/*     */       {
/*  94 */         File humbImg = new File(savePath + "/" + name + "_s" + extName);
/*  95 */         if (extName.equals(".gif"))
/*  96 */           ImageUtils.CreatetHumbnailByGif(saveFile, humbImg, 
/*  97 */             ImageUtils.getImgPreWidth());
/*     */         else
/*  99 */           ImageUtils.CreatetHumbnail(saveFile, humbImg, 
/* 100 */             ImageUtils.getImgPreWidth());
/*     */       }
/*     */     } catch (Exception e) {
/* 103 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 113 */     String photoUrl = "http://img8.itiexue.net/1250/12504752.jpg";
/* 114 */     String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));
/*     */ 
/* 116 */     boolean flag = saveImgUrlAs(photoUrl, "aa");
/* 117 */     System.out.println("Run ok!\n Get URL file " + flag);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.img.RemoteImage
 * JD-Core Version:    0.6.2
 */