/*     */ package com.cicro.analyzer_bak_20151106;

import com.cicro.analyzer.*;
import com.cicro.analyzer.Lexeme;

/*     */
/*     */ class Context$IKSortedLinkSet
/*     */ {
/*     */   private com.cicro.analyzer.Lexeme head;
/*     */   private com.cicro.analyzer.Lexeme tail;
/* 178 */   private int size = 0;
/*     */ 
/*     */   private Context$IKSortedLinkSet(com.cicro.analyzer.Context paramContext)
/*     */   {
/*     */   }
/*     */ 
/*     */   private void addLexeme(com.cicro.analyzer.Lexeme lexeme) {
/* 185 */     if (this.size == 0) {
/* 186 */       this.head = lexeme;
/* 187 */       this.tail = lexeme;
/* 188 */       this.size += 1;
/* 189 */       return;
/*     */     }
/*     */ 
/* 192 */     if (this.tail.compareTo(lexeme) == 0) {
/* 193 */       return;
/*     */     }
/* 195 */     if (this.tail.compareTo(lexeme) < 0) {
/* 196 */       this.tail.setNext(lexeme);
/* 197 */       lexeme.setPrev(this.tail);
/* 198 */       this.tail = lexeme;
/* 199 */       this.size += 1;
/* 200 */       return;
/*     */     }
/* 202 */     if (this.head.compareTo(lexeme) > 0) {
/* 203 */       this.head.setPrev(lexeme);
/* 204 */       lexeme.setNext(this.head);
/* 205 */       this.head = lexeme;
/* 206 */       this.size += 1;
/* 207 */       return;
/*     */     }
/*     */ 
/* 211 */     com.cicro.analyzer.Lexeme l = this.tail;
/* 212 */     while ((l != null) && (l.compareTo(lexeme) > 0)) {
/* 213 */       l = l.getPrev();
/*     */     }
/* 215 */     if (l.compareTo(lexeme) == 0) {
/* 216 */       return;
/*     */     }
/* 218 */     if (l.compareTo(lexeme) < 0) {
/* 219 */       lexeme.setPrev(l);
/* 220 */       lexeme.setNext(l.getNext());
/* 221 */       l.getNext().setPrev(lexeme);
/* 222 */       l.setNext(lexeme);
/* 223 */       this.size += 1;
/* 224 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private com.cicro.analyzer.Lexeme pollFirst()
/*     */   {
/* 236 */     if (this.size == 1) {
/* 237 */       com.cicro.analyzer.Lexeme first = this.head;
/* 238 */       this.head = null;
/* 239 */       this.tail = null;
/* 240 */       this.size -= 1;
/* 241 */       return first;
/* 242 */     }if (this.size > 1) {
/* 243 */       com.cicro.analyzer.Lexeme first = this.head;
/* 244 */       this.head = first.getNext();
/* 245 */       first.setNext(null);
/* 246 */       this.size -= 1;
/* 247 */       return first;
/*     */     }
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */   private com.cicro.analyzer.Lexeme pollLast()
/*     */   {
/* 258 */     if (this.size == 1) {
/* 259 */       com.cicro.analyzer.Lexeme last = this.head;
/* 260 */       this.head = null;
/* 261 */       this.tail = null;
/* 262 */       this.size -= 1;
/* 263 */       return last;
/*     */     }
/* 265 */     if (this.size > 1) {
/* 266 */       com.cicro.analyzer.Lexeme last = this.tail;
/* 267 */       this.tail = last.getPrev();
/* 268 */       last.setPrev(null);
/* 269 */       this.size -= 1;
/* 270 */       return last;
/*     */     }
/*     */ 
/* 273 */     return null;
/*     */   }
/*     */ 
/*     */   private void excludeOverlap()
/*     */   {
/* 282 */     if (this.size > 1) {
/* 283 */       com.cicro.analyzer.Lexeme one = this.head;
/* 284 */       Lexeme another = one.getNext();
/*     */       do
/* 286 */         if (one.isOverlap(another))
/*     */         {
/* 292 */           another = another.getNext();
/*     */ 
/* 294 */           one.setNext(another);
/* 295 */           if (another != null) {
/* 296 */             another.setPrev(one);
/*     */           }
/* 298 */           this.size -= 1;
/*     */         }
/*     */         else {
/* 301 */           one = another;
/* 302 */           another = another.getNext();
/*     */         }
/* 304 */       while (another != null);
/*     */     }
/*     */   }
/*     */ 
/*     */   private int size() {
/* 309 */     return this.size;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.Context.IKSortedLinkSet
 * JD-Core Version:    0.6.2
 */