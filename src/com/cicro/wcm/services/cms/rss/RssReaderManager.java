/*    */ package com.cicro.wcm.services.cms.rss;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*    */ import com.sun.syndication.feed.synd.SyndEntry;
/*    */ import com.sun.syndication.feed.synd.SyndFeed;
/*    */ import com.sun.syndication.io.SyndFeedInput;
/*    */ import com.sun.syndication.io.XmlReader;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RssReaderManager
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 27 */     getRssInfoList("http://news.163.com/special/00011K6L/rss_newstop.xml", 1, 20);
/*    */   }
/*    */ 
/*    */   public static List<InfoBean> getRssInfoList(String url, int currentPage, int pageSize)
/*    */   {
/* 34 */     List infos = new ArrayList();
/*    */     try {
/* 36 */       URLConnection feedUrl = new URL(url).openConnection();
/* 37 */       SyndFeedInput input = new SyndFeedInput();
/* 38 */       SyndFeed feed = input.build(new XmlReader(feedUrl));
/* 39 */       List entryList = feed.getEntries();
/* 40 */       int start = (currentPage - 1) * pageSize;
/* 41 */       int end = currentPage * pageSize > entryList.size() ? entryList.size() : currentPage * pageSize;
/*    */ 
/* 43 */       for (int i = start; i < end; i++) {
/* 44 */         SyndEntry entry = (SyndEntry)entryList.get(i);
/* 45 */         String released_dtime = "";
/* 46 */         if (entry.getPublishedDate() != null)
/* 47 */           released_dtime = DateUtil.getDateTimeString(entry.getPublishedDate());
/* 48 */         String title = entry.getTitle();
/* 49 */         String content_url = entry.getLink();
/* 50 */         InfoBean info = new InfoBean();
/* 51 */         info.setReleased_dtime(released_dtime);
/* 52 */         info.setTitle(title);
/* 53 */         info.setContent_url(content_url);
/* 54 */         infos.add(info);
/*    */       }
/* 56 */       return infos;
/*    */     } catch (Exception e) {
/* 58 */       e.printStackTrace();
/* 59 */     }return infos;
/*    */   }
/*    */ 
/*    */   public static int getRssInfoCount(String url)
/*    */   {
/* 65 */     List infos = new ArrayList();
/*    */     try {
/* 67 */       URLConnection feedUrl = new URL(url).openConnection();
/* 68 */       SyndFeedInput input = new SyndFeedInput();
/* 69 */       SyndFeed feed = input.build(new XmlReader(feedUrl));
/* 70 */       List entryList = feed.getEntries();
/* 71 */       if (entryList != null)
/*    */       {
/* 73 */         return entryList.size();
/*    */       }
/*    */ 
/* 77 */       return 0;
/*    */     }
/*    */     catch (Exception e) {
/* 80 */       e.printStackTrace();
/* 81 */     }return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.rss.RssReaderManager
 * JD-Core Version:    0.6.2
 */