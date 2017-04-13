/*     */ package com.cicro.util.io;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ public class BufferedRandomAccessFile extends RandomAccessFile
/*     */ {
/*     */   private static final int DEFAULT_BUFFER_BIT_LEN = 10;
/*     */   protected byte[] buf;
/*     */   protected int bufbitlen;
/*     */   protected int bufsize;
/*     */   protected long bufmask;
/*     */   protected boolean bufdirty;
/*     */   protected int bufusedsize;
/*     */   protected long curpos;
/*     */   protected long bufstartpos;
/*     */   protected long bufendpos;
/*     */   protected long fileendpos;
/*     */   protected boolean append;
/*     */   protected String filename;
/*     */   protected long initfilelen;
/*     */ 
/*     */   public BufferedRandomAccessFile(String name)
/*     */     throws IOException
/*     */   {
/*  39 */     this(name, "r", 10);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(File file) throws IOException, FileNotFoundException {
/*  43 */     this(file.getPath(), "r", 10);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(String name, int bufbitlen) throws IOException {
/*  47 */     this(name, "r", bufbitlen);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(File file, int bufbitlen) throws IOException, FileNotFoundException {
/*  51 */     this(file.getPath(), "r", bufbitlen);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(String name, String mode) throws IOException {
/*  55 */     this(name, mode, 10);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(File file, String mode) throws IOException, FileNotFoundException {
/*  59 */     this(file.getPath(), mode, 10);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(String name, String mode, int bufbitlen) throws IOException {
/*  63 */     super(name, mode);
/*  64 */     init(name, mode, bufbitlen);
/*     */   }
/*     */ 
/*     */   public BufferedRandomAccessFile(File file, String mode, int bufbitlen) throws IOException, FileNotFoundException {
/*  68 */     this(file.getPath(), mode, bufbitlen);
/*     */   }
/*     */ 
/*     */   private void init(String name, String mode, int bufbitlen) throws IOException {
/*  72 */     if (mode.equals("r"))
/*  73 */       this.append = false;
/*     */     else {
/*  75 */       this.append = true;
/*     */     }
/*     */ 
/*  78 */     this.filename = name;
/*  79 */     this.initfilelen = super.length();
/*  80 */     this.fileendpos = (this.initfilelen - 1L);
/*  81 */     this.curpos = super.getFilePointer();
/*     */ 
/*  83 */     if (bufbitlen < 0) {
/*  84 */       throw new IllegalArgumentException("bufbitlen size must >= 0");
/*     */     }
/*     */ 
/*  87 */     this.bufbitlen = bufbitlen;
/*  88 */     this.bufsize = (1 << bufbitlen);
/*  89 */     this.buf = new byte[this.bufsize];
/*  90 */     this.bufmask = (this.bufsize - 1L ^ 0xFFFFFFFF);
/*  91 */     this.bufdirty = false;
/*  92 */     this.bufusedsize = 0;
/*  93 */     this.bufstartpos = -1L;
/*  94 */     this.bufendpos = -1L;
/*     */   }
/*     */ 
/*     */   private void flushbuf() throws IOException {
/*  98 */     if (this.bufdirty) {
/*  99 */       if (super.getFilePointer() != this.bufstartpos) {
/* 100 */         super.seek(this.bufstartpos);
/*     */       }
/* 102 */       super.write(this.buf, 0, this.bufusedsize);
/* 103 */       this.bufdirty = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private int fillbuf() throws IOException {
/* 108 */     super.seek(this.bufstartpos);
/* 109 */     this.bufdirty = false;
/* 110 */     return super.read(this.buf);
/*     */   }
/*     */ 
/*     */   public byte read(long pos) throws IOException {
/* 114 */     if ((pos < this.bufstartpos) || (pos > this.bufendpos)) {
/* 115 */       flushbuf();
/* 116 */       seek(pos);
/*     */ 
/* 118 */       if ((pos < this.bufstartpos) || (pos > this.bufendpos)) {
/* 119 */         throw new IOException();
/*     */       }
/*     */     }
/* 122 */     this.curpos = pos;
/* 123 */     return this.buf[((int)(pos - this.bufstartpos))];
/*     */   }
/*     */ 
/*     */   public boolean write(byte bw) throws IOException {
/* 127 */     return write(bw, this.curpos);
/*     */   }
/*     */ 
/*     */   public boolean append(byte bw) throws IOException {
/* 131 */     return write(bw, this.fileendpos + 1L);
/*     */   }
/*     */ 
/*     */   public boolean write(byte bw, long pos) throws IOException
/*     */   {
/* 136 */     if ((pos >= this.bufstartpos) && (pos <= this.bufendpos)) {
/* 137 */       this.buf[((int)(pos - this.bufstartpos))] = bw;
/* 138 */       this.bufdirty = true;
/*     */ 
/* 140 */       if (pos == this.fileendpos + 1L) {
/* 141 */         this.fileendpos += 1L;
/* 142 */         this.bufusedsize += 1;
/*     */       }
/*     */     } else {
/* 145 */       seek(pos);
/*     */ 
/* 147 */       if ((pos >= 0L) && (pos <= this.fileendpos) && (this.fileendpos != 0L)) {
/* 148 */         this.buf[((int)(pos - this.bufstartpos))] = bw;
/*     */       }
/* 150 */       else if (((pos == 0L) && (this.fileendpos == 0L)) || (pos == this.fileendpos + 1L)) {
/* 151 */         this.buf[0] = bw;
/* 152 */         this.fileendpos += 1L;
/* 153 */         this.bufusedsize = 1;
/*     */       } else {
/* 155 */         throw new IndexOutOfBoundsException();
/*     */       }
/* 157 */       this.bufdirty = true;
/*     */     }
/* 159 */     this.curpos = pos;
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   public void write(byte[] b, int off, int len) throws IOException
/*     */   {
/* 165 */     long writeendpos = this.curpos + len - 1L;
/*     */ 
/* 167 */     if (writeendpos <= this.bufendpos) {
/* 168 */       System.arraycopy(b, off, this.buf, (int)(this.curpos - this.bufstartpos), len);
/* 169 */       this.bufdirty = true;
/* 170 */       this.bufusedsize = ((int)(writeendpos - this.bufstartpos + 1L));
/*     */     }
/*     */     else {
/* 173 */       super.seek(this.curpos);
/* 174 */       super.write(b, off, len);
/*     */     }
/*     */ 
/* 177 */     if (writeendpos > this.fileendpos) {
/* 178 */       this.fileendpos = writeendpos;
/*     */     }
/* 180 */     seek(writeendpos + 1L);
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int off, int len) throws IOException
/*     */   {
/* 185 */     long readendpos = this.curpos + len - 1L;
/*     */ 
/* 187 */     if ((readendpos <= this.bufendpos) && (readendpos <= this.fileendpos)) {
/* 188 */       System.arraycopy(this.buf, (int)(this.curpos - this.bufstartpos), b, off, len);
/*     */     }
/*     */     else {
/* 191 */       if (readendpos > this.fileendpos) {
/* 192 */         len = (int)(length() - this.curpos + 1L);
/*     */       }
/*     */ 
/* 195 */       super.seek(this.curpos);
/* 196 */       len = super.read(b, off, len);
/* 197 */       readendpos = this.curpos + len - 1L;
/*     */     }
/* 199 */     seek(readendpos + 1L);
/* 200 */     return len;
/*     */   }
/*     */ 
/*     */   public void write(byte[] b) throws IOException {
/* 204 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   public int read(byte[] b) throws IOException {
/* 208 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   public void seek(long pos) throws IOException
/*     */   {
/* 213 */     if ((pos < this.bufstartpos) || (pos > this.bufendpos)) {
/* 214 */       flushbuf();
/*     */ 
/* 216 */       if ((pos >= 0L) && (pos <= this.fileendpos) && (this.fileendpos != 0L)) {
/* 217 */         this.bufstartpos = (pos & this.bufmask);
/* 218 */         this.bufusedsize = fillbuf();
/*     */       }
/* 220 */       else if (((pos == 0L) && (this.fileendpos == 0L)) || (pos == this.fileendpos + 1L))
/*     */       {
/* 222 */         this.bufstartpos = pos;
/* 223 */         this.bufusedsize = 0;
/*     */       }
/* 225 */       this.bufendpos = (this.bufstartpos + this.bufsize - 1L);
/*     */     }
/* 227 */     this.curpos = pos;
/*     */   }
/*     */ 
/*     */   public long length() throws IOException {
/* 231 */     return max(this.fileendpos + 1L, this.initfilelen);
/*     */   }
/*     */ 
/*     */   public void setLength(long newLength) throws IOException {
/* 235 */     if (newLength > 0L)
/* 236 */       this.fileendpos = (newLength - 1L);
/*     */     else {
/* 238 */       this.fileendpos = 0L;
/*     */     }
/* 240 */     super.setLength(newLength);
/*     */   }
/*     */   public long getFilePointer() throws IOException {
/* 243 */     return this.curpos;
/*     */   }
/*     */ 
/*     */   private long max(long a, long b) {
/* 247 */     if (a > b) return a;
/* 248 */     return b;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 252 */     flushbuf();
/* 253 */     super.close();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws IOException {
/* 257 */     long readfilelen = 0L;
/*     */ 
/* 260 */     BufferedRandomAccessFile brafReadFile = new BufferedRandomAccessFile("C:\\WINNT\\Fonts\\STKAITI.TTF");
/* 261 */     readfilelen = brafReadFile.initfilelen;
/* 262 */     BufferedRandomAccessFile brafWriteFile = new BufferedRandomAccessFile(".\\STKAITI.001", "rw", 10);
/*     */ 
/* 264 */     byte[] buf = new byte[1024];
/*     */ 
/* 267 */     long start = System.currentTimeMillis();
/*     */     int readcount;
/* 269 */     while ((readcount = brafReadFile.read(buf)) != -1)
/*     */     {
/*     */       int readcount;
/* 270 */       brafWriteFile.write(buf, 0, readcount);
/*     */     }
/*     */ 
/* 273 */     brafWriteFile.close();
/* 274 */     brafReadFile.close();
/*     */ 
/* 276 */     System.out.println("BufferedRandomAccessFile Copy & Write File: " + 
/* 277 */       brafReadFile.filename + 
/* 278 */       "    FileSize: " + 
/* 279 */       Integer.toString((int)readfilelen >> 1024) + 
/* 280 */       " (KB)    " + 
/* 281 */       "Spend: " + 
/* 282 */       (System.currentTimeMillis() - start) / 1000.0D + 
/* 283 */       "(s)");
/*     */ 
/* 285 */     FileInputStream fdin = new FileInputStream("C:\\WINNT\\Fonts\\STKAITI.TTF");
/* 286 */     BufferedInputStream bis = new BufferedInputStream(fdin, 1024);
/* 287 */     DataInputStream dis = new DataInputStream(bis);
/*     */ 
/* 289 */     FileOutputStream fdout = new FileOutputStream(".\\STKAITI.002");
/* 290 */     BufferedOutputStream bos = new BufferedOutputStream(fdout, 1024);
/* 291 */     DataOutputStream dos = new DataOutputStream(bos);
/*     */ 
/* 293 */     start = System.currentTimeMillis();
/*     */ 
/* 295 */     for (int i = 0; i < readfilelen; i++) {
/* 296 */       dos.write(dis.readByte());
/*     */     }
/*     */ 
/* 299 */     dos.close();
/* 300 */     dis.close();
/*     */ 
/* 302 */     System.out.println("DataBufferedios Copy & Write File: " + 
/* 303 */       brafReadFile.filename + 
/* 304 */       "    FileSize: " + 
/* 305 */       Integer.toString((int)readfilelen >> 1024) + 
/* 306 */       " (KB)    " + 
/* 307 */       "Spend: " + 
/* 308 */       (System.currentTimeMillis() - start) / 1000.0D + 
/* 309 */       "(s)");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.io.BufferedRandomAccessFile
 * JD-Core Version:    0.6.2
 */