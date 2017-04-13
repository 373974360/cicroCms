/*     */ package com.cicro.util;
/*     */ 
/*     */ import com.gif4j.GifDecoder;
/*     */ import com.gif4j.GifEncoder;
/*     */ import com.gif4j.GifImage;
/*     */ import com.gif4j.GifTransformer;
/*     */ import com.sun.image.codec.jpeg.JPEGCodec;
/*     */ import com.sun.image.codec.jpeg.JPEGEncodeParam;
/*     */ import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.AffineTransformOp;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public final class ImageUtils
/*     */ {
/*     */   public static boolean CreatetHumbnail(File fi, File fo, int nw)
/*     */   {
/*     */     try
/*     */     {
/*  57 */       AffineTransform transform = new AffineTransform();
/*  58 */       BufferedImage bis = ImageIO.read(fi);
/*  59 */       int w = bis.getWidth();
/*  60 */       int h = bis.getHeight();
/*  61 */       double scale = w / h;
/*  62 */       int nh = nw * h / w;
/*  63 */       double sx = nw / w;
/*  64 */       double sy = nh / h;
/*  65 */       transform.setToScale(sx, sy);
/*     */ 
/*  67 */       AffineTransformOp ato = new AffineTransformOp(transform, null);
/*  68 */       BufferedImage bid = new BufferedImage(nw, nh, 5);
/*  69 */       ato.filter(bis, bid);
/*  70 */       ImageIO.write(bid, "jpeg", fo);
/*  71 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  75 */       e.printStackTrace();
/*  76 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean CreatetHumbnailByGif(File fi, File fo, int nw)
/*     */   {
/*     */     try
/*     */     {
/*  95 */       BufferedImage bis = ImageIO.read(fi);
/*  96 */       int w = bis.getWidth();
/*  97 */       int h = bis.getHeight();
/*  98 */       int nh = nw * h / w;
/*  99 */       return CreatetHumbnailByGifHandl(fi, fo, nw, nh, true);
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean CreatetHumbnailByGifHandl(File srcImg, File destImg, int width, int height, boolean smooth)
/*     */   {
/*     */     try
/*     */     {
/* 118 */       GifImage gifImage = GifDecoder.decode(srcImg);
/* 119 */       GifImage resizeIMG = GifTransformer.resize(gifImage, width, height, smooth);
/* 120 */       GifEncoder.encode(resizeIMG, destImg);
/* 121 */       return true;
/*     */     }
/*     */     catch (IOException e) {
/* 124 */       e.printStackTrace();
/* 125 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean isMovingGif(File srcImg)
/*     */   {
/*     */     try
/*     */     {
/* 139 */       GifImage gifImage = GifDecoder.decode(srcImg);
/* 140 */       if (gifImage.getNumberOfFrames() > 1) {
/* 141 */         return true;
/*     */       }
/* 143 */       return false;
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 147 */       e.printStackTrace();
/* 148 */     }return false;
/*     */   }
/*     */ 
/*     */   public static final void pressImage(String pressImg, File _file, int position_type, String extName)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       if ((".gif".equals(extName)) && (isMovingGif(_file)))
/*     */       {
/* 170 */         System.out.println("pressImage:file is moving gif");
/* 171 */         return;
/*     */       }
/*     */ 
/* 174 */       BufferedImage image = ImageIO.read(_file);
/* 175 */       int width = image.getWidth();
/* 176 */       int height = image.getHeight();
/* 177 */       Graphics g = image.createGraphics();
/*     */ 
/* 179 */       File _filebiao = new File(pressImg);
/* 180 */       Image src_biao = ImageIO.read(_filebiao);
/* 181 */       int width_biao = src_biao.getWidth(null);
/* 182 */       int height_biao = src_biao.getHeight(null);
/*     */ 
/* 184 */       int x = 0;
/* 185 */       int y = 0;
/* 186 */       switch (position_type) {
/*     */       case 1:
/* 188 */         x = 0;
/* 189 */         y = 0;
/* 190 */         break;
/*     */       case 2:
/* 192 */         x = (width - width_biao) / 2;
/* 193 */         y = 0;
/* 194 */         break;
/*     */       case 3:
/* 196 */         x = width - width_biao;
/* 197 */         y = 0;
/* 198 */         break;
/*     */       case 4:
/* 200 */         x = 0;
/* 201 */         y = (height - height_biao) / 2;
/* 202 */         break;
/*     */       case 5:
/* 204 */         x = (width - width_biao) / 2;
/* 205 */         y = (height - height_biao) / 2;
/* 206 */         break;
/*     */       case 6:
/* 208 */         x = width - width_biao;
/* 209 */         y = (height - height_biao) / 2;
/* 210 */         break;
/*     */       case 7:
/* 212 */         x = 0;
/* 213 */         y = height - height_biao;
/* 214 */         break;
/*     */       case 8:
/* 216 */         x = (width - width_biao) / 2;
/* 217 */         y = height - height_biao;
/* 218 */         break;
/*     */       case 9:
/* 220 */         x = width - width_biao;
/* 221 */         y = height - height_biao;
/*     */       }
/*     */ 
/* 225 */       g.drawImage(src_biao, x, y, width_biao, height_biao, null);
/*     */ 
/* 228 */       g.dispose();
/* 229 */       FileOutputStream out = new FileOutputStream(_file);
/*     */ 
/* 231 */       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
/* 232 */       JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
/* 233 */       param.setQuality(1.0F, false);
/* 234 */       encoder.setJPEGEncodeParam(param);
/* 235 */       encoder.encode(image);
/* 236 */       out.close();
/*     */     }
/*     */     catch (Exception e) {
/* 239 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void pressText(String pressText, File _file, String fontName, int fontStyle, String color, int fontSize, int position_type, float alpha, String extName)
/*     */   {
/*     */     try
/*     */     {
/* 268 */       if ((".gif".equals(extName)) && (isMovingGif(_file)))
/*     */       {
/* 270 */         System.out.println("pressText: file is moving gif");
/* 271 */         return;
/*     */       }
/* 273 */       Image src = ImageIO.read(_file);
/* 274 */       int width = src.getWidth(null);
/* 275 */       int height = src.getHeight(null);
/* 276 */       BufferedImage image = new BufferedImage(width, height, 
/* 277 */         1);
/* 278 */       Graphics2D g = image.createGraphics();
/*     */ 
/* 280 */       g.drawImage(src, 0, 0, width, height, null);
/* 281 */       g.setColor(Color.decode(color));
/* 282 */       g.setFont(new Font(fontName, fontStyle, fontSize));
/*     */ 
/* 284 */       g.setComposite(
/* 285 */         AlphaComposite.getInstance(10, 
/* 285 */         alpha));
/*     */ 
/* 287 */       int x = 0;
/* 288 */       int y = 0;
/* 289 */       switch (position_type) {
/*     */       case 1:
/* 291 */         x = 0;
/* 292 */         y = fontSize;
/* 293 */         break;
/*     */       case 2:
/* 295 */         x = (width - getLength(pressText) * fontSize) / 2;
/* 296 */         y = fontSize;
/* 297 */         break;
/*     */       case 3:
/* 299 */         x = width - getLength(pressText) * fontSize - fontSize;
/* 300 */         y = fontSize;
/* 301 */         break;
/*     */       case 4:
/* 303 */         x = 0;
/* 304 */         y = (height - fontSize) / 2;
/* 305 */         break;
/*     */       case 5:
/* 307 */         x = (width - getLength(pressText) * fontSize) / 2;
/* 308 */         y = (height - fontSize) / 2;
/* 309 */         break;
/*     */       case 6:
/* 311 */         x = width - getLength(pressText) * fontSize - fontSize;
/* 312 */         y = (height - fontSize) / 2;
/* 313 */         break;
/*     */       case 7:
/* 315 */         x = 0;
/* 316 */         y = height - 10;
/* 317 */         break;
/*     */       case 8:
/* 319 */         x = (width - getLength(pressText) * fontSize) / 2;
/* 320 */         y = height - 10;
/* 321 */         break;
/*     */       case 9:
/* 323 */         x = width - getLength(pressText) * fontSize - fontSize;
/* 324 */         y = height - 10;
/*     */       }
/*     */ 
/* 328 */       g.drawString(pressText, x, y);
/* 329 */       g.dispose();
/* 330 */       FileOutputStream out = new FileOutputStream(_file);
/* 331 */       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
/* 332 */       encoder.encode(image);
/* 333 */       out.close();
/*     */     } catch (Exception e) {
/* 335 */       System.out.println(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getLength(String text)
/*     */   {
/* 347 */     int length = 0;
/* 348 */     for (int i = 0; i < text.length(); i++) {
/* 349 */       if (new String(text.charAt(i)).getBytes().length > 1)
/* 350 */         length += 2;
/*     */       else {
/* 352 */         length++;
/*     */       }
/*     */     }
/* 355 */     return length / 2;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.ImageUtils
 * JD-Core Version:    0.6.2
 */