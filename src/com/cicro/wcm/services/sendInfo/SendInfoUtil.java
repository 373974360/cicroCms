/*    */ package com.cicro.wcm.services.sendInfo;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import sun.misc.BASE64Decoder;
/*    */ import sun.misc.BASE64Encoder;
/*    */ 
/*    */ public class SendInfoUtil
/*    */ {
/*    */   public static String encodeBase64File(String path)
/*    */   {
/* 15 */     String str = "";
/*    */     try {
/* 17 */       File file = new File(path);
/*    */ 
/* 19 */       FileInputStream inputFile = new FileInputStream(file);
/* 20 */       byte[] buffer = new byte[(int)file.length()];
/* 21 */       inputFile.read(buffer);
/* 22 */       inputFile.close();
/* 23 */       str = new BASE64Encoder().encode(buffer);
/*    */     } catch (FileNotFoundException e) {
/* 25 */       e.printStackTrace();
/*    */     } catch (IOException e) {
/* 27 */       e.printStackTrace();
/*    */     }
/* 29 */     return str;
/*    */   }
/*    */ 
/*    */   public static void decoderBase64File(String base64Code, String filePath, String fileName)
/*    */   {
/*    */     try
/*    */     {
/* 41 */       File file = new File(filePath);
/* 42 */       System.out.println("正在创建目录...");
/* 43 */       if (!file.exists())
/* 44 */         file.mkdirs();
/* 45 */       System.out.println("创建目录成功..." + filePath);
/* 46 */       System.out.println("正在写入文件..." + fileName);
/* 47 */       byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
/* 48 */       FileOutputStream out = new FileOutputStream(filePath + fileName);
/* 49 */       out.write(buffer);
/* 50 */       out.close();
/* 51 */       System.out.println("写入文件成功..." + fileName);
/*    */     } catch (IOException e) {
/* 53 */       e.printStackTrace();
/* 54 */       System.out.println("写入文件失败...");
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendInfoUtil
 * JD-Core Version:    0.6.2
 */