/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class MailEncode
/*    */ {
/* 75 */   public static final char[] alphabet = { 
/* 76 */     'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/* 77 */     'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
/* 78 */     'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
/* 79 */     'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
/* 80 */     'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
/* 81 */     'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
/* 82 */     '8', '9', '+', '/' };
/*    */ 
/*    */   public static String encode(String s)
/*    */   {
/* 22 */     return encode(s.getBytes());
/*    */   }
/*    */ 
/*    */   public static String encode(byte[] abyte0)
/*    */   {
/* 32 */     char[] ac = new char[((abyte0.length - 1) / 3 + 1) * 4];
/* 33 */     int k1 = 0;
/*    */ 
/* 35 */     for (int l1 = 0; l1 + 3 <= abyte0.length; )
/*    */     {
/* 37 */       int i = (abyte0[(l1++)] & 0xFF) << 16;
/* 38 */       i |= (abyte0[(l1++)] & 0xFF) << 8;
/* 39 */       i |= (abyte0[(l1++)] & 0xFF) << 0;
/* 40 */       int l = (i & 0xFC0000) >> 18;
/* 41 */       ac[(k1++)] = alphabet[l];
/* 42 */       l = (i & 0x3F000) >> 12;
/* 43 */       ac[(k1++)] = alphabet[l];
/* 44 */       l = (i & 0xFC0) >> 6;
/* 45 */       ac[(k1++)] = alphabet[l];
/* 46 */       l = i & 0x3F;
/* 47 */       ac[(k1++)] = alphabet[l];
/*    */     }
/*    */ 
/* 50 */     if (abyte0.length - l1 == 2)
/*    */     {
/* 52 */       int j = (abyte0[l1] & 0xFF) << 16;
/* 53 */       j |= (abyte0[(l1 + 1)] & 0xFF) << 8;
/* 54 */       int i1 = (j & 0xFC0000) >> 18;
/* 55 */       ac[(k1++)] = alphabet[i1];
/* 56 */       i1 = (j & 0x3F000) >> 12;
/* 57 */       ac[(k1++)] = alphabet[i1];
/* 58 */       i1 = (j & 0xFC0) >> 6;
/* 59 */       ac[(k1++)] = alphabet[i1];
/* 60 */       ac[(k1++)] = '=';
/*    */     }
/* 62 */     else if (abyte0.length - l1 == 1)
/*    */     {
/* 64 */       int k = (abyte0[l1] & 0xFF) << 16;
/* 65 */       int j1 = (k & 0xFC0000) >> 18;
/* 66 */       ac[(k1++)] = alphabet[j1];
/* 67 */       j1 = (k & 0x3F000) >> 12;
/* 68 */       ac[(k1++)] = alphabet[j1];
/* 69 */       ac[(k1++)] = '=';
/* 70 */       ac[(k1++)] = '=';
/*    */     }
/* 72 */     return new String(ac);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 86 */     System.out.println(encode("000"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.MailEncode
 * JD-Core Version:    0.6.2
 */