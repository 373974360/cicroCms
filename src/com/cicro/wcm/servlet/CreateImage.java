/*     */ package com.cicro.wcm.servlet;
/*     */ 
/*     */ import com.cicro.util.RandomStrg;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class CreateImage extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  26 */     response.setHeader("Pragma", "No-cache");
/*  27 */     response.setHeader("Cache-Control", "no-cache");
/*  28 */     response.setDateHeader("Expires", 0L);
/*     */     try
/*     */     {
/*  31 */       int width = 62; int height = 22;
/*     */ 
/*  34 */       BufferedImage image = new BufferedImage(width, height, 
/*  35 */         1);
/*     */ 
/*  37 */       Graphics g = image.createGraphics();
/*     */ 
/*  40 */       g.setColor(getRandColor(180, 250));
/*  41 */       g.fillRect(0, 0, width, height);
/*     */ 
/*  43 */       g.setFont(new Font("Times New Roman", 0, 16));
/*     */ 
/*  45 */       String rstr = "";
/*  46 */       RandomStrg rst = new RandomStrg();
/*  47 */       rst.setCharset("0-9");
/*  48 */       rst.setLength("2");
/*  49 */       rst.generateRandomObject();
/*  50 */       rstr = rst.getRandom();
/*     */ 
/*  53 */       String sRand = "";
/*  54 */       sRand = rstr;
/*     */ 
/*  56 */       String rand = "";
/*  57 */       int count = 0;
/*  58 */       for (int i = 0; i < sRand.length(); i++) {
/*  59 */         count += Integer.parseInt(String.valueOf(rstr.charAt(i)));
/*  60 */         rand = rand + String.valueOf(rstr.charAt(i));
/*  61 */         if (i == 0) {
/*  62 */           rand = rand + "+";
/*     */         }
/*     */ 
/*  65 */         g.setColor(getRandColor(10, 150));
/*     */       }
/*     */ 
/*  70 */       rand = rand + "=?";
/*  71 */       g.drawString(rand, 4, 17);
/*     */ 
/*  74 */       request.getSession().setAttribute("valiCode", count);
/*     */ 
/*  77 */       g.dispose();
/*     */ 
/*  80 */       ImageIO.write(image, "JPEG", response.getOutputStream());
/*     */     } catch (Exception e) {
/*  82 */       e.printStackTrace(System.out);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*     */   {
/*  88 */     doGet(request, response);
/*     */   }
/*     */ 
/*     */   Color getRandColor(int fc, int bc)
/*     */   {
/*  93 */     Random random = new Random();
/*  94 */     if (fc > 255)
/*  95 */       fc = 255;
/*  96 */     if (bc > 255)
/*  97 */       bc = 255;
/*  98 */     int r = fc + random.nextInt(bc - fc);
/*  99 */     int g = fc + random.nextInt(bc - fc);
/* 100 */     int b = fc + random.nextInt(bc - fc);
/* 101 */     return new Color(r, g, b);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.servlet.CreateImage
 * JD-Core Version:    0.6.2
 */