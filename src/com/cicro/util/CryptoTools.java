/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.security.Key;
/*    */ import java.security.spec.AlgorithmParameterSpec;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.DESKeySpec;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import sun.misc.BASE64Decoder;
/*    */ import sun.misc.BASE64Encoder;
/*    */ 
/*    */ public class CryptoTools
/*    */ {
/* 20 */   private final byte[] DESkey = "cicioweb".getBytes();
/* 21 */   private final byte[] DESIV = { 18, 52, 86, 120, 1, 2, 3, 4 };
/*    */ 
/* 23 */   private AlgorithmParameterSpec iv = null;
/* 24 */   private Key key = null;
/*    */ 
/*    */   public CryptoTools()
/*    */   {
/*    */     try
/*    */     {
/* 30 */       DESKeySpec keySpec = new DESKeySpec(this.DESkey);
/* 31 */       this.iv = new IvParameterSpec(this.DESIV);
/* 32 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
/* 33 */       this.key = keyFactory.generateSecret(keySpec);
/*    */     }
/*    */     catch (Exception e) {
/* 36 */       e.printStackTrace(System.out);
/*    */     }
/*    */   }
/*    */ 
/*    */   public String encode(String data)
/*    */   {
/*    */     try
/*    */     {
/* 44 */       Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
/* 45 */       enCipher.init(1, this.key, this.iv);
/* 46 */       byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
/* 47 */       BASE64Encoder base64Encoder = new BASE64Encoder();
/* 48 */       return "=#=" + base64Encoder.encode(pasByte);
/*    */     }
/*    */     catch (Exception e) {
/* 51 */       e.printStackTrace(System.out);
/* 52 */     }return data;
/*    */   }
/*    */ 
/*    */   public String decode(String data)
/*    */   {
/*    */     try
/*    */     {
/* 59 */       if (data.startsWith("=#="))
/*    */       {
/* 61 */         Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
/* 62 */         deCipher.init(2, this.key, this.iv);
/* 63 */         BASE64Decoder base64Decoder = new BASE64Decoder();
/*    */ 
/* 65 */         byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data.substring(3)));
/*    */ 
/* 67 */         return new String(pasByte, "UTF-8");
/*    */       }
/*    */ 
/* 70 */       return data;
/*    */     }
/*    */     catch (Exception e) {
/* 73 */       e.printStackTrace(System.out);
/* 74 */     }return data;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 80 */     CryptoTools ct = new CryptoTools();
/* 81 */     System.out.println(ct.encode("zzzz"));
/* 82 */     System.out.println(ct.decode("=#=mCX1K3glErs="));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.CryptoTools
 * JD-Core Version:    0.6.2
 */