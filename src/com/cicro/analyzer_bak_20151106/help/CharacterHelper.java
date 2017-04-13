/*    */ package com.cicro.analyzer_bak_20151106.help;
/*    */ 
/*    */ public class CharacterHelper
/*    */ {
/*    */   public static boolean isSpaceLetter(char input)
/*    */   {
/* 16 */     return (input == '\b') || (input == '\t') || 
/* 15 */       (input == '\n') || (input == '\r') || 
/* 16 */       (input == ' ') || (input == ' ');
/*    */   }
/*    */ 
/*    */   public static boolean isEnglishLetter(char input)
/*    */   {
/* 21 */     return ((input >= 'a') && (input <= 'z')) || (
/* 21 */       (input >= 'A') && (input <= 'Z'));
/*    */   }
/*    */ 
/*    */   public static boolean isArabicNumber(char input) {
/* 25 */     return (input >= '0') && (input <= '9');
/*    */   }
/*    */ 
/*    */   public static boolean isCJKCharacter(char input) {
/* 29 */     Character.UnicodeBlock ub = Character.UnicodeBlock.of(input);
/* 30 */     if ((ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) || 
/* 31 */       (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) || 
/* 32 */       (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) || 
/* 34 */       (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) || 
/* 36 */       (ub == Character.UnicodeBlock.HANGUL_SYLLABLES) || 
/* 37 */       (ub == Character.UnicodeBlock.HANGUL_JAMO) || 
/* 38 */       (ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) || 
/* 40 */       (ub == Character.UnicodeBlock.HIRAGANA) || 
/* 41 */       (ub == Character.UnicodeBlock.KATAKANA) || 
/* 42 */       (ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS))
/*    */     {
/* 44 */       return true;
/*    */     }
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   public static char regularize(char input)
/*    */   {
/* 60 */     if (input == '　') {
/* 61 */       input = ' ';
/*    */     }
/* 63 */     else if ((input > 65280) && (input < 65375)) {
/* 64 */       input = (char)(input - 65248);
/*    */     }
/* 66 */     else if ((input >= 'A') && (input <= 'Z')) {
/* 67 */       input = (char)(input + ' ');
/*    */     }
/*    */ 
/* 70 */     return input;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.help.CharacterHelper
 * JD-Core Version:    0.6.2
 */