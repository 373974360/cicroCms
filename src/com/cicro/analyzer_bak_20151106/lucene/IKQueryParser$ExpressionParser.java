/*     */ package com.cicro.analyzer_bak_20151106.lucene;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ import com.cicro.analyzer.lucene.*;
import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.search.BooleanClause;
/*     */ import org.apache.lucene.search.BooleanClause.Occur;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Query;
/*     */ import org.apache.lucene.search.TermQuery;
/*     */ 
/*     */ class IKQueryParser$ExpressionParser
/*     */ {
/* 535 */   private List<com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element> elements = new ArrayList();
/*     */ 
/* 537 */   private Stack<Query> querys = new Stack();
/*     */ 
/* 539 */   private Stack<com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element> operates = new Stack();
/*     */ 
/*     */   public Query parserExp(String expression)
/*     */   {
/* 551 */     Query lucenceQuery = null;
/*     */     try
/*     */     {
/* 554 */       splitElements(expression);
/*     */ 
/* 556 */       parseSyntax();
/* 557 */       if (this.querys.size() == 1)
/* 558 */         lucenceQuery = (Query)this.querys.pop();
/*     */       else
/* 560 */         throw new IllegalStateException("表达式异常： 缺少逻辑操作符");
/*     */     }
/*     */     finally {
/* 563 */       this.elements.clear();
/* 564 */       this.querys.clear();
/* 565 */       this.operates.clear();
/*     */     }
/* 563 */     this.elements.clear();
/* 564 */     this.querys.clear();
/* 565 */     this.operates.clear();
/*     */ 
/* 567 */     return lucenceQuery;
/*     */   }
/*     */ 
/*     */   private void splitElements(String expression)
/*     */   {
/* 576 */     if (expression == null) {
/* 577 */       return;
/*     */     }
/* 579 */     com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element curretElement = null;
/*     */ 
/* 581 */     char[] expChars = expression.toCharArray();
/* 582 */     for (int i = 0; i < expChars.length; i++) {
/* 583 */       switch (expChars[i]) {
/*     */       case '&':
/* 585 */         if (curretElement == null) {
/* 586 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 587 */           curretElement.type = '&';
/* 588 */           curretElement.append(expChars[i]);
/* 589 */         } else if (curretElement.type == '&') {
/* 590 */           curretElement.append(expChars[i]);
/* 591 */           this.elements.add(curretElement);
/* 592 */           curretElement = null;
/* 593 */         } else if (curretElement.type == '\'') {
/* 594 */           curretElement.append(expChars[i]);
/*     */         } else {
/* 596 */           this.elements.add(curretElement);
/* 597 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 598 */           curretElement.type = '&';
/* 599 */           curretElement.append(expChars[i]);
/*     */         }
/* 601 */         break;
/*     */       case '|':
/* 604 */         if (curretElement == null) {
/* 605 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 606 */           curretElement.type = '|';
/* 607 */           curretElement.append(expChars[i]);
/* 608 */         } else if (curretElement.type == '|') {
/* 609 */           curretElement.append(expChars[i]);
/* 610 */           this.elements.add(curretElement);
/* 611 */           curretElement = null;
/* 612 */         } else if (curretElement.type == '\'') {
/* 613 */           curretElement.append(expChars[i]);
/*     */         } else {
/* 615 */           this.elements.add(curretElement);
/* 616 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 617 */           curretElement.type = '|';
/* 618 */           curretElement.append(expChars[i]);
/*     */         }
/* 620 */         break;
/*     */       case '-':
/* 623 */         if (curretElement != null) {
/* 624 */           if (curretElement.type == '\'') {
/* 625 */             curretElement.append(expChars[i]);
/*     */           }
/*     */           else
/* 628 */             this.elements.add(curretElement);
/*     */         }
/*     */         else {
/* 631 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 632 */           curretElement.type = '-';
/* 633 */           curretElement.append(expChars[i]);
/* 634 */           this.elements.add(curretElement);
/* 635 */           curretElement = null;
/* 636 */         }break;
/*     */       case '(':
/* 639 */         if (curretElement != null) {
/* 640 */           if (curretElement.type == '\'') {
/* 641 */             curretElement.append(expChars[i]);
/*     */           }
/*     */           else
/* 644 */             this.elements.add(curretElement);
/*     */         }
/*     */         else {
/* 647 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 648 */           curretElement.type = '(';
/* 649 */           curretElement.append(expChars[i]);
/* 650 */           this.elements.add(curretElement);
/* 651 */           curretElement = null;
/* 652 */         }break;
/*     */       case ')':
/* 655 */         if (curretElement != null) {
/* 656 */           if (curretElement.type == '\'') {
/* 657 */             curretElement.append(expChars[i]);
/*     */           }
/*     */           else
/* 660 */             this.elements.add(curretElement);
/*     */         }
/*     */         else {
/* 663 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 664 */           curretElement.type = ')';
/* 665 */           curretElement.append(expChars[i]);
/* 666 */           this.elements.add(curretElement);
/* 667 */           curretElement = null;
/* 668 */         }break;
/*     */       case ':':
/* 671 */         if (curretElement != null) {
/* 672 */           if (curretElement.type == '\'') {
/* 673 */             curretElement.append(expChars[i]);
/*     */           }
/*     */           else
/* 676 */             this.elements.add(curretElement);
/*     */         }
/*     */         else {
/* 679 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 680 */           curretElement.type = ':';
/* 681 */           curretElement.append(expChars[i]);
/* 682 */           this.elements.add(curretElement);
/* 683 */           curretElement = null;
/* 684 */         }break;
/*     */       case '=':
/* 687 */         if (curretElement != null) {
/* 688 */           if (curretElement.type == '\'') {
/* 689 */             curretElement.append(expChars[i]);
/*     */           }
/*     */           else
/* 692 */             this.elements.add(curretElement);
/*     */         }
/*     */         else {
/* 695 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 696 */           curretElement.type = '=';
/* 697 */           curretElement.append(expChars[i]);
/* 698 */           this.elements.add(curretElement);
/* 699 */           curretElement = null;
/* 700 */         }break;
/*     */       case ' ':
/* 703 */         if (curretElement != null) {
/* 704 */           if (curretElement.type == '\'') {
/* 705 */             curretElement.append(expChars[i]);
/*     */           } else {
/* 707 */             this.elements.add(curretElement);
/* 708 */             curretElement = null;
/*     */           }
/*     */         }
/*     */ 
/* 712 */         break;
/*     */       case '\'':
/* 715 */         if (curretElement == null) {
/* 716 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 717 */           curretElement.type = '\'';
/*     */         }
/* 719 */         else if (curretElement.type == '\'') {
/* 720 */           this.elements.add(curretElement);
/* 721 */           curretElement = null;
/*     */         }
/*     */         else {
/* 724 */           this.elements.add(curretElement);
/* 725 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 726 */           curretElement.type = '\'';
/*     */         }
/*     */ 
/* 729 */         break;
/*     */       default:
/* 732 */         if (curretElement == null) {
/* 733 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 734 */           curretElement.type = 'F';
/* 735 */           curretElement.append(expChars[i]);
/*     */         }
/* 737 */         else if (curretElement.type == 'F') {
/* 738 */           curretElement.append(expChars[i]);
/*     */         }
/* 740 */         else if (curretElement.type == '\'') {
/* 741 */           curretElement.append(expChars[i]);
/*     */         }
/*     */         else {
/* 744 */           this.elements.add(curretElement);
/* 745 */           curretElement = new com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element(this);
/* 746 */           curretElement.type = 'F';
/* 747 */           curretElement.append(expChars[i]);
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/* 752 */     if (curretElement != null) {
/* 753 */       this.elements.add(curretElement);
/* 754 */       curretElement = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseSyntax()
/*     */   {
/* 763 */     for (int i = 0; i < this.elements.size(); i++) {
/* 764 */       com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element e = (com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element)this.elements.get(i);
/* 765 */       if ('F' == e.type) {
/* 766 */         com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element e2 = (com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element)this.elements.get(i + 1);
/* 767 */         if (('=' != e2.type) && (':' != e2.type)) {
/* 768 */           throw new IllegalStateException("表达式异常： = 或 ： 号丢失");
/*     */         }
/* 770 */         com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element e3 = (com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element)this.elements.get(i + 2);
/* 771 */         if ('\'' != e3.type) {
/* 772 */           throw new IllegalStateException("表达式异常：匹配值丢失");
/*     */         }
/* 774 */         i += 2;
/* 775 */         if ('=' == e2.type) {
/* 776 */           TermQuery tQuery = new TermQuery(new Term(e.toString(), e3.toString()));
/* 777 */           this.querys.push(tQuery);
/* 778 */         } else if (':' == e2.type) {
/*     */           try {
/* 780 */             Query tQuery = com.cicro.analyzer.lucene.IKQueryParser.parse(e.toString(), e3.toString());
/* 781 */             this.querys.push(tQuery);
/*     */           } catch (IOException e1) {
/* 783 */             e1.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/* 787 */       else if ('(' == e.type) {
/* 788 */         this.operates.push(e);
/*     */       }
/* 790 */       else if (')' == e.type) {
/* 791 */         boolean doPop = true;
/*     */         do {
/* 793 */           com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element op = (com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element)this.operates.pop();
/* 794 */           if ('(' == op.type) {
/* 795 */             doPop = false;
/*     */           } else {
/* 797 */             Query q = toQuery(op);
/* 798 */             this.querys.push(q);
/*     */           }
/* 792 */           if (!doPop) break;  } while (!this.operates.empty());
/*     */       }
/* 804 */       else if (this.operates.isEmpty()) {
/* 805 */         this.operates.push(e);
/*     */       } else {
/* 807 */         boolean doPeek = true;
/* 808 */         while ((doPeek) && (!this.operates.isEmpty())) {
/* 809 */           com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element eleOnTop = (com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element)this.operates.peek();
/* 810 */           if ('(' == eleOnTop.type) {
/* 811 */             doPeek = false;
/* 812 */             this.operates.push(e);
/* 813 */           } else if (compare(e, eleOnTop) == 1) {
/* 814 */             this.operates.push(e);
/* 815 */             doPeek = false;
/* 816 */           } else if (compare(e, eleOnTop) == 0) {
/* 817 */             Query q = toQuery(eleOnTop);
/* 818 */             this.operates.pop();
/* 819 */             this.querys.push(q);
/*     */           } else {
/* 821 */             Query q = toQuery(eleOnTop);
/* 822 */             this.operates.pop();
/* 823 */             this.querys.push(q);
/*     */           }
/*     */         }
/*     */ 
/* 827 */         if ((doPeek) && (this.operates.empty())) {
/* 828 */           this.operates.push(e);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 834 */     while (!this.operates.isEmpty()) {
/* 835 */       com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element eleOnTop = (com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element)this.operates.pop();
/* 836 */       Query q = toQuery(eleOnTop);
/* 837 */       this.querys.push(q);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Query toQuery(com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element op)
/*     */   {
/* 847 */     BooleanQuery resultQuery = new BooleanQuery();
/* 848 */     if (this.querys.size() < 2) {
/* 849 */       throw new IllegalStateException("表达式异常：SubQuery 个数不匹配");
/*     */     }
/* 851 */     Query q2 = (Query)this.querys.pop();
/* 852 */     Query q1 = (Query)this.querys.pop();
/* 853 */     if ('&' == op.type) {
/* 854 */       if ((q1 instanceof TermQuery)) {
/* 855 */         resultQuery.add(q1, BooleanClause.Occur.MUST);
/*     */       } else {
/* 857 */         BooleanClause[] clauses = ((BooleanQuery)q1).getClauses();
/* 858 */         if (clauses[0].getOccur() == BooleanClause.Occur.MUST) {
/* 859 */           for (BooleanClause c : clauses)
/* 860 */             resultQuery.add(c);
/*     */         }
/*     */         else {
/* 863 */           resultQuery.add(q1, BooleanClause.Occur.MUST);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 868 */       if ((q2 instanceof TermQuery)) {
/* 869 */         resultQuery.add(q2, BooleanClause.Occur.MUST);
/*     */       } else {
/* 871 */         BooleanClause[] clauses = ((BooleanQuery)q2).getClauses();
/* 872 */         if (clauses[0].getOccur() == BooleanClause.Occur.MUST) {
/* 873 */           for (BooleanClause c : clauses)
/* 874 */             resultQuery.add(c);
/*     */         }
/*     */         else {
/* 877 */           resultQuery.add(q2, BooleanClause.Occur.MUST);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/* 882 */     else if ('|' == op.type) {
/* 883 */       if ((q1 instanceof TermQuery)) {
/* 884 */         resultQuery.add(q1, BooleanClause.Occur.SHOULD);
/*     */       } else {
/* 886 */         BooleanClause[] clauses = ((BooleanQuery)q1).getClauses();
/* 887 */         if (clauses[0].getOccur() == BooleanClause.Occur.SHOULD) {
/* 888 */           for (BooleanClause c : clauses)
/* 889 */             resultQuery.add(c);
/*     */         }
/*     */         else {
/* 892 */           resultQuery.add(q1, BooleanClause.Occur.SHOULD);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 897 */       if ((q2 instanceof TermQuery)) {
/* 898 */         resultQuery.add(q2, BooleanClause.Occur.SHOULD);
/*     */       } else {
/* 900 */         BooleanClause[] clauses = ((BooleanQuery)q2).getClauses();
/* 901 */         if (clauses[0].getOccur() == BooleanClause.Occur.SHOULD) {
/* 902 */           for (BooleanClause c : clauses)
/* 903 */             resultQuery.add(c);
/*     */         }
/*     */         else {
/* 906 */           resultQuery.add(q2, BooleanClause.Occur.SHOULD);
/*     */         }
/*     */       }
/*     */     }
/* 910 */     else if ('-' == op.type)
/*     */     {
/* 912 */       if ((q1 instanceof TermQuery)) {
/* 913 */         resultQuery.add(q1, BooleanClause.Occur.MUST);
/*     */       } else {
/* 915 */         BooleanClause[] clauses = ((BooleanQuery)q1).getClauses();
/* 916 */         for (BooleanClause c : clauses) {
/* 917 */           resultQuery.add(c);
/*     */         }
/*     */       }
/*     */ 
/* 921 */       resultQuery.add(q2, BooleanClause.Occur.MUST_NOT);
/*     */     }
/* 923 */     return resultQuery;
/*     */   }
/*     */ 
/*     */   private int compare(com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element e1, com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser.Element e2)
/*     */   {
/* 933 */     if ('&' == e1.type) {
/* 934 */       if ('&' == e2.type) {
/* 935 */         return 0;
/*     */       }
/* 937 */       return 1;
/*     */     }
/* 939 */     if ('|' == e1.type) {
/* 940 */       if ('&' == e2.type)
/* 941 */         return -1;
/* 942 */       if ('|' == e2.type) {
/* 943 */         return 0;
/*     */       }
/* 945 */       return 1;
/*     */     }
/*     */ 
/* 948 */     if ('-' == e2.type) {
/* 949 */       return 0;
/*     */     }
/* 951 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.lucene.IKQueryParser.ExpressionParser
 * JD-Core Version:    0.6.2
 */