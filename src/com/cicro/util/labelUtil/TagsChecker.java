/*     */ package com.cicro.util.labelUtil;
/*     */ 
/*     */ public class TagsChecker
/*     */ {
/*     */   public static boolean check(String str)
/*     */   {
/*  10 */     TagsList[] unclosedTags = getUnclosedTags(str);
/*  11 */     if (unclosedTags[0].size() != 0) {
/*  12 */       return false;
/*     */     }
/*  14 */     for (int i = 0; i < unclosedTags[1].size(); i++) {
/*  15 */       if (unclosedTags[1].get(i) != null)
/*  16 */         return false;
/*     */     }
/*  18 */     return true;
/*     */   }
/*     */ 
/*     */   public static String fix(String str) {
/*  22 */     StringBuilder fixed = new StringBuilder();
/*  23 */     TagsList[] unclosedTags = getUnclosedTags(str);
/*     */ 
/*  25 */     for (int i = unclosedTags[0].size() - 1; i > -1; i--) {
/*  26 */       fixed.append("<" + unclosedTags[0].get(i) + ">");
/*     */     }
/*  28 */     fixed.append(str);
/*  29 */     for (int i = unclosedTags[1].size() - 1; i > -1; i--) {
/*  30 */       String s = null;
/*  31 */       if ((s = unclosedTags[1].get(i)) != null) {
/*  32 */         fixed.append("</" + s + ">");
/*     */       }
/*     */     }
/*  35 */     return fixed.toString();
/*     */   }
/*     */ 
/*     */   private static TagsList[] getUnclosedTags(String str) {
/*  39 */     StringBuilder temp = new StringBuilder();
/*  40 */     TagsList[] unclosedTags = new TagsList[2];
/*  41 */     unclosedTags[0] = new TagsList();
/*  42 */     unclosedTags[1] = new TagsList();
/*  43 */     boolean flag = false;
/*  44 */     char currentJump = ' ';
/*  45 */     char current = ' '; char last = ' ';
/*     */ 
/*  47 */     for (int i = 0; i < str.length(); ) {
/*  48 */       current = str.charAt(i++);
/*  49 */       if ((current == '"') || (current == '\'')) {
/*  50 */         flag = !flag;
/*  51 */         currentJump = current;
/*  52 */         if (flag) {
/*  53 */           while ((i < str.length()) && (str.charAt(i++) != currentJump));
/*  55 */           flag = false;
/*     */         }
/*     */       }
/*  58 */       else if (current == '<') {
/*  59 */         current = str.charAt(i++);
/*  60 */         if (current == '/') {
/*  61 */           current = str.charAt(i++);
/*     */ 
/*  63 */           while ((i < str.length()) && (current != '>')) {
/*  64 */             temp.append(current);
/*  65 */             current = str.charAt(i++);
/*     */           }
/*     */ 
/*  68 */           if (!unclosedTags[1].remove(temp.toString())) {
/*  69 */             unclosedTags[0].add(temp.toString());
/*     */           }
/*  71 */           temp.delete(0, temp.length());
/*     */         }
/*     */         else {
/*  74 */           last = current;
/*     */           do
/*     */           {
/*  77 */             temp.append(current);
/*  78 */             last = current;
/*  79 */             current = str.charAt(i++);
/*     */ 
/*  75 */             if ((i >= str.length()) || (current == ' ') || 
/*  76 */               (current == ' ')) break;  } while (current != '>');
/*     */ 
/*  82 */           while ((i < str.length()) && (current != '>')) {
/*  83 */             last = current;
/*  84 */             current = str.charAt(i++);
/*  85 */             if ((current == '"') || (current == '\'')) {
/*  86 */               flag = !flag;
/*  87 */               currentJump = current;
/*  88 */               if (flag) {
/*  89 */                 while ((i < str.length()) && 
/*  90 */                   (str.charAt(i++) != currentJump));
/*  92 */                 current = str.charAt(i++);
/*  93 */                 flag = false;
/*     */               }
/*     */             }
/*     */           }
/*  97 */           if ((last != '/') && (current == '>'))
/*  98 */             unclosedTags[1].add(temp.toString());
/*  99 */           temp.delete(0, temp.length());
/*     */         }
/*     */       }
/*     */     }
/* 103 */     return unclosedTags;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.labelUtil.TagsChecker
 * JD-Core Version:    0.6.2
 */