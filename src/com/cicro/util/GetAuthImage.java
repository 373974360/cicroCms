/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.ServletConfig;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class GetAuthImage extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public void init(ServletConfig conf)
/*     */     throws ServletException
/*     */   {
/*  33 */     super.init(conf);
/*  34 */     System.setProperty("java.awt.headless", "true");
/*     */   }
/*     */ 
/*     */   public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
/*     */     try {
/*  39 */       res.setContentType("image/jpeg");
/*  40 */       res.setHeader("Pragma", "No-cache");
/*  41 */       res.setHeader("Cache-Control", "no-cache");
/*  42 */       res.setDateHeader("Expires", 0L);
/*  43 */       HttpSession session = req.getSession();
/*     */ 
/*  47 */       int width = 100; int height = 25;
/*  48 */       BufferedImage image = new BufferedImage(width, height, 
/*  49 */         1);
/*     */ 
/*  51 */       Graphics g = image.getGraphics();
/*     */ 
/*  53 */       Random random = new Random();
/*     */ 
/*  55 */       g.setColor(getRandColor(250, 255));
/*  56 */       g.fillRect(0, 0, width, height);
/*     */ 
/*  59 */       g.setFont(new Font("Times New Roman", 3, 22));
/*     */ 
/*  65 */       g.setColor(getRandColor(240, 255));
/*  66 */       for (int i = 0; i < 10; i++) {
/*  67 */         int x = random.nextInt(width);
/*  68 */         int y = random.nextInt(height);
/*  69 */         int xl = random.nextInt(12);
/*  70 */         int yl = random.nextInt(12);
/*  71 */         g.drawLine(x, y, x + xl, y + yl);
/*     */       }
/*  73 */       String rstr = "";
/*  74 */       RandomStrg rst = new RandomStrg();
/*  75 */       rst.setCharset("0-9");
/*  76 */       rst.setLength("4");
/*     */ 
/*  78 */       rst.generateRandomObject();
/*  79 */       rstr = rst.getRandom();
/*     */ 
/*  82 */       String sRand = "";
/*  83 */       sRand = rstr;
/*  84 */       for (int i = 0; i < sRand.length(); i++)
/*     */       {
/*  86 */         String rand = String.valueOf(rstr.charAt(i));
/*     */ 
/*  89 */         g.setColor(new Color(20 + random.nextInt(110), 20 + random
/*  90 */           .nextInt(110), 20 + random.nextInt(110)));
/*     */ 
/*  92 */         g.drawString(rand, 20 * i + 6, 21);
/*     */       }
/*     */ 
/*  96 */       session.setAttribute("AUTHCODE", sRand);
/*     */ 
/*  99 */       g.dispose();
/*     */ 
/* 102 */       ImageIO.write(image, "JPEG", res.getOutputStream());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 107 */       e.printStackTrace(System.out);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
/*     */   {
/* 113 */     doGet(req, res);
/*     */   }
/*     */ 
/*     */   private Color getRandColor(int fc, int bc)
/*     */   {
/* 118 */     Random random = new Random();
/* 119 */     if (fc > 255)
/* 120 */       fc = 255;
/* 121 */     if (bc > 255)
/* 122 */       bc = 255;
/* 123 */     int r = fc + random.nextInt(bc - fc);
/* 124 */     int g = fc + random.nextInt(bc - fc);
/* 125 */     int b = fc + random.nextInt(bc - fc);
/* 126 */     return new Color(r, g, b);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.GetAuthImage
 * JD-Core Version:    0.6.2
 */