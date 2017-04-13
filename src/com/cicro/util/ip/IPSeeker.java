/*     */ package com.cicro.util.ip;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileChannel.MapMode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class IPSeeker
/*     */ {
/*  67 */   private static final String IP_FILE = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "class_Path") + "/QQWry.Dat");
/*     */   private static final int IP_RECORD_LENGTH = 7;
/*     */   private static final byte AREA_FOLLOWED = 1;
/*     */   private static final byte NO_AREA = 2;
/*     */   private Hashtable ipCache;
/*     */   private RandomAccessFile ipFile;
/*     */   private MappedByteBuffer mbb;
/*  79 */   private static IPSeeker instance = new IPSeeker();
/*     */   private long ipBegin;
/*     */   private long ipEnd;
/*     */   private IPSeeker.IPLocation loc;
/*     */   private byte[] buf;
/*     */   private byte[] b4;
/*     */   private byte[] b3;
/*     */ 
/*     */   private IPSeeker()
/*     */   {
/*  91 */     this.ipCache = new Hashtable();
/*  92 */     this.loc = new IPSeeker.IPLocation(this);
/*  93 */     this.buf = new byte[100];
/*  94 */     this.b4 = new byte[4];
/*  95 */     this.b3 = new byte[3];
/*     */     try {
/*  97 */       this.ipFile = new RandomAccessFile(IP_FILE, "r");
/*     */     }
/*     */     catch (FileNotFoundException e) {
/* 100 */       System.out.println(IP_FILE);
/* 101 */       System.out.println("IP地址信息文件没有找到，IP显示功能将无法使用");
/* 102 */       this.ipFile = null;
/*     */     }
/*     */ 
/* 105 */     if (this.ipFile != null)
/*     */       try {
/* 107 */         this.ipBegin = readLong4(0L);
/* 108 */         this.ipEnd = readLong4(4L);
/* 109 */         if ((this.ipBegin == -1L) || (this.ipEnd == -1L)) {
/* 110 */           this.ipFile.close();
/* 111 */           this.ipFile = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 114 */         System.out.println("IP地址信息文件格式有错误，IP显示功能将无法使用");
/* 115 */         this.ipFile = null;
/*     */       }
/*     */   }
/*     */ 
/*     */   public static IPSeeker getInstance()
/*     */   {
/* 123 */     return instance;
/*     */   }
/*     */ 
/*     */   public List getIPEntriesDebug(String s)
/*     */   {
/* 131 */     List ret = new ArrayList();
/* 132 */     long endOffset = this.ipEnd + 4L;
/* 133 */     for (long offset = this.ipBegin + 4L; offset <= endOffset; offset += 7L)
/*     */     {
/* 135 */       long temp = readLong3(offset);
/*     */ 
/* 137 */       if (temp != -1L) {
/* 138 */         IPSeeker.IPLocation loc = getIPLocation(temp);
/*     */ 
/* 140 */         if ((loc.country.indexOf(s) != -1) || (loc.area.indexOf(s) != -1)) {
/* 141 */           IPEntry entry = new IPEntry();
/* 142 */           entry.country = loc.country;
/* 143 */           entry.area = loc.area;
/*     */ 
/* 145 */           readIP(offset - 4L, this.b4);
/* 146 */           entry.beginIp = Utils.getIpStringFromBytes(this.b4);
/*     */ 
/* 148 */           readIP(temp, this.b4);
/* 149 */           entry.endIp = Utils.getIpStringFromBytes(this.b4);
/*     */ 
/* 151 */           ret.add(entry);
/*     */         }
/*     */       }
/*     */     }
/* 155 */     return ret;
/*     */   }
/*     */ 
/*     */   public List getIPEntries(String s)
/*     */   {
/* 163 */     List ret = new ArrayList();
/*     */     try
/*     */     {
/* 166 */       if (this.mbb == null) {
/* 167 */         FileChannel fc = this.ipFile.getChannel();
/* 168 */         this.mbb = fc.map(MapMode.READ_ONLY, 0L, this.ipFile.length());
/* 169 */         this.mbb.order(ByteOrder.LITTLE_ENDIAN);
/*     */       }
/* 171 */       int endOffset = (int)this.ipEnd;
/* 172 */       for (int offset = (int)this.ipBegin + 4; offset <= endOffset; offset += 7) {
/* 173 */         int temp = readInt3(offset);
/* 174 */         if (temp != -1) {
/* 175 */           IPSeeker.IPLocation loc = getIPLocation(temp);
/*     */ 
/* 177 */           if ((loc.country.indexOf(s) != -1) || (loc.area.indexOf(s) != -1)) {
/* 178 */             IPEntry entry = new IPEntry();
/* 179 */             entry.country = loc.country;
/* 180 */             entry.area = loc.area;
/*     */ 
/* 182 */             readIP(offset - 4, this.b4);
/* 183 */             entry.beginIp = Utils.getIpStringFromBytes(this.b4);
/*     */ 
/* 185 */             readIP(temp, this.b4);
/* 186 */             entry.endIp = Utils.getIpStringFromBytes(this.b4);
/*     */ 
/* 188 */             ret.add(entry);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 193 */       System.out.println(e.getMessage());
/*     */     }
/* 195 */     return ret;
/*     */   }
/*     */ 
/*     */   private int readInt3(int offset)
/*     */   {
/* 203 */     this.mbb.position(offset);
/* 204 */     return this.mbb.getInt() & 0xFFFFFF;
/*     */   }
/*     */ 
/*     */   private int readInt3()
/*     */   {
/* 211 */     return this.mbb.getInt() & 0xFFFFFF;
/*     */   }
/*     */ 
/*     */   public String getCountry(byte[] ip)
/*     */   {
/* 220 */     if (this.ipFile == null) return "错误的IP数据库文件";
/*     */ 
/* 222 */     String ipStr = Utils.getIpStringFromBytes(ip);
/*     */ 
/* 224 */     if (this.ipCache.containsKey(ipStr)) {
/* 225 */       IPSeeker.IPLocation loc = (IPSeeker.IPLocation)this.ipCache.get(ipStr);
/* 226 */       return loc.country;
/*     */     }
/* 228 */     IPSeeker.IPLocation loc = getIPLocation(ip);
/* 229 */     this.ipCache.put(ipStr, loc.getCopy());
/* 230 */     return loc.country;
/*     */   }
/*     */ 
/*     */   public String getCountry(String ip)
/*     */   {
/* 239 */     return getCountry(Utils.getIpByteArrayFromString(ip));
/*     */   }
/*     */ 
/*     */   public String getArea(byte[] ip)
/*     */   {
/* 248 */     if (this.ipFile == null) return "错误的IP数据库文件";
/*     */ 
/* 250 */     String ipStr = Utils.getIpStringFromBytes(ip);
/*     */ 
/* 252 */     if (this.ipCache.containsKey(ipStr)) {
/* 253 */       IPSeeker.IPLocation loc = (IPSeeker.IPLocation)this.ipCache.get(ipStr);
/* 254 */       return loc.area;
/*     */     }
/* 256 */     IPSeeker.IPLocation loc = getIPLocation(ip);
/* 257 */     this.ipCache.put(ipStr, loc.getCopy());
/* 258 */     return loc.area;
/*     */   }
/*     */ 
/*     */   public String getArea(String ip)
/*     */   {
/* 267 */     return getArea(Utils.getIpByteArrayFromString(ip));
/*     */   }
/*     */ 
/*     */   private IPSeeker.IPLocation getIPLocation(byte[] ip)
/*     */   {
/* 275 */     IPSeeker.IPLocation info = null;
/* 276 */     long offset = locateIP(ip);
/* 277 */     if (offset != -1L)
/* 278 */       info = getIPLocation(offset);
/* 279 */     if (info == null) {
/* 280 */       info = new IPSeeker.IPLocation(this);
/* 281 */       info.country = "未知国家";
/* 282 */       info.area = "未知地区";
/*     */     }
/* 284 */     return info;
/*     */   }
/*     */ 
/*     */   private long readLong4(long offset)
/*     */   {
/* 293 */     long ret = 0L;
/*     */     try {
/* 295 */       this.ipFile.seek(offset);
/* 296 */       ret |= this.ipFile.readByte() & 0xFF;
/* 297 */       ret |= this.ipFile.readByte() << 8 & 0xFF00;
/* 298 */       ret |= this.ipFile.readByte() << 16 & 0xFF0000;
/* 299 */       return ret | this.ipFile.readByte() << 24 & 0xFF000000;
/*     */     } catch (IOException e) {
/*     */     }
/* 302 */     return -1L;
/*     */   }
/*     */ 
/*     */   private long readLong3(long offset)
/*     */   {
/* 312 */     long ret = 0L;
/*     */     try {
/* 314 */       this.ipFile.seek(offset);
/* 315 */       this.ipFile.readFully(this.b3);
/* 316 */       ret |= this.b3[0] & 0xFF;
/* 317 */       ret |= this.b3[1] << 8 & 0xFF00;
/* 318 */       return ret | this.b3[2] << 16 & 0xFF0000;
/*     */     } catch (IOException e) {
/*     */     }
/* 321 */     return -1L;
/*     */   }
/*     */ 
/*     */   private long readLong3()
/*     */   {
/* 329 */     long ret = 0L;
/*     */     try {
/* 331 */       this.ipFile.readFully(this.b3);
/* 332 */       ret |= this.b3[0] & 0xFF;
/* 333 */       ret |= this.b3[1] << 8 & 0xFF00;
/* 334 */       return ret | this.b3[2] << 16 & 0xFF0000;
/*     */     } catch (IOException e) {
/*     */     }
/* 337 */     return -1L;
/*     */   }
/*     */ 
/*     */   private void readIP(long offset, byte[] ip)
/*     */   {
/*     */     try
/*     */     {
/* 348 */       this.ipFile.seek(offset);
/* 349 */       this.ipFile.readFully(ip);
/* 350 */       byte temp = ip[0];
/* 351 */       ip[0] = ip[3];
/* 352 */       ip[3] = temp;
/* 353 */       temp = ip[1];
/* 354 */       ip[1] = ip[2];
/* 355 */       ip[2] = temp;
/*     */     } catch (IOException e) {
/* 357 */       System.out.println(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readIP(int offset, byte[] ip)
/*     */   {
/* 367 */     this.mbb.position(offset);
/* 368 */     this.mbb.get(ip);
/* 369 */     byte temp = ip[0];
/* 370 */     ip[0] = ip[3];
/* 371 */     ip[3] = temp;
/* 372 */     temp = ip[1];
/* 373 */     ip[1] = ip[2];
/* 374 */     ip[2] = temp;
/*     */   }
/*     */ 
/*     */   private int compareIP(byte[] ip, byte[] beginIp)
/*     */   {
/* 383 */     for (int i = 0; i < 4; i++) {
/* 384 */       int r = compareByte(ip[i], beginIp[i]);
/* 385 */       if (r != 0)
/* 386 */         return r;
/*     */     }
/* 388 */     return 0;
/*     */   }
/*     */ 
/*     */   private int compareByte(byte b1, byte b2)
/*     */   {
/* 397 */     if ((b1 & 0xFF) > (b2 & 0xFF))
/* 398 */       return 1;
/* 399 */     if ((b1 ^ b2) == 0) {
/* 400 */       return 0;
/*     */     }
/* 402 */     return -1;
/*     */   }
/*     */ 
/*     */   private long locateIP(byte[] ip)
/*     */   {
/* 411 */     long m = 0L;
/*     */ 
/* 414 */     readIP(this.ipBegin, this.b4);
/* 415 */     int r = compareIP(ip, this.b4);
/* 416 */     if (r == 0) return this.ipBegin;
/* 417 */     if (r < 0) return -1L;
/*     */ 
/* 419 */     long i = this.ipBegin; for (long j = this.ipEnd; i < j; ) {
/* 420 */       m = getMiddleOffset(i, j);
/* 421 */       readIP(m, this.b4);
/* 422 */       r = compareIP(ip, this.b4);
/*     */ 
/* 424 */       if (r > 0)
/* 425 */         i = m;
/* 426 */       else if (r < 0) {
/* 427 */         if (m == j) {
/* 428 */           j -= 7L;
/* 429 */           m = j;
/*     */         } else {
/* 431 */           j = m;
/*     */         }
/*     */       } else return readLong3(m + 4L);
/*     */ 
/*     */     }
/*     */ 
/* 437 */     m = readLong3(m + 4L);
/* 438 */     readIP(m, this.b4);
/* 439 */     r = compareIP(ip, this.b4);
/* 440 */     if (r <= 0) return m;
/* 441 */     return -1L;
/*     */   }
/*     */ 
/*     */   private long getMiddleOffset(long begin, long end)
/*     */   {
/* 450 */     long records = (end - begin) / 7L;
/* 451 */     records >>= 1;
/* 452 */     if (records == 0L) records = 1L;
/* 453 */     return begin + records * 7L;
/*     */   }
/*     */ 
/*     */   private IPSeeker.IPLocation getIPLocation(long offset)
/*     */   {
/*     */     try
/*     */     {
/* 463 */       this.ipFile.seek(offset + 4L);
/*     */ 
/* 465 */       byte b = this.ipFile.readByte();
/* 466 */       if (b == 1)
/*     */       {
/* 468 */         long countryOffset = readLong3();
/*     */ 
/* 470 */         this.ipFile.seek(countryOffset);
/*     */ 
/* 472 */         b = this.ipFile.readByte();
/* 473 */         if (b == 2) {
/* 474 */           this.loc.country = readString(readLong3());
/* 475 */           this.ipFile.seek(countryOffset + 4L);
/*     */         } else {
/* 477 */           this.loc.country = readString(countryOffset);
/*     */         }
/* 479 */         this.loc.area = readArea(this.ipFile.getFilePointer());
/* 480 */       } else if (b == 2) {
/* 481 */         this.loc.country = readString(readLong3());
/* 482 */         this.loc.area = readArea(offset + 8L);
/*     */       } else {
/* 484 */         this.loc.country = readString(this.ipFile.getFilePointer() - 1L);
/* 485 */         this.loc.area = readArea(this.ipFile.getFilePointer());
/*     */       }
/* 487 */       return this.loc; } catch (IOException e) {
/*     */     }
/* 489 */     return null;
/*     */   }
/*     */ 
/*     */   private IPSeeker.IPLocation getIPLocation(int offset)
/*     */   {
/* 498 */     this.mbb.position(offset + 4);
/*     */ 
/* 500 */     byte b = this.mbb.get();
/* 501 */     if (b == 1)
/*     */     {
/* 503 */       int countryOffset = readInt3();
/*     */ 
/* 505 */       this.mbb.position(countryOffset);
/*     */ 
/* 507 */       b = this.mbb.get();
/* 508 */       if (b == 2) {
/* 509 */         this.loc.country = readString(readInt3());
/* 510 */         this.mbb.position(countryOffset + 4);
/*     */       } else {
/* 512 */         this.loc.country = readString(countryOffset);
/*     */       }
/* 514 */       this.loc.area = readArea(this.mbb.position());
/* 515 */     } else if (b == 2) {
/* 516 */       this.loc.country = readString(readInt3());
/* 517 */       this.loc.area = readArea(offset + 8);
/*     */     } else {
/* 519 */       this.loc.country = readString(this.mbb.position() - 1);
/* 520 */       this.loc.area = readArea(this.mbb.position());
/*     */     }
/* 522 */     return this.loc;
/*     */   }
/*     */ 
/*     */   private String readArea(long offset)
/*     */     throws IOException
/*     */   {
/* 531 */     this.ipFile.seek(offset);
/* 532 */     byte b = this.ipFile.readByte();
/* 533 */     if ((b == 1) || (b == 2)) {
/* 534 */       long areaOffset = readLong3(offset + 1L);
/* 535 */       if (areaOffset == 0L) {
/* 536 */         return "未知地区";
/*     */       }
/* 538 */       return readString(areaOffset);
/*     */     }
/* 540 */     return readString(offset);
/*     */   }
/*     */ 
/*     */   private String readArea(int offset)
/*     */   {
/* 547 */     this.mbb.position(offset);
/* 548 */     byte b = this.mbb.get();
/* 549 */     if ((b == 1) || (b == 2)) {
/* 550 */       int areaOffset = readInt3();
/* 551 */       if (areaOffset == 0) {
/* 552 */         return "未知地区";
/*     */       }
/* 554 */       return readString(areaOffset);
/*     */     }
/* 556 */     return readString(offset);
/*     */   }
/*     */ 
/*     */   private String readString(long offset)
/*     */   {
/*     */     try
/*     */     {
/* 565 */       this.ipFile.seek(offset);
/*     */ 
/* 567 */       int i = 0; for (this.buf[i] = this.ipFile.readByte(); this.buf[i] != 0; this.buf[(++i)] = this.ipFile.readByte());
/* 568 */       if (i != 0)
/* 569 */         return Utils.getString(this.buf, 0, i, "GBK");
/*     */     } catch (IOException e) {
/* 571 */       System.out.println(e.getMessage());
/*     */     }
/* 573 */     return "";
/*     */   }
/*     */ 
/*     */   private String readString(int offset)
/*     */   {
/*     */     try
/*     */     {
/* 582 */       this.mbb.position(offset);
/*     */ 
/* 584 */       int i = 0; for (this.buf[i] = this.mbb.get(); this.buf[i] != 0; this.buf[(++i)] = this.mbb.get());
/* 585 */       if (i != 0)
/* 586 */         return Utils.getString(this.buf, 0, i, "GBK");
/*     */     } catch (IllegalArgumentException e) {
/* 588 */       System.out.println(e.getMessage());
/*     */     }
/* 590 */     return "";
/*     */   }
/*     */   public String getAddress(String ip) {
/* 593 */     String country = getCountry(ip).equals("CZ88.NET") ? "" : getCountry(ip);
/* 594 */     String area = getArea(ip).equals("CZ88.NET") ? "" : getArea(ip);
/* 595 */     String address = country + " " + area;
/* 596 */     return address.trim();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.ip.IPSeeker
 * JD-Core Version:    0.6.2
 */