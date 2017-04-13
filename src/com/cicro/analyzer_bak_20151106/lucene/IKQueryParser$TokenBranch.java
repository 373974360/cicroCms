/*     */ package com.cicro.analyzer_bak_20151106.lucene;
/*     */ 
/*     */ import com.cicro.analyzer.Lexeme;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import com.cicro.analyzer.lucene.*;
import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.search.BooleanClause.Occur;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ 
/*     */ class IKQueryParser$TokenBranch
/*     */ {
/*     */   private static final int REFUSED = -1;
/*     */   private static final int ACCEPTED = 0;
/*     */   private static final int TONEXT = 1;
/*     */   private int leftBorder;
/*     */   private int rightBorder;
/*     */   private Lexeme lexeme;
/*     */   private List<TokenBranch> acceptedBranchs;
/*     */   private TokenBranch nextBranch;
/*     */ 
/*     */   IKQueryParser$TokenBranch(Lexeme lexeme)
/*     */   {
/* 331 */     if (lexeme != null) {
/* 332 */       this.lexeme = lexeme;
/*     */ 
/* 334 */       this.leftBorder = lexeme.getBeginPosition();
/* 335 */       this.rightBorder = lexeme.getEndPosition();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getLeftBorder() {
/* 340 */     return this.leftBorder;
/*     */   }
/*     */ 
/*     */   public int getRightBorder() {
/* 344 */     return this.rightBorder;
/*     */   }
/*     */ 
/*     */   public Lexeme getLexeme() {
/* 348 */     return this.lexeme;
/*     */   }
/*     */ 
/*     */   public List<TokenBranch> getAcceptedBranchs() {
/* 352 */     return this.acceptedBranchs;
/*     */   }
/*     */ 
/*     */   public TokenBranch getNextBranch() {
/* 356 */     return this.nextBranch;
/*     */   }
/*     */ 
/*     */   public int hashCode() {
/* 360 */     if (this.lexeme == null) {
/* 361 */       return 0;
/*     */     }
/* 363 */     return this.lexeme.hashCode() * 37;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 368 */     if (o == null) {
/* 369 */       return false;
/*     */     }
/* 371 */     if (this == o) {
/* 372 */       return true;
/*     */     }
/* 374 */     if ((o instanceof TokenBranch)) {
/* 375 */       TokenBranch other = (TokenBranch)o;
/* 376 */       if ((this.lexeme == null) || 
/* 377 */         (other.getLexeme() == null)) {
/* 378 */         return false;
/*     */       }
/* 380 */       return this.lexeme.equals(other.getLexeme());
/*     */     }
/*     */ 
/* 383 */     return false;
/*     */   }
/*     */ 
/*     */   boolean accept(Lexeme _lexeme)
/*     */   {
/* 400 */     int acceptType = checkAccept(_lexeme);
/* 401 */     switch (acceptType)
/*     */     {
/*     */     case -1:
/* 404 */       return false;
/*     */     case 0:
/* 407 */       if (this.acceptedBranchs == null)
/*     */       {
/* 409 */         this.acceptedBranchs = new ArrayList(2);
/* 410 */         this.acceptedBranchs.add(new TokenBranch(_lexeme));
/*     */       } else {
/* 412 */         boolean acceptedByChild = false;
/*     */ 
/* 414 */         for (TokenBranch childBranch : this.acceptedBranchs) {
/* 415 */           acceptedByChild = (childBranch.accept(_lexeme)) || (acceptedByChild);
/*     */         }
/*     */ 
/* 418 */         if (!acceptedByChild) {
/* 419 */           this.acceptedBranchs.add(new TokenBranch(_lexeme));
/*     */         }
/*     */       }
/*     */ 
/* 423 */       if (_lexeme.getEndPosition() > this.rightBorder) {
/* 424 */         this.rightBorder = _lexeme.getEndPosition();
/*     */       }
/* 426 */       break;
/*     */     case 1:
/* 430 */       if (this.nextBranch == null)
/*     */       {
/* 432 */         this.nextBranch = new TokenBranch(null);
/*     */       }
/* 434 */       this.nextBranch.accept(_lexeme);
/*     */     }
/*     */ 
/* 438 */     return true;
/*     */   }
/*     */ 
/*     */   List<Query> toQueries(String fieldName)
/*     */   {
/* 446 */     List queries = new ArrayList(1);
/*     */ 
/* 448 */     if (this.lexeme != null) {
/* 449 */       queries.add(new TermQuery(new Term(fieldName, this.lexeme.getLexemeText())));
/*     */     }
/*     */ 
/* 452 */     if ((this.acceptedBranchs != null) && (this.acceptedBranchs.size() > 0)) {
/* 453 */       if (this.acceptedBranchs.size() == 1) {
/* 454 */         Query onlyOneQuery = com.cicro.analyzer.lucene.IKQueryParser.access$0(((TokenBranch)this.acceptedBranchs.get(0)).toQueries(fieldName));
/* 455 */         if (onlyOneQuery != null)
/* 456 */           queries.add(onlyOneQuery);
/*     */       }
/*     */       else {
/* 459 */         BooleanQuery orQuery = new BooleanQuery();
/* 460 */         for (TokenBranch childBranch : this.acceptedBranchs) {
/* 461 */           Query childQuery = com.cicro.analyzer.lucene.IKQueryParser.access$0(childBranch.toQueries(fieldName));
/* 462 */           if (childQuery != null) {
/* 463 */             orQuery.add(childQuery, BooleanClause.Occur.SHOULD);
/*     */           }
/*     */         }
/* 466 */         if (orQuery.getClauses().length > 0) {
/* 467 */           queries.add(orQuery);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 472 */     if (this.nextBranch != null) {
/* 473 */       queries.addAll(this.nextBranch.toQueries(fieldName));
/*     */     }
/* 475 */     return queries;
/*     */   }
/*     */ 
/*     */   private int checkAccept(Lexeme _lexeme)
/*     */   {
/* 484 */     int acceptType = 0;
/*     */ 
/* 486 */     if (_lexeme == null) {
/* 487 */       throw new IllegalArgumentException("parameter:lexeme is null");
/*     */     }
/*     */ 
/* 490 */     if (this.lexeme == null) {
/* 491 */       if ((this.rightBorder > 0) && 
/* 492 */         (_lexeme.getBeginPosition() >= this.rightBorder))
/*     */       {
/* 494 */         acceptType = 1;
/*     */       }
/* 496 */       else acceptType = 0;
/*     */ 
/*     */     }
/* 500 */     else if (_lexeme.getBeginPosition() < this.lexeme.getBeginPosition())
/*     */     {
/* 502 */       acceptType = -1;
/* 503 */     } else if ((_lexeme.getBeginPosition() >= this.lexeme.getBeginPosition()) && 
/* 504 */       (_lexeme.getBeginPosition() < this.lexeme.getEndPosition()))
/*     */     {
/* 506 */       acceptType = -1;
/* 507 */     } else if ((_lexeme.getBeginPosition() >= this.lexeme.getEndPosition()) && 
/* 508 */       (_lexeme.getBeginPosition() < this.rightBorder))
/*     */     {
/* 510 */       acceptType = 0;
/*     */     }
/*     */     else {
/* 513 */       acceptType = 1;
/*     */     }
/*     */ 
/* 516 */     return acceptType;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKQueryParser.TokenBranch
 * JD-Core Version:    0.6.2
 */