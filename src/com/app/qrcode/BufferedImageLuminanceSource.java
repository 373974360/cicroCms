/*    */ package com.app.qrcode;
/*    */ 
/*    */ import com.google.zxing.LuminanceSource;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.WritableRaster;
/*    */ 
/*    */ public final class BufferedImageLuminanceSource extends LuminanceSource
/*    */ {
/*    */   private final BufferedImage image;
/*    */   private final int left;
/*    */   private final int top;
/*    */ 
/*    */   public BufferedImageLuminanceSource(BufferedImage image)
/*    */   {
/* 17 */     this(image, 0, 0, image.getWidth(), image.getHeight());
/*    */   }
/*    */ 
/*    */   public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {
/* 21 */     super(width, height);
/*    */ 
/* 23 */     int sourceWidth = image.getWidth();
/* 24 */     int sourceHeight = image.getHeight();
/* 25 */     if ((left + width > sourceWidth) || (top + height > sourceHeight)) {
/* 26 */       throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
/*    */     }
/*    */ 
/* 29 */     for (int y = top; y < top + height; y++) {
/* 30 */       for (int x = left; x < left + width; x++) {
/* 31 */         if ((image.getRGB(x, y) & 0xFF000000) == 0) {
/* 32 */           image.setRGB(x, y, -1);
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 37 */     this.image = new BufferedImage(sourceWidth, sourceHeight, 10);
/* 38 */     this.image.getGraphics().drawImage(image, 0, 0, null);
/* 39 */     this.left = left;
/* 40 */     this.top = top;
/*    */   }
/*    */ 
/*    */   public byte[] getRow(int y, byte[] row)
/*    */   {
/* 45 */     if ((y < 0) || (y >= getHeight())) {
/* 46 */       throw new IllegalArgumentException("Requested row is outside the image: " + y);
/*    */     }
/* 48 */     int width = getWidth();
/* 49 */     if ((row == null) || (row.length < width)) {
/* 50 */       row = new byte[width];
/*    */     }
/* 52 */     this.image.getRaster().getDataElements(this.left, this.top + y, width, 1, row);
/* 53 */     return row;
/*    */   }
/*    */ 
/*    */   public byte[] getMatrix()
/*    */   {
/* 58 */     int width = getWidth();
/* 59 */     int height = getHeight();
/* 60 */     int area = width * height;
/* 61 */     byte[] matrix = new byte[area];
/* 62 */     this.image.getRaster().getDataElements(this.left, this.top, width, height, matrix);
/* 63 */     return matrix;
/*    */   }
/*    */ 
/*    */   public boolean isCropSupported()
/*    */   {
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */   public LuminanceSource crop(int left, int top, int width, int height)
/*    */   {
/* 73 */     return new BufferedImageLuminanceSource(this.image, this.left + left, this.top + top, width, height);
/*    */   }
/*    */ 
/*    */   public boolean isRotateSupported()
/*    */   {
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   public LuminanceSource rotateCounterClockwise()
/*    */   {
/* 84 */     int sourceWidth = this.image.getWidth();
/* 85 */     int sourceHeight = this.image.getHeight();
/*    */ 
/* 87 */     AffineTransform transform = new AffineTransform(0.0D, -1.0D, 1.0D, 0.0D, 0.0D, sourceWidth);
/*    */ 
/* 89 */     BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, 10);
/*    */ 
/* 91 */     Graphics2D g = rotatedImage.createGraphics();
/* 92 */     g.drawImage(this.image, transform, null);
/* 93 */     g.dispose();
/*    */ 
/* 95 */     int width = getWidth();
/* 96 */     return new BufferedImageLuminanceSource(rotatedImage, this.top, sourceWidth - (this.left + width), getHeight(), width);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.app.qrcode.BufferedImageLuminanceSource
 * JD-Core Version:    0.6.2
 */