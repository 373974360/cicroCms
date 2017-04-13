/*    */ package com.cicro.util.io;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.text.NumberFormat;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class FileInfo
/*    */ {
/*    */   public static String getSize(String fileName)
/*    */   {
/* 32 */     if (fileName == null) return null;
/* 33 */     File f = new File(fileName);
/* 34 */     return getSize(f);
/*    */   }
/*    */ 
/*    */   public static String getSize(File fileIn)
/*    */   {
/* 43 */     if (fileIn == null) return null;
/* 44 */     NumberFormat nf = NumberFormat.getInstance();
/* 45 */     nf.setMaximumFractionDigits(2);
/* 46 */     double d = fileIn.length() / 1024.0D;
/* 47 */     return nf.format(d);
/*    */   }
/*    */ 
/*    */   public static String[] getWidthHeight(String fileName)
/*    */   {
/* 56 */     if (fileName == null) return null;
/* 57 */     File f = new File(fileName);
/* 58 */     return getWidthHeight(f);
/*    */   }
/*    */ 
/*    */   public static String[] getWidthHeight(File fileIn)
/*    */   {
/* 67 */     if (fileIn == null) return null; try
/*    */     {
/* 69 */       BufferedImage bufferedImage = ImageIO.read(fileIn);
/* 70 */       String[] straReturn = new String[2];
/* 71 */       straReturn[0] = bufferedImage.getWidth();
/* 72 */       straReturn[1] = bufferedImage.getHeight();
/* 73 */       return straReturn; } catch (IOException e) {
/*    */     }
/* 75 */     return null;
/*    */   }
/*    */ 
/*    */   public static String getType(String strFileName)
/*    */   {
/* 85 */     String strType = "unknown";
/*    */     try {
/* 87 */       String strExt = strFileName.substring(strFileName.lastIndexOf(".") + 1);
/* 88 */       if ((strExt.equalsIgnoreCase("jpg")) || 
/* 89 */         (strExt.equalsIgnoreCase("jpeg")) || 
/* 90 */         (strExt.equalsIgnoreCase("jpe")) || 
/* 91 */         (strExt.equalsIgnoreCase("gif")) || 
/* 92 */         (strExt.equalsIgnoreCase("png")))
/* 93 */         strType = "image";
/* 94 */       if (strExt.equalsIgnoreCase("swf"));
/* 95 */       return "flash";
/*    */     } catch (Exception e) {
/*    */     }
/* 98 */     return strType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.io.FileInfo
 * JD-Core Version:    0.6.2
 */