/*     */ package com.cicro.util.labelUtil;
/*     */ 
/*     */ import com.cicro.util.HtmlRegexpUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AutoPagerHandl
/*     */ {
/*     */   public static String splitContent(String content, int page_num, int text_count)
/*     */   {
/*  29 */     int start_num = 0;
/*  30 */     int num = text_count;
/*  31 */     int real_num = text_count;
/*  32 */     boolean is_turn = true;
/*  33 */     int page_count = 1;
/*  34 */     String prev_content = "";
/*  35 */     while (is_turn)
/*     */     {
/*  37 */       real_num = cutContentStr(num, content);
/*     */ 
/*  39 */       if (real_num > content.length())
/*     */       {
/*  41 */         prev_content = content.substring(start_num);
/*  42 */         break;
/*     */       }
/*  44 */       prev_content = content.substring(start_num, real_num);
/*  45 */       content = content.substring(real_num);
/*     */ 
/*  47 */       if (page_count == page_num)
/*     */       {
/*     */         break;
/*     */       }
/*  51 */       if (num > content.length())
/*     */       {
/*  53 */         prev_content = content.substring(start_num);
/*  54 */         is_turn = false;
/*     */       }
/*     */ 
/*  57 */       String htmlFilter = "";
/*  58 */       htmlFilter = HtmlRegexpUtil.filterHtml(prev_content).replaceAll("&nbsp;", "");
/*  59 */       if (htmlFilter.equals(""))
/*     */       {
/*     */         break;
/*     */       }
/*  63 */       page_count++;
/*     */     }
/*  65 */     if (page_num == 0) {
/*  66 */       return page_count;
/*     */     }
/*     */ 
/*  69 */     if (page_num > 1)
/*     */     {
/*  72 */       return TagsChecker.fix(prev_content);
/*     */     }
/*     */ 
/*  75 */     return prev_content;
/*     */   }
/*     */ 
/*     */   public static int cutContentStr(int num, String s)
/*     */   {
/*  82 */     String cut_str = "";
/*  83 */     String start_str = "";
/*  84 */     if (s.length() > num + 1) {
/*  85 */       cut_str = s.substring(num, num + 1);
/*  86 */       start_str = s.substring(0, num);
/*     */     }
/*     */     else {
/*  89 */       return num;
/*     */     }
/*     */ 
/*  92 */     if (!isInfoEnd(start_str))
/*     */     {
/*  95 */       num++;
/*  96 */       return cutContentStr(num, s);
/*     */     }
/*     */ 
/*  99 */     return num;
/*     */   }
/*     */ 
/*     */   public static boolean isInfoEnd(String content)
/*     */   {
/* 104 */     String[] regEx2 = { "<p>", "</p>", "<br>", "<br/>" };
/* 105 */     List regExList = Arrays.asList(regEx2);
/* 106 */     boolean result = false;
/* 107 */     for (String pstr : regExList) {
/* 108 */       if (content.toLowerCase().trim().replaceAll(" ", "").endsWith(pstr)) {
/* 109 */         result = true;
/* 110 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 114 */     if (result)
/*     */     {
/* 116 */       return true;
/*     */     }
/*     */ 
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 125 */     String content = "<p>　昨日召开的全市政务服务工作培训会议透露,今年全市将推广实施市与开发区（区、县）&ldquo;大联动&rdquo;并联审批机制, 这将有效解决办事企业和群众在开发区（区、县）和市政务中心&ldquo;两边跑&rdquo;、审批难的问题。</p><p style=\"line-height: 18px; margin-top: 2px; margin-bottom: 2px\">　　我市从2008年起，在市级层面开始筹建政务服务中心，推行政务服务制度，全市政务服务体系建设取得了长足进步。截至目前，全市11个区县、4个开发区建立了政务服务中心，10 个市级部门建立了政务大厅，379个乡镇街办（站所）建立了便民服务大厅，343个社区、村（组）建立了服务代理点。</p><p style=\"line-height: 18px; margin-top: 2px; margin-bottom: 2px\">　　据了解，今年我市将继续扩大覆盖面，加快四级政务服务体系建设，年底基本建成覆盖市、区（县）、乡镇（街道）、村组（社区）的四级政务服务体系。会议要求周至、户县，经开区、曲江新区、沣渭新区、航空基地等还没有成立政务中心的县、开发区，务必在下半年完成筹建任务，年底前正式启动运行；已成立政务中心的区县和开发区，进一步搞好规范化建设，做到审批事项应进必进，审批流程科学规范。同时，指导乡镇（街道）、村组（社区）做好基层服务机构建设，尽最大努力方便群众办事。</p><p style=\"line-height: 18px; margin-top: 2px; margin-bottom: 2px\">　　此外，我市还将试行市政务服务中心与开发区（区、县）&ldquo;联动式&rdquo;并联审批机制。据介绍，市政务服务中心通过调研，初步建立了与开发区（区、县）&ldquo;联动式&rdquo;并联审批机制，对在开发区（区、县）和市政务中心都有审批内容的事项，通过联席会议，联合受理、同步办理，办结后统一发送审批结果，能够有效解决办事企业和群众在开发区（区、县）和市政务中心&ldquo;两边跑&rdquo;，审批难的问题。今年，将在全市推广实施市与开发区（区、县）&ldquo;大联动&rdquo;并联审批机制。</p><p>&nbsp;</p>";
/* 126 */     int page_num = 1;
/* 127 */     int text_count = 200;
/* 128 */     String str = splitContent(content, page_num, text_count);
/*     */ 
/* 130 */     System.out.println(str);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.labelUtil.AutoPagerHandl
 * JD-Core Version:    0.6.2
 */