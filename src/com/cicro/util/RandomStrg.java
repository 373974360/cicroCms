/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class RandomStrg
/*     */ {
/*     */   private String randomstr;
/*  20 */   private boolean allchars = false;
/*     */ 
/*  23 */   private Integer length = new Integer(8);
/*     */   private HashMap<String, String> hmap;
/*  27 */   private ArrayList<Character> lower = null;
/*     */ 
/*  29 */   private ArrayList<Character> upper = null;
/*     */ 
/*  31 */   private char[] single = null;
/*     */ 
/*  33 */   private int singlecount = 0;
/*     */ 
/*  35 */   private boolean singles = false;
/*     */ 
/*  37 */   private String algorithm = null;
/*     */ 
/*  39 */   private String provider = null;
/*     */ 
/*  41 */   private boolean secure = false;
/*     */ 
/*  43 */   private Random random = null;
/*     */ 
/*  45 */   private SecureRandom secrandom = null;
/*     */ 
/*     */   private final float getFloat() {
/*  48 */     if (this.random == null) {
/*  49 */       return this.secrandom.nextFloat();
/*     */     }
/*  51 */     return this.random.nextFloat();
/*     */   }
/*     */ 
/*     */   public final void generateRandomObject()
/*     */     throws Exception
/*     */   {
/*  62 */     if (this.secure)
/*     */       try
/*     */       {
/*  65 */         if (this.provider != null)
/*     */         {
/*  67 */           this.random = SecureRandom.getInstance(this.algorithm, this.provider);
/*     */         }
/*  69 */         else this.random = SecureRandom.getInstance(this.algorithm); 
/*     */       }
/*  71 */       catch (NoSuchAlgorithmException ne) { throw new Exception(ne.getMessage());
/*     */       } catch (NoSuchProviderException pe) {
/*  73 */         throw new Exception(pe.getMessage());
/*     */       }
/*     */     else
/*  76 */       this.random = new Random();
/*     */   }
/*     */ 
/*     */   private final void generaterandom()
/*     */   {
/*  85 */     if (this.allchars)
/*  86 */       for (int i = 0; i < this.length.intValue(); i++)
/*  87 */         this.randomstr = 
/*  88 */           (this.randomstr + 
/*  88 */           new Character(
/*  89 */           (char)(34 + (int)(getFloat() * 93.0F)))
/*  90 */           .toString());
/*  91 */     else if (this.singles)
/*     */     {
/*  94 */       if (this.upper.size() == 3)
/*     */       {
/*  99 */         for (int i = 0; i < this.length.intValue(); i++)
/*     */         {
/* 106 */           if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */           {
/* 109 */             if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */             {
/* 111 */               this.randomstr += randomSingle().toString();
/*     */             }
/*     */             else {
/* 114 */               this.randomstr = 
/* 115 */                 (this.randomstr + 
/* 115 */                 randomChar((Character)this.lower.get(2), 
/* 116 */                 (Character)this.upper.get(2))
/* 117 */                 .toString());
/*     */             }
/*     */ 
/*     */           }
/* 121 */           else if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */           {
/* 123 */             this.randomstr = 
/* 124 */               (this.randomstr + 
/* 124 */               randomChar((Character)this.lower.get(1), 
/* 125 */               (Character)this.upper.get(1))
/* 126 */               .toString());
/*     */           }
/*     */           else
/* 129 */             this.randomstr = 
/* 130 */               (this.randomstr + 
/* 130 */               randomChar((Character)this.lower.get(0), 
/* 131 */               (Character)this.upper.get(0))
/* 132 */               .toString());
/*     */         }
/*     */       }
/* 135 */       else if (this.upper.size() == 2)
/*     */       {
/* 140 */         for (int i = 0; i < this.length.intValue(); i++)
/*     */         {
/* 145 */           if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */           {
/* 148 */             this.randomstr += randomSingle().toString();
/* 149 */           } else if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */           {
/* 152 */             this.randomstr = 
/* 153 */               (this.randomstr + 
/* 153 */               randomChar((Character)this.lower.get(1), 
/* 154 */               (Character)this.upper.get(1)).toString());
/*     */           }
/*     */           else
/*     */           {
/* 158 */             this.randomstr = 
/* 159 */               (this.randomstr + 
/* 159 */               randomChar((Character)this.lower.get(0), 
/* 160 */               (Character)this.upper.get(0)).toString());
/*     */           }
/*     */         }
/*     */       }
/* 163 */       else if (this.upper.size() == 1)
/*     */       {
/* 166 */         for (int i = 0; i < this.length.intValue(); i++) {
/* 167 */           if ((int)getFloat() * 100 % 2 == 0)
/*     */           {
/* 169 */             this.randomstr += randomSingle().toString();
/*     */           }
/*     */           else
/* 172 */             this.randomstr = 
/* 173 */               (this.randomstr + 
/* 173 */               randomChar((Character)this.lower.get(0), 
/* 174 */               (Character)this.upper.get(0)).toString());
/*     */         }
/*     */       }
/*     */       else {
/* 178 */         for (int i = 0; i < this.length.intValue(); i++) {
/* 179 */           this.randomstr += randomSingle().toString();
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/* 184 */     else if (this.upper.size() == 3)
/*     */     {
/* 187 */       for (int i = 0; i < this.length.intValue(); i++)
/*     */       {
/* 189 */         if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */         {
/* 192 */           this.randomstr = 
/* 193 */             (this.randomstr + 
/* 193 */             randomChar((Character)this.lower.get(2), 
/* 194 */             (Character)this.upper.get(2)).toString());
/* 195 */         } else if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */         {
/* 198 */           this.randomstr = 
/* 199 */             (this.randomstr + 
/* 199 */             randomChar((Character)this.lower.get(1), 
/* 200 */             (Character)this.upper.get(1)).toString());
/*     */         }
/*     */         else
/*     */         {
/* 204 */           this.randomstr = 
/* 205 */             (this.randomstr + 
/* 205 */             randomChar((Character)this.lower.get(0), 
/* 206 */             (Character)this.upper.get(0)).toString());
/*     */         }
/*     */       }
/*     */     }
/* 209 */     else if (this.upper.size() == 2)
/*     */     {
/* 212 */       for (int i = 0; i < this.length.intValue(); i++) {
/* 213 */         if ((int)(getFloat() * 100.0F) % 2 == 0)
/*     */         {
/* 215 */           this.randomstr = 
/* 216 */             (this.randomstr + 
/* 216 */             randomChar((Character)this.lower.get(1), 
/* 217 */             (Character)this.upper.get(1)).toString());
/*     */         }
/*     */         else {
/* 220 */           this.randomstr = 
/* 221 */             (this.randomstr + 
/* 221 */             randomChar((Character)this.lower.get(0), 
/* 222 */             (Character)this.upper.get(0)).toString());
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/* 227 */       for (int i = 0; i < this.length.intValue(); i++)
/*     */       {
/* 229 */         this.randomstr = 
/* 230 */           (this.randomstr + 
/* 230 */           randomChar((Character)this.lower.get(0), 
/* 231 */           (Character)this.upper.get(0)).toString());
/*     */       }
/*     */   }
/*     */ 
/*     */   private final Character randomSingle()
/*     */   {
/* 243 */     return new Character(this.single[((int)(getFloat() * this.singlecount - 1.0F))]);
/*     */   }
/*     */ 
/*     */   private final Character randomChar(Character lower, Character upper)
/*     */   {
/* 259 */     char low = lower.charValue();
/* 260 */     char up = upper.charValue();
/*     */ 
/* 263 */     int tempval = (int)(low + getFloat() * (up - low));
/*     */ 
/* 266 */     return new Character((char)tempval);
/*     */   }
/*     */ 
/*     */   public final String getRandom()
/*     */   {
/* 275 */     this.randomstr = new String();
/*     */ 
/* 277 */     generaterandom();
/*     */ 
/* 279 */     if (this.hmap != null)
/*     */     {
/* 281 */       while (this.hmap.containsKey(this.randomstr))
/*     */       {
/* 284 */         generaterandom();
/*     */       }
/*     */ 
/* 287 */       this.hmap.put(this.randomstr, null);
/*     */     }
/*     */ 
/* 290 */     return this.randomstr;
/*     */   }
/*     */ 
/*     */   public final void setRanges(ArrayList<Character> low, ArrayList<Character> up)
/*     */   {
/* 303 */     this.lower = low;
/* 304 */     this.upper = up;
/*     */   }
/*     */ 
/*     */   public final void setHmap(HashMap<String, String> map)
/*     */   {
/* 316 */     this.hmap = map;
/*     */   }
/*     */ 
/*     */   public final void setLength(String value)
/*     */   {
/* 327 */     this.length = new Integer(value);
/*     */   }
/*     */ 
/*     */   public final void setAlgorithm(String value)
/*     */   {
/* 339 */     this.algorithm = value;
/* 340 */     this.secure = true;
/*     */   }
/*     */ 
/*     */   public final void setProvider(String value)
/*     */   {
/* 351 */     this.provider = value;
/*     */   }
/*     */ 
/*     */   public final void setAllchars(boolean value)
/*     */   {
/* 362 */     this.allchars = value;
/*     */   }
/*     */ 
/*     */   public final void setSingle(char[] chars, int value)
/*     */   {
/* 376 */     this.single = chars;
/* 377 */     this.singlecount = value;
/* 378 */     this.singles = true;
/*     */   }
/*     */ 
/*     */   public final void setCharset(String value)
/*     */   {
/* 383 */     boolean more = true;
/*     */ 
/* 387 */     this.lower = new ArrayList(3);
/* 388 */     this.upper = new ArrayList(3);
/*     */ 
/* 391 */     if (value.compareTo("all") == 0) {
/* 392 */       this.allchars = true;
/*     */ 
/* 395 */       more = false;
/* 396 */     } else if ((value.charAt(1) == '-') && (value.charAt(0) != '\\'))
/*     */     {
/* 398 */       while ((more) && (value.charAt(1) == '-'))
/*     */       {
/* 401 */         if (value.charAt(0) == '\\')
/*     */         {
/*     */           break;
/*     */         }
/* 405 */         this.lower.add(new Character(value.charAt(0)));
/* 406 */         this.upper.add(new Character(value.charAt(2)));
/*     */ 
/* 410 */         if (value.length() <= 3) {
/* 411 */           more = false;
/*     */         }
/*     */         else
/*     */         {
/* 416 */           value = value.substring(3);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 421 */     if (more)
/*     */     {
/* 423 */       this.single = new char[30];
/*     */ 
/* 426 */       StringTokenizer tokens = new StringTokenizer(value);
/*     */ 
/* 428 */       while (tokens.hasMoreTokens())
/*     */       {
/* 430 */         String token = tokens.nextToken();
/*     */ 
/* 432 */         if (token.length() > 1)
/*     */         {
/* 434 */           this.single[(this.singlecount++)] = '-';
/*     */         }
/*     */ 
/* 437 */         this.single[(this.singlecount++)] = token.charAt(0);
/*     */       }
/*     */     }
/* 440 */     if ((this.lower == null) && (this.single == null))
/* 441 */       setCharset("a-zA-Z0-9");
/*     */   }
/*     */ 
/*     */   public static String getRandomStr(String charset, String strLength)
/*     */   {
/* 452 */     RandomStrg rs = new RandomStrg();
/* 453 */     if ((charset == null) || ("".equals(charset)))
/*     */     {
/* 455 */       rs.setCharset("A-Z0-9");
/*     */     }
/* 457 */     else rs.setCharset(charset);
/*     */ 
/* 459 */     if ((charset == null) || ("".equals(strLength)))
/*     */     {
/* 461 */       rs.setLength("4");
/*     */     }
/* 463 */     else rs.setLength(strLength);
/*     */     try
/*     */     {
/* 466 */       rs.generateRandomObject();
/* 467 */       return rs.getRandom();
/*     */     }
/*     */     catch (Exception e) {
/* 470 */       e.printStackTrace();
/* 471 */     }return "8888";
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.RandomStrg
 * JD-Core Version:    0.6.2
 */