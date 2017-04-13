/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.BufferedOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ 
/*    */ public class CGZipManager
/*    */ {
/*    */   public void compress(String strSource, String strTarget)
/*    */   {
/*    */     try
/*    */     {
/* 29 */       File source = new File(strSource);
/* 30 */       long size = source.length();
/* 31 */       InputStream bis = new FileInputStream(source);
/* 32 */       OutputStream out = 
/* 33 */         new GZIPOutputStream(
/* 34 */         new FileOutputStream(strTarget));
/*    */ 
/* 36 */       byte[] temp = new byte[(int)size];
/* 37 */       while (bis.read(temp) != -1)
/*    */       {
/* 39 */         out.write(temp);
/*    */       }
/* 41 */       bis.close();
/* 42 */       out.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 46 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void compress(byte[] b, String strTarget)
/*    */   {
/*    */     try
/*    */     {
/* 59 */       BufferedOutputStream out = 
/* 60 */         new BufferedOutputStream(
/* 61 */         new GZIPOutputStream(
/* 62 */         new FileOutputStream(strTarget)));
/* 63 */       out.write(b);
/* 64 */       out.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 68 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 74 */     long before = new Date().getTime();
/* 75 */     String strS = "F:/test.txt";
/* 76 */     String strT = "F:/test.gz";
/* 77 */     CGZipManager g = new CGZipManager();
/* 78 */     g.compress(strS, strT);
/* 79 */     long after = new Date().getTime();
/* 80 */     System.out.println("cost:" + (after - before) + " mini-secs");
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.CGZipManager
 * JD-Core Version:    0.6.2
 */