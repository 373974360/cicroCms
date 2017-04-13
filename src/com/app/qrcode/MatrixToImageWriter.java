/*     */ package com.app.qrcode;
/*     */ 
/*     */ import com.google.zxing.BarcodeFormat;
/*     */ import com.google.zxing.Binarizer;
/*     */ import com.google.zxing.BinaryBitmap;
/*     */ import com.google.zxing.EncodeHintType;
/*     */ import com.google.zxing.LuminanceSource;
/*     */ import com.google.zxing.MultiFormatReader;
/*     */ import com.google.zxing.MultiFormatWriter;
/*     */ import com.google.zxing.Result;
/*     */ import com.google.zxing.common.BitMatrix;
/*     */ import com.google.zxing.common.HybridBinarizer;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Hashtable;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public final class MatrixToImageWriter
/*     */ {
/*     */   private static final int BLACK = -16777216;
/*     */   private static final int WHITE = -1;
/*     */ 
/*     */   public static BufferedImage toBufferedImage(BitMatrix matrix)
/*     */   {
/*  38 */     int width = matrix.getWidth();
/*  39 */     int height = matrix.getHeight();
/*  40 */     BufferedImage image = new BufferedImage(width, height, 1);
/*  41 */     for (int x = 0; x < width; x++) {
/*  42 */       for (int y = 0; y < height; y++) {
/*  43 */         image.setRGB(x, y, matrix.get(x, y) ? -16777216 : -1);
/*     */       }
/*     */     }
/*  46 */     return image;
/*     */   }
/*     */ 
/*     */   public static void writeToFile(BitMatrix matrix, String format, File file)
/*     */     throws IOException
/*     */   {
/*  52 */     BufferedImage image = toBufferedImage(matrix);
/*  53 */     if (!ImageIO.write(image, format, file))
/*  54 */       throw new IOException("Could not write an image of format " + format + " to " + file);
/*     */   }
/*     */ 
/*     */   public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
/*     */     throws IOException
/*     */   {
/*  61 */     BufferedImage image = toBufferedImage(matrix);
/*  62 */     if (!ImageIO.write(image, format, stream))
/*  63 */       throw new IOException("Could not write an image of format " + format);
/*     */   }
/*     */ 
/*     */   public static String CreatQrcode(String filename, String filepath, String content)
/*     */   {
/*  76 */     String path = "";
/*     */     try {
/*  78 */       MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
/*  79 */       Hashtable hints = new Hashtable();
/*  80 */       hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
/*  81 */       BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
/*  82 */       File file1 = new File(filepath, filename);
/*  83 */       if (file1.exists()) {
/*  84 */         file1.delete();
/*     */       }
/*  86 */       writeToFile(bitMatrix, "gif", file1);
/*  87 */       path = file1.getPath();
/*     */     } catch (Exception e) {
/*  89 */       e.printStackTrace();
/*     */     }
/*  91 */     return path;
/*     */   }
/*     */ 
/*     */   public static Result deCodeQrcode(String path)
/*     */   {
/* 101 */     Result result = null;
/*     */     try {
/* 103 */       MultiFormatReader formatReader = new MultiFormatReader();
/* 104 */       String filePath = path;
/* 105 */       File file = new File(filePath);
/* 106 */       BufferedImage image = ImageIO.read(file);
/* 107 */       LuminanceSource source = new BufferedImageLuminanceSource(image);
/* 108 */       Binarizer binarizer = new HybridBinarizer(source);
/* 109 */       BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
/* 110 */       Hashtable hints = new Hashtable();
/* 111 */       hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
/* 112 */       return formatReader.decode(binaryBitmap, hints);
/*     */     }
/*     */     catch (Exception e) {
/* 115 */       e.printStackTrace();
/*     */     }
/* 117 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.app.qrcode.MatrixToImageWriter
 * JD-Core Version:    0.6.2
 */